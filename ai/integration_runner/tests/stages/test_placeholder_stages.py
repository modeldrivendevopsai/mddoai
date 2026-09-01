"""integration_runner/stages/generation/agent.py unit tests: the one
remaining LLM-prompt placeholder stage agent (a stage-specific system
prompt, stages/_shared.py's constraints_note(), one ai_layer_client.chat()
call) — pim/psm/atl/acceleo switched to fixed mock content + a real
validator-agent call instead, see test_mock_validated_stages.py for their
own tests. The one real agent in this package (stages/docs/agent.py) has
its own tests too, see test_docs_agent.py. The stage_agents dict assembly
itself has its own tests, see test_stages_registry.py. No real API calls —
clients/ai_layer_client.chat is mocked.

Tests verify gen_stage() calls chat() with its own system prompt, forwards
the caller's chosen model (or None), incorporates constraints, and returns
the response's "content" field, combining psm/atl/acceleo's prior outputs
into the prompt.
"""
from unittest.mock import patch

from clients import ai_layer_client
from integration_runner.stages.generation.agent import gen_stage
from helpers import ok_response


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


def test_gen_stage_forwards_the_chosen_model_from_context():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("Final summary")) as mock_chat:
        gen_stage({
            "psm_output": "psm out", "atl_output": "atl out", "acceleo_output": "acceleo out",
            "model": "gemini-flash",
        })

    assert mock_chat.call_args.kwargs["model"] == "gemini-flash"


def test_gen_stage_passes_none_model_when_none_chosen():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("Final summary")) as mock_chat:
        gen_stage({"psm_output": "psm out", "atl_output": "atl out", "acceleo_output": "acceleo out"})

    assert mock_chat.call_args.kwargs["model"] is None


def test_gen_stage_incorporates_constraints():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("Final summary")) as mock_chat:
        gen_stage({
            "psm_output": "psm out", "atl_output": "atl out", "acceleo_output": "acceleo out",
            "constraints": {"generation": ["Mention the rollback step"]},
        })

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "Mention the rollback step" in user_content
