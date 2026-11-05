import { useMemo, useState } from 'react';
import type { ReactNode } from 'react';
import { SIDEBAR_TABS } from '../config/sidebar.config';
import type { SessionType } from '../services/sessions.service';
import { SidebarTabContext } from './sidebarTab';

/**
 * Holds the sidebar's active tab above the routes, so it survives moving
 * between screens and the sidebar and the screens read the same one.
 */
export function SidebarTabProvider({ children }: { children: ReactNode }) {
  const [activeTab, setActiveTab] = useState<SessionType>(SIDEBAR_TABS[0].id);
  const value = useMemo(() => ({ activeTab, setActiveTab }), [activeTab]);
  return <SidebarTabContext.Provider value={value}>{children}</SidebarTabContext.Provider>;
}
