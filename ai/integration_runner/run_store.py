"""Keeps each run in the journal, so a restart loses none of them. A run's events
are the history's events, and the rest of what makes the run what it is, such as
the stage it is at, is its state. How a history is added to is the journal
client's job, and where it is kept is the journal's."""
from collections.abc import Callable

from clients.journal_client import HistoryClient
from integration_runner.pipeline import IntegrationRun


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
        self._history = HistoryClient("runs", "run")

    def save_quietly(self, run: IntegrationRun) -> None:
        """Sends the journal what changed in the run since the last save. A run
        nothing has happened on is not kept. The run goes on if the journal
        fails: the failure is logged, not raised."""
        events, state = _events_and_state(run)
        if events:
            self._history.save_quietly(run.run_id, events, state)

    def delete(self, run_id: str) -> bool:
        """Removes the run from the journal, with the files of its attempts. False
        when the journal held none (a run nothing happened on is never kept). The
        journal refuses a run that a platform was published from, which raises."""
        return self._history.delete(run_id)

    def load_all(self, on_change: Callable[[IntegrationRun], None]) -> list[IntegrationRun]:
        """Every run the journal holds, idle. One that is not a saved run is
        skipped with a log line."""
        return self._history.load_all(_restore(on_change))
