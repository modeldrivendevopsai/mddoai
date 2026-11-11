import { useId, useState } from 'react';
import { Icon } from 'design-system';
import { SIDEBAR_SESSIONS_COPY } from '../config/sidebar.config';
import type { Session } from '../services/sessions.service';
import { sessionDetail } from './sessionDetail';

interface SessionRowProps {
  session: Session;
  // The chat on screen.
  selected: boolean;
  onOpen: () => void;
  // Deletes the run for good. Rejects with the reason when the backend refuses.
  onDelete: () => Promise<void>;
}

/**
 * One run in the sidebar: its platform, where it is and how long ago it began,
 * and a cross that deletes it. Deleting cannot be undone, so the cross asks
 * first, in the row itself. The current run has no cross: it is open on screen
 * and may be worked on, so it is restarted or left, not deleted. A refusal (a
 * platform was published from the run) is shown in the row with its reason.
 */
export function SessionRow({ session, selected, onOpen, onDelete }: SessionRowProps) {
  const copy = SIDEBAR_SESSIONS_COPY;
  const [confirming, setConfirming] = useState(false);
  const [deleting, setDeleting] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const canDelete = session.state !== 'selected';
  const detailId = useId();

  async function confirmDelete() {
    setDeleting(true);
    setError(null);
    try {
      await onDelete();
      // The row is gone from the list now, so there is nothing left to update.
    } catch (err) {
      setError(err instanceof Error && err.message ? err.message : copy.deleteFailed);
      setConfirming(false);
      setDeleting(false);
    }
  }

  return (
    <div
      role="button"
      tabIndex={0}
      aria-label={session.name}
      aria-describedby={detailId}
      aria-current={selected ? 'true' : undefined}
      className={[
        'mdd-sessions__item',
        `mdd-sessions__item--${session.type}`,
        selected ? 'is-selected' : '',
        session.state === 'attention' ? 'is-attention' : '',
      ]
        .filter(Boolean)
        .join(' ')}
      onClick={onOpen}
      onKeyDown={(e) => {
        // A key pressed on a button inside the row is that button's, not a request to open the run.
        if (e.target !== e.currentTarget) return;
        if (e.key === 'Enter' || e.key === ' ') onOpen();
      }}
    >
      <span className="mdd-sessions__text">
        <span className="mdd-sessions__name">{session.name}</span>
        {error ? (
          <span id={detailId} className="mdd-sessions__meta mdd-sessions__meta--error" role="alert">
            {error}
          </span>
        ) : (
          <span id={detailId} className={confirming ? 'mdd-sessions__meta mdd-sessions__meta--asking' : 'mdd-sessions__meta'}>
            {confirming ? copy.confirm : sessionDetail(session)}
          </span>
        )}
      </span>
      {session.state === 'attention' && <Icon name="AlertTriangle" size={12} className="mdd-sessions__warn" />}
      {canDelete &&
        (confirming ? (
          <span className="mdd-sessions__confirm" role="group" aria-label={copy.confirm}>
            <button
              type="button"
              className="mdd-sessions__confirm-yes"
              disabled={deleting}
              onClick={(e) => {
                e.stopPropagation();
                void confirmDelete();
              }}
            >
              {copy.confirmYes}
            </button>
            <button
              type="button"
              className="mdd-sessions__confirm-no"
              disabled={deleting}
              onClick={(e) => {
                e.stopPropagation();
                setConfirming(false);
              }}
            >
              {copy.confirmNo}
            </button>
          </span>
        ) : (
          <button
            type="button"
            className="mdd-sessions__close"
            aria-label={copy.deleteLabel(session.name)}
            title={copy.deleteTitle}
            onClick={(e) => {
              e.stopPropagation();
              setError(null);
              setConfirming(true);
            }}
          >
            <Icon name="X" size={12} />
          </button>
        ))}
    </div>
  );
}
