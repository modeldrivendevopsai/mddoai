import { Button } from 'design-system';
import { SIDEBAR_ACTIONS } from '../config/sidebar.config';
import type { SessionType } from '../services/sessions.service';

/**
 * SidebarActions — renders the action buttons for whichever tab is active,
 * sourced from SIDEBAR_ACTIONS config. Adding a third action to a tab is a
 * config edit, not a component edit.
 */
interface SidebarActionsProps {
  activeTab: SessionType;
  collapsed: boolean;
  onAction?: (actionId: string) => void;
}

export function SidebarActions({ activeTab, collapsed, onAction }: SidebarActionsProps) {
  const actions = SIDEBAR_ACTIONS[activeTab] ?? [];

  return (
    <div className="mdd-sidebar-actions">
      {actions.map((action) => (
        <Button
          key={action.id}
          variant="ghost"
          full
          icon={action.icon}
          disabled={action.comingSoon}
          title={action.comingSoon ? 'Not built yet' : undefined}
          onClick={() => onAction?.(action.id)}
        >
          {!collapsed && (action.comingSoon ? `${action.label} (coming soon)` : action.label)}
        </Button>
      ))}
    </div>
  );
}
