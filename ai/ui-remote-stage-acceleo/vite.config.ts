import path from "node:path"
import { defineConfig } from "vitest/config"
import react from "@vitejs/plugin-react"
import { federation } from "@module-federation/vite"

// Module Federation remote: exposes its own component(s) for ui-host to
// consume as a federated import. Published (ports:, see docker-compose.yml),
// not just container-internal: the browser itself fetches this dev server's
// remoteEntry.js directly from its own origin, after ui-host's page has
// already loaded, not something ui-host's server proxies for it. cors: true
// so the browser is allowed to fetch it cross-origin from ui-host's own
// page (localhost:5173). build.target/modulePreload match
// @module-federation/vite's own documented requirements for a federated
// build (https://module-federation.io/integrations/build-tool/vite.html).
export default defineConfig({
  plugins: [
    react(),
    federation({
      name: "uiRemoteStageAcceleo",
      filename: "remoteEntry.js",
      exposes: {
        "./AcceleoStagePanel": "./src/AcceleoStagePanel.tsx",
      },
      shared: ["react", "react-dom"],
      // Manual ambient .d.ts declarations on ui-host's side are the type-sharing
      // approach here (see ui-host/src/federated/remotes.d.ts), not this
      // plugin's own dts generation -- which also failed in this environment
      // (TYPE-001, a known module-federation/vite tsconfig interaction, see
      // https://module-federation.io/guide/troubleshooting/type#type-001).
      dts: false,
    }),
  ],
  server: {
    fs: {
      // design-system is a sibling directory of this project (this remote
      // imports its components/CSS directly, see e.g. this project's own
      // StagePanel.tsx) - Vite's default filesystem allow-list only
      // covers this project's own root, so serving a file that only
      // exists under design-system's own node_modules (its
      // @fontsource-variable font files, imported by
      // design-system/src/tokens.css) 403s without this. Resolved by name
      // relative to this file, not hardcoded to either environment's own
      // absolute layout (Docker's sibling top-level dirs vs. a local
      // checkout's ../design-system), so it works the same in both.
      allow: [path.resolve(__dirname), path.resolve(__dirname, "..", "design-system")],
    },
    port: 5181,
    strictPort: true,
    cors: true,
    watch: {
      // Same Docker-Desktop-on-Windows reasoning as ui-host/vite.config.ts.
      usePolling: process.env.CHOKIDAR_USEPOLLING === "true",
      interval: 300,
    },
  },
  build: {
    target: "esnext",
    modulePreload: false,
  },
  test: {
    environment: "node",
  },
})
