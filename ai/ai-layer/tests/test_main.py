"""
FastAPI endpoint tests. No real API calls — router.chat is mocked.

Tests verify:
  1. /health reports ok.
  2. /providers lists the configured providers.
  3. /chat returns content+model on success.
  4. /chat rejects an unrecognized model with 400 before calling chat().
  5. /chat accepts "auto" and passes it straight through.
  6. /chat converts a downstream exception into a 500.
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
