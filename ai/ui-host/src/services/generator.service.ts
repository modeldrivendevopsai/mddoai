/**
 * The one API client for the "Generate a CI/CD pipeline" mode's own backend
 * (ai/pipeline_generator), reached through ui-host's `/generator-api` proxy.
 * Separate from orchestrator.service.ts on purpose:
 * that mode has no pipeline run, chat or stages, so it is not reached
 * through ai/orchestrator. Its types live here rather than in
 * orchestrator-types, which is the orchestrator's own contract.
 */

export interface GeneratorPlatform {
  id: string
  name: string
  output_filename: string
  // "engine" for a built-in platform, "published" for one published from an
  // integration run, which also has the version used.
  source: "engine" | "published"
  version: number | null
}

export interface ModelAttribute {
  name: string
  value: string
}

export interface ModelIssue {
  severity: "ERROR" | "WARNING"
  message: string
}

export interface ModelValidation {
  valid: boolean
  issues: ModelIssue[]
}

export interface Inspection {
  // null when the file couldn't be read as XML at all
  element: string | null
  attributes: ModelAttribute[]
  model_validation: ModelValidation
}

export interface IntermediateModel {
  id: string
  title: string
  filename: string
  content: string
}

export interface PlatformRef {
  id: string
  name: string
}

// One platform's outcome. A platform whose generation failed carries `error`
// and no pipeline; one whose pipeline could not be saved to the requested
// folder still has its pipeline, with `save_error` saying why.
export interface GenerationResult {
  platform: PlatformRef
  output_filename: string
  pipeline: string | null
  intermediates: IntermediateModel[]
  duration_ms: number | null
  saved_to: string | null
  // True when a file of that name was already in the folder and was replaced.
  replaced: boolean
  save_error: string | null
  error: string | null
}

export interface GenerateRequest {
  filename: string
  content: string
  platforms: string[]
  // Where in the workspace the model was loaded from; null for an upload.
  source_path: string | null
  // The workspace folder to save each generated file into; null saves nothing.
  save_to: string | null
}

// How often a running generation is checked. Each platform takes seconds
// to minutes, so a second's delay in noticing it finished is unnoticeable
// (engineering guess).
const GENERATION_POLL_MS = 1000
// Checks in a row that may fail (no answer, or a 5xx from a proxy restarting)
// before giving up on a job that is probably still running (engineering
// guess, a few seconds of outage).
const GENERATION_POLL_RETRIES = 3

interface GenerationJob extends Partial<GenerateResponse> {
  state: "running" | "done" | "failed"
  error?: string
}

export interface GenerateResponse {
  // null when nothing was generated or the result could not be kept.
  pipeline_id: string | null
  results: GenerationResult[]
}

export interface PipelineSummary {
  id: string
  swarch_name: string
  source_path: string | null
  platforms: PlatformRef[]
  outputs: string[]
  // ISO 8601, UTC
  generated_at: string
}

export interface PipelineRecord extends PipelineSummary {
  content: string
  save_to: string | null
  results: GenerationResult[]
}

export interface WorkspaceEntry {
  name: string
  kind: "dir" | "file"
}

export interface WorkspaceListing {
  path: string
  parent: string | null
  entries: WorkspaceEntry[]
  truncated: boolean
  // The folder every path above is inside, as the person knows it (for
  // example the repository's name), so the dialog can say where it looks.
  root_label: string
}

export interface WorkspaceFile {
  filename: string
  path: string
  content: string
}

export interface PipelineCheck {
  id: string
  title: string
  status: "pass" | "fail" | "skipped"
  details: string[]
}

export interface PipelineValidation {
  valid: boolean
  checks: PipelineCheck[]
}

// A failed request, with the backend's own HTTP status and, for the one
// failure that carries structured detail (a model refused for being
// invalid), the issues behind it - so a caller can show those instead of a
// bare message.
export class GeneratorError extends Error {
  status: number
  issues: ModelIssue[]

  constructor(message: string, status: number, issues: ModelIssue[] = []) {
    super(message)
    this.status = status
    this.issues = issues
  }
}

async function errorFor(label: string, res: Response): Promise<GeneratorError> {
  const generic = `${label} request failed: ${res.status}`
  try {
    const body = await res.json()
    const detail = body?.detail
    if (typeof detail === "string") return new GeneratorError(detail, res.status)
    if (detail && typeof detail.message === "string") {
      return new GeneratorError(detail.message, res.status, Array.isArray(detail.issues) ? detail.issues : [])
    }
  } catch {
    // Body wasn't JSON, fall through to the generic message.
  }
  return new GeneratorError(generic, res.status)
}

async function post<T>(path: string, label: string, payload: unknown): Promise<T> {
  const res = await fetch(`/generator-api${path}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  })
  if (!res.ok) throw await errorFor(label, res)
  return res.json()
}

async function get<T>(path: string, label: string, query?: Record<string, string>): Promise<T> {
  const search = query ? `?${new URLSearchParams(query)}` : ""
  const res = await fetch(`/generator-api${path}${search}`)
  if (!res.ok) throw await errorFor(label, res)
  return res.json()
}

export async function getPlatforms(): Promise<GeneratorPlatform[]> {
  const res = await fetch("/generator-api/platforms")
  if (!res.ok) throw await errorFor("Platforms", res)
  return (await res.json()).platforms
}

export function inspectModel(filename: string, content: string): Promise<Inspection> {
  return post("/inspect", "Inspect", { filename, content })
}

// The generator answers POST /generate at once with a job and generates
// afterwards, since a generation can outlast a proxy's request timeout. This
// waits for the job, so callers get the finished results either way. Aborting
// `signal` stops the waiting; the job itself still finishes on the server.
export async function generatePipelines(request: GenerateRequest, signal?: AbortSignal): Promise<GenerateResponse> {
  const { job_id } = await post<{ job_id: string }>("/generate", "Generate", request)
  let failures = 0
  for (;;) {
    signal?.throwIfAborted()
    let job: GenerationJob
    try {
      job = await get<GenerationJob>(`/generate/${encodeURIComponent(job_id)}`, "Generate")
      failures = 0
    } catch (err) {
      if (!isTransient(err) || ++failures > GENERATION_POLL_RETRIES) throw err
      await sleep(GENERATION_POLL_MS)
      continue
    }
    // The job's own outcome, not an HTTP error, so it carries no status.
    if (job.state === "done") return { pipeline_id: job.pipeline_id ?? null, results: job.results ?? [] }
    if (job.state === "failed") throw new GeneratorError(job.error ?? "The generation failed.", 0)
    if (job.state !== "running") throw new GeneratorError("The pipeline generator gave an unexpected answer.", 0)
    await sleep(GENERATION_POLL_MS)
  }
}

// No answer at all, or one from a server error rather than about the job.
function isTransient(err: unknown): boolean {
  return err instanceof TypeError || (err instanceof GeneratorError && err.status >= 500)
}

function sleep(ms: number): Promise<void> {
  return new Promise((resolve) => setTimeout(resolve, ms))
}

export function validatePipeline(platform: string, content: string): Promise<PipelineValidation> {
  return post("/validate", "Validate", { platform, content })
}

export async function listPipelines(): Promise<PipelineSummary[]> {
  return (await get<{ pipelines: PipelineSummary[] }>("/pipelines", "Pipelines")).pipelines
}

export function getPipeline(id: string): Promise<PipelineRecord> {
  return get(`/pipelines/${encodeURIComponent(id)}`, "Pipeline")
}

export function browseWorkspace(path: string, dirsOnly: boolean): Promise<WorkspaceListing> {
  return get("/workspace/browse", "Browse", { path, dirs_only: String(dirsOnly) })
}

export function readWorkspaceFile(path: string): Promise<WorkspaceFile> {
  return get("/workspace/file", "Open file", { path })
}
