# ai/

The MDDOAI AI layer: a chat frontend and an LLM backend, run together behind a shared gateway. Each service has its own README for service-specific setup — this file only covers how they fit together.

## Services

- **`ai-layer/`** — FastAPI backend that routes chat requests to an LLM provider (with automatic fallback across providers). See [ai-layer/README.md](./ai-layer/README.md).
- **`chat-ui/`** — React SPA. Its Dockerfile only builds static assets; it doesn't serve them itself. See [chat-ui/README.md](./chat-ui/README.md).
- **`caddy`** (via `docker-compose.yml`, config in [Caddyfile](./Caddyfile)) — the gateway. Serves chat-ui's built static files and reverse-proxies `/api/*` to `ai-layer`. Not its own folder — it's a standalone service in `docker-compose.yml` using the stock `caddy` image, decoupled from chat-ui's build so routing changes don't require rebuilding the frontend.

## Request path

Browser → `caddy` → static files for `/`, or reverse-proxied to `ai-layer` for `/api/*` (prefix stripped). `ai-layer` never talks to the Java/Eclipse backend at the repo root, and `chat-ui` never talks to an LLM provider directly — everything routes through `ai-layer`.

## Run everything

```bash
cd ai
docker compose up --build
```

Served at [http://localhost:8080](http://localhost:8080). Stop with `docker compose down`.

`chat-ui`'s build output reaches `caddy` through a shared Docker volume — the `chat-ui-build` container builds once, copies its output into the volume, and exits; `caddy` waits for that to finish before starting.

`ai-layer` needs at least one provider API key configured just to start — with none, its container will fail on startup rather than start and error per-request. See its own README for `.env` setup.

`ai-layer`'s container also gets read-only access to your host's `~/.claude` — see `docker-compose.yml` for why (the Claude Pro/Max subscription auto-detect path needs it; without the mount, the container has no way to see it since containers don't share the host's home directory).
