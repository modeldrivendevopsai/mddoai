"""Generating a pipeline for a platform published from an integration run.

Three steps, each done by the service that owns it:

1. swarch-agent turns the model into a PIM.
2. execution-agent runs the platform's transformation on the PIM, giving the
   platform's own model.
3. execution-agent runs the platform's template on that model, giving the
   pipeline file.

No engine command is involved, so a published platform needs no Java change.
"""
import logging
import time

import httpx

from clients import execution_agent_client, swarch_agent_client
from clients.agent_service_errors import AgentServiceError
from engine_runner import MAX_PIPELINE_BYTES, GenerationResult
from platform_registry import PlatformRegistry
from platforms import Platform

logger = logging.getLogger(__name__)


class PublishedRunError(Exception):
    """A step failed or produced no usable pipeline. Its message is for the
    service log: it can carry other services' own error text."""


def _intermediate(platform: Platform, intermediate_id: str, content: str) -> dict:
    intermediate = next(item for item in platform.intermediates if item.id == intermediate_id)
    return {"id": intermediate.id, "title": intermediate.title, "filename": intermediate.filename, "content": content}


def run_published(content: str, platform: Platform, registry: PlatformRegistry) -> GenerationResult:
    start = time.monotonic()
    version = registry.get(platform.id, platform.version) if platform.version is not None else None
    if version is None:
        raise PublishedRunError(f"published platform {platform.id} v{platform.version} is not available")
    try:
        artifacts = registry.artifacts(version)
    except (OSError, ValueError) as e:
        raise PublishedRunError(f"could not read published platform {platform.id} v{version.version}: {e}") from e
    try:
        pim = swarch_agent_client.to_pim(content)
        platform_model = execution_agent_client.execute_atl(
            artifacts.transformation, pim, artifacts.metamodel, version.output_model_name
        )
        files = execution_agent_client.execute_acceleo(artifacts.template, platform_model, artifacts.metamodel)
    # KeyError, TypeError and ValueError: an answer that is not the shape the
    # clients expect. Any failure here is this platform's alone.
    except (AgentServiceError, httpx.HTTPError, KeyError, TypeError, ValueError) as e:
        raise PublishedRunError(f"generating for {platform.id} failed: {e}") from e
    if not isinstance(files, dict):
        raise PublishedRunError(f"the template for {platform.id} returned no files")

    pipeline = files.get(platform.output_filename)
    if pipeline is None:
        raise PublishedRunError(
            f"the template for {platform.id} produced {sorted(files)}, not {platform.output_filename}"
        )
    if len(pipeline.encode("utf-8")) > MAX_PIPELINE_BYTES:
        raise PublishedRunError(f"the pipeline for {platform.id} exceeds {MAX_PIPELINE_BYTES} bytes")

    duration_ms = int((time.monotonic() - start) * 1000)
    logger.info("published run: platform=%s v%d duration_ms=%d", platform.id, version.version, duration_ms)
    return {
        "pipeline": pipeline,
        "intermediates": [_intermediate(platform, "pim", pim), _intermediate(platform, "platform_model", platform_model)],
        "duration_ms": duration_ms,
    }
