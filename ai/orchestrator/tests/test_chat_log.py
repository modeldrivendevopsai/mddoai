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
  2. Narration happens on a background thread and appends one "message" turn,
     on the newest new raw event. Older events in the batch get no comment,
     and an event that arrives mid-comment replaces one still waiting.
  3. The narration history is everything already in the transcript before
     that event's own position, not a stale snapshot and not including the
     event itself.
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


def test_only_the_newest_of_several_new_events_gets_a_comment(fake_reactor):
    events = [
        {"type": "call_started", "stage": "docs", "data": {}, "timestamp": 1.0},
        {"type": "call_completed", "stage": "docs", "data": {}, "timestamp": 2.0},
    ]
    with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-2", events)):
        chat_log.get_events(run_id="run-2")
    _wait_for_narration("run-2")

    # Both raw events are mirrored synchronously, up front (see
    # test_get_events_mirrors_new_raw_events_immediately). Only the newest
    # earns a comment, appended after them.
    transcript = chat_log.get_chat_log("run-2").events
    assert [e["type"] for e in transcript] == ["call_started", "call_completed", "message"]
    assert transcript[2]["text"] == "Narrated: call_completed"
    assert [call["event"]["type"] for call in fake_reactor.calls] == ["call_completed"]


def test_a_run_opened_after_it_moved_on_costs_one_comment_not_one_per_event(fake_reactor):
    # Regression test: opening the browser on runs that advanced while nobody
    # watched mirrored all their events and wrote an LLM comment on each,
    # which spent the provider's per-minute request limit in one go.
    events = [{"type": "call_completed", "stage": "docs", "data": {"n": n}, "timestamp": float(n)} for n in range(30)]
    with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-bl", events)):
        chat_log.get_events(run_id="run-bl")
    _wait_for_narration("run-bl")

    transcript = chat_log.get_chat_log("run-bl").events
    assert [e["type"] for e in transcript].count("call_completed") == 30  # every fact is kept
    assert [call["event"]["data"]["n"] for call in fake_reactor.calls] == [29]


def test_the_comment_on_the_newest_event_sees_the_events_before_it(fake_reactor):
    events = [
        {"type": "call_started", "stage": "docs", "data": {}, "timestamp": 1.0},
        {"type": "call_completed", "stage": "docs", "data": {}, "timestamp": 2.0},
    ]
    with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-3", events)):
        chat_log.get_events(run_id="run-3")
    _wait_for_narration("run-3")

    (call,) = fake_reactor.calls
    # History is everything before the event's own position, never the event
    # itself, even though it was mirrored before narration started.
    assert [e["type"] for e in call["history"]] == ["call_started"]


def test_constraint_added_events_are_mirrored_but_not_narrated(fake_reactor):
    # Regression test: the atl/acceleo repair-and-promote loop can add a run
    # of a dozen or more constraint_added events in one go (confirmed: 20 in
    # a single real ATL repair round) - narrating each one its own LLM call
    # wasted a real call per entry and read as the same near-identical
    # comment repeated over and over.
    events = [
        {"type": "constraint_added", "stage": "atl", "data": {"constraint": "x"}, "timestamp": 1.0},
        {"type": "constraint_added", "stage": "atl", "data": {"constraint": "y"}, "timestamp": 2.0},
        {"type": "call_completed", "stage": "atl", "data": {}, "timestamp": 3.0},
    ]
    with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-9", events)):
        chat_log.get_events(run_id="run-9")
    _wait_for_narration("run-9")

    transcript = chat_log.get_chat_log("run-9").events
    types = [e["type"] for e in transcript]
    # Both raw constraint_added facts are still mirrored, but only
    # call_completed earned a narration "message" turn.
    assert types == ["constraint_added", "constraint_added", "call_completed", "message"]
    assert transcript[3]["text"] == "Narrated: call_completed"
    # The reactor was never called for either constraint_added event.
    narrated_types = [call["event"]["type"] for call in fake_reactor.calls]
    assert narrated_types == ["call_completed"]


def test_a_batch_that_ends_in_constraint_added_comments_on_the_newest_event_that_is_narrated(fake_reactor):
    events = [
        {"type": "call_completed", "stage": "atl", "data": {}, "timestamp": 1.0},
        {"type": "constraint_added", "stage": "atl", "data": {"constraint": "x"}, "timestamp": 2.0},
    ]
    with patch.object(integration_runner_client, "get_events", return_value=_raw_events_response("run-ce", events)):
        chat_log.get_events(run_id="run-ce")
    _wait_for_narration("run-ce")

    assert [call["event"]["type"] for call in fake_reactor.calls] == ["call_completed"]


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


# --- only the events the chat has not seen are fetched ----------------------------


class _FakeRunner:
    """Stands in for integration_runner's GET /events: it answers from
    since_index on, and says where its answer starts and how many events the
    run has."""

    def __init__(self, run_id, count=0, event_type="constraint_added"):
        self.run_id = run_id
        self.event_type = event_type
        self.events = []
        self.calls = []
        self.add(count)

    def add(self, count):
        for _ in range(count):
            number = len(self.events)
            self.events.append({"type": self.event_type, "stage": "docs", "data": {"n": number}, "timestamp": float(number)})

    def get_events(self, since_index=0, run_id=None):
        self.calls.append({"since_index": since_index, "run_id": run_id})
        answer = _raw_events_response(self.run_id, self.events[since_index:])
        return {**answer, "offset": since_index, "total": len(self.events)}


@pytest.fixture
def runner():
    fake = _FakeRunner("run-p", count=3)
    with patch.object(integration_runner_client, "get_events", side_effect=fake.get_events):
        yield fake


def _raw_numbers(run_id):
    # constraint_added events are mirrored and never narrated, so no thread runs.
    return [e["data"]["n"] for e in chat_log.get_chat_log(run_id).events if e["type"] == "constraint_added"]


def test_a_poll_asks_only_for_the_events_the_chat_has_not_seen(runner):
    chat_log.get_events(run_id="run-p")
    runner.add(2)

    result = chat_log.get_events(run_id="run-p")

    assert [call["since_index"] for call in runner.calls] == [0, 3]
    assert _raw_numbers("run-p") == [0, 1, 2, 3, 4]
    assert len(result["events"]) == 5


def test_a_poll_with_nothing_new_adds_nothing(runner):
    chat_log.get_events(run_id="run-p")

    chat_log.get_events(run_id="run-p")

    assert runner.calls[1]["since_index"] == 3
    assert _raw_numbers("run-p") == [0, 1, 2]


def test_the_current_run_is_asked_for_by_its_id_after_a_status_call_that_carries_no_events(runner):
    with patch.object(integration_runner_client, "get_status", return_value={"run_id": "run-p"}) as status:
        result = chat_log.get_events()

    status.assert_called_once_with()
    assert runner.calls == [{"since_index": 0, "run_id": "run-p"}]
    assert result["run_id"] == "run-p"


def test_where_the_events_start_and_how_many_there_are_are_not_passed_on(runner):
    result = chat_log.get_events(run_id="run-p")

    assert "offset" not in result
    assert "total" not in result


def test_two_polls_that_fetched_the_same_events_add_them_once(runner):
    real = runner.get_events
    other_poll_ran = []

    def get_events(since_index=0, run_id=None):
        answer = real(since_index, run_id)
        if not other_poll_ran:
            other_poll_ran.append(True)
            # Another poll fetches and adds the same events before this one returns.
            chat_log.get_events(run_id=run_id)
        return answer

    with patch.object(integration_runner_client, "get_events", side_effect=get_events):
        chat_log.get_events(run_id="run-p")

    assert _raw_numbers("run-p") == [0, 1, 2]
    assert chat_log.get_chat_log("run-p").raw_seen == 3


def test_events_a_runner_lost_in_a_crash_do_not_make_the_chat_skip_the_ones_it_records_next(runner):
    runner.add(2)
    chat_log.get_events(run_id="run-p")
    del runner.events[4:]  # the runner restarted with its last event lost
    chat_log.get_events(run_id="run-p")
    runner.add(2)  # and records two new ones

    chat_log.get_events(run_id="run-p")

    assert chat_log.get_chat_log("run-p").raw_seen == 6
    assert _raw_numbers("run-p") == [0, 1, 2, 3, 4, 4, 5]


def test_a_runner_that_sends_every_event_and_says_nothing_of_where_they_start_is_still_read_right():
    events = [{"type": "constraint_added", "stage": "docs", "data": {"n": n}, "timestamp": float(n)} for n in range(4)]
    with patch.object(integration_runner_client, "get_events", side_effect=lambda **kwargs: _raw_events_response("run-q", events[:3])):
        chat_log.get_events(run_id="run-q")
    with patch.object(integration_runner_client, "get_events", side_effect=lambda **kwargs: _raw_events_response("run-q", events)):
        chat_log.get_events(run_id="run-q")

    assert _raw_numbers("run-q") == [0, 1, 2, 3]


# --- events that arrive while a narration runs: only the newest waits ----------------


def test_events_that_arrive_while_a_narration_is_running_leave_only_the_newest_to_comment_on():
    runner = _FakeRunner("run-n", count=1, event_type="call_completed")
    started, release = threading.Event(), threading.Event()
    narrated = []
    running = []
    most_at_once = []

    def reactor(event, history):
        narrated.append(event["data"]["n"])
        running.append(True)
        most_at_once.append(len(running))
        started.set()
        release.wait(5)
        running.pop()
        return {"message": "ok", "model": "m"}

    original = chat_log._reactor
    chat_log.set_reactor(reactor)
    try:
        with patch.object(integration_runner_client, "get_events", side_effect=runner.get_events):
            chat_log.get_events(run_id="run-n")
            assert started.wait(5)
            runner.add(1)
            chat_log.get_events(run_id="run-n")  # the first event is still being narrated
            runner.add(1)
            chat_log.get_events(run_id="run-n")  # this one replaces the one waiting
            release.set()
            _wait_for_narration("run-n")
    finally:
        release.set()
        chat_log.set_reactor(original)

    chat = chat_log.get_chat_log("run-n")
    assert narrated == [0, 2]  # event 1 was outdated before its turn came
    assert max(most_at_once) == 1  # one thread narrates them, one after the other
    assert [e["type"] for e in chat.events].count("message") == 2
    assert [e["data"]["n"] for e in chat.events if e["type"] == "call_completed"] == [0, 1, 2]  # every fact is kept
    assert chat.pending is None
    assert chat.narrating is False


def test_events_that_arrive_after_a_narration_finished_are_narrated_by_a_new_thread(fake_reactor):
    runner = _FakeRunner("run-m", count=1, event_type="call_completed")
    with patch.object(integration_runner_client, "get_events", side_effect=runner.get_events):
        chat_log.get_events(run_id="run-m")
        _wait_for_narration("run-m")
        runner.add(1)
        chat_log.get_events(run_id="run-m")
        _wait_for_narration("run-m")

    assert [call["event"]["data"]["n"] for call in fake_reactor.calls] == [0, 1]


def test_a_narration_thread_that_dies_unexpectedly_does_not_block_the_narration_after_it(fake_reactor, monkeypatch):
    runner = _FakeRunner("run-d", count=1, event_type="call_completed")
    monkeypatch.setattr(threading, "excepthook", lambda args: None)
    real = chat_log.summarize_history
    failed = []

    def summarize_history(events):
        if not failed:
            failed.append(True)
            raise RuntimeError("unexpected")
        return real(events)

    monkeypatch.setattr(chat_log, "summarize_history", summarize_history)
    with patch.object(integration_runner_client, "get_events", side_effect=runner.get_events):
        chat_log.get_events(run_id="run-d")
        _wait_for_narration("run-d")
        runner.add(1)
        chat_log.get_events(run_id="run-d")
        _wait_for_narration("run-d")

    assert chat_log.get_chat_log("run-d").narrating is False
    assert [call["event"]["data"]["n"] for call in fake_reactor.calls] == [1]

