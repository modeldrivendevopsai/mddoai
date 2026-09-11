"""HTTP client for integration_runner: the pipeline state machine and run
history, reached only over HTTP, never imported as a Python package.

Two kinds of failure, handled differently, matching this repo's established
client pattern (see ai_layer_client.py) but extended for one real difference:
integration_runner reports its own real business errors (a stale stage id,
busy, an unknown run) as 400/404/409 with a crisp `detail` message, not just
infrastructure failures. Those are raised as IntegrationRunnerError, carrying
the real status code and message through unchanged, rather than degrading to
a raw httpx.HTTPStatusError string. A genuine connection failure or 5xx
still propagates raw, there's no fallback for those, same as every other
client in this repo.
"""
import os

import httpx

INTEGRATION_RUNNER_URL = os.environ.get("INTEGRATION_RUNNER_URL", "http://localhost:8050")
# 16 minutes: real margin above retrieval_client.RETRIEVAL_TIMEOUT (15 min),
# for /docs/extend specifically — see add_page_to_docs's own docstring for
# why that one endpoint needs more than the fast-bookkeeping default below.
DOCS_EXTEND_TIMEOUT = float(os.environ.get("DOCS_EXTEND_TIMEOUT", "960.0"))

_BUSINESS_ERROR_CODES = (400, 404, 409)


class IntegrationRunnerError(Exception):
    """One of integration_runner's own reported business errors (stale
    stage id, busy, unknown run), not an infrastructure failure. Callers
    that want the real HTTP status back (main.py's exception handler, the
    orchestrator's own tool implementations) read status_code/detail
    directly rather than parsing str(e)."""

    def __init__(self, status_code: int, detail: str):
        self.status_code = status_code
        self.detail = detail
        super().__init__(detail)


def _request(method: str, path: str, timeout: float = 10.0, **kwargs) -> httpx.Response:
    response = httpx.request(method, f"{INTEGRATION_RUNNER_URL}{path}", timeout=timeout, **kwargs)
    if response.status_code in _BUSINESS_ERROR_CODES:
        try:
            detail = response.json().get("detail", response.text)
        except ValueError:
            detail = response.text
        raise IntegrationRunnerError(response.status_code, detail)
    response.raise_for_status()
    return response


def get_events(since_index: int = 0, run_id: str | None = None) -> dict:
    params = {"since_index": since_index}
    if run_id is not None:
        params["run_id"] = run_id
    return _request("GET", "/events", params=params).json()


def get_status() -> dict:
    return _request("GET", "/status").json()


def get_stage_metadata() -> dict:
    """{"stages": [...], "descriptions": {...}} — static, safe to cache
    client-side once fetched (the caller's own lazy caching, not done here,
    this function always makes a real call)."""
    return _request("GET", "/stages").json()


def list_runs() -> list[dict]:
    return _request("GET", "/runs").json()


def start_pipeline(
    platform_description: str,
    seed_url: str,
    model: str | None = None,
    docs_options: dict | None = None,
) -> dict:
    payload = {"platform_description": platform_description, "seed_url": seed_url, "model": model}
    payload.update(docs_options or {})
    return _request("POST", "/start", json=payload).json()


def reset_pipeline() -> dict:
    return _request("POST", "/reset").json()


def resume_run(run_id: str) -> dict:
    return _request("POST", f"/resume/{run_id}").json()


def review(stage_id: str, approved: bool, correction: str | None = None) -> dict:
    payload = {"approved": approved, "correction": correction}
    return _request("POST", f"/review/{stage_id}", json=payload).json()


def rerun_stage(stage_id: str, overrides: dict | None = None) -> dict:
    payload = {"overrides": overrides} if overrides else None
    return _request("POST", f"/rerun/{stage_id}", json=payload).json()


def add_constraint(stage: str, constraint: str) -> dict:
    return _request("POST", f"/constraint/{stage}", json={"constraint": constraint}).json()


def start_stage_run(context: dict) -> dict:
    return _request("POST", "/stage/run", json={"context": context}).json()


def add_page_to_docs(url: str, force_refresh: bool = False) -> dict:
    """Fetches one specific page for real and appends it to the docs
    stage's current pending output — the add_page_to_docs tool's real HTTP
    target (POST /docs/extend). An add, not a replace: steering/redoing the
    whole crawl instead goes through rerun_stage(), not this.

    16-minute timeout, not the client's usual 10s default: unlike every other
    endpoint here, /docs/extend runs a real page fetch synchronously inline
    (retrieval_client.fetch_page's own budget is 15 minutes, deliberately
    generous for real free-tier LLM latency) instead of kicking off
    background work and returning immediately, so it needs real margin above
    that inner budget rather than a quick-response default."""
    return _request(
        "POST", "/docs/extend", timeout=DOCS_EXTEND_TIMEOUT, json={"url": url, "force_refresh": force_refresh}
    ).json()


def set_model(model: str | None) -> dict:
    return _request("POST", "/model", json={"model": model}).json()


# --- Generic run/attempt introspection (routes/attempts.py) ---------------


def get_run_manifest(run_id: str) -> list[dict]:
    return _request("GET", f"/runs/{run_id}/manifest").json()["attempts"]


def get_attempt(run_id: str, stage: str, attempt: str) -> dict:
    return _request("GET", f"/runs/{run_id}/{stage}/{attempt}").json()


# --- psm's own prompt-config pass-throughs (routes/psm.py) ----------------
# Every one of these is itself a thin pass-through on integration_runner's
# own side too (see routes/psm.py), all the way down to psm_agent, which is
# the service that actually owns this data - integration_runner and this
# client both just relay it.


def get_psm_prompt_config(name: str) -> dict:
    return _request("GET", f"/psm/prompt-config/{name}").json()


def save_psm_prompt_config(name: str, config: dict) -> dict:
    return _request("PUT", f"/psm/prompt-config/{name}", json=config).json()


def get_psm_prompt_config_history(name: str) -> list[str]:
    return _request("GET", f"/psm/prompt-config/{name}/history").json()["versions"]


def diff_psm_prompt_config_versions(name: str, version_a: str, version_b: str) -> dict:
    return _request("GET", f"/psm/prompt-config/{name}/diff", params={"a": version_a, "b": version_b}).json()


def restore_psm_prompt_config_version(name: str, version: str) -> dict:
    return _request("POST", f"/psm/prompt-config/{name}/restore/{version}").json()


def revert_psm_prompt_config(name: str) -> dict:
    return _request("POST", f"/psm/prompt-config/{name}/revert").json()


def promote_psm_prompt_config_to_default(name: str) -> dict:
    return _request("POST", f"/psm/prompt-config/{name}/promote-to-default").json()


def check_psm_prompt_config_references(name: str) -> list[dict]:
    return _request("GET", f"/psm/prompt-config/{name}/check-references").json()["broken"]


def preview_psm_prompt_config(name: str) -> dict:
    return _request("POST", f"/psm/prompt-config/{name}/preview").json()


def add_psm_learned_constraints(name: str, constraints: list[str]) -> dict:
    return _request(
        "POST", f"/psm/prompt-config/{name}/learned-constraints", json={"constraints": constraints}
    ).json()


def remove_psm_learned_constraint(name: str, constraint: str) -> dict:
    return _request(
        "DELETE", f"/psm/prompt-config/{name}/learned-constraints", json={"constraint": constraint}
    ).json()


def list_psm_available_files() -> list[str]:
    return _request("GET", "/psm/available-files").json()["files"]


def promote_psm_constraints(constraints: list[str]) -> dict:
    return _request("POST", "/psm/promote-constraints", json={"constraints": constraints}).json()


def resolve_psm_mode(platform_description: str) -> dict:
    return _request("GET", "/psm/resolve-mode", params={"platform_description": platform_description}).json()


def upload_psm_attachment_file(filename: str, content: bytes) -> str:
    """POSTs a real multipart file upload to integration_runner's own real
    /psm/attachment-uploads (a thin proxy over psm_agent's own real upload
    endpoint) - returns the real, safe stored path to use as a new "file"
    attachment's own `path`."""
    return _request(
        "POST", "/psm/attachment-uploads", files={"file": (filename, content)}
    ).json()["path"]


# --- atl prompt-config/uploads/constraints - same shape as the psm functions
# above, a real, separate service (atl_agent) reached at its own /atl prefix.


def get_atl_prompt_config(name: str) -> dict:
    return _request("GET", f"/atl/prompt-config/{name}").json()


def save_atl_prompt_config(name: str, config: dict) -> dict:
    return _request("PUT", f"/atl/prompt-config/{name}", json=config).json()


def get_atl_prompt_config_history(name: str) -> list[str]:
    return _request("GET", f"/atl/prompt-config/{name}/history").json()["versions"]


def diff_atl_prompt_config_versions(name: str, version_a: str, version_b: str) -> dict:
    return _request("GET", f"/atl/prompt-config/{name}/diff", params={"a": version_a, "b": version_b}).json()


def restore_atl_prompt_config_version(name: str, version: str) -> dict:
    return _request("POST", f"/atl/prompt-config/{name}/restore/{version}").json()


def revert_atl_prompt_config(name: str) -> dict:
    return _request("POST", f"/atl/prompt-config/{name}/revert").json()


def promote_atl_prompt_config_to_default(name: str) -> dict:
    return _request("POST", f"/atl/prompt-config/{name}/promote-to-default").json()


def check_atl_prompt_config_references(name: str) -> list[dict]:
    return _request("GET", f"/atl/prompt-config/{name}/check-references").json()["broken"]


def preview_atl_prompt_config(name: str) -> dict:
    return _request("POST", f"/atl/prompt-config/{name}/preview").json()


def add_atl_learned_constraints(name: str, constraints: list[str]) -> dict:
    return _request(
        "POST", f"/atl/prompt-config/{name}/learned-constraints", json={"constraints": constraints}
    ).json()


def remove_atl_learned_constraint(name: str, constraint: str) -> dict:
    return _request(
        "DELETE", f"/atl/prompt-config/{name}/learned-constraints", json={"constraint": constraint}
    ).json()


def list_atl_available_files() -> list[str]:
    return _request("GET", "/atl/available-files").json()["files"]


def promote_atl_constraints(constraints: list[str]) -> dict:
    return _request("POST", "/atl/promote-constraints", json={"constraints": constraints}).json()


def upload_atl_attachment_file(filename: str, content: bytes) -> str:
    """POSTs a real multipart file upload to integration_runner's own real
    /atl/attachment-uploads (a thin proxy over atl_agent's own real upload
    endpoint) - returns the real, safe stored path to use as a new "file"
    attachment's own `path`."""
    return _request(
        "POST", "/atl/attachment-uploads", files={"file": (filename, content)}
    ).json()["path"]


# --- acceleo prompt-config/uploads/constraints - same shape as atl's above,
# a real, separate service (acceleo_agent) reached at its own /acceleo prefix.


def get_acceleo_prompt_config(name: str) -> dict:
    return _request("GET", f"/acceleo/prompt-config/{name}").json()


def save_acceleo_prompt_config(name: str, config: dict) -> dict:
    return _request("PUT", f"/acceleo/prompt-config/{name}", json=config).json()


def get_acceleo_prompt_config_history(name: str) -> list[str]:
    return _request("GET", f"/acceleo/prompt-config/{name}/history").json()["versions"]


def diff_acceleo_prompt_config_versions(name: str, version_a: str, version_b: str) -> dict:
    return _request("GET", f"/acceleo/prompt-config/{name}/diff", params={"a": version_a, "b": version_b}).json()


def restore_acceleo_prompt_config_version(name: str, version: str) -> dict:
    return _request("POST", f"/acceleo/prompt-config/{name}/restore/{version}").json()


def revert_acceleo_prompt_config(name: str) -> dict:
    return _request("POST", f"/acceleo/prompt-config/{name}/revert").json()


def promote_acceleo_prompt_config_to_default(name: str) -> dict:
    return _request("POST", f"/acceleo/prompt-config/{name}/promote-to-default").json()


def check_acceleo_prompt_config_references(name: str) -> list[dict]:
    return _request("GET", f"/acceleo/prompt-config/{name}/check-references").json()["broken"]


def preview_acceleo_prompt_config(name: str) -> dict:
    return _request("POST", f"/acceleo/prompt-config/{name}/preview").json()


def add_acceleo_learned_constraints(name: str, constraints: list[str]) -> dict:
    return _request(
        "POST", f"/acceleo/prompt-config/{name}/learned-constraints", json={"constraints": constraints}
    ).json()


def remove_acceleo_learned_constraint(name: str, constraint: str) -> dict:
    return _request(
        "DELETE", f"/acceleo/prompt-config/{name}/learned-constraints", json={"constraint": constraint}
    ).json()


def list_acceleo_available_files() -> list[str]:
    return _request("GET", "/acceleo/available-files").json()["files"]


def promote_acceleo_constraints(constraints: list[str]) -> dict:
    return _request("POST", "/acceleo/promote-constraints", json={"constraints": constraints}).json()


def upload_acceleo_attachment_file(filename: str, content: bytes) -> str:
    """POSTs a real multipart file upload to integration_runner's own real
    /acceleo/attachment-uploads (a thin proxy over acceleo_agent's own real
    upload endpoint) - returns the real, safe stored path to use as a new
    "file" attachment's own `path`."""
    return _request(
        "POST", "/acceleo/attachment-uploads", files={"file": (filename, content)}
    ).json()["path"]
