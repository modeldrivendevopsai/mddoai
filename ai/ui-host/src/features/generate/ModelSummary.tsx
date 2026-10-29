import { StatusPill } from 'design-system';
import type { Inspection } from '@/services/generator.service';
import { IssueList } from './IssueList';
import { elementLabel, labelFor } from './format';

interface ModelSummaryProps {
  inspecting: boolean;
  inspection: Inspection | null;
}

/**
 * What the loaded file says, in plain words, and whether the real model
 * checker accepts it. The verdict and any problems are always visible; the
 * file's own values sit behind a disclosure, since most of the time the
 * verdict is all anyone needs.
 */
export function ModelSummary({ inspecting, inspection }: ModelSummaryProps) {
  if (inspecting) {
    return (
      <p className="mdd-generate__hint" role="status">
        Checking your file…
      </p>
    );
  }
  // A check that failed has already said so, next to the path field.
  if (!inspection) return null;

  const { element, attributes, model_validation: validation } = inspection;
  const problems = validation.issues.filter((issue) => issue.severity === 'ERROR').length;

  return (
    <div className="mdd-generate__summary">
      <div className="mdd-generate__row">
        {validation.valid ? (
          <StatusPill variant="success">This model is valid</StatusPill>
        ) : (
          <StatusPill variant="danger">
            {problems === 1 ? '1 problem to fix' : `${problems} problems to fix`}
          </StatusPill>
        )}
        {element && <span className="mdd-generate__hint">Found a {elementLabel(element)} model.</span>}
      </div>

      <IssueList issues={validation.issues} />

      {attributes.length > 0 && (
        <details className="mdd-generate__details">
          <summary>What we found in your file</summary>
          <dl className="mdd-generate__facts">
            {attributes.map((attribute) => (
              <div key={attribute.name} className="mdd-generate__fact">
                <dt>{labelFor(attribute.name)}</dt>
                <dd>
                  {attribute.value === '' ? (
                    <span className="mdd-generate__unset">Not set</span>
                  ) : (
                    attribute.value
                  )}
                </dd>
              </div>
            ))}
          </dl>
        </details>
      )}
    </div>
  );
}
