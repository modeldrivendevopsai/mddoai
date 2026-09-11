"""PSM Generation Agent tests: this module's own responsibility is PSM-specific
wiring (which files to read, how grounding folds into the prompt, how
validator-agent maps to a pass/fail check, and now, how the real, editable
prompt config from generation_toolkit.prompt_config gets loaded and
resolved) - the generic regenerate-loop mechanics (max rounds, constraint
accumulation, code-fence stripping) are generation_toolkit's own concern,
tested in its own test suite, not re-tested here. Real LLM/grounding/
validation calls are all mocked via patch.object(..., ...), the same
convention test_comparison.py uses; only the master example file (real
githubMM.ecore) and the real, git-committed generation/default.default.json
prompt config are left real.
"""
import json
from pathlib import Path
from unittest.mock import patch

from clients import ai_layer_client, pim_agent_client, validator_agent_client
from comparison import DEFAULT_PSM_MASTER_EXAMPLE_PATH
from generation import generate


def ok_response(content):
    return {"model": "test-model", "content": content, "tool_calls": None}


def valid_result():
    return {"valid": True, "mode": "reflective", "issues": [], "duration_ms": 5, "generated_source_path": None}


def invalid_result(message="dangling reference"):
    return {
        "valid": False,
        "mode": "reflective",
        "issues": [{"severity": "ERROR", "message": message, "source": None}],
        "duration_ms": 5,
        "generated_source_path": None,
    }


def test_uses_psm_generation_system_prompt():
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")) as mock_chat, \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()):
        generate("Some new CI platform", "<pim/>", "docs")

    messages = mock_chat.call_args.args[0]
    assert messages[0]["role"] == "system"
    assert "generation agent" in messages[0]["content"].lower()


def test_prompt_assembles_docs_and_real_master_example():
    # pim_artifact is still accepted as a parameter (it still feeds grounding,
    # see test_grounding_is_folded_into_psm_docs below), but is deliberately
    # not part of the prompt itself - see generate()'s own docstring for why.
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")), \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()):
        result = generate("Some new CI platform", "<pim-artifact/>", "target docs text")

    prompt = result["prompt"]
    assert "pim_ecore" not in prompt
    assert "target docs text" in prompt["psm_docs"]
    # Real githubMM.ecore content, not a mock.
    assert prompt["psm_example"] == Path(DEFAULT_PSM_MASTER_EXAMPLE_PATH).read_text()


def test_grounding_is_folded_into_psm_docs():
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[{"category": "metamodel", "title": "Job", "content": "A unit of work."}]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")), \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()):
        result = generate("Some new CI platform", "<pim/>", "docs")

    assert "Job: A unit of work." in result["prompt"]["psm_docs"]


def test_validation_result_is_the_real_validator_agent_response():
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")), \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()) as mock_validate:
        result = generate("Some new CI platform", "<pim/>", "docs", run_id="run-123")

    mock_validate.assert_called_once_with(
        "<ecore:EPackage/>", mode="codegen", run_id="run-123", stage=None, attempt=None
    )
    assert result["validation"] == valid_result()


def test_forwards_stage_and_attempt_for_compiled_output_nesting():
    # Every regeneration round shares the same stage/attempt: they all
    # belong to one attempt of the psm stage, each round just gets its own
    # uniquely-named subfolder underneath it (see generation.py's own
    # _validate() docstring).
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")), \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()) as mock_validate:
        generate("Some new CI platform", "<pim/>", "docs", run_id="run-123", stage="psm", attempt="attempt_1")

    mock_validate.assert_called_once_with(
        "<ecore:EPackage/>", mode="codegen", run_id="run-123", stage="psm", attempt="attempt_1"
    )


def test_regenerates_once_on_a_real_validation_failure_then_succeeds():
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")) as mock_chat, \
         patch.object(validator_agent_client, "validate_ecore",
                       side_effect=[invalid_result("missing RetryPolicy"), valid_result()]):
        result = generate("Some new CI platform", "<pim/>", "docs")

    assert mock_chat.call_count == 2
    assert result["rounds"] == 2
    assert "Fix: missing RetryPolicy" in result["prompt"]["constraints"]


def test_prior_constraints_carried_into_first_round():
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")) as mock_chat, \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()):
        generate("Some new CI platform", "<pim/>", "docs", constraints=["Use camelCase names"])

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "Use camelCase names" in user_content


def test_forwards_model_to_chat():
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")) as mock_chat, \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()):
        generate("Some new CI platform", "<pim/>", "docs", model="gemini-flash")

    assert mock_chat.call_args.kwargs["model"] == "gemini-flash"


def test_uses_the_default_preset_for_an_unknown_platform():
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")), \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()):
        result = generate("A brand new platform nobody has a preset for", "<pim/>", "docs")

    assert result["preset"] == "default"
    # Only a live, human-edited save stamps a real "_version" (see
    # generation_toolkit.prompt_config.storage) - the shipped
    # default.default.json alone has none. This repo's own real dev stack
    # bind-mounts this exact directory read-write (ai/docker-compose.yml),
    # so a real live edit through the running service can legitimately
    # leave a real live config sitting next to the shipped one - this
    # assertion checks the real type, not a specific value, so it stays
    # correct either way instead of assuming the shared directory is
    # pristine.
    assert result["prompt_version"] is None or isinstance(result["prompt_version"], str)


def test_shipped_learned_constraints_are_applied_even_with_no_run_level_constraints():
    # The generic default (ai/psm_agent/prompts/generation/default.default.json)
    # ships with real, already-proven constraints (ported from the real
    # ai-research experiments) - they should apply to every run of this
    # preset, not just a run that also supplies its own live corrections.
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")) as mock_chat, \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()):
        generate("Some new CI platform", "<pim/>", "docs")

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "valid Java identifier" in user_content


def test_mock_skips_the_real_llm_call_and_grounding_but_still_validates():
    # mock=True's whole point: fast, free local iteration on the prompt
    # config itself, so the two slow/billed calls (grounding, the real LLM
    # call) must never happen, while the real config resolution and the
    # real validator-agent call both still do.
    with patch.object(pim_agent_client, "concepts") as mock_concepts, \
         patch.object(pim_agent_client, "ground") as mock_ground, \
         patch.object(ai_layer_client, "chat") as mock_chat, \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()) as mock_validate:
        result = generate("Some new CI platform", "<pim-artifact/>", "target docs text", mock=True)

    mock_concepts.assert_not_called()
    mock_ground.assert_not_called()
    mock_chat.assert_not_called()
    mock_validate.assert_called_once()
    assert result["validation"] == valid_result()
    assert result["rounds"] == 1
    # Real config resolution still happened - same real master example and
    # real learned constraints a non-mock call would resolve.
    assert result["prompt"]["psm_example"] == Path(DEFAULT_PSM_MASTER_EXAMPLE_PATH).read_text()
    assert "valid Java identifier" in result["prompt"]["constraints"]
    assert result["preset"] == "default"
    assert "ecore:EPackage" in result["artifact"]


def test_generate_resolves_a_file_attachment_the_human_uploaded(
    isolated_prompt_config_dir, isolated_attachment_uploads_dir
):
    # Real regression test: a "file" attachment referencing a human's own
    # upload is already validated as resolvable by routes/prompt_config.py's
    # own save/check-references (both resolve against comparison.files_root(),
    # which includes ATTACHMENT_UPLOADS_DIR) - a real generate() call must
    # resolve that same attachment too, not only the editor. Before
    # comparison.files_root() existed, generate() resolved "file" attachments
    # against META_MODELS_DIR alone, so this exact config would have raised
    # AttachmentFileError here instead of succeeding.
    (isolated_attachment_uploads_dir / "custom-guidance.md").write_text(
        "Always emit camelCase attribute names.", encoding="utf-8"
    )
    config = {
        "attachments": [
            {"id": "system", "name": "System prompt", "type": "text", "content": "You are the psm generation agent."},
            {"id": "custom", "name": "Custom guidance", "type": "file", "path": "custom-guidance.md"},
        ]
    }
    directory = isolated_prompt_config_dir / "generation"
    directory.mkdir(parents=True)
    (directory / "default.default.json").write_text(json.dumps(config), encoding="utf-8")

    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")), \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()):
        result = generate("Some new CI platform", "<pim-artifact/>", "target docs text")

    assert result["prompt"]["custom"] == "Always emit camelCase attribute names."
