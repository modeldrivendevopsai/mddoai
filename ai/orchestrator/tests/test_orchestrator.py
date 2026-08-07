"""
Orchestrator unit tests: the pipeline state machine, plus its shared
HTTP-client infra (chat/fetch_documentation/fetch_page). No real API calls —
orchestrator.chat / orchestrator.httpx are mocked.

Sibling test files cover the rest of the module split (see
ai/orchestrator/README.md's "Module layout"): test_stage_agents.py (the six
stage agents), test_pipeline_tools.py (MDDOAI's declared tools), and
test_assistant.py (react_to_event()/nudge()). conftest.py wires
orchestrator.set_reactor(assistant.react_to_event) once for the whole
session, this file relies on that (real narration runs during record_event()
tests, only chat() itself is mocked).

Tests verify:
  1. is_good_enough()/validate() reject empty/error-marker responses and accept
     good ones.
  2. Orchestrator.run_stage() looks up the current stage's agent (via
     stage_agents.py, a local/deferred import, see run_stage()'s own comment),
     validates its output, and reports the current stage.
  3. Orchestrator.advance_stage()/review() move through STAGES and handle
     approval vs. rejection (constraint recording) correctly.
  4. run_stage()/rerun() pick up constraints recorded via add_constraint() since
     the last run — verifying corrections are actually threaded into the agent's
     prompt, not just stored and left unused.
  5. review() on approval starts the next stage running in the background (not
     just advancing the pointer), threading the approved stage's output into the
     next stage's context under the right f"{stage_id}_output" key, and
     accumulating outputs across approvals so the final generation stage sees
     all three. Because the run happens on a background thread, every mock that
     patches orchestrator.chat for one of these calls stays active until the
     thread is joined (Orchestrator._last_thread.join()/orchestrator.wait_for_idle()),
     never after the `with` block that installed it has already exited,
     otherwise the thread's real work races against the mock being torn down.
  6. record_event() appends an event, reacts to it (real narration, chat()
     mocked), and appends the reply too; the just-added event is excluded from
     the history handed to the reactor; long string fields are truncated in
     BOTH the current event and every history entry (a field truncated only
     once, when its own event was recorded, would otherwise get re-sent raw as
     part of history on every later call for the rest of the run); a narration
     failure falls back to a fixed message without crashing or losing the real
     event.
  7. run_stage_async() sets busy synchronously before the background thread
     starts, and records call_completed/call_failed depending on outcome.
  8. start_pipeline() resets the pipeline (dropping prior progress/constraints)
     and starts the docs stage running fresh for a new platform description —
     same behavior POST /start uses.
  9. chat() POSTs to AI_LAYER_URL/chat with the right payload (omitting tools/
     tool_choice when not given) and returns ai-layer's parsed JSON response as
     a plain dict, unmodified.
  10. fetch_documentation()/fetch_page() POST to retrieval's real endpoints
      with the right payload shape.
"""
import threading
from unittest.mock import MagicMock, patch

import httpx
import pytest

import orchestrator
from helpers import _fake_fetch_response, _fake_httpx_response, _fake_httpx_response_raw, _fake_page_response, \
    _fast_forward_to_psm, _chat_stub, ok_response


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


def test_validate_reuses_is_good_enough():
    assert orchestrator.validate("A real response.")
    assert not orchestrator.validate("")
    assert not orchestrator.validate("I cannot help with that.")


# --- Stage-based pipeline tests ---------------------------------------------


def test_stages_order():
    assert orchestrator.STAGES == ["docs", "pim", "psm", "atl", "acceleo", "generation"]


# --- run identity (run_id / _runs) tests -------------------------------------


def test_orchestrator_auto_generates_a_run_id_when_none_given():
    o1 = orchestrator.Orchestrator()
    o2 = orchestrator.Orchestrator()

    assert o1.run_id
    assert o2.run_id
    assert o1.run_id != o2.run_id


def test_orchestrator_accepts_an_explicit_run_id():
    o = orchestrator.Orchestrator(run_id="a-fixed-id")
    assert o.run_id == "a-fixed-id"


def test_get_run_looks_up_a_known_run_by_id():
    original = orchestrator._default
    try:
        orchestrator.reset_pipeline()
        assert orchestrator.get_run(orchestrator._default.run_id) is orchestrator._default
        assert orchestrator.get_run("no-such-run-id") is None
    finally:
        orchestrator._default = original
        orchestrator._runs.clear()
        orchestrator._runs[original.run_id] = original


def test_current_run_id_matches_the_default_orchestrator():
    original = orchestrator._default
    try:
        orchestrator.reset_pipeline()
        assert orchestrator.current_run_id() == orchestrator._default.run_id
    finally:
        orchestrator._default = original
        orchestrator._runs.clear()
        orchestrator._runs[original.run_id] = original


def test_reset_pipeline_keeps_prior_runs_as_history():
    original = orchestrator._default
    try:
        orchestrator.reset_pipeline()
        first_reset_id = orchestrator._default.run_id
        orchestrator.reset_pipeline()
        second_reset_id = orchestrator._default.run_id

        assert first_reset_id != second_reset_id
        # Both stay in _runs (in-memory session history, see list_runs()) —
        # only the most recent one is _default, the one live endpoints act on.
        assert first_reset_id in orchestrator._runs
        assert second_reset_id in orchestrator._runs
        assert orchestrator._default.run_id == second_reset_id
    finally:
        orchestrator._default = original
        orchestrator._runs.clear()
        orchestrator._runs[original.run_id] = original


def test_list_runs_marks_only_the_default_run_as_current():
    original = orchestrator._default
    try:
        orchestrator.reset_pipeline()
        first_id = orchestrator._default.run_id
        orchestrator.reset_pipeline()
        second_id = orchestrator._default.run_id

        runs = orchestrator.list_runs()
        by_id = {r["run_id"]: r for r in runs}

        assert by_id[first_id]["is_current"] is False
        assert by_id[second_id]["is_current"] is True
        # newest first
        assert runs[0]["run_id"] == second_id
    finally:
        orchestrator._default = original
        orchestrator._runs.clear()
        orchestrator._runs[original.run_id] = original


def test_list_runs_surfaces_platform_name_from_the_first_recorded_event():
    original = orchestrator._default
    try:
        orchestrator.reset_pipeline()
        orchestrator._default.record_event(
            "call_started", "docs", {"platform_description": "TeamCity"}
        )

        runs = orchestrator.list_runs()

        assert runs[0]["platform_name"] == "TeamCity"
    finally:
        orchestrator._default = original
        orchestrator._runs.clear()
        orchestrator._runs[original.run_id] = original


def test_get_run_events_returns_none_for_an_unknown_run():
    assert orchestrator.get_run_events("no-such-run-id") is None


def test_get_run_events_reads_a_specific_run_not_just_default():
    original = orchestrator._default
    try:
        orchestrator.reset_pipeline()
        first_id = orchestrator._default.run_id
        orchestrator._default.record_event("call_started", "docs", {"platform_description": "TeamCity"})
        orchestrator.reset_pipeline()  # first_id is no longer _default

        result = orchestrator.get_run_events(first_id)

        assert result is not None
        assert len(result["events"]) == 2  # call_started + its auto-narration message
        assert result["events"][0]["data"]["platform_description"] == "TeamCity"
        assert result["is_current"] is False
    finally:
        orchestrator._default = original
        orchestrator._runs.clear()
        orchestrator._runs[original.run_id] = original


# --- retrieval HTTP-client tests --------------------------------------------


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


def test_start_pipeline_forwards_docs_options_to_the_real_fetch_call():
    original = orchestrator._default
    try:
        with patch.object(orchestrator, "httpx") as mock_httpx:
            mock_httpx.post.return_value = _fake_fetch_response()
            orchestrator.start_pipeline(
                "TeamCity",
                "https://example.com/docs",
                docs_options={
                    "hint": "focus on syntax",
                    "exclude_urls": ["https://example.com/blog"],
                    "max_pages": 5,
                    "max_depth": 2,
                    "force_refresh": True,
                },
            )
            orchestrator.wait_for_idle()

        fetch_calls = [c for c in mock_httpx.post.call_args_list if c.args[0].endswith("/fetch")]
        assert fetch_calls[0].kwargs["json"] == {
            "url": "https://example.com/docs", "hint": "focus on syntax",
            "exclude_urls": ["https://example.com/blog"], "max_pages": 5, "max_depth": 2, "force_refresh": True,
        }
    finally:
        orchestrator._default = original


def test_start_pipeline_omits_docs_options_when_none_given():
    original = orchestrator._default
    try:
        with patch.object(orchestrator, "httpx") as mock_httpx:
            mock_httpx.post.return_value = _fake_fetch_response()
            orchestrator.start_pipeline("TeamCity", "https://example.com/docs")
            orchestrator.wait_for_idle()

        fetch_calls = [c for c in mock_httpx.post.call_args_list if c.args[0].endswith("/fetch")]
        assert fetch_calls[0].kwargs["json"] == {"url": "https://example.com/docs"}
    finally:
        orchestrator._default = original


def test_list_providers_proxies_ai_layers_real_providers_endpoint():
    payload = [{"name": "gemini-flash", "tier": "free"}, {"name": "claude-subscription", "tier": "subscription"}]
    with patch("orchestrator.httpx.get", return_value=_fake_httpx_response_raw(payload)) as mock_get:
        result = orchestrator.list_providers()

    mock_get.assert_called_once_with(f"{orchestrator.AI_LAYER_URL}/providers", timeout=10.0)
    assert result == payload


# --- Orchestrator.run_stage() / rerun() tests --------------------------------


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
    assert o.advance_stage() == "pim"
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


def test_record_event_truncates_long_data_fields_in_history_too():
    """The bug this guards against: a long field truncated only once, right
    when its own event was recorded, still got re-sent raw as part of
    history on every later record_event() call for the rest of the run."""
    o = orchestrator.Orchestrator()
    long_output = "x" * (orchestrator._REACTION_FIELD_MAX_CHARS + 500)
    with patch.object(orchestrator, "chat", return_value=ok_response("ok")):
        o.record_event("call_completed", "docs", {"output": long_output})

    with patch.object(orchestrator, "chat", return_value=ok_response("ok")) as mock_chat:
        o.record_event("call_started", "pim", {})

    # messages[0] = system prompt, messages[1] = the first history entry
    # (the call_completed event carrying long_output), messages[-1] = the
    # new current event.
    history_entry_json = mock_chat.call_args.args[0][1]["content"]
    assert "... (truncated)" in history_entry_json
    assert len(history_entry_json) < len(long_output)


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


# --- chat() HTTP client tests -------------------------------------------------
#
# chat() POSTs to ai-layer's /chat endpoint and returns its parsed JSON
# response as a plain dict, unmodified — these tests mock httpx.post itself
# (the actual network boundary), rather than mocking chat() as the tests
# above do for the agents/nudge().


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
    tools_schema = [{"type": "function", "function": {"name": "rerun_stage", "description": "...", "parameters": {}}}]
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
