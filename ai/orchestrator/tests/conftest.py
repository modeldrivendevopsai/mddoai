"""
Shared test setup for orchestrator.

orchestrator.py imports router.router (from the sibling ai-layer/ service), which raises at
import time if no provider keys are configured. Fake keys here, set at conftest.py import
time, ensure that import succeeds regardless of the local environment.
"""
import os

for _key in ("GOOGLE_API_KEY", "MISTRAL_API_KEY", "CEREBRAS_API_KEY", "GROQ_API_KEY", "ANTHROPIC_API_KEY"):
    os.environ.setdefault(_key, f"test-{_key.lower()}")
