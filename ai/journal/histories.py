"""Histories kept on disk: for each key, a list of events and some state fields,
such as a run or a chat. One journal file per key.

An append says where in the history its events start. That makes a retry safe:
events already kept are written over by the same ones, never added twice. The
state goes before the events it goes with, so a crash between them can lose an
event and never leaves the state behind. A record is {"event": {...}} or
{"state": {...}}.

It knows nothing about what an event or a state field means.
"""
import re
import threading
from collections.abc import Callable
from pathlib import Path

from .file import Journal

# A key becomes a file or folder name, so only plain ones are kept.
_KEY = re.compile(r"[A-Za-z0-9_-]{1,64}")


class UnknownHistory(KeyError):
    """No history is kept under that key."""


class BadHistory(ValueError):
    """A journal is on disk but is not a history."""


class HistoryGap(Exception):
    """An append starts after the end of the history kept. `count` is how many
    events are kept."""

    def __init__(self, count: int):
        self.count = count
        super().__init__(f"the history keeps {count} events, so events cannot start after that")


def check_key(key: str) -> None:
    if not isinstance(key, str) or not _KEY.fullmatch(key):
        raise UnknownHistory(f"not a key that can name a file: {key!r}")


class HistoryStore:
    def __init__(self, path_for: Callable[[str], Path], keys_in_folder: Callable[[], list[str]]):
        """`path_for` says where a key's journal lives, `keys_in_folder` which
        keys have one."""
        self._path_for = path_for
        self._keys_in_folder = keys_in_folder
        # How many events each key holds, as last written or read here. Saves
        # reading a journal to count it at every append.
        self._counts: dict[str, int] = {}
        # One writer at a time: this service runs as one process.
        self._lock = threading.Lock()

    def keys(self) -> list[str]:
        return sorted(self._keys_in_folder())

    def read(self, key: str) -> tuple[list[dict], dict]:
        """The key's events, and its state. Raises UnknownHistory when none is
        kept and BadHistory when the journal is not a history. A last line cut
        short by a crash is left out."""
        check_key(key)
        events, state, _ = self._read(key)
        return events, state

    def replace(self, key: str, state: dict, events: list[dict]) -> int:
        """Keeps exactly this history under the key. Returns the event count."""
        check_key(key)
        with self._lock:
            return self._write_whole(key, state, events)

    def append(self, key: str, start: int, state: dict, events: list[dict]) -> int:
        """Adds `events` at position `start`, and merges `state` into the state
        kept. Events already kept from `start` on are replaced. Returns the
        event count. Raises HistoryGap when `start` is past the end."""
        check_key(key)
        with self._lock:
            kept = self._kept(key)
            if kept is None:
                if start != 0:
                    raise HistoryGap(0)
                return self._write_whole(key, state, events)
            count, clean = kept
            if start > count:
                raise HistoryGap(count)
            if start < count or not clean:
                stored_events, stored_state, _ = self._read(key)
                return self._write_whole(key, {**stored_state, **state}, stored_events[:start] + events)
            Journal(self._path_for(key)).append(
                ([{"state": state}] if state else []) + [{"event": event} for event in events]
            )
            self._counts[key] = count + len(events)
            return self._counts[key]

    def _write_whole(self, key: str, state: dict, events: list[dict]) -> int:
        Journal(self._path_for(key)).rewrite([{"state": state}, *({"event": event} for event in events)])
        self._counts[key] = len(events)
        return len(events)

    def _kept(self, key: str) -> tuple[int, bool] | None:
        """How many events the key holds and whether its journal ends cleanly,
        or None when there is no journal."""
        path = self._path_for(key)
        if not path.is_file():
            return None
        if key in self._counts:
            return self._counts[key], True
        events, _, clean = self._read(key)
        # Only a journal that ends cleanly is remembered, so a cut-short one is
        # still known to be cut short at the next append, whatever came between.
        if clean:
            self._counts[key] = len(events)
        return len(events), clean

    def _read(self, key: str) -> tuple[list[dict], dict, bool]:
        try:
            records, clean = Journal(self._path_for(key)).read()
        except FileNotFoundError as e:
            raise UnknownHistory(key) from e
        except ValueError as e:
            raise BadHistory(str(e)) from e
        events, state = [], {}
        for record in records:
            if "event" in record:
                if not isinstance(record["event"], dict):
                    raise BadHistory("an event is not an object")
                events.append(record["event"])
            elif "state" in record:
                if not isinstance(record["state"], dict):
                    raise BadHistory("a state record is not an object")
                state.update(record["state"])
            else:
                raise BadHistory("a record is neither an event nor state")
        return events, state, clean
