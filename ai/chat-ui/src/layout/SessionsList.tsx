import { useEffect, useState } from 'react';
import { getSessions, closeSession } from '../services/sessions.service';
import type { Session, SessionType } from '../services/sessions.service';
import { Icon } from '../design-system';
import './sessions-list.css';

/**
 * SessionsList — renders the open-sessions list for whichever sidebar tab
 * is active. Sourced entirely from sessions.service.ts; swapping that
 * service's mock data for a real API call requires no change here.
 */
interface SessionsListProps {
  activeTab: SessionType;
}

export function SessionsList({ activeTab }: SessionsListProps) {
  const [sessions, setSessions] = useState<Session[]>([]);

  useEffect(() => {
    let cancelled = false;
    getSessions(activeTab).then((data) => {
      if (!cancelled) setSessions(data);
    });
    return () => {
      cancelled = true;
    };
  }, [activeTab]);

  function handleClose(id: string) {
    setSessions((prev) => prev.filter((s) => s.id !== id));
    closeSession(id);
  }

  return (
    <div className="mdd-sessions">
      <div className="mdd-sessions__label">Open sessions</div>

      {sessions.length === 0 && (
        <div className="mdd-sessions__empty">No open sessions</div>
      )}

      {sessions.map((s) => (
        <div
          key={s.id}
          className={[
            'mdd-sessions__item',
            `mdd-sessions__item--${s.type}`,
            s.state === 'selected' ? 'is-selected' : '',
            s.state === 'attention' ? 'is-attention' : '',
          ]
            .filter(Boolean)
            .join(' ')}
        >
          <span className="mdd-sessions__name">{s.name}</span>
          {s.state === 'attention' && (
            <Icon name="AlertTriangle" size={12} className="mdd-sessions__warn" />
          )}
          <button
            className="mdd-sessions__close"
            aria-label={`Close ${s.name}`}
            onClick={() => handleClose(s.id)}
          >
            <Icon name="X" size={12} />
          </button>
        </div>
      ))}
    </div>
  );
}
