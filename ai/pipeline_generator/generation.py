"""Generating pipelines from a model: each platform's entry, and a whole
generation as /generate's job reports it."""
import logging

from engine_runner import EngineError, run_engine
from pipeline_history import PipelineHistory
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


def generate_all(
    content: str,
    filename: str,
    source_path: str | None,
    save_to: str | None,
    platforms: list[Platform],
    workspace: Workspace,
    history: PipelineHistory,
    registry: PlatformRegistry,
) -> dict:
    """Generates a pipeline for each platform, saves each one into save_to
    when given, and records the result in the history so it can be
    reopened. Returns what the finished job reports."""
    results = [generate_one(content, platform, workspace, save_to, registry) for platform in platforms]
    generated = [result for result in results if result["error"] is None]
    pipeline_id = history.record_generation(
        filename=filename,
        content=content,
        source_path=workspace.normalize(source_path) if source_path else None,
        save_to=save_to,
        generated=generated,
    )
    return {"pipeline_id": pipeline_id, "results": results}
