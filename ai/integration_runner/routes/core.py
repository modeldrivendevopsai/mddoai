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
import httpx
from fastapi import APIRouter, HTTPException
from fastapi.responses import JSONResponse
from pydantic import BaseModel, Field

from clients.agent_service_errors import AgentServiceError
from integration_runner import pipeline, publishing, runs, stages
from integration_runner.pipeline import BusyError

router = APIRouter()

_BUSY_DETAIL = "A stage is still running, try again shortly."
_NOT_CURRENT_RUN_DETAIL = "This is no longer the current run. Resume it to publish its platform."


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
    """One shared shape for every stage's rerun overrides, even though most
    fields are docs-specific (retrieval's real /fetch parameters) - see
    pipeline.py's own _STAGE_OVERRIDE_KEYS for exactly which stage
    recognizes which field; an override this model allows but the current
    stage doesn't recognize still raises there. `mock` is the one field two
    stages share: docs skips the real crawl, psm skips the real LLM call -
    each stage's own agent reads it from context, this model just carries it."""

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


class PublishRequest(BaseModel):
    """All optional. name and output_filename unset: the run's own platform
    description and the one file its generation stage produced are used (see
    GET /publish). run_id set: publish only if that run is still the current
    one, so a page showing an older run cannot publish a different one."""

    name: str | None = Field(default=None, max_length=100)
    output_filename: str | None = Field(default=None, max_length=255)
    run_id: str | None = None


class StageRunRequest(BaseModel):
    context: dict


class ModelRequest(BaseModel):
    model: str | None = None


class ForkRequest(BaseModel):
    source_run_id: str
    from_stage: str


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
    hardcoded copy that could drift from this, the real source. `details`
    carries the fuller per-stage shape (input/output/real) from
    stages.STAGE_DETAILS, for a UI to explain a stage to a human rather
    than just narrate it to an LLM."""
    return {
        "stages": pipeline.STAGES,
        "descriptions": stages.STAGE_DESCRIPTIONS,
        "details": {stage: vars(info) for stage, info in stages.STAGE_DETAILS.items()},
    }


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
    # start_pipeline() may reuse the current run in place (see its own
    # docstring) instead of always swapping in a brand-new IntegrationRun,
    # but never while the current run is busy — without this guard, a
    # start mid-run would either kick off a second stage run against the
    # same still-executing run (the reuse case) or silently orphan the old
    # run's background thread (the reset case), instead of rejecting the
    # request.
    if runs.current().busy:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    docs_options = request.model_dump(
        include={"hint", "exclude_urls", "max_pages", "max_depth", "force_refresh", "mock"}, exclude_none=True
    )
    try:
        return runs.start_pipeline(request.platform_description, request.seed_url, request.model, docs_options)
    except BusyError:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)


@router.post("/reset")
def reset_endpoint():
    """Replaces the current run with a fresh, blank one — the empty-state
    counterpart to /start, and the "give up on this one" counterpart to
    /resume below. The old run isn't deleted, reset_pipeline() keeps it in
    the run history, it just stops being current."""
    if runs.current().busy:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    try:
        runs.reset_pipeline()
    except BusyError:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
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
    except BusyError:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)


@router.post("/fork")
def fork_endpoint(request: ForkRequest):
    """Starts a genuinely new run, seeded with a past run's own real output
    up to (not including) from_stage, then pauses there pending a human's
    review, same as any other manual-start stage — the real target for "the
    problem was actually in an earlier stage, let me fix that one instead
    of redoing the whole pipeline." source_run itself is untouched, its own
    real history (including its own failure) stays exactly as it happened.
    404 for an unknown source_run_id, 400 for a from_stage that isn't a
    real stage, or one source_run never actually reached."""
    if runs.current().busy:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    if runs.get_run(request.source_run_id) is None:
        raise HTTPException(status_code=404, detail=f"No run with id {request.source_run_id!r}")
    try:
        return runs.fork_run(request.source_run_id, request.from_stage)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))
    except BusyError:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)


@router.post("/review/{stage_id}")
def review_endpoint(stage_id: str, request: ReviewRequest):
    if runs.current().busy:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    try:
        result = runs.current().review(stage_id, request.approved, request.correction)
    except ValueError as e:
        raise HTTPException(status_code=400, detail=str(e))
    except BusyError:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
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
    except BusyError:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)


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
    try:
        return runs.current().start_stage_run(request.context)
    except BusyError:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)


@router.get("/publish")
def publish_defaults_endpoint():
    """What POST /publish would use for the current run when given nothing,
    so a form can show it before anyone presses Publish."""
    return publishing.defaults(runs.current())


@router.post("/publish")
def publish_endpoint(request: PublishRequest):
    """Publishes the current, completed run's approved platform to the
    pipeline generator. 409 while a stage is running or another publish is,
    when run_id is not the current run, or when the run cannot be published
    (not complete, or what it produced cannot be; the reason in detail). 400
    when the pipeline generator refuses what was sent (its reason in detail),
    502 when it cannot be reached or fails.

    The run is claimed busy for the whole publish, like every other mutating
    path, so a double click or a chat tool call racing a button press cannot
    publish two versions, and no stage can start in between."""
    run = runs.current()
    if request.run_id is not None and request.run_id != run.run_id:
        raise HTTPException(status_code=409, detail=_NOT_CURRENT_RUN_DETAIL)
    try:
        run.claim_busy()
    except BusyError:
        raise HTTPException(status_code=409, detail=_BUSY_DETAIL)
    try:
        return publishing.publish(run, request.name, request.output_filename)
    except publishing.NotPublishableError as e:
        raise HTTPException(status_code=409, detail=str(e))
    except AgentServiceError as e:
        raise HTTPException(status_code=400, detail=e.detail)
    except httpx.HTTPError:
        raise HTTPException(status_code=502, detail="The pipeline generator is unavailable, try again shortly.")
    finally:
        run.release_busy()
