import { StatusPill } from 'design-system';
import type { PipelineCheck, PipelineValidation } from '@/services/generator.service';

const CHECK_PILL: Record<PipelineCheck['status'], { variant: 'success' | 'danger' | 'info'; label: string }> = {
  pass: { variant: 'success', label: 'Passed' },
  fail: { variant: 'danger', label: 'Failed' },
  skipped: { variant: 'info', label: 'Skipped' },
};

/**
 * A plain-language checklist for a generated pipeline file, not a raw schema
 * dump: each row says what was checked, and a failing row lists what to look
 * at, a skipped one why it was not made.
 */
export function PipelineChecks({ validation }: { validation: PipelineValidation }) {
  return (
    <ul className="mdd-generate__checks">
      {validation.checks.map((check) => (
        <li key={check.id}>
          <div className="mdd-generate__check-head">
            <StatusPill variant={CHECK_PILL[check.status].variant}>{CHECK_PILL[check.status].label}</StatusPill>
            <span>{check.title}</span>
          </div>
          {check.details.length > 0 && (
            <ul className="mdd-generate__check-details">
              {check.details.map((detail, index) => (
                <li key={index}>{detail}</li>
              ))}
            </ul>
          )}
        </li>
      ))}
    </ul>
  );
}
