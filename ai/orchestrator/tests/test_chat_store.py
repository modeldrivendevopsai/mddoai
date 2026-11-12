"""Chats are kept in the journal and come back after a restart, so nothing is
narrated twice. A restart is simulated by forgetting the in-memory chats and
loading from the journal. The journal is the real one, in this process, over
folders of the test alone (see conftest.py's journal fixture)."""
import json
import logging
import threading
import time
from contextlib import contextmanager
from unittest.mock import patch

import httpx
import pytest
from fastapi.testclient import TestClient

import chat_log
import main
from chat_log import ChatLog
from chat_store import ChatStore
from clients import journal_client
from clients.journal_testing import restart_journal


@pytest.fixture(autouse=True)
def _no_store_left_behind():
    yield
    chat_log._store = None


@pytest.fixture
def store():
    return ChatStore()


def _chat(store, run_id="r1"):
    return ChatLog(run_id, store)


def _kept(run_id="r1"):
    """What the journal holds for the chat: its events, and its state."""
    body = journal_client._request("GET", f"/histories/chats/{run_id}").json()
    return body["events"], body["state"]


def _kept_types(run_id="r1"):
    return [event["type"] for event in _kept(run_id)[0]]


def _sent():
    """The requests made to the journal, as (method, path, body)."""
    sent = []
    for call in journal_client.httpx.request.call_args_list:
        body = json.loads(call.kwargs["content"]) if "content" in call.kwargs else None
        sent.append((call.args[0], call.args[1].removeprefix(journal_client.JOURNAL_URL), body))
    return sent


@contextmanager
def _journal_behaving(behaviour):
    request = journal_client.httpx.request
    real = request.side_effect
    request.side_effect = behaviour
    try:
        yield real
    finally:
        request.side_effect = real


def _journal_down():
    def down(*args, **kwargs):
        raise httpx.ConnectError("the journal is down")

    return _journal_behaving(down)


def _restart():
    chat_log._chat_logs.clear()
    chat_log.load_saved(ChatStore())


def _raw(types, run_id="r1"):
    events = [{"type": t, "stage": "docs", "data": {}, "timestamp": float(i)} for i, t in enumerate(types)]
    return {"run_id": run_id, "events": events, "current_stage": "docs", "busy": False, "model": None, "is_current": True}


def _wait_for_narration(run_id):
    thread = chat_log.get_chat_log(run_id)._last_thread
    if thread is not None:
        thread.join(timeout=5)


# --- keeping a chat ------------------------------------------------------------


def test_what_is_added_to_a_chat_is_kept_in_the_journal(store):
    chat = _chat(store)

    chat.add([{"type": "user_message", "text": "hi"}], raw_seen=3)

    assert _kept() == ([{"type": "user_message", "text": "hi"}], {"raw_seen": 3})


def test_adding_sends_only_what_is_new(store):
    chat = _chat(store)
    chat.add([{"type": "message", "text": "x" * 100_000}], raw_seen=1)
    journal_client.httpx.request.reset_mock()

    chat.add([{"type": "message", "text": "short"}])

    [(method, path, body)] = _sent()
    assert (method, path.rsplit("/", 1)[-1]) == ("POST", "append")
    assert body["events"] == [{"type": "message", "text": "short"}] and body["state"] == {}


def test_a_chat_comes_back_as_it_was(store):
    chat = _chat(store)
    chat.add([{"type": "call_started"}, {"type": "message", "text": "ok"}], raw_seen=1)
    chat.add([{"type": "user_message", "text": "hi"}])

    loaded = ChatStore().load_all()

    assert loaded["r1"].events == chat.events
    assert loaded["r1"].raw_seen == 1
    assert loaded["r1"].run_id == "r1"


def test_a_chat_without_a_store_is_not_kept():
    ChatLog("r1").add([{"type": "message"}])

    assert journal_client._request("GET", "/histories/chats").json() == {"keys": []}


def test_the_journal_being_down_is_logged_and_the_chat_goes_on(store, caplog):
    chat = _chat(store)

    with _journal_down(), caplog.at_level(logging.ERROR):
        chat.add([{"type": "message"}])

    assert len(chat.events) == 1
    assert "could not keep chat" in caplog.text


def test_when_the_journal_is_back_the_next_save_sends_everything_since_the_last_good_one(store):
    chat = _chat(store)
    chat.add([{"type": "a"}])
    with _journal_down():
        chat.add([{"type": "b"}])

    chat.add([{"type": "c"}])

    assert _kept_types() == ["a", "b", "c"]


def test_a_save_whose_answer_was_lost_does_not_keep_an_event_twice(store):
    chat = _chat(store)
    chat.add([{"type": "a"}])
    lost = []

    def answer_lost(method, url, **kwargs):
        response = real(method, url, **kwargs)
        if method == "POST" and not lost:
            lost.append(True)
            raise httpx.ReadTimeout("the answer never came")
        return response

    with _journal_behaving(answer_lost) as real:
        chat.add([{"type": "b"}])
    chat.add([{"type": "c"}])

    assert _kept_types() == ["a", "b", "c"]


def test_replying_is_kept(store):
    chat = _chat(store)
    message = {"type": "user_message", "text": "hi"}

    chat_log.begin_reply(chat, message)

    assert _kept_types() == ["user_message"]

    chat_log.end_reply(chat, message, [{"type": "message", "text": "hello"}])

    assert _kept_types() == ["user_message", "message"]


def test_a_reply_that_started_a_new_run_is_kept_in_both_chats(store):
    old, new = _chat(store, "old"), _chat(store, "new")
    message = {"type": "user_message", "text": "start"}

    chat_log.begin_reply(old, message)
    chat_log.end_reply(old, message, [{"type": "message", "text": "started"}], new)

    assert _kept_types("new") == ["user_message", "message"]


def test_a_chat_with_nothing_in_it_is_not_kept(store):
    _chat(store).add([])

    assert journal_client._request("GET", "/histories/chats").json() == {"keys": []}


@pytest.mark.parametrize("run_id", ["../evil", "a/b", "", "x" * 65, None])
def test_a_run_id_that_cannot_name_a_file_is_not_kept(store, run_id, caplog):
    chat = ChatLog(run_id, store)

    with caplog.at_level(logging.ERROR):
        chat.add([{"type": "a"}])

    assert len(chat.events) == 1
    assert journal_client._request("GET", "/histories/chats").json() == {"keys": []}
    assert "could not keep" in caplog.text


def test_a_journal_that_stays_down_is_logged_once(store, caplog):
    chat = _chat(store)

    with _journal_down(), caplog.at_level(logging.ERROR):
        for _ in range(5):
            chat.add([{"type": "a"}])

    assert caplog.text.count("could not keep chat") == 1


def test_one_thread_at_a_time_adds_to_a_chat():
    inside = most = 0
    guard = threading.Lock()

    class SlowStore:
        def save_quietly(self, chat):
            nonlocal inside, most
            with guard:
                inside += 1
                most = max(most, inside)
            time.sleep(0.01)
            with guard:
                inside -= 1

    chat = ChatLog("r1", SlowStore())
    threads = [threading.Thread(target=chat.add, args=([{"type": "x"}],)) for _ in range(8)]
    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()

    assert most == 1
    assert len(chat.events) == 8


# --- bringing chats back -------------------------------------------------------


def test_a_restart_mirrors_and_narrates_nothing_twice():
    chat_log.load_saved(ChatStore())
    calls = []

    def reactor(event, history):
        calls.append(event["type"])
        return {"message": f"Narrated: {event['type']}", "model": "m"}

    raw = _raw(["call_started", "call_completed"])
    with patch.object(chat_log, "_reactor", reactor), patch.object(chat_log.integration_runner_client, "get_events", return_value=raw):
        chat_log.get_events("r1")
        _wait_for_narration("r1")
        before = chat_log.get_events("r1")["events"]
        _restart()
        after = chat_log.get_events("r1")["events"]
        _wait_for_narration("r1")

    # Only the newest event is commented on, and the restart adds no second comment.
    assert calls == ["call_completed"]
    assert [e["type"] for e in after] == [e["type"] for e in before] == ["call_started", "call_completed", "message"]


def test_a_poll_with_nothing_new_makes_no_request_to_the_journal():
    chat_log.load_saved(ChatStore())
    raw = _raw(["constraint_added"])

    with patch.object(chat_log.integration_runner_client, "get_events", return_value=raw):
        chat_log.get_events("r1")
        journal_client.httpx.request.reset_mock()
        for _ in range(5):
            chat_log.get_events("r1")

    journal_client.httpx.request.assert_not_called()


def test_an_event_that_arrives_after_a_restart_is_narrated_once():
    chat_log.load_saved(ChatStore())
    calls = []

    def reactor(event, history):
        calls.append(event["type"])
        return {"message": "ok", "model": "m"}

    with patch.object(chat_log, "_reactor", reactor):
        with patch.object(chat_log.integration_runner_client, "get_events", return_value=_raw(["call_started"])):
            chat_log.get_events("r1")
            _wait_for_narration("r1")
        with patch.object(chat_log.integration_runner_client, "get_events", return_value=_raw(["call_started", "call_completed"])):
            chat_log.get_events("r1")
            _wait_for_narration("r1")
        _restart()
        with patch.object(chat_log.integration_runner_client, "get_events", return_value=_raw(["call_started", "call_completed", "call_failed"])):
            chat_log.get_events("r1")
            _wait_for_narration("r1")

    assert calls == ["call_started", "call_completed", "call_failed"]


def test_events_that_are_mirrored_but_not_narrated_are_kept_too():
    chat_log.load_saved(ChatStore())
    raw = _raw(["constraint_added"])

    with patch.object(chat_log.integration_runner_client, "get_events", return_value=raw):
        chat_log.get_events("r1")
        kept = _kept()
        _restart()
        after = chat_log.get_events("r1")["events"]

    assert kept == ([raw["events"][0]], {"raw_seen": 1})
    assert [e["type"] for e in after] == ["constraint_added"]


def test_a_chat_already_in_memory_is_left_alone(store):
    on_disk = _chat(store)
    on_disk.add([{"type": "message"}])
    in_memory = chat_log.get_chat_log("r1")

    chat_log.load_saved(ChatStore())

    assert chat_log.get_chat_log("r1") is in_memory


def test_a_chat_cut_short_by_a_crash_loses_only_its_last_line_and_the_next_save_leaves_it_whole(store, journal):
    chat = _chat(store)
    chat.add([{"type": "a"}, {"type": "b"}], raw_seen=2)
    with open(journal / "chats" / "r1.jsonl", "ab") as handle:
        handle.write(b'{"event": {"typ')
    restart_journal()

    loaded = ChatStore().load_all()["r1"]
    loaded.add([{"type": "c"}])

    assert (journal / "chats" / "r1.jsonl").read_text(encoding="utf-8").endswith(chr(10))
    assert _kept_types() == ["a", "b", "c"]


@pytest.mark.parametrize("content", [
    "{not json\n", "[]\n", '{"mystery": 1}\n', '{"event": 5}\n', '{"raw_seen": 3}\n', '{"state": 5}\n',
    '{"state": {"raw_seen": "3"}}\n', '{"state": {"raw_seen": true}}\n', '{"state": {"raw_seen": -1}}\n',
])
def test_a_chat_that_is_not_a_saved_chat_is_skipped_and_the_others_still_come_back(store, journal, content, caplog):
    good = _chat(store, "good")
    good.add([{"type": "a"}])
    (journal / "chats" / "bad.jsonl").write_text(content, encoding="utf-8")
    (journal / "chats" / "notes.txt").write_text("not a chat", encoding="utf-8")

    with caplog.at_level(logging.WARNING):
        loaded = ChatStore().load_all()

    assert list(loaded) == ["good"]
    assert "skipping chat bad" in caplog.text


def test_a_journal_with_no_chats_holds_none():
    assert ChatStore().load_all() == {}


def test_the_service_loads_its_chats_when_it_starts():
    chat = ChatLog("started")
    chat.events.append({"type": "message", "text": "kept"})
    ChatStore().save_quietly(chat)

    with TestClient(main.app):
        pass

    assert chat_log.get_chat_log("started").events == [{"type": "message", "text": "kept"}]


def test_a_history_the_journal_lost_is_sent_whole_again(store, journal):
    chat = _chat(store)
    chat.add([{"type": "a"}], raw_seen=1)
    (journal / "chats" / "r1.jsonl").unlink()

    chat.add([{"type": "b"}])

    assert _kept() == ([{"type": "a"}, {"type": "b"}], {"raw_seen": 1})
