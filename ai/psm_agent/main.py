"""psm_agent's REST surface: POST /psm wraps psm_flow.run(), the pipeline's
real psm stage entrypoint (routes between generation and comparison - see
psm_flow.py's own docstring). POST /compare wraps comparison.compare()
directly for standalone/manual use; psm_flow.run() calls it internally too
when a platform already has a real metamodel, it's not superseded by /psm.
"""
from dataclasses import asdict

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel

from comparison import compare
from psm_flow import run as run_psm_flow

app = FastAPI(title="MDDOAI PSM Agent")


class CompareRequest(BaseModel):
    serialized_docs: str
    psm_metamodel_path: str | None = None
    model: str | None = None


class PsmRequest(BaseModel):
    platform_description: str
    pim_artifact: str
    platform_docs: str
    constraints: list[str] | None = None
    model: str | None = None


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/compare")
def compare_endpoint(request: CompareRequest):
    try:
        suggestions = compare(request.serialized_docs, request.psm_metamodel_path, model=request.model)
    except FileNotFoundError as e:
        raise HTTPException(status_code=400, detail=f"PSM metamodel not found: {e}")
    return {"suggestions": [asdict(s) for s in suggestions]}


@app.post("/psm")
def psm_endpoint(request: PsmRequest):
    try:
        return run_psm_flow(
            request.platform_description,
            request.pim_artifact,
            request.platform_docs,
            constraints=request.constraints,
            model=request.model,
        )
    except FileNotFoundError as e:
        raise HTTPException(status_code=400, detail=f"PSM metamodel not found: {e}")
