"""Shared business-error translation for the sibling agent-service clients
(psm_agent_client.py/atl_agent_client.py/acceleo_agent_client.py's own
_config_request) - the same real pattern integration_runner_client.py's own
IntegrationRunnerError already establishes for a downstream service that
reports genuine business errors (a bad prompt-config edit, an unknown saved
version) as 4xx with a crisp `detail` message, not just infrastructure
failures. Without this, a plain pass-through route calling one of these
clients lets a raw httpx.HTTPStatusError propagate uncaught, which FastAPI
turns into a generic, undiagnosable 500 for whoever called that route,
losing the real status code and message the agent service actually
reported. A genuine connection failure or 5xx still propagates raw, there's
no fallback for those, same as every other client in this repo.
"""
import httpx

_BUSINESS_ERROR_CODES = (400, 404, 409)


class AgentServiceError(Exception):
    """One of a sibling agent service's own reported business errors, not
    an infrastructure failure. Callers that want the real HTTP status back
    (integration_runner/main.py's own exception handler) read
    status_code/detail directly rather than parsing str(e)."""

    def __init__(self, status_code: int, detail: str):
        self.status_code = status_code
        self.detail = detail
        super().__init__(detail)


def raise_for_business_error(response: httpx.Response) -> None:
    """Raises AgentServiceError for one of _BUSINESS_ERROR_CODES, preserving
    the real detail message the agent service reported; otherwise defers to
    response.raise_for_status() for a genuine infrastructure failure."""
    if response.status_code in _BUSINESS_ERROR_CODES:
        try:
            detail = response.json().get("detail", response.text)
        except ValueError:
            detail = response.text
        raise AgentServiceError(response.status_code, detail)
    response.raise_for_status()
