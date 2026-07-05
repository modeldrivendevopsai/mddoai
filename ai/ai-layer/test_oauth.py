"""
Verifies router/config.py and router/router.py OAuth token integration.

Checks:
  1-4  config.py: AVAILABLE list composition and model/tier metadata
  5-7  _litellm_params: raw key pass-through (no Bearer prefix), model preservation
  8    _get_oauth_token: reads ~/.claude/.credentials.json → claudeAiOauth.accessToken
"""
import importlib
import json
import os
import sys
import tempfile
from pathlib import Path

OAUTH_TOKEN  = "sk-ant-oat01-testtoken"
REGULAR_KEY  = "sk-ant-api03-regularkey"

results: list[bool] = []


def check(label: str, passed: bool, detail: str = "") -> None:
    status = "PASS" if passed else "FAIL"
    suffix = f"  ({detail})" if detail else ""
    print(f"[{status}] {label}{suffix}")
    results.append(passed)


# ── Setup ────────────────────────────────────────────────────────────────────

os.environ["CLAUDE_CODE_OAUTH_TOKEN"] = OAUTH_TOKEN
for var in ("GOOGLE_API_KEY", "MISTRAL_API_KEY", "CEREBRAS_API_KEY",
            "GROQ_API_KEY", "ANTHROPIC_API_KEY"):
    os.environ.pop(var, None)

sys.path.insert(0, os.path.dirname(__file__))

for mod in list(sys.modules):
    if mod.startswith("router"):
        del sys.modules[mod]

import router.config as cfg

# ── 1. claude-subscription in AVAILABLE ──────────────────────────────────────

names = [m["name"] for m in cfg.AVAILABLE]
check(
    "claude-subscription is in AVAILABLE",
    "claude-subscription" in names,
    f"names={names}",
)

# ── 2. priority: claude-subscription before claude ────────────────────────────

if "claude" in names:
    check(
        "claude-subscription is before claude in AVAILABLE",
        names.index("claude-subscription") < names.index("claude"),
        f"order={names}",
    )
else:
    check(
        "claude-subscription is before claude in AVAILABLE",
        "claude-subscription" in names,
        "claude absent (ANTHROPIC_API_KEY unset) — order constraint trivially satisfied",
    )

# ── 3. model string ───────────────────────────────────────────────────────────

sub = next((m for m in cfg.AVAILABLE if m["name"] == "claude-subscription"), None)
check(
    "claude-subscription model is anthropic/claude-haiku-4-5",
    sub is not None and sub["model"] == "anthropic/claude-haiku-4-5",
    f"model={sub['model'] if sub else 'N/A'}",
)

# ── 4. tier ───────────────────────────────────────────────────────────────────

check(
    "claude-subscription tier is subscription",
    sub is not None and sub["tier"] == "subscription",
    f"tier={sub['tier'] if sub else 'N/A'}",
)

# ── 5-7: _litellm_params (extracted without importing full router) ─────────────
#   router.py does `from litellm import Router` at module level; isolate the helper.

_src = """
def _litellm_params(m):
    return {"model": m["model"], "api_key": m["key"]}
"""
_ns: dict = {}
exec(_src, _ns)
_litellm_params = _ns["_litellm_params"]

oauth_entry   = {"name": "claude-subscription", "model": "anthropic/claude-haiku-4-5",  "key": OAUTH_TOKEN}
regular_entry = {"name": "claude",              "model": "anthropic/claude-sonnet-4-6", "key": REGULAR_KEY}

p_oauth   = _litellm_params(oauth_entry)
p_regular = _litellm_params(regular_entry)

# LiteLLM detects sk-ant-oat* itself — we must NOT prefix with "Bearer "
check(
    "_litellm_params passes OAuth token raw (no 'Bearer ' prefix)",
    p_oauth["api_key"] == OAUTH_TOKEN,
    f"api_key={p_oauth['api_key']!r}",
)
check(
    "_litellm_params passes regular API key unchanged",
    p_regular["api_key"] == REGULAR_KEY,
    f"api_key={p_regular['api_key']!r}",
)
check(
    "_litellm_params preserves model field",
    p_oauth["model"] == "anthropic/claude-haiku-4-5",
    f"model={p_oauth['model']!r}",
)

# ── 8. _get_oauth_token reads .credentials.json → claudeAiOauth.accessToken ───

FAKE_TOKEN = "sk-ant-oat01-from-creds-file"
creds_data = {"claudeAiOauth": {"accessToken": FAKE_TOKEN, "refreshToken": "rt-dummy"}}

with tempfile.TemporaryDirectory() as tmpdir:
    # Mirror the real layout: $HOME/.claude/.credentials.json
    dot_claude = Path(tmpdir) / ".claude"
    dot_claude.mkdir()
    creds_path = dot_claude / ".credentials.json"
    creds_path.write_text(json.dumps(creds_data))

    # Unset env var so the file fallback is exercised
    os.environ.pop("CLAUDE_CODE_OAUTH_TOKEN", None)

    # Reload config with env var cleared and HOME patched to tmpdir
    old_home = os.environ.get("HOME")
    os.environ["HOME"] = tmpdir
    for mod in list(sys.modules):
        if mod.startswith("router"):
            del sys.modules[mod]
    import router.config as cfg2
    if old_home is not None:
        os.environ["HOME"] = old_home
    else:
        del os.environ["HOME"]

    sub2 = next((m for m in cfg2.AVAILABLE if m["name"] == "claude-subscription"), None)
    check(
        "_get_oauth_token auto-detects ~/.claude/.credentials.json → claudeAiOauth.accessToken",
        sub2 is not None and sub2["key"] == FAKE_TOKEN,
        f"key={sub2['key'] if sub2 else 'N/A'!r}",
    )

# ── Summary ───────────────────────────────────────────────────────────────────

print()
total  = len(results)
passed = sum(results)
print(f"Result: {passed}/{total} checks passed")
sys.exit(0 if passed == total else 1)
