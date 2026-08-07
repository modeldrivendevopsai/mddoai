"""
pipeline_tools.py unit tests: MDDOAI's declared abilities. No real API calls
— orchestrator.httpx is mocked.

Tests verify:
  1. The two docs-only tool wrappers (_fetch_documentation_tool,
     _fetch_page_tool) return a compact summary, not the full raw crawl
     content, so a nudge() reply doesn't dump a whole crawl into a tool-call
     result.
  2. load_tools() (tool_calling.py's generic filter, exercised here against
     pipeline_tools.TOOLS's real, declared shape) scopes fetch_documentation/
     fetch_page to the docs stage only, while the five pipeline-control tools
     (run_stage, rerun_stage, stage_result, add_constraint, start_pipeline)
     are available regardless of stage.
"""
from unittest.mock import patch

import orchestrator
import pipeline_tools
import tool_calling
from helpers import _fake_fetch_response, _fake_page_response


def test_fetch_documentation_tool_returns_summary_not_raw_content():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response(confidence=0.9)
        result = pipeline_tools._fetch_documentation_tool("https://example.com/docs")

    assert result == {
        "seed_url": "https://example.com/docs", "pages_fetched": 1, "confidence": 0.9, "pending_links": 0,
    }


def test_fetch_page_tool_returns_summary_not_raw_content():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_page_response()
        result = pipeline_tools._fetch_page_tool("https://example.com/docs/page")

    assert result == {
        "url": "https://example.com/docs", "success": True, "status_code": 200,
        "markdown_length": len("# Docs\nSome real content."),
    }


def test_load_tools_scopes_docs_only_tools_to_the_docs_stage():
    docs_tools = {t.name for t in tool_calling.load_tools("docs", pipeline_tools.TOOLS)}
    psm_tools = {t.name for t in tool_calling.load_tools("psm", pipeline_tools.TOOLS)}

    assert {"fetch_documentation", "fetch_page"} <= docs_tools
    assert not ({"fetch_documentation", "fetch_page"} & psm_tools)
    # the five pipeline-control tools have no "stages" key, available everywhere
    global_tools = {"run_stage", "rerun_stage", "stage_result", "add_constraint", "start_pipeline"}
    assert global_tools <= docs_tools
    assert global_tools <= psm_tools
