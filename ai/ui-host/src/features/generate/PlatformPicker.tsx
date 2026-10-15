import { Button } from 'design-system';
import type { GeneratorPlatform } from '@/services/generator.service';
import { GENERATE_COPY } from '../../config/generate.config';
import type { StepError } from './stepError';

interface PlatformPickerProps {
  platforms: GeneratorPlatform[];
  selected: string[];
  disabled: boolean;
  error: StepError | null;
  onToggle: (platformId: string) => void;
  onRetry: () => void;
}

/** One or more CI/CD platforms to generate for, as checkboxes. */
export function PlatformPicker({ platforms, selected, disabled, error, onToggle, onRetry }: PlatformPickerProps) {
  const copy = GENERATE_COPY.input;
  return (
    <fieldset className="mdd-generate__field mdd-generate__platforms" disabled={disabled}>
      <legend className="mdd-generate__field-label">
        <span>{copy.platforms}</span>
        {!disabled && <span className="mdd-generate__hint">{copy.platformsHint}</span>}
      </legend>
      <div className="mdd-generate__checks-row">
        {platforms.map((platform) => (
          <label key={platform.id} className="mdd-generate__check">
            <input
              type="checkbox"
              checked={selected.includes(platform.id)}
              onChange={() => onToggle(platform.id)}
            />
            <span>{platform.name}</span>
          </label>
        ))}
        {platforms.length === 0 && !error && <span className="mdd-generate__hint">Loading platforms…</span>}
      </div>
      {error && (
        <div className="mdd-generate__row" role="alert">
          <p className="mdd-generate__error">{error.message}</p>
          <Button variant="secondary" size="sm" onClick={onRetry}>
            Try again
          </Button>
        </div>
      )}
    </fieldset>
  );
}
