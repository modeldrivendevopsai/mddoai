"""evolution.summarize: what a run took, read from its events. The first group
gives it plain event dicts; the second gives it the events a real run records, so
a renamed field or event type in the pipeline cannot leave these tests green."""
from unittest.mock import patch

from clients import atl_agent_client
from helpers import _atl_generation_result, _fast_forward_to
from integration_runner import pipeline, runs
from integration_runner.evolution import summarize


def _event(kind, stage, data=None, timestamp=1.0):
    return {"type": kind, "stage": stage, "data": data, "timestamp": timestamp}


# --- plain events ----------------------------------------------------------------


def test_a_run_with_no_events_took_no_rounds():
    assert summarize([]) == {"forked_from": None, "stages": [], "total_rounds": 0}


def test_a_stage_adds_up_the_rounds_of_its_completed_calls():
    events = [
        _event("call_completed", "atl", {"rounds": 3}),
        _event("call_completed", "atl", {"rounds": 2}),
    ]

    summary = summarize(events)

    assert summary["stages"] == [{"stage": "atl", "rounds": 5, "constraints": []}]
    assert summary["total_rounds"] == 5


def test_stages_come_in_pipeline_order_whatever_order_they_ran_in():
    events = [_event("call_completed", "acceleo", {"rounds": 1}), _event("call_completed", "psm", {"rounds": 4})]

    assert [s["stage"] for s in summarize(events)["stages"]] == ["psm", "acceleo"]


def test_a_stage_with_no_loop_is_left_out():
    # pim and docs report no rounds, and carry no constraint here.
    events = [_event("call_completed", "pim", {"output": "x"}), _event("call_completed", "docs", None)]

    assert summarize(events)["stages"] == []


def test_a_failed_or_started_call_spends_no_rounds():
    events = [_event("call_started", "psm", {}), _event("call_failed", "psm", {"error": "boom", "rounds": 9})]

    assert summarize(events)["stages"] == []


def test_a_rounds_value_that_is_not_a_whole_number_is_ignored():
    events = [
        _event("call_completed", "psm", {"rounds": True}),
        _event("call_completed", "psm", {"rounds": "3"}),
        _event("call_completed", "psm", {"rounds": 2}),
    ]

    assert summarize(events)["stages"][0]["rounds"] == 2


def test_constraints_keep_the_order_they_were_added_with_their_source_and_time():
    events = [
        _event("constraint_added", "atl", {"constraint": "use real names", "source": "validation"}, timestamp=10.0),
        _event("constraint_added", "atl", {"constraint": "keep it short"}, timestamp=20.0),
    ]

    stage = summarize(events)["stages"][0]

    assert stage["constraints"] == [
        {"text": "use real names", "source": "validation", "timestamp": 10.0},
        {"text": "keep it short", "source": None, "timestamp": 20.0},
    ]
    assert stage["rounds"] == 0


def test_events_of_no_stage_or_an_unknown_one_are_ignored():
    events = [
        _event("constraint_added", None, {"constraint": "a"}),
        _event("constraint_added", "not-a-stage", {"constraint": "b"}),
        _event("constraint_added", "psm", {"nothing": "here"}),
    ]

    assert summarize(events)["stages"] == []


def test_a_malformed_event_is_skipped_instead_of_failing_the_summary():
    events = [
        _event("call_completed", "psm", "not a dict"),
        _event("call_completed", ["psm"], {"rounds": 3}),
        {"type": "call_completed"},
        _event("call_completed", "psm", {"rounds": 2}),
    ]

    assert summarize(events)["total_rounds"] == 2


def test_the_total_is_the_sum_over_every_stage():
    events = [
        _event("call_completed", "psm", {"rounds": 2}),
        _event("call_completed", "atl", {"rounds": 3}),
        _event("call_completed", "acceleo", {"rounds": 1}),
    ]

    assert summarize(events)["total_rounds"] == 6


def test_a_forked_run_says_which_run_it_was_forked_from_and_at_which_stage():
    events = [_event("forked_from", "atl", {"source_run_id": "run-1"})]

    assert summarize(events)["forked_from"] == {"run_id": "run-1", "stage": "atl"}


# --- the events a real run records ------------------------------------------------


def _run_atl_once(run, result):
    """One real ATL stage call through the run's own worker, so the events are
    the ones the pipeline records."""
    with patch.object(atl_agent_client, "run_atl", return_value=result):
        run.run_stage_async({"platform_description": "A GitLab CI platform"})
        run._last_thread.join(timeout=5)


def test_the_events_of_a_real_stage_call_give_its_rounds_and_what_it_learned():
    run = pipeline.IntegrationRun()
    _fast_forward_to(run, "atl")
    run.add_constraint("atl", "Use kebab-case rule names")
    _run_atl_once(
        run,
        {**_atl_generation_result(valid=False), "rounds": 3, "round_constraints": ["Fix: dangling reference"]},
    )

    summary = summarize(run.events)

    assert summary["total_rounds"] == 3
    [atl] = summary["stages"]
    assert (atl["stage"], atl["rounds"]) == ("atl", 3)
    # The typed correction has no source; the fix the validator's errors produced says so.
    assert [(c["text"], c["source"]) for c in atl["constraints"]] == [
        ("Use kebab-case rule names", None),
        ("Fix: dangling reference", pipeline.FIX_SOURCE_VALIDATION),
    ]


def test_a_second_call_of_the_same_stage_adds_its_rounds():
    run = pipeline.IntegrationRun()
    _fast_forward_to(run, "atl")
    _run_atl_once(run, {**_atl_generation_result(valid=False), "rounds": 3})
    _run_atl_once(run, {**_atl_generation_result(), "rounds": 2})

    assert summarize(run.events)["stages"][0]["rounds"] == 5


def test_a_run_forked_at_a_stage_counts_none_of_the_earlier_runs_work():
    original = runs._default
    try:
        runs.reset_pipeline()
        source = runs._default
        source.last_context = {"platform_description": "TeamCity", "docs_output": "docs", "serialization_output": "s", "pim_output": "p", "psm_output": "m"}
        source.record_event("call_completed", "psm", {"rounds": 9})

        runs.fork_run(source.run_id, "atl")
        forked = runs._default
        summary = summarize(forked.events)

        assert summary["forked_from"] == {"run_id": source.run_id, "stage": "atl"}
        assert summary["total_rounds"] == 0
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original
