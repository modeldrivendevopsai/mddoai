import type { ReactNode } from "react"
import { BrowserRouter, Routes, Route, useLocation, useNavigate, useParams } from "react-router-dom"
import GenerateScreen from "@/screens/GenerateScreen"
import IntegrationScreen from "@/screens/IntegrationScreen"
import PipelinesScreen from "@/screens/PipelinesScreen"
import PlatformsScreen from "@/screens/PlatformsScreen"
import { AppShell } from "@/layout/AppShell"
import { SidebarTabProvider } from "@/layout/SidebarTabProvider"
import { StartScreen } from "@/screens/StartScreen"

// Where each StartScreen card goes. Both open on the list of what already
// exists, so someone coming back starts from their work: "Generate a CI/CD
// pipeline" is its own mode, with its own backend, and opens on the pipelines
// already generated; "Add or update a CI/CD platform" opens on the platforms
// already supported. The integration screen (its docs stage IS the add/update
// flow: it fetches the platform's documentation as the first of its stages) is
// behind the "+ New platform" button.
const START_OPTION_ROUTES: Record<string, string> = {
  "generate-pipeline": "/generate",
  "add-update-platform": "/platforms",
}

// A blank integration run. It needs ?new=1, not a bare "/integration": the
// comment on SIDEBAR_ROUTES below says why.
const NEW_PLATFORM_PATH = "/integration?new=1"

// Where a saved pipeline is shown. Used by the pipelines list and by the sidebar.
const pipelinePath = (pipelineId: string) => `/generate/${pipelineId}`

function StartScreenRoute() {
  const navigate = useNavigate()
  return (
    <StartScreen
      onSelectOption={(optionId) => navigate(START_OPTION_ROUTES[optionId] ?? "/platforms")}
      onOpenPipeline={(pipelineId) => navigate(pipelinePath(pipelineId))}
    />
  )
}

function PlatformsScreenRoute() {
  const navigate = useNavigate()
  return <PlatformsScreen onBack={() => navigate("/")} onNew={() => navigate(NEW_PLATFORM_PATH)} />
}

function PipelinesScreenRoute() {
  const navigate = useNavigate()
  return (
    <PipelinesScreen
      onBack={() => navigate("/")}
      onNew={() => navigate("/generate/new")}
      onOpen={(pipelineId) => navigate(pipelinePath(pipelineId))}
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
  "add-platform": NEW_PLATFORM_PATH,
}

function useSidebarNavigation() {
  const navigate = useNavigate()
  return (actionId: string) => navigate(SIDEBAR_ROUTES[actionId] ?? "/integration")
}

function AppShellRoute({ children }: { children: ReactNode }) {
  const navigate = useNavigate()
  // Set only on /generate/<id>, the screen of a saved pipeline.
  const { pipelineId } = useParams()
  const onSidebarAction = useSidebarNavigation()
  return (
    <AppShell
      onSidebarAction={onSidebarAction}
      onOpenPipeline={(id) => navigate(pipelinePath(id))}
      openPipelineId={pipelineId}
    >
      {children}
    </AppShell>
  )
}

function App() {
  return (
    <BrowserRouter>
      {/* Above the routes, so the sidebar's tab and the screens that follow it
          (StartScreen) read one value that survives moving between screens. */}
      <SidebarTabProvider>
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
            path="/platforms"
            element={
              <AppShellRoute>
                <PlatformsScreenRoute />
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
      </SidebarTabProvider>
    </BrowserRouter>
  )
}

export default App
