"""journal_client against the real journal app, in the same process."""
import logging
from pathlib import Path
from unittest.mock import patch

import httpx
import pytest

from clients import journal_client
from clients.agent_service_errors import AgentServiceError
from clients.journal_client import HistoryClient
from clients.journal_testing import folders_in, journal_in_process


@pytest.fixture
def folders(tmp_path):
    return folders_in(tmp_path)


@pytest.fixture(autouse=True)
def journal(folders):
    with journal_in_process(folders):
        yield


@pytest.fixture
def history():
    return HistoryClient("runs", "run")


def _on_disk(folders, key="r1"):
    return (folders[0] / key / "run.jsonl").read_text(encoding="utf-8")


def _held(key="r1"):
    return journal_client._request("GET", f"/histories/runs/{key}").json()


def _build(key, events, state):
    return key, events, state


def _methods_sent():
    return [call.args[0] + " " + call.args[1].removeprefix(journal_client.JOURNAL_URL) for call in journal_client.httpx.request.call_args_list]


# --- saving a history ------------------------------------------------------------------


def test_the_first_save_sends_the_whole_history(history):
    history.save("r1", [{"n": 1}], {"stage": 0})

    assert _held() == {"state": {"stage": 0}, "events": [{"n": 1}]}
    assert _methods_sent() == ["PUT /histories/runs/r1", "GET /histories/runs/r1"]


def test_a_later_save_sends_only_the_new_events_and_the_state_that_changed(history, folders):
    events = [{"n": 1, "text": "x" * 100_000}]
    history.save("r1", events, {"stage": 0, "model": None})
    before = len(_on_disk(folders))
    journal_client.httpx.request.reset_mock()

    events.append({"n": 2})
    history.save("r1", events, {"stage": 1, "model": None})

    assert _methods_sent() == ["POST /histories/runs/r1/append"]
    body = journal_client.httpx.request.call_args.kwargs["content"]
    assert b"xxxx" not in body and b'"state": {"stage": 1}' in body
    assert len(_on_disk(folders)) - before < 200
    assert _held() == {"state": {"stage": 1, "model": None}, "events": events}


def test_a_value_changed_inside_the_state_after_a_save_is_still_seen_as_changed(history):
    state = {"constraints": {"docs": ["a"]}}
    history.save("r1", [{"n": 1}], state)

    state["constraints"]["docs"].append("b")
    history.save("r1", [{"n": 1}], state)

    assert _held()["state"] == {"constraints": {"docs": ["a", "b"]}}


def test_a_deleted_history_is_gone_from_the_journal_and_a_save_after_it_is_sent_whole(history, folders):
    history.save("r1", [{"n": 1}, {"n": 2}], {"stage": 0})

    assert history.delete("r1") is True

    assert not (folders[0] / "r1").exists()
    history.save("r1", [{"n": 7}], {"stage": 0})
    assert _held()["events"] == [{"n": 7}]


def test_deleting_a_history_the_journal_does_not_hold_is_false_and_not_an_error(history):
    assert history.delete("nothing") is False


def test_a_delete_the_journal_refuses_raises_with_its_reason(history):
    history.save("run1", [{"n": 1}], {"stage": 0})
    journal_client.publish_platform(**{**PLATFORM, "source_run_id": "run1"})

    with pytest.raises(AgentServiceError) as refused:
        history.delete("run1")

    assert refused.value.status_code == 409 and "Woodpecker CI" in refused.value.detail
    assert _held("run1")["events"] == [{"n": 1}]


def test_two_keys_are_kept_apart(history):
    history.save("r1", [{"n": 1}], {})
    history.save("r2", [{"n": 2}], {})
    history.save("r1", [{"n": 1}, {"n": 3}], {})

    assert [e["n"] for e in _held("r1")["events"]] == [1, 3]
    assert [e["n"] for e in _held("r2")["events"]] == [2]


def test_a_history_the_journal_lost_is_sent_whole_again(history, folders):
    history.save("r1", [{"n": 1}], {"stage": 0})
    (folders[0] / "r1" / "run.jsonl").unlink()

    history.save("r1", [{"n": 1}, {"n": 2}], {"stage": 0})

    assert _held() == {"state": {"stage": 0}, "events": [{"n": 1}, {"n": 2}]}


def test_events_that_became_fewer_replace_the_whole_history_kept(history):
    history.save("r1", [{"n": 1}, {"n": 2}], {})

    history.save("r1", [{"n": 9}], {})

    assert _held()["events"] == [{"n": 9}]


def test_a_save_that_is_sent_again_after_a_lost_answer_keeps_each_event_once(history):
    history.save("r1", [{"n": 1}], {"stage": 0})
    real = journal_client.httpx.request.side_effect
    lost = []

    def answer_lost(method, url, **kwargs):
        response = real(method, url, **kwargs)
        if not lost and method == "POST":
            lost.append(True)
            raise httpx.ReadTimeout("the answer never came")
        return response

    journal_client.httpx.request.side_effect = answer_lost
    with pytest.raises(httpx.ReadTimeout):
        history.save("r1", [{"n": 1}, {"n": 2}], {"stage": 1})

    history.save("r1", [{"n": 1}, {"n": 2}, {"n": 3}], {"stage": 1})

    assert _held() == {"state": {"stage": 1}, "events": [{"n": 1}, {"n": 2}, {"n": 3}]}


def test_a_journal_that_is_down_fails_the_save_and_the_next_save_sends_everything_since_the_last_good_one(history):
    history.save("r1", [{"n": 1}], {"stage": 0})
    real = journal_client.httpx.request.side_effect

    def down(method, url, **kwargs):
        raise httpx.ConnectError("the journal is down")

    journal_client.httpx.request.side_effect = down
    with pytest.raises(httpx.ConnectError):
        history.save("r1", [{"n": 1}, {"n": 2}], {"stage": 1})
    journal_client.httpx.request.side_effect = real

    history.save("r1", [{"n": 1}, {"n": 2}, {"n": 3}], {"stage": 2})

    assert _held() == {"state": {"stage": 2}, "events": [{"n": 1}, {"n": 2}, {"n": 3}]}


def test_save_quietly_logs_a_failure_once_and_goes_on(history, caplog):
    def down(method, url, **kwargs):
        raise httpx.ConnectError("the journal is down")

    journal_client.httpx.request.side_effect = down
    with caplog.at_level(logging.ERROR):
        for _ in range(5):
            history.save_quietly("r1", [{"n": 1}], {})

    assert caplog.text.count("could not keep run r1 in the journal") == 1


def test_a_journal_that_breaks_again_after_working_is_logged_again(history, caplog):
    real = journal_client.httpx.request.side_effect

    def down(method, url, **kwargs):
        raise httpx.ConnectError("the journal is down")

    with caplog.at_level(logging.ERROR):
        journal_client.httpx.request.side_effect = down
        history.save_quietly("r1", [{"n": 1}], {})
        journal_client.httpx.request.side_effect = real
        history.save_quietly("r1", [{"n": 1}], {})
        journal_client.httpx.request.side_effect = down
        history.save_quietly("r1", [{"n": 1}, {"n": 2}], {})

    assert caplog.text.count("could not keep") == 2


def test_a_save_with_nothing_new_sends_nothing(history):
    events = [{"n": 1}]
    history.save("r1", events, {"stage": 0})
    journal_client.httpx.request.reset_mock()

    history.save("r1", events, {"stage": 0})
    history.save_quietly("r1", events, {"stage": 0})

    journal_client.httpx.request.assert_not_called()


def test_a_save_with_only_a_changed_state_is_sent(history):
    history.save("r1", [{"n": 1}], {"stage": 0})
    journal_client.httpx.request.reset_mock()

    history.save("r1", [{"n": 1}], {"stage": 1})

    assert _methods_sent() == ["POST /histories/runs/r1/append"]
    assert _held()["state"] == {"stage": 1}


@pytest.fixture
def clock(monkeypatch):
    """A clock the test moves, and a real cool-down."""
    now = [100.0]
    monkeypatch.setattr(journal_client, "_now", lambda: now[0])
    monkeypatch.setattr(journal_client, "RETRY_COOLDOWN", 5.0)
    return now


def _down(method, url, **kwargs):
    raise httpx.ConnectError("the journal is down")


def test_after_a_failed_save_quiet_saves_wait_out_a_cool_down_without_calling_the_journal(history, clock):
    journal_client.httpx.request.side_effect, real = _down, journal_client.httpx.request.side_effect
    history.save_quietly("r1", [{"n": 1}], {})
    journal_client.httpx.request.side_effect = real
    journal_client.httpx.request.reset_mock()

    clock[0] += 4.9
    history.save_quietly("r1", [{"n": 1}, {"n": 2}], {})
    history.save_quietly("r2", [{"n": 1}], {})

    journal_client.httpx.request.assert_not_called()


def test_once_the_cool_down_is_over_the_next_quiet_save_sends_everything_since_the_last_good_one(history, clock):
    history.save_quietly("r1", [{"n": 1}], {})
    real = journal_client.httpx.request.side_effect
    journal_client.httpx.request.side_effect = _down
    history.save_quietly("r1", [{"n": 1}, {"n": 2}], {})
    journal_client.httpx.request.side_effect = real

    clock[0] += 5.1
    history.save_quietly("r1", [{"n": 1}, {"n": 2}, {"n": 3}], {})

    assert [e["n"] for e in _held()["events"]] == [1, 2, 3]


def test_a_save_that_is_not_quiet_ignores_the_cool_down(history, clock):
    real = journal_client.httpx.request.side_effect
    journal_client.httpx.request.side_effect = _down
    history.save_quietly("r1", [{"n": 1}], {})
    journal_client.httpx.request.side_effect = real

    history.save("r1", [{"n": 1}], {})

    assert _held()["events"] == [{"n": 1}]


def test_a_journal_that_hangs_costs_one_wait_per_cool_down_and_not_one_per_save(history, clock):
    calls = []

    def hangs(method, url, **kwargs):
        calls.append(url)
        raise httpx.ReadTimeout("no answer")

    journal_client.httpx.request.side_effect = hangs
    for number in range(1, 21):
        history.save_quietly("r1", [{"n": n} for n in range(number)], {})
    assert len(calls) == 1

    clock[0] += 6
    for number in range(21, 41):
        history.save_quietly("r1", [{"n": n} for n in range(number)], {})
    assert len(calls) == 2


def test_a_history_the_journal_cannot_read_is_put_right_by_sending_it_whole(history, folders):
    history.save("r1", [{"n": 1}], {"stage": 0})
    with open(folders[0] / "r1" / "run.jsonl", "ab") as handle:
        handle.write(b"not json" + bytes([10]) + b'{"event": {"n": 2}}' + bytes([10]))
    from clients.journal_testing import restart_journal

    restart_journal()

    history.save("r1", [{"n": 1}, {"n": 2}], {"stage": 0})

    assert _held() == {"state": {"stage": 0}, "events": [{"n": 1}, {"n": 2}]}


def test_a_name_with_dots_or_slashes_stays_inside_its_own_segment_of_the_address(history):
    journal_client.reserve_attempt("run1", "psm")
    journal_client.httpx.request.reset_mock()

    with pytest.raises(AgentServiceError) as error:
        journal_client.purge_platform("../attempts/run1/psm/attempt_1")
    with pytest.raises(AgentServiceError):
        journal_client.get_attempt("../x", "psm", "attempt_1")

    assert error.value.status_code == 404
    assert all(".." not in call.args[1].split("/") for call in journal_client.httpx.request.call_args_list)
    # The reserved attempt was not discarded by the first call.
    assert journal_client.reserve_attempt("run1", "psm") == "attempt_2"


@pytest.mark.parametrize("name", ["", ".", ".."])
def test_a_name_that_an_http_library_would_collapse_out_of_the_address_is_refused_before_anything_is_sent(name):
    journal_client.httpx.request.reset_mock()

    for call in (
        lambda: journal_client.purge_platform(name),
        lambda: journal_client.purge_platform("x", name),
        lambda: journal_client.get_platform(name),
        lambda: journal_client.get_attempt(name, "psm", "attempt_1"),
        lambda: journal_client.reserve_attempt(name, "psm"),
    ):
        with pytest.raises(ValueError, match="one part of an address"):
            call()

    journal_client.httpx.request.assert_not_called()


def test_a_platform_id_cannot_carry_a_query_that_picks_another_version():
    journal_client.publish_platform(**PLATFORM, version="1.0.0")
    journal_client.publish_platform(**PLATFORM, version="2.0.0")

    assert journal_client.get_platform("woodpecker-ci?version=1.0.0") is None
    assert journal_client.get_platform("woodpecker-ci", "1.0.0&x=y") is None
    assert journal_client.get_platform("woodpecker-ci")["version"] == "2.0.0"


def test_asking_for_the_saved_histories_is_tried_again_while_the_journal_is_starting(history, monkeypatch):
    history.save("r1", [{"n": 1}], {})
    real = journal_client.httpx.request.side_effect
    answers = iter([httpx.ConnectError("starting"), httpx.ConnectError("starting")])
    waits = []

    def starting(method, url, **kwargs):
        error = next(answers, None)
        if error is not None:
            raise error
        return real(method, url, **kwargs)

    monkeypatch.setattr(journal_client.time, "sleep", waits.append)
    journal_client.httpx.request.side_effect = starting

    assert [key for key, _, _ in HistoryClient("runs", "run").load_all(_build)] == ["r1"]
    assert len(waits) == 2


def test_the_service_gives_up_when_the_journal_never_answers(monkeypatch):
    monkeypatch.setattr(journal_client.time, "sleep", lambda seconds: None)
    monkeypatch.setattr(journal_client, "LOAD_ATTEMPTS", 3)
    journal_client.httpx.request.side_effect = _down

    with pytest.raises(httpx.ConnectError):
        HistoryClient("runs", "run").load_all(_build)

    assert journal_client.httpx.request.call_count == 3


def test_a_value_json_cannot_hold_is_kept_as_text(history):
    history.save("r1", [{"where": Path("/data/x")}], {})

    assert _held()["events"] == [{"where": str(Path("/data/x"))}]


def test_a_key_that_is_not_text_is_refused_and_nothing_is_sent(history):
    journal_client.httpx.request.reset_mock()

    with pytest.raises(ValueError, match="text key"):
        history.save(None, [{"n": 1}], {})

    journal_client.httpx.request.assert_not_called()


def test_a_business_error_from_the_journal_is_raised_with_its_reason(history):
    with pytest.raises(AgentServiceError) as error:
        history.save("x" * 65, [{"n": 1}], {})

    assert error.value.status_code == 404


# --- loading histories --------------------------------------------------------------------


def test_every_history_the_journal_holds_comes_back_as_it_was(history):
    history.save("r1", [{"n": 1}], {"stage": 0})
    history.save("r1", [{"n": 1}, {"n": 2}], {"stage": 1})
    history.save("r2", [{"n": 9}], {})

    loaded = HistoryClient("runs", "run").load_all(_build)

    assert sorted(loaded) == [("r1", [{"n": 1}, {"n": 2}], {"stage": 1}), ("r2", [{"n": 9}], {})]


def test_after_loading_a_save_adds_to_what_the_journal_holds_and_does_not_send_it_whole(history, folders):
    history.save("r1", [{"n": 1}], {"stage": 0})
    restarted = HistoryClient("runs", "run")
    restarted.load_all(_build)
    journal_client.httpx.request.reset_mock()

    restarted.save("r1", [{"n": 1}, {"n": 2}], {"stage": 0})

    assert _methods_sent() == ["POST /histories/runs/r1/append"]
    assert _on_disk(folders).count('"n": 1') == 1


def test_a_history_the_caller_cannot_build_is_skipped_and_logged_and_the_rest_come_back(history, caplog):
    history.save("r1", [{"n": 1}], {})
    history.save("r2", [{"n": 2}], {})

    def build(key, events, state):
        if key == "r1":
            raise ValueError("not mine")
        return key

    with caplog.at_level(logging.WARNING):
        assert HistoryClient("runs", "run").load_all(build) == ["r2"]

    assert "skipping run r1: not mine" in caplog.text


def test_a_history_that_cannot_be_read_is_skipped_and_logged_and_the_rest_come_back(history, folders, caplog):
    history.save("r2", [{"n": 2}], {})
    (folders[0] / "bad").mkdir()
    (folders[0] / "bad" / "run.jsonl").write_text("{not json\n", encoding="utf-8")

    with caplog.at_level(logging.WARNING):
        loaded = HistoryClient("runs", "run").load_all(_build)

    assert [key for key, _, _ in loaded] == ["r2"]
    assert "skipping run bad" in caplog.text


def test_a_history_that_could_not_be_built_is_sent_whole_if_it_is_saved(history):
    history.save("r1", [{"n": 1}], {"stage": 0})
    restarted = HistoryClient("runs", "run")
    restarted.load_all(lambda key, events, state: (_ for _ in ()).throw(ValueError("not mine")))

    restarted.save("r1", [{"n": 1}, {"n": 2}], {"stage": 0})

    assert _held()["events"] == [{"n": 1}, {"n": 2}]


def test_chats_are_kept_in_their_own_kind():
    chats = HistoryClient("chats", "chat")
    chats.save("r1", [{"m": 1}], {"raw_seen": 1})

    assert journal_client._request("GET", "/histories/chats/r1").json() == {"state": {"raw_seen": 1}, "events": [{"m": 1}]}
    assert journal_client._request("GET", "/histories/runs").json() == {"keys": []}


# --- attempts -------------------------------------------------------------------------------


def test_an_attempt_is_reserved_recorded_and_read_back():
    attempt = journal_client.reserve_attempt("run1", "psm")

    journal_client.record_attempt(
        "run1", "psm", attempt, filename="psm.ecore", content="<ecore/>", result={"valid": True}, prompt={"p": 1}, prompt_version="v1"
    )

    assert attempt == "attempt_1"
    assert journal_client.get_attempt("run1", "psm", attempt)["artifact"] == {"filename": "psm.ecore", "content": "<ecore/>"}
    assert journal_client.get_manifest("run1")[0]["attempt_n"] == 1


def test_a_reserved_attempt_is_discarded_and_a_missing_one_is_a_refusal():
    attempt = journal_client.reserve_attempt("run1", "psm")

    journal_client.discard_attempt("run1", "psm", attempt)

    with pytest.raises(AgentServiceError) as error:
        journal_client.get_attempt("run1", "psm", attempt)
    assert error.value.status_code == 404
    with pytest.raises(AgentServiceError):
        journal_client.get_manifest("run1")


# --- platforms ------------------------------------------------------------------------------

PLATFORM = {
    "name": "Woodpecker CI",
    "output_filename": ".woodpecker.yaml",
    "output_model_name": "WoodpeckerMM",
    "metamodel": "<ecore/>",
    "transformation": "module T;",
    "template": "[template]",
    "source_run_id": "run1",
}


def test_a_platform_is_published_listed_read_and_purged():
    published = journal_client.publish_platform(**PLATFORM)
    journal_client.publish_platform(**PLATFORM, version="2.0.0")

    assert published["version"] == "1.0.0"
    assert [(p["id"], p["version"], p["versions"]) for p in journal_client.list_platforms()] == [("woodpecker-ci", "2.0.0", ["1.0.0", "2.0.0"])]
    assert journal_client.get_platform("woodpecker-ci", "1.0.0")["files"]["transformation"] == "module T;"
    assert journal_client.purge_platform("woodpecker-ci", "2.0.0") == ["2.0.0"]
    assert journal_client.purge_platform("woodpecker-ci") == ["1.0.0"]
    assert journal_client.list_platforms() == []


def test_a_platform_or_version_that_is_not_there_reads_as_none():
    assert journal_client.get_platform("nothing") is None
    journal_client.publish_platform(**PLATFORM)
    assert journal_client.get_platform("woodpecker-ci", "9.9.9") is None


def test_a_version_that_exists_is_a_refusal_with_its_reason():
    journal_client.publish_platform(**PLATFORM, version="1.0.0")

    with pytest.raises(AgentServiceError) as error:
        journal_client.publish_platform(**PLATFORM, version="1.0.0")

    assert error.value.status_code == 409 and "never changed" in error.value.detail


def test_a_file_over_the_limit_is_a_refusal_and_not_an_outage():
    with patch("journal.settings.MAX_ARTIFACT_BYTES", 10):
        with pytest.raises(AgentServiceError) as error:
            journal_client.publish_platform(**{**PLATFORM, "metamodel": "x" * 11})

    assert error.value.status_code == 413


def test_the_swarch_to_pim_transformation_is_read(folders):
    folders[2].mkdir(exist_ok=True)
    (folders[2] / "swarch2pim.atl").write_text("module S;", encoding="utf-8")

    assert journal_client.get_pim_transformation() == "module S;"


# --- compiled output and generated pipelines ----------------------------------------------------


def test_compiled_output_is_kept_with_a_reserved_attempt_and_read_back_as_bytes():
    attempt = journal_client.reserve_attempt("r1", "atl")

    journal_client.keep_compiled_output("r1", "atl", attempt, b"PK\x03\x04 zipped \xff")

    assert journal_client.get_compiled_output("r1", "atl", attempt) == b"PK\x03\x04 zipped \xff"
    assert journal_client.get_compiled_output("r1", "psm", "attempt_1") is None


def test_compiled_output_for_an_attempt_that_is_not_reserved_is_refused():
    with pytest.raises(AgentServiceError) as error:
        journal_client.keep_compiled_output("r1", "atl", "attempt_1", b"x")

    assert error.value.status_code == 404


def _result(pipeline="stages: [build]\n"):
    return {
        "platform": {"id": "gitlab", "name": "GitLab CI"},
        "output_filename": ".gitlab-ci.yml",
        "pipeline": pipeline,
        "intermediates": [],
    }


def test_a_pipeline_is_kept_listed_and_read_back():
    pipeline_id = journal_client.record_pipeline(
        key="path:/m/a.swarch", swarch_name="a.swarch", source_path="/m/a.swarch", content="<a/>", results=[_result()]
    )

    [summary] = journal_client.list_pipelines()
    assert summary["id"] == pipeline_id and summary["swarch_name"] == "a.swarch"
    record = journal_client.get_pipeline(pipeline_id)
    assert record["content"] == "<a/>" and record["results"][0]["pipeline"] == "stages: [build]\n"
    assert journal_client.get_pipeline("0" * 32) is None


def test_a_newer_generation_for_the_same_key_keeps_the_id_and_a_bad_result_is_refused():
    first = journal_client.record_pipeline(key="k", swarch_name="a", source_path=None, content="<old/>", results=[_result()])
    second = journal_client.record_pipeline(key="k", swarch_name="a", source_path=None, content="<new/>", results=[_result()])

    assert second == first
    with pytest.raises(AgentServiceError) as error:
        journal_client.record_pipeline(key="k2", swarch_name="a", source_path=None, content="<x/>", results=[_result(pipeline=None)])
    assert error.value.status_code == 422
