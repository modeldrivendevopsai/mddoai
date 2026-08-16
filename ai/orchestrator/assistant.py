"""The orchestrator's one reply mechanism: react_to_event() (narration,
called automatically by chat_log.py for every new raw event it notices from
integration_runner, via the set_reactor() hook, wired in explicitly by
main.py at startup, not as a side effect of importing this file) and
send_message() (a human's free-form chat message, the tools-enabled path
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
import time

import chat_log
import system_prompt
import tool_calling
import tools
from clients import ai_layer_client, integration_runner_client
from event_summarization import summarize_for_reaction, summarize_history


def react_to_event(event: dict, history: list[dict] | None = None, use_tools: bool = False) -> dict:
    status = integration_runner_client.get_status()
    live_stage = status["current_stage"]
    # The prompt's "current pending stage" line describes THIS event, not
    # necessarily whatever's live by the time this actually runs: narration
    # happens on a background thread (see chat_log.py), and a fast
    # placeholder stage can advance past the event being narrated before
    # that thread's LLM call even starts — event["stage"] (set by both real
    # callers: chat_log.py's narration and send_message()'s own synthetic
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


def send_message(user_message: str) -> dict:
    """A human's free-form chat message -> react_to_event with tools
    enabled, recording both the message and the reply as this run's own
    chat turns, the same as chat_log's narration does automatically for
    real pipeline events. use_tools=True is the only difference from
    narrating: same function, same prompt, same dispatch. Any tool that
    starts a stage running (run_stage, rerun_stage, stage_result-approve,
    start_pipeline) does so via integration_runner's own REST endpoints,
    the same ones a direct REST call would use, so it returns immediately
    instead of blocking this request for the stage's full duration."""
    status = integration_runner_client.get_status()
    run_id, stage = status["run_id"], status["current_stage"]
    chat = chat_log.get_chat_log(run_id)
    event = {
        "type": "user_message",
        "stage": stage,
        "data": {"message": user_message},
        "timestamp": time.time(),
    }
    # Summarized before either is used to build a prompt (see
    # event_summarization.summarize_for_reaction()'s own docstring for why
    # history specifically can't skip this): chat_log's own narration
    # already does the same for every other event, send_message() is the
    # other place that builds a prompt from real events, so it needs the
    # same truncation, not just the current event.
    history = summarize_history(chat.events)
    chat.append(event)
    reply = react_to_event(summarize_for_reaction(event), history, use_tools=True)
    # Every dispatched tool call is a real, structured fact about this run —
    # the same "every real action becomes a real, persisted event" rule
    # integration_runner's own call_started/review_approved/constraint_added
    # events already follow. Recorded here, not there: these tool calls (run_stage,
    # stage_result, ...) already produce their OWN real integration_runner
    # events once dispatched (call_started, review_approved, etc.) — this is
    # the durable record of the DISPATCH decision itself (which tool, what
    # arguments, what immediate result), which would otherwise only ever
    # have existed in this one synchronous HTTP response.
    for step in reply.get("steps", []):
        chat.append({
            "type": "tool_called", "stage": stage,
            "data": {"tool": step["tool"], "arguments": step["arguments"], "result": step["result"]},
            "timestamp": time.time(),
        })
    text = reply.get("message") or f"Called {reply.get('tool_called')}."
    chat.append(
        {"type": "message", "stage": stage, "text": text, "model": reply.get("model"), "timestamp": time.time()}
    )
    return reply
