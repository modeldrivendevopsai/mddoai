# ai/

The MDDOAI AI layer: a chat frontend and an LLM backend. Each service has its own README for
service-specific setup — this file only covers how they fit together.

## Services

- **`ai-layer/`** — FastAPI backend that routes chat requests to an LLM provider (with automatic fallback across providers), published directly on port 8000. See [ai-layer/README.md](./ai-layer/README.md).
- **`chat-ui/`** — React SPA, run via `docker-compose.yml` as a hot-reloading Vite dev server (not its `Dockerfile`, that one builds static assets for an actual deployment target later), published on port 5173. Its dev-server proxy forwards `/api/*` to `ai-layer` by Docker Compose service name (`chat-ui/vite.config.ts`). See [chat-ui/README.md](./chat-ui/README.md).

There's no reverse proxy in front of these right now, `chat-ui` and `ai-layer` talk directly, each published on its own port. A single-origin gateway (static files + `/api/*` reverse-proxied behind one exposed port) is worth adding once there's an actual deployment target, not for local dev, where each service on its own port is normal.

## Request path

Browser → `chat-ui`'s dev server → proxied to `ai-layer` for `/api/*` (prefix stripped). `ai-layer` never talks to the Java/Eclipse backend at the repo root, and `chat-ui` never talks to an LLM provider directly, everything routes through `ai-layer`.

## Run everything

```bash
cd ai
docker compose up --build
```

Starts every service in `docker-compose.yml`. `ai-layer` at [http://localhost:8000](http://localhost:8000) and `chat-ui` at [http://localhost:5173](http://localhost:5173) are published to the host; any service without a `ports:` entry in `docker-compose.yml` stays internal-only, reachable by other containers on the same Docker network by service name, not from the host. Stop with `docker compose down`.

`ai-layer` needs at least one provider API key configured just to start — with none, its container will fail on startup rather than start and error per-request. See its own README for `.env` setup.

`ai-layer`'s container also gets read-only access to your host's `~/.claude` — see `docker-compose.yml` for why (the Claude Pro/Max subscription auto-detect path needs it; without the mount, the container has no way to see it since containers don't share the host's home directory).
