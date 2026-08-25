import { Component, Suspense } from "react"
import type { ErrorInfo, ReactNode } from "react"
import { Button } from "design-system"

// A rejected React.lazy() import (the remote's container is down, its
// remoteEntry.js 404s, a network blip) throws during render — Suspense only
// ever covers the *pending* state, never a rejection. Without a real error
// boundary somewhere, React's own documented behavior for an uncaught
// render error is to unmount the entire root, not just the one failed
// piece: one dead ui-remote-* container would take the whole app down to a
// blank screen. Confirmed for real, not just from React's docs: stopping
// ui-remote-stage-pim and hitting this app crashed everything, not just the
// PIM panel, before this file existed.
interface RemoteBoundaryProps {
  // Human-readable label for whichever federated piece this wraps, shown in
  // the fallback so it's obvious which container is the actual problem.
  name: string
  children: ReactNode
}

interface RemoteErrorBoundaryState {
  failed: boolean
}

class RemoteErrorBoundary extends Component<RemoteBoundaryProps, RemoteErrorBoundaryState> {
  state: RemoteErrorBoundaryState = { failed: false }

  static getDerivedStateFromError(): RemoteErrorBoundaryState {
    return { failed: true }
  }

  componentDidCatch(error: Error, info: ErrorInfo) {
    console.error(`Failed to load the "${this.props.name}" remote:`, error, info.componentStack)
  }

  render() {
    if (!this.state.failed) return this.props.children

    // No auto-retry: React.lazy() memoizes its loader's promise, so simply
    // clearing local error state wouldn't actually re-fetch remoteEntry.js,
    // it would just re-render against the same already-rejected promise. A
    // real reload is the honest fix here, not a fake "Retry" button that
    // silently does nothing.
    return (
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          gap: "var(--space-3)",
          height: "100%",
          padding: "var(--space-4)",
          background: "var(--danger-100)",
          border: "1px solid var(--danger-border)",
          borderRadius: "var(--radius-md)",
          boxSizing: "border-box",
        }}
      >
        <p style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-sm)", color: "var(--text-body)", margin: 0 }}>
          Couldn't load "{this.props.name}". Its own container may be down or unreachable.
        </p>
        <Button variant="secondary" size="sm" onClick={() => location.reload()} style={{ alignSelf: "flex-start" }}>
          Reload page
        </Button>
      </div>
    )
  }
}

// Shown in place of a federated remote while its own bundle is still
// loading — a real Suspense fallback, not a skeleton screen, deliberately
// plain since this only shows on first paint of a not-yet-cached remote.
function RemoteLoading() {
  return <div style={{ padding: "var(--space-4)", color: "var(--text-muted)" }}>Loading…</div>
}

// The one place every federated import in this app goes through: pairs the
// loading state (Suspense) with the failure state (the error boundary
// above) so neither has to be wired up separately at each call site.
// resetKey lets a call site force a fresh error-boundary instance when the
// underlying remote identity changes (e.g. switching which stage panel is
// being viewed) — without it, a boundary that already caught one panel's
// failure would keep showing that same failure even after navigating to a
// different, perfectly healthy panel, since it's still the same mounted
// class instance.
export function RemoteBoundary({ name, resetKey, children }: RemoteBoundaryProps & { resetKey?: string }) {
  return (
    <RemoteErrorBoundary key={resetKey ?? name} name={name}>
      <Suspense fallback={<RemoteLoading />}>{children}</Suspense>
    </RemoteErrorBoundary>
  )
}
