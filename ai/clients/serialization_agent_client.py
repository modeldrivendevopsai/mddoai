"""HTTP client for serialization_agent: turns the docs stage's raw output
into a PIM-concept-labeled markdown artifact. No fallback on failure,
matching this repo's other client modules. Generous timeout: a real call
does one LLM extraction call (ai_layer_client.chat's own default is now 15
minutes) plus one fast grounding call per extracted fragment against
pim_agent."""
import os

import httpx

SERIALIZATION_AGENT_URL = os.environ.get("SERIALIZATION_AGENT_URL", "http://localhost:8060")
# 16 minutes: real margin above the inner extraction call's own 15-minute
# budget (ai_layer_client.LLM_CHAT_TIMEOUT) - observed for real: a 180s value
# here died silently, serialization-agent's own logs showed the request still
# in flight, not even failed yet, when this client had already given up.
SERIALIZE_TIMEOUT = float(os.environ.get("SERIALIZE_TIMEOUT", "960.0"))


def serialize(docs_output: str, model: str | None = None) -> str:
    """POST serialization_agent's real /serialize: returns the labeled
    markdown artifact built from docs_output."""
    response = httpx.post(
        f"{SERIALIZATION_AGENT_URL}/serialize",
        json={"docs_output": docs_output, "model": model},
        timeout=SERIALIZE_TIMEOUT,
    )
    response.raise_for_status()
    return response.json()["markdown"]
