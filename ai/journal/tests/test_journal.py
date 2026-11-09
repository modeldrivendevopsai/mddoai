import pytest

from journal.file import Journal


@pytest.fixture
def journal(tmp_path):
    return Journal(tmp_path / "runs" / "one" / "file.jsonl")


def test_records_come_back_in_the_order_they_were_added(journal):
    journal.append([{"a": 1}, {"b": 2}])
    journal.append([{"c": 3}])

    assert journal.read() == ([{"a": 1}, {"b": 2}, {"c": 3}], True)


def test_adding_writes_only_the_new_records(journal):
    journal.append([{"a": "x" * 1000}])
    before = journal.path.stat().st_size

    journal.append([{"b": 1}])

    assert journal.path.stat().st_size - before == len('{"b": 1}\n')


def test_adding_nothing_creates_no_file(journal):
    journal.append([])

    assert not journal.path.exists()


def test_text_that_is_not_ascii_is_kept(journal):
    journal.append([{"text": "café ✓"}])

    assert journal.read()[0] == [{"text": "café ✓"}]


def test_a_value_json_cannot_hold_is_kept_as_text(journal):
    journal.append([{"path": journal.path}])

    assert journal.read()[0] == [{"path": str(journal.path)}]


def test_a_last_line_cut_short_is_left_out_and_reported(journal):
    journal.append([{"a": 1}, {"b": 2}])
    with open(journal.path, "ab") as handle:
        handle.write(b'{"c": ')

    assert journal.read() == ([{"a": 1}, {"b": 2}], False)


def test_a_last_record_missing_its_newline_counts_as_cut_short(journal):
    journal.append([{"a": 1}])
    with open(journal.path, "ab") as handle:
        handle.write(b'{"b": 2}')

    assert journal.read() == ([{"a": 1}], False)


def test_a_bad_line_in_the_middle_is_an_error(journal):
    journal.path.parent.mkdir(parents=True)
    journal.path.write_text('{"a": 1}\nnot json\n{"b": 2}\n', encoding="utf-8")

    with pytest.raises(ValueError, match="line 2"):
        journal.read()


def test_a_line_that_is_not_an_object_is_an_error(journal):
    journal.path.parent.mkdir(parents=True)
    journal.path.write_text("[1]\n", encoding="utf-8")

    with pytest.raises(ValueError, match="line 1"):
        journal.read()


def test_rewriting_replaces_the_records(journal):
    journal.append([{"a": 1}, {"b": 2}])

    journal.rewrite([{"c": 3}])

    assert journal.read() == ([{"c": 3}], True)
    assert [path.name for path in journal.path.parent.iterdir()] == ["file.jsonl"]


def test_a_failed_rewrite_leaves_the_old_file_whole_and_no_temp_file(journal, monkeypatch):
    journal.append([{"a": 1}])
    monkeypatch.setattr("journal.file.os.replace", lambda *args: (_ for _ in ()).throw(OSError("full")))

    with pytest.raises(OSError):
        journal.rewrite([{"b": 2}])

    assert journal.read() == ([{"a": 1}], True)
    assert [path.name for path in journal.path.parent.iterdir()] == ["file.jsonl"]


def test_any_text_can_be_written_even_a_lone_surrogate(journal):
    journal.append([{"text": "bad \ud800 end"}])
    journal.rewrite([{"text": "bad \ud800 end"}])

    assert journal.read() == ([{"text": "bad \ud800 end"}], True)


def test_rewriting_creates_the_folder(journal):
    journal.rewrite([{"a": 1}])

    assert journal.read() == ([{"a": 1}], True)


def test_blank_lines_are_ignored(journal):
    journal.path.parent.mkdir(parents=True)
    journal.path.write_text('{"a": 1}\n\n{"b": 2}\n', encoding="utf-8")

    assert journal.read() == ([{"a": 1}, {"b": 2}], True)


def test_reading_a_file_that_is_not_there_is_an_error(journal):
    with pytest.raises(FileNotFoundError):
        journal.read()


def test_lines_are_ended_with_a_newline_and_nothing_else_on_any_system(journal):
    journal.rewrite([{"a": 1}])
    journal.append([{"b": 2}])

    assert journal.path.read_bytes() == b'{"a": 1}\n{"b": 2}\n'
