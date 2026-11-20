# Clasificación de columnas al descubrir un log

En **Descubrir Log**, seleccionar el CSV y pulsar **Descubrir**. El diálogo muestra
una fila por columna del archivo, en su orden original, con ejemplos y un selector
de significado. Se sugieren coincidencias por nombre exacto sin distinguir
mayúsculas; no se asignan columnas arbitrarias a campos faltantes.

Las opciones provienen de `GET /discoverer/column-types`:

| Significado | Rol en minería de procesos |
| --- | --- |
| ID | Case ID |
| Action | Activity |
| Activity Type | Activity |
| Activity | Activity |
| Timestamp | Timestamp |
| Career | Other |
| Plan | Other |
| Curricular Unit | Activity |
| Course Edition | Other |
| Course Year | Other |
| Grade | Other |
| Credits | Other |
| Unified Curricular Unit | Activity |
| Unified Curricular Unit Name | Activity |

Cada significado se asigna como máximo una vez. **Sin clasificar (Other)** conserva
una columna adicional sin atribuirle significado académico. Si su nombre coincide
con un atributo reservado, se guarda con prefijo `source:` para evitar reemplazar
la clasificación o la actividad generada.

El rol Activity identifica atributos relacionados con la actividad. La composición
del nombre minado conserva exactamente el orden preexistente de la API Python:

```text
Action - Activity Type - Unified Curricular Unit [- Unified Curricular Unit Name]
```

Si no están asignadas ambas columnas Action y Activity Type, se admite una columna
Activity que ya contenga la etiqueta del evento:

```text
Activity - Unified Curricular Unit [- Unified Curricular Unit Name]
```

Cuando están asignadas las tres, Action + Activity Type tiene prioridad. La etiqueta
Activity original se conserva como atributo `source:Activity` (con otro prefijo si
ese nombre ya existe), separado de la actividad compuesta que usa minería.

Unified Curricular Unit es opcional: se deriva de Curricular Unit cuando no se
asigna. También se usa Curricular Unit en eventos cuyo código unificado está vacío.
Unified Curricular Unit Name es opcional y se omite del nombre compuesto cuando
no está asignado o su valor está vacío.

`Curricular Unit` sigue disponible por separado para estadísticas y no se agrega
al nombre compuesto. El orden del CSV no cambia esta composición. El diálogo
muestra una vista previa usando el primer evento.

Se requieren ID, Timestamp y Curricular Unit, además de Action + Activity Type o
una columna Activity. Los restantes son opcionales para descubrir, pero determinados análisis requieren
datos adicionales. Si faltan, las estadísticas devuelven 400 con los nombres de
los campos necesarios, en lugar de fallar por atributos inexistentes.
Un Activity genérico no se divide ni se interpreta automáticamente como Action y
Activity Type para estadísticas que necesitan esos significados específicos.

## Contrato HTTP

El servicio de minería usa normalmente el puerto 9000. Los clientes existentes
conservan el contrato `POST /discoverer/0`, multipart con `file`, `name` y
`column_mapping`. El diccionario sigue siendo **significado → nombre de columna**:

```json
{
  "ID": "Alumno",
  "Action": "Accion",
  "Activity Type": "Tipo",
  "Timestamp": "Fecha",
  "Curricular Unit": "Codigo",
  "Unified Curricular Unit": "UC unificada",
  "Unified Curricular Unit Name": "Nombre unificado",
  "Grade": "Nota"
}
```

La UI invierte las selecciones por columna antes de enviarlas, sin cambiar ese
contrato. Sin mapeo, siguen funcionando los encabezados canónicos del servicio.
Asignaciones inválidas o columnas requeridas faltantes devuelven 400 antes de
iniciar el descubrimiento. La respuesta válida sigue siendo `{uuid, status:
"started"}` y el procesamiento continúa en segundo plano.

`GET /discoverer/column-types` devuelve `column_types` (name, role, required),
`activity_order`, `activity_alternatives`, `fallbacks` y `optional_activity_fields`.
Los roles técnicos son `case_id`, `activity`, `timestamp` y `other`. `required`
identifica los tres campos obligatorios incondicionales; las alternativas de
actividad se validan como grupos, en su orden de prioridad.

Los atributos clasificados se guardan con sus nombres canónicos en XES para que
las estadísticas existentes puedan usarlos. ID y códigos se leen como texto
para conservar ceros iniciales. Los tipos de Grade, Credits, Course Edition y
Course Year se aplican después de mapear los nombres originales.

El mapeo se conserva en `mappings/<uuid>.json`, separado de los modelos, con
versión, nombres originales, significados, roles, nombres de atributos, orden de
actividad efectivo y campos derivados. `GET /log-columns/<uuid>` mantiene su campo `columns` y agrega `mapping`
cuando existe esa información. Logs anteriores sin metadatos siguen devolviendo
solamente sus columnas.

## Verificación

Desde la raíz del repositorio, con las dependencias de `requirements.txt`:

```sh
python -m unittest discover -s proyecto/PM_microservice/tests -v
node --test proyecto/front/tests/discoverCsv.test.cjs
```

Las pruebas cubren CSV con comillas, BOM y valores multilínea; clasificación y
tipos; ceros iniciales; orden fijo de actividad; mapeos inválidos; persistencia de
metadatos; generación de modelos; lectura XES y estadísticas académicas.
