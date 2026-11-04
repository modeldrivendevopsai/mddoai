import { Button } from 'design-system';
import { GENERATE_COPY } from '../config/generate.config';
import { PipelinesTable } from '../features/generate/PipelinesTable';
import { PipelinesHeader } from '../features/generate/PipelineHeader';
import { usePipelines } from '../features/generate/usePipelines';
import './generate-screen.css';

interface PipelinesScreenProps {
  onBack: () => void;
  onNew: () => void;
  onOpen: (pipelineId: string) => void;
}

/**
 * PipelinesScreen - the first screen of the "Generate a CI/CD pipeline"
 * mode: the pipelines already generated, so someone coming back to their
 * work starts from it, with "+ New pipeline" for a fresh one.
 */
export default function PipelinesScreen({ onBack, onNew, onOpen }: PipelinesScreenProps) {
  const { pipelines, error, retry } = usePipelines();
  const copy = GENERATE_COPY.pipelines;

  return (
    <div className="mdd-generate">
      <PipelinesHeader onBack={onBack} />
      <div className="mdd-generate__body">
        <div className="mdd-generate__row">
          <Button variant="secondary" onClick={onNew}>
            {copy.newPipeline}
          </Button>
        </div>

        <h2 className="mdd-generate__section-title">{copy.listTitle}</h2>

        {error ? (
          <div className="mdd-generate__row" role="alert">
            <p className="mdd-generate__error">{error.message}</p>
            <Button variant="secondary" size="sm" onClick={retry}>
              {copy.retry}
            </Button>
          </div>
        ) : pipelines === null ? (
          <p className="mdd-generate__hint" role="status">
            {copy.loading}
          </p>
        ) : pipelines.length === 0 ? (
          <p className="mdd-generate__hint">{copy.empty}</p>
        ) : (
          <PipelinesTable pipelines={pipelines} onOpen={onOpen} />
        )}
      </div>
    </div>
  );
}
