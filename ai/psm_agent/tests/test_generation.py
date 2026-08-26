"""PSM Generation Agent tests: this module's own responsibility is PSM-specific
wiring (which files to read, how grounding folds into the prompt, how
validator-agent maps to a pass/fail check) - the generic regenerate-loop
mechanics (max rounds, constraint accumulation, code-fence stripping) are
generation_toolkit's own concern, tested in its own test suite, not
re-tested here. Real LLM/grounding/validation calls are all mocked via
patch.object(..., ...), the same convention test_comparison.py uses; only
the master example file (real githubMM.ecore) is left real.
"""
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
        generate("<pim/>", "docs")

    messages = mock_chat.call_args.args[0]
    assert messages[0]["role"] == "system"
    assert "generation agent" in messages[0]["content"].lower()


def test_prompt_assembles_pim_artifact_docs_and_real_master_example():
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")), \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()):
        result = generate("<pim-artifact/>", "target docs text")

    prompt = result["prompt"]
    assert prompt["pim_ecore"] == "<pim-artifact/>"
    assert "target docs text" in prompt["psm_docs"]
    # Real githubMM.ecore content, not a mock.
    assert prompt["psm_example"] == Path(DEFAULT_PSM_MASTER_EXAMPLE_PATH).read_text()


def test_grounding_is_folded_into_psm_docs():
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[{"category": "metamodel", "title": "Job", "content": "A unit of work."}]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")), \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()):
        result = generate("<pim/>", "docs")

    assert "Job: A unit of work." in result["prompt"]["psm_docs"]


def test_validation_result_is_the_real_validator_agent_response():
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")), \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()) as mock_validate:
        result = generate("<pim/>", "docs")

    mock_validate.assert_called_once_with("<ecore:EPackage/>", mode="reflective")
    assert result["validation"] == valid_result()


def test_regenerates_once_on_a_real_validation_failure_then_succeeds():
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")) as mock_chat, \
         patch.object(validator_agent_client, "validate_ecore",
                       side_effect=[invalid_result("missing RetryPolicy"), valid_result()]):
        result = generate("<pim/>", "docs")

    assert mock_chat.call_count == 2
    assert result["rounds"] == 2
    assert "Fix: missing RetryPolicy" in result["prompt"]["constraints"]


def test_prior_constraints_carried_into_first_round():
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")) as mock_chat, \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()):
        generate("<pim/>", "docs", constraints=["Use camelCase names"])

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "Use camelCase names" in user_content


def test_forwards_model_to_chat():
    with patch.object(pim_agent_client, "concepts", return_value={"Job": ["Job"]}), \
         patch.object(pim_agent_client, "ground", return_value=[]), \
         patch.object(ai_layer_client, "chat", return_value=ok_response("<ecore:EPackage/>")) as mock_chat, \
         patch.object(validator_agent_client, "validate_ecore", return_value=valid_result()):
        generate("<pim/>", "docs", model="gemini-flash")

    assert mock_chat.call_args.kwargs["model"] == "gemini-flash"
