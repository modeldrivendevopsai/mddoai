"""The psm stage's own package: agent.py (a real, thin proxy to psm_agent,
dispatched via stages.stage_agents). No actions.py yet — no extra chat
tools for this stage today, added as a sibling file here when one becomes
real, per stages/__init__.py's own docstring.
"""
from integration_runner.stages.psm import agent
