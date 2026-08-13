"""
stage_agents.py unit tests: the six stage agents (docs/pim/psm/atl/acceleo/
generation), their prompts, and the stage_agents lookup dict. No real API
calls — orchestrator.chat / orchestrator.httpx are mocked (stage_agents.py
calls orchestrator.chat()/fetch_documentation(), it has no HTTP client of its
own, see stage_agents.py's own module docstring for why).

Tests verify:
  1. Each placeholder agent (pim/psm/atl/acceleo/generation) calls chat() with
     its own stage-specific system prompt, forwards the caller's chosen model
     (or None), and returns the response's "content" field.
  2. psm_agent prefers pim_output, falling back to docs_output, then
     platform_description; pim_agent prefers docs_output, falling back to
     platform_description — the same "prefer the real prior stage's real
     output, fall back for a direct/test call" pattern for both.
  3. docs_agent (the one real agent) calls fetch_documentation, folds
     constraints into retrieval's own hint parameter (an explicit hint
     overrides them), raises when the crawl found essentially nothing useful
     (low confidence or zero successful pages), and short-circuits to canned
     output without calling retrieval at all when ORCHESTRATOR_STUB_DOCS is
     set or the per-run context["mock"] flag is passed.
  4. stage_agents maps every stage name to its real agent function.
"""
from unittest.mock import patch

import pytest

import orchestrator
import serialization_agent
import stage_agents
from helpers import _fake_fetch_response, ok_response


def test_psm_agent_uses_psm_system_prompt_and_platform_description():
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")) as mock_chat:
        result = stage_agents.psm_agent({"platform_description": "A GitLab CI platform"})

    assert result == "PSM description"
    messages = mock_chat.call_args.args[0]
    assert messages[0]["role"] == "system"
    assert "PSM" in messages[0]["content"]
    assert messages[1]["content"].startswith("A GitLab CI platform")


def test_psm_agent_forwards_the_chosen_model_from_context():
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")) as mock_chat:
        stage_agents.psm_agent({"platform_description": "A GitLab CI platform", "model": "gemini-flash"})

    assert mock_chat.call_args.kwargs["model"] == "gemini-flash"


def test_psm_agent_passes_none_model_when_none_chosen():
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")) as mock_chat:
        stage_agents.psm_agent({"platform_description": "A GitLab CI platform"})

    assert mock_chat.call_args.kwargs["model"] is None


def test_atl_agent_uses_atl_system_prompt_and_psm_output():
    with patch.object(orchestrator, "chat", return_value=ok_response("ATL rules")) as mock_chat:
        result = stage_agents.atl_agent({"psm_output": "some PSM output"})

    assert result == "ATL rules"
    messages = mock_chat.call_args.args[0]
    assert "ATL" in messages[0]["content"]
    assert messages[1]["content"].startswith("some PSM output")


def test_acceleo_agent_uses_acceleo_system_prompt_and_atl_output():
    with patch.object(orchestrator, "chat", return_value=ok_response("Acceleo template")) as mock_chat:
        result = stage_agents.acceleo_agent({"atl_output": "some ATL output"})

    assert result == "Acceleo template"
    messages = mock_chat.call_args.args[0]
    assert "Acceleo" in messages[0]["content"]
    assert messages[1]["content"].startswith("some ATL output")


def test_gen_agent_combines_all_previous_outputs():
    with patch.object(orchestrator, "chat", return_value=ok_response("Final summary")) as mock_chat:
        result = stage_agents.gen_agent({
            "psm_output": "psm out",
            "atl_output": "atl out",
            "acceleo_output": "acceleo out",
        })

    assert result == "Final summary"
    messages = mock_chat.call_args.args[0]
    user_content = messages[1]["content"]
    assert "psm out" in user_content
    assert "atl out" in user_content
    assert "acceleo out" in user_content


def test_pim_agent_uses_pim_system_prompt_and_docs_output():
    with patch.object(orchestrator, "chat", return_value=ok_response("PIM description")) as mock_chat:
        result = stage_agents.pim_agent({"docs_output": "Fetched 3 pages about GitLab CI."})

    assert result == "PIM description"
    messages = mock_chat.call_args.args[0]
    assert messages[0]["role"] == "system"
    assert "PIM" in messages[0]["content"]
    assert messages[1]["content"].startswith("Fetched 3 pages about GitLab CI.")


def test_pim_agent_falls_back_to_platform_description_without_docs_output():
    with patch.object(orchestrator, "chat", return_value=ok_response("PIM description")) as mock_chat:
        stage_agents.pim_agent({"platform_description": "A GitLab CI platform"})

    assert mock_chat.call_args.args[0][1]["content"].startswith("A GitLab CI platform")


def test_psm_agent_prefers_pim_output_over_docs_output():
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")) as mock_chat:
        stage_agents.psm_agent({"pim_output": "PIM: jobs/stages/triggers", "docs_output": "raw docs"})

    assert mock_chat.call_args.args[0][1]["content"].startswith("PIM: jobs/stages/triggers")


def test_psm_agent_falls_back_to_docs_output_without_pim_output():
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")) as mock_chat:
        stage_agents.psm_agent({"docs_output": "raw docs"})

    assert mock_chat.call_args.args[0][1]["content"].startswith("raw docs")


def test_docs_agent_returns_formatted_content_on_success():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response(confidence=0.75)
        result = stage_agents.docs_agent({"seed_url": "https://example.com/docs"})

    assert "Fetched 1 page(s) from https://example.com/docs, confidence 0.75" in result
    assert "Some real content." in result


def test_docs_agent_folds_constraints_into_hint():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        stage_agents.docs_agent({
            "seed_url": "https://example.com/docs",
            "constraints": {"docs": ["focus on the yaml reference", "skip tutorials"]},
        })

    sent = mock_httpx.post.call_args.kwargs["json"]
    assert sent["hint"] == "focus on the yaml reference skip tutorials"


def test_docs_agent_explicit_hint_overrides_constraints():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        stage_agents.docs_agent({
            "seed_url": "https://example.com/docs",
            "hint": "the human's exact override",
            "constraints": {"docs": ["an older, lower-priority correction"]},
        })

    sent = mock_httpx.post.call_args.kwargs["json"]
    assert sent["hint"] == "the human's exact override"


def test_docs_agent_raises_when_confidence_below_floor():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response(confidence=0.01)
        with pytest.raises(RuntimeError, match="essentially nothing useful"):
            stage_agents.docs_agent({"seed_url": "https://example.com/docs"})


def test_docs_agent_raises_when_no_pages_succeeded():
    failed_page = [{"url": "https://example.com/docs", "success": False, "status_code": 404, "markdown": "", "links": []}]
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response(pages=failed_page, confidence=0.9)
        with pytest.raises(RuntimeError, match="essentially nothing useful"):
            stage_agents.docs_agent({"seed_url": "https://example.com/docs"})


def test_docs_agent_returns_stub_output_without_calling_retrieval_when_flag_set():
    with patch.object(stage_agents, "_STUB_DOCS", True), patch.object(orchestrator, "httpx") as mock_httpx:
        result = stage_agents.docs_agent({"seed_url": "https://example.com/docs"})

    mock_httpx.post.assert_not_called()
    assert "https://example.com/docs" in result
    assert "MOCKED" in result


def test_docs_agent_returns_stub_output_without_calling_retrieval_when_mock_context_flag_set():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        result = stage_agents.docs_agent({"seed_url": "https://example.com/docs", "mock": True})

    mock_httpx.post.assert_not_called()
    assert "https://example.com/docs" in result
    assert "MOCKED" in result


def test_docs_agent_crawls_for_real_when_mock_context_flag_is_falsy():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        stage_agents.docs_agent({"seed_url": "https://example.com/docs", "mock": False})

    mock_httpx.post.assert_called_once()


def test_stage_agents_maps_stage_names_to_agent_functions():
    assert stage_agents.stage_agents == {
        "docs": stage_agents.docs_agent,
        "serialization": serialization_agent.serialization_agent,
        "pim": stage_agents.pim_agent,
        "psm": stage_agents.psm_agent,
        "atl": stage_agents.atl_agent,
        "acceleo": stage_agents.acceleo_agent,
        "generation": stage_agents.gen_agent,
    }
