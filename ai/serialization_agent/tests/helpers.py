"""Shared test fixtures used across more than one test file in this
directory. Not a test file itself (no test_ prefix, pytest won't collect it).

Deliberately not imported from ai/orchestrator/tests/helpers.py: each
package's test suite is self-contained, matching ai/pim_agent/ and
ai/psm_agent/'s established pattern, rather than one package's tests
reaching into a sibling package's test directory."""


def ok_response(content):
    """A plain chat() response: narrated/agent text, no tool calls."""
    return {"content": content, "model": "test-model", "tool_calls": None}
