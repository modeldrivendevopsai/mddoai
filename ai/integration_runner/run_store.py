"""Keeps each run on disk, so a restart loses none of them. A run is one journal,
runs/<run_id>/run.jsonl, next to the attempt files its stages write there.

Each save adds only what is new: the events recorded since the last save, and
the fields of the run's state that changed. A record is either
{"event": {...}} or {"state": {...changed fields...}}. The state record comes
before the events it goes with, so a crash between them can lose an event and
never leaves the state behind."""
import logging
from collections.abc import Callable

from integration_runner.pipeline import IntegrationRun
from integration_runner.stages import _validation
from journal import Journal

logger = logging.getLogger(__name__)

_FILE = "run.jsonl"

# What each run has on disk: how many events, and its state fields as last
# written. A run missing here is written whole on its next save. Changed only
# while that run's own on_change lock is held.
_on_disk: dict[str, tuple[int, dict]] = {}
# Runs whose last save failed. A disk that stays broken is logged once, not at
# every event.
_failing: set[str] = set()


def _journal(run_id: str) -> Journal:
    return Journal(_validation.RUNS_DIR / run_id / _FILE)


def save(run: IntegrationRun) -> None:
    """Adds to the run's journal what changed since the last save. A run nothing
    has happened on is not kept."""
    state = run.snapshot()
    events = state.pop("events")
    state.pop("run_id")
    if not events:
        return
    journal = _journal(run.run_id)
    try:
        if run.run_id not in _on_disk or not journal.path.exists():
            journal.rewrite([{"state": state}, *({"event": event} for event in events)])
        else:
            event_count, saved = _on_disk[run.run_id]
            changed = {name: value for name, value in state.items() if saved.get(name) != value}
            journal.append(
                ([{"state": changed}] if changed else []) + [{"event": event} for event in events[event_count:]]
            )
    except BaseException:
        # What reached the disk is unknown, so the next save writes the run whole.
        _on_disk.pop(run.run_id, None)
        raise
    _on_disk[run.run_id] = (len(events), state)


def save_quietly(run: IntegrationRun) -> None:
    """save() for a run that must go on if the disk fails: the failure is
    logged, not raised."""
    try:
        save(run)
    except Exception:
        if run.run_id not in _failing:
            _failing.add(run.run_id)
            logger.exception("could not keep run %s on disk", run.run_id)
    else:
        _failing.discard(run.run_id)


def _read(run_id: str, on_change: Callable[[IntegrationRun], None]) -> IntegrationRun:
    """The run in a journal, idle. Raises OSError or ValueError if there is none."""
    records, clean = _journal(run_id).read()
    state: dict = {"run_id": run_id, "events": []}
    for record in records:
        if "event" in record:
            state["events"].append(record["event"])
        elif "state" in record:
            fields = record["state"]
            if not isinstance(fields, dict) or {"run_id", "events"} & fields.keys():
                raise ValueError("a state record is not run state")
            state.update(fields)
        else:
            raise ValueError("a record is neither an event nor state")
    run = IntegrationRun.restore(state, on_change)
    if clean:
        saved = run.snapshot()
        events = saved.pop("events")
        saved.pop("run_id")
        _on_disk[run_id] = (len(events), saved)
    return run


def load_all(on_change: Callable[[IntegrationRun], None]) -> list[IntegrationRun]:
    """Every run the runs folder holds, idle. A folder whose journal is not a
    saved run is skipped with a log line."""
    runs_dir = _validation.RUNS_DIR
    if not runs_dir.is_dir():
        return []
    restored = []
    for folder in runs_dir.iterdir():
        if not (folder / _FILE).is_file():
            continue
        try:
            restored.append(_read(folder.name, on_change))
        except (OSError, ValueError) as e:
            logger.warning("skipping %s: %s", folder / _FILE, e)
    return restored
