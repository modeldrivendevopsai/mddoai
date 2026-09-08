"""orchestrator's own HTTP routes, split by concern the same way
integration_runner/routes/ already is: main.py registers each router,
rather than growing its own flat list of endpoint bodies as this service
gains more real HTTP surface.
"""
