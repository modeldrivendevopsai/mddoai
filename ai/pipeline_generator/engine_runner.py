"""Subprocess boundary: runs the real engine CLI (main/'s `Main`, e.g.
`swarch2gitlab`) and reads back what it produced.

A fresh JVM per call, not a long-lived embedded one, for the same reason
validator_agent and execution_agent give: EMF keeps mutable global registry
state that was never designed for reuse across calls in one JVM.

The CLI is not a clean function of its arguments. It loads its compiled ATL
transformations from `./src/main/resources/transformations` and writes its
intermediate models to fixed relative paths (`./test/generatedModels` and
`./intermediate`), all relative to the current working directory. Two
concurrent calls sharing a directory delete each other's files and the run
fails, so every call runs in its own scratch directory seeded with just the
compiled transformations the engine reads.
"""
import logging
import os
import shutil
import subprocess
import tempfile
import time
from pathlib import Path
from typing import TypedDict

from platforms import Platform

logger = logging.getLogger(__name__)

# Resolved to absolute paths once, here: the subprocess below runs in a
# scratch working directory, so a path relative to this service's own
# directory would no longer point anywhere real by then. In Docker,
# Compose sets both explicitly; the defaults only matter for
# local dev against a sibling main/ checkout already built via
# `cd main && ./gradlew installDist`.
LIB_DIR = Path(os.environ.get("GENERATOR_LIB_DIR", "../../main/build/install/com.mddoai/lib")).resolve()
TRANSFORMATIONS_DIR = Path(
    os.environ.get("ENGINE_TRANSFORMATIONS_DIR", "../../main/src/main/resources/transformations")
).resolve()
MAIN_CLASS = "main.java.Main"
TIMEOUT_SECONDS = float(os.environ.get("GENERATOR_TIMEOUT_SECONDS", "60"))
# The largest file read back from the engine, and also the largest pipeline
# the validate endpoint accepts (main.py reuses it), so anything the engine
# can write can also be checked. A real pipeline is a few KB; this only guards
# against reading something unexpectedly huge into memory.
MAX_PIPELINE_BYTES = int(os.environ.get("GENERATOR_MAX_PIPELINE_BYTES", str(512 * 1024)))

# Where the engine's own handlers hardcode their intermediate output,
# relative to the working directory.
_INTERMEDIATES_SUBDIR = Path("test") / "generatedModels"
# The only file type the engine reads from its transformations directory.
_COMPILED_TRANSFORMATION_SUFFIX = ".asm"
# What the caller's model is always saved as: nothing the caller supplies is
# ever used to build a path.
_MODEL_FILENAME = "model.swarch"


class IntermediateModel(TypedDict):
    id: str
    title: str
    filename: str
    content: str


class GenerationResult(TypedDict):
    pipeline: str
    intermediates: list[IntermediateModel]
    duration_ms: int


class EngineError(Exception):
    """The engine subprocess failed to run or produced no usable output.
    Distinct from a model being invalid: callers validate the model first,
    so a failure here is the tool breaking, not a bad input."""


def _skip_all_but_compiled(directory: str, names: list[str]) -> list[str]:
    """shutil.copytree `ignore` callback: keep subdirectories (so the tree
    structure the engine expects survives) and compiled transformations,
    drop everything else (sources, Eclipse project files)."""
    return [
        name for name in names
        if not name.endswith(_COMPILED_TRANSFORMATION_SUFFIX) and not (Path(directory) / name).is_dir()
    ]


def _read_capped(path: Path) -> str:
    if path.stat().st_size > MAX_PIPELINE_BYTES:
        raise EngineError(f"engine output {path.name} exceeds {MAX_PIPELINE_BYTES} bytes")
    return path.read_text(encoding="utf-8")


def _seed_workdir(root: Path) -> Path:
    """The scratch working directory the engine runs in: just the compiled
    transformations, in the layout the engine looks for them."""
    work = root / "work"
    resources = work / "src" / "main" / "resources"
    resources.mkdir(parents=True)
    shutil.copytree(TRANSFORMATIONS_DIR, resources / "transformations", ignore=_skip_all_but_compiled)
    return work


def _run_cli(platform: Platform, model: Path, output_dir: Path, work: Path) -> None:
    # The trailing /* is the JVM's own classpath wildcard, expanded
    # internally at JVM startup - not a shell glob, so no shell=True.
    argv = ["java", "-cp", f"{LIB_DIR}/*", MAIN_CLASS, platform.command, str(model), str(output_dir)]
    try:
        proc = subprocess.run(argv, cwd=work, capture_output=True, text=True, timeout=TIMEOUT_SECONDS)
    except FileNotFoundError as e:
        raise EngineError(f"java executable not found: {e}") from e
    except subprocess.TimeoutExpired as e:
        raise EngineError(f"engine subprocess timed out after {TIMEOUT_SECONDS}s") from e
    except OSError as e:
        raise EngineError(f"failed to start engine subprocess: {e}") from e
    if proc.returncode != 0:
        raise EngineError(f"engine subprocess exited {proc.returncode}: {proc.stderr.strip()[:2000]}")


def _collect_output(platform: Platform, output_dir: Path, work: Path) -> tuple[str, list[IntermediateModel]]:
    pipeline_path = output_dir / platform.output_filename
    if not pipeline_path.is_file():
        raise EngineError(f"engine ran but produced no {platform.output_filename}")

    intermediates: list[IntermediateModel] = []
    for intermediate in platform.intermediates:
        path = work / _INTERMEDIATES_SUBDIR / intermediate.filename
        if path.is_file():
            intermediates.append({
                "id": intermediate.id,
                "title": intermediate.title,
                "filename": intermediate.filename,
                "content": _read_capped(path),
            })
    return _read_capped(pipeline_path), intermediates


def run_engine(content: str, platform: Platform) -> GenerationResult:
    start = time.monotonic()
    with tempfile.TemporaryDirectory() as tmp:
        root = Path(tmp)
        work = _seed_workdir(root)
        model = root / _MODEL_FILENAME
        model.write_text(content, encoding="utf-8")
        output_dir = root / "out"
        output_dir.mkdir()

        _run_cli(platform, model, output_dir, work)
        pipeline, intermediates = _collect_output(platform, output_dir, work)

        duration_ms = int((time.monotonic() - start) * 1000)
        logger.info("engine run: platform=%s duration_ms=%d", platform.id, duration_ms)
        return {"pipeline": pipeline, "intermediates": intermediates, "duration_ms": duration_ms}
