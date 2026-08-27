# ai/

The MDDOAI AI layer: a chat frontend and an LLM backend. Each service has its own README for
service-specific setup — this file only covers how they fit together.

## Services

- **`ai-layer/`** — FastAPI backend that routes chat requests to an LLM provider (with automatic fallback across providers), published directly on port 8000. See [ai-layer/README.md](./ai-layer/README.md).
- **`chat-ui/`** — React SPA, run via `docker-compose.yml` as a hot-reloading Vite dev server (not its `Dockerfile`, that one builds static assets for an actual deployment target later), published on port 5173. Its dev-server proxy forwards to `orchestrator` by Docker Compose service name (`chat-ui/vite.config.ts`) — that's the only backend it talks to directly. See [chat-ui/README.md](./chat-ui/README.md).
- **`validator_agent/`** — wraps `main/`'s headless `.ecore`/`.atl`/`.mtl` validators as an HTTP service, internal-only (no host port). The one deliberate exception to "nothing in `ai/` talks to the Java/Eclipse backend" — see [validator_agent/README.md](./validator_agent/README.md) and `ai/CLAUDE.md`'s folder-boundaries section.
- **`orchestrator/`** — the chat and REST gateway: the one thing `chat-ui` talks to, and the only place LLM tool-calling and narration live. Internal-only (no host port). Calls `ai-layer` for chat completions and `integration_runner` for everything about the pipeline itself (running a stage, reviewing it, adding a constraint) — it has no knowledge of the pipeline's own state machine, that all lives on `integration_runner`'s side. See [orchestrator/README.md](./orchestrator/README.md) for the full module layout.
- **`integration_runner/`** — the pipeline state machine: its own separately deployed service, reached only by `orchestrator`, over HTTP, never as a Python import. Calls `ai-layer` for the `generation` stage's LLM completion (the one remaining LLM-prompt placeholder), `retrieval` for the docs stage's real crawl, `serialization_agent` for the serialization stage's concept labeling, and `validator_agent` for the `pim`/`psm`/`atl`/`acceleo` stages' own real validation of their (currently mock) DSL output. See [integration_runner/README.md](./integration_runner/README.md).
- **`retrieval/`** — FastAPI backend that fetches a CI/CD platform's documentation. Internal-only (no host port); reached only by `integration_runner`, never directly by `orchestrator` or `chat-ui`. See [retrieval/README.md](./retrieval/README.md).
- **`serialization_agent/`** — FastAPI backend that turns the docs stage's raw output into a PIM-concept-labeled markdown artifact, for the pim stage to build on. Internal-only (no host port); reached only by `integration_runner`, calls `ai-layer` for its own extraction step and `pim_agent` for concept grounding. See [serialization_agent/README.md](./serialization_agent/README.md).
- **`pim_agent/`** — FastAPI backend serving a static, hand-curated PIM (Platform-Independent Model) knowledge base. Internal-only (no host port); reached only by `serialization_agent` today. See [pim_agent/README.md](./pim_agent/README.md).
- **`psm_agent/`** — FastAPI backend that compares serialized platform documentation against a real PSM (Platform-Specific Model) metamodel via an LLM call. Internal-only (no host port); not called by anything live yet, built ahead of that wiring. See [psm_agent/README.md](./psm_agent/README.md).

There's no reverse proxy in front of these right now, each published service talks directly to the one it needs, each published on its own port. A single-origin gateway (static files + API routes reverse-proxied behind one exposed port) is worth adding once there's an actual deployment target, not for local dev, where each service on its own port is normal.

## Request path

Browser → `chat-ui`'s dev server → proxied to `orchestrator` (prefix stripped). `orchestrator` calls `ai-layer` for LLM completions and `integration_runner` for every real pipeline capability, over the Docker network — those are the only two services it talks to. `integration_runner` in turn calls `ai-layer`, `retrieval`, `serialization_agent`, and `validator_agent` (the `pim`/`psm`/`atl`/`acceleo` stages' own real validation call), and `serialization_agent` itself calls `ai-layer` and `pim_agent`. `psm_agent` exists as its own service but isn't called by anything live yet. `ai-layer` never talks to the Java/Eclipse backend at the repo root, and `chat-ui` never talks to any backend except `orchestrator` directly.

## Run everything

```bash
cd ai
docker compose up --build
```

Starts every service in `docker-compose.yml`. `ai-layer` at [http://localhost:8000](http://localhost:8000) and `chat-ui` at [http://localhost:5173](http://localhost:5173) are published to the host; any service without a `ports:` entry in `docker-compose.yml` stays internal-only, reachable by other containers on the same Docker network by service name, not from the host. Stop with `docker compose down`.

`ai-layer` needs at least one provider API key configured just to start — with none, its container will fail on startup rather than start and error per-request. See its own README for `.env` setup.

`ai-layer`'s container also gets read-only access to your host's `~/.claude` — see `docker-compose.yml` for why (the Claude Pro/Max subscription auto-detect path needs it; without the mount, the container has no way to see it since containers don't share the host's home directory).
