"""HTTP client for ai-layer: chat completions and provider listings. No
fallback on failure, the call's result is the deliverable, so errors
propagate as real httpx.HTTPStatusErrors (status code + URL included),
matching retrieval's own top-level call pattern, not caught here."""
import os

import httpx

AI_LAYER_URL = os.environ.get("AI_LAYER_URL", "http://localhost:8000")


def chat(
    messages: list[dict],
    model: str | None = None,
    tools: list[dict] | None = None,
    tool_choice: str | None = None,
) -> dict:
    """POST to ai-layer's /chat endpoint, returns its parsed JSON response
    directly: {"model": ..., "content": str | None, "tool_calls": [{"function":
    {"name": ..., "arguments": "..."}}] | None}. content is None when the
    model responded with only tool calls."""
    payload = {"messages": messages, "model": model}
    if tools is not None:
        payload["tools"] = tools
    if tool_choice is not None:
        payload["tool_choice"] = tool_choice
    response = httpx.post(f"{AI_LAYER_URL}/chat", json=payload, timeout=120.0)
    response.raise_for_status()
    return response.json()


def list_providers() -> list[dict]:
    """Proxies ai-layer's real GET /providers. The frontend never calls
    ai-layer directly (see ai/chat-ui/CLAUDE.md), orchestrator is the one
    place that does, on its behalf, so the model picker can show real,
    current provider/tier options rather than a hardcoded list that could
    drift."""
    response = httpx.get(f"{AI_LAYER_URL}/providers", timeout=10.0)
    response.raise_for_status()
    return response.json()
