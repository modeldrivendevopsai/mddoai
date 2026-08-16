"""pipeline.py unit tests: the pipeline state machine (STAGES, validate(),
class IntegrationRun — run_stage, rerun, advance_stage, add_constraint,
review, record_review, record_event, run_stage_async). No real API calls —
ai_layer_client.chat is mocked.

record_event() only ever appends a raw fact and returns, it has no
narration/reactor concept at all (that moved to orchestrator/chat_log.py
when integration_runner became its own service) — so unlike before this
split, exactly ONE real chat() call happens per stage transition here (the
stage agent's own), never two. Tests that used to need a marker-matching
stub to tell a narration call apart from the real stage call
(orchestrator/tests/helpers.py's _chat_stub, still used there for
send_message()'s own narration+tool-dispatch tests) can just mock chat()
directly with a plain return_value/side_effect here.

Tests verify:
  1. is_good_enough()/validate() reject empty/error-marker responses and accept
     good ones.
  2. IntegrationRun.run_stage() looks up the current stage's agent (via
     stages/), validates its output, and reports the current stage.
  3. IntegrationRun.advance_stage()/review() move through STAGES and handle
     approval vs. rejection (constraint recording) correctly.
  4. run_stage()/rerun() pick up constraints recorded via add_constraint() since
     the last run — verifying corrections are actually threaded into the agent's
     prompt, not just stored and left unused.
  5. review() on approval starts the next stage running in the background (not
     just advancing the pointer), threading the approved stage's output into the
     next stage's context under the right f"{stage_id}_output" key, and
     accumulating outputs across approvals so the final generation stage sees
     all three. Because the run happens on a background thread, every mock that
     patches ai_layer_client.chat for one of these calls stays active until the
     thread is joined (IntegrationRun._last_thread.join()), never after the
     `with` block that installed it has already exited, otherwise the thread's
     real work races against the mock being torn down.
  6. record_event() appends a raw event and returns it, with no reaction of
     any kind — no chat() call, nothing else touched.
  7. run_stage_async() sets busy synchronously before the background thread
     starts, and records call_completed/call_failed depending on outcome.
"""
import threading
from unittest.mock import patch

import pytest

from clients import ai_layer_client
from integration_runner import pipeline
from helpers import _fast_forward_to_psm, ok_response


def test_is_good_enough_rejects_empty():
    assert not pipeline.is_good_enough("")
    assert not pipeline.is_good_enough("   ")


def test_is_good_enough_rejects_error_markers():
    assert not pipeline.is_good_enough("I cannot help with that.")
    assert not pipeline.is_good_enough("I don't know how to do that.")
    assert not pipeline.is_good_enough("Sorry, an error occurred.")


def test_is_good_enough_accepts_valid_response():
    assert pipeline.is_good_enough("Here are the pipeline stages you need: build, test, deploy.")


def test_is_good_enough_accepts_technical_content_that_mentions_error_handling():
    # "error" as a bare substring must not trip the refusal check — legitimate
    # generated content (e.g. an Acceleo template) routinely discusses error
    # handling as a concept, distinct from the agent itself reporting a failure.
    content = (
        "The template defines an onDependencyFailure block that invokes the "
        "errorHandler class, catching IOError and surfacing an error code to "
        "the pipeline's error-handling policy."
    )
    assert pipeline.is_good_enough(content)


def test_validate_reuses_is_good_enough():
    assert pipeline.validate("A real response.")
    assert not pipeline.validate("")
    assert not pipeline.validate("I cannot help with that.")


def test_stages_order():
    assert pipeline.STAGES == ["docs", "serialization", "pim", "psm", "atl", "acceleo", "generation"]


def test_integration_run_auto_generates_a_run_id_when_none_given():
    o1 = pipeline.IntegrationRun()
    o2 = pipeline.IntegrationRun()

    assert o1.run_id
    assert o2.run_id
    assert o1.run_id != o2.run_id


def test_integration_run_accepts_an_explicit_run_id():
    o = pipeline.IntegrationRun(run_id="a-fixed-id")
    assert o.run_id == "a-fixed-id"


def test_run_stage_calls_current_stage_agent_and_validates_output():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM description")) as mock_chat:
        result = o.run_stage({"platform_description": "A GitLab CI platform"})

    assert mock_chat.call_count == 1
    assert result == {"stage": "psm", "output": "PSM description", "valid": True}


def test_run_stage_threads_the_chosen_model_into_the_agent_s_context():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    o.model = "mistral-small"
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM description")) as mock_chat:
        o.run_stage({"platform_description": "A GitLab CI platform"})

    assert mock_chat.call_args.kwargs["model"] == "mistral-small"


def test_run_stage_reports_invalid_output():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("I cannot help with that.")):
        result = o.run_stage({"platform_description": "A GitLab CI platform"})

    assert result["valid"] is False


def test_run_stage_incorporates_constraints_added_since_the_last_run():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM v1")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    o.add_constraint("psm", "Use kebab-case job names")

    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM v2")) as mock_chat:
        o.run_stage({"platform_description": "A GitLab CI platform"})

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "Use kebab-case job names" in user_content


def test_rerun_replays_the_last_context_and_picks_up_new_constraints():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM v1")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    o.add_constraint("psm", "Include a lint stage")

    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM v2")) as mock_chat:
        result = o.rerun()
        o._last_thread.join(timeout=5)

    assert result == {"status": "started", "stage": "psm"}
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"] == {"stage": "psm", "output": "PSM v2", "valid": True}
    assert mock_chat.call_count == 1
    sent_content = mock_chat.call_args.args[0][1]["content"]
    assert sent_content.startswith("A GitLab CI platform")
    assert "Include a lint stage" in sent_content


def test_rerun_rejects_overrides_on_a_non_docs_stage():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    with pytest.raises(ValueError, match="only 'docs' does"):
        o.rerun({"hint": "not applicable here"})


def test_advance_stage_moves_through_stages_and_returns_none_at_end():
    o = pipeline.IntegrationRun()
    assert o.current_stage == "docs"
    assert o.advance_stage() == "serialization"
    assert o.advance_stage() == "pim"
    assert o.advance_stage() == "psm"
    assert o.advance_stage() == "atl"
    assert o.advance_stage() == "acceleo"
    assert o.advance_stage() == "generation"
    assert o.advance_stage() is None
    assert o.current_stage is None


def test_add_constraint_records_correction_for_stage():
    o = pipeline.IntegrationRun()
    o.add_constraint("psm", "Use kebab-case job names")
    o.add_constraint("psm", "Include a lint stage")

    assert o.constraints["psm"] == ["Use kebab-case job names", "Include a lint stage"]


def test_add_constraint_records_a_real_constraint_added_event():
    # Every real action becomes a real, persisted event, the same rule
    # call_started/call_completed/review_approved already follow — a
    # constraint recorded via add_constraint() shouldn't be the one action
    # that leaves no trace in this run's event log.
    o = pipeline.IntegrationRun()
    o.add_constraint("psm", "Use kebab-case job names")

    constraint_events = [e for e in o.events if e["type"] == "constraint_added"]
    assert len(constraint_events) == 1
    assert constraint_events[0]["stage"] == "psm"
    assert constraint_events[0]["data"] == {"constraint": "Use kebab-case job names"}


def test_review_approved_starts_next_stage_and_it_completes_with_the_right_input():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM description")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL rules")) as mock_chat:
        result = o.review("psm", approved=True)
        o._last_thread.join(timeout=5)

    assert result == {"status": "started", "stage": "atl"}
    assert o.current_stage == "atl"
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"] == {"stage": "atl", "output": "ATL rules", "valid": True}
    assert mock_chat.call_count == 1
    assert mock_chat.call_args.args[0][1]["content"].startswith("PSM description")


def test_review_approved_accumulates_outputs_through_generation():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM description")):
        o.run_stage({"platform_description": "A GitLab CI platform"})
    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL rules")):
        o.review("psm", approved=True)
        o._last_thread.join(timeout=5)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("Acceleo template")):
        o.review("atl", approved=True)
        o._last_thread.join(timeout=5)

    with patch.object(ai_layer_client, "chat", return_value=ok_response("Final summary")) as mock_chat:
        result = o.review("acceleo", approved=True)
        o._last_thread.join(timeout=5)

    assert result == {"status": "started", "stage": "generation"}
    completed = [e for e in o.events if e["type"] == "call_completed"][-1]
    assert completed["data"] == {"stage": "generation", "output": "Final summary", "valid": True}
    assert mock_chat.call_count == 1
    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "PSM description" in user_content
    assert "ATL rules" in user_content
    assert "Acceleo template" in user_content


def test_review_approved_on_last_stage_returns_complete():
    o = pipeline.IntegrationRun()
    o.current_stage_index = len(pipeline.STAGES) - 1
    result = o.review("generation", approved=True)

    assert result == {"status": "complete"}
    assert o.current_stage is None


def test_review_rejected_records_constraint_and_does_not_advance():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
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
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    with pytest.raises(ValueError, match="psm"):
        o.record_review("atl", approved=True)


def test_record_review_rejects_missing_correction_when_not_approved():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    with pytest.raises(ValueError, match="correction"):
        o.record_review("psm", approved=False)


def test_record_review_approved_advances_without_running_next_stage():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM description")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    with patch.object(ai_layer_client, "chat") as mock_chat:
        result = o.record_review("psm", approved=True)

    # record_review() itself never reacts to anything it records — no chat()
    # call at all, not even for atl's own agent, "advanced" means the caller
    # (review(), or main.py's /review handler) schedules that separately.
    assert mock_chat.call_count == 0
    assert result["status"] == "advanced"
    assert result["stage"] == "atl"
    assert result["context"]["psm_output"] == "PSM description"
    assert o.current_stage == "atl"
    assert o._last_thread is None  # record_review() itself never starts a run


def test_record_review_approved_on_last_stage_returns_complete():
    o = pipeline.IntegrationRun()
    o.current_stage_index = len(pipeline.STAGES) - 1
    result = o.record_review("generation", approved=True)

    assert result == {"status": "complete"}
    assert o.current_stage is None


def test_record_review_rejected_records_constraint_and_returns_rerun():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    result = o.record_review("psm", approved=False, correction="Missing artifact retention policy")

    assert result == {"status": "rerun", "stage": "psm"}
    assert o.constraints["psm"] == ["Missing artifact retention policy"]


def test_record_review_records_review_approved_and_rejected_events():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM description")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    o.record_review("psm", approved=False, correction="fix the port mapping")

    review_events = [e for e in o.events if e["type"] == "review_rejected"]
    assert len(review_events) == 1
    assert review_events[0]["stage"] == "psm"
    assert review_events[0]["data"] == {"correction": "fix the port mapping"}


# --- record_event() / run_stage_async() tests -----------------------------------


def test_record_event_appends_a_raw_event_and_returns_it_without_any_reaction():
    o = pipeline.IntegrationRun()
    with patch.object(ai_layer_client, "chat") as mock_chat:
        event = o.record_event("call_started", "docs", {"url": "https://x"})

    assert mock_chat.call_count == 0  # no reaction of any kind, unlike before this split
    assert event["type"] == "call_started"
    assert event["data"] == {"url": "https://x"}
    assert o.events == [event]


def test_run_stage_async_sets_busy_synchronously_before_the_thread_finishes():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    release = threading.Event()

    def _blocking_chat(messages, model=None, tools=None, tool_choice=None):
        # Blocks the background thread's one real chat() call (the psm
        # stage agent's own) until the main thread has asserted busy is
        # True and released it, otherwise a fast/mocked run could finish
        # before this assertion even runs, making the check meaningless.
        release.wait(timeout=5)
        return ok_response("PSM description")

    with patch.object(ai_layer_client, "chat", side_effect=_blocking_chat):
        o.run_stage_async({"platform_description": "A GitLab CI platform"})
        assert o.busy is True
        release.set()
        o._last_thread.join(timeout=5)

    assert o.busy is False


def test_run_stage_async_records_call_completed_on_success():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("PSM description")):
        o.run_stage_async({"platform_description": "A GitLab CI platform"})
        o._last_thread.join(timeout=5)

    types = [e["type"] for e in o.events]
    assert "call_started" in types
    assert "call_completed" in types
    assert "call_failed" not in types
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"] == {"stage": "psm", "output": "PSM description", "valid": True}


def test_run_stage_async_records_call_failed_on_agent_error():
    o = pipeline.IntegrationRun()
    _fast_forward_to_psm(o)
    with patch.object(ai_layer_client, "chat", side_effect=RuntimeError("all providers exhausted")):
        o.run_stage_async({"platform_description": "A GitLab CI platform"})
        o._last_thread.join(timeout=5)

    types = [e["type"] for e in o.events]
    assert "call_failed" in types
    assert "call_completed" not in types
    failed = next(e for e in o.events if e["type"] == "call_failed")
    assert failed["data"] == {"error": "all providers exhausted"}
    assert o.busy is False
