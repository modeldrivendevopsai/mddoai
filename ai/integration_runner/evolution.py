"""What a run took to get where it is, read from its own events: for each stage,
every call it made, oldest first, with the rounds the call spent and the
corrections it ran with and learned.

Four words are used here, and they are different things:
  - A round is one try by the model inside a call: the answer is checked, and if
    it fails it is tried again with what was wrong. Only the stages that run that
    loop report rounds.
  - A call is one run of a stage, from its start to its end. A person's correction
    followed by Retry starts a new call, whose rounds count from one again.
  - An attempt is what the journal records of a call that produced a result, and
    its number is `attempt_n`. A call that stopped on an error before it had a
    result records none, though pim and generation record one for a result that
    failed their check.
  - A correction is a constraint added to a stage: typed by a person (or the
    chat), or learned from the validator's errors, which the run saves by itself
    while a call is running. Either way it is in the prompt of every later call.

A call is listed with the corrections of its own period. `ran_with` is how many
the stage had when the call began, which is what explains how the call went.
`given` are those added since the previous call ended, which is where a person's
correction shows. `learned` are those added while the call ran. A person adds
theirs between calls, so a correction recorded during a call is read as learned
by it, whether or not it says where it came from, which older events do not.

Only the stages with an evolution to show are listed: those that ran the
generate-and-check loop, were given a correction, needed more than one call, or
ended in something other than a clean result. A stage that ran once and was
fine has nothing to show.

Only what this run itself did is counted. A run forked from another reuses that
run's approved stage outputs but not its events, so the work the earlier run did
on those stages does not appear here. The summary says the run was forked, and at
which stage, so a reader knows its numbers are only part of the story.
"""
import re

from clients import journal_client
from clients.agent_service_errors import AgentServiceError
from integration_runner.pipeline import FIX_SOURCE_VALIDATION, STAGES

_ATTEMPT_NAME = re.compile(r"attempt_([1-9][0-9]*)")

# The stages whose agents record an attempt for each result. The others (docs,
# serialization) never do, so the journal has nothing to number their calls from.
ATTEMPT_STAGES = ("pim", "psm", "atl", "acceleo", "generation")

# What a call carries that a recorded attempt of the same stage shows beside its
# own facts.
_ATTEMPT_FIELDS = ("rounds", "ran_with", "given", "learned", "promoted", "mock")


def _data_of(event: dict) -> dict:
    """The event's data, or an empty one for an event that has none."""
    data = event.get("data")
    return data if isinstance(data, dict) else {}


def _rounds_of(data: dict) -> int | None:
    """The rounds a completed call reports, or None for a stage that has no
    generate-and-check loop (it reports none)."""
    rounds = data.get("rounds")
    return rounds if isinstance(rounds, int) and not isinstance(rounds, bool) else None


def _attempt_number(data: dict) -> int | None:
    """The number of the attempt a call was recorded as, or None when the call did
    not say (a stage that does not report it, a call from before the attempt's name
    was kept, or a call that stopped on an error before it had an attempt)."""
    name = data.get("attempt")
    match = _ATTEMPT_NAME.fullmatch(name) if isinstance(name, str) else None
    return int(match.group(1)) if match else None


def _outcome(failed: bool, data: dict) -> str:
    """"failed" for a call that raised, "valid" or "invalid" for a result the
    validator judged, and "done" for a result nothing checked."""
    if failed:
        return "failed"
    validation = data.get("validation")
    valid = validation.get("valid") if isinstance(validation, dict) else None
    if isinstance(valid, bool):
        return "valid" if valid else "invalid"
    return "done"


def _forked_from(events: list[dict]) -> dict | None:
    """{"run_id", "stage"}: the run this one was forked from and the stage it was
    forked at, or None for a run that was not forked."""
    for event in events:
        if event.get("type") == "forked_from":
            return {"run_id": _data_of(event).get("source_run_id"), "stage": event.get("stage")}
    return None


def _new_stage() -> dict:
    # `open` is the call that has started and not ended. `waiting` holds the
    # corrections added since the last call ended, which the next call starts
    # with. `count` is every correction the stage has had so far.
    return {"calls": [], "waiting": [], "open": None, "count": 0}


def _start_call(state: dict, data: dict) -> None:
    unfinished = state["open"]
    # A call that started and never ended hands what it had to the next one.
    carried = unfinished["given"] + unfinished["learned"] if unfinished else []
    state["open"] = {
        "mock": bool(data.get("mock")),
        "ran_with": state["count"],
        "given": carried + state["waiting"],
        "learned": [],
    }
    state["waiting"] = []


def _end_call(state: dict, failed: bool, data: dict) -> None:
    ended = state["open"]
    if ended is None:
        # An end with no start in the events: what was waiting is what it was given.
        ended = {"mock": False, "ran_with": state["count"], "given": state["waiting"], "learned": []}
        state["waiting"] = []
    state["open"] = None
    state["calls"].append(
        {
            "attempt_n": _attempt_number(data),
            "outcome": _outcome(failed, data),
            "rounds": None if failed else _rounds_of(data),
            "mock": ended["mock"],
            "ran_with": ended["ran_with"],
            "given": ended["given"],
            "learned": ended["learned"],
            "promoted": [],
        }
    )


def _add_correction(state: dict, data: dict) -> None:
    text = data.get("constraint")
    if not isinstance(text, str):
        return
    state["count"] += 1
    if state["open"] is not None:
        state["open"]["learned"].append({"text": text, "learned": True})
    else:
        state["waiting"].append({"text": text, "learned": data.get("source") == FIX_SOURCE_VALIDATION})


def _promote(state: dict, data: dict) -> None:
    """Corrections a person made permanent, which only a result they had seen
    allows, so they go with the stage's latest call that ended with one."""
    promoted = data.get("constraints")
    results = [call for call in state["calls"] if call["outcome"] != "failed"]
    if results and isinstance(promoted, list):
        results[-1]["promoted"].extend(text for text in promoted if isinstance(text, str))


def summarize(events: list[dict]) -> dict:
    """{"forked_from", "stages": [...]}. A stage is listed, in pipeline order, when
    it reported rounds, was given a correction, made more than one call, or did not
    end cleanly. Its entry is:
      "stage"
      "calls": oldest first, each {"attempt_n", "outcome", "rounds", "mock",
        "ran_with", "given", "learned", "promoted"}. A mock call called no model, so
        its rounds say nothing about how a model did: a reader leaves them out of
        any comparison.
      "waiting": the corrections not yet tried, in the same shape as those in a call
    A correction is {"text", "learned"}, "learned" true for a fix the validator's
    errors produced and false for one a person typed. "promoted" is the texts a
    person made permanent after the call."""
    states = {stage: _new_stage() for stage in STAGES}
    for event in events:
        stage = event.get("stage")
        state = states.get(stage) if isinstance(stage, str) else None
        if state is None:
            continue
        kind, data = event.get("type"), _data_of(event)
        if kind == "call_started":
            _start_call(state, data)
        elif kind in ("call_completed", "call_failed"):
            _end_call(state, kind == "call_failed", data)
        elif kind == "constraint_added":
            _add_correction(state, data)
        elif kind == "constraints_promoted":
            _promote(state, data)

    stages = []
    for stage in STAGES:
        state = states[stage]
        has_rounds = any(call["rounds"] is not None for call in state["calls"])
        clean_once = len(state["calls"]) < 2 and all(call["outcome"] in ("valid", "done") for call in state["calls"])
        if not has_rounds and state["count"] == 0 and clean_once:
            continue
        # A call still running holds the corrections it started with and has learned, which are not lost.
        running = state["open"]["given"] + state["open"]["learned"] if state["open"] else []
        stages.append(
            {"stage": stage, "calls": state["calls"], "waiting": running + state["waiting"]}
        )
    return {"forked_from": _forked_from(events), "stages": stages}


def _attempt_numbers(entries: list[dict], stage: str) -> set[int]:
    """The attempt numbers the journal holds for the stage."""
    return {
        entry["attempt_n"]
        for entry in entries
        if entry.get("stage") == stage and isinstance(entry.get("attempt_n"), int)
    }


def _unnumbered(stage: dict) -> list[int]:
    """The positions of the stage's calls that ended with a result but did not say
    which attempt they were. None for a stage that records no attempts."""
    if stage["stage"] not in ATTEMPT_STAGES:
        return []
    return [
        index
        for index, call in enumerate(stage["calls"])
        if call["outcome"] != "failed" and call["attempt_n"] is None
    ]


def number_calls(summary: dict, entries: list[dict]) -> dict:
    """The summary with the attempt number filled in for the calls that did not say
    which attempt they were, which every call from before the number was kept in
    the result is. The journal's manifest entries for a stage that are not claimed
    by a call that did say are, in order, the attempts of the stage's unnamed calls
    that ended with a result, and they are matched in order when there are as
    many of each. When there are not (an attempt a call never reported, or a call
    that reported one the journal lacks) nothing is guessed, and the calls stay
    unnumbered. The summary it was given is not changed."""
    numbered = []
    for stage in summary["stages"]:
        claimed = {call["attempt_n"] for call in stage["calls"] if call["attempt_n"] is not None}
        free = sorted(_attempt_numbers(entries, stage["stage"]) - claimed)
        unnamed = _unnumbered(stage)
        calls = [dict(call) for call in stage["calls"]]
        if unnamed and len(unnamed) == len(free):
            for index, number in zip(unnamed, free):
                calls[index]["attempt_n"] = number
        numbered.append({**stage, "calls": calls})
    return {**summary, "stages": numbered}


def annotate_manifest(entries: list[dict], summary: dict) -> list[dict]:
    """The journal's manifest entries, each with what the run's events say about
    that attempt (the fields in _ATTEMPT_FIELDS), found by stage and attempt
    number. An entry no call claims, such as one from before a call kept its
    attempt's name, is returned as it is."""
    calls = {
        (stage["stage"], call["attempt_n"]): call
        for stage in summary["stages"]
        for call in stage["calls"]
        if call["attempt_n"] is not None
    }
    annotated = []
    for entry in entries:
        call = calls.get((entry.get("stage"), entry.get("attempt_n")))
        annotated.append({**entry, **{field: call[field] for field in _ATTEMPT_FIELDS}} if call else entry)
    return annotated


def _manifest_of(run_id: str) -> list[dict]:
    """The journal's manifest entries for the run: none when the run has recorded
    no attempt yet, which is the journal's 404. Any other failure is raised, so a
    page never shows numbers that are missing only because the journal was down."""
    try:
        return journal_client.get_manifest(run_id)
    except AgentServiceError as e:
        if e.status_code == 404:
            return []
        raise


def of_run(run) -> dict:
    """The run's summary, with attempt numbers from the journal's manifest for the
    calls that did not say theirs. The journal is asked only when there are such
    calls."""
    # A copy, so a stage still running cannot add to the list while it is read.
    summary = summarize(list(run.events))
    if any(_unnumbered(stage) for stage in summary["stages"]):
        summary = number_calls(summary, _manifest_of(run.run_id))
    return {"run_id": run.run_id, **summary}


def annotated_manifest(run, entries: list[dict]) -> list[dict]:
    """The journal's manifest entries for the run, each with what the run's events
    say about that attempt."""
    summary = number_calls(summarize(list(run.events)), entries)
    return annotate_manifest(entries, summary)
