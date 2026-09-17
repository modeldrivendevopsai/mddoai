"""pipeline.py unit tests: the pipeline state machine (STAGES,
class IntegrationRun, run_stage, rerun, advance_stage, add_constraint,
review, record_review, record_event, run_stage_async). No real API calls,
execution_agent_client's execute_atl/execute_acceleo and
validator_agent_client's validate_* functions are mocked.

Generic pipeline-mechanics tests (does run_stage() call the right agent and
report its output, does review() advance and start the next stage, ...) run
against "generation" (real now - see stages/generation/agent.py -
_fast_forward_to_generation() in helpers.py), mocking its own real
execution_agent_client boundary via helpers.py's own
_mocked_generation_execution(). generation no longer reads
context["constraints"] at all (there's nothing a real ATL/Acceleo execution
could do with a free-text correction), so a test about a correction being
threaded into a real agent call uses atl instead, mocking
atl_agent_client.run_atl directly. Tests that specifically need a
non-terminal transition (review() advancing to a NEXT stage, not completing
the run) use pim directly, with validator_agent_client mocked, or
psm/atl/acceleo directly, with each of their own real *_agent_client mocked
instead.

record_event() only ever appends a raw fact and returns, it has no
narration/reactor concept at all (that moved to orchestrator/chat_log.py
when integration_runner became its own service), so unlike before this
split, exactly ONE real agent call happens per stage transition here, never
two.

Tests verify:
  1. IntegrationRun.run_stage() looks up the current stage's agent (via
     stages/) and reports the current stage, threading the run's own
     run_id into the agent's context alongside model/constraints.
  2. IntegrationRun.advance_stage()/review() move through STAGES and handle
     approval vs. rejection (constraint recording) correctly.
  3. run_stage()/rerun() pick up constraints recorded via add_constraint() since
     the last run, verifying corrections are actually threaded into the agent's
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
     any kind, no chat() call, nothing else touched.
  6. run_stage_async() sets busy synchronously before the background thread
     starts, and records call_completed/call_failed depending on outcome.
"""
import threading
import time
from unittest.mock import patch

import pytest

from clients import acceleo_agent_client, atl_agent_client, execution_agent_client, psm_agent_client, validator_agent_client
from integration_runner import pipeline
from integration_runner.stages.pim import agent as pim_agent
from helpers import (
    _MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME,
    _acceleo_generation_result,
    _atl_generation_result,
    _fast_forward_to,
    _fast_forward_to_generation,
    _mocked_generation_execution,
    _psm_generation_result,
    _validation_result,
)


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
    with _mocked_generation_execution() as (mock_execute_atl, mock_execute_acceleo):
        result = o.run_stage({"platform_description": "A GitLab CI platform", "atl_output": _MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME})

    assert mock_execute_atl.call_count == 1
    assert mock_execute_acceleo.call_count == 1
    assert result == {
        "stage": "generation", "output": "stages: []\n",
        "psm_instance": "<gitlabMM:Pipeline/>", "generated_files": {".gitlab-ci.yml": "stages: []\n"},
    }


def test_run_stage_threads_the_chosen_model_into_the_agent_s_context():
    # atl, not generation: generation's own real work (execution_agent's
    # execute_atl/execute_acceleo) takes no model choice at all, unlike a
    # real LLM call - atl_agent_client.run_atl still does, matching every
    # other real, config-driven-prompt stage.
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "atl")
    o.model = "mistral-small"
    with patch.object(atl_agent_client, "run_atl", return_value=_atl_generation_result()) as mock_run_atl:
        o.run_stage({"platform_description": "A GitLab CI platform"})

    assert mock_run_atl.call_args.kwargs["model"] == "mistral-small"


def test_run_stage_threads_the_run_id_into_the_agent_s_context(tmp_path):
    # stages/_validation.py's persist_attempt() needs a real run_id to
    # write runs/<run_id>/<stage>/... under, this is what supplies it.
    # pim, not psm: psm's own real boundary is psm_agent_client, not
    # validator_agent_client directly (see stages/psm/agent.py's own
    # docstring), this test's real point is generic run_id threading, pim
    # is the vehicle that matches _validation.py's own direct-call shape.
    fixture_path = tmp_path / "pimMM.ecore"
    fixture_path.write_text("<ecore:EPackage/>", encoding="utf-8")

    o = pipeline.IntegrationRun(run_id="fixed-run-id")
    _fast_forward_to(o, "pim")
    with patch.object(pim_agent, "PIM_METAMODEL_PATH", fixture_path):
        with patch.object(validator_agent_client, "validate_ecore", return_value=_validation_result()) as mock_validate:
            with patch.object(pim_agent, "persist_attempt") as mock_persist:
                o.run_stage({"platform_description": "A GitLab CI platform"})

    assert mock_validate.call_count == 1
    mock_persist.assert_called_once_with("fixed-run-id", "pim", "pimMM.ecore", "<ecore:EPackage/>", _validation_result())


def test_run_stage_incorporates_constraints_added_since_the_last_run():
    # atl, not generation: generation no longer reads context["constraints"]
    # at all (there's nothing a real ATL/Acceleo execution could do with a
    # free-text correction) - atl_agent_client.run_atl still receives and
    # forwards it, matching every other real, config-driven-prompt stage.
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "atl")
    with patch.object(atl_agent_client, "run_atl", return_value=_atl_generation_result()):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    o.add_constraint("atl", "Mention the rollback step explicitly")

    with patch.object(atl_agent_client, "run_atl", return_value=_atl_generation_result()) as mock_run_atl:
        o.run_stage({"platform_description": "A GitLab CI platform"})

    assert mock_run_atl.call_args.kwargs["constraints"] == ["Mention the rollback step explicitly"]


def test_run_stage_persists_round_constraints_as_real_constraints():
    # generation_toolkit.generation_agent.run_with_retry's own real
    # generate-validate-retry loop can spend real LLM rounds discovering
    # fixes for a persistently-invalid generation - see its own
    # round_constraints return value. Confirmed for real: a stuck run
    # needed 5 external retries, each re-discovering the same real
    # mistakes from scratch, before this was wired up.
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "atl")
    result_with_learning = {**_atl_generation_result(valid=False), "round_constraints": ["Fix: dangling reference"]}
    with patch.object(atl_agent_client, "run_atl", return_value=result_with_learning):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    assert o.constraints["atl"] == ["Fix: dangling reference"]


def test_run_stage_does_not_duplicate_a_round_constraint_already_recorded():
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "atl")
    o.add_constraint("atl", "Fix: dangling reference")
    result_with_learning = {
        **_atl_generation_result(valid=False),
        "round_constraints": ["Fix: dangling reference", "Fix: a second problem"],
    }
    with patch.object(atl_agent_client, "run_atl", return_value=result_with_learning):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    assert o.constraints["atl"] == ["Fix: dangling reference", "Fix: a second problem"]


def test_run_stage_does_not_leak_round_constraints_into_the_returned_event_data():
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "atl")
    result_with_learning = {**_atl_generation_result(), "round_constraints": ["Fix: something"]}
    with patch.object(atl_agent_client, "run_atl", return_value=result_with_learning):
        result = o.run_stage({"platform_description": "A GitLab CI platform"})

    assert "round_constraints" not in result


def test_rerun_replays_the_last_context_and_picks_up_new_constraints():
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "atl")
    with patch.object(atl_agent_client, "run_atl", return_value=_atl_generation_result(artifact="v1")):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    o.add_constraint("atl", "Mention the rollback step explicitly")

    with patch.object(atl_agent_client, "run_atl", return_value=_atl_generation_result(artifact="v2")) as mock_run_atl:
        result = o.rerun()
        o._last_thread.join(timeout=5)

    assert result == {"status": "started", "stage": "atl"}
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"]["stage"] == "atl"
    assert completed["data"]["output"] == "v2"
    assert mock_run_atl.call_count == 1
    assert mock_run_atl.call_args.kwargs["constraints"] == ["Mention the rollback step explicitly"]


def test_rerun_rejects_overrides_on_a_stage_with_no_recognized_overrides():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    with pytest.raises(ValueError, match="only acceleo and atl and docs and psm do"):
        o.rerun({"hint": "not applicable here"})


def test_rerun_rejects_a_key_the_current_stage_doesnt_recognize():
    # psm recognizes "mock" but not a different stage's own shape - an
    # override key valid elsewhere is rejected here too, not silently
    # dropped or silently accepted.
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "pim")
    o.last_completed_stage = "pim"
    o.last_output = "PIM: jobs/stages/triggers"
    o.last_context = {"platform_description": "A brand new platform"}
    o.review("pim", approved=True)

    with pytest.raises(ValueError, match=r"doesn't recognize override\(s\) \['hint'\], it only accepts \['mock'\]"):
        o.rerun({"hint": "not applicable to psm"})


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
    # call_started/call_completed/review_approved already follow, a
    # constraint recorded via add_constraint() shouldn't be the one action
    # that leaves no trace in this run's event log.
    o = pipeline.IntegrationRun()
    o.add_constraint("psm", "Use kebab-case job names")

    constraint_events = [e for e in o.events if e["type"] == "constraint_added"]
    assert len(constraint_events) == 1
    assert constraint_events[0]["stage"] == "psm"
    assert constraint_events[0]["data"] == {"constraint": "Use kebab-case job names"}


def test_review_approved_starts_next_stage_and_threads_its_output_forward():
    # atl -> acceleo: both real, thin-proxy stages using their own real
    # *_agent_client boundary (see each of their own agent.py). acceleo is
    # in _REQUIRES_MANUAL_START (a real, editable prompt config), so
    # approving atl advances but does NOT auto-run it - the real point of
    # this test (atl's approved output threaded into acceleo's own context
    # under the right key) still holds, checked via last_context instead of
    # a completed event. rerun() is the real "Generate" trigger, same
    # pattern psm's own test_a_pending_manual_start_stage_can_be_started_via_rerun
    # already establishes.
    atl_result = _atl_generation_result(artifact="module pim2gitlab; ...")
    acceleo_result = _acceleo_generation_result()
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "atl")
    with patch.object(atl_agent_client, "run_atl", return_value=atl_result):
        o.run_stage({"platform_description": "A GitLab CI platform"})

    result = o.review("atl", approved=True)

    assert result == {"status": "advanced_pending", "stage": "acceleo"}
    assert o.current_stage == "acceleo"
    assert o._last_thread is None
    # The real point of this test: atl's approved output threaded into
    # acceleo's own context under the right key.
    assert o.last_context["atl_output"] == atl_result["artifact"]

    with patch.object(acceleo_agent_client, "run_acceleo", return_value=acceleo_result) as mock_run_acceleo:
        o.rerun()
        o._last_thread.join(timeout=5)

    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"]["stage"] == "acceleo"
    assert completed["data"]["output"] == acceleo_result["artifact"]
    assert mock_run_acceleo.call_count == 1


def test_review_approved_into_psm_does_not_auto_run_it():
    # psm is in _REQUIRES_MANUAL_START (it has a real, editable prompt
    # config): arriving there advances the pipeline but does not fire
    # psm_agent_client.run_psm() - a human gets to review/edit the prompt
    # first, the real point of this test.
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "pim")
    o.last_completed_stage = "pim"
    o.last_output = "PIM: jobs/stages/triggers"
    o.last_context = {"platform_description": "A brand new platform"}

    with patch.object(psm_agent_client, "run_psm") as mock_run_psm:
        result = o.review("pim", approved=True)

    assert result == {"status": "advanced_pending", "stage": "psm"}
    assert o.current_stage == "psm"
    assert mock_run_psm.call_count == 0
    assert o._last_thread is None
    assert o.busy is False
    # The next stage's real, already-computed context is stored, not
    # thrown away - a later rerun()/Retry click needs this to actually run
    # psm for the first time with the right input.
    assert o.last_context["pim_output"] == "PIM: jobs/stages/triggers"


def test_a_pending_manual_start_stage_can_be_started_via_rerun():
    # The existing rerun() mechanism ("run the current stage using
    # last_context plus overrides") is deliberately reused as the real
    # "Generate" trigger for a pending manual-start stage - no new backend
    # action needed, the panel's existing Retry/Generate button already
    # wires to this.
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "pim")
    o.last_completed_stage = "pim"
    o.last_output = "PIM: jobs/stages/triggers"
    o.last_context = {"platform_description": "A brand new platform"}

    with patch.object(psm_agent_client, "run_psm", return_value=_psm_generation_result()) as mock_run_psm:
        o.review("pim", approved=True)
        o.rerun()
        o._last_thread.join(timeout=5)

    assert mock_run_psm.call_count == 1
    assert o.current_stage == "psm"


def test_a_pending_manual_start_stage_started_via_stage_run_keeps_its_real_context():
    # Real regression: /stage/run (start_stage_run(), the run_stage tool's
    # own real target) used to set self.last_context to exactly whatever
    # the caller passed as context, discarding everything review() had
    # just assembled into it - confirmed for real against a live run whose
    # own atl_output reached the generation stage as an empty string after
    # going through exactly this path with context={}, the same {} the
    # orchestrator's own run_stage tool sends whenever there's nothing
    # extra to add (its own schema: "rarely needs anything supplied
    # manually"). start_stage_run() must layer the given context onto
    # self.last_context instead, mirroring rerun()'s own established merge.
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "pim")
    o.last_completed_stage = "pim"
    o.last_output = "PIM: jobs/stages/triggers"
    o.last_context = {"platform_description": "A brand new platform"}

    with patch.object(psm_agent_client, "run_psm", return_value=_psm_generation_result()) as mock_run_psm:
        o.review("pim", approved=True)
        o.start_stage_run({})
        o._last_thread.join(timeout=5)

    assert mock_run_psm.call_count == 1
    # The real point: platform_description and pim_output, both already in
    # last_context before this call, must have survived it.
    assert o.last_context["platform_description"] == "A brand new platform"
    assert o.last_context["pim_output"] == "PIM: jobs/stages/triggers"


def test_start_stage_run_still_lets_a_given_key_override_last_context():
    # The merge goes last_context first, then the given context - an
    # explicit override (the tool's own "extra or overriding input") must
    # still win, not be silently shadowed by the newly-added merge.
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "pim")
    o.last_completed_stage = "pim"
    o.last_output = "PIM: jobs/stages/triggers"
    o.last_context = {"platform_description": "A brand new platform"}

    with patch.object(psm_agent_client, "run_psm", return_value=_psm_generation_result()):
        o.review("pim", approved=True)
        o.start_stage_run({"platform_description": "An overridden platform"})
        o._last_thread.join(timeout=5)

    assert o.last_context["platform_description"] == "An overridden platform"


def test_pending_manual_start_stage_accepts_a_mock_override_on_rerun():
    # psm is the second stage in _STAGE_OVERRIDE_KEYS (after docs) -
    # a rerun override reaches psm_agent_client.run_psm's own mock kwarg via
    # stages/psm/agent.py's context.get("mock"), the real "test the prompt
    # builder without a real, slow, billed LLM call" path.
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "pim")
    o.last_completed_stage = "pim"
    o.last_output = "PIM: jobs/stages/triggers"
    o.last_context = {"platform_description": "A brand new platform"}

    with patch.object(psm_agent_client, "run_psm", return_value=_psm_generation_result()) as mock_run_psm:
        o.review("pim", approved=True)
        o.rerun({"mock": True})
        o._last_thread.join(timeout=5)

    assert mock_run_psm.call_args.kwargs.get("mock") is True


def test_review_approved_accumulates_outputs_through_generation():
    atl_result = _atl_generation_result(artifact=_MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME)
    acceleo_result = _acceleo_generation_result(artifact="[module generate('gitlab')] ...")
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "psm")
    with patch.object(psm_agent_client, "run_psm", return_value=_psm_generation_result(artifact="<ecore/>")):
        o.run_stage({"platform_description": "A GitLab CI platform"})
    o.review("psm", approved=True)
    with patch.object(atl_agent_client, "run_atl", return_value=atl_result):
        o.rerun()
        o._last_thread.join(timeout=5)
    # acceleo is in _REQUIRES_MANUAL_START, so approving atl only advances -
    # rerun() is the real trigger, same as the test above.
    o.review("atl", approved=True)
    with patch.object(acceleo_agent_client, "run_acceleo", return_value=acceleo_result):
        o.rerun()
        o._last_thread.join(timeout=5)

    with _mocked_generation_execution() as (mock_execute_atl, mock_execute_acceleo):
        result = o.review("acceleo", approved=True)
        o._last_thread.join(timeout=5)

    assert result == {"status": "started", "stage": "generation"}
    completed = [e for e in o.events if e["type"] == "call_completed"][-1]
    assert completed["data"] == {
        "stage": "generation", "output": "stages: []\n",
        "psm_instance": "<gitlabMM:Pipeline/>", "generated_files": {".gitlab-ci.yml": "stages: []\n"},
    }
    # The real point of this test: atl's and acceleo's own real, approved
    # output actually reached generation's own real execution call, not
    # just advanced the pointer.
    assert mock_execute_atl.call_args[0][0] == atl_result["artifact"]
    assert mock_execute_atl.call_args[0][2] == "<ecore/>"
    assert mock_execute_acceleo.call_args[0][0] == acceleo_result["artifact"]


def test_review_on_a_busy_run_refuses_without_advancing_or_recording():
    # review() claims busy before record_review() touches anything, so a
    # racing stage start can't leave the pipeline advanced-but-not-running.
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    o.last_completed_stage = "generation"  # a real run_stage() would have set this
    o.claim_busy()  # stands in for a stage run already in flight

    try:
        with pytest.raises(pipeline.BusyError):
            o.review("generation", approved=True)
    finally:
        o.release_busy()

    assert o.current_stage == "generation"  # not advanced
    assert not any(e["type"] == "review_approved" for e in o.events)  # not recorded


def test_review_rejected_releases_the_busy_claim():
    # A rejection claims busy (to keep two near-simultaneous rejections of
    # the same stage from both mutating state) but starts no stage, so it
    # must hand the claim back once record_review() returns.
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)

    with patch.object(o, "claim_busy", wraps=o.claim_busy) as mock_claim:
        o.review("generation", approved=False, correction="needs retries")

    mock_claim.assert_called_once()
    assert o.busy is False


def test_review_approved_on_last_stage_releases_the_busy_claim():
    # Same reasoning as the rejection case above: completing the final
    # stage never starts a thread either, so its claim must also come back.
    o = pipeline.IntegrationRun()
    o.current_stage_index = len(pipeline.STAGES) - 1
    o.last_completed_stage = "generation"

    with patch.object(o, "claim_busy", wraps=o.claim_busy) as mock_claim:
        result = o.review("generation", approved=True)

    mock_claim.assert_called_once()
    assert result == {"status": "complete"}
    assert o.busy is False


def test_two_concurrent_rejections_of_the_same_stage_do_not_both_apply():
    # The double-submit hazard claim_busy() exists to close: two
    # near-simultaneous review() calls on the same stage must not both
    # mutate state, even though a rejection alone never starts a thread.
    # A losing claim_busy() raises before record_review() is ever reached,
    # so the two threads can't rendezvous on a shared barrier the way
    # run_stage_async()'s own equivalent test does (the loser would never
    # arrive at it) - record_review() is slowed down instead, one-sided,
    # so the first thread is still holding the claim by the time the second
    # one's own claim_busy() runs.
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    real_record_review = o.record_review

    def _slow_record_review(*args, **kwargs):
        time.sleep(0.1)
        return real_record_review(*args, **kwargs)

    results = []
    errors = []

    def reject():
        try:
            results.append(o.review("generation", approved=False, correction="dup"))
        except pipeline.BusyError as e:
            errors.append(e)

    with patch.object(o, "record_review", side_effect=_slow_record_review):
        t1 = threading.Thread(target=reject)
        t2 = threading.Thread(target=reject)
        t1.start()
        time.sleep(0.02)  # head start, well inside t1's own 0.1s slow record_review
        t2.start()
        t1.join(timeout=5)
        t2.join(timeout=5)

    assert len(errors) == 1  # exactly one loses the race
    assert len(results) == 1
    assert o.constraints["generation"] == ["dup"]  # recorded exactly once, not twice
    assert len([e for e in o.events if e["type"] == "review_rejected"]) == 1


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
    with _mocked_generation_execution(execute_atl_kwargs={"side_effect": RuntimeError("execution-agent unreachable")}):
        with pytest.raises(RuntimeError):
            o.run_stage({"platform_description": "A GitLab CI platform", "atl_output": _MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME})

    with pytest.raises(ValueError, match="hasn't completed successfully"):
        o.record_review("generation", approved=True)


def test_record_review_rejects_approval_after_a_failed_validation():
    # Same real bug class as the LLM-side test above, for a mock-validated
    # stage's own failure path (raise_if_invalid(), not an LLM error). pim,
    # not psm: psm's own generation-mode failure deliberately does NOT raise
    # (see stages/psm/agent.py's own docstring) - it stays a normal
    # call_completed with the real validation detail, so this raise_if_invalid
    # regression test needs a stage that actually still uses it.
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "pim")
    with patch.object(validator_agent_client, "validate_ecore", return_value=_validation_result(valid=False, issues=[
        {"severity": "error", "message": "NoSuchType", "source": None}
    ])):
        with pytest.raises(RuntimeError, match="failed validation"):
            o.run_stage({"platform_description": "A GitLab CI platform"})

    with pytest.raises(ValueError, match="hasn't completed successfully"):
        o.record_review("pim", approved=True)


def test_record_review_approved_advances_without_running_next_stage(tmp_path):
    # pim, with psm as the next stage: record_review() never actually runs
    # the next stage (unlike review()), so psm becoming current here never
    # exercises its own real psm_agent_client boundary at all - safe to use
    # regardless of psm's own real implementation.
    fixture_path = tmp_path / "pimMM.ecore"
    fixture_path.write_text("<ecore:EPackage/>", encoding="utf-8")

    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "pim")
    with patch.object(pim_agent, "PIM_METAMODEL_PATH", fixture_path):
        with patch.object(validator_agent_client, "validate_ecore", return_value=_validation_result()):
            o.run_stage({"platform_description": "A GitLab CI platform"})

    with patch.object(psm_agent_client, "run_psm") as mock_run_psm:
        result = o.record_review("pim", approved=True)

    # record_review() itself never reacts to anything it records, no
    # real call at all, not even for psm's own agent, "advanced" means
    # the caller (review(), or main.py's /review handler) schedules that
    # separately.
    assert mock_run_psm.call_count == 0
    assert result["status"] == "advanced"
    assert result["stage"] == "psm"
    assert result["context"]["pim_output"] == "<ecore:EPackage/>"
    assert o.current_stage == "psm"
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
    with _mocked_generation_execution():
        o.run_stage({"platform_description": "A GitLab CI platform", "atl_output": _MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME})

    o.record_review("generation", approved=False, correction="fix the port mapping")

    review_events = [e for e in o.events if e["type"] == "review_rejected"]
    assert len(review_events) == 1
    assert review_events[0]["stage"] == "generation"
    assert review_events[0]["data"] == {"correction": "fix the port mapping"}


# --- record_event() / run_stage_async() tests -----------------------------------


def test_record_event_appends_a_raw_event_and_returns_it_without_any_reaction():
    o = pipeline.IntegrationRun()
    with patch.object(execution_agent_client, "execute_atl") as mock_execute_atl:
        event = o.record_event("call_started", "docs", {"url": "https://x"})

    assert mock_execute_atl.call_count == 0  # no reaction of any kind, unlike before this split
    assert event["type"] == "call_started"
    assert event["data"] == {"url": "https://x"}
    assert o.events == [event]


def test_run_stage_async_sets_busy_synchronously_before_the_thread_finishes():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    release = threading.Event()

    def _blocking_execute_atl(*args, **kwargs):
        # Blocks the background thread's one real execute_atl() call (the
        # generation stage agent's own) until the main thread has asserted
        # busy is True and released it, otherwise a fast/mocked run could
        # finish before this assertion even runs, making the check meaningless.
        release.wait(timeout=5)
        return "<gitlabMM:Pipeline/>"

    with _mocked_generation_execution(execute_atl_kwargs={"side_effect": _blocking_execute_atl}):
        o.run_stage_async({"platform_description": "A GitLab CI platform", "atl_output": _MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME})
        assert o.busy is True
        release.set()
        o._last_thread.join(timeout=5)

    assert o.busy is False


def test_run_stage_async_refuses_a_second_start_while_one_is_in_flight():
    # The route handlers' own pre-flight `if run.busy` check has a window
    # before busy is actually set; run_stage_async()'s claim closes it, so a
    # second start against the same run raises BusyError instead of spawning
    # a second thread that would race the first on last_output/last_context/
    # current_stage_index and interleave the event log.
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    release = threading.Event()
    context = {"platform_description": "A GitLab CI platform", "atl_output": _MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME}

    def _blocking_execute_atl(*args, **kwargs):
        release.wait(timeout=5)
        return "<gitlabMM:Pipeline/>"

    with _mocked_generation_execution(execute_atl_kwargs={"side_effect": _blocking_execute_atl}):
        o.run_stage_async(context)
        with pytest.raises(pipeline.BusyError):
            o.run_stage_async(context)
        release.set()
        o._last_thread.join(timeout=5)

    # Only the first thread ever ran: exactly one call_started.
    assert [e["type"] for e in o.events].count("call_started") == 1
    assert o.busy is False
    # And once idle, a start is accepted again.
    with _mocked_generation_execution():
        o.run_stage_async(context)
        o._last_thread.join(timeout=5)
    assert [e["type"] for e in o.events].count("call_started") == 2


def test_run_stage_async_claim_is_atomic_under_real_concurrent_contention():
    # Many threads racing the claim at once: exactly one wins, every other
    # gets BusyError, never two threads spawned. Repeated with a fresh
    # Barrier each round so the release is as close to simultaneous as the
    # OS scheduler allows (a single lucky run proves nothing about a race).
    thread_count = 12
    for _ in range(25):
        o = pipeline.IntegrationRun()
        _fast_forward_to_generation(o)
        barrier = threading.Barrier(thread_count)
        outcomes: list[str] = []
        outcomes_lock = threading.Lock()
        release = threading.Event()

        def _blocking_execute_atl(*args, **kwargs):
            release.wait(timeout=5)
            return "<gitlabMM:Pipeline/>"

        def _claim():
            barrier.wait(timeout=5)
            try:
                o.run_stage_async({
                    "platform_description": "A GitLab CI platform",
                    "atl_output": _MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME,
                })
                with outcomes_lock:
                    outcomes.append("won")
            except pipeline.BusyError:
                with outcomes_lock:
                    outcomes.append("refused")

        with _mocked_generation_execution(execute_atl_kwargs={"side_effect": _blocking_execute_atl}):
            threads = [threading.Thread(target=_claim) for _ in range(thread_count)]
            for t in threads:
                t.start()
            for t in threads:
                t.join(timeout=5)
            release.set()
            if o._last_thread is not None:
                o._last_thread.join(timeout=5)

        assert outcomes.count("won") == 1, outcomes
        assert outcomes.count("refused") == thread_count - 1, outcomes
        assert [e["type"] for e in o.events].count("call_started") == 1


def test_run_stage_async_records_call_completed_on_success():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    with _mocked_generation_execution():
        o.run_stage_async({"platform_description": "A GitLab CI platform", "atl_output": _MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME})
        o._last_thread.join(timeout=5)

    types = [e["type"] for e in o.events]
    assert "call_started" in types
    assert "call_completed" in types
    assert "call_failed" not in types
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"] == {
        "stage": "generation", "output": "stages: []\n",
        "psm_instance": "<gitlabMM:Pipeline/>", "generated_files": {".gitlab-ci.yml": "stages: []\n"},
    }


def test_run_stage_async_records_call_failed_on_agent_error():
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    with _mocked_generation_execution(execute_atl_kwargs={"side_effect": RuntimeError("all providers exhausted")}):
        o.run_stage_async({"platform_description": "A GitLab CI platform", "atl_output": _MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME})
        o._last_thread.join(timeout=5)

    types = [e["type"] for e in o.events]
    assert "call_failed" in types
    assert "call_completed" not in types
    failed = next(e for e in o.events if e["type"] == "call_failed")
    assert failed["data"] == {"error": "ATL execution failed: all providers exhausted"}
    assert o.busy is False


def test_run_stage_async_records_a_failed_call_s_own_extra_data():
    # A raised exception's own real "extra" attribute (see
    # stages/generation/agent.py's own RuntimeError.extra) rides along
    # into call_failed's data - generation attaches the real psm_instance
    # ATL already produced when Acceleo then fails, so a human looking at
    # the failed run can still see it, not just the bare error string.
    o = pipeline.IntegrationRun()
    _fast_forward_to_generation(o)
    with _mocked_generation_execution(execute_acceleo_kwargs={"side_effect": RuntimeError("template crashed")}):
        o.run_stage_async({"platform_description": "A GitLab CI platform", "atl_output": _MINIMAL_ATL_WITH_OUTPUT_MODEL_NAME})
        o._last_thread.join(timeout=5)

    failed = next(e for e in o.events if e["type"] == "call_failed")
    assert failed["data"] == {
        "error": "Acceleo execution failed (ATL succeeded): template crashed",
        "psm_instance": "<gitlabMM:Pipeline/>",
    }


def test_run_stage_async_records_call_failed_on_validation_failure():
    # Same real reporting path, exercised through a mock-validated stage's
    # own failure instead of an LLM error. pim is the one remaining stage
    # that still raises via raise_if_invalid() on a failing validation -
    # psm/atl/acceleo deliberately don't (see the section below).
    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "pim")
    with patch.object(validator_agent_client, "validate_ecore", return_value=_validation_result(valid=False, issues=[
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


# --- psm/atl/acceleo's own (str, dict) tuple handling and their deliberate -----
# --- non-raising exhausted-retries behavior (contrast with pim's own -----------
# --- raise_if_invalid() above) --------------------------------------------------


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


def test_run_stage_treats_a_plain_str_return_exactly_as_before(tmp_path):
    # pim, not generation: generation now also returns (output, extra) (see
    # stages/generation/agent.py's own psm_instance/generated_files extra),
    # so it no longer demonstrates run_stage()'s own plain-string handling -
    # pim is still a real, unmodified plain-string stage agent.
    fixture_path = tmp_path / "pimMM.ecore"
    fixture_path.write_text("<ecore:EPackage/>", encoding="utf-8")

    o = pipeline.IntegrationRun()
    _fast_forward_to(o, "pim")
    with patch.object(pim_agent, "PIM_METAMODEL_PATH", fixture_path):
        with patch.object(validator_agent_client, "validate_ecore", return_value=_validation_result()):
            result = o.run_stage({"platform_description": "A GitLab CI platform"})

    assert result == {"stage": "pim", "output": "<ecore:EPackage/>"}
    assert "mode" not in result


def test_run_stage_async_records_call_completed_not_call_failed_when_psm_generation_exhausts_retries():
    # The one deliberate behavioral difference from pim's own
    # raise_if_invalid(): psm's Generation Agent already retried internally
    # (psm_agent/generation.py) before returning, so an exhausted-retries
    # failure still completes normally with the real validation/round detail
    # intact, rather than collapsing to a bare call_failed string - chat-ui's
    # own failure view depends on this (see stages/psm/agent.py's docstring).
    o = pipeline.IntegrationRun()
    o.current_stage_index = pipeline.STAGES.index("psm")
    failed_result = _psm_generation_result(artifact="<still-broken/>", valid=False)
    with patch.object(psm_agent_client, "run_psm", return_value=failed_result):
        o.run_stage_async({"platform_description": "TeamCity"})
        o._last_thread.join(timeout=5)

    types = [e["type"] for e in o.events]
    assert "call_completed" in types
    assert "call_failed" not in types
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"]["output"] == "<still-broken/>"
    assert completed["data"]["validation"]["valid"] is False


def test_run_stage_async_records_call_completed_not_call_failed_when_atl_generation_exhausts_retries():
    # Same real, deliberate behavior as psm's own test above - atl_agent's
    # own generate() already retried internally before returning.
    o = pipeline.IntegrationRun()
    o.current_stage_index = pipeline.STAGES.index("atl")
    failed_result = _atl_generation_result(artifact="still-broken", valid=False)
    with patch.object(atl_agent_client, "run_atl", return_value=failed_result):
        o.run_stage_async({"platform_description": "TeamCity"})
        o._last_thread.join(timeout=5)

    types = [e["type"] for e in o.events]
    assert "call_completed" in types
    assert "call_failed" not in types
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"]["output"] == "still-broken"
    assert completed["data"]["validation"]["valid"] is False


def test_run_stage_async_records_call_completed_not_call_failed_when_acceleo_generation_exhausts_retries():
    # Same real, deliberate behavior as psm's own test above - acceleo_agent's
    # own generate() already retried internally before returning.
    o = pipeline.IntegrationRun()
    o.current_stage_index = pipeline.STAGES.index("acceleo")
    failed_result = _acceleo_generation_result(artifact="still-broken", valid=False)
    with patch.object(acceleo_agent_client, "run_acceleo", return_value=failed_result):
        o.run_stage_async({"platform_description": "TeamCity"})
        o._last_thread.join(timeout=5)

    types = [e["type"] for e in o.events]
    assert "call_completed" in types
    assert "call_failed" not in types
    completed = next(e for e in o.events if e["type"] == "call_completed")
    assert completed["data"]["output"] == "still-broken"
    assert completed["data"]["validation"]["valid"] is False
