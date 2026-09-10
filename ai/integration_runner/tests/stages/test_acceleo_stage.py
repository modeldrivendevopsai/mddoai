"""integration_runner/stages/acceleo/agent.py's real (non-placeholder) unit
tests: acceleo_stage is a thin proxy to acceleo_agent's real /generate
capability, mirroring the shape of test_atl_stage.py/test_psm_stage.py. No
real HTTP call - clients/acceleo_agent_client.run_acceleo is mocked.

Tests verify:
  1. acceleo_stage returns a (str, dict) tuple: the artifact, plus every
     other key from acceleo_agent's response.
  2. docs precedence: serialization_output first, falling back to
     docs_output, then platform_description - the same real chain
     stages/psm/agent.py's own docs fallback uses.
  3. psm_output/platform_description/constraints/model are forwarded from
     context.
  4. Given a run_id, acceleo_stage reserves its own attempt directory
     first and forwards its own stage name plus that attempt's name to
     run_acceleo(), so acceleo_agent's own real compiled .emtl module nests
     inside that attempt directory instead of landing as an unlinked
     sibling of it.
  5. The per-run "Mock" override forwards through to run_acceleo()'s own
     mock kwarg, defaulting to False when absent.
"""
import json
from unittest.mock import patch

from clients import acceleo_agent_client
from integration_runner.stages.acceleo.agent import acceleo_stage


def _generation_response(artifact="[module generate('x')] ..."):
    return {
        "artifact": artifact,
        "prompt": {"psm_ecore": "x", "platform_docs": "y", "acceleo_example": "z", "constraints": ""},
        "validation": {"valid": True},
        "rounds": 1,
        "preset": "default",
        "prompt_version": None,
    }


def test_returns_artifact_and_extra_data_as_a_tuple():
    with patch.object(acceleo_agent_client, "run_acceleo", return_value=_generation_response()) as mock_run:
        output, extra = acceleo_stage({"psm_output": "<psm/>", "platform_description": "TeamCity"})

    assert output == "[module generate('x')] ..."
    assert extra["prompt"] == {"psm_ecore": "x", "platform_docs": "y", "acceleo_example": "z", "constraints": ""}
    assert extra["validation"] == {"valid": True}
    assert extra["rounds"] == 1
    assert "artifact" not in extra
    mock_run.assert_called_once()


def test_prefers_serialization_output_over_docs_output():
    with patch.object(acceleo_agent_client, "run_acceleo", return_value=_generation_response()) as mock_run:
        acceleo_stage({
            "psm_output": "<psm/>",
            "serialization_output": "labeled docs",
            "docs_output": "raw docs",
            "platform_description": "TeamCity",
        })

    args, kwargs = mock_run.call_args
    assert args[1] == "labeled docs"  # docs positional arg


def test_falls_back_to_docs_output_without_serialization_output():
    with patch.object(acceleo_agent_client, "run_acceleo", return_value=_generation_response()) as mock_run:
        acceleo_stage({"psm_output": "<psm/>", "docs_output": "raw docs", "platform_description": "TeamCity"})

    args, kwargs = mock_run.call_args
    assert args[1] == "raw docs"


def test_falls_back_to_platform_description_without_docs_output():
    with patch.object(acceleo_agent_client, "run_acceleo", return_value=_generation_response()) as mock_run:
        acceleo_stage({"platform_description": "A GitLab CI platform"})

    args, kwargs = mock_run.call_args
    assert args[1] == "A GitLab CI platform"


def test_defaults_to_empty_psm_artifact_without_psm_output():
    with patch.object(acceleo_agent_client, "run_acceleo", return_value=_generation_response()) as mock_run:
        acceleo_stage({"platform_description": "TeamCity"})

    args, kwargs = mock_run.call_args
    assert args[0] == ""


def test_forwards_constraints_and_model():
    with patch.object(acceleo_agent_client, "run_acceleo", return_value=_generation_response()) as mock_run:
        acceleo_stage({
            "platform_description": "TeamCity",
            "constraints": {"acceleo": ["Fix: bad thing"]},
            "model": "gemini-flash",
        })

    args, kwargs = mock_run.call_args
    assert kwargs["constraints"] == ["Fix: bad thing"]
    assert kwargs["model"] == "gemini-flash"


def test_forwards_no_stage_or_attempt_without_a_run_id():
    with patch.object(acceleo_agent_client, "run_acceleo", return_value=_generation_response()) as mock_run:
        acceleo_stage({"platform_description": "TeamCity"})

    assert mock_run.call_args.kwargs.get("stage") is None
    assert mock_run.call_args.kwargs.get("attempt") is None


def test_reserves_and_forwards_its_own_stage_and_attempt_name(tmp_path):
    with patch.object(acceleo_agent_client, "run_acceleo", return_value=_generation_response()) as mock_run:
        acceleo_stage({"platform_description": "TeamCity", "run_id": "run-1"})

    assert mock_run.call_args.kwargs.get("stage") == "acceleo"
    assert mock_run.call_args.kwargs.get("attempt") == "attempt_1"
    assert (tmp_path / "runs" / "run-1" / "acceleo" / "attempt_1").is_dir()


def test_forwards_mock_override_from_context():
    with patch.object(acceleo_agent_client, "run_acceleo", return_value=_generation_response()) as mock_run:
        acceleo_stage({"platform_description": "TeamCity", "mock": True})

    assert mock_run.call_args.kwargs.get("mock") is True


def test_defaults_mock_to_false_when_absent():
    with patch.object(acceleo_agent_client, "run_acceleo", return_value=_generation_response()) as mock_run:
        acceleo_stage({"platform_description": "TeamCity"})

    assert mock_run.call_args.kwargs.get("mock") is False


def test_forwards_the_second_reserved_attempt_name_on_retry(tmp_path):
    with patch.object(acceleo_agent_client, "run_acceleo", return_value=_generation_response()) as mock_run:
        acceleo_stage({"platform_description": "TeamCity", "run_id": "run-1"})  # attempt_1
        acceleo_stage({"platform_description": "TeamCity", "run_id": "run-1"})  # attempt_2

    assert mock_run.call_args_list[0].kwargs.get("attempt") == "attempt_1"
    assert mock_run.call_args_list[1].kwargs.get("attempt") == "attempt_2"


def test_persists_the_real_prompt_and_version_from_the_response(tmp_path):
    response = _generation_response()
    response["prompt_version"] = "20260101T000000.000000Z-abcdef"
    with patch.object(acceleo_agent_client, "run_acceleo", return_value=response):
        acceleo_stage({"platform_description": "TeamCity", "run_id": "run-1"})

    attempt_dir = tmp_path / "runs" / "run-1" / "acceleo" / "attempt_1"
    prompt_record = json.loads((attempt_dir / "prompt.json").read_text(encoding="utf-8"))
    assert prompt_record["prompt"] == response["prompt"]
    assert prompt_record["prompt_version"] == "20260101T000000.000000Z-abcdef"
