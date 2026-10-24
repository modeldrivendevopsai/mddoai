import { useCallback, useState } from 'react';
import { generatePipelines } from '@/services/generator.service';
import type { GenerationResult, GeneratorPlatform } from '@/services/generator.service';
import { toStepError } from './stepError';
import type { StepError } from './stepError';
import type { ModelSource } from './types';
import { useGenerationSettings } from './useGenerationSettings';
import type { StaleGuard } from './useStaleGuard';
import { useValidation } from './useValidation';

interface Options {
  // Shared with the model-source state, see useStaleGuard.
  guard: StaleGuard;
  source: ModelSource | null;
  platforms: GeneratorPlatform[];
}

/**
 * Generating: a real call to ai/pipeline_generator for the platforms and save
 * folder the person chose. Choosing is useGenerationSettings's job and
 * validating what came back is useValidation's.
 *
 * Whatever invalidated an in-flight request (a different file, generating
 * again) is the one that clears the busy flags the request would have
 * cleared, via reset() or the start of generate(): a dropped answer never
 * gets to do it itself.
 */
export function useGeneration({ guard, source, platforms }: Options) {
  const settings = useGenerationSettings(platforms);
  const { chosen, saveTo, apply: applySettings } = settings;
  const [results, setResults] = useState<GenerationResult[] | null>(null);
  const [pipelineId, setPipelineId] = useState<string | null>(null);
  const [generating, setGenerating] = useState(false);
  const [generatingNames, setGeneratingNames] = useState<string[]>([]);
  const [generateError, setGenerateError] = useState<StepError | null>(null);
  const validation = useValidation({ guard, results });
  const resetValidation = validation.reset;

  // Everything downstream of the loaded model. The settings stay.
  const reset = useCallback(() => {
    setResults(null);
    setPipelineId(null);
    setGenerating(false);
    setGenerateError(null);
    resetValidation();
  }, [resetValidation]);

  const generate = useCallback(async () => {
    if (!source || chosen.length === 0) return;
    // Also supersedes any validation still in flight for the pipelines this replaces.
    const current = guard.next();
    reset();
    setGenerating(true);
    setGeneratingNames(chosen.map((platform) => platform.name));
    try {
      const response = await generatePipelines({
        filename: source.filename,
        content: source.content,
        platforms: chosen.map((platform) => platform.id),
        source_path: source.path,
        save_to: saveTo.trim() === '' ? null : saveTo.trim(),
      });
      if (guard.isCurrent(current)) {
        setResults(response.results);
        setPipelineId(response.pipeline_id);
      }
    } catch (err) {
      if (guard.isCurrent(current)) setGenerateError(toStepError(err, 'Could not generate the pipeline.'));
    } finally {
      if (guard.isCurrent(current)) setGenerating(false);
    }
  }, [chosen, source, saveTo, guard, reset]);

  // A saved pipeline brought back: its result, and the settings it was made with.
  const restore = useCallback(
    (saved: { results: GenerationResult[]; platformIds: string[]; saveTo: string | null; pipelineId: string }) => {
      setResults(saved.results);
      setPipelineId(saved.pipelineId);
      applySettings(saved.platformIds, saved.saveTo);
    },
    [applySettings]
  );

  return {
    selected: settings.selected,
    chosen,
    saveTo,
    results,
    pipelineId,
    generating,
    generatingNames,
    generateError,
    validations: validation.validations,
    validating: validation.validating,
    validateErrors: validation.errors,
    toggle: settings.toggle,
    setSaveTo: settings.setSaveTo,
    generate,
    validate: validation.validate,
    reset,
    restore,
  };
}
