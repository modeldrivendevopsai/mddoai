"""stages/psm/actions.py unit tests: promote_constraints() called directly
as a plain function, matching stages/docs/actions.py's own test style. No
real HTTP call - clients/psm_agent_client.add_learned_constraints is
mocked.
"""
from unittest.mock import patch

import pytest

from clients import psm_agent_client
from integration_runner import pipeline
from integration_runner.stages.psm.actions import promote_constraints


def _generation_completed_event(valid=True):
    return {
        "type": "call_completed",
        "stage": "psm",
        "data": {
            "mode": "generation",
            "output": "<ecore/>",
            "validation": {"valid": valid, "issues": []},
        },
    }


def test_promote_constraints_calls_the_real_client():
    run = pipeline.IntegrationRun()
    run.current_stage_index = pipeline.STAGES.index("psm")
    run.event_log.events.append(_generation_completed_event())

    with patch.object(psm_agent_client, "add_learned_constraints", return_value={"learned_constraints": ["x"]}) as mock_add:
        result = promote_constraints(run, ["Use camelCase"])

    mock_add.assert_called_once_with("generation", ["Use camelCase"])
    assert result == {"learned_constraints": ["x"]}


def test_promote_constraints_records_a_real_event():
    run = pipeline.IntegrationRun()
    run.current_stage_index = pipeline.STAGES.index("psm")
    run.event_log.events.append(_generation_completed_event())

    with patch.object(psm_agent_client, "add_learned_constraints", return_value={}):
        promote_constraints(run, ["Use camelCase"])

    promoted_events = [e for e in run.events if e["type"] == "constraints_promoted"]
    assert len(promoted_events) == 1
    assert promoted_events[0]["data"]["constraints"] == ["Use camelCase"]


def test_promote_constraints_rejects_when_psm_is_not_the_current_stage():
    run = pipeline.IntegrationRun()
    run.current_stage_index = pipeline.STAGES.index("atl")

    with pytest.raises(ValueError):
        promote_constraints(run, ["x"])


def test_promote_constraints_rejects_with_no_completed_result_yet():
    run = pipeline.IntegrationRun()
    run.current_stage_index = pipeline.STAGES.index("psm")

    with pytest.raises(ValueError):
        promote_constraints(run, ["x"])


def test_promote_constraints_rejects_a_knowledge_mode_result():
    run = pipeline.IntegrationRun()
    run.current_stage_index = pipeline.STAGES.index("psm")
    run.event_log.events.append({
        "type": "call_completed", "stage": "psm",
        "data": {"mode": "knowledge", "output": "<ecore/>", "gaps": []},
    })

    with pytest.raises(ValueError):
        promote_constraints(run, ["x"])


def test_promote_constraints_rejects_a_failed_validation():
    run = pipeline.IntegrationRun()
    run.current_stage_index = pipeline.STAGES.index("psm")
    run.event_log.events.append(_generation_completed_event(valid=False))

    with pytest.raises(ValueError):
        promote_constraints(run, ["x"])


def test_promote_constraints_uses_the_latest_completed_result_not_an_earlier_one():
    run = pipeline.IntegrationRun()
    run.current_stage_index = pipeline.STAGES.index("psm")
    # If promote_constraints looked at the first (invalid) event instead of
    # the latest (valid) one, this would raise ValueError instead of
    # succeeding - the real regression this guards against.
    run.event_log.events.append(_generation_completed_event(valid=False))
    run.event_log.events.append(_generation_completed_event(valid=True))

    with patch.object(psm_agent_client, "add_learned_constraints", return_value={}) as mock_add:
        promote_constraints(run, ["x"])

    mock_add.assert_called_once_with("generation", ["x"])
