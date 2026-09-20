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
from fastapi import FastAPI, Request
from fastapi.responses import JSONResponse

from clients.agent_service_errors import AgentServiceError
from integration_runner.routes import acceleo, atl, attempts, core, docs, psm

app = FastAPI(title="MDDOAI Integration Runner")


@app.exception_handler(AgentServiceError)
def agent_service_error_handler(request: Request, exc: AgentServiceError):
    """psm_agent/atl_agent/acceleo_agent's own business errors (a bad
    prompt-config edit, an unknown saved version), caught once here instead
    of a try/except at every pass-through route in routes/{psm,atl,acceleo}.py:
    this is what lets every one of those stay a plain, thin forwarding call.
    Reconstructs the real status code and message the agent service itself
    reported, rather than degrading to a generic 500 a plain, uncaught
    httpx.HTTPStatusError would otherwise turn into."""
    return JSONResponse(status_code=exc.status_code, content={"detail": exc.detail})


app.include_router(core.router)
app.include_router(docs.router)
app.include_router(psm.router)
app.include_router(atl.router)
app.include_router(acceleo.router)
app.include_router(attempts.router)
