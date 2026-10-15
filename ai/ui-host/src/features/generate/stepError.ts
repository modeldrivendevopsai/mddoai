import { GeneratorError } from '@/services/generator.service';
import type { ModelIssue } from '@/services/generator.service';

export interface StepError {
  message: string;
  // Set only when the backend refused a model for being invalid.
  issues: ModelIssue[];
}

export function stepError(message: string): StepError {
  return { message, issues: [] };
}

// What fetch throws when the request never got an answer (the browser's own
// wording, "Failed to fetch", means nothing to the person looking at it).
const UNREACHABLE = 'Could not reach the pipeline generator. Check that it is running, then try again.';

export function toStepError(err: unknown, fallback: string): StepError {
  if (err instanceof GeneratorError) return { message: err.message, issues: err.issues };
  if (err instanceof TypeError) return stepError(UNREACHABLE);
  return stepError(err instanceof Error && err.message ? err.message : fallback);
}
