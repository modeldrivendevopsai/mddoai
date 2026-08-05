# ai/

The MDDOAI AI layer: a chat frontend and an LLM backend. Each service has its own README for
service-specific setup — this file only covers how they fit together.

## Services

- **`ai-layer/`** — FastAPI backend that routes chat requests to an LLM provider (with automatic fallback across providers), published directly on port 8000. See [ai-layer/README.md](./ai-layer/README.md).
- **`orchestrator/`** — FastAPI backend that walks a platform description through the docs → PIM → PSM → ATL → Acceleo → generation pipeline. Internal-only (no host port); reached only through `chat-ui`'s dev-server proxy. Calls `ai-layer`'s `/chat` for LLM completions, and calls `retrieval`'s `/fetch` for its docs stage's real crawl. See [orchestrator/README.md](./orchestrator/README.md).
- **`retrieval/`** — FastAPI backend that fetches a CI/CD platform's documentation. Internal-only (no host port); reached only by `orchestrator`, never directly by `chat-ui`. See [retrieval/README.md](./retrieval/README.md).
- **`chat-ui/`** — React SPA, run via `docker-compose.yml` as a hot-reloading Vite dev server (not its `Dockerfile`, that one builds static assets for an actual deployment target later), published on port 5173. Its dev-server proxy forwards `/orchestrator-api/*` to `orchestrator` by Docker Compose service name (`chat-ui/vite.config.ts`) — that's the only backend it talks to. See [chat-ui/README.md](./chat-ui/README.md).

There's no reverse proxy in front of these right now, each published service talks directly to the one it needs, each published on its own port. A single-origin gateway (static files + API routes reverse-proxied behind one exposed port) is worth adding once there's an actual deployment target, not for local dev, where each service on its own port is normal.

## Request path

Browser → `chat-ui`'s dev server → proxied to `orchestrator` for `/orchestrator-api/*` (prefix stripped). `orchestrator` calls `ai-layer`'s `/chat` for LLM completions and `retrieval`'s `/fetch`/`/fetch/page` for its docs stage's real crawl, both over the Docker network — it's the only thing that talks to either of them. `ai-layer` never talks to the Java/Eclipse backend at the repo root, and `chat-ui` never talks to `ai-layer`, `retrieval`, or an LLM provider directly, that always routes through `orchestrator` then `ai-layer`.

## Run everything

```bash
cd ai
docker compose up --build
```

Starts every service in `docker-compose.yml`. `ai-layer` at [http://localhost:8000](http://localhost:8000) and `chat-ui` at [http://localhost:5173](http://localhost:5173) are published to the host; any service without a `ports:` entry in `docker-compose.yml` stays internal-only, reachable by other containers on the same Docker network by service name, not from the host. Stop with `docker compose down`.

`ai-layer` needs at least one provider API key configured just to start — with none, its container will fail on startup rather than start and error per-request. See its own README for `.env` setup.

`ai-layer`'s container also gets read-only access to your host's `~/.claude` — see `docker-compose.yml` for why (the Claude Pro/Max subscription auto-detect path needs it; without the mount, the container has no way to see it since containers don't share the host's home directory).
