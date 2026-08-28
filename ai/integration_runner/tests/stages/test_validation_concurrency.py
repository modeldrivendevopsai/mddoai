"""stages/_validation.py concurrency tests: _next_attempt_dir()'s own
try-create-and-retry loop under genuine concurrent contention, and proof
that the check-then-act logic it replaced (list the directory, compute
"next" in Python, create that exact name as a separate step) really would
collide under the same contention — not just a claim, a real side-by-side
reproduction using the exact same stress harness against both
implementations.

Races are inherently flaky: a single passing run proves nothing. Every test
here repeats its stress round many times (_REPEATS), each round with a
fresh threading.Barrier so every thread is released as close to
simultaneously as real OS scheduling allows, not trickling in one at a time
in creation order (which would never exercise the race at all).

RUNS_DIR is redirected to a throwaway tmp_path for every test in this whole
suite by conftest.py's own autouse fixture, not repeated here.
"""
import os
import threading
import time
from pathlib import Path

from integration_runner.stages import _validation

_THREAD_COUNT = 16
_REPEATS = 30


def _broken_next_attempt_dir(run_id: str, stage: str) -> Path:
    """A faithful replica of the list-then-create logic _next_attempt_dir()
    used to have, before the atomic try-create-and-retry fix — kept here,
    not in the real module, specifically so this test file can prove the
    fix actually closes a real race rather than just asserting it does.
    The sleep() between the list and the create widens the race window
    deterministically (the same interleaving a contended real disk or
    scheduler can produce on its own, just reliable enough here to test
    against instead of hoping for it), it's not needed to make the race
    POSSIBLE, only to make it reliably observable in a test run.
    """
    stage_dir = _validation.RUNS_DIR / run_id / stage
    stage_dir.mkdir(parents=True, exist_ok=True)
    existing = [
        int(name.removeprefix("attempt_"))
        for name in os.listdir(stage_dir)
        if name.startswith("attempt_") and name.removeprefix("attempt_").isdigit()
    ]
    time.sleep(0.01)
    attempt_dir = stage_dir / f"attempt_{max(existing, default=0) + 1}"
    attempt_dir.mkdir()
    return attempt_dir


def _stress_round(fn, run_id: str, stage: str, thread_count: int):
    """Launches thread_count threads, all calling fn(run_id, stage), held
    at a barrier until every thread has reached it so they're released as
    one simultaneous burst — real contention, not sequential calls that
    happen to be on different threads. Returns (successes, exceptions):
    every thread's real outcome, nothing swallowed."""
    barrier = threading.Barrier(thread_count)
    successes = []
    exceptions = []
    lock = threading.Lock()

    def worker():
        barrier.wait()
        try:
            result = fn(run_id, stage)
            with lock:
                successes.append(result)
        except Exception as e:  # noqa: BLE001 - a real race outcome, not swallowed
            with lock:
                exceptions.append(e)

    threads = [threading.Thread(target=worker) for _ in range(thread_count)]
    for t in threads:
        t.start()
    for t in threads:
        t.join(timeout=10)

    return successes, exceptions


def test_next_attempt_dir_never_collides_under_real_concurrency():
    for round_n in range(_REPEATS):
        run_id = f"race-fix-run-{round_n}"
        successes, exceptions = _stress_round(_validation._next_attempt_dir, run_id, "pim", _THREAD_COUNT)

        assert exceptions == [], f"round {round_n}: {len(exceptions)} threads raised: {exceptions}"
        assert len(successes) == _THREAD_COUNT, f"round {round_n}: only {len(successes)}/{_THREAD_COUNT} succeeded"
        names = sorted(p.name for p in successes)
        expected = sorted(f"attempt_{n}" for n in range(1, _THREAD_COUNT + 1))
        assert names == expected, f"round {round_n}: got {names}, expected {expected} (duplicate or gap)"
        # Every one of those names is a real directory on disk, not just a
        # Path object that happened not to collide with another Path object.
        for p in successes:
            assert p.is_dir(), f"round {round_n}: {p} was returned but doesn't exist on disk"


def test_old_list_then_create_logic_would_have_collided():
    # Proves the stress harness itself is a real race detector, and that
    # the logic _next_attempt_dir() used to have genuinely fails it — not
    # a hypothetical, the same _stress_round() used to prove the fix above,
    # pointed at the broken implementation instead.
    collided_rounds = 0
    for round_n in range(_REPEATS):
        run_id = f"race-broken-run-{round_n}"
        successes, exceptions = _stress_round(_broken_next_attempt_dir, run_id, "pim", _THREAD_COUNT)

        names = sorted(p.name for p in successes)
        has_duplicate = len(names) != len(set(names))
        lost_writes = len(successes) < _THREAD_COUNT
        if exceptions or has_duplicate or lost_writes:
            collided_rounds += 1

    assert collided_rounds > 0, (
        f"the broken list-then-create logic never collided across {_REPEATS} rounds of "
        f"{_THREAD_COUNT} concurrent callers — the stress harness isn't actually exercising the race"
    )
