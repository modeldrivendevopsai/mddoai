"""Shared helper the pim/psm/atl/acceleo stage agents use to persist their
(currently mock) DSL output alongside its real validator-agent result, win
or lose, and to turn a failing result into the same raised-exception
failure pipeline.py's own _run_stage_worker already knows how to report
(a call_failed event carrying str(e)) — matching stages/docs/agent.py's own
raise-on-failure convention, no new reporting path needed. The one other
thing these four stages share besides _shared.py's constraints_note()
(which they no longer use, see each stage's own agent.py).
"""
import json
import os
from pathlib import Path

# Sibling to stages/ itself (ai/integration_runner/runs/), so this travels
# with the service in any deployment, local or Docker, without needing a
# separately mounted path — mounting it externally for durability across a
# container restart is a real future concern, deliberately not solved here.
RUNS_DIR = Path(__file__).resolve().parent.parent / "runs"


def _next_attempt_dir(run_id: str, stage: str) -> Path:
    """The Nth attempt for this run+stage, one-indexed, computed from what's
    already on disk rather than an in-memory counter — a counter would
    drift from reality across a process restart, disk is the one source of
    truth for "how many attempts already happened" here."""
    stage_dir = RUNS_DIR / run_id / stage
    stage_dir.mkdir(parents=True, exist_ok=True)
    existing = [
        int(name.removeprefix("attempt_"))
        for name in os.listdir(stage_dir)
        if name.startswith("attempt_") and name.removeprefix("attempt_").isdigit()
    ]
    attempt_dir = stage_dir / f"attempt_{max(existing, default=0) + 1}"
    attempt_dir.mkdir()
    return attempt_dir


def persist_attempt(run_id: str, stage: str, filename: str, content: str, result: dict) -> Path:
    """Writes this attempt's real artifact and validator-agent result to
    disk, synchronously, before the caller decides pass/fail — a failed
    attempt is exactly the record this exists to keep, so both files land
    on disk even when raise_if_invalid() (below) is about to raise. Never
    overwrites a prior attempt (see _next_attempt_dir()). Returns the
    attempt directory, for a caller that wants to log/report its path."""
    attempt_dir = _next_attempt_dir(run_id, stage)
    (attempt_dir / filename).write_text(content, encoding="utf-8")
    (attempt_dir / "result.json").write_text(json.dumps(result, indent=2), encoding="utf-8")
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
