"""event_summarization.py unit tests: summarize_for_reaction()/
summarize_history()'s own truncation contract, independent of chat_log.py's
narration loop or assistant.py's send_message() (both of which exercise
these functions indirectly — see test_chat_log.py's own truncation test for
that integration-level coverage).

Tests verify:
  1. A string field longer than the truncation threshold is cut and marked.
  2. A string field at or under the threshold is left untouched.
  3. Non-string data values are left untouched regardless of length.
  4. An event whose "data" isn't a dict is returned unchanged.
  5. summarize_history() maps summarize_for_reaction() over a list, in order.
"""
import event_summarization


def test_long_string_field_is_truncated_and_marked():
    long_value = "x" * (event_summarization._REACTION_FIELD_MAX_CHARS + 100)
    event = {"type": "call_completed", "stage": "docs", "data": {"output": long_value}}

    result = event_summarization.summarize_for_reaction(event)

    assert result["data"]["output"].endswith("... (truncated)")
    assert len(result["data"]["output"]) < len(long_value)


def test_short_string_field_is_left_untouched():
    short_value = "x" * event_summarization._REACTION_FIELD_MAX_CHARS
    event = {"type": "call_completed", "stage": "docs", "data": {"output": short_value}}

    result = event_summarization.summarize_for_reaction(event)

    assert result["data"]["output"] == short_value


def test_non_string_values_are_left_untouched():
    event = {"type": "call_completed", "stage": "docs", "data": {"confidence": 0.9, "pages": ["a", "b"]}}

    result = event_summarization.summarize_for_reaction(event)

    assert result["data"] == {"confidence": 0.9, "pages": ["a", "b"]}


def test_event_with_non_dict_data_is_returned_unchanged():
    event = {"type": "message", "stage": "docs", "text": "hello", "data": None}

    result = event_summarization.summarize_for_reaction(event)

    assert result == event


def test_summarize_history_maps_over_events_in_order():
    long_value = "x" * (event_summarization._REACTION_FIELD_MAX_CHARS + 100)
    events = [
        {"type": "call_started", "stage": "docs", "data": {"seed_url": "https://x"}},
        {"type": "call_completed", "stage": "docs", "data": {"output": long_value}},
    ]

    result = event_summarization.summarize_history(events)

    assert result[0]["data"]["seed_url"] == "https://x"
    assert result[1]["data"]["output"].endswith("... (truncated)")
