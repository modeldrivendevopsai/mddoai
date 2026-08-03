import { useEffect, useRef, useState } from "react"
import "@fontsource-variable/space-grotesk"
import "@fontsource-variable/ibm-plex-sans"
import "@fontsource/ibm-plex-mono"
import { CodeBlockCode } from "@/components/ui/code-block"
import { OrchestratorPanel } from "@/components/platform-integration/OrchestratorPanel"
import { StagePipeline } from "@/components/platform-integration/StagePipeline"
import { BrandButton } from "@/components/platform-integration/BrandButton"
import { TextField, NumberField, TextAreaField } from "@/components/platform-integration/FormField"
import { brandTokenStyle } from "@/components/platform-integration/brandTokens"
import { fetchDocumentation } from "@/services/retrievalService"
import type { RetrievalFetchResult } from "@/services/retrievalService"
import { getProviders } from "@/services/providersService"
import type { Provider } from "@/services/providersService"
import type {
  DocsStepState,
  OrchestratorNote,
  PipelineStage,
} from "@/components/platform-integration/types"

const PIPELINE_LABELS: { key: PipelineStage["key"]; label: string }[] = [
  { key: "docs", label: "Docs" },
  { key: "pim", label: "PIM" },
  { key: "psm", label: "PSM" },
  { key: "atl", label: "ATL" },
  { key: "acceleo", label: "Acceleo" },
  { key: "generation", label: "Generation + Test" },
]

const DOCS_STATUS_BY_STEP: Record<DocsStepState, PipelineStage["status"]> = {
  input: "idle",
  starting: "active",
  reviewing: "reviewing",
  done: "done",
  error: "error",
}

const INTRO_NOTE: OrchestratorNote = {
  id: "intro",
  text: "MDDOAI reads the documentation, then works through PSM → ATL → Acceleo → Generation/Test one stage at a time, showing you each result to approve or correct before moving on.",
}

// retrieval fetches rendered web pages, not PDFs (see ai/retrieval/README.md's
// "Known limitations") — so only the URL field is wired to the real backend.
const MAX_PREVIEW_LENGTH = 6000

function combineMarkdown(pages: RetrievalFetchResult["pages"]): string {
  const combined = pages
    .filter((p) => p.success)
    .map((p) => p.markdown)
    .join("\n\n---\n\n")
  return combined.length > MAX_PREVIEW_LENGTH
    ? combined.slice(0, MAX_PREVIEW_LENGTH) + "\n\n… (truncated for preview)"
    : combined
}

function parseExcludeUrls(text: string): string[] {
  return text
    .split("\n")
    .map((line) => line.trim())
    .filter(Boolean)
}

export default function PlatformIntegrationScreen({
  onExit,
  onApprove,
}: {
  onExit?: () => void
  // Called once the fetched documentation is approved, with the platform name and
  // combined markdown — whatever screen owns the next (PIM/PSM…) stage decides what
  // to do with it, that's outside this screen's scope (the retrieval agent's job
  // ends at fetching and reviewing documentation).
  onApprove?: (docs: { platformName: string; markdown: string }) => void
}) {
  const [step, setStep] = useState<DocsStepState>("input")
  const [platformName, setPlatformName] = useState("")
  const [docUrl, setDocUrl] = useState("")
  const [fileName, setFileName] = useState<string | null>(null)
  const [result, setResult] = useState<RetrievalFetchResult | null>(null)
  const [error, setError] = useState<string | null>(null)
  const fileInputRef = useRef<HTMLInputElement>(null)

  const [providers, setProviders] = useState<Provider[]>([])
  const [selectedModel, setSelectedModel] = useState("auto")

  // Advanced retrieval controls — retrieval's real POST /fetch supports all of
  // these (see ai/retrieval/README.md); left blank uses its own defaults.
  const [maxPages, setMaxPages] = useState("")
  const [maxDepth, setMaxDepth] = useState("")
  const [forceRefresh, setForceRefresh] = useState(false)
  const [hint, setHint] = useState("")
  const [excludeUrlsText, setExcludeUrlsText] = useState("")

  useEffect(() => {
    getProviders()
      .then(setProviders)
      .catch(() => setProviders([]))
  }, [])

  const canStart = Boolean(docUrl.trim())

  const stages: PipelineStage[] = PIPELINE_LABELS.map(({ key, label }) => ({
    key,
    label,
    status: key === "docs" ? DOCS_STATUS_BY_STEP[step] : "idle",
  }))

  const notes: OrchestratorNote[] = [INTRO_NOTE]
  if (step === "starting") {
    notes.push({
      id: "parsing",
      text: `Reading ${platformName || "the platform's"} docs, compiling and parsing... this can take a few minutes for a fresh crawl.`,
    })
  }
  if ((step === "reviewing" || step === "done") && result) {
    notes.push({
      id: "built",
      text: `Built this from ${result.meta.pages_crawled} page${
        result.meta.pages_crawled === 1 ? "" : "s"
      } across ${result.meta.depth_reached} hop${result.meta.depth_reached === 1 ? "" : "s"}.`,
    })
  }
  if (step === "done") {
    notes.push({ id: "approved", text: "Documentation approved." })
  }
  if (step === "error" && error) {
    notes.push({ id: "error", text: `Docs fetch failed: ${error}` })
  }

  const runRetrieval = async () => {
    setStep("starting")
    setError(null)
    try {
      const fetched = await fetchDocumentation(docUrl.trim(), {
        maxPages: maxPages ? Number(maxPages) : undefined,
        maxDepth: maxDepth ? Number(maxDepth) : undefined,
        forceRefresh,
        hint: hint.trim() || undefined,
        excludeUrls: parseExcludeUrls(excludeUrlsText),
        model: selectedModel === "auto" ? undefined : selectedModel,
      })
      setResult(fetched)
      setStep("reviewing")
    } catch (e) {
      setError(e instanceof Error ? e.message : "Unknown error")
      setStep("error")
    }
  }

  const handleStart = () => {
    if (!canStart) return
    void runRetrieval()
  }

  const handleRetry = () => {
    setResult(null)
    void runRetrieval()
  }

  const handleApprove = () => {
    if (!result) return
    setStep("done")
    onApprove?.({ platformName, markdown: combineMarkdown(result.pages) })
  }

  const handleFileChange = (file: File | null) => {
    setFileName(file ? file.name : null)
  }

  return (
    <div
      className="flex h-screen flex-col"
      style={{
        ...brandTokenStyle,
        background: "var(--brand-faint)",
        color: "var(--text-strong)",
        fontFamily: "var(--font-sans)",
      }}
    >
      <header
        className="flex h-16 shrink-0 items-center"
        style={{ padding: "0 24px" }}
      >
        {onExit && (
          <button
            onClick={onExit}
            aria-label="Back"
            style={{
              background: "none",
              border: "none",
              cursor: "pointer",
              color: "var(--text-strong)",
              marginRight: 10,
              fontSize: 16,
            }}
          >
            ←
          </button>
        )}
        <h1
          style={{
            fontFamily: "var(--font-display)",
            fontSize: 20,
            fontWeight: 700,
            letterSpacing: "-0.015em",
            margin: 0,
            color: "var(--text-strong)",
          }}
        >
          Add a CI/CD Platform
        </h1>
      </header>

      <div style={{ maxWidth: 1180, width: "100%", margin: "0 auto" }}>
        <StagePipeline stages={stages} />
      </div>

      <div
        className="flex flex-1 justify-center overflow-auto"
        style={{ padding: 24 }}
      >
        <div
          className="flex w-full"
          style={{ maxWidth: 1180, gap: 24, alignItems: "stretch", flexWrap: "wrap" }}
        >
          <div className="flex flex-1" style={{ minWidth: 280, flexBasis: 280 }}>
            <OrchestratorPanel
              notes={notes}
              providers={providers}
              selectedModel={selectedModel}
              onModelChange={setSelectedModel}
            />
          </div>

          <div
            className="flex flex-1 flex-col"
            style={{
            minWidth: 280,
            flexBasis: 280,
            background: "var(--surface-card)",
            border: "1px solid var(--border-subtle)",
            borderRadius: "var(--radius-lg)",
            boxShadow: "var(--shadow-sm)",
            padding: 20,
            gap: 16,
          }}
        >
          <TextField
            label="Platform name"
            value={platformName}
            placeholder="e.g. TeamCity"
            onChange={setPlatformName}
            disabled={step !== "input"}
          />

          <TextField
            label="Documentation URL"
            value={docUrl}
            placeholder="https://docs.example.com/ci/"
            onChange={setDocUrl}
            disabled={step !== "input"}
          />

          {step === "input" && (
            <>
              <div
                className="flex items-center justify-center"
                style={{ color: "var(--text-faint)", fontSize: 12 }}
              >
                or
              </div>

              <label style={{ display: "block" }}>
                <span
                  style={{
                    display: "block",
                    fontSize: 13,
                    fontWeight: 600,
                    color: "var(--text-strong)",
                    marginBottom: 6,
                  }}
                >
                  Upload documentation
                </span>
                <button
                  type="button"
                  onClick={() => fileInputRef.current?.click()}
                  className="w-full text-left"
                  style={{
                    padding: "9px 12px",
                    borderRadius: "var(--radius-md)",
                    border: "1px dashed var(--border-default)",
                    background: "var(--surface-card)",
                    color: fileName ? "var(--text-strong)" : "var(--text-faint)",
                    fontSize: 14,
                  }}
                >
                  {fileName ? `✓ ${fileName}` : "drop PDF here, or browse"}
                </button>
                <input
                  ref={fileInputRef}
                  type="file"
                  accept="application/pdf"
                  className="hidden"
                  onChange={(e) => handleFileChange(e.target.files?.[0] ?? null)}
                />
                <span style={{ display: "block", fontSize: 11.5, color: "var(--text-faint)", marginTop: 6 }}>
                  Not wired yet — retrieval only fetches rendered web pages, not PDFs.
                  Use a documentation URL for now.
                </span>
              </label>
            </>
          )}

          {step !== "done" && (
            <details open={step === "error" || step === "reviewing"}>
              <summary
                style={{ fontSize: 13, fontWeight: 600, cursor: "pointer", color: "var(--text-muted)" }}
              >
                Advanced
              </summary>
              <div className="flex flex-col" style={{ gap: 12, marginTop: 12 }}>
                <div className="flex" style={{ gap: 12 }}>
                  <div style={{ flex: 1 }}>
                    <NumberField
                      label="Max pages"
                      value={maxPages}
                      placeholder="15 (default)"
                      onChange={setMaxPages}
                      disabled={step === "starting"}
                    />
                  </div>
                  <div style={{ flex: 1 }}>
                    <NumberField
                      label="Max depth"
                      value={maxDepth}
                      placeholder="5 (default)"
                      onChange={setMaxDepth}
                      disabled={step === "starting"}
                    />
                  </div>
                </div>

                <TextField
                  label="Hint (steer the crawl, or a retry)"
                  value={hint}
                  placeholder="e.g. prioritize pages about triggers and secrets"
                  onChange={setHint}
                  disabled={step === "starting"}
                />

                <TextAreaField
                  label="Exclude URLs (one per line)"
                  value={excludeUrlsText}
                  placeholder="https://docs.example.com/ci/old-page/"
                  onChange={setExcludeUrlsText}
                  disabled={step === "starting"}
                />

                <label className="flex items-center" style={{ gap: 8, fontSize: 13, color: "var(--text-body)" }}>
                  <input
                    type="checkbox"
                    checked={forceRefresh}
                    onChange={(e) => setForceRefresh(e.target.checked)}
                    disabled={step === "starting"}
                  />
                  Force refresh (bypass cache, fetch everything fresh)
                </label>
              </div>
            </details>
          )}

          {step === "input" && (
            <BrandButton onClick={handleStart} disabled={!canStart} className="w-fit">
              Start Integration
            </BrandButton>
          )}

          {step === "starting" && (
            <div
              style={{
                padding: "9px 14px",
                borderRadius: "var(--radius-md)",
                border: "1px dashed var(--border-default)",
                color: "var(--text-muted)",
                fontSize: 13,
              }}
            >
              Starting…
            </div>
          )}

          {step === "error" && (
            <>
              <div
                style={{
                  padding: "9px 14px",
                  borderRadius: "var(--radius-md)",
                  fontSize: 13,
                  color: "var(--danger-500)",
                  border: "1px solid var(--danger-500)",
                  background: "var(--danger-100)",
                }}
              >
                Docs fetch failed: {error}
              </div>
              <BrandButton onClick={handleRetry} className="w-fit">
                Try again
              </BrandButton>
            </>
          )}

          {(step === "reviewing" || step === "done") && result && (
            <>
              <div className="flex flex-col" style={{ gap: 6 }}>
                <span style={{ fontSize: 13, fontWeight: 600, color: "var(--text-strong)" }}>
                  Documentation output
                </span>
                <div
                  style={{
                    border: "1px solid var(--border-subtle)",
                    borderRadius: "var(--radius-md)",
                    overflow: "clip",
                  }}
                >
                  <CodeBlockCode code={combineMarkdown(result.pages)} language="markdown" />
                </div>
              </div>

              <div className="flex" style={{ gap: 10 }}>
                <BrandButton onClick={handleApprove} disabled={step === "done"} className="w-fit">
                  {step === "done" ? "Approved" : "Approve"}
                </BrandButton>
                <BrandButton
                  variant="secondary"
                  onClick={handleRetry}
                  disabled={step === "done"}
                  className="w-fit"
                >
                  Retry this stage
                </BrandButton>
              </div>
            </>
          )}
          </div>
        </div>
      </div>
    </div>
  )
}
