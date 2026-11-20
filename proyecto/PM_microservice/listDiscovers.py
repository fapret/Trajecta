'''
Created on 15 jul. 2025

@author: Santiago Nicolas Diaz Conde
'''
from flask import Flask, jsonify, send_from_directory
import os
import re
from flask_cors import CORS

app = Flask(__name__)
CORS(app) #Without cors it gets blocked

MODEL_FILES = {
    'dfg': ('dfg', '.dfg'),
    'bpmn': ('bpmn', '.bpmn'),
    'pnml_alpha': ('pnml', '_alpha.pnml'),
    'pnml_heuristics': ('pnml', '_heuristics.pnml'),
    'pnml_inductive': ('pnml', '_inductive.pnml'),
    'ptml': ('ptml', '.ptml'),
    'pnml_reference': ('reference', '.pnml'),
}


@app.get('/listdiscovers/download/<model>/<caseid>')
def download_model(model, caseid):
    if model not in MODEL_FILES or not re.fullmatch(r'[A-Za-z0-9_][A-Za-z0-9._+\-]*', caseid):
        return jsonify({'error': 'Unknown model'}), 404
    folder, suffix = MODEL_FILES[model]
    return send_from_directory(os.path.abspath(folder), caseid + suffix,
                               mimetype='application/octet-stream', as_attachment=True)

@app.route('/listdiscovers/', methods=['GET'])
@app.route('/listdiscovers/<mode>', methods=['GET'])
def list_files(mode):
    folder_path = './discovers'
    try:
        files = os.listdir(folder_path)
        # Remove extensions from filenames
        filenames = [os.path.splitext(f)[0] for f in files if os.path.isfile(os.path.join(folder_path, f))]
        return jsonify(filenames)
    except Exception as e:
        return jsonify({'error': str(e)}), 500

@app.route('/listuploads/<mode>', methods=['GET'])
def list_uploads(mode):
    if mode == "reference":
        folder_path = './reference'
    else:
        folder_path = './imports'
    try:
        files = os.listdir(folder_path)
        # Remove extensions from filenames
        filenames = [os.path.splitext(f)[0] for f in files if os.path.isfile(os.path.join(folder_path, f))]
        return jsonify(filenames)
    except Exception as e:
        return jsonify({'error': str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9004)
