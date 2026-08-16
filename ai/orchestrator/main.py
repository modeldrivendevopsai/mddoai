from fastapi import FastAPI, HTTPException, Request
from fastapi.responses import JSONResponse
from pydantic import BaseModel, Field

import assistant
import chat_log
from clients import ai_layer_client, integration_runner_client
from clients.integration_runner_client import IntegrationRunnerError

# Every new raw pipeline event chat_log notices gets narrated via the
# wired-in reactor (see chat_log.set_reactor()'s own docstring for why this
# is late-bound rather than a direct import both ways). Wired explicitly at
# startup, not as a side effect of importing assistant.py elsewhere.
chat_log.set_reactor(assistant.react_to_event)

app = FastAPI(title="MDDOAI Orchestrator")


@app.exception_handler(IntegrationRunnerError)
def integration_runner_error_handler(request: Request, exc: IntegrationRunnerError):
    """integration_runner's own busy/stale-stage/unknown-run errors, caught
    once here instead of a try/except at every endpoint below: this is what
    lets every endpoint stay a plain, thin forwarding call. Reconstructs
    the real status code and message integration_runner itself reported,
    rather than degrading to a generic httpx error string."""
    return JSONResponse(status_code=exc.status_code, content={"detail": exc.detail})


@app.get("/health")
def health():
    return {"status": "ok"}


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
    # Skips the real crawl entirely, the docs stage returns canned
    # placeholder output instead — for local dev, where a real crawl is
    # slow enough to make iterating on the rest of the pipeline painful.
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


class MessageRequest(BaseModel):
    message: str


class ModelRequest(BaseModel):
    model: str | None = None


@app.get("/events")
def events_endpoint(since_index: int = 0, run_id: str | None = None):
    return chat_log.get_events(run_id=run_id, since_index=since_index)


@app.get("/runs")
def runs_endpoint():
    return integration_runner_client.list_runs()


@app.post("/model")
def model_endpoint(request: ModelRequest):
    """Changes the model for the rest of the run, not just what /start chose,
    every subsequent real chat() call picks this up. None means ai-layer's
    own automatic routing."""
    return integration_runner_client.set_model(request.model)


@app.post("/start", status_code=202)
def start_endpoint(request: StartRequest):
    docs_options = request.model_dump(
        include={"hint", "exclude_urls", "max_pages", "max_depth", "force_refresh", "mock"}, exclude_none=True
    )
    return integration_runner_client.start_pipeline(
        request.platform_description, request.seed_url, request.model, docs_options
    )


@app.post("/reset")
def reset_endpoint():
    """Replaces the current run with a fresh, blank one — the empty-state
    counterpart to /start, and the "give up on this one" counterpart to
    /resume below. The old run isn't deleted, integration_runner keeps it
    in its own run history, it just stops being current."""
    return integration_runner_client.reset_pipeline()


@app.post("/resume/{run_id}")
def resume_endpoint(run_id: str):
    """Makes a past run current again, so it can be approved/retried/
    messaged like any other live run, picking up exactly where it left
    off. 404 for an unknown run_id, surfaced via IntegrationRunnerError."""
    return integration_runner_client.resume_run(run_id)


@app.get("/providers")
def providers_endpoint():
    return ai_layer_client.list_providers()


@app.post("/review/{stage_id}")
def review_endpoint(stage_id: str, request: ReviewRequest):
    result = integration_runner_client.review(stage_id, request.approved, request.correction)
    if result["status"] == "started":
        return JSONResponse(status_code=202, content=result)
    return result


@app.post("/rerun/{stage_id}", status_code=202)
def rerun_endpoint(stage_id: str, request: RerunRequest | None = None):
    overrides = request.overrides.model_dump(exclude_none=True) if request and request.overrides else None
    return integration_runner_client.rerun_stage(stage_id, overrides)


@app.post("/message")
def message_endpoint(request: MessageRequest):
    # Deliberately no busy pre-check here, unlike every endpoint above: a
    # message that doesn't need a tool (a status question, small talk)
    # should still get a reply even while a stage is running. Any tool call
    # that WOULD mutate state still hits the real busy guard, inside
    # tool_calling.dispatch_tool()'s own per-call try/except, surfaced as
    # that step's own {"error": ...} result rather than blocking the whole
    # message.
    try:
        return assistant.send_message(request.message)
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
