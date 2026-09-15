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

# 422 added alongside the original three for execution_agent_client.py's own
# use: a well-formed request whose real transformation/generation genuinely
# failed (bad ATL, a model that isn't a real instance of the given
# metamodel) is a textbook 422 Unprocessable Entity, not a 400 (the request
# itself was fine) or a 500 (nothing actually broke). Shared with every
# other caller of this module too, so a request-validation 422 from one of
# THEIR routes (FastAPI's own automatic response to a malformed request
# body) now reads as a business error rather than an uncaught httpx error -
# correct either way (a schema mismatch is closer to a business error than
# an infra failure), but a real behavior change worth knowing about if a
# caller ever depended on seeing that raw httpx error instead.
_BUSINESS_ERROR_CODES = (400, 404, 409, 422)


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
