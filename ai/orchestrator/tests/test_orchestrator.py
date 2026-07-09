"""
Orchestrator unit tests. No real API calls — orchestrator.chat is mocked.

Tests verify:
  1. Intent classification for pipeline_generation, platform_question, general_question.
  2. is_good_enough() rejects empty/error-marker responses and accepts good ones.
  3. orchestrate() returns the first good response without retrying.
  4. orchestrate() retries up to MAX_ATTEMPTS on low-quality responses, then returns the
     last response with a human-curation note.
  5. orchestrate() stops retrying as soon as a later attempt succeeds.
"""
from unittest.mock import MagicMock, patch

import orchestrator


def ok_response(content):
    r = MagicMock()
    r.choices = [MagicMock(message=MagicMock(content=content))]
    return r


def test_classify_intent_pipeline_generation():
    text = "Can you help me generate a CI/CD pipeline to deploy my app?"
    assert orchestrator.classify_intent(text) == "pipeline_generation"


def test_classify_intent_platform_question():
    assert orchestrator.classify_intent("Does this support TeamCity or GitLab?") == "platform_question"


def test_classify_intent_general_question():
    assert orchestrator.classify_intent("What is DevOps anyway?") == "general_question"


def test_is_good_enough_rejects_empty():
    assert not orchestrator.is_good_enough("")
    assert not orchestrator.is_good_enough("   ")


def test_is_good_enough_rejects_error_markers():
    assert not orchestrator.is_good_enough("I cannot help with that.")
    assert not orchestrator.is_good_enough("I don't know how to do that.")
    assert not orchestrator.is_good_enough("Sorry, an error occurred.")


def test_is_good_enough_accepts_valid_response():
    assert orchestrator.is_good_enough("Here are the pipeline stages you need: build, test, deploy.")


def test_orchestrate_returns_first_good_response_without_retrying():
    with patch.object(orchestrator, "chat", return_value=ok_response("Here's your pipeline plan.")) as mock_chat:
        result = orchestrator.orchestrate([{"role": "user", "content": "generate a pipeline"}])

    assert result == "Here's your pipeline plan."
    assert mock_chat.call_count == 1


def test_orchestrate_retries_on_low_quality_then_returns_note():
    with patch.object(orchestrator, "chat", return_value=ok_response("I cannot help with that.")) as mock_chat:
        result = orchestrator.orchestrate([{"role": "user", "content": "generate a pipeline"}])

    assert mock_chat.call_count == orchestrator.MAX_ATTEMPTS
    assert "I cannot help with that." in result
    assert "human curation" in result.lower()


def test_orchestrate_succeeds_on_a_later_attempt():
    responses = [
        ok_response("I cannot help."),
        ok_response("I cannot help."),
        ok_response("Here's the answer."),
    ]
    with patch.object(orchestrator, "chat", side_effect=responses) as mock_chat:
        result = orchestrator.orchestrate([{"role": "user", "content": "generate a pipeline"}])

    assert result == "Here's the answer."
    assert mock_chat.call_count == 3
