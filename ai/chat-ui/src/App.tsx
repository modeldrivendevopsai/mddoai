import { BrowserRouter, Routes, Route, useNavigate } from "react-router-dom"
import ConversationScreen from "@/screens/ConversationScreen"
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

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<ConversationScreen />} />
        <Route
          path="/start"
          element={
            <AppShell>
              <StartScreenRoute />
            </AppShell>
          }
        />
        <Route
          path="/platforms/new"
          element={
            <AppShell>
              <PlatformIntegrationScreen />
            </AppShell>
          }
        />
      </Routes>
    </BrowserRouter>
  )
}

export default App
