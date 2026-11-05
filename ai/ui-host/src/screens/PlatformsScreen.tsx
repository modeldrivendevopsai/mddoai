import { Button, Icon } from 'design-system';
import { PLATFORMS_COPY } from '../config/platforms.config';
import { PlatformsList } from '../features/platforms/PlatformsList';
import './platforms-screen.css';

interface PlatformsScreenProps {
  onBack: () => void;
  onNew: () => void;
}

/**
 * PlatformsScreen - the first screen of the "Add or update a CI/CD platform"
 * mode: the platforms already supported, as the pipelines screen lists the
 * pipelines, with "+ New platform" to add one. The editing view (the
 * integration screen) is behind that button, not the first thing seen.
 */
export default function PlatformsScreen({ onBack, onNew }: PlatformsScreenProps) {
  const copy = PLATFORMS_COPY;

  return (
    <div className="mdd-platforms">
      {/* "← Title": the title is itself the way back, as on the pipeline screens. */}
      <header className="mdd-platforms__band">
        <h1>
          <button type="button" className="mdd-platforms__back" title={copy.backHint} onClick={onBack}>
            <Icon name="ArrowLeft" size={18} />
            {copy.title}
          </button>
        </h1>
      </header>
      <div className="mdd-platforms__body">
        <div>
          <Button variant="secondary" onClick={onNew}>
            {copy.newPlatform}
          </Button>
        </div>

        <h2 className="mdd-platforms__section-title">{copy.listTitle}</h2>
        <PlatformsList />
      </div>
    </div>
  );
}
