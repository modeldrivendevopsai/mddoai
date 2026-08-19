"""event_log.py unit tests: EventLog's own storage contract, independent of
IntegrationRun. See test_pipeline.py's own record_event() test for the
guarantee that IntegrationRun.record_event() delegates here without adding
any reaction of its own.

Tests verify:
  1. record() builds an event with the given type/stage/data and a real
     timestamp, appends it, and returns it.
  2. data defaults to None when not given.
  3. Multiple records accumulate in call order.
"""
from integration_runner.event_log import EventLog


def test_record_builds_and_appends_the_event():
    log = EventLog()

    event = log.record("call_started", "docs", {"seed_url": "https://x"})

    assert event["type"] == "call_started"
    assert event["stage"] == "docs"
    assert event["data"] == {"seed_url": "https://x"}
    assert isinstance(event["timestamp"], float)
    assert log.events == [event]


def test_record_defaults_data_to_none():
    log = EventLog()

    event = log.record("call_started", "docs")

    assert event["data"] is None


def test_multiple_records_accumulate_in_order():
    log = EventLog()

    first = log.record("call_started", "docs")
    second = log.record("call_completed", "docs")

    assert log.events == [first, second]
