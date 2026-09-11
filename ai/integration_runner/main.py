"""integration_runner's REST surface: the pipeline state machine and run
history exposed over HTTP, reached only by orchestrator (via
clients/integration_runner_client.py), never by ui-host directly. This
service has no knowledge of chat, narration, or LLM tool-calling.

Just app assembly — every real route lives in routes/ (core.py, the
generic pipeline-lifecycle endpoints; docs.py/psm.py/atl.py/acceleo.py,
each stage's own specific ones; attempts.py, generic run/attempt
introspection), so this file doesn't grow as new stage-specific routes are
added.
"""
from fastapi import FastAPI

from integration_runner.routes import acceleo, atl, attempts, core, docs, psm

app = FastAPI(title="MDDOAI Integration Runner")
app.include_router(core.router)
app.include_router(docs.router)
app.include_router(psm.router)
app.include_router(atl.router)
app.include_router(acceleo.router)
app.include_router(attempts.router)
