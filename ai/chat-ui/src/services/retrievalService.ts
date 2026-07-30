// Calls the retrieval service's real POST /fetch directly (see ai/retrieval/README.md),
// routed through the Vite dev proxy's /retrieval-api prefix (see vite.config.ts). The
// retrieval agent is its own standalone service — this does not go through orchestrator.

export interface RetrievedPage {
  url: string
  success: boolean
  status_code: number | null
  markdown: string
  links: string[]
}

export interface PendingLink {
  href: string
  text: string
  title: string
}

export interface RetrievalMeta {
  confidence: number
  pages_crawled: number
  depth_reached: number | null
  pending_links: PendingLink[]
}

export interface RetrievalFetchResult {
  seed_url: string
  pages: RetrievedPage[]
  meta: RetrievalMeta
}

export interface FetchDocumentationOptions {
  maxPages?: number
  maxDepth?: number
  forceRefresh?: boolean
  hint?: string
  excludeUrls?: string[]
  // Which ai-layer provider handles the ranking/cleanup LLM calls (see GET
  // /api/providers). Omitted lets ai-layer's own default provider chain run.
  model?: string
}

export async function fetchDocumentation(
  url: string,
  options: FetchDocumentationOptions = {}
): Promise<RetrievalFetchResult> {
  const res = await fetch("/retrieval-api/fetch", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      url,
      max_pages: options.maxPages,
      max_depth: options.maxDepth,
      force_refresh: options.forceRefresh ?? false,
      hint: options.hint || null,
      exclude_urls: options.excludeUrls?.length ? options.excludeUrls : null,
      model: options.model || null,
    }),
  })

  if (!res.ok) {
    const body = await res.json().catch(() => null)
    throw new Error(body?.detail || `HTTP ${res.status}`)
  }

  return res.json()
}
