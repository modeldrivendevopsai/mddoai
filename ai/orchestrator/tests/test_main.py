"""
FastAPI endpoint tests for the orchestrator service.

Unlike a typical endpoint test, these mock only the network boundary — httpx.post,
standing in for ai-layer's /chat — and let the real orchestrator.py state machine
(run_stage, rerun_stage, judge, stage_result, current_stage) execute for real. This
exercises the actual wiring between main.py's routes and orchestrator.py's logic,
which is exactly the seam this service's independence from ai-layer depends on.

Tests verify:
  1. POST /start resets the pipeline and runs the psm stage against a real POST to
     ai-layer's /chat (mocked at httpx.post).
  2. POST /start converts a downstream error into a 500.
  3. POST /rerun/{stage_id} reruns the current stage, folding in a constraint recorded
     via a prior rejection, when stage_id matches the current pending stage.
  4. POST /rerun/{stage_id} returns 400 without calling chat when stage_id doesn't match,
     and converts a downstream error into a 500.
  5. POST /judge dispatches whatever tool call(s) the (mocked) LLM response returns,
     executing real orchestrator logic, and returns a clarification directly when no
     tool is called; converts a downstream error into a 500.
  6. POST /review/{stage_id} returns the next stage's real output on approval, or
     {"status": "complete"} on approving the last stage, or {"status": "rerun", ...}
     on rejection (without calling chat) — all when stage_id matches the current
     pending stage; returns 400 (without calling chat) when it doesn't, or when
     approved=False is missing a correction.
"""
import json
from unittest.mock import MagicMock, patch

import pytest
from fastapi.testclient import TestClient

import main
import orchestrator

client = TestClient(main.app)


@pytest.fixture(autouse=True)
def reset_orchestrator_state():
    """Each test drives the real Orchestrator singleton through its endpoints, so
    isolate them from each other by swapping in a fresh instance per test."""
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    yield
    orchestrator._default = original


def fake_response(content=None, model="gemini/gemini-2.5-flash", tool_calls=None):
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = {"content": content, "model": model, "tool_calls": tool_calls}
    return resp


def tool_call_response(name, arguments, model="gemini/gemini-2.5-flash"):
    return fake_response(
        content=None,
        model=model,
        tool_calls=[{"function": {"name": name, "arguments": json.dumps(arguments)}}],
    )


def start_pipeline(platform_description="A GitLab CI platform", psm_output="PSM description"):
    with patch("orchestrator.httpx.post", return_value=fake_response(psm_output)):
        return client.post("/start", json={"platform_description": platform_description})


def test_start_endpoint_runs_psm_stage_via_real_pipeline():
    with patch("orchestrator.httpx.post", return_value=fake_response("PSM description")) as mock_post:
        response = client.post("/start", json={"platform_description": "A GitLab CI platform"})

    assert response.status_code == 200
    assert response.json() == {"stage": "psm", "output": "PSM description", "valid": True}
    mock_post.assert_called_once()
    sent_payload = mock_post.call_args.kwargs["json"]
    assert sent_payload["messages"][1]["content"].startswith("A GitLab CI platform")


def test_start_endpoint_returns_500_on_downstream_error():
    with patch("orchestrator.httpx.post", side_effect=RuntimeError("all providers exhausted")):
        response = client.post("/start", json={"platform_description": "A GitLab CI platform"})

    assert response.status_code == 500
    assert response.json()["detail"] == "all providers exhausted"


def test_rerun_endpoint_reruns_current_stage_with_correction_folded_in():
    start_pipeline(psm_output="PSM v1")
    client.post("/review/psm", json={"approved": False, "correction": "Include a lint stage"})

    with patch("orchestrator.httpx.post", return_value=fake_response("PSM v2 (lint stage added)")) as mock_post:
        response = client.post("/rerun/psm")

    assert response.status_code == 200
    assert response.json() == {"stage": "psm", "output": "PSM v2 (lint stage added)", "valid": True}
    sent_payload = mock_post.call_args.kwargs["json"]
    assert "Include a lint stage" in sent_payload["messages"][1]["content"]


def test_rerun_endpoint_rejects_mismatched_stage_id_without_calling_chat():
    start_pipeline()

    with patch("orchestrator.httpx.post") as mock_post:
        response = client.post("/rerun/atl")

    assert response.status_code == 400
    assert "atl" in response.json()["detail"]
    mock_post.assert_not_called()


def test_rerun_endpoint_returns_500_on_downstream_error():
    start_pipeline()

    with patch("orchestrator.httpx.post", side_effect=RuntimeError("all providers exhausted")):
        response = client.post("/rerun/psm")

    assert response.status_code == 500
    assert response.json()["detail"] == "all providers exhausted"


def test_judge_endpoint_dispatches_rerun_stage_and_returns_result():
    start_pipeline(psm_output="PSM output")

    responses = [tool_call_response("rerun_stage", {}), fake_response("PSM v2")]
    with patch("orchestrator.httpx.post", side_effect=responses) as mock_post:
        response = client.post("/judge", json={"message": "redo the psm stage"})

    assert response.status_code == 200
    body = response.json()
    assert body["tool_called"] == "rerun_stage"
    assert body["result"] == {"stage": "psm", "output": "PSM v2", "valid": True}
    assert mock_post.call_count == 2


def test_judge_endpoint_returns_clarification_when_no_tool_called():
    start_pipeline()

    with patch(
        "orchestrator.httpx.post",
        return_value=fake_response("Could you clarify which stage you mean?"),
    ):
        response = client.post("/judge", json={"message": "hello there"})

    assert response.status_code == 200
    assert response.json() == {
        "tool_called": None,
        "result": None,
        "message": "Could you clarify which stage you mean?",
    }


def test_judge_endpoint_returns_500_on_downstream_error():
    with patch("orchestrator.httpx.post", side_effect=RuntimeError("all providers exhausted")):
        response = client.post("/judge", json={"message": "redo the psm stage"})

    assert response.status_code == 500
    assert response.json()["detail"] == "all providers exhausted"


def test_review_endpoint_returns_next_stage_output_on_approval():
    start_pipeline(psm_output="PSM description")

    with patch("orchestrator.httpx.post", return_value=fake_response("ATL rules")) as mock_post:
        response = client.post("/review/psm", json={"approved": True})

    assert response.status_code == 200
    assert response.json() == {"stage": "atl", "output": "ATL rules", "valid": True}
    mock_post.assert_called_once()


def test_review_endpoint_returns_complete_status_on_last_stage_approval():
    start_pipeline(psm_output="PSM description")
    with patch("orchestrator.httpx.post", return_value=fake_response("ATL rules")):
        client.post("/review/psm", json={"approved": True})
    with patch("orchestrator.httpx.post", return_value=fake_response("Acceleo template")):
        client.post("/review/atl", json={"approved": True})
    with patch("orchestrator.httpx.post", return_value=fake_response("Final summary")):
        response = client.post("/review/acceleo", json={"approved": True})

    assert response.json() == {"stage": "generation", "output": "Final summary", "valid": True}

    final_response = client.post("/review/generation", json={"approved": True})

    assert final_response.status_code == 200
    assert final_response.json() == {"status": "complete"}


def test_review_endpoint_returns_rerun_status_with_correction():
    start_pipeline(psm_output="PSM description")

    with patch("orchestrator.httpx.post") as mock_post:
        response = client.post(
            "/review/psm", json={"approved": False, "correction": "Missing artifact retention policy"}
        )

    assert response.status_code == 200
    assert response.json() == {"status": "rerun", "stage": "psm"}
    mock_post.assert_not_called()


def test_review_endpoint_rejects_mismatched_stage_id_without_calling_chat():
    start_pipeline()

    with patch("orchestrator.httpx.post") as mock_post:
        response = client.post("/review/atl", json={"approved": True})

    assert response.status_code == 400
    assert "atl" in response.json()["detail"]
    mock_post.assert_not_called()


def test_review_endpoint_rejects_missing_correction_when_not_approved():
    start_pipeline()

    with patch("orchestrator.httpx.post") as mock_post:
        response = client.post("/review/psm", json={"approved": False})

    assert response.status_code == 400
    assert "correction" in response.json()["detail"].lower()
    mock_post.assert_not_called()
