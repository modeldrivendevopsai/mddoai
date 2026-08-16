"""Turning a raw pipeline event (or a list of them) into an LLM-prompt-safe
shape: long string fields get truncated so re-sending history on every
narration/send_message() call doesn't balloon the prompt.

Shared by chat_log.py's own narration and assistant.py's send_message(), the
only two places that build a prompt from real events — neither one owns
this, it's a concern of "how we build LLM prompts from event history," not
of the chat transcript (chat_log.py) or the reply mechanism (assistant.py)
themselves.
"""

# Long fields (notably docs_stage's real fetched markdown) get cut to this
# length before being fed into a narration prompt's history, so re-sending
# history on every subsequent narration call doesn't balloon the prompt.
# ChatLog.events (and GET /events) keep the original untruncated.
_REACTION_FIELD_MAX_CHARS = 2000


def summarize_for_reaction(event: dict) -> dict:
    """Truncates long string fields in event's data before it's fed into a
    narration prompt. Must be applied to every entry of a history list, not
    just "the current event": a long field truncated only once, right when
    its own event is recorded, would otherwise get re-sent raw as part of
    history on every later call for the rest of the run."""
    data = event.get("data")
    if not isinstance(data, dict):
        return event
    summarized = {
        key: (value[:_REACTION_FIELD_MAX_CHARS] + "... (truncated)")
        if isinstance(value, str) and len(value) > _REACTION_FIELD_MAX_CHARS
        else value
        for key, value in data.items()
    }
    return {**event, "data": summarized}


def summarize_history(events: list[dict]) -> list[dict]:
    """summarize_for_reaction(), mapped over a whole event list."""
    return [summarize_for_reaction(e) for e in events]
