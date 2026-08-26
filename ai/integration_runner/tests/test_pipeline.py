"""pipeline.py unit tests: the pipeline state machine (STAGES,
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

Most stage-mechanics tests below exercise "atl" as their stand-in generic
placeholder stage (via helpers._fast_forward_to_atl), not "psm": psm is a
real stage now (stages/psm/agent.py calls psm_agent over HTTP, not
ai_layer_client.chat directly, see run_stage()'s own tuple-handling test at
the bottom of this file for its one narrow, documented deviation from every
other stage's plain str return).

Tests verify:
  1. IntegrationRun.run_stage() looks up the current stage's agent (via
     stages/) and reports the current stage.
  2. IntegrationRun.advance_stage()/review() move through STAGES and handle
     approval vs. rejection (constraint recording) correctly.
  3. run_stage()/rerun() pick up constraints recorded via add_constraint() since
     the last run — verifying corrections are actually threaded into the agent's
     prompt, not just stored and left unused.
  4. review() on approval starts the next stage running in the background (not
     just advancing the pointer), threading the approved stage's output into the
     next stage's context under the right f"{stage_id}_output" key, and
     accumulating outputs across approvals so the final generation stage sees
     them all. Because the run happens on a background thread, every mock that
     patches ai_layer_client.chat for one of these calls stays active until the
     thread is joined (IntegrationRun._last_thread.join()), never after the
     `with` block that installed it has already exited, otherwise the thread's
     real work races against the mock being torn down.
  5. record_event() appends a raw event and returns it, with no reaction of
     any kind — no chat() call, nothing else touched.
  6. run_stage_async() sets busy synchronously before the background thread
     starts, and records call_completed/call_failed depending on outcome.
  7. run_stage() accepts either a plain str or a (str, dict) tuple from a
     stage agent, merging the dict into call_completed's own data either way.
"""
import threading
from unittest.mock import patch

import pytest

from clients import ai_layer_client
from integration_runner import pipeline
from helpers import _fast_forward_to_atl, ok_response


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
    _fast_forward_to_atl(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL description")) as mock_chat:
        result = o.run_stage({"platform_description": "A GitLab CI platform"})

    assert mock_chat.call_count == 1
    assert result == {"stage": "atl", "output": "ATL description"}


def test_run_stage_threads_the_chosen_model_into_the_agent_s_context():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    o.model = "mistral-small"
    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL description")) as mock_chat:
        o.run_stage({"platform_description": "A GitLab CI platform"})

    assert mock_chat.call_args.kwargs["model"] == "mistral-small"


def test_run_stage_incorporates_constraints_added_since_the_last_run():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL v1")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    o.add_constraint("atl", "Use kebab-case job names")

    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL v2")) as mock_chat:
        o.run_stage({"platform_description": "A GitLab CI platform"})

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "Use kebab-case job names" in user_content


def test_rerun_replays_the_last_context_and_picks_up_new_constraints():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    # atl_stage's real input is psm_output alone (no platform_description
    # fallback the way pim/psm have — see stages/atl/agent.py), so that's
    # what a direct/standalone call here needs to supply.
    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL v1")):
        o.run_stage({"psm_output": "A GitLab CI platform PSM"})

    o.add_constraint("atl", "Include a lint stage")

    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL v2")) as mock_chat:
        result = o.rerun()
        o._last_thread.join(timeout=5)

    assert result == {"status": "started", "stage": "atl"}
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"] == {"stage": "atl", "output": "ATL v2"}
    assert mock_chat.call_count == 1
    sent_content = mock_chat.call_args.args[0][1]["content"]
    assert sent_content.startswith("A GitLab CI platform PSM")
    assert "Include a lint stage" in sent_content


def test_rerun_rejects_overrides_on_a_non_docs_stage():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
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
    o.add_constraint("atl", "Use kebab-case job names")
    o.add_constraint("atl", "Include a lint stage")

    assert o.constraints["atl"] == ["Use kebab-case job names", "Include a lint stage"]


def test_add_constraint_records_a_real_constraint_added_event():
    # Every real action becomes a real, persisted event, the same rule
    # call_started/call_completed/review_approved already follow — a
    # constraint recorded via add_constraint() shouldn't be the one action
    # that leaves no trace in this run's event log.
    o = pipeline.IntegrationRun()
    o.add_constraint("atl", "Use kebab-case job names")

    constraint_events = [e for e in o.events if e["type"] == "constraint_added"]
    assert len(constraint_events) == 1
    assert constraint_events[0]["stage"] == "atl"
    assert constraint_events[0]["data"] == {"constraint": "Use kebab-case job names"}


def test_review_approved_starts_next_stage_and_it_completes_with_the_right_input():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL description")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    with patch.object(ai_layer_client, "chat", return_value=ok_response("Acceleo template")) as mock_chat:
        result = o.review("atl", approved=True)
        o._last_thread.join(timeout=5)

    assert result == {"status": "started", "stage": "acceleo"}
    assert o.current_stage == "acceleo"
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"] == {"stage": "acceleo", "output": "Acceleo template"}
    assert mock_chat.call_count == 1
    assert mock_chat.call_args.args[0][1]["content"].startswith("ATL description")


def test_review_approved_accumulates_outputs_through_generation():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL rules")):
        o.run_stage({"platform_description": "A GitLab CI platform"})
    with patch.object(ai_layer_client, "chat", return_value=ok_response("Acceleo template")):
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
    assert "ATL rules" in user_content
    assert "Acceleo template" in user_content


def test_review_approved_on_last_stage_returns_complete():
    o = pipeline.IntegrationRun()
    o.current_stage_index = len(pipeline.STAGES) - 1
    o.last_completed_stage = "generation"  # stands in for a real run_stage() call, like _fast_forward_to_atl does for current_stage_index
    result = o.review("generation", approved=True)

    assert result == {"status": "complete"}
    assert o.current_stage is None


def test_review_rejected_records_constraint_and_does_not_advance():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    result = o.review("atl", approved=False, correction="Missing artifact retention policy")

    assert result == {"status": "rerun", "stage": "atl"}
    assert o.current_stage == "atl"
    assert o.constraints["atl"] == ["Missing artifact retention policy"]


# --- _validate_review() / record_review() tests ---------------------------------
#
# review() is a thin wrapper over record_review() (it additionally starts the
# next stage running on approval), so validation is only tested once here,
# against record_review() directly.


def test_record_review_rejects_mismatched_stage_id():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    with pytest.raises(ValueError, match="atl"):
        o.record_review("acceleo", approved=True)


def test_record_review_rejects_missing_correction_when_not_approved():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    with pytest.raises(ValueError, match="correction"):
        o.record_review("atl", approved=False)


def test_record_review_rejects_approval_when_current_stage_never_completed():
    # Nothing has run yet for the current stage (atl) - approving it would
    # silently forward the PREVIOUS stage's last_output onward, mislabeled as
    # atl's own output. Regression test for a real bug: a failed stage's
    # review(approved=True) used to succeed anyway.
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    with pytest.raises(ValueError, match="hasn't completed successfully"):
        o.record_review("atl", approved=True)


def test_record_review_rejects_approval_after_a_failed_attempt():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    with patch.object(ai_layer_client, "chat", side_effect=RuntimeError("all providers exhausted")):
        with pytest.raises(RuntimeError):
            o.run_stage({"platform_description": "A GitLab CI platform"})

    with pytest.raises(ValueError, match="hasn't completed successfully"):
        o.record_review("atl", approved=True)


def test_record_review_approved_advances_without_running_next_stage():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL description")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    with patch.object(ai_layer_client, "chat") as mock_chat:
        result = o.record_review("atl", approved=True)

    # record_review() itself never reacts to anything it records — no chat()
    # call at all, not even for acceleo's own agent, "advanced" means the
    # caller (review(), or main.py's /review handler) schedules that
    # separately.
    assert mock_chat.call_count == 0
    assert result["status"] == "advanced"
    assert result["stage"] == "acceleo"
    assert result["context"]["atl_output"] == "ATL description"
    assert o.current_stage == "acceleo"
    assert o._last_thread is None  # record_review() itself never starts a run


def test_record_review_approved_on_last_stage_returns_complete():
    o = pipeline.IntegrationRun()
    o.current_stage_index = len(pipeline.STAGES) - 1
    o.last_completed_stage = "generation"  # stands in for a real run_stage() call, like _fast_forward_to_atl does for current_stage_index
    result = o.record_review("generation", approved=True)

    assert result == {"status": "complete"}
    assert o.current_stage is None


def test_record_review_rejected_records_constraint_and_returns_rerun():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    result = o.record_review("atl", approved=False, correction="Missing artifact retention policy")

    assert result == {"status": "rerun", "stage": "atl"}
    assert o.constraints["atl"] == ["Missing artifact retention policy"]


def test_record_review_records_review_approved_and_rejected_events():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL description")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    o.record_review("atl", approved=False, correction="fix the port mapping")

    review_events = [e for e in o.events if e["type"] == "review_rejected"]
    assert len(review_events) == 1
    assert review_events[0]["stage"] == "atl"
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
    _fast_forward_to_atl(o)
    release = threading.Event()

    def _blocking_chat(messages, model=None, tools=None, tool_choice=None):
        # Blocks the background thread's one real chat() call (the atl
        # stage agent's own) until the main thread has asserted busy is
        # True and released it, otherwise a fast/mocked run could finish
        # before this assertion even runs, making the check meaningless.
        release.wait(timeout=5)
        return ok_response("ATL description")

    with patch.object(ai_layer_client, "chat", side_effect=_blocking_chat):
        o.run_stage_async({"platform_description": "A GitLab CI platform"})
        assert o.busy is True
        release.set()
        o._last_thread.join(timeout=5)

    assert o.busy is False


def test_run_stage_async_records_call_completed_on_success():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL description")):
        o.run_stage_async({"platform_description": "A GitLab CI platform"})
        o._last_thread.join(timeout=5)

    types = [e["type"] for e in o.events]
    assert "call_started" in types
    assert "call_completed" in types
    assert "call_failed" not in types
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"] == {"stage": "atl", "output": "ATL description"}


def test_run_stage_async_records_call_failed_on_agent_error():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    with patch.object(ai_layer_client, "chat", side_effect=RuntimeError("all providers exhausted")):
        o.run_stage_async({"platform_description": "A GitLab CI platform"})
        o._last_thread.join(timeout=5)

    types = [e["type"] for e in o.events]
    assert "call_failed" in types
    assert "call_completed" not in types
    failed = next(e for e in o.events if e["type"] == "call_failed")
    assert failed["data"] == {"error": "all providers exhausted"}
    assert o.busy is False


# --- run_stage()'s (str, dict) tuple handling ------------------------------------


def test_run_stage_merges_a_tuple_return_into_call_completed_data():
    o = pipeline.IntegrationRun()
    o.current_stage_index = pipeline.STAGES.index("psm")
    with patch("integration_runner.stages.stage_agents", {
        **pipeline.stages.stage_agents,
        "psm": lambda context: ("<new-ecore/>", {"mode": "generation", "rounds": 2}),
    }):
        result = o.run_stage({"platform_description": "TeamCity"})

    assert result == {"stage": "psm", "output": "<new-ecore/>", "mode": "generation", "rounds": 2}
    assert o.last_output == "<new-ecore/>"


def test_run_stage_treats_a_plain_str_return_exactly_as_before():
    o = pipeline.IntegrationRun()
    _fast_forward_to_atl(o)
    with patch.object(ai_layer_client, "chat", return_value=ok_response("ATL description")):
        result = o.run_stage({"platform_description": "A GitLab CI platform"})

    assert result == {"stage": "atl", "output": "ATL description"}
    assert "mode" not in result
