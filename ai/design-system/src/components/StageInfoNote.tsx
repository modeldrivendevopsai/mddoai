import { StatusPill } from './StatusPill';

// Local, structurally-matching copy of integration_runner's own StageInfo
// shape (see stages/__init__.py's STAGE_DETAILS) - this package never
// imports orchestrator-types (see PromptBuilder/AttemptsBrowser's own
// types.ts for the same, established boundary), so every consumer's real
// StageDetail type just has to satisfy this shape structurally.
interface StageDetail {
  description: string;
  input: string;
  output: string;
  real: boolean;
}

interface StageInfoNoteProps {
  detail: StageDetail | null | undefined;
}

/**
 * StageInfoNote: what a pipeline stage really reads and produces, and
 * whether that output is genuinely derived from real input yet or still
 * fixed placeholder content. Sourced from a real backend field
 * (StageDetail, fetched once from GET /stages) rather than hardcoded prose
 * per stage panel, so a stage going from placeholder to real needs no UI
 * change here, only its own backend metadata entry to update. Renders
 * nothing while that metadata hasn't loaded yet.
 */
export function StageInfoNote({ detail }: StageInfoNoteProps) {
  if (!detail) return null;

  return (
    <div
      style={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between',
        gap: 'var(--space-2)',
        fontFamily: 'var(--font-sans)',
        fontSize: 'var(--text-xs)',
        color: 'var(--text-muted)',
      }}
    >
      <span>
        Reads {detail.input}, produces {detail.output}.
      </span>
      {!detail.real && (
        <StatusPill variant="warning" title={detail.description}>
          Placeholder
        </StatusPill>
      )}
    </div>
  );
}
