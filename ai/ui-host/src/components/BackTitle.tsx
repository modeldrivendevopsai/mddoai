import { Icon } from 'design-system';
import './back-title.css';

interface BackTitleProps {
  title: string;
  hint: string;
  onBack: () => void;
}

// The arrow matches the size of the headings it sits in, so it reads as part
// of the title.
const ARROW_SIZE = 18;

/**
 * "← Title": the title is itself the way back, with an arrow on its left.
 * Every screen's header uses it, so going back looks the same everywhere. It
 * takes its font, size and colour from the heading around it.
 */
export function BackTitle({ title, hint, onBack }: BackTitleProps) {
  return (
    <button type="button" className="mdd-back-title" title={hint} onClick={onBack}>
      <Icon name="ArrowLeft" size={ARROW_SIZE} />
      {title}
    </button>
  );
}
