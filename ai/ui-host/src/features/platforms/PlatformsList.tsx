import { useEffect, useState } from 'react';
import { Panel, StatusPill } from 'design-system';
import { getPlatforms } from '../../services/platforms.service';
import type { Platform } from '../../services/platforms.service';
import './platforms-list.css';

/**
 * The supported CI/CD platforms, each with its status and name, and nothing
 * that does not work yet. It loads its own rows from the platforms data
 * service, so the start screen and the platforms screen show the same list
 * without either knowing where it comes from.
 */
export function PlatformsList() {
  const [platforms, setPlatforms] = useState<Platform[]>([]);

  useEffect(() => {
    getPlatforms().then(setPlatforms);
  }, []);

  return (
    <Panel tone="neutral" className="mdd-platforms-list">
      {platforms.map((p) => (
        <div key={p.id} className="mdd-platforms-list__row">
          <StatusPill variant={p.status}>{p.statusLabel}</StatusPill>
          <span className="mdd-platforms-list__name">{p.name}</span>
        </div>
      ))}
    </Panel>
  );
}
