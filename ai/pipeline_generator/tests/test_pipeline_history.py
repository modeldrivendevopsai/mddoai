import json

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


def test_no_temporary_files_are_left_behind(history, tmp_path):
    pipeline_id = _record(history)

    names = sorted(path.name for path in (tmp_path / "data" / pipeline_id).iterdir())

    assert names == ["meta.json", "record.json"]


def test_the_record_is_written_before_the_list_row_that_points_at_it(history, tmp_path, monkeypatch):
    # If saving stops half way, a list row must never point at a missing record.
    import pipeline_history

    real_write = pipeline_history._write_json
    written = []

    def recording_write(path, value):
        written.append(path.name)
        real_write(path, value)

    monkeypatch.setattr(pipeline_history, "_write_json", recording_write)
    _record(history)

    assert written == ["record.json", "meta.json"]


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
