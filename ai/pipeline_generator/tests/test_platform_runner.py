from unittest.mock import patch

import httpx
import pytest

import platform_runner
from clients.agent_service_errors import AgentServiceError
from platform_runner import PlatformRunError, run_platform
from platforms import Platform

PLATFORM = Platform("x", "X CI", 3, ".x-ci.yml", "XMM")


class _Registry:
    """Stands in for the registry: this module only asks it for the files."""

    def __init__(self, artifacts=None, error=None):
        self._artifacts = artifacts
        self._error = error

    def artifacts(self, platform):
        if self._error:
            raise self._error
        return self._artifacts


class _Artifacts:
    metamodel = "<ecore/>"
    transformation = "module t;"
    template = "[module g/]"


def _run(registry=None, *, to_pim="<pim/>", atl="<psm/>", acceleo=None, content="<swarch/>"):
    acceleo = {".x-ci.yml": "stages: [build]\n"} if acceleo is None else acceleo
    with patch("platform_runner.swarch_agent_client.to_pim", side_effect=to_pim if isinstance(to_pim, Exception) else None, return_value=to_pim) as pim, \
            patch("platform_runner.execution_agent_client.execute_atl", side_effect=atl if isinstance(atl, Exception) else None, return_value=atl) as run_atl, \
            patch("platform_runner.execution_agent_client.execute_acceleo", return_value=acceleo) as run_acceleo:
        result = run_platform(content, PLATFORM, registry or _Registry(_Artifacts()))
    return result, pim, run_atl, run_acceleo


def test_runs_the_three_steps_in_order_with_the_platforms_own_files():
    result, pim, run_atl, run_acceleo = _run()

    pim.assert_called_once_with("<swarch/>")
    run_atl.assert_called_once_with("module t;", "<pim/>", "<ecore/>", "XMM")
    run_acceleo.assert_called_once_with("[module g/]", "<psm/>", "<ecore/>")
    assert result["pipeline"] == "stages: [build]\n"


def test_returns_both_models_it_passed_through_under_fixed_names():
    result, *_ = _run()

    assert result["intermediates"] == [
        {"id": "pim", "title": "Platform-independent model", "filename": "PipelinePIM.pimmm", "content": "<pim/>"},
        {"id": "platform_model", "title": "X CI model", "filename": "PipelinePSM.xmi", "content": "<psm/>"},
    ]
    assert isinstance(result["duration_ms"], int)


@pytest.mark.parametrize(
    "failure",
    [AgentServiceError(422, "ATL runtime failure: boom"), httpx.ConnectError("down"), KeyError("output_xmi")],
)
def test_a_failing_step_is_reported_as_a_run_error(failure):
    with pytest.raises(PlatformRunError, match="generating for x failed"):
        _run(atl=failure)


def test_a_failing_first_step_is_reported_too():
    with pytest.raises(PlatformRunError):
        _run(to_pim=AgentServiceError(422, {"message": "invalid model"}))


@pytest.mark.parametrize("error", [FileNotFoundError("gone"), UnicodeDecodeError("utf-8", b"\xff", 0, 1, "bad")])
def test_files_that_cannot_be_read_are_a_run_error_and_no_step_runs(error):
    with patch("platform_runner.swarch_agent_client.to_pim") as pim:
        with pytest.raises(PlatformRunError, match="could not read the files"):
            run_platform("<swarch/>", PLATFORM, _Registry(error=error))

    pim.assert_not_called()


def test_a_template_that_makes_other_files_than_the_platforms_is_a_run_error():
    with pytest.raises(PlatformRunError, match="not .x-ci.yml"):
        _run(acceleo={"other.yml": "a: b"})


def test_a_template_that_returns_no_files_at_all_is_a_run_error():
    with pytest.raises(PlatformRunError, match="returned no files"):
        _run(acceleo=["not", "a", "dict"])


def test_a_pipeline_over_the_size_limit_is_a_run_error(monkeypatch):
    monkeypatch.setattr(platform_runner, "MAX_PIPELINE_BYTES", 5)

    with pytest.raises(PlatformRunError, match="exceeds 5 bytes"):
        _run(acceleo={".x-ci.yml": "123456"})


@pytest.mark.parametrize("which", ["pim", "platform_model"])
def test_a_model_over_the_size_limit_is_a_run_error_too(monkeypatch, which):
    monkeypatch.setattr(platform_runner, "MAX_PIPELINE_BYTES", 5)
    big = "1234567"
    kwargs = {"to_pim": big} if which == "pim" else {"atl": big}

    with pytest.raises(PlatformRunError, match="exceeds 5 bytes"):
        _run(acceleo={".x-ci.yml": "ok"}, **kwargs)
