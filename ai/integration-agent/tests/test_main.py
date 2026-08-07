"""FastAPI endpoint tests. Mocks only the real boundary — main.run_ecore_validator
(main.py's own bound reference, imported from validator_runner) — matching
orchestrator's convention of mocking the process/network boundary, not internal
logic.
"""
from unittest.mock import patch

from fastapi.testclient import TestClient

import main
from validator_runner import ValidatorInfraError

client = TestClient(main.app)


def fake_result(valid=True, mode="reflective", issues=None, duration_ms=42):
    return {"valid": valid, "mode": mode, "issues": issues or [], "duration_ms": duration_ms}


def test_health_endpoint():
    response = client.get("/health")

    assert response.status_code == 200
    assert response.json() == {"status": "ok"}


def test_validate_ecore_returns_200_with_valid_true():
    with patch("main.run_ecore_validator", return_value=fake_result(valid=True)):
        response = client.post("/validate/ecore", json={
            "filename": "model.ecore", "content": "<ecore/>", "mode": "reflective",
        })

    assert response.status_code == 200
    assert response.json()["valid"] is True


def test_validate_ecore_returns_200_with_valid_false_and_issues():
    issues = [{"severity": "ERROR", "message": "broken thing", "source": "model.ecore"}]
    with patch("main.run_ecore_validator", return_value=fake_result(valid=False, issues=issues)):
        response = client.post("/validate/ecore", json={
            "filename": "model.ecore", "content": "<ecore/>", "mode": "reflective",
        })

    assert response.status_code == 200
    body = response.json()
    assert body["valid"] is False
    assert body["issues"] == issues


def test_validate_ecore_returns_500_on_infra_error():
    with patch("main.run_ecore_validator", side_effect=ValidatorInfraError("java not found")):
        response = client.post("/validate/ecore", json={
            "filename": "model.ecore", "content": "<ecore/>", "mode": "reflective",
        })

    assert response.status_code == 500
    assert "java not found" in response.json()["detail"]


def test_validate_ecore_rejects_oversized_content():
    oversized = "x" * (main.MAX_CONTENT_BYTES + 1)
    response = client.post("/validate/ecore", json={
        "filename": "model.ecore", "content": oversized, "mode": "reflective",
    })

    assert response.status_code == 413


def test_validate_ecore_rejects_invalid_mode():
    response = client.post("/validate/ecore", json={
        "filename": "model.ecore", "content": "<ecore/>", "mode": "bogus",
    })

    assert response.status_code == 422


def test_validate_ecore_rejects_missing_content():
    response = client.post("/validate/ecore", json={"filename": "model.ecore", "mode": "reflective"})

    assert response.status_code == 422
