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
  return <StartScreen onSelectOption={() => navigate("/")} />
}

// Shared by every route's AppShell so the sidebar behaves the same
// everywhere: every action ("New pipeline", "Add/copy a platform", and
// session rows of either type) goes to the one real screen. "copy-platform"
// has no real backend capability yet (no endpoint to copy an existing
// platform's support), so it lands on the same screen rather than
// pretending to do something it can't.
function useSidebarNavigation() {
  const navigate = useNavigate()
  return () => navigate("/")
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
              <OrchestratorScreen />
            </AppShellRoute>
          }
        />
        <Route
          path="/start"
          element={
            <AppShellRoute>
              <StartScreenRoute />
            </AppShellRoute>
          }
        />
      </Routes>
    </BrowserRouter>
  )
}

export default App
