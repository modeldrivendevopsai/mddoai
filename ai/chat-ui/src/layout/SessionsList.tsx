import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getSessions, closeSession } from '../services/sessions.service';
import type { Session, SessionType } from '../services/sessions.service';
import { Icon } from '../design-system';
import './sessions-list.css';

// Mock session data has no real pipeline/platform run behind it yet (see
// sessions.service.ts), so clicking a row can't resume real state, only
// route to the screen that type of session belongs to.
const ROUTE_BY_TYPE: Record<SessionType, string> = {
  pipeline: '/',
  platform: '/platforms/new',
};

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
  const navigate = useNavigate();

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
          role="button"
          tabIndex={0}
          className={[
            'mdd-sessions__item',
            `mdd-sessions__item--${s.type}`,
            s.state === 'selected' ? 'is-selected' : '',
            s.state === 'attention' ? 'is-attention' : '',
          ]
            .filter(Boolean)
            .join(' ')}
          onClick={() => navigate(ROUTE_BY_TYPE[s.type])}
          onKeyDown={(e) => {
            if (e.key === 'Enter' || e.key === ' ') navigate(ROUTE_BY_TYPE[s.type]);
          }}
        >
          <span className="mdd-sessions__name">{s.name}</span>
          {s.state === 'attention' && (
            <Icon name="AlertTriangle" size={12} className="mdd-sessions__warn" />
          )}
          <button
            className="mdd-sessions__close"
            aria-label={`Close ${s.name}`}
            onClick={(e) => {
              e.stopPropagation();
              handleClose(s.id);
            }}
          >
            <Icon name="X" size={12} />
          </button>
        </div>
      ))}
    </div>
  );
}
