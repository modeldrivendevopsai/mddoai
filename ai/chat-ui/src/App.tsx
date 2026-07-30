import { BrowserRouter, Routes, Route } from "react-router-dom"
import ConversationScreen from "@/screens/ConversationScreen"
import { AppShell } from "@/layout/AppShell"
import { StartScreen } from "@/pages/StartScreen"

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<ConversationScreen />} />
        <Route
          path="/start"
          element={
            <AppShell>
              <StartScreen />
            </AppShell>
          }
        />
      </Routes>
    </BrowserRouter>
  )
}

export default App
