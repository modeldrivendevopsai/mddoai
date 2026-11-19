"""What a run took to get where it is, read from its own events: for each stage,
how many rounds of its generate-and-check loop its calls spent, and which
constraints were added along the way. A round is one try at the stage: the answer
is checked, and if it fails it is tried again with what was wrong. Fewer rounds
means the stage got it right sooner.

Only what this run itself did is counted. A run forked from another reuses that
run's approved stage outputs but not its events, so the work the earlier run did
on those stages does not appear here. The summary says the run was forked, and at
which stage, so a reader knows its numbers are only part of the story.
"""
from integration_runner.pipeline import STAGES


def _data_of(event: dict) -> dict:
    """The event's data, or an empty one for an event that has none."""
    data = event.get("data")
    return data if isinstance(data, dict) else {}


def _rounds_of(data: dict) -> int | None:
    """The rounds a completed call reports, or None for a stage that has no
    generate-and-check loop (it reports none)."""
    rounds = data.get("rounds")
    return rounds if isinstance(rounds, int) and not isinstance(rounds, bool) else None


def _forked_from(events: list[dict]) -> dict | None:
    """{"run_id", "stage"}: the run this one was forked from and the stage it was
    forked at, or None for a run that was not forked."""
    for event in events:
        if event.get("type") == "forked_from":
            return {"run_id": _data_of(event).get("source_run_id"), "stage": event.get("stage")}
    return None


def summarize(events: list[dict]) -> dict:
    """{"forked_from", "stages": [...], "total_rounds": int}. A stage is listed,
    in pipeline order, once it has a call that reported rounds or a constraint.
    Its entry is {"stage", "rounds" (summed over its completed calls that
    reported rounds), "constraints": [{"text", "source", "timestamp"}] in the
    order they were added}. A constraint's source is "validation" for a fix the
    validator's errors produced, or None for a correction a person typed."""
    by_stage = {stage: {"stage": stage, "rounds": 0, "has_rounds": False, "constraints": []} for stage in STAGES}
    for event in events:
        stage = event.get("stage")
        entry = by_stage.get(stage) if isinstance(stage, str) else None
        if entry is None:
            continue
        data = _data_of(event)
        if event.get("type") == "call_completed" and (rounds := _rounds_of(data)) is not None:
            entry["rounds"] += rounds
            entry["has_rounds"] = True
        elif event.get("type") == "constraint_added" and "constraint" in data:
            entry["constraints"].append(
                {"text": data["constraint"], "source": data.get("source"), "timestamp": event.get("timestamp")}
            )
    stages = [
        {"stage": e["stage"], "rounds": e["rounds"], "constraints": e["constraints"]}
        for e in by_stage.values()
        if e["has_rounds"] or e["constraints"]
    ]
    return {
        "forked_from": _forked_from(events),
        "stages": stages,
        "total_rounds": sum(entry["rounds"] for entry in stages),
    }
