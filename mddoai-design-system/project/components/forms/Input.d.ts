import * as React from 'react';

export interface InputProps {
  value?: string;
  onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
  placeholder?: string;
  label?: React.ReactNode;
  hint?: React.ReactNode;
  /** Error message; turns the border red and replaces the hint. */
  error?: React.ReactNode;
  disabled?: boolean;
  /** Use the monospace family — for commands, paths, tags. @default false */
  mono?: boolean;
  iconLeft?: React.ReactNode;
  type?: string;
  /** @default true */
  full?: boolean;
}

/** Single-line text field with label, hint, and error states. */
export function Input(props: InputProps): JSX.Element;
