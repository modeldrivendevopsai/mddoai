# ui-host

The UI for MDDOAI. A router-based SPA with a persistent shell (sidebar + top bar) around two
screens: a landing page and a platform-integration dashboard. See [CLAUDE.md](./CLAUDE.md) for
the full product and design spec.

## Stack

| Purpose | Tool | Docs |
|---|---|---|
| Build tool / dev server | [Vite](https://vite.dev/) | [Guide](https://vite.dev/guide/) |
| UI framework | [React 19](https://react.dev/) + TypeScript | [React docs](https://react.dev/learn) · [TS handbook](https://www.typescriptlang.org/docs/handbook/intro.html) |
| Routing | [react-router-dom](https://reactrouter.com/) | [Docs](https://reactrouter.com/en/main) |
| Styling | Inline `style={{ }}` against the `design-system` package's `tokens.css`, plus small per-component `.css` files for the rules inline styles can't express | see [CLAUDE.md](./CLAUDE.md)'s Design System section |
| Testing | [Vitest](https://vitest.dev/) | [Docs](https://vitest.dev/guide/) |

## Prerequisites

- [Node.js](https://nodejs.org/) 22+ and npm
- [Docker](https://www.docker.com/) (only needed for the containerized run)

## Getting started

```bash
npm install
npm run dev
```

Open the URL Vite prints (default [http://localhost:5173](http://localhost:5173)).

## Develop

Routes live in `App.tsx`, each wrapped in the shared `AppShell` (sidebar + top bar):

- **`/`** — `src/screens/StartScreen.tsx`, the landing page.
- **`/integration`** — `src/screens/IntegrationScreen.tsx`, the platform-integration dashboard.
  This is the one real "add/update a platform" flow: its docs stage is a real crawl.

Where things live:

- **Platform integration dashboard** `src/screens/IntegrationScreen.tsx`, composing the chat
  column, the stepper, and the current pipeline stage's own panel — each its own Module
  Federation remote (`ai/ui-remote-chat`, `ai/ui-remote-stepper`, `ai/ui-remote-stage-*`, loaded
  via `src/features/integration/stages/registry.ts` for the stage panels; see `vite.config.ts`'s
  `federation()` config for the current, exact remotes list), not local source under this app's
  own `src/`. Backed by its own real API client `src/services/orchestrator.service.ts`, polling
  hook `src/hooks/useIntegration.ts`, and REST-contract types `src/types/orchestrator.ts`.
- **Landing page** `src/screens/StartScreen.tsx`, sourced from `src/config/startOptions.config.ts`,
  with mock data from `src/services/platforms.service.ts`.
- **Shell** `src/layout/` (`AppShell`, `Sidebar`, `SidebarActions`, `SessionsList`, `TopBar`),
  sourced from `src/config/sidebar.config.ts` and `src/services/sessions.service.ts`.
- **Component primitives** the sibling `design-system` package (`ai/design-system`, not a folder
  under this app's own `src/`), a real local npm dependency, not a copy — see its own README.
- **Backend contract** All real network calls go through `src/services/orchestrator.service.ts`,
  which only ever talks to `ai/orchestrator` (never the Java backend, `ai-layer`, or
  `ai/retrieval` directly, `ai/orchestrator` itself is the only thing that talks to those, see
  `ai/README.md`).

The `@/` import alias points at `src/` (configured in `vite.config.ts` and `tsconfig.app.json`) —
`design-system` isn't under `src/` any more, so it's imported by its bare package name instead.

## Test

```bash
npm run test         # one-shot run
npm run test:watch   # watch mode
```

`src/services/orchestrator.service.test.ts` covers the real API client's request/response
contract with `fetch` mocked, see the file for current cases.

## Lint and typecheck

```bash
npm run lint
npx tsc -b
```

## Build

```bash
npm run build      # outputs to dist/
npm run preview    # serve the production build locally
```

## Docker

`docker compose up --build` from `ai/` runs this as a hot-reloading dev server (`npm run dev --
--host 0.0.0.0`, source volume-mounted in), published at `http://localhost:5173`, proxying
`/orchestrator-api/*` to `ai/orchestrator` by its Docker Compose service name. Not the
`Dockerfile` in this folder, that one builds static assets only (`npm run build`, no server) and
is meant for an actual deployment target later, not local dev. See `ai/README.md` for the full
compose setup.

Build context is `ai/`, not this folder alone, and the sibling `design-system` package gets its
own bind mount too — see this package's own `CLAUDE.md`'s Docker section for why (both come from
`package.json` depending on `design-system` via a local `file:` path).

This is also the Module Federation host: it loads the chat column, the stepper, and each pipeline
stage panel at runtime from their own `ui-remote-*` containers (see `ai/README.md`'s services list
for the current set and their ports). Each remote's `remoteEntry.js` is fetched by the **browser**
directly against that remote's own published port, not proxied by this dev server — see this
package's own `CLAUDE.md`'s Docker section for the full mechanics. If a remote's container isn't
running, its piece of the screen shows a small "couldn't load" fallback instead of the real
panel/chat/stepper — everything else on the page keeps working, see this package's own `CLAUDE.md`
for why (`RemoteBoundary`) — rather than the rejected import taking down the whole page;
`docker compose up --build` from `ai/` starts every remote alongside this host.

**After adding a new npm dependency**, a plain `docker compose up --build` isn't enough: Compose
reuses the container's anonymous `node_modules` volume (`docker-compose.yml`'s
`/app/node_modules` entry) across recreation by default, so the new package won't actually be
there even though the image rebuilt with it. Force a fresh volume with:

```bash
docker compose up -d --build --renew-anon-volumes ui-host
```

Symptom if you skip this: Vite's overlay reports `Failed to resolve import "..."` for the
package you just added, even though `npm ci` clearly succeeded during the image build.

**If an edit to a source file doesn't show up in the browser** (no `[vite] hmr update` line in
`docker compose logs ui-host`, page keeps serving the old version even after a hard refresh):
Docker Desktop on Windows doesn't always forward native filesystem change events through the
bind mount into the Linux container, so Vite's watcher silently misses the edit. `vite.config.ts`
enables polling (`server.watch.usePolling`) whenever `CHOKIDAR_USEPOLLING=true` is set, which
`docker-compose.yml` sets for exactly this reason, if you still hit this, confirm that env var
made it into the running container (`docker compose exec ui-host printenv CHOKIDAR_USEPOLLING`).

## Project structure

Everything lives under `src/`: `screens/` holds the app's two screens, `layout/` holds the
persistent shell, `features/integration/` holds the cross-stage plumbing that stays local to this
host (see CLAUDE.md's Design System section for the full breakdown), `federated/` holds the
ambient TypeScript declarations for every federated import, `services/` holds the files that call
the backend or provide mock data, `hooks/` holds `useIntegration`, and `types/orchestrator.ts`
holds the shared REST-contract types. Shared component/token primitives live in the sibling
`design-system` package (`ai/design-system`); the chat column, the stepper, and every pipeline
stage panel live in their own `ai/ui-remote-*` packages — none of them under this app's own `src/`.
See `ai/README.md` for how the full stack fits together.
