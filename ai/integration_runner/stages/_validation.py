"""Shared helper the pim/psm/atl/acceleo stage agents use to persist their
DSL output (mock for pim/atl/acceleo, real for psm) alongside its
validation result, win or lose. pim/atl/acceleo also use this module to
turn a failing result into the same raised-exception failure pipeline.py's
own _run_stage_worker already knows how to report (a call_failed event
carrying str(e)) — matching stages/docs/agent.py's own raise-on-failure
convention, no new reporting path needed. psm deliberately does NOT raise
on its own generation-mode failure — see stages/psm/agent.py's own
docstring for why. The one other thing these four stages share besides
_shared.py's constraints_note() (which they no longer use, see each
stage's own agent.py).

Known, deliberately out of scope here: IntegrationRun.busy (pipeline.py) is
a plain unguarded bool, checked then set across two unsynchronized steps
spanning a route handler and run_stage_async() — every real mutating
endpoint is a sync `def`, dispatched through FastAPI's own real threadpool
(not just the asyncio loop), so two near-simultaneous requests to the same
endpoint really can both pass that check before either sets busy=True,
starting two stage runs against the same run concurrently. That's a
correctness issue broader than file naming (two operations racing against
the same run, not just a folder collision) and deserves its own dedicated
fix, not a side effect of closing the attempt-numbering race below.
"""
import json
import os
import threading
from datetime import datetime, timezone
from pathlib import Path

# Sibling to stages/ itself (ai/integration_runner/runs/), so this travels
# with the service in any deployment, local or Docker, without needing a
# separately mounted path — mounting it externally for durability across a
# container restart is a real future concern, deliberately not solved here.
RUNS_DIR = Path(__file__).resolve().parent.parent / "runs"

# Serializes manifest.json's own read-modify-write (_update_manifest())
# process-wide — sufficient because this service runs single-process, no
# --workers flag (see integration_runner/Dockerfile); a cross-process file
# lock would solve a problem this deployment doesn't actually have.
_manifest_lock = threading.Lock()


def _next_attempt_dir(run_id: str, stage: str) -> Path:
    """The Nth attempt for this run+stage, one-indexed — found by atomically
    trying to create attempt_1, attempt_2, ... in turn, not by listing the
    directory first and trusting that snapshot. Path.mkdir()'s default
    exist_ok=False already raises FileExistsError atomically (backed by the
    OS's own atomic mkdir(2)); an earlier version of this function listed
    the directory to compute "next" as a separate step before creating it
    — a real, reachable check-then-act race (confirmed against this
    service's actual threading model, not just in theory: every mutating
    endpoint is a sync route dispatched through FastAPI's real threadpool,
    see this module's own docstring) where two concurrent callers could
    compute the same number and collide. Trying each candidate in turn and
    catching the collision is what makes this actually atomic, no pre-scan
    needed."""
    stage_dir = RUNS_DIR / run_id / stage
    stage_dir.mkdir(parents=True, exist_ok=True)
    n = 1
    while True:
        attempt_dir = stage_dir / f"attempt_{n}"
        try:
            attempt_dir.mkdir()
            return attempt_dir
        except FileExistsError:
            n += 1


def _atomic_write_json(path: Path, data) -> None:
    """Writes data to path as JSON without ever leaving a partially-written
    or corrupted file behind, even if the process crashes mid-write: writes
    to a sibling temp file first, then os.replace()'s it into place — an
    atomic rename on both POSIX and Windows, so any reader always sees
    either the previous complete version or the new one, never a half-written
    one. The temp name includes the pid and thread id so two threads writing
    concurrently never collide on the temp file itself — only the final
    os.replace() needs to be serialized against other writers of the same
    real path, which _update_manifest()'s own lock already does."""
    tmp_path = path.with_name(f"{path.name}.tmp.{os.getpid()}.{threading.get_ident()}")
    tmp_path.write_text(json.dumps(data, indent=2), encoding="utf-8")
    os.replace(tmp_path, path)


def _update_manifest(run_id: str, stage: str, attempt_n: int, valid: bool) -> None:
    """Appends this attempt's summary to runs/<run_id>/manifest.json — the
    one place that answers "what happened in this run" without opening any
    attempt folder by hand: a flat, append-only, chronological list of
    {run_id, stage, attempt_n, valid, timestamp} records, one call = one
    record. Read-modify-write, so an atomic write on its own isn't enough —
    two concurrent updates could both read the same old version and each
    write back independently, silently losing whichever wrote first.
    _manifest_lock serializes the whole read-modify-write as one critical
    section (correct and sufficient here — see this module's own docstring
    for why a single process's threading.Lock is the real deployment shape,
    not a cross-process file lock); _atomic_write_json's own os.replace() on
    top means a crash mid-write still can't corrupt a previously-good
    manifest, on top of what the lock already prevents between live
    writers."""
    manifest_path = RUNS_DIR / run_id / "manifest.json"
    # persist_attempt()'s own call to _next_attempt_dir() already creates
    # this directory before _update_manifest() ever runs, but this
    # shouldn't be a function that only works if called in the right order
    # after something else — exist_ok=True makes the normal case a no-op.
    manifest_path.parent.mkdir(parents=True, exist_ok=True)
    entry = {
        "run_id": run_id,
        "stage": stage,
        "attempt_n": attempt_n,
        "valid": valid,
        "timestamp": datetime.now(timezone.utc).isoformat(),
    }
    with _manifest_lock:
        if manifest_path.exists():
            manifest = json.loads(manifest_path.read_text(encoding="utf-8"))
        else:
            manifest = []
        manifest.append(entry)
        _atomic_write_json(manifest_path, manifest)


def persist_attempt(run_id: str, stage: str, filename: str, content: str, result: dict) -> Path:
    """Writes this attempt's real artifact and validator-agent result to
    disk, synchronously, before the caller decides pass/fail — a failed
    attempt is exactly the record this exists to keep, so both files land
    on disk even when raise_if_invalid() (below) is about to raise. Never
    overwrites a prior attempt (see _next_attempt_dir()). Also updates
    runs/<run_id>/manifest.json with this same attempt, every time — not
    something a caller does separately (see _update_manifest()). Returns
    the attempt directory, for a caller that wants to log/report its path."""
    attempt_dir = _next_attempt_dir(run_id, stage)
    (attempt_dir / filename).write_text(content, encoding="utf-8")
    (attempt_dir / "result.json").write_text(json.dumps(result, indent=2), encoding="utf-8")
    attempt_n = int(attempt_dir.name.removeprefix("attempt_"))
    _update_manifest(run_id, stage, attempt_n, result["valid"])
    return attempt_dir


def raise_if_invalid(stage: str, result: dict) -> None:
    """Turns a validator-agent 'valid: false' result into a real raised
    failure, carrying its real issue detail — never called for an infra
    failure (a raised httpx error from validator_agent_client propagates on
    its own, before persist_attempt ever runs, so there's no result to
    check here yet)."""
    if result["valid"]:
        return
    issues = "; ".join(f"{issue['severity']}: {issue['message']}" for issue in result["issues"])
    raise RuntimeError(f"{stage} output failed validation: {issues or 'no issue detail returned'}")
