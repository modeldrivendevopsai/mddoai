import path from "node:path"
import { defineConfig } from "vite"
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
      name: "uiRemoteStagePim",
      filename: "remoteEntry.js",
      exposes: {
        "./PimStagePanel": "./src/PimStagePanel.tsx",
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
      // relative to this file, not hardcoded to one checkout's absolute
      // layout.
      allow: [path.resolve(__dirname), path.resolve(__dirname, "..", "design-system")],
    },
    port: 5178,
    strictPort: true,
    cors: true,
  },
  build: {
    target: "esnext",
    modulePreload: false,
  },
})
