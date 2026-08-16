"""
chat_log.py unit tests: the mechanism that replaces record_event()'s old
reactor call (see chat_log.py's own module docstring for the full design
reasoning — clock skew, why raw events are mirrored synchronously while
narration happens in the background). No real API calls —
clients/integration_runner_client.get_events is mocked; the reactor itself
is swapped for a controllable test double per test (conftest.py's own
autouse fixture wires the real assistant.react_to_event for the whole
session, restored after each test here).

Tests verify:
  1. New raw events are mirrored into the chat log synchronously, inside
     get_events() itself — visible on the very same call, before narration
     has necessarily run.
  2. Narration happens on a background thread and appends a "message" turn
     per new raw event, in order.
  3. Each event's narration history is everything already in the transcript
     before that event's own position, not a stale snapshot and not
     including events queued after it in the same batch.
  4. A narration failure (reactor raises, or none wired in at all) falls
     back to a fixed message without crashing or losing the real event.
  5. Long string fields are truncated for the narration prompt, but the
     mirrored transcript entry itself keeps the untruncated original.
  6. Different runs get independent chat logs.
  7. since_index slices the merged (mirrored + narrated) transcript.
"""
import threading
from unittest.mock import patch

import pytest

import chat_log
import event_summarization
from clients import integration_runner_client


@pytest.fixture
def fake_reactor():
    """Installs a controllable test double as chat_log's reactor for one
    test, restoring the real one (wired by conftest.py's own autouse
    fixture) afterward."""
    calls = []

    def _reactor(event, history):
        calls.append({"event": event, "history": history})
        return {"message": f"Narrated: {event['type']}", "model": "test-model"}

    _reactor.calls = calls
    original = chat_log._reactor
    chat_log.set_reactor(_reactor)
    yield _reactor
    chat_log.set_reactor(original)


def _raw_events_response(run_id, events):
    return {"run_id": run_id, "events": events, "current_stage": "docs", "busy": False, "model": None, "is_current": True}


def _wait_for_narration(run_id: str) -> None:
    thread = chat_log.get_chat_log(run_id)._last_thread
    if thread is not None:
        thread.join(timeout=5)


def test_get_events_mirrors_new_raw_events_immediately(fake_reactor):
    raw_event = {"type": "call_started", "stage": "docs", "data": {"seed_url": "https://x"}, "timestamp": 1.0}
    with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-1", [raw_event])):
        result = chat_log.get_events(run_id="run-1")

    # The raw fact is visible on this very call, before narration necessarily ran.
    assert raw_event in result["events"]


def test_narration_appends_a_message_turn_per_new_raw_event(fake_reactor):
    events = [
        {"type": "call_started", "stage": "docs", "data": {}, "timestamp": 1.0},
        {"type": "call_completed", "stage": "docs", "data": {}, "timestamp": 2.0},
    ]
    with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-2", events)):
        chat_log.get_events(run_id="run-2")
    _wait_for_narration("run-2")

    # Both raw events are mirrored synchronously, up front (see
    # test_get_events_mirrors_new_raw_events_immediately); their comments
    # are appended afterward, one at a time, in the same order — so a
    # batch of more than one new event lands as "facts first, then
    # comments", not strictly alternating.
    transcript = chat_log.get_chat_log("run-2").events
    types = [e["type"] for e in transcript]
    assert types == ["call_started", "call_completed", "message", "message"]
    assert transcript[2]["text"] == "Narrated: call_started"
    assert transcript[3]["text"] == "Narrated: call_completed"


def test_narration_history_for_each_event_excludes_events_queued_after_it(fake_reactor):
    events = [
        {"type": "call_started", "stage": "docs", "data": {}, "timestamp": 1.0},
        {"type": "call_completed", "stage": "docs", "data": {}, "timestamp": 2.0},
    ]
    with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-3", events)):
        chat_log.get_events(run_id="run-3")
    _wait_for_narration("run-3")

    first_call, second_call = fake_reactor.calls
    # call_started's own narration ran with nothing before it yet.
    assert first_call["history"] == []
    # call_completed's narration saw only call_started: both raw events were
    # already mirrored by the time narration started, but call_completed
    # sits at index 1 (before call_started's own message, appended after
    # it) — history is "everything before this event's own position",
    # never the event itself or anything queued after it.
    history_types = [e["type"] for e in second_call["history"]]
    assert history_types == ["call_started"]


def test_narration_failure_falls_back_without_crashing_or_losing_the_event():
    def _broken_reactor(event, history):
        raise RuntimeError("ai-layer unreachable")

    original = chat_log._reactor
    chat_log.set_reactor(_broken_reactor)
    try:
        raw_event = {"type": "call_started", "stage": "docs", "data": {}, "timestamp": 1.0}
        with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-4", [raw_event])):
            chat_log.get_events(run_id="run-4")
        _wait_for_narration("run-4")

        transcript = chat_log.get_chat_log("run-4").events
        assert transcript[0] == raw_event  # the real event is still there
        assert transcript[1]["text"] == "(narration unavailable)"
    finally:
        chat_log.set_reactor(original)


def test_no_reactor_wired_in_falls_back_gracefully():
    original = chat_log._reactor
    chat_log.set_reactor(None)
    try:
        raw_event = {"type": "call_started", "stage": "docs", "data": {}, "timestamp": 1.0}
        with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-5", [raw_event])):
            chat_log.get_events(run_id="run-5")
        _wait_for_narration("run-5")

        transcript = chat_log.get_chat_log("run-5").events
        assert transcript[1]["text"] == "(narration unavailable)"
    finally:
        chat_log.set_reactor(original)


def test_long_fields_truncated_for_narration_but_not_in_the_mirrored_transcript(fake_reactor):
    long_output = "x" * (event_summarization._REACTION_FIELD_MAX_CHARS + 500)
    raw_event = {"type": "call_completed", "stage": "docs", "data": {"output": long_output}, "timestamp": 1.0}
    with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-6", [raw_event])):
        chat_log.get_events(run_id="run-6")
    _wait_for_narration("run-6")

    # The reactor saw the truncated version...
    sent_event = fake_reactor.calls[0]["event"]
    assert "... (truncated)" in sent_event["data"]["output"]
    assert len(sent_event["data"]["output"]) < len(long_output)
    # ...but the mirrored transcript entry (and a real GET /events caller)
    # still has the real, untruncated output.
    transcript = chat_log.get_chat_log("run-6").events
    assert transcript[0]["data"]["output"] == long_output


def test_leaked_fake_tool_call_in_narration_text_is_replaced():
    # Regression test: a weaker/faster model can ignore the "no tools" system
    # prompt instruction and write a JSON-shaped fake tool call directly into
    # its narration text (confirmed against a real run, cerebras/gpt-oss-120b)
    # instead of plain prose. Narration never passes tools=, so this was never
    # a real dispatched call, just malformed, confusing text that shouldn't
    # reach a human as-is.
    def _leaky_reactor(event, history):
        return {"message": 'We need to call run_stage.{"tool": "run_stage", "arguments": {}}', "model": "cerebras"}

    original = chat_log._reactor
    chat_log.set_reactor(_leaky_reactor)
    try:
        raw_event = {"type": "call_completed", "stage": "docs", "data": {}, "timestamp": 1.0}
        with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-8", [raw_event])):
            chat_log.get_events(run_id="run-8")
        _wait_for_narration("run-8")

        transcript = chat_log.get_chat_log("run-8").events
        assert '"tool"' not in transcript[1]["text"]
        assert transcript[1]["text"] == "(a stage transition happened; narration for it was malformed and skipped)"
    finally:
        chat_log.set_reactor(original)


def test_different_runs_get_independent_chat_logs(fake_reactor):
    event_a = {"type": "call_started", "stage": "docs", "data": {"platform_description": "A"}, "timestamp": 1.0}
    event_b = {"type": "call_started", "stage": "docs", "data": {"platform_description": "B"}, "timestamp": 1.0}
    with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-a", [event_a])):
        chat_log.get_events(run_id="run-a")
    with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-b", [event_b])):
        chat_log.get_events(run_id="run-b")

    assert chat_log.get_chat_log("run-a") is not chat_log.get_chat_log("run-b")
    assert chat_log.get_chat_log("run-a").events[0]["data"]["platform_description"] == "A"
    assert chat_log.get_chat_log("run-b").events[0]["data"]["platform_description"] == "B"


def test_since_index_slices_the_merged_transcript(fake_reactor):
    events = [{"type": "call_started", "stage": "docs", "data": {}, "timestamp": 1.0}]
    with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-7", events)):
        chat_log.get_events(run_id="run-7")
        _wait_for_narration("run-7")

        full = chat_log.get_events(run_id="run-7", since_index=0)
        assert len(full["events"]) == 2  # call_started + its narration

        tail = chat_log.get_events(run_id="run-7", since_index=1)
        assert len(tail["events"]) == 1
        assert tail["events"][0]["type"] == "message"
