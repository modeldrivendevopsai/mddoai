"""Shared test fixtures used across more than one test file. Not a test file
itself (no test_ prefix, pytest won't collect it)."""
import json
from unittest.mock import MagicMock

import orchestrator


def ok_response(content):
    """A plain chat() response: narrated/agent text, no tool calls."""
    return {"content": content, "model": "test-model", "tool_calls": None}


def tool_call(name, arguments):
    return {"function": {"name": name, "arguments": json.dumps(arguments)}}


def tool_response(tool_calls, content=None):
    return {"content": content, "model": "test-model", "tool_calls": tool_calls}


def _chat_stub(stage_outputs=None, narration="Noted."):
    """A chat() stub for any test where run_stage_async's background thread
    makes its own call_started/call_completed narration calls around the real
    stage-agent call: narration calls (system prompt mentions "MDDOAI
    Orchestrator", no tools attached) get a fixed reply; anything else is
    assumed to be a real stage agent, matched by a unique substring of its
    system prompt (see stage_outputs)."""
    stage_outputs = stage_outputs or {}

    def _respond(messages, model=None, tools=None, tool_choice=None):
        system_content = messages[0]["content"]
        if "MDDOAI Orchestrator" in system_content and not tools:
            return ok_response(narration)
        for marker, output in stage_outputs.items():
            if marker in system_content:
                return ok_response(output)
        raise AssertionError(f"unexpected chat() call, system prompt: {system_content!r}")

    return _respond


def _nudge_chat_stub(tool_calls, stage_outputs=None, nudge_content=None, narration="Noted."):
    """Like _chat_stub, plus nudge()'s own routing call (system prompt
    mentions "MDDOAI Orchestrator" WITH tools attached)."""
    base = _chat_stub(stage_outputs, narration)

    def _respond(messages, model=None, tools=None, tool_choice=None):
        system_content = messages[0]["content"]
        if "MDDOAI Orchestrator" in system_content and tools:
            return tool_response(tool_calls, content=nudge_content)
        return base(messages, model=model, tools=tools, tool_choice=tool_choice)

    return _respond


def _fake_fetch_response(pages=None, confidence=0.8):
    """A retrieval-shaped httpx response, for tests that mock orchestrator.httpx
    directly to let docs_agent's real call through the mock."""
    pages = pages or [{
        "url": "https://example.com/docs", "success": True, "status_code": 200,
        "markdown": "# Docs\nSome real content.", "links": [],
    }]
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = {
        "seed_url": "https://example.com/docs",
        "pages": pages,
        "meta": {"confidence": confidence, "pages_crawled": len(pages), "depth_reached": 1, "pending_links": []},
    }
    return resp


def _fake_page_response(url="https://example.com/docs", success=True, markdown="# Docs\nSome real content."):
    """retrieval's real POST /fetch/page returns a bare Page dict, not a
    FetchResult, distinct from _fake_fetch_response()."""
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = {"url": url, "success": success, "status_code": 200 if success else 404, "markdown": markdown, "links": []}
    return resp


def _fast_forward_to_psm(o: "orchestrator.Orchestrator") -> None:
    """Most stage-mechanics tests are about psm/atl/acceleo/generation
    behavior, not about the docs stage itself, so skip straight past docs by
    setting the index directly rather than mocking a real retrieval fetch."""
    o.current_stage_index = orchestrator.STAGES.index("psm")


def _fake_httpx_response(content=None, model="gemini/gemini-2.5-flash", tool_calls=None):
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = {"content": content, "model": model, "tool_calls": tool_calls}
    return resp


def _fake_httpx_response_raw(payload):
    """Like _fake_httpx_response, but for endpoints that aren't ai-layer's
    /chat shape, e.g. GET /providers, which returns a bare list."""
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = payload
    return resp
