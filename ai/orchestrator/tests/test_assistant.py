"""
assistant.py unit tests: react_to_event() (narration) and nudge() (a human's
free-form message, the tools-enabled path through the SAME react_to_event()
— there is no separate "judge"). No real API calls — orchestrator.chat /
orchestrator.httpx are mocked. Relies on conftest.py's
orchestrator.set_reactor(assistant.react_to_event) wiring.

Tests verify:
  1. nudge() calls chat() with TOOLS/tool_choice="auto" and the current stage
     baked into the system prompt, dispatches whatever tool call(s) the LLM
     returns to the real functions (including a multi-call add_constraint ->
     rerun_stage sequence, a stage_result approval, and a start_pipeline call),
     reports {"tool_called", "result", "steps"} on success, {"tool_called":
     None, ...} with a clarification message when the LLM calls no tool, and
     doesn't crash on a hallucinated/unknown tool name. Any tool that starts a
     stage running does so via orchestrator.run_stage_async() (a background
     thread), the same as a REST call, so nudge() itself only ever makes one
     chat() call (the routing decision), never blocking for a stage's duration.
  2. nudge() records the human's message and the reply as real events.
  3. react_to_event() with use_tools=False only narrates, it never passes
     tools= at all, so there's nothing for the LLM to dispatch even if it tried.
"""
import json
from unittest.mock import patch

import orchestrator
import pipeline_tools
import tool_calling
import assistant
from helpers import _fake_fetch_response, _fast_forward_to_psm, _chat_stub, _nudge_chat_stub, ok_response, \
    tool_call, tool_response


def test_nudge_calls_chat_with_tools_and_current_stage_in_system_prompt():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    _fast_forward_to_psm(orchestrator._default)
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM output")):
            orchestrator.run_stage({"platform_description": "A GitLab CI platform"})
        stub = _chat_stub({"ATL transformation agent": "ATL output"})
        with patch.object(orchestrator, "chat", side_effect=stub):
            orchestrator.review("psm", approved=True)  # advances current_stage to "atl"
            orchestrator.wait_for_idle()

        with patch.object(orchestrator, "chat", return_value=ok_response("Sure, one moment.")) as mock_chat:
            assistant.nudge("what's the status?")

        messages, kwargs = mock_chat.call_args.args, mock_chat.call_args.kwargs
        assert kwargs["tools"] == [t.schema() for t in tool_calling.load_tools("atl", pipeline_tools.TOOLS)]
        assert kwargs["tool_choice"] == "auto"
        assert messages[0][0]["role"] == "system"
        assert "is: atl" in messages[0][0]["content"]
        # index -1: the newly appended user_message event is always last, prior
        # history (there's real history by now: the review_approved event and
        # the atl run's own call_started/call_completed) fills the middle.
        sent_event = json.loads(messages[0][-1]["content"])
        assert sent_event["type"] == "user_message"
        assert sent_event["stage"] == "atl"
        assert sent_event["data"] == {"message": "what's the status?"}
    finally:
        orchestrator._default = original


def test_nudge_returns_clarification_when_llm_calls_no_tool():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        response = tool_response(None, content="Could you clarify which stage you mean?")
        with patch.object(orchestrator, "chat", return_value=response) as mock_chat:
            result = assistant.nudge("hello there")

        assert mock_chat.call_count == 1
        assert result == {
            "tool_called": None,
            "result": None,
            "message": "Could you clarify which stage you mean?",
            "model": "test-model",
        }
    finally:
        orchestrator._default = original


def test_nudge_falls_back_to_generic_clarification_when_llm_gives_no_text_either():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        response = tool_response(None, content=None)
        with patch.object(orchestrator, "chat", return_value=response):
            result = assistant.nudge("???")

        assert result["tool_called"] is None
        assert "clarify" in result["message"].lower()
    finally:
        orchestrator._default = original


def test_nudge_records_the_human_message_and_reply_as_events():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        response = tool_response(None, content="Sure, one moment.")
        with patch.object(orchestrator, "chat", return_value=response):
            assistant.nudge("what's the status?")

        events = orchestrator.events()
        assert len(events) == 2
        assert events[0] == {
            "type": "user_message", "stage": "docs",
            "data": {"message": "what's the status?"}, "timestamp": events[0]["timestamp"],
        }
        assert events[1] == {
            "type": "message", "stage": "docs",
            "text": "Sure, one moment.", "model": "test-model", "timestamp": events[1]["timestamp"],
        }
    finally:
        orchestrator._default = original


def test_nudge_dispatches_single_rerun_stage_tool_call():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    _fast_forward_to_psm(orchestrator._default)
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM v1")):
            orchestrator.run_stage({"platform_description": "A GitLab CI platform"})

        stub = _nudge_chat_stub(
            tool_calls=[tool_call("rerun_stage", {})],
            stage_outputs={"PSM (Platform-Specific Model)": "PSM v2"},
        )
        with patch.object(orchestrator, "chat", side_effect=stub):
            result = assistant.nudge("redo the psm stage")
            orchestrator.wait_for_idle()

        assert result == {
            "tool_called": "rerun_stage",
            "result": {"status": "started", "stage": "psm"},
            "steps": [{"tool": "rerun_stage", "arguments": {}, "result": {"status": "started", "stage": "psm"}}],
            "model": "test-model",
        }
        completed = next(e for e in orchestrator.events() if e["type"] == "call_completed")
        assert completed["data"] == {"stage": "psm", "output": "PSM v2", "valid": True}
    finally:
        orchestrator._default = original


def test_nudge_dispatches_add_constraint_then_rerun_stage_for_inline_correction():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    _fast_forward_to_psm(orchestrator._default)
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM output")):
            orchestrator.run_stage({"platform_description": "A GitLab CI platform"})
        stub = _chat_stub({"ATL transformation agent": "ATL output v1"})
        with patch.object(orchestrator, "chat", side_effect=stub):
            orchestrator.review("psm", approved=True)  # auto-starts the atl stage
            orchestrator.wait_for_idle()

        assert orchestrator.current_stage() == "atl"

        stub = _nudge_chat_stub(
            tool_calls=[
                tool_call("add_constraint", {"stage": "atl", "constraint": "Add a lint step"}),
                tool_call("rerun_stage", {}),
            ],
            stage_outputs={"ATL transformation agent": "ATL output v2 (lint step added)"},
        )
        with patch.object(orchestrator, "chat", side_effect=stub):
            result = assistant.nudge("the ATL stage output is wrong, please redo it with a lint step added")
            orchestrator.wait_for_idle()

        assert orchestrator._default.constraints["atl"] == ["Add a lint step"]
        assert [s["tool"] for s in result["steps"]] == ["add_constraint", "rerun_stage"]
        assert result["steps"][0]["result"] is None
        assert result["tool_called"] == "rerun_stage"
        assert result["result"] == {"status": "started", "stage": "atl"}
        # last, not first: the setup's own psm approval already recorded one
        # call_completed (atl's first run, "ATL output v1").
        completed = [e for e in orchestrator.events() if e["type"] == "call_completed"][-1]
        assert completed["data"] == {"stage": "atl", "output": "ATL output v2 (lint step added)", "valid": True}
    finally:
        orchestrator._default = original


def test_nudge_dispatches_stage_result_approve_tool_call():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    _fast_forward_to_psm(orchestrator._default)
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM output")):
            orchestrator.run_stage({"platform_description": "A GitLab CI platform"})

        stub = _nudge_chat_stub(
            tool_calls=[tool_call("stage_result", {"stage_id": "psm", "approved": True})],
            stage_outputs={"ATL transformation agent": "ATL output"},
        )
        with patch.object(orchestrator, "chat", side_effect=stub):
            result = assistant.nudge("the psm output looks good, approve it")
            orchestrator.wait_for_idle()

        assert result["tool_called"] == "stage_result"
        assert result["result"] == {"status": "started", "stage": "atl"}
        assert orchestrator.current_stage() == "atl"
        completed = next(e for e in orchestrator.events() if e["type"] == "call_completed")
        assert completed["data"] == {"stage": "atl", "output": "ATL output", "valid": True}
    finally:
        orchestrator._default = original


def test_nudge_dispatches_start_pipeline_tool_call():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    _fast_forward_to_psm(orchestrator._default)
    try:
        # Get some way into an in-progress run first, so we can confirm
        # start_pipeline actually resets it rather than continuing it.
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM output (TeamCity)")):
            orchestrator.run_stage({"platform_description": "TeamCity: A CI/CD platform using Kotlin DSL"})
        orchestrator.add_constraint("psm", "Use kebab-case job names")

        stub = _nudge_chat_stub(tool_calls=[tool_call("start_pipeline", {
            "platform_description": "GitLab CI with YAML pipelines",
            "seed_url": "https://docs.gitlab.com/ci/yaml/",
        })])
        with patch.object(orchestrator, "chat", side_effect=stub), \
             patch.object(orchestrator, "httpx") as mock_httpx:
            mock_httpx.post.return_value = _fake_fetch_response()
            result = assistant.nudge("let's do this for GitLab instead")
            orchestrator.wait_for_idle()

        assert result["tool_called"] == "start_pipeline"
        assert result["result"] == {"status": "started", "stage": "docs"}
        # New Orchestrator instance: prior progress/constraints are gone.
        assert orchestrator.current_stage() == "docs"
        assert orchestrator._default.constraints == {}
        completed = next(e for e in orchestrator.events() if e["type"] == "call_completed")
        assert completed["stage"] == "docs"
        assert completed["data"]["valid"] is True
        sent_payload = mock_httpx.post.call_args.kwargs["json"]
        assert sent_payload["url"] == "https://docs.gitlab.com/ci/yaml/"
    finally:
        orchestrator._default = original


def test_nudge_records_error_for_unknown_tool_call_without_crashing():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        nudge_response = tool_response([tool_call("delete_everything", {})])
        with patch.object(orchestrator, "chat", return_value=nudge_response):
            result = assistant.nudge("do something weird")

        assert result["tool_called"] == "delete_everything"
        assert result["result"] == {"error": "Unknown tool: delete_everything"}
    finally:
        orchestrator._default = original


def test_react_to_event_without_tools_only_narrates_and_never_dispatches():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        event = {"type": "call_started", "stage": "docs", "data": {"seed_url": "https://x"}, "timestamp": 1.0}
        # Even if the mocked LLM tries to return a tool call, react_to_event
        # never passes tools=, so there's nothing for the real API to honor;
        # this asserts the call itself carries no tools/tool_choice kwargs.
        with patch.object(orchestrator, "chat", return_value=ok_response("Fetching docs now.")) as mock_chat:
            result = assistant.react_to_event(event, [])

        assert result == {"tool_called": None, "result": None, "message": "Fetching docs now.", "model": "test-model"}
        assert "tools" not in mock_chat.call_args.kwargs
        assert "tool_choice" not in mock_chat.call_args.kwargs
    finally:
        orchestrator._default = original
