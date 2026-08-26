"""HTTP client for validator_agent: headless .ecore validation. No fallback
on failure, matching this repo's other client modules."""
import os

import httpx

VALIDATOR_AGENT_URL = os.environ.get("VALIDATOR_AGENT_URL", "http://localhost:8020")
# Generous margin above validator_agent's own VALIDATOR_TIMEOUT_SECONDS default
# (60s for the subprocess itself), so this client doesn't give up first.
VALIDATE_TIMEOUT = float(os.environ.get("VALIDATE_TIMEOUT", "90.0"))


def validate_ecore(content: str, filename: str = "model.ecore", mode: str = "reflective") -> dict:
    """POST validator_agent's real /validate/ecore: returns
    {"valid": bool, "mode": str, "issues": [...], "duration_ms": int,
    "generated_source_path": str | None}."""
    response = httpx.post(
        f"{VALIDATOR_AGENT_URL}/validate/ecore",
        json={"filename": filename, "content": content, "mode": mode},
        timeout=VALIDATE_TIMEOUT,
    )
    response.raise_for_status()
    return response.json()
