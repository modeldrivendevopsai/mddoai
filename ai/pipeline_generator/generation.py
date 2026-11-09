"""Generating pipelines from a model: each platform's entry, and a whole
generation as /generate's job reports it."""
import logging

from pipeline_history import PipelineHistory
from platform_registry import PlatformRegistry
from platform_runner import PlatformRunError, run_platform
from platforms import Platform
from workspace import Workspace

logger = logging.getLogger(__name__)

# The other services' own error text can carry stack traces and server paths:
# it is logged for whoever runs the service, never returned.
GENERATION_FAILED = "The pipeline generator failed. Check the service logs for details."


def generate_one(content: str, platform: Platform, registry: PlatformRegistry) -> dict:
    """One platform's result. A failure is reported in the entry, not raised,
    so one platform failing does not discard the others."""
    entry = {
        "platform": {"id": platform.id, "name": platform.name},
        "output_filename": platform.output_filename,
        "pipeline": None,
        "intermediates": [],
        "duration_ms": None,
        "error": None,
    }
    try:
        result = run_platform(content, platform, registry)
    except PlatformRunError as e:
        logger.error("generation failure for platform %s: %s", platform.id, e)
        return {**entry, "error": GENERATION_FAILED}
    entry.update(
        pipeline=result["pipeline"], intermediates=result["intermediates"], duration_ms=result["duration_ms"]
    )
    return entry


def generate_all(
    content: str,
    filename: str,
    source_path: str | None,
    platforms: list[Platform],
    registry: PlatformRegistry,
    workspace: Workspace,
    history: PipelineHistory,
) -> dict:
    """Generates a pipeline for each platform and records the result in the
    history so it can be reopened. Returns what the finished job reports."""
    results = [generate_one(content, platform, registry) for platform in platforms]
    generated = [result for result in results if result["error"] is None]
    pipeline_id = history.record_generation(
        filename=filename,
        content=content,
        source_path=workspace.normalize(source_path) if source_path else None,
        generated=generated,
    )
    return {"pipeline_id": pipeline_id, "results": results}
