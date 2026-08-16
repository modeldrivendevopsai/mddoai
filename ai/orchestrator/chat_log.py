"""orchestrator's own chat log per run — the run's conversation transcript.

integration_runner produces raw pipeline facts (call_started, call_completed,
call_failed, review_approved, review_rejected) and knows nothing about chat
or narration. This module is the one place that turns those facts into a
running conversation: for each new raw event it notices, it mirrors the raw
fact itself into this run's transcript, then asks the wired-in reactor (see
set_reactor(), same pattern integration_runner's own pipeline.py used before
this split, just relocated) for a short comment and appends that as its own
"message" turn, the same way send_message() (a human's own chat turn, see
assistant.py) appends directly. All three append to the exact same list, in
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
in, not defined here: send_message() (assistant.py) needs the exact same
truncation this module's own narration does, and neither module owns that
concern more than the other.
"""
import logging
import threading
import time
from typing import Callable

from clients import integration_runner_client
from event_summarization import summarize_for_reaction, summarize_history

logger = logging.getLogger(__name__)


# Late-bound, same reasoning integration_runner/pipeline.py's old
# set_reactor() had: this module and assistant.py would otherwise need to
# import each other (this module calls react_to_event() for narration,
# assistant.py calls into this module to append send_message()'s own
# turns) — main.py wires the real one in explicitly at startup instead.
_reactor: Callable[[dict, list[dict] | None], dict] | None = None


def set_reactor(reactor: Callable[[dict, list[dict] | None], dict]) -> None:
    global _reactor
    _reactor = reactor


class ChatLog:
    """One run's chat transcript: the AI's comments on what
    integration_runner reported, plus the human's own messages and replies
    from send_message(), one ordered, append-only list."""

    def __init__(self):
        self.events: list[dict] = []
        self.raw_seen: int = 0   # how many of integration_runner's raw events are already reflected here
        self.lock = threading.Lock()
        self.narrating = False   # true while a background thread is writing a new comment in
        # The most recently started narration thread, if any. Not used by
        # the API itself (a real client polls GET /events instead), only
        # exposed so tests can deterministically join() it rather than
        # sleep/poll — same idiom IntegrationRun._last_thread already uses.
        self._last_thread: threading.Thread | None = None

    def append(self, event: dict) -> None:
        with self.lock:
            self.events.append(event)


# One chat log per run, keyed by integration_runner's real run_id, kept for
# the life of this process. Lost on an orchestrator restart even though
# integration_runner itself keeps a run's raw history across one — a new,
# real failure mode this split introduces, not present when everything ran
# in one process. _chat_logs.setdefault() below handles a run this process
# has never seen locally: worst case is a burst of re-narration on first
# contact, not a crash — the same MVP, in-memory-only tradeoff
# integration_runner's own _runs/_default already accepts.
_chat_logs: dict[str, ChatLog] = {}


def get_chat_log(run_id: str) -> ChatLog:
    """The real ChatLog for a run, creating one if this process hasn't seen
    it yet. Used by send_message() to append the human's own turn and its
    reply directly, without going through get_events()'s integration_runner
    round-trip first."""
    return _chat_logs.setdefault(run_id, ChatLog())


def _narrate_in_background(chat: ChatLog, run_id: str, new_raw_events: list[dict]) -> None:
    """Generates and appends a "message" comment for each of new_raw_events,
    one at a time, in order. Each raw event has already been appended to
    chat.events by the caller (get_events(), synchronously, before this
    background thread even starts) — that's what makes the raw fact visible
    on the very next poll even if its comment takes a moment longer. history
    for each event is looked up by identity (`is`, not position), not a
    captured index, since chat.events keeps growing (both from narration
    appended here and, concurrently, from a real send_message() call) while
    this loop runs; an index captured up front would silently drift."""
    def _run():
        try:
            for event in new_raw_events:
                position = next(i for i, e in enumerate(chat.events) if e is event)
                history = summarize_history(chat.events[:position])
                try:
                    if _reactor is None:
                        raise RuntimeError("no reactor wired in, see set_reactor()")
                    reply = _reactor(summarize_for_reaction(event), history)
                    text = reply.get("message") or "(no reply)"
                    model = reply.get("model")
                except Exception:
                    logger.exception("narration failed for run %s, event %s", run_id, event.get("type"))
                    text, model = "(narration unavailable)", None
                chat.append({
                    "type": "message", "stage": event.get("stage"),
                    "text": text, "model": model, "timestamp": time.time(),
                })
        finally:
            chat.narrating = False

    chat.narrating = True
    chat._last_thread = threading.Thread(target=_run, daemon=True)
    chat._last_thread.start()


def get_events(run_id: str | None = None, since_index: int = 0) -> dict:
    """The merged view GET /events actually returns: integration_runner's
    real current state (current_stage/busy/model/run_id) plus this run's
    full chat transcript (mirrored raw events, their narration, and
    send_message() turns), sliced from since_index. Any newly-noticed raw
    event is mirrored into the transcript immediately, synchronously, right
    here — so it's visible on this very call — while narrating it (a real
    LLM call) happens in the background and never blocks this call; a
    comment on the very latest event may only show up on the next poll,
    same as before this split."""
    raw = integration_runner_client.get_events(run_id=run_id)
    chat = get_chat_log(raw["run_id"])
    with chat.lock:
        new_raw = raw["events"][chat.raw_seen:]
        chat.raw_seen = len(raw["events"])
        chat.events.extend(new_raw)
    if new_raw and not chat.narrating:
        _narrate_in_background(chat, raw["run_id"], new_raw)
    return {**raw, "events": chat.events[since_index:]}
