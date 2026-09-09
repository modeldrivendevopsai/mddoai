"""integration_runner/stages/psm/agent.py's real (non-placeholder) unit
tests: psm_stage is a thin proxy to psm_agent's real /psm capability,
mirroring the shape of the other real stage's own test file
(test_docs_agent.py). No real HTTP call — clients/psm_agent_client.run_psm
is mocked.

Tests verify:
  1. psm_stage returns a (str, dict) tuple: the artifact, plus every other
     key from psm_agent's response.
  2. docs precedence: serialization_output first (the serialization stage's
     own labeled restructuring of the raw crawl), falling back to
     docs_output (the raw crawl), then platform_description. Independent
     of pim_output, a separate parameter with no fallback chain of its own.
  3. constraints and model are forwarded from context.
  4. Given a run_id, psm_stage reserves its own attempt directory first and
     forwards its own stage name plus that attempt's name to run_psm(), the
     same pattern atl_stage/acceleo_stage use, so psm_agent's own real
     compiled Ecore classes (produced deep inside its generation.py, once
     per regeneration round) nest inside that attempt directory instead of
     landing as an unlinked sibling of it - see stages/_validation.py's own
     reserve_attempt_dir() and ai/CLAUDE.md's "Second exception" section.
"""
from unittest.mock import patch

from clients import psm_agent_client
from integration_runner.stages.psm.agent import psm_stage


def _generation_response(artifact="<new-ecore/>"):
    return {
        "mode": "generation",
        "artifact": artifact,
        "prompt": {"psm_docs": "y", "psm_example": "z", "constraints": ""},
        "validation": {"valid": True},
        "rounds": 1,
    }


def test_returns_artifact_and_extra_data_as_a_tuple():
    with patch.object(psm_agent_client, "run_psm", return_value=_generation_response()) as mock_run:
        output, extra = psm_stage({"pim_output": "PIM: jobs/stages/triggers", "platform_description": "TeamCity"})

    assert output == "<new-ecore/>"
    assert extra["mode"] == "generation"
    assert extra["prompt"] == {"psm_docs": "y", "psm_example": "z", "constraints": ""}
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


def test_prefers_serialization_output_over_docs_output():
    with patch.object(psm_agent_client, "run_psm", return_value=_generation_response()) as mock_run:
        psm_stage({
            "serialization_output": "labeled docs",
            "docs_output": "raw docs",
            "platform_description": "TeamCity",
        })

    args, kwargs = mock_run.call_args
    assert args[2] == "labeled docs"  # docs positional arg


def test_falls_back_to_docs_output_without_serialization_output():
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


def test_forwards_no_stage_or_attempt_without_a_run_id():
    # No run_id means no run tree to reserve an attempt under - matching
    # pim_stage/atl_stage/acceleo_stage's own behavior in the same case.
    with patch.object(psm_agent_client, "run_psm", return_value=_generation_response()) as mock_run:
        psm_stage({"platform_description": "TeamCity", "pim_output": "pim"})

    assert mock_run.call_args.kwargs.get("stage") is None
    assert mock_run.call_args.kwargs.get("attempt") is None


def test_reserves_and_forwards_its_own_stage_and_attempt_name(tmp_path):
    # tmp_path here is the same instance conftest.py's own autouse
    # _isolated_validation_runs_dir fixture already redirected RUNS_DIR to
    # (fixtures requested by both the test and another fixture in the same
    # test share one instance) - no need to re-patch it here too.
    with patch.object(psm_agent_client, "run_psm", return_value=_generation_response()) as mock_run:
        psm_stage({"platform_description": "TeamCity", "pim_output": "pim", "run_id": "run-1"})

    assert mock_run.call_args.kwargs.get("stage") == "psm"
    assert mock_run.call_args.kwargs.get("attempt") == "attempt_1"
    # The reservation really happened on disk, not just a string the mock
    # received - the same real attempt_dir persist_attempt() then reuses.
    assert (tmp_path / "runs" / "run-1" / "psm" / "attempt_1").is_dir()


def test_forwards_mock_override_from_context():
    # The same per-run "Mock" override docs_stage's own context["mock"]
    # already reads (RerunOverrides.mock/StartRequest.mock) - defaults to
    # False when absent, same as every other real run.
    with patch.object(psm_agent_client, "run_psm", return_value=_generation_response()) as mock_run:
        psm_stage({"platform_description": "TeamCity", "pim_output": "pim", "mock": True})

    assert mock_run.call_args.kwargs.get("mock") is True


def test_defaults_mock_to_false_when_absent():
    with patch.object(psm_agent_client, "run_psm", return_value=_generation_response()) as mock_run:
        psm_stage({"platform_description": "TeamCity", "pim_output": "pim"})

    assert mock_run.call_args.kwargs.get("mock") is False


def test_forwards_the_second_reserved_attempt_name_on_retry(tmp_path):
    with patch.object(psm_agent_client, "run_psm", return_value=_generation_response()) as mock_run:
        psm_stage({"platform_description": "TeamCity", "pim_output": "pim", "run_id": "run-1"})  # attempt_1
        psm_stage({"platform_description": "TeamCity", "pim_output": "pim", "run_id": "run-1"})  # attempt_2

    assert mock_run.call_args_list[0].kwargs.get("attempt") == "attempt_1"
    assert mock_run.call_args_list[1].kwargs.get("attempt") == "attempt_2"
