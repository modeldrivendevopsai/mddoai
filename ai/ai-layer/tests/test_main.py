"""
FastAPI endpoint tests. No real API calls — router.chat is mocked.

Tests verify:
  1. /health reports ok.
  2. /providers lists the configured providers.
  3. /chat returns content+model on success.
  4. /chat rejects an unrecognized model with 400 before calling chat().
  5. /chat accepts "auto" and passes it straight through.
  6. /chat converts a downstream exception into a 500.
  7. /chat forwards tools/tool_choice to chat() only when provided, and serializes any
     tool_calls on the response message into plain dicts (the shape orchestrator's HTTP
     client — the sole consumer of this contract — expects back).

Orchestrator's endpoints (/start, /rerun/{stage_id}, /judge, /review/{stage_id}) now live
in the standalone orchestrator service — see orchestrator/tests/test_main.py.
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
    # tool_calls=None explicitly — MagicMock() would otherwise auto-vivify a truthy
    # mock attribute, which chat_endpoint's `if message.tool_calls:` would wrongly
    # treat as "the model called a tool".
    r.choices = [MagicMock(message=MagicMock(content="hello", tool_calls=None))]
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
    assert response.json() == {"content": "hello", "model": "gemini/gemini-2.5-flash", "tool_calls": None}
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


def test_chat_passes_tools_and_tool_choice_through_to_router_chat():
    tools = [{"type": "function", "function": {"name": "foo", "parameters": {}}}]
    with patch.object(main, "chat", return_value=ok_response("gemini/gemini-2.5-flash")) as mock_chat:
        response = client.post(
            "/chat",
            json={
                "messages": [{"role": "user", "content": "hi"}],
                "tools": tools,
                "tool_choice": "auto",
            },
        )

    assert response.status_code == 200
    mock_chat.assert_called_once_with(
        [{"role": "user", "content": "hi"}], model=None, tools=tools, tool_choice="auto"
    )


def test_chat_omits_tools_and_tool_choice_kwargs_when_not_provided():
    with patch.object(main, "chat", return_value=ok_response("gemini/gemini-2.5-flash")) as mock_chat:
        client.post("/chat", json={"messages": [{"role": "user", "content": "hi"}]})

    mock_chat.assert_called_once_with([{"role": "user", "content": "hi"}], model=None)


def test_chat_serializes_tool_calls_in_response():
    response_with_tool_call = MagicMock()
    response_with_tool_call.model = "gemini/gemini-2.5-flash"
    tool_call = MagicMock()
    tool_call.function.name = "rerun_stage"
    tool_call.function.arguments = "{}"
    response_with_tool_call.choices = [MagicMock(message=MagicMock(content=None, tool_calls=[tool_call]))]

    with patch.object(main, "chat", return_value=response_with_tool_call):
        response = client.post(
            "/chat",
            json={
                "messages": [{"role": "user", "content": "hi"}],
                "tools": [{"type": "function", "function": {"name": "rerun_stage", "parameters": {}}}],
                "tool_choice": "auto",
            },
        )

    assert response.status_code == 200
    assert response.json() == {
        "content": None,
        "model": "gemini/gemini-2.5-flash",
        "tool_calls": [{"function": {"name": "rerun_stage", "arguments": "{}"}}],
    }
