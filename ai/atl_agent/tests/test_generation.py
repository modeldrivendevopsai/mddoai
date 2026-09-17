"""ATL Generation Agent tests: this module's own responsibility is
ATL-specific wiring (which context values feed the prompt, how
validator-agent maps to a pass/fail check, how the real, editable prompt
config from generation_toolkit.prompt_config gets loaded and resolved) -
the generic regenerate-loop mechanics (max rounds, constraint accumulation,
code-fence stripping) are generation_toolkit's own concern, tested in its
own test suite, not re-tested here. Real LLM/validation calls are mocked
via patch.object(...), the same convention psm_agent's own tests use; only
the master example file (real pim2gitlabmodel.atl) and the real,
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


def invalid_result(message="reserved keyword used as target variable name"):
    return {
        "valid": False,
        "issues": [{"severity": "ERROR", "message": message, "source": None}],
        "duration_ms": 5,
        "generated_source_path": None,
    }


def test_uses_atl_system_prompt():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("module m; ...")) as mock_chat, \
         patch.object(validator_agent_client, "validate_atl", return_value=valid_result()):
        generate("<pim/>", "<psm/>")

    messages = mock_chat.call_args.args[0]
    assert messages[0]["role"] == "system"
    assert "atl transformation agent" in messages[0]["content"].lower()


def test_prompt_assembles_pim_psm_and_real_master_example():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("module m; ...")), \
         patch.object(validator_agent_client, "validate_atl", return_value=valid_result()):
        result = generate("<pim-artifact/>", "<psm-artifact/>")

    prompt = result["prompt"]
    assert prompt["pim_ecore"] == "<pim-artifact/>"
    assert prompt["psm_ecore"] == "<psm-artifact/>"
    # Real pim2gitlabmodel.atl content, not a mock.
    assert prompt["atl_example"] == Path(REFERENCE_EXAMPLE_PATH).read_text()


def test_validation_result_is_the_real_validator_agent_response():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("module m; ...")), \
         patch.object(validator_agent_client, "validate_atl", return_value=valid_result()) as mock_validate:
        result = generate("<pim/>", "<psm/>", run_id="run-123")

    mock_validate.assert_called_once_with(
        "module m; ...", "generated.atl", run_id="run-123", stage=None, attempt=None, metamodel_ecore="<psm/>"
    )
    assert result["validation"] == valid_result()


def test_forwards_stage_and_attempt_for_compiled_output_nesting():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("module m; ...")), \
         patch.object(validator_agent_client, "validate_atl", return_value=valid_result()) as mock_validate:
        generate("<pim/>", "<psm/>", run_id="run-123", stage="atl", attempt="attempt_1")

    mock_validate.assert_called_once_with(
        "module m; ...", "generated.atl", run_id="run-123", stage="atl", attempt="attempt_1", metamodel_ecore="<psm/>"
    )


def test_forwards_the_real_psm_artifact_as_the_target_metamodel_to_validate():
    # The whole point of this fix: without the target platform's own real
    # PSM ecore, validator-agent can never actually RUN the generated ATL
    # against a real model instance, only compile it - a real runtime-only
    # failure (e.g. instantiating an abstract classifier) would then only
    # ever surface later, at the pipeline's own final execution stage, with
    # no retries left (see AtlValidator.java's own execution smoke test).
    with patch.object(ai_layer_client, "chat", return_value=ok_response("module m; ...")), \
         patch.object(validator_agent_client, "validate_atl", return_value=valid_result()) as mock_validate:
        generate("<pim/>", "<real-psm-ecore/>")

    assert mock_validate.call_args.kwargs["metamodel_ecore"] == "<real-psm-ecore/>"


def test_mock_mode_does_not_forward_a_metamodel_since_it_always_targets_a_fixed_fake():
    # mock=True's own fixed _MOCK_ARTIFACT always targets a fictional "PSM"
    # package, never this run's own real psm_artifact (see generation.py's
    # own _MOCK_ARTIFACT) - forwarding a real, unrelated target metamodel
    # alongside it would make the real execution smoke test run the mock
    # ATL against the wrong metamodel entirely.
    with patch.object(ai_layer_client, "chat") as mock_chat, \
         patch.object(validator_agent_client, "validate_atl", return_value=valid_result()) as mock_validate:
        generate("<pim/>", "<real-psm-ecore/>", mock=True)

    mock_chat.assert_not_called()
    assert mock_validate.call_args.kwargs.get("metamodel_ecore") is None


def test_regenerates_once_on_a_real_validation_failure_then_succeeds():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("module m; ...")) as mock_chat, \
         patch.object(validator_agent_client, "validate_atl",
                       side_effect=[invalid_result("dangling reference"), valid_result()]):
        result = generate("<pim/>", "<psm/>")

    assert mock_chat.call_count == 2
    assert result["rounds"] == 2
    assert "Fix: dangling reference" in result["prompt"]["constraints"]
    # round_constraints is what lets integration_runner's own atl_stage
    # persist this real, per-run fix as a real constraint, so the NEXT
    # external retry builds on it instead of rediscovering it from scratch
    # (see pipeline.py's own _persist_round_constraints()). A membership
    # check, not exact equality: it also carries this config's own real,
    # already-promoted learned_constraints (a separate, permanent concept),
    # which real prompt configs already have some of.
    assert "Fix: dangling reference" in result["round_constraints"]


def test_prior_constraints_carried_into_first_round():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("module m; ...")) as mock_chat, \
         patch.object(validator_agent_client, "validate_atl", return_value=valid_result()):
        generate("<pim/>", "<psm/>", constraints=["Use camelCase names"])

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "Use camelCase names" in user_content


def test_forwards_model_to_chat():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("module m; ...")) as mock_chat, \
         patch.object(validator_agent_client, "validate_atl", return_value=valid_result()):
        generate("<pim/>", "<psm/>", model="gemini-flash")

    assert mock_chat.call_args.kwargs.get("model") == "gemini-flash"


def test_generation_uses_zero_temperature():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("module m; ...")) as mock_chat, \
         patch.object(validator_agent_client, "validate_atl", return_value=valid_result()):
        generate("<pim/>", "<psm/>")

    assert mock_chat.call_args.kwargs.get("temperature") == 0


def test_mock_skips_the_real_llm_call_but_still_validates_and_resolves_config():
    with patch.object(ai_layer_client, "chat") as mock_chat, \
         patch.object(validator_agent_client, "validate_atl", return_value=valid_result()) as mock_validate:
        result = generate("<pim/>", "<psm/>", run_id="run-123", stage="atl", attempt="attempt_1", mock=True)

    mock_chat.assert_not_called()
    mock_validate.assert_called_once()
    assert result["validation"] == valid_result()
    assert result["rounds"] == 1
    # Real config still resolved: prompt still carries the real master example.
    assert result["prompt"]["atl_example"] == Path(REFERENCE_EXAMPLE_PATH).read_text()
    # Present even in mock mode (a real list, not missing) - its exact
    # content here is just the config's own real, already-promoted
    # learned_constraints, a separate concern this test isn't about.
    assert isinstance(result["round_constraints"], list)
