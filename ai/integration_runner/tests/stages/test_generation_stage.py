"""integration_runner/stages/generation/agent.py's real (non-placeholder)
unit tests: gen_stage actually runs this run's own real, generated ATL and
Acceleo source against a real PIM model instance, via execution_agent -
no LLM call of its own any more. No real HTTP call -
clients/execution_agent_client.execute_atl/execute_acceleo are mocked.
PIM_SAMPLE_INSTANCE_PATH is redirected to a throwaway fixture file per test
(see _pim_sample_fixture below), so these tests never depend on this
repo's real main/ test-resources tree. RUNS_DIR is redirected to a tmp_path
by integration_runner/tests/conftest.py's own autouse fixture, so real
persistence tests below read back real files under tmp_path, never this
repo's own runs/ tree.

Tests verify:
  1. gen_stage calls execute_atl with the real atl_output/psm_output
     context and the real PIM sample instance, then execute_acceleo with
     the real acceleo_output/psm_output context and execute_atl's own
     returned PSM instance.
  2. The real output model name is parsed out of the real atl_output text
     (matching atl_agent's own "create OUT : <Name> from IN : PIM;"
     convention), not guessed or hardcoded.
  3. A single generated file is returned directly; more than one is joined
     with each file's own name.
  4. A real, missing output model name in the given ATL source raises a
     clear error before ever calling execute_atl.
  5. A real, successful run persists the intermediate PSM instance, every
     real generated file, and the joined primary artifact to disk.
  6. A real execution failure persists a real failure record before
     re-raising, rather than leaving no trace of what actually happened.
"""
import json

import pytest

from clients import execution_agent_client
from integration_runner.stages.generation import agent as generation_agent
from integration_runner.stages.generation.agent import gen_stage

_ATL_SOURCE = (
    "-- @nsURI PIM=pimMM=http://www.mddoai.com/mddoai/metamodel/pim\n"
    "-- @nsURI GitLabMM=gitlabMM=http://www.mddoai.com/mddoai/metamodel/gitlab\n\n"
    "module pim2gitlabmodel;\n"
    "create OUT : GitLabMM from IN : PIM;\n"
)


@pytest.fixture(autouse=True)
def _pim_sample_fixture(tmp_path, monkeypatch):
    fixture_path = tmp_path / "input.pimmm"
    fixture_path.write_text("<pimMM:Pipeline/>", encoding="utf-8")
    monkeypatch.setattr(generation_agent, "PIM_SAMPLE_INSTANCE_PATH", fixture_path)


def test_gen_stage_runs_the_real_atl_then_the_real_acceleo(monkeypatch):
    monkeypatch.setattr(execution_agent_client, "execute_atl", lambda *a, **k: "<gitlabMM:Pipeline/>")
    monkeypatch.setattr(execution_agent_client, "execute_acceleo", lambda *a, **k: {".gitlab-ci.yml": "stages: []\n"})

    output, extra = gen_stage({
        "atl_output": _ATL_SOURCE, "acceleo_output": "[module x]", "psm_output": "<ecore/>",
    })

    assert output == "stages: []\n"
    assert extra["psm_instance"] == "<gitlabMM:Pipeline/>"
    assert extra["generated_files"] == {".gitlab-ci.yml": "stages: []\n"}


def test_gen_stage_calls_execute_atl_with_the_real_context_and_pim_sample(monkeypatch):
    calls = []
    monkeypatch.setattr(
        execution_agent_client, "execute_atl",
        lambda *a, **k: (calls.append((a, k)), "<gitlabMM:Pipeline/>")[1],
    )
    monkeypatch.setattr(execution_agent_client, "execute_acceleo", lambda *a, **k: {".gitlab-ci.yml": "stages: []\n"})

    gen_stage({"atl_output": _ATL_SOURCE, "acceleo_output": "[module x]", "psm_output": "<ecore/>"})

    args, kwargs = calls[0]
    assert args[0] == _ATL_SOURCE
    assert args[1] == "<pimMM:Pipeline/>"
    assert args[2] == "<ecore/>"
    assert args[3] == "GitLabMM"


def test_gen_stage_calls_execute_acceleo_with_the_real_psm_instance_execute_atl_produced(monkeypatch):
    monkeypatch.setattr(execution_agent_client, "execute_atl", lambda *a, **k: "<gitlabMM:Pipeline real-output/>")
    calls = []
    monkeypatch.setattr(
        execution_agent_client, "execute_acceleo",
        lambda *a, **k: (calls.append((a, k)), {".gitlab-ci.yml": "stages: []\n"})[1],
    )

    gen_stage({"atl_output": _ATL_SOURCE, "acceleo_output": "[module x]", "psm_output": "<ecore/>"})

    args, kwargs = calls[0]
    assert args[0] == "[module x]"
    assert args[1] == "<gitlabMM:Pipeline real-output/>"
    assert args[2] == "<ecore/>"


def test_gen_stage_joins_more_than_one_generated_file_with_their_own_names(monkeypatch):
    monkeypatch.setattr(execution_agent_client, "execute_atl", lambda *a, **k: "<gitlabMM:Pipeline/>")
    monkeypatch.setattr(
        execution_agent_client, "execute_acceleo",
        lambda *a, **k: {"a.yml": "a: 1\n", "b.yml": "b: 2\n"},
    )

    output, extra = gen_stage({"atl_output": _ATL_SOURCE, "acceleo_output": "[module x]", "psm_output": "<ecore/>"})

    assert "# a.yml" in output and "a: 1" in output
    assert "# b.yml" in output and "b: 2" in output
    assert extra["generated_files"] == {"a.yml": "a: 1\n", "b.yml": "b: 2\n"}


def test_gen_stage_raises_a_clear_error_when_the_atl_source_has_no_real_output_model_name():
    with pytest.raises(ValueError, match="output model name"):
        gen_stage({"atl_output": "not real ATL source", "acceleo_output": "[module x]", "psm_output": "<ecore/>"})


def test_gen_stage_ignores_a_commented_out_output_model_name(monkeypatch):
    # A header comment mentioning the "create OUT : X from IN : PIM;"
    # convention, above the one real declaration this ATL source actually
    # uses, must not be mistaken for it.
    atl_source = (
        "-- e.g. create OUT : WrongName from IN : PIM;\n"
        "module pim2gitlabmodel;\n"
        "create OUT : GitLabMM from IN : PIM;\n"
    )
    calls = []
    monkeypatch.setattr(
        execution_agent_client, "execute_atl",
        lambda *a, **k: (calls.append(a), "<gitlabMM:Pipeline/>")[1],
    )
    monkeypatch.setattr(execution_agent_client, "execute_acceleo", lambda *a, **k: {".gitlab-ci.yml": "stages: []\n"})

    gen_stage({"atl_output": atl_source, "acceleo_output": "[module x]", "psm_output": "<ecore/>"})

    assert calls[0][3] == "GitLabMM"


def test_gen_stage_raises_when_the_only_output_model_name_is_commented_out():
    atl_source = "-- create OUT : GitLabMM from IN : PIM;\nmodule pim2gitlabmodel;\n"
    with pytest.raises(ValueError, match="output model name"):
        gen_stage({"atl_output": atl_source, "acceleo_output": "[module x]", "psm_output": "<ecore/>"})


def test_gen_stage_persists_the_psm_instance_every_generated_file_and_the_primary_artifact(monkeypatch, tmp_path):
    monkeypatch.setattr(execution_agent_client, "execute_atl", lambda *a, **k: "<gitlabMM:Pipeline real-output/>")
    monkeypatch.setattr(execution_agent_client, "execute_acceleo", lambda *a, **k: {".gitlab-ci.yml": "stages: []\n"})

    gen_stage({
        "atl_output": _ATL_SOURCE, "acceleo_output": "[module x]", "psm_output": "<ecore/>", "run_id": "run-1",
    })

    attempt_dir = tmp_path / "runs" / "run-1" / "generation" / "attempt_1"
    assert (attempt_dir / "psm_instance.xmi").read_text(encoding="utf-8") == "<gitlabMM:Pipeline real-output/>"
    assert (attempt_dir / ".gitlab-ci.yml").read_text(encoding="utf-8") == "stages: []\n"
    assert (attempt_dir / "output.yaml").read_text(encoding="utf-8") == "stages: []\n"
    result = json.loads((attempt_dir / "result.json").read_text(encoding="utf-8"))
    assert result["valid"] is True
    manifest = json.loads((tmp_path / "runs" / "run-1" / "manifest.json").read_text(encoding="utf-8"))
    assert manifest[-1] == {"run_id": "run-1", "stage": "generation", "attempt_n": 1, "valid": True,
                             "timestamp": manifest[-1]["timestamp"]}


def test_gen_stage_persists_a_generated_file_nested_in_its_own_subdirectory(monkeypatch, tmp_path):
    # A real platform's own generated file name can carry a subdirectory
    # (e.g. Woodpecker's real ".woodpecker/pipeline.yaml" convention) -
    # confirmed for real: writing straight to attempt_dir/name failed with
    # "No such file or directory" since nothing had created
    # attempt_dir/.woodpecker/ yet.
    monkeypatch.setattr(execution_agent_client, "execute_atl", lambda *a, **k: "<gitlabMM:Pipeline/>")
    monkeypatch.setattr(
        execution_agent_client, "execute_acceleo",
        lambda *a, **k: {".woodpecker/pipeline.yaml": "steps: []\n"},
    )

    gen_stage({
        "atl_output": _ATL_SOURCE, "acceleo_output": "[module x]", "psm_output": "<ecore/>", "run_id": "run-1",
    })

    attempt_dir = tmp_path / "runs" / "run-1" / "generation" / "attempt_1"
    assert (attempt_dir / ".woodpecker" / "pipeline.yaml").read_text(encoding="utf-8") == "steps: []\n"


def test_gen_stage_rejects_a_generated_file_name_that_escapes_the_attempt_directory(monkeypatch):
    monkeypatch.setattr(execution_agent_client, "execute_atl", lambda *a, **k: "<gitlabMM:Pipeline/>")
    monkeypatch.setattr(
        execution_agent_client, "execute_acceleo",
        lambda *a, **k: {"../../escape.yaml": "stages: []\n"},
    )

    with pytest.raises(ValueError, match="escapes the attempt directory"):
        gen_stage({
            "atl_output": _ATL_SOURCE, "acceleo_output": "[module x]", "psm_output": "<ecore/>", "run_id": "run-1",
        })


def test_gen_stage_persists_a_real_atl_failure_labeled_by_phase(monkeypatch, tmp_path):
    # A bare "execution-agent unreachable" tells a human nothing about
    # which real phase broke - the message itself must say ATL, since
    # pipeline.py's own call_failed event carries nothing else (see
    # gen_stage()'s own comment).
    monkeypatch.setattr(
        execution_agent_client, "execute_atl",
        lambda *a, **k: (_ for _ in ()).throw(RuntimeError("execution-agent unreachable")),
    )

    with pytest.raises(RuntimeError, match="ATL execution failed: execution-agent unreachable"):
        gen_stage({
            "atl_output": _ATL_SOURCE, "acceleo_output": "[module x]", "psm_output": "<ecore/>", "run_id": "run-1",
        })

    attempt_dir = tmp_path / "runs" / "run-1" / "generation" / "attempt_1"
    assert (attempt_dir / "error.txt").read_text(encoding="utf-8") == "ATL execution failed: execution-agent unreachable"
    result = json.loads((attempt_dir / "result.json").read_text(encoding="utf-8"))
    assert result["valid"] is False
    assert "ATL execution failed" in result["issues"][0]["message"]


def test_gen_stage_persists_a_real_acceleo_failure_labeled_by_phase_and_keeps_the_psm_instance(monkeypatch, tmp_path):
    # Acceleo failing after ATL already succeeded is a real, distinct case:
    # the message must say Acceleo (not ATL), and the real psm_instance ATL
    # already produced - genuinely not the problem - must survive on disk
    # for a human debugging why Acceleo failed, not be silently discarded.
    monkeypatch.setattr(execution_agent_client, "execute_atl", lambda *a, **k: "<gitlabMM:Pipeline real-output/>")
    monkeypatch.setattr(
        execution_agent_client, "execute_acceleo",
        lambda *a, **k: (_ for _ in ()).throw(RuntimeError("template crashed")),
    )

    with pytest.raises(RuntimeError, match="Acceleo execution failed \\(ATL succeeded\\): template crashed") as exc_info:
        gen_stage({
            "atl_output": _ATL_SOURCE, "acceleo_output": "[module x]", "psm_output": "<ecore/>", "run_id": "run-1",
        })

    # The real psm_instance also rides along on the raised exception's own
    # "extra" attribute - pipeline.py's own run_stage_async worker threads
    # this into the real call_failed event's data, so a human looking at
    # the failed run in the chat-ui (not digging through the attempt
    # directory on disk) can still see the real, valid model ATL produced.
    assert exc_info.value.extra == {"psm_instance": "<gitlabMM:Pipeline real-output/>"}
    attempt_dir = tmp_path / "runs" / "run-1" / "generation" / "attempt_1"
    assert (attempt_dir / "psm_instance.xmi").read_text(encoding="utf-8") == "<gitlabMM:Pipeline real-output/>"
    assert (attempt_dir / "error.txt").read_text(encoding="utf-8") == "Acceleo execution failed (ATL succeeded): template crashed"
    result = json.loads((attempt_dir / "result.json").read_text(encoding="utf-8"))
    assert result["valid"] is False
    assert "Acceleo execution failed" in result["issues"][0]["message"]
