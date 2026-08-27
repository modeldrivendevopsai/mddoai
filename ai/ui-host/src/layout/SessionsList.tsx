import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getSessions, closeSession } from '../services/sessions.service';
import type { Session, SessionType } from '../services/sessions.service';
import { Icon } from 'design-system';
import './sessions-list.css';

/**
 * SessionsList — renders the open-sessions list for whichever sidebar tab
 * is active. Sourced entirely from sessions.service.ts; swapping that
 * service's mock data for a real API call requires no change here.
 */
interface SessionsListProps {
  activeTab: SessionType;
}

// Which run is current (the blue highlight) and the list itself both change
// from actions taken entirely outside this component — starting, approving,
// or restarting a run on IntegrationScreen. A one-time fetch on tab-switch
// goes stale the moment any of those happen, since nothing here would ever
// trigger a refetch otherwise. Polls on the same "MVP: poll, don't add a
// websocket" basis as useIntegration's own event polling, just on a slower
// interval since this list is far less latency-sensitive than live events.
const POLL_INTERVAL_MS = 4000;

export function SessionsList({ activeTab }: SessionsListProps) {
  const [sessions, setSessions] = useState<Session[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    let cancelled = false;
    function load() {
      getSessions(activeTab).then((data) => {
        if (!cancelled) setSessions(data);
      });
    }
    load();
    const id = setInterval(load, POLL_INTERVAL_MS);
    return () => {
      cancelled = true;
      clearInterval(id);
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
          onClick={() => navigate(`/integration?run=${s.id}`)}
          onKeyDown={(e) => {
            if (e.key === 'Enter' || e.key === ' ') navigate(`/integration?run=${s.id}`);
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
