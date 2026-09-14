"""integration_runner.main's own app-level concerns: just the
AgentServiceError exception handler today (registered once in main.py, not
per-route, see main.py's own docstring on it). Every route's own real
behavior has its own test file under tests/routes/ - this file only proves
the handler itself actually wires up, which calling a route function
directly (as tests/routes/test_psm.py etc. do) can't exercise, since a
FastAPI exception handler only ever runs through the real ASGI dispatch
path, not a bare Python function call.

Uses FastAPI's own TestClient against the real app, matching this repo's
established "exercise the real in-process app, mock only the true external
network boundary" convention used across this repo's other FastAPI test
suites - clients.psm_agent_client.save_prompt_config is that boundary here,
mocked directly.
"""
from unittest.mock import patch

from fastapi.testclient import TestClient

from clients import psm_agent_client
from clients.agent_service_errors import AgentServiceError
from integration_runner.main import app

client = TestClient(app)


def test_agent_service_error_is_forwarded_as_its_own_real_status_and_detail():
    with patch.object(
        psm_agent_client, "save_prompt_config",
        side_effect=AgentServiceError(400, "attachment 'x' references unknown context key 'y'"),
    ):
        response = client.put("/psm/prompt-config/generation", json={"attachments": []})

    assert response.status_code == 400
    assert response.json() == {"detail": "attachment 'x' references unknown context key 'y'"}


def test_agent_service_error_preserves_a_404_too():
    with patch.object(psm_agent_client, "save_prompt_config", side_effect=AgentServiceError(404, "no config found")):
        response = client.put("/psm/prompt-config/generation", json={"attachments": []})

    assert response.status_code == 404
    assert response.json() == {"detail": "no config found"}
