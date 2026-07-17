from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field, HttpUrl

from retrieval import fetch_documentation

app = FastAPI(title="MDDOAI Retrieval")


class FetchRequest(BaseModel):
    url: HttpUrl
    # Each additional page is a full sequential browser fetch plus, once every
    # max_pages-1 links, another LLM call, an unbounded value lets one request
    # hold a connection open indefinitely and spin up an unbounded number of
    # page loads. 20 is a generous ceiling for "a few extra reference pages".
    max_pages: int = Field(default=5, ge=1, le=20)


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/fetch")
async def fetch_endpoint(request: FetchRequest):
    try:
        return await fetch_documentation(str(request.url), max_pages=request.max_pages)
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
