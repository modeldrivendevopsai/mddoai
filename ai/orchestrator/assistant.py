"""The orchestrator's one reply mechanism: react_to_event() (narration,
called automatically by orchestrator.record_event() for every real event,
via the set_reactor() hook, wired in explicitly by main.py at startup, not
as a side effect of importing this file) and nudge() (a human's free-form
message, the tools-enabled path through the SAME react_to_event()). Giving
it tools is the only difference between narrating and acting, same
function, same prompt, same dispatch.

Composes orchestrator.py (pipeline state + operations) with pipeline_tools.py
(MDDOAI's declared abilities) and tool_calling.py (the generic engine that
turns a prompt + tools into a reply). Neither of those three knows about
this file.
"""
import time

import orchestrator
import pipeline_tools
import tool_calling


def react_to_event(event: dict, history: list[dict] | None = None, use_tools: bool = False) -> dict:
    stage = orchestrator.current_stage()
    stage_description = stage if stage is not None else "none, the pipeline hasn't been started"
    system_prompt = pipeline_tools.SYSTEM_PROMPT_TEMPLATE.format(current_stage=stage_description)
    available_tools = tool_calling.load_tools(stage, pipeline_tools.TOOLS) if use_tools else None
    return tool_calling.build_reply(
        orchestrator.chat, system_prompt, event, history, available_tools, model=orchestrator.current_model()
    )


def nudge(user_message: str) -> dict:
    """A human's free-form message -> react_to_event with tools enabled,
    recording both the message and the reply as events, the same as
    record_event() does automatically for real calls. use_tools=True is the
    only difference from narrating: same function, same prompt, same
    dispatch. Any tool that starts a stage running (run_stage, rerun_stage,
    stage_result-approve, start_pipeline) does so via orchestrator's own
    run_stage_async(), the same as a REST call would use, so it returns
    immediately instead of blocking this request for the stage's full
    duration."""
    stage = orchestrator.current_stage()
    event = {
        "type": "user_message",
        "stage": stage,
        "data": {"message": user_message},
        "timestamp": time.time(),
    }
    # Summarized before either is used to build a prompt (see
    # orchestrator.summarize_for_reaction()'s own docstring for why history
    # specifically can't skip this): record_event()'s automatic narration
    # already does the same for every other event, nudge() is the other
    # place that builds a prompt from real events, so it needs the same
    # truncation, not just the current event.
    history = orchestrator.summarize_history(orchestrator.events())
    orchestrator.append_event(event)
    reply = react_to_event(orchestrator.summarize_for_reaction(event), history, use_tools=True)
    text = reply.get("message") or f"Called {reply.get('tool_called')}."
    orchestrator.append_event(
        {"type": "message", "stage": stage, "text": text, "model": reply.get("model"), "timestamp": time.time()}
    )
    return reply
