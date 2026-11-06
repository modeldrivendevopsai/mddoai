import { createContext, useContext } from 'react';
import type { SessionType } from '../services/sessions.service';

/**
 * Which of the sidebar's tabs is on. It is shared, not the sidebar's own, so a
 * screen can follow the tab: the start screen shows platforms or pipelines
 * depending on it.
 */
export interface SidebarTabState {
  activeTab: SessionType;
  setActiveTab: (tab: SessionType) => void;
}

export const SidebarTabContext = createContext<SidebarTabState | null>(null);

export function useSidebarTab(): SidebarTabState {
  const state = useContext(SidebarTabContext);
  if (state === null) throw new Error('useSidebarTab needs a SidebarTabProvider above it.');
  return state;
}
