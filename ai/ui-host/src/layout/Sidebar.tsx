import { useLayoutEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { Tabs, Icon } from 'design-system';
import { ROUTES } from '../config/routes.config';
import { SIDEBAR_TABS } from '../config/sidebar.config';
import { SidebarActions } from './SidebarActions';
import { PipelinesList } from './PipelinesList';
import { SessionsList } from './SessionsList';
import { useSidebarTab } from './sidebarTab';
import type { SessionType } from '../services/sessions.service';
import './sidebar.css';

/**
 * Sidebar — collapsible nav panel. The tab set comes from config. Each tab
 * type has its own list (see TabList), and the compiler requires one for every
 * tab type, so a new tab cannot be added without it. The active tab is shared
 * (see useSidebarTab), so the screens can follow it.
 */
interface SidebarProps {
  onAction?: (actionId: string) => void;
  // Opens a generated pipeline; where that goes is the caller's to decide.
  onOpenPipeline: (pipelineId: string) => void;
  // The generated pipeline on screen, if one is.
  openPipelineId: string | undefined;
}

type TabListProps = Pick<SidebarProps, 'onOpenPipeline' | 'openPipelineId'> & { tab: SessionType };

function TabList({ tab, onOpenPipeline, openPipelineId }: TabListProps) {
  switch (tab) {
    case 'pipeline':
      return <PipelinesList openId={openPipelineId} onOpen={onOpenPipeline} />;
    case 'platform':
      return <SessionsList activeTab={tab} />;
  }
}

export function Sidebar({ onAction, onOpenPipeline, openPipelineId }: SidebarProps) {
  const { activeTab, setActiveTab } = useSidebarTab();
  const [collapsed, setCollapsed] = useState(false);
  const { pathname } = useLocation();

  // Landing on a platforms-mode screen (reached via the StartScreen's "Add
  // or update a CI/CD platform" card, or a sidebar action on that tab) or
  // on any pipelines-mode screen (the "Generate a CI/CD pipeline" card, or "New
  // pipeline") makes it obvious which tab you actually want to see - switch to it
  // once, the same way opening a document switches focus to it, without
  // fighting a later manual tab click on this same visit. useLayoutEffect,
  // not an initial state: App.tsx's routing keeps this component mounted
  // across route changes (same position in the tree every time), so the tab
  // would otherwise only ever be set once and never react to a later route
  // change.
  useLayoutEffect(() => {
    if (pathname === ROUTES.platforms || pathname.startsWith(`${ROUTES.platforms}/`) || pathname === ROUTES.integration) {
      setActiveTab('platform');
    }
    if (pathname === ROUTES.pipelines || pathname.startsWith(`${ROUTES.pipelines}/`)) setActiveTab('pipeline');
  }, [pathname, setActiveTab]);

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
          <TabList tab={activeTab} onOpenPipeline={onOpenPipeline} openPipelineId={openPipelineId} />
        </div>
      )}
    </aside>
  );
}
