"""
Orchestrator unit tests. No real API calls — orchestrator.chat is mocked.

Module layout under test:
  orchestrator.py    the pipeline state machine (no knowledge of tool-calling
                      at all; record_event() reacts via a blank set_reactor()
                      hook someone else fills in).
  tool_calling.py     a generic, reusable LLM tool-calling reply engine, zero
                      knowledge of MDDOAI/pipelines/stages.
  pipeline_tools.py   MDDOAI's actual system prompt and its 7 declared Tools
                      (each bundling a real orchestrator.py function).
  assistant.py        react_to_event()/nudge(), composing the three above.

This file wires orchestrator.set_reactor(assistant.react_to_event) itself,
explicitly, near the top — it is NOT a side effect of importing assistant.py.

Tests verify:
  1. is_good_enough() rejects empty/error-marker responses and accepts good ones.
  2. The stage agents (psm/atl/acceleo/generation) call chat() with a stage-specific
     system prompt and return the response's "content" field.
  3. Orchestrator.run_stage() looks up the right agent, validates its output, and
     reports the current stage.
  4. Orchestrator.advance_stage()/review() move through STAGES and handle approval
     vs. rejection (constraint recording) correctly.
  5. run_stage()/rerun() pick up constraints recorded via add_constraint() since
     the last run — verifying corrections are actually threaded into the agent's
     prompt, not just stored and left unused.
  6. review() on approval starts the next stage running in the background (not just
     advancing the pointer), threading the approved stage's output into the next
     stage's context under the right f"{stage_id}_output" key, and accumulating
     outputs across approvals so the final generation stage sees all three. Because
     the run happens on a background thread, every mock that patches orchestrator.chat
     for one of these calls stays active until the thread is joined (Orchestrator.
     _last_thread.join()/orchestrator.wait_for_idle()), never after the `with` block
     that installed it has already exited, otherwise the thread's real work races
     against the mock being torn down.
  7. nudge() calls chat() with TOOLS/tool_choice="auto" and the current stage baked
     into the system prompt, dispatches whatever tool call(s) the LLM returns to the
     real functions (including a multi-call add_constraint -> rerun_stage sequence
     and a start_pipeline call), reports {"tool_called", "result", "steps"} on
     success, {"tool_called": None, ...} with a clarification message when the LLM
     calls no tool, and doesn't crash on a hallucinated/unknown tool name. Any tool
     that starts a stage running does so via orchestrator.run_stage_async() (a
     background thread), the same as a REST call, so nudge() itself only ever makes
     one chat() call (the routing decision), never blocking for a stage's duration.
  8. start_pipeline() resets the pipeline (dropping prior progress/constraints) and
     starts the docs stage running fresh for a new platform description — same
     behavior POST /start uses.
  9. chat() POSTs to AI_LAYER_URL/chat with the right payload (omitting tools/
     tool_choice when not given) and returns ai-layer's parsed JSON response as a
     plain dict, unmodified.
"""
import json
import threading
from unittest.mock import MagicMock, patch

import httpx
import pytest

import assistant
import orchestrator
import pipeline_tools
import tool_calling

orchestrator.set_reactor(assistant.react_to_event)


def ok_response(content):
    """A plain chat() response: narrated/agent text, no tool calls."""
    return {"content": content, "model": "test-model", "tool_calls": None}


def tool_call(name, arguments):
    return {"function": {"name": name, "arguments": json.dumps(arguments)}}


def tool_response(tool_calls, content=None):
    return {"content": content, "model": "test-model", "tool_calls": tool_calls}


def _chat_stub(stage_outputs=None, narration="Noted."):
    """A chat() stub for any test where run_stage_async's background thread
    makes its own call_started/call_completed narration calls around the real
    stage-agent call: narration calls (system prompt mentions "MDDOAI
    Orchestrator", no tools attached) get a fixed reply; anything else is
    assumed to be a real stage agent, matched by a unique substring of its
    system prompt (see stage_outputs)."""
    stage_outputs = stage_outputs or {}

    def _respond(messages, model=None, tools=None, tool_choice=None):
        system_content = messages[0]["content"]
        if "MDDOAI Orchestrator" in system_content and not tools:
            return ok_response(narration)
        for marker, output in stage_outputs.items():
            if marker in system_content:
                return ok_response(output)
        raise AssertionError(f"unexpected chat() call, system prompt: {system_content!r}")

    return _respond


def _nudge_chat_stub(tool_calls, stage_outputs=None, nudge_content=None, narration="Noted."):
    """Like _chat_stub, plus nudge()'s own routing call (system prompt
    mentions "MDDOAI Orchestrator" WITH tools attached)."""
    base = _chat_stub(stage_outputs, narration)

    def _respond(messages, model=None, tools=None, tool_choice=None):
        system_content = messages[0]["content"]
        if "MDDOAI Orchestrator" in system_content and tools:
            return tool_response(tool_calls, content=nudge_content)
        return base(messages, model=model, tools=tools, tool_choice=tool_choice)

    return _respond


def _fake_fetch_response(pages=None, confidence=0.8):
    """A retrieval-shaped httpx response, for tests that mock orchestrator.httpx
    directly to let docs_agent's real call through the mock."""
    pages = pages or [{
        "url": "https://example.com/docs", "success": True, "status_code": 200,
        "markdown": "# Docs\nSome real content.", "links": [],
    }]
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = {
        "seed_url": "https://example.com/docs",
        "pages": pages,
        "meta": {"confidence": confidence, "pages_crawled": len(pages), "depth_reached": 1, "pending_links": []},
    }
    return resp


def _fake_page_response(url="https://example.com/docs", success=True, markdown="# Docs\nSome real content."):
    """retrieval's real POST /fetch/page returns a bare Page dict, not a
    FetchResult, distinct from _fake_fetch_response()."""
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = {"url": url, "success": success, "status_code": 200 if success else 404, "markdown": markdown, "links": []}
    return resp


def test_is_good_enough_rejects_empty():
    assert not orchestrator.is_good_enough("")
    assert not orchestrator.is_good_enough("   ")


def test_is_good_enough_rejects_error_markers():
    assert not orchestrator.is_good_enough("I cannot help with that.")
    assert not orchestrator.is_good_enough("I don't know how to do that.")
    assert not orchestrator.is_good_enough("Sorry, an error occurred.")


def test_is_good_enough_accepts_valid_response():
    assert orchestrator.is_good_enough("Here are the pipeline stages you need: build, test, deploy.")


def test_is_good_enough_accepts_technical_content_that_mentions_error_handling():
    # "error" as a bare substring must not trip the refusal check — legitimate
    # generated content (e.g. an Acceleo template) routinely discusses error
    # handling as a concept, distinct from the agent itself reporting a failure.
    content = (
        "The template defines an onDependencyFailure block that invokes the "
        "errorHandler class, catching IOError and surfacing an error code to "
        "the pipeline's error-handling policy."
    )
    assert orchestrator.is_good_enough(content)


# --- Stage-based pipeline tests ---------------------------------------------


def test_stages_order():
    assert orchestrator.STAGES == ["docs", "psm", "atl", "acceleo", "generation"]


def test_psm_agent_uses_psm_system_prompt_and_platform_description():
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")) as mock_chat:
        result = orchestrator.psm_agent({"platform_description": "A GitLab CI platform"})

    assert result == "PSM description"
    messages = mock_chat.call_args.args[0]
    assert messages[0]["role"] == "system"
    assert "PSM" in messages[0]["content"]
    assert messages[1]["content"].startswith("A GitLab CI platform")


def test_psm_agent_forwards_the_chosen_model_from_context():
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")) as mock_chat:
        orchestrator.psm_agent({"platform_description": "A GitLab CI platform", "model": "gemini-flash"})

    assert mock_chat.call_args.kwargs["model"] == "gemini-flash"


def test_psm_agent_passes_none_model_when_none_chosen():
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")) as mock_chat:
        orchestrator.psm_agent({"platform_description": "A GitLab CI platform"})

    assert mock_chat.call_args.kwargs["model"] is None


def test_atl_agent_uses_atl_system_prompt_and_psm_output():
    with patch.object(orchestrator, "chat", return_value=ok_response("ATL rules")) as mock_chat:
        result = orchestrator.atl_agent({"psm_output": "some PSM output"})

    assert result == "ATL rules"
    messages = mock_chat.call_args.args[0]
    assert "ATL" in messages[0]["content"]
    assert messages[1]["content"].startswith("some PSM output")


def test_acceleo_agent_uses_acceleo_system_prompt_and_atl_output():
    with patch.object(orchestrator, "chat", return_value=ok_response("Acceleo template")) as mock_chat:
        result = orchestrator.acceleo_agent({"atl_output": "some ATL output"})

    assert result == "Acceleo template"
    messages = mock_chat.call_args.args[0]
    assert "Acceleo" in messages[0]["content"]
    assert messages[1]["content"].startswith("some ATL output")


def test_gen_agent_combines_all_previous_outputs():
    with patch.object(orchestrator, "chat", return_value=ok_response("Final summary")) as mock_chat:
        result = orchestrator.gen_agent({
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


def test_validate_reuses_is_good_enough():
    assert orchestrator.validate("A real response.")
    assert not orchestrator.validate("")
    assert not orchestrator.validate("I cannot help with that.")


# --- docs stage / retrieval tests -----------------------------------------------


def test_fetch_documentation_posts_url_and_omits_unset_optional_params():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        orchestrator.fetch_documentation("https://example.com/docs")

    mock_httpx.post.assert_called_once_with(
        f"{orchestrator.RETRIEVAL_URL}/fetch", json={"url": "https://example.com/docs"}, timeout=180.0,
    )


def test_fetch_documentation_forwards_all_optional_params_when_given():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        orchestrator.fetch_documentation(
            "https://example.com/docs", hint="focus on syntax", exclude_urls=["https://example.com/blog"],
            max_pages=5, max_depth=2, force_refresh=True,
        )

    sent = mock_httpx.post.call_args.kwargs["json"]
    assert sent == {
        "url": "https://example.com/docs", "hint": "focus on syntax",
        "exclude_urls": ["https://example.com/blog"], "max_pages": 5, "max_depth": 2, "force_refresh": True,
    }


def test_fetch_page_posts_url_and_force_refresh():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_page_response()
        orchestrator.fetch_page("https://example.com/docs/specific-page", force_refresh=True)

    mock_httpx.post.assert_called_once_with(
        f"{orchestrator.RETRIEVAL_URL}/fetch/page",
        json={"url": "https://example.com/docs/specific-page", "force_refresh": True},
        timeout=60.0,
    )


def test_docs_agent_returns_formatted_content_on_success():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response(confidence=0.75)
        result = orchestrator.docs_agent({"seed_url": "https://example.com/docs"})

    assert "Fetched 1 page(s) from https://example.com/docs, confidence 0.75" in result
    assert "Some real content." in result


def test_docs_agent_folds_constraints_into_hint():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        orchestrator.docs_agent({
            "seed_url": "https://example.com/docs",
            "constraints": {"docs": ["focus on the yaml reference", "skip tutorials"]},
        })

    sent = mock_httpx.post.call_args.kwargs["json"]
    assert sent["hint"] == "focus on the yaml reference skip tutorials"


def test_docs_agent_explicit_hint_overrides_constraints():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response()
        orchestrator.docs_agent({
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
            orchestrator.docs_agent({"seed_url": "https://example.com/docs"})


def test_docs_agent_raises_when_no_pages_succeeded():
    failed_page = [{"url": "https://example.com/docs", "success": False, "status_code": 404, "markdown": "", "links": []}]
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response(pages=failed_page, confidence=0.9)
        with pytest.raises(RuntimeError, match="essentially nothing useful"):
            orchestrator.docs_agent({"seed_url": "https://example.com/docs"})


def test_docs_agent_returns_stub_output_without_calling_retrieval_when_flag_set():
    with patch.object(orchestrator, "_STUB_DOCS", True), patch.object(orchestrator, "httpx") as mock_httpx:
        result = orchestrator.docs_agent({"seed_url": "https://example.com/docs"})

    mock_httpx.post.assert_not_called()
    assert "https://example.com/docs" in result
    assert "STUBBED" in result


def test_start_pipeline_stores_the_chosen_model_for_the_whole_run():
    original = orchestrator._default
    try:
        with patch.object(orchestrator, "httpx") as mock_httpx:
            mock_httpx.post.return_value = _fake_fetch_response()
            orchestrator.start_pipeline("TeamCity", "https://example.com/docs", model="gemini-flash")
            assert orchestrator._default.model == "gemini-flash"
            orchestrator.wait_for_idle()
    finally:
        orchestrator._default = original


def test_start_pipeline_defaults_model_to_none():
    original = orchestrator._default
    try:
        with patch.object(orchestrator, "httpx") as mock_httpx:
            mock_httpx.post.return_value = _fake_fetch_response()
            orchestrator.start_pipeline("TeamCity", "https://example.com/docs")
            assert orchestrator._default.model is None
            orchestrator.wait_for_idle()
    finally:
        orchestrator._default = original


def test_list_providers_proxies_ai_layers_real_providers_endpoint():
    payload = [{"name": "gemini-flash", "tier": "free"}, {"name": "claude-subscription", "tier": "subscription"}]
    with patch("orchestrator.httpx.get", return_value=_fake_httpx_response_raw(payload)) as mock_get:
        result = orchestrator.list_providers()

    mock_get.assert_called_once_with(f"{orchestrator.AI_LAYER_URL}/providers", timeout=10.0)
    assert result == payload


def test_fetch_documentation_tool_returns_summary_not_raw_content():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_fetch_response(confidence=0.9)
        result = pipeline_tools._fetch_documentation_tool("https://example.com/docs")

    assert result == {
        "seed_url": "https://example.com/docs", "pages_fetched": 1, "confidence": 0.9, "pending_links": 0,
    }


def test_fetch_page_tool_returns_summary_not_raw_content():
    with patch.object(orchestrator, "httpx") as mock_httpx:
        mock_httpx.post.return_value = _fake_page_response()
        result = pipeline_tools._fetch_page_tool("https://example.com/docs/page")

    assert result == {
        "url": "https://example.com/docs", "success": True, "status_code": 200,
        "markdown_length": len("# Docs\nSome real content."),
    }


def test_stage_agents_maps_stage_names_to_agent_functions():
    assert orchestrator.stage_agents == {
        "docs": orchestrator.docs_agent,
        "psm": orchestrator.psm_agent,
        "atl": orchestrator.atl_agent,
        "acceleo": orchestrator.acceleo_agent,
        "generation": orchestrator.gen_agent,
    }


def _fast_forward_to_psm(o: "orchestrator.Orchestrator") -> None:
    """Most stage-mechanics tests below are about psm/atl/acceleo/generation
    behavior, not about the docs stage itself, so skip straight past docs by
    setting the index directly rather than mocking a real retrieval fetch."""
    o.current_stage_index = orchestrator.STAGES.index("psm")


def test_run_stage_calls_current_stage_agent_and_validates_output():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")) as mock_chat:
        result = o.run_stage({"platform_description": "A GitLab CI platform"})

    assert mock_chat.call_count == 1
    assert result == {"stage": "psm", "output": "PSM description", "valid": True}


def test_run_stage_threads_the_orchestrator_s_chosen_model_into_the_agent_s_context():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    o.model = "mistral-small"
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")) as mock_chat:
        o.run_stage({"platform_description": "A GitLab CI platform"})

    assert mock_chat.call_args.kwargs["model"] == "mistral-small"


def test_run_stage_reports_invalid_output():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    with patch.object(orchestrator, "chat", return_value=ok_response("I cannot help with that.")):
        result = o.run_stage({"platform_description": "A GitLab CI platform"})

    assert result["valid"] is False


def test_run_stage_incorporates_constraints_added_since_the_last_run():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM v1")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    o.add_constraint("psm", "Use kebab-case job names")

    with patch.object(orchestrator, "chat", return_value=ok_response("PSM v2")) as mock_chat:
        o.run_stage({"platform_description": "A GitLab CI platform"})

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "Use kebab-case job names" in user_content


def test_rerun_replays_the_last_context_and_picks_up_new_constraints():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM v1")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    o.add_constraint("psm", "Include a lint stage")

    # "PSM (Platform-Specific Model) agent" (not just "...Model)") is the
    # marker: the orchestrator's OWN narration prompt also lists every stage
    # by its "PSM (Platform-Specific Model)" description, so without the
    # "agent" suffix this would also match narration calls, not just the
    # real psm agent call.
    stub = _chat_stub({"PSM (Platform-Specific Model) agent": "PSM v2"})
    with patch.object(orchestrator, "chat", side_effect=stub) as mock_chat:
        result = o.rerun()
        o._last_thread.join(timeout=5)

    assert result == {"status": "started", "stage": "psm"}
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"] == {"stage": "psm", "output": "PSM v2", "valid": True}
    agent_calls = [c for c in mock_chat.call_args_list if "PSM (Platform-Specific Model) agent" in c.args[0][0]["content"]]
    assert len(agent_calls) == 1
    assert agent_calls[0].args[0][1]["content"].startswith("A GitLab CI platform")
    assert "Include a lint stage" in agent_calls[0].args[0][1]["content"]


def test_rerun_rejects_overrides_on_a_non_docs_stage():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    with pytest.raises(ValueError, match="only 'docs' does"):
        o.rerun({"hint": "not applicable here"})


def test_module_level_rerun_stage_delegates_to_default_orchestrator():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    _fast_forward_to_psm(orchestrator._default)
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM v1")):
            orchestrator.run_stage({"platform_description": "A GitLab CI platform"})

        orchestrator.add_constraint("psm", "Include a lint stage")

        stub = _chat_stub({"PSM (Platform-Specific Model)": "PSM v2"})
        with patch.object(orchestrator, "chat", side_effect=stub):
            result = orchestrator.rerun_stage()
            orchestrator.wait_for_idle()

        assert result == {"status": "started", "stage": "psm"}
        completed = next(e for e in orchestrator.events() if e["type"] == "call_completed")
        assert completed["data"] == {"stage": "psm", "output": "PSM v2", "valid": True}
    finally:
        orchestrator._default = original


def test_advance_stage_moves_through_stages_and_returns_none_at_end():
    o = orchestrator.Orchestrator()
    assert o.current_stage == "docs"
    assert o.advance_stage() == "psm"
    assert o.advance_stage() == "atl"
    assert o.advance_stage() == "acceleo"
    assert o.advance_stage() == "generation"
    assert o.advance_stage() is None
    assert o.current_stage is None


def test_add_constraint_records_correction_for_stage():
    o = orchestrator.Orchestrator()
    o.add_constraint("psm", "Use kebab-case job names")
    o.add_constraint("psm", "Include a lint stage")

    assert o.constraints["psm"] == ["Use kebab-case job names", "Include a lint stage"]


def test_review_approved_starts_next_stage_and_it_completes_with_the_right_input():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    stub = _chat_stub({"ATL transformation agent": "ATL rules"})
    with patch.object(orchestrator, "chat", side_effect=stub) as mock_chat:
        result = o.review("psm", approved=True)
        o._last_thread.join(timeout=5)

    assert result == {"status": "started", "stage": "atl"}
    assert o.current_stage == "atl"
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"] == {"stage": "atl", "output": "ATL rules", "valid": True}
    agent_calls = [c for c in mock_chat.call_args_list if "ATL transformation agent" in c.args[0][0]["content"]]
    assert agent_calls[0].args[0][1]["content"].startswith("PSM description")


def test_review_approved_accumulates_outputs_through_generation():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")):
        o.run_stage({"platform_description": "A GitLab CI platform"})
    with patch.object(orchestrator, "chat", side_effect=_chat_stub({"ATL transformation agent": "ATL rules"})):
        o.review("psm", approved=True)
        o._last_thread.join(timeout=5)
    with patch.object(orchestrator, "chat", side_effect=_chat_stub({"Acceleo template agent": "Acceleo template"})):
        o.review("atl", approved=True)
        o._last_thread.join(timeout=5)

    stub = _chat_stub({"generation summary agent": "Final summary"})
    with patch.object(orchestrator, "chat", side_effect=stub) as mock_chat:
        result = o.review("acceleo", approved=True)
        o._last_thread.join(timeout=5)

    assert result == {"status": "started", "stage": "generation"}
    # last, not first: psm's approval already recorded one call_completed
    # (atl's run), and atl's approval recorded another (acceleo's run).
    completed = [e for e in o.events if e["type"] == "call_completed"][-1]
    assert completed["data"] == {"stage": "generation", "output": "Final summary", "valid": True}
    agent_calls = [c for c in mock_chat.call_args_list if "generation summary agent" in c.args[0][0]["content"]]
    user_content = agent_calls[0].args[0][1]["content"]
    assert "PSM description" in user_content
    assert "ATL rules" in user_content
    assert "Acceleo template" in user_content


def test_review_approved_on_last_stage_returns_complete():
    o = orchestrator.Orchestrator()
    o.current_stage_index = len(orchestrator.STAGES) - 1
    with patch.object(orchestrator, "chat", return_value=ok_response("Noted.")):
        result = o.review("generation", approved=True)

    assert result == {"status": "complete"}
    assert o.current_stage is None


def test_review_rejected_records_constraint_and_does_not_advance():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    with patch.object(orchestrator, "chat", return_value=ok_response("Noted.")):
        result = o.review("psm", approved=False, correction="Missing artifact retention policy")

    assert result == {"status": "rerun", "stage": "psm"}
    assert o.current_stage == "psm"
    assert o.constraints["psm"] == ["Missing artifact retention policy"]


# --- _validate_review() / record_review() tests ---------------------------------
#
# review() is a thin wrapper over record_review() (it additionally starts the
# next stage running on approval), so validation is only tested once here,
# against record_review() directly.


def test_record_review_rejects_mismatched_stage_id():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    with pytest.raises(ValueError, match="psm"):
        o.record_review("atl", approved=True)


def test_record_review_rejects_missing_correction_when_not_approved():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    with pytest.raises(ValueError, match="correction"):
        o.record_review("psm", approved=False)


def test_record_review_approved_advances_without_running_next_stage():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    with patch.object(orchestrator, "chat", return_value=ok_response("Noted.")) as mock_chat:
        result = o.record_review("psm", approved=True)

    # one chat() call for the review_approved event's own narration, but no
    # second call for atl_agent, "advanced" means the caller (review(), or
    # main.py's /review handler) schedules that separately
    assert mock_chat.call_count == 1
    assert "MDDOAI Orchestrator" in mock_chat.call_args.args[0][0]["content"]
    assert result["status"] == "advanced"
    assert result["stage"] == "atl"
    assert result["context"]["psm_output"] == "PSM description"
    assert o.current_stage == "atl"
    assert o._last_thread is None  # record_review() itself never starts a run


def test_record_review_approved_on_last_stage_returns_complete():
    o = orchestrator.Orchestrator()
    o.current_stage_index = len(orchestrator.STAGES) - 1
    with patch.object(orchestrator, "chat", return_value=ok_response("Noted.")):
        result = o.record_review("generation", approved=True)

    assert result == {"status": "complete"}
    assert o.current_stage is None


def test_record_review_rejected_records_constraint_and_returns_rerun():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    with patch.object(orchestrator, "chat", return_value=ok_response("Noted.")):
        result = o.record_review("psm", approved=False, correction="Missing artifact retention policy")

    assert result == {"status": "rerun", "stage": "psm"}
    assert o.constraints["psm"] == ["Missing artifact retention policy"]


def test_record_review_records_review_approved_and_rejected_events():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    with patch.object(orchestrator, "chat", return_value=ok_response("Noted.")):
        o.record_review("psm", approved=False, correction="fix the port mapping")

    review_events = [e for e in o.events if e["type"] == "review_rejected"]
    assert len(review_events) == 1
    assert review_events[0]["stage"] == "psm"
    assert review_events[0]["data"] == {"correction": "fix the port mapping"}


def test_module_level_wrappers_delegate_to_default_orchestrator():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    _fast_forward_to_psm(orchestrator._default)
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM description")):
            run_result = orchestrator.run_stage({"platform_description": "desc"})
        assert run_result["stage"] == "psm"

        advance_result = orchestrator.advance_stage()
        assert advance_result == "atl"

        orchestrator.add_constraint("atl", "Name rules after the mapped concept")
        assert orchestrator._default.constraints["atl"] == ["Name rules after the mapped concept"]

        stub = _chat_stub({"Acceleo template agent": "Acceleo template"})
        with patch.object(orchestrator, "chat", side_effect=stub):
            review_result = orchestrator.review("atl", approved=True)
            orchestrator.wait_for_idle()

        assert review_result == {"status": "started", "stage": "acceleo"}
        completed = next(e for e in orchestrator.events() if e["type"] == "call_completed")
        assert completed["data"] == {"stage": "acceleo", "output": "Acceleo template", "valid": True}
    finally:
        orchestrator._default = original


# --- assistant.react_to_event() / assistant.nudge() tests -----------------------
#
# nudge() is the tools-enabled path through the SAME react_to_event() that
# record_event() uses tools-disabled for narration, there is no separate
# "judge". These tests exercise nudge()'s tool-dispatch behavior; a dedicated
# narration-only (use_tools=False) test lives further down.


def test_nudge_calls_chat_with_tools_and_current_stage_in_system_prompt():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    _fast_forward_to_psm(orchestrator._default)
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM output")):
            orchestrator.run_stage({"platform_description": "A GitLab CI platform"})
        stub = _chat_stub({"ATL transformation agent": "ATL output"})
        with patch.object(orchestrator, "chat", side_effect=stub):
            orchestrator.review("psm", approved=True)  # advances current_stage to "atl"
            orchestrator.wait_for_idle()

        with patch.object(orchestrator, "chat", return_value=ok_response("Sure, one moment.")) as mock_chat:
            assistant.nudge("what's the status?")

        messages, kwargs = mock_chat.call_args.args, mock_chat.call_args.kwargs
        assert kwargs["tools"] == [t.schema() for t in tool_calling.load_tools("atl", pipeline_tools.TOOLS)]
        assert kwargs["tool_choice"] == "auto"
        assert messages[0][0]["role"] == "system"
        assert "is: atl" in messages[0][0]["content"]
        # index -1: the newly appended user_message event is always last, prior
        # history (there's real history by now: the review_approved event and
        # the atl run's own call_started/call_completed) fills the middle.
        sent_event = json.loads(messages[0][-1]["content"])
        assert sent_event["type"] == "user_message"
        assert sent_event["stage"] == "atl"
        assert sent_event["data"] == {"message": "what's the status?"}
    finally:
        orchestrator._default = original


def test_nudge_returns_clarification_when_llm_calls_no_tool():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        response = tool_response(None, content="Could you clarify which stage you mean?")
        with patch.object(orchestrator, "chat", return_value=response) as mock_chat:
            result = assistant.nudge("hello there")

        assert mock_chat.call_count == 1
        assert result == {
            "tool_called": None,
            "result": None,
            "message": "Could you clarify which stage you mean?",
            "model": "test-model",
        }
    finally:
        orchestrator._default = original


def test_nudge_falls_back_to_generic_clarification_when_llm_gives_no_text_either():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        response = tool_response(None, content=None)
        with patch.object(orchestrator, "chat", return_value=response):
            result = assistant.nudge("???")

        assert result["tool_called"] is None
        assert "clarify" in result["message"].lower()
    finally:
        orchestrator._default = original


def test_nudge_records_the_human_message_and_reply_as_events():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        response = tool_response(None, content="Sure, one moment.")
        with patch.object(orchestrator, "chat", return_value=response):
            assistant.nudge("what's the status?")

        events = orchestrator.events()
        assert len(events) == 2
        assert events[0] == {
            "type": "user_message", "stage": "docs",
            "data": {"message": "what's the status?"}, "timestamp": events[0]["timestamp"],
        }
        assert events[1] == {
            "type": "message", "stage": "docs",
            "text": "Sure, one moment.", "model": "test-model", "timestamp": events[1]["timestamp"],
        }
    finally:
        orchestrator._default = original


# --- record_event() / run_stage_async() tests -----------------------------------


def test_record_event_appends_event_then_reacts_and_appends_the_reply():
    o = orchestrator.Orchestrator()
    with patch.object(orchestrator, "chat", return_value=ok_response("Fetching now.")) as mock_chat:
        event = o.record_event("call_started", "docs", {"url": "https://x"})

    assert mock_chat.call_count == 1
    assert event["type"] == "call_started"
    assert event["data"] == {"url": "https://x"}
    assert len(o.events) == 2
    assert o.events[0] == event
    assert o.events[1]["text"] == "Fetching now."


def test_record_event_reaction_excludes_the_just_added_event_from_history():
    o = orchestrator.Orchestrator()
    with patch.object(orchestrator, "chat", return_value=ok_response("ok")):
        o.record_event("call_started", "docs", {})
    history_before_second_call = list(o.events)

    with patch.object(orchestrator, "_reactor", return_value={"message": "ok2"}) as mock_reactor:
        o.record_event("call_completed", "docs", {})

    args = mock_reactor.call_args.args
    assert args[1] == history_before_second_call


def test_record_event_truncates_long_data_fields_for_the_reaction_call():
    o = orchestrator.Orchestrator()
    long_output = "x" * (orchestrator._REACTION_FIELD_MAX_CHARS + 500)
    with patch.object(orchestrator, "chat", return_value=ok_response("ok")) as mock_chat:
        o.record_event("call_completed", "docs", {"output": long_output})

    sent_event_json = mock_chat.call_args.args[0][-1]["content"]
    assert "... (truncated)" in sent_event_json
    assert len(sent_event_json) < len(long_output)
    # the untruncated original is still what's actually recorded
    assert o.events[0]["data"]["output"] == long_output


def test_record_event_narration_failure_falls_back_without_crashing():
    o = orchestrator.Orchestrator()
    with patch.object(orchestrator, "_reactor", side_effect=RuntimeError("ai-layer unreachable")):
        event = o.record_event("call_started", "docs", {})

    assert event["type"] == "call_started"  # the real event is still recorded
    assert o.events[1]["text"] == "(narration unavailable)"


def test_run_stage_async_sets_busy_synchronously_before_the_thread_finishes():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    release = threading.Event()

    def _blocking_chat(messages, model=None, tools=None, tool_choice=None):
        # Blocks the background thread's very first chat() call (the
        # call_started narration) until the main thread has asserted busy is
        # True and released it, otherwise a fast/mocked run could finish
        # before this assertion even runs, making the check meaningless.
        release.wait(timeout=5)
        return ok_response("Noted.")

    with patch.object(orchestrator, "chat", side_effect=_blocking_chat):
        o.run_stage_async({"platform_description": "A GitLab CI platform"})
        assert o.busy is True
        release.set()
        o._last_thread.join(timeout=5)

    assert o.busy is False


def test_run_stage_async_records_call_completed_on_success():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    stub = _chat_stub({"PSM (Platform-Specific Model)": "PSM description"})
    with patch.object(orchestrator, "chat", side_effect=stub):
        o.run_stage_async({"platform_description": "A GitLab CI platform"})
        o._last_thread.join(timeout=5)

    types = [e["type"] for e in o.events]
    assert "call_started" in types
    assert "call_completed" in types
    assert "call_failed" not in types
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"] == {"stage": "psm", "output": "PSM description", "valid": True}


def test_run_stage_async_records_call_failed_on_agent_error():
    o = orchestrator.Orchestrator()
    _fast_forward_to_psm(o)
    responses = [ok_response("Fetching now."), RuntimeError("all providers exhausted"), ok_response("Stage failed.")]
    with patch.object(orchestrator, "chat", side_effect=responses):
        o.run_stage_async({"platform_description": "A GitLab CI platform"})
        o._last_thread.join(timeout=5)

    types = [e["type"] for e in o.events]
    assert "call_failed" in types
    assert "call_completed" not in types
    failed = next(e for e in o.events if e["type"] == "call_failed")
    assert failed["data"] == {"error": "all providers exhausted"}
    assert o.busy is False


def test_nudge_dispatches_single_rerun_stage_tool_call():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    _fast_forward_to_psm(orchestrator._default)
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM v1")):
            orchestrator.run_stage({"platform_description": "A GitLab CI platform"})

        stub = _nudge_chat_stub(
            tool_calls=[tool_call("rerun_stage", {})],
            stage_outputs={"PSM (Platform-Specific Model)": "PSM v2"},
        )
        with patch.object(orchestrator, "chat", side_effect=stub):
            result = assistant.nudge("redo the psm stage")
            orchestrator.wait_for_idle()

        assert result == {
            "tool_called": "rerun_stage",
            "result": {"status": "started", "stage": "psm"},
            "steps": [{"tool": "rerun_stage", "arguments": {}, "result": {"status": "started", "stage": "psm"}}],
            "model": "test-model",
        }
        completed = next(e for e in orchestrator.events() if e["type"] == "call_completed")
        assert completed["data"] == {"stage": "psm", "output": "PSM v2", "valid": True}
    finally:
        orchestrator._default = original


def test_nudge_dispatches_add_constraint_then_rerun_stage_for_inline_correction():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    _fast_forward_to_psm(orchestrator._default)
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM output")):
            orchestrator.run_stage({"platform_description": "A GitLab CI platform"})
        stub = _chat_stub({"ATL transformation agent": "ATL output v1"})
        with patch.object(orchestrator, "chat", side_effect=stub):
            orchestrator.review("psm", approved=True)  # auto-starts the atl stage
            orchestrator.wait_for_idle()

        assert orchestrator.current_stage() == "atl"

        stub = _nudge_chat_stub(
            tool_calls=[
                tool_call("add_constraint", {"stage": "atl", "constraint": "Add a lint step"}),
                tool_call("rerun_stage", {}),
            ],
            stage_outputs={"ATL transformation agent": "ATL output v2 (lint step added)"},
        )
        with patch.object(orchestrator, "chat", side_effect=stub):
            result = assistant.nudge("the ATL stage output is wrong, please redo it with a lint step added")
            orchestrator.wait_for_idle()

        assert orchestrator._default.constraints["atl"] == ["Add a lint step"]
        assert [s["tool"] for s in result["steps"]] == ["add_constraint", "rerun_stage"]
        assert result["steps"][0]["result"] is None
        assert result["tool_called"] == "rerun_stage"
        assert result["result"] == {"status": "started", "stage": "atl"}
        # last, not first: the setup's own psm approval already recorded one
        # call_completed (atl's first run, "ATL output v1").
        completed = [e for e in orchestrator.events() if e["type"] == "call_completed"][-1]
        assert completed["data"] == {"stage": "atl", "output": "ATL output v2 (lint step added)", "valid": True}
    finally:
        orchestrator._default = original


def test_nudge_dispatches_stage_result_approve_tool_call():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    _fast_forward_to_psm(orchestrator._default)
    try:
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM output")):
            orchestrator.run_stage({"platform_description": "A GitLab CI platform"})

        stub = _nudge_chat_stub(
            tool_calls=[tool_call("stage_result", {"stage_id": "psm", "approved": True})],
            stage_outputs={"ATL transformation agent": "ATL output"},
        )
        with patch.object(orchestrator, "chat", side_effect=stub):
            result = assistant.nudge("the psm output looks good, approve it")
            orchestrator.wait_for_idle()

        assert result["tool_called"] == "stage_result"
        assert result["result"] == {"status": "started", "stage": "atl"}
        assert orchestrator.current_stage() == "atl"
        completed = next(e for e in orchestrator.events() if e["type"] == "call_completed")
        assert completed["data"] == {"stage": "atl", "output": "ATL output", "valid": True}
    finally:
        orchestrator._default = original


def test_nudge_dispatches_start_pipeline_tool_call():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    _fast_forward_to_psm(orchestrator._default)
    try:
        # Get some way into an in-progress run first, so we can confirm
        # start_pipeline actually resets it rather than continuing it.
        with patch.object(orchestrator, "chat", return_value=ok_response("PSM output (TeamCity)")):
            orchestrator.run_stage({"platform_description": "TeamCity: A CI/CD platform using Kotlin DSL"})
        orchestrator.add_constraint("psm", "Use kebab-case job names")

        stub = _nudge_chat_stub(tool_calls=[tool_call("start_pipeline", {
            "platform_description": "GitLab CI with YAML pipelines",
            "seed_url": "https://docs.gitlab.com/ci/yaml/",
        })])
        with patch.object(orchestrator, "chat", side_effect=stub), \
             patch.object(orchestrator, "httpx") as mock_httpx:
            mock_httpx.post.return_value = _fake_fetch_response()
            result = assistant.nudge("let's do this for GitLab instead")
            orchestrator.wait_for_idle()

        assert result["tool_called"] == "start_pipeline"
        assert result["result"] == {"status": "started", "stage": "docs"}
        # New Orchestrator instance: prior progress/constraints are gone.
        assert orchestrator.current_stage() == "docs"
        assert orchestrator._default.constraints == {}
        completed = next(e for e in orchestrator.events() if e["type"] == "call_completed")
        assert completed["stage"] == "docs"
        assert completed["data"]["valid"] is True
        sent_payload = mock_httpx.post.call_args.kwargs["json"]
        assert sent_payload["url"] == "https://docs.gitlab.com/ci/yaml/"
    finally:
        orchestrator._default = original


def test_nudge_records_error_for_unknown_tool_call_without_crashing():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        nudge_response = tool_response([tool_call("delete_everything", {})])
        with patch.object(orchestrator, "chat", return_value=nudge_response):
            result = assistant.nudge("do something weird")

        assert result["tool_called"] == "delete_everything"
        assert result["result"] == {"error": "Unknown tool: delete_everything"}
    finally:
        orchestrator._default = original


def test_react_to_event_without_tools_only_narrates_and_never_dispatches():
    original = orchestrator._default
    orchestrator._default = orchestrator.Orchestrator()
    try:
        event = {"type": "call_started", "stage": "docs", "data": {"seed_url": "https://x"}, "timestamp": 1.0}
        # Even if the mocked LLM tries to return a tool call, react_to_event
        # never passes tools=, so there's nothing for the real API to honor;
        # this asserts the call itself carries no tools/tool_choice kwargs.
        with patch.object(orchestrator, "chat", return_value=ok_response("Fetching docs now.")) as mock_chat:
            result = assistant.react_to_event(event, [])

        assert result == {"tool_called": None, "result": None, "message": "Fetching docs now.", "model": "test-model"}
        assert "tools" not in mock_chat.call_args.kwargs
        assert "tool_choice" not in mock_chat.call_args.kwargs
    finally:
        orchestrator._default = original


def test_load_tools_scopes_docs_only_tools_to_the_docs_stage():
    docs_tools = {t.name for t in tool_calling.load_tools("docs", pipeline_tools.TOOLS)}
    psm_tools = {t.name for t in tool_calling.load_tools("psm", pipeline_tools.TOOLS)}

    assert {"fetch_documentation", "fetch_page"} <= docs_tools
    assert not ({"fetch_documentation", "fetch_page"} & psm_tools)
    # the five pipeline-control tools have no "stages" key, available everywhere
    global_tools = {"run_stage", "rerun_stage", "stage_result", "add_constraint", "start_pipeline"}
    assert global_tools <= docs_tools
    assert global_tools <= psm_tools


# --- chat() HTTP client tests -------------------------------------------------
#
# chat() POSTs to ai-layer's /chat endpoint and returns its parsed JSON
# response as a plain dict, unmodified — these tests mock httpx.post itself
# (the actual network boundary), rather than mocking chat() as the tests
# above do for the agents/nudge().


def _fake_httpx_response(content=None, model="gemini/gemini-2.5-flash", tool_calls=None):
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = {"content": content, "model": model, "tool_calls": tool_calls}
    return resp


def _fake_httpx_response_raw(payload):
    """Like _fake_httpx_response, but for endpoints that aren't ai-layer's
    /chat shape, e.g. GET /providers, which returns a bare list."""
    resp = MagicMock()
    resp.raise_for_status.return_value = None
    resp.json.return_value = payload
    return resp


def test_chat_posts_to_ai_layer_url_with_messages_and_model():
    messages = [{"role": "user", "content": "hi"}]
    with patch("orchestrator.httpx.post", return_value=_fake_httpx_response("hello")) as mock_post:
        result = orchestrator.chat(messages, model="auto")

    mock_post.assert_called_once_with(
        f"{orchestrator.AI_LAYER_URL}/chat",
        json={"messages": messages, "model": "auto"},
        timeout=120.0,
    )
    assert result["content"] == "hello"
    assert result["model"] == "gemini/gemini-2.5-flash"


def test_chat_omits_tools_and_tool_choice_when_not_provided():
    with patch("orchestrator.httpx.post", return_value=_fake_httpx_response("hello")) as mock_post:
        orchestrator.chat([{"role": "user", "content": "hi"}])

    sent_payload = mock_post.call_args.kwargs["json"]
    assert "tools" not in sent_payload
    assert "tool_choice" not in sent_payload


def test_chat_includes_tools_and_tool_choice_when_provided():
    tools_schema = [t.schema() for t in tool_calling.load_tools("docs", pipeline_tools.TOOLS)]
    with patch("orchestrator.httpx.post", return_value=_fake_httpx_response("hello")) as mock_post:
        orchestrator.chat([{"role": "user", "content": "hi"}], tools=tools_schema, tool_choice="auto")

    sent_payload = mock_post.call_args.kwargs["json"]
    assert sent_payload["tools"] == tools_schema
    assert sent_payload["tool_choice"] == "auto"


def test_chat_returns_tool_calls_as_plain_dicts():
    tool_calls = [{"function": {"name": "rerun_stage", "arguments": "{}"}}]
    with patch("orchestrator.httpx.post", return_value=_fake_httpx_response(None, tool_calls=tool_calls)):
        result = orchestrator.chat([{"role": "user", "content": "hi"}])

    assert result["content"] is None
    assert result["tool_calls"] == tool_calls


def test_chat_returns_no_tool_calls_when_response_has_none():
    with patch("orchestrator.httpx.post", return_value=_fake_httpx_response("hello", tool_calls=None)):
        result = orchestrator.chat([{"role": "user", "content": "hi"}])

    assert result["tool_calls"] is None


def test_chat_raises_when_ai_layer_returns_an_error_status():
    resp = MagicMock()
    resp.raise_for_status.side_effect = httpx.HTTPStatusError("500", request=MagicMock(), response=MagicMock())
    with patch("orchestrator.httpx.post", return_value=resp):
        with pytest.raises(httpx.HTTPStatusError):
            orchestrator.chat([{"role": "user", "content": "hi"}])
