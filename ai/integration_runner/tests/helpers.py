"""Shared test fixtures used across more than one test file in this
directory. Not a test file itself (no test_ prefix, pytest won't collect it).

Deliberately not imported from ai/orchestrator/tests/helpers.py: each
package's test suite is self-contained, matching ai/pim_agent/ and
ai/psm_agent/'s established pattern, rather than one package's tests
reaching into a sibling package's test directory."""
from unittest.mock import MagicMock

from integration_runner import pipeline


def ok_response(content):
    """A plain chat() response: agent text, no tool calls."""
    return {"content": content, "model": "test-model", "tool_calls": None}


def _fast_forward_to_psm(o: "pipeline.IntegrationRun") -> None:
    """Most stage-mechanics tests are about psm/atl/acceleo/generation
    behavior, not about the docs stage itself, so skip straight past docs by
    setting the index directly rather than mocking a real retrieval fetch."""
    o.current_stage_index = pipeline.STAGES.index("psm")


def _fake_fetch_response(pages=None, confidence=0.8):
    """A retrieval-shaped httpx response, for tests that mock
    retrieval_client.httpx directly to let docs_stage's real call through
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
