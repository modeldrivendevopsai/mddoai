import { Button } from 'design-system';
import { GENERATE_COPY } from '../../config/generate.config';
import { ModelSummary } from './ModelSummary';
import { PathField } from './PathField';
import { PlatformPicker } from './PlatformPicker';
import type { useGenerate } from './useGenerate';

interface InputPanelProps {
  state: ReturnType<typeof useGenerate>;
  onBrowse: (kind: 'file' | 'folder') => void;
}

// Why Generate is unavailable, in the order a person would fix things; null
// when it is available, or when the answer is still on its way.
function blockedReason({ source, model, generation }: ReturnType<typeof useGenerate>): string | null {
  const copy = GENERATE_COPY.input.blocked;
  if (!source) return copy.noFile;
  if (model.inspecting || !model.inspection) return null;
  if (!model.inspection.model_validation.valid) return copy.invalid;
  if (generation.chosen.length === 0) return copy.noPlatform;
  return null;
}

/**
 * The left-hand panel: which model, which platforms, where to save, and the
 * Generate button. Locked while a generation is running.
 */
export function InputPanel({ state, onBrowse }: InputPanelProps) {
  const { source, model, generation, platforms, platformsError, retryPlatforms, canGenerate } = state;
  const copy = GENERATE_COPY.input;
  const busy = generation.generating;
  const reason = busy ? null : blockedReason(state);

  return (
    <div className="mdd-generate__panel mdd-generate__form">
      <PathField
        label={copy.inputPath}
        note={source && !model.loadError && <span className="mdd-generate__loaded">{copy.fileLoaded}</span>}
        value={model.pathText}
        placeholder={copy.inputPlaceholder}
        title={copy.pathTitle}
        disabled={busy}
        error={model.loadError?.message ?? null}
        onChange={model.setPathText}
        onCommit={model.commitPath}
        onBrowse={() => onBrowse('file')}
        onUpload={(file) => void model.loadFromUpload(file)}
        browseLabel={copy.browse}
        uploadLabel={copy.upload}
      />

      {source && <ModelSummary inspecting={model.inspecting} inspection={model.inspection} />}

      <PlatformPicker
        platforms={platforms}
        selected={generation.selected}
        disabled={busy}
        error={platformsError}
        onToggle={generation.toggle}
        onRetry={retryPlatforms}
      />

      <PathField
        label={copy.saveTo}
        value={generation.saveTo}
        placeholder={copy.savePlaceholder}
        title={copy.pathTitle}
        disabled={busy}
        onChange={generation.setSaveTo}
        onBrowse={() => onBrowse('folder')}
        browseLabel={copy.browse}
      />

      <div className="mdd-generate__row">
        <Button variant="primary" disabled={!canGenerate} onClick={() => void generation.generate()}>
          {busy ? copy.generating : copy.generate}
        </Button>
        {reason && <span className="mdd-generate__hint">{reason}</span>}
      </div>
    </div>
  );
}
