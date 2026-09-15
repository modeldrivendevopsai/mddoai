"""integration_runner/stages/generation/agent.py's real (non-placeholder)
unit tests: gen_stage actually runs this run's own real, generated ATL and
Acceleo source against a real PIM model instance, via execution_agent -
no LLM call of its own any more. No real HTTP call -
clients/execution_agent_client.execute_atl/execute_acceleo are mocked.
PIM_SAMPLE_INSTANCE_PATH is redirected to a throwaway fixture file per test
(see _pim_sample_fixture below), so these tests never depend on this
repo's real main/ test-resources tree.

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
"""
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

    result = gen_stage({
        "atl_output": _ATL_SOURCE, "acceleo_output": "[module x]", "psm_output": "<ecore/>",
    })

    assert result == "stages: []\n"


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

    result = gen_stage({"atl_output": _ATL_SOURCE, "acceleo_output": "[module x]", "psm_output": "<ecore/>"})

    assert "# a.yml" in result and "a: 1" in result
    assert "# b.yml" in result and "b: 2" in result


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
