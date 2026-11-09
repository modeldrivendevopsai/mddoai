"""stages/_validation.py unit tests: persist_attempt() keeps the real artifact
and validator-agent result in the journal under the run, stage and attempt,
never overwriting a prior attempt; raise_if_invalid() turns a failing result
into a real raised failure carrying its issue detail. No real validator-agent
calls here, result dicts are hand-built, matching what validator_agent_client
would actually return.

The journal is the real one, in this process, over folders of each test alone
(see conftest.py's journal fixture)."""
import threading

import httpx
import pytest

from clients import journal_client
from clients.agent_service_errors import AgentServiceError
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


def _attempt(attempt):
    return journal_client.get_attempt(attempt.run_id, attempt.stage, attempt.name)


def test_persist_attempt_keeps_content_and_result_under_attempt_1():
    attempt = _validation.persist_attempt("run-1", "pim", "pim_mock.ecore", "<ecore>content</ecore>", _ok_result())

    assert attempt == _validation.Attempt("run-1", "pim", "attempt_1")
    assert _attempt(attempt)["artifact"] == {"filename": "pim_mock.ecore", "content": "<ecore>content</ecore>"}
    assert _attempt(attempt)["result"] == _ok_result()


def test_persist_attempt_keeps_a_failing_result_too():
    # A failed attempt is exactly the record this exists to keep.
    attempt = _validation.persist_attempt("run-1", "atl", "atl_mock.atl", "module M; broken", _failing_result())

    kept = _attempt(attempt)
    assert kept["artifact"]["content"] == "module M; broken"
    assert kept["result"]["valid"] is False
    assert kept["result"]["issues"][0]["message"] == "NoSuchType: Widget"


def test_the_prompt_and_its_version_and_extra_files_are_kept_with_the_attempt(journal):
    attempt = _validation.persist_attempt(
        "run-1", "generation", "output.yaml", "a: b", _ok_result(),
        prompt={"parts": ["x"]}, prompt_version="v7", extra_files={"psm_instance.xmi": "<xmi/>", ".ci/pipeline.yaml": "steps: []"},
    )

    kept = _attempt(attempt)
    assert (kept["prompt"], kept["prompt_version"]) == ({"parts": ["x"]}, "v7")
    assert (journal / "run-1" / "generation" / "attempt_1" / "psm_instance.xmi").read_text(encoding="utf-8") == "<xmi/>"
    assert (journal / "run-1" / "generation" / "attempt_1" / ".ci" / "pipeline.yaml").is_file()


def test_second_attempt_for_same_run_and_stage_does_not_overwrite_the_first():
    first = _validation.persist_attempt("run-1", "psm", "psm_mock.ecore", "first content", _ok_result())
    second = _validation.persist_attempt("run-1", "psm", "psm_mock.ecore", "second content", _failing_result())

    assert (first.name, second.name) == ("attempt_1", "attempt_2")
    assert _attempt(first)["artifact"]["content"] == "first content"
    assert _attempt(second)["artifact"]["content"] == "second content"


def test_persist_attempt_uses_a_pre_reserved_attempt_instead_of_reserving_a_new_one():
    # atl_stage/acceleo_stage reserve first (the name is what they forward to
    # validator_agent_client as the compiled output's own attempt scope), then
    # hand that same attempt to persist_attempt() so it does not reserve a second.
    reserved = _validation.reserve_attempt("run-1", "atl")

    returned = _validation.persist_attempt("run-1", "atl", "atl_mock.atl", "module M;", _ok_result(), attempt=reserved)

    assert returned == reserved and returned.name == "attempt_1"
    assert _validation.reserve_attempt("run-1", "atl").name == "attempt_2"
    assert _attempt(returned)["artifact"]["content"] == "module M;"


def test_persist_attempt_without_an_attempt_reserves_its_own():
    attempt = _validation.persist_attempt("run-1", "pim", "pim_mock.ecore", "content", _ok_result())

    assert attempt == _validation.Attempt("run-1", "pim", "attempt_1")


def test_every_recorded_attempt_is_in_the_run_manifest_in_order():
    _validation.persist_attempt("run-1", "pim", "pim.ecore", "c", _ok_result())
    _validation.persist_attempt("run-1", "psm", "psm.ecore", "c", _failing_result())

    manifest = journal_client.get_manifest("run-1")

    assert [(e["stage"], e["attempt_n"], e["valid"]) for e in manifest] == [("pim", 1, True), ("psm", 1, False)]


def test_attempts_are_scoped_per_run_and_per_stage():
    a = _validation.persist_attempt("run-1", "pim", "pim_mock.ecore", "content", _ok_result())
    b = _validation.persist_attempt("run-2", "pim", "pim_mock.ecore", "content", _ok_result())
    c = _validation.persist_attempt("run-1", "acceleo", "acceleo_mock.mtl", "content", _ok_result())

    assert a.name == b.name == c.name == "attempt_1"
    assert len({a, b, c}) == 3


def test_attempts_reserved_at_once_never_share_a_number():
    got = []
    lock = threading.Lock()

    def reserve():
        attempt = _validation.reserve_attempt("run-1", "psm")
        with lock:
            got.append(attempt.name)

    threads = [threading.Thread(target=reserve) for _ in range(12)]
    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()

    assert sorted(got) == sorted(f"attempt_{n}" for n in range(1, 13))


def test_the_attempt_scope_for_a_validator_is_the_stage_and_attempt_name_or_nothing():
    attempt = _validation.Attempt("run-1", "atl", "attempt_3")

    assert _validation.attempt_scope_kwargs("atl", attempt) == {"stage": "atl", "attempt": "attempt_3"}
    assert _validation.attempt_scope_kwargs("atl", None) == {"stage": None, "attempt": None}


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


def test_reserved_attempt_releases_the_attempt_and_frees_the_number_when_the_body_raises():
    # A transient validator/psm failure between reserving an attempt and
    # recording anything must not strand an empty attempt or burn its number.
    with pytest.raises(RuntimeError, match="validator-agent went away"):
        with _validation.reserved_attempt("run-1", "atl") as attempt:
            assert attempt.name == "attempt_1"
            raise RuntimeError("validator-agent went away")

    with pytest.raises(AgentServiceError) as error:
        _attempt(attempt)
    assert error.value.status_code == 404
    assert _validation.reserve_attempt("run-1", "atl").name == "attempt_1"


def test_reserved_attempt_keeps_an_attempt_once_persist_attempt_has_recorded_it():
    # raise_if_invalid() raising inside the with-block after persist_attempt()
    # has run must not delete the record it just wrote.
    with pytest.raises(RuntimeError):
        with _validation.reserved_attempt("run-1", "psm") as attempt:
            _validation.persist_attempt("run-1", "psm", "psm_mock.ecore", "content", _failing_result(), attempt=attempt)
            raise RuntimeError("something after persist")

    assert _attempt(attempt)["artifact"]["content"] == "content"


def test_a_journal_that_cannot_release_the_attempt_does_not_hide_the_error_being_raised(monkeypatch, caplog):
    def down(*args, **kwargs):
        raise httpx.ConnectError("the journal is down")

    with pytest.raises(RuntimeError, match="the real failure"):
        with _validation.reserved_attempt("run-1", "atl"):
            monkeypatch.setattr(journal_client.httpx.request, "side_effect", down)
            raise RuntimeError("the real failure")

    assert "could not release attempt_1" in caplog.text


def test_reserved_attempt_yields_none_and_stays_a_noop_without_a_run_id():
    with pytest.raises(ValueError):
        with _validation.reserved_attempt(None, "atl") as attempt:
            assert attempt is None
            raise ValueError("boom")
