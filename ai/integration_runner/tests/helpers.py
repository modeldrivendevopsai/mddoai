"""Shared test fixtures used across more than one test file in this
directory. Not a test file itself (no test_ prefix, pytest won't collect it).

Deliberately not imported from ai/orchestrator/tests/helpers.py: each
package's test suite is self-contained, matching ai/pim_agent/ and
ai/psm_agent/'s established pattern, rather than one package's tests
reaching into a sibling package's test directory."""
from unittest.mock import MagicMock

from integration_runner import pipeline


def ok_response(content):
    """A plain chat() response: agent text, no tool calls."""
    return {"content": content, "model": "test-model", "tool_calls": None}


def _fast_forward_to_generation(o: "pipeline.IntegrationRun") -> None:
    """Most stage-mechanics tests just need to be past docs (skip straight
    there by setting the index directly rather than mocking a real
    retrieval fetch), on a stage that still calls ai_layer_client.chat() —
    generation is the only one left since pim/psm/atl/acceleo switched to
    fixed mock content + real validator-agent calls (see each of their own
    agent.py). Named for the stage it lands on, not "generic placeholder",
    since it's no longer interchangeable with any of the other four."""
    o.current_stage_index = pipeline.STAGES.index("generation")


def _fast_forward_to(o: "pipeline.IntegrationRun", stage: str) -> None:
    """Like _fast_forward_to_generation, for a test that specifically needs
    a different stage (e.g. one of the mock-validated ones) rather than
    "the one remaining LLM stage"."""
    o.current_stage_index = pipeline.STAGES.index(stage)


def _validation_result(valid=True, issues=None, duration_ms=5, mode=None):
    """A validator-agent-shaped result dict, for tests that mock
    validator_agent_client.validate_ecore/validate_atl/validate_acceleo
    directly rather than the real HTTP call underneath them."""
    result = {"valid": valid, "issues": issues or [], "duration_ms": duration_ms}
    if mode is not None:
        result["mode"] = mode
    return result


def _psm_generation_result(artifact="<ecore:EPackage/>", valid=True):
    """A psm_agent_client.run_psm()-shaped result for generation mode — psm
    is the one mock-validated-looking stage whose real boundary isn't
    validator_agent_client directly (see stages/psm/agent.py's own
    docstring): it calls psm_agent_client.run_psm() instead, so any test
    that runs psm_stage() for real must mock THIS, not validate_ecore —
    mocking validate_ecore alone silently does nothing to intercept it and
    the call falls through to a real (likely unreachable) network request."""
    return {
        "mode": "generation",
        "artifact": artifact,
        "prompt": {"pim_ecore": "", "psm_docs": "", "psm_example": "", "constraints": ""},
        "validation": _validation_result(valid=valid),
        "rounds": 1,
    }


def _fake_fetch_response(pages=None, confidence=0.8):
    """A retrieval-shaped httpx response, for tests that mock
    retrieval_client.httpx directly to let docs_stage's real call through
    the mock."""
    pages = pages or [{
        "url": "https://example.com/docs", "success": True, "status_code": 200,
        "markdown": "# Docs\nSome real content.", "links": [],
    }]
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = {
        "seed_url": "https://example.com/docs",
        "pages": pages,
        "meta": {"confidence": confidence, "pages_crawled": len(pages), "depth_reached": 1, "pending_links": []},
    }
    return resp


def _fake_page_response(url="https://example.com/docs", success=True, markdown="# Docs\nSome real content."):
    """retrieval's real POST /fetch/page returns a bare Page dict, not a
    FetchResult, distinct from _fake_fetch_response()."""
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = {"url": url, "success": success, "status_code": 200 if success else 404, "markdown": markdown, "links": []}
    return resp
