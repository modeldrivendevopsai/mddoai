import type { ReactNode } from "react"
import { BrowserRouter, Routes, Route, useNavigate } from "react-router-dom"
import IntegrationScreen from "@/screens/IntegrationScreen"
import { AppShell } from "@/layout/AppShell"
import { StartScreen } from "@/screens/StartScreen"

// Both entry points land on the same real screen — the integration's docs
// stage IS the "add/update a platform" flow (it fetches the platform's
// documentation as the first of its six stages), there's no separate
// standalone screen for it anymore.
function StartScreenRoute() {
  const navigate = useNavigate()
  return <StartScreen onSelectOption={() => navigate("/integration")} />
}

// Shared by every route's AppShell so the sidebar behaves the same
// everywhere: every action ("New pipeline", "Add/copy a platform", and
// session rows of either type) goes to the one real screen. "copy-platform"
// has no real backend capability yet (no endpoint to copy an existing
// platform's support), so it lands on the same screen rather than
// pretending to do something it can't.
//
// "new-pipeline"/"add-platform" specifically mean "start over" — they need
// ?new=1, not a bare navigate("/integration"): the backend's current run
// isn't scoped to a URL, so if you're already on /integration (viewing the
// live run, or a finished one with nothing left to approve), navigating to
// the exact same URL is a no-op in react-router (no re-render, nothing
// resets) and the old run just sits there. IntegrationScreen reads ?new=1
// and actually resets the backend, the same real action its own Restart
// button takes. "new-pipeline" is disabled today (see sidebar.config.ts's
// comingSoon), can't actually fire, and is only in this set for that
// disabled click's sake — once "Generate a CI/CD pipeline" is a real,
// separate mode, it needs its own screen/route entirely, not /integration,
// so this whole mapping (not just the target URL) will need reworking then.
const START_FRESH_ACTIONS = new Set(["new-pipeline", "add-platform"])

function useSidebarNavigation() {
  const navigate = useNavigate()
  return (actionId: string) => navigate(START_FRESH_ACTIONS.has(actionId) ? "/integration?new=1" : "/integration")
}

function AppShellRoute({ children }: { children: ReactNode }) {
  const onSidebarAction = useSidebarNavigation()
  return <AppShell onSidebarAction={onSidebarAction}>{children}</AppShell>
}

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={
            <AppShellRoute>
              <StartScreenRoute />
            </AppShellRoute>
          }
        />
        <Route
          path="/integration"
          element={
            <AppShellRoute>
              <IntegrationScreen />
            </AppShellRoute>
          }
        />
      </Routes>
    </BrowserRouter>
  )
}

export default App
