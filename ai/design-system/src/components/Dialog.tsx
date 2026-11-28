import { useEffect, useId, useRef } from 'react';
import type { ReactNode } from 'react';
import { Icon } from './Icon';
import './dialog.css';

interface DialogProps {
  title: string;
  onClose: () => void;
  children: ReactNode;
  // Controls under the body, such as a copy button.
  footer?: ReactNode;
}

const FOCUSABLE = 'button:not([disabled]), [href], input:not([disabled]), textarea:not([disabled]), [tabindex]:not([tabindex="-1"])';

// Keeps Tab inside the dialog: from its last control it goes to its first, and back, and from
// anywhere outside it (focus can fall to the page when the control that had it is removed) it
// comes back in.
function keepFocusInside(event: KeyboardEvent, container: HTMLElement | null) {
  if (!container) return;
  const items = [...container.querySelectorAll<HTMLElement>(FOCUSABLE)];
  if (items.length === 0) {
    event.preventDefault();
    container.focus();
    return;
  }
  const first = items[0];
  const last = items[items.length - 1];
  const active = document.activeElement;
  const outside = !container.contains(active) || active === container;
  if (event.shiftKey && (outside || active === first)) {
    event.preventDefault();
    last.focus();
  } else if (!event.shiftKey && (outside || active === last)) {
    event.preventDefault();
    first.focus();
  }
}

/**
 * Dialog: a small window over the page, for what is too much to bake into the page itself.
 * It takes keyboard focus when it opens and gives it back to what opened it, closes on
 * Escape, on a press outside it and on its own close button, and keeps Tab inside while it is
 * open. It is drawn where it is rendered, fixed over the whole window, so it needs no portal.
 */
export function Dialog({ title, onClose, children, footer }: DialogProps) {
  const titleId = useId();
  const dialog = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const opener = document.activeElement as HTMLElement | null;
    dialog.current?.focus();
    return () => opener?.focus?.();
  }, []);

  // Heard on the document, not on the dialog: when the focused control is removed, focus falls
  // to the page and the dialog would stop hearing keys.
  useEffect(() => {
    const onKey = (event: KeyboardEvent) => {
      if (event.key === 'Escape') onClose();
      else if (event.key === 'Tab') keepFocusInside(event, dialog.current);
    };
    document.addEventListener('keydown', onKey);
    return () => document.removeEventListener('keydown', onKey);
  }, [onClose]);

  return (
    <div className="mdd-dialog__backdrop" onMouseDown={(event) => event.target === event.currentTarget && onClose()}>
      <div ref={dialog} className="mdd-dialog" role="dialog" aria-modal="true" aria-labelledby={titleId} tabIndex={-1}>
        <header className="mdd-dialog__header">
          <h2 id={titleId} className="mdd-dialog__title">
            {title}
          </h2>
          <button type="button" className="mdd-dialog__close" aria-label="Close" onClick={onClose}>
            <Icon name="X" size={16} />
          </button>
        </header>
        <div className="mdd-dialog__body">{children}</div>
        {footer && <footer className="mdd-dialog__footer">{footer}</footer>}
      </div>
    </div>
  );
}
