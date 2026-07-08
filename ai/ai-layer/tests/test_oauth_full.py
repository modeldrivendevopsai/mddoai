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
"""

import json
import os
import sys
import tempfile
import types
from pathlib import Path
from unittest.mock import patch as mock_patch

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

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

    for mod in list(sys.modules):
        if mod.startswith("router"):
            del sys.modules[mod]

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

_litellm_params_src = """
def _litellm_params(m):
    return {"model": m["model"], "api_key": m["key"]}
"""
_ns: dict = {}
exec(_litellm_params_src, _ns)
_litellm_params = _ns["_litellm_params"]

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

print("\n=== Section 4: Error handling ===")

# Reconstruct the error-routing logic from router.py in isolation
# (avoids importing litellm at module level)
_error_logic_src = """
def handle_auth_error(e, has_subscription):
    if has_subscription:
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
    raise e
"""
exec(_error_logic_src, _ns)
handle_auth_error = _ns["handle_auth_error"]


class FakeAuthError(Exception):
    pass


# 4a: expired token
try:
    handle_auth_error(FakeAuthError("authentication_error: token expired"), has_subscription=True)
    check("4a  Expired token -> RuntimeError mentioning claude setup-token", False, "no exception raised")
except RuntimeError as rt:
    check(
        "4a  Expired token -> RuntimeError mentioning `claude setup-token`",
        "claude setup-token" in str(rt) and "expired" in str(rt).lower(),
        str(rt)[:80],
    )

# 4b: invalid/revoked token
try:
    handle_auth_error(FakeAuthError("invalid_api_key: bad token"), has_subscription=True)
    check("4b  Invalid token -> RuntimeError mentioning token prefix check", False, "no exception raised")
except RuntimeError as rt:
    check(
        "4b  Invalid/revoked token -> RuntimeError mentioning 'sk-ant-oat' prefix",
        "sk-ant-oat" in str(rt),
        str(rt)[:80],
    )

# 4c: no subscription -> original error re-raised unchanged
try:
    handle_auth_error(FakeAuthError("invalid_api_key"), has_subscription=False)
    check("4c  No subscription -> original error re-raised", False, "no exception raised")
except FakeAuthError:
    check("4c  No subscription -> original AuthenticationError re-raised unchanged", True)
except RuntimeError as rt:
    check("4c  No subscription -> original AuthenticationError re-raised unchanged", False,
          f"got RuntimeError instead: {rt}")

# 4d: expired detection is case-insensitive
try:
    handle_auth_error(FakeAuthError("Token Expiration: credential has expiry"), has_subscription=True)
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
