from fastapi import FastAPI, HTTPException
from fastapi.responses import JSONResponse
from pydantic import BaseModel, Field

import assistant
import orchestrator
from orchestrator import (
    current_model,
    current_run_id,
    current_stage,
    events,
    get_run_events,
    is_busy,
    list_providers,
    list_runs,
    reset_pipeline,
    resume_run,
    rerun_stage,
    review,
    set_model,
    start_pipeline,
)

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
    model: str | None = None
    # Same shape as RerunOverrides below — the docs stage's real retrieval
    # parameters, settable at start time too, not just on a retry.
    hint: str | None = None
    exclude_urls: list[str] | None = None
    max_pages: int | None = Field(default=None, ge=1)
    max_depth: int | None = Field(default=None, ge=1)
    force_refresh: bool | None = None
    # Skips the real crawl entirely, docs_agent returns canned placeholder
    # output instead (see stage_agents.py) — for local dev, where a real
    # crawl is slow enough to make iterating on the rest of the pipeline
    # painful.
    mock: bool | None = None


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
    mock: bool | None = None


class RerunRequest(BaseModel):
    overrides: RerunOverrides | None = None


class NudgeRequest(BaseModel):
    message: str


class ModelRequest(BaseModel):
    model: str | None = None


@app.get("/events")
def events_endpoint(since_index: int = 0, run_id: str | None = None):
    if run_id is None or run_id == current_run_id():
        return {
            "events": events()[since_index:],
            "current_stage": current_stage(),
            "busy": is_busy(),
            "model": current_model(),
            "is_current": True,
        }
    # A past run: read-only, no polling loop needed, always the full list.
    result = get_run_events(run_id)
    if result is None:
        raise HTTPException(status_code=404, detail=f"No run with id {run_id!r}")
    return result


@app.get("/runs")
def runs_endpoint():
    return list_runs()


@app.post("/model")
def model_endpoint(request: ModelRequest):
    """Changes the model for the rest of the run, not just what /start chose,
    every subsequent real chat() call picks this up. None means ai-layer's
    own automatic routing."""
    set_model(request.model)
    return {"model": request.model}


@app.post("/start", status_code=202)
def start_endpoint(request: StartRequest):
    # Unlike /review, /rerun, and /nudge, this used to have no busy guard:
    # start_pipeline() swaps in a brand-new Orchestrator, so a double-click
    # (or a restart while a stage is genuinely still running) didn't error,
    # it silently orphaned the old run's background thread, which kept
    # burning a real retrieval crawl or LLM call to write its result into an
    # Orchestrator instance nothing could ever read again.
    if is_busy():
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    docs_options = request.model_dump(
        include={"hint", "exclude_urls", "max_pages", "max_depth", "force_refresh", "mock"}, exclude_none=True
    )
    return start_pipeline(request.platform_description, request.seed_url, request.model, docs_options)


@app.post("/reset")
def reset_endpoint():
    """Replaces the current run with a fresh, blank one — the empty-state
    counterpart to /start, and the "give up on this one" counterpart to
    /resume below. The old run isn't deleted, reset_pipeline() keeps it in
    _runs, it just stops being current. Same busy guard as every other
    mutating endpoint, for the same reason /start's guard exists: swapping
    the Orchestrator instance out from under a genuinely in-flight
    background thread just orphans it."""
    if is_busy():
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    reset_pipeline()
    return {"status": "reset"}


@app.post("/resume/{run_id}")
def resume_endpoint(run_id: str):
    """Makes a past run current again, so it can be approved/retried/nudged
    like any other live run, picking up exactly where it left off. Same
    busy guard as /reset and /start, for the same reason: swapping _default
    out from under a genuinely in-flight background thread just orphans it.
    404 for an unknown run_id."""
    if is_busy():
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    try:
        return resume_run(run_id)
    except ValueError as e:
        raise HTTPException(status_code=404, detail=str(e))


@app.get("/providers")
def providers_endpoint():
    return list_providers()


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
