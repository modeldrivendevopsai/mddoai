import json
import threading

import pytest

from journal.attempts import AttemptConflict, AttemptNotFound, AttemptStore

RESULT = {"valid": True, "issues": []}


@pytest.fixture
def store(tmp_path):
    return AttemptStore(tmp_path / "runs")


def _record(store, run="run1", stage="psm", attempt=None, **overrides):
    attempt = attempt or store.reserve(run, stage)
    fields = {"filename": "psm.ecore", "content": "<ecore/>", "result": RESULT, "prompt": None, "prompt_version": None}
    store.record(run, stage, attempt, **{**fields, **overrides})
    return attempt


def test_attempts_of_a_stage_are_numbered_from_one(store):
    assert [store.reserve("run1", "psm") for _ in range(3)] == ["attempt_1", "attempt_2", "attempt_3"]


def test_each_stage_and_run_counts_its_own_attempts(store):
    assert store.reserve("run1", "psm") == "attempt_1"
    assert store.reserve("run1", "atl") == "attempt_1"
    assert store.reserve("run2", "psm") == "attempt_1"


def test_two_callers_never_get_the_same_attempt(store):
    got = []
    lock = threading.Lock()

    def reserve():
        attempt = store.reserve("run1", "psm")
        with lock:
            got.append(attempt)

    threads = [threading.Thread(target=reserve) for _ in range(16)]
    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()

    assert sorted(got) == sorted(f"attempt_{n}" for n in range(1, 17))


def test_a_recorded_attempt_comes_back_with_its_artifact_result_and_prompt(store):
    attempt = _record(store, prompt={"parts": ["a"]}, prompt_version="v3")

    assert store.read("run1", "psm", attempt) == {
        "artifact": {"filename": "psm.ecore", "content": "<ecore/>"},
        "result": RESULT,
        "prompt": {"parts": ["a"]},
        "prompt_version": "v3",
    }


def test_an_attempt_without_a_prompt_reads_back_with_none(store):
    attempt = _record(store)

    read = store.read("run1", "psm", attempt)

    assert read["prompt"] is None and read["prompt_version"] is None


def test_an_artifact_keeps_the_file_name_it_was_given(store):
    attempt = _record(store, filename="atl_mock.atl")

    assert store.read("run1", "psm", attempt)["artifact"]["filename"] == "atl_mock.atl"


def test_a_stray_hidden_file_is_not_taken_for_the_artifact(store, tmp_path):
    attempt = _record(store)
    (tmp_path / "runs" / "run1" / "psm" / attempt / ".result.json.1.2").write_text("x", encoding="utf-8")

    assert store.read("run1", "psm", attempt)["artifact"]["filename"] == "psm.ecore"


def test_every_recorded_attempt_is_added_to_the_manifest_in_order(store):
    _record(store, stage="psm", result={"valid": False, "issues": []})
    _record(store, stage="psm")
    _record(store, stage="atl", filename="a.atl")

    manifest = store.manifest("run1")

    assert [(e["stage"], e["attempt_n"], e["valid"]) for e in manifest] == [("psm", 1, False), ("psm", 2, True), ("atl", 1, True)]
    assert all(e["run_id"] == "run1" and e["timestamp"] for e in manifest)


def test_the_manifest_loses_no_entry_when_attempts_are_recorded_at_once(store):
    attempts = [store.reserve("run1", "psm") for _ in range(12)]
    threads = [threading.Thread(target=_record, args=(store,), kwargs={"attempt": attempt}) for attempt in attempts]
    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()

    assert sorted(e["attempt_n"] for e in store.manifest("run1")) == list(range(1, 13))


def test_result_json_is_written_last_so_a_reserved_attempt_is_told_from_a_recorded_one(store, tmp_path, monkeypatch):
    attempt = store.reserve("run1", "psm")
    folder = tmp_path / "runs" / "run1" / "psm" / attempt
    seen = []
    real = AttemptStore._write_json

    def spying(path, data):
        seen.append(sorted(p.name for p in folder.iterdir()))
        real(path, data)

    monkeypatch.setattr(AttemptStore, "_write_json", staticmethod(spying))

    _record(store, attempt=attempt, prompt={"p": 1})

    assert "psm.ecore" in seen[0] and "prompt.json" in seen[0] and "result.json" not in seen[0]


def test_a_reserved_attempt_that_was_never_recorded_is_discarded_and_its_number_is_reused(store, tmp_path):
    attempt = store.reserve("run1", "psm")

    store.discard("run1", "psm", attempt)

    assert not (tmp_path / "runs" / "run1" / "psm" / attempt).exists()
    assert store.reserve("run1", "psm") == "attempt_1"


def test_a_recorded_attempt_cannot_be_discarded(store, tmp_path):
    attempt = _record(store)

    with pytest.raises(AttemptConflict):
        store.discard("run1", "psm", attempt)

    assert (tmp_path / "runs" / "run1" / "psm" / attempt / "result.json").is_file()


def test_an_attempt_cannot_be_recorded_twice(store):
    attempt = _record(store)

    with pytest.raises(AttemptConflict):
        _record(store, attempt=attempt, content="other")

    assert store.read("run1", "psm", attempt)["artifact"]["content"] == "<ecore/>"


def test_an_attempt_that_was_not_reserved_cannot_be_recorded(store):
    with pytest.raises(AttemptNotFound):
        _record(store, attempt="attempt_4")


@pytest.mark.parametrize("filename", ["result.json", "prompt.json", "", ".", "..", "a/b", "a\\b", "c:x", "x\x00y", "n" * 256])
def test_a_file_name_that_could_clash_or_leave_the_folder_is_refused(store, filename):
    attempt = store.reserve("run1", "psm")

    with pytest.raises(ValueError):
        _record(store, attempt=attempt, filename=filename)


@pytest.mark.parametrize("result", [{}, {"valid": "yes"}, {"valid": None}])
def test_a_result_without_a_true_or_false_valid_is_refused(store, result):
    attempt = store.reserve("run1", "psm")

    with pytest.raises(ValueError):
        _record(store, attempt=attempt, result=result)


@pytest.mark.parametrize("run_id,stage,attempt", [("../x", "psm", "attempt_1"), ("run1", "../x", "attempt_1"), ("run1", "psm", "../x"), ("run1", "psm", "attempt_0"), ("run1", "psm", "attempt_01"), ("", "psm", "attempt_1")])
def test_a_name_that_could_leave_the_runs_folder_finds_nothing(store, run_id, stage, attempt):
    with pytest.raises(AttemptNotFound):
        store.read(run_id, stage, attempt)
    with pytest.raises(AttemptNotFound):
        store.discard(run_id, stage, attempt)


def test_reserving_with_a_name_that_could_leave_the_runs_folder_is_refused_and_creates_nothing(store, tmp_path):
    with pytest.raises(AttemptNotFound):
        store.reserve("../x", "psm")
    with pytest.raises(AttemptNotFound):
        store.reserve("run1", "a/b")

    assert not (tmp_path / "runs").exists() or not any((tmp_path / "runs").iterdir())


def test_reading_an_attempt_that_is_only_reserved_finds_nothing(store):
    attempt = store.reserve("run1", "psm")

    with pytest.raises(AttemptNotFound):
        store.read("run1", "psm", attempt)


def test_a_run_with_no_attempts_has_no_manifest(store):
    with pytest.raises(AttemptNotFound):
        store.manifest("run1")


def test_the_manifest_is_valid_json_on_disk(store, tmp_path):
    _record(store)

    assert json.loads((tmp_path / "runs" / "run1" / "manifest.json").read_text(encoding="utf-8"))[0]["stage"] == "psm"


# --- extra files ---------------------------------------------------------------------------


def test_extra_files_are_kept_beside_the_artifact_even_in_subfolders(store, tmp_path):
    attempt = _record(store, extra_files={"psm_instance.xmi": "<xmi/>", ".woodpecker/pipeline.yaml": "steps: []"})

    folder = tmp_path / "runs" / "run1" / "psm" / attempt
    assert (folder / "psm_instance.xmi").read_text(encoding="utf-8") == "<xmi/>"
    assert (folder / ".woodpecker" / "pipeline.yaml").read_text(encoding="utf-8") == "steps: []"
    assert store.read("run1", "psm", attempt)["artifact"]["filename"] == "psm.ecore"


@pytest.mark.parametrize(
    "name",
    [
        "",
        "../x",
        "a/../x",
        "/abs",
        "a//b",
        "./x",
        "a/./b",
        "a\\b",
        "c:x",
        "x\x00y",
        "result.json",
        "prompt.json",
        "psm.ecore",
        "result.json/x",
        "n/" + "n" * 256,
    ],
)
def test_an_extra_file_that_could_leave_the_attempt_or_clash_with_its_files_is_refused_and_nothing_is_kept(store, tmp_path, name):
    attempt = store.reserve("run1", "psm")

    with pytest.raises(ValueError):
        _record(store, attempt=attempt, extra_files={"fine.txt": "ok", name: "x"})

    folder = tmp_path / "runs" / "run1" / "psm" / attempt
    assert sorted(path.name for path in folder.iterdir()) == []
    assert not (tmp_path / "x").exists()


def test_an_extra_file_cannot_be_written_through_a_link_that_leaves_the_attempt(store, tmp_path):
    attempt = store.reserve("run1", "psm")
    folder = tmp_path / "runs" / "run1" / "psm" / attempt
    outside = tmp_path / "outside"
    outside.mkdir()
    try:
        (folder / "link").symlink_to(outside, target_is_directory=True)
    except OSError:
        pytest.skip("this system does not let the test make a link")

    with pytest.raises(ValueError):
        _record(store, attempt=attempt, extra_files={"link/escaped.txt": "x"})

    assert list(outside.iterdir()) == []


def test_extra_files_that_cannot_share_a_folder_are_refused(store):
    attempt = store.reserve("run1", "psm")

    with pytest.raises(ValueError, match="together"):
        _record(store, attempt=attempt, extra_files={"a": "file", "a/b": "inside"})
