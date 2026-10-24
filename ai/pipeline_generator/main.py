import logging
import os

import httpx
from fastapi import Depends, FastAPI, HTTPException, Request
from fastapi.exceptions import RequestValidationError
from fastapi.responses import JSONResponse
from pydantic import BaseModel, Field

from clients import validator_agent_client
from engine_runner import MAX_PIPELINE_BYTES
from generation import generate_one
from pipeline_checks import CHECKERS, PipelineValidation
from pipeline_history import PipelineHistory
from platforms import PLATFORMS, Platform, get_platform
from routes import pipelines as pipelines_routes
from routes import workspace as workspace_routes
from routes.dependencies import get_history, get_workspace
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


def _require_platform(platform_id: str) -> Platform:
    platform = get_platform(platform_id)
    if platform is None:
        supported = ", ".join(sorted(PLATFORMS))
        raise HTTPException(status_code=422, detail=f"Unsupported platform '{platform_id}'. Supported: {supported}")
    return platform


def _require_platforms(platform_ids: list[str]) -> list[Platform]:
    """Each named platform once, in the order given."""
    return [_require_platform(platform_id) for platform_id in dict.fromkeys(platform_ids)]


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


@app.get("/platforms")
def list_platforms():
    return {
        "platforms": [
            {"id": p.id, "name": p.name, "output_filename": p.output_filename} for p in PLATFORMS.values()
        ]
    }


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


@app.post("/generate")
def generate_endpoint(
    request: GenerateRequest,
    workspace: Workspace = Depends(get_workspace),
    history: PipelineHistory = Depends(get_history),
):
    """Generates a pipeline for each requested platform, saves each one into
    `save_to` when given, and records the result so it can be reopened."""
    _require_size(request.content, MAX_MODEL_BYTES, "content")
    platforms = _require_platforms(request.platforms)
    logger.info("POST /generate filename=%r platforms=%s", request.filename, [p.id for p in platforms])

    # Re-checked here, not trusted from an earlier /inspect: this endpoint is
    # what actually acts on the content, so it enforces the invariant itself.
    model_validation = _check_model(request)
    if not model_validation["valid"]:
        raise HTTPException(
            status_code=422,
            detail={"message": "The model is not valid, so no pipeline was generated.", "issues": model_validation["issues"]},
        )

    results = [generate_one(request.content, platform, workspace, request.save_to) for platform in platforms]
    generated = [result for result in results if result["error"] is None]
    pipeline_id = history.record_generation(
        filename=request.filename,
        content=request.content,
        source_path=workspace.normalize(request.source_path) if request.source_path else None,
        save_to=request.save_to,
        generated=generated,
    )
    return {"pipeline_id": pipeline_id, "results": results}


@app.post("/validate")
def validate_endpoint(request: PipelineRequest) -> PipelineValidation:
    _require_size(request.content, MAX_PIPELINE_BYTES, "content")
    platform = _require_platform(request.platform)
    logger.info("POST /validate platform=%s", platform.id)
    result = CHECKERS[platform.id](request.content)
    logger.info("POST /validate done platform=%s valid=%s", platform.id, result["valid"])
    return result
