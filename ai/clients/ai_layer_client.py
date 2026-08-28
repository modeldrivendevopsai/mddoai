"""HTTP client for ai-layer: chat completions and provider listings. No
fallback on failure, the call's result is the deliverable, so errors
propagate as real httpx.HTTPStatusErrors (status code + URL included),
matching retrieval's own top-level call pattern, not caught here."""
import os

import httpx

AI_LAYER_URL = os.environ.get("AI_LAYER_URL", "http://localhost:8000")
# 15 minutes: real free-tier LLM latency (rate-limit backoff, a long prompt, a
# slow provider) is unpredictable enough that a tighter default just trades a
# slow-but-real answer for a false failure. Every caller in this repo shares
# this one default rather than tuning a different number per call site.
LLM_CHAT_TIMEOUT = float(os.environ.get("LLM_CHAT_TIMEOUT", "900.0"))


def _chat_payload(
    messages: list[dict], model: str | None, tools: list[dict] | None, tool_choice: str | None
) -> dict:
    payload = {"messages": messages, "model": model}
    if tools is not None:
        payload["tools"] = tools
    if tool_choice is not None:
        payload["tool_choice"] = tool_choice
    return payload


def chat(
    messages: list[dict],
    model: str | None = None,
    tools: list[dict] | None = None,
    tool_choice: str | None = None,
    timeout: float = LLM_CHAT_TIMEOUT,
) -> dict:
    """POST to ai-layer's /chat endpoint, returns its parsed JSON response
    directly: {"model": ..., "content": str | None, "tool_calls": [{"function":
    {"name": ..., "arguments": "..."}}] | None}. content is None when the
    model responded with only tool calls."""
    payload = _chat_payload(messages, model, tools, tool_choice)
    response = httpx.post(f"{AI_LAYER_URL}/chat", json=payload, timeout=timeout)
    response.raise_for_status()
    return response.json()


async def achat(
    client: httpx.AsyncClient,
    messages: list[dict],
    model: str | None = None,
    tools: list[dict] | None = None,
    tool_choice: str | None = None,
) -> dict:
    """Async counterpart to chat(), for a caller already running inside an
    event loop with its own httpx.AsyncClient — retrieval.py's whole crawl
    is one such caller, and needs to be: a plain synchronous chat() call
    from inside async code would block that event loop for the call's full
    duration, stalling every other concurrent fetch/cleanup it's running,
    not just this one. Takes the client as a parameter rather than opening
    its own, same reason: the caller may be pooling one client across many
    concurrent calls (see retrieval.py's clean_page_content()), which a
    fresh client per call would lose. No timeout override here either, the
    caller's own client already carries the timeout it was built with."""
    payload = _chat_payload(messages, model, tools, tool_choice)
    response = await client.post(f"{AI_LAYER_URL}/chat", json=payload)
    response.raise_for_status()
    return response.json()


def list_providers() -> list[dict]:
    """Proxies ai-layer's real GET /providers. The frontend never calls
    ai-layer directly (see ai/ui-host/CLAUDE.md), orchestrator is the one
    place that does, on its behalf, so the model picker can show real,
    current provider/tier options rather than a hardcoded list that could
    drift."""
    response = httpx.get(f"{AI_LAYER_URL}/providers", timeout=10.0)
    response.raise_for_status()
    return response.json()
