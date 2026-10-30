# ai/

The MDDOAI AI layer: a chat frontend and an LLM backend. Each service has its own README for
service-specific setup — this file only covers how they fit together.

## Services

- **`ai-layer/`** — FastAPI backend that routes chat requests to an LLM provider (with automatic fallback across providers), published directly on port 8000. See [ai-layer/README.md](./ai-layer/README.md).
- **`ui-host/`** — React SPA, the Module Federation host/shell (routing, the persistent layout, `useIntegration.ts`'s state hub, every real backend service call), built into static files and served by nginx, published on port 5173: the one port the browser uses. Its proxy (`ui-host/nginx/default.conf.template`) serves the app, forwards `/remotes/<name>/` to each `ui-remote-*` container, and forwards to `orchestrator` by its Docker Compose service name. It renders the chat column, the stepper, and each pipeline stage panel by loading them at runtime from their own `ui-remote-*` containers below, not by importing their source directly. See [ui-host/README.md](./ui-host/README.md).
- **`ui-remote-*/`** — one Module Federation remote per independently-liftable piece of the integration screen (the chat column, the stepper, and one per real pipeline stage), each its own image and container, built into static files served by nginx and reached through `ui-host`'s proxy under `/remotes/<name>/`, consumed by `ui-host` at runtime via a federated import. Each can be rebuilt and restarted on its own; the page picks it up on its next load. `ui-host/src/features/integration/stages/registry.ts` is the current, exact list of which remote exposes which stage panel; `ui-host/vite.config.ts`'s `federation()` `remotes` map is the current, exact list of every remote, and `docker-compose.yml` sets the path each is served under. See any `ui-remote-*/README.md` for the shared Module Federation mechanics, and `ui-host/README.md`'s own Docker section for the Docker Compose topology all of them share.
- **`validator_agent/`** — wraps `main/`'s headless `.ecore`/`.atl`/`.mtl` validators as an HTTP service, internal-only (no host port). The one deliberate exception to "nothing in `ai/` talks to the Java/Eclipse backend" — see [validator_agent/README.md](./validator_agent/README.md) and `ai/CLAUDE.md`'s folder-boundaries section.
- **`orchestrator/`** — the chat and REST gateway: the one thing `ui-host` talks to, and the only place LLM tool-calling and narration live. Internal-only (no host port). Calls `ai-layer` for chat completions and `integration_runner` for everything about the pipeline itself (running a stage, reviewing it, adding a constraint) — it has no knowledge of the pipeline's own state machine, that all lives on `integration_runner`'s side. See [orchestrator/README.md](./orchestrator/README.md) for the full module layout.
- **`integration_runner/`** — the pipeline state machine: its own separately deployed service, reached only by `orchestrator`, over HTTP, never as a Python import. Calls `ai-layer` for the `generation` stage's LLM completion (the one remaining LLM-prompt placeholder), `retrieval` for the docs stage's real crawl, `serialization_agent` for the serialization stage's concept labeling, `psm_agent` for the `psm` stage's own real generation-or-drift-check, and `validator_agent` for the `pim`/`atl`/`acceleo` stages' own real validation of their (currently mock) DSL output. See [integration_runner/README.md](./integration_runner/README.md).
- **`retrieval/`** — FastAPI backend that fetches a CI/CD platform's documentation. Internal-only (no host port); reached only by `integration_runner`, never directly by `orchestrator` or `ui-host`. See [retrieval/README.md](./retrieval/README.md).
- **`serialization_agent/`** — FastAPI backend that turns the docs stage's raw output into a PIM-concept-labeled markdown artifact, for the pim stage to build on. Internal-only (no host port); reached only by `integration_runner`, calls `ai-layer` for its own extraction step and `pim_agent` for concept grounding. See [serialization_agent/README.md](./serialization_agent/README.md).
- **`pim_agent/`** — FastAPI backend serving a static, hand-curated PIM (Platform-Independent Model) knowledge base. Internal-only (no host port); reached by `serialization_agent` and `psm_agent`, both for concept grounding. See [pim_agent/README.md](./pim_agent/README.md).
- **`psm_agent/`** — FastAPI backend serving the real `psm` pipeline stage's whole capability: routes between generating a real, new PSM (Platform-Specific Model) metamodel for a platform that doesn't have one yet, and comparing platform documentation against an existing metamodel for drift. Internal-only (no host port); reached only by `integration_runner`, calls `ai-layer` for its own LLM generation/comparison, `pim_agent` for concept grounding, and `validator_agent` for real Ecore validation on the generation path. See [psm_agent/README.md](./psm_agent/README.md).

`clients/`, `design-system/`, and `orchestrator-types/` aren't services either, no port, no Dockerfile, nothing in `docker-compose.yml` — the first is a shared package of HTTP wrapper functions the Python services import directly, the other two are shared frontend packages (`design-system` for UI components/tokens, `orchestrator-types` for the shared REST/event type contract) that `ui-host` and every `ui-remote-*` package depend on as an ordinary local npm dependency. See `ai/CLAUDE.md`'s folder-boundaries section for why each is structured that way.

The browser reaches everything through one origin, `ui-host` on port 5173: the app's files, every remote's files and the orchestrator's API. One port is simpler to forward over SSH, and the page needs no cross-origin requests. The UI is built, not run as dev servers, so a change to UI code takes `docker compose up -d --build <service>` for the package it is in; there is no hot reloading in Docker. `npm run dev` in a UI package still works outside Docker (see that package's README).

## Request path

Browser → `ui-host`'s proxy → `orchestrator` (prefix stripped). `orchestrator` calls `ai-layer` for LLM completions and `integration_runner` for every real pipeline capability, over the Docker network — those are the only two services it talks to. `integration_runner` in turn calls `ai-layer`, `retrieval`, `serialization_agent`, `psm_agent` (the `psm` stage's own real generation-or-drift-check), and `validator_agent` (the `pim`/`atl`/`acceleo` stages' own real validation call), and `serialization_agent` itself calls `ai-layer` and `pim_agent`. `psm_agent` in turn calls `ai-layer`, `pim_agent` (concept grounding), and `validator_agent` (real Ecore validation, generation path only). `ai-layer` never talks to the Java/Eclipse backend at the repo root, and `ui-host` never talks to any backend except `orchestrator` directly.

Loading a `ui-remote-*` piece: after the page has loaded, the **browser itself** fetches the remote's `remoteEntry.js` at runtime, from `/remotes/<name>/` on the same origin, and `ui-host`'s proxy forwards that to the remote's container. Each remote is served only through that proxy, so no `ui-remote-*` service publishes a port.

## Run everything

```bash
cd ai
docker compose up --build
```

Starts every service in `docker-compose.yml`. `ai-layer` at [http://localhost:8000](http://localhost:8000), and `ui-host` at [http://localhost:5173](http://localhost:5173) (the whole UI, every remote included) are published to the host; any service without a `ports:` entry in `docker-compose.yml` stays internal-only, reachable by other containers on the same Docker network by service name, not from the host. Stop with `docker compose down`.

These settings change how the stack is reached. Set them in the shell or in `ai/.env`:

- `UI_PORT` is the port `ui-host` is published on, `5173` by default. It always listens on this machine only.
- `AI_LAYER_PORT` is the port `ai-layer` is published on, `8000` by default, on every network address of the machine. Put an address in front to listen on that address only, for example `127.0.0.1:8100`.
- `UI_ALLOWED_HOSTS` lists the host names the UI may be opened under, separated by spaces, `localhost 127.0.0.1` by default. `ui-host` refuses a request under any other name. Add a name when the app is reached through one, for example through a tunnel.
- `UI_PROXY_READ_TIMEOUT` is how long `ui-host` waits for a service's answer, `120s` by default. Slow work runs in the background, so this only covers quick calls. Keep it under the limit of any proxy in front of the app, such as Cloudflare's 125 seconds.

The app has no login of its own. Before opening it under a public name, for example through a tunnel, put a proxy that logs people in in front of it, such as `oauth2-proxy`. Without one, anyone who finds the name can use the app and spend its LLM credentials. Such a proxy does not cover `ai-layer`, which holds the LLM credentials and listens on every network address by default: set `AI_LAYER_PORT=127.0.0.1:8000` unless another machine needs it.

Change the two ports to run a second copy of the stack on the same machine. Give that copy its own Compose project name too (`COMPOSE_PROJECT_NAME` in its `ai/.env`, or `docker compose -p <name>`), so its containers, networks and volumes are separate from the first copy's. Both copies still read the same `~/.claude` folder (see `ai-layer` below).

`ai-layer` needs at least one provider API key configured just to start — with none, its container will fail on startup rather than start and error per-request. See its own README for `.env` setup.

`ai-layer`'s container also gets read-only access to your host's `~/.claude` — see `docker-compose.yml` for why (the Claude Pro/Max subscription auto-detect path needs it; without the mount, the container has no way to see it since containers don't share the host's home directory).
