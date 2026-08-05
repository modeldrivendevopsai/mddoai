import type { ReactNode } from "react"
import { BrowserRouter, Routes, Route, useNavigate } from "react-router-dom"
import OrchestratorScreen from "@/screens/OrchestratorScreen"
import { AppShell } from "@/layout/AppShell"
import { StartScreen } from "@/screens/StartScreen"

// Both entry points land on the same real screen — Orchestrator's docs
// stage IS the "add/update a platform" flow (it fetches the platform's
// documentation as the first stage of the pipeline), there's no separate
// standalone screen for it anymore.
function StartScreenRoute() {
  const navigate = useNavigate()
  return <StartScreen onSelectOption={() => navigate("/pipeline")} />
}

// Shared by every route's AppShell so the sidebar behaves the same
// everywhere: every action ("New pipeline", "Add/copy a platform", and
// session rows of either type) goes to the one real screen. "copy-platform"
// has no real backend capability yet (no endpoint to copy an existing
// platform's support), so it lands on the same screen rather than
// pretending to do something it can't.
//
// "new-pipeline"/"add-platform" specifically mean "start over" — they need
// ?new=1, not a bare navigate("/pipeline"): the backend's current run isn't
// scoped to a URL, so if you're already on /pipeline (viewing the live run,
// or a finished one with nothing left to approve), navigating to the exact
// same URL is a no-op in react-router (no re-render, nothing resets) and the
// old run just sits there. OrchestratorScreen reads ?new=1 and actually
// resets the backend, the same real action its own Restart button takes.
const START_FRESH_ACTIONS = new Set(["new-pipeline", "add-platform"])

function useSidebarNavigation() {
  const navigate = useNavigate()
  return (actionId: string) => navigate(START_FRESH_ACTIONS.has(actionId) ? "/pipeline?new=1" : "/pipeline")
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
          path="/pipeline"
          element={
            <AppShellRoute>
              <OrchestratorScreen />
            </AppShellRoute>
          }
        />
      </Routes>
    </BrowserRouter>
  )
}

export default App
