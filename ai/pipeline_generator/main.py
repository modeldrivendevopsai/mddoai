import functools
import logging
import os

import httpx
from fastapi import Depends, FastAPI, HTTPException, Request
from fastapi.exceptions import RequestValidationError
from fastapi.responses import JSONResponse
from pydantic import BaseModel, Field

from clients import validator_agent_client
from engine_runner import MAX_PIPELINE_BYTES
from generation import GENERATION_FAILED, generate_all
from generation_jobs import GenerationJobs
from pipeline_checks import PipelineValidation, check_pipeline
from pipeline_history import PipelineHistory
from platform_catalog import all_platforms, get_platform
from platform_registry import PlatformRegistry
from platforms import Platform
from routes import pipelines as pipelines_routes
from routes import platforms as platforms_routes
from routes import workspace as workspace_routes
from routes.dependencies import get_history, get_jobs, get_registry, get_workspace
from settings import MAX_FILENAME_CHARS, MAX_MODEL_BYTES, MAX_PATH_CHARS
from swarch_inspect import Inspection, inspect_swarch
from workspace import Workspace

logging.basicConfig(
    level=os.environ.get("LOG_LEVEL", "INFO").upper(),
    format="%(asctime)s %(levelname)s %(name)s: %(message)s",
)
logger = logging.getLogger(__name__)

# A JSON body is only parsed when the request says it is JSON. Without that, a
# web page open in the person's browser could send a "simple" cross-origin POST
# (no Content-Type, so no preflight) and make this service save files, since it
# has no authentication. FastAPI does this by default from 0.132.0; stated here
# so it cannot be lost by a version change.
app = FastAPI(title="MDDOAI Pipeline Generator", strict_content_type=True)
app.include_router(workspace_routes.router)
app.include_router(pipelines_routes.router)
app.include_router(platforms_routes.router)

# What the validator is always told the model is called. The caller's own
# filename never reaches it: there it would become a file name on disk, and
# its extension would decide how the content is recognised.
_VALIDATOR_FILENAME = "model.swarch"

# A person ticks platforms from a short list. The ceiling only stops a
# request naming the same few over and over.
_MAX_PLATFORMS = 20


class ModelRequest(BaseModel):
    filename: str = Field(..., max_length=MAX_FILENAME_CHARS, description="Name of the model file, shown in the pipelines list.")
    content: str = Field(..., min_length=1, description="Raw .swarch (XMI) content.")


class GenerateRequest(ModelRequest):
    platforms: list[str] = Field(
        ..., min_length=1, max_length=_MAX_PLATFORMS, description="Platform ids from GET /platforms."
    )
    source_path: str | None = Field(
        None, max_length=MAX_PATH_CHARS, description="Where in the workspace the model was loaded from, if it was."
    )
    save_to: str | None = Field(
        None,
        min_length=1,
        max_length=MAX_PATH_CHARS,
        description="A workspace folder to save each generated file into. Omitted: nothing is saved.",
    )


class PipelineRequest(BaseModel):
    platform: str = Field(..., description="A platform id from GET /platforms.")
    content: str = Field(..., min_length=1, description="The generated pipeline file's content.")


@app.exception_handler(RequestValidationError)
def readable_validation_error(_request: Request, exc: RequestValidationError) -> JSONResponse:
    """FastAPI's own 422 body is a list of internal records; every other error
    this service returns carries a plain-text `detail`, so this one does too."""
    problems = "; ".join(
        ".".join(str(part) for part in error["loc"][1:]) + ": " + error["msg"] for error in exc.errors()
    )
    return JSONResponse(status_code=422, content={"detail": problems})


def _require_size(content: str, limit: int, what: str) -> None:
    if len(content.encode("utf-8")) > limit:
        raise HTTPException(status_code=413, detail=f"{what} exceeds {limit} bytes")


def _require_platform(platform_id: str, registry: PlatformRegistry) -> Platform:
    platform = get_platform(platform_id, registry)
    if platform is None:
        supported = ", ".join(sorted(p.id for p in all_platforms(registry)))
        raise HTTPException(status_code=422, detail=f"Unsupported platform '{platform_id}'. Supported: {supported}")
    return platform


def _require_platforms(platform_ids: list[str], registry: PlatformRegistry) -> list[Platform]:
    """Each named platform once, in the order given."""
    return [_require_platform(platform_id, registry) for platform_id in dict.fromkeys(platform_ids)]


def _check_model(request: ModelRequest) -> dict:
    """The real EMF check, via validator-agent. Only severity and message go
    back to the caller: an issue's own `source` is the server-side temp path
    the file happened to be validated from, which means nothing to them."""
    try:
        result = validator_agent_client.validate_swarch(request.content, _VALIDATOR_FILENAME)
    except httpx.HTTPError as e:
        logger.error("validator-agent call failed: %s", e)
        raise HTTPException(status_code=502, detail="The model checker is unavailable, try again shortly.")
    return {
        "valid": result["valid"],
        "issues": [{"severity": issue["severity"], "message": issue["message"]} for issue in result["issues"]],
    }


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/inspect")
def inspect_endpoint(request: ModelRequest):
    """What the uploaded model says, plus whether the real validator accepts
    it. Always 200 for a bad model: an invalid file is an expected answer
    here, not a failed request."""
    _require_size(request.content, MAX_MODEL_BYTES, "content")
    logger.info("POST /inspect filename=%r", request.filename)
    inspection: Inspection | None = inspect_swarch(request.content)
    return {
        "element": inspection["element"] if inspection else None,
        "attributes": inspection["attributes"] if inspection else [],
        "model_validation": _check_model(request),
    }


@app.post("/generate", status_code=202)
def generate_endpoint(
    request: GenerateRequest,
    workspace: Workspace = Depends(get_workspace),
    history: PipelineHistory = Depends(get_history),
    registry: PlatformRegistry = Depends(get_registry),
    jobs: GenerationJobs = Depends(get_jobs),
):
    """Checks the request, then generates after answering: the outcome is
    fetched from GET /generate/{job_id}. A request that cannot be generated
    at all (an unknown platform, an invalid model) is refused here, at once."""
    _require_size(request.content, MAX_MODEL_BYTES, "content")
    platforms = _require_platforms(request.platforms, registry)
    logger.info("POST /generate filename=%r platforms=%s", request.filename, [p.id for p in platforms])

    # Re-checked here, not trusted from an earlier /inspect: this endpoint is
    # what actually acts on the content, so it enforces the invariant itself.
    model_validation = _check_model(request)
    if not model_validation["valid"]:
        raise HTTPException(
            status_code=422,
            detail={"message": "The model is not valid, so no pipeline was generated.", "issues": model_validation["issues"]},
        )

    work = functools.partial(
        generate_all,
        content=request.content,
        filename=request.filename,
        source_path=request.source_path,
        save_to=request.save_to,
        platforms=platforms,
        workspace=workspace,
        history=history,
        registry=registry,
    )
    return {"job_id": jobs.start(work, GENERATION_FAILED)}


@app.get("/generate/{job_id}")
def generation_job_endpoint(job_id: str, jobs: GenerationJobs = Depends(get_jobs)):
    """A generation started by POST /generate: `state` is "running", then
    "done" (with `pipeline_id` and `results`) or "failed" (with `error`)."""
    job = jobs.get(job_id)
    if job is None:
        raise HTTPException(status_code=404, detail="No such generation. It may have been lost in a restart.")
    return job


@app.post("/validate")
def validate_endpoint(
    request: PipelineRequest, registry: PlatformRegistry = Depends(get_registry)
) -> PipelineValidation:
    _require_size(request.content, MAX_PIPELINE_BYTES, "content")
    platform = _require_platform(request.platform, registry)
    logger.info("POST /validate platform=%s", platform.id)
    result = check_pipeline(platform, request.content)
    logger.info("POST /validate done platform=%s valid=%s", platform.id, result["valid"])
    return result
