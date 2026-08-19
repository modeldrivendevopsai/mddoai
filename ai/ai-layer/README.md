# ai-layer

LLM router for MDDOAI. Exposes a FastAPI service that agents (including the standalone `orchestrator` service — see `../orchestrator/README.md`) and the chat-ui call over HTTP. Free providers are tried first in priority order; commercial Claude is the last resort.

## Provider priority

Priority order is exactly the top-to-bottom order of the `MODELS` list in
[`router/config.py`](router/config.py) — that list is the one source of truth, not repeated here
since it changes independently (a provider retiring a model ID, or a reordering like this one,
would otherwise mean remembering to update this file too every time). A provider with no API key
set is skipped silently at startup (see `AVAILABLE` in the same file). Free tiers come first;
the Claude Pro/Max subscription and commercial Anthropic key are the last resorts.

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

**GET /providers** — list every provider this deployment knows about (name + tier + whether it currently has a real key), for a UI to build a selector from. `available: false` entries are listed too (not hidden), so a UI can show the full option set and gray out/disable the ones that aren't usable yet rather than silently omitting them; `POST /chat` still rejects selecting one of those with a 400.

```bash
curl http://localhost:8000/providers
# [{"name": "gemini-flash", "tier": "free", "available": false}, {"name": "claude-subscription", "tier": "subscription", "available": true}, ...]
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
