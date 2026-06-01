'''
Created on 10 aug. 2025

@author: Santiago Nicolas Diaz Conde
'''
import pm4py
import os
import uuid
import pandas as pd
import threading
from flask import Flask, request, jsonify
from flask_cors import CORS
import traceback
from pm4py.visualization.powl.variants import net
import json

app = Flask(__name__)
CORS(app)

# Create required folders if they don't exist
for folder in ['./imports', './imports2', './reference', './dfg', './bpmn', './pnml', './ptml']:
    os.makedirs(folder, exist_ok=True)


def run_discovery(file_path, file_uuid, mode, column_mapping=None, action_mapping=None, activity_type_mapping=None):
    try:
        dtype_spec = {
            "Curricular Unit": str,
            "Unified Curricular Unit": str,
            "Unified Curricular Unit Name": str,
            "Course Edition": 'Int64',
            "Course Year": 'Int64',
            "Grade": 'Float64',
            "Credits": 'Int64',
        }

        # Read CSV
        event_log = pd.read_csv(file_path, sep=',', dtype=dtype_spec)
        
        if column_mapping:
            reverse_mapping = {source: target for target, source in column_mapping.items()}
            event_log = event_log.rename(columns=reverse_mapping)

        missing_cols = [col for col in ['ID', 'Timestamp', 'Curricular Unit', 'Unified Curricular Unit', 'Unified Curricular Unit Name'] if col not in event_log.columns]
        if missing_cols:
            raise ValueError(f'Missing required columns after mapping: {missing_cols}')

        #if activity_mapping:
        #    event_log['Activity'] = event_log['Activity'].astype(str).map(lambda val: activity_mapping.get(val, val))
        
        event_log['Activity'] = (
            event_log['Action'].astype(str)
            + ' - '
            + event_log['Activity Type'].astype(str)
        )

        # Format for pm4py
        event_log = pm4py.format_dataframe(
            event_log,
            case_id="ID",
            activity_key="Activity",
            timestamp_key="Timestamp",
            timest_format='%a %b %d %H:%M:%S %Z %Y'
        )

        # Save initial XES
        pm4py.write_xes(event_log, f'./imports/{file_uuid}.xes')

        # Update activity names for certain cases
        event_log['Activity'] = event_log.apply(
            lambda row: f"{row['Activity']} - {row['Unified Curricular Unit']} - {row['Unified Curricular Unit Name']}",
            #if row['Activity'] in ['Evaluation - Exam', 'Evaluation - Course', 'Inscription to Course', 'Evaluation - Tutoring', 'Evaluation - Partial']
            #else row['Activity'],
            axis=1
        )

        event_log['concept:name'] = event_log.apply(
            lambda row: f"{row['concept:name']} - {row['Unified Curricular Unit']} - {row['Unified Curricular Unit Name']}",
            #if row['concept:name'] in ['Evaluation - Exam', 'Evaluation - Course', 'Inscription to Course', 'Evaluation - Tutoring', 'Evaluation - Partial']
            #else row['concept:name'],
            axis=1
        )

        # Save updated XES
        if mode == "1":
            pm4py.write_xes(event_log, f'./reference/{file_uuid}.xes')
        else:
            pm4py.write_xes(event_log, f'./imports2/{file_uuid}.xes')
            
        # --- DFG ---
        dfg, dfg_start_activities, dfg_end_activities = pm4py.discover_dfg(event_log)
        pm4py.write_dfg(dfg, dfg_start_activities, dfg_end_activities, f'./dfg/{file_uuid}.dfg')
        del dfg
        del dfg_start_activities
        del dfg_end_activities
        
        print(f"[{file_uuid}] Discovery DFG completed successfully.")

        # --- PNML ---
        net, im, fm = pm4py.discover_petri_net_alpha(event_log)
        pm4py.write_pnml(net, im, fm, f'./pnml/{file_uuid}_alpha.pnml')
        del net
        del im
        del fm
        print(f"[{file_uuid}] Discovery PNML with Alpha completed successfully.")
        net2, im2, fm2 = pm4py.discover_petri_net_heuristics(event_log)
        pm4py.write_pnml(net2, im2, fm2, f'./pnml/{file_uuid}_heuristics.pnml')
        del net2
        del im2
        del fm2
        print(f"[{file_uuid}] Discovery PNML with Heuristics completed successfully.")
        net3, im3, fm3 = pm4py.discover_petri_net_inductive(event_log) #takes longer, goes last
        pm4py.write_pnml(net3, im3, fm3, './pnml/' + file_uuid + '_inductive.pnml')
        print(f"[{file_uuid}] Discovery PNML with Inductive completed successfully.")
        
        print(f"[{file_uuid}] Discovery PNML completed successfully.")
        
        # --- BPMN --- Removed, takes too much time
        bpmn_graph = pm4py.convert_to_bpmn(net3, im3, fm3)
        pm4py.write_bpmn(bpmn_graph, f'./bpmn/{file_uuid}.bpmn', auto_layout=False)
        del bpmn_graph
        
        print(f"[{file_uuid}] Discovery BPMN completed successfully.")
        

        # --- PTML --- Removed, takes too much time
        #process_tree = pm4py.convert_to_process_tree(net3, im3, fm3)
        #pm4py.write_ptml(process_tree, f'./ptml/{file_uuid}.ptml', auto_layout=True)
        #del process_tree
        
        #print(f"[{file_uuid}] Discovery PTML completed successfully.")
        
        del net3
        del im3
        del fm3

        print(f"[{file_uuid}] Discovery completed successfully.")

    except Exception as e:
        print(f"[{file_uuid}] ERROR during discovery: {e}")
        traceback.print_exc()

@app.route('/discoverer/', methods=['POST'])
@app.route('/discoverer/<mode>', methods=['POST'])
def discover(mode):
    if 'file' not in request.files:
        return jsonify({'error': 'No file'}), 400

    file = request.files['file']
    name = request.form.get('name', '')
    file_uuid = str(uuid.uuid4()).split('-')[0] + '+' + name

    try:
        # Save uploaded CSV to disk so thread can access it
        file_path = f'./imports/{file_uuid}.csv'
        file.save(file_path)

        # Start discovery in background thread
        column_mapping = json.loads(request.form.get('column_mapping', '{}'))
        action_mapping = json.loads(request.form.get('action_mapping', '{}'))
        activity_type_mapping = json.loads(request.form.get('activity_type_mapping', '{}'))
        thread = threading.Thread(target=run_discovery, args=(file_path, file_uuid, mode, column_mapping, action_mapping, activity_type_mapping))
        thread.start()

        # Respond immediately with job ID
        return jsonify({
            'uuid': file_uuid,
            'status': 'started'
        })

    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9000)