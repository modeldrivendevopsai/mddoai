"""acceleo_agent's REST surface: POST /generate wraps generation.py's real
generate() - the pipeline's real acceleo stage entrypoint (see
integration_runner/stages/acceleo/agent.py, its own thin proxy to this
service). Own container, own port, same reasoning as psm_agent/atl_agent:
each real pipeline capability gets independent deployability, not bundled
into whichever process happens to call it. Simpler than psm_agent: no
generation-vs-knowledge-mode routing, no PIM-concept grounding call - one
real capability, generate a new Acceleo template from this run's own real
PSM artifact and platform docs.
"""
from fastapi import FastAPI, Request
from fastapi.responses import JSONResponse
from pydantic import BaseModel

from generation_toolkit.attachments.files import PathSegmentError

import generation
from routes import files, prompt_config, uploads

app = FastAPI(title="MDDOAI Acceleo Agent")
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


class GenerateRequest(BaseModel):
    psm_artifact: str
    platform_docs: str
    constraints: list[str] | None = None
    model: str | None = None
    run_id: str | None = None
    # Names the calling stage ("acceleo") and its own reserved attempt
    # directory, forwarded through to generation.py's real validator-agent
    # call so a call's compiled .emtl module nests inside that attempt
    # directory - see validator_agent's own main.py for the actual
    # path-safety validation on these values (this service never touches
    # the filesystem with them directly, only passes them through).
    stage: str | None = None
    attempt: str | None = None
    # The per-run "Mock" override (same opt-in as psm_agent's own) - skips
    # the real, slow, billed LLM call, in favor of a fixed already-valid
    # artifact, while still resolving the real prompt config and running
    # the real validator-agent call. See generation.py's generate() docstring.
    mock: bool = False


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/generate")
def generate_endpoint(request: GenerateRequest):
    return generation.generate(
        request.psm_artifact,
        request.platform_docs,
        constraints=request.constraints,
        model=request.model,
        run_id=request.run_id,
        stage=request.stage,
        attempt=request.attempt,
        mock=request.mock,
    )
