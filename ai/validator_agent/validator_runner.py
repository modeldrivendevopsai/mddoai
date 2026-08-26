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


class ValidatorInfraError(Exception):
    """The subprocess itself failed to run or produce usable output — distinct
    from the validator successfully reporting an invalid model."""


def _run_cli(argv: list[str]) -> tuple[dict, int]:
    """Shared subprocess boundary for both *ValidatorCli classes: run, time it,
    and turn every non-"the model is invalid" failure mode into ValidatorInfraError.
    Returns the parsed JSON plus the measured duration; callers own their own
    result-shape fields (mode, logging) since those differ per validator type.
    """
    start = time.monotonic()
    try:
        proc = subprocess.run(argv, capture_output=True, text=True, timeout=TIMEOUT_SECONDS)
    except FileNotFoundError as e:
        raise ValidatorInfraError(f"java executable not found: {e}") from e
    except subprocess.TimeoutExpired as e:
        raise ValidatorInfraError(f"validator subprocess timed out after {TIMEOUT_SECONDS}s") from e
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


def run_ecore_validator(content: str, filename: str, mode: Literal["reflective", "codegen"]) -> EcoreValidationResult:
    with tempfile.TemporaryDirectory() as tmp:
        target = Path(tmp) / (Path(filename).name or "model.ecore")
        target.write_text(content, encoding="utf-8")

        # The trailing /* is the JVM's own classpath wildcard, expanded internally
        # at JVM startup — not a shell glob. Safe to pass as one argv element with
        # no shell=True.
        argv = ["java", "-cp", f"{LIB_DIR}/*", ECORE_MAIN_CLASS, mode, str(target)]
        result, duration_ms = _run_cli(argv)

        result["duration_ms"] = duration_ms
        # generatedOutputPath (EcoreValidatorCli's own camelCase JSON key,
        # matching Java naming) -> generated_source_path (this boundary's job
        # is translating the subprocess's raw JSON into this service's own
        # Python-conventioned response shape).
        result["generated_source_path"] = result.pop("generatedOutputPath", None)
        logger.info("ecore validation: mode=%s valid=%s duration_ms=%d", mode, result.get("valid"), duration_ms)
        return result


def run_atl_validator(content: str, filename: str) -> AtlValidationResult:
    with tempfile.TemporaryDirectory() as tmp:
        target = Path(tmp) / (Path(filename).name or "transformation.atl")
        target.write_text(content, encoding="utf-8")

        argv = ["java", "-cp", f"{LIB_DIR}/*", ATL_MAIN_CLASS, str(target)]
        result, duration_ms = _run_cli(argv)

        result["duration_ms"] = duration_ms
        logger.info("atl validation: valid=%s duration_ms=%d", result.get("valid"), duration_ms)
        return result
