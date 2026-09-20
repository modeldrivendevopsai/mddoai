"""psm_agent's own HTTP routes, split by concern the same way
integration_runner/routes/ already is: main.py registers each router,
never grows its own endpoint bodies as this service gains more real HTTP
surface.
"""
