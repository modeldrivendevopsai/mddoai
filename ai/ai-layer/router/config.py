import json
import os
from pathlib import Path

from dotenv import load_dotenv

load_dotenv()


def _get_oauth_token():
    """Return the Claude OAuth token from env or ~/.claude/credentials.json, whichever is set."""
    token = os.getenv("CLAUDE_CODE_OAUTH_TOKEN")
    if token:
        return token
    creds_path = Path.home() / ".claude" / "credentials.json"
    if creds_path.exists():
        try:
            return json.loads(creds_path.read_text()).get("oauth_token")
        except (json.JSONDecodeError, OSError):
            return None
    return None


# Models are tried in priority order. Any provider with a missing API key is skipped.
MODELS = [
    {"name": "gemini-flash",       "model": "gemini/gemini-2.5-flash",      "key": os.getenv("GOOGLE_API_KEY"),    "tier": "free"},
    {"name": "mistral-small",      "model": "mistral/mistral-small-2506",   "key": os.getenv("MISTRAL_API_KEY"),   "tier": "free"},
    {"name": "cerebras-120b",      "model": "cerebras/gpt-oss-120b",        "key": os.getenv("CEREBRAS_API_KEY"),  "tier": "free"},
    {"name": "groq-llama",         "model": "groq/llama-3.3-70b-versatile", "key": os.getenv("GROQ_API_KEY"),      "tier": "free"},
    # Claude Pro/Max subscription — token from env or ~/.claude/credentials.json
    {"name": "claude-subscription","model": "anthropic/claude-haiku-4-5",   "key": _get_oauth_token(),             "tier": "subscription"},
    {"name": "claude",             "model": "anthropic/claude-sonnet-4-6",  "key": os.getenv("ANTHROPIC_API_KEY"), "tier": "commercial"},
]

AVAILABLE = [m for m in MODELS if m["key"]]
