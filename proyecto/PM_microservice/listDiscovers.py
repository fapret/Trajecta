'''
Created on 15 jul. 2025

@author: Santiago Nicolas Diaz Conde
'''
from flask import Flask, jsonify
import os
from flask_cors import CORS

app = Flask(__name__)
CORS(app) #Without cors it gets blocked

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