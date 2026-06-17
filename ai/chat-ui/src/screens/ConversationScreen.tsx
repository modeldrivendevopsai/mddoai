import { useEffect, useState } from "react"
import { ArrowUp } from "lucide-react"
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

  useEffect(() => {
    localStorage.setItem(STORAGE_KEY, JSON.stringify({ messages, isComplete }))
  }, [messages, isComplete])

  const handleSubmit = async () => {
    const content = value.trim()
    if (!content || isLoading || isComplete) return

    const userMessage: Message = {
      id: crypto.randomUUID(),
      role: "user",
      content,
      timestamp: Date.now(),
    }
    setMessages((prev) => [...prev, userMessage])
    setValue("")
    setIsLoading(true)

    const response = await sendMessage(content)
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
      <header className="flex h-12 shrink-0 items-center border-b border-border px-4">
        <h1 className="text-sm font-medium text-foreground">MDDOAI</h1>
      </header>

      <ChatContainerRoot className="flex-1">
        <ChatContainerContent className="mx-auto w-full max-w-2xl gap-4 px-4 py-6">
          {messages.map((message) => (
            <div
              key={message.id}
              className={cn(
                "group flex flex-col gap-1",
                message.role === "user" ? "items-end" : "items-start"
              )}
            >
              <MessageContent
                markdown
                className={cn(
                  "max-w-[80%] rounded-[4px] p-2 text-sm",
                  message.role === "user"
                    ? "bg-[var(--user-msg)] text-foreground"
                    : "bg-transparent p-0 text-foreground"
                )}
              >
                {message.content}
              </MessageContent>
              <span className="text-xs text-muted-foreground opacity-0 transition-opacity group-hover:opacity-100">
                {formatTimestamp(message.timestamp)}
              </span>
            </div>
          ))}
          <ChatContainerScrollAnchor />
        </ChatContainerContent>
      </ChatContainerRoot>

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
