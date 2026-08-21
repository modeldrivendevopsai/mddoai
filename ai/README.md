# ai/

The MDDOAI AI layer: a chat frontend and an LLM backend. Each service has its own README for
service-specific setup — this file only covers how they fit together.

## Services

- **`ai-layer/`** — FastAPI backend that routes chat requests to an LLM provider (with automatic fallback across providers), published directly on port 8000. See [ai-layer/README.md](./ai-layer/README.md).
- **`ui-host/`** — React SPA, the Module Federation host/shell (routing, the persistent layout, `useIntegration.ts`'s state hub, every real backend service call), run via `docker-compose.yml` as a hot-reloading Vite dev server (not its `Dockerfile`, that one builds static assets for an actual deployment target later), published on port 5173. Its dev-server proxy forwards to `orchestrator` by Docker Compose service name (`ui-host/vite.config.ts`) — that's the only backend it talks to directly. It renders the chat column, the stepper, and each pipeline stage panel by loading them at runtime from their own `ui-remote-*` containers below, not by importing their source directly. See [ui-host/README.md](./ui-host/README.md).
- **`ui-remote-*/`** — one Module Federation remote per independently-liftable piece of the integration screen (the chat column, the stepper, and one per real pipeline stage), each its own container, own port, own `npm run build`/`npm run dev`, consumed by `ui-host` at runtime via a federated import. `ui-host/src/features/integration/stages/registry.ts` is the current, exact list of which remote exposes which stage panel; `ui-host/vite.config.ts`'s `federation()` `remotes` map is the current, exact list of every remote and its port. See any `ui-remote-*/README.md` for the shared Module Federation mechanics, and `ui-host/README.md`'s own Docker section for the Docker Compose topology all of them share.
- **`validator_agent/`** — wraps `main/`'s headless `.ecore` validator as an HTTP service, internal-only (no host port). The one deliberate exception to "nothing in `ai/` talks to the Java/Eclipse backend" — see [validator_agent/README.md](./validator_agent/README.md) and `ai/CLAUDE.md`'s folder-boundaries section.
- **`orchestrator/`** — the chat and REST gateway: the one thing `ui-host` talks to, and the only place LLM tool-calling and narration live. Internal-only (no host port). Calls `ai-layer` for chat completions and `integration_runner` for everything about the pipeline itself (running a stage, reviewing it, adding a constraint) — it has no knowledge of the pipeline's own state machine, that all lives on `integration_runner`'s side. See [orchestrator/README.md](./orchestrator/README.md) for the full module layout.
- **`integration_runner/`** — the pipeline state machine: its own separately deployed service, reached only by `orchestrator`, over HTTP, never as a Python import. Calls `ai-layer` for the placeholder stages' LLM completions, `retrieval` for the docs stage's real crawl, and `serialization_agent` for the serialization stage's concept labeling. See [integration_runner/README.md](./integration_runner/README.md).
- **`retrieval/`** — FastAPI backend that fetches a CI/CD platform's documentation. Internal-only (no host port); reached only by `integration_runner`, never directly by `orchestrator` or `ui-host`. See [retrieval/README.md](./retrieval/README.md).
- **`serialization_agent/`** — FastAPI backend that turns the docs stage's raw output into a PIM-concept-labeled markdown artifact, for the pim stage to build on. Internal-only (no host port); reached only by `integration_runner`, calls `ai-layer` for its own extraction step and `pim_agent` for concept grounding. See [serialization_agent/README.md](./serialization_agent/README.md).
- **`pim_agent/`** — FastAPI backend serving a static, hand-curated PIM (Platform-Independent Model) knowledge base. Internal-only (no host port); reached only by `serialization_agent` today. See [pim_agent/README.md](./pim_agent/README.md).
- **`psm_agent/`** — FastAPI backend that compares serialized platform documentation against a real PSM (Platform-Specific Model) metamodel via an LLM call. Internal-only (no host port); not called by anything live yet, built ahead of that wiring. See [psm_agent/README.md](./psm_agent/README.md).

`clients/` and `design-system/` aren't services either, no port, no Dockerfile, nothing in `docker-compose.yml` — the first is a shared package of HTTP wrapper functions the Python services import directly, the second a shared UI-kit package `ui-host` depends on as an ordinary local npm dependency. See `ai/CLAUDE.md`'s folder-boundaries section for why each is structured that way.

There's no reverse proxy in front of these right now, each published service talks directly to the one it needs, each published on its own port. A single-origin gateway (static files + API routes reverse-proxied behind one exposed port) is worth adding once there's an actual deployment target, not for local dev, where each service on its own port is normal.

## Request path

Browser → `ui-host`'s dev server → proxied to `orchestrator` (prefix stripped). `orchestrator` calls `ai-layer` for LLM completions and `integration_runner` for every real pipeline capability, over the Docker network — those are the only two services it talks to. `integration_runner` in turn calls `ai-layer`, `retrieval`, and `serialization_agent`, which itself calls `ai-layer` and `pim_agent`. `psm_agent` and `validator_agent` exist as their own services but aren't called by anything live yet. `ai-layer` never talks to the Java/Eclipse backend at the repo root, and `ui-host` never talks to any backend except `orchestrator` directly.

Loading a `ui-remote-*` piece works differently from that server-side proxy: the **browser itself** fetches a remote's `remoteEntry.js` directly against that remote's own published port, after `ui-host`'s own page has already loaded — not something `ui-host`'s dev server proxies or fetches on the browser's behalf. That's why every `ui-remote-*` service needs a real `ports:` entry in `docker-compose.yml` (browser-reachable), unlike the internal-only backend services above (`expose:`, container-to-container only).

## Run everything

```bash
cd ai
docker compose up --build
```

Starts every service in `docker-compose.yml`. `ai-layer` at [http://localhost:8000](http://localhost:8000), `ui-host` at [http://localhost:5173](http://localhost:5173), and every `ui-remote-*` on its own port (see `ui-host/vite.config.ts`'s `federation()` `remotes` map for the current, exact list) are published to the host; any service without a `ports:` entry in `docker-compose.yml` stays internal-only, reachable by other containers on the same Docker network by service name, not from the host. Stop with `docker compose down`.

`ai-layer` needs at least one provider API key configured just to start — with none, its container will fail on startup rather than start and error per-request. See its own README for `.env` setup.

`ai-layer`'s container also gets read-only access to your host's `~/.claude` — see `docker-compose.yml` for why (the Claude Pro/Max subscription auto-detect path needs it; without the mount, the container has no way to see it since containers don't share the host's home directory).
