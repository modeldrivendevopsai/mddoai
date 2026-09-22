"""FastAPI endpoint tests. Mocks only the real boundary, main.run_atl_executor/
main.run_acceleo_executor (main.py's own bound references, imported from
execution_runner), matching validator_agent's own convention of mocking the
process/network boundary, not internal logic.
"""
from unittest.mock import patch

from fastapi.testclient import TestClient

import main
from execution_runner import ExecutionFailedError, ExecutionInfraError

client = TestClient(main.app)

_ATL_REQUEST = {
    "atl_source": "-- atl source",
    "pim_model_xmi": "<pim/>",
    "target_ecore": "<ecore/>",
    "output_model_name": "OUT",
}

_ACCELEO_REQUEST = {
    "mtl_source": "[module x]",
    "psm_model_xmi": "<psm/>",
    "target_ecore": "<ecore/>",
}


def test_health_endpoint():
    response = client.get("/health")

    assert response.status_code == 200
    assert response.json() == {"status": "ok"}


def test_execute_atl_returns_200_with_the_real_output():
    with patch("main.run_atl_executor", return_value={"output_xmi": "<real-output/>"}):
        response = client.post("/execute/atl", json=_ATL_REQUEST)

    assert response.status_code == 200
    assert response.json() == {"output_xmi": "<real-output/>"}


def test_execute_atl_returns_422_for_a_real_execution_failure():
    with patch("main.run_atl_executor", side_effect=ExecutionFailedError("not a real Pipeline instance")):
        response = client.post("/execute/atl", json=_ATL_REQUEST)

    assert response.status_code == 422
    assert response.json()["detail"] == "not a real Pipeline instance"


def test_execute_atl_returns_500_for_an_infra_failure():
    with patch("main.run_atl_executor", side_effect=ExecutionInfraError("subprocess timed out")):
        response = client.post("/execute/atl", json=_ATL_REQUEST)

    assert response.status_code == 500
    assert response.json()["detail"] == "subprocess timed out"


def test_execute_atl_rejects_content_over_the_configured_max(monkeypatch):
    monkeypatch.setattr(main, "MAX_CONTENT_BYTES", 10)

    response = client.post("/execute/atl", json=_ATL_REQUEST)

    assert response.status_code == 413


def test_execute_acceleo_returns_200_with_the_real_generated_files():
    with patch("main.run_acceleo_executor", return_value={"generated_files": {".gitlab-ci.yml": "stages: []\n"}}):
        response = client.post("/execute/acceleo", json=_ACCELEO_REQUEST)

    assert response.status_code == 200
    assert response.json() == {"generated_files": {".gitlab-ci.yml": "stages: []\n"}}


def test_execute_acceleo_returns_422_for_a_real_execution_failure():
    with patch("main.run_acceleo_executor", side_effect=ExecutionFailedError("target metamodel could not be loaded")):
        response = client.post("/execute/acceleo", json=_ACCELEO_REQUEST)

    assert response.status_code == 422
    assert response.json()["detail"] == "target metamodel could not be loaded"


def test_execute_acceleo_returns_500_for_an_infra_failure():
    with patch("main.run_acceleo_executor", side_effect=ExecutionInfraError("subprocess timed out")):
        response = client.post("/execute/acceleo", json=_ACCELEO_REQUEST)

    assert response.status_code == 500
    assert response.json()["detail"] == "subprocess timed out"
