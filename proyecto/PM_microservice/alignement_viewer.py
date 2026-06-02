'''
Created on 19 ago. 2025

@author: sndc3
'''
import pm4py
import os
import uuid
import pandas as pd
import threading
from flask import Flask, request, jsonify, send_file
from flask_cors import CORS

from pm4py.objects.petri_net.utils.initial_marking import discover_initial_marking
from pm4py.objects.petri_net.utils.final_marking import discover_final_marking
from pm4py.objects.petri_net.obj import Marking

import traceback #for debugging

app = Flask(__name__)
CORS(app) #Without cors it gets blocked

# Create required folders if doesnt exists
for folder in ['./conformance']:
    os.makedirs(folder, exist_ok=True)
    
render_status = {}
    
def run_viewer(
    reference,
    caseid,
    id_columns,
    activity_columns,
    timestamp_column
):
    try:
        render_status[(reference, caseid)] = "rendering"   
        pnfilepath = './reference/' + reference + '.pnml'
        elfilepath = './imports/' + caseid + '.csv'
        imagepath = './conformance/' + reference + '/alignements/' + caseid + '.svg'
        
        if not os.path.exists(pnfilepath):
            return "Unknown reference id"
        if not os.path.exists(elfilepath):
            return "Unknown discovery id"
        
        os.makedirs('./conformance/' + reference + '/alignements', exist_ok=True)
        
        #event_log = pm4py.read_xes(elfilepath)
        event_log = pd.read_csv(elfilepath, sep=',')
        
        event_log["ID"] = (
            event_log[id_columns]
            .astype(str)
            .agg(" - ".join, axis=1)
        )
        
        event_log["Activity"] = (
            event_log[activity_columns]
            .astype(str)
            .agg(" - ".join, axis=1)
            .str.replace(r'\.0$', '', regex=True) # Converts "1030.0" to "1030"
            .str.strip()
        )
        
        event_log = pm4py.format_dataframe(
            event_log,
            case_id="ID",
            activity_key="Activity",
            timestamp_key=timestamp_column,
            timest_format='%a %b %d %H:%M:%S %Z %Y'
        )
        
        # Sort chronologically so sequential entry dependencies evaluate correctly
        event_log = event_log.sort_values(by=["ID", timestamp_column]).reset_index(drop=True)
        
        # Deduplicate identical events happening at the exact same timestamp to prevent model blockages
        event_log = event_log.drop_duplicates(subset=["ID", "Activity", timestamp_column])
        
        #print(pnfilepath)
        
        #with open(pnfilepath, 'r', encoding='utf-8-sig') as f:
        #    first_chars = f.read(20)
        #    print(f"DEBUG: Real starting characters are: '{first_chars}'")
        # --
        
        net, im, fm = pm4py.read_pnml(pnfilepath)
        
        for t in net.transitions:
            name = str(t.name).strip() if t.name is not None else ""
        
            # Tau transitions must remain invisible
            if name.startswith("tau"):
                t.label = None
                continue
        
            # If label is None or only whitespace, use transition name
            label = "" if t.label is None else str(t.label).strip()
        
            if label == "":
                t.label = name
            else:
                t.label = label
        
        for t in net.transitions:
            print("name =", repr(t.name), "label =", repr(t.label))
        
        for transition in net.transitions:
            if transition.name:
                transition.name = str(transition.name).strip()
            else:
                transition.name = str(transition.id).strip()
        
        if im is None or len(im) == 0:
            im = discover_initial_marking(net)
        if fm is None or len(fm) == 0:
            fm = discover_final_marking(net)
        
        print("im es:", im)
        print("fm es:", fm)
        
        
        parameters = {'enable_easy_soundness_check': False,
                      'disable_soundness_check': True,
                      'activity_key': 'Activity',      # Points to your original dataframe column name
                      'concept:name': 'concept:name'}
        
        #pm4py.write_xes(event_log, f'./test/{caseid}.xes')
        
        from pm4py.algo.conformance.alignments.petri_net import algorithm as alignments_algorithm
        
        #conformance_alignment = pm4py.conformance.conformance_diagnostics_alignments(event_log, net, im, fm, parameters=parameters)
        conformance_alignment = alignments_algorithm.apply(
            event_log, 
            net, 
            im, 
            fm, 
            parameters=parameters
        )
        pm4py.save_vis_alignments(event_log, conformance_alignment, imagepath)
        
        render_status[(reference, caseid)] = "completed"
    except Exception as e:
        print(e);
        tb = traceback.format_exc()
        return
        #return jsonify({'error': str(e), 'trace': tb}), 500
    
@app.route('/alignement/<reference>/<caseid>', methods=['GET'])
def TBR(reference, caseid):
    try:
        id_columns = request.args.get("ids", "")
        activity_columns = request.args.get("activities", "")
        timestamp_column = request.args.get("timestamp")

        id_columns = [
            x.strip()
            for x in id_columns.split(",")
            if x.strip()
        ]

        activity_columns = [
            x.strip()
            for x in activity_columns.split(",")
            if x.strip()
        ]
        
        # If rendering is ongoing, report status
        if render_status.get((reference, caseid)) == "rendering":
            return jsonify({'status': 'rendering'}), 202
        
        pnfilepath = './reference/' + reference + '.pnml'
        elfilepath = './imports/' + caseid + '.csv'
        imagepath = './conformance/' + reference + '/alignements/' + caseid + '.svg'
        
        if not os.path.exists(pnfilepath):
            return "Unknown reference id"
        if not os.path.exists(elfilepath):
            return "Unknown discovery id"
        
        if os.path.exists(imagepath):
                    return send_file(imagepath, mimetype='image/svg+xml')
        
        thread = threading.Thread(
            target=run_viewer,
            args=(
                reference,
                caseid,
                id_columns,
                activity_columns,
                timestamp_column
            )
        )
        thread.start()
        return jsonify({'status': 'rendering started'}), 200
        
    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500
    
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9008)