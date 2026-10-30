import { defineConfig } from "vitest/config"
import react from "@vitejs/plugin-react"
import { federation } from "@module-federation/vite"

// Module Federation remote: exposes its own component(s) for ui-host to
// consume as a federated import. In Docker it is built and served through
// ui-host's proxy under /remotes/<name>/ (its Vite base, passed as
// --base by the Dockerfile), so the browser loads it from ui-host's own
// origin. The dev server below is for `npm run dev` outside Docker, where
// ui-host's page (localhost:5173) loads it cross-origin, hence cors: true.
// build.target/modulePreload match @module-federation/vite's own documented
// requirements for a federated build
// (https://module-federation.io/integrations/build-tool/vite.html).
export default defineConfig({
  plugins: [
    react(),
    federation({
      name: "uiRemoteChat",
      filename: "remoteEntry.js",
      exposes: {
        "./ChatColumn": "./src/ChatColumn.tsx",
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
    port: 5174,
    strictPort: true,
    cors: true,
  },
  build: {
    target: "esnext",
    modulePreload: false,
  },
  test: {
    environment: "node",
  },
})
