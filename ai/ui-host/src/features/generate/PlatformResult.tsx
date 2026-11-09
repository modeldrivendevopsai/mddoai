import { Button, CodeBlock, StatusPill } from 'design-system';
import type { GenerationResult, PipelineValidation } from '@/services/generator.service';
import { downloadName, downloadText } from './download';
import { seconds } from './format';
import { ValidationStatus } from './ValidationStatus';
import type { StepError } from './stepError';

interface PlatformResultProps {
  result: GenerationResult;
  validation: PipelineValidation | undefined;
  validating: boolean;
  validateError: StepError | undefined;
  onRetryValidation: () => void;
}

/**
 * One platform's outcome: how the pipeline checked out, the Download button,
 * the pipeline itself, and the intermediate models the engine passed through
 * tucked behind a disclosure for anyone curious how it was built. Download
 * comes before the code so it is in view whatever the pipeline's length.
 */
export function PlatformResult({ result, validation, validating, validateError, onRetryValidation }: PlatformResultProps) {
  const { platform, pipeline } = result;

  if (pipeline === null) {
    return (
      <section className="mdd-generate__result" aria-label={platform.name}>
        <StatusPill variant="danger">{platform.name} pipeline could not be generated</StatusPill>
        <p className="mdd-generate__error" role="alert">
          {result.error}
        </p>
      </section>
    );
  }

  const downloadAs = downloadName(result.output_filename);

  return (
    <section className="mdd-generate__result" aria-label={platform.name}>
      <div className="mdd-generate__result-head">
        <StatusPill variant="success">
          {platform.name} pipeline generated{result.duration_ms !== null && ` in ${seconds(result.duration_ms)}`}
        </StatusPill>
        <ValidationStatus
          validation={validation}
          validating={validating}
          error={validateError}
          onRetry={onRetryValidation}
        />
      </div>

      <div className="mdd-generate__row">
        <Button
          variant="secondary"
          size="sm"
          icon="Download"
          onClick={() => downloadText(downloadAs, pipeline)}
        >
          Download {result.output_filename}
        </Button>
      </div>

      {downloadAs !== result.output_filename && (
        <p className="mdd-generate__hint">
          {platform.name} needs this file to be named {result.output_filename}. Your browser may save a download
          without the leading dot; rename it if it does.
        </p>
      )}

      <div className="mdd-generate__code">
        <CodeBlock code={pipeline} title={result.output_filename} lang="yaml" />
      </div>

      {result.intermediates.length > 0 && (
        <details className="mdd-generate__details">
          <summary>How it was built</summary>
          <p className="mdd-generate__hint">
            Your architecture was turned into a platform-independent model, then into a {platform.name} model, and
            finally into the pipeline above.
          </p>
          {result.intermediates.map((model) => (
            <CodeBlock key={model.id} code={model.content} title={`${model.title} (${model.filename})`} lang="xml" />
          ))}
        </details>
      )}
    </section>
  );
}
