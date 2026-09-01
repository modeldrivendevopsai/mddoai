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


def validate_ecore(content: str, filename: str = "model.ecore", mode: str = "reflective") -> dict:
    """POSTs to validator-agent's real /validate/ecore, returns its parsed
    JSON response directly: {"valid": bool, "mode": str, "issues":
    [{"severity", "message", "source"}], "duration_ms": int,
    "generated_source_path": str | None}. filename defaults to a generic
    name for callers (e.g. psm_agent's own generate()) that validate
    in-memory content with no real source file of its own."""
    response = httpx.post(
        f"{VALIDATOR_AGENT_URL}/validate/ecore",
        json={"filename": filename, "content": content, "mode": mode},
        timeout=VALIDATE_TIMEOUT,
    )
    response.raise_for_status()
    return response.json()


def validate_atl(content: str, filename: str) -> dict:
    """POSTs to validator-agent's real /validate/atl, returns its parsed
    JSON response directly: {"valid": bool, "issues": [...], "duration_ms": int}."""
    response = httpx.post(
        f"{VALIDATOR_AGENT_URL}/validate/atl",
        json={"filename": filename, "content": content},
        timeout=VALIDATE_TIMEOUT,
    )
    response.raise_for_status()
    return response.json()


def validate_acceleo(content: str, filename: str) -> dict:
    """POSTs to validator-agent's real /validate/acceleo, returns its
    parsed JSON response directly: {"valid": bool, "issues": [...], "duration_ms": int}."""
    response = httpx.post(
        f"{VALIDATOR_AGENT_URL}/validate/acceleo",
        json={"filename": filename, "content": content},
        timeout=VALIDATE_TIMEOUT,
    )
    response.raise_for_status()
    return response.json()
