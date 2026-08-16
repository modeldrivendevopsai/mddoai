"""The docs stage's own package: agent.py (the real stage agent, dispatched
via stages.stage_agents) and actions.py (extend_with_page, an extra
run-aware chat tool). See stages/__init__.py's own docstring for why every
stage gets this shape.

Imports the agent/actions MODULES below, not a same-named function from
within them: stages/__init__.py's own docstring explains the real Python
footgun (submodule-attribute shadowing) this avoids.
"""
from integration_runner.stages.docs import actions, agent
