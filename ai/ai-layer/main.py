from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from router.router import chat

app = FastAPI(title="MDDOAI AI Layer")


class ChatRequest(BaseModel):
    messages: list[dict]


class ChatResponse(BaseModel):
    content: str
    model: str


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/chat", response_model=ChatResponse)
def chat_endpoint(request: ChatRequest):
    try:
        response = chat(request.messages)
        return {"content": response.choices[0].message.content, "model": response.model}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
