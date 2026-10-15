import { useEffect, useRef, useState } from 'react';
import { Button, Icon } from 'design-system';
import { keepFocusInside } from './focusTrap';
import { useWorkspaceListing } from './useWorkspaceListing';

interface WorkspaceBrowserProps {
  // 'file' picks a file; 'folder' picks the folder being looked at.
  kind: 'file' | 'folder';
  onSelect: (path: string) => void;
  onClose: () => void;
}

const joinPath = (folder: string, name: string) => (folder === '/' ? `/${name}` : `${folder}/${name}`);

/**
 * A dialog for finding a file or folder inside the workspace folder, the one
 * place the service reads from and writes to. It lists what the backend
 * lists; nothing outside the workspace is ever shown.
 */
export function WorkspaceBrowser({ kind, onSelect, onClose }: WorkspaceBrowserProps) {
  const [folder, setFolder] = useState('/');
  const { listing, error } = useWorkspaceListing(folder, kind);
  const dialog = useRef<HTMLDivElement>(null);
  const title = kind === 'file' ? 'Choose an architecture file' : 'Choose a folder to save into';

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
      <div ref={dialog} className="mdd-generate__dialog" role="dialog" aria-modal="true" aria-label={title} tabIndex={-1}>
        <header className="mdd-generate__dialog-header">
          <h2>{title}</h2>
          <Button variant="ghost" size="sm" aria-label="Close" onClick={onClose}>
            <Icon name="X" size={16} />
          </Button>
        </header>
        <p className="mdd-generate__dialog-path" aria-live="polite">
          Workspace {listing ? listing.path : folder}
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
            {listing.entries.map((entry) => (
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
            ))}
            {listing.entries.length === 0 && (
              <li className="mdd-generate__hint">{kind === 'file' ? 'No .swarch files here.' : 'No folders here.'}</li>
            )}
          </ul>
        )}
        {listing?.truncated && <p className="mdd-generate__hint">Only the first entries are shown.</p>}

        {kind === 'folder' && (
          <footer className="mdd-generate__dialog-footer">
            <Button variant="primary" disabled={!listing} onClick={() => listing && onSelect(listing.path)}>
              Select this folder
            </Button>
          </footer>
        )}
      </div>
    </div>
  );
}
