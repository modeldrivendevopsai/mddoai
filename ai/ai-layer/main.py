from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from router.config import AVAILABLE
from router.router import AUTO, chat

app = FastAPI(title="MDDOAI AI Layer")


class ChatRequest(BaseModel):
    messages: list[dict]
    model: str | None = None
    tools: list[dict] | None = None
    tool_choice: str | None = None


class ChatResponse(BaseModel):
    content: str | None
    model: str
    tool_calls: list[dict] | None = None


class Provider(BaseModel):
    name: str
    tier: str


@app.get("/health")
def health():
    return {"status": "ok"}


@app.get("/providers", response_model=list[Provider])
def providers():
    return [{"name": m["name"], "tier": m["tier"]} for m in AVAILABLE]


@app.post("/chat", response_model=ChatResponse)
def chat_endpoint(request: ChatRequest):
    valid_names = {m["name"] for m in AVAILABLE}
    if request.model is not None and request.model != AUTO and request.model not in valid_names:
        raise HTTPException(
            status_code=400,
            detail=f"Unknown model '{request.model}'. Valid options: {sorted(valid_names)} or '{AUTO}'.",
        )
    kwargs = {}
    if request.tools is not None:
        kwargs["tools"] = request.tools
    if request.tool_choice is not None:
        kwargs["tool_choice"] = request.tool_choice
    try:
        response = chat(request.messages, model=request.model, **kwargs)
        message = response.choices[0].message
        tool_calls = None
        if message.tool_calls:
            tool_calls = [
                {"function": {"name": tc.function.name, "arguments": tc.function.arguments}}
                for tc in message.tool_calls
            ]
        return {"content": message.content, "model": response.model, "tool_calls": tool_calls}
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
