"""runs.py unit tests: process-wide run management, which run is current,
the history of every run this process has seen, and every one-line
operation this service's own main.py exposes over REST. No real API
calls, execution_agent_client's execute_atl/execute_acceleo /
retrieval_client.httpx are mocked.

Tests verify:
  1. Run identity: run_id auto-generation, get_run()/current_run_id()/
     list_runs()/get_run_events() against real in-memory history.
  2. reset_pipeline() keeps prior runs as history rather than discarding
     them, only _default changes.
  3. start_pipeline() stores the chosen model for the whole run and starts
     the docs stage running fresh against retrieval's real /fetch endpoint;
     it only resets the pipeline (dropping prior progress/constraints) when
     the current run isn't already a blank slot, reusing an already-current
     empty run (e.g. one just brought back via resume_run()) in place
     otherwise.
  4. Module-level wrapper functions (run_stage, rerun_stage, review,
     advance_stage, add_constraint) delegate to the current _default
     IntegrationRun instance correctly.
  5. fork_run() seeds a genuinely new run from an earlier stage's real
     output on a past run, without mutating that past run at all, never
     auto-starts the target stage, and refuses an unknown source run, an
     unreal stage name, or a source run that never actually produced the
     required earlier output.
"""
import threading
from unittest.mock import patch

import pytest

from clients import retrieval_client
from integration_runner import pipeline, runs
from helpers import _MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME, _fake_fetch_response, _fast_forward_to_generation, _mocked_generation_execution


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
        # Both stay in _runs (in-memory session history, see list_runs()),
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
        # start_pipeline() only resets when the current run already has
        # events (see test_start_pipeline_resets_when_current_run_already_has_events
        # below), reset explicitly first so this test always runs against
        # its own fresh, isolated run rather than possibly reusing (and
        # mutating) `original` in place if it happened to already be empty.
        runs.reset_pipeline()
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
        # See test_start_pipeline_stores_the_chosen_model_for_the_whole_run's
        # own comment for why this resets explicitly first.
        runs.reset_pipeline()
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
        # See test_start_pipeline_stores_the_chosen_model_for_the_whole_run's
        # own comment for why this resets explicitly first.
        runs.reset_pipeline()
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
        # See test_start_pipeline_stores_the_chosen_model_for_the_whole_run's
        # own comment for why this resets explicitly first.
        runs.reset_pipeline()
        with patch.object(retrieval_client, "httpx") as mock_httpx:
            mock_httpx.post.return_value = _fake_fetch_response()
            runs.start_pipeline("TeamCity", "https://example.com/docs")
            runs.wait_for_idle()

        fetch_calls = [c for c in mock_httpx.post.call_args_list if c.args[0].endswith("/fetch")]
        assert fetch_calls[0].kwargs["json"] == {"url": "https://example.com/docs"}
    finally:
        runs._default = original


def test_start_pipeline_reuses_a_resumed_empty_run_in_place():
    # The real scenario this covers, reproduced faithfully rather than just
    # asserted: reset_pipeline() creates a blank run, ANOTHER reset_pipeline()
    # replaces it as current (leaving the first one as empty history, e.g.
    # abandoned before the start form was ever submitted), resume_run()
    # brings the first one back, filling in the start form and clicking
    # Start from there should continue that resumed run, not discard it for
    # yet another new one the instant Start is clicked.
    original = runs._default
    try:
        runs.reset_pipeline()
        resumed_run_id = runs._default.run_id
        runs.reset_pipeline()  # a different run is current now; resumed_run_id is empty history
        runs.resume_run(resumed_run_id)
        assert runs._default.run_id == resumed_run_id  # sanity: resume actually worked

        with patch.object(retrieval_client, "httpx") as mock_httpx:
            mock_httpx.post.return_value = _fake_fetch_response()
            runs.start_pipeline("TeamCity", "https://example.com/docs")
            runs.wait_for_idle()

        assert runs._default.run_id == resumed_run_id
        assert any(e["type"] == "call_completed" for e in runs._default.events)  # really ran, in place
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_start_pipeline_resets_when_current_run_already_has_events():
    # Restart calls start_pipeline() again for the SAME platform, the
    # current run at that point already has real progress (events), so it
    # must still get a genuinely fresh run, never reused in place.
    original = runs._default
    try:
        runs.reset_pipeline()
        runs._default.record_event("call_started", "docs", {"platform_description": "Old"})
        old_run_id = runs._default.run_id

        with patch.object(retrieval_client, "httpx") as mock_httpx:
            mock_httpx.post.return_value = _fake_fetch_response()
            runs.start_pipeline("TeamCity", "https://example.com/docs")
            runs.wait_for_idle()

        assert runs._default.run_id != old_run_id
        assert old_run_id in runs._runs  # kept as history, same as any other reset_pipeline() call
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_start_pipeline_refuses_a_busy_run_instead_of_resetting_it():
    # The idle gap between stages: a stage claimed busy after the route
    # handler's own pre-flight check but before start_pipeline() runs. It
    # must not reset a run out from under its own in-flight thread.
    original = runs._default
    try:
        runs.reset_pipeline()
        runs._default.busy = True

        with pytest.raises(pipeline.BusyError):
            runs.start_pipeline("TeamCity", "https://example.com/docs")
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_start_pipeline_does_not_mutate_model_when_refused_as_busy():
    # A refused start must leave the busy run completely untouched, model
    # included - claim_busy() runs before set_model() specifically so a
    # racing claim on the same instance is caught before any mutation, not
    # discovered only once model has already changed underneath it.
    original = runs._default
    try:
        runs.reset_pipeline()
        runs._default.model = "gemini-flash"
        runs._default.busy = True

        with pytest.raises(pipeline.BusyError):
            runs.start_pipeline("TeamCity", "https://example.com/docs", model="mistral-small")

        assert runs._default.model == "gemini-flash"
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_reset_pipeline_refuses_a_busy_run():
    # Same atomic backstop as start_pipeline()'s own busy check, for the
    # identical race on /reset: a run claimed busy after the route
    # handler's own pre-flight check but before reset_pipeline() runs must
    # not be swapped out from under its own in-flight thread.
    original = runs._default
    try:
        runs.reset_pipeline()
        busy_run_id = runs._default.run_id
        runs._default.busy = True

        with pytest.raises(pipeline.BusyError):
            runs.reset_pipeline()

        assert runs._default.run_id == busy_run_id  # not swapped out
    finally:
        runs._default.busy = False
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_resume_run_refuses_when_the_current_run_is_busy():
    # Same race as reset_pipeline()'s own busy check, for /resume: the run
    # being replaced (not the one being resumed) must not be swapped out
    # from under its own in-flight thread.
    original = runs._default
    try:
        runs.reset_pipeline()
        target_run_id = runs._default.run_id
        runs.reset_pipeline()
        busy_run_id = runs._default.run_id
        runs._default.busy = True

        with pytest.raises(pipeline.BusyError):
            runs.resume_run(target_run_id)

        assert runs._default.run_id == busy_run_id  # not swapped out
    finally:
        runs._default.busy = False
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_fork_run_seeds_a_new_run_from_an_earlier_stages_real_output():
    original = runs._default
    try:
        runs.reset_pipeline()
        source_run = runs._default
        source_run.current_stage_index = pipeline.STAGES.index("generation")
        source_run.last_context = {
            "platform_description": "TeamCity",
            "docs_output": "real docs",
            "serialization_output": "real serialization",
            "pim_output": "real pim",
            "psm_output": "real psm",
            "atl_output": "real atl, the one with the real bug",
            "acceleo_output": "real acceleo",
        }
        source_run_id = source_run.run_id

        result = runs.fork_run(source_run_id, "atl")

        assert result["stage"] == "atl"
        new_run = runs._default
        assert new_run.run_id != source_run_id
        assert new_run.current_stage == "atl"
        assert new_run.last_context == {
            "platform_description": "TeamCity",
            "docs_output": "real docs",
            "serialization_output": "real serialization",
            "pim_output": "real pim",
            "psm_output": "real psm",
        }
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_fork_run_never_auto_starts_the_target_stage_even_outside_manual_start():
    # "pim" isn't in _REQUIRES_MANUAL_START, but a fork always pauses
    # regardless: the human just made a real judgment call about where to
    # restart from, they should get the same chance every manual-start
    # stage already gets to add a real constraint before it fires, not a
    # race against an immediately-spawned thread.
    original = runs._default
    try:
        runs.reset_pipeline()
        source_run = runs._default
        source_run.current_stage_index = pipeline.STAGES.index("psm")
        source_run.last_context = {
            "platform_description": "TeamCity",
            "docs_output": "real docs",
            "serialization_output": "real serialization",
        }
        source_run_id = source_run.run_id

        runs.fork_run(source_run_id, "pim")

        assert runs._default.busy is False
        assert not any(e["type"] == "call_started" for e in runs._default.events)  # never actually ran
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_fork_run_records_a_forked_from_event_naming_the_source_run():
    original = runs._default
    try:
        runs.reset_pipeline()
        source_run_id = runs._default.run_id
        runs._default.last_context = {"platform_description": "TeamCity", "docs_output": "real docs"}

        runs.fork_run(source_run_id, "serialization")

        [event] = runs._default.events
        assert event["type"] == "forked_from"
        assert event["stage"] == "serialization"
        assert event["data"]["source_run_id"] == source_run_id
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_fork_run_leaves_the_source_run_completely_untouched():
    original = runs._default
    try:
        runs.reset_pipeline()
        source_run = runs._default
        source_run.current_stage_index = pipeline.STAGES.index("generation")
        source_run.last_context = {
            "platform_description": "TeamCity",
            "docs_output": "real docs",
            "serialization_output": "real serialization",
            "pim_output": "real pim",
            "psm_output": "real psm",
            "atl_output": "real atl",
        }
        source_run.record_event("call_failed", "generation", {"error": "real execution error"})
        source_run_id = source_run.run_id
        events_before = list(source_run.events)

        runs.fork_run(source_run_id, "acceleo")

        still_there = runs.get_run(source_run_id)
        assert still_there is source_run
        assert still_there.current_stage == "generation"  # unchanged
        assert still_there.events == events_before  # nothing appended to the source run itself
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_fork_run_raises_for_an_unknown_source_run():
    original = runs._default
    try:
        with pytest.raises(ValueError, match="No run"):
            runs.fork_run("no-such-run-id", "atl")
    finally:
        runs._default = original


def test_fork_run_raises_for_a_stage_that_is_not_real():
    original = runs._default
    try:
        runs.reset_pipeline()
        source_run_id = runs._default.run_id

        with pytest.raises(ValueError, match="isn't a real stage"):
            runs.fork_run(source_run_id, "not-a-real-stage")
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_fork_run_raises_when_the_source_run_never_reached_a_required_earlier_stage():
    original = runs._default
    try:
        runs.reset_pipeline()
        source_run = runs._default
        source_run.last_context = {"platform_description": "TeamCity"}  # never even finished docs
        source_run_id = source_run.run_id

        with pytest.raises(ValueError, match="pim_output"):
            runs.fork_run(source_run_id, "atl")
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_fork_run_refuses_when_the_current_run_is_busy():
    # Same race as reset_pipeline()'s/resume_run()'s own busy check: the run
    # being replaced (not the source run being forked from) must not be
    # swapped out from under its own in-flight thread.
    original = runs._default
    try:
        runs.reset_pipeline()
        source_run_id = runs._default.run_id
        runs._default.last_context = {"platform_description": "TeamCity", "docs_output": "real docs"}
        runs.reset_pipeline()
        busy_run_id = runs._default.run_id
        runs._default.busy = True

        with pytest.raises(pipeline.BusyError):
            runs.fork_run(source_run_id, "serialization")

        assert runs._default.run_id == busy_run_id  # not swapped out
    finally:
        runs._default.busy = False
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_fork_run_succeeds_even_while_the_source_run_itself_is_busy():
    # A source run genuinely still executing its OWN current stage doesn't
    # block forking an EARLIER stage's already-final output out of it: the
    # keys _seed_context_from() reads are only ever for stages strictly
    # before the source run's own current one, already fixed before that
    # run's in-flight attempt began, so a concurrent write to the source
    # run's own current-stage fields can't race the read. Only the run
    # being REPLACED (the current run) needs the busy guard, covered above.
    original = runs._default
    try:
        runs.reset_pipeline()
        source_run = runs._default
        source_run.last_context = {"platform_description": "TeamCity", "docs_output": "real docs"}
        source_run_id = source_run.run_id
        runs.reset_pipeline()  # a separate, not-busy run is current now
        source_run.busy = True  # only after it's no longer current - its own stage is still running

        result = runs.fork_run(source_run_id, "serialization")

        assert result["stage"] == "serialization"
        assert runs._default.run_id != source_run_id
    finally:
        # source_run itself (left busy=True above) is discarded here along
        # with every other run _runs.clear() drops, not reused by any later
        # test, so its stray busy flag needs no explicit reset.
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_list_runs_stays_consistent_under_a_concurrent_reset():
    # reset_pipeline() inserts into _runs under _registry_lock; list_runs()
    # must read under the same lock or risk "dictionary changed size during
    # iteration" when the two run concurrently. Bounded rounds of a fixed,
    # modest number of resets and reads each, not an open-ended loop: _runs
    # only ever grows (reset_pipeline() keeps prior runs as history, see its
    # own docstring), so an unbounded resetter thread racing an unbounded
    # reader would blow up list_runs()'s own O(size of _runs) cost into a
    # runaway feedback loop instead of actually testing the race.
    reset_count = 50
    read_count = 50
    original = runs._default
    try:
        for _ in range(20):
            errors = []
            barrier = threading.Barrier(2)

            def resetter():
                barrier.wait()
                for _ in range(reset_count):
                    runs.reset_pipeline()

            def reader():
                barrier.wait()
                for _ in range(read_count):
                    try:
                        runs.list_runs()
                    except RuntimeError as e:
                        errors.append(e)

            t_reset = threading.Thread(target=resetter)
            t_read = threading.Thread(target=reader)
            t_reset.start()
            t_read.start()
            t_reset.join(timeout=10)
            t_read.join(timeout=10)

            assert errors == [], f"list_runs() raised under concurrent reset_pipeline(): {errors}"
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original


def test_current_returns_the_live_default_run():
    # current() is the one thing this registry module exposes for operating
    # on the current run, everything else (run_stage, review,
    # add_constraint, ...) is a real method on the IntegrationRun instance
    # it returns (see test_pipeline.py), not duplicated here as its own
    # proxy function.
    original = runs._default
    fresh = pipeline.IntegrationRun()
    runs._default = fresh
    try:
        assert runs.current() is fresh

        _fast_forward_to_generation(runs.current())
        with _mocked_generation_execution():
            runs.current().run_stage({"platform_description": "desc", "atl_output": _MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME})

        # A mutation through runs.current() is visible on the same real
        # object reset_pipeline()/resume_run() would also act on.
        assert runs._default.last_output == "stages: []\n"
    finally:
        runs._default = original
