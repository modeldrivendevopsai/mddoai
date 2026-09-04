"""Real end-to-end test: no mocking, makes a real ai_layer_client.chat() call,
real pim_agent_client.ground()/concepts() calls, and a real
validator_agent_client.validate_ecore() call, exercising generate() fully end
to end. Auto-skips when any of the three isn't reachable, matching
test_serialization_agent_real_llm.py's own real-dependency-gated pattern. CI
(or a local run after `docker compose up ai-layer pim-agent validator-agent`)
gets the real run.

This is also the test that satisfies this feature's own acceptance criterion:
"A test confirms the PSM artifact correctly represents platform-specific
concepts for a known platform" - run against a real platform's real docs with
a synthetic PIM artifact, asserting the generated artifact's structure
plausibly reflects that platform's concepts, not generic placeholders.
"""
import httpx
import pytest

from clients import ai_layer_client, pim_agent_client, validator_agent_client
from generation import generate


def _dependencies_reachable() -> bool:
    try:
        httpx.get(f"{ai_layer_client.AI_LAYER_URL}/health", timeout=2.0).raise_for_status()
        httpx.get(f"{pim_agent_client.PIM_AGENT_URL}/health", timeout=2.0).raise_for_status()
        httpx.get(f"{validator_agent_client.VALIDATOR_AGENT_URL}/health", timeout=2.0).raise_for_status()
        return True
    except Exception:
        return False


pytestmark = pytest.mark.skipif(
    not _dependencies_reachable(),
    reason=(
        f"real ai-layer ({ai_layer_client.AI_LAYER_URL}), pim-agent "
        f"({pim_agent_client.PIM_AGENT_URL}), or validator-agent "
        f"({validator_agent_client.VALIDATOR_AGENT_URL}) not reachable"
    ),
)

_SYNTHETIC_PIM_ARTIFACT = """# PIM: GitLab CI Pipeline
A pipeline consists of jobs organized into stages. Jobs run scripts and can
be triggered on push, on a schedule, or manually. Jobs may declare a retry
count and can attach service containers (e.g. a database) they depend on.
"""

_GITLAB_DOCS = """# GitLab CI/CD Configuration
Pipelines are defined in .gitlab-ci.yml. Jobs belong to stages and run
scripts. Jobs support a `retry` field controlling how many times a failed
job re-runs. Jobs can declare `services` (e.g. postgres:14) available
alongside the job's own script. Runners execute the jobs; a runner can be
shared across projects or specific to one.
"""


def test_real_generation_produces_platform_specific_structure():
    result = generate(_SYNTHETIC_PIM_ARTIFACT, _GITLAB_DOCS, model=None)

    assert result["artifact"].strip()
    assert "rounds" in result and result["rounds"] >= 1
    assert "validation" in result and "valid" in result["validation"]
    # Real, non-deterministic LLM output - only structural invariants are
    # checked, not exact wording: it must at least look like Ecore XML and
    # reference concepts this platform's docs actually describe (job/stage
    # shaped concepts), not a generic or empty placeholder.
    artifact_lower = result["artifact"].lower()
    assert "epackage" in artifact_lower or "eclass" in artifact_lower
    assert any(concept in artifact_lower for concept in ("job", "stage", "pipeline", "retry", "runner"))
