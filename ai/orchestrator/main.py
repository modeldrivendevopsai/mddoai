from fastapi import FastAPI, HTTPException
from fastapi.responses import JSONResponse
from pydantic import BaseModel, Field

import assistant
import orchestrator
from orchestrator import current_stage, events, is_busy, rerun_stage, review, start_pipeline

# Every recorded event reacts automatically (see orchestrator.record_event()),
# but orchestrator.py has no knowledge of assistant.py, it only exposes a
# blank hook. This wires the real reactor in, explicitly, at startup, rather
# than as an easy-to-miss side effect of importing assistant.py elsewhere.
orchestrator.set_reactor(assistant.react_to_event)

app = FastAPI(title="MDDOAI Orchestrator")

_BUSY_DETAIL = "A stage is still running, try again shortly."


class StartRequest(BaseModel):
    platform_description: str
    seed_url: str


class ReviewRequest(BaseModel):
    approved: bool
    correction: str | None = None


class RerunOverrides(BaseModel):
    seed_url: str | None = None
    hint: str | None = None
    exclude_urls: list[str] | None = None
    max_pages: int | None = Field(default=None, ge=1)
    max_depth: int | None = Field(default=None, ge=1)
    force_refresh: bool | None = None


class RerunRequest(BaseModel):
    overrides: RerunOverrides | None = None


class NudgeRequest(BaseModel):
    message: str


@app.get("/events")
def events_endpoint(since_index: int = 0):
    return {"events": events()[since_index:], "current_stage": current_stage(), "busy": is_busy()}


@app.post("/start", status_code=202)
def start_endpoint(request: StartRequest):
    return start_pipeline(request.platform_description, request.seed_url)


@app.post("/review/{stage_id}")
def review_endpoint(stage_id: str, request: ReviewRequest):
    if is_busy():
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    try:
        result = review(stage_id, request.approved, request.correction)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))
    if result["status"] == "started":
        return JSONResponse(status_code=202, content=result)
    return result


@app.post("/rerun/{stage_id}", status_code=202)
def rerun_endpoint(stage_id: str, request: RerunRequest | None = None):
    if is_busy():
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    if stage_id != current_stage():
        raise HTTPException(
            status_code=400,
            detail=f"'{stage_id}' is not the current pending stage (current: {current_stage()!r}).",
        )
    overrides = request.overrides.model_dump(exclude_none=True) if request and request.overrides else {}
    try:
        return rerun_stage(overrides)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))


@app.post("/nudge")
def nudge_endpoint(request: NudgeRequest):
    if is_busy():
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    try:
        return assistant.nudge(request.message)
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
