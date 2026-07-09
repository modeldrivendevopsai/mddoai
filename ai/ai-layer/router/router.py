import litellm
from litellm import Router
from litellm.types.router import Deployment, LiteLLM_Params
from .config import AVAILABLE, _get_oauth_token
from .logger import log_call

if not AVAILABLE:
    raise RuntimeError("No API keys configured. Add at least one key to .env (see .env.example).")

_names = [m["name"] for m in AVAILABLE]
_tier  = {m["model"]: m["tier"] for m in AVAILABLE}
_subscription_entry = next((m for m in AVAILABLE if m["tier"] == "subscription"), None)
# litellm strips the provider prefix ("anthropic/claude-haiku-4-5" -> "claude-haiku-4-5")
# on the `model` attribute of exceptions it raises, so compare against the bare name.
_subscription_model = (
    _subscription_entry["model"].split("/", 1)[-1] if _subscription_entry else None
)

def _litellm_params(m: dict) -> dict:
    # Pass the raw key. LiteLLM's Anthropic provider detects sk-ant-oat* tokens
    # and automatically sets Authorization: Bearer + adds the oauth-2025-04-20 beta
    # header (see litellm/llms/anthropic/common_utils.py:get_anthropic_headers).
    # Wrapping with "Bearer " here would defeat that detection.
    return {"model": m["model"], "api_key": m["key"]}


# Build the LiteLLM router. Each provider falls back to the next on RateLimitError.
_router = Router(
    model_list=[
        {"model_name": m["name"], "litellm_params": _litellm_params(m)}
        for m in AVAILABLE
    ],
    fallbacks=[{_names[i]: _names[i + 1:]} for i in range(len(_names) - 1)],
    num_retries=0,
)

# id of the subscription deployment within _router, so we can refresh just its
# api_key in place (upsert_deployment matches on this id — without it, upserting
# adds a second, duplicate deployment instead of replacing the stale one).
_subscription_deployment_id = (
    _router.get_model_ids(_subscription_entry["name"])[0] if _subscription_entry else None
)


def _refresh_subscription_token() -> None:
    """Re-read the OAuth token and update just the subscription deployment's api_key.

    ~/.claude/.credentials.json's accessToken (auto-detect path) is refreshed on disk
    by the CLI roughly hourly; without this, _router would keep using whatever token
    was current at process startup for the process's entire lifetime. Only touches
    the subscription deployment — every other provider's config is untouched.
    """
    if _subscription_deployment_id is None:
        return
    fresh_token = _get_oauth_token()
    if not fresh_token:
        return
    _router.upsert_deployment(Deployment(
        model_name=_subscription_entry["name"],
        litellm_params=LiteLLM_Params(model=_subscription_entry["model"], api_key=fresh_token),
        model_info={"id": _subscription_deployment_id},
    ))


AUTO = "auto"


def chat(messages: list[dict], model: str | None = None, **kwargs):
    """Call the LLM. `model="auto"` (or omitted/unrecognized) runs the full default
    priority chain starting at _names[0]; naming a specific provider starts there
    instead. Either way, litellm falls back through the remaining providers on failure."""
    requested_a_specific_provider = model is not None and model != AUTO and model in _names
    starting_model = model if requested_a_specific_provider else _names[0]
    _refresh_subscription_token()
    try:
        response = _router.completion(model=starting_model, messages=messages, **kwargs)
    except litellm.AuthenticationError as e:
        if _subscription_model is not None and getattr(e, "model", None) == _subscription_model:
            msg = str(e).lower()
            if "expired" in msg or "expir" in msg:
                raise RuntimeError(
                    "Your Claude OAuth token has expired. Run `claude setup-token` to generate "
                    "a new one and update CLAUDE_CODE_OAUTH_TOKEN in your .env file."
                ) from e
            raise RuntimeError(
                "Claude OAuth authentication failed — the token may be malformed or revoked. "
                "Verify the token starts with 'sk-ant-oat' and run `claude setup-token` to "
                "generate a fresh one, then update CLAUDE_CODE_OAUTH_TOKEN in your .env file."
            ) from e
        raise
    tier = _tier.get(response.model, "free")
    log_call(response.model or starting_model, tier, response.usage)
    return response
