/**
 * Where each screen lives. Every route definition, link and redirect reads its
 * path from here, so moving a screen is one edit.
 */
export const ROUTES = {
  start: '/',
  pipelines: '/generate',
  newPipeline: '/generate/new',
  platforms: '/platforms',
  integration: '/integration',
} as const;

/** A saved pipeline's screen. */
export const pipelinePath = (pipelineId: string) => `${ROUTES.pipelines}/${encodeURIComponent(pipelineId)}`;

/** The integration screen's query parameters, written by links and read by the screen. */
export const INTEGRATION_QUERY = {
  /** Shows a past run, read-only, instead of the live one. */
  run: 'run',
  /** Asks for a blank run. */
  newRun: 'new',
} as const;

// What the "new run" parameter is set to.
const NEW_RUN_FLAG = '1';

/** Whether the location asks the integration screen for a blank run. */
export const isNewRunRequested = (params: URLSearchParams) =>
  params.get(INTEGRATION_QUERY.newRun) === NEW_RUN_FLAG;

/**
 * A blank integration run. It needs the parameter, not just the integration
 * path: the backend's current run is not scoped to a URL, so going to the path
 * you are already on is a no-op in the router and the old run would stay. The
 * screen reads the parameter and resets the run.
 */
export const NEW_PLATFORM_PATH = `${ROUTES.integration}?${new URLSearchParams({ [INTEGRATION_QUERY.newRun]: NEW_RUN_FLAG })}`;

/** A past integration run, shown read-only. */
export const integrationRunPath = (runId: string) =>
  `${ROUTES.integration}?${new URLSearchParams({ [INTEGRATION_QUERY.run]: runId })}`;
