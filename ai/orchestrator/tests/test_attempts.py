"""main.py's own run/attempt introspection endpoints: each is a one-line
proxy to clients/integration_runner_client.py, mocked directly at that
boundary (this behavior has no dependency on any real pipeline/run state,
unlike test_main.py's own real cross-service tests) - a real 404 from
integration_runner surfaces via the shared IntegrationRunnerError handler
already covered by test_main.py's own error-mapping tests, not re-tested
here.
"""
import httpx
from unittest.mock import patch

from fastapi.testclient import TestClient

import main
from clients import integration_runner_client

client = TestClient(main.app)


def test_manifest_endpoint_proxies_the_real_client():
    with patch.object(integration_runner_client, "get_run_manifest", return_value=[{"stage": "psm"}]) as mock_get:
        response = client.get("/runs/run-1/manifest")

    mock_get.assert_called_once_with("run-1")
    assert response.status_code == 200
    assert response.json() == {"attempts": [{"stage": "psm"}]}


def test_attempt_endpoint_proxies_the_real_client():
    attempt = {"artifact": {"filename": "psm.ecore", "content": "<ecore/>"}, "result": {"valid": True}}
    with patch.object(integration_runner_client, "get_attempt", return_value=attempt) as mock_get:
        response = client.get("/runs/run-1/psm/attempt_1")

    mock_get.assert_called_once_with("run-1", "psm", "attempt_1")
    assert response.status_code == 200
    assert response.json() == attempt


def test_evolution_endpoint_proxies_the_real_client():
    summary = {"run_id": "run-1", "forked_from": None, "stages": []}
    with patch.object(integration_runner_client, "get_run_evolution", return_value=summary) as mock_get:
        response = client.get("/runs/run-1/evolution")

    mock_get.assert_called_once_with("run-1")
    assert response.status_code == 200
    assert response.json() == summary


def test_evolution_endpoint_is_a_502_when_the_runner_or_journal_cannot_be_reached():
    with patch.object(integration_runner_client, "get_run_evolution", side_effect=httpx.ConnectError("down")):
        response = client.get("/runs/run-1/evolution")

    assert response.status_code == 502
    assert "history" in response.json()["detail"]


def test_manifest_endpoint_is_a_502_when_the_runner_cannot_be_reached():
    with patch.object(integration_runner_client, "get_run_manifest", side_effect=httpx.ConnectError("down")):
        response = client.get("/runs/run-1/manifest")

    assert response.status_code == 502
