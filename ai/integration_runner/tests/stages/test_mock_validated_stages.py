"""integration_runner/stages/pim/agent.py unit tests: pim reads the
project's own real, fixed PIM metamodel off disk (see PIM_METAMODEL_PATH)
and validates it for real against validator-agent, persisted to disk win
or lose (see stages/_validation.py). No real validator-agent HTTP calls
here - validator_agent_client's validate_ecore is mocked at that boundary;
see test_mock_validated_stages_real_validator.py for the real end-to-end
exception. RUNS_DIR is redirected to a throwaway tmp_path for every test in
this whole suite by conftest.py's own autouse fixture, so persist_attempt()
runs for real here without touching this repo's actual runs/ directory.
PIM_METAMODEL_PATH is likewise redirected to a throwaway fixture file per
test (see _real_content below), so these tests never depend on this repo's
real meta_models/ tree either.

atl and acceleo used to share this same shape (fixed mock content, validated
directly against validator_agent_client) - now each is a thin proxy to its
own real service (atl_agent, acceleo_agent), so their own tests moved to
test_atl_stage.py/test_acceleo_stage.py, mirroring test_psm_stage.py's own
shape instead. psm was never in this file either, for the same reason -
see test_psm_stage.py's own docstring.

Tests verify:
  1. It calls validator_agent_client.validate_ecore with the real metamodel
     content read off PIM_METAMODEL_PATH, and its own fixed filename, in
     reflective mode.
  2. On a passing result, it returns that content and leaves a real
     attempt_1/ on disk (content + result.json).
  3. On a failing result, it raises with the real issue detail AND still
     leaves a real attempt_1/ on disk - a failed attempt is exactly the
     record this exists to keep, not a NOT_CONFIRMED status best skipped.
  4. Two attempts for the same run_id don't collide - attempt_2/ appears
     alongside attempt_1/, neither overwritten.
Context (platform_description, docs_output, etc.) is deliberately never
asserted against the sent content here: pim ignores its input entirely
(see its own agent.py's docstring for why).
"""
import json

import pytest

from clients import validator_agent_client
from integration_runner.stages import _validation
from integration_runner.stages.pim import agent as pim_agent
from helpers import _validation_result

_REAL_CONTENT = "<ecore:EPackage/>"


@pytest.fixture(autouse=True)
def _pim_metamodel_fixture(tmp_path, monkeypatch):
    """Points PIM_METAMODEL_PATH at a small, throwaway fixture file for
    every test in this module - what's actually inside it never matters
    here (validate_ecore itself is mocked in every test below), only that
    pim_stage reads *some* real file off disk rather than an in-memory
    constant, and that these tests never touch this repo's real
    meta_models/ tree."""
    fixture_path = tmp_path / "pimMM.ecore"
    fixture_path.write_text(_REAL_CONTENT, encoding="utf-8")
    monkeypatch.setattr(pim_agent, "PIM_METAMODEL_PATH", fixture_path)


def test_pim_calls_validate_ecore_with_the_real_metamodel_content(monkeypatch):
    calls = []
    monkeypatch.setattr(
        validator_agent_client, "validate_ecore",
        lambda *args, **kwargs: (calls.append((args, kwargs)), _validation_result())[1],
    )

    pim_agent.pim_stage({"run_id": "run-1"})

    assert len(calls) == 1
    args, kwargs = calls[0]
    assert args[0] == _REAL_CONTENT
    assert args[1] == pim_agent._FILENAME


def test_pim_validates_against_ecore_with_reflective_mode(monkeypatch):
    calls = []
    monkeypatch.setattr(
        validator_agent_client, "validate_ecore",
        lambda *args, **kwargs: (calls.append(kwargs), _validation_result())[1],
    )

    pim_agent.pim_stage({"run_id": "run-1"})

    assert len(calls) == 1
    # mode defaults to "reflective" (validator_agent_client's own default), pim doesn't override it.
    assert calls[0].get("mode", "reflective") == "reflective"


def test_pim_returns_the_real_metamodel_content_on_a_passing_result(monkeypatch):
    monkeypatch.setattr(validator_agent_client, "validate_ecore", lambda *a, **k: _validation_result(valid=True))

    result = pim_agent.pim_stage({"run_id": "run-1"})

    assert result == _REAL_CONTENT


def test_pim_persists_a_passing_attempt_to_disk(monkeypatch):
    passing = _validation_result(valid=True, duration_ms=7)
    monkeypatch.setattr(validator_agent_client, "validate_ecore", lambda *a, **k: passing)

    pim_agent.pim_stage({"run_id": "run-1"})

    attempt_dir = _validation.RUNS_DIR / "run-1" / "pim" / "attempt_1"
    assert (attempt_dir / pim_agent._FILENAME).read_text(encoding="utf-8") == _REAL_CONTENT
    assert json.loads((attempt_dir / "result.json").read_text(encoding="utf-8")) == passing


def test_pim_raises_with_real_issue_detail_on_a_failing_result_and_still_persists(monkeypatch):
    failing = _validation_result(valid=False, issues=[
        {"severity": "error", "message": "deliberately broken for this test", "source": pim_agent._FILENAME}
    ])
    monkeypatch.setattr(validator_agent_client, "validate_ecore", lambda *a, **k: failing)

    with pytest.raises(RuntimeError) as exc_info:
        pim_agent.pim_stage({"run_id": "run-1"})

    assert "deliberately broken for this test" in str(exc_info.value)
    # A failed attempt is exactly the record this exists to keep, it must
    # still be on disk even though the call above raised.
    attempt_dir = _validation.RUNS_DIR / "run-1" / "pim" / "attempt_1"
    assert (attempt_dir / pim_agent._FILENAME).read_text(encoding="utf-8") == _REAL_CONTENT
    persisted = json.loads((attempt_dir / "result.json").read_text(encoding="utf-8"))
    assert persisted["valid"] is False


def test_pim_two_attempts_for_the_same_run_do_not_collide(monkeypatch):
    monkeypatch.setattr(validator_agent_client, "validate_ecore", lambda *a, **k: _validation_result(valid=True))

    pim_agent.pim_stage({"run_id": "run-1"})
    pim_agent.pim_stage({"run_id": "run-1"})

    stage_dir = _validation.RUNS_DIR / "run-1" / "pim"
    assert (stage_dir / "attempt_1").exists()
    assert (stage_dir / "attempt_2").exists()
