"""integration_runner/stages/{pim,psm,atl,acceleo,generation}/agent.py unit
tests: the five placeholder stage agents, each its own folder (see
stages/__init__.py's own docstring for why), tested together here since
they're still homogeneous placeholders sharing the same real conventions
(a stage-specific system prompt, stages/_shared.py's constraints_note(),
one ai_layer_client.chat() call). The one real agent in this package
(stages/docs/agent.py) has its own tests, see test_docs_agent.py. The
stage_agents dict assembly itself has its own tests too, see
test_stages_registry.py. No real API calls — clients/ai_layer_client.chat
is mocked.

Tests verify:
  1. Each placeholder agent calls chat() with its own stage-specific system
     prompt, forwards the caller's chosen model (or None), and returns the
     response's "content" field.
  2. psm_stage prefers pim_output, falling back to docs_output, then
     platform_description; pim_stage prefers serialization_output, falling
     back to docs_output, then platform_description — the same "prefer the
     real prior stage's real output, fall back for a direct/test call"
     pattern for both.
"""
from unittest.mock import patch

from clients import ai_layer_client
from integration_runner.stages.acceleo.agent import acceleo_stage
from integration_runner.stages.atl.agent import atl_stage
from integration_runner.stages.generation.agent import gen_stage
from integration_runner.stages.pim.agent import pim_stage
from integration_runner.stages.psm.agent import psm_stage
from helpers import ok_response


def test_psm_stage_uses_psm_system_prompt_and_platform_description():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM description")) as mock_chat:
        result = psm_stage({"platform_description": "A GitLab CI platform"})

    assert result == "PSM description"
    messages = mock_chat.call_args.args[0]
    assert messages[0]["role"] == "system"
    assert "PSM" in messages[0]["content"]
    assert messages[1]["content"].startswith("A GitLab CI platform")


def test_psm_stage_forwards_the_chosen_model_from_context():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM description")) as mock_chat:
        psm_stage({"platform_description": "A GitLab CI platform", "model": "gemini-flash"})

    assert mock_chat.call_args.kwargs["model"] == "gemini-flash"


def test_psm_stage_passes_none_model_when_none_chosen():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM description")) as mock_chat:
        psm_stage({"platform_description": "A GitLab CI platform"})

    assert mock_chat.call_args.kwargs["model"] is None


def test_atl_stage_uses_atl_system_prompt_and_psm_output():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL rules")) as mock_chat:
        result = atl_stage({"psm_output": "some PSM output"})

    assert result == "ATL rules"
    messages = mock_chat.call_args.args[0]
    assert "ATL" in messages[0]["content"]
    assert messages[1]["content"].startswith("some PSM output")


def test_acceleo_stage_uses_acceleo_system_prompt_and_atl_output():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("Acceleo template")) as mock_chat:
        result = acceleo_stage({"atl_output": "some ATL output"})

    assert result == "Acceleo template"
    messages = mock_chat.call_args.args[0]
    assert "Acceleo" in messages[0]["content"]
    assert messages[1]["content"].startswith("some ATL output")


def test_gen_stage_combines_all_previous_outputs():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("Final summary")) as mock_chat:
        result = gen_stage({
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


def test_pim_stage_uses_pim_system_prompt_and_docs_output():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PIM description")) as mock_chat:
        result = pim_stage({"docs_output": "Fetched 3 pages about GitLab CI."})

    assert result == "PIM description"
    messages = mock_chat.call_args.args[0]
    assert messages[0]["role"] == "system"
    assert "PIM" in messages[0]["content"]
    assert messages[1]["content"].startswith("Fetched 3 pages about GitLab CI.")


def test_pim_stage_falls_back_to_platform_description_without_docs_output():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PIM description")) as mock_chat:
        pim_stage({"platform_description": "A GitLab CI platform"})

    assert mock_chat.call_args.args[0][1]["content"].startswith("A GitLab CI platform")


def test_pim_stage_prefers_serialization_output_over_docs_output():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PIM description")) as mock_chat:
        pim_stage({
            "serialization_output": "## Pipeline\n- labeled fragment",
            "docs_output": "raw docs",
        })

    assert mock_chat.call_args.args[0][1]["content"].startswith("## Pipeline\n- labeled fragment")


def test_psm_stage_prefers_pim_output_over_docs_output():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM description")) as mock_chat:
        psm_stage({"pim_output": "PIM: jobs/stages/triggers", "docs_output": "raw docs"})

    assert mock_chat.call_args.args[0][1]["content"].startswith("PIM: jobs/stages/triggers")


def test_psm_stage_falls_back_to_docs_output_without_pim_output():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM description")) as mock_chat:
        psm_stage({"docs_output": "raw docs"})

    assert mock_chat.call_args.args[0][1]["content"].startswith("raw docs")
