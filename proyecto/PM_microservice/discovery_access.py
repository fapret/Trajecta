import os
from flask import jsonify

from metadata_store import get_discovery_metadata, init_metadata_db


init_metadata_db()


MODE_TO_FOLDER = {
    "reference": "./reference",
    "log": "./imports2",
}


def validate_workspace_access(discovery_id, workspace_uuid, allowed_modes=None):
    metadata = get_discovery_metadata(discovery_id)
    if metadata is None:
        return None, (jsonify({"error": "Unknown discovery id"}), 404)

    if metadata["workspace_uuid"] != workspace_uuid:
        return None, (jsonify({"error": "Forbidden discovery access"}), 403)

    if allowed_modes and metadata["mode"] not in allowed_modes:
        return None, (jsonify({"error": "Unknown discovery id"}), 404)

    return metadata, None


def resolve_discovery_xes_path(metadata):
    folder = MODE_TO_FOLDER.get(metadata["mode"])
    if folder is None:
        return None
    return os.path.join(folder, f"{metadata['discovery_id']}.xes")
