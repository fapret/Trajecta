'''
Created on 10 aug. 2025

@author: Santiago Nicolas Diaz Conde
'''
import pm4py
import os
import uuid
import pandas as pd
import threading
from flask import Flask, request, jsonify, send_file
from flask_cors import CORS

import traceback #for debugging

app = Flask(__name__)
CORS(app) #Without cors it gets blocked

# Create required folders if doesnt exists
for folder in ['./imports', './dfg/png', './bpmn/png', './pnml/png', './ptml/png']:
    os.makedirs(folder, exist_ok=True)
    
render_status = {}

def run_viewer(view, caseid, mode, activity, path, filtermode):
    try:
        render_status[(view, caseid, activity, path)] = "rendering"
        match view:
            case 'dfg':
                filepath = './dfg/png/' + caseid + '.png'

                dfg, dfg_start_activities, dfg_end_activities = pm4py.read_dfg('./dfg/' + caseid + '.dfg') #eliminar luego
                
                if (filtermode == 1):
                    filepath = './dfg/png/' + caseid + '_a' + str(activity) + '_p' + str(path) + '.png'
                
                    ########## Test 1
                    # FILTER PATHS (edges)
                    if len(dfg) > 0:
                        max_edge_freq = max(dfg.values())
                        path_threshold = max_edge_freq * (float(path) / 100.0)
                    else:
                        path_threshold = 0
                    
                    dfg = {
                        (a, b): f for (a, b), f in dfg.items()
                        if f >= path_threshold
                    }
                    
                    # FILTER ACTIVITIES (nodes)
                    # compute node frequencies
                    node_freq = {}
                    
                    for (a, b), f in dfg.items():
                        node_freq[a] = node_freq.get(a, 0) + f
                        node_freq[b] = node_freq.get(b, 0) + f
                    
                    if len(node_freq) > 0:
                        max_node_freq = max(node_freq.values())
                        activity_threshold = max_node_freq * (float(activity) / 100.0)
                    else:
                        activity_threshold = 0
                    
                    allowed_nodes = {
                        act for act, f in node_freq.items()
                        if f >= activity_threshold
                    }
                    
                    # remove arcs touching filtered nodes
                    dfg = {
                        (a, b): f for (a, b), f in dfg.items()
                        if a in allowed_nodes and b in allowed_nodes
                    }
                    
                    dfg_start_activities = {
                        act: cnt for act, cnt in dfg_start_activities.items()
                        if act in allowed_nodes
                    }
                    
                    dfg_end_activities = {
                        act: cnt for act, cnt in dfg_end_activities.items()
                        if act in allowed_nodes
                    }
                elif (filtermode == 2):
                    filepath = './dfg/png/' + caseid + '_2_a' + str(activity) + '_p' + str(path) + '.png'
                    
                    sorted_paths = sorted(dfg.items(), key=lambda x: x[1], reverse=True)
                    
                    k_paths = int(len(sorted_paths) * (path / 100.0))
                    k_paths = max(k_paths, 1) if len(sorted_paths) > 0 else 0
                    
                    # keep top-k paths (as dict)
                    filtered_paths = dict(sorted_paths[:k_paths]) if k_paths > 0 else {}
                    
                    dfg = dict(filtered_paths)
                    
                    # BUILD NODE FREQUENCIES FROM FILTERED PATHS (incoming + outgoing)
                    node_freq = {}
                    for (a, b), f in dfg.items():
                        node_freq[a] = node_freq.get(a, 0) + f
                        node_freq[b] = node_freq.get(b, 0) + f
                    
                    sorted_acts = sorted(node_freq.items(), key=lambda x: x[1], reverse=True)
                
                    k_acts = int(len(sorted_acts) * (activity / 100.0))
                    k_acts = max(k_acts, 1) if len(sorted_acts) > 0 else 0
                
                    filtered_activities = dict(sorted_acts[:k_acts]) if k_acts > 0 else {}
                    allowed_nodes = set(filtered_activities.keys())
                    
                    dfg = {
                        (a, b): f for (a, b), f in dfg.items()
                        if a in allowed_nodes and b in allowed_nodes
                    }
                    
                    # FILTER start/end activity maps to allowed nodes
                    # final list of nodes: any activity appearing in filtered_dfg + those kept by filtering
                    final_nodes = set()
                    for (a, b) in dfg.keys():
                        final_nodes.add(a)
                        final_nodes.add(b)
                    
                    dfg_start_activities = {
                        act: cnt for act, cnt in dfg_start_activities.items()
                        if act in final_nodes
                    }
                    
                    dfg_end_activities = {
                        act: cnt for act, cnt in dfg_end_activities.items()
                        if act in final_nodes
                    }
            
                pm4py.save_vis_dfg(dfg, dfg_start_activities, dfg_end_activities, filepath, 'white', 9223372036854775807, 'TB', engine="neato")
                #return send_file(filepath, mimetype='image/png')
            
            case 'perf_dfg':
                filepath = './dfg/png/' + caseid + '_performance.png'
                
                # Load the SAME frequency DFG used in normal DFG diagram
                freq_dfg_file = './dfg/' + caseid + '.dfg'

                dfg_freq, start_acts_freq, end_acts_freq = pm4py.read_dfg(freq_dfg_file)
                
                if mode == "1":
                    filepathLog = './reference/' + caseid + '.xes'
                else:
                    filepathLog = './imports2/' + caseid + '.xes'
                if os.path.exists(filepathLog):
                    event_log = pm4py.read_xes(filepathLog)
                    event_log = pm4py.format_dataframe(event_log, case_id="ID", activity_key="Activity", timestamp_key="Timestamp", timest_format='%a %b %d %H:%M:%S %Z %Y')
                    event_log = pm4py.convert_to_event_log(event_log)
                    dfg, dfg_start_activities, dfg_end_activities = pm4py.discover_performance_dfg(event_log);
                    
                    if filtermode == 2:
                        filepath = './dfg/png/' + caseid + '_performance_2_a' + str(activity) + '_p' + str(path) + '.png'
    
                        # STEP 1 — Filter paths by frequency
                        sorted_paths = sorted(dfg_freq.items(), key=lambda x: x[1], reverse=True)
                        k_paths = int(len(sorted_paths) * (path / 100.0))
                        k_paths = max(k_paths, 1) if len(sorted_paths) > 0 else 0
                        kept_paths = dict(sorted_paths[:k_paths])
    
                        # STEP 2 — Filter nodes by frequency
                        node_freq = {}
                        for (a, b), f in kept_paths.items():
                            node_freq[a] = node_freq.get(a, 0) + f
                            node_freq[b] = node_freq.get(b, 0) + f
    
                        sorted_acts = sorted(node_freq.items(), key=lambda x: x[1], reverse=True)
                        k_acts = int(len(sorted_acts) * (activity / 100.0))
                        k_acts = max(k_acts, 1) if len(sorted_acts) > 0 else 0
                        kept_acts = set(dict(sorted_acts[:k_acts]).keys())
    
                        # Keep only surviving edges
                        final_edges = {
                            (a, b): f for (a, b), f in kept_paths.items()
                            if a in kept_acts and b in kept_acts
                        }
    
                        start_acts_freq = {a: c for a, c in start_acts_freq.items() if a in kept_acts}
                        end_acts_freq = {a: c for a, c in end_acts_freq.items() if a in kept_acts}
    
                        # Now filter the performance metrics to match final_edges
                        dfg = {
                            (a, b): v for (a, b), v in dfg.items()
                            if (a, b) in final_edges
                        }
                        dfg_start_activities = {a: c for a, c in dfg_start_activities.items() if a in kept_acts}
                        dfg_end_activities = {a: c for a, c in dfg_end_activities.items() if a in kept_acts}
                    
                    pm4py.save_vis_performance_dfg(dfg, dfg_start_activities, dfg_end_activities, filepath, rankdir='TB', engine="neato")
                    #return send_file(filepath, mimetype='image/png')
                
            case 'bpmn':
                filepath = './bpmn/png/' + caseid + '.png'
                
                bpmn_graph = pm4py.read_bpmn('./bpmn/' + caseid + '.bpmn')                
                pm4py.save_vis_bpmn(bpmn_graph, filepath, 'white', 'TB', engine="neato")
                #return send_file(filepath, mimetype='image/png')
            
            case 'pnml_alpha':
                filepath = './pnml/png/' + caseid + '_alpha.png'
                net, im, fm = pm4py.read_pnml('./pnml/' + caseid + '_alpha.pnml')
                pm4py.save_vis_petri_net(net, im, fm, filepath, rankdir='TB', engine="neato")
                #return send_file(filepath, mimetype='image/png')
                
            case 'pnml_heuristics':
                filepath = './pnml/png/' + caseid + '_heuristics.png'

                net, im, fm = pm4py.read_pnml('./pnml/' + caseid + '_heuristics.pnml')
                pm4py.save_vis_petri_net(net, im, fm, filepath, rankdir='TB', engine="neato")
                #return send_file(filepath, mimetype='image/png')
                
            case 'pnml_inductive':
                filepath = './pnml/png/' + caseid + '_inductive.png'
                net, im, fm = pm4py.read_pnml('./pnml/' + caseid + '_inductive.pnml')
                pm4py.save_vis_petri_net(net, im, fm, filepath, rankdir='TB', engine="neato")
                #return send_file(filepath, mimetype='image/png')
                
            case 'ptml':
                filepath = './ptml/png/' + caseid + '.png'
                process_tree = pm4py.read_ptml('./ptml/' + caseid + '.ptml')
                pm4py.save_vis_process_tree(process_tree, filepath, engine="neato")
                #return send_file(filepath, mimetype='image/png')
            
            case _:
                return "Unknown view"
            
        render_status[(view, caseid, activity, path)] = "completed"
    
    except Exception as e:
        tb = traceback.format_exc()
        print(tb)
    
@app.route('/<view>/<caseid>/<mode>', methods=['GET'])
@app.route('/<view>/<caseid>', methods=['GET'])
def viewer(view, caseid, mode=0):
    try:
        activity = float(request.args.get("activity", 100))
        path = float(request.args.get("path", 100))
        filtermode = int(request.args.get("filtermode", 2))
        
        # If rendering is ongoing, report status
        if render_status.get((view, caseid, activity, path)) == "rendering":
            return jsonify({'status': 'rendering'}), 202
        match view:
            case 'dfg':
                if filtermode == 1:
                    filepath = './dfg/png/' + caseid + '_a' + str(activity) + '_p' + str(path) + '.png'
                elif filtermode == 2:
                    filepath = './dfg/png/' + caseid + '_2_a' + str(activity) + '_p' + str(path) + '.png'
                else:
                    filepath = './dfg/png/' + caseid + '.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                
            case 'perf_dfg':
                filepath = './dfg/png/' + caseid + '_performance.png'
                if filtermode == 2:
                    filepath = './dfg/png/' + caseid + '_performance_2_a' + str(activity) + '_p' + str(path) + '.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
    
            case 'bpmn':
                filepath = './bpmn/png/' + caseid + '.png'
                #if filtermode == 2:
                #    filepath = f'./bpmn/png/{caseid}_2_a{activity}_p{path}.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
    
            case 'pnml_alpha':
                filepath = './pnml/png/' + caseid + '_alpha.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                
            case 'pnml_heuristics':
                filepath = './pnml/png/' + caseid + '_heuristics.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                
            case 'pnml_inductive':
                filepath = './pnml/png/' + caseid + '_inductive.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                
            case 'ptml':
                filepath = './ptml/png/' + caseid + '.png'
                if os.path.exists(filepath):
                    return send_file(filepath, mimetype='image/png')
                
            case _:
                return "Unknown view"
            
        thread = threading.Thread(target=run_viewer, args=(view, caseid, mode, activity, path, filtermode))
        thread.start()
        return jsonify({'status': 'rendering started'}), 200
                    
    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500
    
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9001)