'''
Created on 22 ago. 2025

@author: sndc3
'''
import pm4py
import os
import uuid
import pandas as pd
from flask import Flask, request, jsonify, send_file
from flask_cors import CORS

import traceback #for debugging

app = Flask(__name__)
CORS(app)

@app.route('/<mode>/<caseid>', methods=['GET'])
def stats(mode, caseid):
    try:
        if mode == "1":
            filepath = './reference/' + caseid + '.xes'
        else:
            filepath = './imports2/' + caseid + '.xes'
        if os.path.exists(filepath):
            event_log = pm4py.read_xes(filepath)
            event_log = pm4py.format_dataframe(event_log, case_id="ID", activity_key="Activity", timestamp_key="Timestamp", timest_format='%a %b %d %H:%M:%S %Z %Y')
            event_log = pm4py.convert_to_event_log(event_log)
            
            variants = pm4py.stats.get_variants(event_log)
            num_variants = len(variants)
            num_traces = len(event_log)
            
            start_activities = pm4py.stats.get_start_activities(event_log)
            end_activities = pm4py.stats.get_end_activities(event_log)
            
            all_activities = list(pm4py.stats.get_event_attribute_values(event_log, "Activity").keys())
            
            result = {
                "num_variants": num_variants,
                "num_traces": num_traces,
                "start_activities": start_activities,
                "end_activities": end_activities,
                "all_activities": all_activities
            }

            return jsonify(result)
            
        else:
            return "Unknown case id"
        
    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500
    
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9009)