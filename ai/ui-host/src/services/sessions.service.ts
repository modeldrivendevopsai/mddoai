/**
 * Sessions data service — backed by ai/orchestrator's real run history
 * (GET /runs, DELETE /runs/{id}). Components
 * depend only on getSessions()/deleteSession()'s signatures, not on this.
 */
import { deleteRun, getRuns } from './orchestrator.service';
import type { RunSummary, StageId } from 'orchestrator-types';

export type SessionType = 'pipeline' | 'platform';
export type SessionState = 'selected' | 'normal' | 'attention';

export interface Session {
  id: string;
  name: string;
  type: SessionType;
  // 'selected' is the run the backend treats as current. It cannot be deleted.
  state: SessionState;
  // Where the run is, and when it began, so runs of one platform can be told apart.
  // stage is null once the whole pipeline has finished; startedAt is in seconds
  // since the epoch, or null for a run nothing has happened on.
  stage: StageId | null;
  startedAt: number | null;
}

function toSession(run: RunSummary): Session {
  return {
    id: run.run_id,
    name: run.platform_name ?? `Run ${run.run_id.slice(0, 8)}`,
    // Every real run today is the "Add/update a CI/CD platform" flow (see
    // IntegrationScreen's own header, "Platform integration" /
    // "Add a CI/CD platform") — 'platform', not 'pipeline', is what makes
    // SessionsList render it with the app's real purple ("platform mode")
    // tint instead of the blue one the "Generate a CI/CD pipeline" mode uses
    // (that mode has no runs of its own).
    type: 'platform',
    state: run.is_current ? 'selected' : 'normal',
    stage: run.current_stage,
    startedAt: run.started_at,
  };
}

// Filtered by type, not just returned wholesale: every real run is
// 'platform' (see toSession). The "Generate a CI/CD pipeline" mode has no
// runs; the pipelines it has generated are listed on the "Pipelines" tab by
// layout/PipelinesList, from the pipeline generator, not from here.
export async function getSessions(type: SessionType): Promise<Session[]> {
  const runs = await getRuns();
  return runs.map(toSession).filter((session) => session.type === type);
}

// Deletes the run for good. Throws with the backend's reason when it refuses.
export async function deleteSession(id: string): Promise<void> {
  await deleteRun(id);
}
