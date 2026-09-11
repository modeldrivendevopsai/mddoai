"""run_with_retry() tests: mocks clients.ai_layer_client.chat, the only real
network call this module makes (matches this repo's other agent tests'
convention of mocking the network boundary, not internal logic)."""
from unittest.mock import patch

from clients import ai_layer_client
from generation_toolkit.generation_agent import DEFAULT_MAX_REGENERATE_ROUNDS, run_with_retry, strip_code_fence


def ok_response(content):
    return {"model": "test-model", "content": content, "tool_calls": None}


def valid_result():
    return {"valid": True, "issues": []}


def invalid_result(message="dangling reference"):
    return {"valid": False, "issues": [{"severity": "ERROR", "message": message, "source": None}]}


def test_single_shot_when_no_validate_fn_given():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("output text")) as mock_chat:
        result = run_with_retry("system prompt", {"a": "content"})

    assert mock_chat.call_count == 1
    assert result == {"output": "output text", "prompt": {"a": "content", "constraints": ""}, "validation": None, "rounds": 1}


def test_stops_immediately_when_valid_on_first_round():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("output")) as mock_chat, \
         patch("generation_toolkit.generation_agent._default_root_cause") as mock_root_cause:
        result = run_with_retry("system prompt", {"a": "x"}, validate_fn=lambda _: valid_result())

    assert mock_chat.call_count == 1
    assert result["rounds"] == 1
    assert result["validation"]["valid"] is True
    mock_root_cause.assert_not_called()


def test_regenerates_once_then_succeeds_and_adds_one_constraint():
    validations = [invalid_result("missing thing"), valid_result()]
    with patch.object(ai_layer_client, "chat", return_value=ok_response("output")) as mock_chat:
        result = run_with_retry("system prompt", {"a": "x"}, validate_fn=lambda _: validations.pop(0))

    assert mock_chat.call_count == 2
    assert result["rounds"] == 2
    assert "Fix: missing thing" in result["prompt"]["constraints"]


def test_stops_at_max_rounds_when_never_valid():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("output")) as mock_chat:
        result = run_with_retry("system prompt", {"a": "x"}, validate_fn=lambda _: invalid_result())

    assert mock_chat.call_count == DEFAULT_MAX_REGENERATE_ROUNDS + 1
    assert result["rounds"] == DEFAULT_MAX_REGENERATE_ROUNDS + 1
    assert result["validation"]["valid"] is False


def test_respects_a_custom_max_regenerate_rounds():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("output")) as mock_chat:
        result = run_with_retry("system prompt", {"a": "x"}, validate_fn=lambda _: invalid_result(), max_regenerate_rounds=1)

    assert mock_chat.call_count == 2
    assert result["rounds"] == 2


def test_uses_a_custom_root_cause_fn():
    validations = [invalid_result("missing thing"), valid_result()]
    with patch.object(ai_layer_client, "chat", return_value=ok_response("output")):
        result = run_with_retry(
            "system prompt", {"a": "x"},
            validate_fn=lambda _: validations.pop(0),
            root_cause_fn=lambda v: f"custom: {v['issues'][0]['message']}",
        )

    assert "custom: missing thing" in result["prompt"]["constraints"]


def test_strips_code_fence_regardless_of_language_tag():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("```xml\n<content/>\n```")):
        result = run_with_retry("system prompt", {"a": "x"})

    assert result["output"] == "<content/>"


def test_default_render_includes_all_parts_and_constraints():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("output")) as mock_chat:
        run_with_retry("system prompt", {"a": "content a", "b": "content b"}, constraints=["do this"])

    user_content = mock_chat.call_args.args[0][1]["content"]
    assert "content a" in user_content
    assert "content b" in user_content
    assert "do this" in user_content


def test_custom_render_user_content_is_used():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("output")) as mock_chat:
        run_with_retry("system prompt", {"a": "x"}, render_user_content=lambda p: f"CUSTOM: {p['a']}")

    assert mock_chat.call_args.args[0][1]["content"] == "CUSTOM: x"


def test_forwards_model_to_chat():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("output")) as mock_chat:
        run_with_retry("system prompt", {"a": "x"}, model="gemini-flash")

    assert mock_chat.call_args.kwargs["model"] == "gemini-flash"


def test_prior_constraints_carried_into_first_round():
    with patch.object(ai_layer_client, "chat", return_value=ok_response("output")) as mock_chat:
        run_with_retry("system prompt", {"a": "x"}, constraints=["Use camelCase names"])

    assert mock_chat.call_count == 1
    assert "Use camelCase names" in mock_chat.call_args.args[0][1]["content"]


def test_strip_code_fence_leaves_unfenced_content_unchanged():
    assert strip_code_fence("<content/>") == "<content/>"
