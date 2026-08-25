import { useState } from "react"
import { Button } from "design-system"
import { TextField, NumberField, TextAreaField } from "./FormField"
import type { DocsOptions } from "orchestrator-types"

interface DocsStartFormProps {
  onStart: (platformName: string, documentationUrl: string, docsOptions?: DocsOptions) => void
}

function parseExcludeUrls(text: string): string[] {
  return text
    .split("\n")
    .map((line) => line.trim())
    .filter(Boolean)
}

// Mock skips the real crawl entirely on the backend, so requiring a real
// platform name/URL first is pointless busywork when
// you're just testing the rest of the integration. Used only as a fallback
// for whichever field is actually left blank (see handleSubmit) — a field
// the human did fill in is never overwritten, even with mock checked.
function randomMockPlatformName(): string {
  return `Mock Platform ${Math.random().toString(36).slice(2, 8)}`
}
const MOCK_DOCUMENTATION_URL = "https://example.com/mock-docs"

// Shown before any run exists (IntegrationScreen's !started branch) — not a
// generic "start a run" form, almost every field here is the docs stage's
// own real input: documentation URL + retrieval's own real retry/steer
// levers (hint, exclude URLs, max pages/depth, force refresh, mock) as an
// Advanced section, sent to orchestrator's own start-pipeline request
// alongside them. The one field that ISN'T docs-specific is platform name
// (platform_description) — docs itself never reads it, but every later
// stage (PIM/PSM/...) falls back to
// it, so it's collected once here rather than re-asked per stage. Filed
// next to DocsStagePanel.tsx (its output-review counterpart), not merged
// into it: input-collection and output-review are different enough shapes
// that one function handling both would be doing two jobs.
export function DocsStartForm({ onStart }: DocsStartFormProps) {
  const [platformName, setPlatformName] = useState("")
  const [documentationUrl, setDocumentationUrl] = useState("")
  const [maxPages, setMaxPages] = useState("")
  const [maxDepth, setMaxDepth] = useState("")
  const [hint, setHint] = useState("")
  const [excludeUrlsText, setExcludeUrlsText] = useState("")
  const [forceRefresh, setForceRefresh] = useState(false)
  const [mock, setMock] = useState(false)
  // Mock doesn't need real input (see randomMockPlatformName() above) —
  // only require the fields to be filled when mock is off.
  const canSubmit = mock || (platformName.trim() && documentationUrl.trim())

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    if (!canSubmit) return
    // Falls back to a random mock value only when the field is actually
    // blank — canSubmit above already guarantees that can only happen with
    // mock checked, so this never overwrites a real value the human typed.
    const finalPlatformName = platformName.trim() || randomMockPlatformName()
    const finalDocumentationUrl = documentationUrl.trim() || MOCK_DOCUMENTATION_URL
    const docsOptions: DocsOptions = {}
    if (maxPages) docsOptions.max_pages = Number(maxPages)
    if (maxDepth) docsOptions.max_depth = Number(maxDepth)
    if (hint.trim()) docsOptions.hint = hint.trim()
    const excludeUrls = parseExcludeUrls(excludeUrlsText)
    if (excludeUrls.length) docsOptions.exclude_urls = excludeUrls
    if (forceRefresh) docsOptions.force_refresh = true
    if (mock) docsOptions.mock = true
    onStart(finalPlatformName, finalDocumentationUrl, docsOptions)
  }

  return (
    <form
      onSubmit={handleSubmit}
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "var(--space-4)",
        height: "100%",
        padding: "var(--space-4)",
        boxSizing: "border-box",
        overflowY: "auto",
        background: "var(--brand-faint)",
        border: "1px solid var(--border-default)",
        borderRadius: "var(--radius-md)",
      }}
    >
      <TextField
        label="Platform name"
        value={platformName}
        placeholder="TeamCity v0.9"
        onChange={setPlatformName}
      />
      <TextField
        label="Documentation URL"
        value={documentationUrl}
        placeholder="https://www.jetbrains.com/help/teamcity/"
        onChange={setDocumentationUrl}
      />

      <details>
        <summary
          style={{
            fontFamily: "var(--font-sans)",
            fontSize: 13,
            fontWeight: 600,
            cursor: "pointer",
            color: "var(--text-muted)",
          }}
        >
          Advanced
        </summary>
        <div style={{ display: "flex", flexDirection: "column", gap: 12, marginTop: 12 }}>
          <div style={{ display: "flex", gap: 12 }}>
            <div style={{ flex: 1 }}>
              <NumberField label="Max pages" value={maxPages} placeholder="15 (default)" onChange={setMaxPages} />
            </div>
            <div style={{ flex: 1 }}>
              <NumberField label="Max depth" value={maxDepth} placeholder="5 (default)" onChange={setMaxDepth} />
            </div>
          </div>
          <TextField
            label="Hint (steer the crawl, or a retry)"
            value={hint}
            placeholder="e.g. prioritize pages about triggers and secrets"
            onChange={setHint}
          />
          <TextAreaField
            label="Exclude URLs (one per line)"
            value={excludeUrlsText}
            placeholder="https://docs.example.com/ci/old-page/"
            onChange={setExcludeUrlsText}
          />
          <label
            style={{
              display: "flex",
              alignItems: "center",
              gap: 8,
              fontFamily: "var(--font-sans)",
              fontSize: 13,
              color: "var(--text-body)",
            }}
          >
            <input type="checkbox" checked={forceRefresh} onChange={(e) => setForceRefresh(e.target.checked)} />
            Force refresh (bypass cache, fetch everything fresh)
          </label>
          <label
            style={{
              display: "flex",
              alignItems: "center",
              gap: 8,
              fontFamily: "var(--font-sans)",
              fontSize: 13,
              color: "var(--text-body)",
            }}
          >
            <input type="checkbox" checked={mock} onChange={(e) => setMock(e.target.checked)} />
            Mock (skip the real crawl, for faster local testing)
          </label>
        </div>
      </details>

      {/* No flex:1 spacer here: bottom-anchoring the button that way only
          holds up while all the content fits in the container's fixed
          height. Advanced's extra fields push past that, overflowY kicks
          in, and the spacer collapses to nothing — the button loses its
          breathing room. Flowing normally with the same gap as everything
          else keeps it consistent whether Advanced is open or not.
          flexShrink:0 matters once Advanced's fields push the form's total
          content past its fixed height: without it, the flex column shrinks
          every child (including this button) to try to fit before finally
          letting overflowY scroll — the same mechanism that shrank Button's
          own icon (see design-system/components/Button.tsx). */}
      <Button
        type="submit"
        variant="primary"
        size="md"
        disabled={!canSubmit}
        style={{ alignSelf: "flex-start", flexShrink: 0 }}
      >
        Start integration
      </Button>
    </form>
  )
}
