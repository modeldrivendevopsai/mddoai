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
"""
from unittest.mock import MagicMock, patch

import orchestrator


def ok_response(content):
    r = MagicMock()
    r.choices = [MagicMock(message=MagicMock(content=content))]
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
