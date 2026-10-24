import type { ReactNode } from 'react';
import { Icon } from 'design-system';
import { GENERATE_COPY } from '../../config/generate.config';

// The pale-blue band the pipeline screens open with: blue is the colour of
// the pipelines mode, purple the platforms' (see the sidebar and start
// cards).
function Band({ children }: { children: ReactNode }) {
  return <header className="mdd-generate__band">{children}</header>;
}

/** "CI/CD Pipelines", with a way back to the start screen. */
export function PipelinesHeader({ onBack }: { onBack: () => void }) {
  return (
    <Band>
      <h1>{GENERATE_COPY.pipelines.title}</h1>
      <button type="button" className="mdd-generate__link" onClick={onBack}>
        <Icon name="ArrowLeft" size={14} />
        {GENERATE_COPY.pipelines.back}
      </button>
    </Band>
  );
}

/** "← Generate CI/CD Pipeline": the title is itself the way back to the pipelines list. */
export function InputHeader({ onBack }: { onBack: () => void }) {
  return (
    <Band>
      <h1>
        <button type="button" className="mdd-generate__back" title="Back to your pipelines" onClick={onBack}>
          <Icon name="ArrowLeft" size={18} />
          {GENERATE_COPY.input.title}
        </button>
      </h1>
    </Band>
  );
}
