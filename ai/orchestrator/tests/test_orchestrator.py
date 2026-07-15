"""
Orchestrator unit tests. No real API calls — orchestrator.chat is mocked.

Tests verify:
  1. Intent classification for pipeline_generation, platform_question, general_question.
  2. is_good_enough() rejects empty/error-marker responses and accepts good ones.
  3. orchestrate() returns the first good response without retrying.
  4. orchestrate() retries up to MAX_ATTEMPTS on low-quality responses, then returns the
     last response with a human-curation note.
  5. orchestrate() stops retrying as soon as a later attempt succeeds.
  6. The stage agents (psm/atl/acceleo/generation) call chat() with a stage-specific
     system prompt and return the response content.
  7. Orchestrator.run_stage() looks up the right agent, validates its output, and
     reports the current stage.
  8. Orchestrator.advance_stage()/stage_result() move through STAGES and handle
     approval vs. rejection (constraint recording) correctly.
  9. run_stage()/rerun_stage() pick up constraints recorded via add_constraint() since
     the last run — verifying corrections are actually threaded into the agent's prompt,
     not just stored and left unused.
"""
from unittest.mock import MagicMock, patch

import orchestrator


def ok_response(content):
    r = MagicMock()
    r.choices = [MagicMock(message=MagicMock(content=content))]
    return r


def test_classify_intent_pipeline_generation():
    text = "Can you help me generate a CI/CD pipeline to deploy my app?"
    assert orchestrator.classify_intent(text) == "pipeline_generation"


def test_classify_intent_platform_question():
    assert orchestrator.classify_intent("Does this support TeamCity or GitLab?") == "platform_question"


def test_classify_intent_general_question():
    assert orchestrator.classify_intent("What is DevOps anyway?") == "general_question"


def test_is_good_enough_rejects_empty():
    assert not orchestrator.is_good_enough("")
    assert not orchestrator.is_good_enough("   ")


def test_is_good_enough_rejects_error_markers():
    assert not orchestrator.is_good_enough("I cannot help with that.")
    assert not orchestrator.is_good_enough("I don't know how to do that.")
    assert not orchestrator.is_good_enough("Sorry, an error occurred.")


def test_is_good_enough_accepts_valid_response():
    assert orchestrator.is_good_enough("Here are the pipeline stages you need: build, test, deploy.")


def test_orchestrate_returns_first_good_response_without_retrying():
    with patch.object(orchestrator, "chat", return_value=ok_response("Here's your pipeline plan.")) as mock_chat:
        result = orchestrator.orchestrate([{"role": "user", "content": "generate a pipeline"}])

    assert result == "Here's your pipeline plan."
    assert mock_chat.call_count == 1


def test_orchestrate_retries_on_low_quality_then_returns_note():
    with patch.object(orchestrator, "chat", return_value=ok_response("I cannot help with that.")) as mock_chat:
        result = orchestrator.orchestrate([{"role": "user", "content": "generate a pipeline"}])

    assert mock_chat.call_count == orchestrator.MAX_ATTEMPTS
    assert "I cannot help with that." in result
    assert "human curation" in result.lower()


def test_orchestrate_succeeds_on_a_later_attempt():
    responses = [
        ok_response("I cannot help."),
        ok_response("I cannot help."),
        ok_response("Here's the answer."),
    ]
    with patch.object(orchestrator, "chat", side_effect=responses) as mock_chat:
        result = orchestrator.orchestrate([{"role": "user", "content": "generate a pipeline"}])

    assert result == "Here's the answer."
    assert mock_chat.call_count == 3


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


def test_stage_result_approved_advances_to_next_stage():
    o = orchestrator.Orchestrator()
    result = o.stage_result("psm", approved=True)

    assert result == {"status": "advanced", "next_stage": "atl"}
    assert o.current_stage == "atl"


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

        stage_result = orchestrator.stage_result("atl", approved=True)
        assert stage_result == {"status": "advanced", "next_stage": "acceleo"}
    finally:
        orchestrator._default = original
