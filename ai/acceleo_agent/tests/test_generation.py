"""Acceleo Generation Agent tests: this module's own responsibility is
Acceleo-specific wiring (which context values feed the prompt, how
validator-agent maps to a pass/fail check, how the real, editable prompt
config from generation_toolkit.prompt_config gets loaded and resolved) -
the generic regenerate-loop mechanics (max rounds, constraint accumulation,
code-fence stripping) are generation_toolkit's own concern, tested in its
own test suite, not re-tested here. Real LLM/validation calls are mocked
via patch.object(...), the same convention psm_agent's own tests use; only
the master example file (real GitLab generate.mtl) and the real,
git-committed generation/default.default.json prompt config are left real.
"""
from pathlib import Path
from unittest.mock import patch

from clients import ai_layer_client, validator_agent_client
from generation import generate
from prompt_paths import REFERENCE_EXAMPLE_PATH


def ok_response(content):
    return {"model": "test-model", "content": content, "tool_calls": None}


def valid_result():
    return {"valid": True, "issues": [], "duration_ms": 5, "generated_source_path": None}


def invalid_result(message="def used as a reserved template parameter name"):
    return {
        "valid": False,
        "issues": [{"severity": "ERROR", "message": message, "source": None}],
        "duration_ms": 5,
        "generated_source_path": None,
    }


def test_uses_acceleo_system_prompt():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("[module generate(...)]")) as mock_chat, \
         patch.object(validator_agent_client, "validate_acceleo", return_value=valid_result()):
        generate("<psm/>", "docs")

    messages = mock_chat.call_args.args[0]
    assert messages[0]["role"] == "system"
    assert "acceleo agent" in messages[0]["content"].lower()


def test_prompt_assembles_psm_docs_and_real_master_example():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("[module generate(...)]")), \
         patch.object(validator_agent_client, "validate_acceleo", return_value=valid_result()):
        result = generate("<psm-artifact/>", "target docs text")

    prompt = result["prompt"]
    assert prompt["psm_ecore"] == "<psm-artifact/>"
    assert prompt["platform_docs"] == "target docs text"
    # Real generate.mtl content, not a mock.
    assert prompt["acceleo_example"] == Path(REFERENCE_EXAMPLE_PATH).read_text()


def test_validation_result_is_the_real_validator_agent_response():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("[module generate(...)]")), \
         patch.object(validator_agent_client, "validate_acceleo", return_value=valid_result()) as mock_validate:
        result = generate("<psm/>", "docs", run_id="run-123")

    mock_validate.assert_called_once_with(
        "[module generate(...)]", "generate.mtl", run_id="run-123", stage=None, attempt=None, metamodel_ecore="<psm/>"
    )
    assert result["validation"] == valid_result()


def test_forwards_stage_and_attempt_for_compiled_output_nesting():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("[module generate(...)]")), \
         patch.object(validator_agent_client, "validate_acceleo", return_value=valid_result()) as mock_validate:
        generate("<psm/>", "docs", run_id="run-123", stage="acceleo", attempt="attempt_1")

    mock_validate.assert_called_once_with(
        "[module generate(...)]", "generate.mtl", run_id="run-123", stage="acceleo", attempt="attempt_1",
        metamodel_ecore="<psm/>",
    )


def test_forwards_the_real_psm_artifact_as_the_target_metamodel_to_validate():
    # The whole point of this fix: without the target platform's own real
    # PSM ecore, validator-agent can never resolve any metamodel besides
    # the ones main/'s own EMFUtils.init() hardcodes (PIM, SWArch, GitLab) -
    # every other platform's real generated template would always fail
    # with "the metamodel couldn't be resolved", regardless of how correct
    # it actually is.
    with patch.object(ai_layer_client, "chat", return_value=ok_response("[module generate(...)]")), \
         patch.object(validator_agent_client, "validate_acceleo", return_value=valid_result()) as mock_validate:
        generate("<teamcity-psm-ecore/>", "docs")

    assert mock_validate.call_args.kwargs["metamodel_ecore"] == "<teamcity-psm-ecore/>"


def test_mock_mode_does_not_forward_a_metamodel_since_it_always_targets_gitlab():
    # mock=True's own fixed _MOCK_ARTIFACT always targets the real,
    # registered GitLab metamodel URI regardless of which real platform is
    # under test (see generation.py's own _MOCK_ARTIFACT comment) - passing
    # a different platform's own ecore here would be actively wrong, not
    # just unnecessary.
    with patch.object(ai_layer_client, "chat") as mock_chat, \
         patch.object(validator_agent_client, "validate_acceleo", return_value=valid_result()) as mock_validate:
        generate("<teamcity-psm-ecore/>", "docs", mock=True)

    mock_chat.assert_not_called()
    assert mock_validate.call_args.kwargs.get("metamodel_ecore") is None


def test_regenerates_once_on_a_real_validation_failure_then_succeeds():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("[module generate(...)]")) as mock_chat, \
         patch.object(validator_agent_client, "validate_acceleo",
                       side_effect=[invalid_result("bad template"), valid_result()]):
        result = generate("<psm/>", "docs")

    assert mock_chat.call_count == 2
    assert result["rounds"] == 2
    assert "Fix: bad template" in result["prompt"]["constraints"]


def test_prior_constraints_carried_into_first_round():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("[module generate(...)]")) as mock_chat, \
         patch.object(validator_agent_client, "validate_acceleo", return_value=valid_result()):
        generate("<psm/>", "docs", constraints=["Use camelCase names"])

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "Use camelCase names" in user_content


def test_forwards_model_to_chat():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("[module generate(...)]")) as mock_chat, \
         patch.object(validator_agent_client, "validate_acceleo", return_value=valid_result()):
        generate("<psm/>", "docs", model="gemini-flash")

    assert mock_chat.call_args.kwargs.get("model") == "gemini-flash"


def test_mock_skips_the_real_llm_call_but_still_validates_and_resolves_config():
    with patch.object(ai_layer_client, "chat") as mock_chat, \
         patch.object(validator_agent_client, "validate_acceleo", return_value=valid_result()) as mock_validate:
        result = generate("<psm/>", "docs", run_id="run-123", stage="acceleo", attempt="attempt_1", mock=True)

    mock_chat.assert_not_called()
    mock_validate.assert_called_once()
    assert result["validation"] == valid_result()
    assert result["rounds"] == 1
    assert result["preset"] == "default"
    # Real config still resolved: prompt still carries the real master example.
    assert result["prompt"]["acceleo_example"] == Path(REFERENCE_EXAMPLE_PATH).read_text()
