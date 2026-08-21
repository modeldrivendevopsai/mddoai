/**
 * Sessions data service — backed by ai/orchestrator's real run history
 * (GET /runs, in-memory for the life of the backend process). Components
 * depend only on getSessions()/closeSession()'s signatures, not on this.
 */
import { getRuns } from './orchestrator.service';
import type { RunSummary } from '@/types/orchestrator';

export type SessionType = 'pipeline' | 'platform';
export type SessionState = 'selected' | 'normal' | 'attention';

export interface Session {
  id: string;
  name: string;
  type: SessionType;
  state: SessionState;
}

function toSession(run: RunSummary): Session {
  return {
    id: run.run_id,
    name: run.platform_name ?? `Run ${run.run_id.slice(0, 8)}`,
    // Every real run today is the "Add/update a CI/CD platform" flow (see
    // IntegrationScreen's own header, "Platform integration" /
    // "Add a CI/CD platform") — 'platform', not 'pipeline', is what makes
    // SessionsList render it with the app's real purple ("platform mode")
    // tint instead of the unimplemented "Generate a CI/CD pipeline" mode's
    // blue one.
    type: 'platform',
    state: run.is_current ? 'selected' : 'normal',
  };
}

// Filtered by type, not just returned wholesale: every real run is
// 'platform' (see toSession), so the "Pipelines" tab is correctly empty
// until "Generate a CI/CD pipeline" is a real flow with its own runs, not a
// duplicate of "Platforms" own list.
export async function getSessions(type: SessionType): Promise<Session[]> {
  const runs = await getRuns();
  return runs.map(toSession).filter((session) => session.type === type);
}

export async function closeSession(id: string): Promise<{ id: string; closed: boolean }> {
  // No backend "delete a run" action yet — a run is in-memory history, not
  // something to discard individually. Matches SessionsList's existing
  // optimistic local removal; nothing to await here yet.
  return { id, closed: true };
}
