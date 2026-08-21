import type { StatusPillVariant } from 'design-system';

/**
 * Platforms data service. Same pattern as sessions.service.ts: mock data
 * now, swap the body for a real fetch('/api/platforms') call later with
 * no changes required in StartScreen or anywhere else that calls this.
 */
export interface Platform {
  id: string;
  name: string;
  status: StatusPillVariant;
  statusLabel: string;
  resumable?: boolean;
}

const MOCK_PLATFORMS: Platform[] = [
  { id: 'p1', name: 'GitLab CI v1.7', status: 'success', statusLabel: 'Ready' },
  { id: 'p2', name: 'Bamboo v2.1.2', status: 'warning', statusLabel: 'In progress', resumable: true },
  { id: 'p3', name: 'Bamboo v2.2.0', status: 'warning', statusLabel: 'In progress', resumable: true },
  { id: 'p4', name: 'TeamCity v0.9', status: 'danger', statusLabel: 'Needs attention' },
];

export async function getPlatforms(): Promise<Platform[]> {
  return MOCK_PLATFORMS;
}
