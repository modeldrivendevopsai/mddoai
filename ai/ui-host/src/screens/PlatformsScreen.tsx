import { Button } from 'design-system';
import { PLATFORMS_COPY } from '../config/platforms.config';
import { PlatformsList } from '../features/platforms/PlatformsList';
import { BackTitle } from '../components/BackTitle';
import './platforms-screen.css';

interface PlatformsScreenProps {
  onBack: () => void;
  onNew: () => void;
  onOpen: (platformId: string) => void;
  onOpenRun: (runId: string) => void;
}

/**
 * PlatformsScreen - the first screen of the "Add or update a CI/CD platform"
 * mode: the platforms already supported, as the pipelines screen lists the
 * pipelines, each opening its platform, with "+ New platform" to add one. The
 * editing view (the integration screen) is behind that button, not the first
 * thing seen.
 */
export default function PlatformsScreen({ onBack, onNew, onOpen, onOpenRun }: PlatformsScreenProps) {
  const copy = PLATFORMS_COPY;

  return (
    <div className="mdd-platforms">
      <header className="mdd-platforms__band">
        <h1>
          <BackTitle title={copy.title} hint={copy.backHint} onBack={onBack} />
        </h1>
      </header>
      <div className="mdd-platforms__body">
        <div>
          <Button variant="secondary" onClick={onNew}>
            {copy.newPlatform}
          </Button>
        </div>

        <h2 className="mdd-platforms__section-title">{copy.listTitle}</h2>
        <PlatformsList onOpen={onOpen} onOpenRun={onOpenRun} />
      </div>
    </div>
  );
}
