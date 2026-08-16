"""PSM Knowledge Agent tests. Real LLM call, mocked via patch.object(ai_layer_client, "chat",
...), the same convention this repo's other agent tests use. Uses the real gitlabMM.ecore file at
DEFAULT_PSM_METAMODEL_PATH (a static, already-checked-in repo file, not a mock) as the
metamodel content, so only the LLM call itself is stubbed.

ok_response() returns a plain dict, matching ai_layer_client.chat()'s real return shape
(response.json() from ai-layer's /chat endpoint: {"model", "content", "tool_calls"} —
see ai_layer_client.chat()'s own docstring) — not a raw completion object with .choices.
Every other real call site in this repo reads chat()'s result the same way:
ai_layer_client.chat(...)["content"].

Tests verify:
  1. compare() calls chat() with a system prompt instructing a missing/outdated comparison.
  2. compare() sends the metamodel content and the serialized docs in the user message.
  3. compare() parses a well-formed JSON array response into Suggestion objects.
  4. compare() returns an empty list when the LLM reports no gaps ([]).
  5. compare() returns an empty list, not a crash, when the LLM response has no JSON array.
  6. compare() returns an empty list, not a crash, when the JSON array is malformed.
  7. compare() drops an individual suggestion missing a required key rather than failing
     the whole call.
  8. compare() defaults to DEFAULT_PSM_METAMODEL_PATH (gitlabMM.ecore) when no path is given.
  9. compare() reads from a caller-supplied psm_metamodel_path instead of the default.
  10. source_excerpt is preserved as None when the LLM omits it.
  11. compare() extracts the real JSON array and ignores a stray bracket in trailing prose,
      rather than over-matching to it and silently failing to parse.

Requires META_MODELS_DIR to resolve to a real checkout of meta_models/ (the
default, computed from this file's location, works for local/non-Docker
runs; the real docker-compose entry sets it to the container's mount point).
"""

import json
from unittest.mock import patch

from clients import ai_layer_client
from comparison import DEFAULT_PSM_METAMODEL_PATH, Suggestion, compare


def ok_response(content):
    return {"model": "test-model", "content": content, "tool_calls": None}


def json_response(items):
    return ok_response(json.dumps(items))


def test_compare_calls_chat_with_comparison_system_prompt():
    with patch.object(ai_layer_client, "chat", return_value=json_response([])) as mock_chat:
        compare("some docs")

    messages = mock_chat.call_args.args[0]
    assert messages[0]["role"] == "system"
    assert "missing" in messages[0]["content"]
    assert "outdated" in messages[0]["content"]


def test_compare_sends_metamodel_and_docs_in_user_message():
    with patch.object(ai_layer_client, "chat", return_value=json_response([])) as mock_chat:
        compare("Jobs support a `retry` field with a max count.")

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "Jobs support a `retry` field with a max count." in user_content
    # gitlabMM.ecore content should be inlined, e.g. its EPackage declaration
    assert "ecore:EPackage" in user_content or "EPackage" in user_content


def test_compare_parses_wellformed_suggestions():
    items = [
        {
            "kind": "missing",
            "target": "Job.retry",
            "description": "Docs describe a retry field with a max count; no such property exists.",
            "source_excerpt": "Jobs support a `retry` field with a max count.",
        }
    ]
    with patch.object(ai_layer_client, "chat", return_value=json_response(items)):
        results = compare("docs")

    assert results == [
        Suggestion(
            kind="missing",
            target="Job.retry",
            description="Docs describe a retry field with a max count; no such property exists.",
            source_excerpt="Jobs support a `retry` field with a max count.",
        )
    ]


def test_compare_returns_empty_list_when_no_gaps():
    with patch.object(ai_layer_client, "chat", return_value=json_response([])):
        assert compare("docs") == []


def test_compare_returns_empty_list_when_no_json_array_present():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("I couldn't compare these.")):
        assert compare("docs") == []


def test_compare_returns_empty_list_on_malformed_json():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("[{not valid json}]")):
        assert compare("docs") == []


def test_compare_drops_suggestion_missing_required_key():
    items = [
        {"kind": "missing", "target": "Job.retry"},  # missing "description"
        {
            "kind": "outdated",
            "target": "Trigger.cron",
            "description": "well formed",
            "source_excerpt": None,
        },
    ]
    with patch.object(ai_layer_client, "chat", return_value=json_response(items)):
        results = compare("docs")

    assert len(results) == 1
    assert results[0].target == "Trigger.cron"


def test_compare_defaults_to_gitlab_metamodel_path():
    with patch.object(ai_layer_client, "chat", return_value=json_response([])) as mock_chat:
        compare("docs")

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert DEFAULT_PSM_METAMODEL_PATH in user_content


def test_compare_uses_supplied_metamodel_path():
    import os

    github_path = os.path.join(
        os.path.dirname(DEFAULT_PSM_METAMODEL_PATH),
        "..", "..", "com.mddoai.metamodel.github", "model", "githubMM.ecore",
    )
    github_path = os.path.normpath(github_path)

    with patch.object(ai_layer_client, "chat", return_value=json_response([])) as mock_chat:
        compare("docs", psm_metamodel_path=github_path)

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert github_path in user_content
    assert DEFAULT_PSM_METAMODEL_PATH not in user_content


def test_compare_preserves_none_source_excerpt():
    items = [{"kind": "outdated", "target": "Agent", "description": "changed", "source_excerpt": None}]
    with patch.object(ai_layer_client, "chat", return_value=json_response(items)):
        results = compare("docs")

    assert results[0].source_excerpt is None


def test_compare_ignores_stray_bracket_in_trailing_prose():
    # The real JSON array is well-formed and closes cleanly, but the LLM appended a
    # trailing note containing an unrelated "[old_retry]"-style bracket. A greedy
    # first-'['-to-last-']' regex would swallow that trailing bracket into the match,
    # produce unparseable JSON, and silently return [] even though the real answer
    # was fine. Depth-tracking must stop at the real array's own closing ']'.
    array_json = json.dumps(
        [
            {
                "kind": "missing",
                "target": "Job.retry",
                "description": "Docs describe a retry field with a max count; no such property exists.",
                "source_excerpt": "Jobs support a `retry` field with a max count.",
            }
        ]
    )
    content = f"{array_json}\n\nNote: see also the deprecated field [old_retry] for context."

    with patch.object(ai_layer_client, "chat", return_value=ok_response(content)):
        results = compare("docs")

    assert results == [
        Suggestion(
            kind="missing",
            target="Job.retry",
            description="Docs describe a retry field with a max count; no such property exists.",
            source_excerpt="Jobs support a `retry` field with a max count.",
        )
    ]
