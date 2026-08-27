import type { ReactNode } from 'react';
import { TopBar } from './TopBar';
import { Sidebar } from './Sidebar';
import 'design-system/tokens.css';
import './app-shell.css';

/**
 * AppShell — persistent frame (sidebar + top bar) for every page. Pages
 * are passed in as `children` and render into the content area; the shell
 * itself never needs to change as new pages (pipeline flow, platform
 * onboarding, stage-review screens) get added.
 */
interface AppShellProps {
  children: ReactNode;
  onSidebarAction?: (actionId: string) => void;
}

export function AppShell({ children, onSidebarAction }: AppShellProps) {
  return (
    <div className="mdd-app-shell">
      <Sidebar onAction={onSidebarAction} />
      <div className="mdd-app-shell__main">
        <TopBar />
        <div className="mdd-app-shell__content">{children}</div>
      </div>
    </div>
  );
}
