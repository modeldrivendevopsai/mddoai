"""HTTP client for swarch-agent: turning a Software Architecture model into a
PIM. A model that fails the EMF check, or a transformation that fails on it,
is a describable business error and raises AgentServiceError (status 422,
with swarch-agent's own detail: a message and, for an invalid model, its
issues). swarch-agent or a service behind it being unavailable still
surfaces as a raw httpx error.
"""
import os

import httpx

from clients.agent_service_errors import raise_for_business_error

SWARCH_AGENT_URL = os.environ.get("SWARCH_AGENT_URL", "http://localhost:8110")
# Comfortably above swarch-agent's own work: one model check plus one
# execution-agent run, each with its own timeout of about a minute.
PIM_TIMEOUT = float(os.environ.get("SWARCH_PIM_TIMEOUT", "180.0"))


def to_pim(content: str) -> str:
    """POSTs `content` (a .swarch model's XMI text) to swarch-agent's /pim and
    returns the PIM it turns into, as XMI text."""
    response = httpx.post(f"{SWARCH_AGENT_URL}/pim", json={"content": content}, timeout=PIM_TIMEOUT)
    raise_for_business_error(response)
    return response.json()["pim"]
