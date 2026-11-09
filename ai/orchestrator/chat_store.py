"""Keeps each run's chat on disk, so a restart repeats no narration. A chat is
one journal, <chats folder>/<run_id>.jsonl. The chat's turns are the journal's
events, and how many of integration_runner's raw events the chat already shows,
raw_seen, is its state. What a journal is and how it is added to is
journal.History's job.

raw_seen is saved before the turns it covers. A crash between the two can lose
a turn from the chat, and never shows one twice."""
from pathlib import Path

from chat_log import ChatLog
from journal import History

_SUFFIX = ".jsonl"


class ChatStore:
    def __init__(self, directory: Path):
        self._directory = directory
        self._history = History(lambda run_id: directory / f"{run_id}{_SUFFIX}", "chat")

    def save(self, chat: ChatLog) -> None:
        """Adds to the chat's journal what is new since the last save. A chat
        with nothing in it is not kept."""
        if chat.events or chat.raw_seen:
            self._history.save(chat.run_id, chat.events, {"raw_seen": chat.raw_seen})

    def save_quietly(self, chat: ChatLog) -> None:
        """save() for a chat that must go on if the disk fails: the failure is
        logged, not raised."""
        if chat.events or chat.raw_seen:
            self._history.save_quietly(chat.run_id, chat.events, {"raw_seen": chat.raw_seen})

    def load_all(self) -> dict[str, ChatLog]:
        """Every chat in the folder. A file that is not a saved chat is skipped
        with a log line."""
        if not self._directory.is_dir():
            return {}
        run_ids = [path.name.removesuffix(_SUFFIX) for path in self._directory.glob(f"*{_SUFFIX}")]
        chats = self._history.load_all(run_ids, self._restore)
        return {chat.run_id: chat for chat in chats}

    def _restore(self, run_id: str, events: list[dict], state: dict) -> ChatLog:
        raw_seen = state.get("raw_seen", 0)
        if type(raw_seen) is not int or raw_seen < 0:
            raise ValueError("raw_seen is not a count")
        return ChatLog.restore(run_id, events, raw_seen, self)
