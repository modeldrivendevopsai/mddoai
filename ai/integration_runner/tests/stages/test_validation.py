"""stages/_validation.py unit tests: persist_attempt() writes the real
artifact + validator-agent result to disk under runs/<run_id>/<stage>/
attempt_N/, never overwriting a prior attempt; raise_if_invalid() turns a
failing result into a real raised failure carrying its issue detail. No
real validator-agent calls here — result dicts are hand-built, matching
what validator_agent_client would actually return.

RUNS_DIR is redirected to a throwaway tmp_path for every test in this whole
suite by conftest.py's own autouse _isolated_validation_runs_dir fixture,
not repeated here.
"""
import json

import pytest

from integration_runner.stages import _validation


def _ok_result():
    return {"valid": True, "issues": [], "duration_ms": 5}


def _failing_result():
    return {
        "valid": False,
        "issues": [
            {"severity": "error", "message": "NoSuchType: Widget", "source": "sample.ecore"},
            {"severity": "warning", "message": "Unused import", "source": None},
        ],
        "duration_ms": 3,
    }


def test_persist_attempt_writes_content_and_result_under_attempt_1():
    attempt_dir = _validation.persist_attempt("run-1", "pim", "pim_mock.ecore", "<ecore>content</ecore>", _ok_result())

    assert attempt_dir == _validation.RUNS_DIR / "run-1" / "pim" / "attempt_1"
    assert (attempt_dir / "pim_mock.ecore").read_text(encoding="utf-8") == "<ecore>content</ecore>"
    assert json.loads((attempt_dir / "result.json").read_text(encoding="utf-8")) == _ok_result()


def test_persist_attempt_persists_a_failing_result_too():
    # A failed attempt is exactly the record this exists to keep.
    attempt_dir = _validation.persist_attempt("run-1", "atl", "atl_mock.atl", "module M; broken", _failing_result())

    assert (attempt_dir / "atl_mock.atl").read_text(encoding="utf-8") == "module M; broken"
    persisted = json.loads((attempt_dir / "result.json").read_text(encoding="utf-8"))
    assert persisted["valid"] is False
    assert persisted["issues"][0]["message"] == "NoSuchType: Widget"


def test_second_attempt_for_same_run_and_stage_does_not_overwrite_the_first():
    first = _validation.persist_attempt("run-1", "psm", "psm_mock.ecore", "first content", _ok_result())
    second = _validation.persist_attempt("run-1", "psm", "psm_mock.ecore", "second content", _failing_result())

    assert first != second
    assert first.name == "attempt_1"
    assert second.name == "attempt_2"
    assert first.exists()
    assert (first / "psm_mock.ecore").read_text(encoding="utf-8") == "first content"
    assert (second / "psm_mock.ecore").read_text(encoding="utf-8") == "second content"


def test_persist_attempt_reuses_a_pre_reserved_attempt_dir_instead_of_reserving_a_new_one():
    # atl_stage/acceleo_stage's own real shape: reserve_attempt_dir() runs
    # first (its result is what they forward to validator_agent_client as
    # the compiled output's own attempt scope), then that same Path is
    # handed to persist_attempt() so it doesn't reserve a second one.
    reserved = _validation.reserve_attempt_dir("run-1", "atl")

    returned = _validation.persist_attempt(
        "run-1", "atl", "atl_mock.atl", "module M;", _ok_result(), attempt_dir=reserved
    )

    assert returned == reserved
    assert returned.name == "attempt_1"
    # No second attempt_2/ was created alongside it.
    assert not (returned.parent / "attempt_2").exists()
    assert (returned / "atl_mock.atl").read_text(encoding="utf-8") == "module M;"


def test_persist_attempt_without_attempt_dir_still_reserves_its_own_exactly_as_before():
    # Backward compatibility: every existing caller (pim_stage, psm_stage)
    # never passes attempt_dir, and must keep reserving its own attempt
    # directory exactly as it always did.
    attempt_dir = _validation.persist_attempt("run-1", "pim", "pim_mock.ecore", "content", _ok_result())

    assert attempt_dir == _validation.RUNS_DIR / "run-1" / "pim" / "attempt_1"


def test_reserve_attempt_dir_is_the_real_next_attempt_dir_implementation():
    # _next_attempt_dir is kept only as an alias for existing callers (see
    # test_validation_concurrency.py) - both names must resolve to the
    # exact same function object, not two copies of the same logic.
    assert _validation._next_attempt_dir is _validation.reserve_attempt_dir


def test_attempts_are_scoped_per_run_and_per_stage():
    # Different run_id -> its own attempt_1, doesn't collide with run-1's.
    a = _validation.persist_attempt("run-1", "pim", "pim_mock.ecore", "content", _ok_result())
    b = _validation.persist_attempt("run-2", "pim", "pim_mock.ecore", "content", _ok_result())
    # Same run, different stage -> also its own attempt_1.
    c = _validation.persist_attempt("run-1", "acceleo", "acceleo_mock.mtl", "content", _ok_result())

    assert a.name == "attempt_1"
    assert b.name == "attempt_1"
    assert c.name == "attempt_1"
    assert a != b != c


def test_raise_if_invalid_does_nothing_for_a_passing_result():
    _validation.raise_if_invalid("pim", _ok_result())  # no raise


def test_raise_if_invalid_raises_with_real_issue_detail():
    with pytest.raises(RuntimeError) as exc_info:
        _validation.raise_if_invalid("pim", _failing_result())

    message = str(exc_info.value)
    assert "pim output failed validation" in message
    assert "NoSuchType: Widget" in message
    assert "Unused import" in message


def test_raise_if_invalid_handles_no_issues_gracefully():
    result = {"valid": False, "issues": [], "duration_ms": 1}
    with pytest.raises(RuntimeError, match="no issue detail returned"):
        _validation.raise_if_invalid("atl", result)


def test_reserved_attempt_removes_the_empty_dir_and_frees_the_number_when_the_body_raises():
    # A transient validator/psm failure between reserving the directory and
    # persisting anything must not strand an empty attempt_N/ or burn N.
    with pytest.raises(RuntimeError, match="validator-agent went away"):
        with _validation.reserved_attempt("run-1", "atl") as attempt_dir:
            assert attempt_dir.is_dir()
            raise RuntimeError("validator-agent went away")

    assert not attempt_dir.exists()
    # The next real attempt still gets attempt_1, not attempt_2.
    assert _validation.reserve_attempt_dir("run-1", "atl").name == "attempt_1"


def test_reserved_attempt_removes_a_non_empty_dir_that_was_never_recorded():
    # psm's codegen validation nests compiled output under the attempt dir
    # once per retry round; a later round raising before any result is
    # persisted must still discard the whole unrecorded attempt, not keep
    # it just because that leftover made the directory non-empty.
    with pytest.raises(RuntimeError):
        with _validation.reserved_attempt("run-1", "psm") as attempt_dir:
            (attempt_dir / "ecore-validate-abc" / "src-gen").mkdir(parents=True)
            raise RuntimeError("round 2 generation failed")

    assert not attempt_dir.exists()
    assert _validation.reserve_attempt_dir("run-1", "psm").name == "attempt_1"


def test_reserved_attempt_keeps_the_dir_once_persist_attempt_has_written_to_it():
    # raise_if_invalid() raising inside the with-block after persist_attempt()
    # has run must not delete the record it just wrote.
    with pytest.raises(RuntimeError):
        with _validation.reserved_attempt("run-1", "psm") as attempt_dir:
            _validation.persist_attempt(
                "run-1", "psm", "psm_mock.ecore", "content", _failing_result(), attempt_dir=attempt_dir
            )
            raise RuntimeError("something after persist")

    assert attempt_dir.is_dir()
    assert (attempt_dir / "psm_mock.ecore").read_text(encoding="utf-8") == "content"


def test_reserved_attempt_yields_none_and_stays_a_noop_without_a_run_id():
    with pytest.raises(ValueError):
        with _validation.reserved_attempt(None, "atl") as attempt_dir:
            assert attempt_dir is None
            raise ValueError("boom")
