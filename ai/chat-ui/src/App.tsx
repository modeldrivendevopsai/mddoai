import type { ReactNode } from "react"
import { BrowserRouter, Routes, Route, useNavigate } from "react-router-dom"
import OrchestratorScreen from "@/screens/OrchestratorScreen"
import PlatformIntegrationScreen from "@/screens/PlatformIntegrationScreen"
import { AppShell } from "@/layout/AppShell"
import { StartScreen } from "@/pages/StartScreen"

function StartScreenRoute() {
  const navigate = useNavigate()
  return (
    <StartScreen
      onSelectOption={(optionId) => {
        if (optionId === "add-update-platform") navigate("/platforms/new")
        if (optionId === "generate-pipeline") navigate("/")
      }}
    />
  )
}

// Shared by every route's AppShell so the sidebar behaves the same
// everywhere: "New pipeline" and session rows of type "pipeline" go to the
// Orchestrator screen, "platform" actions/rows go to the platform-onboarding
// screen. "copy-platform" has no real backend capability yet (no endpoint
// to copy an existing platform's support), so it lands on the same add-
// platform screen rather than pretending to do something it can't.
function useSidebarNavigation() {
  const navigate = useNavigate()
  return (actionId: string) => {
    if (actionId === "new-pipeline") navigate("/")
    if (actionId === "add-platform" || actionId === "copy-platform") navigate("/platforms/new")
  }
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
        <Route
          path="/platforms/new"
          element={
            <AppShellRoute>
              <PlatformIntegrationScreen />
            </AppShellRoute>
          }
        />
      </Routes>
    </BrowserRouter>
  )
}

export default App
