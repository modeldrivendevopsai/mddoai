"""integration_runner/stages/{pim,atl,acceleo}/agent.py unit tests: the
three stages that switched from LLM prose to fixed mock DSL content, each
validated for real against validator-agent and persisted to disk win or
lose (see stages/_validation.py). No real validator-agent HTTP calls here —
validator_agent_client's validate_* functions are mocked at that boundary;
see test_mock_validated_stages_real_validator.py for the real end-to-end
exception. RUNS_DIR is redirected to a throwaway tmp_path for every test in
this whole suite by conftest.py's own autouse fixture, so persist_attempt()
runs for real here without touching this repo's actual runs/ directory.

psm is deliberately NOT parametrized here even though it also calls
persist_attempt(): unlike these three, its real content/validation comes
from psm_agent_client.run_psm() (a real generation/comparison call), not a
fixed mock string validated directly against validator_agent_client, and it
deliberately does NOT raise_if_invalid() on a generation-mode failure (see
stages/psm/agent.py's own docstring) — its own tests live in
test_psm_stage.py and test_pipeline.py instead.

Tests verify, for each of the three stages:
  1. It calls the right validator_agent_client function with its own fixed
     mock content and filename (and, for pim, the ecore "mode").
  2. On a passing result, it returns the mock content and leaves a real
     attempt_1/ on disk (content + result.json).
  3. On a failing result, it raises with the real issue detail AND still
     leaves a real attempt_1/ on disk — a failed attempt is exactly the
     record this exists to keep, not a NOT_CONFIRMED status best skipped.
  4. Two attempts for the same run_id don't collide — attempt_2/ appears
     alongside attempt_1/, neither overwritten.
Context (platform_description, docs_output, etc.) is deliberately never
asserted against the sent content here: these three stages ignore their
input, unlike the LLM-placeholder ones (see each agent.py's own docstring
for why).
"""
import json

import pytest

from clients import validator_agent_client
from integration_runner.stages import _validation
from integration_runner.stages.acceleo import agent as acceleo_agent
from integration_runner.stages.atl import agent as atl_agent
from integration_runner.stages.pim import agent as pim_agent
from helpers import _validation_result

# (stage module, stage_fn, stage name, validator_agent_client function name, filename)
_STAGES = [
    (pim_agent, pim_agent.pim_stage, "pim", "validate_ecore", pim_agent._FILENAME),
    (atl_agent, atl_agent.atl_stage, "atl", "validate_atl", atl_agent._FILENAME),
    (acceleo_agent, acceleo_agent.acceleo_stage, "acceleo", "validate_acceleo", acceleo_agent._FILENAME),
]


@pytest.mark.parametrize("module,stage_fn,stage,client_fn_name,filename", _STAGES)
def test_stage_calls_the_right_validator_with_its_own_mock_content(module, stage_fn, stage, client_fn_name, filename, monkeypatch):
    calls = []
    monkeypatch.setattr(
        validator_agent_client, client_fn_name,
        lambda *args, **kwargs: (calls.append((args, kwargs)), _validation_result())[1],
    )

    stage_fn({"run_id": "run-1"})

    assert len(calls) == 1
    args, kwargs = calls[0]
    assert args[0] == module._MOCK_CONTENT
    assert args[1] == filename


@pytest.mark.parametrize("module,stage_fn,stage,client_fn_name,filename", _STAGES)
def test_stage_returns_its_mock_content_on_a_passing_result(module, stage_fn, stage, client_fn_name, filename, monkeypatch):
    monkeypatch.setattr(validator_agent_client, client_fn_name, lambda *a, **k: _validation_result(valid=True))

    result = stage_fn({"run_id": "run-1"})

    assert result == module._MOCK_CONTENT


@pytest.mark.parametrize("module,stage_fn,stage,client_fn_name,filename", _STAGES)
def test_stage_persists_a_passing_attempt_to_disk(module, stage_fn, stage, client_fn_name, filename, monkeypatch):
    passing = _validation_result(valid=True, duration_ms=7)
    monkeypatch.setattr(validator_agent_client, client_fn_name, lambda *a, **k: passing)

    stage_fn({"run_id": "run-1"})

    attempt_dir = _validation.RUNS_DIR / "run-1" / stage / "attempt_1"
    assert (attempt_dir / filename).read_text(encoding="utf-8") == module._MOCK_CONTENT
    assert json.loads((attempt_dir / "result.json").read_text(encoding="utf-8")) == passing


@pytest.mark.parametrize("module,stage_fn,stage,client_fn_name,filename", _STAGES)
def test_stage_raises_with_real_issue_detail_on_a_failing_result_and_still_persists(module, stage_fn, stage, client_fn_name, filename, monkeypatch):
    failing = _validation_result(valid=False, issues=[
        {"severity": "error", "message": "deliberately broken for this test", "source": filename}
    ])
    monkeypatch.setattr(validator_agent_client, client_fn_name, lambda *a, **k: failing)

    with pytest.raises(RuntimeError) as exc_info:
        stage_fn({"run_id": "run-1"})

    assert "deliberately broken for this test" in str(exc_info.value)
    # A failed attempt is exactly the record this exists to keep — it must
    # still be on disk even though the call above raised.
    attempt_dir = _validation.RUNS_DIR / "run-1" / stage / "attempt_1"
    assert (attempt_dir / filename).read_text(encoding="utf-8") == module._MOCK_CONTENT
    persisted = json.loads((attempt_dir / "result.json").read_text(encoding="utf-8"))
    assert persisted["valid"] is False


@pytest.mark.parametrize("module,stage_fn,stage,client_fn_name,filename", _STAGES)
def test_two_attempts_for_the_same_run_do_not_collide(module, stage_fn, stage, client_fn_name, filename, monkeypatch):
    monkeypatch.setattr(validator_agent_client, client_fn_name, lambda *a, **k: _validation_result(valid=True))

    stage_fn({"run_id": "run-1"})
    stage_fn({"run_id": "run-1"})

    stage_dir = _validation.RUNS_DIR / "run-1" / stage
    assert (stage_dir / "attempt_1").exists()
    assert (stage_dir / "attempt_2").exists()
    assert (stage_dir / "attempt_1" / filename).read_text(encoding="utf-8") == module._MOCK_CONTENT
    assert (stage_dir / "attempt_2" / filename).read_text(encoding="utf-8") == module._MOCK_CONTENT


def test_pim_validates_against_ecore_with_reflective_mode(monkeypatch):
    # Confirms pim is wired to /validate/ecore specifically (structurally
    # identical to atl_stage calling /validate/atl and acceleo_stage calling
    # /validate/acceleo, both already covered by the parametrized tests
    # above), not just "some Ecore-shaped content" by coincidence, and that
    # it uses the reflective (not codegen) mode.
    calls = []
    monkeypatch.setattr(
        validator_agent_client, "validate_ecore",
        lambda *args, **kwargs: (calls.append(kwargs), _validation_result())[1],
    )

    pim_agent.pim_stage({"run_id": "run-1"})

    assert len(calls) == 1
    # mode defaults to "reflective" (validator_agent_client's own default), pim doesn't override it.
    assert calls[0].get("mode", "reflective") == "reflective"
