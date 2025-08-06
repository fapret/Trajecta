'''
Created on 10 jun. 2025

@author: Santiago Nicolas Diaz Conde
'''
import pm4py
import os
import uuid
import pandas as pd
from flask import Flask, request, jsonify
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

# Create required folders if doesnt exists
for folder in ['./imports', './imports2', './dfg', './bpmn', './pnml', './ptml']:
    os.makedirs(folder, exist_ok=True)
    
@app.route('/', methods=['POST'])
def discover():
    if 'file' not in request.files:
        return jsonify({'error': 'No file'}), 400
    
    file = request.files['file']
    
    try:       
        file_uuid = str(uuid.uuid4())
        
        dtype_spec = {
            "Curricular Unit": str,
            "Course Edition": 'Int64',  # Use 'Int64' (capital I) for nullable integers
            "Course Year": 'Int64',
            "Grade": 'Int64'
        }
        
        event_log = pd.read_csv(file, sep=',', dtype=dtype_spec)        
        event_log = pm4py.format_dataframe(event_log, case_id="ID", activity_key="Activity", timestamp_key="Timestamp", timest_format='%a %b %d %H:%M:%S %Z %Y')
        
        pm4py.write_xes(event_log, './imports/' + file_uuid + '.xes') #save Dataframe as xes
        
        # Update the activity name by combining it with the Curricular Unit
        event_log['Activity'] = event_log.apply(
            lambda row: f"{row['Activity']} - {row['Curricular Unit']}"
            if row['Activity'] in ['Evaluation - Exam', 'Evaluation - Course'] else row['Activity'],
            axis=1
        )
        
        event_log['concept:name'] = event_log.apply(
            lambda row: f"{row['concept:name']} - {row['Curricular Unit']}"
            if row['concept:name'] in ['Evaluation - Exam', 'Evaluation - Course'] else row['concept:name'],
            axis=1
        )
        
        pm4py.write_xes(event_log, './imports2/' + file_uuid + '.xes') #save Dataframe as xes
        
        # --- DFG ---
        dfg, dfg_start_activities, dfg_end_activities = pm4py.discover_dfg(event_log)
        pm4py.write_dfg(dfg, dfg_start_activities, dfg_end_activities, './dfg/' + file_uuid + '.dfg')
        
        # --- BPMN --- for some reason it doesnt works
        bpmn_graph = pm4py.discover_bpmn_inductive(event_log)
        pm4py.write_bpmn(bpmn_graph, './bpmn/' + file_uuid + '.bpmn', auto_layout=False)
        
        # --- PNML ---
        net, im, fm = pm4py.discover_petri_net_alpha(event_log)
        net2, im2, fm2 = pm4py.discover_petri_net_heuristics(event_log)
        pm4py.write_pnml(net, im, fm, './pnml/' + file_uuid + '_alpha.pnml')
        pm4py.write_pnml(net2, im2, fm2, './pnml/' + file_uuid + '_heuristics.pnml')
        
        # --- PTML ---
        process_tree = pm4py.discover_process_tree_inductive(event_log)
        pm4py.write_ptml(process_tree, './ptml/' + file_uuid + '.ptml', auto_layout=True)
        
        return jsonify({
            'uuid': file_uuid,
            'status': 'success'
        })
        
    except Exception as e:
        return jsonify({'error': str(e)}), 500
    
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9000)
        