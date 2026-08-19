"""serialization_agent: the 7th real pipeline stage (extract -> label ->
assemble). Own top-level service (own container, own REST API, see main.py),
reached over HTTP by integration_runner's serialization stage
(stages/serialization/agent.py, via clients/serialization_agent_client.py) -
the same shape pim_agent/psm_agent already use, since this service itself
makes a real outbound call to pim_agent (see serialization.py's own
docstring).
"""
from .serialization import serialization_agent

__all__ = ["serialization_agent"]
