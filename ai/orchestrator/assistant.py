"""The orchestrator's one reply mechanism: react_to_event() (narration,
called automatically by chat_log.py for every new raw event it notices from
integration_runner, via the set_reactor() hook, wired in explicitly by
main.py at startup, not as a side effect of importing this file) and
accept_message() (a human's free-form chat message, the tools-enabled path
through the SAME react_to_event()). Giving it tools is the only difference
between narrating and acting, same function, same prompt, same dispatch.

Composes clients/integration_runner_client.py (the pipeline's real state,
reached over HTTP, never imported as a package) with chat_log.py (this
run's chat transcript), event_summarization.py (how a prompt is built from
event history, shared with chat_log.py's own narration), system_prompt.py
(the system prompt template), tools/ (MDDOAI's declared abilities), and
tool_calling.py (the generic engine that turns a prompt + tools into a
reply). None of those know about this file.
"""
import functools
import logging
import time
from collections.abc import Callable

import chat_log
import system_prompt
import tool_calling
import tools
from clients import ai_layer_client, integration_runner_client
from event_summarization import summarize_for_reaction, summarize_history

logger = logging.getLogger(__name__)


def react_to_event(event: dict, history: list[dict] | None = None, use_tools: bool = False) -> dict:
    status = integration_runner_client.get_status()
    live_stage = status["current_stage"]
    # The prompt's "current pending stage" line describes THIS event, not
    # necessarily whatever's live by the time this actually runs: narration
    # happens on a background thread (see chat_log.py), and a fast
    # placeholder stage can advance past the event being narrated before
    # that thread's LLM call even starts — event["stage"] (set by both real
    # callers: chat_log.py's narration and accept_message()'s own synthetic
    # user_message event, below) is what this reaction is actually about,
    # a live re-fetch is not. Tool availability below still needs the live
    # stage, not the event's: a tool call decided here acts on the pipeline
    # as it is right now, not as it was when the event was recorded.
    event_stage = event.get("stage")
    stage_description = event_stage or live_stage or "none, the pipeline hasn't been started"
    prompt_text = system_prompt.get_system_prompt_template().format(current_stage=stage_description)
    available_tools = tool_calling.load_tools(live_stage, tools.get_tools()) if use_tools else None
    return tool_calling.build_reply(
        ai_layer_client.chat, prompt_text, event, history, available_tools, model=status["model"]
    )


class ReplyInProgressError(Exception):
    """A chat message arrived while this run's previous one is still being
    answered."""


def accept_message(user_message: str) -> Callable[[], dict]:
    """Records a human's free-form chat message as this run's newest chat
    turn and returns the step that answers it. The caller runs that step
    later (POST /message runs it after its response has gone out), because
    an answer can take minutes: the model may call several tools, and one
    of them fetches a whole documentation page. The reply, each dispatched
    tool call and any failure all land in the chat log, where the UI's
    GET /events poll picks them up. One message is answered at a time per
    run, so a second one is refused until the first is done."""
    status = integration_runner_client.get_status()
    stage = status["current_stage"]
    chat = chat_log.get_chat_log(status["run_id"])
    event = {
        "type": "user_message",
        "stage": stage,
        "data": {"message": user_message},
        "timestamp": time.time(),
    }
    with chat.lock:
        if chat.replying:
            raise ReplyInProgressError("The previous message is still being answered.")
        # Summarized before either is used to build a prompt (see
        # event_summarization.summarize_for_reaction()'s own docstring for why
        # history specifically can't skip this): chat_log's own narration
        # already does the same for every other event, this is the other
        # place that builds a prompt from real events, so it needs the same
        # truncation, not just the current event.
        history = summarize_history(chat.events)
        # Last, so nothing that can fail leaves the run marked as replying.
        chat.events.append(event)
        chat.replying = True
    return functools.partial(_answer, chat, status["run_id"], stage, event, history)


def _answer(chat: chat_log.ChatLog, run_id: str, stage: str | None, event: dict, history: list[dict]) -> dict:
    """Answers a message accept_message() recorded: react_to_event with tools
    enabled, the same function, prompt and dispatch as narration. Any tool
    that starts a stage running (run_stage, rerun_stage, stage_result-
    approve, start_pipeline) does so via integration_runner's own REST
    endpoints, the same ones a direct REST call would use, so it returns once
    the stage has started, not finished. Returns the reply, or {"error": ...}
    when answering failed."""
    outcome: list[dict] = []
    try:
        reply = react_to_event(summarize_for_reaction(event), history, use_tools=True)
        outcome = _reply_events(stage, reply)
    except Exception as e:
        logger.exception("chat reply failed for run %s", run_id)
        reply = {"error": str(e)}
        outcome = [{"type": "message_failed", "stage": stage, "data": {"error": str(e)}, "timestamp": time.time()}]
    finally:
        # Recorded and released in one step, so a poll never sees "not
        # replying" without the reply, and the run is never left replying.
        with chat.lock:
            chat.events.extend(outcome)
            chat.replying = False
    _copy_to_new_run(run_id, event, outcome)
    return reply


def _copy_to_new_run(run_id: str, event: dict, outcome: list[dict]) -> None:
    """A message that started a new run (the start_pipeline tool) is also
    recorded at the start of that run's chat, which is the one the UI now
    shows, so the person sees what they asked and what was done."""
    try:
        current = integration_runner_client.get_status()["run_id"]
    except Exception:
        logger.exception("could not check whether the chat answer changed the run")
        return
    if current != run_id:
        chat_log.get_chat_log(current).append_all([event, *outcome])


def _reply_events(stage: str | None, reply: dict) -> list[dict]:
    # Every dispatched tool call is a real, structured fact about this run —
    # the same "every real action becomes a real, persisted event" rule
    # integration_runner's own call_started/review_approved/constraint_added
    # events already follow. Recorded here, not there: these tool calls (run_stage,
    # stage_result, ...) already produce their OWN real integration_runner
    # events once dispatched (call_started, review_approved, etc.) — this is
    # the durable record of the DISPATCH decision itself (which tool, what
    # arguments, what immediate result), which no response carries.
    events = [
        {
            "type": "tool_called", "stage": stage,
            "data": {"tool": step["tool"], "arguments": step["arguments"], "result": step["result"]},
            "timestamp": time.time(),
        }
        for step in reply.get("steps", [])
    ]
    text = reply.get("message") or f"Called {reply.get('tool_called')}."
    events.append({"type": "message", "stage": stage, "text": text, "model": reply.get("model"), "timestamp": time.time()})
    return events
