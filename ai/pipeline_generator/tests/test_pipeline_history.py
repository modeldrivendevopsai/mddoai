import json
import os
from pathlib import Path

import pytest

from pipeline_history import PipelineHistory

GITLAB = {"id": "gitlab", "name": "GitLab CI"}
BAMBOO = {"id": "bamboo", "name": "Bamboo"}


def _result(platform=GITLAB, filename=".gitlab-ci.yml", pipeline="stages: [build]\n"):
    return {
        "platform": platform,
        "output_filename": filename,
        "pipeline": pipeline,
        "intermediates": [],
        "duration_ms": 10,
        "saved_to": None,
        "save_error": None,
        "error": None,
    }


def _record(history, *, key="/repos/a.swarch", results=None, at="2026-01-01T10:00:00+00:00", content="<a/>"):
    return history.record(
        key=key,
        swarch_name="a.swarch",
        source_path="/repos/a.swarch",
        content=content,
        save_to="./ci-output/",
        results=results or [_result()],
        generated_at=at,
    )


def test_a_recorded_pipeline_can_be_listed_and_reopened_in_full(history):
    pipeline_id = _record(history)

    [summary] = history.summaries()
    assert summary["id"] == pipeline_id
    assert summary["swarch_name"] == "a.swarch"
    assert summary["platforms"] == [GITLAB]
    assert summary["outputs"] == [".gitlab-ci.yml"]
    assert summary["generated_at"] == "2026-01-01T10:00:00+00:00"
    record = history.get(pipeline_id)
    assert record["content"] == "<a/>"
    assert record["save_to"] == "./ci-output/"
    assert record["results"][0]["pipeline"] == "stages: [build]\n"


def test_the_list_carries_only_the_small_summary_never_the_bulky_parts(history):
    _record(history)

    [summary] = history.summaries()

    assert "content" not in summary
    assert "results" not in summary


def test_generating_again_for_the_same_input_replaces_the_pipeline_instead_of_adding_a_row(history):
    first = _record(history, content="<old/>", at="2026-01-01T10:00:00+00:00")
    second = _record(history, content="<new/>", results=[_result(BAMBOO, "bamboo-spec.yml")], at="2026-01-02T10:00:00+00:00")

    assert second == first
    [summary] = history.summaries()
    assert summary["platforms"] == [BAMBOO]
    assert summary["generated_at"] == "2026-01-02T10:00:00+00:00"
    assert history.get(first)["content"] == "<new/>"


def test_different_inputs_are_different_pipelines_newest_first(history):
    older = _record(history, key="/a.swarch", at="2026-01-01T10:00:00+00:00")
    newer = _record(history, key="/b.swarch", at="2026-01-03T10:00:00+00:00")

    assert [summary["id"] for summary in history.summaries()] == [newer, older]


def test_an_empty_history_lists_nothing(history):
    assert history.summaries() == []


def test_the_history_survives_a_new_instance_over_the_same_folder(history, tmp_path):
    pipeline_id = _record(history)

    reopened = PipelineHistory(tmp_path / "data")

    assert [summary["id"] for summary in reopened.summaries()] == [pipeline_id]
    assert reopened.get(pipeline_id)["content"] == "<a/>"


@pytest.mark.parametrize("bad_id", ["", "../data", "..", "../../etc/passwd", "a" * 32 + "/x", "G" * 32, "abc", "0" * 31, "0" * 33])
def test_an_id_that_is_not_one_this_class_generated_is_never_used_as_a_path(history, bad_id):
    _record(history)

    assert history.get(bad_id) is None


def test_an_unknown_but_well_formed_id_is_simply_not_found(history):
    assert history.get("0" * 32) is None


def test_a_damaged_entry_is_skipped_not_fatal(history, tmp_path):
    good = _record(history, key="/good")
    broken = tmp_path / "data" / ("1" * 32)
    broken.mkdir()
    (broken / "meta.json").write_text("{not json", encoding="utf-8")

    assert [summary["id"] for summary in history.summaries()] == [good]
    assert history.get("1" * 32) is None


def test_no_temporary_files_or_folders_are_left_behind(history, tmp_path):
    pipeline_id = _record(history)
    _record(history)  # a second generation for the same input replaces the folder

    assert [path.name for path in (tmp_path / "data").iterdir()] == [pipeline_id]
    names = sorted(str(path.relative_to(tmp_path / "data" / pipeline_id)) for path in (tmp_path / "data" / pipeline_id).rglob("*"))
    assert names == sorted(["meta.json", "record.json", "model.swarch", "gitlab", str(Path("gitlab") / ".gitlab-ci.yml")])


# --- the generation as real files ----------------------------------------------


PIM = {"id": "pim", "title": "Platform-independent model", "filename": "PipelinePIM.pimmm", "content": "<pim/>\r\n"}


def test_the_model_each_pipeline_and_each_intermediate_are_kept_as_files(history, tmp_path):
    pipeline_id = _record(history, results=[{**_result(), "intermediates": [PIM]}])
    folder = tmp_path / "data" / pipeline_id

    assert (folder / "model.swarch").read_text(encoding="utf-8") == "<a/>"
    assert (folder / "gitlab" / ".gitlab-ci.yml").read_text(encoding="utf-8") == "stages: [build]\n"
    with open(folder / "gitlab" / "intermediates" / "PipelinePIM.pimmm", encoding="utf-8", newline="") as handle:
        assert handle.read() == "<pim/>\r\n"


def test_the_record_file_keeps_no_file_contents(history, tmp_path):
    pipeline_id = _record(history, results=[{**_result(), "intermediates": [PIM]}])

    record = json.loads((tmp_path / "data" / pipeline_id / "record.json").read_text(encoding="utf-8"))

    assert "content" not in record
    assert "pipeline" not in record["results"][0]
    assert record["results"][0]["intermediates"] == [{"id": "pim", "title": "Platform-independent model", "filename": "PipelinePIM.pimmm"}]


def test_reopening_returns_the_files_exactly_as_generated(history):
    result = {**_result(pipeline="a: 1\r\nb: 2\n"), "intermediates": [PIM]}
    pipeline_id = _record(history, results=[result])

    [reopened] = history.get(pipeline_id)["results"]

    assert reopened["pipeline"] == "a: 1\r\nb: 2\n"
    assert reopened["intermediates"] == [PIM]


def test_generating_again_removes_files_the_new_generation_did_not_produce(history, tmp_path):
    pipeline_id = _record(history, results=[_result()])
    _record(history, results=[_result(BAMBOO, "bamboo-spec.yml")])

    folder = tmp_path / "data" / pipeline_id
    assert not (folder / "gitlab").exists()
    assert (folder / "bamboo" / "bamboo-spec.yml").is_file()


@pytest.mark.parametrize("missing", [
    Path("gitlab") / ".gitlab-ci.yml",
    Path("gitlab") / "intermediates" / "PipelinePIM.pimmm",
    Path("model.swarch"),
])
def test_a_missing_file_makes_the_pipeline_unavailable_not_a_crash(history, tmp_path, missing):
    pipeline_id = _record(history, results=[{**_result(), "intermediates": [PIM]}])
    (tmp_path / "data" / pipeline_id / missing).unlink()

    assert history.get(pipeline_id) is None


def test_reopening_returns_exactly_what_was_recorded(history):
    results = [{**_result(), "intermediates": [PIM]}, _result(BAMBOO, "bamboo-spec.yml", "plan: x\n")]
    pipeline_id = _record(history, results=results, content="<model/>\n")

    record = history.get(pipeline_id)

    assert record["results"] == results
    assert record["content"] == "<model/>\n"
    assert record["save_to"] == "./ci-output/"


def test_a_result_without_a_pipeline_is_refused_and_record_generation_does_not_raise(history):
    without = {**_result(), "pipeline": None}

    with pytest.raises(ValueError):
        _record(history, results=[without])
    assert history.record_generation(
        filename="a.swarch", content="<a/>", source_path=None, save_to=None, generated=[without]
    ) is None
    assert history.summaries() == []


@pytest.mark.parametrize("field, value", [("platform", {"id": "../x", "name": "X"}), ("output_filename", "../../escape.yml")])
def test_a_name_that_would_leave_the_pipeline_folder_is_refused(history, tmp_path, field, value):
    with pytest.raises(ValueError):
        _record(history, results=[{**_result(), field: value}])

    assert not (tmp_path / "escape.yml").exists()
    assert history.summaries() == []


def test_a_tampered_record_naming_a_path_outside_its_folder_is_not_read(history, tmp_path):
    pipeline_id = _record(history)
    record_path = tmp_path / "data" / pipeline_id / "record.json"
    record = json.loads(record_path.read_text(encoding="utf-8"))
    record["results"][0]["output_filename"] = "../../../secret.txt"
    record_path.write_text(json.dumps(record), encoding="utf-8")

    assert history.get(pipeline_id) is None


def test_if_the_new_generation_cannot_be_put_in_place_the_earlier_one_is_restored(history, monkeypatch):
    import pipeline_history

    pipeline_id = _record(history, content="<old/>")
    real_rename = os.rename

    def failing_for_the_new_folder(source, target):
        if Path(source).name.startswith(".writing-"):
            raise OSError("rename failed")
        real_rename(source, target)

    monkeypatch.setattr(pipeline_history.os, "rename", failing_for_the_new_folder)
    with pytest.raises(OSError):
        _record(history, content="<new/>")
    monkeypatch.setattr(pipeline_history.os, "rename", real_rename)

    assert history.get(pipeline_id)["content"] == "<old/>"
    assert [summary["id"] for summary in history.summaries()] == [pipeline_id]


def test_a_generation_moved_aside_by_a_crash_is_put_back_not_deleted(history, tmp_path):
    # A crash between moving the earlier generation aside and putting the new
    # one in place leaves the earlier one as the only complete copy.
    pipeline_id = _record(history, key="/a", content="<only copy/>")
    folder = tmp_path / "data" / pipeline_id
    os.rename(folder, tmp_path / "data" / f".replaced-{pipeline_id}-crashed")

    _record(history, key="/another")

    assert history.get(pipeline_id)["content"] == "<only copy/>"
    assert not list((tmp_path / "data").glob(".replaced-*"))


def test_a_generation_moved_aside_whose_replacement_is_in_place_is_cleaned_up(history, tmp_path):
    pipeline_id = _record(history, key="/a")
    leftover = tmp_path / "data" / f".replaced-{pipeline_id}-crashed"
    leftover.mkdir()

    _record(history, key="/another")

    assert not leftover.exists()
    assert history.get(pipeline_id) is not None


def test_a_pipeline_being_forgotten_is_moved_aside_before_it_is_deleted(tmp_path, monkeypatch):
    import pipeline_history

    capped = PipelineHistory(tmp_path / "capped", max_entries=1)
    oldest = _record(capped, key="/1", at="2026-01-01T10:00:00+00:00")
    removed_in_place = []
    real_rmtree = pipeline_history.shutil.rmtree

    def watching_rmtree(path, *args, **kwargs):
        if Path(path).name == oldest:
            removed_in_place.append(path)
        real_rmtree(path, *args, **kwargs)

    monkeypatch.setattr(pipeline_history.shutil, "rmtree", watching_rmtree)
    _record(capped, key="/2", at="2026-01-02T10:00:00+00:00")

    assert removed_in_place == []
    assert not (tmp_path / "capped" / oldest).exists()
    assert not list((tmp_path / "capped").glob(".removing-*"))


@pytest.mark.skipif(os.name != "posix", reason="file modes are POSIX permissions")
def test_the_files_and_folder_are_readable_by_others(history, tmp_path):
    # The history lives in the data folder so it can be opened from the host
    # by a user other than the one the service runs as. A private (0700/0600)
    # mode would hide it; with the usual umask everything is world-readable.
    previous = os.umask(0o022)
    try:
        pipeline_id = _record(history, results=[{**_result(), "intermediates": [PIM]}])
    finally:
        os.umask(previous)
    folder = tmp_path / "data" / pipeline_id

    for path in [folder, *folder.rglob("*")]:
        assert path.stat().st_mode & 0o004, f"{path.name} is not readable by others"


def test_a_folder_left_by_an_interrupted_write_is_never_listed_and_is_cleaned_up(history, tmp_path):
    leftover = tmp_path / "data" / ".writing-abc"
    leftover.mkdir(parents=True)
    (leftover / "meta.json").write_text(_meta(".writing-abc"), encoding="utf-8")

    assert history.summaries() == []
    _record(history)
    assert not leftover.exists()


def test_a_generation_that_fails_part_way_leaves_the_earlier_one_untouched(history, monkeypatch):
    # If saving stops half way, the list must never show a pipeline whose
    # files are not all there, and the earlier generation must survive.
    import pipeline_history

    pipeline_id = _record(history, content="<old/>")

    def failing_write(path, value):
        raise OSError("disk full")

    monkeypatch.setattr(pipeline_history, "_write_json", failing_write)
    with pytest.raises(OSError):
        _record(history, content="<new/>")

    assert history.get(pipeline_id)["content"] == "<old/>"
    assert [summary["id"] for summary in history.summaries()] == [pipeline_id]


def test_the_stored_files_are_plain_json(history, tmp_path):
    pipeline_id = _record(history)

    meta = json.loads((tmp_path / "data" / pipeline_id / "meta.json").read_text(encoding="utf-8"))

    assert meta["id"] == pipeline_id


# --- retention and damaged entries -------------------------------------------


def _capped(tmp_path, limit):
    return PipelineHistory(tmp_path / "capped", max_entries=limit)


def test_the_oldest_pipelines_are_forgotten_beyond_the_limit(tmp_path):
    capped = _capped(tmp_path, 2)
    oldest = _record(capped, key="/1", at="2026-01-01T10:00:00+00:00")
    middle = _record(capped, key="/2", at="2026-01-02T10:00:00+00:00")
    newest = _record(capped, key="/3", at="2026-01-03T10:00:00+00:00")

    assert [s["id"] for s in capped.summaries()] == [newest, middle]
    assert capped.get(oldest) is None
    assert not (tmp_path / "capped" / oldest).exists()


def test_the_one_just_recorded_is_never_the_one_forgotten(tmp_path):
    # Even when its timestamp is not newer than the others (a clock set back),
    # the pipeline just generated must still be there to reopen.
    capped = _capped(tmp_path, 1)
    _record(capped, key="/1", at="2026-06-01T10:00:00+00:00")

    latest = _record(capped, key="/2", at="2026-01-01T10:00:00+00:00")

    assert [s["id"] for s in capped.summaries()] == [latest]


def test_generating_again_for_a_kept_input_does_not_count_twice(tmp_path):
    capped = _capped(tmp_path, 2)
    first = _record(capped, key="/1", at="2026-01-01T10:00:00+00:00")
    _record(capped, key="/2", at="2026-01-02T10:00:00+00:00")
    _record(capped, key="/2", at="2026-01-03T10:00:00+00:00")

    # Still both inputs: the repeat replaced a row, it did not add one and
    # push the oldest out.
    assert len(capped.summaries()) == 2
    assert capped.get(first) is not None


def test_without_a_limit_nothing_is_forgotten(history):
    for number in range(5):
        _record(history, key=f"/{number}", at=f"2026-01-0{number + 1}T10:00:00+00:00")

    assert len(history.summaries()) == 5


@pytest.mark.parametrize("meta", ['{"id": "' + "1" * 32 + '"}', '[]', '"text"', '{"id": 5, "key": "k", "generated_at": "x"}'])
def test_an_entry_that_parses_but_lacks_what_the_list_needs_is_skipped_not_fatal(history, tmp_path, meta):
    good = _record(history, key="/good")
    broken = tmp_path / "data" / ("1" * 32)
    broken.mkdir()
    (broken / "meta.json").write_text(meta, encoding="utf-8")

    assert [summary["id"] for summary in history.summaries()] == [good]
    # And recording keeps working alongside it.
    assert _record(history, key="/another") != good


# --- tampered or foreign entries ----------------------------------------------


def _meta(folder_name, **overrides):
    meta = {
        "id": folder_name, "key": "path:/x", "swarch_name": "x.swarch", "source_path": "/x",
        "platforms": [], "outputs": [], "generated_at": "2026-01-01T10:00:00+00:00",
    }
    return json.dumps({**meta, **overrides})


@pytest.mark.parametrize("overrides", [
    {"platforms": None}, {"outputs": "not a list"}, {"swarch_name": 7}, {"source_path": 7},
    {"id": "2" * 32}, {"generated_at": None},
])
def test_an_entry_missing_what_the_screen_shows_or_naming_another_folder_is_skipped(history, tmp_path, overrides):
    good = _record(history, key="/good")
    folder = tmp_path / "data" / ("1" * 32)
    folder.mkdir()
    (folder / "meta.json").write_text(_meta("1" * 32, **overrides), encoding="utf-8")

    assert [summary["id"] for summary in history.summaries()] == [good]


def test_a_folder_not_named_like_an_id_is_never_listed(history, tmp_path):
    folder = tmp_path / "data" / "not-an-id"
    folder.mkdir(parents=True)
    (folder / "meta.json").write_text(_meta("not-an-id"), encoding="utf-8")

    assert history.summaries() == []


def test_a_record_that_is_not_an_object_is_not_returned(history, tmp_path):
    folder = tmp_path / "data" / ("3" * 32)
    folder.mkdir(parents=True)
    (folder / "record.json").write_text("[1, 2]", encoding="utf-8")

    assert history.get("3" * 32) is None


def test_record_generation_names_the_input_by_path_else_by_file_name(history):
    by_path = history.record_generation(
        filename="a.swarch", content="<a/>", source_path="/repos/a.swarch", save_to=None, generated=[_result()]
    )
    by_upload = history.record_generation(
        filename="/repos/a.swarch", content="<a/>", source_path=None, save_to=None, generated=[_result()]
    )

    assert by_path != by_upload
    assert len(history.summaries()) == 2


def test_record_generation_has_nothing_to_record_when_nothing_was_generated(history):
    assert history.record_generation(filename="a.swarch", content="<a/>", source_path=None, save_to=None, generated=[]) is None
    assert history.summaries() == []


def test_record_generation_reports_a_failed_write_as_no_id_instead_of_raising(history, monkeypatch):
    def failing(*args, **kwargs):
        raise OSError("disk full")

    monkeypatch.setattr(history, "record", failing)

    assert history.record_generation(filename="a.swarch", content="<a/>", source_path=None, save_to=None, generated=[_result()]) is None
