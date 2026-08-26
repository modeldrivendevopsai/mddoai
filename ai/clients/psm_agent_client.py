"""HTTP client for psm_agent: the real psm stage's whole capability (routes
between generation and existing-platform comparison internally, see
psm_agent/psm_flow.py). No fallback on failure, matching this repo's other
client modules. Generous timeout: a real call can run the Generation Agent's
multi-round regenerate loop, each round doing one real LLM call plus one real
validator-agent call."""
import os

import httpx

PSM_AGENT_URL = os.environ.get("PSM_AGENT_URL", "http://localhost:8040")
PSM_TIMEOUT = float(os.environ.get("PSM_TIMEOUT", "960.0"))


def run_psm(
    platform_description: str,
    pim_artifact: str,
    platform_docs: str,
    constraints: list[str] | None = None,
    model: str | None = None,
) -> dict:
    """POST psm_agent's real /psm: returns either a generation-mode result
    ({"mode": "generation", "artifact", "prompt", "validation", "rounds"}) or
    a knowledge-mode result ({"mode": "knowledge", "artifact", "gaps", "prompt"})."""
    response = httpx.post(
        f"{PSM_AGENT_URL}/psm",
        json={
            "platform_description": platform_description,
            "pim_artifact": pim_artifact,
            "platform_docs": platform_docs,
            "constraints": constraints,
            "model": model,
        },
        timeout=PSM_TIMEOUT,
    )
    response.raise_for_status()
    return response.json()
