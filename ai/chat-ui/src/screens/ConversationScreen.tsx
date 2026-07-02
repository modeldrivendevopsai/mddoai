import { useEffect, useState } from "react"
import { ArrowUp, Menu, Moon, Sun } from "lucide-react"
import { Button } from "@/components/ui/button"
import {
  ChatContainerRoot,
  ChatContainerContent,
  ChatContainerScrollAnchor,
} from "@/components/ui/chat-container"
import { MessageContent } from "@/components/ui/message"
import {
  PromptInput,
  PromptInputTextarea,
  PromptInputActions,
  PromptInputAction,
} from "@/components/ui/prompt-input"
import { cn } from "@/lib/utils"
import { sendMessage } from "@/services/orchestratorService"
import type { Message } from "@/types"

const STORAGE_KEY = "mddoai-conversation"

const PLATFORMS = [
  { name: "GitLab CI" },
  { name: "Bamboo" },
  { name: "Azure DevOps" },
]

interface StoredState {
  messages: Message[]
  isComplete: boolean
}

function loadStoredState(): StoredState {
  const raw = localStorage.getItem(STORAGE_KEY)
  if (!raw) return { messages: [], isComplete: false }
  try {
    return JSON.parse(raw) as StoredState
  } catch {
    return { messages: [], isComplete: false }
  }
}

function formatTimestamp(timestamp: number) {
  return new Date(timestamp).toLocaleTimeString([], {
    hour: "2-digit",
    minute: "2-digit",
  })
}

export default function ConversationScreen() {
  const [messages, setMessages] = useState<Message[]>(
    () => loadStoredState().messages
  )
  const [isComplete, setIsComplete] = useState(
    () => loadStoredState().isComplete
  )
  const [value, setValue] = useState("")
  const [isLoading, setIsLoading] = useState(false)
  const [isPanelOpen, setIsPanelOpen] = useState(false)
  const [isDark, setIsDark] = useState(true)

  useEffect(() => {
    localStorage.setItem(STORAGE_KEY, JSON.stringify({ messages, isComplete }))
  }, [messages, isComplete])

  const handleNewConversation = () => {
    localStorage.removeItem(STORAGE_KEY)
    setMessages([])
    setIsComplete(false)
  }

  const handleThemeToggle = () => {
    const next = !isDark
    setIsDark(next)
    if (next) {
      document.documentElement.removeAttribute("data-theme")
    } else {
      document.documentElement.setAttribute("data-theme", "light")
    }
  }

  const handleSubmit = async () => {
    const content = value.trim()
    if (!content || isLoading || isComplete) return

    const userMessage: Message = {
      id: crypto.randomUUID(),
      role: "user",
      content,
      timestamp: Date.now(),
    }
    const updatedMessages = [...messages, userMessage]
    setMessages(updatedMessages)
    setValue("")
    setIsLoading(true)

    const response = await sendMessage(updatedMessages)
    const assistantMessage: Message = {
      id: crypto.randomUUID(),
      role: "assistant",
      content: response.message,
      timestamp: Date.now(),
    }
    setMessages((prev) => [...prev, assistantMessage])
    setIsComplete(response.status === "complete")
    setIsLoading(false)
  }

  return (
    <div className="flex h-screen flex-col bg-background">
      {/* Sliding history panel */}
      <div
        style={{
          width: isPanelOpen ? 200 : 0,
          overflow: "hidden",
          transition: "width 0.2s ease",
          position: "fixed",
          top: 0,
          left: 0,
          bottom: 0,
          zIndex: 40,
          background: "var(--surface)",
          borderRight: "1px solid var(--border)",
        }}
      >
        <div style={{ width: 200, height: "100%", padding: "16px 12px", overflowY: "auto" }}>
          <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between", marginBottom: 10 }}>
            <p style={{ fontSize: 11, fontWeight: 600, color: "var(--muted)", letterSpacing: "0.07em", margin: 0 }}>
              Conversations
            </p>
            <button
              onClick={() => setIsPanelOpen(false)}
              aria-label="Close panel"
              style={{ color: "var(--muted)", background: "none", border: "none", cursor: "pointer", display: "flex", alignItems: "center", padding: 2, borderRadius: 3, fontSize: 16, lineHeight: 1 }}
            >
              ×
            </button>
          </div>
          {messages.length > 0 ? (
            <div
              style={{
                fontSize: 13,
                color: "var(--text)",
                padding: "7px 8px",
                borderRadius: 4,
                background: "var(--bg)",
                border: "1px solid var(--border)",
                cursor: "default",
              }}
            >
              <div style={{ overflow: "hidden", textOverflow: "ellipsis", whiteSpace: "nowrap" }}>
                {messages[0].content}
              </div>
              <div style={{ fontSize: 11, color: "var(--muted)", marginTop: 3 }}>
                {messages.length} message{messages.length !== 1 ? "s" : ""}
              </div>
            </div>
          ) : (
            <p style={{ fontSize: 13, color: "var(--muted)", margin: 0 }}>No conversations</p>
          )}

          <p style={{ fontSize: 11, fontWeight: 600, color: "var(--muted)", letterSpacing: "0.07em", margin: "24px 0 10px" }}>
            Platforms
          </p>
          {PLATFORMS.map((p) => (
            <div
              key={p.name}
              style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "space-between",
                fontSize: 13,
                color: "var(--text)",
                padding: "5px 0",
              }}
            >
              <span>{p.name}</span>
              <span style={{ fontSize: 11, color: "var(--accent)" }}>ready</span>
            </div>
          ))}
        </div>
      </div>

      {/* Header */}
      <header
        className="flex h-12 shrink-0 items-center border-b border-border px-4"
        style={{ gap: 10 }}
      >
        <button
          onClick={() => setIsPanelOpen((o) => !o)}
          aria-label="Toggle history"
          style={{
            color: "var(--muted)",
            background: "none",
            border: "none",
            cursor: "pointer",
            display: "flex",
            alignItems: "center",
            padding: 4,
            borderRadius: 4,
          }}
        >
          <Menu size={16} />
        </button>
        <h1 className="text-sm font-medium text-foreground">MDDOAI</h1>
        <div className="ml-auto flex items-center" style={{ gap: 8 }}>
          <button
            onClick={handleThemeToggle}
            aria-label="Toggle theme"
            style={{
              color: "var(--muted)",
              background: "none",
              border: "none",
              cursor: "pointer",
              display: "flex",
              alignItems: "center",
              padding: 4,
              borderRadius: 4,
            }}
          >
            {isDark ? <Sun size={16} /> : <Moon size={16} />}
          </button>
          <button
            onClick={handleNewConversation}
            style={{
              color: "var(--muted)",
              background: "none",
              border: "none",
              cursor: "pointer",
              fontSize: 13,
              padding: "4px 8px",
              borderRadius: 4,
            }}
          >
            New conversation
          </button>
        </div>
      </header>

      {/* Messages area */}
      <div className="flex flex-1 flex-col overflow-hidden">
        {messages.length === 0 ? (
          <div
            className="flex flex-1 flex-col items-center justify-center text-center"
            style={{ gap: 6, padding: "0 16px" }}
          >
            <p style={{ fontSize: 12, letterSpacing: "0.08em", textTransform: "uppercase", color: "var(--muted)", margin: 0, fontWeight: 500 }}>
              MDDOAI
            </p>
            <p style={{ fontSize: 13, color: "var(--muted)", margin: 0 }}>
              Generate CI/CD pipelines from your architecture models
            </p>
            <p style={{ fontSize: 12, color: "var(--muted)", margin: 0 }}>
              Describe your integration goal to get started
            </p>
          </div>
        ) : (
          <ChatContainerRoot className="flex-1">
            <ChatContainerContent
              className="mx-auto w-full max-w-2xl px-4 py-6"
              style={{ gap: 12 }}
            >
              {messages.map((message) => (
                <div
                  key={message.id}
                  className={cn(
                    "group flex flex-col",
                    message.role === "user" ? "items-end" : "items-start"
                  )}
                >
                  {message.role === "user" ? (
                    <div
                      style={{
                        background: "var(--user-msg)",
                        border: "1px solid var(--border)",
                        borderRadius: 4,
                        padding: "9px 12px",
                        maxWidth: "70%",
                        fontSize: 14,
                        color: "var(--text)",
                        lineHeight: 1.6,
                        wordBreak: "break-word",
                      }}
                    >
                      {message.content}
                    </div>
                  ) : (
                    <MessageContent
                      markdown
                      className="max-w-[70%] bg-transparent p-0 rounded-none text-sm"
                    >
                      {message.content}
                    </MessageContent>
                  )}
                  <span
                    className="mt-1 text-xs opacity-0 transition-opacity group-hover:opacity-100"
                    style={{ color: "var(--muted)" }}
                  >
                    {formatTimestamp(message.timestamp)}
                  </span>
                </div>
              ))}

              {isComplete && (
                <div style={{ display: "flex", justifyContent: "center", marginTop: 4 }}>
                  <button
                    onClick={handleNewConversation}
                    style={{
                      color: "var(--muted)",
                      fontSize: 12,
                      background: "none",
                      border: "none",
                      cursor: "pointer",
                      textDecoration: "none",
                      padding: 0,
                    }}
                    onMouseEnter={(e) => {
                      e.currentTarget.style.textDecoration = "underline"
                    }}
                    onMouseLeave={(e) => {
                      e.currentTarget.style.textDecoration = "none"
                    }}
                  >
                    Start a new conversation →
                  </button>
                </div>
              )}

              <ChatContainerScrollAnchor />
            </ChatContainerContent>
          </ChatContainerRoot>
        )}
      </div>

      {/* Input bar */}
      <div className="border-t border-border p-4">
        <PromptInput
          value={value}
          onValueChange={setValue}
          onSubmit={handleSubmit}
          isLoading={isLoading}
          disabled={isComplete}
          className="mx-auto w-full max-w-2xl rounded-[4px]"
        >
          <PromptInputTextarea placeholder="Describe what you want to build..." />
          <PromptInputActions className="justify-end">
            <PromptInputAction tooltip="Send">
              <Button
                size="icon"
                disabled={!value.trim() || isLoading || isComplete}
                onClick={handleSubmit}
              >
                <ArrowUp />
              </Button>
            </PromptInputAction>
          </PromptInputActions>
        </PromptInput>
      </div>
    </div>
  )
}
