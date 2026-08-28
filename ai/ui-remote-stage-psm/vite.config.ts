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
      name: "uiRemoteStagePsm",
      filename: "remoteEntry.js",
      exposes: {
        "./PsmStagePanel": "./src/PsmStagePanel.tsx",
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
    port: 5179,
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
