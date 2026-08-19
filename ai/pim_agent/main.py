"""pim_agent's REST surface: mirrors its real Python public API one-for-one
(ground(), PIM_CONCEPTS) so remote callers (orchestrator's
clients/pim_agent_client.py, currently serialization_agent) get the exact
same contract a local import would have given them before this became a
service.
"""
from dataclasses import asdict

from fastapi import FastAPI
from pydantic import BaseModel

from reference_knowledge import ground, PIM_CONCEPTS

app = FastAPI(title="MDDOAI PIM Agent")


class GroundRequest(BaseModel):
    query: str
    top_k: int = 5


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/ground")
def ground_endpoint(request: GroundRequest):
    return [asdict(example) for example in ground(request.query, request.top_k)]


@app.get("/concepts")
def concepts_endpoint():
    return {concept: list(titles) for concept, titles in PIM_CONCEPTS.items()}
