import { useCallback, useRef, useState } from 'react';
import { GeneratorError, getPipeline, readWorkspaceFile } from '@/services/generator.service';
import type { PipelineRecord } from '@/services/generator.service';
import { toStepError } from './stepError';
import type { StepError } from './stepError';
import type { ModelSource } from './types';
import { useGeneration } from './useGeneration';
import { useModelSource } from './useModelSource';
import { usePlatforms } from './usePlatforms';
import { useStaleGuard } from './useStaleGuard';

// The model a saved pipeline is brought back with. One that was loaded from
// the workspace is read again, so the person gets the file as it is now,
// not as it was when the pipeline was last generated. If it has moved or gone,
// the copy kept with the pipeline stands in.
async function modelFor(record: PipelineRecord): Promise<ModelSource> {
  const kept = { filename: record.swarch_name, content: record.content, path: record.source_path };
  if (!record.source_path) return kept;
  try {
    const live = await readWorkspaceFile(record.source_path);
    return { filename: live.filename, content: live.content, path: live.path };
  } catch (err) {
    // The file is gone or cannot be reached. Anything else is a bug and says so.
    if (err instanceof GeneratorError || err instanceof TypeError) return kept;
    throw err;
  }
}

/**
 * State hub for the "Generate a CI/CD pipeline" screen: the model file, the
 * platform list, and the generation that follows from them, wired together
 * so that replacing the file drops everything that came from the old one.
 * Every action is a real call to ai/pipeline_generator, no simulated state.
 */
export function useGenerate() {
  // One guard for the whole screen: any action that makes an answer still in
  // flight stale invalidates it, whichever part of the state it belongs to.
  const guard = useStaleGuard();
  const [source, setSource] = useState<ModelSource | null>(null);
  const [opening, setOpening] = useState(false);
  const [openError, setOpenError] = useState<StepError | null>(null);
  // Which call to openSaved is the latest, for its own flag only: the guard
  // moves on as soon as the saved model is adopted, so it cannot tell.
  const latestOpen = useRef(0);

  const platformList = usePlatforms();
  const generation = useGeneration({ guard, source, platforms: platformList.platforms });
  const model = useModelSource({ guard, setSource, onReplace: generation.reset });

  const { adopt } = model;
  const { restore } = generation;

  // Brings a saved pipeline back: its model, the settings it was made with
  // and the result it produced.
  const openSaved = useCallback(
    async (pipelineId: string) => {
      const current = guard.next();
      const mine = ++latestOpen.current;
      setOpening(true);
      setOpenError(null);
      try {
        const record = await getPipeline(pipelineId);
        if (!guard.isCurrent(current)) return;
        const saved = await modelFor(record);
        if (!guard.isCurrent(current)) return;
        // adopt() resets what followed the previous model before it takes
        // over, so the saved result is put back only after it has started.
        const loading = adopt(saved);
        restore({
          results: record.results,
          platformIds: record.platforms.map((platform) => platform.id),
          saveTo: record.save_to,
          pipelineId: record.id,
        });
        await loading;
      } catch (err) {
        if (guard.isCurrent(current)) setOpenError(toStepError(err, 'Could not open this pipeline.'));
      } finally {
        if (mine === latestOpen.current) setOpening(false);
      }
    },
    [adopt, restore, guard]
  );

  const modelValid = model.inspection?.model_validation.valid === true;

  return {
    source,
    platforms: platformList.platforms,
    platformsError: platformList.error,
    retryPlatforms: platformList.retry,
    model,
    generation,
    opening,
    openError,
    openSaved,
    canGenerate:
      source !== null && modelValid && generation.chosen.length > 0 && !generation.generating && !model.inspecting,
  };
}
