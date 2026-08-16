"""stages/docs/agent.py unit tests: the one real (non-placeholder) stage
agent besides serialization_agent. No real API calls —
clients/retrieval_client.httpx is mocked.

Tests verify:
  1. docs_stage() calls fetch_documentation, folds constraints into
     retrieval's own hint parameter (an explicit hint overrides them), and
     returns formatted content on success.
  2. It raises when the crawl found essentially nothing useful (low
     confidence or zero successful pages).
  3. It short-circuits to canned output without calling retrieval at all
     when ORCHESTRATOR_STUB_DOCS is set or the per-run context["mock"] flag
     is passed.
"""
from unittest.mock import patch

import pytest

from clients import retrieval_client
from integration_runner.stages.docs import agent as docs_stage
from helpers import _fake_fetch_response


def test_docs_stage_returns_formatted_content_on_success():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response(confidence=0.75)
        result = docs_stage.docs_stage({"seed_url": "https://example.com/docs"})

    assert "Fetched 1 page(s) from https://example.com/docs, confidence 0.75" in result
    assert "Some real content." in result


def test_docs_stage_folds_constraints_into_hint():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        docs_stage.docs_stage({
            "seed_url": "https://example.com/docs",
            "constraints": {"docs": ["focus on the yaml reference", "skip tutorials"]},
        })

    sent = mock_httpx.post.call_args.kwargs["json"]
    assert sent["hint"] == "focus on the yaml reference skip tutorials"


def test_docs_stage_explicit_hint_overrides_constraints():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        docs_stage.docs_stage({
            "seed_url": "https://example.com/docs",
            "hint": "the human's exact override",
            "constraints": {"docs": ["an older, lower-priority correction"]},
        })

    sent = mock_httpx.post.call_args.kwargs["json"]
    assert sent["hint"] == "the human's exact override"


def test_docs_stage_raises_when_confidence_below_floor():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response(confidence=0.01)
        with pytest.raises(RuntimeError, match="essentially nothing useful"):
            docs_stage.docs_stage({"seed_url": "https://example.com/docs"})


def test_docs_stage_raises_when_no_pages_succeeded():
    failed_page = [{"url": "https://example.com/docs", "success": False, "status_code": 404, "markdown": "", "links": []}]
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response(pages=failed_page, confidence=0.9)
        with pytest.raises(RuntimeError, match="essentially nothing useful"):
            docs_stage.docs_stage({"seed_url": "https://example.com/docs"})


def test_docs_stage_returns_stub_output_without_calling_retrieval_when_flag_set():
    with patch.object(docs_stage, "_STUB_DOCS", True), patch.object(retrieval_client, "httpx") as mock_httpx:
        result = docs_stage.docs_stage({"seed_url": "https://example.com/docs"})

    mock_httpx.post.assert_not_called()
    assert "https://example.com/docs" in result
    assert "MOCKED" in result


def test_docs_stage_returns_stub_output_without_calling_retrieval_when_mock_context_flag_set():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        result = docs_stage.docs_stage({"seed_url": "https://example.com/docs", "mock": True})

    mock_httpx.post.assert_not_called()
    assert "https://example.com/docs" in result
    assert "MOCKED" in result


def test_docs_stage_crawls_for_real_when_mock_context_flag_is_falsy():
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        docs_stage.docs_stage({"seed_url": "https://example.com/docs", "mock": False})

    mock_httpx.post.assert_called_once()
