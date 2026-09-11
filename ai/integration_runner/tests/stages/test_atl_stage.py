"""integration_runner/stages/atl/agent.py's real (non-placeholder) unit
tests: atl_stage is a thin proxy to atl_agent's real /generate capability,
mirroring the shape of test_psm_stage.py. No real HTTP call -
clients/atl_agent_client.run_atl is mocked.

Tests verify:
  1. atl_stage returns a (str, dict) tuple: the artifact, plus every other
     key from atl_agent's response.
  2. pim_output/psm_output/constraints/model are forwarded from context.
  3. Given a run_id, atl_stage reserves its own attempt directory first and
     forwards its own stage name plus that attempt's name to run_atl(), the
     same pattern psm_stage/acceleo_stage use, so atl_agent's own real
     compiled .asm bytecode (produced deep inside its generation.py, once
     per regeneration round) nests inside that attempt directory instead of
     landing as an unlinked sibling of it.
  4. The per-run "Mock" override forwards through to run_atl()'s own mock
     kwarg, defaulting to False when absent.
"""
from unittest.mock import patch

import pytest

from clients import atl_agent_client
from integration_runner.stages.atl.agent import atl_stage


def _generation_response(artifact="module pim2teamcity; ..."):
    return {
        "artifact": artifact,
        "prompt": {"pim_ecore": "x", "psm_ecore": "y", "atl_example": "z", "constraints": ""},
        "validation": {"valid": True},
        "rounds": 1,
        "prompt_version": None,
    }


def test_returns_artifact_and_extra_data_as_a_tuple():
    with patch.object(atl_agent_client, "run_atl", return_value=_generation_response()) as mock_run:
        output, extra = atl_stage({"pim_output": "<pim/>", "psm_output": "<psm/>"})

    assert output == "module pim2teamcity; ..."
    assert extra["prompt"] == {"pim_ecore": "x", "psm_ecore": "y", "atl_example": "z", "constraints": ""}
    assert extra["validation"] == {"valid": True}
    assert extra["rounds"] == 1
    assert "artifact" not in extra
    mock_run.assert_called_once()


def test_forwards_pim_and_psm_output():
    with patch.object(atl_agent_client, "run_atl", return_value=_generation_response()) as mock_run:
        atl_stage({"pim_output": "<pim/>", "psm_output": "<psm/>"})

    args, kwargs = mock_run.call_args
    assert args == ("<pim/>", "<psm/>")


def test_defaults_to_empty_artifacts_without_pim_or_psm_output():
    with patch.object(atl_agent_client, "run_atl", return_value=_generation_response()) as mock_run:
        atl_stage({})

    args, kwargs = mock_run.call_args
    assert args == ("", "")


def test_forwards_constraints_and_model():
    with patch.object(atl_agent_client, "run_atl", return_value=_generation_response()) as mock_run:
        atl_stage({
            "constraints": {"atl": ["Fix: bad thing"]},
            "model": "gemini-flash",
        })

    args, kwargs = mock_run.call_args
    assert kwargs["constraints"] == ["Fix: bad thing"]
    assert kwargs["model"] == "gemini-flash"


def test_forwards_no_stage_or_attempt_without_a_run_id():
    # No run_id means no run tree to reserve an attempt under - matching
    # psm_stage/acceleo_stage's own behavior in the same case.
    with patch.object(atl_agent_client, "run_atl", return_value=_generation_response()) as mock_run:
        atl_stage({})

    assert mock_run.call_args.kwargs.get("stage") is None
    assert mock_run.call_args.kwargs.get("attempt") is None


def test_reserves_and_forwards_its_own_stage_and_attempt_name(tmp_path):
    with patch.object(atl_agent_client, "run_atl", return_value=_generation_response()) as mock_run:
        atl_stage({"run_id": "run-1"})

    assert mock_run.call_args.kwargs.get("stage") == "atl"
    assert mock_run.call_args.kwargs.get("attempt") == "attempt_1"
    # The reservation really happened on disk, not just a string the mock
    # received - the same real attempt_dir persist_attempt() then reuses.
    assert (tmp_path / "runs" / "run-1" / "atl" / "attempt_1").is_dir()


def test_forwards_mock_override_from_context():
    with patch.object(atl_agent_client, "run_atl", return_value=_generation_response()) as mock_run:
        atl_stage({"mock": True})

    assert mock_run.call_args.kwargs.get("mock") is True


def test_defaults_mock_to_false_when_absent():
    with patch.object(atl_agent_client, "run_atl", return_value=_generation_response()) as mock_run:
        atl_stage({})

    assert mock_run.call_args.kwargs.get("mock") is False


def test_forwards_the_second_reserved_attempt_name_on_retry(tmp_path):
    with patch.object(atl_agent_client, "run_atl", return_value=_generation_response()) as mock_run:
        atl_stage({"run_id": "run-1"})  # attempt_1
        atl_stage({"run_id": "run-1"})  # attempt_2

    assert mock_run.call_args_list[0].kwargs.get("attempt") == "attempt_1"
    assert mock_run.call_args_list[1].kwargs.get("attempt") == "attempt_2"


def test_persists_the_real_prompt_and_version_from_the_response(tmp_path):
    import json

    response = _generation_response()
    response["prompt_version"] = "20260101T000000.000000Z-abcdef"
    with patch.object(atl_agent_client, "run_atl", return_value=response):
        atl_stage({"run_id": "run-1"})

    attempt_dir = tmp_path / "runs" / "run-1" / "atl" / "attempt_1"
    prompt_record = json.loads((attempt_dir / "prompt.json").read_text(encoding="utf-8"))
    assert prompt_record["prompt"] == response["prompt"]
    assert prompt_record["prompt_version"] == "20260101T000000.000000Z-abcdef"


def test_a_raising_run_atl_leaves_no_orphan_attempt_dir(tmp_path):
    # A transient atl_agent failure (its validator round-trip times out, a
    # network blip) between reserving the attempt directory and persisting a
    # result must not strand an empty attempt_1/ or push the next real
    # attempt to attempt_2.
    with patch.object(atl_agent_client, "run_atl", side_effect=RuntimeError("atl_agent unreachable")):
        with pytest.raises(RuntimeError, match="atl_agent unreachable"):
            atl_stage({"run_id": "run-1"})

    stage_dir = tmp_path / "runs" / "run-1" / "atl"
    assert not (stage_dir / "attempt_1").exists()
    assert not (tmp_path / "runs" / "run-1" / "manifest.json").exists()
    with patch.object(atl_agent_client, "run_atl", return_value=_generation_response()):
        atl_stage({"run_id": "run-1"})
    assert (stage_dir / "attempt_1").is_dir()
