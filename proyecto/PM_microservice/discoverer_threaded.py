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
from metadata_store import (
    init_metadata_db,
    insert_discovery_metadata,
    name_exists,
    update_discovery_status,
)

app = Flask(__name__)
CORS(app)

# Create required folders if they don't exist
for folder in ['./imports', './imports2', './reference', './dfg', './bpmn', './pnml', './ptml']:
    os.makedirs(folder, exist_ok=True)

init_metadata_db()


def normalize_mode(mode):
    return "reference" if mode == "1" else "log"


def resolve_display_name(discovery_id, workspace_uuid, mode, requested_name):
    default_name = requested_name.strip() if requested_name and requested_name.strip() else discovery_id
    if not name_exists(workspace_uuid, mode, default_name):
        return default_name

    shortid = discovery_id[:8]
    fallback_name = f"{shortid}+{default_name}"
    while name_exists(workspace_uuid, mode, fallback_name):
        shortid = str(uuid.uuid4())[:8]
        fallback_name = f"{shortid}+{default_name}"
    return fallback_name


def run_discovery(file_path, discovery_id, mode):
    try:
        dtype_spec = {
            "Curricular Unit": str,
            "Course Edition": 'Int64',
            "Course Year": 'Int64',
            "Grade": 'Float64',
            "Credits": 'Int64',
        }

        # Read CSV
        event_log = pd.read_csv(file_path, sep=',', dtype=dtype_spec)

        # Format for pm4py
        event_log = pm4py.format_dataframe(
            event_log,
            case_id="ID",
            activity_key="Activity",
            timestamp_key="Timestamp",
            timest_format='%a %b %d %H:%M:%S %Z %Y'
        )

        # Save initial XES
        pm4py.write_xes(event_log, f'./imports/{discovery_id}.xes')

        # Update activity names for certain cases
        event_log['Activity'] = event_log.apply(
            lambda row: f"{row['Activity']} - {row['Curricular Unit']}"
            if row['Activity'] in ['Evaluation - Exam', 'Evaluation - Course', 'Inscription to Course']
            else row['Activity'],
            axis=1
        )

        event_log['concept:name'] = event_log.apply(
            lambda row: f"{row['concept:name']} - {row['Curricular Unit']}"
            if row['concept:name'] in ['Evaluation - Exam', 'Evaluation - Course', 'Inscription to Course']
            else row['concept:name'],
            axis=1
        )

        # Save updated XES
        if mode == "1":
            pm4py.write_xes(event_log, f'./reference/{discovery_id}.xes')
        else:
            pm4py.write_xes(event_log, f'./imports2/{discovery_id}.xes')
            
        # --- DFG ---
        dfg, dfg_start_activities, dfg_end_activities = pm4py.discover_dfg(event_log)
        pm4py.write_dfg(dfg, dfg_start_activities, dfg_end_activities, f'./dfg/{discovery_id}.dfg')
        del dfg
        del dfg_start_activities
        del dfg_end_activities
        
        print(f"[{discovery_id}] Discovery DFG completed successfully.")

        # --- PNML ---
        net, im, fm = pm4py.discover_petri_net_alpha(event_log)
        pm4py.write_pnml(net, im, fm, f'./pnml/{discovery_id}_alpha.pnml')
        del net
        del im
        del fm
        print(f"[{discovery_id}] Discovery PNML with Alpha completed successfully.")
        net2, im2, fm2 = pm4py.discover_petri_net_heuristics(event_log)
        pm4py.write_pnml(net2, im2, fm2, f'./pnml/{discovery_id}_heuristics.pnml')
        del net2
        del im2
        del fm2
        print(f"[{discovery_id}] Discovery PNML with Heuristics completed successfully.")
        net3, im3, fm3 = pm4py.discover_petri_net_inductive(event_log) #takes longer, goes last
        pm4py.write_pnml(net3, im3, fm3, './pnml/' + discovery_id + '_inductive.pnml')
        print(f"[{discovery_id}] Discovery PNML with Inductive completed successfully.")
        
        print(f"[{discovery_id}] Discovery PNML completed successfully.")
        
        # --- BPMN --- Removed, takes too much time
        bpmn_graph = pm4py.convert_to_bpmn(net3, im3, fm3)
        pm4py.write_bpmn(bpmn_graph, f'./bpmn/{discovery_id}.bpmn', auto_layout=False)
        del bpmn_graph
        
        print(f"[{discovery_id}] Discovery BPMN completed successfully.")
        

        # --- PTML --- Removed, takes too much time
        process_tree = pm4py.convert_to_process_tree(net3, im3, fm3)
        pm4py.write_ptml(process_tree, f'./ptml/{discovery_id}.ptml', auto_layout=True)
        del process_tree
        
        print(f"[{discovery_id}] Discovery PTML completed successfully.")
        
        del net3
        del im3
        del fm3

        update_discovery_status(discovery_id, "completed")
        print(f"[{discovery_id}] Discovery completed successfully.")

    except Exception as e:
        update_discovery_status(discovery_id, "failed")
        print(f"[{discovery_id}] ERROR during discovery: {e}")
        traceback.print_exc()

@app.route('/', methods=['POST'])
@app.route('/<mode>', methods=['POST'])
def discover(mode):
    if 'file' not in request.files:
        return jsonify({'error': 'No file'}), 400
    workspace_uuid = request.form.get('workspace_uuid')
    if not workspace_uuid:
        return jsonify({'error': 'Missing workspace_uuid'}), 400

    file = request.files['file']
    discovery_id = str(uuid.uuid4())
    mode_label = normalize_mode(mode)
    requested_name = request.form.get('name')
    display_name = resolve_display_name(discovery_id, workspace_uuid, mode_label, requested_name)

    try:
        # Save uploaded CSV to disk so thread can access it
        file_path = f'./imports/{discovery_id}.csv'
        file.save(file_path)

        insert_discovery_metadata(
            discovery_id=discovery_id,
            workspace_uuid=workspace_uuid,
            mode=mode_label,
            display_name=display_name,
            original_name=requested_name,
            status="started",
        )

        # Start discovery in background thread
        thread = threading.Thread(target=run_discovery, args=(file_path, discovery_id, mode))
        thread.start()

        # Respond immediately with job ID
        return jsonify({
            'discovery_id': discovery_id,
            'display_name': display_name,
            'status': 'started'
        })

    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9000)
