"""Turning a Software Architecture model into a PIM: check the model with the
real EMF validator, then run the current SWArch-to-PIM transformation on it
through execution-agent.

The transformation is read from its file on every call, not once at startup,
so replacing the file takes effect on the next request.
"""
import logging

from clients import execution_agent_client, validator_agent_client
from clients.agent_service_errors import AgentServiceError
from settings import SWARCH2PIM_ATL_PATH

logger = logging.getLogger(__name__)

# What the validator is told the model is called. Its own messages can name
# that file, so it is a fixed, meaningless name rather than anything a caller
# sent.
_VALIDATOR_FILENAME = "model.swarch"

# execution-agent's status for a run that started and failed: the
# transformation could not be compiled, or failed on this model. Its other
# business statuses (a route it does not know, a request it refuses) mean the
# service is not the one this code expects, not that the model is at fault.
_EXECUTION_FAILED_STATUS = 422


class InvalidModelError(Exception):
    """The model failed the EMF check. `issues` are severity and message only:
    an issue's own source is the validator's server-side temporary path."""

    def __init__(self, issues: list[dict]):
        super().__init__("The model is not a valid Software Architecture model.")
        self.issues = issues


class TransformationFailedError(Exception):
    """The transformation ran but failed: it would not compile, or failed on
    this model. The reason is logged here, not passed on, because the engine's
    own message can name the service's temporary files."""


class TransformationUnavailableError(Exception):
    """The transformation cannot be run: its file is missing or empty, or
    execution-agent answered in a way this service does not expect."""


def _read_transformation() -> str:
    try:
        source = SWARCH2PIM_ATL_PATH.read_text(encoding="utf-8")
    except OSError as e:
        logger.error("could not read the SWArch-to-PIM transformation at %s: %s", SWARCH2PIM_ATL_PATH, e)
        raise TransformationUnavailableError() from e
    if not source.strip():
        logger.error("the SWArch-to-PIM transformation at %s is empty", SWARCH2PIM_ATL_PATH)
        raise TransformationUnavailableError()
    return source


def to_pim(content: str) -> str:
    """The PIM `content` (a .swarch model's XMI text) turns into, as XMI text.
    Raises InvalidModelError, TransformationFailedError or
    TransformationUnavailableError; a sibling service being unreachable
    propagates as an httpx error."""
    validation = validator_agent_client.validate_swarch(content, _VALIDATOR_FILENAME)
    if not validation["valid"]:
        raise InvalidModelError(
            [{"severity": issue["severity"], "message": issue["message"]} for issue in validation["issues"]]
        )
    atl_source = _read_transformation()
    try:
        return execution_agent_client.execute_atl_from_swarch(atl_source, content)
    except AgentServiceError as e:
        if e.status_code == _EXECUTION_FAILED_STATUS:
            logger.warning("the SWArch-to-PIM transformation failed: %s", e.detail)
            raise TransformationFailedError() from e
        logger.error("execution-agent answered %s: %s", e.status_code, e.detail)
        raise TransformationUnavailableError() from e
