"""
Orchestrator tests. No real API calls — router.chat is mocked.

Tests verify:
  1. Intent classification for pipeline_generation, platform_question, general_question.
  2. /orchestrate returns 200 with content and model fields.
"""
from unittest.mock import patch

from fastapi.testclient import TestClient

from conftest import reload_router_modules

# conftest.py sets fake provider keys at import time. Force a fresh import here too,
# in case another test module already reloaded router.config under different env vars.
reload_router_modules()

import main  # noqa: E402
from orchestrator import classify_intent  # noqa: E402

client = TestClient(main.app)


def test_classify_intent_pipeline_generation():
    assert classify_intent("Can you help me generate a CI/CD pipeline to deploy my app?") == "pipeline_generation"


def test_classify_intent_platform_question():
    assert classify_intent("Does this support TeamCity or GitLab?") == "platform_question"


def test_classify_intent_general_question():
    assert classify_intent("What is DevOps anyway?") == "general_question"


def test_orchestrate_endpoint_returns_content_and_model():
    with patch.object(main, "orchestrate", return_value="hello") as mock_orchestrate:
        response = client.post("/orchestrate", json={"messages": [{"role": "user", "content": "hi"}]})

    assert response.status_code == 200
    body = response.json()
    assert body["content"] == "hello"
    assert "model" in body
    mock_orchestrate.assert_called_once_with([{"role": "user", "content": "hi"}])
