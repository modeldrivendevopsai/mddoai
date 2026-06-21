import * as React from 'react';

export interface CodeLine {
  text: string;
  /** Syntax role for colouring. */
  kind?: 'prompt' | 'comment' | 'output' | 'flag' | 'string';
  /** Render a leading "$ " prompt. */
  prompt?: boolean;
}

export interface CodeBlockProps {
  /** Plain multi-line code (split on \n). */
  code?: string;
  /** Structured lines, alternative to `code`. */
  lines?: (string | CodeLine)[];
  /** Title shown in the window bar. */
  title?: React.ReactNode;
  /** Language label when no title. @default "bash" */
  lang?: string;
  /** Show the copy button. @default true */
  copyable?: boolean;
}

/**
 * Dark CLI/terminal code surface — a signature brand element.
 * @startingPoint section="Surfaces" subtitle="Terminal code block" viewport="700x200"
 */
export function CodeBlock(props: CodeBlockProps): JSX.Element;
