import logging

import pytest

from journal import History, Journal


@pytest.fixture
def history(tmp_path):
    return History(lambda key: tmp_path / key / "history.jsonl", "thing")


def _journal(tmp_path, key="one"):
    return Journal(tmp_path / key / "history.jsonl")


def _records(tmp_path, key="one"):
    return _journal(tmp_path, key).read()[0]


def _events(tmp_path, key="one"):
    return [r["event"]["n"] for r in _records(tmp_path, key) if "event" in r]


def _load(history, keys):
    return history.load_all(keys, lambda key, events, state: (key, events, state))


# --- saving ----------------------------------------------------------------------


def test_a_history_not_kept_yet_is_written_whole_with_its_state_first(history, tmp_path):
    history.save("one", [{"n": 1}, {"n": 2}], {"stage": 0})

    assert _records(tmp_path) == [{"state": {"stage": 0}}, {"event": {"n": 1}}, {"event": {"n": 2}}]


def test_a_save_adds_only_the_new_events(history, tmp_path):
    events = [{"n": 1, "text": "x" * 100_000}]
    history.save("one", events, {"stage": 0})
    before = _journal(tmp_path).path.stat().st_size

    events.append({"n": 2})
    history.save("one", events, {"stage": 0})

    assert _journal(tmp_path).path.stat().st_size - before == len('{"event": {"n": 2}}\n')
    assert [r for r in _records(tmp_path) if "state" in r] == [{"state": {"stage": 0}}]


def test_only_the_state_fields_that_changed_are_written_and_before_the_new_events(history, tmp_path):
    history.save("one", [{"n": 1}], {"stage": 0, "model": None})

    history.save("one", [{"n": 1}, {"n": 2}], {"stage": 1, "model": None})

    assert _records(tmp_path)[-2:] == [{"state": {"stage": 1}}, {"event": {"n": 2}}]


def test_a_change_made_later_to_a_value_inside_the_state_is_still_a_change(history, tmp_path):
    state = {"constraints": {"docs": ["a"]}}
    history.save("one", [{"n": 1}], state)

    state["constraints"]["docs"].append("b")
    history.save("one", [{"n": 1}], state)

    assert _records(tmp_path)[-1] == {"state": {"constraints": {"docs": ["a", "b"]}}}


def test_histories_are_kept_apart(history, tmp_path):
    history.save("one", [{"n": 1}], {})
    history.save("two", [{"n": 2}], {})
    history.save("one", [{"n": 1}, {"n": 3}], {})

    assert _events(tmp_path, "one") == [1, 3]
    assert _events(tmp_path, "two") == [2]


def test_a_journal_that_vanished_between_saves_is_written_whole_again(history, tmp_path):
    history.save("one", [{"n": 1}], {"stage": 0})
    _journal(tmp_path).path.unlink()

    history.save("one", [{"n": 1}, {"n": 2}], {"stage": 0})

    assert _records(tmp_path) == [{"state": {"stage": 0}}, {"event": {"n": 1}}, {"event": {"n": 2}}]


def test_events_that_became_fewer_are_written_whole_again(history, tmp_path):
    history.save("one", [{"n": 1}, {"n": 2}], {})

    history.save("one", [{"n": 9}], {})

    assert _events(tmp_path) == [9]


@pytest.mark.parametrize("key", ["../evil", "a/b", "", "x" * 65, None, 5])
def test_a_key_that_cannot_name_a_file_is_refused_and_nothing_is_written(history, tmp_path, key):
    with pytest.raises(ValueError, match="key"):
        history.save(key, [{"n": 1}], {})

    assert not any(tmp_path.iterdir())


# --- when the disk fails -----------------------------------------------------------


def test_after_a_save_that_failed_part_way_the_next_save_writes_the_whole_history_so_no_event_is_kept_twice(
    history, tmp_path, monkeypatch
):
    history.save("one", [{"n": 1}], {})
    real_append = Journal.append

    def writes_then_fails(self, records):
        real_append(self, records)
        raise OSError("disk went away")

    with monkeypatch.context() as patched:
        patched.setattr(Journal, "append", writes_then_fails)
        with pytest.raises(OSError):
            history.save("one", [{"n": 1}, {"n": 2}], {})

    history.save("one", [{"n": 1}, {"n": 2}, {"n": 3}], {})

    assert _events(tmp_path) == [1, 2, 3]


def test_a_failure_is_raised_by_save_and_only_logged_by_save_quietly(history, monkeypatch, caplog):
    monkeypatch.setattr(Journal, "rewrite", lambda *args: (_ for _ in ()).throw(OSError("full")))

    with pytest.raises(OSError):
        history.save("one", [{"n": 1}], {})
    with caplog.at_level(logging.ERROR):
        history.save_quietly("one", [{"n": 1}], {})

    assert "could not keep thing one on disk" in caplog.text


def test_a_disk_that_stays_broken_is_logged_once(history, monkeypatch, caplog):
    monkeypatch.setattr(Journal, "rewrite", lambda *args: (_ for _ in ()).throw(OSError("full")))

    with caplog.at_level(logging.ERROR):
        for _ in range(5):
            history.save_quietly("one", [{"n": 1}], {})

    assert caplog.text.count("could not keep") == 1


def test_a_disk_that_breaks_again_after_working_is_logged_again(history, monkeypatch, caplog):
    def broken(*args):
        raise OSError("full")

    with caplog.at_level(logging.ERROR):
        with monkeypatch.context() as patched:
            patched.setattr(Journal, "rewrite", broken)
            history.save_quietly("one", [{"n": 1}], {})
        history.save_quietly("one", [{"n": 1}], {})
        with monkeypatch.context() as patched:
            patched.setattr(Journal, "rewrite", broken)
            patched.setattr(Journal, "append", broken)
            history.save_quietly("one", [{"n": 1}, {"n": 2}], {})

    assert caplog.text.count("could not keep") == 2


def test_one_key_failing_does_not_hide_another_from_the_log(history, monkeypatch, caplog):
    monkeypatch.setattr(Journal, "rewrite", lambda *args: (_ for _ in ()).throw(OSError("full")))

    with caplog.at_level(logging.ERROR):
        history.save_quietly("one", [{"n": 1}], {})
        history.save_quietly("two", [{"n": 1}], {})

    assert caplog.text.count("could not keep") == 2


# --- loading -------------------------------------------------------------------------


def test_a_history_comes_back_as_it_was(history, tmp_path):
    history.save("one", [{"n": 1}], {"stage": 0, "model": None})
    history.save("one", [{"n": 1}, {"n": 2}], {"stage": 1, "model": None})

    loaded = _load(History(lambda key: tmp_path / key / "history.jsonl", "thing"), ["one"])

    assert loaded == [("one", [{"n": 1}, {"n": 2}], {"stage": 1, "model": None})]


def test_after_loading_a_save_adds_to_the_journal_and_does_not_write_it_again(history, tmp_path, monkeypatch):
    history.save("one", [{"n": 1}], {"stage": 0})
    again = History(lambda key: tmp_path / key / "history.jsonl", "thing")
    _load(again, ["one"])
    before = _journal(tmp_path).path.read_bytes()
    monkeypatch.setattr(Journal, "rewrite", lambda *args: pytest.fail("written whole"))

    again.save("one", [{"n": 1}, {"n": 2}], {"stage": 0})

    assert _journal(tmp_path).path.read_bytes().startswith(before)
    assert _events(tmp_path) == [1, 2]


def test_a_journal_ending_in_a_cut_short_line_loads_and_is_written_whole_at_its_next_save(history, tmp_path):
    history.save("one", [{"n": 1}, {"n": 2}], {"stage": 0})
    with open(_journal(tmp_path).path, "ab") as handle:
        handle.write(b'{"event": {"n": ')
    again = History(lambda key: tmp_path / key / "history.jsonl", "thing")

    loaded = _load(again, ["one"])
    again.save("one", [{"n": 1}, {"n": 2}, {"n": 3}], {"stage": 0})

    assert loaded[0][1] == [{"n": 1}, {"n": 2}]
    assert _journal(tmp_path).read()[1] is True
    assert _events(tmp_path) == [1, 2, 3]


BAD_JOURNALS = [
    "{not json\n",
    "[]\n",
    '{"mystery": 1}\n',
    '{"event": 5}\n',
    '{"event": null}\n',
    '{"state": 5}\n',
    '{"state": null}\n',
]


@pytest.mark.parametrize("content", BAD_JOURNALS)
def test_a_journal_that_is_not_a_history_is_skipped_and_logged_and_the_rest_load(history, tmp_path, content, caplog):
    history.save("good", [{"n": 1}], {})
    (tmp_path / "bad").mkdir()
    (tmp_path / "bad" / "history.jsonl").write_text(content, encoding="utf-8")

    with caplog.at_level(logging.WARNING):
        loaded = _load(History(lambda key: tmp_path / key / "history.jsonl", "thing"), ["bad", "good"])

    assert [key for key, _, _ in loaded] == ["good"]
    assert "skipping" in caplog.text


def test_a_key_with_no_journal_is_skipped(history, caplog):
    with caplog.at_level(logging.WARNING):
        loaded = _load(history, ["nothing"])

    assert loaded == []
    assert "skipping" in caplog.text


def test_a_key_that_cannot_name_a_file_is_skipped_when_loading_even_if_a_journal_is_there(tmp_path, caplog):
    outside = Journal(tmp_path / "outside" / "history.jsonl")
    outside.append([{"event": {"n": 1}}])
    (tmp_path / "kept").mkdir()
    history = History(lambda key: tmp_path / "kept" / key / "history.jsonl", "thing")
    # kept/../outside/history.jsonl is the journal above.
    key = "../outside"

    with caplog.at_level(logging.WARNING):
        loaded = _load(history, [key])

    assert loaded == []
    assert "skipping" in caplog.text


def test_a_journal_the_caller_cannot_build_is_skipped_and_is_not_treated_as_kept(history, tmp_path, caplog):
    history.save("one", [{"n": 1}], {"stage": 0})
    again = History(lambda key: tmp_path / key / "history.jsonl", "thing")

    def refuses(key, events, state):
        raise ValueError("not mine")

    with caplog.at_level(logging.WARNING):
        assert again.load_all(["one"], refuses) == []
    again.save("one", [{"n": 1}, {"n": 2}], {"stage": 0})

    assert "not mine" in caplog.text
    assert _records(tmp_path) == [{"state": {"stage": 0}}, {"event": {"n": 1}}, {"event": {"n": 2}}]
