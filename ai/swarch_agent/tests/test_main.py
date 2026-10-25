"""POST /pim: status codes and response shapes for each outcome of
conversion.to_pim, which is mocked here (its own behavior is covered in
test_conversion.py)."""
from unittest.mock import patch

import httpx
from fastapi.testclient import TestClient

import main
from conversion import InvalidModelError, TransformationFailedError, TransformationUnavailableError

client = TestClient(main.app)


def test_health():
    assert client.get("/health").json() == {"status": "ok"}


def test_a_model_is_turned_into_a_pim():
    with patch("main.to_pim", return_value="<pim/>") as to_pim:
        response = client.post("/pim", json={"content": "<swarch/>"})

    assert response.status_code == 200
    assert response.json() == {"pim": "<pim/>"}
    to_pim.assert_called_once_with("<swarch/>")


def test_an_invalid_model_is_422_with_its_issues():
    issues = [{"severity": "ERROR", "message": "missing name"}]
    with patch("main.to_pim", side_effect=InvalidModelError(issues)):
        response = client.post("/pim", json={"content": "<swarch/>"})

    assert response.status_code == 422
    assert response.json()["detail"]["issues"] == issues


def test_a_failed_transformation_is_422_with_a_message_that_names_no_server_details():
    with patch("main.to_pim", side_effect=TransformationFailedError()):
        response = client.post("/pim", json={"content": "<swarch/>"})

    assert response.status_code == 422
    assert response.json()["detail"]["message"].startswith("The SWArch-to-PIM transformation failed")
    assert response.json()["detail"]["issues"] == []


def test_a_missing_transformation_is_500():
    with patch("main.to_pim", side_effect=TransformationUnavailableError()):
        response = client.post("/pim", json={"content": "<swarch/>"})

    assert response.status_code == 500


def test_an_unreachable_sibling_service_is_502():
    with patch("main.to_pim", side_effect=httpx.ConnectError("refused")):
        response = client.post("/pim", json={"content": "<swarch/>"})

    assert response.status_code == 502


def test_a_model_over_the_size_limit_is_413_and_never_converted(monkeypatch):
    monkeypatch.setattr(main, "MAX_MODEL_BYTES", 10)
    with patch("main.to_pim") as to_pim:
        response = client.post("/pim", json={"content": "x" * 11})

    assert response.status_code == 413
    to_pim.assert_not_called()


def test_an_empty_model_is_refused():
    assert client.post("/pim", json={"content": ""}).status_code == 422
