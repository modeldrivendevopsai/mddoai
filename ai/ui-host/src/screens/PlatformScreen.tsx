import { Button } from 'design-system';
import { BackTitle } from '../components/BackTitle';
import { PLATFORM_VIEW_COPY } from '../config/platforms.config';
import { LoadError, LoadHint } from '../features/platforms/PlatformsFeedback';
import { PlatformView } from '../features/platforms/PlatformView';
import { usePlatform } from '../features/platforms/usePlatform';
import './platforms-screen.css';

interface PlatformScreenProps {
  platformId: string;
  // The version the address names; the platform's latest when it names none.
  version?: string;
  onBack: () => void;
  onVersionChange: (version: string) => void;
  // Leaves the named version for the platform's latest.
  onShowLatest: () => void;
  onOpenRun: (runId: string) => void;
}

/**
 * PlatformScreen - the second screen of the "Add or update a CI/CD platform"
 * mode: one platform, to read what it is made of and see which run made it. Its
 * header is the platform's name, the way back to the platforms list, in the
 * same style as every screen's.
 */
export default function PlatformScreen({
  platformId,
  version,
  onBack,
  onVersionChange,
  onShowLatest,
  onOpenRun,
}: PlatformScreenProps) {
  const copy = PLATFORM_VIEW_COPY;
  const { detail, error, loading, retry } = usePlatform(platformId, version);

  return (
    <div className="mdd-platforms">
      <header className="mdd-platforms__band">
        <h1>
          <BackTitle title={detail?.name ?? copy.title} hint={copy.backHint} onBack={onBack} />
        </h1>
      </header>
      <div className="mdd-platforms__body">
        {error ? (
          <LoadError message={error}>
            <Button variant="secondary" size="sm" onClick={retry}>
              {copy.retry}
            </Button>
            {/* A version that was purged stays missing however often it is asked for, so the way out is the latest. */}
            {version && (
              <Button variant="secondary" size="sm" onClick={onShowLatest}>
                {copy.showLatest}
              </Button>
            )}
          </LoadError>
        ) : detail === null ? (
          <LoadHint busy>{copy.loading}</LoadHint>
        ) : (
          <PlatformView
            key={detail.id}
            detail={detail}
            busy={loading}
            onVersionChange={onVersionChange}
            onOpenRun={onOpenRun}
          />
        )}
      </div>
    </div>
  );
}
