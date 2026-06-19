# ai-layer

LLM router for MDDOAI. Exposes a FastAPI service that agents and the chat-ui call. Free providers are tried first in priority order; commercial Claude is the last resort.

## Provider priority

| Priority | Provider | Model |
|---|---|---|
| 1 | Google AI Studio | `gemini/gemini-2.5-flash` |
| 2 | Mistral | `mistral/mistral-small-2506` |
| 3 | Cerebras | `cerebras/gpt-oss-120b` |
| 4 | Groq | `groq/llama-3.3-70b-versatile` |
| 5 (commercial fallback) | Anthropic | `anthropic/claude-sonnet-4-6` |

Providers with no API key set are skipped silently at startup.

## Setup

```bash
pip install -r requirements.txt
cp .env.example .env
# Fill in at minimum ANTHROPIC_API_KEY, plus any free-tier keys you have
```

## Run

```bash
uvicorn main:app --reload
```

The service starts at [http://localhost:8000](http://localhost:8000).

## API

**GET /health** — liveness check.

```bash
curl http://localhost:8000/health
# {"status":"ok"}
```

**POST /chat** — send a message and get a response.

```bash
curl -X POST http://localhost:8000/chat \
  -H "Content-Type: application/json" \
  -d '{"messages": [{"role": "user", "content": "Which CI/CD stages are needed?"}]}'
# {"content": "...", "model": "gemini/gemini-2.5-flash"}
```

Request body: `{ "messages": [{"role": "user"|"assistant"|"system", "content": "..."}] }`

Response: `{ "content": "...", "model": "provider/model-name" }`

Every successful call also logs a JSON line to stdout:

```json
{"timestamp": "2026-06-19T14:32:01Z", "model": "gemini/gemini-2.5-flash", "tier": "free", "input_tokens": 120, "output_tokens": 38, "total_tokens": 158}
```

## Calling the router directly (Python)

Agents can import `chat()` without going through HTTP:

```python
from router.router import chat

response = chat(messages=[{"role": "user", "content": "Describe the pipeline stages."}])
print(response.choices[0].message.content)
```

## Test

```bash
pytest
```

No real API calls are made — `litellm.completion` is mocked in tests.

## Structure

```
ai-layer/
  main.py             FastAPI app — /health and /chat endpoints
  router/
    config.py         provider list and priority order
    logger.py         JSON stdout logging per call
    router.py         chat() — builds the LiteLLM router, exposes one function
  tests/
    test_router.py    fallback behaviour tests
  requirements.txt
  .env.example
```
