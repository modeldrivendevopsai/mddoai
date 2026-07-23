# ai-layer

LLM router for MDDOAI. Exposes a FastAPI service that agents (including the standalone `orchestrator` service — see `../orchestrator/README.md`) and the chat-ui call over HTTP. Free providers are tried first in priority order; commercial Claude is the last resort.

## Provider priority

| Priority | Provider | Model |
|---|---|---|
| 1 | Google AI Studio | `gemini/gemini-2.5-flash` |
| 2 | Mistral | `mistral/mistral-small-2506` |
| 3 | Cerebras | `cerebras/gpt-oss-120b` |
| 4 | Groq | `groq/llama-3.3-70b-versatile` |
| 5 (Claude Pro/Max subscription) | Anthropic | `anthropic/claude-haiku-4-5` |
| 6 (commercial fallback) | Anthropic | `anthropic/claude-sonnet-4-6` |

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

**GET /providers** — list the currently configured providers (name + tier), for a UI to build a selector from.

```bash
curl http://localhost:8000/providers
# [{"name": "gemini-flash", "tier": "free"}, ...]
```

**POST /chat** — send a message and get a response. Also supports tool/function calling, so it can serve as the transport for other services (like `orchestrator`) that need an LLM to choose between tools rather than just returning text.

```bash
curl -X POST http://localhost:8000/chat \
  -H "Content-Type: application/json" \
  -d '{"messages": [{"role": "user", "content": "Which CI/CD stages are needed?"}]}'
# {"content": "...", "model": "gemini/gemini-2.5-flash", "tool_calls": null}
```

Request body: `{ "messages": [...], "model": "...", "tools": [...], "tool_choice": "..." }` — `model` is optional; omit it or pass `"auto"` for the default priority order, or name one of the providers from `/providers` to start there instead (still falls back through the rest on failure). `tools`/`tool_choice` are optional OpenAI-style tool schemas/choice strings, forwarded to `router.router.chat()` as-is only when present. See `router/router.py`'s `chat()` for the exact behavior.

Response: `{ "content": "..." | null, "model": "provider/model-name", "tool_calls": [{"function": {"name": "...", "arguments": "..."}}] | null }` — `content` is `null` when the model responded with only tool calls; `tool_calls` is `null` when it didn't call any.

Every successful call also logs a JSON line to stdout:

```json
{"timestamp": "2026-06-19T14:32:01Z", "model": "gemini/gemini-2.5-flash", "tier": "free", "input_tokens": 120, "output_tokens": 38, "total_tokens": 158}
```

## Test

```bash
pytest
```

Run from `ai-layer/` — `pytest.ini` sets `pythonpath = .` so `from router.router import chat` resolves; running `pytest` from a different working directory will fail to import.

No real API calls are made — `litellm.completion` is mocked in tests.

## Structure

FastAPI app and routes are in `main.py`; the provider list, priority order, and LiteLLM router setup are in `router/`. See those files directly for current details.
