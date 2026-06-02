'''
Created on 02 ago. 2025

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
CORS(app) #Without cors it gets blocked

def activity_time_offsets(df):
    # Get case start times
    case_starts = df.groupby("ID")["Timestamp"].min()

    # Keep only the last occurrence of each activity per case
    df = df.sort_values("Timestamp").groupby(["ID", "Activity"], as_index=False).last()

    # Compute offset in months (integer, no decimals)
    df["OffsetMonths"] = df.apply(
        lambda row: round((row["Timestamp"] - case_starts[row["ID"]]).total_seconds() / (60 * 60 * 24 * 30.44)),
        axis=1
    )

    # Aggregate median across all traces
    median_offsets = df.groupby("Activity")["OffsetMonths"].median().to_dict()
    return median_offsets


@app.route('/<discoverA>/<discoverB>', defaults={'completeOnly': 'false'}, methods=['GET'])
@app.route('/<discoverA>/<discoverB>/<completeOnly>', methods=['GET'])
def compareDiscover(discoverA, discoverB, completeOnly):
    try:
        filepathA = f'./discovers/{discoverA}.xes'
        filepathB = f'./discovers/{discoverB}.xes'
        
        if os.path.exists(filepathA) and os.path.exists(filepathB):
            event_logA = pm4py.read_xes(filepathA)
            event_logA = pm4py.format_dataframe(event_logA, case_id="ID", activity_key="Activity", timestamp_key="Timestamp")
            
            event_logB = pm4py.read_xes(filepathB)
            event_logB = pm4py.format_dataframe(event_logB, case_id="ID", activity_key="Activity", timestamp_key="Timestamp")
            
            if completeOnly.lower() == "true":
                ids_with_degree_A = event_logA.loc[event_logA["Activity"] == "Degree obtained", "ID"].unique()
                event_logA = event_logA[event_logA["ID"].isin(ids_with_degree_A)]
                
                ids_with_degree_B = event_logB.loc[event_logB["Activity"] == "Degree obtained", "ID"].unique()
                event_logB = event_logB[event_logB["ID"].isin(ids_with_degree_B)]
            
            # Get median offsets per activity
            offsetsA = activity_time_offsets(event_logA)
            offsetsB = activity_time_offsets(event_logB)
            
            # Combine into comparison table
            all_activities = set(offsetsA.keys()).union(offsetsB.keys())
            rows = []
            for act in all_activities:
                real = offsetsA.get(act)
                ref = offsetsB.get(act)
                deviation = None
                if real is not None and ref is not None:
                    deviation = real - ref
                rows.append({
                    "Activity": act,
                    "DisA_Months": real,
                    "DisB_Months": ref,
                    "Deviation_Months": deviation
                })
            
            df_comparison = pd.DataFrame(rows).sort_values("Deviation_Months", ascending=False)
            return jsonify(df_comparison.to_dict(orient='records'))
        
        else:
            return "Unknown discover id"
    
    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500
    
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9005)