"""
assistant.py unit tests: react_to_event() (narration) and send_message() (a
human's free-form message, the tools-enabled path through the SAME
react_to_event() — there is no separate "judge"). No real API calls —
clients/ai_layer_client.chat and clients/integration_runner_client's
functions are mocked directly: unlike before this split, there's no live
IntegrationRun instance to drive through real stage transitions, tool
dispatch just needs integration_runner_client's own functions (review,
rerun_stage, add_constraint, start_stage_run, start_pipeline) to report
whatever result the test cares about, since assistant.py never touches
integration_runner's Python internals at all.

Tests verify:
  1. send_message() calls chat() with tools/tool_choice="auto" and the
     current stage baked into the system prompt, dispatches whatever tool
     call(s) the LLM returns to the real integration_runner_client
     functions (including a multi-call add_constraint -> rerun_stage
     sequence, a stage_result approval, and a start_pipeline call), reports
     {"tool_called", "result", "steps"} on success, {"tool_called": None,
     ...} with a clarification message when the LLM calls no tool, and
     doesn't crash on a hallucinated/unknown tool name.
  2. send_message() records the human's message, each dispatched tool call,
     and the reply as this run's own chat log turns.
  3. react_to_event() with use_tools=False only narrates, it never passes
     tools= at all, so there's nothing for the LLM to dispatch even if it tried.
"""
import json
from unittest.mock import patch

import assistant
import chat_log
import tool_calling
import tools
from clients import ai_layer_client, integration_runner_client
from helpers import ok_response, tool_call, tool_response


def _status(run_id="test-run", current_stage="docs", model=None, busy=False):
    return {"run_id": run_id, "current_stage": current_stage, "busy": busy, "model": model, "is_current": True}


def test_send_message_calls_chat_with_tools_and_current_stage_in_system_prompt():
    with patch.object(integration_runner_client, "get_status", return_value=_status(current_stage="atl")):
        with patch.object(ai_layer_client, "chat", return_value=ok_response("Sure, one moment.")) as mock_chat:
            assistant.send_message("what's the status?")

    messages, kwargs = mock_chat.call_args.args, mock_chat.call_args.kwargs
    assert kwargs["tools"] == [t.schema() for t in tool_calling.load_tools("atl", tools.get_tools())]
    assert kwargs["tool_choice"] == "auto"
    assert messages[0][0]["role"] == "system"
    assert "is: atl" in messages[0][0]["content"]
    # The newly appended user_message event is always last in what's sent.
    sent_event = json.loads(messages[0][-1]["content"])
    assert sent_event["type"] == "user_message"
    assert sent_event["stage"] == "atl"
    assert sent_event["data"] == {"message": "what's the status?"}


def test_send_message_returns_clarification_when_llm_calls_no_tool():
    response = tool_response(None, content="Could you clarify which stage you mean?")
    with patch.object(integration_runner_client, "get_status", return_value=_status()):
        with patch.object(ai_layer_client, "chat", return_value=response) as mock_chat:
            result = assistant.send_message("hello there")

    assert mock_chat.call_count == 1
    assert result == {
        "tool_called": None,
        "result": None,
        "message": "Could you clarify which stage you mean?",
        "model": "test-model",
    }


def test_send_message_falls_back_to_generic_clarification_when_llm_gives_no_text_either():
    response = tool_response(None, content=None)
    with patch.object(integration_runner_client, "get_status", return_value=_status()):
        with patch.object(ai_layer_client, "chat", return_value=response):
            result = assistant.send_message("???")

    assert result["tool_called"] is None
    assert "clarify" in result["message"].lower()


def test_send_message_records_the_human_message_and_reply_as_chat_turns():
    response = tool_response(None, content="Sure, one moment.")
    with patch.object(integration_runner_client, "get_status", return_value=_status(run_id="run-1")):
        with patch.object(ai_layer_client, "chat", return_value=response):
            assistant.send_message("what's the status?")

    events = chat_log.get_chat_log("run-1").events
    assert len(events) == 2
    assert events[0] == {
        "type": "user_message", "stage": "docs",
        "data": {"message": "what's the status?"}, "timestamp": events[0]["timestamp"],
    }
    assert events[1] == {
        "type": "message", "stage": "docs",
        "text": "Sure, one moment.", "model": "test-model", "timestamp": events[1]["timestamp"],
    }


def test_send_message_dispatches_single_rerun_stage_tool_call():
    stub = tool_response([tool_call("rerun_stage", {})])
    with patch.object(integration_runner_client, "get_status", return_value=_status(current_stage="psm")):
        with patch.object(ai_layer_client, "chat", return_value=stub):
            with patch.object(integration_runner_client, "rerun_stage", return_value={"status": "started", "stage": "psm"}) as mock_rerun:
                result = assistant.send_message("redo the psm stage")

    mock_rerun.assert_called_once_with("psm", {})
    assert result == {
        "tool_called": "rerun_stage",
        "result": {"status": "started", "stage": "psm"},
        "steps": [{"tool": "rerun_stage", "arguments": {}, "result": {"status": "started", "stage": "psm"}}],
        "model": "test-model",
    }


def test_send_message_dispatches_add_constraint_then_rerun_stage_for_inline_correction():
    stub = tool_response([
        tool_call("add_constraint", {"stage": "atl", "constraint": "Add a lint step"}),
        tool_call("rerun_stage", {}),
    ])
    with patch.object(integration_runner_client, "get_status", return_value=_status(current_stage="atl")):
        with patch.object(ai_layer_client, "chat", return_value=stub):
            with patch.object(integration_runner_client, "add_constraint", return_value={"status": "recorded", "stage": "atl"}) as mock_add:
                with patch.object(integration_runner_client, "rerun_stage", return_value={"status": "started", "stage": "atl"}) as mock_rerun:
                    result = assistant.send_message("the ATL stage output is wrong, please redo it with a lint step added")

    mock_add.assert_called_once_with(stage="atl", constraint="Add a lint step")
    mock_rerun.assert_called_once_with("atl", {})
    assert [s["tool"] for s in result["steps"]] == ["add_constraint", "rerun_stage"]
    assert result["tool_called"] == "rerun_stage"
    assert result["result"] == {"status": "started", "stage": "atl"}


def test_send_message_dispatches_stage_result_approve_tool_call():
    stub = tool_response([tool_call("stage_result", {"stage_id": "psm", "approved": True})])
    with patch.object(integration_runner_client, "get_status", return_value=_status(current_stage="psm")):
        with patch.object(ai_layer_client, "chat", return_value=stub):
            with patch.object(integration_runner_client, "review", return_value={"status": "started", "stage": "atl"}) as mock_review:
                result = assistant.send_message("the psm output looks good, approve it")

    mock_review.assert_called_once_with(stage_id="psm", approved=True)
    assert result["tool_called"] == "stage_result"
    assert result["result"] == {"status": "started", "stage": "atl"}


def test_send_message_dispatches_start_pipeline_tool_call():
    stub = tool_response([tool_call("start_pipeline", {
        "platform_description": "GitLab CI with YAML pipelines",
        "seed_url": "https://docs.gitlab.com/ci/yaml/",
    })])
    with patch.object(integration_runner_client, "get_status", return_value=_status(current_stage="psm")):
        with patch.object(ai_layer_client, "chat", return_value=stub):
            with patch.object(
                integration_runner_client, "start_pipeline", return_value={"status": "started", "stage": "docs"},
            ) as mock_start:
                result = assistant.send_message("let's do this for GitLab instead")

    mock_start.assert_called_once_with(
        "GitLab CI with YAML pipelines", "https://docs.gitlab.com/ci/yaml/", None, {},
    )
    assert result["tool_called"] == "start_pipeline"
    assert result["result"] == {"status": "started", "stage": "docs"}


def test_send_message_dispatches_start_pipeline_tool_call_with_mock_and_model():
    stub = tool_response([tool_call("start_pipeline", {
        "platform_description": "GitLab CI with YAML pipelines",
        "seed_url": "https://docs.gitlab.com/ci/yaml/",
        "model": "gemini-flash",
        "mock": True,
    })])
    with patch.object(integration_runner_client, "get_status", return_value=_status(current_stage="psm")):
        with patch.object(ai_layer_client, "chat", return_value=stub):
            with patch.object(
                integration_runner_client, "start_pipeline", return_value={"status": "started", "stage": "docs"},
            ) as mock_start:
                assistant.send_message("start a mock run for GitLab instead")

    mock_start.assert_called_once_with(
        "GitLab CI with YAML pipelines", "https://docs.gitlab.com/ci/yaml/", "gemini-flash", {"mock": True},
    )


def test_send_message_records_error_for_unknown_tool_call_without_crashing():
    stub = tool_response([tool_call("delete_everything", {})])
    with patch.object(integration_runner_client, "get_status", return_value=_status()):
        with patch.object(ai_layer_client, "chat", return_value=stub):
            result = assistant.send_message("do something weird")

    assert result["tool_called"] == "delete_everything"
    assert result["result"] == {"error": "Unknown tool: delete_everything"}


def test_react_to_event_without_tools_only_narrates_and_never_dispatches():
    event = {"type": "call_started", "stage": "docs", "data": {"seed_url": "https://x"}, "timestamp": 1.0}
    # Even if the mocked LLM tries to return a tool call, react_to_event
    # never passes tools=, so there's nothing for the real API to honor;
    # this asserts the call itself carries no tools/tool_choice kwargs.
    with patch.object(integration_runner_client, "get_status", return_value=_status()):
        with patch.object(ai_layer_client, "chat", return_value=ok_response("Fetching docs now.")) as mock_chat:
            result = assistant.react_to_event(event, [])

    assert result == {"tool_called": None, "result": None, "message": "Fetching docs now.", "model": "test-model"}
    assert "tools" not in mock_chat.call_args.kwargs
    assert "tool_choice" not in mock_chat.call_args.kwargs


def test_react_to_event_describes_the_events_own_stage_not_a_stale_live_one():
    # Regression test: narration runs on a background thread (chat_log.py),
    # so by the time this actually calls chat(), the pipeline can have
    # already advanced past the event being narrated - a fast placeholder
    # stage can finish before the LLM narration call even starts. The
    # system prompt must describe event["stage"] (pim, what this event is
    # actually about), never live get_status() (psm here, what's live by
    # the time this call happens) - conflating the two previously produced
    # narration that named the wrong stage.
    event = {"type": "review_approved", "stage": "pim", "data": {}, "timestamp": 1.0}
    with patch.object(integration_runner_client, "get_status", return_value=_status(current_stage="psm")):
        with patch.object(ai_layer_client, "chat", return_value=ok_response("Noted.")) as mock_chat:
            assistant.react_to_event(event, [])

    system_prompt_text = mock_chat.call_args.args[0][0]["content"]
    assert "is: pim" in system_prompt_text
    assert "is: psm" not in system_prompt_text
