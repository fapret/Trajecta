'''
Created on 19 ago. 2025

@author: sndc3
'''
import pm4py
import os
import threading
from flask import Flask, request, jsonify, send_file
from flask_cors import CORS

import traceback #for debugging
from discovery_access import resolve_discovery_xes_path, validate_workspace_access

app = Flask(__name__)
CORS(app) #Without cors it gets blocked

# Create required folders if doesnt exists
for folder in ['./conformance']:
    os.makedirs(folder, exist_ok=True)

render_status = {}


def run_viewer(mode, reference, caseid, workspace_uuid):
    try:
        render_status[(reference, caseid, mode)] = "rendering"
        metadata, error_response = validate_workspace_access(caseid, workspace_uuid, {'log'})
        if error_response:
            return

        pnfilepath = './pnml/' + reference + '_' + mode + '.pnml'
        elfilepath = resolve_discovery_xes_path(metadata)
        imagepath = './conformance/' + reference + '/alignements/' + caseid + '_' + mode + '.svg'

        if not os.path.exists(pnfilepath):
            return
        if not elfilepath or not os.path.exists(elfilepath):
            return

        os.makedirs('./conformance/' + reference + '/alignements', exist_ok=True)

        event_log = pm4py.read_xes(elfilepath)
        net, im, fm = pm4py.read_pnml(pnfilepath)

        conformance_alignment = pm4py.conformance.conformance_diagnostics_alignments(event_log, net, im, fm)
        pm4py.save_vis_alignments(event_log, conformance_alignment, imagepath)

        render_status[(reference, caseid, mode)] = "completed"
    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500


@app.route('/<mode>/<reference>/<caseid>', methods=['GET'])
def TBR(mode, reference, caseid):
    try:
        # If rendering is ongoing, report status
        if render_status.get((reference, caseid, mode)) == "rendering":
            return jsonify({'status': 'rendering'}), 202

        workspace_uuid = request.args.get('workspace_uuid')
        if not workspace_uuid:
            return jsonify({'error': 'Missing workspace_uuid'}), 400

        metadata, error_response = validate_workspace_access(caseid, workspace_uuid, {'log'})
        if error_response:
            return error_response

        pnfilepath = './pnml/' + reference + '_' + mode + '.pnml'
        elfilepath = resolve_discovery_xes_path(metadata)
        imagepath = './conformance/' + reference + '/alignements/' + caseid + '_' + mode + '.svg'

        if not os.path.exists(pnfilepath):
            return jsonify({'error': 'Unknown reference id'}), 404
        if not elfilepath or not os.path.exists(elfilepath):
            return jsonify({'error': 'Unknown discovery id'}), 404

        if os.path.exists(imagepath):
            return send_file(imagepath, mimetype='image/svg+xml')

        thread = threading.Thread(target=run_viewer, args=(mode, reference, caseid, workspace_uuid))
        thread.start()
        return jsonify({'status': 'rendering started'}), 200

    except Exception as e:
        tb = traceback.format_exc()
        return jsonify({'error': str(e), 'trace': tb}), 500


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=9008)
