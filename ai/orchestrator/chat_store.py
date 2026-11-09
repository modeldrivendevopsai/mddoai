"""Keeps each run's chat in the journal, so a restart repeats no narration. A
chat's turns are the history's events, and how many of integration_runner's raw
events the chat already shows, raw_seen, is its state. How a history is added to
is the journal client's job, and where it is kept is the journal's.

raw_seen is saved before the turns it covers. A crash between the two can lose a
turn from the chat, and never shows one twice."""
from chat_log import ChatLog
from clients.journal_client import HistoryClient


class ChatStore:
    def __init__(self):
        self._history = HistoryClient("chats", "chat")

    def save(self, chat: ChatLog) -> None:
        """Sends the journal what is new in the chat since the last save. A chat
        with nothing in it is not kept."""
        if chat.events or chat.raw_seen:
            self._history.save(chat.run_id, chat.events, {"raw_seen": chat.raw_seen})

    def save_quietly(self, chat: ChatLog) -> None:
        """save() for a chat that must go on if the journal fails: the failure
        is logged, not raised."""
        if chat.events or chat.raw_seen:
            self._history.save_quietly(chat.run_id, chat.events, {"raw_seen": chat.raw_seen})

    def load_all(self) -> dict[str, ChatLog]:
        """Every chat the journal holds. One that is not a saved chat is
        skipped with a log line."""
        chats = self._history.load_all(self._restore)
        return {chat.run_id: chat for chat in chats}

    def _restore(self, run_id: str, events: list[dict], state: dict) -> ChatLog:
        raw_seen = state.get("raw_seen", 0)
        if type(raw_seen) is not int or raw_seen < 0:
            raise ValueError("raw_seen is not a count")
        return ChatLog.restore(run_id, events, raw_seen, self)
