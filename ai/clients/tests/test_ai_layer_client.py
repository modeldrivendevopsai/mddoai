"""ai_layer_client.py unit tests: chat() POSTs to ai-layer's real /chat
endpoint and returns its parsed JSON response as a plain dict, unmodified;
list_providers() proxies ai-layer's real GET /providers. These mock
httpx.post/get directly (the actual network boundary), not chat() itself —
every consumer (integration_runner's stage agents, orchestrator's own
chat-reply code, retrieval's ranking/cleanup calls) has its own tests that
mock chat()/achat() instead, at their own boundary.
"""
from unittest.mock import MagicMock, patch

import httpx
import pytest

import ai_layer_client
from helpers import _fake_httpx_response, _fake_httpx_response_raw


def test_chat_posts_to_ai_layer_url_with_messages_and_model():
    messages = [{"role": "user", "content": "hi"}]
    with patch("ai_layer_client.httpx.post", return_value=_fake_httpx_response("hello")) as mock_post:
        result = ai_layer_client.chat(messages, model="auto")

    mock_post.assert_called_once_with(
        f"{ai_layer_client.AI_LAYER_URL}/chat",
        json={"messages": messages, "model": "auto"},
        timeout=120.0,
    )
    assert result["content"] == "hello"
    assert result["model"] == "gemini/gemini-2.5-flash"


def test_chat_omits_tools_and_tool_choice_when_not_provided():
    with patch("ai_layer_client.httpx.post", return_value=_fake_httpx_response("hello")) as mock_post:
        ai_layer_client.chat([{"role": "user", "content": "hi"}])

    sent_payload = mock_post.call_args.kwargs["json"]
    assert "tools" not in sent_payload
    assert "tool_choice" not in sent_payload


def test_chat_includes_tools_and_tool_choice_when_provided():
    tools_schema = [{"type": "function", "function": {"name": "rerun_stage", "description": "...", "parameters": {}}}]
    with patch("ai_layer_client.httpx.post", return_value=_fake_httpx_response("hello")) as mock_post:
        ai_layer_client.chat([{"role": "user", "content": "hi"}], tools=tools_schema, tool_choice="auto")

    sent_payload = mock_post.call_args.kwargs["json"]
    assert sent_payload["tools"] == tools_schema
    assert sent_payload["tool_choice"] == "auto"


def test_chat_returns_tool_calls_as_plain_dicts():
    tool_calls = [{"function": {"name": "rerun_stage", "arguments": "{}"}}]
    with patch("ai_layer_client.httpx.post", return_value=_fake_httpx_response(None, tool_calls=tool_calls)):
        result = ai_layer_client.chat([{"role": "user", "content": "hi"}])

    assert result["content"] is None
    assert result["tool_calls"] == tool_calls


def test_chat_returns_no_tool_calls_when_response_has_none():
    with patch("ai_layer_client.httpx.post", return_value=_fake_httpx_response("hello", tool_calls=None)):
        result = ai_layer_client.chat([{"role": "user", "content": "hi"}])

    assert result["tool_calls"] is None


def test_chat_raises_when_ai_layer_returns_an_error_status():
    resp = MagicMock()
    resp.raise_for_status.side_effect = httpx.HTTPStatusError("500", request=MagicMock(), response=MagicMock())
    with patch("ai_layer_client.httpx.post", return_value=resp):
        with pytest.raises(httpx.HTTPStatusError):
            ai_layer_client.chat([{"role": "user", "content": "hi"}])


def test_list_providers_proxies_ai_layers_real_providers_endpoint():
    payload = [{"name": "gemini-flash", "tier": "free"}, {"name": "claude-subscription", "tier": "subscription"}]
    with patch("ai_layer_client.httpx.get", return_value=_fake_httpx_response_raw(payload)) as mock_get:
        result = ai_layer_client.list_providers()

    mock_get.assert_called_once_with(f"{ai_layer_client.AI_LAYER_URL}/providers", timeout=10.0)
    assert result == payload
