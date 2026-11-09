"""Keeps each run on disk, so a restart loses none of them. A run is one file,
runs/<run_id>/run.json, next to the attempt files its stages write there."""
import json
import logging
import os
import uuid
from collections.abc import Callable
from pathlib import Path

from integration_runner.pipeline import IntegrationRun
from integration_runner.stages import _validation

logger = logging.getLogger(__name__)

_FILE = "run.json"


def _path(run_id: str) -> Path:
    return _validation.RUNS_DIR / run_id / _FILE


def save(run: IntegrationRun) -> None:
    """Writes the run to a temp file and renames it into place, so the file is
    always a whole run. A run nothing has happened on is not kept."""
    state = run.snapshot()
    if not state["events"]:
        return
    path = _path(run.run_id)
    path.parent.mkdir(parents=True, exist_ok=True)
    temporary = path.with_name(f".{_FILE}.{uuid.uuid4().hex}")
    try:
        # Values JSON cannot hold are saved as text, so one odd value never
        # stops a run from being kept.
        temporary.write_text(json.dumps(state, default=str, ensure_ascii=False), encoding="utf-8")
        os.replace(temporary, path)
    except BaseException:
        temporary.unlink(missing_ok=True)
        raise


def save_quietly(run: IntegrationRun) -> None:
    """save() for a run that must go on if the disk fails: the failure is
    logged, not raised."""
    try:
        save(run)
    except Exception:
        logger.exception("could not keep run %s on disk", run.run_id)


def load_all(on_change: Callable[[IntegrationRun], None]) -> list[IntegrationRun]:
    """Every run the runs folder holds, idle. A file that is not a saved run
    is skipped with a log line."""
    runs_dir = _validation.RUNS_DIR
    if not runs_dir.is_dir():
        return []
    restored = []
    for folder in runs_dir.iterdir():
        path = folder / _FILE
        if not path.is_file():
            continue
        try:
            run = IntegrationRun.restore(json.loads(path.read_text(encoding="utf-8")), on_change)
            if run.run_id != folder.name:
                raise ValueError(f"run id {run.run_id!r} does not match its folder")
        except (OSError, ValueError) as e:
            logger.warning("skipping %s: %s", path, e)
            continue
        restored.append(run)
    return restored
