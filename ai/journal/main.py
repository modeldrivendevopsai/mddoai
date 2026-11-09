"""The journal's REST surface: the stack's histories, run attempts and
published platforms, kept on disk. Reached only by the other services through
clients/journal_client.py. It knows nothing about what a run, a chat or a stage
means: that stays with the service that owns the idea.

Just app assembly: every route lives in routes/."""
from fastapi import FastAPI

from journal.routes import attempts, histories, platforms

app = FastAPI(title="MDDOAI Journal")


@app.get("/health")
def health():
    return {"status": "ok"}


app.include_router(histories.router)
app.include_router(attempts.router)
app.include_router(platforms.router)
