"""Shared pytest configuration for orchestrator's test suite."""
from unittest.mock import patch

import pytest

import assistant
import chat_log
import tools
from clients import integration_runner_client

# The real STAGES/STAGE_DESCRIPTIONS content (integration_runner/pipeline.py,
# integration_runner/stages/__init__.py) — kept here, not fetched from a live
# integration_runner, since these tests are unit tests of orchestrator's own
# code, not integration tests of the two services together (see
# test_main.py's own real end-to-end tests for that boundary).
_FAKE_STAGE_METADATA = {
    "stages": ["docs", "serialization", "pim", "psm", "atl", "acceleo", "generation"],
    "descriptions": {
        "docs": "fetches the platform's real documentation (a real call to retrieval).",
        "serialization": "restructures the fetched documentation into a labeled, PIM-concept-tagged markdown artifact.",
        "pim": "a PIM (Platform-Independent Model) description of the platform.",
        "psm": "a PSM (Platform-Specific Model) description of the platform.",
        "atl": "the ATL transformation rules needed to build that PSM.",
        "acceleo": "the Acceleo code-generation template for that ATL.",
        "generation": "a final summary tying all prior stages together.",
    },
}

# Every new raw pipeline event chat_log notices gets narrated automatically
# (see chat_log.get_events()), but chat_log has no knowledge of
# assistant.py, it only exposes a blank hook. This wires the real reactor
# in, explicitly, once for the whole session, rather than as an
# easy-to-miss side effect of importing assistant.py elsewhere. Several
# test files (test_chat_log.py's narration tests, test_main.py's /events
# tests) exercise this real narration.
chat_log.set_reactor(assistant.react_to_event)


@pytest.fixture(autouse=True)
def reset_chat_logs():
    """Each test starts with a clean chat_log._chat_logs, so one test's
    mirrored events/narration/messages can't leak into another's
    assertions — every test that touches GET /events or send_message()
    writes into this same process-wide dict."""
    original = dict(chat_log._chat_logs)
    chat_log._chat_logs.clear()
    yield
    chat_log._chat_logs.clear()
    chat_log._chat_logs.update(original)


@pytest.fixture(autouse=True)
def reset_tools_cache():
    """tools.stage_metadata()/get_tools() cache their result for the life of
    the process (see tools/__init__.py's own docstring for why: it's real,
    static data, fetched once). That caching is exactly wrong across tests
    that mock integration_runner_client.get_stage_metadata() differently —
    without this reset, whichever test runs first would "win" and every
    later test would silently see its stale cached value instead of its own
    mock."""
    tools._stage_metadata_cache = None
    tools._tools_cache = None
    yield
    tools._stage_metadata_cache = None
    tools._tools_cache = None


@pytest.fixture(autouse=True)
def fake_stage_metadata():
    """Every test that calls send_message()/react_to_event() needs a real
    stage list/descriptions to build the system prompt and tool schemas
    from (see tools.stage_metadata()) — patched here once, for the whole
    suite, rather than every test file repeating the same mock."""
    with patch.object(integration_runner_client, "get_stage_metadata", return_value=_FAKE_STAGE_METADATA):
        yield
