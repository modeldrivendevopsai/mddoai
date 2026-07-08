"""
Shared test setup for ai-layer.

Fake provider keys are set here, at conftest.py import time, so they're already
in place before pytest imports any test_*.py module in this directory — those
files read AVAILABLE at module level (e.g. `import router.config`), which runs
during collection, before any fixture would get a chance to run.
"""
import os
import sys

# Fake keys so all providers appear available to any test that imports router.config
# fresh. Uses setdefault so a real .env (if present) isn't clobbered.
for _key in ("GOOGLE_API_KEY", "MISTRAL_API_KEY", "CEREBRAS_API_KEY", "GROQ_API_KEY", "ANTHROPIC_API_KEY"):
    os.environ.setdefault(_key, f"test-{_key.lower()}")


def reload_router_modules() -> None:
    """Drop cached router.* modules so the next `import router...` re-executes
    them against the current os.environ, instead of reusing whatever config
    another test module built under different (possibly patched) env vars."""
    for mod in list(sys.modules):
        if mod.startswith("router"):
            del sys.modules[mod]
