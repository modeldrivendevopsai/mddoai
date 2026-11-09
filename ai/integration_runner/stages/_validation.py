"""What the pim/psm/atl/acceleo/generation stage agents use to keep each try of
their stage: its real output with its validation result, win or lose, in the
journal (see clients/journal_client.py). pim/atl/acceleo also use this module to
turn a failing result into the raised exception pipeline.py's own
_run_stage_worker already reports as a call_failed event. psm deliberately does
not raise on its own generation-mode failure, see stages/psm/agent.py.

An attempt is reserved first, which claims its number, and recorded after. A
stage that calls a fallible service in between (a validator, psm_agent) reserves
it up front so it can pass the attempt's name down as the validator's scope.
"""
import logging
from contextlib import contextmanager
from dataclasses import dataclass

from clients import journal_client

logger = logging.getLogger(__name__)


@dataclass(frozen=True)
class Attempt:
    """One try of a stage in a run, as the journal numbers it."""

    run_id: str
    stage: str
    name: str  # such as "attempt_3"


def reserve_attempt(run_id: str, stage: str) -> Attempt:
    """The next attempt of the stage in the run."""
    return Attempt(run_id, stage, journal_client.reserve_attempt(run_id, stage))


def attempt_scope_kwargs(stage: str, attempt: Attempt | None) -> dict[str, str | None]:
    """The stage/attempt kwargs a caller forwards into its own real validator
    call, from an already reserved (or absent) attempt, so each stage does not
    repeat its own pair of "x if attempt else None". Both None when there is no
    attempt, because there is no run_id to have reserved one under."""
    return {
        "stage": stage if attempt else None,
        "attempt": attempt.name if attempt else None,
    }


@contextmanager
def reserved_attempt(run_id: str | None, stage: str):
    """Reserves this stage's attempt up front, for a stage that has to hand its
    attempt number to a fallible downstream call before it has a result to
    record. If that call raises before persist_attempt() recorded the attempt,
    the reservation is released, so a transient failure (a validator timeout or
    restart, a network blip) leaves no stray attempt and does not skip a number
    on the next retry. An attempt that was recorded is kept. Yields None, with
    nothing to undo, when there is no run_id."""
    attempt = reserve_attempt(run_id, stage) if run_id else None
    try:
        yield attempt
    except BaseException:
        if attempt is not None:
            _release(attempt)
        raise


def _release(attempt: Attempt) -> None:
    try:
        journal_client.discard_attempt(attempt.run_id, attempt.stage, attempt.name)
    except Exception as e:
        # A recorded attempt is refused with a 409, and is meant to stay. Any
        # other failure must not hide the error that is being raised.
        logger.warning("could not release %s of %s in run %s: %s", attempt.name, attempt.stage, attempt.run_id, e)


def persist_attempt(
    run_id: str,
    stage: str,
    filename: str,
    content: str,
    result: dict,
    attempt: Attempt | None = None,
    prompt: dict | None = None,
    prompt_version: str | None = None,
    extra_files: dict[str, str] | None = None,
) -> Attempt:
    """Records this attempt's real artifact and validator-agent result, before
    the caller decides pass or fail: a failed attempt is exactly the record this
    exists to keep, so it is kept even when raise_if_invalid() is about to
    raise. The journal also adds the attempt to the run's manifest.

    `attempt` is the one the caller reserved. When omitted, this reserves its
    own. `prompt` and `prompt_version`, when given, record the exact resolved
    parts, and the saved prompt-config version that produced them, that this
    attempt's LLM call used: every stage with a config-driven prompt (psm, atl,
    acceleo) passes them, and pim, which has none, omits them. `extra_files` are
    other files the try produced, by path inside the attempt, which can sit in
    subfolders."""
    if attempt is None:
        attempt = reserve_attempt(run_id, stage)
    journal_client.record_attempt(
        run_id,
        stage,
        attempt.name,
        filename=filename,
        content=content,
        result=result,
        prompt=prompt,
        prompt_version=prompt_version,
        extra_files=extra_files,
    )
    return attempt


def raise_if_invalid(stage: str, result: dict) -> None:
    """Turns a validator-agent 'valid: false' result into a real raised
    failure, carrying its real issue detail, never called for an infra
    failure (a raised httpx error from validator_agent_client propagates on
    its own, before persist_attempt ever runs, so there's no result to
    check here yet)."""
    if result["valid"]:
        return
    issues = "; ".join(f"{issue['severity']}: {issue['message']}" for issue in result["issues"])
    raise RuntimeError(f"{stage} output failed validation: {issues or 'no issue detail returned'}")
