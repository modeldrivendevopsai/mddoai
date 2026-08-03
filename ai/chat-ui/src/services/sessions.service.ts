/**
 * Sessions data service.
 *
 * Components depend only on getSessions()/closeSession()'s signatures, not
 * on where the data comes from. When the backend endpoint exists, replace
 * the bodies below with real calls, e.g.:
 *
 *   export async function getSessions(type: SessionType) {
 *     const res = await fetch(`/api/sessions?type=${type}`);
 *     if (!res.ok) throw new Error('Failed to load sessions');
 *     return res.json();
 *   }
 *
 *   export async function closeSession(id: string) {
 *     await fetch(`/api/sessions/${id}`, { method: 'DELETE' });
 *   }
 *
 * No caller-side changes required when that swap happens.
 */
export type SessionType = 'pipeline' | 'platform';
export type SessionState = 'selected' | 'normal' | 'attention';

export interface Session {
  id: string;
  name: string;
  type: SessionType;
  state: SessionState;
}

const MOCK_SESSIONS: Session[] = [
  { id: 's1', name: 'GitLab CI v1.7 pipeline', type: 'pipeline', state: 'selected' },
  { id: 's2', name: 'Bamboo v2.2.0 update', type: 'platform', state: 'normal' },
  { id: 's3', name: 'TeamCity v0.9 build', type: 'platform', state: 'attention' },
];

export async function getSessions(type: SessionType): Promise<Session[]> {
  return MOCK_SESSIONS.filter((s) => s.type === type);
}

export async function closeSession(id: string): Promise<{ id: string; closed: boolean }> {
  return { id, closed: true };
}
