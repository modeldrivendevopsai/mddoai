"""stages/docs/actions.py unit tests: extend_with_page(), the
add_page_to_docs tool's real target. No real API calls —
clients/retrieval_client.httpx is mocked.

Tests verify:
  1. It fetches the page for real and appends its content to run.last_output.
  2. Appending to an existing non-empty last_output keeps what was already
     there, it doesn't replace it.
  3. It records a real documentation_extended event.
  4. It raises ValueError when docs isn't the current pending stage.
  5. It raises RuntimeError, without appending anything, when the fetch itself failed.
"""
from unittest.mock import patch

import pytest

from clients import retrieval_client
from integration_runner import pipeline
from integration_runner.stages.docs import actions
from helpers import _fake_page_response, _fast_forward_to_psm


def test_extend_with_page_appends_fetched_content_to_last_output():
    run = pipeline.IntegrationRun()
    run.last_output = "Fetched 2 page(s) from https://example.com/docs, confidence 0.80."
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_page_response(url="https://example.com/docs/missing")
        actions.extend_with_page(run, "https://example.com/docs/missing")

    assert "Fetched 2 page(s) from https://example.com/docs, confidence 0.80." in run.last_output
    assert "# https://example.com/docs/missing" in run.last_output
    assert "Some real content." in run.last_output


def test_extend_with_page_works_from_an_empty_last_output():
    run = pipeline.IntegrationRun()
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_page_response()
        actions.extend_with_page(run, "https://example.com/docs")

    assert run.last_output.startswith("# https://example.com/docs")


def test_extend_with_page_returns_a_summary_and_records_a_real_event():
    run = pipeline.IntegrationRun()
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_page_response()
        result = actions.extend_with_page(run, "https://example.com/docs")

    assert result == {
        "url": "https://example.com/docs", "success": True, "status_code": 200,
        "markdown_length": len("# Docs\nSome real content."),
    }
    extended_events = [e for e in run.events if e["type"] == "documentation_extended"]
    assert len(extended_events) == 1
    assert extended_events[0]["data"] == result
    assert extended_events[0]["stage"] == "docs"


def test_extend_with_page_rejects_when_docs_is_not_the_current_stage():
    run = pipeline.IntegrationRun()
    _fast_forward_to_psm(run)

    with pytest.raises(ValueError, match="docs"):
        actions.extend_with_page(run, "https://example.com/docs")


def test_extend_with_page_raises_and_does_not_append_when_fetch_failed():
    run = pipeline.IntegrationRun()
    run.last_output = "existing output"
    with patch.object(retrieval_client, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_page_response(success=False)
        with pytest.raises(RuntimeError, match="failed"):
            actions.extend_with_page(run, "https://example.com/docs/missing")

    assert run.last_output == "existing output"
