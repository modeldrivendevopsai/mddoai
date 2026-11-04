import { useEffect, useRef, useState } from 'react';
import { Button, Icon } from 'design-system';
import { GENERATE_COPY, MODEL_FILE_ACCEPT } from '../../config/generate.config';
import { keepFocusInside } from './focusTrap';
import { useWorkspaceListing } from './useWorkspaceListing';

interface WorkspaceBrowserProps {
  onSelect: (path: string) => void;
  onClose: () => void;
}

const joinPath = (folder: string, name: string) => (folder === '/' ? `/${name}` : `${folder}/${name}`);

// What the dialog is called, for the person and for a screen reader.
const TITLE = 'Choose an architecture file';

/**
 * A dialog for finding an architecture file inside the workspace folder, the
 * one place on the server's disk the service reads from. It lists what the
 * backend lists; nothing outside the workspace is ever shown.
 */
export function WorkspaceBrowser({ onSelect, onClose }: WorkspaceBrowserProps) {
  const [folder, setFolder] = useState('/');
  const { listing, error } = useWorkspaceListing(folder);
  const dialog = useRef<HTMLDivElement>(null);

  // Takes keyboard focus on open, and gives it back to what opened it.
  useEffect(() => {
    const opener = document.activeElement as HTMLElement | null;
    dialog.current?.focus();
    return () => opener?.focus?.();
  }, []);

  // Heard on the document, not on the dialog: while a folder loads its list is
  // gone, and with it the focused button, so the dialog would stop hearing keys.
  useEffect(() => {
    const onKey = (event: KeyboardEvent) => {
      if (event.key === 'Escape') onClose();
      else if (event.key === 'Tab') keepFocusInside(event, dialog.current);
    };
    document.addEventListener('keydown', onKey);
    return () => document.removeEventListener('keydown', onKey);
  }, [onClose]);

  return (
    <div
      className="mdd-generate__backdrop"
      onMouseDown={(event) => event.target === event.currentTarget && onClose()}
    >
      <div ref={dialog} className="mdd-generate__dialog" role="dialog" aria-modal="true" aria-label={TITLE} tabIndex={-1}>
        <header className="mdd-generate__dialog-header">
          <h2>{TITLE}</h2>
          <Button variant="ghost" size="sm" aria-label="Close" onClick={onClose}>
            <Icon name="X" size={16} />
          </Button>
        </header>
        {listing && (
          <p className="mdd-generate__hint">
            Showing the files in {listing.root_label}. Only {MODEL_FILE_ACCEPT} files can be opened. To use a
            file from somewhere else, close this and choose {GENERATE_COPY.input.upload}.
          </p>
        )}
        <p className="mdd-generate__dialog-path" aria-live="polite">
          Folder {listing ? listing.path : folder}
        </p>

        {error && (
          <p className="mdd-generate__error" role="alert">
            {error}
          </p>
        )}
        {!listing && !error && <p className="mdd-generate__hint">Loading…</p>}

        {listing && (
          <ul className="mdd-generate__entries">
            {listing.parent !== null && (
              <li>
                <button type="button" onClick={() => setFolder(listing.parent as string)}>
                  <Icon name="CornerLeftUp" size={16} />
                  <span>Up one folder</span>
                </button>
              </li>
            )}
            {listing.entries.map((entry) =>
              entry.kind === 'other' ? (
                <li key={entry.name}>
                  <span className="mdd-generate__entry-static">
                    <Icon name="FileText" size={16} />
                    <span>{entry.name}</span>
                  </span>
                </li>
              ) : (
                <li key={entry.name}>
                  <button
                    type="button"
                    onClick={() =>
                      entry.kind === 'dir'
                        ? setFolder(joinPath(listing.path, entry.name))
                        : onSelect(joinPath(listing.path, entry.name))
                    }
                  >
                    <Icon name={entry.kind === 'dir' ? 'Folder' : 'FileText'} size={16} />
                    <span>{entry.name}</span>
                  </button>
                </li>
              )
            )}
            {listing.entries.length === 0 && (
              <li className="mdd-generate__hint">No files here.</li>
            )}
          </ul>
        )}
        {listing?.truncated && <p className="mdd-generate__hint">Only the first entries are shown.</p>}
      </div>
    </div>
  );
}
