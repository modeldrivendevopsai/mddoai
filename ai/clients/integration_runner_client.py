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


def _request(method: str, path: str, **kwargs) -> httpx.Response:
    response = httpx.request(method, f"{INTEGRATION_RUNNER_URL}{path}", timeout=10.0, **kwargs)
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
    whole crawl instead goes through rerun_stage(), not this."""
    return _request("POST", "/docs/extend", json={"url": url, "force_refresh": force_refresh}).json()


def set_model(model: str | None) -> dict:
    return _request("POST", "/model", json={"model": model}).json()
