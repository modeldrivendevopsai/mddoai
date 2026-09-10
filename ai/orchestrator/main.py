from fastapi import FastAPI, HTTPException, Request, UploadFile
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


@app.get("/stages")
def stages_endpoint():
    """Static pipeline metadata (stage list, LLM-narration descriptions,
    and the fuller per-stage input/output/real detail) - a thin proxy to
    integration_runner's own real GET /stages, the same shape
    tools.stage_metadata() already fetches for the system prompt, exposed
    here too so the UI can explain a stage to a human, not just narrate it
    to an LLM."""
    return integration_runner_client.get_stage_metadata()


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


# --- Run/attempt introspection ---------------------------------------------------
# Generic, stage-agnostic: thin proxy to integration_runner's own
# /runs/{run_id}/manifest and /runs/{run_id}/{stage}/{attempt}.
# IntegrationRunnerError (404 for an unknown run/attempt) is handled once,
# above, by integration_runner_error_handler - no try/except needed here.


@app.get("/runs/{run_id}/manifest")
def manifest_endpoint(run_id: str):
    return {"attempts": integration_runner_client.get_run_manifest(run_id)}


@app.get("/runs/{run_id}/{stage}/{attempt}")
def attempt_endpoint(run_id: str, stage: str, attempt: str):
    return integration_runner_client.get_attempt(run_id, stage, attempt)


# --- Prompt-config CRUD (psm/atl/acceleo) -----------------------------------------
# Each of these three real, separate services (psm_agent, atl_agent,
# acceleo_agent) exposes an identically-shaped prompt-config surface,
# reached through integration_runner's own matching /psm, /atl, /acceleo
# proxy routes (see clients/integration_runner_client.py's own
# get_psm_prompt_config/get_atl_prompt_config/get_acceleo_prompt_config
# etc.). One shared request-body shape per concern below (all three
# services' real config schema is identical), one endpoint group per
# prefix - ui-host never reaches integration_runner directly, only through
# this service, the same rule every other endpoint above already follows.


class SaveConfigRequest(BaseModel):
    # No system_prompt field: a config's own first "text" attachment IS
    # the system message (see each real service's own PromptConfigBody,
    # the real schema this pass-through mirrors).
    attachments: list[dict]
    learned_constraints: list[str] = []
    label: str | None = None
    platform_hints: list[str] = []


class LearnedConstraintsRequest(BaseModel):
    constraints: list[str]


class RemoveLearnedConstraintRequest(BaseModel):
    constraint: str


class PromoteConstraintsRequest(BaseModel):
    constraints: list[str]


# --- psm ---


@app.get("/psm/prompt-config/{name}/presets")
def psm_list_presets_endpoint(name: str):
    return {"presets": integration_runner_client.list_psm_presets(name)}


@app.get("/psm/prompt-config/{name}/{preset}")
def psm_get_prompt_config_endpoint(name: str, preset: str):
    return integration_runner_client.get_psm_prompt_config(name, preset)


@app.put("/psm/prompt-config/{name}/{preset}")
def psm_save_prompt_config_endpoint(name: str, preset: str, request: SaveConfigRequest):
    return integration_runner_client.save_psm_prompt_config(name, preset, request.model_dump())


@app.get("/psm/prompt-config/{name}/{preset}/history")
def psm_prompt_config_history_endpoint(name: str, preset: str):
    return {"versions": integration_runner_client.get_psm_prompt_config_history(name, preset)}


@app.get("/psm/prompt-config/{name}/{preset}/diff")
def psm_prompt_config_diff_endpoint(name: str, preset: str, a: str, b: str):
    return integration_runner_client.diff_psm_prompt_config_versions(name, preset, a, b)


@app.post("/psm/prompt-config/{name}/{preset}/restore/{version}")
def psm_restore_prompt_config_endpoint(name: str, preset: str, version: str):
    return integration_runner_client.restore_psm_prompt_config_version(name, preset, version)


@app.post("/psm/prompt-config/{name}/{preset}/revert")
def psm_revert_prompt_config_endpoint(name: str, preset: str):
    return integration_runner_client.revert_psm_prompt_config(name, preset)


@app.post("/psm/prompt-config/{name}/{preset}/promote-to-default")
def psm_promote_prompt_config_to_default_endpoint(name: str, preset: str):
    return integration_runner_client.promote_psm_prompt_config_to_default(name, preset)


@app.get("/psm/prompt-config/{name}/{preset}/check-references")
def psm_check_prompt_config_references_endpoint(name: str, preset: str):
    return {"broken": integration_runner_client.check_psm_prompt_config_references(name, preset)}


@app.post("/psm/prompt-config/{name}/{preset}/preview")
def psm_preview_prompt_config_endpoint(name: str, preset: str):
    return integration_runner_client.preview_psm_prompt_config(name, preset)


@app.post("/psm/prompt-config/{name}/{preset}/learned-constraints")
def psm_add_learned_constraints_endpoint(name: str, preset: str, request: LearnedConstraintsRequest):
    return integration_runner_client.add_psm_learned_constraints(name, preset, request.constraints)


@app.delete("/psm/prompt-config/{name}/{preset}/learned-constraints")
def psm_remove_learned_constraint_endpoint(name: str, preset: str, request: RemoveLearnedConstraintRequest):
    return integration_runner_client.remove_psm_learned_constraint(name, preset, request.constraint)


@app.get("/psm/available-files")
def psm_available_files_endpoint():
    return {"files": integration_runner_client.list_psm_available_files()}


@app.get("/psm/resolve-mode")
def psm_resolve_mode_endpoint(platform_description: str):
    return integration_runner_client.resolve_psm_mode(platform_description)


@app.post("/psm/attachment-uploads")
async def psm_upload_attachment_endpoint(file: UploadFile):
    content = await file.read()
    return {"path": integration_runner_client.upload_psm_attachment_file(file.filename or "upload", content)}


@app.post("/psm/promote-constraints")
def psm_promote_constraints_endpoint(request: PromoteConstraintsRequest):
    return integration_runner_client.promote_psm_constraints(request.constraints)


# --- atl ---


@app.get("/atl/prompt-config/{name}/presets")
def atl_list_presets_endpoint(name: str):
    return {"presets": integration_runner_client.list_atl_presets(name)}


@app.get("/atl/prompt-config/{name}/{preset}")
def atl_get_prompt_config_endpoint(name: str, preset: str):
    return integration_runner_client.get_atl_prompt_config(name, preset)


@app.put("/atl/prompt-config/{name}/{preset}")
def atl_save_prompt_config_endpoint(name: str, preset: str, request: SaveConfigRequest):
    return integration_runner_client.save_atl_prompt_config(name, preset, request.model_dump())


@app.get("/atl/prompt-config/{name}/{preset}/history")
def atl_prompt_config_history_endpoint(name: str, preset: str):
    return {"versions": integration_runner_client.get_atl_prompt_config_history(name, preset)}


@app.get("/atl/prompt-config/{name}/{preset}/diff")
def atl_prompt_config_diff_endpoint(name: str, preset: str, a: str, b: str):
    return integration_runner_client.diff_atl_prompt_config_versions(name, preset, a, b)


@app.post("/atl/prompt-config/{name}/{preset}/restore/{version}")
def atl_restore_prompt_config_endpoint(name: str, preset: str, version: str):
    return integration_runner_client.restore_atl_prompt_config_version(name, preset, version)


@app.post("/atl/prompt-config/{name}/{preset}/revert")
def atl_revert_prompt_config_endpoint(name: str, preset: str):
    return integration_runner_client.revert_atl_prompt_config(name, preset)


@app.post("/atl/prompt-config/{name}/{preset}/promote-to-default")
def atl_promote_prompt_config_to_default_endpoint(name: str, preset: str):
    return integration_runner_client.promote_atl_prompt_config_to_default(name, preset)


@app.get("/atl/prompt-config/{name}/{preset}/check-references")
def atl_check_prompt_config_references_endpoint(name: str, preset: str):
    return {"broken": integration_runner_client.check_atl_prompt_config_references(name, preset)}


@app.post("/atl/prompt-config/{name}/{preset}/preview")
def atl_preview_prompt_config_endpoint(name: str, preset: str):
    return integration_runner_client.preview_atl_prompt_config(name, preset)


@app.post("/atl/prompt-config/{name}/{preset}/learned-constraints")
def atl_add_learned_constraints_endpoint(name: str, preset: str, request: LearnedConstraintsRequest):
    return integration_runner_client.add_atl_learned_constraints(name, preset, request.constraints)


@app.delete("/atl/prompt-config/{name}/{preset}/learned-constraints")
def atl_remove_learned_constraint_endpoint(name: str, preset: str, request: RemoveLearnedConstraintRequest):
    return integration_runner_client.remove_atl_learned_constraint(name, preset, request.constraint)


@app.get("/atl/available-files")
def atl_available_files_endpoint():
    return {"files": integration_runner_client.list_atl_available_files()}


@app.post("/atl/attachment-uploads")
async def atl_upload_attachment_endpoint(file: UploadFile):
    content = await file.read()
    return {"path": integration_runner_client.upload_atl_attachment_file(file.filename or "upload", content)}


@app.post("/atl/promote-constraints")
def atl_promote_constraints_endpoint(request: PromoteConstraintsRequest):
    return integration_runner_client.promote_atl_constraints(request.constraints)


# --- acceleo ---


@app.get("/acceleo/prompt-config/{name}/presets")
def acceleo_list_presets_endpoint(name: str):
    return {"presets": integration_runner_client.list_acceleo_presets(name)}


@app.get("/acceleo/prompt-config/{name}/{preset}")
def acceleo_get_prompt_config_endpoint(name: str, preset: str):
    return integration_runner_client.get_acceleo_prompt_config(name, preset)


@app.put("/acceleo/prompt-config/{name}/{preset}")
def acceleo_save_prompt_config_endpoint(name: str, preset: str, request: SaveConfigRequest):
    return integration_runner_client.save_acceleo_prompt_config(name, preset, request.model_dump())


@app.get("/acceleo/prompt-config/{name}/{preset}/history")
def acceleo_prompt_config_history_endpoint(name: str, preset: str):
    return {"versions": integration_runner_client.get_acceleo_prompt_config_history(name, preset)}


@app.get("/acceleo/prompt-config/{name}/{preset}/diff")
def acceleo_prompt_config_diff_endpoint(name: str, preset: str, a: str, b: str):
    return integration_runner_client.diff_acceleo_prompt_config_versions(name, preset, a, b)


@app.post("/acceleo/prompt-config/{name}/{preset}/restore/{version}")
def acceleo_restore_prompt_config_endpoint(name: str, preset: str, version: str):
    return integration_runner_client.restore_acceleo_prompt_config_version(name, preset, version)


@app.post("/acceleo/prompt-config/{name}/{preset}/revert")
def acceleo_revert_prompt_config_endpoint(name: str, preset: str):
    return integration_runner_client.revert_acceleo_prompt_config(name, preset)


@app.post("/acceleo/prompt-config/{name}/{preset}/promote-to-default")
def acceleo_promote_prompt_config_to_default_endpoint(name: str, preset: str):
    return integration_runner_client.promote_acceleo_prompt_config_to_default(name, preset)


@app.get("/acceleo/prompt-config/{name}/{preset}/check-references")
def acceleo_check_prompt_config_references_endpoint(name: str, preset: str):
    return {"broken": integration_runner_client.check_acceleo_prompt_config_references(name, preset)}


@app.post("/acceleo/prompt-config/{name}/{preset}/preview")
def acceleo_preview_prompt_config_endpoint(name: str, preset: str):
    return integration_runner_client.preview_acceleo_prompt_config(name, preset)


@app.post("/acceleo/prompt-config/{name}/{preset}/learned-constraints")
def acceleo_add_learned_constraints_endpoint(name: str, preset: str, request: LearnedConstraintsRequest):
    return integration_runner_client.add_acceleo_learned_constraints(name, preset, request.constraints)


@app.delete("/acceleo/prompt-config/{name}/{preset}/learned-constraints")
def acceleo_remove_learned_constraint_endpoint(name: str, preset: str, request: RemoveLearnedConstraintRequest):
    return integration_runner_client.remove_acceleo_learned_constraint(name, preset, request.constraint)


@app.get("/acceleo/available-files")
def acceleo_available_files_endpoint():
    return {"files": integration_runner_client.list_acceleo_available_files()}


@app.post("/acceleo/attachment-uploads")
async def acceleo_upload_attachment_endpoint(file: UploadFile):
    content = await file.read()
    return {"path": integration_runner_client.upload_acceleo_attachment_file(file.filename or "upload", content)}


@app.post("/acceleo/promote-constraints")
def acceleo_promote_constraints_endpoint(request: PromoteConstraintsRequest):
    return integration_runner_client.promote_acceleo_constraints(request.constraints)
