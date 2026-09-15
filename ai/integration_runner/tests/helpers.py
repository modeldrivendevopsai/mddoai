"""Shared test fixtures used across more than one test file in this
directory. Not a test file itself (no test_ prefix, pytest won't collect it).

Deliberately not imported from ai/orchestrator/tests/helpers.py: each
package's test suite is self-contained, matching ai/pim_agent/ and
ai/psm_agent/'s established pattern, rather than one package's tests
reaching into a sibling package's test directory."""
import tempfile
from contextlib import contextmanager
from pathlib import Path
from unittest.mock import MagicMock, patch

from clients import execution_agent_client
from integration_runner import pipeline
from integration_runner.stages.generation import agent as generation_agent


def _fast_forward_to_generation(o: "pipeline.IntegrationRun") -> None:
    """Most stage-mechanics tests just need to be past docs (skip straight
    there by setting the index directly rather than mocking a real
    retrieval fetch). generation is real now too (see
    stages/generation/agent.py) - a test landing here for its own real
    execution behavior mocks clients.execution_agent_client's own
    execute_atl/execute_acceleo (see _mocked_generation_execution below and
    _MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME's own real context requirement);
    generation no longer reads context["constraints"] at all (there's
    nothing a real ATL/Acceleo execution could do with a free-text
    correction), so a test about corrections being threaded into a real
    agent call uses atl or acceleo instead - see e.g.
    test_run_stage_incorporates_constraints_added_since_the_last_run."""
    o.current_stage_index = pipeline.STAGES.index("generation")


# atl_agent's own prompt forces every generated ATL's output model name into
# this exact shape (see stages/generation/agent.py's own
# _OUTPUT_MODEL_NAME_PATTERN) - the minimal real string gen_stage's own
# parsing needs to not raise, for a test that just needs to get past
# generation, not exercise its own real parsing (see
# test_generation_stage.py for that).
_MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME = "create OUT : GitLabMM from IN : PIM;\n"


@contextmanager
def _mocked_generation_execution(
    execute_atl_kwargs: dict | None = None, execute_acceleo_kwargs: dict | None = None
):
    """Patches execution_agent_client.execute_atl/execute_acceleo together,
    and stages.generation.agent.PIM_SAMPLE_INSTANCE_PATH to a throwaway
    real file (gen_stage reads that file directly off disk before ever
    calling execute_atl, so it needs to genuinely exist even when
    execute_atl itself is mocked) - gen_stage's own real boundary,
    replacing ai_layer_client.chat() for every pipeline-mechanics test that
    just needs to get PAST the generation stage, not test its own real
    execution logic (see test_generation_stage.py for that). Defaults to a
    plain successful outcome for each; pass execute_atl_kwargs/
    execute_acceleo_kwargs (return_value=/side_effect=) to override either
    - matching unittest.mock.patch's own kwarg shape directly, so a caller
    that needs side_effect=RuntimeError(...) or a custom blocking function
    doesn't need a second, bespoke helper."""
    execute_atl_kwargs = execute_atl_kwargs or {"return_value": "<gitlabMM:Pipeline/>"}
    execute_acceleo_kwargs = execute_acceleo_kwargs or {"return_value": {".gitlab-ci.yml": "stages: []\n"}}
    with tempfile.TemporaryDirectory() as tmp:
        fixture_path = Path(tmp) / "input.pimmm"
        fixture_path.write_text("<pimMM:Pipeline/>", encoding="utf-8")
        with (
            patch.object(generation_agent, "PIM_SAMPLE_INSTANCE_PATH", fixture_path),
            patch.object(execution_agent_client, "execute_atl", **execute_atl_kwargs) as mock_execute_atl,
            patch.object(execution_agent_client, "execute_acceleo", **execute_acceleo_kwargs) as mock_execute_acceleo,
        ):
            yield mock_execute_atl, mock_execute_acceleo


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
    """A psm_agent_client.run_psm()-shaped result for generation mode, psm
    is the one mock-validated-looking stage whose real boundary isn't
    validator_agent_client directly (see stages/psm/agent.py's own
    docstring): it calls psm_agent_client.run_psm() instead, so any test
    that runs psm_stage() for real must mock THIS, not validate_ecore,
    mocking validate_ecore alone silently does nothing to intercept it and
    the call falls through to a real (likely unreachable) network request."""
    return {
        "mode": "generation",
        "artifact": artifact,
        "prompt": {"psm_docs": "", "psm_example": "", "constraints": ""},
        "validation": _validation_result(valid=valid),
        "rounds": 1,
    }


def _atl_generation_result(artifact="module m; ...", valid=True):
    """An atl_agent_client.run_atl()-shaped result - atl's own real
    boundary isn't validator_agent_client directly (see
    stages/atl/agent.py's own docstring): it calls atl_agent_client.run_atl()
    instead, so any test that runs atl_stage() for real must mock THIS, not
    validate_atl - mocking validate_atl alone silently does nothing to
    intercept it and the call falls through to a real (likely unreachable)
    network request."""
    return {
        "artifact": artifact,
        "prompt": {"pim_ecore": "", "psm_ecore": "", "atl_example": "", "constraints": ""},
        "validation": _validation_result(valid=valid),
        "rounds": 1,
        "prompt_version": None,
    }


def _acceleo_generation_result(artifact="[module generate('x')] ...", valid=True):
    """An acceleo_agent_client.run_acceleo()-shaped result - same reasoning
    as _atl_generation_result above, acceleo's own real boundary is
    acceleo_agent_client.run_acceleo(), not validator_agent_client directly."""
    return {
        "artifact": artifact,
        "prompt": {"psm_ecore": "", "platform_docs": "", "acceleo_example": "", "constraints": ""},
        "validation": _validation_result(valid=valid),
        "rounds": 1,
        "prompt_version": None,
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
