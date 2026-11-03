"""Generating a pipeline for a platform, from the files the registry holds.

Three steps, each done by the service that owns it:

1. swarch-agent turns the model into a PIM.
2. execution-agent runs the platform's transformation on the PIM, giving the
   platform's own model.
3. execution-agent runs the platform's template on that model, giving the
   pipeline file.

Every platform goes through the same three steps: nothing here is specific to
one of them.
"""
import logging
import time
from typing import TypedDict

import httpx

from clients import execution_agent_client, swarch_agent_client
from clients.agent_service_errors import AgentServiceError
from platform_registry import PlatformRegistry
from platforms import Platform
from settings import MAX_PIPELINE_BYTES

logger = logging.getLogger(__name__)

# The two models a generation passes through, as the person sees them.
_PIM_TITLE = "Platform-independent model"
_PIM_FILENAME = "PipelinePIM.pimmm"
_PLATFORM_MODEL_FILENAME = "PipelinePSM.xmi"


class IntermediateModel(TypedDict):
    id: str
    title: str
    filename: str
    content: str


class GenerationResult(TypedDict):
    pipeline: str
    intermediates: list[IntermediateModel]
    duration_ms: int


class PlatformRunError(Exception):
    """A step failed or produced no usable pipeline. Distinct from a model
    being invalid: callers check the model first, so a failure here is a
    service or a platform's files breaking, not a bad input. The message is
    for the service log: it can carry other services' own error text."""


def run_platform(content: str, platform: Platform, registry: PlatformRegistry) -> GenerationResult:
    start = time.monotonic()
    try:
        artifacts = registry.artifacts(platform)
    except (OSError, ValueError) as e:
        raise PlatformRunError(f"could not read the files of platform {platform.id} v{platform.version}: {e}") from e
    try:
        pim = swarch_agent_client.to_pim(content)
        platform_model = execution_agent_client.execute_atl(
            artifacts.transformation, pim, artifacts.metamodel, platform.output_model_name
        )
        files = execution_agent_client.execute_acceleo(artifacts.template, platform_model, artifacts.metamodel)
    # KeyError, TypeError and ValueError: an answer that is not the shape the
    # clients expect. Any failure here is this platform's alone.
    except (AgentServiceError, httpx.HTTPError, KeyError, TypeError, ValueError) as e:
        raise PlatformRunError(f"generating for {platform.id} failed: {e}") from e
    if not isinstance(files, dict):
        raise PlatformRunError(f"the template for {platform.id} returned no files")

    pipeline = files.get(platform.output_filename)
    if pipeline is None:
        raise PlatformRunError(
            f"the template for {platform.id} produced {sorted(files)}, not {platform.output_filename}"
        )
    for what, text in (("pipeline", pipeline), ("PIM", pim), ("platform model", platform_model)):
        if len(text.encode("utf-8")) > MAX_PIPELINE_BYTES:
            raise PlatformRunError(f"the {what} for {platform.id} exceeds {MAX_PIPELINE_BYTES} bytes")

    duration_ms = int((time.monotonic() - start) * 1000)
    logger.info("run: platform=%s v%d duration_ms=%d", platform.id, platform.version, duration_ms)
    return {
        "pipeline": pipeline,
        "intermediates": [
            {"id": "pim", "title": _PIM_TITLE, "filename": _PIM_FILENAME, "content": pim},
            {
                "id": "platform_model",
                "title": f"{platform.name} model",
                "filename": _PLATFORM_MODEL_FILENAME,
                "content": platform_model,
            },
        ],
        "duration_ms": duration_ms,
    }
