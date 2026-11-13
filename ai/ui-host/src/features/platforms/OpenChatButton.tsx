import type { PlatformSummary } from 'orchestrator-types';
import { PLATFORMS_COPY } from '../../config/platforms.config';

interface OpenChatButtonProps {
  platform: PlatformSummary;
  // Opens the chat (the run) that made a platform, by the run's id.
  onOpenRun: (runId: string) => void;
}

// Why no chat made a platform: it came with the stack, or it was published without one.
function noChatReason(platform: PlatformSummary): string {
  const copy = PLATFORMS_COPY.source.noChat;
  return platform.published_at ? copy.unrecorded : copy.shipped;
}

/**
 * The button that opens the chat that made a platform. Where no chat did, it
 * stays in the row, greyed out, with the reason as its description. It is
 * aria-disabled and not disabled, so the keyboard and a screen reader still reach
 * it and its reason. Pressing it never reaches the row, so it does not open the platform.
 */
export function OpenChatButton({ platform, onOpenRun }: OpenChatButtonProps) {
  const runId = platform.source_run_id;
  return (
    <button
      type="button"
      className="mdd-platforms-list__link mdd-platforms-list__chat"
      aria-label={PLATFORMS_COPY.source.openChatOf(platform.name)}
      aria-disabled={runId ? undefined : true}
      title={runId ? undefined : noChatReason(platform)}
      onClick={(event) => {
        event.stopPropagation();
        if (runId) onOpenRun(runId);
      }}
    >
      {PLATFORMS_COPY.source.openChat}
    </button>
  );
}
