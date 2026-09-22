"""Subprocess boundary: spawns the Java Atl/AcceleoExecutorCli, parses its
JSON stdout.

A fresh JVM per call, mirroring validator_agent's own real reasoning (see
its own validator_runner.py) - AtlExecutor/AcceleoExecutor do even more EMF
global registry manipulation per call (dynamically loading and registering
a fresh target metamodel every time) than the validators do, so the same
"never reuse a JVM across calls" argument applies at least as strongly here.
Not implemented by importing validator_runner.py directly: this is a
genuinely separate service (see this package's own README for why),
sibling code, not a shared dependency of one on the other.

Unlike validator_agent, there is no shared, per-run-scoped output volume
here: the real output (a transformed model instance, or generated CI/CD
text) is exactly what a caller needs back, not a debug-only compiled
artifact worth persisting for its own sake, so it's read back and returned
inline over HTTP the same way atl_agent's/acceleo_agent's own /generate
responses already return their real generated .atl/.mtl text inline.
"""
import json
import logging
import os
import subprocess
import tempfile
import time
from pathlib import Path
from typing import TypedDict

logger = logging.getLogger(__name__)

# In Docker/production, ai/docker-compose.yml always sets this explicitly
# (pointed at the gradle-builder service's shared build-output volume),
# this default only matters for local dev, run directly against a sibling
# main/ checkout already built via `cd main && ./gradlew installDist`.
LIB_DIR = os.environ.get("EXECUTION_LIB_DIR", "../../main/build/install/com.mddoai/lib")
ATL_MAIN_CLASS = "main.java.mddoai.execution.atl.AtlExecutorCli"
ACCELEO_MAIN_CLASS = "main.java.mddoai.execution.acceleo.AcceleoExecutorCli"
TIMEOUT_SECONDS = float(os.environ.get("EXECUTION_TIMEOUT_SECONDS", "60"))


class ExecutionInfraError(Exception):
    """The subprocess itself failed to run or produce usable output,
    distinct from the executor successfully reporting a real, describable
    execution failure (see ExecutionFailedError)."""


class ExecutionFailedError(Exception):
    """The executor ran to completion but reported a real failure (bad
    input, a genuine ATL/Acceleo compile or runtime failure - see
    Atl/AcceleoExecutorCli's own success:false JSON) - a legitimate
    business-error outcome, not an infrastructure problem."""


class AtlExecutionResult(TypedDict):
    output_xmi: str


class AcceleoExecutionResult(TypedDict):
    # Relative path (as the compiled Acceleo module itself named the file,
    # e.g. ".gitlab-ci.yml") -> real generated text content.
    generated_files: dict[str, str]


def _run_cli(argv: list[str]) -> tuple[dict, int]:
    """Shared subprocess boundary for both *ExecutorCli classes: run, time
    it, and turn every non-"the executor itself reported a real failure"
    failure mode into ExecutionInfraError. Mirrors validator_runner.py's own
    _run_cli exactly (same real subprocess failure modes apply here
    unchanged), duplicated rather than imported since this is a separate
    service's own code, not a shared library the two happen to both use."""
    start = time.monotonic()
    try:
        proc = subprocess.run(argv, capture_output=True, text=True, timeout=TIMEOUT_SECONDS)
    except FileNotFoundError as e:
        raise ExecutionInfraError(f"java executable not found: {e}") from e
    except subprocess.TimeoutExpired as e:
        raise ExecutionInfraError(f"execution subprocess timed out after {TIMEOUT_SECONDS}s") from e
    except OSError as e:
        raise ExecutionInfraError(f"failed to start execution subprocess: {e}") from e
    duration_ms = int((time.monotonic() - start) * 1000)

    if proc.returncode != 0:
        raise ExecutionInfraError(
            f"execution subprocess exited {proc.returncode}: {proc.stderr.strip()[:2000]}"
        )
    try:
        result = json.loads(proc.stdout.strip().splitlines()[-1])
    except (json.JSONDecodeError, IndexError) as e:
        raise ExecutionInfraError(
            f"execution subprocess produced unparseable stdout: {proc.stdout[:2000]!r}"
        ) from e
    return result, duration_ms


def run_atl_executor(
    atl_source: str, pim_model_xmi: str, target_ecore: str, output_model_name: str
) -> AtlExecutionResult:
    with tempfile.TemporaryDirectory() as tmp:
        tmp_path = Path(tmp)
        atl_file = tmp_path / "transformation.atl"
        atl_file.write_text(atl_source, encoding="utf-8")
        pim_model_file = tmp_path / "input.xmi"
        pim_model_file.write_text(pim_model_xmi, encoding="utf-8")
        ecore_file = tmp_path / "target.ecore"
        ecore_file.write_text(target_ecore, encoding="utf-8")
        output_file = tmp_path / "output.xmi"

        argv = [
            "java", "-cp", f"{LIB_DIR}/*", ATL_MAIN_CLASS,
            str(atl_file), str(pim_model_file), str(ecore_file), output_model_name, str(output_file),
        ]
        result, duration_ms = _run_cli(argv)

        if not result.get("success"):
            raise ExecutionFailedError(result.get("error") or "ATL execution failed with no detail")
        logger.info("atl execution: success duration_ms=%d", duration_ms)
        return {"output_xmi": output_file.read_text(encoding="utf-8")}


def run_acceleo_executor(mtl_source: str, psm_model_xmi: str, target_ecore: str) -> AcceleoExecutionResult:
    with tempfile.TemporaryDirectory() as tmp:
        tmp_path = Path(tmp)
        mtl_file = tmp_path / "generate.mtl"
        mtl_file.write_text(mtl_source, encoding="utf-8")
        psm_model_file = tmp_path / "input.xmi"
        psm_model_file.write_text(psm_model_xmi, encoding="utf-8")
        ecore_file = tmp_path / "target.ecore"
        ecore_file.write_text(target_ecore, encoding="utf-8")
        output_dir = tmp_path / "generated"
        output_dir.mkdir()

        argv = [
            "java", "-cp", f"{LIB_DIR}/*", ACCELEO_MAIN_CLASS,
            str(mtl_file), str(psm_model_file), str(ecore_file), str(output_dir),
        ]
        result, duration_ms = _run_cli(argv)

        if not result.get("success"):
            raise ExecutionFailedError(result.get("error") or "Acceleo execution failed with no detail")
        generated_files = {
            relative_path: (output_dir / relative_path).read_text(encoding="utf-8")
            for relative_path in result.get("files", [])
        }
        logger.info("acceleo execution: success files=%d duration_ms=%d", len(generated_files), duration_ms)
        return {"generated_files": generated_files}
