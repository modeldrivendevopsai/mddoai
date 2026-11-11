import { STAGE_LABELS } from 'orchestrator-types';
import { timeAgo } from '../features/generate/format';
import type { Session } from '../services/sessions.service';

// What a finished run says where a stage name would go.
const COMPLETE_LABEL = 'Complete';

/**
 * The second line of a session row: where the run is and how long ago it began,
 * such as "PSM, 2 hours ago". It is what tells apart the rows of one platform,
 * which all carry the same name. A run nothing has happened on has no time to
 * give, so it says only where it is.
 */
export function sessionDetail(session: Session, now: Date = new Date()): string {
  const stage = session.stage === null ? COMPLETE_LABEL : STAGE_LABELS[session.stage];
  if (session.startedAt === null) return stage;
  return `${stage}, ${timeAgo(new Date(session.startedAt * 1000).toISOString(), now)}`;
}
