import litellm
from litellm import Router
from .config import AVAILABLE
from .logger import log_call

if not AVAILABLE:
    raise RuntimeError("No API keys configured. Add at least one key to .env (see .env.example).")

_names = [m["name"] for m in AVAILABLE]
_tier  = {m["model"]: m["tier"] for m in AVAILABLE}
_has_subscription = any(m["tier"] == "subscription" for m in AVAILABLE)

def _litellm_params(m: dict) -> dict:
    key = m["key"]
    # OAuth tokens from `claude setup-token` must be sent as Bearer auth
    api_key = f"Bearer {key}" if key and key.startswith("sk-ant-oat") else key
    return {"model": m["model"], "api_key": api_key}


# Build the LiteLLM router. Each provider falls back to the next on RateLimitError.
_router = Router(
    model_list=[
        {"model_name": m["name"], "litellm_params": _litellm_params(m)}
        for m in AVAILABLE
    ],
    fallbacks=[{_names[i]: _names[i + 1:]} for i in range(len(_names) - 1)],
    num_retries=0,
)


def chat(messages: list[dict], **kwargs):
    """Call the LLM. Tries free providers first, falls back to commercial Claude if all fail."""
    try:
        response = _router.completion(model=_names[0], messages=messages, **kwargs)
    except litellm.AuthenticationError as e:
        if _has_subscription:
            raise RuntimeError(
                "Your Claude OAuth token has expired. Run `claude setup-token` to generate "
                "a new one and update CLAUDE_CODE_OAUTH_TOKEN in your .env file."
            ) from e
        raise
    tier = _tier.get(response.model, "free")
    log_call(response.model or _names[0], tier, response.usage)
    return response
