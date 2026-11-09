"""Chats are kept on disk and come back after a restart, so nothing is narrated
twice. A restart is simulated by forgetting the in-memory chats and loading
from the chats folder."""
import logging
import threading
import time
from unittest.mock import patch

import pytest
from fastapi.testclient import TestClient

import chat_log
import main
import settings
from chat_log import ChatLog
from chat_store import ChatStore
from journal import Journal


@pytest.fixture(autouse=True)
def _no_store_left_behind():
    yield
    chat_log._store = None


@pytest.fixture
def store(tmp_path):
    return ChatStore(tmp_path / "chats")


def _chat(store, run_id="r1"):
    return ChatLog(run_id, store)


def _records(store, run_id="r1"):
    return Journal(store._directory / f"{run_id}.jsonl").read()[0]


def _restart(tmp_path):
    chat_log._chat_logs.clear()
    chat_log.load_saved(ChatStore(tmp_path / "chats"))


def _raw(types, run_id="r1"):
    events = [{"type": t, "stage": "docs", "data": {}, "timestamp": float(i)} for i, t in enumerate(types)]
    return {"run_id": run_id, "events": events, "current_stage": "docs", "busy": False, "model": None, "is_current": True}


def _wait_for_narration(run_id):
    thread = chat_log.get_chat_log(run_id)._last_thread
    if thread is not None:
        thread.join(timeout=5)


# --- keeping a chat ------------------------------------------------------------


def test_what_is_added_to_a_chat_is_written_to_its_own_journal(store):
    chat = _chat(store)

    chat.add([{"type": "user_message", "text": "hi"}], raw_seen=3)

    assert _records(store) == [{"raw_seen": 3}, {"event": {"type": "user_message", "text": "hi"}}]


def test_adding_writes_only_what_is_new(store):
    chat = _chat(store)
    chat.add([{"type": "message", "text": "x" * 100_000}], raw_seen=1)
    before = (store._directory / "r1.jsonl").stat().st_size

    chat.add([{"type": "message", "text": "short"}])

    assert (store._directory / "r1.jsonl").stat().st_size - before < 200
    assert [r for r in _records(store) if "raw_seen" in r] == [{"raw_seen": 1}]


def test_a_chat_comes_back_as_it_was(store, tmp_path):
    chat = _chat(store)
    chat.add([{"type": "call_started"}, {"type": "message", "text": "ok"}], raw_seen=1)
    chat.add([{"type": "user_message", "text": "hi"}])

    loaded = ChatStore(tmp_path / "chats").load_all()

    assert loaded["r1"].events == chat.events
    assert loaded["r1"].raw_seen == 1
    assert loaded["r1"].run_id == "r1"


def test_a_chat_without_a_store_is_not_written(tmp_path):
    ChatLog("r1").add([{"type": "message"}])

    assert not (tmp_path / "chats").exists()


def test_the_disk_failing_is_logged_and_the_chat_goes_on(store, monkeypatch, caplog):
    chat = _chat(store)
    monkeypatch.setattr(Journal, "rewrite", lambda *args: (_ for _ in ()).throw(OSError("full")))

    with caplog.at_level(logging.ERROR):
        chat.add([{"type": "message"}])

    assert len(chat.events) == 1
    assert "could not keep the chat" in caplog.text


def test_after_an_add_that_failed_part_way_the_next_save_writes_the_chat_whole_so_no_event_is_kept_twice(store, monkeypatch):
    chat = _chat(store)
    chat.add([{"type": "a"}])
    real_append = Journal.append

    def writes_then_fails(self, records):
        real_append(self, records)
        raise OSError("disk went away")

    with monkeypatch.context() as patched:
        patched.setattr(Journal, "append", writes_then_fails)
        chat.add([{"type": "b"}])

    chat.add([{"type": "c"}])

    assert [r["event"]["type"] for r in _records(store) if "event" in r] == ["a", "b", "c"]


def test_replying_is_kept(store):
    chat = _chat(store)
    message = {"type": "user_message", "text": "hi"}

    chat_log.begin_reply(chat, message)

    assert [r["event"]["type"] for r in _records(store) if "event" in r] == ["user_message"]

    chat_log.end_reply(chat, message, [{"type": "message", "text": "hello"}])

    assert [r["event"]["type"] for r in _records(store) if "event" in r] == ["user_message", "message"]


def test_a_reply_that_started_a_new_run_is_kept_in_both_chats(store):
    old, new = _chat(store, "old"), _chat(store, "new")
    message = {"type": "user_message", "text": "start"}

    chat_log.begin_reply(old, message)
    chat_log.end_reply(old, message, [{"type": "message", "text": "started"}], new)

    assert [r["event"]["type"] for r in _records(store, "new") if "event" in r] == ["user_message", "message"]


# --- bringing chats back -------------------------------------------------------


def test_a_restart_mirrors_and_narrates_nothing_twice(tmp_path):
    chat_log.load_saved(ChatStore(tmp_path / "chats"))
    calls = []

    def reactor(event, history):
        calls.append(event["type"])
        return {"message": f"Narrated: {event['type']}", "model": "m"}

    raw = _raw(["call_started", "call_completed"])
    with patch.object(chat_log, "_reactor", reactor), patch.object(chat_log.integration_runner_client, "get_events", return_value=raw):
        chat_log.get_events("r1")
        _wait_for_narration("r1")
        before = chat_log.get_events("r1")["events"]
        _restart(tmp_path)
        after = chat_log.get_events("r1")["events"]
        _wait_for_narration("r1")

    assert calls == ["call_started", "call_completed"]
    assert [e["type"] for e in after] == [e["type"] for e in before] == ["call_started", "call_completed", "message", "message"]


def test_an_event_that_arrives_after_a_restart_is_narrated_once(tmp_path):
    chat_log.load_saved(ChatStore(tmp_path / "chats"))
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
        _restart(tmp_path)
        with patch.object(chat_log.integration_runner_client, "get_events", return_value=_raw(["call_started", "call_completed", "call_failed"])):
            chat_log.get_events("r1")
            _wait_for_narration("r1")

    assert calls == ["call_started", "call_completed", "call_failed"]


def test_events_that_are_mirrored_but_not_narrated_are_kept_too(tmp_path):
    chat_log.load_saved(ChatStore(tmp_path / "chats"))
    raw = _raw(["constraint_added"])

    with patch.object(chat_log.integration_runner_client, "get_events", return_value=raw):
        chat_log.get_events("r1")
        kept = Journal(tmp_path / "chats" / "r1.jsonl").read()[0]
        _restart(tmp_path)
        after = chat_log.get_events("r1")["events"]

    assert kept == [{"raw_seen": 1}, {"event": raw["events"][0]}]
    assert [e["type"] for e in after] == ["constraint_added"]


def test_a_chat_already_in_memory_is_left_alone(tmp_path, store):
    on_disk = _chat(store)
    on_disk.add([{"type": "message"}])
    in_memory = chat_log.get_chat_log("r1")

    chat_log.load_saved(ChatStore(tmp_path / "chats"))

    assert chat_log.get_chat_log("r1") is in_memory


def test_a_chat_cut_short_by_a_crash_loses_only_its_last_line_and_is_written_whole_on_the_next_save(tmp_path, store):
    chat = _chat(store)
    chat.add([{"type": "a"}, {"type": "b"}], raw_seen=2)
    with open(store._directory / "r1.jsonl", "ab") as handle:
        handle.write(b'{"event": {"typ')

    loaded = ChatStore(tmp_path / "chats").load_all()["r1"]
    loaded.add([{"type": "c"}])

    records, clean = Journal(store._directory / "r1.jsonl").read()
    assert clean is True
    assert [r["event"]["type"] for r in records if "event" in r] == ["a", "b", "c"]


@pytest.mark.parametrize("content", [
    "{not json\n", "[]\n", '{"mystery": 1}\n', '{"event": 5}\n', '{"raw_seen": "3"}\n', '{"raw_seen": true}\n', '{"raw_seen": -1}\n',
])
def test_a_file_that_is_not_a_saved_chat_is_skipped_and_the_others_still_come_back(store, content, caplog):
    good = _chat(store, "good")
    good.add([{"type": "a"}])
    (store._directory / "bad.jsonl").write_text(content, encoding="utf-8")
    (store._directory / "notes.txt").write_text("not a chat", encoding="utf-8")

    with caplog.at_level(logging.WARNING):
        loaded = ChatStore(store._directory).load_all()

    assert list(loaded) == ["good"]
    assert "skipping" in caplog.text


def test_a_missing_folder_holds_no_chats(tmp_path):
    assert ChatStore(tmp_path / "nothing").load_all() == {}


def test_the_service_loads_its_chats_when_it_starts(tmp_path, monkeypatch):
    monkeypatch.setattr(settings, "CHATS_DIR", tmp_path / "chats")
    chat = ChatLog("started")
    chat.events.append({"type": "message", "text": "kept"})
    ChatStore(tmp_path / "chats").save(chat)

    with TestClient(main.app):
        pass

    assert chat_log.get_chat_log("started").events == [{"type": "message", "text": "kept"}]


def test_a_chat_with_nothing_in_it_is_not_written(store):
    _chat(store).add([])

    assert not store._directory.exists()


def test_a_journal_that_vanished_between_saves_is_written_whole_again(store):
    chat = _chat(store)
    chat.add([{"type": "a"}], raw_seen=1)
    (store._directory / "r1.jsonl").unlink()

    chat.add([{"type": "b"}])

    assert _records(store) == [{"raw_seen": 1}, {"event": {"type": "a"}}, {"event": {"type": "b"}}]


@pytest.mark.parametrize("run_id", ["../evil", "a/b", "", "x" * 65, None])
def test_a_run_id_that_cannot_name_a_file_is_not_kept(store, run_id, caplog):
    chat = ChatLog(run_id, store)

    with caplog.at_level(logging.ERROR):
        chat.add([{"type": "a"}])

    assert len(chat.events) == 1
    assert not store._directory.exists()
    assert "could not keep" in caplog.text


def test_a_disk_that_stays_broken_is_logged_once(store, monkeypatch, caplog):
    chat = _chat(store)
    monkeypatch.setattr(Journal, "rewrite", lambda *args: (_ for _ in ()).throw(OSError("full")))

    with caplog.at_level(logging.ERROR):
        for _ in range(5):
            chat.add([{"type": "a"}])

    assert caplog.text.count("could not keep the chat") == 1


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
