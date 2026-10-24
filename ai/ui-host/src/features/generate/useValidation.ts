import { useCallback, useState } from 'react';
import { validatePipeline } from '@/services/generator.service';
import type { GenerationResult, PipelineValidation } from '@/services/generator.service';
import { toStepError } from './stepError';
import type { StepError } from './stepError';
import type { StaleGuard } from './useStaleGuard';

interface Options {
  guard: StaleGuard;
  results: GenerationResult[] | null;
}

/**
 * Checking each generated pipeline, one platform at a time. An answer for a
 * pipeline that has since been replaced is dropped, and reset() (called by
 * whatever replaced it) clears the busy flags that answer would have cleared.
 */
export function useValidation({ guard, results }: Options) {
  const [validations, setValidations] = useState<Record<string, PipelineValidation>>({});
  const [validating, setValidating] = useState<string[]>([]);
  const [errors, setErrors] = useState<Record<string, StepError>>({});

  const reset = useCallback(() => {
    setValidations({});
    setValidating([]);
    setErrors({});
  }, []);

  const validate = useCallback(
    async (platformId: string) => {
      const pipeline = results?.find((result) => result.platform.id === platformId)?.pipeline;
      if (!pipeline) return;
      const current = guard.token();
      setValidating((ids) => [...ids, platformId]);
      setErrors(({ [platformId]: _dropped, ...rest }) => rest);
      try {
        const outcome = await validatePipeline(platformId, pipeline);
        if (guard.isCurrent(current)) setValidations((all) => ({ ...all, [platformId]: outcome }));
      } catch (err) {
        if (guard.isCurrent(current)) {
          setErrors((all) => ({ ...all, [platformId]: toStepError(err, 'Could not validate the pipeline.') }));
        }
      } finally {
        if (guard.isCurrent(current)) setValidating((ids) => ids.filter((id) => id !== platformId));
      }
    },
    [results, guard]
  );

  return { validations, validating, errors, validate, reset };
}
