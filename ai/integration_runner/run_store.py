"""Keeps each run on disk, so a restart loses none of them. A run is one journal,
runs/<run_id>/run.jsonl, next to the attempt files its stages write there. The
run's events are the journal's events, and the rest of what makes the run what
it is, such as the stage it is at, is its state. What a journal is and how it is
added to is journal.History's job."""
from collections.abc import Callable

from integration_runner.pipeline import IntegrationRun
from integration_runner.stages import _validation
from journal import History

_FILE = "run.jsonl"


def _path(run_id: str):
    return _validation.RUNS_DIR / run_id / _FILE


def _events_and_state(run: IntegrationRun) -> tuple[list[dict], dict]:
    state = run.snapshot()
    events = state.pop("events")
    state.pop("run_id")
    return events, state


def _restore(on_change: Callable[[IntegrationRun], None]):
    def build(run_id: str, events: list[dict], state: dict) -> IntegrationRun:
        if {"run_id", "events"} & state.keys():
            raise ValueError("a state record is not run state")
        return IntegrationRun.restore({**state, "run_id": run_id, "events": events}, on_change)

    return build


class RunStore:
    def __init__(self):
        self._history = History(_path, "run")

    def save(self, run: IntegrationRun) -> None:
        """Adds to the run's journal what changed since the last save. A run
        nothing has happened on is not kept."""
        events, state = _events_and_state(run)
        if events:
            self._history.save(run.run_id, events, state)

    def save_quietly(self, run: IntegrationRun) -> None:
        """save() for a run that must go on if the disk fails: the failure is
        logged, not raised."""
        events, state = _events_and_state(run)
        if events:
            self._history.save_quietly(run.run_id, events, state)

    def load_all(self, on_change: Callable[[IntegrationRun], None]) -> list[IntegrationRun]:
        """Every run the runs folder holds, idle. A folder whose journal is not a
        saved run is skipped with a log line."""
        runs_dir = _validation.RUNS_DIR
        if not runs_dir.is_dir():
            return []
        run_ids = [folder.name for folder in runs_dir.iterdir() if (folder / _FILE).is_file()]
        return self._history.load_all(run_ids, _restore(on_change))
