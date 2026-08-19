import json
from datetime import datetime, timezone


def _now() -> str:
    return datetime.now(timezone.utc).strftime("%Y-%m-%dT%H:%M:%SZ")


def log_call(model: str, tier: str, usage) -> None:
    print(json.dumps({
        "timestamp": _now(),
        "model": model,
        "tier": tier,
        "input_tokens": getattr(usage, "prompt_tokens", 0),
        "output_tokens": getattr(usage, "completion_tokens", 0),
        "total_tokens": getattr(usage, "total_tokens", 0),
    }), flush=True)


def log_deployment_failure(model: str, error: str) -> None:
    """One deployment in the fallback chain failed. Without this, a request
    that silently falls back through 1-2 failed providers before succeeding
    is indistinguishable in the logs from one that hit its first choice
    cleanly — observed for real: two consecutive real calls (explicitly
    requesting cerebras-120b and groq-oss-120b) came back attributed to
    mistral-small-latest instead, with no log trail anywhere explaining
    why."""
    print(json.dumps({
        "timestamp": _now(),
        "event": "deployment_failed",
        "model": model,
        "error": error,
    }), flush=True)
