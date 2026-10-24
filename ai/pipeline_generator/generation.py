"""Generating a pipeline for one platform, as the entry /generate reports it."""
import logging

from engine_runner import EngineError, run_engine
from platforms import Platform
from workspace import Workspace, WorkspaceError

logger = logging.getLogger(__name__)

# The engine's own stderr can carry stack traces and server paths: it is
# logged for whoever runs the service, never returned.
_ENGINE_FAILED = "The pipeline generator failed. Check the service logs for details."


def generate_one(content: str, platform: Platform, workspace: Workspace, save_to: str | None) -> dict:
    """One platform's result. An engine failure is reported in the entry, not
    raised, so one platform failing does not discard the others. A failed save
    is reported alongside the pipeline, which was still generated."""
    entry = {
        "platform": {"id": platform.id, "name": platform.name},
        "output_filename": platform.output_filename,
        "pipeline": None,
        "intermediates": [],
        "duration_ms": None,
        "saved_to": None,
        "replaced": False,
        "save_error": None,
        "error": None,
    }
    try:
        result = run_engine(content, platform)
    except EngineError as e:
        logger.error("engine failure for platform %s: %s", platform.id, e)
        return {**entry, "error": _ENGINE_FAILED}
    entry.update(
        pipeline=result["pipeline"], intermediates=result["intermediates"], duration_ms=result["duration_ms"]
    )
    if save_to is not None:
        try:
            saved = workspace.write_text(save_to, platform.output_filename, result["pipeline"])
            entry["saved_to"] = saved.path
            entry["replaced"] = saved.replaced
        except WorkspaceError as e:
            entry["save_error"] = e.message
    return entry
