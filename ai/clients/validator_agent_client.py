"""HTTP client for validator-agent: real .ecore/.atl/.mtl structural/syntax
validation, backed by the same Java CLIs main/'s own build produces (see
validator_agent/README.md). Every call either returns validator-agent's own
{"valid": bool, "issues": [...], "duration_ms": int, ...} body directly —
win or lose, that's real data, not an error to branch on — or raises: a
genuine infra failure (validator-agent unreachable, its own subprocess
crashed) surfaces as a raw httpx error, matching ai_layer_client.py's own
pattern exactly. Deliberately not integration_runner_client.py's
IntegrationRunnerError shape: validator-agent never reports a structured
400/404/409 business error to branch on, only 200 (valid or not) or 500
(infra), so there's nothing that wrapper would add here.
"""
import os

import httpx

VALIDATOR_AGENT_URL = os.environ.get("VALIDATOR_AGENT_URL", "http://localhost:8020")
# Comfortably above validator-agent's own VALIDATOR_TIMEOUT_SECONDS default
# (60s, see validator_agent/.env.example) so a real subprocess run never
# gets cut off from this side first.
VALIDATE_TIMEOUT = float(os.environ.get("VALIDATE_TIMEOUT", "90.0"))


def validate_ecore(
    content: str,
    filename: str = "model.ecore",
    mode: str = "reflective",
    run_id: str | None = None,
    stage: str | None = None,
    attempt: str | None = None,
) -> dict:
    """POSTs to validator-agent's real /validate/ecore, returns its parsed
    JSON response directly: {"valid": bool, "mode": str, "issues":
    [{"severity", "message", "source"}], "duration_ms": int,
    "generated_source_path": str | None}. filename defaults to a generic
    name for callers (e.g. psm_agent's own generate()) that validate
    in-memory content with no real source file of its own. stage and
    attempt name the calling stage (e.g. "psm") and its own reserved attempt
    directory (e.g. "attempt_2"), joined in that order onto run_id, so a
    codegen-mode call's real compiled output nests inside that exact
    attempt directory rather than only scoped by run_id. Only ever matters
    for a real codegen-mode call: a reflective-only caller never passes
    either, since reflective mode produces nothing worth scoping in the
    first place."""
    payload = {"filename": filename, "content": content, "mode": mode}
    if run_id is not None:
        payload["run_id"] = run_id
    if stage is not None:
        payload["stage"] = stage
    if attempt is not None:
        payload["attempt"] = attempt
    response = httpx.post(
        f"{VALIDATOR_AGENT_URL}/validate/ecore",
        json=payload,
        timeout=VALIDATE_TIMEOUT,
    )
    response.raise_for_status()
    return response.json()


def validate_atl(
    content: str, filename: str, run_id: str | None = None, stage: str | None = None, attempt: str | None = None
) -> dict:
    """POSTs to validator-agent's real /validate/atl, returns its parsed
    JSON response directly: {"valid": bool, "issues": [...], "duration_ms":
    int, "generated_source_path": str | None} — the compiled .asm bytecode's
    real path on disk, when compiling actually produced one (see
    validate_ecore's own generated_source_path for why this is kept). stage
    and attempt name the calling stage and its own reserved attempt
    directory, joined in that order onto run_id, so that real compiled
    output nests inside the exact attempt directory rather than only scoped
    by run_id."""
    payload = {"filename": filename, "content": content}
    if run_id is not None:
        payload["run_id"] = run_id
    if stage is not None:
        payload["stage"] = stage
    if attempt is not None:
        payload["attempt"] = attempt
    response = httpx.post(
        f"{VALIDATOR_AGENT_URL}/validate/atl",
        json=payload,
        timeout=VALIDATE_TIMEOUT,
    )
    response.raise_for_status()
    return response.json()


def validate_acceleo(
    content: str, filename: str, run_id: str | None = None, stage: str | None = None, attempt: str | None = None
) -> dict:
    """POSTs to validator-agent's real /validate/acceleo, returns its
    parsed JSON response directly: {"valid": bool, "issues": [...],
    "duration_ms": int, "generated_source_path": str | None} — the compiled
    .emtl module's real path on disk, when compiling actually produced one.
    stage and attempt name the calling stage and its own reserved attempt
    directory, joined in that order onto run_id, so that real compiled
    output nests inside the exact attempt directory rather than only scoped
    by run_id."""
    payload = {"filename": filename, "content": content}
    if run_id is not None:
        payload["run_id"] = run_id
    if stage is not None:
        payload["stage"] = stage
    if attempt is not None:
        payload["attempt"] = attempt
    response = httpx.post(
        f"{VALIDATOR_AGENT_URL}/validate/acceleo",
        json=payload,
        timeout=VALIDATE_TIMEOUT,
    )
    response.raise_for_status()
    return response.json()
