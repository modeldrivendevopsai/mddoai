"""Real end-to-end test: no mocking of validator_agent_client — pim_stage
makes a real HTTP call to a real running validator-agent, which in turn
shells out to the real Java CLIs (see validator_agent/validator_runner.py).
Auto-skips when validator-agent isn't reachable, so the fast suite (plain
`pytest`) always runs standalone on any machine, matching
test_integration_real_jvm.py's own real-dependency-gated pattern in
validator_agent's own test suite. A local run needs validator-agent
actually started (`docker compose up validator-agent`, or directly via
uvicorn against a built main/ distribution — see validator_agent/README.md).

psm/atl/acceleo are deliberately not included here: each of their own real
end-to-end paths goes through its own real, separate service
(psm_agent_client.run_psm(), atl_agent_client.run_atl(),
acceleo_agent_client.run_acceleo()), generating real content, not a fixed
mock string validated directly against validator_agent_client with no
other real inputs — see each service's own tests/test_generation_real_llm.py
for its real end-to-end coverage.

RUNS_DIR is still redirected to a throwaway tmp_path by conftest.py's own
autouse fixture — only the validator-agent HTTP call is real here, not the
on-disk persistence path, which the mocked tests in
test_mock_validated_stages.py already cover directly.
"""
import json

import httpx
import pytest

from clients import validator_agent_client
from integration_runner.stages import _validation
from integration_runner.stages import pim


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


def test_real_end_to_end_validation_passes_and_persists():
    output = pim.agent.pim_stage({"run_id": "real-run"})

    assert output  # pim's own mock content, unchanged on success

    attempt_dir = _validation.RUNS_DIR / "real-run" / "pim" / "attempt_1"
    assert (attempt_dir / pim.agent._FILENAME).exists()
    result = json.loads((attempt_dir / "result.json").read_text(encoding="utf-8"))
    assert result["valid"] is True, f"real validator-agent rejected pim's mock content: {result['issues']}"
    assert result["issues"] == []
    assert result["duration_ms"] >= 0


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
