import type { ReactNode } from "react"
import { BrowserRouter, Routes, Route, useLocation, useNavigate, useParams, useSearchParams } from "react-router-dom"
import GenerateScreen from "@/screens/GenerateScreen"
import IntegrationScreen from "@/screens/IntegrationScreen"
import PipelinesScreen from "@/screens/PipelinesScreen"
import PlatformScreen from "@/screens/PlatformScreen"
import PlatformsScreen from "@/screens/PlatformsScreen"
import { AppShell } from "@/layout/AppShell"
import { SidebarTabProvider } from "@/layout/SidebarTabProvider"
import { StartScreen } from "@/screens/StartScreen"
import {
  NEW_PLATFORM_PATH,
  PLATFORM_QUERY,
  ROUTES,
  integrationRunPath,
  pipelinePath,
  platformPath,
} from "@/config/routes.config"

// Where each StartScreen card goes. Both open on the list of what already
// exists, so someone coming back starts from their work: "Generate a CI/CD
// pipeline" is its own mode, with its own backend, and opens on the pipelines
// already generated; "Add or update a CI/CD platform" opens on the platforms
// already supported. The integration screen (its docs stage IS the add/update
// flow: it fetches the platform's documentation as the first of its stages) is
// behind the "+ New platform" button.
const START_OPTION_ROUTES: Record<string, string> = {
  "generate-pipeline": ROUTES.pipelines,
  "add-update-platform": ROUTES.platforms,
}

function StartScreenRoute() {
  const navigate = useNavigate()
  return (
    <StartScreen
      onSelectOption={(optionId) => navigate(START_OPTION_ROUTES[optionId] ?? ROUTES.platforms)}
      onOpenPipeline={(pipelineId) => navigate(pipelinePath(pipelineId))}
      onOpenPlatform={(platformId) => navigate(platformPath(platformId))}
      onOpenRun={(runId) => navigate(integrationRunPath(runId))}
    />
  )
}

function PlatformsScreenRoute() {
  const navigate = useNavigate()
  return (
    <PlatformsScreen
      onBack={() => navigate(ROUTES.start)}
      onNew={() => navigate(NEW_PLATFORM_PATH)}
      onOpen={(platformId) => navigate(platformPath(platformId))}
      onOpenRun={(runId) => navigate(integrationRunPath(runId))}
    />
  )
}

// One platform, at the version the address names (its latest when it names
// none). Its header is the way back to the platforms list. Picking another
// version replaces the entry, so Back still goes to the list, and the run that
// made it opens on the integration screen, like any past run.
function PlatformScreenRoute() {
  const navigate = useNavigate()
  const { platformId = "" } = useParams()
  const [params] = useSearchParams()
  return (
    <PlatformScreen
      platformId={platformId}
      version={params.get(PLATFORM_QUERY.version) ?? undefined}
      onBack={() => navigate(ROUTES.platforms)}
      onVersionChange={(version) => navigate(platformPath(platformId, version), { replace: true })}
      onShowLatest={() => navigate(platformPath(platformId), { replace: true })}
      onOpenRun={(runId) => navigate(integrationRunPath(runId))}
    />
  )
}

// Its header is the way back to the list of supported platforms, where the
// screen is opened from.
function IntegrationScreenRoute() {
  const navigate = useNavigate()
  return <IntegrationScreen onBack={() => navigate(ROUTES.platforms)} />
}

function PipelinesScreenRoute() {
  const navigate = useNavigate()
  return (
    <PipelinesScreen
      onBack={() => navigate(ROUTES.start)}
      onNew={() => navigate(ROUTES.newPipeline)}
      onOpen={(pipelineId) => navigate(pipelinePath(pipelineId))}
    />
  )
}

// Keyed by the navigation entry: "New pipeline" while already on
// the new-pipeline route navigates to the same URL, and without a fresh key
// React would keep the screen mounted, still holding the previous file and
// results. The new-pipeline route has no id; the saved-pipeline route brings a
// saved pipeline back.
function GenerateScreenRoute() {
  const location = useLocation()
  const navigate = useNavigate()
  const { pipelineId } = useParams()
  return <GenerateScreen key={location.key} pipelineId={pipelineId} onBack={() => navigate(ROUTES.pipelines)} />
}

// Shared by every route's AppShell so the sidebar behaves the same
// everywhere. "New pipeline" opens a blank generate screen. Every other action
// ("Add/copy a platform", and session rows of either type) goes to the
// integration screen; "copy-platform" has no real backend capability yet (no
// endpoint to copy an existing platform's support), so it lands there rather
// than pretending to do something it can't.
//
// "add-platform" specifically means "start over", so it goes to the blank-run
// path and not the bare integration route: going to the path you are already
// on does nothing in the router, and the old run would stay.
const SIDEBAR_ROUTES: Record<string, string> = {
  "new-pipeline": ROUTES.newPipeline,
  "add-platform": NEW_PLATFORM_PATH,
}

function useSidebarNavigation() {
  const navigate = useNavigate()
  return (actionId: string) => navigate(SIDEBAR_ROUTES[actionId] ?? ROUTES.integration)
}

function AppShellRoute({ children }: { children: ReactNode }) {
  const navigate = useNavigate()
  // Set only on the saved-pipeline route, the screen of a saved pipeline.
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
            path={ROUTES.start}
            element={
              <AppShellRoute>
                <StartScreenRoute />
              </AppShellRoute>
            }
          />
          <Route
            path={ROUTES.pipelines}
            element={
              <AppShellRoute>
                <PipelinesScreenRoute />
              </AppShellRoute>
            }
          />
          <Route
            path={ROUTES.newPipeline}
            element={
              <AppShellRoute>
                <GenerateScreenRoute />
              </AppShellRoute>
            }
          />
          <Route
            path={`${ROUTES.pipelines}/:pipelineId`}
            element={
              <AppShellRoute>
                <GenerateScreenRoute />
              </AppShellRoute>
            }
          />
          <Route
            path={ROUTES.platforms}
            element={
              <AppShellRoute>
                <PlatformsScreenRoute />
              </AppShellRoute>
            }
          />
          <Route
            path={`${ROUTES.platforms}/:platformId`}
            element={
              <AppShellRoute>
                <PlatformScreenRoute />
              </AppShellRoute>
            }
          />
          <Route
            path={ROUTES.integration}
            element={
              <AppShellRoute>
                <IntegrationScreenRoute />
              </AppShellRoute>
            }
          />
        </Routes>
      </SidebarTabProvider>
    </BrowserRouter>
  )
}

export default App
