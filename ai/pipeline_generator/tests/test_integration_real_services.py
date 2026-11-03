"""Real end-to-end test: nothing mocked. Runs the committed GitLab platform
through the real execution-agent. Auto-skips when it is not reachable, so the fast suite (plain `pytest`) runs standalone anywhere. In
the Docker stack, run it inside the pipeline-generator container, where
docker-compose.yml sets the services' addresses.
"""
from concurrent.futures import ThreadPoolExecutor
from pathlib import Path

import httpx
import pytest

from clients import execution_agent_client
from clients import validator_agent_client
from platform_runner import run_platform

FIXTURES = Path(__file__).parent / "fixtures"


def _reachable(url: str) -> bool:
    try:
        return httpx.get(f"{url}/health", timeout=2).is_success
    except httpx.HTTPError:
        return False


pytestmark = pytest.mark.skipif(
    not _reachable(execution_agent_client.EXECUTION_AGENT_URL),
    reason="execution-agent is not reachable",
)


def _normalized(text: str) -> str:
    """Line endings, trailing spaces and blank lines ignored: the template's
    indented call leaves a line of two spaces, which no YAML reader can see.
    (A blank line inside a multi-line script block would be ignored too; the
    fixture has none.)"""
    lines = (line.rstrip() for line in text.replace("\r\n", "\n").split("\n"))
    return "\n".join(line for line in lines if line)


@pytest.fixture
def gitlab(registry):
    return registry.get("gitlab")


def test_generates_the_expected_gitlab_pipeline(gitlab, registry):
    model = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")
    expected = (FIXTURES / "expected-gitlab-ci.yml").read_text(encoding="utf-8")

    result = run_platform(model, gitlab, registry)

    assert _normalized(result["pipeline"]) == _normalized(expected)


def test_returns_both_intermediate_models(gitlab, registry):
    model = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")

    result = run_platform(model, gitlab, registry)

    assert [m["id"] for m in result["intermediates"]] == ["pim", "platform_model"]
    assert all(m["content"].strip() for m in result["intermediates"])


@pytest.mark.skipif(not _reachable(validator_agent_client.VALIDATOR_AGENT_URL), reason="validator-agent is not reachable")
def test_the_real_output_passes_the_real_pipeline_checks(gitlab, registry):
    model = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")

    result = run_platform(model, gitlab, registry)
    checked = validator_agent_client.validate_pipeline(
        result["pipeline"], gitlab.output_filename, gitlab.name, registry.schema(gitlab)
    )

    assert checked["valid"] is True, checked
    assert [entry["status"] for entry in checked["checks"]] == ["pass"] * 2


def test_concurrent_runs_do_not_mix_up_each_others_output(gitlab, registry):
    base = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")
    models = [base.replace('name="framework-chatbot"', f'name="app-{i}"') for i in range(4)]

    with ThreadPoolExecutor(max_workers=4) as pool:
        results = list(pool.map(lambda m: run_platform(m, gitlab, registry), models))

    for i, result in enumerate(results):
        assert f"app-{i}" in result["pipeline"], f"run {i} got another run's output"
        assert f"app-{i}" in result["intermediates"][0]["content"]
