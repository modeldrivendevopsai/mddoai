"""published_runner.run_published: the model goes through swarch-agent, then the
platform's transformation and template through execution-agent. Only the two
HTTP clients are mocked; the registry is real, on a temporary folder."""
from unittest.mock import patch

import httpx
import pytest

from clients.agent_service_errors import AgentServiceError
from platform_catalog import published_platform
from platform_registry import Artifacts, PlatformRegistry
from published_runner import PublishedRunError, run_published

ARTIFACTS = Artifacts(metamodel="<ecore/>", transformation="<atl/>", template="<mtl/>")


@pytest.fixture
def registry(tmp_path):
    return PlatformRegistry(tmp_path / "platforms")


@pytest.fixture
def platform(registry):
    version = registry.publish(name="Woodpecker", output_filename=".woodpecker/pipeline.yaml", output_model_name="WoodMM",
                               artifacts=ARTIFACTS, source_run_id=None, reserved_ids=set())
    return published_platform(version)


def test_the_three_steps_run_in_order_with_the_published_files(registry, platform):
    with patch("published_runner.swarch_agent_client.to_pim", return_value="<pim/>") as to_pim, \
            patch("published_runner.execution_agent_client.execute_atl", return_value="<psm/>") as atl, \
            patch("published_runner.execution_agent_client.execute_acceleo",
                  return_value={".woodpecker/pipeline.yaml": "steps: []\n"}) as acceleo:
        result = run_published("<swarch/>", platform, registry)

    to_pim.assert_called_once_with("<swarch/>")
    atl.assert_called_once_with("<atl/>", "<pim/>", "<ecore/>", "WoodMM")
    acceleo.assert_called_once_with("<mtl/>", "<psm/>", "<ecore/>")
    assert result["pipeline"] == "steps: []\n"
    assert [(i["id"], i["filename"], i["content"]) for i in result["intermediates"]] == [
        ("pim", "PipelinePIM.pimmm", "<pim/>"),
        ("platform_model", "PipelinePSM.xmi", "<psm/>"),
    ]
    assert isinstance(result["duration_ms"], int)


@pytest.mark.parametrize("error", [AgentServiceError(422, "bad"), httpx.ConnectError("refused")])
def test_a_failing_step_is_a_published_run_error(registry, platform, error):
    with patch("published_runner.swarch_agent_client.to_pim", side_effect=error):
        with pytest.raises(PublishedRunError):
            run_published("<swarch/>", platform, registry)


def test_a_template_that_does_not_produce_the_platforms_file_is_an_error(registry, platform):
    with patch("published_runner.swarch_agent_client.to_pim", return_value="<pim/>"), \
            patch("published_runner.execution_agent_client.execute_atl", return_value="<psm/>"), \
            patch("published_runner.execution_agent_client.execute_acceleo", return_value={"other.yml": "x"}):
        with pytest.raises(PublishedRunError, match="other.yml"):
            run_published("<swarch/>", platform, registry)


def _steps(acceleo_result):
    return (
        patch("published_runner.swarch_agent_client.to_pim", return_value="<pim/>"),
        patch("published_runner.execution_agent_client.execute_atl", return_value="<psm/>"),
        patch("published_runner.execution_agent_client.execute_acceleo", return_value=acceleo_result),
    )


def test_a_version_published_since_the_list_was_read_still_runs_the_version_listed(registry, platform):
    newer = Artifacts(metamodel="<ecore2/>", transformation="<atl2/>", template="<mtl2/>")
    registry.publish(name="Woodpecker", output_filename=".woodpecker/pipeline.yaml", output_model_name="WoodMM",
                     artifacts=newer, source_run_id=None, reserved_ids=set())
    to_pim, atl, acceleo = _steps({".woodpecker/pipeline.yaml": "steps: []\n"})

    with to_pim, atl as atl_mock, acceleo:
        result = run_published("<swarch/>", platform, registry)

    assert atl_mock.call_args.args[0] == "<atl/>"
    assert result["pipeline"] == "steps: []\n"


def test_a_version_whose_files_cannot_be_read_is_a_published_run_error(registry, platform, tmp_path):
    (tmp_path / "platforms" / platform.id / "1" / "transformation.atl").unlink()

    with patch("published_runner.swarch_agent_client.to_pim") as to_pim:
        with pytest.raises(PublishedRunError, match="could not read"):
            run_published("<swarch/>", platform, registry)
    to_pim.assert_not_called()


def test_an_unknown_version_is_a_published_run_error(registry, platform):
    from dataclasses import replace

    with pytest.raises(PublishedRunError, match="not available"):
        run_published("<swarch/>", replace(platform, version=9), registry)


@pytest.mark.parametrize("acceleo_result", [["not", "a", "dict"], None])
def test_an_answer_of_the_wrong_shape_is_a_published_run_error(registry, platform, acceleo_result):
    to_pim, atl, acceleo = _steps(acceleo_result)

    with to_pim, atl, acceleo:
        with pytest.raises(PublishedRunError):
            run_published("<swarch/>", platform, registry)


def test_a_client_answer_missing_its_field_is_a_published_run_error(registry, platform):
    with patch("published_runner.swarch_agent_client.to_pim", side_effect=KeyError("pim")):
        with pytest.raises(PublishedRunError):
            run_published("<swarch/>", platform, registry)


def test_a_pipeline_over_the_size_limit_is_a_published_run_error(registry, platform, monkeypatch):
    monkeypatch.setattr("published_runner.MAX_PIPELINE_BYTES", 4)
    to_pim, atl, acceleo = _steps({".woodpecker/pipeline.yaml": "steps: []\n"})

    with to_pim, atl, acceleo:
        with pytest.raises(PublishedRunError, match="exceeds"):
            run_published("<swarch/>", platform, registry)
