import { useEffect, useState } from 'react';
import { InputPanel } from '../features/generate/InputPanel';
import { InputHeader } from '../features/generate/PipelineHeader';
import { ResultPanel } from '../features/generate/ResultPanel';
import { WorkspaceBrowser } from '../features/generate/WorkspaceBrowser';
import { useGenerate } from '../features/generate/useGenerate';
import './generate-screen.css';

interface GenerateScreenProps {
  // A saved pipeline to bring back; omitted for a new one.
  pipelineId?: string;
  onBack: () => void;
}

/**
 * GenerateScreen - the "Generate CI/CD Pipeline" screen: inputs on the left
 * (the model file, the platforms, where to save), the pipeline on the right.
 * There is deliberately no run, chat or stage review here (that is the
 * platform-integration screen's job). Beyond checking the file and the
 * pipeline, the only thing kept is the list of pipelines on the screen
 * before this one.
 */
export default function GenerateScreen({ pipelineId, onBack }: GenerateScreenProps) {
  const state = useGenerate();
  const { generation, model, openSaved } = state;
  const [browsing, setBrowsing] = useState<'file' | 'folder' | null>(null);

  useEffect(() => {
    if (pipelineId) void openSaved(pipelineId);
  }, [pipelineId, openSaved]);

  const handleSelect = (path: string) => {
    if (browsing === 'file') void model.loadFromPath(path);
    else generation.setSaveTo(path);
    setBrowsing(null);
  };

  return (
    <div className="mdd-generate">
      <InputHeader onBack={onBack} />
      <div className="mdd-generate__body">
        {state.opening && (
          <p className="mdd-generate__hint" role="status">
            Opening your pipeline…
          </p>
        )}
        {state.openError && (
          <p className="mdd-generate__error" role="alert">
            {state.openError.message}
          </p>
        )}
        <div className="mdd-generate__panels">
          <InputPanel state={state} onBrowse={setBrowsing} />
          <ResultPanel
            generating={generation.generating}
            generatingNames={generation.generatingNames}
            results={generation.results}
            kept={generation.pipelineId !== null}
            error={generation.generateError}
            validations={generation.validations}
            validating={generation.validating}
            validateErrors={generation.validateErrors}
            onValidate={(platformId) => void generation.validate(platformId)}
          />
        </div>
      </div>
      {browsing && <WorkspaceBrowser kind={browsing} onSelect={handleSelect} onClose={() => setBrowsing(null)} />}
    </div>
  );
}
