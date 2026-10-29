"""Generating a pipeline for one platform, as the entry /generate reports it."""
import logging

from engine_runner import EngineError, run_engine
from platform_registry import PlatformRegistry
from platforms import ENGINE, Platform
from published_runner import PublishedRunError, run_published
from workspace import Workspace, WorkspaceError

logger = logging.getLogger(__name__)

# The engine's own stderr, and other services' error text, can carry stack
# traces and server paths: they are logged for whoever runs the service,
# never returned.
GENERATION_FAILED = "The pipeline generator failed. Check the service logs for details."


def _run(content: str, platform: Platform, registry: PlatformRegistry) -> dict:
    if platform.runner == ENGINE:
        return run_engine(content, platform)
    return run_published(content, platform, registry)


def generate_one(
    content: str, platform: Platform, workspace: Workspace, save_to: str | None, registry: PlatformRegistry
) -> dict:
    """One platform's result. A generation failure is reported in the entry,
    not raised, so one platform failing does not discard the others. A failed
    save is reported alongside the pipeline, which was still generated."""
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
        result = _run(content, platform, registry)
    except (EngineError, PublishedRunError) as e:
        logger.error("generation failure for platform %s: %s", platform.id, e)
        return {**entry, "error": GENERATION_FAILED}
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
