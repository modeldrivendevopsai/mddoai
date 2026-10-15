import { Icon } from 'design-system';
import type { GenerationResult, PipelineValidation } from '@/services/generator.service';
import { GENERATE_COPY } from '../../config/generate.config';
import { IssueList } from './IssueList';
import { PlatformResult } from './PlatformResult';
import type { StepError } from './stepError';

interface ResultPanelProps {
  generating: boolean;
  generatingNames: string[];
  results: GenerationResult[] | null;
  // Whether what was generated was also added to the pipelines list.
  kept: boolean;
  error: StepError | null;
  validations: Record<string, PipelineValidation>;
  validating: string[];
  validateErrors: Record<string, StepError>;
  onValidate: (platformId: string) => void;
}

/**
 * The right-hand panel: what is happening to the pipeline and then the
 * pipeline itself, one block per platform.
 */
export function ResultPanel({
  generating,
  generatingNames,
  results,
  kept,
  error,
  validations,
  validating,
  validateErrors,
  onValidate,
}: ResultPanelProps) {
  if (generating) {
    return (
      <div className="mdd-generate__panel mdd-generate__panel--centered" role="status">
        <span className="mdd-generate__spinner" aria-hidden="true">
          <Icon name="LoaderCircle" size={26} />
        </span>
        <p className="mdd-generate__hint">{GENERATE_COPY.result.running(generatingNames)}</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="mdd-generate__panel" role="alert">
        <p className="mdd-generate__error">{error.message}</p>
        <IssueList issues={error.issues} />
      </div>
    );
  }

  if (!results) {
    return (
      <div className="mdd-generate__panel">
        <p className="mdd-generate__placeholder">{GENERATE_COPY.result.placeholder}</p>
      </div>
    );
  }

  return (
    <div className="mdd-generate__panel mdd-generate__panel--results">
      {results.map((result) => (
        <PlatformResult
          key={result.platform.id}
          result={result}
          validation={validations[result.platform.id]}
          validating={validating.includes(result.platform.id)}
          validateError={validateErrors[result.platform.id]}
          onValidate={() => onValidate(result.platform.id)}
        />
      ))}
      {!kept && results.some((result) => result.pipeline !== null) && (
        <p className="mdd-generate__hint">This pipeline could not be added to your pipelines list.</p>
      )}
    </div>
  );
}
