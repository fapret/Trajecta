'''
Created on 15 jul. 2025

@author: Santiago Nicolas Diaz Conde
'''
from flask import Flask, jsonify, request
from flask_cors import CORS

from metadata_store import init_metadata_db, list_discoveries

app = Flask(__name__)
CORS(app) #Without cors it gets blocked

init_metadata_db()


@app.route('/', methods=['GET'])
def list_workspace_discoveries():
    workspace_uuid = request.args.get('workspace_uuid')
    if not workspace_uuid:
        return jsonify({'error': 'Missing workspace_uuid'}), 400

    mode = request.args.get('mode')
    if mode == '1':
        mode = 'reference'
    elif mode == '2':
        mode = 'log'

    return jsonify(list_discoveries(workspace_uuid, mode))


@app.route('/<mode>', methods=['GET'])
def list_files_deprecated(mode):
    workspace_uuid = request.args.get('workspace_uuid')
    if not workspace_uuid:
        return jsonify({'error': 'Missing workspace_uuid'}), 400

    if mode == '1':
        normalized_mode = 'reference'
    elif mode == '2':
        normalized_mode = 'log'
    else:
        return jsonify({'error': 'Unsupported mode'}), 400

    discoveries = list_discoveries(workspace_uuid, normalized_mode)
    return jsonify([item['discovery_id'] for item in discoveries])

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9004)
