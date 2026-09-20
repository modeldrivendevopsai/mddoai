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

This module's own concern is the attempt directory and manifest below.
The related "two near-simultaneous mutating requests race on the same run"
concern is handled in pipeline.py: IntegrationRun.claim_busy() makes the
busy check-and-set one atomic step.
"""
import json
import os
import shutil
import threading
from contextlib import contextmanager
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


def reserve_attempt_dir(run_id: str, stage: str) -> Path:
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
    needed.

    Public so a stage agent that calls out to a real validator (atl, acceleo
    directly; psm indirectly, via psm_agent's own generation code) can
    reserve its attempt directory before that call, and pass its name down
    as the validator's own attempt scope. See persist_attempt()'s own
    attempt_dir parameter for how the same reserved Path is then reused
    instead of reserved twice, and attempt_scope_kwargs() below for the
    stage/attempt kwargs every one of those callers forwards from it."""
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


def attempt_scope_kwargs(stage: str, attempt_dir: Path | None) -> dict[str, str | None]:
    """The stage/attempt kwargs a caller forwards into its own real
    validator call, from an already-reserved (or absent) attempt directory
    - shared by atl_stage/acceleo_stage/psm_stage so each doesn't repeat its
    own "stage if attempt_dir else None" / "attempt_dir.name if attempt_dir
    else None" pair. {"stage": None, "attempt": None} when there's no
    attempt_dir at all (no run_id to have reserved one under)."""
    return {
        "stage": stage if attempt_dir else None,
        "attempt": attempt_dir.name if attempt_dir else None,
    }


# Kept as an alias, not a second implementation: test_validation_concurrency.py
# and this module's own history both refer to the reservation step by this
# name. reserve_attempt_dir is the real, public entry point new callers
# (atl_stage, acceleo_stage) use directly.
_next_attempt_dir = reserve_attempt_dir


@contextmanager
def reserved_attempt(run_id: str | None, stage: str):
    """Reserve this stage's attempt directory up front, for a stage that has
    to hand its attempt number to a fallible downstream call (a real
    validator, or psm_agent) before it has a result to persist. If that call
    raises before persist_attempt() recorded this attempt, the whole
    reserved directory is removed again, so a transient failure
    (validator-agent timeout or restart, a network blip) does not leave an
    unlisted directory on disk or silently skip an attempt number on the
    next retry. "Recorded" means result.json exists: persist_attempt() always
    writes it, and it is the last of the two attempt files it writes, so its
    absence means nothing real was persisted here even if a half-finished
    validator round already nested its own compiled output under this
    directory (psm's codegen validation does exactly that, once per retry
    round). Yields None (nothing reserved, nothing to undo) when there is no
    run_id."""
    attempt_dir = reserve_attempt_dir(run_id, stage) if run_id else None
    try:
        yield attempt_dir
    except BaseException:
        if attempt_dir is not None and attempt_dir.is_dir() and not (attempt_dir / "result.json").exists():
            shutil.rmtree(attempt_dir, ignore_errors=True)
        raise


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
    # persist_attempt()'s own reserve_attempt_dir() call already creates
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


def persist_attempt(
    run_id: str,
    stage: str,
    filename: str,
    content: str,
    result: dict,
    attempt_dir: Path | None = None,
    prompt: dict | None = None,
    prompt_version: str | None = None,
) -> Path:
    """Writes this attempt's real artifact and validator-agent result to
    disk, synchronously, before the caller decides pass/fail — a failed
    attempt is exactly the record this exists to keep, so both files land
    on disk even when raise_if_invalid() (below) is about to raise. Never
    overwrites a prior attempt (see reserve_attempt_dir()). Also updates
    runs/<run_id>/manifest.json with this same attempt, every time — not
    something a caller does separately (see _update_manifest()). Returns
    the attempt directory, for a caller that wants to log/report its path.

    attempt_dir lets a caller that already reserved its own attempt
    directory (atl_stage, acceleo_stage, see each agent.py's own body) hand
    it in here instead of a second one being reserved: those two stages
    call validator_agent_client before this function ever runs, and the
    validator's own real compiled output (AtlValidator's .asm and
    AcceleoValidator's .emtl) needs the real attempt number to scope itself
    under, which only exists once reserve_attempt_dir() has actually run.
    When omitted (the default), this reserves its own attempt directory
    exactly as it always has, and every existing caller that doesn't pass
    this keeps working unchanged.

    prompt/prompt_version, when given, are also written to
    attempt_dir/prompt.json - the exact resolved parts (and the saved
    prompt-config version that produced them) this attempt's own real LLM
    call actually used, mirroring the real ai-research branch's own round
    layout (prompt.md alongside output.ecore/notes.md). Every stage with a
    real, config-driven prompt (psm, atl, acceleo) passes these; pim omits
    them, since it has no real prompt yet."""
    if attempt_dir is None:
        attempt_dir = reserve_attempt_dir(run_id, stage)
    (attempt_dir / filename).write_text(content, encoding="utf-8")
    if prompt is not None:
        prompt_record = {"prompt": prompt, "prompt_version": prompt_version}
        (attempt_dir / "prompt.json").write_text(json.dumps(prompt_record, indent=2), encoding="utf-8")
    # result.json last, and written atomically (same os.replace() the
    # manifest already uses): its presence is what reserved_attempt() reads
    # to tell a real, recorded attempt from a directory a failed call
    # stranded, so it must never appear half-written or before the artifact.
    _atomic_write_json(attempt_dir / "result.json", result)
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
