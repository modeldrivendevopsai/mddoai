# ai/

The MDDOAI AI layer: a chat frontend and an LLM backend. Each service has its own README for
service-specific setup — this file only covers how they fit together.

## Services

- **`ai-layer/`** — FastAPI backend that routes chat requests to an LLM provider (with automatic fallback across providers), published directly on `AI_LAYER_PORT`. See [ai-layer/README.md](./ai-layer/README.md).
- **`ui-host/`** — React SPA, the Module Federation host/shell (routing, the persistent layout, `useIntegration.ts`'s state hub, every real backend service call), built into static files and served by nginx, published on `UI_PORT`: the one port the browser uses. Its proxy (`ui-host/nginx/default.conf.template`) serves the app, forwards `/remotes/<name>/` to each `ui-remote-*` container, and forwards to `orchestrator` (the platform-integration mode) and to `pipeline_generator` (the "Generate a CI/CD pipeline" mode) by their Docker Compose service names, one proxy path per backend. It renders the chat column, the stepper, and each pipeline stage panel by loading them at runtime from their own `ui-remote-*` containers below, not by importing their source directly. See [ui-host/README.md](./ui-host/README.md).
- **`ui-remote-*/`** — one Module Federation remote per independently-liftable piece of the integration screen (the chat column, the stepper, and one per real pipeline stage), each its own image and container, built into static files served by nginx and reached through `ui-host`'s proxy under `/remotes/<name>/`, consumed by `ui-host` at runtime via a federated import. Each can be rebuilt and restarted on its own; the page picks it up on its next load. `ui-host/src/features/integration/stages/registry.ts` is the current, exact list of which remote exposes which stage panel; `ui-host/vite.config.ts`'s `federation()` `remotes` map is the current, exact list of every remote, and `docker-compose.yml` sets the path each is served under. See any `ui-remote-*/README.md` for the shared Module Federation mechanics, and `ui-host/README.md`'s own Docker section for the Docker Compose topology all of them share.
- **`validator_agent/`**: wraps `main/`'s headless `.ecore`/`.atl`/`.mtl`/`.swarch` validators as an HTTP service, and also checks generated pipeline files in plain Python (`/validate/pipeline`), internal-only (no host port). See [validator_agent/README.md](./validator_agent/README.md) and `ai/CLAUDE.md`'s folder-boundaries section for how it reaches `main/`.
- **`pipeline_generator/`**: the backend of the "Generate a CI/CD pipeline" mode: checks a Software Architecture model (uploaded, or picked from a mounted workspace folder), runs each chosen platform's own files on it (through `execution_agent`), remembers what was generated so it can be reopened, and checks the generated pipeline. Internal-only (no host port); reached only through `ui-host`'s proxy, calls `validator_agent` for the real model check and the pipeline checks, and `execution_agent` for the generation. See [pipeline_generator/README.md](./pipeline_generator/README.md).
- **`orchestrator/`** — the chat and REST gateway: the backend `ui-host` calls for the platform-integration mode, and the only place LLM tool-calling and narration live. Internal-only (no host port). Calls `ai-layer` for chat completions and `integration_runner` for everything about the pipeline itself (running a stage, reviewing it, adding a constraint) — it has no knowledge of the pipeline's own state machine, that all lives on `integration_runner`'s side. See [orchestrator/README.md](./orchestrator/README.md) for the full module layout.
- **`integration_runner/`** — the pipeline state machine: its own separately deployed service, reached only by `orchestrator`, over HTTP, never as a Python import. Calls `ai-layer` for the `generation` stage's LLM completion (the one remaining LLM-prompt placeholder), `retrieval` for the docs stage's real crawl, `serialization_agent` for the serialization stage's concept labeling, `psm_agent` for the `psm` stage's own real generation-or-drift-check, and `validator_agent` for the `pim`/`atl`/`acceleo` stages' own real validation of their (currently mock) DSL output. See [integration_runner/README.md](./integration_runner/README.md).
- **`retrieval/`** — FastAPI backend that fetches a CI/CD platform's documentation. Internal-only (no host port); reached only by `integration_runner`, never directly by `orchestrator` or `ui-host`. See [retrieval/README.md](./retrieval/README.md).
- **`serialization_agent/`** — FastAPI backend that turns the docs stage's raw output into a PIM-concept-labeled markdown artifact, for the pim stage to build on. Internal-only (no host port); reached only by `integration_runner`, calls `ai-layer` for its own extraction step and `pim_agent` for concept grounding. See [serialization_agent/README.md](./serialization_agent/README.md).
- **`pim_agent/`** — FastAPI backend serving a static, hand-curated PIM (Platform-Independent Model) knowledge base. Internal-only (no host port); reached by `serialization_agent` and `psm_agent`, both for concept grounding. See [pim_agent/README.md](./pim_agent/README.md).
- **`psm_agent/`** — FastAPI backend serving the real `psm` pipeline stage's whole capability: routes between generating a real, new PSM (Platform-Specific Model) metamodel for a platform that doesn't have one yet, and comparing platform documentation against an existing metamodel for drift. Internal-only (no host port); reached only by `integration_runner`, calls `ai-layer` for its own LLM generation/comparison, `pim_agent` for concept grounding, and `validator_agent` for real Ecore validation on the generation path. See [psm_agent/README.md](./psm_agent/README.md).

The folders that are not services are described in `ai/CLAUDE.md`'s folder-boundaries section.

The browser reaches everything through one origin, `ui-host` on `UI_PORT`: the app's files, every remote's files and the orchestrator's and generator's APIs. One port is simpler to forward over SSH, and the page needs no cross-origin requests. The UI is built, not run as dev servers, so a change to UI code takes `docker compose up -d --build <service>` for the package it is in; there is no hot reloading in Docker. `npm run dev` in a UI package still works outside Docker (see that package's README).

## Request path

Browser → `ui-host`'s proxy → `orchestrator` (prefix stripped). `orchestrator` calls `ai-layer` for LLM completions and `integration_runner` for every real pipeline capability, over the Docker network. Those are the only two services it talks to. `integration_runner` in turn calls `ai-layer`, `retrieval`, `serialization_agent`, `psm_agent` (the `psm` stage's own real generation-or-drift-check), and `validator_agent` (the `pim`/`atl`/`acceleo` stages' own real validation call), and `serialization_agent` itself calls `ai-layer` and `pim_agent`. `psm_agent` in turn calls `ai-layer`, `pim_agent` (concept grounding), and `validator_agent` (real Ecore validation, generation path only). `ai-layer` never talks to the Java/Eclipse backend at the repo root, and `ui-host` never talks to `ai-layer`, `retrieval` or the Java backend directly. It calls `orchestrator` and `pipeline_generator`.

The "Generate a CI/CD pipeline" mode has its own, shorter path: browser → `ui-host`'s proxy → `pipeline_generator` (prefix stripped). `pipeline_generator` calls `validator_agent` for the real model check, then `execution_agent` for the generation itself, then `validator_agent` again for the pipeline checks. It involves no LLM, no `orchestrator` and no `integration_runner`.

Loading a `ui-remote-*` piece: after the page has loaded, the **browser itself** fetches the remote's `remoteEntry.js` at runtime, from `/remotes/<name>/` on the same origin, and `ui-host`'s proxy forwards that to the remote's container. Each remote is served only through that proxy, so no `ui-remote-*` service publishes a port.

## Run everything

Every copy of the stack is an environment under `infra/environments/`, which sets its ports and host names (see [infra/README.md](../infra/README.md#environments)). To run your own:

```bash
cd infra/environments/dev
cp ../templates/dev/config.env config.env    # once, then fill it in
docker compose up --build
```

Fill in every line of `config.env` first (see [Where files live](#where-files-live)). Compose names any setting left empty. `cd ai/tests && pip install -r requirements.txt && pytest` checks the compose file and the templates.

Starts every service in `ai/docker-compose.yml`. `ai-layer` is on the environment's `AI_LAYER_PORT` and `ui-host` on `localhost` at its `UI_PORT` (the whole UI, every remote included), both on this machine only; any service without a `ports:` entry in `docker-compose.yml` stays internal-only, reachable by other containers on the same Docker network by service name, not from the host. Stop with `docker compose down`. Run every `docker compose` command in the environment's folder.

The app has no login of its own. Before opening it under a public name, for example through a tunnel, put a proxy that logs people in in front of it, such as `oauth2-proxy`. Without one, anyone who finds the name can use the app and spend its LLM credentials. Such a proxy does not cover `ai-layer`, which holds the LLM credentials: keep the address in `AI_LAYER_PORT` unless another machine needs to reach it.

Every environment on the same machine reads the same `~/.claude` folder (see `ai-layer` below).

## Where files live

Each folder a service keeps files in, and each folder of content people maintain, is its own required setting in the environment's `config.env`. A service stops at start if one of its settings is missing or empty.

- **You fill in:** the models, the platforms and each prompt agent's prompt configs. These folders must already exist, and the platforms and prompt configs must already hold their content.
- **Docker creates:** the uploads, the integration runs, the validators' compiled output and the pipelines.
- **The LLM keys file** is the setting `MDDOAI_LLM_KEYS_FILE`. It must exist. Keep it out of git and readable by you only.

`ai/data` ships the starting platforms and prompt configs and a layout to follow (see [data/README.md](./data/README.md)).

Each folder has one writer. Other services reach what a service generates through its API, never through its folder.

Folders are bind-mounted so you can open them from the host ([Docker's guidance](https://docs.docker.com/engine/storage/bind-mounts/)). Build caches and the built `main/` distribution are Docker volumes, and scratch files are temporary.

Things to know:

- Docker creates a missing folder itself, usually owned by root, so create the ones you fill in first.
- On Linux, files a service writes are owned by root. Use `sudo` to change them.
- `git clean -x` deletes the git-ignored folders under `ai/data`. Keep yours outside the repository if that matters.
- Anyone who can reach the UI can list the workspace and read its `.swarch` files.

`ai-layer` needs at least one provider API key configured just to start — with none, its container will fail on startup rather than start and error per-request. See its own README for `.env` setup.

`ai-layer`'s container also gets read-only access to your host's `~/.claude` — see `docker-compose.yml` for why (the Claude Pro/Max subscription auto-detect path needs it; without the mount, the container has no way to see it since containers don't share the host's home directory).
