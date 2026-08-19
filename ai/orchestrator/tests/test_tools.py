"""
tools/ package unit tests: get_tools() assembles pipeline_control.py's 5
generic tools and docs.py's 1 docs-only tool into one list; stage_metadata()
caches integration_runner's real GET /stages response. No real API calls —
clients/integration_runner_client's functions are mocked.

Tests verify:
  1. load_tools() (tool_calling.py's generic filter, exercised here against
     tools.get_tools()'s real, declared shape) scopes add_page_to_docs to
     the docs stage only, while the five pipeline-control tools are
     available regardless of stage.
  2. add_page_to_docs points directly at integration_runner_client's own
     function — no local wrapper needed here, that logic lives server-side,
     in integration_runner's own test suite.
  3. rerun_stage's own wrapper resolves the real current stage via GET
     /status first (the tool's schema takes only the docs-specific
     override fields, all optional), then calls
     integration_runner_client.rerun_stage with only the overrides the
     caller actually supplied. start_pipeline's own wrapper does the same
     override-assembly, without needing GET /status first (a fresh run
     has no "current stage" to resolve).
  4. get_tools()/stage_metadata() cache their result rather than making a
     real call on every access (see conftest.py's reset_tools_cache
     fixture, which clears this between tests).
"""
from unittest.mock import patch

import tool_calling
import tools
from clients import integration_runner_client


def test_load_tools_scopes_docs_only_tools_to_the_docs_stage():
    docs_tools = {t.name for t in tool_calling.load_tools("docs", tools.get_tools())}
    psm_tools = {t.name for t in tool_calling.load_tools("psm", tools.get_tools())}

    assert "add_page_to_docs" in docs_tools
    assert "add_page_to_docs" not in psm_tools
    # the five pipeline-control tools have no "stages" key, available everywhere
    global_tools = {"run_stage", "rerun_stage", "stage_result", "add_constraint", "start_pipeline"}
    assert global_tools <= docs_tools
    assert global_tools <= psm_tools


def test_add_page_to_docs_tool_points_directly_at_the_client_function():
    docs_tool = next(t for t in tools.get_tools() if t.name == "add_page_to_docs")
    assert docs_tool.impl is integration_runner_client.add_page_to_docs


def test_rerun_stage_tool_resolves_the_current_stage_before_calling_the_client():
    rerun_tool = next(t for t in tools.get_tools() if t.name == "rerun_stage")
    with patch.object(integration_runner_client, "get_status", return_value={"current_stage": "atl"}):
        with patch.object(integration_runner_client, "rerun_stage", return_value={"status": "started", "stage": "atl"}) as mock_rerun:
            result = rerun_tool.impl()

    mock_rerun.assert_called_once_with("atl", {})
    assert result == {"status": "started", "stage": "atl"}


def test_rerun_stage_tool_forwards_only_the_docs_overrides_actually_supplied():
    rerun_tool = next(t for t in tools.get_tools() if t.name == "rerun_stage")
    with patch.object(integration_runner_client, "get_status", return_value={"current_stage": "docs"}):
        with patch.object(integration_runner_client, "rerun_stage", return_value={"status": "started", "stage": "docs"}) as mock_rerun:
            rerun_tool.impl(hint="focus on the yaml reference", max_pages=10)

    mock_rerun.assert_called_once_with("docs", {"hint": "focus on the yaml reference", "max_pages": 10})


def test_rerun_stage_tool_forwards_mock():
    rerun_tool = next(t for t in tools.get_tools() if t.name == "rerun_stage")
    with patch.object(integration_runner_client, "get_status", return_value={"current_stage": "docs"}):
        with patch.object(integration_runner_client, "rerun_stage", return_value={"status": "started", "stage": "docs"}) as mock_rerun:
            rerun_tool.impl(mock=True)

    mock_rerun.assert_called_once_with("docs", {"mock": True})


def test_start_pipeline_tool_forwards_model_and_docs_overrides():
    start_tool = next(t for t in tools.get_tools() if t.name == "start_pipeline")
    with patch.object(
        integration_runner_client, "start_pipeline", return_value={"status": "started", "stage": "docs"},
    ) as mock_start:
        start_tool.impl(
            platform_description="GitLab CI", seed_url="https://docs.gitlab.com/ci/", model="gemini-flash", mock=True,
        )

    mock_start.assert_called_once_with("GitLab CI", "https://docs.gitlab.com/ci/", "gemini-flash", {"mock": True})


def test_start_pipeline_tool_omits_docs_overrides_when_none_given():
    start_tool = next(t for t in tools.get_tools() if t.name == "start_pipeline")
    with patch.object(
        integration_runner_client, "start_pipeline", return_value={"status": "started", "stage": "docs"},
    ) as mock_start:
        start_tool.impl(platform_description="GitLab CI", seed_url="https://docs.gitlab.com/ci/")

    mock_start.assert_called_once_with("GitLab CI", "https://docs.gitlab.com/ci/", None, {})


def test_get_tools_caches_across_calls():
    with patch.object(integration_runner_client, "get_stage_metadata") as mock_get_stages:
        mock_get_stages.return_value = {"stages": ["docs"], "descriptions": {"docs": "..."}}
        first = tools.get_tools()
        second = tools.get_tools()

    assert first is second
    mock_get_stages.assert_called_once()


def test_stage_metadata_caches_across_calls():
    with patch.object(integration_runner_client, "get_stage_metadata") as mock_get_stages:
        mock_get_stages.return_value = {"stages": ["docs"], "descriptions": {"docs": "..."}}
        first = tools.stage_metadata()
        second = tools.stage_metadata()

    assert first is second
    mock_get_stages.assert_called_once()
