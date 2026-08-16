"""Shared test fixtures used across more than one test file. Not a test file
itself (no test_ prefix, pytest won't collect it)."""
import json
from unittest.mock import MagicMock


def ok_response(content):
    """A plain chat() response: narrated/agent text, no tool calls."""
    return {"content": content, "model": "test-model", "tool_calls": None}


def tool_call(name, arguments):
    return {"function": {"name": name, "arguments": json.dumps(arguments)}}


def tool_response(tool_calls, content=None):
    return {"content": content, "model": "test-model", "tool_calls": tool_calls}


def _fake_fetch_response(pages=None, confidence=0.8):
    """A retrieval-shaped httpx response, for tests that mock
    retrieval_client.httpx directly to let docs_agent's real call through
    the mock."""
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
