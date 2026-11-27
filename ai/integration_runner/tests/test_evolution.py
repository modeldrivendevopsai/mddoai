"""evolution.summarize: what a run took, read from its events. The first groups
give it plain event dicts; the last gives it the events a real run records, so
a renamed field or event type in the pipeline cannot leave these tests green."""
import copy
from unittest.mock import patch

from clients import atl_agent_client, journal_client
from clients.agent_service_errors import AgentServiceError
from helpers import _atl_generation_result, _fast_forward_to
from integration_runner import evolution, pipeline, runs
from integration_runner.evolution import annotate_manifest, number_calls, summarize

VALIDATION = pipeline.FIX_SOURCE_VALIDATION


def _event(kind, stage, data=None, timestamp=1.0):
    return {"type": kind, "stage": stage, "data": data, "timestamp": timestamp}


def _call(stage, rounds=None, valid=None, attempt=None, mock=False, timestamp=1.0):
    """The started and completed events of one call."""
    result = {}
    if rounds is not None:
        result["rounds"] = rounds
    if valid is not None:
        result["validation"] = {"valid": valid}
    if attempt is not None:
        result["attempt"] = attempt
    return [_event("call_started", stage, {"mock": True} if mock else {}), _event("call_completed", stage, result, timestamp)]


def _failed(stage):
    return [_event("call_started", stage), _event("call_failed", stage, {"error": "boom"})]


def _typed(stage, text):
    return _event("constraint_added", stage, {"constraint": text})


def _learned(stage, text):
    return _event("constraint_added", stage, {"constraint": text, "source": VALIDATION})


def _stage(summary, name):
    return next(stage for stage in summary["stages"] if stage["stage"] == name)


def _texts(corrections):
    return [c["text"] for c in corrections]


# --- calls ------------------------------------------------------------------------


def test_a_run_with_no_events_took_nothing():
    assert summarize([]) == {"forked_from": None, "stages": []}


def test_a_stage_lists_its_calls_oldest_first_with_the_rounds_of_each():
    events = _call("atl", rounds=7, valid=False, attempt="attempt_1") + _call("atl", rounds=5, valid=False, attempt="attempt_2")
    events += _call("atl", rounds=1, valid=True, attempt="attempt_3")

    atl = _stage(summarize(events), "atl")

    assert [(c["attempt_n"], c["rounds"], c["outcome"]) for c in atl["calls"]] == [
        (1, 7, "invalid"),
        (2, 5, "invalid"),
        (3, 1, "valid"),
    ]


def test_stages_come_in_pipeline_order_whatever_order_they_ran_in():
    events = _call("acceleo", rounds=1) + _call("psm", rounds=4)

    assert [s["stage"] for s in summarize(events)["stages"]] == ["psm", "acceleo"]


def test_a_stage_with_no_loop_has_calls_with_no_rounds():
    # pim and docs report none: that is not the same as a stage that spent zero.
    pim = _stage(summarize(_call("pim") + _call("pim")), "pim")

    assert [c["rounds"] for c in pim["calls"]] == [None, None]
    assert [c["outcome"] for c in pim["calls"]] == ["done", "done"]


def test_a_stage_that_ran_once_with_no_loop_and_no_correction_has_no_evolution_to_show():
    summary = summarize(_call("docs") + _call("pim") + _call("psm", rounds=1))

    assert [s["stage"] for s in summary["stages"]] == ["psm"]


def test_a_stage_that_needed_more_than_one_call_is_listed_even_with_no_loop():
    generation = _stage(summarize(_failed("generation") + _call("generation")), "generation")

    assert [c["outcome"] for c in generation["calls"]] == ["failed", "done"]


def test_a_stage_whose_only_call_did_not_end_cleanly_is_listed():
    assert [s["stage"] for s in summarize(_failed("pim"))["stages"]] == ["pim"]
    assert [s["stage"] for s in summarize(_call("pim", valid=False))["stages"]] == ["pim"]


def test_a_failed_call_is_listed_with_no_rounds_and_the_attempt_it_names():
    events = [_event("call_started", "psm"), _event("call_failed", "psm", {"error": "boom", "rounds": 9, "attempt": "attempt_1"})]

    [call] = _stage(summarize(events), "psm")["calls"]

    assert (call["outcome"], call["rounds"], call["attempt_n"]) == ("failed", None, 1)


def test_a_failed_call_that_names_no_attempt_has_none():
    events = [_event("call_started", "psm"), _event("call_failed", "psm", {"error": "boom"})]

    [call] = _stage(summarize(events), "psm")["calls"]

    assert call["attempt_n"] is None


def test_a_failed_call_that_named_its_attempt_does_not_leave_the_stage_unnumbered():
    # A stage that records an attempt for a failed result (pim, generation): the failed call
    # names its own, so the older call that did not name one still gets the one that is left.
    events = [
        *_call("pim"),
        _event("call_started", "pim"),
        _event("call_failed", "pim", {"error": "check failed", "attempt": "attempt_2"}),
    ]

    numbered = number_calls(summarize(events), [_entry("pim", 1), _entry("pim", 2)])

    assert [call["attempt_n"] for call in _stage(numbered, "pim")["calls"]] == [1, 2]


def test_a_rounds_value_that_is_not_a_whole_number_is_ignored():
    events = _call("psm", rounds=True) + _call("psm", rounds="3") + _call("psm", rounds=2)

    psm = _stage(summarize(events), "psm")

    assert [c["rounds"] for c in psm["calls"]] == [None, None, 2]


def test_an_attempt_name_that_is_not_one_gives_no_attempt_number():
    events = _call("atl", rounds=1, attempt="attempt_0") + _call("atl", rounds=1, attempt="latest") + _call("atl", rounds=1)

    assert [c["attempt_n"] for c in _stage(summarize(events), "atl")["calls"]] == [None, None, None]


def test_a_mock_call_is_marked():
    events = _call("psm", rounds=1, mock=True) + _call("psm", rounds=1)

    assert [c["mock"] for c in _stage(summarize(events), "psm")["calls"]] == [True, False]


def test_a_result_the_validator_never_judged_is_done_not_valid():
    # A correction is what gets the lone stage listed.
    pim = _stage(summarize(_call("pim") + [_typed("pim", "x")]), "pim")

    assert pim["calls"][0]["outcome"] == "done"


# --- corrections ----------------------------------------------------------------------


def test_what_the_validator_learned_during_a_call_is_what_that_call_learned():
    events = [_event("call_started", "atl"), _learned("atl", "Fix: a"), _learned("atl", "Fix: b"), _event("call_completed", "atl", {"rounds": 3})]

    [call] = _stage(summarize(events), "atl")["calls"]

    assert [(c["text"], c["learned"]) for c in call["learned"]] == [("Fix: a", True), ("Fix: b", True)]
    assert call["given"] == []


def test_a_correction_typed_between_calls_is_given_to_the_call_that_follows():
    events = _call("atl", rounds=2) + [_typed("atl", "use real names")] + _call("atl", rounds=1)

    first, second = _stage(summarize(events), "atl")["calls"]

    assert first["given"] == []
    assert [(c["text"], c["learned"]) for c in second["given"]] == [("use real names", False)]
    assert second["learned"] == []


def test_a_call_ran_with_the_corrections_the_stage_had_when_it_began_not_those_it_learned():
    # Attempt 1 learned three fixes over 4 rounds. Attempt 2 ran with them, in 1.
    events = [_event("call_started", "atl")] + [_learned("atl", f"Fix: {n}") for n in "abc"] + [_event("call_completed", "atl", {"rounds": 4})]
    events += _call("atl", rounds=1)

    first, second = _stage(summarize(events), "atl")["calls"]

    assert (first["ran_with"], len(first["learned"]), first["rounds"]) == (0, 3, 4)
    assert (second["ran_with"], len(second["learned"]), second["rounds"]) == (3, 0, 1)


def test_ran_with_grows_with_every_correction_the_stage_has_had_by_then():
    events = [_typed("atl", "a"), _typed("atl", "b")] + _call("atl", rounds=3)
    events += [_event("call_started", "atl"), _learned("atl", "Fix: c"), _event("call_completed", "atl", {"rounds": 2})]
    events += [_typed("atl", "d")] + _call("atl", rounds=1)

    calls = _stage(summarize(events), "atl")["calls"]

    # The first ran with the two typed before it, the second with the same two, the third with all four.
    assert [c["ran_with"] for c in calls] == [2, 2, 4]
    assert [_texts(c["given"]) for c in calls] == [["a", "b"], [], ["d"]]


def test_each_stage_counts_only_its_own_corrections():
    events = [_typed("psm", "p")] + _call("psm", rounds=1) + [_typed("atl", "a")] + _call("atl", rounds=1)

    summary = summarize(events)

    assert _stage(summary, "psm")["calls"][0]["ran_with"] == 1
    assert _stage(summary, "atl")["calls"][0]["ran_with"] == 1


def test_a_correction_added_after_the_last_call_waits_to_be_tried():
    events = _call("atl", rounds=1) + [_typed("atl", "next time")]

    atl = _stage(summarize(events), "atl")

    assert _texts(atl["waiting"]) == ["next time"]
    assert atl["calls"][0]["given"] == [] and atl["calls"][0]["learned"] == []


def test_a_stage_with_only_a_waiting_correction_is_listed_with_no_calls():
    atl = _stage(summarize([_typed("atl", "before anything ran")]), "atl")

    assert atl["calls"] == []
    assert _texts(atl["waiting"]) == ["before anything ran"]


def test_a_call_still_running_keeps_what_it_started_with_and_has_learned():
    events = [_typed("atl", "typed"), _event("call_started", "atl"), _learned("atl", "Fix: x")]

    atl = _stage(summarize(events), "atl")

    assert atl["calls"] == []
    assert _texts(atl["waiting"]) == ["typed", "Fix: x"]


def test_a_call_that_never_ended_hands_what_it_had_to_the_next_one():
    events = [_typed("atl", "a"), _event("call_started", "atl"), _learned("atl", "Fix: b")] + _call("atl", rounds=2)

    [call] = _stage(summarize(events), "atl")["calls"]

    assert _texts(call["given"]) == ["a", "Fix: b"]
    assert call["ran_with"] == 2


def test_a_correction_with_no_source_recorded_during_a_call_is_read_as_learned_by_it():
    # A run from before a correction said where it came from: the timing says so.
    events = [_event("call_started", "atl"), _typed("atl", "Fix: old style"), _event("call_completed", "atl", {"rounds": 2})]

    atl = _stage(summarize(events), "atl")

    assert [(c["text"], c["learned"]) for c in atl["calls"][0]["learned"]] == [("Fix: old style", True)]
    assert atl["waiting"] == []


def test_a_correction_a_person_types_while_a_call_runs_is_read_as_learned_by_it():
    # Known limit: nothing tells it from a fix the validator produced, since typed corrections carry no source.
    events = [_event("call_started", "atl"), _typed("atl", "typed mid-call"), _event("call_completed", "atl", {})]

    [call] = _stage(summarize(events), "atl")["calls"]

    assert _texts(call["learned"]) == ["typed mid-call"]


def test_corrections_a_person_made_permanent_go_with_the_stages_latest_result():
    events = _call("atl", rounds=4, valid=True) + [_event("constraints_promoted", "atl", {"constraints": ["keep", "this"]})]
    events += _call("atl", rounds=1, valid=True)

    first, second = _stage(summarize(events), "atl")["calls"]

    assert first["promoted"] == ["keep", "this"]
    assert second["promoted"] == []


def test_corrections_made_permanent_skip_a_failed_call_after_the_result_they_followed():
    events = _call("atl", rounds=2, valid=True) + _failed("atl") + [_event("constraints_promoted", "atl", {"constraints": ["x"]})]

    valid, failed = _stage(summarize(events), "atl")["calls"]

    assert (valid["promoted"], failed["promoted"]) == (["x"], [])


def test_a_promotion_with_no_result_before_it_is_ignored():
    events = [_event("constraints_promoted", "atl", {"constraints": ["x"]})]

    assert summarize(events)["stages"] == []


# --- malformed events and forks -----------------------------------------------------


def test_events_of_no_stage_or_an_unknown_one_are_ignored():
    events = [_typed(None, "a"), _typed("not-a-stage", "b"), _typed(["psm"], "c"), {"type": "call_completed"}]

    assert summarize(events)["stages"] == []


def test_a_malformed_event_is_skipped_instead_of_failing_the_summary():
    events = [
        _event("constraint_added", "psm", "not a dict"),
        _event("constraint_added", "psm", {"constraint": 7}),
        _event("constraints_promoted", "psm", {"constraints": "nope"}),
        _event("call_completed", "psm", "not a dict"),
    ] + _call("psm", rounds=2)

    psm = _stage(summarize(events), "psm")

    assert [c["outcome"] for c in psm["calls"]] == ["done", "done"]


def test_a_forked_run_says_which_run_it_was_forked_from_and_at_which_stage():
    events = [_event("forked_from", "atl", {"source_run_id": "run-1"})]

    summary = summarize(events)

    assert summary["forked_from"] == {"run_id": "run-1", "stage": "atl"}
    assert summary["stages"] == []


# --- the manifest ---------------------------------------------------------------------


def _entry(stage, attempt_n):
    return {"run_id": "r", "stage": stage, "attempt_n": attempt_n, "valid": True, "timestamp": "2026-01-01T00:00:00+00:00"}


def test_a_manifest_entry_gets_what_its_own_call_ran_with_and_learned():
    events = _call("atl", rounds=7, valid=False, attempt="attempt_1") + [_typed("atl", "fix it")]
    events += [_event("call_started", "atl"), _learned("atl", "Fix: z"), _event("call_completed", "atl", {"rounds": 2, "attempt": "attempt_2", "validation": {"valid": True}})]
    events += [_event("constraints_promoted", "atl", {"constraints": ["fix it"]})]

    first, second = annotate_manifest([_entry("atl", 1), _entry("atl", 2)], summarize(events))

    assert (first["rounds"], first["ran_with"], first["given"], first["learned"], first["mock"]) == (7, 0, [], [], False)
    assert (second["rounds"], second["ran_with"], second["promoted"]) == (2, 1, ["fix it"])
    assert _texts(second["given"]) == ["fix it"] and _texts(second["learned"]) == ["Fix: z"]
    assert second["stage"] == "atl" and second["attempt_n"] == 2 and second["valid"] is True


def test_a_manifest_entry_matches_by_stage_as_well_as_attempt_number():
    events = _call("psm", rounds=9, attempt="attempt_1") + _call("atl", rounds=2, attempt="attempt_1")

    psm, atl = annotate_manifest([_entry("psm", 1), _entry("atl", 1)], summarize(events))

    assert (psm["rounds"], atl["rounds"]) == (9, 2)


def test_a_manifest_entry_no_call_claims_is_left_as_it_is():
    entry = _entry("atl", 1)
    # A call from before the attempt's name was kept, and a failed call, claim none.
    events = _call("atl", rounds=3) + _failed("atl")

    assert annotate_manifest([entry], summarize(events)) == [entry]


# --- calls from before the attempt's name was kept ------------------------------------


def test_calls_that_never_said_which_attempt_they_were_are_numbered_from_the_manifest_in_order():
    events = _call("atl", rounds=7, valid=False) + _call("atl", rounds=5, valid=False) + _call("atl", rounds=1, valid=True)

    summary = number_calls(summarize(events), [_entry("atl", 1), _entry("atl", 2), _entry("atl", 3)])

    assert [(c["attempt_n"], c["rounds"]) for c in _stage(summary, "atl")["calls"]] == [(1, 7), (2, 5), (3, 1)]


def test_a_failed_call_between_two_unnamed_ones_takes_no_number_from_them():
    events = _call("atl", rounds=3) + _failed("atl") + _call("atl", rounds=1)

    calls = _stage(number_calls(summarize(events), [_entry("atl", 1), _entry("atl", 2)]), "atl")["calls"]

    assert [(c["outcome"], c["attempt_n"]) for c in calls] == [("done", 1), ("failed", None), ("done", 2)]


def test_the_numbers_a_call_reported_are_kept_and_the_unnamed_take_the_rest():
    # A run carried on after the number began to be kept: attempt 1 is old, 2 and 3 say so.
    events = _call("atl", rounds=4) + _call("atl", rounds=2, attempt="attempt_2") + _call("atl", rounds=1, attempt="attempt_3")

    calls = _stage(number_calls(summarize(events), [_entry("atl", 1), _entry("atl", 2), _entry("atl", 3)]), "atl")["calls"]

    assert [c["attempt_n"] for c in calls] == [1, 2, 3]


def test_a_skipped_attempt_number_is_not_filled_in_for_a_call_that_took_it():
    # The journal lacks attempt 2 (a crash left it unrecorded), and the calls are 1 and 3.
    events = _call("atl", rounds=2, attempt="attempt_1") + _call("atl", rounds=1, attempt="attempt_3")

    calls = _stage(number_calls(summarize(events), [_entry("atl", 1), _entry("atl", 3)]), "atl")["calls"]

    assert [c["attempt_n"] for c in calls] == [1, 3]


def test_nothing_is_guessed_when_the_calls_and_the_manifest_do_not_match_in_number():
    events = _call("atl", rounds=3) + _call("atl", rounds=1)

    summary = number_calls(summarize(events), [_entry("atl", 1)])

    assert [c["attempt_n"] for c in _stage(summary, "atl")["calls"]] == [None, None]


def test_the_journal_is_not_asked_about_stages_that_record_no_attempts():
    # docs and serialization never record an attempt, so there is nothing to number their calls from.
    run = pipeline.IntegrationRun()
    for stage in ("docs", "serialization"):
        run.record_event("call_started", stage, {})
        run.record_event("call_failed", stage, {"error": "x"})
        run.record_event("call_started", stage, {})
        run.record_event("call_completed", stage, {})

    with patch.object(journal_client, "get_manifest", side_effect=AssertionError("asked the journal")):
        evolved = evolution.of_run(run)

    assert [s["stage"] for s in evolved["stages"]] == ["docs", "serialization"]
    assert all(call["attempt_n"] is None for s in evolved["stages"] for call in s["calls"])


def test_a_mock_call_keeps_its_rounds_but_says_it_was_a_mock_so_a_reader_can_leave_it_out():
    events = _call("atl", rounds=7, valid=False) + _call("atl", rounds=1, valid=True, mock=True)

    calls = _stage(summarize(events), "atl")["calls"]

    assert [(c["rounds"], c["mock"]) for c in calls] == [(7, False), (1, True)]


def test_a_failed_check_that_pim_recorded_as_an_attempt_leaves_its_calls_unnumbered_not_guessed():
    # pim records an attempt for a result that failed its check, then raises, so it has
    # two attempts for these two calls, only one of which ended with a result.
    events = _failed("pim") + _call("pim")

    summary = number_calls(summarize(events), [_entry("pim", 1), _entry("pim", 2)])

    assert [c["attempt_n"] for c in _stage(summary, "pim")["calls"]] == [None, None]


def test_each_stage_is_numbered_from_its_own_attempts_only():
    events = _call("psm", rounds=1) + _call("atl", rounds=2) + _call("atl", rounds=1)

    summary = number_calls(summarize(events), [_entry("psm", 1), _entry("atl", 1), _entry("atl", 2)])

    assert [c["attempt_n"] for c in _stage(summary, "psm")["calls"]] == [1]
    assert [c["attempt_n"] for c in _stage(summary, "atl")["calls"]] == [1, 2]


def test_numbering_changes_nothing_in_the_summary_it_was_given():
    summary = summarize(_call("atl", rounds=3) + [_typed("atl", "later")] + _call("atl", rounds=1))
    before = copy.deepcopy(summary)

    numbered = number_calls(summary, [_entry("atl", 1), _entry("atl", 2)])

    assert summary == before
    assert numbered["stages"][0]["waiting"] == before["stages"][0]["waiting"]
    assert numbered["forked_from"] == before["forked_from"]
    assert [c["attempt_n"] for c in numbered["stages"][0]["calls"]] == [1, 2]


def test_an_entry_of_the_journal_that_is_malformed_is_skipped_instead_of_failing():
    events = _call("atl", rounds=3)

    summary = number_calls(summarize(events), [{"stage": "atl"}, {"stage": "atl", "attempt_n": "2"}, _entry("atl", 1)])

    assert [c["attempt_n"] for c in _stage(summary, "atl")["calls"]] == [1]


def test_the_manifest_of_an_old_run_is_annotated_by_order():
    events = _call("atl", rounds=7, valid=False) + [_typed("atl", "fix it")] + _call("atl", rounds=2, valid=True)
    entries = [_entry("atl", 1), _entry("atl", 2)]

    first, second = annotate_manifest(entries, number_calls(summarize(events), entries))

    assert (first["rounds"], second["rounds"]) == (7, 2)
    assert _texts(second["given"]) == ["fix it"]


# --- the events a real run records --------------------------------------------------------


def _run_atl_once(run, result):
    """One real ATL stage call through the run's own worker, so the events are
    the ones the pipeline records."""
    with patch.object(atl_agent_client, "run_atl", return_value=result):
        run.run_stage_async({"platform_description": "A GitLab CI platform"})
        run._last_thread.join(timeout=5)


def test_the_events_of_a_real_call_give_its_rounds_its_attempt_and_what_it_ran_with_and_learned():
    run = pipeline.IntegrationRun()
    _fast_forward_to(run, "atl")
    run.add_constraint("atl", "Use kebab-case rule names")
    _run_atl_once(
        run,
        {**_atl_generation_result(valid=False), "rounds": 3, "round_constraints": ["Fix: dangling reference"]},
    )

    [atl] = summarize(run.events)["stages"]

    assert atl["stage"] == "atl"
    [call] = atl["calls"]
    assert (call["attempt_n"], call["outcome"], call["rounds"], call["ran_with"]) == (1, "invalid", 3, 1)
    # The typed correction was given to it; the fix the validator's errors produced was learned by it.
    assert [(c["text"], c["learned"]) for c in call["given"]] == [("Use kebab-case rule names", False)]
    assert [(c["text"], c["learned"]) for c in call["learned"]] == [("Fix: dangling reference", True)]


def test_a_retry_after_a_correction_is_a_new_attempt_that_ran_with_what_the_first_learned():
    run = pipeline.IntegrationRun()
    _fast_forward_to(run, "atl")
    _run_atl_once(run, {**_atl_generation_result(valid=False), "rounds": 3, "round_constraints": ["Fix: a"]})
    run.add_constraint("atl", "Name the root rule Main")
    _run_atl_once(run, {**_atl_generation_result(), "rounds": 1})

    first, second = summarize(run.events)["stages"][0]["calls"]

    assert (first["attempt_n"], first["rounds"], first["outcome"], first["ran_with"]) == (1, 3, "invalid", 0)
    assert (second["attempt_n"], second["rounds"], second["outcome"], second["ran_with"]) == (2, 1, "valid", 2)
    assert _texts(second["given"]) == ["Name the root rule Main"]


def test_the_manifest_of_a_real_run_gets_the_rounds_of_each_attempt():
    run = pipeline.IntegrationRun()
    runs._runs[run.run_id] = run
    try:
        _fast_forward_to(run, "atl")
        _run_atl_once(run, {**_atl_generation_result(valid=False), "rounds": 3})
        _run_atl_once(run, {**_atl_generation_result(), "rounds": 1})

        entries = runs.annotated_manifest(run.run_id, journal_client.get_manifest(run.run_id))
    finally:
        runs._runs.pop(run.run_id, None)

    atl = [entry for entry in entries if entry["stage"] == "atl"]
    assert [(e["attempt_n"], e["rounds"]) for e in atl] == [(1, 3), (2, 1)]


def test_a_manifest_of_a_run_unknown_here_is_returned_as_the_journal_gave_it():
    entries = [_entry("atl", 1)]

    assert runs.annotated_manifest("not-a-run-here", entries) == entries


def _without_attempt_names(run):
    """What the calls recorded before the attempt's name was kept looked like."""
    for event in run.events:
        if isinstance(event.get("data"), dict):
            event["data"].pop("attempt", None)


def test_the_evolution_of_a_real_run_gets_its_attempt_numbers_from_the_journal_even_for_old_calls():
    run = pipeline.IntegrationRun()
    runs._runs[run.run_id] = run
    try:
        _fast_forward_to(run, "atl")
        _run_atl_once(run, {**_atl_generation_result(valid=False), "rounds": 3})
        _run_atl_once(run, {**_atl_generation_result(), "rounds": 1})
        _without_attempt_names(run)

        evolved = runs.get_run_evolution(run.run_id)
    finally:
        runs._runs.pop(run.run_id, None)

    atl = next(stage for stage in evolved["stages"] if stage["stage"] == "atl")
    assert [(c["attempt_n"], c["rounds"]) for c in atl["calls"]] == [(1, 3), (2, 1)]


def test_the_journal_is_not_asked_when_every_call_says_which_attempt_it_was():
    run = pipeline.IntegrationRun()
    runs._runs[run.run_id] = run
    try:
        _fast_forward_to(run, "atl")
        _run_atl_once(run, {**_atl_generation_result(), "rounds": 2})

        with patch.object(journal_client, "get_manifest", side_effect=AssertionError("asked the journal")):
            evolved = runs.get_run_evolution(run.run_id)
    finally:
        runs._runs.pop(run.run_id, None)

    assert [c["attempt_n"] for c in next(s for s in evolved["stages"] if s["stage"] == "atl")["calls"]] == [1]


def test_a_run_with_no_attempt_recorded_is_summarized_without_numbers_instead_of_failing():
    run = pipeline.IntegrationRun()
    for _ in range(2):
        run.record_event("call_started", "pim", {})
        run.record_event("call_completed", "pim", {})
    runs._runs[run.run_id] = run
    try:
        evolved = runs.get_run_evolution(run.run_id)
    finally:
        runs._runs.pop(run.run_id, None)

    assert [c["attempt_n"] for c in evolved["stages"][0]["calls"]] == [None, None]


def test_a_journal_that_fails_is_not_hidden_behind_a_page_with_missing_numbers():
    run = pipeline.IntegrationRun()
    for _ in range(2):
        run.record_event("call_started", "pim", {})
        run.record_event("call_completed", "pim", {})
    try:
        with patch.object(journal_client, "get_manifest", side_effect=AgentServiceError(500, "journal down")):
            evolution.of_run(run)
    except AgentServiceError as e:
        assert e.status_code == 500
    else:
        raise AssertionError("the journal's failure was swallowed")


def test_a_run_forked_at_a_stage_counts_none_of_the_earlier_runs_work():
    original = runs._default
    try:
        runs.reset_pipeline()
        source = runs._default
        source.last_context = {"platform_description": "TeamCity", "docs_output": "docs", "serialization_output": "s", "pim_output": "p", "psm_output": "m"}
        source.record_event("call_started", "psm", {})
        source.record_event("call_completed", "psm", {"rounds": 9})

        runs.fork_run(source.run_id, "atl")
        forked = runs._default
        summary = summarize(forked.events)

        assert summary["forked_from"] == {"run_id": source.run_id, "stage": "atl"}
        assert summary["stages"] == []
    finally:
        runs._default = original
        runs._runs.clear()
        runs._runs[original.run_id] = original
