"""Subprocess boundary: spawns the Java EcoreValidatorCli, parses its JSON stdout.

A fresh JVM per call, not a long-lived embedded one (JPype/Py4J) — see the plan's
"why subprocess, not JPype/Py4J" note. EMF's validator leans on mutable global
registry state (EPackage.Registry, EcorePlugin's platform resource map) that was
never designed for safe reuse across many calls in one shared JVM; a fresh JVM
per call sidesteps that risk entirely at the cost of measured, logged latency.
"""
import io
import json
import logging
import os
import subprocess
import tempfile
import time
import zipfile
from pathlib import Path
from typing import Literal, TypedDict

import httpx

from clients import journal_client
from clients.agent_service_errors import AgentServiceError

logger = logging.getLogger(__name__)

def required_env(name: str) -> str:
    """A required setting. Unset or empty stops the service."""
    value = os.environ[name].strip()
    if not value:
        raise KeyError(f"{name} is set but empty")
    return value


# The Java libraries the validators run from. Required: no default. Docker Compose points it
# at the gradle-builder service's shared build-output volume. Run directly against a sibling
# main/ checkout, point it at that checkout's build/install/com.mddoai/lib.
LIB_DIR = required_env("VALIDATOR_LIB_DIR")
ECORE_MAIN_CLASS = "main.java.mddoai.validation.ecore.EcoreValidatorCli"
ATL_MAIN_CLASS = "main.java.mddoai.validation.atl.AtlValidatorCli"
ACCELEO_MAIN_CLASS = "main.java.mddoai.validation.acceleo.AcceleoValidatorCli"
SWARCH_MAIN_CLASS = "main.java.mddoai.validation.swarch.SwarchValidatorCli"
# How long one validator subprocess may run before it is treated as failed (engineering guess:
# a real validation takes a few seconds).
TIMEOUT_SECONDS = 60.0


class Issue(TypedDict):
    severity: str
    message: str
    source: str | None


class EcoreValidationResult(TypedDict):
    valid: bool
    mode: str
    issues: list[Issue]
    duration_ms: int
    # Whether what EcoreValidator's codegen path compiled (the .genmodel, src-gen and classes
    # output) was kept, as one zip with the attempt it was compiled for in the journal. False
    # in reflective mode, when nothing was generated, when the call named no attempt, or when
    # the journal could not keep it.
    compiled_output_kept: bool


class AtlValidationResult(TypedDict):
    valid: bool
    issues: list[Issue]
    duration_ms: int
    # Whether the compiled .asm bytecode was kept with the attempt, when compiling produced one:
    # see EcoreValidationResult's own compiled_output_kept for when it is not.
    compiled_output_kept: bool


class AcceleoValidationResult(TypedDict):
    valid: bool
    issues: list[Issue]
    duration_ms: int
    # Whether the compiled .emtl module was kept with the attempt: see AtlValidationResult's own field.
    compiled_output_kept: bool


class SwarchValidationResult(TypedDict):
    valid: bool
    issues: list[Issue]
    duration_ms: int


class ValidatorInfraError(Exception):
    """The subprocess itself failed to run or produce usable output — distinct
    from the validator successfully reporting an invalid model."""


def _run_cli(argv: list[str], env: dict[str, str] | None = None) -> tuple[dict, int]:
    """Shared subprocess boundary for both *ValidatorCli classes: run, time it,
    and turn every non-"the model is invalid" failure mode into ValidatorInfraError.
    Returns the parsed JSON plus the measured duration; callers own their own
    result-shape fields (mode, logging) since those differ per validator type.
    """
    start = time.monotonic()
    try:
        proc = subprocess.run(argv, capture_output=True, text=True, timeout=TIMEOUT_SECONDS, env=env)
    except FileNotFoundError as e:
        raise ValidatorInfraError(f"java executable not found: {e}") from e
    except subprocess.TimeoutExpired as e:
        raise ValidatorInfraError(f"validator subprocess timed out after {TIMEOUT_SECONDS}s") from e
    except OSError as e:
        # Defense in depth alongside main.py's own _ID_MAX_LENGTH cap on
        # run_id/stage/attempt: the OS's own execve() argv+envp size limit
        # (confirmed directly, in a real Linux container matching this
        # project's own Docker deployment: a large enough combined env
        # value raises exactly this, "Argument list too long") isn't
        # something either Python or Pydantic enforces on their own, so if
        # anything else ever pushes the real environment over that limit,
        # this still fails as a clean infra error instead of an unhandled
        # 500 with no useful detail.
        raise ValidatorInfraError(f"failed to start validator subprocess: {e}") from e
    duration_ms = int((time.monotonic() - start) * 1000)

    if proc.returncode != 0:
        raise ValidatorInfraError(
            f"validator subprocess exited {proc.returncode}: {proc.stderr.strip()[:2000]}"
        )
    try:
        result = json.loads(proc.stdout.strip().splitlines()[-1])
    except (json.JSONDecodeError, IndexError) as e:
        raise ValidatorInfraError(
            f"validator produced unparseable stdout: {proc.stdout[:2000]!r}"
        ) from e

    return result, duration_ms


def _output_env(output_dir: Path) -> dict[str, str]:
    """The environment a validator runs in, with the folder the Java validators compile into."""
    output_dir.mkdir(parents=True, exist_ok=True)
    return {**os.environ, "VALIDATOR_OUTPUT_DIR": str(output_dir)}


def _keep_compiled_output(output_dir: Path, run_id: str | None, stage: str | None, attempt: str | None) -> bool:
    """Gives what was compiled to the journal, as one zip kept with the attempt it was
    compiled for, so it is there for reuse and not only for debugging. Nothing is kept for a
    call that names no attempt, or that compiled nothing. A journal that cannot keep it is
    logged and does not fail the validation."""
    if run_id is None or stage is None or attempt is None:
        return False
    files = [path for path in sorted(output_dir.rglob("*")) if path.is_file()]
    if not files:
        return False
    archive = io.BytesIO()
    with zipfile.ZipFile(archive, "w", zipfile.ZIP_DEFLATED) as zipped:
        for path in files:
            zipped.write(path, path.relative_to(output_dir).as_posix())
    try:
        journal_client.keep_compiled_output(run_id, stage, attempt, archive.getvalue())
    except (AgentServiceError, httpx.HTTPError) as e:
        logger.warning("could not keep the compiled output of %s/%s/%s: %s", run_id, stage, attempt, e)
        return False
    return True


def _run_cli_keeping_output(
    argv: list[str], output_dir: Path, run_id: str | None, stage: str | None, attempt: str | None
) -> tuple[dict, int]:
    """Runs a validator with `output_dir`, a folder in scratch that is deleted with it, as the
    place it compiles into, and then keeps what it compiled (see _keep_compiled_output). The
    validator's own report of where it wrote is dropped: that place is gone."""
    result, duration_ms = _run_cli(argv, env=_output_env(output_dir))
    result.pop("generatedOutputPath", None)
    result["compiled_output_kept"] = _keep_compiled_output(output_dir, run_id, stage, attempt)
    return result, duration_ms


def run_ecore_validator(
    content: str,
    filename: str,
    mode: Literal["reflective", "codegen"],
    run_id: str | None = None,
    stage: str | None = None,
    attempt: str | None = None,
) -> EcoreValidationResult:
    with tempfile.TemporaryDirectory() as tmp:
        target = Path(tmp) / (Path(filename).name or "model.ecore")
        target.write_text(content, encoding="utf-8")

        # The trailing /* is the JVM's own classpath wildcard, expanded internally
        # at JVM startup — not a shell glob. Safe to pass as one argv element with
        # no shell=True.
        argv = ["java", "-cp", f"{LIB_DIR}/*", ECORE_MAIN_CLASS, mode, str(target)]
        result, duration_ms = _run_cli_keeping_output(argv, Path(tmp) / "out", run_id, stage, attempt)

        result["duration_ms"] = duration_ms
        logger.info("ecore validation: mode=%s valid=%s duration_ms=%d", mode, result.get("valid"), duration_ms)
        return result


def run_atl_validator(
    content: str,
    filename: str,
    run_id: str | None = None,
    stage: str | None = None,
    attempt: str | None = None,
    metamodel_ecore: str | None = None,
) -> AtlValidationResult:
    """metamodel_ecore, when given, is the target platform's own real PSM
    .ecore content - AtlValidatorCli's own optional second arg. Beyond
    dynamically registering it (mirroring run_acceleo_validator's own
    metamodel_ecore), a real target metamodel also lets AtlValidator actually
    RUN the compiled transformation against a real, fixed PIM model instance
    (mounted into this container - see AtlValidator.java's own
    ATL_SMOKE_TEST_PIM_INSTANCE_PATH), catching a real runtime-only failure
    (e.g. instantiating an abstract classifier) that compiling alone can
    never see. Without it, only compile-time checking runs, exactly as
    before."""
    with tempfile.TemporaryDirectory() as tmp:
        target = Path(tmp) / (Path(filename).name or "transformation.atl")
        target.write_text(content, encoding="utf-8")

        argv = ["java", "-cp", f"{LIB_DIR}/*", ATL_MAIN_CLASS, str(target)]
        if metamodel_ecore is not None:
            ecore_target = Path(tmp) / "target_metamodel.ecore"
            ecore_target.write_text(metamodel_ecore, encoding="utf-8")
            argv.append(str(ecore_target))
        result, duration_ms = _run_cli_keeping_output(argv, Path(tmp) / "out", run_id, stage, attempt)

        result["duration_ms"] = duration_ms
        logger.info("atl validation: valid=%s duration_ms=%d", result.get("valid"), duration_ms)
        return result


def run_acceleo_validator(
    content: str,
    filename: str,
    run_id: str | None = None,
    stage: str | None = None,
    attempt: str | None = None,
    metamodel_ecore: str | None = None,
    atl_source: str | None = None,
) -> AcceleoValidationResult:
    """metamodel_ecore, when given, is the target platform's own real PSM
    .ecore content - AcceleoValidatorCli's own optional second arg, which
    dynamically registers that platform's metamodel before compiling,
    unless the build already provides a compiled package for that nsURI
    (see AcceleoValidator.validate(String, String)'s own comment for why
    this needs no genmodel or compile step at all). Without it, only the
    metamodels EMFUtils.init() hardcodes (today: PIM, SWArch, GitLab) can
    ever resolve - every other platform's own real generated template would
    otherwise always fail with "the metamodel couldn't be resolved",
    regardless of how correct it actually is.

    atl_source, when given alongside metamodel_ecore, is this run's own
    already-generated ATL - AcceleoValidatorCli's own optional third arg.
    With it, AcceleoValidator also actually RUNS the compiled module against
    a real PSM model instance (produced by actually running atl_source
    against the same fixed PIM sample AtlValidator uses), catching a real
    runtime-only Acceleo failure that compiling alone can never see, the
    same class of gap metamodel_ecore alone already closes for AtlValidator.
    Ignored without metamodel_ecore too, since there's no target metamodel
    to run either executor against."""
    with tempfile.TemporaryDirectory() as tmp:
        target = Path(tmp) / (Path(filename).name or "generate.mtl")
        target.write_text(content, encoding="utf-8")

        argv = ["java", "-cp", f"{LIB_DIR}/*", ACCELEO_MAIN_CLASS, str(target)]
        if metamodel_ecore is not None:
            ecore_target = Path(tmp) / "target_metamodel.ecore"
            ecore_target.write_text(metamodel_ecore, encoding="utf-8")
            argv.append(str(ecore_target))
            if atl_source is not None:
                atl_target = Path(tmp) / "transformation.atl"
                atl_target.write_text(atl_source, encoding="utf-8")
                argv.append(str(atl_target))
        result, duration_ms = _run_cli_keeping_output(argv, Path(tmp) / "out", run_id, stage, attempt)

        result["duration_ms"] = duration_ms
        logger.info("acceleo validation: valid=%s duration_ms=%d", result.get("valid"), duration_ms)
        return result


def run_swarch_validator(content: str, filename: str) -> SwarchValidationResult:
    """A .swarch model instance has no compiled output worth keeping (unlike
    the .ecore/.atl/.mtl validators), so there's no run_id/stage/attempt
    scoping here - just validate and report.

    `filename` is only logged. The content is always written under a fixed
    name: a caller-supplied name would otherwise become a path on disk (a
    value such as ".." is not a usable file name) and its extension could
    change how the content is recognised."""
    with tempfile.TemporaryDirectory() as tmp:
        target = Path(tmp) / "model.swarch"
        target.write_text(content, encoding="utf-8")

        argv = ["java", "-cp", f"{LIB_DIR}/*", SWARCH_MAIN_CLASS, str(target)]
        result, duration_ms = _run_cli(argv, env=_output_env(Path(tmp) / "out"))

        result["duration_ms"] = duration_ms
        logger.info("swarch validation: filename=%r valid=%s duration_ms=%d", filename, result.get("valid"), duration_ms)
        return result
