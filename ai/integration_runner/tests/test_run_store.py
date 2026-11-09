"""Runs are kept on disk and come back after a restart. A restart is simulated
by forgetting the in-memory registry and loading from the runs folder."""
import itertools
import logging
import threading
import time
import types
from unittest.mock import patch

import pytest
from fastapi.testclient import TestClient

import main
from integration_runner import pipeline, publishing, run_store, runs
from integration_runner.stages import _validation
from journal import Journal

CONTEXT = {"psm_output": "<ecore/>", "atl_output": "create OUT : W from IN : PIM;", "acceleo_output": "[m]"}


@pytest.fixture(autouse=True)
def _registry(monkeypatch):
    original_default, original_runs = runs._default, dict(runs._runs)
    ticks = itertools.count(1000)
    monkeypatch.setattr("integration_runner.event_log.time", types.SimpleNamespace(time=lambda: float(next(ticks))))
    yield
    runs._default = original_default
    runs._runs.clear()
    runs._runs.update(original_runs)
    run_store._on_disk.clear()
    run_store._failing.clear()


def _restart():
    """What a restart leaves in memory: one blank run and nothing else."""
    runs._runs.clear()
    run_store._on_disk.clear()
    run_store._failing.clear()
    runs._default = runs._new_run()
    runs._runs[runs._default.run_id] = runs._default


def _journal(run_id):
    return Journal(_validation.RUNS_DIR / run_id / "run.jsonl")


def _saved(run_id):
    """What the journal holds: its events, and its state records."""
    records, _ = _journal(run_id).read()
    return [r["event"] for r in records if "event" in r], [r["state"] for r in records if "state" in r]


def _completed_run():
    run = runs._new_run()
    run.record_event("call_started", "docs", {"platform_description": "Woodpecker CI"})
    run.record_event("call_started", "generation", dict(CONTEXT))
    run.record_event("call_completed", "generation", {"output": "x", "generated_files": {"ci.yml": "x"}})
    run.current_stage_index = len(pipeline.STAGES)
    run.last_context = dict(CONTEXT)
    run.record_event("review_approved", "generation", {"correction": None})
    return run


# --- keeping a run -------------------------------------------------------------


def test_a_run_is_written_to_its_own_journal_after_an_event():
    run = runs._new_run()

    run.record_event("call_started", "docs", {"seed_url": "https://x"})

    events, states = _saved(run.run_id)
    assert [event["type"] for event in events] == ["call_started"]
    assert states[0]["current_stage_index"] == 0


def test_a_run_nothing_has_happened_on_is_not_written():
    run = runs._new_run()
    run.set_model("some-model")
    run.release_busy()

    assert not (_validation.RUNS_DIR / run.run_id).exists()


def test_an_event_adds_only_that_event_to_the_journal():
    run = runs._new_run()
    run.last_context = {"docs_output": "x" * 100_000}
    run.record_event("call_started", "docs", {})
    before = _journal(run.run_id).path.stat().st_size

    for _ in range(5):
        run.record_event("constraint_added", "docs", {"constraint": "c"})

    grew = _journal(run.run_id).path.stat().st_size - before
    assert grew < 2_000


def test_only_what_changed_in_the_state_is_written():
    run = runs._new_run()
    run.record_event("call_started", "docs", {})
    run.record_event("call_completed", "docs", {"output": "x"})
    run.set_model("some-model")

    _, states = _saved(run.run_id)

    assert len(states) == 2
    assert states[1] == {"model": "some-model"}


def test_the_model_is_kept_even_when_it_is_the_last_change():
    run = runs._new_run()
    run.record_event("call_started", "docs", {})
    run.set_model("some-model")
    _restart()

    runs.load_saved()

    assert runs.get_run(run.run_id).model == "some-model"


def test_an_approval_that_pauses_the_next_stage_is_kept_though_it_records_no_event_after_it():
    run = runs._new_run()
    run.record_event("call_started", "pim", {})
    run.current_stage_index = pipeline.STAGES.index("pim")
    run.last_output = "pim out"
    run.last_completed_stage = "pim"

    assert run.review("pim", True) == {"status": "advanced_pending", "stage": "psm"}
    _restart()
    runs.load_saved()

    restored = runs.get_run(run.run_id)
    assert restored.current_stage == "psm"
    assert restored.last_context["pim_output"] == "pim out"


def test_a_run_comes_back_exactly_as_it_was():
    run = runs._new_run()
    run.set_model("some-model")
    run.add_constraint("docs", "use kebab-case")
    run.last_context = {"seed_url": "https://x"}
    run.last_output = "docs text"
    run.last_completed_stage = "docs"
    run.current_stage_index = 2
    run.record_event("call_completed", "docs", {"output": "docs text"})

    _restart()
    runs.load_saved()

    restored = runs.get_run(run.run_id)
    assert restored.model == "some-model"
    assert restored.constraints == {"docs": ["use kebab-case"]}
    assert restored.last_context == {"seed_url": "https://x"}
    assert restored.last_output == "docs text"
    assert restored.last_completed_stage == "docs"
    assert restored.current_stage_index == 2
    assert [event["type"] for event in restored.events] == ["constraint_added", "call_completed"]
    assert restored.busy is False


def test_a_restored_run_goes_on_adding_to_the_same_journal():
    run = runs._new_run()
    run.record_event("call_started", "docs", {})
    run.record_event("call_completed", "docs", {"output": "x"})
    _restart()
    runs.load_saved()
    restored = runs.get_run(run.run_id)
    size = _journal(run.run_id).path.stat().st_size

    restored.record_event("constraint_added", "docs", {"constraint": "c"})

    events, states = _saved(run.run_id)
    assert [event["type"] for event in events] == ["call_started", "call_completed", "constraint_added"]
    assert len(states) == 1
    assert _journal(run.run_id).path.stat().st_size > size


def test_a_failed_first_save_leaves_no_file(monkeypatch):
    monkeypatch.setattr("journal.os.replace", lambda *args: (_ for _ in ()).throw(OSError("full")))
    run = runs._new_run()

    run.record_event("call_started", "docs", {})

    assert list((_validation.RUNS_DIR / run.run_id).iterdir()) == []


def test_after_an_add_that_failed_part_way_the_next_save_writes_the_run_whole_so_no_event_is_kept_twice(monkeypatch):
    run = runs._new_run()
    run.record_event("call_started", "docs", {})
    real_append = Journal.append

    def writes_then_fails(self, records):
        real_append(self, records)
        raise OSError("disk went away")

    with monkeypatch.context() as patched:
        patched.setattr(Journal, "append", writes_then_fails)
        run.record_event("call_completed", "docs", {"output": "x"})

    run.record_event("constraint_added", "docs", {"constraint": "c"})

    events, _ = _saved(run.run_id)
    assert [event["type"] for event in events] == ["call_started", "call_completed", "constraint_added"]


def test_a_run_goes_on_when_the_disk_fails(tmp_path, monkeypatch, caplog):
    blocker = tmp_path / "blocker"
    blocker.write_text("a file, not a folder", encoding="utf-8")
    monkeypatch.setattr(_validation, "RUNS_DIR", blocker / "runs")
    run = runs._new_run()

    with caplog.at_level(logging.ERROR):
        run.record_event("call_started", "docs", {})

    assert len(run.events) == 1
    assert "could not keep run" in caplog.text


def test_no_temp_file_is_left_behind():
    run = runs._new_run()
    run.record_event("call_started", "docs", {})
    run.record_event("call_completed", "docs", {"output": "x"})

    assert [path.name for path in (_validation.RUNS_DIR / run.run_id).iterdir()] == ["run.jsonl"]


def test_threads_changing_one_run_never_lose_an_event_or_a_constraint():
    run = runs._new_run()
    run.record_event("call_started", "docs", {})
    run.record_event("call_completed", "docs", {"output": "x"})

    def work():
        for _ in range(20):
            run.add_constraint("docs", "c")

    threads = [threading.Thread(target=work) for _ in range(6)]
    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()
    _restart()
    runs.load_saved()

    restored = runs.get_run(run.run_id)
    assert len(restored.events) == 122
    assert len(restored.constraints["docs"]) == 120


def test_one_thread_at_a_time_keeps_a_run():
    inside = most = 0
    guard = threading.Lock()

    def slow_save(run):
        nonlocal inside, most
        with guard:
            inside += 1
            most = max(most, inside)
        time.sleep(0.01)
        with guard:
            inside -= 1

    run = pipeline.IntegrationRun(on_change=slow_save)
    threads = [threading.Thread(target=run.record_event, args=("x", None)) for _ in range(8)]
    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()

    assert most == 1


# --- bringing runs back --------------------------------------------------------


def test_the_run_with_the_latest_activity_becomes_current_and_the_blank_one_is_dropped():
    first = runs._new_run()
    first.record_event("call_started", "docs", {})
    second = runs._new_run()
    second.record_event("call_started", "docs", {})
    second.record_event("call_completed", "docs", {"output": "x"})
    first.record_event("call_completed", "docs", {"output": "x"})
    _restart()
    blank_id = runs.current_run_id()

    runs.load_saved()

    assert runs.current_run_id() == first.run_id
    assert [r["run_id"] for r in runs.list_runs()] == [second.run_id, first.run_id]
    assert runs.get_run(blank_id) is None


def test_a_service_with_no_saved_runs_keeps_its_blank_run():
    _restart()
    blank_id = runs.current_run_id()

    runs.load_saved()

    assert runs.current_run_id() == blank_id


def test_a_run_already_in_memory_is_left_alone():
    run = runs._new_run()
    run.record_event("call_started", "docs", {})
    run.record_event("call_completed", "docs", {"output": "x"})
    runs._runs[run.run_id] = run
    runs._default = run

    runs.load_saved()

    assert runs.get_run(run.run_id) is run
    assert runs._default is run


_EVENT = '{"event": {"type": "call_started", "stage": null, "timestamp": 1}}\n'


@pytest.mark.parametrize("content", [
    "{not json\n",
    "[]\n",
    '{"mystery": 1}\n',
    _EVENT,
    '{"state": {"current_stage_index": "0"}}\n' + _EVENT,
    '{"state": {"current_stage_index": 99}}\n' + _EVENT,
    '{"state": {"current_stage_index": 0}}\n{"event": 5}\n',
    '{"state": {"current_stage_index": 0}}\n{"event": {"type": "call_started"}}\n',
    '{"state": {"current_stage_index": 0, "constraints": {"docs": "a"}}}\n' + _EVENT,
    '{"state": 5}\n' + _EVENT,
    '{"state": null}\n' + _EVENT,
    '{"state": {"current_stage_index": 0, "run_id": "../evil"}}\n' + _EVENT,
    '{"state": {"current_stage_index": 0, "events": []}}\n' + _EVENT,
])
def test_a_journal_that_is_not_a_saved_run_is_skipped_and_the_other_runs_still_come_back(content, caplog):
    good = runs._new_run()
    good.record_event("call_started", "docs", {})
    broken = _validation.RUNS_DIR / "x"
    broken.mkdir()
    (broken / "run.jsonl").write_text(content, encoding="utf-8")
    (_validation.RUNS_DIR / "only-attempts").mkdir()
    _restart()

    with caplog.at_level(logging.WARNING):
        runs.load_saved()

    assert [r["run_id"] for r in runs.list_runs()] == [good.run_id]
    assert "skipping" in caplog.text


def test_a_journal_cut_short_by_a_crash_loses_only_its_last_line_and_is_written_whole_on_the_next_save():
    run = runs._new_run()
    run.record_event("call_started", "docs", {})
    run.record_event("call_completed", "docs", {"output": "x"})
    with open(_journal(run.run_id).path, "ab") as handle:
        handle.write(b'{"event": {"type": "cal')
    _restart()

    runs.load_saved()
    restored = runs.get_run(run.run_id)
    restored.record_event("constraint_added", "docs", {"constraint": "c"})

    events, clean = _journal(run.run_id).read()[0], _journal(run.run_id).read()[1]
    assert clean is True
    assert [record["event"]["type"] for record in events if "event" in record] == [
        "call_started", "call_completed", "constraint_added",
    ]


# --- a stage that was running when the service stopped -------------------------


def _interrupted_second_stage():
    """A run whose docs stage finished and whose serialization stage was
    running when the service stopped, as the stage worker leaves it."""
    run = runs._new_run()
    run.record_event("call_started", "docs", {"seed_url": "https://x"})
    run.record_event("call_completed", "docs", {"output": "docs text"})
    run.last_completed_stage = "docs"
    run.last_output = "docs text"
    run.record_event("review_approved", "docs", {"correction": None})
    run.advance_stage()
    context = {"seed_url": "https://x", "docs_output": "docs text"}
    run.last_context = context
    run.record_event("call_started", "serialization", context)
    return run


def test_the_context_a_stage_runs_with_is_on_disk_while_it_runs():
    run = runs._new_run()
    entered, release = threading.Event(), threading.Event()

    def agent(context):
        entered.set()
        release.wait(5)
        return "out"

    with patch.dict("integration_runner.stages.stage_agents", {"docs": agent}):
        run.run_stage_async({"seed_url": "https://x"})
        assert entered.wait(5)
        _, states = _saved(run.run_id)
        release.set()
        run._last_thread.join(timeout=5)

    assert states[0]["last_context"] == {"seed_url": "https://x"}


def test_a_stage_that_was_running_at_shutdown_comes_back_failed():
    run = _interrupted_second_stage()
    _restart()

    runs.load_saved()

    restored = runs.get_run(run.run_id)
    assert [(event["type"], event["stage"]) for event in restored.events][-2:] == [
        ("call_started", "serialization"),
        ("call_failed", "serialization"),
    ]
    assert "restarted" in restored.events[-1]["data"]["error"]
    assert restored.last_completed_stage is None
    assert restored.busy is False
    assert _saved(run.run_id)[0][-1]["type"] == "call_failed"


def test_an_interrupted_stage_is_retried_with_the_context_it_was_started_with():
    run = _interrupted_second_stage()
    _restart()
    runs.load_saved()
    restored = runs.get_run(run.run_id)
    seen = {}

    def agent(context):
        seen.update(context)
        return "serialized"

    with patch.dict("integration_runner.stages.stage_agents", {"serialization": agent}):
        restored.rerun()
        restored._last_thread.join(timeout=5)

    assert seen["docs_output"] == "docs text"
    assert restored.events[-1]["type"] == "call_completed"


def test_an_interrupted_stage_cannot_be_approved():
    run = _interrupted_second_stage()
    _restart()
    runs.load_saved()

    with pytest.raises(ValueError, match="hasn't completed successfully"):
        runs.get_run(run.run_id).review("serialization", True)


def test_only_the_stage_still_running_is_marked_failed_after_a_retry():
    run = runs._new_run()
    run.record_event("call_started", "docs", {})
    run.record_event("call_failed", "docs", {"error": "x"})
    run.record_event("call_started", "docs", {})
    _restart()

    runs.load_saved()

    assert [event["type"] for event in runs.get_run(run.run_id).events] == [
        "call_started", "call_failed", "call_started", "call_failed",
    ]


def test_a_stage_that_finished_is_not_marked_failed():
    run = runs._new_run()
    run.record_event("call_started", "docs", {})
    run.record_event("call_completed", "docs", {"output": "x"})
    _restart()

    runs.load_saved()

    assert [event["type"] for event in runs.get_run(run.run_id).events] == ["call_started", "call_completed"]


# --- using a run that came back ------------------------------------------------


def test_a_finished_run_can_be_published_after_a_restart():
    run = _completed_run()
    _restart()
    runs.load_saved()
    published = {"id": "woodpecker-ci", "name": "Woodpecker CI", "version": 1, "output_filename": "ci.yml"}

    with patch("integration_runner.publishing.pipeline_generator_client.publish_platform", return_value=published) as publish:
        result = publishing.publish(runs.current())

    assert result == published
    assert publish.call_args.kwargs["name"] == "Woodpecker CI"
    assert publish.call_args.kwargs["source_run_id"] == run.run_id


def test_an_earlier_run_can_be_resumed_and_forked_after_a_restart():
    run = runs._new_run()
    run.record_event("call_started", "docs", {})
    run.record_event("call_completed", "docs", {"output": "docs text"})
    run.last_completed_stage = "docs"
    run.last_output = "docs text"
    run.last_context = {"docs_output": "docs text"}
    run.record_event("review_approved", "docs", {"correction": None})
    run.advance_stage()
    run.last_context = {"docs_output": "docs text", "serialization_output": "s", "pim_output": "pim"}
    run.current_stage_index = pipeline.STAGES.index("psm")
    run.record_event("call_started", "psm", {})
    run.record_event("call_completed", "psm", {"output": "p"})
    other = runs._new_run()
    other.record_event("call_started", "docs", {})
    other.record_event("call_completed", "docs", {"output": "x"})
    _restart()
    runs.load_saved()

    assert runs.current_run_id() == other.run_id
    assert runs.resume_run(run.run_id) == {"run_id": run.run_id, "current_stage": "psm"}
    forked = runs.fork_run(run.run_id, "psm")

    assert forked["stage"] == "psm"
    assert runs.get_run(forked["run_id"]).last_context == {
        "docs_output": "docs text", "serialization_output": "s", "pim_output": "pim",
    }


def test_the_service_loads_its_runs_when_it_starts():
    run = runs._new_run()
    run.record_event("call_started", "docs", {"platform_description": "Woodpecker CI"})
    run.record_event("call_completed", "docs", {"output": "x"})
    _restart()

    with TestClient(main.app) as client:
        listed = client.get("/runs").json()

    assert [r["run_id"] for r in listed] == [run.run_id]


def test_a_journal_that_vanished_between_saves_is_written_whole_again():
    run = runs._new_run()
    run.record_event("call_started", "docs", {})
    run.record_event("call_completed", "docs", {"output": "x"})
    _journal(run.run_id).path.unlink()

    run.record_event("constraint_added", "docs", {"constraint": "c"})
    _restart()
    runs.load_saved()

    assert [event["type"] for event in runs.get_run(run.run_id).events] == ["call_started", "call_completed", "constraint_added"]


def test_a_disk_that_stays_broken_is_logged_once(tmp_path, monkeypatch, caplog):
    blocker = tmp_path / "blocker"
    blocker.write_text("a file, not a folder", encoding="utf-8")
    monkeypatch.setattr(_validation, "RUNS_DIR", blocker / "runs")
    run = runs._new_run()

    with caplog.at_level(logging.ERROR):
        for _ in range(5):
            run.record_event("constraint_added", "docs", {"constraint": "c"})

    assert caplog.text.count("could not keep run") == 1
