"""HTTP client for serialization_agent: turns the docs stage's raw output
into a PIM-concept-labeled markdown artifact that holds all of it. No fallback
on failure, matching this repo's other client modules. No model is called: a
real call is one fast grounding call per documentation section against
pim_agent, and a long page has many sections."""
import httpx

from clients.required_env import required_env

SERIALIZATION_AGENT_URL = required_env("SERIALIZATION_AGENT_URL")
# Five minutes: an engineering guess, far above one grounding call per section
# of even a very long documentation page.
SERIALIZE_TIMEOUT = 300.0


def serialize(docs_output: str) -> str:
    """POST serialization_agent's real /serialize: returns the labeled
    markdown artifact built from docs_output."""
    response = httpx.post(
        f"{SERIALIZATION_AGENT_URL}/serialize",
        json={"docs_output": docs_output},
        timeout=SERIALIZE_TIMEOUT,
    )
    response.raise_for_status()
    return response.json()["markdown"]
