"""pipeline.py unit tests: the pipeline state machine (STAGES,
class IntegrationRun — run_stage, rerun, advance_stage, add_constraint,
review, record_review, record_event, run_stage_async). No real API calls —
ai_layer_client.chat and validator_agent_client's validate_* functions are
mocked.

Generic pipeline-mechanics tests (does run_stage() call the right agent and
report its output, does review() advance and start the next stage, does a
correction get threaded in, ...) run against "generation", the one
remaining LLM-placeholder stage (pim/psm/atl/acceleo now return fixed mock
content + a real validator-agent call instead, see each of their own
agent.py) — _fast_forward_to_generation() in helpers.py. Tests that
specifically need a non-terminal transition (review() advancing to a NEXT
stage, not completing the run) use psm/atl/acceleo directly, with
validator_agent_client mocked instead.

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
  1. IntegrationRun.run_stage() looks up the current stage's agent (via
     stages/) and reports the current stage, threading the run's own
     run_id into the agent's context alongside model/constraints.
  2. IntegrationRun.advance_stage()/review() move through STAGES and handle
     approval vs. rejection (constraint recording) correctly.
  3. run_stage()/rerun() pick up constraints recorded via add_constraint() since
     the last run — verifying corrections are actually threaded into the agent's
     prompt, not just stored and left unused.
  4. review() on approval starts the next stage running in the background (not
     just advancing the pointer), threading the approved stage's output into the
     next stage's context under the right f"{stage_id}_output" key, and
     accumulating outputs across approvals so the final generation stage sees
     all three. Because the run happens on a background thread, every mock that
     patches a stage agent's real client call for one of these calls stays active
     until the thread is joined (IntegrationRun._last_thread.join()), never after
     the `with` block that installed it has already exited, otherwise the
     thread's real work races against the mock being torn down.
  5. record_event() appends a raw event and returns it, with no reaction of
     any kind — no chat() call, nothing else touched.
  6. run_stage_async() sets busy synchronously before the background thread
     starts, and records call_completed/call_failed depending on outcome.
"""
import threading
from unittest.mock import patch

import pytest

from clients import ai_layer_client, validator_agent_client
from integration_runner import pipeline
from integration_runner.stages.atl import agent as atl_agent
from integration_runner.stages.acceleo import agent as acceleo_agent
from integration_runner.stages.psm import agent as psm_agent
from helpers import _fast_forward_to, _fast_forward_to_generation, _validation_result, ok_response


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


def test_run_stage_calls_current_stage_agent_and_reports_its_output():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("Final summary")) as mock_chat:
        result = o.run_stage({"platform_description": "A GitLab CI platform"})

    assert mock_chat.call_count == 1
    assert result == {"stage": "generation", "output": "Final summary"}


def test_run_stage_threads_the_chosen_model_into_the_agent_s_context():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    o.model = "mistral-small"
    with patch.object(ai_layer_client, "chat", return_value=ok_response("Final summary")) as mock_chat:
        o.run_stage({"platform_description": "A GitLab CI platform"})

    assert mock_chat.call_args.kwargs["model"] == "mistral-small"


def test_run_stage_threads_the_run_id_into_the_agent_s_context():
    # stages/_validation.py's persist_attempt() needs a real run_id to
    # write runs/<run_id>/<stage>/... under — this is what supplies it.
    o = pipeline.IntegrationRun(run_id="fixed-run-id")
    _fast_forward_to(o, "psm")
    with patch.object(validator_agent_client, "validate_ecore", return_value=_validation_result()) as mock_validate:
        with patch.object(psm_agent, "persist_attempt") as mock_persist:
            o.run_stage({"platform_description": "A GitLab CI platform"})

    assert mock_validate.call_count == 1
    mock_persist.assert_called_once_with("fixed-run-id", "psm", "psm_mock.ecore", psm_agent._MOCK_CONTENT, _validation_result())


def test_run_stage_incorporates_constraints_added_since_the_last_run():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("Summary v1")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    o.add_constraint("generation", "Mention the rollback step explicitly")

    with patch.object(ai_layer_client, "chat", return_value=ok_response("Summary v2")) as mock_chat:
        o.run_stage({"platform_description": "A GitLab CI platform"})

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "Mention the rollback step explicitly" in user_content


def test_rerun_replays_the_last_context_and_picks_up_new_constraints():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("Summary v1")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    o.add_constraint("generation", "Mention the rollback step explicitly")

    with patch.object(ai_layer_client, "chat", return_value=ok_response("Summary v2")) as mock_chat:
        result = o.rerun()
        o._last_thread.join(timeout=5)

    assert result == {"status": "started", "stage": "generation"}
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"] == {"stage": "generation", "output": "Summary v2"}
    assert mock_chat.call_count == 1
    sent_content = mock_chat.call_args.args[0][1]["content"]
    assert "Mention the rollback step explicitly" in sent_content


def test_rerun_rejects_overrides_on_a_non_docs_stage():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
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


def test_review_approved_starts_next_stage_and_threads_its_output_forward():
    # psm -> atl: both mock-validated stages now (see each of their own
    # agent.py), so "the right input" means the right CONTEXT KEY/VALUE
    # threaded forward, not "atl's own output reflects psm's content" —
    # atl's mock output is fixed regardless of input, same as psm's is.
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "psm")
    with patch.object(validator_agent_client, "validate_ecore", return_value=_validation_result()):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    with patch.object(validator_agent_client, "validate_atl", return_value=_validation_result()) as mock_validate_atl:
        result = o.review("psm", approved=True)
        o._last_thread.join(timeout=5)

    assert result == {"status": "started", "stage": "atl"}
    assert o.current_stage == "atl"
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"] == {"stage": "atl", "output": atl_agent._MOCK_CONTENT}
    assert mock_validate_atl.call_count == 1
    # The real point of this test: psm's approved output threaded into
    # atl's own context under the right key.
    assert o.last_context["psm_output"] == psm_agent._MOCK_CONTENT


def test_review_approved_accumulates_outputs_through_generation():
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "atl")
    with patch.object(validator_agent_client, "validate_atl", return_value=_validation_result()):
        o.run_stage({"platform_description": "A GitLab CI platform"})
    with patch.object(validator_agent_client, "validate_acceleo", return_value=_validation_result()):
        o.review("atl", approved=True)
        o._last_thread.join(timeout=5)

    with patch.object(ai_layer_client, "chat", return_value=ok_response("Final summary")) as mock_chat:
        result = o.review("acceleo", approved=True)
        o._last_thread.join(timeout=5)

    assert result == {"status": "started", "stage": "generation"}
    completed = [e for e in o.events if e["type"] == "call_completed"][-1]
    assert completed["data"] == {"stage": "generation", "output": "Final summary"}
    assert mock_chat.call_count == 1
    user_content = mock_chat.call_args.args[0][1]["content"]
    assert atl_agent._MOCK_CONTENT in user_content
    assert acceleo_agent._MOCK_CONTENT in user_content


def test_review_approved_on_last_stage_returns_complete():
    o = pipeline.IntegrationRun()
    o.current_stage_index = len(pipeline.STAGES) - 1
    o.last_completed_stage = "generation"  # stands in for a real run_stage() call, like _fast_forward_to_generation does for current_stage_index
    result = o.review("generation", approved=True)

    assert result == {"status": "complete"}
    assert o.current_stage is None


def test_review_rejected_records_constraint_and_does_not_advance():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    result = o.review("generation", approved=False, correction="Missing artifact retention policy")

    assert result == {"status": "rerun", "stage": "generation"}
    assert o.current_stage == "generation"
    assert o.constraints["generation"] == ["Missing artifact retention policy"]


# --- _validate_review() / record_review() tests ---------------------------------
#
# review() is a thin wrapper over record_review() (it additionally starts the
# next stage running on approval), so validation is only tested once here,
# against record_review() directly.


def test_record_review_rejects_mismatched_stage_id():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    with pytest.raises(ValueError, match="generation"):
        o.record_review("atl", approved=True)


def test_record_review_rejects_missing_correction_when_not_approved():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    with pytest.raises(ValueError, match="correction"):
        o.record_review("generation", approved=False)


def test_record_review_rejects_approval_when_current_stage_never_completed():
    # Nothing has run yet for the current stage - approving it would
    # silently forward the PREVIOUS stage's last_output onward, mislabeled as
    # this stage's own output. Regression test for a real bug: a failed stage's
    # review(approved=True) used to succeed anyway.
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    with pytest.raises(ValueError, match="hasn't completed successfully"):
        o.record_review("generation", approved=True)


def test_record_review_rejects_approval_after_a_failed_attempt():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    with patch.object(ai_layer_client, "chat", side_effect=RuntimeError("all providers exhausted")):
        with pytest.raises(RuntimeError):
            o.run_stage({"platform_description": "A GitLab CI platform"})

    with pytest.raises(ValueError, match="hasn't completed successfully"):
        o.record_review("generation", approved=True)


def test_record_review_rejects_approval_after_a_failed_validation():
    # Same real bug class as the LLM-side test above, for a mock-validated
    # stage's own failure path (raise_if_invalid(), not an LLM error).
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "psm")
    with patch.object(validator_agent_client, "validate_ecore", return_value=_validation_result(valid=False, issues=[
        {"severity": "error", "message": "NoSuchType", "source": None}
    ])):
        with pytest.raises(RuntimeError, match="failed validation"):
            o.run_stage({"platform_description": "A GitLab CI platform"})

    with pytest.raises(ValueError, match="hasn't completed successfully"):
        o.record_review("psm", approved=True)


def test_record_review_approved_advances_without_running_next_stage():
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "psm")
    with patch.object(validator_agent_client, "validate_ecore", return_value=_validation_result()):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    with patch.object(validator_agent_client, "validate_atl") as mock_validate_atl:
        result = o.record_review("psm", approved=True)

    # record_review() itself never reacts to anything it records — no
    # validate call at all, not even for atl's own agent, "advanced" means
    # the caller (review(), or main.py's /review handler) schedules that
    # separately.
    assert mock_validate_atl.call_count == 0
    assert result["status"] == "advanced"
    assert result["stage"] == "atl"
    assert result["context"]["psm_output"] == psm_agent._MOCK_CONTENT
    assert o.current_stage == "atl"
    assert o._last_thread is None  # record_review() itself never starts a run


def test_record_review_approved_on_last_stage_returns_complete():
    o = pipeline.IntegrationRun()
    o.current_stage_index = len(pipeline.STAGES) - 1
    o.last_completed_stage = "generation"  # stands in for a real run_stage() call, like _fast_forward_to_generation does for current_stage_index
    result = o.record_review("generation", approved=True)

    assert result == {"status": "complete"}
    assert o.current_stage is None


def test_record_review_rejected_records_constraint_and_returns_rerun():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    result = o.record_review("generation", approved=False, correction="Missing artifact retention policy")

    assert result == {"status": "rerun", "stage": "generation"}
    assert o.constraints["generation"] == ["Missing artifact retention policy"]


def test_record_review_records_review_approved_and_rejected_events():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("Final summary")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    o.record_review("generation", approved=False, correction="fix the port mapping")

    review_events = [e for e in o.events if e["type"] == "review_rejected"]
    assert len(review_events) == 1
    assert review_events[0]["stage"] == "generation"
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
    _fast_forward_to_generation(o)
    release = threading.Event()

    def _blocking_chat(messages, model=None, tools=None, tool_choice=None):
        # Blocks the background thread's one real chat() call (the
        # generation stage agent's own) until the main thread has asserted
        # busy is True and released it, otherwise a fast/mocked run could
        # finish before this assertion even runs, making the check meaningless.
        release.wait(timeout=5)
        return ok_response("Final summary")

    with patch.object(ai_layer_client, "chat", side_effect=_blocking_chat):
        o.run_stage_async({"platform_description": "A GitLab CI platform"})
        assert o.busy is True
        release.set()
        o._last_thread.join(timeout=5)

    assert o.busy is False


def test_run_stage_async_records_call_completed_on_success():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("Final summary")):
        o.run_stage_async({"platform_description": "A GitLab CI platform"})
        o._last_thread.join(timeout=5)

    types = [e["type"] for e in o.events]
    assert "call_started" in types
    assert "call_completed" in types
    assert "call_failed" not in types
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"] == {"stage": "generation", "output": "Final summary"}


def test_run_stage_async_records_call_failed_on_agent_error():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    with patch.object(ai_layer_client, "chat", side_effect=RuntimeError("all providers exhausted")):
        o.run_stage_async({"platform_description": "A GitLab CI platform"})
        o._last_thread.join(timeout=5)

    types = [e["type"] for e in o.events]
    assert "call_failed" in types
    assert "call_completed" not in types
    failed = next(e for e in o.events if e["type"] == "call_failed")
    assert failed["data"] == {"error": "all providers exhausted"}
    assert o.busy is False


def test_run_stage_async_records_call_failed_on_validation_failure():
    # Same real reporting path, exercised through a mock-validated stage's
    # own failure instead of an LLM error.
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "acceleo")
    with patch.object(validator_agent_client, "validate_acceleo", return_value=_validation_result(valid=False, issues=[
        {"severity": "error", "message": "unresolved import", "source": None}
    ])):
        o.run_stage_async({"platform_description": "A GitLab CI platform"})
        o._last_thread.join(timeout=5)

    types = [e["type"] for e in o.events]
    assert "call_failed" in types
    assert "call_completed" not in types
    failed = next(e for e in o.events if e["type"] == "call_failed")
    assert "failed validation" in failed["data"]["error"]
    assert "unresolved import" in failed["data"]["error"]
    assert o.busy is False
