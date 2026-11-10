import pytest

from journal.file import Journal
from journal.histories import BadHistory, HistoryGap, HistoryStore, UnknownHistory


@pytest.fixture
def store(tmp_path):
    return HistoryStore(
        lambda key: tmp_path / key / "history.jsonl",
        lambda: [folder.name for folder in tmp_path.iterdir() if (folder / "history.jsonl").is_file()],
    )


def _journal(tmp_path, key="one"):
    return Journal(tmp_path / key / "history.jsonl")


def _records(tmp_path, key="one"):
    return _journal(tmp_path, key).read()[0]


def _numbers(tmp_path, key="one"):
    return [r["event"]["n"] for r in _records(tmp_path, key) if "event" in r]


# --- writing ------------------------------------------------------------------------


def test_a_history_is_written_whole_with_its_state_first(store, tmp_path):
    store.replace("one", {"stage": 0}, [{"n": 1}, {"n": 2}])

    assert _records(tmp_path) == [{"state": {"stage": 0}}, {"event": {"n": 1}}, {"event": {"n": 2}}]


def test_replacing_a_history_leaves_only_the_new_one(store, tmp_path):
    store.replace("one", {"stage": 0}, [{"n": 1}, {"n": 2}])

    assert store.replace("one", {"stage": 5}, [{"n": 9}]) == 1

    assert _records(tmp_path) == [{"state": {"stage": 5}}, {"event": {"n": 9}}]


def test_an_append_adds_only_the_new_events(store, tmp_path):
    store.replace("one", {}, [{"n": 1, "text": "x" * 100_000}])
    before = _journal(tmp_path).path.stat().st_size

    count = store.append("one", 1, {}, [{"n": 2}])

    assert count == 2
    assert _journal(tmp_path).path.stat().st_size - before == len('{"event": {"n": 2}}\n')


def test_the_state_of_an_append_goes_before_its_events_and_is_merged_in(store, tmp_path):
    store.replace("one", {"stage": 0, "model": None}, [{"n": 1}])

    store.append("one", 1, {"stage": 1}, [{"n": 2}])

    assert _records(tmp_path)[-2:] == [{"state": {"stage": 1}}, {"event": {"n": 2}}]
    assert store.read("one")[1] == {"stage": 1, "model": None}


def test_an_append_with_no_state_changes_writes_no_state_record(store, tmp_path):
    store.replace("one", {"stage": 0}, [{"n": 1}])

    store.append("one", 1, {}, [{"n": 2}])

    assert [r for r in _records(tmp_path) if "state" in r] == [{"state": {"stage": 0}}]


def test_an_append_of_state_only_adds_no_event(store):
    store.replace("one", {"stage": 0}, [{"n": 1}])

    assert store.append("one", 1, {"stage": 3}, []) == 1
    assert store.read("one") == ([{"n": 1}], {"stage": 3})


def test_an_append_sent_twice_is_kept_once(store, tmp_path):
    store.replace("one", {}, [{"n": 1}])
    store.append("one", 1, {"stage": 1}, [{"n": 2}])

    count = store.append("one", 1, {"stage": 1}, [{"n": 2}])

    assert count == 2
    assert _numbers(tmp_path) == [1, 2]
    assert store.read("one")[1] == {"stage": 1}


def test_an_append_starting_inside_the_history_replaces_from_there(store, tmp_path):
    store.replace("one", {}, [{"n": 1}, {"n": 2}, {"n": 3}])

    assert store.append("one", 1, {}, [{"n": 20}]) == 2

    assert _numbers(tmp_path) == [1, 20]


def test_a_rewrite_for_an_overlapping_append_keeps_the_state_fields_it_does_not_change(store):
    store.replace("one", {"stage": 0, "model": "m"}, [{"n": 1}, {"n": 2}])

    store.append("one", 1, {"stage": 3}, [{"n": 20}])

    assert store.read("one") == ([{"n": 1}, {"n": 20}], {"stage": 3, "model": "m"})


def test_an_append_starting_past_the_end_is_a_gap_that_says_how_many_are_kept(store, tmp_path):
    store.replace("one", {}, [{"n": 1}])

    with pytest.raises(HistoryGap) as gap:
        store.append("one", 3, {}, [{"n": 4}])

    assert gap.value.count == 1
    assert _numbers(tmp_path) == [1]


def test_an_append_to_a_history_not_kept_starts_it_when_it_begins_at_the_start(store, tmp_path):
    assert store.append("one", 0, {"stage": 0}, [{"n": 1}]) == 1

    assert _records(tmp_path) == [{"state": {"stage": 0}}, {"event": {"n": 1}}]


def test_an_append_to_a_history_not_kept_is_a_gap_when_it_begins_later(store, tmp_path):
    with pytest.raises(HistoryGap) as gap:
        store.append("one", 2, {}, [{"n": 3}])

    assert gap.value.count == 0
    assert not (tmp_path / "one").exists()


def test_a_journal_that_vanished_is_a_gap(store, tmp_path):
    store.replace("one", {}, [{"n": 1}])
    _journal(tmp_path).path.unlink()

    with pytest.raises(HistoryGap) as gap:
        store.append("one", 1, {}, [{"n": 2}])

    assert gap.value.count == 0


def test_the_count_is_not_read_from_the_file_at_every_append(store, tmp_path, monkeypatch):
    store.replace("one", {}, [{"n": 1}])
    monkeypatch.setattr(Journal, "read", lambda self: pytest.fail("the journal was read"))

    store.append("one", 1, {}, [{"n": 2}])
    store.append("one", 2, {}, [{"n": 3}])


def test_after_a_restart_the_count_is_read_once_and_the_append_still_only_adds(store, tmp_path):
    store.replace("one", {"stage": 0}, [{"n": 1}])
    restarted = HistoryStore(store._path_for, store._keys_in_folder)
    before = _journal(tmp_path).path.read_bytes()

    restarted.append("one", 1, {}, [{"n": 2}])

    assert _journal(tmp_path).path.read_bytes().startswith(before)
    assert _numbers(tmp_path) == [1, 2]


def test_a_journal_ending_in_a_cut_short_line_is_written_whole_and_clean_before_the_append(store, tmp_path):
    store.replace("one", {"stage": 0}, [{"n": 1}, {"n": 2}])
    with open(_journal(tmp_path).path, "ab") as handle:
        handle.write(b'{"event": {"n": ')
    restarted = HistoryStore(store._path_for, store._keys_in_folder)

    restarted.append("one", 2, {}, [{"n": 3}])

    assert _journal(tmp_path).read()[1] is True
    assert _numbers(tmp_path) == [1, 2, 3]


def test_a_cut_short_journal_is_still_known_to_be_cut_short_after_an_append_that_started_too_late(store, tmp_path):
    store.replace("one", {"stage": 0}, [{"n": 1}, {"n": 2}])
    with open(_journal(tmp_path).path, "ab") as handle:
        handle.write(b'{"event": {"n": ')
    restarted = HistoryStore(store._path_for, store._keys_in_folder)
    with pytest.raises(HistoryGap):
        restarted.append("one", 5, {}, [{"n": 6}])

    restarted.append("one", 2, {}, [{"n": 3}])

    assert _journal(tmp_path).read()[1] is True
    assert _numbers(tmp_path) == [1, 2, 3]

@pytest.mark.parametrize("key", ["../evil", "a/b", "", "x" * 65, None, 5])
def test_a_key_that_cannot_name_a_file_is_refused_everywhere_and_nothing_is_written(store, tmp_path, key):
    for call in (
        lambda: store.read(key),
        lambda: store.replace(key, {}, [{"n": 1}]),
        lambda: store.append(key, 0, {}, [{"n": 1}]),
    ):
        with pytest.raises(UnknownHistory):
            call()

    assert not any(tmp_path.iterdir())


# --- reading -------------------------------------------------------------------------


def test_a_history_comes_back_as_it_was(store):
    store.replace("one", {"stage": 0, "model": None}, [{"n": 1}])
    store.append("one", 1, {"stage": 1}, [{"n": 2}])

    assert store.read("one") == ([{"n": 1}, {"n": 2}], {"stage": 1, "model": None})


def test_reading_a_key_that_is_not_kept_is_an_error(store):
    with pytest.raises(UnknownHistory):
        store.read("nothing")


def test_a_cut_short_last_line_is_left_out_when_reading(store, tmp_path):
    store.replace("one", {}, [{"n": 1}])
    with open(_journal(tmp_path).path, "ab") as handle:
        handle.write(b'{"event": {"n": ')

    assert store.read("one") == ([{"n": 1}], {})


@pytest.mark.parametrize(
    "content",
    ["{not json\n", "[]\n", '{"mystery": 1}\n', '{"event": 5}\n', '{"event": null}\n', '{"state": 5}\n', '{"state": null}\n'],
)
def test_a_journal_that_is_not_a_history_cannot_be_read(store, tmp_path, content):
    (tmp_path / "bad").mkdir()
    (tmp_path / "bad" / "history.jsonl").write_text(content, encoding="utf-8")

    with pytest.raises(BadHistory):
        store.read("bad")


def test_a_later_state_record_overrides_an_earlier_one_field_by_field(store, tmp_path):
    (tmp_path / "one").mkdir()
    (tmp_path / "one" / "history.jsonl").write_text(
        '{"state": {"a": 1, "b": 1}}\n{"event": {"n": 1}}\n{"state": {"b": 2}}\n', encoding="utf-8"
    )

    assert store.read("one") == ([{"n": 1}], {"a": 1, "b": 2})


def test_the_keys_are_the_histories_kept_in_order(store):
    store.replace("b", {}, [])
    store.replace("a", {}, [])

    assert store.keys() == ["a", "b"]
