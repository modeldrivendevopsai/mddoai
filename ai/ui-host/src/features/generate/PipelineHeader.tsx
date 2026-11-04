import type { ReactNode } from 'react';
import { Icon } from 'design-system';
import { GENERATE_COPY } from '../../config/generate.config';

// The pale-blue band the pipeline screens open with: blue is the colour of
// the pipelines mode, purple the platforms' (see the sidebar and start
// cards).
function Band({ children }: { children: ReactNode }) {
  return <header className="mdd-generate__band">{children}</header>;
}

interface BackTitleProps {
  title: string;
  hint: string;
  onBack: () => void;
}

// "← Title": the title is itself the way back, with the arrow on its left.
// Every pipeline screen's header is this, so going back looks the same on all.
function BackTitle({ title, hint, onBack }: BackTitleProps) {
  return (
    <Band>
      <h1>
        <button type="button" className="mdd-generate__back" title={hint} onClick={onBack}>
          <Icon name="ArrowLeft" size={18} />
          {title}
        </button>
      </h1>
    </Band>
  );
}

/** "← CI/CD Pipelines": back to the start screen. */
export function PipelinesHeader({ onBack }: { onBack: () => void }) {
  const copy = GENERATE_COPY.pipelines;
  return <BackTitle title={copy.title} hint={copy.backHint} onBack={onBack} />;
}

/** "← Generate CI/CD Pipeline": back to the pipelines list. */
export function InputHeader({ onBack }: { onBack: () => void }) {
  const copy = GENERATE_COPY.input;
  return <BackTitle title={copy.title} hint={copy.backHint} onBack={onBack} />;
}
