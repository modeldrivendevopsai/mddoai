"""
Full end-to-end review of the Claude Pro OAuth integration.

Sections
--------
1  Credential detection  (3 scenarios)
2  Token format          (raw pass-through, no Bearer prefix)
3  Priority order        (subscription before commercial)
4  Error handling        (expired / invalid / no-subscription)
5  Fallback chain        (fallbacks dict structure)
6  Edge cases            (malformed file, null field, empty-string env)
7  .env.example          (documents auto-detection)
8  Token refresh          (per-call refresh replaces the key in place, no duplicates)
"""

import json
import os
import sys
import tempfile
import types
from pathlib import Path
from unittest.mock import patch as mock_patch

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from conftest import reload_router_modules

# ── Helpers ───────────────────────────────────────────────────────────────────

results: list[tuple[str, bool, str]] = []


def check(label: str, passed: bool, detail: str = "") -> None:
    status = "PASS" if passed else "FAIL"
    suffix = f"  ({detail})" if detail else ""
    print(f"[{status}] {label}{suffix}")
    results.append((label, passed, detail))


def reload_config(env_patch, home_dir=None):
    """Reload router.config under a patched environment.

    home_dir, when given, patches pathlib.Path.home() directly rather than the
    HOME env var so credential-file detection is exercised identically on
    Windows (which has no HOME) and Unix.
    """
    saved = {k: os.environ.get(k) for k in env_patch}

    for k, v in env_patch.items():
        if v is None:
            os.environ.pop(k, None)
        else:
            os.environ[k] = v

    reload_router_modules()

    if home_dir is not None:
        with mock_patch("pathlib.Path.home", return_value=Path(home_dir)):
            import router.config as cfg
    else:
        import router.config as cfg

    # Restore
    for k, saved_v in saved.items():
        if saved_v is None:
            os.environ.pop(k, None)
        else:
            os.environ[k] = saved_v

    return cfg


def make_creds_home(token="sk-ant-oat01-fromfile"):
    """Return a tmpdir whose .claude/.credentials.json contains the given accessToken."""
    d = tempfile.mkdtemp()
    dot_claude = Path(d) / ".claude"
    dot_claude.mkdir()
    payload = {"claudeAiOauth": {"accessToken": token, "refreshToken": "rt-dummy"}}
    (dot_claude / ".credentials.json").write_text(json.dumps(payload))
    return d


# ── Section 1: Credential detection ─────────────────────────────────────────

print("\n=== Section 1: Credential detection ===")

OAUTH_TOKEN = "sk-ant-oat01-envtoken"
FILE_TOKEN  = "sk-ant-oat01-filetoken"

# 1a: env var takes precedence
cfg = reload_config({"CLAUDE_CODE_OAUTH_TOKEN": OAUTH_TOKEN,
                     "ANTHROPIC_API_KEY": None})
sub = next((m for m in cfg.AVAILABLE if m["name"] == "claude-subscription"), None)
check(
    "1a  CLAUDE_CODE_OAUTH_TOKEN env var -> used as subscription key",
    sub is not None and sub["key"] == OAUTH_TOKEN,
    f"key={sub['key'] if sub else 'N/A'}",
)
check(
    "1a2 claude-subscription model is anthropic/claude-haiku-4-5",
    sub is not None and sub["model"] == "anthropic/claude-haiku-4-5",
    f"model={sub['model'] if sub else 'N/A'}",
)
check(
    "1a3 claude-subscription tier is subscription",
    sub is not None and sub["tier"] == "subscription",
    f"tier={sub['tier'] if sub else 'N/A'}",
)

# 1b: env var absent -> reads file
home_dir = make_creds_home(FILE_TOKEN)
cfg = reload_config({"CLAUDE_CODE_OAUTH_TOKEN": None,
                     "ANTHROPIC_API_KEY": None}, home_dir=home_dir)
sub = next((m for m in cfg.AVAILABLE if m["name"] == "claude-subscription"), None)
check(
    "1b  No env var -> reads ~/.claude/.credentials.json -> claudeAiOauth.accessToken",
    sub is not None and sub["key"] == FILE_TOKEN,
    f"key={sub['key'] if sub else 'N/A'}",
)

# 1c: neither -> claude-subscription absent from AVAILABLE (no crash)
# Must patch Path.home() to an empty dir too, otherwise this reads whatever
# real ~/.claude/.credentials.json happens to exist on the machine running the test.
empty_home = tempfile.mkdtemp()
cfg = reload_config({"CLAUDE_CODE_OAUTH_TOKEN": None,
                     "ANTHROPIC_API_KEY": None}, home_dir=empty_home)
names = [m["name"] for m in cfg.AVAILABLE]
check(
    "1c  No token anywhere -> claude-subscription silently absent from AVAILABLE",
    "claude-subscription" not in names,
    f"names={names}",
)

# ── Section 2: Token format ───────────────────────────────────────────────────

print("\n=== Section 2: Token format ===")

from router.router import _litellm_params  # the real function, not a duplicate

raw_token = "sk-ant-oat01-rawtoken"
entry = {"name": "claude-subscription", "model": "anthropic/claude-haiku-4-5", "key": raw_token}
p = _litellm_params(entry)

check(
    "2a  _litellm_params passes raw sk-ant-oat token unchanged",
    p["api_key"] == raw_token,
    f"api_key={p['api_key']!r}",
)
check(
    "2b  _litellm_params does NOT add 'Bearer ' prefix",
    not p["api_key"].startswith("Bearer "),
    f"api_key={p['api_key']!r}",
)

# ── Section 3: Priority order ────────────────────────────────────────────────

print("\n=== Section 3: Priority order ===")

cfg = reload_config({
    "CLAUDE_CODE_OAUTH_TOKEN": "sk-ant-oat01-sub",
    "ANTHROPIC_API_KEY": "sk-ant-api03-commercial",
    "GOOGLE_API_KEY": None, "MISTRAL_API_KEY": None,
    "CEREBRAS_API_KEY": None, "GROQ_API_KEY": None,
})
names = [m["name"] for m in cfg.AVAILABLE]
has_both = "claude-subscription" in names and "claude" in names
check(
    "3a  Both tokens present -> claude-subscription before claude in AVAILABLE",
    has_both and names.index("claude-subscription") < names.index("claude"),
    f"order={names}",
)

# ── Section 4: Error handling ────────────────────────────────────────────────
# These call the real router.router.chat(), with only _router.completion mocked,
# so they exercise the actual exception-handling code, not a copy of it.

print("\n=== Section 4: Error handling ===")

import litellm as _litellm_for_errors


def _reload_router_with_subscription():
    """Fresh import of router.router configured with a subscription entry.

    Reuses reload_config() (defined above for Section 1) for the module-clearing
    and env-patching, instead of duplicating that logic.
    """
    reload_config({
        "CLAUDE_CODE_OAUTH_TOKEN": "sk-ant-oat01-forerrortest",
        "GOOGLE_API_KEY": None, "MISTRAL_API_KEY": None,
        "CEREBRAS_API_KEY": None, "GROQ_API_KEY": None, "ANTHROPIC_API_KEY": None,
    })
    import router.router as rr
    return rr


def _call_chat_with_auth_error(message: str):
    """Call the real chat() with _router.completion raising a real AuthenticationError
    attributed to the subscription model."""
    rr = _reload_router_with_subscription()

    def raise_it(*args, **kwargs):
        raise _litellm_for_errors.AuthenticationError(
            message=message, llm_provider="anthropic", model=rr._subscription_model
        )

    with mock_patch.object(rr, "_router") as mock_router:
        mock_router.completion.side_effect = raise_it
        rr.chat([{"role": "user", "content": "hi"}])


# 4a: expired token
try:
    _call_chat_with_auth_error("authentication_error: token expired")
    check("4a  Expired token -> RuntimeError mentioning claude setup-token", False, "no exception raised")
except RuntimeError as rt:
    check(
        "4a  Expired token -> RuntimeError mentioning `claude setup-token`",
        "claude setup-token" in str(rt) and "expired" in str(rt).lower(),
        str(rt)[:80],
    )

# 4b: invalid/revoked token
try:
    _call_chat_with_auth_error("invalid_api_key: bad token")
    check("4b  Invalid token -> RuntimeError mentioning token prefix check", False, "no exception raised")
except RuntimeError as rt:
    check(
        "4b  Invalid/revoked token -> RuntimeError mentioning 'sk-ant-oat' prefix",
        "sk-ant-oat" in str(rt),
        str(rt)[:80],
    )

# 4c: a DIFFERENT provider's auth error, even with a subscription configured,
# must be re-raised unchanged, not misattributed to OAuth
try:
    rr = _reload_router_with_subscription()

    def raise_other_provider_error(*args, **kwargs):
        raise _litellm_for_errors.AuthenticationError(
            message="invalid_api_key", llm_provider="gemini", model="gemini-2.5-flash"
        )

    with mock_patch.object(rr, "_router") as mock_router:
        mock_router.completion.side_effect = raise_other_provider_error
        rr.chat([{"role": "user", "content": "hi"}])
    check("4c  Different provider's auth error -> re-raised, not misattributed to OAuth",
          False, "no exception raised")
except _litellm_for_errors.AuthenticationError:
    check("4c  Different provider's auth error -> re-raised, not misattributed to OAuth", True)
except RuntimeError as rt:
    check("4c  Different provider's auth error -> re-raised, not misattributed to OAuth", False,
          f"got RuntimeError instead: {rt}")

# 4d: expired detection is case-insensitive
try:
    _call_chat_with_auth_error("Token Expiration: credential has expiry")
    check("4d  Expiry keyword is case-insensitive", False, "no exception raised")
except RuntimeError as rt:
    check(
        "4d  Expiry keyword detected case-insensitively",
        "expired" in str(rt).lower(),
        str(rt)[:60],
    )

# ── Section 5: Fallback chain ────────────────────────────────────────────────

print("\n=== Section 5: Fallback chain ===")

cfg = reload_config({
    "CLAUDE_CODE_OAUTH_TOKEN": "sk-ant-oat01-sub",
    "ANTHROPIC_API_KEY": "sk-ant-api03-commercial",
    "GOOGLE_API_KEY": "gk-free",
    "MISTRAL_API_KEY": None, "CEREBRAS_API_KEY": None, "GROQ_API_KEY": None,
})
names5 = [m["name"] for m in cfg.AVAILABLE]

# Re-derive the fallbacks list as router.py does
fallbacks = [{names5[i]: names5[i + 1:]} for i in range(len(names5) - 1)]

sub_idx = names5.index("claude-subscription") if "claude-subscription" in names5 else -1
claude_idx = names5.index("claude") if "claude" in names5 else -1

# claude-subscription's fallback entry should include "claude"
sub_fallback = next(
    (v for d in fallbacks for k, v in d.items() if k == "claude-subscription"), None
)
check(
    "5a  claude-subscription fallback entry includes 'claude' as next provider",
    sub_fallback is not None and "claude" in sub_fallback,
    f"sub_fallback={sub_fallback}",
)

# gemini-flash's fallback should include every subsequent provider including claude-subscription
free_fallback = next(
    (v for d in fallbacks for k, v in d.items() if k == "gemini-flash"), None
)
check(
    "5b  First free provider fallback chain includes claude-subscription and claude",
    free_fallback is not None
    and "claude-subscription" in free_fallback
    and "claude" in free_fallback,
    f"free_fallback={free_fallback}",
)

# ── Section 6: Edge cases ────────────────────────────────────────────────────

print("\n=== Section 6: Edge cases ===")

# Helper: call _get_oauth_token with a patched home directory
def get_token_from_home(home_dir):
    """Import a fresh config with HOME patched and return the resolved OAuth token."""
    cfg_edge = reload_config({"CLAUDE_CODE_OAUTH_TOKEN": None,
                               "ANTHROPIC_API_KEY": None}, home_dir=home_dir)
    sub_edge = next((m for m in cfg_edge.AVAILABLE if m["name"] == "claude-subscription"), None)
    return sub_edge["key"] if sub_edge else None


# 6a: .credentials.json exists but claudeAiOauth key is missing entirely
d6a = tempfile.mkdtemp()
dot_claude_6a = Path(d6a) / ".claude"
dot_claude_6a.mkdir()
(dot_claude_6a / ".credentials.json").write_text(json.dumps({"someOtherKey": "value"}))
check(
    "6a  credentials file missing claudeAiOauth -> no crash, claude-subscription absent",
    get_token_from_home(d6a) is None,
)

# 6b: claudeAiOauth exists but accessToken is null
d6b = tempfile.mkdtemp()
dot_claude_6b = Path(d6b) / ".claude"
dot_claude_6b.mkdir()
(dot_claude_6b / ".credentials.json").write_text(
    json.dumps({"claudeAiOauth": {"accessToken": None, "refreshToken": "rt"}})
)
check(
    "6b  claudeAiOauth.accessToken is null -> no crash, claude-subscription absent",
    get_token_from_home(d6b) is None,
)

# 6b2: claudeAiOauth itself (not just accessToken) is null — dict.get(k, {}) only
# substitutes the default when k is ABSENT, not when it's present with value null,
# so this previously crashed with AttributeError on `.get("accessToken")`.
d6b2 = tempfile.mkdtemp()
dot_claude_6b2 = Path(d6b2) / ".claude"
dot_claude_6b2.mkdir()
(dot_claude_6b2 / ".credentials.json").write_text(json.dumps({"claudeAiOauth": None}))
try:
    result_6b2 = get_token_from_home(d6b2)
    check(
        "6b2 claudeAiOauth itself is null -> no crash, claude-subscription absent",
        result_6b2 is None,
        f"result={result_6b2!r}",
    )
except AttributeError as exc:
    check(
        "6b2 claudeAiOauth itself is null -> no crash, claude-subscription absent",
        False,
        f"crashed: {exc}",
    )

# 6c: malformed JSON
d6c = tempfile.mkdtemp()
dot_claude_6c = Path(d6c) / ".claude"
dot_claude_6c.mkdir()
(dot_claude_6c / ".credentials.json").write_text("{not valid json!!!")
check(
    "6c  malformed JSON in credentials file -> no crash, claude-subscription absent",
    get_token_from_home(d6c) is None,
)

# 6d: CLAUDE_CODE_OAUTH_TOKEN="" (empty string) -> falls through to file
d6d = make_creds_home("sk-ant-oat01-fromfile-edge")
cfg_6d = reload_config({"CLAUDE_CODE_OAUTH_TOKEN": "",
                         "ANTHROPIC_API_KEY": None}, home_dir=d6d)
sub_6d = next((m for m in cfg_6d.AVAILABLE if m["name"] == "claude-subscription"), None)
check(
    "6d  CLAUDE_CODE_OAUTH_TOKEN='' (empty string) -> falls through to credentials file",
    sub_6d is not None and sub_6d["key"] == "sk-ant-oat01-fromfile-edge",
    f"key={sub_6d['key'] if sub_6d else 'N/A'}",
)

# 6e: No HOME directory at all (HOME unset, no file) -> no crash
try:
    cfg_6e = reload_config({"CLAUDE_CODE_OAUTH_TOKEN": None, "ANTHROPIC_API_KEY": None},
                            home_dir="/nonexistent-dir-that-does-not-exist")
    check("6e  HOME points to non-existent dir -> no crash", True)
except Exception as exc:
    check("6e  HOME points to non-existent dir -> no crash", False, str(exc)[:60])

# ── Section 7: .env.example accuracy ────────────────────────────────────────

print("\n=== Section 7: .env.example accuracy ===")

env_example_path = Path(__file__).resolve().parent.parent / ".env.example"
env_text = env_example_path.read_text()

check(
    "7a  .env.example documents CLAUDE_CODE_OAUTH_TOKEN",
    "CLAUDE_CODE_OAUTH_TOKEN" in env_text,
)
check(
    "7b  .env.example mentions ~/.claude auto-detection (for `claude login` users)",
    ".credentials.json" in env_text or "claude login" in env_text,
    "missing mention of .credentials.json or claude login",
)
check(
    "7c  .env.example explains this is an alternative to ANTHROPIC_API_KEY",
    "alternative" in env_text.lower() or "no extra" in env_text.lower(),
    env_text[env_text.find("CLAUDE_CODE"):env_text.find("CLAUDE_CODE") + 200] if "CLAUDE_CODE" in env_text else "",
)
check(
    "7d  .env.example mentions claude setup-token command",
    "claude setup-token" in env_text,
)

# ── Section 8: Token refresh ─────────────────────────────────────────────────
# _refresh_subscription_token() re-reads the token on every chat() call so a
# long-running process picks up the CLI's background refresh instead of using
# whatever was cached at startup. These check it actually replaces the key in
# place, rather than accumulating duplicate deployments litellm would then
# load-balance across non-deterministically.

print("\n=== Section 8: Token refresh ===")

rr8 = _reload_router_with_subscription()

gemini_key_before = next(
    (d["litellm_params"].get("api_key") for d in rr8._router.get_model_list()
     if d["model_name"] == "gemini-flash"),
    None,
)

with mock_patch.object(rr8, "_get_oauth_token", return_value="sk-ant-oat01-refreshed"):
    rr8._refresh_subscription_token()

sub_deployments = [d for d in rr8._router.get_model_list() if d["model_name"] == "claude-subscription"]
check(
    "8a  Refresh updates the subscription key in place",
    len(sub_deployments) == 1 and sub_deployments[0]["litellm_params"].get("api_key") == "sk-ant-oat01-refreshed",
    f"deployments={[d['litellm_params'].get('api_key') for d in sub_deployments]}",
)

with mock_patch.object(rr8, "_get_oauth_token", return_value="sk-ant-oat01-refreshed-again"):
    rr8._refresh_subscription_token()
    rr8._refresh_subscription_token()

sub_deployments_after = [d for d in rr8._router.get_model_list() if d["model_name"] == "claude-subscription"]
check(
    "8b  Repeated refreshes don't accumulate duplicate deployments",
    len(sub_deployments_after) == 1,
    f"deployment count={len(sub_deployments_after)}",
)

gemini_key_after = next(
    (d["litellm_params"].get("api_key") for d in rr8._router.get_model_list()
     if d["model_name"] == "gemini-flash"),
    None,
)
check(
    "8c  Refreshing the subscription key leaves other providers untouched",
    gemini_key_after == gemini_key_before,
    f"before={gemini_key_before!r} after={gemini_key_after!r}",
)

with mock_patch.object(rr8, "_get_oauth_token", return_value=None):
    pre_refresh = [d["litellm_params"].get("api_key") for d in rr8._router.get_model_list()
                   if d["model_name"] == "claude-subscription"]
    rr8._refresh_subscription_token()
    post_refresh = [d["litellm_params"].get("api_key") for d in rr8._router.get_model_list()
                    if d["model_name"] == "claude-subscription"]
check(
    "8d  A falsy fresh token (e.g. file briefly unreadable) doesn't clobber the last-known-good key",
    pre_refresh == post_refresh,
    f"pre={pre_refresh} post={post_refresh}",
)

# ── Summary ───────────────────────────────────────────────────────────────────

total  = len(results)
passed = sum(1 for _, p, _ in results if p)
failed = [(label, detail) for label, p, detail in results if not p]


def test_oauth_full_checks():
    """Entry point for pytest: fails if any check above did not pass."""
    if failed:
        print("\nFAILURES:")
        for label, detail in failed:
            print(f"  [FAIL] {label}" + (f"  ({detail})" if detail else ""))
    print(f"\nResult: {passed}/{total} checks passed")
    assert passed == total, f"{total - passed}/{total} checks failed"


if __name__ == "__main__":
    if failed:
        print("FAILURES:")
        for label, detail in failed:
            print(f"  [FAIL] {label}" + (f"  ({detail})" if detail else ""))
    print(f"\nResult: {passed}/{total} checks passed")
    sys.exit(0 if passed == total else 1)
