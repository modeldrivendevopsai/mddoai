import sys
from pathlib import Path

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from router.config import AVAILABLE
from router.router import AUTO, chat

# orchestrator.py lives in the sibling ai/orchestrator/ module, not inside ai-layer,
# so it has to be added to sys.path explicitly before the import below resolves.
_ORCHESTRATOR_DIR = Path(__file__).resolve().parent.parent / "orchestrator"
if str(_ORCHESTRATOR_DIR) not in sys.path:
    sys.path.insert(0, str(_ORCHESTRATOR_DIR))

from orchestrator import current_stage, judge, rerun_stage, reset_pipeline, run_stage, stage_result  # noqa: E402

app = FastAPI(title="MDDOAI AI Layer")


class ChatRequest(BaseModel):
    messages: list[dict]
    model: str | None = None


class ChatResponse(BaseModel):
    content: str
    model: str


class Provider(BaseModel):
    name: str
    tier: str


class ReviewRequest(BaseModel):
    approved: bool
    correction: str | None = None


class StartRequest(BaseModel):
    platform_description: str


class JudgeRequest(BaseModel):
    message: str


@app.get("/health")
def health():
    return {"status": "ok"}


@app.get("/providers", response_model=list[Provider])
def providers():
    return [{"name": m["name"], "tier": m["tier"]} for m in AVAILABLE]


@app.post("/chat", response_model=ChatResponse)
def chat_endpoint(request: ChatRequest):
    valid_names = {m["name"] for m in AVAILABLE}
    if request.model is not None and request.model != AUTO and request.model not in valid_names:
        raise HTTPException(
            status_code=400,
            detail=f"Unknown model '{request.model}'. Valid options: {sorted(valid_names)} or '{AUTO}'.",
        )
    try:
        response = chat(request.messages, model=request.model)
        return {"content": response.choices[0].message.content, "model": response.model}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))


@app.post("/orchestrate/start")
def orchestrate_start_endpoint(request: StartRequest):
    reset_pipeline()
    try:
        return run_stage({"platform_description": request.platform_description})
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))


@app.post("/orchestrate/rerun/{stage_id}")
def orchestrate_rerun_endpoint(stage_id: str):
    if stage_id != current_stage():
        raise HTTPException(
            status_code=400,
            detail=f"'{stage_id}' is not the current pending stage (current: {current_stage()!r}).",
        )
    try:
        return rerun_stage()
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))


@app.post("/orchestrate/judge")
def orchestrate_judge_endpoint(request: JudgeRequest):
    try:
        return judge(request.message)
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))


@app.post("/review/{stage_id}")
def review_endpoint(stage_id: str, request: ReviewRequest):
    if stage_id != current_stage():
        raise HTTPException(
            status_code=400,
            detail=f"'{stage_id}' is not the current pending stage (current: {current_stage()!r}).",
        )
    if not request.approved and not request.correction:
        raise HTTPException(status_code=400, detail="correction is required when approved is False.")
    return stage_result(stage_id, request.approved, request.correction)
