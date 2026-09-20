import { useLayoutEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { Tabs, Icon } from 'design-system';
import { SIDEBAR_TABS } from '../config/sidebar.config';
import { SidebarActions } from './SidebarActions';
import { SessionsList } from './SessionsList';
import type { SessionType } from '../services/sessions.service';
import './sidebar.css';

/**
 * Sidebar — collapsible nav panel. Tab set comes from config, not
 * hardcoded here, so adding a third tab later is a config change only.
 */
interface SidebarProps {
  onAction?: (actionId: string) => void;
}

export function Sidebar({ onAction }: SidebarProps) {
  const [activeTab, setActiveTab] = useState<SessionType>(SIDEBAR_TABS[0].id);
  const [collapsed, setCollapsed] = useState(false);
  const { pathname } = useLocation();

  // Landing on /integration (today, only ever reached via the StartScreen's
  // "Add or update a CI/CD platform" card, or a sidebar action that's
  // already on this tab) makes it obvious which tab you actually want to
  // see - switch to it once, the same way opening a document switches
  // focus to it, without fighting a later manual tab click on this same
  // visit. useLayoutEffect, not useState's own lazy initializer: App.tsx's
  // routing keeps this component mounted across "/" <-> "/integration"
  // navigation (same position in the tree both times), so activeTab only
  // ever gets set once at mount otherwise and never reacts to a later
  // route change.
  useLayoutEffect(() => {
    if (pathname === '/integration') setActiveTab('platform');
  }, [pathname]);

  return (
    <aside className={`mdd-sidebar mdd-sidebar--${activeTab} ${collapsed ? 'is-collapsed' : ''}`}>
      <div className="mdd-sidebar__header">
        <button
          className="mdd-sidebar__collapse"
          onClick={() => setCollapsed((c) => !c)}
          aria-label={collapsed ? 'Show panel' : 'Hide panel'}
        >
          <Icon name={collapsed ? 'ChevronsRight' : 'ChevronsLeft'} size={14} />
          {!collapsed && <span>Hide panel</span>}
        </button>
      </div>

      {!collapsed && (
        <Tabs
          items={SIDEBAR_TABS}
          activeId={activeTab}
          onChange={(id) => setActiveTab(id as SessionType)}
        />
      )}

      <SidebarActions activeTab={activeTab} collapsed={collapsed} onAction={onAction} />

      {!collapsed && (
        <div className="mdd-sidebar__sessions">
          <SessionsList activeTab={activeTab} />
        </div>
      )}
    </aside>
  );
}
