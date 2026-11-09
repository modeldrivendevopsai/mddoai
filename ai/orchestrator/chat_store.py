"""Keeps each run's chat on disk, so a restart repeats no narration. A chat is
one journal, <chats folder>/<run_id>.jsonl. Each save adds only what is new. A
record is {"event": {...}} or {"raw_seen": n}.

A save writes raw_seen before the events it covers. A crash between the two
can lose an event from the chat, and never shows one twice."""
import logging
import re
from pathlib import Path

from chat_log import ChatLog
from journal import Journal

logger = logging.getLogger(__name__)

_SUFFIX = ".jsonl"

# A run id becomes a file name, so only plain ones are kept.
_SAFE_RUN_ID = re.compile(r"[A-Za-z0-9_-]{1,64}")


class ChatStore:
    def __init__(self, directory: Path):
        self._directory = directory
        # What each chat has on disk: how many events, and its raw_seen. A chat
        # missing here is written whole on its next save. Changed only while
        # that chat's lock is held.
        self._on_disk: dict[str, tuple[int, int]] = {}
        # Chats whose last save failed. A disk that stays broken is logged
        # once, not at every poll.
        self._failing: set[str] = set()

    def _journal(self, run_id: str) -> Journal:
        return Journal(self._directory / f"{run_id}{_SUFFIX}")

    def save(self, chat: ChatLog) -> None:
        """Adds to the chat's journal what is new since the last save. A chat
        with nothing in it is not kept."""
        events, raw_seen = chat.events, chat.raw_seen
        if not events and raw_seen == 0:
            return
        if not _SAFE_RUN_ID.fullmatch(chat.run_id or ""):
            raise ValueError(f"not a run id that can name a file: {chat.run_id!r}")
        journal = self._journal(chat.run_id)
        try:
            if chat.run_id not in self._on_disk or not journal.path.exists():
                journal.rewrite([{"raw_seen": raw_seen}, *({"event": event} for event in events)])
            else:
                event_count, saved_raw_seen = self._on_disk[chat.run_id]
                journal.append(
                    ([{"raw_seen": raw_seen}] if raw_seen != saved_raw_seen else [])
                    + [{"event": event} for event in events[event_count:]]
                )
        except BaseException:
            # What reached the disk is unknown, so the next save writes the chat whole.
            self._on_disk.pop(chat.run_id, None)
            raise
        self._on_disk[chat.run_id] = (len(events), raw_seen)

    def save_quietly(self, chat: ChatLog) -> None:
        """save() for a chat that must go on if the disk fails: the failure is
        logged, not raised."""
        try:
            self.save(chat)
        except Exception:
            if chat.run_id not in self._failing:
                self._failing.add(chat.run_id)
                logger.exception("could not keep the chat of run %s on disk", chat.run_id)
        else:
            self._failing.discard(chat.run_id)

    def load_all(self) -> dict[str, ChatLog]:
        """Every chat in the folder. A file that is not a saved chat is skipped
        with a log line."""
        if not self._directory.is_dir():
            return {}
        chats = {}
        for path in self._directory.glob(f"*{_SUFFIX}"):
            run_id = path.name.removesuffix(_SUFFIX)
            try:
                records, clean = Journal(path).read()
                events, raw_seen = [], 0
                for record in records:
                    if isinstance(record.get("event"), dict):
                        events.append(record["event"])
                    elif type(record.get("raw_seen")) is int and record["raw_seen"] >= 0:
                        raw_seen = record["raw_seen"]
                    else:
                        raise ValueError("a record is neither an event nor raw_seen")
            except (OSError, ValueError) as e:
                logger.warning("skipping %s: %s", path, e)
                continue
            chats[run_id] = ChatLog.restore(run_id, events, raw_seen, self)
            if clean:
                self._on_disk[run_id] = (len(events), raw_seen)
        return chats
