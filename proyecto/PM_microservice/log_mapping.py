"""Semantic column mapping shared by discovery and academic analysis.

The HTTP mapping remains {semantic_name: csv_header} for existing clients.
Source order never determines the mined activity: ACTIVITY_ORDER is the existing
Python API's composition, while all academic attributes remain available in XES.
"""
import csv
import json
from pathlib import Path

import pandas as pd


COLUMN_ROLES = {
    'ID': 'case_id',
    'Action': 'activity',
    'Activity Type': 'activity',
    'Activity': 'activity',
    'Timestamp': 'timestamp',
    'Career': 'other',
    'Plan': 'other',
    'Curricular Unit': 'activity',
    'Course Edition': 'other',
    'Course Year': 'other',
    'Grade': 'other',
    'Credits': 'other',
    'Unified Curricular Unit': 'activity',
    'Unified Curricular Unit Name': 'activity',
}
ACTIVITY_ORDER = ('Action', 'Activity Type', 'Unified Curricular Unit', 'Unified Curricular Unit Name')
REQUIRED_COLUMNS = ('ID', 'Timestamp', 'Curricular Unit')
ACTIVITY_ALTERNATIVES = (('Action', 'Activity Type'), ('Activity',))
NUMERIC_COLUMNS = {'Course Edition': 'Int64', 'Course Year': 'Int64',
                   'Grade': 'Float64', 'Credits': 'Int64'}
RESERVED_COLUMNS = {'Activity', 'concept:name', 'case:concept:name', 'time:timestamp'}


class ColumnMappingError(ValueError):
    """A CSV or column assignment that the user can correct before discovery."""


def column_types():
    return {
        'column_types': [{'name': name, 'role': role, 'required': name in REQUIRED_COLUMNS}
                         for name, role in COLUMN_ROLES.items()],
        'activity_order': list(ACTIVITY_ORDER),
        'activity_alternatives': [list(names) for names in ACTIVITY_ALTERNATIVES],
        'fallbacks': {'Unified Curricular Unit': 'Curricular Unit'},
        'optional_activity_fields': ['Unified Curricular Unit Name'],
    }


def activity_columns(mapping):
    for prefix in ACTIVITY_ALTERNATIVES:
        if all(name in mapping for name in prefix):
            unit = 'Unified Curricular Unit' if 'Unified Curricular Unit' in mapping else 'Curricular Unit'
            name = ['Unified Curricular Unit Name'] if 'Unified Curricular Unit Name' in mapping else []
            return [*prefix, unit, *name]
    raise ColumnMappingError('Asigne Action y Activity Type, o una columna Activity.')


def read_headers(file_path):
    try:
        with open(file_path, encoding='utf-8-sig', newline='') as source:
            headers = next((row for row in csv.reader(source)
                            if row and not (len(row) == 1 and not row[0].strip())), [])
    except (UnicodeError, csv.Error) as error:
        raise ColumnMappingError('El archivo debe ser un CSV UTF-8 separado por comas.') from error
    headers = [header.strip() for header in headers]
    if not headers or any(not header for header in headers):
        raise ColumnMappingError('El CSV debe tener un encabezado no vacío por columna.')
    if len(set(headers)) != len(headers):
        raise ColumnMappingError('El CSV tiene nombres de columna repetidos.')
    return headers


def normalize_mapping(headers, column_mapping=None):
    if column_mapping is not None and not isinstance(column_mapping, dict):
        raise ColumnMappingError('column_mapping debe ser un objeto {significado: columna del CSV}.')
    # An absent/empty mapping keeps the existing API's canonical-header behavior.
    mapping = column_mapping or {name: name for name in headers if name in COLUMN_ROLES}
    seen = set()
    for target, source in mapping.items():
        if target not in COLUMN_ROLES:
            raise ColumnMappingError(f'Significado de columna desconocido: {target}')
        if not isinstance(source, str) or source not in headers:
            raise ColumnMappingError(f'No existe la columna del CSV asignada a {target}: {source}')
        if source in seen:
            raise ColumnMappingError(f'La columna {source} tiene más de un significado asignado.')
        seen.add(source)
    missing = [name for name in REQUIRED_COLUMNS if name not in mapping]
    if missing:
        raise ColumnMappingError('Falta asignar las columnas requeridas por descubrimiento: ' + ', '.join(missing))
    activity_columns(mapping)
    return mapping


def prepare_event_log(file_path, column_mapping=None):
    headers = read_headers(file_path)
    mapping = normalize_mapping(headers, column_mapping)
    try:
        # Read source names as text first, so renamed identifiers retain zeros and
        # numeric conversion happens according to semantics, not original headers.
        frame = pd.read_csv(file_path, sep=',', encoding='utf-8-sig', dtype=str)
    except (ValueError, pd.errors.ParserError) as error:
        raise ColumnMappingError(f'No se pudo leer el CSV: {error}') from error
    if len(frame.columns) != len(headers) or not isinstance(frame.index, pd.RangeIndex):
        raise ColumnMappingError('Las filas del CSV no coinciden con su encabezado.')
    frame.columns = headers
    if frame.empty:
        raise ColumnMappingError('El CSV debe contener al menos un evento.')
    reverse = {source: target for target, source in mapping.items()}
    occupied = set(COLUMN_ROLES) | RESERVED_COLUMNS
    rename = {}
    columns = []
    for source in headers:
        semantic = reverse.get(source)
        attribute = semantic or source
        if semantic is None:
            while attribute in occupied:
                attribute = 'source:' + attribute
        occupied.add(attribute)
        rename[source] = attribute
        columns.append({'source': source, 'semantic': semantic,
                        'role': COLUMN_ROLES.get(semantic, 'other'), 'attribute': attribute})
    frame = frame.rename(columns=rename)
    if 'Activity' in mapping:
        # Activity is also the generated PM attribute. Keep the uploaded label
        # separately, including when Action + Activity Type take precedence.
        attribute = 'source:Activity'
        while attribute in frame.columns:
            attribute = 'source:' + attribute
        frame[attribute] = frame['Activity']
        next(column for column in columns if column['semantic'] == 'Activity')['attribute'] = attribute
    derived = {}
    if 'Unified Curricular Unit' not in mapping:
        frame['Unified Curricular Unit'] = frame['Curricular Unit']
        derived['Unified Curricular Unit'] = 'Curricular Unit'
    else:
        missing_unit = frame['Unified Curricular Unit'].isna() | frame['Unified Curricular Unit'].fillna('').str.strip().eq('')
        frame.loc[missing_unit, 'Unified Curricular Unit'] = frame.loc[missing_unit, 'Curricular Unit']
    for name, dtype in NUMERIC_COLUMNS.items():
        if name not in mapping:
            continue
        try:
            frame[name] = pd.to_numeric(frame[name], errors='raise').astype(dtype)
        except (ValueError, TypeError, OverflowError) as error:
            raise ColumnMappingError(f'La columna asignada a {name} contiene números inválidos.') from error
    prefix = next(names for names in ACTIVITY_ALTERNATIVES if all(name in mapping for name in names))
    for name in ('ID', 'Timestamp', *prefix):
        if frame[name].isna().any() or frame[name].astype(str).str.strip().eq('').any():
            raise ColumnMappingError(f'La columna asignada a {name} contiene valores vacíos.')
    metadata = {'version': 1, 'columns': columns, 'column_mapping': mapping,
                'activity_order': activity_columns(mapping), 'derived_fields': derived}
    return frame, metadata


def build_activity(frame):
    """Preserve API order; accept an existing label and an optional unit name."""
    names = activity_columns(frame.columns)
    if 'Unified Curricular Unit Name' not in names:
        return frame[names].astype(str).agg(' - '.join, axis=1)
    base = frame[names[:-1]].astype(str).agg(' - '.join, axis=1)
    name = frame['Unified Curricular Unit Name'].fillna('').astype(str)
    return base + (' - ' + name).where(name.str.strip().ne(''), '')


def save_mapping(case_id, metadata, directory='./mappings'):
    path = Path(directory) / (case_id + '.json')
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(metadata, ensure_ascii=False, indent=2), encoding='utf-8')


def load_mapping(case_id, directory='./mappings'):
    path = Path(directory) / (case_id + '.json')
    return json.loads(path.read_text(encoding='utf-8')) if path.is_file() else None


def require_analysis_columns(frame, names):
    missing = [name for name in names if name not in frame.columns]
    if missing:
        raise ColumnMappingError('El log no tiene los datos clasificados que requiere este análisis: ' + ', '.join(missing))
