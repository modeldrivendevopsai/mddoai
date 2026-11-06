import { GENERATE_COPY } from '../../config/generate.config';
import { BackTitle } from '../../components/BackTitle';

interface BackBandProps {
  title: string;
  hint: string;
  onBack: () => void;
}

// The pale-blue band the pipeline screens open with: blue is the colour of
// the pipelines mode, purple the platforms' (see the sidebar and start
// cards). The title in it is the way back, as on every screen.
function BackBand({ title, hint, onBack }: BackBandProps) {
  return (
    <header className="mdd-generate__band">
      <h1>
        <BackTitle title={title} hint={hint} onBack={onBack} />
      </h1>
    </header>
  );
}

/** "← CI/CD Pipelines": back to the start screen. */
export function PipelinesHeader({ onBack }: { onBack: () => void }) {
  const copy = GENERATE_COPY.pipelines;
  return <BackBand title={copy.title} hint={copy.backHint} onBack={onBack} />;
}

/** "← Generate CI/CD Pipeline": back to the pipelines list. */
export function InputHeader({ onBack }: { onBack: () => void }) {
  const copy = GENERATE_COPY.input;
  return <BackBand title={copy.title} hint={copy.backHint} onBack={onBack} />;
}
