'''
Created on 10 aug. 2025

@author: Santiago Nicolas Diaz Conde
'''
import pm4py
import os
import uuid
import threading
from flask import Flask, request, jsonify
from flask_cors import CORS
import traceback
import json
from werkzeug.utils import secure_filename
from log_mapping import (ColumnMappingError, column_types, prepare_event_log,
                         build_activity, save_mapping, load_mapping, read_headers)

app = Flask(__name__)
CORS(app)

# Create required folders if they don't exist
for folder in ['./imports', './discovers', './reference', './dfg', './bpmn', './pnml', './ptml', './mappings']:
    os.makedirs(folder, exist_ok=True)


def run_discovery(file_path, file_uuid, mode, column_mapping=None, action_mapping=None, activity_type_mapping=None, prepared_log=None):
    try:
        event_log, metadata = prepared_log if prepared_log is not None else prepare_event_log(file_path, column_mapping)
        event_log['Activity'] = build_activity(event_log)
        event_log = pm4py.format_dataframe(
            event_log,
            case_id="ID",
            activity_key="Activity",
            timestamp_key="Timestamp",
            timest_format='%a %b %d %H:%M:%S %Z %Y'
        )
        if prepared_log is None:
            save_mapping(file_uuid, metadata)

        pm4py.write_xes(event_log, f'./discovers/{file_uuid}.xes')
            
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
def discover(mode=0):
    if 'file' not in request.files:
        return jsonify({'error': 'No file'}), 400

    file = request.files['file']
    name = secure_filename(request.form.get('name', ''))
    file_uuid = str(uuid.uuid4()).split('-')[0] + '+' + name

    try:
        if mode == "pnml":
            file_path = f'./reference/{file_uuid}.pnml'
            file.save(file_path)
        else:
        
            # Save uploaded CSV to disk so thread can access it
            file_path = f'./imports/{file_uuid}.csv'
            file.save(file_path)
    
            # Reject bad assignments before returning a background job ID.
            column_mapping = json.loads(request.form.get('column_mapping', '{}'))
            action_mapping = json.loads(request.form.get('action_mapping', '{}'))
            activity_type_mapping = json.loads(request.form.get('activity_type_mapping', '{}'))
            prepared_log = prepare_event_log(file_path, column_mapping)
            save_mapping(file_uuid, prepared_log[1])
            thread = threading.Thread(target=run_discovery, args=(file_path, file_uuid, mode, column_mapping, action_mapping, activity_type_mapping, prepared_log))
            thread.start()

        # Respond immediately with job ID
        return jsonify({
            'uuid': file_uuid,
            'status': 'started'
        })

    except (json.JSONDecodeError, ColumnMappingError, UnicodeError) as e:
        if 'file_path' in locals() and os.path.isfile(file_path):
            os.remove(file_path)
        return jsonify({'error': str(e)}), 400
    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500
    
@app.route('/uploads/<mode>', methods=['POST'])
def upload(mode):
    if 'file' not in request.files:
        return jsonify({'error': 'No file'}), 400

    file = request.files['file']
    name = secure_filename(request.form.get('name', ''))
    file_uuid = str(uuid.uuid4()).split('-')[0] + '+' + name
    
    try:
        if mode == "reference":
            file_path = f'./reference/{file_uuid}.pnml'
            file.save(file_path)
        elif mode == "event_log":
            file_path = f'./imports/{file_uuid}.csv'
            file.save(file_path)

        return jsonify({
            'uuid': file_uuid,
            'status': 'uploaded'
        })
    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500

@app.route('/log-columns/<caseid>', methods=['GET'])
def get_log_columns(caseid):
    filepath = './imports/' + caseid + '.csv'

    if not os.path.exists(filepath):
        return jsonify({'error': 'Unknown event log id'}), 404

    try:
        result = {'columns': read_headers(filepath)}
        metadata = load_mapping(caseid)
        if metadata is not None:
            result['mapping'] = metadata
        return jsonify(result)
    except ColumnMappingError as error:
        return jsonify({'error': str(error)}), 400


@app.route('/discoverer/column-types', methods=['GET'])
def get_column_types():
    return jsonify(column_types())

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9000)
