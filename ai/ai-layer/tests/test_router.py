"""
Router fallback tests. No real API calls — litellm.completion is mocked.

Tests verify:
  1. All free providers exhausted → commercial Claude is called.
  2. Providers 1-3 fail → Groq handles it, commercial Claude is not called.
"""
import os
from unittest.mock import MagicMock, patch

from litellm import Router
from litellm.exceptions import RateLimitError

# Fake keys must be set before importing router modules so all five providers appear available.
for key in ("GOOGLE_API_KEY", "MISTRAL_API_KEY", "CEREBRAS_API_KEY", "GROQ_API_KEY", "ANTHROPIC_API_KEY"):
    os.environ.setdefault(key, f"test-{key.lower()}")

from router.config import AVAILABLE  # noqa: E402
from router import router as router_module  # noqa: E402


def make_router():
    names = [m["name"] for m in AVAILABLE]
    router = Router(
        model_list=[
            {"model_name": m["name"], "litellm_params": {"model": m["model"], "api_key": m["key"]}}
            for m in AVAILABLE
        ],
        fallbacks=[{names[i]: names[i + 1:]} for i in range(len(names) - 1)],
        num_retries=0,
    )
    return router, names[0]


def ok_response(model):
    r = MagicMock()
    r.model = model
    r.usage = MagicMock(prompt_tokens=10, completion_tokens=5, total_tokens=15)
    r.choices = [MagicMock(message=MagicMock(content="ok"))]
    return r


def rate_limit(model):
    return RateLimitError(
        message="Rate limit exceeded",
        model=model,
        llm_provider="test",
        response=MagicMock(status_code=429),
    )


def is_commercial(model):
    return "claude" in model or "anthropic" in model


def is_groq(model):
    return "groq" in model or "llama" in model


def test_all_free_exhausted_falls_back_to_commercial():
    router, primary = make_router()
    called = []

    def side_effect(*args, **kwargs):
        model = kwargs.get("model", "")
        called.append(model)
        if is_commercial(model):
            return ok_response("anthropic/claude-sonnet-4-6")
        raise rate_limit(model)

    with patch("litellm.completion", side_effect=side_effect):
        response = router.completion(model=primary, messages=[{"role": "user", "content": "hi"}])

    assert is_commercial(response.model), f"Expected Claude, got {response.model}"
    assert any(not is_commercial(m) for m in called), "No free provider was tried before falling back"


def test_providers_1_to_3_fail_groq_handles_it():
    router, primary = make_router()
    called = []

    def side_effect(*args, **kwargs):
        model = kwargs.get("model", "")
        called.append(model)
        if is_groq(model):
            return ok_response("groq/llama-3.3-70b-versatile")
        raise rate_limit(model)

    with patch("litellm.completion", side_effect=side_effect):
        response = router.completion(model=primary, messages=[{"role": "user", "content": "hi"}])

    assert is_groq(response.model), f"Expected Groq, got {response.model}"
    assert not any(is_commercial(m) for m in called), "Commercial Claude should not have been called"


def test_chat_starts_from_requested_model():
    names = [m["name"] for m in AVAILABLE]
    requested = names[2]

    with patch.object(router_module, "_router") as mock_router:
        mock_router.completion.return_value = ok_response(AVAILABLE[2]["model"])
        router_module.chat([{"role": "user", "content": "hi"}], model=requested)

    assert mock_router.completion.call_args.kwargs["model"] == requested


def test_chat_ignores_invalid_model_and_uses_default():
    names = [m["name"] for m in AVAILABLE]

    with patch.object(router_module, "_router") as mock_router:
        mock_router.completion.return_value = ok_response(AVAILABLE[0]["model"])
        router_module.chat([{"role": "user", "content": "hi"}], model="not-a-real-provider")

    assert mock_router.completion.call_args.kwargs["model"] == names[0]


def test_chat_explicit_auto_uses_default():
    names = [m["name"] for m in AVAILABLE]

    with patch.object(router_module, "_router") as mock_router:
        mock_router.completion.return_value = ok_response(AVAILABLE[0]["model"])
        router_module.chat([{"role": "user", "content": "hi"}], model="auto")

    assert mock_router.completion.call_args.kwargs["model"] == names[0]
