from litellm import Router
from .config import AVAILABLE
from .logger import log_call

if not AVAILABLE:
    raise RuntimeError("No API keys configured. Add at least one key to .env (see .env.example).")

_names = [m["name"] for m in AVAILABLE]
_tier  = {m["model"]: m["tier"] for m in AVAILABLE}

# Build the LiteLLM router. Each provider falls back to the next on RateLimitError.
_router = Router(
    model_list=[
        {"model_name": m["name"], "litellm_params": {"model": m["model"], "api_key": m["key"]}}
        for m in AVAILABLE
    ],
    fallbacks=[{_names[i]: _names[i + 1:]} for i in range(len(_names) - 1)],
    num_retries=0,
)


AUTO = "auto"


def chat(messages: list[dict], model: str | None = None, **kwargs):
    """Call the LLM. `model="auto"` (or omitted/unrecognized) runs the full default
    priority chain starting at _names[0]; naming a specific provider starts there
    instead. Either way, litellm falls back through the remaining providers on failure."""
    requested_a_specific_provider = model is not None and model != AUTO and model in _names
    starting_model = model if requested_a_specific_provider else _names[0]
    response = _router.completion(model=starting_model, messages=messages, **kwargs)
    tier = _tier.get(response.model, "free")
    log_call(response.model or starting_model, tier, response.usage)
    return response
