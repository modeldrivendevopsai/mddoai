"""psm_agent's REST surface: POST /psm wraps psm_flow.run(), the pipeline's
real psm stage entrypoint (routes between generation and comparison - see
psm_flow.py's own docstring). POST /compare wraps comparison.compare()
directly for standalone/manual use; psm_flow.run() calls it internally too
when a platform already has a real metamodel, it's not superseded by /psm.
"""
from dataclasses import asdict

from fastapi import FastAPI, HTTPException, Request
from fastapi.responses import JSONResponse
from pydantic import BaseModel

from generation_toolkit.attachments.files import PathSegmentError

from comparison import compare, resolve_platform_metamodel
from psm_flow import run as run_psm_flow
from routes import files, prompt_config, uploads

app = FastAPI(title="MDDOAI PSM Agent")
app.include_router(prompt_config.router)
app.include_router(files.router)
app.include_router(uploads.router)


@app.exception_handler(PathSegmentError)
def _path_segment_error_handler(request: Request, exc: PathSegmentError) -> JSONResponse:
    # A real, user-suppliable name/version route parameter failed
    # generation_toolkit.prompt_config's own path-safety validation - a
    # real 400 (bad request), not an unhandled 500, registered once here
    # rather than a try/except repeated in every prompt_config.py handler.
    return JSONResponse(status_code=400, content={"detail": str(exc)})


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
    run_id: str | None = None
    # Names the calling stage ("psm") and its own reserved attempt
    # directory, forwarded through to generation.py's real validator-agent
    # call so a generation-mode call's compiled Ecore classes nest inside
    # that attempt directory - see validator_agent's own main.py for the
    # actual path-safety validation on these values (this service never
    # touches the filesystem with them directly, only passes them through,
    # so it doesn't duplicate that check, matching run_id's own existing
    # plain-passthrough treatment above).
    stage: str | None = None
    attempt: str | None = None
    # The per-run "Mock" override (same opt-in as docs_stage's own
    # context["mock"]) - skips the real, slow, billed LLM call on the
    # generation branch only, in favor of a fixed already-valid artifact,
    # while still resolving the real prompt config and running the real
    # validator-agent call. See generation.py's generate() docstring.
    mock: bool = False


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


@app.get("/resolve-mode")
def resolve_mode_endpoint(platform_description: str):
    """Which real mode a POST /psm call for this platform_description would
    take, without spending any real work (no LLM call, no grounding, no
    validation) - psm_flow.run()'s own real routing decision
    (resolve_platform_metamodel), exposed read-only so a human reviewing the
    generation prompt before a first attempt can see up front whether this
    platform is actually new, or already has a real metamodel and will
    route to a drift-check instead."""
    metamodel_path = resolve_platform_metamodel(platform_description)
    if metamodel_path is None:
        return {"mode": "generation", "metamodel_path": None}
    return {"mode": "knowledge", "metamodel_path": metamodel_path}


@app.post("/psm")
def psm_endpoint(request: PsmRequest):
    try:
        return run_psm_flow(
            request.platform_description,
            request.pim_artifact,
            request.platform_docs,
            constraints=request.constraints,
            model=request.model,
            run_id=request.run_id,
            stage=request.stage,
            attempt=request.attempt,
            mock=request.mock,
        )
    except FileNotFoundError as e:
        raise HTTPException(status_code=400, detail=f"PSM metamodel not found: {e}")
