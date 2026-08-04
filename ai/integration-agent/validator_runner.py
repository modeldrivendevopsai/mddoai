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

LIB_DIR = os.environ.get("VALIDATOR_LIB_DIR", "/app/lib")
MAIN_CLASS = "main.java.mddoai.validation.EcoreValidatorCli"
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


class ValidatorInfraError(Exception):
    """The subprocess itself failed to run or produce usable output — distinct
    from the validator successfully reporting an invalid model."""


def run_ecore_validator(content: str, filename: str, mode: Literal["reflective", "codegen"]) -> EcoreValidationResult:
    with tempfile.TemporaryDirectory() as tmp:
        target = Path(tmp) / (Path(filename).name or "model.ecore")
        target.write_text(content, encoding="utf-8")

        # The trailing /* is the JVM's own classpath wildcard, expanded internally
        # at JVM startup — not a shell glob. Safe to pass as one argv element with
        # no shell=True.
        argv = ["java", "-cp", f"{LIB_DIR}/*", MAIN_CLASS, mode, str(target)]
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

        result["duration_ms"] = duration_ms
        logger.info("ecore validation: mode=%s valid=%s duration_ms=%d", mode, result.get("valid"), duration_ms)
        return result
