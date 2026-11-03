"""Real end-to-end test: nothing mocked. Runs the shipped GitLab platform
through the real swarch-agent and execution-agent. Auto-skips when they are
not reachable, so the fast suite (plain `pytest`) runs standalone anywhere. In
the Docker stack, run it inside the pipeline-generator container, where
docker-compose.yml sets the services' addresses.
"""
from concurrent.futures import ThreadPoolExecutor
from pathlib import Path

import httpx
import pytest

from clients import execution_agent_client, swarch_agent_client
from pipeline_checks import check_gitlab_pipeline
from platform_runner import run_platform

FIXTURES = Path(__file__).parent / "fixtures"


def _reachable(url: str) -> bool:
    try:
        return httpx.get(f"{url}/health", timeout=2).is_success
    except httpx.HTTPError:
        return False


pytestmark = pytest.mark.skipif(
    not (_reachable(swarch_agent_client.SWARCH_AGENT_URL) and _reachable(execution_agent_client.EXECUTION_AGENT_URL)),
    reason="swarch-agent and execution-agent are not reachable",
)


def _normalized(text: str) -> str:
    """Line endings and trailing spaces ignored: the template's indented call
    leaves two spaces on one blank line, which no YAML reader can see."""
    return "\n".join(line.rstrip() for line in text.replace("\r\n", "\n").split("\n"))


@pytest.fixture
def gitlab(registry):
    return registry.get("gitlab")


def test_generates_what_the_engines_own_end_to_end_test_expects(gitlab, registry):
    model = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")
    expected = (FIXTURES / "expected-gitlab-ci.yml").read_text(encoding="utf-8")

    result = run_platform(model, gitlab, registry)

    assert _normalized(result["pipeline"]) == _normalized(expected)


def test_returns_both_intermediate_models(gitlab, registry):
    model = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")

    result = run_platform(model, gitlab, registry)

    assert [m["id"] for m in result["intermediates"]] == ["pim", "platform_model"]
    assert all(m["content"].strip() for m in result["intermediates"])


def test_the_real_output_passes_the_pipeline_checks(gitlab, registry):
    model = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")

    result = run_platform(model, gitlab, registry)

    assert check_gitlab_pipeline(result["pipeline"])["valid"] is True


def test_concurrent_runs_do_not_mix_up_each_others_output(gitlab, registry):
    base = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")
    models = [base.replace('name="framework-chatbot"', f'name="app-{i}"') for i in range(4)]

    with ThreadPoolExecutor(max_workers=4) as pool:
        results = list(pool.map(lambda m: run_platform(m, gitlab, registry), models))

    for i, result in enumerate(results):
        assert f"app-{i}" in result["pipeline"], f"run {i} got another run's output"
        assert f"app-{i}" in result["intermediates"][0]["content"]
