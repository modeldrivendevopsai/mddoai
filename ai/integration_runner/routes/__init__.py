"""integration_runner's REST routes, one module per the same axis
stages/ and orchestrator/tools/ already split on: core.py (generic,
stage-agnostic pipeline-lifecycle routes) and docs.py (docs-stage-specific
routes). main.py just assembles these into the real FastAPI app.
"""
