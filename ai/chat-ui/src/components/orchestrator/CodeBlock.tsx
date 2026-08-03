import { useState } from "react"

interface CodeBlockProps {
  code: string
  title?: string
  lang?: string
}

// Partial port of
// mddoai-design-system/project/components/surfaces/CodeBlock.jsx: the dark
// terminal surface, title bar, and copy affordance. Skips the source's
// `lines`-with-`kind` per-line syntax coloring variant, nothing here needs
// per-line prompt/comment/flag highlighting, a stage's output is one plain
// block of text.
export function CodeBlock({ code, title, lang = "text" }: CodeBlockProps) {
  const [copied, setCopied] = useState(false)

  const copy = () => {
    void navigator.clipboard.writeText(code)
    setCopied(true)
    setTimeout(() => setCopied(false), 1400)
  }

  return (
    <div
      style={{
        background: "var(--surface-code)",
        border: "1px solid rgba(255,255,255,0.07)",
        borderRadius: "var(--radius-md)",
        overflow: "hidden",
        fontFamily: "var(--font-mono)",
        display: "flex",
        flexDirection: "column",
        // The source component has no layout opinion (it's used in static
        // docs pages); this panel needs it to fill the remaining vertical
        // space of its flex-column parent.
        flex: "1 1 auto",
        minHeight: 120,
      }}
    >
      <div
        style={{
          display: "flex",
          alignItems: "center",
          justifyContent: "space-between",
          padding: "8px 12px 8px 14px",
          borderBottom: "1px solid rgba(255,255,255,0.07)",
          flexShrink: 0,
        }}
      >
        <div style={{ display: "flex", alignItems: "center", gap: 8 }}>
          <span style={{ display: "flex", gap: 6 }}>
            <span style={{ width: 10, height: 10, borderRadius: "50%", background: "#46435a" }} />
            <span style={{ width: 10, height: 10, borderRadius: "50%", background: "#46435a" }} />
            <span style={{ width: 10, height: 10, borderRadius: "50%", background: "#46435a" }} />
          </span>
          {title ? (
            <span style={{ fontSize: 12, color: "#9b98b5", marginLeft: 4 }}>{title}</span>
          ) : (
            <span style={{ fontSize: 11, color: "#6f6c86", marginLeft: 4 }}>{lang}</span>
          )}
        </div>
        <button
          onClick={copy}
          disabled={!code}
          style={{
            appearance: "none",
            background: copied ? "rgba(127,214,168,0.14)" : "rgba(255,255,255,0.06)",
            border: "1px solid rgba(255,255,255,0.09)",
            color: copied ? "#7fd6a8" : "#b9b6d0",
            fontFamily: "var(--font-mono)",
            fontSize: 11,
            padding: "3px 9px",
            borderRadius: "var(--radius-sm)",
            cursor: code ? "pointer" : "not-allowed",
            transition: "all var(--duration-fast) var(--ease-out)",
          }}
        >
          {copied ? "copied" : "copy"}
        </button>
      </div>
      {/* Real spec uses `whiteSpace: pre` + horizontal scroll, tuned for
          actual source code. Stage output is often unbroken LLM prose, so
          this wraps instead, horizontal-scroll-only prose is unreadable. */}
      <pre
        style={{
          margin: 0,
          padding: "14px 16px",
          overflow: "auto",
          fontSize: 13,
          lineHeight: 1.75,
          color: "#d6d3e8",
          whiteSpace: "pre-wrap",
          wordBreak: "break-word",
          flex: 1,
          minHeight: 0,
        }}
      >
        {code}
      </pre>
    </div>
  )
}
