import sqlite3
import threading
from datetime import datetime, timezone
from pathlib import Path


BASE_DIR = Path(__file__).resolve().parent
DB_PATH = BASE_DIR / "pm_metadata.db"

_DB_LOCK = threading.Lock()


def _utc_now_iso() -> str:
    return datetime.now(timezone.utc).isoformat()


def init_metadata_db() -> None:
    with _DB_LOCK:
        conn = sqlite3.connect(DB_PATH)
        try:
            conn.execute(
                """
                CREATE TABLE IF NOT EXISTS discovery_metadata (
                    discovery_id TEXT PRIMARY KEY,
                    workspace_uuid TEXT NOT NULL,
                    mode TEXT NOT NULL,
                    display_name TEXT NOT NULL,
                    original_name TEXT,
                    status TEXT NOT NULL,
                    created_at TEXT NOT NULL,
                    updated_at TEXT NOT NULL,
                    completed_at TEXT
                )
                """
            )
            conn.execute(
                """
                CREATE INDEX IF NOT EXISTS idx_discovery_workspace_mode_name
                ON discovery_metadata(workspace_uuid, mode, display_name)
                """
            )
            conn.commit()
        finally:
            conn.close()


def name_exists(workspace_uuid: str, mode: str, display_name: str) -> bool:
    with _DB_LOCK:
        conn = sqlite3.connect(DB_PATH)
        try:
            row = conn.execute(
                """
                SELECT 1
                FROM discovery_metadata
                WHERE workspace_uuid = ? AND mode = ? AND display_name = ?
                LIMIT 1
                """,
                (workspace_uuid, mode, display_name),
            ).fetchone()
            return row is not None
        finally:
            conn.close()


def insert_discovery_metadata(
    discovery_id: str,
    workspace_uuid: str,
    mode: str,
    display_name: str,
    original_name: str | None,
    status: str = "started",
) -> None:
    now_iso = _utc_now_iso()
    with _DB_LOCK:
        conn = sqlite3.connect(DB_PATH)
        try:
            conn.execute(
                """
                INSERT INTO discovery_metadata (
                    discovery_id,
                    workspace_uuid,
                    mode,
                    display_name,
                    original_name,
                    status,
                    created_at,
                    updated_at,
                    completed_at
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, NULL)
                """,
                (
                    discovery_id,
                    workspace_uuid,
                    mode,
                    display_name,
                    original_name,
                    status,
                    now_iso,
                    now_iso,
                ),
            )
            conn.commit()
        finally:
            conn.close()


def update_discovery_status(discovery_id: str, status: str) -> None:
    now_iso = _utc_now_iso()
    completed_at = now_iso if status in {"completed", "failed"} else None
    with _DB_LOCK:
        conn = sqlite3.connect(DB_PATH)
        try:
            conn.execute(
                """
                UPDATE discovery_metadata
                SET status = ?, updated_at = ?, completed_at = ?
                WHERE discovery_id = ?
                """,
                (status, now_iso, completed_at, discovery_id),
            )
            conn.commit()
        finally:
            conn.close()
