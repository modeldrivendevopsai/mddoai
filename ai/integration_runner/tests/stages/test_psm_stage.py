"""integration_runner/stages/psm/agent.py's real (non-placeholder) unit
tests: psm_stage is a thin proxy to psm_agent's real /psm capability,
mirroring the shape of the other real stage's own test file
(test_docs_agent.py). No real HTTP call — clients/psm_agent_client.run_psm
is mocked.

Tests verify:
  1. psm_stage returns a (str, dict) tuple: the artifact, plus every other
     key from psm_agent's response.
  2. Input precedence: pim_output first, falling back to docs_output, then
     platform_description — preserving the placeholder agent's own
     already-tested precedence (see this repo's own CLAUDE.md: replacing a
     placeholder keeps its established behavior, doesn't quietly regress it).
  3. constraints and model are forwarded from context.
"""
from unittest.mock import patch

from clients import psm_agent_client
from integration_runner.stages.psm.agent import psm_stage


def _generation_response(artifact="<new-ecore/>"):
    return {
        "mode": "generation",
        "artifact": artifact,
        "prompt": {"pim_ecore": "x", "psm_docs": "y", "psm_example": "z", "constraints": ""},
        "validation": {"valid": True},
        "rounds": 1,
    }


def test_returns_artifact_and_extra_data_as_a_tuple():
    with patch.object(psm_agent_client, "run_psm", return_value=_generation_response()) as mock_run:
        output, extra = psm_stage({"pim_output": "PIM: jobs/stages/triggers", "platform_description": "TeamCity"})

    assert output == "<new-ecore/>"
    assert extra["mode"] == "generation"
    assert extra["prompt"] == {"pim_ecore": "x", "psm_docs": "y", "psm_example": "z", "constraints": ""}
    assert extra["validation"] == {"valid": True}
    assert extra["rounds"] == 1
    assert "artifact" not in extra
    mock_run.assert_called_once()


def test_prefers_pim_output_over_docs_output():
    with patch.object(psm_agent_client, "run_psm", return_value=_generation_response()) as mock_run:
        psm_stage({
            "pim_output": "PIM: jobs/stages/triggers",
            "docs_output": "raw docs",
            "platform_description": "TeamCity",
        })

    args, kwargs = mock_run.call_args
    assert args[1] == "PIM: jobs/stages/triggers"  # pim_artifact positional arg


def test_falls_back_to_docs_output_without_pim_output():
    with patch.object(psm_agent_client, "run_psm", return_value=_generation_response()) as mock_run:
        psm_stage({"docs_output": "raw docs", "platform_description": "TeamCity"})

    args, kwargs = mock_run.call_args
    assert args[2] == "raw docs"  # docs positional arg


def test_falls_back_to_platform_description_without_docs_output():
    with patch.object(psm_agent_client, "run_psm", return_value=_generation_response()) as mock_run:
        psm_stage({"platform_description": "A GitLab CI platform"})

    args, kwargs = mock_run.call_args
    assert args[2] == "A GitLab CI platform"


def test_forwards_platform_description_constraints_and_model():
    with patch.object(psm_agent_client, "run_psm", return_value=_generation_response()) as mock_run:
        psm_stage({
            "platform_description": "TeamCity",
            "pim_output": "pim",
            "constraints": {"psm": ["Fix: bad thing"]},
            "model": "gemini-flash",
        })

    args, kwargs = mock_run.call_args
    assert args[0] == "TeamCity"
    assert kwargs["constraints"] == ["Fix: bad thing"]
    assert kwargs["model"] == "gemini-flash"
