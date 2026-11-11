import { useEffect, useRef, useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { getSessions, deleteSession } from '../services/sessions.service';
import type { Session, SessionType } from '../services/sessions.service';
import { INTEGRATION_QUERY, integrationRunPath, isNewRunRequested } from '../config/routes.config';
import { SIDEBAR_POLL_INTERVAL_MS } from '../config/sidebar.config';
import { SessionRow } from './SessionRow';
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
  // Runs this list has deleted. A poll that was already in flight when one was
  // deleted still carries it, and must not bring the row back.
  const deleted = useRef(new Set<string>());
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
        if (!cancelled) setSessions(data.filter((s) => !deleted.current.has(s.id)));
      });
    }
    load();
    const id = setInterval(load, SIDEBAR_POLL_INTERVAL_MS);
    return () => {
      cancelled = true;
      clearInterval(id);
    };
  }, [activeTab]);

  // The row leaves the list only once the backend has deleted the run, so a
  // refusal leaves it where it is.
  async function handleDelete(id: string) {
    await deleteSession(id);
    deleted.current.add(id);
    setSessions((prev) => prev.filter((s) => s.id !== id));
  }

  return (
    <div className="mdd-sessions">
      <div className="mdd-sessions__label">Open sessions</div>

      {sessions.length === 0 && (
        <div className="mdd-sessions__empty">No open sessions</div>
      )}

      {sessions.map((s) => (
        <SessionRow
          key={s.id}
          session={s}
          selected={s.id === selectedId}
          onOpen={() => navigate(integrationRunPath(s.id))}
          onDelete={() => handleDelete(s.id)}
        />
      ))}
    </div>
  );
}
