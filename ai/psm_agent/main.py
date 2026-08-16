"""psm_agent's REST surface: POST /compare wraps comparison.compare(), the
one real capability this service has today. Not called by anything live
yet (the pipeline's own psm stage is still a placeholder LLM prompt) —
this endpoint exists so the real capability is callable and testable now,
ahead of that wiring.
"""
from dataclasses import asdict

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel

from comparison import compare

app = FastAPI(title="MDDOAI PSM Agent")


class CompareRequest(BaseModel):
    serialized_docs: str
    psm_metamodel_path: str | None = None


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/compare")
def compare_endpoint(request: CompareRequest):
    try:
        suggestions = compare(request.serialized_docs, request.psm_metamodel_path)
    except FileNotFoundError as e:
        raise HTTPException(status_code=400, detail=f"PSM metamodel not found: {e}")
    return {"suggestions": [asdict(s) for s in suggestions]}
