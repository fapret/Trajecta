import io
import json
import os
from pathlib import Path
import sys
import tempfile
import unittest
from unittest.mock import patch

import pandas as pd

sys.path.insert(0, str(Path(__file__).resolve().parents[1]))
from log_mapping import (ACTIVITY_ORDER, COLUMN_ROLES, ColumnMappingError,
                         build_activity, column_types, prepare_event_log)


HEADERS = ['student', 'action', 'kind', 'when', 'career', 'plan', 'unit',
           'edition', 'year', 'grade', 'credits', 'unified', 'unit name', 'extra']
CANONICAL_HEADERS = [name for name in COLUMN_ROLES if name != 'Activity']
MAPPING = dict(zip(CANONICAL_HEADERS, HEADERS))


def csv_bytes(rows=None, headers=HEADERS):
    import csv
    out = io.StringIO(newline='')
    writer = csv.writer(out)
    writer.writerow(headers)
    writer.writerows(rows or [['0001', 'Evaluation', 'Exam', 'Thu Nov 05 03:00:00 UTC 2026',
                              'Computing', '2026', '001', '1', '2026', '4.5', '10',
                              'U001', 'Algebra, "I"\nAdvanced', 'kept']])
    return out.getvalue().encode('utf-8-sig')


class MappingTests(unittest.TestCase):
    def setUp(self):
        self.tmp = tempfile.TemporaryDirectory()
        self.addCleanup(self.tmp.cleanup)
        self.file = Path(self.tmp.name) / 'log.csv'
        self.file.write_bytes(csv_bytes())

    def test_renamed_fields_keep_meaning_types_and_leading_zeros(self):
        frame, metadata = prepare_event_log(self.file, MAPPING)
        self.assertEqual(frame.at[0, 'ID'], '0001')
        self.assertEqual(frame.at[0, 'Curricular Unit'], '001')
        self.assertEqual(frame.at[0, 'Grade'], 4.5)
        self.assertEqual(frame.at[0, 'Course Year'], 2026)
        self.assertEqual(frame.at[0, 'Unified Curricular Unit Name'], 'Algebra, "I"\nAdvanced')
        self.assertEqual(metadata['column_mapping'], MAPPING)
        self.assertEqual(metadata['columns'][-1], {'source':'extra', 'semantic':None, 'role':'other', 'attribute':'extra'})

    def test_source_order_does_not_change_api_activity(self):
        frame, _ = prepare_event_log(self.file, MAPPING)
        reordered = frame[list(reversed(frame.columns))]
        self.assertEqual(build_activity(reordered)[0], 'Evaluation - Exam - U001 - Algebra, "I"\nAdvanced')
        self.assertNotIn('001 - U001', build_activity(reordered)[0])
        self.assertEqual(column_types()['activity_order'], list(ACTIVITY_ORDER))
        self.assertEqual(next(c for c in column_types()['column_types'] if c['name']=='Curricular Unit')['role'], 'activity')

    def test_legacy_canonical_headers_work_without_mapping(self):
        self.file.write_bytes(csv_bytes(headers=[*CANONICAL_HEADERS, 'extra']))
        frame, _ = prepare_event_log(self.file)
        self.assertEqual(frame.at[0,'ID'], '0001')

    def test_unified_code_falls_back_to_unit_and_name_is_optional(self):
        mapping = {key:value for key,value in MAPPING.items()
                   if key not in {'Unified Curricular Unit','Unified Curricular Unit Name'}}
        frame, metadata = prepare_event_log(self.file,mapping)
        self.assertEqual(frame.at[0,'Unified Curricular Unit'],'001')
        self.assertEqual(build_activity(frame)[0],'Evaluation - Exam - 001')
        self.assertEqual(metadata['derived_fields'],{'Unified Curricular Unit':'Curricular Unit'})
        self.assertEqual(metadata['activity_order'],['Action','Activity Type','Curricular Unit'])

    def test_empty_unified_code_falls_back_per_event_and_empty_name_is_omitted(self):
        self.file.write_bytes(csv_bytes(rows=[['0001','Evaluation','Exam','Thu Nov 05 03:00:00 UTC 2026',
                                            'Computing','2026','001','1','2026','4','10','','   ','']]))
        frame, _ = prepare_event_log(self.file,MAPPING)
        self.assertEqual(build_activity(frame)[0],'Evaluation - Exam - 001')

    def test_existing_activity_replaces_missing_action_pair_and_keeps_raw_label(self):
        self.file.write_bytes(b'ID,Timestamp,Curricular Unit,Activity\n0001,Thu Nov 05 03:00:00 UTC 2026,001,Passed exam\n')
        frame, metadata = prepare_event_log(self.file)
        self.assertEqual(build_activity(frame)[0],'Passed exam - 001')
        frame['Activity'] = build_activity(frame)
        self.assertEqual(frame.at[0,'source:Activity'],'Passed exam')
        self.assertEqual(next(c for c in metadata['columns'] if c['semantic']=='Activity')['attribute'],'source:Activity')

    def test_complete_action_pair_takes_precedence_over_existing_activity(self):
        self.file.write_bytes(b'ID,Timestamp,Curricular Unit,Action,Activity Type,Activity\n0001,Thu Nov 05 03:00:00 UTC 2026,001,Evaluation,Exam,Original label\n')
        frame, _ = prepare_event_log(self.file)
        self.assertEqual(build_activity(frame)[0],'Evaluation - Exam - 001')
        self.assertEqual(frame.at[0,'source:Activity'],'Original label')

    def test_incomplete_action_pair_needs_existing_activity(self):
        mapping={key:value for key,value in MAPPING.items() if key!='Activity Type'}
        with self.assertRaisesRegex(ColumnMappingError,'Activity'):
            prepare_event_log(self.file,mapping)

    def test_unknown_and_repeated_source_assignments_are_rejected(self):
        for mapping in [{**MAPPING, 'Career':'student'}, {**MAPPING, 'Other':'extra'}, {**MAPPING, 'Grade':'missing'}]:
            with self.subTest(mapping=mapping), self.assertRaises(ColumnMappingError):
                prepare_event_log(self.file, mapping)

    def test_missing_required_fields_and_repeated_headers_are_rejected(self):
        mapping = {key:value for key,value in MAPPING.items() if key != 'Action'}
        with self.assertRaisesRegex(ColumnMappingError, 'Action'):
            prepare_event_log(self.file, mapping)
        self.file.write_text('ID,ID\n1,2\n',encoding='utf-8')
        with self.assertRaisesRegex(ColumnMappingError, 'repetidos'):
            prepare_event_log(self.file)

    def test_metadata_is_optional_but_invalid_mapped_numbers_are_rejected(self):
        optional = {'Career','Plan','Course Edition','Course Year','Grade','Credits'}
        mapping = {key:value for key,value in MAPPING.items() if key not in optional}
        frame, metadata = prepare_event_log(self.file, mapping)
        self.assertNotIn('Grade', frame.columns)
        self.assertIn('grade', frame.columns)
        self.assertEqual(metadata['columns'][9]['role'], 'other')
        self.file.write_bytes(csv_bytes(rows=[['0001','Evaluation','Exam','Thu Nov 05 03:00:00 UTC 2026',
                                            'Computing','2026','001','1','2026','bad','10','U001','Algebra','']]))
        with self.assertRaisesRegex(ColumnMappingError, 'Grade'):
            prepare_event_log(self.file, MAPPING)

    def test_unclassified_reserved_names_cannot_override_semantics(self):
        self.file.write_bytes(csv_bytes(headers=[*HEADERS[:-1], 'Activity']))
        frame, metadata = prepare_event_log(self.file, MAPPING)
        self.assertEqual(frame.at[0,'source:Activity'], 'kept')
        self.assertNotIn('Activity', frame.columns)
        self.file.write_bytes(csv_bytes(headers=[*CANONICAL_HEADERS, 'extra']))
        mapping = {name:name for name in CANONICAL_HEADERS if name != 'Grade'}
        frame, _ = prepare_event_log(self.file, mapping)
        self.assertNotIn('Grade',frame.columns)
        self.assertEqual(frame.at[0,'source:Grade'],'4.5')


class DiscoveryApiTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.tmp = tempfile.TemporaryDirectory()
        cls.previous_cwd = os.getcwd()
        os.chdir(cls.tmp.name)
        import discoverer_threaded
        import CU_stats
        import Plan_stats
        cls.discovery = discoverer_threaded
        cls.client = discoverer_threaded.app.test_client()
        cls.cu_client = CU_stats.app.test_client()
        cls.plan_client = Plan_stats.app.test_client()

    @classmethod
    def tearDownClass(cls):
        os.chdir(cls.previous_cwd)
        cls.tmp.cleanup()

    def test_column_type_api_matches_fixed_discovery_order(self):
        data = self.client.get('/discoverer/column-types').get_json()
        self.assertEqual(len(data['column_types']), 14)
        self.assertEqual(data['activity_order'], list(ACTIVITY_ORDER))

    def test_discovery_pipeline_keeps_activity_order_and_writes_models(self):
        rows = [
            ['0001','Inscription','Course','Thu Nov 05 03:00:00 UTC 2026','Computing','2026','001','1','2026','','10','U001','Algebra','extra'],
            ['0001','Evaluation','Exam','Fri Nov 06 03:00:00 UTC 2026','Computing','2026','001','1','2026','4','10','U001','Algebra','extra'],
        ]
        path = Path('imports','pipeline.csv'); path.write_bytes(csv_bytes(rows))
        self.discovery.run_discovery(str(path),'pipeline',0,MAPPING)
        for output in ['discovers/pipeline.xes','dfg/pipeline.dfg','pnml/pipeline_alpha.pnml',
                       'pnml/pipeline_heuristics.pnml','pnml/pipeline_inductive.pnml',
                       'bpmn/pipeline.bpmn','mappings/pipeline.json']:
            self.assertTrue(Path(output).is_file(),output)

    def test_compact_log_with_activity_and_no_unified_columns_is_discovered(self):
        path=Path('imports','compact.csv')
        path.write_text('ID,Timestamp,Curricular Unit,Activity\n'
                        '0001,Thu Nov 05 03:00:00 UTC 2026,001,Enroll\n'
                        '0001,Fri Nov 06 03:00:00 UTC 2026,001,Pass\n',encoding='utf-8')
        self.discovery.run_discovery(str(path),'compact',0)
        self.assertTrue(Path('bpmn/compact.bpmn').is_file())
        import pm4py
        frame=pm4py.read_xes('discovers/compact.xes')
        self.assertEqual(frame['concept:name'].tolist(),['Enroll - 001','Pass - 001'])
        self.assertEqual(frame['source:Activity'].tolist(),['Enroll','Pass'])

    def test_bad_mapping_returns_400_without_starting_a_job(self):
        with patch.object(self.discovery.threading.Thread, 'start') as start:
            response = self.client.post('/discoverer/0', data={'file':(io.BytesIO(csv_bytes()),'log.csv'),
                                        'column_mapping':json.dumps({'ID':'student'})})
            self.assertEqual(response.status_code, 400)
            self.assertIn('Timestamp', response.get_json()['error'])
            start.assert_not_called()
        response = self.client.post('/discoverer/0', data={'file':(io.BytesIO(csv_bytes()),'log.csv'), 'column_mapping':'invalid json'})
        self.assertEqual(response.status_code,400)

    def test_upload_persists_semantics_and_exposes_them_with_headers(self):
        with patch.object(self.discovery.threading.Thread, 'start'):
            response = self.client.post('/discoverer/0', data={'file':(io.BytesIO(csv_bytes()),'log.csv'),
                'name':'classified', 'column_mapping':json.dumps(MAPPING)})
        self.assertEqual(response.status_code,200)
        case_id = response.get_json()['uuid']
        info = self.client.get('/log-columns/'+case_id).get_json()
        self.assertEqual(info['columns'], HEADERS)
        self.assertEqual(info['mapping']['column_mapping'], MAPPING)
        self.assertTrue(Path('mappings',case_id+'.json').is_file())

    def test_xes_round_trip_and_academic_statistics_use_semantic_fields(self):
        import pm4py
        rows = []
        for action, kind, grade in [('Inscription','Plan',''),('Inscription','Course',''),('Evaluation','Course','6'),('Evaluation','Exam','4')]:
            rows.append(['0001',action,kind,'Thu Nov 05 03:00:00 UTC 2026','Computing','2026',
                         '001','1','2026',grade,'10','U001','Algebra','preserved'])
        path = Path('imports','stats.csv'); path.write_bytes(csv_bytes(rows))
        frame, metadata = prepare_event_log(path,MAPPING)
        frame['Activity'] = build_activity(frame)
        frame = pm4py.format_dataframe(frame,case_id='ID',activity_key='Activity',timestamp_key='Timestamp',
                                      timest_format='%a %b %d %H:%M:%S %Z %Y')
        pm4py.write_xes(frame,'discovers/stats.xes')
        loaded = pm4py.read_xes('discovers/stats.xes')
        self.assertEqual(loaded.iloc[-1]['Curricular Unit'],'001')
        self.assertEqual(loaded.iloc[-1]['extra'],'preserved')
        self.assertEqual(loaded.iloc[-1]['concept:name'],'Evaluation - Exam - U001 - Algebra')
        self.assertEqual(float(loaded.iloc[-1]['Grade']),4.0)
        cu = self.cu_client.get('/custats/stats/001')
        self.assertEqual(cu.status_code,200,cu.get_json())
        self.assertEqual(cu.get_json()['didExamAmount'],1)
        self.assertEqual(cu.get_json()['didCourseAmount'],1)
        plan = self.plan_client.get('/planstats/stats/Computing/2026')
        self.assertEqual(plan.status_code,200,plan.get_json())
        self.assertEqual(plan.get_json()['inscriptionsCounter'],1)
        # Optional unclassified fields remain in XES but cannot masquerade as academic roles.
        minimal = frame.drop(columns=['Grade'])
        pm4py.write_xes(minimal,'discovers/missing-grade.xes')
        missing = self.cu_client.get('/custats/missing-grade/001')
        self.assertEqual(missing.status_code,400)
        self.assertIn('Grade',missing.get_json()['error'])


if __name__ == '__main__':
    unittest.main()
