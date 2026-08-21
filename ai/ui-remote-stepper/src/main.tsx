import { StrictMode } from "react"
import { createRoot } from "react-dom/client"

// This page is not the real runtime entry point — ui-host loads this
// package's exposed component(s) via Module Federation (see vite.config.ts's
// exposes map), not by visiting this URL. It exists only because a plain
// `vite build` needs an HTML entry point to build against; visiting this
// URL directly (e.g. during `npm run dev`) is a standalone smoke check that
// this package's own build/dev server is up, not a real UI.
createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <div style={{ fontFamily: "sans-serif", padding: 24 }}>
      <p>ui-remote-stepper — Module Federation remote.</p>
      <p>Exposes: Stepper</p>
    </div>
  </StrictMode>
)
