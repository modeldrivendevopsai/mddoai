import { useEffect, useState } from 'react';
import { Panel, Button, StatusPill } from '../design-system';
import { getPlatforms } from '../services/platforms.service';
import type { Platform } from '../services/platforms.service';
import { START_OPTIONS, FLOW_CAPTIONS } from '../config/startOptions.config';
import './start-screen.css';

/**
 * StartScreen — the "Choose how you want to start" landing page
 * (wireframe scenario 0a). Content comes from startOptions.config.ts,
 * platform rows come from platforms.service.ts. onSelectOption lets the
 * parent route decide what happens next (e.g. navigate to the generate-
 * pipeline flow or the add/update-platform flow) — this page has no
 * routing knowledge itself.
 */
interface StartScreenProps {
  onSelectOption?: (optionId: string) => void;
}

export function StartScreen({ onSelectOption }: StartScreenProps) {
  const [platforms, setPlatforms] = useState<Platform[]>([]);

  useEffect(() => {
    getPlatforms().then(setPlatforms);
  }, []);

  return (
    <div className="mdd-start-screen">
      <p className="mdd-start-screen__subtitle">Choose how you want to start</p>
      <p className="mdd-start-screen__caption">{FLOW_CAPTIONS.above}</p>

      <div className="mdd-start-screen__cards">
        {START_OPTIONS.map((option) => (
          <Panel key={option.id} tone={option.tone} className="mdd-start-screen__card">
            <h2>{option.title}</h2>
            <p>{option.description}</p>
            <Button
              variant="primary"
              disabled={option.comingSoon}
              title={option.comingSoon ? 'Not built yet' : undefined}
              onClick={() => onSelectOption?.(option.id)}
            >
              {option.comingSoon ? 'Coming soon' : option.cta}
            </Button>
          </Panel>
        ))}
      </div>

      <p className="mdd-start-screen__caption mdd-start-screen__caption--below">
        {FLOW_CAPTIONS.below}
      </p>

      <h3 className="mdd-start-screen__heading">Supported CI/CD platforms</h3>
      <Panel tone="neutral" className="mdd-start-screen__table">
        {platforms.map((p) => (
          <div key={p.id} className="mdd-start-screen__row">
            <StatusPill variant={p.status}>{p.statusLabel}</StatusPill>
            <span className="mdd-start-screen__platform-name">{p.name}</span>
            {p.resumable && (
              <a href="#" className="mdd-start-screen__link">
                resume →
              </a>
            )}
            <a href="#" className="mdd-start-screen__link">
              view breakdown
            </a>
          </div>
        ))}
      </Panel>
    </div>
  );
}
