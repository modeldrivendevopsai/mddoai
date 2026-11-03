import { useEffect, useRef } from 'react';
import type { ReactNode, RefObject } from 'react';
import { Button, Icon, StatusPill } from 'design-system';
import type { PipelineValidation } from '@/services/generator.service';
import { GENERATE_COPY } from '../../config/generate.config';
import { PipelineChecks } from './PipelineChecks';
import type { StepError } from './stepError';

interface ValidationStatusProps {
  validation: PipelineValidation | undefined;
  validating: boolean;
  error: StepError | undefined;
  // Checks the pipeline again, for when the check itself could not run.
  onRetry: () => void;
}

type PillVariant = 'success' | 'danger' | 'info';

interface Summary {
  variant: PillVariant;
  label: string;
  // What opens under the status; null while there is nothing to show yet.
  details: ReactNode | null;
}

function summarize({ validation, validating, error, onRetry }: ValidationStatusProps): Summary {
  const copy = GENERATE_COPY.validation;
  if (error) {
    return {
      variant: 'danger',
      label: copy.unavailable,
      details: (
        <>
          <p className="mdd-generate__error">{error.message}</p>
          <Button variant="secondary" size="sm" onClick={onRetry}>
            {copy.retry}
          </Button>
        </>
      ),
    };
  }
  if (validating || !validation) return { variant: 'info', label: copy.checking, details: null };
  const total = validation.checks.length;
  const failed = validation.checks.filter((check) => check.status === 'fail').length;
  return {
    variant: validation.valid ? 'success' : 'danger',
    label: validation.valid ? copy.passed(total) : copy.failed(failed, total),
    details: <PipelineChecks validation={validation} />,
  };
}

// The status is a live region, so a person using a screen reader hears
// "Checking" turn into the result without having to open anything.
function Pill({ variant, label }: { variant: PillVariant; label: string }) {
  return (
    <span role="status">
      <StatusPill variant={variant}>{label}</StatusPill>
    </span>
  );
}

// Closes the menu on Escape or a press outside it, as a drop-down should; a
// native <details> only opens and closes from its own summary.
function useDismiss(menu: RefObject<HTMLDetailsElement | null>) {
  useEffect(() => {
    const dismiss = (event: Event) => {
      const element = menu.current;
      if (!element?.open) return;
      const dismissed =
        event instanceof KeyboardEvent ? event.key === 'Escape' : !element.contains(event.target as Node);
      if (dismissed) element.open = false;
    };
    document.addEventListener('keydown', dismiss);
    document.addEventListener('pointerdown', dismiss);
    return () => {
      document.removeEventListener('keydown', dismiss);
      document.removeEventListener('pointerdown', dismiss);
    };
  }, [menu]);
}

function StatusMenu({ variant, label, children }: { variant: PillVariant; label: string; children: ReactNode }) {
  const menu = useRef<HTMLDetailsElement>(null);
  useDismiss(menu);
  return (
    <details ref={menu} className="mdd-generate__status-menu">
      <summary>
        <Pill variant={variant} label={label} />
        <Icon name="ChevronDown" size={14} />
      </summary>
      <div className="mdd-generate__status-panel">{children}</div>
    </details>
  );
}

/**
 * How the generated pipeline checked out, at a glance, with each check under
 * a drop-down for anyone who wants the detail. Nothing here is a button to
 * press in the normal case: the checks run as soon as the pipeline is
 * generated. Only a check that could not run offers to try again.
 */
export function ValidationStatus(props: ValidationStatusProps) {
  const { variant, label, details } = summarize(props);
  if (details === null) return <Pill variant={variant} label={label} />;
  return (
    <StatusMenu variant={variant} label={label}>
      {details}
    </StatusMenu>
  );
}
