import { Button } from 'design-system';
import type { PlatformSummary } from 'orchestrator-types';
import { isSelectingTextIn } from '../../components/isSelectingTextIn';
import { PLATFORMS_COPY } from '../../config/platforms.config';
import { timeAgo } from '../generate/format';
import { LoadError, LoadHint } from './PlatformsFeedback';
import { usePlatformList } from './usePlatformList';
import './platforms-list.css';

interface PlatformsListProps {
  onOpen: (platformId: string) => void;
  // Opens the chat (the run) that made a platform, by the run's id.
  onOpenRun: (runId: string) => void;
}

// Where a platform came from, in words: the stack's own, or published from a run.
function sourceOf(platform: PlatformSummary): string {
  const copy = PLATFORMS_COPY.source;
  return platform.published_at ? copy.published(timeAgo(platform.published_at)) : copy.included;
}

/**
 * The platforms MDDOAI can generate for: each at its latest version, where it
 * came from and the file it writes. A row opens its platform wherever it is
 * pressed; the platform's name is a button, which is how the keyboard gets in.
 * Where a run made the platform, its source links to that chat.
 * It loads its own rows, so the start screen and the platforms screen show the
 * same list without either knowing where it comes from.
 */
export function PlatformsList({ onOpen, onOpenRun }: PlatformsListProps) {
  const { platforms, error, retry } = usePlatformList();
  const copy = PLATFORMS_COPY;

  if (error) {
    return (
      <LoadError message={error}>
        <Button variant="secondary" size="sm" onClick={retry}>
          {copy.retry}
        </Button>
      </LoadError>
    );
  }
  if (platforms === null) return <LoadHint busy>{copy.loading}</LoadHint>;
  if (platforms.length === 0) return <LoadHint>{copy.empty}</LoadHint>;

  return (
    <div className="mdd-platforms-list">
      <table className="mdd-platforms-list__table">
        <thead>
          <tr>
            <th scope="col">{copy.columns.platform}</th>
            <th scope="col">{copy.columns.version}</th>
            <th scope="col">{copy.columns.source}</th>
            <th scope="col">{copy.columns.outputFile}</th>
          </tr>
        </thead>
        <tbody>
          {platforms.map((platform) => (
            <tr
              key={platform.id}
              onClick={(event) => {
                if (!isSelectingTextIn(event.currentTarget)) onOpen(platform.id);
              }}
            >
              <th scope="row">
                {/* Opens the platform itself, so a text selection left in the row never blocks the keyboard. */}
                <button
                  type="button"
                  className="mdd-platforms-list__link"
                  aria-label={copy.openPlatform(platform.name)}
                  onClick={(event) => {
                    event.stopPropagation();
                    onOpen(platform.id);
                  }}
                >
                  {platform.name}
                </button>
              </th>
              <td className="mdd-platforms-list__mono">{platform.version}</td>
              <td title={platform.published_at ? new Date(platform.published_at).toLocaleString() : undefined}>
                {sourceOf(platform)}
                {platform.source_run_id && (
                  <button
                    type="button"
                    className="mdd-platforms-list__link mdd-platforms-list__chat"
                    aria-label={copy.source.openChatOf(platform.name)}
                    onClick={(event) => {
                      event.stopPropagation();
                      onOpenRun(platform.source_run_id as string);
                    }}
                  >
                    {copy.source.openChat}
                  </button>
                )}
              </td>
              <td className="mdd-platforms-list__mono">{platform.output_filename}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
