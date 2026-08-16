"""The generic, stage-agnostic pipeline-lifecycle routes: operate on
"whichever stage is current," not any specific stage's own real
capability, so they carry no per-stage awareness (mirrors the same
generic/stage-specific split orchestrator's own declared tools use).

Every endpoint either calls a real registry-level function from runs.py
(list_runs, reset_pipeline, resume_run, start_pipeline — genuine "which run
is current, run history" bookkeeping) or operates on the current run
directly via runs.current(), calling IntegrationRun's own real methods
(review, rerun, add_constraint, ...) — runs.py deliberately doesn't
duplicate any of those as its own proxy functions, see its own module
docstring. Plus the validation that used to live in orchestrator/main.py:
the busy guard and the /rerun stage-mismatch check both live here, since a
check made in one process before a mutating call to a different process is
a real race, not just a relocation.
"""
from fastapi import APIRouter, HTTPException
from fastapi.responses import JSONResponse
from pydantic import BaseModel, Field

from integration_runner import pipeline, runs, stages

router = APIRouter()

_BUSY_DETAIL = "A stage is still running, try again shortly."


class StartRequest(BaseModel):
    platform_description: str
    seed_url: str
    model: str | None = None
    hint: str | None = None
    exclude_urls: list[str] | None = None
    max_pages: int | None = Field(default=None, ge=1)
    max_depth: int | None = Field(default=None, ge=1)
    force_refresh: bool | None = None
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


class ConstraintRequest(BaseModel):
    constraint: str


class StageRunRequest(BaseModel):
    context: dict


class ModelRequest(BaseModel):
    model: str | None = None


@router.get("/health")
def health():
    return {"status": "ok"}


@router.get("/events")
def events_endpoint(since_index: int = 0, run_id: str | None = None):
    if run_id is None or run_id == runs.current_run_id():
        run = runs.current()
        return {
            "run_id": run.run_id,
            "events": run.events[since_index:],
            "current_stage": run.current_stage,
            "busy": run.busy,
            "model": run.model,
            "is_current": True,
        }
    # A past run: read-only, no polling loop needed, always the full list.
    result = runs.get_run_events(run_id)
    if result is None:
        raise HTTPException(status_code=404, detail=f"No run with id {run_id!r}")
    return {"run_id": run_id, **result}


@router.get("/status")
def status_endpoint():
    """Cheap current-state check, no events array — used by orchestrator's
    rerun_stage tool wrapper (which needs the real current stage to build
    POST /rerun/{stage_id}'s path, since the tool itself is never told one)
    and anything else that just needs "what's current," not the full log."""
    run = runs.current()
    return {
        "run_id": run.run_id,
        "current_stage": run.current_stage,
        "busy": run.busy,
        "model": run.model,
        "is_current": True,
    }


@router.get("/stages")
def stages_endpoint():
    """Static pipeline metadata: orchestrator fetches this once and caches
    it, rather than duplicating STAGES/STAGE_DESCRIPTIONS as a second,
    hardcoded copy that could drift from this, the real source."""
    return {"stages": pipeline.STAGES, "descriptions": stages.STAGE_DESCRIPTIONS}


@router.get("/runs")
def runs_endpoint():
    return runs.list_runs()


@router.post("/model")
def model_endpoint(request: ModelRequest):
    """Changes the model for the rest of the run, not just what /start chose,
    every subsequent real chat() call picks this up. None means ai-layer's
    own automatic routing."""
    runs.current().set_model(request.model)
    return {"model": request.model}


@router.post("/start", status_code=202)
def start_endpoint(request: StartRequest):
    # start_pipeline() swaps in a brand-new IntegrationRun, so without this
    # guard a restart mid-run wouldn't error, it would silently orphan the
    # old run's background thread instead of rejecting the request.
    if runs.current().busy:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    docs_options = request.model_dump(
        include={"hint", "exclude_urls", "max_pages", "max_depth", "force_refresh", "mock"}, exclude_none=True
    )
    return runs.start_pipeline(request.platform_description, request.seed_url, request.model, docs_options)


@router.post("/reset")
def reset_endpoint():
    """Replaces the current run with a fresh, blank one — the empty-state
    counterpart to /start, and the "give up on this one" counterpart to
    /resume below. The old run isn't deleted, reset_pipeline() keeps it in
    the run history, it just stops being current."""
    if runs.current().busy:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    runs.reset_pipeline()
    return {"status": "reset"}


@router.post("/resume/{run_id}")
def resume_endpoint(run_id: str):
    """Makes a past run current again, so it can be approved/retried/messaged
    like any other live run, picking up exactly where it left off. 404 for
    an unknown run_id."""
    if runs.current().busy:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    try:
        return runs.resume_run(run_id)
    except ValueError as e:
        raise HTTPException(status_code=404, detail=str(e))


@router.post("/review/{stage_id}")
def review_endpoint(stage_id: str, request: ReviewRequest):
    if runs.current().busy:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    try:
        result = runs.current().review(stage_id, request.approved, request.correction)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))
    if result["status"] == "started":
        return JSONResponse(status_code=202, content=result)
    return result


@router.post("/rerun/{stage_id}", status_code=202)
def rerun_endpoint(stage_id: str, request: RerunRequest | None = None):
    run = runs.current()
    if run.busy:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    if stage_id != run.current_stage:
        raise HTTPException(
            status_code=400,
            detail=f"'{stage_id}' is not the current pending stage (current: {run.current_stage!r}).",
        )
    overrides = request.overrides.model_dump(exclude_none=True) if request and request.overrides else {}
    try:
        return run.rerun(overrides)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))


@router.post("/constraint/{stage}")
def constraint_endpoint(stage: str, request: ConstraintRequest):
    """Records a correction against a stage without rerunning it — the
    add_constraint tool's real HTTP target. The correction is folded into
    that stage's prompt automatically the next time it runs (via POST
    /rerun, or the pipeline advancing into it)."""
    runs.current().add_constraint(stage, request.constraint)
    return {"status": "recorded", "stage": stage}


@router.post("/stage/run", status_code=202)
def stage_run_endpoint(request: StageRunRequest):
    """Starts the CURRENT pending stage running with new context — the
    run_stage tool's real HTTP target. No {stage_id} in the path: the tool
    itself is never told one, "current stage" is resolved server-side, same
    as it always has been."""
    if runs.current().busy:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    return runs.current().start_stage_run(request.context)
