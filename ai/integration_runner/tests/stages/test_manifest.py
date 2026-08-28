"""stages/_validation.py's manifest.json contract: persist_attempt() writes
runs/<run_id>/manifest.json every time it persists an attempt, a flat,
chronological, append-only list of {run_id, stage, attempt_n, valid,
timestamp} records — the one place "what happened in this run" is
answerable without opening any attempt folder by hand.

RUNS_DIR is redirected to a throwaway tmp_path for every test in this whole
suite by conftest.py's own autouse fixture, not repeated here.
"""
import json
import threading
from unittest.mock import patch

from clients import validator_agent_client
from integration_runner.stages import _validation
from integration_runner.stages.acceleo.agent import acceleo_stage
from integration_runner.stages.atl.agent import atl_stage
from integration_runner.stages.psm.agent import psm_stage
from integration_runner.stages.pim.agent import pim_stage
from helpers import _validation_result


def _manifest(run_id: str) -> list[dict]:
    return json.loads((_validation.RUNS_DIR / run_id / "manifest.json").read_text(encoding="utf-8"))


def test_manifest_accumulates_every_attempt_across_stages_in_order():
    run_id = "manifest-order-run"
    with patch.object(validator_agent_client, "validate_ecore", return_value=_validation_result(valid=True)):
        pim_stage({"run_id": run_id})
        psm_stage({"run_id": run_id})  # attempt 1
        psm_stage({"run_id": run_id})  # a retry -> attempt 2, same stage
    with patch.object(validator_agent_client, "validate_atl", return_value=_validation_result(valid=True)):
        atl_stage({"run_id": run_id})

    manifest = _manifest(run_id)

    assert [(e["stage"], e["attempt_n"]) for e in manifest] == [
        ("pim", 1), ("psm", 1), ("psm", 2), ("atl", 1),
    ]
    assert all(e["run_id"] == run_id for e in manifest)
    assert all(e["valid"] is True for e in manifest)
    assert all("timestamp" in e and e["timestamp"] for e in manifest)


def test_manifest_records_a_failed_attempt_not_silently_dropped():
    run_id = "manifest-failure-run"
    failing = _validation_result(valid=False, issues=[{"severity": "error", "message": "broken", "source": None}])
    with patch.object(validator_agent_client, "validate_acceleo", return_value=failing):
        try:
            acceleo_stage({"run_id": run_id})
        except RuntimeError:
            pass  # raise_if_invalid() is expected to raise here — the point is the manifest entry still lands

    manifest = _manifest(run_id)

    assert len(manifest) == 1
    assert manifest[0]["stage"] == "acceleo"
    assert manifest[0]["attempt_n"] == 1
    assert manifest[0]["valid"] is False


def test_manifest_timestamps_are_real_and_advance():
    run_id = "manifest-timestamp-run"
    with patch.object(validator_agent_client, "validate_ecore", return_value=_validation_result(valid=True)):
        pim_stage({"run_id": run_id})
        psm_stage({"run_id": run_id})

    manifest = _manifest(run_id)

    assert manifest[0]["timestamp"] <= manifest[1]["timestamp"]  # ISO 8601 UTC sorts lexicographically


def test_manifest_concurrent_updates_do_not_lose_entries():
    # Same real concern as _next_attempt_dir()'s own race, one level up:
    # read-modify-write on the manifest file itself. _manifest_lock should
    # serialize every call so N concurrent updates always produce exactly N
    # entries, never fewer. Repeated many times — a single passing round
    # proves nothing for a race.
    thread_count = 16
    repeats = 30
    for round_n in range(repeats):
        run_id = f"manifest-race-run-{round_n}"
        barrier = threading.Barrier(thread_count)

        def worker(i):
            barrier.wait()
            _validation._update_manifest(run_id, "pim", i, True)

        threads = [threading.Thread(target=worker, args=(i,)) for i in range(1, thread_count + 1)]
        for t in threads:
            t.start()
        for t in threads:
            t.join(timeout=10)

        manifest = _manifest(run_id)
        assert len(manifest) == thread_count, (
            f"round {round_n}: expected {thread_count} manifest entries, got {len(manifest)} "
            f"(attempt_ns present: {sorted(e['attempt_n'] for e in manifest)}) — a concurrent update was lost"
        )
        assert sorted(e["attempt_n"] for e in manifest) == list(range(1, thread_count + 1))


def test_atomic_write_json_never_corrupts_an_existing_file_when_the_replace_itself_fails():
    # The real atomicity guarantee is at the os.replace() step — the temp
    # file can be fully written (a real crash could still happen right
    # before the rename), and the real path must still hold only its
    # previous complete content, never a half-applied one, since
    # os.replace() is a single all-or-nothing syscall: it either lands or
    # it doesn't, there's no "partially renamed" state to worry about. This
    # simulates the failure landing exactly there — after the temp write
    # genuinely succeeds, before the rename — by making os.replace() itself
    # raise, and confirms the real file is untouched.
    run_id = "manifest-corruption-run"
    manifest_path = _validation.RUNS_DIR / run_id / "manifest.json"
    manifest_path.parent.mkdir(parents=True)
    good_content = json.dumps([{"stage": "pim", "attempt_n": 1, "valid": True}])
    manifest_path.write_text(good_content, encoding="utf-8")

    with patch("integration_runner.stages._validation.os.replace", side_effect=OSError("disk full")):
        try:
            _validation._atomic_write_json(manifest_path, [{"stage": "psm", "attempt_n": 1, "valid": True}])
        except OSError:
            pass  # expected — the point is what's left on disk afterward

    assert manifest_path.read_text(encoding="utf-8") == good_content
