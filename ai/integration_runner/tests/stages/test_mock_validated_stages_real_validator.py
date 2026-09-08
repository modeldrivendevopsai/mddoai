"""Real end-to-end test: no mocking of validator_agent_client — pim_stage/
atl_stage/acceleo_stage each make a real HTTP call to a real running
validator-agent, which in turn shells out to the real Java CLIs (see
validator_agent/validator_runner.py). Auto-skips when validator-agent isn't
reachable, so the fast suite (plain `pytest`) always runs standalone on any
machine, matching test_integration_real_jvm.py's own real-dependency-gated
pattern in validator_agent's own test suite. A local run needs
validator-agent actually started (`docker compose up validator-agent`, or
directly via uvicorn against a built main/ distribution — see
validator_agent/README.md).

psm is deliberately not included here: its own real end-to-end path goes
through psm_agent_client.run_psm() (a separate real service, generating or
comparing real content, not a fixed mock string), not a direct
validator_agent_client call with no other real inputs — see psm_agent's own
tests/test_generation_real_llm.py for its real end-to-end coverage.

RUNS_DIR is still redirected to a throwaway tmp_path by conftest.py's own
autouse fixture — only the validator-agent HTTP call is real here, not the
on-disk persistence path, which the mocked tests in
test_mock_validated_stages.py already cover directly.
"""
import json
from pathlib import Path

import httpx
import pytest

from clients import validator_agent_client
from integration_runner.stages import _validation
from integration_runner.stages import acceleo, atl, pim


def _validator_agent_reachable() -> bool:
    try:
        httpx.get(f"{validator_agent_client.VALIDATOR_AGENT_URL}/health", timeout=2.0).raise_for_status()
        return True
    except Exception:
        return False


pytestmark = pytest.mark.skipif(
    not _validator_agent_reachable(),
    reason=f"real validator-agent ({validator_agent_client.VALIDATOR_AGENT_URL}) not reachable",
)


@pytest.mark.parametrize("stage_fn,stage,filename", [
    (pim.agent.pim_stage, "pim", pim.agent._FILENAME),
    (atl.agent.atl_stage, "atl", atl.agent._FILENAME),
    (acceleo.agent.acceleo_stage, "acceleo", acceleo.agent._FILENAME),
])
def test_real_end_to_end_validation_passes_and_persists(stage_fn, stage, filename):
    output = stage_fn({"run_id": "real-run"})

    assert output  # the stage's own mock content, unchanged on success

    attempt_dir = _validation.RUNS_DIR / "real-run" / stage / "attempt_1"
    assert (attempt_dir / filename).exists()
    result = json.loads((attempt_dir / "result.json").read_text(encoding="utf-8"))
    assert result["valid"] is True, f"real validator-agent rejected {stage}'s mock content: {result['issues']}"
    assert result["issues"] == []
    assert result["duration_ms"] >= 0


@pytest.mark.parametrize("stage_fn,stage", [
    (atl.agent.atl_stage, "atl"),
    (acceleo.agent.acceleo_stage, "acceleo"),
])
def test_real_end_to_end_compiled_output_nests_inside_the_real_attempt_directory(stage_fn, stage):
    # Not the mocked test_mock_validated_stages.py's own coverage (which
    # only checks the run_id/stage/attempt kwargs a mock received) - this
    # runs the real validator-agent, which shells out to the real Java CLI,
    # and checks the actual compiled .asm/.emtl this produces really lands
    # inside a real <run_id>/<stage>/attempt_N/ directory (see ai/CLAUDE.md's
    # own "Second exception" paragraph for the real layout this proves).
    # Can't assert this path sits inside this test process's own RUNS_DIR
    # (conftest.py's autouse fixture redirects that to a throwaway tmp_path
    # local to this test run) - validator-agent is a separately started, real
    # process with its own independently configured VALIDATOR_OUTPUT_DIR (the
    # two only share one physical tree in the real docker-compose deployment's
    # pipeline-runs volume, already verified directly against a real running
    # validator-agent as part of this same change). Checking the path's own
    # structure is what's actually testable here, and is exactly what the
    # bug being guarded against would get wrong: the compiled output landing
    # one level too shallow (missing the stage segment) or colliding with
    # another stage's own same-numbered attempt.
    stage_fn({"run_id": "real-run-nesting"})

    attempt_dir = _validation.RUNS_DIR / "real-run-nesting" / stage / "attempt_1"
    result = json.loads((attempt_dir / "result.json").read_text(encoding="utf-8"))
    generated_source_path = result["generated_source_path"]

    assert generated_source_path is not None, f"real {stage} validator produced no compiled output to check nesting on"
    # AtlValidator's own generated_source_path names the compiled .asm file
    # directly; AcceleoValidator's names its output directory (the real
    # .emtl lives one level under that) - real asymmetry between the two,
    # not something this test should paper over, so it accepts either.
    assert Path(generated_source_path).exists()
    expected_segment = str(Path("real-run-nesting") / stage / "attempt_1")
    assert expected_segment in generated_source_path, (
        f"expected {generated_source_path!r} to contain the real nested "
        f"{expected_segment!r} segment (run_id/stage/attempt), not just run_id"
    )


def test_real_end_to_end_validation_fails_and_still_persists_for_genuinely_broken_content():
    # Not a mocked failing result (test_mock_validated_stages.py already
    # covers that) — genuinely broken Ecore XML, run through the real
    # validator-agent -> real EcoreValidatorCli, the same "NoSuchType"
    # failure validator_agent's own test suite demonstrates against its
    # invalid.ecore fixture. pim_stage._MOCK_CONTENT itself is untouched;
    # this calls the same real client + persist + raise chain pim_stage
    # itself uses, just with deliberately invalid content in its place.
    broken_content = pim.agent._MOCK_CONTENT.replace(
        'eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"',
        'eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//NoSuchType"',
    )
    assert broken_content != pim.agent._MOCK_CONTENT  # sanity: the replace above actually matched something

    result = validator_agent_client.validate_ecore(broken_content, pim.agent._FILENAME)
    _validation.persist_attempt("real-run-broken", "pim", pim.agent._FILENAME, broken_content, result)

    assert result["valid"] is False
    assert any("NoSuchType" in issue["message"] for issue in result["issues"])

    # A failed attempt is exactly the record this exists to keep.
    attempt_dir = _validation.RUNS_DIR / "real-run-broken" / "pim" / "attempt_1"
    assert (attempt_dir / pim.agent._FILENAME).read_text(encoding="utf-8") == broken_content
    persisted = json.loads((attempt_dir / "result.json").read_text(encoding="utf-8"))
    assert persisted["valid"] is False

    with pytest.raises(RuntimeError, match="NoSuchType"):
        _validation.raise_if_invalid("pim", result)
