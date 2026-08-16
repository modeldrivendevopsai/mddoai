"""Real end-to-end test: no mocking, makes a real ai_layer_client.chat() call
against a real running ai-layer AND real pim_agent_client.ground()/concepts()
calls against a real running pim_agent, exercising serialization_agent()
fully end to end. Auto-skips when either isn't reachable, so the fast suite
(plain `pytest`) always runs standalone on any machine, matching
test_integration_real_jvm.py's own real-dependency-gated pattern. CI (or a
local run after `docker compose up ai-layer pim-agent`) gets the real run.

Real LLM output is non-deterministic, so this only asserts structural invariants
serialization_agent() must hold regardless of exact wording (every concept header
present, nothing crashes, at least one real fragment actually got labeled), not exact
concept assignments the way the mocked unit tests do.
"""
import httpx
import pytest

import serialization_agent
from clients import ai_layer_client, pim_agent_client


def _dependencies_reachable() -> bool:
    try:
        httpx.get(f"{ai_layer_client.AI_LAYER_URL}/health", timeout=2.0).raise_for_status()
        httpx.get(f"{pim_agent_client.PIM_AGENT_URL}/health", timeout=2.0).raise_for_status()
        return True
    except Exception:
        return False


pytestmark = pytest.mark.skipif(
    not _dependencies_reachable(),
    reason=(
        f"real ai-layer ({ai_layer_client.AI_LAYER_URL}) or pim-agent "
        f"({pim_agent_client.PIM_AGENT_URL}) not reachable"
    ),
)

_SAMPLE_DOCS = """# GitLab CI/CD Pipeline Configuration

A pipeline is the top-level construct for GitLab CI/CD, made up of jobs
that execute in stages. Jobs are the smallest unit of execution and run
scripts.

## Triggers

Pipelines can be triggered automatically when you push a commit, on a
schedule using cron syntax, or manually by a user.

## Runners

Jobs run on GitLab Runners (agents) that execute the defined script steps.
Runners can be shared or specific to a project, and can be Linux, Windows,
or macOS based.

## Services

You can attach a Docker container as a service to a job, for example to
run a PostgreSQL database alongside your test job.
"""


def test_real_extraction_and_labeling_against_a_real_llm():
    # Fetched over real HTTP, same as serialization_agent() itself does, not a
    # local package import: this test's whole point is exercising the real
    # pim_agent service boundary, not reaching across it.
    concepts = pim_agent_client.concepts()
    result = serialization_agent.serialization_agent({"docs_output": _SAMPLE_DOCS})

    # Structural contract must hold regardless of the real model's exact wording:
    # every concept header is always present, plus Unrecognized.
    for concept in concepts:
        assert f"## {concept}" in result
    assert "## Unrecognized" in result

    # The extraction call must have actually found and labeled something real from
    # this unambiguous sample - not every section can legitimately read "(none
    # found)", or extraction/labeling silently failed against the real model.
    assert "(none found)" not in result or result.count("(none found)") < len(concepts)
