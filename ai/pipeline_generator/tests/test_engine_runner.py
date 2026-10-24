"""engine_runner.py unit tests: mocks only the real boundary, subprocess.run
standing in for the Java process, matching validator_agent's own convention.
Everything else (scratch directory, file layout, reading output back) runs
for real."""
import subprocess
from pathlib import Path
from unittest.mock import MagicMock, patch

import pytest

import engine_runner
from engine_runner import EngineError, run_engine
from platforms import PLATFORMS

GITLAB = PLATFORMS["gitlab"]


@pytest.fixture
def transformations(tmp_path):
    root = tmp_path / "transformations"
    (root / "swarch2pim").mkdir(parents=True)
    (root / "swarch2pim" / "swarch2pim.asm").write_bytes(b"compiled")
    (root / "swarch2pim" / "swarch2pim.atl").write_text("source", encoding="utf-8")
    (root / "swarch2pim" / ".project").write_text("eclipse", encoding="utf-8")
    with patch.object(engine_runner, "TRANSFORMATIONS_DIR", root):
        yield root


def _engine_that_succeeds(pipeline="stages: [build]\n", write_intermediates=True, capture=None):
    def fake_run(argv, cwd, **kwargs):
        if capture is not None:
            capture["argv"], capture["cwd"], capture["kwargs"] = argv, Path(cwd), kwargs
            capture["model"] = Path(argv[5]).read_text(encoding="utf-8")
            capture["transformations"] = sorted(
                p.relative_to(cwd).as_posix() for p in (Path(cwd) / "src").rglob("*") if p.is_file()
            )
        Path(argv[6], ".gitlab-ci.yml").write_text(pipeline, encoding="utf-8")
        if write_intermediates:
            models = Path(cwd) / "test" / "generatedModels"
            models.mkdir(parents=True)
            (models / "PipelinePIM.pimmm").write_text("<pim/>", encoding="utf-8")
            (models / "PipelineGit.gitlabmm").write_text("<gitlab/>", encoding="utf-8")
        proc = MagicMock()
        proc.returncode, proc.stdout, proc.stderr = 0, "", ""
        return proc

    return fake_run


def test_invokes_the_real_cli_with_the_platforms_command_then_the_paths(transformations):
    capture = {}
    with patch("engine_runner.subprocess.run", side_effect=_engine_that_succeeds(capture=capture)):
        run_engine("<swarch/>", GITLAB)

    argv = capture["argv"]
    assert argv[:2] == ["java", "-cp"]
    assert argv[2].endswith("/*")
    assert argv[3] == "main.java.Main"
    assert argv[4] == "swarch2gitlab"
    assert Path(argv[5]).name == "model.swarch"
    assert Path(argv[6]).name == "out"
    assert capture["kwargs"]["timeout"] == engine_runner.TIMEOUT_SECONDS


def test_writes_the_model_to_a_fixed_name_never_one_the_caller_chose(transformations):
    capture = {}
    with patch("engine_runner.subprocess.run", side_effect=_engine_that_succeeds(capture=capture)):
        run_engine("<swarch name='x'/>", GITLAB)

    assert capture["model"] == "<swarch name='x'/>"
    assert Path(capture["argv"][5]).name == "model.swarch"


def test_runs_in_a_scratch_directory_seeded_with_only_compiled_transformations(transformations):
    capture = {}
    with patch("engine_runner.subprocess.run", side_effect=_engine_that_succeeds(capture=capture)):
        run_engine("<swarch/>", GITLAB)

    # Sources and Eclipse project files are not copied, only what the engine reads.
    assert capture["transformations"] == ["src/main/resources/transformations/swarch2pim/swarch2pim.asm"]


def test_each_call_gets_its_own_scratch_directory(transformations):
    first, second = {}, {}
    with patch("engine_runner.subprocess.run", side_effect=_engine_that_succeeds(capture=first)):
        run_engine("<swarch/>", GITLAB)
    with patch("engine_runner.subprocess.run", side_effect=_engine_that_succeeds(capture=second)):
        run_engine("<swarch/>", GITLAB)

    assert first["cwd"] != second["cwd"]


def test_the_scratch_directory_is_removed_afterward(transformations):
    capture = {}
    with patch("engine_runner.subprocess.run", side_effect=_engine_that_succeeds(capture=capture)):
        run_engine("<swarch/>", GITLAB)

    assert not capture["cwd"].exists()


def test_returns_the_pipeline_and_the_intermediate_models(transformations):
    with patch("engine_runner.subprocess.run", side_effect=_engine_that_succeeds(pipeline="stages: [x]\n")):
        result = run_engine("<swarch/>", GITLAB)

    assert result["pipeline"] == "stages: [x]\n"
    assert [(m["id"], m["filename"], m["content"]) for m in result["intermediates"]] == [
        ("pim", "PipelinePIM.pimmm", "<pim/>"),
        ("platform_model", "PipelineGit.gitlabmm", "<gitlab/>"),
    ]
    assert result["duration_ms"] >= 0


def test_a_missing_intermediate_is_skipped_not_fatal(transformations):
    with patch("engine_runner.subprocess.run", side_effect=_engine_that_succeeds(write_intermediates=False)):
        result = run_engine("<swarch/>", GITLAB)

    assert result["intermediates"] == []


def test_a_nonzero_exit_becomes_an_engine_error_carrying_stderr(transformations):
    proc = MagicMock(returncode=1, stdout="", stderr="boom")
    with patch("engine_runner.subprocess.run", return_value=proc):
        with pytest.raises(EngineError, match="boom"):
            run_engine("<swarch/>", GITLAB)


def test_a_clean_exit_with_no_output_file_is_an_engine_error(transformations):
    proc = MagicMock(returncode=0, stdout="", stderr="")
    with patch("engine_runner.subprocess.run", return_value=proc):
        with pytest.raises(EngineError, match="produced no"):
            run_engine("<swarch/>", GITLAB)


def test_a_timeout_becomes_an_engine_error(transformations):
    with patch("engine_runner.subprocess.run", side_effect=subprocess.TimeoutExpired("java", 1)):
        with pytest.raises(EngineError, match="timed out"):
            run_engine("<swarch/>", GITLAB)


def test_a_missing_java_becomes_an_engine_error(transformations):
    with patch("engine_runner.subprocess.run", side_effect=FileNotFoundError("java")):
        with pytest.raises(EngineError, match="java executable not found"):
            run_engine("<swarch/>", GITLAB)


def test_oversized_output_is_refused_not_read_into_memory(transformations):
    with patch.object(engine_runner, "MAX_PIPELINE_BYTES", 5):
        with patch("engine_runner.subprocess.run", side_effect=_engine_that_succeeds(pipeline="x" * 50)):
            with pytest.raises(EngineError, match="exceeds"):
                run_engine("<swarch/>", GITLAB)
