"""conversion.to_pim: the model is checked first, then the transformation's
source text is read from its file and run through execution-agent. Only the
two HTTP clients are mocked, the real network boundary."""
from unittest.mock import patch

import pytest

import conversion
from clients.agent_service_errors import AgentServiceError

VALID = {"valid": True, "issues": [], "duration_ms": 5}
INVALID = {
    "valid": False,
    "issues": [{"severity": "ERROR", "message": "The feature 'name' is missing", "source": "/tmp/x/model.swarch"}],
    "duration_ms": 5,
}


@pytest.fixture
def transformation(tmp_path, monkeypatch):
    path = tmp_path / "swarch2pim.atl"
    path.write_text("create OUT : PIMMM from IN : SWArchMM;", encoding="utf-8")
    monkeypatch.setattr(conversion, "SWARCH2PIM_ATL_PATH", path)
    return path


def test_a_valid_model_is_run_through_the_transformation_read_from_its_file(transformation):
    with patch("conversion.validator_agent_client.validate_swarch", return_value=VALID), \
            patch("conversion.execution_agent_client.execute_atl_from_swarch", return_value="<pim/>") as execute:
        pim = conversion.to_pim("<swarch/>")

    assert pim == "<pim/>"
    execute.assert_called_once_with("create OUT : PIMMM from IN : SWArchMM;", "<swarch/>")


def test_the_transformation_file_is_read_on_every_call_so_a_replacement_takes_effect(transformation):
    with patch("conversion.validator_agent_client.validate_swarch", return_value=VALID), \
            patch("conversion.execution_agent_client.execute_atl_from_swarch", return_value="<pim/>") as execute:
        conversion.to_pim("<swarch/>")
        transformation.write_text("an improved transformation", encoding="utf-8")
        conversion.to_pim("<swarch/>")

    assert execute.call_args_list[1].args[0] == "an improved transformation"


def test_an_invalid_model_is_refused_before_anything_runs_and_keeps_only_severity_and_message(transformation):
    with patch("conversion.validator_agent_client.validate_swarch", return_value=INVALID), \
            patch("conversion.execution_agent_client.execute_atl_from_swarch") as execute:
        with pytest.raises(conversion.InvalidModelError) as caught:
            conversion.to_pim("<swarch/>")

    execute.assert_not_called()
    assert caught.value.issues == [{"severity": "ERROR", "message": "The feature 'name' is missing"}]


def test_the_validator_is_never_told_a_name_the_caller_chose(transformation):
    with patch("conversion.validator_agent_client.validate_swarch", return_value=VALID) as validate, \
            patch("conversion.execution_agent_client.execute_atl_from_swarch", return_value="<pim/>"):
        conversion.to_pim("<swarch/>")

    assert validate.call_args.args == ("<swarch/>", "model.swarch")


def test_a_failed_run_is_a_transformation_failure_whose_reason_is_logged_not_passed_on(transformation, caplog):
    reason = "Failed to load Software Architecture model: file:/tmp/atl-execute-swarch-123/input.swarch"
    with patch("conversion.validator_agent_client.validate_swarch", return_value=VALID), \
            patch("conversion.execution_agent_client.execute_atl_from_swarch", side_effect=AgentServiceError(422, reason)):
        with pytest.raises(conversion.TransformationFailedError) as caught:
            conversion.to_pim("<swarch/>")

    assert "/tmp/" not in str(caught.value)
    assert reason in caplog.text


@pytest.mark.parametrize("status", [400, 404, 409])
def test_any_other_answer_from_execution_agent_is_unavailable_not_the_models_fault(transformation, status):
    with patch("conversion.validator_agent_client.validate_swarch", return_value=VALID), \
            patch("conversion.execution_agent_client.execute_atl_from_swarch", side_effect=AgentServiceError(status, "Not Found")):
        with pytest.raises(conversion.TransformationUnavailableError):
            conversion.to_pim("<swarch/>")


def test_a_missing_transformation_file_is_reported_as_unavailable(tmp_path, monkeypatch):
    monkeypatch.setattr(conversion, "SWARCH2PIM_ATL_PATH", tmp_path / "missing.atl")

    with patch("conversion.validator_agent_client.validate_swarch", return_value=VALID):
        with pytest.raises(conversion.TransformationUnavailableError):
            conversion.to_pim("<swarch/>")


def test_an_empty_transformation_file_is_reported_as_unavailable_and_never_sent(transformation):
    transformation.write_text("  \n", encoding="utf-8")

    with patch("conversion.validator_agent_client.validate_swarch", return_value=VALID), \
            patch("conversion.execution_agent_client.execute_atl_from_swarch") as execute:
        with pytest.raises(conversion.TransformationUnavailableError):
            conversion.to_pim("<swarch/>")
    execute.assert_not_called()


def test_the_default_transformation_is_the_engines_own_swarch2pim_file(monkeypatch):
    import importlib

    import settings

    monkeypatch.delenv("SWARCH2PIM_ATL_PATH", raising=False)
    try:
        default = importlib.reload(settings).SWARCH2PIM_ATL_PATH
        if not default.is_file():
            pytest.skip("not running inside a full repository checkout")
        assert "module swarch2pim;" in default.read_text(encoding="utf-8")
    finally:
        monkeypatch.undo()
        importlib.reload(settings)
