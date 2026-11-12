# CLAUDE.md — ui-host

## Project Overview

This is the UI host for MDDOAI, a system that generates CI/CD pipeline configurations from software architecture models. It lives inside the `mddoai` monorepo at `ai/ui-host/`.

All AI-related work lives under `mddoai/ai/`, separate from the Java/Eclipse codebase at the repo root. This file only covers `ui-host/`. See `ai/README.md` for how this service fits into the rest of the stack, and the folder-boundaries section of `ai/CLAUDE.md` for the `ui-` naming convention shared with the `ui-remote-*` packages below.

`ui-host` is a Module Federation host. It owns routing, shell layout and all real state and backend calls. Every independently-liftable UI section is its own `ui-remote-*` package in its own container. That covers each pipeline stage's panel, the chat column and the stepper. They are loaded here by a federated import at runtime, not by a source import. See Architecture below for how. The `remotes` map in `vite.config.ts` and the stage registry in `src/features/integration/` are the source of truth for which remote exposes what.

The app is a router-based SPA with a persistent shell, `AppShell` (sidebar + top bar), wrapping the app's screens. `App.tsx` says which route shows which screen, and `src/screens/` holds one file per screen. That list is not repeated here, so it cannot go stale.

The app has two modes. Each opens on a list of what already exists, so someone coming back to their work starts from it, and each keeps its own colour from the start screen to the sidebar (blue for pipelines, purple for platforms).

- **The start screen** is the landing page. It offers the two modes as the two sides of one cycle: a gap found while generating a pipeline leads to platform work, and a fixed platform leads back to generating. The arrows carry a few words each, so the picture explains itself without a paragraph. Each side has a one-line description and an "Open" button, which opens that mode's list. Under the picture is a short list that follows the sidebar's active tab: the supported platforms (name, version and where each came from, each opening its platform) while Platforms is on, the latest pipelines (newest first, each opening its pipeline) while Pipelines is on. The first screen is read at a glance, so it shows no more than that.
- **Add or update a CI/CD platform** opens on the supported platforms, laid out like the pipelines list below, with a "+ New platform" button. A row opens its platform wherever it is pressed (the platform's name is its button), and each source has an "Open chat" link under it, which opens the chat of the run that published the platform and is greyed out, with the reason on hover, where no run did. Its header is "← CI/CD Platforms", the way back to the start screen. The platform's own screen shows what it is made of, to read and copy: its version (a picker lists every version, newest first), the file it writes, and a tab each for its metamodel, its transformation and its template, with a line saying what that file does. It says which run published it and opens that chat, which has the stage outputs that made it, or says it came with MDDOAI and no chat made it. Its header is the platform's name, the way back to the list. It changes nothing: a new version is a new integration run. The editing view is behind that button, not the first thing seen, and its header title is the way back to this list, in the same style as every screen's. It is the platform-integration dashboard: a human starts a run by entering a platform name, its real documentation URL, and any advanced retrieval options. They then watch the run's progress through its stages on a stepper, review each stage's output (approve, reject with a correction, or retry), and can nudge the Orchestrator with a free-form message at any point. Its first stage (`docs`) is a real crawl. An optional `?run=<run_id>` query param (set when a sidebar session row is clicked, see Session history below) views a past run instead of the live one, read-only.
- **Generate a CI/CD pipeline** opens on the pipelines already generated (the model, the platforms, the output files and when), a row opens its pipeline wherever it is pressed (the model's name is its button), plus "+ New pipeline". Its header is "← CI/CD Pipelines", the way back to the start screen. Opening one of them, or starting a new one, leads to the mode's second screen, for someone who is not a DevOps engineer. Its header is "← Generate CI/CD Pipeline", the way back to the pipelines list, in the same style as the first screen's. Inputs on the left: the model file (a typed or browsed workspace path, or an upload), and one or more platforms. The pipeline on the right, with a button to download it above its code. The model is checked as soon as it is loaded. A generated pipeline is checked as soon as it is shown, with no button: its status is at the top right of the result, with each check in a drop-down that closes on Escape or a press outside. Only a check that could not run offers "Try again". A pipeline is called passed only when every check was made and passed; skipped checks are counted and shown as skipped. The layout and wording follow the approved wireframes (issue #277); the "Something's not right?" feedback box and the orchestrator triage behind it are not built. The mode has no run, chat, stepper or stage review. Its own backend is `ai/pipeline_generator`, not `ai/orchestrator`. Its components, state and copy live under `src/features/generate/` and `src/config/`.

Naming note, since it is easy to get backwards. The platform-integration screen and its supporting code are named around **"integration"**, not "pipeline", because the other mode is called "Generate a CI/CD pipeline" and naming the integration screen "pipeline" would collide with it. The other mode's code is named around **"generate"**. "Orchestrator" is reserved for the chat persona and controller and for the backend service `ai/orchestrator`. It does not name the whole integration screen.

---

## Behaviour

This is the platform-integration flow.

1. A human enters a platform name and its real documentation URL. They can expand "Advanced" for the crawler's own retry and steer options (see `ai/retrieval/README.md` for the current options) and tick "Mock", then start a run.
2. The first stage, `docs`, runs for real: `ai/orchestrator` calls the crawl in `ai/retrieval`. A fresh (not cached) crawl is slow enough to make local iteration painful. Ticking "Mock" skips the crawl. The orchestrator's docs agent returns canned placeholder output instead, so the other stages can be tried quickly during local development.
3. The human reviews each stage's output in turn. **Approve** advances and starts the next stage. **Reject** with a correction records it and stays on the same stage. **Retry** reruns the current stage, folding in any recorded correction.
4. The Orchestrator narrates what happens in the chat column after every real action, automatically.
5. The human can also type a free-form message into the nudge input, for example that a stage's output is wrong and how to fix it. An LLM decides which of the actions above it maps to.
6. This repeats until the last stage is approved.
7. A finished run offers **Publish**, which stores the platform the run built as a new version in the pipeline generator, so the "Generate a CI/CD pipeline" mode can generate pipelines for it.

---

## Stack

- **Vite + React + TypeScript**, no Next.js, no SSR
- **`react-router-dom`** for the shell and routing described above
- Styling is inline `style` against the design system's token custom properties (`var(--...)`), plus small per-component `.css` files for the rules inline styles cannot express (focus states, keyframes). See Design System below. No Tailwind, no shadcn.
- **Vitest** for tests

---

## Architecture

`AppShell` is the permanent navigation frame (sidebar and top bar), mounted once in `App.tsx` around whichever screen the current route shows. The sidebar's active tab (Pipelines or Platforms) is shared state, not the sidebar's own. It is held above the routes in `App.tsx`. The sidebar sets it (a tab press, or landing on a screen of that mode), and a screen that follows the tab, such as the start screen, reads it.

Two things are defined once and read everywhere, so changing them is one edit. Every screen's path lives in config, and every route definition, link and redirect reads it from there, as do the integration screen's query parameters, which links write and the screen reads. Every screen header's back control is one shared component, so going back looks the same on all of them.

Network calls go through `src/services/`, never directly from a component. There is one client per backend:

- **The orchestrator client** is the API client for every `ai/orchestrator` operation. It calls `/orchestrator-api/*` on this app's own origin, which ui-host's proxy forwards (the orchestrator is internal-only in `docker-compose.yml`). `ai/orchestrator` is itself what talks to `ai-layer` and `ai/retrieval` (see `ai/README.md`), so ui-host never calls either directly. Its function names mirror the orchestrator's real Python function and endpoint names one to one. That is a deliberate exception to the naming note above: they name the backend calls being wrapped, not the UI mode.
- **The generator client** is the API client for the generate mode. It calls `/generator-api/*`, which the proxy forwards to `ai/pipeline_generator`, also internal-only. It is separate from the orchestrator client on purpose: that mode has no run, chat or stages, so it is not reached through `ai/orchestrator`. Its types live in its own file, not in `orchestrator-types`, which is the orchestrator's own contract.
- **The sessions client** gives real session history, backed by the orchestrator's run-history endpoint (see Session history below).
- **Platforms** come from the orchestrator client too: the list is `GET /platforms` and one platform with its files is `GET /platforms/{id}`, which the orchestrator reads from the journal. There is no separate platforms client.

The `orchestrator-types` package (`ai/orchestrator-types`, a sibling package like `design-system`) mirrors the orchestrator's REST contract, its stage list, its event shape and its run-history shape. It is the source of truth for that contract across this host and every `ui-remote-*` package. See `ai/orchestrator/README.md` for the backend's own description of it.

The integration hook in `src/hooks/` takes an optional run id.

- With no run id, it polls the live run's events on an interval. It exposes the derived state plus every real mutating action the integration screen needs. Corrections are folded into retry, so there is no separate reject action. It keeps polling while a chat message is being answered. When the run changes underneath it (a chat answer can start a new run), it replaces the log instead of appending to it.
- With a run id for a past (non-current) run, it fetches that run's frozen event log once and reports that the run is not current. The screen uses that to switch from the live interactive view to a read-only one. It keeps polling such a run only while a chat answer is still coming.

It holds no local or simulated state: every action is a real call. It also returns the run id that is actually being shown, whether live or a past one from the URL. That differs from its own parameter, which is set only for a past run. A stage panel's attempts browser needs the real id in both cases.

The hook also owns the network call the chat column needs for its model picker (the provider list). Every federated remote is purely prop-driven. It receives state and callbacks from this hook the same way a locally imported component would, and never fetches its own backend data. That is a consistency choice, not something Module Federation requires. A `fetch()` inside a remote's code still resolves relative URLs against the host page's origin, not the remote's own (MDN: relative URLs resolve against `document.baseURI`). A remote could therefore call `/orchestrator-api/*` directly.

The integration screen loads the chat column, the stepper, the start form and each stage panel as `React.lazy()` federated imports. Each one is wrapped in its own `RemoteBoundary` (in `src/federated/`), not a bare `<Suspense>`. `Suspense` only handles a lazy import's pending state, never a rejected one. Without an error boundary somewhere, React unmounts the entire app on an uncaught render error, not just the broken piece. `RemoteBoundary` pairs a class-based error boundary with `Suspense`, so a dead or unreachable remote becomes a small inline fallback for just that piece. Everything else keeps working. Its `resetKey` prop remounts the boundary, clearing a caught error, when what it shows changes, for example when switching between stage panels.

The `federation()` config in `vite.config.ts` holds the remotes map. Each entry's URL comes from a `VITE_REMOTE_*_URL` build argument, which is a `/remotes/<name>` path on this origin in Docker (see Docker below). The ambient TypeScript declarations that each federated import resolves against are written by hand in `src/federated/`. That is this app's chosen alternative to the automatic type generation in `@module-federation/vite`, which failed in this environment. Every `vite.config.ts` that uses that plugin turns the generation off with `dts: false` and a comment saying so.

See `ai/CLAUDE.md` for cross-service folder boundaries.

---

## Session history

The sidebar's "Open sessions" list shows real data. The sessions client calls the orchestrator's `GET /runs`, which lists every run, newest first. The integration runner keeps its runs on disk, so the list survives a restart.

The list is polled on an interval (the generated-pipelines list shares it), not fetched once per tab switch. Which run is current, and the list itself, both change from actions taken entirely on the integration screen (starting, approving, resetting, resuming). The sidebar has to notice those on its own, not only when its own tab is clicked.

A row has two lines: the platform's name, and under it the stage the run is at and how long ago it began, such as "PSM, 2 hours ago". Runs of one platform are told apart by that second line.

A row's cross deletes the run for good. It asks first, in the row itself ("Delete this run for good?" with Delete and Keep), because a deleted run cannot be brought back. The current run has no cross: it is open on screen, so it is restarted or left, not deleted. The backend refuses a run a platform was published from, and the row shows its reason (remove that platform first). A run the person deleted stays out of the list even if a poll that began before the delete answers late.

Clicking a row opens the integration screen with that run's id in the URL (`?run=<run_id>`), which the screen passes to the integration hook.

The row marked as on screen is the chat being shown, so it is marked only on the integration screen: the run named in the URL, or the current run when the URL names none, and none for a blank run. On every other screen, such as the platforms list or a platform's own screen, no chat is on screen and no row is marked.

- The live run is always current. Any other run is read-only history. Every mutating action is disabled and replaced by "Back to current run" (clears the query param, no backend call) and "Resume this run" (the orchestrator's `POST /resume/{run_id}`, which makes that run current again, picking up exactly where it left off). Resume is the counterpart to Restart, which replaces the current run with a blank one instead of reviving an existing one.
- A past run that never got past the input form (for example one created by Restart or "Add a new platform" and then abandoned) shows a plain "No stages were run for this session" message. It renders neither the stage panels nor the live start form. That form's submit always acts on whatever is actually current, so showing it for a non-current run would let a read-only view silently take over the live run.

---

## The stage stepper

The stepper is its own `ui-remote-stepper` package, loaded here as a federated import. It renders directly off the orchestrator's stage list, imported from the shared `orchestrator-types` package, and follows the wireframe. It has no per-stage special-casing for its nodes. A stage's own output panel is deliberately not generic in the same way (see Design System below).

---

## Design System

`ai/design-system` is a sibling package, not a folder under this app's own `src/`. It is the shared component and token library for the whole app. Its own `src/index.ts` barrel export is the current list of what it exports, so no other doc should enumerate it.

Its values are ported verbatim from the MDDOAI Design System reference in `mddoai-design-system/project/` at the repo root. That reference is a set of Claude Design HTML/JSX prototypes to copy from, not an installable package. The brand is violet (`--brand`), with Space Grotesk for headings, IBM Plex Sans for body text and IBM Plex Mono for code and output. The app is light mode only.

Fonts are self-hosted through `@fontsource*` packages, not the reference's remote Google Fonts import. The mono face imports its `latin-<weight>.css` variant, not the bare `<weight>.css`. The bare file bundles Cyrillic and Vietnamese subsets that this English-only app never renders. The other two faces do not offer that split for their variable-weight builds, so they use the bundled file.

The design system lives outside `ui-host` on purpose. It is a real dependency (a local npm `file:` path dependency in `package.json`, with no workspace setup needed), not a copy, so every `ui-remote-*` package can depend on the same one. See `ai/design-system/README.md` for why it is a plain dependency and not a Module Federation remote even though it is shared code, and for its Windows symlink caveat for local npm paths.

`orchestrator-types` has the same shape for the REST and event contract instead of UI components. It is a local `file:` dependency in every consuming `package.json`, bundled in at build time. It is almost entirely type-only, so it is erased at compile time and costs nothing at runtime. The exception is a few small constant arrays that some consumers also need at runtime, such as the stage list and the event types. The contract types used across this host and every `ui-remote-*` package live there, not copied into each package. See `ai/orchestrator-types/README.md` for why.

`src/features/` holds what is local to this host: cross-cutting plumbing and each mode's own UI. The chat column, the stepper and every per-stage panel live in their own `ui-remote-*` package.

- `src/features/integration/` holds the plumbing shared by the stages. It has pure functions over the real event log that the integration screen calls, which are not tied to any one stage. It also has the stage registry, which maps each stage id to its panel component. Each entry is a `React.lazy()` federated import into that stage's own `ui-remote-stage-*` package. The registry is where all the stages are known together. A stage panel's own package never imports the registry or another stage's package.
- `src/federated/` holds the remote boundary and the ambient TypeScript declarations for every federated import specifier. They refer to `orchestrator-types` for the prop shapes.

Each stage has its own package (`ai/ui-remote-stage-*/`, one per stage id). It holds that stage's panel as its own component, not one generic component parameterized by stage id. The panel renders both the stage's active state and its read-only viewed state. The active state (Approve and Retry) shows when the stage is the live pending one. The viewed state shows when a past instance of the stage is selected on the stepper. Which one shows depends on whether the caller passes the approve and retry callbacks or a back callback.

Many stages render near-identically today. The point is that each stage's component and container can diverge on their own as MDDOAI's per-stage backend prompts diverge. They can differ in output shape, in review UI, even in release cadence, without touching the others. Some stages already differ. They render an initial screen with a prompt builder from the design system. It edits the stage's own editable prompt config and has a "Generate" action, because these stages do not auto-run when they become current. They also render an attempts browser for real past attempts. See each package's own README. A stage without its own backend implementation yet stays near-identical to the simple ones until it gets one.

The docs stage package is asymmetric. It also exposes the start form, the one thing shown before any run exists. The integration screen loads it directly, bypassing the registry, because there is no started state for the registry to look up yet. It is not a generic "start a run" form. Almost every field it collects is the docs stage's own real input, read straight from the pipeline's request body on the backend. The one field that is not docs-specific is the platform name. The docs stage never reads it, but every later stage falls back to it, so it is collected once here instead of asked again per stage.

`ai/ui-remote-chat/` holds the chat column: the Orchestrator's chat log, nudge input and model picker. "The Orchestrator" in the UI means this column, not the whole integration screen. `ai/ui-remote-stepper/` holds the stepper (see The stage stepper above). The code-output display that the stage panels share lives in the design system, not in any one remote. Several otherwise unrelated consumers need the exact same one, which is the reason anything lives in that package.

Component styling throughout `src/features/` and every `ui-remote-*` package uses inline `style` referencing token custom properties directly, not Tailwind utility classes (see Stack above).

---

## Docker

`docker compose up --build` in an environment's folder (see `infra/README.md`) builds this app into static files and serves them with nginx, published on the environment's `UI_PORT`, on this machine only. That server is the origin the browser uses (see `nginx/default.conf.template`). It serves the app, forwards `/remotes/<name>/` to each `ui-remote-*` container, and forwards one prefix per backend API to its internal-only service. See `ai/README.md` for the full topology.

The build context is `ai/`, not this folder alone. The `design-system` and `orchestrator-types` dependencies are sibling packages (local `file:` paths), and Docker cannot `COPY` from outside its build context. The Dockerfile therefore copies those packages in too, keeping them siblings in the image as they are on disk (see the Dockerfile's own comment). It builds in a Node stage and keeps only `dist/` plus nginx in the final image. Every `ui-remote-*` package follows the same Dockerfile shape, with the stock nginx configuration.

A change to code here or in a shared package takes `docker compose up -d --build` of the services that use it. There is no hot reloading in Docker.

Loading a `ui-remote-*` piece works like this. The **browser** fetches each remote's `remoteEntry.js` at runtime from `/remotes/<name>/` on this origin, and the proxy forwards it to that remote's container. The `remote()` helper in `vite.config.ts` builds each URL from a `VITE_REMOTE_*_URL` variable, which `docker-compose.yml` passes as a build argument. Each remote is built with the matching path as its Vite `base` (its `PUBLIC_PATH` build argument), so its own files are requested under it too. `remoteEntry.js` is served uncached, so a remote rebuilt on its own is picked up on the next page load without rebuilding this app. There is no `depends_on` between `ui-host` and anything else. The proxy resolves names per request, and a remote that is down only affects its own piece of the screen.

---

## What This Is Not

- Not a generic chatbot template. Every action maps to a real `ai/orchestrator` operation. There is no conversation independent of the run.
- Not connected to `ai-layer` or `ai/retrieval` directly from this app. It always goes through `ai/orchestrator`. It also talks to `ai/pipeline_generator`, for the generate mode only.
- Not an editor of platforms: a platform's files are shown to read and copy. The list has no status or coverage figure, because no real data stands behind them yet. Add one only when it is real.
- Not a place for generate-mode sessions: that mode has no run to resume. The sidebar's Pipelines tab instead lists the generated pipelines, newest first, each with the platforms it was made for and the files that came out. The list is kept by `ai/pipeline_generator`, not by this app. Opening one is the caller's to route, so the list component knows nothing about routes.
- Not a production deployment: the stack runs on one machine for development and review, with no TLS and no authentication in front of it (see `ai/README.md`).
