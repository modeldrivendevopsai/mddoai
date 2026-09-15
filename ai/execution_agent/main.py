import logging
import os

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field

from execution_runner import (
    AcceleoExecutionResult,
    AtlExecutionResult,
    ExecutionFailedError,
    ExecutionInfraError,
    run_acceleo_executor,
    run_atl_executor,
)

logging.basicConfig(
    level=os.environ.get("LOG_LEVEL", "INFO").upper(),
    format="%(asctime)s %(levelname)s %(name)s: %(message)s",
)
logger = logging.getLogger(__name__)

app = FastAPI(title="MDDOAI Execution Agent")

MAX_CONTENT_BYTES = int(os.environ.get("MAX_CONTENT_BYTES", str(5 * 1024 * 1024)))


class AtlExecuteRequest(BaseModel):
    atl_source: str = Field(..., min_length=1, description="Real .atl transformation source.")
    pim_model_xmi: str = Field(..., min_length=1, description="A real PIM model instance, XMI text.")
    target_ecore: str = Field(
        ..., min_length=1, description="The target platform's own real PSM metamodel, Ecore text."
    )
    output_model_name: str = Field(default="OUT", description="ATL's own output model name for this transformation.")


class AcceleoExecuteRequest(BaseModel):
    mtl_source: str = Field(..., min_length=1, description="Real .mtl Acceleo template source.")
    psm_model_xmi: str = Field(
        ..., min_length=1, description="A real PSM model instance, XMI text, conforming to target_ecore."
    )
    target_ecore: str = Field(
        ..., min_length=1, description="The target platform's own real PSM metamodel, Ecore text."
    )


def _check_size(field_name: str, content: str) -> None:
    content_bytes = len(content.encode("utf-8"))
    if content_bytes > MAX_CONTENT_BYTES:
        raise HTTPException(status_code=413, detail=f"{field_name} exceeds {MAX_CONTENT_BYTES} bytes")


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/execute/atl", response_model=AtlExecutionResult)
def execute_atl_endpoint(request: AtlExecuteRequest) -> AtlExecutionResult:
    _check_size("atl_source", request.atl_source)
    _check_size("pim_model_xmi", request.pim_model_xmi)
    _check_size("target_ecore", request.target_ecore)

    logger.info("POST /execute/atl output_model_name=%s", request.output_model_name)
    try:
        result = run_atl_executor(
            request.atl_source, request.pim_model_xmi, request.target_ecore, request.output_model_name
        )
    except ExecutionFailedError as e:
        raise HTTPException(status_code=422, detail=str(e))
    except ExecutionInfraError as e:
        logger.error("POST /execute/atl infra failure: %s", e)
        raise HTTPException(status_code=500, detail=str(e))
    logger.info("POST /execute/atl done output_bytes=%d", len(result["output_xmi"].encode("utf-8")))
    return result


@app.post("/execute/acceleo", response_model=AcceleoExecutionResult)
def execute_acceleo_endpoint(request: AcceleoExecuteRequest) -> AcceleoExecutionResult:
    _check_size("mtl_source", request.mtl_source)
    _check_size("psm_model_xmi", request.psm_model_xmi)
    _check_size("target_ecore", request.target_ecore)

    logger.info("POST /execute/acceleo")
    try:
        result = run_acceleo_executor(request.mtl_source, request.psm_model_xmi, request.target_ecore)
    except ExecutionFailedError as e:
        raise HTTPException(status_code=422, detail=str(e))
    except ExecutionInfraError as e:
        logger.error("POST /execute/acceleo infra failure: %s", e)
        raise HTTPException(status_code=500, detail=str(e))
    logger.info("POST /execute/acceleo done files=%d", len(result["generated_files"]))
    return result
