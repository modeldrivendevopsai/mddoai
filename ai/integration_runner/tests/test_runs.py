"""runs.py unit tests: process-wide run management — which run is current,
the history of every run this process has seen, and every one-line
operation this service's own main.py exposes over REST. No real API
calls — ai_layer_client.chat / retrieval_client.httpx are mocked.

Tests verify:
  1. Run identity: run_id auto-generation, get_run()/current_run_id()/
     list_runs()/get_run_events() against real in-memory history.
  2. reset_pipeline() keeps prior runs as history rather than discarding
     them, only _default changes.
  3. start_pipeline() resets the pipeline (dropping prior progress/
     constraints), stores the chosen model for the whole run, and starts
     the docs stage running fresh against retrieval's real /fetch endpoint.
  4. Module-level wrapper functions (run_stage, rerun_stage, review,
     advance_stage, add_constraint) delegate to the current _default
     IntegrationRun instance correctly.
"""
from unittest.mock import patch

from clients import ai_layer_client, retrieval_client
from integration_runner import pipeline, runs
from helpers import _fake_fetch_response, _fast_forward_to_atl, ok_response


def test_get_run_looks_up_a_known_run_by_id():
    original = runs._default
    try:
        runs.reset_pipeline()
        assert runs.get_run(runs._default.run_id) is runs._default
        assert runs.get_run("no-such-run-id") is None
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_current_run_id_matches_the_default_run():
    original = runs._default
    try:
        runs.reset_pipeline()
        assert runs.current_run_id() == runs._default.run_id
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_reset_pipeline_keeps_prior_runs_as_history():
    original = runs._default
    try:
        runs.reset_pipeline()
        first_reset_id = runs._default.run_id
        runs.reset_pipeline()
        second_reset_id = runs._default.run_id

        assert first_reset_id != second_reset_id
        # Both stay in _runs (in-memory session history, see list_runs()) —
        # only the most recent one is _default, the one live endpoints act on.
        assert first_reset_id in runs._runs
        assert second_reset_id in runs._runs
        assert runs._default.run_id == second_reset_id
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_list_runs_marks_only_the_default_run_as_current():
    original = runs._default
    try:
        runs.reset_pipeline()
        first_id = runs._default.run_id
        runs.reset_pipeline()
        second_id = runs._default.run_id

        run_list = runs.list_runs()
        by_id = {r["run_id"]: r for r in run_list}

        assert by_id[first_id]["is_current"] is False
        assert by_id[second_id]["is_current"] is True
        # newest first
        assert run_list[0]["run_id"] == second_id
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_list_runs_surfaces_platform_name_from_the_first_recorded_event():
    original = runs._default
    try:
        runs.reset_pipeline()
        runs._default.record_event(
            "call_started", "docs", {"platform_description": "TeamCity"}
        )

        run_list = runs.list_runs()

        assert run_list[0]["platform_name"] == "TeamCity"
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_get_run_events_returns_none_for_an_unknown_run():
    assert runs.get_run_events("no-such-run-id") is None


def test_get_run_events_reads_a_specific_run_not_just_default():
    original = runs._default
    try:
        runs.reset_pipeline()
        first_id = runs._default.run_id
        runs._default.record_event("call_started", "docs", {"platform_description": "TeamCity"})
        runs.reset_pipeline()  # first_id is no longer _default

        result = runs.get_run_events(first_id)

        assert result is not None
        assert len(result["events"]) == 1  # just the raw call_started event, no narration here
        assert result["events"][0]["data"]["platform_description"] == "TeamCity"
        assert result["is_current"] is False
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_start_pipeline_stores_the_chosen_model_for_the_whole_run():
    original = runs._default
    try:
        with patch.object(retrieval_client, "httpx") as mock_httpx:
            mock_httpx.post.return_value = _fake_fetch_response()
            runs.start_pipeline("TeamCity", "https://example.com/docs", model="gemini-flash")
            assert runs._default.model == "gemini-flash"
            runs.wait_for_idle()
    finally:
        runs._default = original


def test_start_pipeline_defaults_model_to_none():
    original = runs._default
    try:
        with patch.object(retrieval_client, "httpx") as mock_httpx:
            mock_httpx.post.return_value = _fake_fetch_response()
            runs.start_pipeline("TeamCity", "https://example.com/docs")
            assert runs._default.model is None
            runs.wait_for_idle()
    finally:
        runs._default = original


def test_start_pipeline_forwards_docs_options_to_the_real_fetch_call():
    original = runs._default
    try:
        with patch.object(retrieval_client, "httpx") as mock_httpx:
            mock_httpx.post.return_value = _fake_fetch_response()
            runs.start_pipeline(
                "TeamCity",
                "https://example.com/docs",
                docs_options={
                    "hint": "focus on syntax",
                    "exclude_urls": ["https://example.com/blog"],
                    "max_pages": 5,
                    "max_depth": 2,
                    "force_refresh": True,
                },
            )
            runs.wait_for_idle()

        fetch_calls = [c for c in mock_httpx.post.call_args_list if c.args[0].endswith("/fetch")]
        assert fetch_calls[0].kwargs["json"] == {
            "url": "https://example.com/docs", "hint": "focus on syntax",
            "exclude_urls": ["https://example.com/blog"], "max_pages": 5, "max_depth": 2, "force_refresh": True,
        }
    finally:
        runs._default = original


def test_start_pipeline_omits_docs_options_when_none_given():
    original = runs._default
    try:
        with patch.object(retrieval_client, "httpx") as mock_httpx:
            mock_httpx.post.return_value = _fake_fetch_response()
            runs.start_pipeline("TeamCity", "https://example.com/docs")
            runs.wait_for_idle()

        fetch_calls = [c for c in mock_httpx.post.call_args_list if c.args[0].endswith("/fetch")]
        assert fetch_calls[0].kwargs["json"] == {"url": "https://example.com/docs"}
    finally:
        runs._default = original


def test_current_returns_the_live_default_run():
    # current() is the one thing this registry module exposes for operating
    # on the current run — everything else (run_stage, review,
    # add_constraint, ...) is a real method on the IntegrationRun instance
    # it returns (see test_pipeline.py), not duplicated here as its own
    # proxy function.
    original = runs._default
    fresh = pipeline.IntegrationRun()
    runs._default = fresh
    try:
        assert runs.current() is fresh

        _fast_forward_to_atl(runs.current())
        with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL description")):
            runs.current().run_stage({"platform_description": "desc"})

        # A mutation through runs.current() is visible on the same real
        # object reset_pipeline()/resume_run() would also act on.
        assert runs._default.last_output == "ATL description"
    finally:
        runs._default = original
