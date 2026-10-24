"""Real end-to-end test: no mocking of the engine. Spawns a real java
subprocess against the real built main/ distribution. Auto-skips when a JDK
and that distribution aren't available, so the fast suite (plain `pytest`)
runs standalone anywhere. Locally, run `cd main && ./gradlew installDist`
first; GENERATOR_LIB_DIR/ENGINE_TRANSFORMATIONS_DIR can point elsewhere.
"""
import shutil
from concurrent.futures import ThreadPoolExecutor
from pathlib import Path

import pytest

import engine_runner
from engine_runner import run_engine
from pipeline_checks import check_gitlab_pipeline
from platforms import PLATFORMS

FIXTURES = Path(__file__).parent / "fixtures"

# Checks the exact paths the real code resolves (see engine_runner.py), not a
# separately hardcoded copy of their defaults.
pytestmark = pytest.mark.skipif(
    not (shutil.which("java") and engine_runner.LIB_DIR.exists() and engine_runner.TRANSFORMATIONS_DIR.exists()),
    reason="real JDK + built main/ distribution not available",
)

GITLAB = PLATFORMS["gitlab"]


def _normalized(text: str) -> str:
    return text.replace("\r\n", "\n")


def test_generates_exactly_what_the_engines_own_end_to_end_test_expects():
    model = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")
    expected = (FIXTURES / "expected-gitlab-ci.yml").read_text(encoding="utf-8")

    result = run_engine(model, GITLAB)

    assert _normalized(result["pipeline"]) == _normalized(expected)


def test_returns_both_intermediate_models():
    model = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")

    result = run_engine(model, GITLAB)

    assert [m["id"] for m in result["intermediates"]] == ["pim", "platform_model"]
    assert all(m["content"].strip() for m in result["intermediates"])


def test_the_real_output_passes_the_pipeline_checks():
    model = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")

    result = run_engine(model, GITLAB)

    assert check_gitlab_pipeline(result["pipeline"])["valid"] is True


def test_concurrent_runs_do_not_overwrite_each_others_intermediates():
    # The engine writes intermediates to a fixed relative path; this is the
    # real proof that per-call scratch directories keep concurrent callers apart.
    base = (FIXTURES / "valid.swarch").read_text(encoding="utf-8")
    models = [base.replace('name="framework-chatbot"', f'name="app-{i}"') for i in range(4)]

    with ThreadPoolExecutor(max_workers=4) as pool:
        results = list(pool.map(lambda m: run_engine(m, GITLAB), models))

    for i, result in enumerate(results):
        assert f"app-{i}" in result["pipeline"], f"run {i} got another run's output"
        assert f"app-{i}" in result["intermediates"][0]["content"]
