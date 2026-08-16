"""stages/serialization/agent.py unit tests: the thin wrapper around the
real, separate serialization_agent service (own container). No real
network call - clients/serialization_agent_client.serialize is mocked.
The real extraction/labeling logic has its own tests in
ai/serialization_agent/tests/test_serialization_agent.py.

Tests verify:
  1. serialization_stage() forwards docs_output and the caller's chosen
     model to serialization_agent_client.serialize, and returns its result
     unchanged.
  2. A missing docs_output defaults to an empty string rather than raising.
"""
from unittest.mock import patch

from clients import serialization_agent_client
from integration_runner.stages.serialization.agent import serialization_stage


def test_serialization_stage_forwards_docs_output_and_model():
    with patch.object(serialization_agent_client, "serialize", return_value="labeled markdown") as mock_serialize:
        result = serialization_stage({"docs_output": "raw docs", "model": "gemini-flash"})

    assert result == "labeled markdown"
    mock_serialize.assert_called_once_with("raw docs", "gemini-flash")


def test_serialization_stage_defaults_missing_docs_output_to_empty_string():
    with patch.object(serialization_agent_client, "serialize", return_value="labeled markdown") as mock_serialize:
        serialization_stage({})

    mock_serialize.assert_called_once_with("", None)
