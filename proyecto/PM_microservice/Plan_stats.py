'''
Created on 10 ago. 2025

@author: sndc3
'''
import pm4py
import os
import json
from flask import Flask, request, jsonify
from collections import Counter, defaultdict
from flask_cors import CORS

import traceback #for debugging
from discovery_access import validate_workspace_access

app = Flask(__name__)
CORS(app) #Without cors it gets blocked

# Create required folders if doesnt exists
for folder in ['./stats']:
    os.makedirs(folder, exist_ok=True)

@app.route('/<caseid>/<career>/<plan>', methods=['GET'])
def stats(caseid, career, plan):
    try:
        workspace_uuid = request.args.get('workspace_uuid')
        if not workspace_uuid:
            return jsonify({'error': 'Missing workspace_uuid'}), 400

        metadata, error_response = validate_workspace_access(caseid, workspace_uuid)
        if error_response:
            return error_response

        filepath = './imports/' + metadata['discovery_id'] + '.xes'
        if os.path.exists(filepath):
            filepath2 = './stats/' + caseid + '_plan.json'
            if os.path.exists(filepath2):
                with open(filepath2, "r", encoding="utf-8") as f:
                    data = json.load(f)
                    return jsonify(data)
                
            event_log = pm4py.read_xes(filepath)
            event_log = pm4py.format_dataframe(event_log, case_id="ID", activity_key="Activity", timestamp_key="Timestamp", timest_format='%a %b %d %H:%M:%S %Z %Y')
            event_log = pm4py.convert_to_event_log(event_log)
            
            examCounter = Counter() #Amount of exams made by each students metric
            courseCounter = Counter() #Amount of courses made by each students metric
            degreeObtainedCounter = 0 #Amount of graduate students
            inscriptionsCounter = 0 #Amount of total students (inscriptionsCounter - degreeObtainedCounter is undergraduate)
            studentsCreditsDistribution = Counter() #Student Credits Distribution
            degreeMeanTime = 0 #Mean time to obtain degree
            
            for trace in event_log:
                
                examCount = 0
                courseCount = 0
                credits = 0
                
                inscription_date = None
                degree_date = None
                
                for event in trace:
                    if (event["concept:name"] == "Inscription to Plan" and event["Career"] == career and str(event["Plan"]) == plan):
                        inscriptionsCounter += 1
                        inscription_date = event["time:timestamp"]
                    
                    if (event["concept:name"] == "Degree obtained" and event["Career"] == career and str(event["Plan"]) == plan):
                        degreeObtainedCounter += 1
                        degree_date = event["time:timestamp"]
                        
                    if (event["concept:name"] == "Evaluation - Exam" and event["Career"] == career and str(event["Plan"]) == plan):
                        examCount += 1
                        if(event["Grade"] >= 3): #Se exonera con 3 o mas
                            credits += event["Credits"]
                        
                    if (event["concept:name"] == "Evaluation - Course" and event["Career"] == career and str(event["Plan"]) == plan):
                        courseCount += 1
                        if(event["Grade"] >= 6): #Se exonera con 6 o mas
                            credits += event["Credits"]
                            
                examCounter[examCount] += 1
                courseCounter[courseCount] += 1
                studentsCreditsDistribution[credits] += 1
                
                # Calculate months between inscription and degree
                if inscription_date and degree_date:
                    diff_days = (degree_date - inscription_date).days
                    diff_months = round(diff_days / 30.44)  # average month length
                    degreeMeanTime += diff_months  # accumulate for later averaging
                
            if degreeObtainedCounter > 0:
                degreeMeanTime = degreeMeanTime / degreeObtainedCounter    
                
            result = {
                'examCounter': examCounter, #Amount of exams made by each students metric
                'courseCounter': courseCounter, #Amount of courses made by each students metric
                'degreeObtainedCounter': degreeObtainedCounter,
                'inscriptionsCounter': inscriptionsCounter,
                'studentsCreditsDistribution': studentsCreditsDistribution, #Student Credits Distribution
                'degreeMeanTime': degreeMeanTime,
                'uuid': caseid,
                'career': career,
                'plan': plan
            }
            
            with open(filepath2, "w", encoding="utf-8") as f:
                json.dump(result, f, indent=4, ensure_ascii=False)
        
            return jsonify(result)
            
        else:
            return "Unknown case id"
    
    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500
    
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9006)