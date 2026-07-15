"""
FastAPI endpoint tests. No real API calls — router.chat is mocked.

Tests verify:
  1. /health reports ok.
  2. /providers lists the configured providers.
  3. /chat returns content+model on success.
  4. /chat rejects an unrecognized model with 400 before calling chat().
  5. /chat accepts "auto" and passes it straight through.
  6. /chat converts a downstream exception into a 500.
  7. /orchestrate returns content+model on success (orchestrator.orchestrate is mocked).
  8. /orchestrate/start resets the pipeline and runs the psm stage (orchestrator.run_stage
     and orchestrator.reset_pipeline are mocked).
  9. /review/{stage_id} calls orchestrator.stage_result and returns its status shape
     (advanced/complete/rerun) when stage_id matches the current pending stage.
  10. /review/{stage_id} returns 400 without calling stage_result when stage_id doesn't
      match the current pending stage, or when approved=False is missing a correction.
  11. /orchestrate/rerun/{stage_id} calls orchestrator.rerun_stage and returns its
      {"stage", "output", "valid"} shape when stage_id matches the current pending stage,
      returns 400 without calling rerun_stage when it doesn't, and converts a downstream
      error into a 500.
"""
from unittest.mock import MagicMock, patch

from fastapi.testclient import TestClient

from conftest import reload_router_modules

# conftest.py sets fake provider keys at import time. Force a fresh import here too,
# in case another test module already reloaded router.config under different env vars.
reload_router_modules()

import main  # noqa: E402

client = TestClient(main.app)


def ok_response(model):
    r = MagicMock()
    r.model = model
    r.choices = [MagicMock(message=MagicMock(content="hello"))]
    return r


def test_health():
    response = client.get("/health")
    assert response.status_code == 200
    assert response.json() == {"status": "ok"}


def test_providers_lists_available_providers():
    response = client.get("/providers")
    assert response.status_code == 200
    names = {p["name"] for p in response.json()}
    assert names == {m["name"] for m in main.AVAILABLE}


def test_chat_returns_content_and_model_on_success():
    with patch.object(main, "chat", return_value=ok_response("gemini/gemini-2.5-flash")) as mock_chat:
        response = client.post("/chat", json={"messages": [{"role": "user", "content": "hi"}]})

    assert response.status_code == 200
    assert response.json() == {"content": "hello", "model": "gemini/gemini-2.5-flash"}
    mock_chat.assert_called_once_with([{"role": "user", "content": "hi"}], model=None)


def test_chat_rejects_unknown_model_without_calling_chat():
    with patch.object(main, "chat") as mock_chat:
        response = client.post(
            "/chat",
            json={"messages": [{"role": "user", "content": "hi"}], "model": "not-a-real-provider"},
        )

    assert response.status_code == 400
    assert "not-a-real-provider" in response.json()["detail"]
    mock_chat.assert_not_called()


def test_chat_accepts_auto():
    with patch.object(main, "chat", return_value=ok_response("gemini/gemini-2.5-flash")) as mock_chat:
        response = client.post(
            "/chat",
            json={"messages": [{"role": "user", "content": "hi"}], "model": "auto"},
        )

    assert response.status_code == 200
    mock_chat.assert_called_once_with([{"role": "user", "content": "hi"}], model="auto")


def test_chat_returns_500_on_downstream_error():
    with patch.object(main, "chat", side_effect=RuntimeError("all providers exhausted")):
        response = client.post("/chat", json={"messages": [{"role": "user", "content": "hi"}]})

    assert response.status_code == 500
    assert response.json()["detail"] == "all providers exhausted"


def test_orchestrate_endpoint_returns_content_and_model():
    with patch.object(main, "orchestrate", return_value="hello") as mock_orchestrate:
        response = client.post("/orchestrate", json={"messages": [{"role": "user", "content": "hi"}]})

    assert response.status_code == 200
    body = response.json()
    assert body["content"] == "hello"
    assert "model" in body
    mock_orchestrate.assert_called_once_with([{"role": "user", "content": "hi"}])


def test_orchestrate_start_resets_pipeline_and_runs_psm_stage():
    with patch.object(main, "reset_pipeline") as mock_reset, patch.object(
        main, "run_stage", return_value={"stage": "psm", "output": "PSM description", "valid": True}
    ) as mock_run_stage:
        response = client.post("/orchestrate/start", json={"platform_description": "A GitLab CI platform"})

    assert response.status_code == 200
    assert response.json() == {"stage": "psm", "output": "PSM description", "valid": True}
    mock_reset.assert_called_once_with()
    mock_run_stage.assert_called_once_with({"platform_description": "A GitLab CI platform"})


def test_orchestrate_start_returns_500_on_downstream_error():
    with patch.object(main, "reset_pipeline"), patch.object(
        main, "run_stage", side_effect=RuntimeError("all providers exhausted")
    ):
        response = client.post("/orchestrate/start", json={"platform_description": "A GitLab CI platform"})

    assert response.status_code == 500
    assert response.json()["detail"] == "all providers exhausted"


def test_orchestrate_rerun_endpoint_returns_stage_output():
    with patch.object(main, "current_stage", return_value="psm"), patch.object(
        main, "rerun_stage", return_value={"stage": "psm", "output": "PSM v2", "valid": True}
    ) as mock_rerun_stage:
        response = client.post("/orchestrate/rerun/psm")

    assert response.status_code == 200
    assert response.json() == {"stage": "psm", "output": "PSM v2", "valid": True}
    mock_rerun_stage.assert_called_once_with()


def test_orchestrate_rerun_endpoint_rejects_mismatched_stage_id_without_calling_rerun_stage():
    with patch.object(main, "current_stage", return_value="atl"), patch.object(main, "rerun_stage") as mock_rerun_stage:
        response = client.post("/orchestrate/rerun/psm")

    assert response.status_code == 400
    assert "psm" in response.json()["detail"]
    mock_rerun_stage.assert_not_called()


def test_orchestrate_rerun_endpoint_returns_500_on_downstream_error():
    with patch.object(main, "current_stage", return_value="psm"), patch.object(
        main, "rerun_stage", side_effect=RuntimeError("all providers exhausted")
    ):
        response = client.post("/orchestrate/rerun/psm")

    assert response.status_code == 500
    assert response.json()["detail"] == "all providers exhausted"


def test_review_endpoint_returns_advanced_status():
    with patch.object(main, "current_stage", return_value="psm"), patch.object(
        main, "stage_result", return_value={"status": "advanced", "next_stage": "atl"}
    ) as mock_stage_result:
        response = client.post("/review/psm", json={"approved": True})

    assert response.status_code == 200
    assert response.json() == {"status": "advanced", "next_stage": "atl"}
    mock_stage_result.assert_called_once_with("psm", True, None)


def test_review_endpoint_returns_complete_status():
    with patch.object(main, "current_stage", return_value="generation"), patch.object(
        main, "stage_result", return_value={"status": "complete"}
    ):
        response = client.post("/review/generation", json={"approved": True})

    assert response.status_code == 200
    assert response.json() == {"status": "complete"}


def test_review_endpoint_returns_rerun_status_with_correction():
    with patch.object(main, "current_stage", return_value="psm"), patch.object(
        main, "stage_result", return_value={"status": "rerun", "stage": "psm"}
    ) as mock_stage_result:
        response = client.post(
            "/review/psm", json={"approved": False, "correction": "Missing artifact retention policy"}
        )

    assert response.status_code == 200
    assert response.json() == {"status": "rerun", "stage": "psm"}
    mock_stage_result.assert_called_once_with("psm", False, "Missing artifact retention policy")


def test_review_endpoint_rejects_mismatched_stage_id_without_calling_stage_result():
    with patch.object(main, "current_stage", return_value="atl"), patch.object(main, "stage_result") as mock_stage_result:
        response = client.post("/review/psm", json={"approved": True})

    assert response.status_code == 400
    assert "psm" in response.json()["detail"]
    mock_stage_result.assert_not_called()


def test_review_endpoint_rejects_missing_correction_when_not_approved():
    with patch.object(main, "current_stage", return_value="psm"), patch.object(main, "stage_result") as mock_stage_result:
        response = client.post("/review/psm", json={"approved": False})

    assert response.status_code == 400
    assert "correction" in response.json()["detail"].lower()
    mock_stage_result.assert_not_called()
