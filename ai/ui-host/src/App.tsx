import type { ReactNode } from "react"
import { BrowserRouter, Routes, Route, useLocation, useNavigate, useParams } from "react-router-dom"
import GenerateScreen from "@/screens/GenerateScreen"
import IntegrationScreen from "@/screens/IntegrationScreen"
import PipelinesScreen from "@/screens/PipelinesScreen"
import { AppShell } from "@/layout/AppShell"
import { StartScreen } from "@/screens/StartScreen"

// Where each StartScreen card goes. "Add or update a CI/CD platform" is the
// integration screen (its docs stage IS that flow: it fetches the platform's
// documentation as the first of its stages); "Generate a CI/CD
// pipeline" is its own mode, with its own backend, and opens on the list of
// pipelines already generated, so someone coming back starts from their work.
const START_OPTION_ROUTES: Record<string, string> = {
  "generate-pipeline": "/generate",
  "add-update-platform": "/integration",
}

function StartScreenRoute() {
  const navigate = useNavigate()
  return <StartScreen onSelectOption={(optionId) => navigate(START_OPTION_ROUTES[optionId] ?? "/integration")} />
}

function PipelinesScreenRoute() {
  const navigate = useNavigate()
  return (
    <PipelinesScreen
      onBack={() => navigate("/")}
      onNew={() => navigate("/generate/new")}
      onOpen={(pipelineId) => navigate(`/generate/${pipelineId}`)}
    />
  )
}

// Keyed by the navigation entry: "New pipeline" while already on
// /generate/new navigates to the same URL, and without a fresh key React
// would keep the screen mounted, still holding the previous file and results.
// /generate/new has no id; /generate/<id> brings a saved pipeline back.
function GenerateScreenRoute() {
  const location = useLocation()
  const navigate = useNavigate()
  const { pipelineId } = useParams()
  return <GenerateScreen key={location.key} pipelineId={pipelineId} onBack={() => navigate("/generate")} />
}

// Shared by every route's AppShell so the sidebar behaves the same
// everywhere. "New pipeline" opens a blank generate screen. Every other action
// ("Add/copy a platform", and session rows of either type) goes to the
// integration screen; "copy-platform" has no real backend capability yet (no
// endpoint to copy an existing platform's support), so it lands there rather
// than pretending to do something it can't.
//
// "add-platform" specifically means "start over" - it needs ?new=1, not a bare
// navigate("/integration"): the backend's current run isn't scoped to a URL,
// so if you're already on /integration (viewing the live run, or a finished
// one with nothing left to approve), navigating to the exact same URL is a
// no-op in react-router (no re-render, nothing resets) and the old run just
// sits there. IntegrationScreen reads ?new=1 and actually resets the backend,
// the same real action its own Restart button takes.
const SIDEBAR_ROUTES: Record<string, string> = {
  "new-pipeline": "/generate/new",
  "add-platform": "/integration?new=1",
}

function useSidebarNavigation() {
  const navigate = useNavigate()
  return (actionId: string) => navigate(SIDEBAR_ROUTES[actionId] ?? "/integration")
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
          path="/generate"
          element={
            <AppShellRoute>
              <PipelinesScreenRoute />
            </AppShellRoute>
          }
        />
        <Route
          path="/generate/new"
          element={
            <AppShellRoute>
              <GenerateScreenRoute />
            </AppShellRoute>
          }
        />
        <Route
          path="/generate/:pipelineId"
          element={
            <AppShellRoute>
              <GenerateScreenRoute />
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
