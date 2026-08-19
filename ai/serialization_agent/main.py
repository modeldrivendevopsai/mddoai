"""serialization_agent's REST surface: wraps serialization_agent()
(serialization.py's real capability, the pipeline's serialization stage)
as a network-addressable service. Own container, same reasoning as
pim_agent/psm_agent: this makes a real outbound call to pim_agent (see
serialization.py's own docstring), so it needs a lifecycle of its own
rather than running bundled inside whichever process happens to import it.
"""
from fastapi import FastAPI
from pydantic import BaseModel

from serialization import serialization_agent

app = FastAPI(title="MDDOAI Serialization Agent")


class SerializeRequest(BaseModel):
    docs_output: str
    model: str | None = None


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/serialize")
def serialize_endpoint(request: SerializeRequest):
    markdown = serialization_agent({"docs_output": request.docs_output, "model": request.model})
    return {"markdown": markdown}
