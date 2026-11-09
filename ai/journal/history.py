"""Many histories kept on disk, one journal each. A history is a list of events
and some state fields, such as a run or a chat.

A save adds only what is new since the last save: the state fields that
changed, then the events after the ones already kept. The state goes before
the events it goes with, so a crash between them can lose an event and never
leaves the state behind. A record is {"event": {...}} or {"state": {...}}.

It knows nothing about what an event or a state field means. The caller says
where a key's journal lives, and turns what is read back into its own objects.
"""
import copy
import logging
import re
from collections.abc import Callable, Iterable
from pathlib import Path
from typing import TypeVar

from .file import Journal

logger = logging.getLogger(__name__)

# A key becomes a file or folder name, so only plain ones are kept.
_SAFE_KEY = re.compile(r"[A-Za-z0-9_-]{1,64}")

T = TypeVar("T")


def _check_key(key: str) -> None:
    if not isinstance(key, str) or not _SAFE_KEY.fullmatch(key):
        raise ValueError(f"not a key that can name a file: {key!r}")


class History:
    def __init__(self, path_for: Callable[[str], Path], what: str):
        """`path_for` says where a key's journal lives. `what` names the kind of
        thing kept, for log lines."""
        self._path_for = path_for
        self._what = what
        # What each key has on disk: how many events, and its state as last
        # written. A key missing here is written whole on its next save.
        self._on_disk: dict[str, tuple[int, dict]] = {}
        # Keys whose last save failed. A disk that stays broken is logged once,
        # not at every save.
        self._failing: set[str] = set()

    def save(self, key: str, events: list[dict], state: dict) -> None:
        """Adds to the key's journal what is new: the state fields that changed,
        then the events after the ones already kept. `events` only ever grows,
        and `state` has the same fields each time. A key not kept yet, or whose
        file has vanished, is written whole. The caller makes sure two saves of
        one key never overlap.

        If a save fails, what reached the disk is unknown, so the key is
        written whole at its next save."""
        _check_key(key)
        journal = Journal(self._path_for(key))
        try:
            kept = self._on_disk.get(key)
            if kept is None or not journal.path.exists() or len(events) < kept[0]:
                journal.rewrite([{"state": state}, *({"event": event} for event in events)])
            else:
                count, saved = kept
                changed = {name: value for name, value in state.items() if saved.get(name) != value}
                journal.append(([{"state": changed}] if changed else []) + [{"event": event} for event in events[count:]])
        except BaseException:
            self._on_disk.pop(key, None)
            raise
        # A copy, so a change the caller makes to its state later is still seen as a change.
        self._on_disk[key] = (len(events), copy.deepcopy(state))

    def save_quietly(self, key: str, events: list[dict], state: dict) -> None:
        """save() for a caller that must go on if the disk fails: the failure is
        logged, once until a save works again, and not raised."""
        try:
            self.save(key, events, state)
        except Exception:
            if key not in self._failing:
                self._failing.add(key)
                logger.exception("could not keep %s %s on disk", self._what, key)
        else:
            self._failing.discard(key)

    def load_all(self, keys: Iterable[str], build: Callable[[str, list[dict], dict], T]) -> list[T]:
        """`build(key, events, state)` for each key whose journal is kept. `build`
        raises ValueError for a journal that is not what the caller keeps. A key
        whose journal cannot be read or built is skipped with a log line."""
        built = []
        for key in keys:
            try:
                events, state, clean = self._read(key)
                item = build(key, events, state)
            except (OSError, ValueError) as e:
                logger.warning("skipping %s: %s", self._path_for(key), e)
                continue
            # A journal that ended in a cut-short line is written whole at its next save.
            if clean:
                self._on_disk[key] = (len(events), copy.deepcopy(state))
            built.append(item)
        return built

    def _read(self, key: str) -> tuple[list[dict], dict, bool]:
        _check_key(key)
        records, clean = Journal(self._path_for(key)).read()
        events, state = [], {}
        for record in records:
            if "event" in record:
                if not isinstance(record["event"], dict):
                    raise ValueError("an event is not an object")
                events.append(record["event"])
            elif "state" in record:
                if not isinstance(record["state"], dict):
                    raise ValueError("a state record is not an object")
                state.update(record["state"])
            else:
                raise ValueError("a record is neither an event nor state")
        return events, state, clean
