import { StatusPill } from 'design-system';
import type { ModelIssue } from '@/services/generator.service';

/**
 * Problems found with a model, one per line, each tagged error or warning.
 * Shared by the "check" step (what's wrong with the file) and the
 * "generate" step (why a pipeline wasn't produced), which report the same
 * kind of issue.
 */
export function IssueList({ issues }: { issues: ModelIssue[] }) {
  if (issues.length === 0) return null;
  return (
    <ul className="mdd-generate__issues">
      {issues.map((issue, index) => (
        <li key={index}>
          <StatusPill variant={issue.severity === 'ERROR' ? 'danger' : 'warning'}>
            {issue.severity === 'ERROR' ? 'Error' : 'Warning'}
          </StatusPill>
          <span>{issue.message}</span>
        </li>
      ))}
    </ul>
  );
}
