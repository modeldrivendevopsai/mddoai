import { useEffect, useRef, useState } from "react"
import { getProviders } from "@/services/orchestrator.service"
import type { OrchestratorEvent, OrchestratorEventType, Provider } from "@/types/orchestrator"
import "./chat.css"

interface ChatColumnProps {
  events: OrchestratorEvent[]
  busy: boolean
  model: string | null
  onSend: (message: string) => void
  onModelChange: (model?: string) => void
  // True while viewing a past (non-current) run from the sidebar's history —
  // sending a message or changing the model would silently act on the live
  // run instead, not the one on screen, so both are disabled.
  readOnly?: boolean
}

// Matches the wireframe's real "Add a CI/CD platform" screen: a static
// explainer from "Orchestrator" is always the first thing in the chat,
// before any real event exists, not just an empty-state placeholder.
const INTRO_TEXT =
  "MDDOAI reads the documentation, then works through PSM → ATL → Acceleo → Generation/Test one stage at a time, showing you each result to approve or correct before moving on."

export function ChatColumn({ events, busy, model, onSend, onModelChange, readOnly = false }: ChatColumnProps) {
  const [value, setValue] = useState("")
  const [providers, setProviders] = useState<Provider[]>([])
  // Sending a message (and picking a model) is a real capability before a
  // run has started too: /message has no busy guard at all (unlike every
  // other mutating endpoint, see main.py's own docstring for why), and the
  // tool-calling layer can itself invoke start_pipeline from a message.
  // Only an in-flight call, or viewing read-only history, blocks input.
  const disabled = busy || readOnly
  const logRef = useRef<HTMLDivElement>(null)
  const lastCountRef = useRef(0)

  useEffect(() => {
    getProviders()
      .then(setProviders)
      .catch(() => setProviders([]))
  }, [])

  // useIntegration polls GET /events every 1.5s, which hands back a freshly
  // parsed array (a new reference) on every tick even when nothing actually
  // changed, so this can't just depend on `events` itself, that would
  // scroll-to-bottom every single tick, yanking you back down while trying
  // to read something further up. Only scroll when a real new event arrived.
  useEffect(() => {
    if (events.length !== lastCountRef.current) {
      logRef.current?.scrollTo({ top: logRef.current.scrollHeight })
      lastCountRef.current = events.length
    }
  }, [events])

  const handleSubmit = () => {
    const trimmed = value.trim()
    if (!trimmed) return
    onSend(trimmed)
    setValue("")
  }

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "var(--space-3)",
        height: "100%",
        padding: "var(--space-4)",
        // Wireframe's d1 screen fills both the chat panel and the form
        // panel with the same #FAF7FF, not a gray chat panel next to a
        // purple form panel. --brand-faint is the closest token match.
        background: "var(--brand-faint)",
        border: "1px solid var(--border-default)",
        borderRadius: "var(--radius-md)",
        boxSizing: "border-box",
      }}
    >
      <h2
        style={{
          fontFamily: "var(--font-display)",
          fontSize: "var(--text-md)",
          fontWeight: "var(--weight-bold)",
          color: "var(--text-strong)",
          margin: 0,
        }}
      >
        Chat
      </h2>

      <div
        ref={logRef}
        style={{ flex: 1, overflowY: "auto", display: "flex", flexDirection: "column", gap: "var(--space-2)" }}
      >
        <MessageLine event={{ type: "message", stage: null, text: INTRO_TEXT, timestamp: 0 }} />
        {events.map((event, i) =>
          event.type === "message" || event.type === "user_message" ? (
            <MessageLine key={i} event={event} />
          ) : (
            <EventChip key={i} event={event} />
          )
        )}
      </div>

      {/* Carried over from the old single-screen chat UI's own model picker,
          positioned the same way, right above the input. Unlike that one
          (which only ever affected the next message), this one changes the
          run's model for the rest of it, every subsequent stage run/retry/
          message, not just the next message, see useIntegration's
          changeModel. */}
      <div style={{ display: "flex", justifyContent: "flex-end" }}>
        <select
          value={model ?? "auto"}
          onChange={(e) => onModelChange(e.target.value === "auto" ? undefined : e.target.value)}
          disabled={readOnly}
          aria-label="Model"
          style={{
            border: "1px solid var(--border-default)",
            borderRadius: "var(--radius-sm)",
            padding: "var(--space-1) var(--space-2)",
            fontFamily: "var(--font-sans)",
            fontSize: "var(--text-2xs)",
            color: "var(--text-muted)",
            background: "var(--surface-card)",
          }}
        >
          <option value="auto">Auto</option>
          {providers
            .filter((p) => p.available)
            .map((p) => (
              <option key={p.name} value={p.name}>
                {p.name} ({p.tier})
              </option>
            ))}
        </select>
      </div>

      <div
        className="mddoai-chat-message-input"
        style={{
          background: "var(--surface-card)",
          border: "1px solid var(--border-strong)",
          borderRadius: "var(--radius-sm)",
          padding: "var(--space-2) var(--space-3)",
          display: "flex",
          alignItems: "center",
          gap: "var(--space-2)",
        }}
      >
        <textarea
          value={value}
          onChange={(e) => setValue(e.target.value)}
          onKeyDown={(e) => {
            if (e.key === "Enter" && !e.shiftKey) {
              e.preventDefault()
              handleSubmit()
            }
          }}
          placeholder="Message the Orchestrator.."
          rows={1}
          disabled={disabled}
          style={{
            flex: 1,
            resize: "none",
            border: "none",
            outline: "none",
            fontFamily: "var(--font-sans)",
            fontSize: "var(--text-sm)",
            background: "transparent",
            color: "var(--text-body)",
          }}
        />
        <button
          onClick={handleSubmit}
          disabled={disabled || !value.trim()}
          aria-label="Send"
          style={{
            width: 28,
            height: 28,
            borderRadius: "var(--radius-pill)",
            border: "none",
            background: disabled || !value.trim() ? "var(--surface-sunken)" : "var(--brand)",
            color: disabled || !value.trim() ? "var(--text-faint)" : "var(--on-brand)",
            cursor: disabled || !value.trim() ? "not-allowed" : "pointer",
            flexShrink: 0,
            fontSize: "var(--text-sm)",
          }}
        >
          ↑
        </button>
      </div>
    </div>
  )
}

function MessageLine({ event }: { event: OrchestratorEvent }) {
  const isUser = event.type === "user_message"
  const text = isUser ? String(event.data?.message ?? "") : (event.text ?? "")
  return (
    <div style={{ display: "flex", flexDirection: "column", gap: 2, padding: "var(--space-1) 0" }}>
      <span
        style={{
          display: "flex",
          alignItems: "center",
          gap: "var(--space-2)",
          fontFamily: "var(--font-sans)",
          fontSize: "var(--text-2xs)",
          fontWeight: "var(--weight-bold)",
          color: "var(--text-muted)",
        }}
      >
        {isUser ? "You" : "Orchestrator"}
        {event.stage ? ` · ${event.stage}` : ""}
        {/* Always-visible, not just a hover tooltip, which which model
            answered was easy to miss entirely. */}
        {!isUser && event.model && (
          <span
            style={{
              fontFamily: "var(--font-mono)",
              fontWeight: "var(--weight-regular)",
              color: "var(--text-faint)",
              background: "var(--surface-card)",
              border: "1px solid var(--border-subtle)",
              borderRadius: "var(--radius-sm)",
              padding: "0 4px",
            }}
          >
            {event.model}
          </span>
        )}
      </span>
      <span
        style={{
          fontFamily: "var(--font-sans)",
          fontSize: "var(--text-sm)",
          color: "var(--text-strong)",
          lineHeight: "var(--leading-normal)",
        }}
      >
        {text}
      </span>
    </div>
  )
}

// Distinct "activity" cards for the real structured events (call_started/
// call_completed/call_failed/review_approved/review_rejected), interspersed
// with the chat messages in the same timeline, rather than filtered out, so
// the log reads like Lovable's build-activity feed: prose replies plus
// visibly different, inspectable action/status entries, not just prose.
// Colors reuse mddoai-design-system's real StatusPill.jsx token
// combinations ("running"/"passed"/"failed") rather than inventing new
// ones; the card shape/size and the click-to-expand "Show details" affordance
// are our own, sized for a real event's full data (a click target, not just
// a hover tooltip), centered in the log like a system message rather than
// left-aligned like the chat bubbles around it.
const EVENT_STATUS: Record<OrchestratorEventType, { bg: string; fg: string; dot: string; label: string; pulse?: boolean }> = {
  call_started: { bg: "var(--warning-100)", fg: "var(--warning-700)", dot: "var(--warning-500)", label: "Started", pulse: true },
  call_completed: { bg: "var(--success-100)", fg: "var(--success-500)", dot: "var(--success-500)", label: "Completed" },
  call_failed: { bg: "var(--danger-100)", fg: "var(--danger-500)", dot: "var(--danger-500)", label: "Failed" },
  review_approved: { bg: "var(--success-100)", fg: "var(--success-500)", dot: "var(--success-500)", label: "Approved" },
  review_rejected: { bg: "var(--warning-100)", fg: "var(--warning-700)", dot: "var(--warning-500)", label: "Rejected" },
  // The two below are real, recorded backend facts, not just prose — same
  // "info" treatment, distinct from the
  // stage-lifecycle colors above.
  constraint_added: { bg: "var(--warning-100)", fg: "var(--warning-700)", dot: "var(--warning-500)", label: "Constraint added" },
  documentation_extended: { bg: "var(--success-100)", fg: "var(--success-500)", dot: "var(--success-500)", label: "Page added to docs" },
  // A dispatched tool call's own real arguments/result (see assistant.py's
  // send_message()) — distinct from the "message" turn right after it,
  // which is just the AI's own prose summary of what it just did.
  tool_called: { bg: "var(--brand-faint)", fg: "var(--text-muted)", dot: "var(--text-faint)", label: "Tool called" },
  message: { bg: "", fg: "", dot: "", label: "" },
  user_message: { bg: "", fg: "", dot: "", label: "" },
}

function eventSummary(event: OrchestratorEvent): string | undefined {
  const data = event.data
  if (!data) return undefined
  if (event.type === "call_failed") return typeof data.error === "string" ? data.error : undefined
  if (event.type === "call_completed") return data.valid === false ? "output didn't pass validation" : undefined
  if (event.type === "review_rejected") return typeof data.correction === "string" ? data.correction : undefined
  if (event.type === "constraint_added") return typeof data.constraint === "string" ? data.constraint : undefined
  if (event.type === "documentation_extended") return typeof data.url === "string" ? data.url : undefined
  if (event.type === "tool_called") return typeof data.tool === "string" ? data.tool : undefined
  return undefined
}

function EventChip({ event }: { event: OrchestratorEvent }) {
  const [expanded, setExpanded] = useState(false)
  const s = EVENT_STATUS[event.type]
  const summary = eventSummary(event)
  const hasDetails = Boolean(event.data && Object.keys(event.data).length > 0)

  return (
    <div style={{ display: "flex", justifyContent: "center", padding: "var(--space-2) 0" }}>
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          gap: "var(--space-2)",
          width: "min(420px, 90%)",
          padding: "var(--space-3) var(--space-4)",
          borderRadius: "var(--radius-lg)",
          background: s.bg,
          boxShadow: "var(--shadow-xs)",
        }}
      >
        <div style={{ display: "flex", alignItems: "center", gap: "var(--space-2)" }}>
          <span
            style={{
              width: 9,
              height: 9,
              borderRadius: "50%",
              background: s.dot,
              flexShrink: 0,
              animation: s.pulse ? "mddoai-pulse 1.6s var(--ease-out) infinite" : "none",
            }}
          />
          <span
            style={{
              fontFamily: "var(--font-mono)",
              fontSize: "var(--text-xs)",
              fontWeight: "var(--weight-bold)",
              letterSpacing: "0.01em",
              color: s.fg,
            }}
          >
            {s.label}
            {event.stage ? `: ${event.stage}` : ""}
          </span>
        </div>

        {summary && (
          <span style={{ fontFamily: "var(--font-sans)", fontSize: "var(--text-xs)", color: "var(--text-muted)" }}>
            {summary}
          </span>
        )}

        {hasDetails && (
          <div>
            <button
              onClick={() => setExpanded((v) => !v)}
              style={{
                display: "inline-flex",
                alignItems: "center",
                gap: 4,
                background: "none",
                border: "none",
                padding: 0,
                cursor: "pointer",
                fontFamily: "var(--font-sans)",
                fontSize: "var(--text-2xs)",
                fontWeight: "var(--weight-bold)",
                color: s.fg,
              }}
            >
              <span
                style={{
                  display: "inline-block",
                  transition: "transform var(--duration-fast) var(--ease-out)",
                  transform: expanded ? "rotate(90deg)" : "none",
                }}
              >
                ›
              </span>
              {expanded ? "Hide details" : "Show details"}
            </button>
            {expanded && (
              <pre
                style={{
                  margin: "var(--space-2) 0 0",
                  padding: "var(--space-2) var(--space-3)",
                  background: "var(--surface-code)",
                  color: "var(--code-text)",
                  borderRadius: "var(--radius-sm)",
                  fontFamily: "var(--font-mono)",
                  fontSize: "var(--text-2xs)",
                  lineHeight: 1.6,
                  whiteSpace: "pre-wrap",
                  wordBreak: "break-word",
                  textAlign: "left",
                }}
              >
                {JSON.stringify(event.data, null, 2)}
              </pre>
            )}
          </div>
        )}
      </div>
    </div>
  )
}
