import { useEffect, useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { getSessions, closeSession } from '../services/sessions.service';
import type { Session, SessionType } from '../services/sessions.service';
import { Icon } from 'design-system';
import { INTEGRATION_QUERY, integrationRunPath, isNewRunRequested } from '../config/routes.config';
import { SIDEBAR_POLL_INTERVAL_MS } from '../config/sidebar.config';
import './sessions-list.css';

/**
 * SessionsList — renders the open-sessions list: the platform-integration
 * runs. Sourced entirely from sessions.service.ts; swapping that service's
 * mock data for a real API call requires no change here.
 *
 * The highlighted row is the chat on screen: the run named in the address,
 * or, when the address names none, the run that is current. It is not simply
 * the backend's current run, because opening another row only shows that run,
 * it does not make it current.
 *
 * The list itself changes from actions taken entirely outside this component:
 * starting, approving, or restarting a run on IntegrationScreen. A one-time
 * fetch on tab-switch goes stale the moment any of those happen, so the list
 * is polled (see SIDEBAR_POLL_INTERVAL_MS).
 */
interface SessionsListProps {
  activeTab: SessionType;
}

export function SessionsList({ activeTab }: SessionsListProps) {
  const [sessions, setSessions] = useState<Session[]>([]);
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  // A blank run on screen is no chat yet, so no row is on screen.
  const selectedId = isNewRunRequested(searchParams)
    ? undefined
    : (searchParams.get(INTEGRATION_QUERY.run) ?? sessions.find((s) => s.state === 'selected')?.id);

  useEffect(() => {
    let cancelled = false;
    function load() {
      getSessions(activeTab).then((data) => {
        if (!cancelled) setSessions(data);
      });
    }
    load();
    const id = setInterval(load, SIDEBAR_POLL_INTERVAL_MS);
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
          aria-current={s.id === selectedId ? 'true' : undefined}
          className={[
            'mdd-sessions__item',
            `mdd-sessions__item--${s.type}`,
            s.id === selectedId ? 'is-selected' : '',
            s.state === 'attention' ? 'is-attention' : '',
          ]
            .filter(Boolean)
            .join(' ')}
          onClick={() => navigate(integrationRunPath(s.id))}
          onKeyDown={(e) => {
            if (e.key === 'Enter' || e.key === ' ') navigate(integrationRunPath(s.id));
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
