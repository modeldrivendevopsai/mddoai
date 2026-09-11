"""Subprocess boundary: spawns the Java EcoreValidatorCli, parses its JSON stdout.

A fresh JVM per call, not a long-lived embedded one (JPype/Py4J) — see the plan's
"why subprocess, not JPype/Py4J" note. EMF's validator leans on mutable global
registry state (EPackage.Registry, EcorePlugin's platform resource map) that was
never designed for safe reuse across many calls in one shared JVM; a fresh JVM
per call sidesteps that risk entirely at the cost of measured, logged latency.
"""
import json
import logging
import os
import subprocess
import tempfile
import time
from pathlib import Path
from typing import Literal, TypedDict

logger = logging.getLogger(__name__)

# In Docker/production, ai/docker-compose.yml always sets this explicitly
# (pointed at the gradle-builder service's shared build-output volume) —
# this default only matters for local dev, run directly against a sibling
# main/ checkout already built via `cd main && ./gradlew installDist`.
LIB_DIR = os.environ.get("VALIDATOR_LIB_DIR", "../../main/build/install/com.mddoai/lib")
ECORE_MAIN_CLASS = "main.java.mddoai.validation.ecore.EcoreValidatorCli"
ATL_MAIN_CLASS = "main.java.mddoai.validation.atl.AtlValidatorCli"
ACCELEO_MAIN_CLASS = "main.java.mddoai.validation.acceleo.AcceleoValidatorCli"
TIMEOUT_SECONDS = float(os.environ.get("VALIDATOR_TIMEOUT_SECONDS", "60"))


class Issue(TypedDict):
    severity: str
    message: str
    source: str | None


class EcoreValidationResult(TypedDict):
    valid: bool
    mode: str
    issues: list[Issue]
    duration_ms: int
    # Where EcoreValidator's codegen path persisted the generated
    # .genmodel/src-gen/classes-out output, when there's real output worth
    # keeping (codegen mode, and generation got at least as far as producing
    # src-gen). None in reflective mode, or when nothing was generated.
    generated_source_path: str | None


class AtlValidationResult(TypedDict):
    valid: bool
    issues: list[Issue]
    duration_ms: int
    # Where AtlValidator persisted the compiled .asm bytecode, when compiling
    # actually produced one - see EcoreValidationResult's own
    # generated_source_path for why this is kept (this repo's stated
    # direction is real reuse, not just debugging), not just for Ecore.
    generated_source_path: str | None


class AcceleoValidationResult(TypedDict):
    valid: bool
    issues: list[Issue]
    duration_ms: int
    # Where AcceleoValidator persisted the compiled .emtl module, when
    # compiling actually produced one - see AtlValidationResult's own field.
    generated_source_path: str | None


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


def _scoped_output_env(
    run_id: str | None, stage: str | None = None, attempt: str | None = None
) -> dict[str, str] | None:
    """Overrides VALIDATOR_OUTPUT_DIR to a per-run_id/per-stage/per-attempt
    subfolder, for a call that's about to persist a real compiled artifact
    there (Ecore's codegen mode, ATL, Acceleo - see each *ValidatorCli's own
    OUTPUT_ROOT), so concurrent runs' compiled output never collides, and so
    it lands nested inside the exact same attempt directory
    integration_runner's own persist_attempt() writes to
    (runs/<run_id>/<stage>/attempt_N/) instead of beside it or, worse,
    beside a same-numbered attempt from a different stage of the same run.
    Segments are joined in order, run_id then stage then attempt, skipping
    any that are None - <base>/<run_id> when only run_id is given (unchanged
    from before stage/attempt existed - every caller that doesn't pass them
    keeps working exactly as it did). None (subprocess inherits this
    process's own environment as-is) when there's no run_id to scope by at
    all - the caller still gets real output, just landing directly in the
    configured base directory, unscoped. run_id, stage, and attempt are each
    already validated (character class plus the dot-segment check, see
    main.py's own _RUN_ID_PATTERN/_reject_dot_segments) before this function
    ever runs, so joining them onto the base path here is safe without a
    second check."""
    if run_id is None:
        return None
    env = os.environ.copy()
    output_dir = Path(env.get("VALIDATOR_OUTPUT_DIR", tempfile.gettempdir())) / run_id
    if stage is not None:
        output_dir /= stage
    if attempt is not None:
        output_dir /= attempt
    env["VALIDATOR_OUTPUT_DIR"] = str(output_dir)
    return env


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
        env = _scoped_output_env(run_id, stage, attempt) if mode == "codegen" else None
        result, duration_ms = _run_cli(argv, env=env)

        result["duration_ms"] = duration_ms
        # generatedOutputPath (EcoreValidatorCli's own camelCase JSON key,
        # matching Java naming) -> generated_source_path (this boundary's job
        # is translating the subprocess's raw JSON into this service's own
        # Python-conventioned response shape).
        result["generated_source_path"] = result.pop("generatedOutputPath", None)
        logger.info("ecore validation: mode=%s valid=%s duration_ms=%d", mode, result.get("valid"), duration_ms)
        return result


def run_atl_validator(
    content: str, filename: str, run_id: str | None = None, stage: str | None = None, attempt: str | None = None
) -> AtlValidationResult:
    with tempfile.TemporaryDirectory() as tmp:
        target = Path(tmp) / (Path(filename).name or "transformation.atl")
        target.write_text(content, encoding="utf-8")

        argv = ["java", "-cp", f"{LIB_DIR}/*", ATL_MAIN_CLASS, str(target)]
        result, duration_ms = _run_cli(argv, env=_scoped_output_env(run_id, stage, attempt))

        result["duration_ms"] = duration_ms
        result["generated_source_path"] = result.pop("generatedOutputPath", None)
        logger.info("atl validation: valid=%s duration_ms=%d", result.get("valid"), duration_ms)
        return result


def run_acceleo_validator(
    content: str,
    filename: str,
    run_id: str | None = None,
    stage: str | None = None,
    attempt: str | None = None,
    metamodel_ecore: str | None = None,
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
    regardless of how correct it actually is."""
    with tempfile.TemporaryDirectory() as tmp:
        target = Path(tmp) / (Path(filename).name or "generate.mtl")
        target.write_text(content, encoding="utf-8")

        argv = ["java", "-cp", f"{LIB_DIR}/*", ACCELEO_MAIN_CLASS, str(target)]
        if metamodel_ecore is not None:
            ecore_target = Path(tmp) / "target_metamodel.ecore"
            ecore_target.write_text(metamodel_ecore, encoding="utf-8")
            argv.append(str(ecore_target))
        result, duration_ms = _run_cli(argv, env=_scoped_output_env(run_id, stage, attempt))

        result["duration_ms"] = duration_ms
        result["generated_source_path"] = result.pop("generatedOutputPath", None)
        logger.info("acceleo validation: valid=%s duration_ms=%d", result.get("valid"), duration_ms)
        return result
