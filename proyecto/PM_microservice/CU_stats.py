'''
Created on 23 jul. 2025

@author: Santiago Nicolas Diaz Conde
'''
import pm4py
import os
import uuid
import pandas as pd
from flask import Flask, request, jsonify, send_file
from collections import Counter, defaultdict
from flask_cors import CORS

import traceback #for debugging

app = Flask(__name__)
CORS(app) #Without cors it gets blocked

@app.route('/custats/<caseid>/<cu>', methods=['GET'])
def stats(caseid, cu):
    try:
        filepath = './discovers/' + caseid + '.xes'
        if os.path.exists(filepath):
            event_log = pm4py.read_xes(filepath)
            event_log = pm4py.format_dataframe(event_log, case_id="ID", activity_key="Activity", timestamp_key="Timestamp", timest_format='%a %b %d %H:%M:%S %Z %Y')
            event_log = pm4py.convert_to_event_log(event_log)
            
            #General Stats of UC
            examCounter = Counter() #Amount of exams made by each students metric
            courseCounter = Counter() #Amount of courses made by each students metric
            hasCourseCounter = 0 #Amount of students that coursed the UC
            hasExamCounter = 0 #Amount of students that did at least one exam
            approvedCounter = 0 #Amount of students that passed the UC
            courseApprovedCounter = 0 #Amount of students that has the course
            approvedExamNoteCounter = Counter() #Note of students that passed using exam
            approvedCourseNoteCounter = Counter() #Note of students that passed with course only
            enrollments = defaultdict(set) #Enrollments for each course edition
            courseExonerations = defaultdict(set) #Exonerations for each course edition
            courseApprovedPerEdition = defaultdict(set) #Approves for each course edition
            examRatioApproved = Counter() #Counter for examratio
            examRatioNotApproved = Counter() #Counter for examratio
            CourseNotApproved = defaultdict(set) #Not approved for each course edition
            CourseNotExonerated = defaultdict(set) #Not exonerated for each course edition
            
            for trace in event_log:
                case_id = trace.attributes["concept:name"]  # student ID
                
                count = 0
                count2 = 0
                has_course = False
                has_exam = False
                is_approved = False
                course_approved = False
                approved_note = 0
                approved_note_course = 0
                
                for event in trace:
                    if (event["concept:name"] == "Evaluation - Exam" and event["Curricular Unit"] == cu):
                        count += 1
                        has_exam = True
                        if(event["Grade"] >= 3): #Se exonera con 3 o mas
                            is_approved = True
                            course_approved = True
                            if(event["Grade"] > approved_note):
                                approved_note = event["Grade"]
                            examRatioApproved[event["time:timestamp"]] += 1
                        else:
                            examRatioNotApproved[event["time:timestamp"]] += 1
                                                            
                    if (event["concept:name"] == "Evaluation - Course" and event["Curricular Unit"] == cu):
                        count2 += 1
                        has_course = True
                        course_year = event["Course Year"]
                        course_edition = event["Course Edition"]
                        key = (course_year, course_edition)
                        if(event["Grade"] >= 6): #Se exonera con 6 o mas
                            is_approved = True
                            if(event["Grade"] > approved_note_course):
                                approved_note_course = event["Grade"]
                            courseExonerations[key].add(case_id)
                        else:
                            CourseNotExonerated[key].add(case_id)
                        if(event["Grade"] >= 3): #Se aprueba con 3 o mas
                            course_approved = True
                            courseApprovedPerEdition[key].add(case_id)
                        else:
                            CourseNotApproved[key].add(case_id)
                    
                    if (event["concept:name"] == "Inscription to Course" and event["Curricular Unit"] == cu):
                        course_year = event["Course Year"]
                        course_edition = event["Course Edition"]
                        key = (course_year, course_edition)
                        enrollments[key].add(case_id)
                    
                if (count > 0):
                    examCounter[count] += 1
                if (count2 > 0):
                    courseCounter[count2] += 1    
                if(has_course):
                    hasCourseCounter += 1
                if(has_exam):
                    hasExamCounter += 1
                if(is_approved):
                    approvedCounter += 1
                if(course_approved):
                    courseApprovedCounter += 1
                if(approved_note > 0):
                    approvedExamNoteCounter[approved_note] += 1
                if(approved_note_course > 0):
                    approvedCourseNoteCounter[approved_note_course] += 1
                    
            enrollmentFinal = {key: len(students) for key, students in enrollments.items()} #Amount of enrollments per year and course Formatted
            formattedEnrollmentsCount = { f"{year}-{edition}": count for (year, edition), count in enrollmentFinal.items() }
            
            courseExonerationsFinal = {key: len(students) for key, students in courseExonerations.items()} #Amount of course exonerations per year and course Formatted
            formattedCourseExonerationsCount = { f"{year}-{edition}": count for (year, edition), count in courseExonerationsFinal.items() }
            
            courseApprovedPerEditionFinal = {key: len(students) for key, students in courseApprovedPerEdition.items()} #Amount of course approves per year and course Formatted
            courseApprovedPerEditionCount = { f"{year}-{edition}": count for (year, edition), count in courseApprovedPerEditionFinal.items() }
            
            # Compute ratios
            examExonerationRatio = {}
            all_exam_keys = set(examRatioApproved.keys()).union(examRatioNotApproved.keys())
            for timestamp in all_exam_keys:
                approved = examRatioApproved.get(timestamp, 0)
                not_approved = examRatioNotApproved.get(timestamp, 0)
                total = approved + not_approved
                if total > 0:
                    examExonerationRatio[str(timestamp)] = approved / total
                else:
                    examExonerationRatio[str(timestamp)] = None
                    
            courseApprovalRatio = {}
            all_approval_keys = set(courseApprovedPerEdition.keys()).union(CourseNotApproved.keys())
            for key in all_approval_keys:
                approved = len(courseApprovedPerEdition.get(key, set()))
                not_approved = len(CourseNotApproved.get(key, set()))
                total = approved + not_approved
                if total > 0:
                    courseApprovalRatio[f"{key[0]}-{key[1]}"] = approved / total
                else:
                    courseApprovalRatio[f"{key[0]}-{key[1]}"] = None
                    
            courseExonerationRatio = {}
            all_course_keys = set(courseExonerations.keys()).union(CourseNotApproved.keys())
            for key in all_course_keys:
                passed = len(courseExonerations.get(key, set()))
                failed = len(CourseNotApproved.get(key, set()))
                total = passed + failed
                if total > 0:
                    courseExonerationRatio[f"{key[0]}-{key[1]}"] = passed / total
                else:
                    courseExonerationRatio[f"{key[0]}-{key[1]}"] = None
            
            return jsonify({
                'uuid': caseid,
                'uc': cu,
                'didCourseAmount': hasCourseCounter,#
                'didExamAmount': hasExamCounter,#
                'exoneratedAmount': approvedCounter,#
                'approvedAmount': courseApprovedCounter,#
                'enrollments': formattedEnrollmentsCount,#
                'courseExonerations': formattedCourseExonerationsCount,#
                'courseApproved': courseApprovedPerEditionCount,#
                'examAttemptsPerStudent': dict(examCounter),#
                'courseAttemptsPerStudent': dict(courseCounter),#
                'approvedExamNotes': dict(approvedExamNoteCounter),#
                'approvedCourseNotes': dict(approvedCourseNoteCounter),#
                'courseExonerationRatio': courseExonerationRatio,
                'courseApprovalRatio': courseApprovalRatio,
                'ExamExonerationRatio': examExonerationRatio
            })
            
        else:
            return "Unknown case id"
        
    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500
    
if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9003)