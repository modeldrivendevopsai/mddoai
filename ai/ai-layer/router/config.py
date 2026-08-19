import json
import os
from pathlib import Path

from dotenv import load_dotenv

load_dotenv()


def _get_oauth_token():
    """Return Claude OAuth token from env or ~/.claude/.credentials.json (written by `claude login`).

    Read once at process startup, not per-request. CLAUDE_CODE_OAUTH_TOKEN (from
    `claude setup-token`) is long-lived (about a year) so this is fine for it. The
    ~/.claude/.credentials.json accessToken (from `claude login`) is short-lived
    (~60 minutes) and is refreshed on disk by the CLI as needed — a long-running
    server process here will NOT pick up that refresh and will start failing auth
    about an hour after startup. Restart the process to pick up a refreshed token.
    """
    token = os.getenv("CLAUDE_CODE_OAUTH_TOKEN")
    if token:
        return token
    # .credentials.json is the hidden dot-file `claude login` creates; the access token
    # lives at claudeAiOauth.accessToken inside it.
    creds_path = Path.home() / ".claude" / ".credentials.json"
    if creds_path.exists():
        try:
            data = json.loads(creds_path.read_text())
            # `.get(..., {})`'s default only applies if the key is absent, not if it's
            # present with value null — guard against a corrupted/partial credentials file.
            return (data.get("claudeAiOauth") or {}).get("accessToken")
        except (json.JSONDecodeError, OSError, AttributeError):
            return None
    return None


# Models are tried in priority order. Any provider with a missing API key is skipped.
#
# Each "model" value has a hardcoded default but can be overridden by an env var
# (see .env.example). Free-tier providers regularly retire/rename specific model
# IDs on their own schedule (e.g. Groq decommissioned llama-3.3-70b-versatile in
# August 2026, Mistral retires dated snapshots like mistral-small-2506 a few
# months after release) — when that happens, router.py's own fallback chain
# already keeps requests working by dropping to the next provider, but fixing
# the broken entry itself should be a one-line env var + restart, not a code
# change, rebuild, and hunt through every test file that names it.
MODELS = [
    {"name": "gemini-flash",       "model": os.getenv("GEMINI_FLASH_MODEL", "gemini/gemini-2.5-flash"),      "key": os.getenv("GOOGLE_API_KEY"),    "tier": "free"},
    {"name": "cerebras-120b",      "model": os.getenv("CEREBRAS_MODEL", "cerebras/gpt-oss-120b"),            "key": os.getenv("CEREBRAS_API_KEY"),  "tier": "free"},
    {"name": "groq-oss-120b",      "model": os.getenv("GROQ_MODEL", "groq/openai/gpt-oss-120b"),             "key": os.getenv("GROQ_API_KEY"),      "tier": "free"},
    {"name": "mistral-small",      "model": os.getenv("MISTRAL_SMALL_MODEL", "mistral/mistral-small-latest"), "key": os.getenv("MISTRAL_API_KEY"),   "tier": "free"},
    # Claude Pro/Max subscription — token from env or ~/.claude/.credentials.json
    {"name": "claude-subscription","model": os.getenv("CLAUDE_SUBSCRIPTION_MODEL", "anthropic/claude-haiku-4-5"), "key": _get_oauth_token(),        "tier": "subscription"},
    {"name": "claude",             "model": os.getenv("CLAUDE_MODEL", "anthropic/claude-sonnet-4-6"),        "key": os.getenv("ANTHROPIC_API_KEY"), "tier": "commercial"},
]

AVAILABLE = [m for m in MODELS if m["key"]]
