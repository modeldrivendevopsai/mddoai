import { useState } from 'react';
import { Button, CodeBlock, Tabs } from 'design-system';
import type { PlatformDetail } from 'orchestrator-types';
import { PLATFORM_VIEW_COPY } from '../../config/platforms.config';
import { timeAgo } from '../generate/format';
import './platform-view.css';

interface PlatformViewProps {
  detail: PlatformDetail;
  // Another version is loading: this one stays on screen, shown as not final.
  busy: boolean;
  onVersionChange: (version: string) => void;
  onOpenRun: (runId: string) => void;
}

/**
 * One platform at one version: where it came from, which file it writes, and
 * the three files it is made of, one tab each, as plain text to read and copy.
 * The screen keys it by platform, so opening another platform starts on the
 * first file, while another version of the same one keeps the file open.
 * Nothing here changes a platform; making a new version is a new integration run.
 */
export function PlatformView({ detail, busy, onVersionChange, onOpenRun }: PlatformViewProps) {
  const copy = PLATFORM_VIEW_COPY;
  const [fileId, setFileId] = useState<string>(copy.files[0].id);
  const file = copy.files.find((f) => f.id === fileId) ?? copy.files[0];

  // The journal lists versions oldest first; the newest is the one a plain link opens.
  const latest = detail.versions[detail.versions.length - 1];
  const newestFirst = [...detail.versions].reverse();

  return (
    <div className={`mdd-platform-view${busy ? ' is-busy' : ''}`} aria-busy={busy}>
      <dl className="mdd-platform-view__facts">
        <div>
          <dt>
            <label htmlFor="mdd-platform-version">{copy.version}</label>
          </dt>
          <dd>
            <select
              id="mdd-platform-version"
              className="mdd-platform-view__select"
              value={detail.version}
              onChange={(event) => onVersionChange(event.target.value)}
            >
              {newestFirst.map((version) => (
                <option key={version} value={version}>
                  {version === latest ? copy.latest(version) : version}
                </option>
              ))}
            </select>
          </dd>
        </div>
        <div>
          <dt>{copy.outputFile}</dt>
          <dd className="mdd-platform-view__mono">{detail.output_filename}</dd>
        </div>
      </dl>

      {detail.published_at ? (
        <div className="mdd-platform-view__source">
          <p title={new Date(detail.published_at).toLocaleString()}>{copy.published(timeAgo(detail.published_at))}</p>
          {/* A platform can be published with no run behind it, so the run is offered only when there is one. */}
          {detail.source_run_id && (
            <Button variant="secondary" size="sm" onClick={() => onOpenRun(detail.source_run_id as string)}>
              {copy.openRun}
            </Button>
          )}
        </div>
      ) : (
        <p className="mdd-platform-view__source">{copy.included}</p>
      )}

      <Tabs items={copy.files.map(({ id, label }) => ({ id, label }))} activeId={file.id} onChange={setFileId} />
      <p className="mdd-platform-view__hint">{file.hint}</p>
      <div className="mdd-platform-view__code">
        <CodeBlock code={detail.files[file.id]} title={file.fileName} />
      </div>
    </div>
  );
}
