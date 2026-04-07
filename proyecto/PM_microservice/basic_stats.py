'''
Created on 10 jul. 2025

@author: Santiago Nicolas Diaz Conde
'''
import pm4py
from flask import Flask, request, jsonify
from flask_cors import CORS

import traceback #for debugging
from discovery_access import resolve_discovery_xes_path, validate_workspace_access

app = Flask(__name__)
CORS(app)

@app.route('/<caseid>', methods=['GET'])
@app.route('/<caseid>/<mode>', methods=['GET'])
def stats(caseid, mode='2'):
    try:
        workspace_uuid = request.args.get('workspace_uuid')
        if not workspace_uuid:
            return jsonify({'error': 'Missing workspace_uuid'}), 400

        allowed_modes = {'1': {'reference'}, '2': {'log'}}
        metadata, error_response = validate_workspace_access(caseid, workspace_uuid, allowed_modes.get(mode))
        if error_response:
            return error_response

        filepath = resolve_discovery_xes_path(metadata)
        if not filepath:
            return jsonify({'error': 'Unknown discovery id'}), 404

        event_log = pm4py.read_xes(filepath)
        event_log = pm4py.format_dataframe(event_log, case_id="ID", activity_key="Activity", timestamp_key="Timestamp", timest_format='%a %b %d %H:%M:%S %Z %Y')
        event_log = pm4py.convert_to_event_log(event_log)

        #From XES
        num_cases = len(set(trace.attributes["concept:name"] for trace in event_log))
        num_events = sum(len(trace) for trace in event_log)
        num_variants = len(pm4py.get_variants(event_log))
        start_activities = pm4py.get_start_activities(event_log)
        end_activities = pm4py.get_end_activities(event_log)
        start_activities_dict = dict(start_activities)
        end_activities_dict = dict(end_activities)

        return jsonify({
            'uuid': caseid,
            'cases': num_cases,
            'events': num_events,
            'variants': num_variants,
            "start_activities": start_activities_dict,
            "end_activities": end_activities_dict
        })

    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9002)
