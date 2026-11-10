"""orchestrator's own chat log per run — the run's conversation transcript.

integration_runner produces raw pipeline facts (call_started, call_completed,
call_failed, review_approved, review_rejected, constraint_added, ...) and
knows nothing about chat or narration. This module is the one place that
turns those facts into a running conversation: every new raw event it
notices is mirrored into this run's transcript as a fact, and the newest of
them (leaving out the _NOT_NARRATED types below) is also given a short comment,
from the wired-in reactor (see set_reactor(), same pattern integration_runner's
own pipeline.py used before this split, just relocated), appended as its own
"message" turn. Older events in the same batch get no comment, so a run that
moved on while nobody watched costs one LLM call, not one per event. A human's chat message and its answer (see assistant.py)
go through begin_reply()/end_reply() below. All of them append to the exact
same list, in
real arrival order, so this is genuinely one merged, ordered chat transcript
per run, not separate streams stitched together after the fact.

An earlier design merged integration_runner's raw events with locally
generated narration by sorting on timestamp. Rejected: a narration's
timestamp comes from this process's clock, the raw event's from
integration_runner's — real clock skew between two containers can sort a
comment before the thing it's commenting on. Keeping one single, local,
append-only list per run side-steps that entirely, playing the exact role
IntegrationRun.events played before this split, just relocated to the
process that now needs it to hold the conversation.

Prompt-building (event_summarization.py's summarize_for_reaction()/
summarize_history()) is a separate concern from this transcript, imported
in, not defined here: this module's narration and assistant.py's answers
both build prompts the same way. begin_reply() hands back the history
already summarized, since it reads the turns under the same lock that
records the message.
"""
import logging
import re
import threading
import time
from typing import Callable

from clients import integration_runner_client
from event_summarization import summarize_for_reaction, summarize_history

logger = logging.getLogger(__name__)

# A weaker/faster model can ignore the "no tools = plain prose only"
# instruction (see system_prompt.py) and write a JSON-shaped fake tool call
# directly into its narration text instead - narration never passes tools=,
# so it has no real way to act, but nothing stops it writing text that looks
# like it did. Confirmed against a real run (cerebras/gpt-oss-120b): e.g.
# `We need to call the run_stage tool...{"tool": "run_stage", ...}`. A
# stricter prompt instruction reduces this but doesn't reliably eliminate it
# on a model that's already not following the existing one, so this is
# caught here too, not left to the prompt alone.
_LEAKED_TOOL_CALL_RE = re.compile(r'["\']tool["\']\s*:')

# Event types mirrored into the transcript as their own raw fact (so a human
# browsing it can still see every one) but never narrated individually: the
# atl/acceleo repair-and-promote loop can add a run of a dozen or more of
# these consecutively in one go (confirmed: 20 in a single real ATL repair
# round), and narrating each one its own LLM call wastes a real call per
# entry for what's really one bulk, automatic bookkeeping action, not a
# human-meaningful pipeline event - it also reads as the same near-identical
# comment repeated over and over, since there's nothing substantively
# different to say about constraint N versus constraint N+1.
_NOT_NARRATED = {"constraint_added"}


def _sanitize_narration_text(text: str) -> str:
    if _LEAKED_TOOL_CALL_RE.search(text):
        logger.warning("narration text looked like a leaked fake tool call, replaced: %r", text[:300])
        return "(a stage transition happened; narration for it was malformed and skipped)"
    return text


# Late-bound: this module and assistant.py would otherwise need to import
# each other (this module calls react_to_event() for narration, assistant.py
# calls into this module to append accept_message()'s own turns) — main.py
# wires the real one in explicitly at startup instead.
_reactor: Callable[[dict, list[dict] | None], dict] | None = None


def set_reactor(reactor: Callable[[dict, list[dict] | None], dict]) -> None:
    global _reactor
    _reactor = reactor


class ChatLog:
    """One run's chat transcript: the AI's comments on what
    integration_runner reported, plus the human's own messages and replies
    from accept_message(), one ordered, append-only list."""

    def __init__(self, run_id: str | None = None, store=None):
        self.run_id = run_id
        self.events: list[dict] = []
        self.raw_seen: int = 0   # how many of integration_runner's raw events are already reflected here
        # Reentrant, so add() can be called by a caller that already holds it
        # to read the chat and add to it in one step.
        self.lock = threading.RLock()
        self.narrating = False   # true while a background thread is writing new comments in
        # The one raw event waiting for its comment, if any. Only the newest event
        # is commented on, so a newer one replaces it and there is never a backlog.
        self.pending: dict | None = None
        # A chat_store.ChatStore, or None for a chat that is not kept on disk.
        self._store = store
        # The most recently started narration thread, if any. Not used by
        # the API itself (a real client polls GET /events instead), only
        # exposed so tests can deterministically join() it rather than
        # sleep/poll — same idiom IntegrationRun._last_thread already uses.
        self._last_thread: threading.Thread | None = None

    @classmethod
    def restore(cls, run_id: str, events: list[dict], raw_seen: int, store) -> "ChatLog":
        """A chat as it was kept."""
        chat = cls(run_id, store)
        chat.events = events
        chat.raw_seen = raw_seen
        return chat

    def add(self, events: list[dict], raw_seen: int | None = None) -> None:
        """Adds turns to the end of the chat, and keeps the chat on disk."""
        with self.lock:
            self.events.extend(events)
            if raw_seen is not None:
                self.raw_seen = raw_seen
            if self._store is not None:
                self._store.save_quietly(self)

    def append(self, event: dict) -> None:
        self.add([event])


class ReplyInProgressError(Exception):
    """A chat message arrived while the previous one is still being
    answered."""


# Held while a human's chat message is being answered. One for the whole
# process, not one per run: the answer's tools act on whichever run is
# current, and a start_pipeline call switches runs mid-answer, so a per-run
# flag would let a second message in on the new run while the first answer
# still acts on it.
_reply_slot = threading.Lock()


def replying() -> bool:
    return _reply_slot.locked()


def begin_reply(chat: ChatLog, message: dict) -> list[dict]:
    """Records a human's message as the newest turn of chat and marks a reply
    as in progress. Returns the turns before the message, summarized for a
    prompt. Raises ReplyInProgressError while another reply runs."""
    if not _reply_slot.acquire(blocking=False):
        raise ReplyInProgressError("The previous message is still being answered.")
    try:
        with chat.lock:
            history = summarize_history(chat.events)
            chat.add([message])
    except BaseException:
        _reply_slot.release()
        raise
    return history


def end_reply(chat: ChatLog, message: dict, outcome: list[dict], new_run_chat: ChatLog | None = None) -> None:
    """Records a reply's outcome (tool calls and the answer, or a failure) in
    chat, and, when the reply started a new run, the message and the outcome
    in new_run_chat too. Only then is the reply marked as done, so a poll
    never sees "not replying" without the answer, in either run."""
    try:
        chat.add(outcome)
        if new_run_chat is not None:
            new_run_chat.add([message, *outcome])
    finally:
        _reply_slot.release()


# One chat log per run, keyed by integration_runner's real run_id. They are
# kept on disk through the store set by load_saved(), and come back after a
# restart. A run with no saved chat gets a fresh one, all its events are
# mirrored at the first poll, and only the newest is commented on.
_chat_logs: dict[str, ChatLog] = {}
_store = None


def load_saved(store) -> None:
    """Keeps every chat from now on in `store` (a chat_store.ChatStore), and
    brings back the chats it holds. Runs once, at service start."""
    global _store
    _store = store
    for run_id, chat in store.load_all().items():
        _chat_logs.setdefault(run_id, chat)


def get_chat_log(run_id: str) -> ChatLog:
    """The real ChatLog for a run, creating one if there is none. Used by assistant.py to record a human's message and its answer
    without going through get_events()'s integration_runner round-trip
    first."""
    return _chat_logs.setdefault(run_id, ChatLog(run_id, _store))


def _narrate_in_background(chat: ChatLog, run_id: str, new_raw_events: list[dict]) -> None:
    """Gives the newest of new_raw_events a "message" comment, written by a
    background thread that is started if none is running. Older events get none:
    a run that moved on while nobody watched, or a burst of events, is commented
    on once, for where it is now, not once per event, which would cost one LLM
    call each. An event that arrives while a comment is being written replaces
    one that is still waiting, so the thread never has more than one to do next.
    Every raw event has already been appended to chat.events by the caller
    (get_events(), synchronously, before this thread even starts), so each is
    visible on the very next poll whether it is commented on or not. history for
    the event is looked up by identity (`is`, not position), not a captured
    index, since chat.events keeps growing (both from narration appended here
    and, concurrently, from a real accept_message() call) while this loop runs;
    an index captured up front would silently drift."""
    newest = next((event for event in reversed(new_raw_events) if event.get("type") not in _NOT_NARRATED), None)
    if newest is None:
        return

    def _run():
        try:
            while True:
                with chat.lock:
                    event, chat.pending = chat.pending, None
                    if event is None:
                        # Checked under the lock that sets pending, so an event
                        # set right after this finds narrating False and starts
                        # a thread of its own.
                        chat.narrating = False
                        return
                position = next(i for i, e in enumerate(chat.events) if e is event)
                history = summarize_history(chat.events[:position])
                try:
                    if _reactor is None:
                        raise RuntimeError("no reactor wired in, see set_reactor()")
                    reply = _reactor(summarize_for_reaction(event), history)
                    text = _sanitize_narration_text(reply.get("message") or "(no reply)")
                    model = reply.get("model")
                except Exception:
                    logger.exception("narration failed for run %s, event %s", run_id, event.get("type"))
                    text, model = "(narration unavailable)", None
                chat.append({
                    "type": "message", "stage": event.get("stage"),
                    "text": text, "model": model, "timestamp": time.time(),
                })
        except BaseException:
            with chat.lock:
                chat.narrating = False
            raise

    with chat.lock:
        chat.pending = newest
        if chat.narrating:
            return
        chat.narrating = True
    chat._last_thread = threading.Thread(target=_run, daemon=True)
    chat._last_thread.start()


def get_events(run_id: str | None = None, since_index: int = 0) -> dict:
    """The merged view GET /events actually returns: integration_runner's
    real current state (current_stage/busy/model/run_id) plus this run's
    full chat transcript (mirrored raw events, their narration, and
    chat message turns), sliced from since_index, plus whether a chat
    message is still being answered. Any newly-noticed raw
    event is mirrored into the transcript immediately, synchronously, right
    here — so it's visible on this very call — while narrating it (a real
    LLM call) happens in the background and never blocks this call; a
    comment on the very latest event may only show up on the next poll,
    same as before this split.

    Only the raw events this chat has not seen are fetched, not the run's
    whole history: a poll runs every second or two, and a run's events can
    hold whole stage outputs."""
    if run_id is None:
        # Which run is current, without its events. They come next, and only
        # the new ones.
        run_id = integration_runner_client.get_status()["run_id"]
    chat = get_chat_log(run_id)
    raw = dict(integration_runner_client.get_events(since_index=chat.raw_seen, run_id=run_id))
    # Where in the run's events the ones sent start, and how many it has in
    # all. A runner that sends every event says neither: they start at 0.
    offset = raw.pop("offset", 0)
    total = raw.pop("total", None)
    with chat.lock:
        if total is not None and total < chat.raw_seen:
            # The runner has fewer events than this chat has seen, because it
            # lost some in a crash. Carry on from what it has, so the events it
            # records next are not skipped.
            chat.add([], raw_seen=total)
        # raw_seen only grows, so a poll that fetched the same events as an
        # earlier one adds nothing twice.
        new_raw = raw["events"][max(chat.raw_seen - offset, 0):]
        chat.add(new_raw, raw_seen=chat.raw_seen + len(new_raw))
        # Read under chat.lock: end_reply() records the answer under this
        # lock before it frees the slot, so "not replying" never comes
        # without the answer.
        is_replying = replying()
        events = chat.events[since_index:]
    if new_raw:
        _narrate_in_background(chat, run_id, new_raw)
    return {**raw, "events": events, "replying": is_replying}
