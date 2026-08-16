"""The serialization stage's own package: agent.py (a real stage agent,
dispatched via stages.stage_agents, calling out to the real, separate
serialization_agent service). No actions.py yet — no extra chat tools for
this stage today, added as a sibling file here when one becomes real, per
stages/__init__.py's own docstring.
"""
from integration_runner.stages.serialization import agent
