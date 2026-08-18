import logging
import os

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field

from validator_runner import (
    AcceleoValidationResult,
    AtlValidationResult,
    EcoreValidationResult,
    ValidatorInfraError,
    run_acceleo_validator,
    run_atl_validator,
    run_ecore_validator,
)

logging.basicConfig(
    level=os.environ.get("LOG_LEVEL", "INFO").upper(),
    format="%(asctime)s %(levelname)s %(name)s: %(message)s",
)
logger = logging.getLogger(__name__)

app = FastAPI(title="MDDOAI Validator Agent")

MAX_CONTENT_BYTES = int(os.environ.get("MAX_CONTENT_BYTES", str(5 * 1024 * 1024)))


class EcoreValidateRequest(BaseModel):
    filename: str = Field(..., description="Original filename, used only for the temp file suffix/logging.")
    content: str = Field(..., min_length=1, description="Raw .ecore XML content.")
    mode: str = Field(default="reflective", pattern="^(reflective|codegen)$")


class AtlValidateRequest(BaseModel):
    filename: str = Field(..., description="Original filename, used only for the temp file suffix/logging.")
    content: str = Field(..., min_length=1, description="Raw .atl source content.")


class AcceleoValidateRequest(BaseModel):
    filename: str = Field(..., description="Original filename, used only for the temp file suffix/logging.")
    content: str = Field(..., min_length=1, description="Raw .mtl source content.")


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/validate/ecore", response_model=EcoreValidationResult)
def validate_ecore_endpoint(request: EcoreValidateRequest) -> EcoreValidationResult:
    content_bytes = len(request.content.encode("utf-8"))
    if content_bytes > MAX_CONTENT_BYTES:
        raise HTTPException(status_code=413, detail=f"content exceeds {MAX_CONTENT_BYTES} bytes")

    logger.info("POST /validate/ecore filename=%s mode=%s bytes=%d", request.filename, request.mode, content_bytes)
    try:
        result = run_ecore_validator(request.content, request.filename, request.mode)
    except ValidatorInfraError as e:
        logger.error("POST /validate/ecore infra failure: %s", e)
        raise HTTPException(status_code=500, detail=str(e))
    logger.info("POST /validate/ecore done filename=%s valid=%s duration_ms=%d",
                request.filename, result["valid"], result["duration_ms"])
    return result


@app.post("/validate/atl", response_model=AtlValidationResult)
def validate_atl_endpoint(request: AtlValidateRequest) -> AtlValidationResult:
    content_bytes = len(request.content.encode("utf-8"))
    if content_bytes > MAX_CONTENT_BYTES:
        raise HTTPException(status_code=413, detail=f"content exceeds {MAX_CONTENT_BYTES} bytes")

    logger.info("POST /validate/atl filename=%s bytes=%d", request.filename, content_bytes)
    try:
        result = run_atl_validator(request.content, request.filename)
    except ValidatorInfraError as e:
        logger.error("POST /validate/atl infra failure: %s", e)
        raise HTTPException(status_code=500, detail=str(e))
    logger.info("POST /validate/atl done filename=%s valid=%s duration_ms=%d",
                request.filename, result["valid"], result["duration_ms"])
    return result


@app.post("/validate/acceleo", response_model=AcceleoValidationResult)
def validate_acceleo_endpoint(request: AcceleoValidateRequest) -> AcceleoValidationResult:
    content_bytes = len(request.content.encode("utf-8"))
    if content_bytes > MAX_CONTENT_BYTES:
        raise HTTPException(status_code=413, detail=f"content exceeds {MAX_CONTENT_BYTES} bytes")

    logger.info("POST /validate/acceleo filename=%s bytes=%d", request.filename, content_bytes)
    try:
        result = run_acceleo_validator(request.content, request.filename)
    except ValidatorInfraError as e:
        logger.error("POST /validate/acceleo infra failure: %s", e)
        raise HTTPException(status_code=500, detail=str(e))
    logger.info("POST /validate/acceleo done filename=%s valid=%s duration_ms=%d",
                request.filename, result["valid"], result["duration_ms"])
    return result
