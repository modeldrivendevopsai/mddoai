import { useEffect, useRef, useState } from 'react';
import { Button } from './Button';
import { Dialog } from './Dialog';
import { Icon } from './Icon';
import { CORRECTION_KINDS, originOf } from './corrections';
import type { CorrectionItem } from './corrections';
import './rules-dialog.css';

interface RulesDialogProps {
  title: string;
  // One sentence saying what these rules are and who used them.
  note: string;
  items: CorrectionItem[];
  onClose: () => void;
}

// How long "Copied" stays on the button before it says Copy again.
const COPY_FEEDBACK_MS = 1400;

type CopyState = 'idle' | 'copied' | 'failed';

// What the status beside the button says once it was pressed.
const COPY_STATUS: Record<Exclude<CopyState, 'idle'>, string> = { copied: 'Copied', failed: 'Could not copy' };

/** The rules as numbered lines of text, the way the dialog numbers them. */
export function numbered(items: CorrectionItem[]): string {
  return items.map((item, index) => `${index + 1}. ${item.text}`).join('\n');
}

/**
 * RulesDialog: the rules behind one number on a chart, as a numbered list in a dialog. Each
 * says in words where it came from, and the Copy button puts the whole list on the clipboard as
 * text. The text of each rule is plain text on the page too, so a part of it can be selected.
 */
export function RulesDialog({ title, note, items, onClose }: RulesDialogProps) {
  const [copy, setCopy] = useState<CopyState>('idle');
  const timer = useRef<ReturnType<typeof setTimeout> | undefined>(undefined);
  useEffect(() => () => clearTimeout(timer.current), []);

  const copyAll = async () => {
    let next: CopyState = 'copied';
    try {
      await navigator.clipboard.writeText(numbered(items));
    } catch {
      // No clipboard in this window (it is not a secure page) or it was refused.
      next = 'failed';
    }
    setCopy(next);
    clearTimeout(timer.current);
    timer.current = setTimeout(() => setCopy('idle'), COPY_FEEDBACK_MS);
  };

  return (
    <Dialog
      title={title}
      onClose={onClose}
      footer={
        <>
          <Button variant="secondary" size="sm" icon={copy === 'copied' ? 'Check' : 'Copy'} onClick={copyAll}>
            Copy
          </Button>
          <span role="status" className="mdd-rules-dialog__status">
            {copy === 'idle' ? '' : COPY_STATUS[copy]}
          </span>
        </>
      }
    >
      <p className="mdd-rules-dialog__note">{note}</p>
      <ol className="mdd-rules-dialog__list">
        {items.map((item, index) => {
          const origin = CORRECTION_KINDS[originOf(item)];
          return (
            // The list is only ever shown whole, so the position is the key (two rules can have the same text).
            <li key={index}>
              <p className="mdd-rules-dialog__text">{item.text}</p>
              <p className={`mdd-rules-dialog__origin mdd-rules-dialog__origin--${originOf(item)}`}>
                <Icon name={origin.icon} size={13} />
                {origin.words}
              </p>
              {item.permanent && (
                <p className="mdd-rules-dialog__origin mdd-rules-dialog__origin--permanent">
                  <Icon name={CORRECTION_KINDS.permanent.icon} size={13} />
                  {CORRECTION_KINDS.permanent.words}
                </p>
              )}
            </li>
          );
        })}
      </ol>
    </Dialog>
  );
}
