"""The journal's REST surface: the stack's histories, run attempts and
published platforms, kept on disk. Reached only by the other services through
clients/journal_client.py. It knows nothing about what a run, a chat or a stage
means: that stays with the service that owns the idea.

Just app assembly: every route lives in routes/."""
from fastapi import FastAPI, Request
from fastapi.exceptions import RequestValidationError
from fastapi.responses import JSONResponse

from journal.routes import attempts, histories, platforms

app = FastAPI(title="MDDOAI Journal")


@app.exception_handler(RequestValidationError)
def readable_validation_error(_request: Request, exc: RequestValidationError) -> JSONResponse:
    """FastAPI's own 422 body is a list of internal records; every other refusal
    here carries a plain-text `detail`, so this one does too."""
    problems = "; ".join(
        ".".join(str(part) for part in error["loc"][1:]) + ": " + error["msg"] for error in exc.errors()
    )
    return JSONResponse(status_code=422, content={"detail": problems})


@app.get("/health")
def health():
    return {"status": "ok"}


app.include_router(histories.router)
app.include_router(attempts.router)
app.include_router(platforms.router)
