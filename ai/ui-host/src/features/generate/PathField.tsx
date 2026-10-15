import { useId, useRef } from 'react';
import type { KeyboardEvent, ReactNode } from 'react';
import { Button } from 'design-system';
import { MODEL_FILE_ACCEPT } from '../../config/generate.config';

interface PathFieldProps {
  label: string;
  // Shown beside the label, for example that a file is loaded.
  note?: ReactNode;
  value: string;
  placeholder?: string;
  // Shown on hover; what the path is relative to.
  title?: string;
  disabled: boolean;
  error?: string | null;
  onChange: (value: string) => void;
  // Pressing Enter in the field, or leaving it: the text is final.
  onCommit?: () => void;
  onBrowse: () => void;
  // Only the input-file field offers an upload from the person's own computer.
  onUpload?: (file: File) => void;
  browseLabel: string;
  uploadLabel?: string;
}

/**
 * A workspace path typed into a text field, with a Browse button that picks
 * one from the workspace folder and, for a model file, an Upload button that
 * takes one from the person's own computer.
 */
export function PathField({
  label,
  note,
  value,
  placeholder,
  title,
  disabled,
  error,
  onChange,
  onCommit,
  onBrowse,
  onUpload,
  browseLabel,
  uploadLabel,
}: PathFieldProps) {
  const inputId = useId();
  const fileInput = useRef<HTMLInputElement>(null);

  const commitOnEnter = (event: KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter') onCommit?.();
  };

  return (
    <div className="mdd-generate__field">
      <div className="mdd-generate__field-label">
        <label htmlFor={inputId}>{label}</label>
        {note}
      </div>
      <div className="mdd-generate__path-row">
        <input
          id={inputId}
          type="text"
          className="mdd-generate__input"
          value={value}
          placeholder={placeholder}
          title={title}
          disabled={disabled}
          spellCheck={false}
          autoComplete="off"
          onChange={(event) => onChange(event.target.value)}
          onBlur={onCommit}
          onKeyDown={commitOnEnter}
        />
        <Button variant="secondary" disabled={disabled} onClick={onBrowse}>
          {browseLabel}
        </Button>
        {onUpload && (
          <>
            <Button variant="secondary" disabled={disabled} onClick={() => fileInput.current?.click()}>
              {uploadLabel}
            </Button>
            <input
              ref={fileInput}
              type="file"
              hidden
              accept={MODEL_FILE_ACCEPT}
              aria-label={uploadLabel}
              onChange={(event) => {
                const file = event.target.files?.[0];
                if (file) onUpload(file);
                // Lets the same file be chosen again after an edit on disk.
                event.target.value = '';
              }}
            />
          </>
        )}
      </div>
      {error && (
        <p className="mdd-generate__error" role="alert">
          {error}
        </p>
      )}
    </div>
  );
}
