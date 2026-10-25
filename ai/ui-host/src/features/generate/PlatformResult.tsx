import { Button, CodeBlock, StatusPill } from 'design-system';
import type { GenerationResult, PipelineValidation } from '@/services/generator.service';
import { downloadName, downloadText } from './download';
import { seconds } from './format';
import { PipelineChecks } from './PipelineChecks';
import type { StepError } from './stepError';

interface PlatformResultProps {
  result: GenerationResult;
  validation: PipelineValidation | undefined;
  validating: boolean;
  validateError: StepError | undefined;
  onValidate: () => void;
}

/**
 * One platform's outcome: the pipeline itself first, since it is the point
 * of the screen, with where it was saved, a way to validate it, and the
 * intermediate models the engine passed through tucked behind a disclosure
 * for anyone curious how it was built.
 */
export function PlatformResult({ result, validation, validating, validateError, onValidate }: PlatformResultProps) {
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

  const savedAs = downloadName(result.output_filename);

  return (
    <section className="mdd-generate__result" aria-label={platform.name}>
      <div className="mdd-generate__row">
        <StatusPill variant="success">
          {platform.name} pipeline generated{result.duration_ms !== null && ` in ${seconds(result.duration_ms)}`}
        </StatusPill>
      </div>

      <div className="mdd-generate__code">
        <CodeBlock code={pipeline} title={result.output_filename} lang="yaml" />
      </div>

      <div className="mdd-generate__row">
        <Button
          variant="secondary"
          size="sm"
          icon="Download"
          onClick={() => downloadText(savedAs, pipeline)}
        >
          Download {result.output_filename}
        </Button>
        <Button variant="secondary" size="sm" disabled={validating} onClick={onValidate}>
          {validating ? 'Validating…' : validation ? 'Validate again' : 'Validate pipeline'}
        </Button>
      </div>

      {result.output_filename.includes('/') ? (
        <p className="mdd-generate__hint">
          {platform.name} reads this file from {result.output_filename}. The download is the file on its own; put it
          there.
        </p>
      ) : (
        savedAs !== result.output_filename && (
          <p className="mdd-generate__hint">
            {platform.name} needs this file to be named {result.output_filename}. Your browser may save a download
            without the leading dot; rename it if it does.
          </p>
        )
      )}
      {result.saved_to && (
        <p className="mdd-generate__hint">
          Saved to {result.saved_to}
          {result.replaced && ' (replaced the file that was there)'}
        </p>
      )}
      {result.save_error && (
        <p className="mdd-generate__error" role="alert">
          Could not save the file: {result.save_error}
        </p>
      )}

      {validateError && (
        <p className="mdd-generate__error" role="alert">
          {validateError.message}
        </p>
      )}
      {validation && <PipelineChecks validation={validation} />}

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
