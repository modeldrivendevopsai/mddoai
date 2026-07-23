"""
Orchestrator unit tests. No real API calls — orchestrator.chat is mocked.

Tests verify:
  1. is_good_enough() rejects empty/error-marker responses and accepts good ones.
  2. The stage agents (psm/atl/acceleo/generation) call chat() with a stage-specific
     system prompt and return the response content.
  3. Orchestrator.run_stage() looks up the right agent, validates its output, and
     reports the current stage.
  4. Orchestrator.advance_stage()/stage_result() move through STAGES and handle
     approval vs. rejection (constraint recording) correctly.
  5. run_stage()/rerun_stage() pick up constraints recorded via add_constraint() since
     the last run — verifying corrections are actually threaded into the agent's prompt,
     not just stored and left unused.
  6. stage_result() on approval automatically runs the next stage's agent (not just
     advancing the pointer), threading the approved stage's output into the next
     stage's context under the right f"{stage_id}_output" key, and accumulating
     outputs across approvals so the final generation stage sees all three.
  7. judge() calls chat() with TOOLS/tool_choice="auto" and the current stage baked into
     the system prompt, dispatches whatever tool call(s) the LLM returns to the real
     functions (including a multi-call add_constraint -> rerun_stage sequence and a
     start_pipeline call), reports {"tool_called", "result", "steps"} on success,
     {"tool_called": None, ...} with a clarification message when the LLM calls no tool,
     and doesn't crash on a hallucinated/unknown tool name.
  8. start_pipeline() resets the pipeline (dropping prior progress/constraints) and runs
     the psm stage fresh for a new platform description — same behavior POST /start uses.
  9. chat() — the HTTP client that replaced the old direct `from router.router import chat`
     import — POSTs to AI_LAYER_URL/chat with the right payload (omitting tools/tool_choice
     when not given), and wraps the JSON response so response.choices[0].message.content and
     .tool_calls (and each tool_call's .function.name/.arguments) work exactly like the
     litellm response object this replaced.
"""
import json
from unittest.mock import MagicMock, patch

import httpx
import pytest

import orchestrator


def ok_response(content):
    r = MagicMock()
    r.choices = [MagicMock(message=MagicMock(content=content, tool_calls=None))]
    return r


def tool_call(name, arguments):
    call = MagicMock()
    call.function.name = name
    call.function.arguments = json.dumps(arguments)
    return call


def tool_response(tool_calls, content=None):
    r = MagicMock()
    r.choices = [MagicMock(message=MagicMock(content=content, tool_calls=tool_calls))]
    return r


def test_is_good_enough_rejects_empty():
    assert not orchestrator.is_good_enough("")
    assert not orchestrator.is_good_enough("   ")


def test_is_good_enough_rejects_error_markers():
    assert not orchestrator.is_good_enough("I cannot help with that.")
    assert not orchestrator.is_good_enough("I don't know how to do that.")
    assert not orchestrator.is_good_enough("Sorry, an error occurred.")


def test_is_good_enough_accepts_valid_response():
    assert orchestrator.is_good_enough("Here are the pipeline stages you need: build, test, deploy.")


def test_is_good_enough_accepts_technical_content_that_mentions_error_handling():
    # "error" as a bare substring must not trip the refusal check — legitimate
    # generated content (e.g. an Acceleo template) routinely discusses error
    # handling as a concept, distinct from the agent itself reporting a failure.
    content = (
        "The template defines an onDependencyFailure block that invokes the "
        "errorHandler class, catching IOError and surfacing an error code to "
        "the pipeline's error-handling policy."
    )
    assert orchestrator.is_good_enough(content)


# --- Stage-based pipeline tests ---------------------------------------------


def test_stages_order():
    assert orchestrator.STAGES == ["psm", "atl", "acceleo", "generation"]


def test_psm_agent_uses_psm_system_prompt_and_platform_description():
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")) as mock_chat:
        result = orchestrator.psm_agent({"platform_description": "A GitLab CI platform"})

    assert result == "PSM description"
    messages = mock_chat.call_args.args[0]
    assert messages[0]["role"] == "system"
    assert "PSM" in messages[0]["content"]
    assert messages[1]["content"].startswith("A GitLab CI platform")


def test_atl_agent_uses_atl_system_prompt_and_psm_output():
    with patch.object(orchestrator, "chat", return_value=ok_response("ATL rules")) as mock_chat:
        result = orchestrator.atl_agent({"psm_output": "some PSM output"})

    assert result == "ATL rules"
    messages = mock_chat.call_args.args[0]
    assert "ATL" in messages[0]["content"]
    assert messages[1]["content"].startswith("some PSM output")


def test_acceleo_agent_uses_acceleo_system_prompt_and_atl_output():
    with patch.object(orchestrator, "chat", return_value=ok_response("Acceleo template")) as mock_chat:
        result = orchestrator.acceleo_agent({"atl_output": "some ATL output"})

    assert result == "Acceleo template"
    messages = mock_chat.call_args.args[0]
    assert "Acceleo" in messages[0]["content"]
    assert messages[1]["content"].startswith("some ATL output")


def test_gen_agent_combines_all_previous_outputs():
    with patch.object(orchestrator, "chat", return_value=ok_response("Final summary")) as mock_chat:
        result = orchestrator.gen_agent({
            "psm_output": "psm out",
            "atl_output": "atl out",
            "acceleo_output": "acceleo out",
        })

    assert result == "Final summary"
    messages = mock_chat.call_args.args[0]
    user_content = messages[1]["content"]
    assert "psm out" in user_content
    assert "atl out" in user_content
    assert "acceleo out" in user_content


def test_validate_reuses_is_good_enough():
    assert orchestrator.validate("A real response.")
    assert not orchestrator.validate("")
    assert not orchestrator.validate("I cannot help with that.")


def test_stage_agents_maps_stage_names_to_agent_functions():
    assert orchestrator.stage_agents == {
        "psm": orchestrator.psm_agent,
        "atl": orchestrator.atl_agent,
        "acceleo": orchestrator.acceleo_agent,
        "generation": orchestrator.gen_agent,
    }


def test_run_stage_calls_current_stage_agent_and_validates_output():
    o = orchestrator.Orchestrator()
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")) as mock_chat:
        result = o.run_stage({"platform_description": "A GitLab CI platform"})

    assert mock_chat.call_count == 1
    assert result == {"stage": "psm", "output": "PSM description", "valid": True}


def test_run_stage_reports_invalid_output():
    o = orchestrator.Orchestrator()
    with patch.object(orchestrator, "chat", return_value=ok_response("I cannot help with that.")):
        result = o.run_stage({"platform_description": "A GitLab CI platform"})

    assert result["valid"] is False


def test_run_stage_incorporates_constraints_added_since_the_last_run():
    o = orchestrator.Orchestrator()
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM v1")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    o.add_constraint("psm", "Use kebab-case job names")

    with patch.object(orchestrator, "chat", return_value=ok_response("PSM v2")) as mock_chat:
        o.run_stage({"platform_description": "A GitLab CI platform"})

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "Use kebab-case job names" in user_content


def test_rerun_stage_replays_the_last_context_and_picks_up_new_constraints():
    o = orchestrator.Orchestrator()
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM v1")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    o.add_constraint("psm", "Include a lint stage")

    with patch.object(orchestrator, "chat", return_value=ok_response("PSM v2")) as mock_chat:
        result = o.rerun_stage()

    messages = mock_chat.call_args.args[0]
    assert messages[1]["content"].startswith("A GitLab CI platform")
    assert "Include a lint stage" in messages[1]["content"]
    assert result == {"stage": "psm", "output": "PSM v2", "valid": True}


def test_module_level_rerun_stage_delegates_to_default_orchestrator():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM v1")):
            orchestrator.run_stage({"platform_description": "A GitLab CI platform"})

        orchestrator.add_constraint("psm", "Include a lint stage")

        with patch.object(orchestrator, "chat", return_value=ok_response("PSM v2")) as mock_chat:
            result = orchestrator.rerun_stage()

        assert "Include a lint stage" in mock_chat.call_args.args[0][1]["content"]
        assert result == {"stage": "psm", "output": "PSM v2", "valid": True}
    finally:
        orchestrator._default = original


def test_advance_stage_moves_through_stages_and_returns_none_at_end():
    o = orchestrator.Orchestrator()
    assert o.current_stage == "psm"
    assert o.advance_stage() == "atl"
    assert o.advance_stage() == "acceleo"
    assert o.advance_stage() == "generation"
    assert o.advance_stage() is None
    assert o.current_stage is None


def test_add_constraint_records_correction_for_stage():
    o = orchestrator.Orchestrator()
    o.add_constraint("psm", "Use kebab-case job names")
    o.add_constraint("psm", "Include a lint stage")

    assert o.constraints["psm"] == ["Use kebab-case job names", "Include a lint stage"]


def test_stage_result_approved_runs_next_stage_and_returns_its_output():
    o = orchestrator.Orchestrator()
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    with patch.object(orchestrator, "chat", return_value=ok_response("ATL rules")) as mock_chat:
        result = o.stage_result("psm", approved=True)

    assert o.current_stage == "atl"
    assert result == {"stage": "atl", "output": "ATL rules", "valid": True}
    user_content = mock_chat.call_args.args[0][1]["content"]
    assert user_content.startswith("PSM description")


def test_stage_result_approved_threads_output_under_stage_output_key_and_accumulates():
    o = orchestrator.Orchestrator()
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")):
        o.run_stage({"platform_description": "A GitLab CI platform"})
    with patch.object(orchestrator, "chat", return_value=ok_response("ATL rules")):
        o.stage_result("psm", approved=True)
    with patch.object(orchestrator, "chat", return_value=ok_response("Acceleo template")):
        o.stage_result("atl", approved=True)

    with patch.object(orchestrator, "chat", return_value=ok_response("Final summary")) as mock_chat:
        result = o.stage_result("acceleo", approved=True)

    assert result == {"stage": "generation", "output": "Final summary", "valid": True}
    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "PSM description" in user_content
    assert "ATL rules" in user_content
    assert "Acceleo template" in user_content


def test_stage_result_approved_on_last_stage_returns_complete():
    o = orchestrator.Orchestrator()
    o.current_stage_index = len(orchestrator.STAGES) - 1
    result = o.stage_result("generation", approved=True)

    assert result == {"status": "complete"}
    assert o.current_stage is None


def test_stage_result_rejected_records_constraint_and_reruns_same_stage():
    o = orchestrator.Orchestrator()
    result = o.stage_result("psm", approved=False, correction="Missing artifact retention policy")

    assert result == {"status": "rerun", "stage": "psm"}
    assert o.current_stage == "psm"
    assert o.constraints["psm"] == ["Missing artifact retention policy"]


def test_module_level_wrappers_delegate_to_default_orchestrator():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")):
            run_result = orchestrator.run_stage({"platform_description": "desc"})
        assert run_result["stage"] == "psm"

        advance_result = orchestrator.advance_stage()
        assert advance_result == "atl"

        orchestrator.add_constraint("atl", "Name rules after the mapped concept")
        assert orchestrator._default.constraints["atl"] == ["Name rules after the mapped concept"]

        with patch.object(orchestrator, "chat", return_value=ok_response("Acceleo template")):
            stage_result = orchestrator.stage_result("atl", approved=True)
        assert stage_result == {"stage": "acceleo", "output": "Acceleo template", "valid": True}
    finally:
        orchestrator._default = original


# --- judge() tests ------------------------------------------------------------


def test_judge_calls_chat_with_tools_and_current_stage_in_system_prompt():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM output")):
            orchestrator.run_stage({"platform_description": "A GitLab CI platform"})
        with patch.object(orchestrator, "chat", return_value=ok_response("ATL output")):
            orchestrator.stage_result("psm", approved=True)  # advances current_stage to "atl"

        with patch.object(orchestrator, "chat", return_value=ok_response("Sure, one moment.")) as mock_chat:
            orchestrator.judge("what's the status?")

        messages, kwargs = mock_chat.call_args.args, mock_chat.call_args.kwargs
        assert kwargs["tools"] == orchestrator.TOOLS
        assert kwargs["tool_choice"] == "auto"
        assert messages[0][0]["role"] == "system"
        assert "is: atl" in messages[0][0]["content"]
        assert messages[0][1] == {"role": "user", "content": "what's the status?"}
    finally:
        orchestrator._default = original


def test_judge_returns_clarification_when_llm_calls_no_tool():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        response = tool_response(None, content="Could you clarify which stage you mean?")
        with patch.object(orchestrator, "chat", return_value=response) as mock_chat:
            result = orchestrator.judge("hello there")

        assert mock_chat.call_count == 1
        assert result == {
            "tool_called": None,
            "result": None,
            "message": "Could you clarify which stage you mean?",
        }
    finally:
        orchestrator._default = original


def test_judge_falls_back_to_generic_clarification_when_llm_gives_no_text_either():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        response = tool_response(None, content=None)
        with patch.object(orchestrator, "chat", return_value=response):
            result = orchestrator.judge("???")

        assert result["tool_called"] is None
        assert "clarify" in result["message"].lower()
    finally:
        orchestrator._default = original


def test_judge_dispatches_single_rerun_stage_tool_call():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM v1")):
            orchestrator.run_stage({"platform_description": "A GitLab CI platform"})

        judge_response = tool_response([tool_call("rerun_stage", {})])
        agent_response = ok_response("PSM v2")
        with patch.object(orchestrator, "chat", side_effect=[judge_response, agent_response]) as mock_chat:
            result = orchestrator.judge("redo the psm stage")

        assert mock_chat.call_count == 2
        assert result == {
            "tool_called": "rerun_stage",
            "result": {"stage": "psm", "output": "PSM v2", "valid": True},
            "steps": [{"tool": "rerun_stage", "arguments": {}, "result": {"stage": "psm", "output": "PSM v2", "valid": True}}],
        }
    finally:
        orchestrator._default = original


def test_judge_dispatches_add_constraint_then_rerun_stage_for_inline_correction():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM output")):
            orchestrator.run_stage({"platform_description": "A GitLab CI platform"})
        with patch.object(orchestrator, "chat", return_value=ok_response("ATL output v1")):
            orchestrator.stage_result("psm", approved=True)  # auto-runs the atl stage

        assert orchestrator.current_stage() == "atl"

        judge_response = tool_response([
            tool_call("add_constraint", {"stage": "atl", "constraint": "Add a lint step"}),
            tool_call("rerun_stage", {}),
        ])
        agent_response = ok_response("ATL output v2 (lint step added)")
        with patch.object(orchestrator, "chat", side_effect=[judge_response, agent_response]) as mock_chat:
            result = orchestrator.judge("the ATL stage output is wrong, please redo it with a lint step added")

        assert mock_chat.call_count == 2
        assert orchestrator._default.constraints["atl"] == ["Add a lint step"]
        assert [s["tool"] for s in result["steps"]] == ["add_constraint", "rerun_stage"]
        assert result["steps"][0]["result"] is None
        assert result["tool_called"] == "rerun_stage"
        assert result["result"] == {
            "stage": "atl",
            "output": "ATL output v2 (lint step added)",
            "valid": True,
        }
    finally:
        orchestrator._default = original


def test_judge_dispatches_stage_result_approve_tool_call():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM output")):
            orchestrator.run_stage({"platform_description": "A GitLab CI platform"})

        judge_response = tool_response([tool_call("stage_result", {"stage_id": "psm", "approved": True})])
        atl_agent_response = ok_response("ATL output")
        with patch.object(orchestrator, "chat", side_effect=[judge_response, atl_agent_response]):
            result = orchestrator.judge("the psm output looks good, approve it")

        assert result["tool_called"] == "stage_result"
        assert result["result"] == {"stage": "atl", "output": "ATL output", "valid": True}
        assert orchestrator.current_stage() == "atl"
    finally:
        orchestrator._default = original


def test_judge_dispatches_start_pipeline_tool_call():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        # Get some way into an in-progress run first, so we can confirm
        # start_pipeline actually resets it rather than continuing it.
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM output (TeamCity)")):
            orchestrator.run_stage({"platform_description": "TeamCity: A CI/CD platform using Kotlin DSL"})
        orchestrator.add_constraint("psm", "Use kebab-case job names")

        judge_response = tool_response(
            [tool_call("start_pipeline", {"platform_description": "GitLab CI with YAML pipelines"})]
        )
        new_psm_response = ok_response("PSM output (GitLab)")
        with patch.object(orchestrator, "chat", side_effect=[judge_response, new_psm_response]) as mock_chat:
            result = orchestrator.judge("let's do this for GitLab instead")

        assert mock_chat.call_count == 2
        assert result == {
            "tool_called": "start_pipeline",
            "result": {"stage": "psm", "output": "PSM output (GitLab)", "valid": True},
            "steps": [{
                "tool": "start_pipeline",
                "arguments": {"platform_description": "GitLab CI with YAML pipelines"},
                "result": {"stage": "psm", "output": "PSM output (GitLab)", "valid": True},
            }],
        }
        # New Orchestrator instance: prior progress/constraints are gone.
        assert orchestrator.current_stage() == "psm"
        assert orchestrator._default.constraints == {}
        agent_call_user_content = mock_chat.call_args.args[0][1]["content"]
        assert agent_call_user_content == "GitLab CI with YAML pipelines"
    finally:
        orchestrator._default = original


def test_judge_records_error_for_unknown_tool_call_without_crashing():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        judge_response = tool_response([tool_call("delete_everything", {})])
        with patch.object(orchestrator, "chat", return_value=judge_response):
            result = orchestrator.judge("do something weird")

        assert result["tool_called"] == "delete_everything"
        assert result["result"] == {"error": "Unknown tool: delete_everything"}
    finally:
        orchestrator._default = original


# --- chat() HTTP client tests -------------------------------------------------
#
# chat() no longer imports router.router directly; it POSTs to ai-layer's /chat
# endpoint. These tests mock httpx.post itself (the actual network boundary),
# rather than mocking chat() as the tests above do for the agents/judge().


def _fake_httpx_response(content=None, model="gemini/gemini-2.5-flash", tool_calls=None):
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = {"content": content, "model": model, "tool_calls": tool_calls}
    return resp


def test_chat_posts_to_ai_layer_url_with_messages_and_model():
    messages = [{"role": "user", "content": "hi"}]
    with patch("orchestrator.httpx.post", return_value=_fake_httpx_response("hello")) as mock_post:
        result = orchestrator.chat(messages, model="auto")

    mock_post.assert_called_once_with(
        f"{orchestrator.AI_LAYER_URL}/chat",
        json={"messages": messages, "model": "auto"},
        timeout=120.0,
    )
    assert result.choices[0].message.content == "hello"
    assert result.model == "gemini/gemini-2.5-flash"


def test_chat_omits_tools_and_tool_choice_when_not_provided():
    with patch("orchestrator.httpx.post", return_value=_fake_httpx_response("hello")) as mock_post:
        orchestrator.chat([{"role": "user", "content": "hi"}])

    sent_payload = mock_post.call_args.kwargs["json"]
    assert "tools" not in sent_payload
    assert "tool_choice" not in sent_payload


def test_chat_includes_tools_and_tool_choice_when_provided():
    with patch("orchestrator.httpx.post", return_value=_fake_httpx_response("hello")) as mock_post:
        orchestrator.chat([{"role": "user", "content": "hi"}], tools=orchestrator.TOOLS, tool_choice="auto")

    sent_payload = mock_post.call_args.kwargs["json"]
    assert sent_payload["tools"] == orchestrator.TOOLS
    assert sent_payload["tool_choice"] == "auto"


def test_chat_wraps_tool_calls_into_navigable_objects():
    tool_calls = [{"function": {"name": "rerun_stage", "arguments": "{}"}}]
    with patch("orchestrator.httpx.post", return_value=_fake_httpx_response(None, tool_calls=tool_calls)):
        result = orchestrator.chat([{"role": "user", "content": "hi"}])

    message = result.choices[0].message
    assert message.content is None
    assert message.tool_calls[0].function.name == "rerun_stage"
    assert message.tool_calls[0].function.arguments == "{}"


def test_chat_returns_no_tool_calls_when_response_has_none():
    with patch("orchestrator.httpx.post", return_value=_fake_httpx_response("hello", tool_calls=None)):
        result = orchestrator.chat([{"role": "user", "content": "hi"}])

    assert result.choices[0].message.tool_calls is None


def test_chat_raises_when_ai_layer_returns_an_error_status():
    resp = MagicMock()
    resp.raise_for_status.side_effect = httpx.HTTPStatusError("500", request=MagicMock(), response=MagicMock())
    with patch("orchestrator.httpx.post", return_value=resp):
        with pytest.raises(httpx.HTTPStatusError):
            orchestrator.chat([{"role": "user", "content": "hi"}])
