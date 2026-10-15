import { useCallback, useRef, useState } from 'react';
import { inspectModel, readWorkspaceFile } from '@/services/generator.service';
import type { Inspection } from '@/services/generator.service';
import { stepError, toStepError } from './stepError';
import type { StepError } from './stepError';
import type { ModelSource } from './types';
import type { StaleGuard } from './useStaleGuard';

interface Options {
  // Shared with the generation state, see useStaleGuard.
  guard: StaleGuard;
  setSource: (source: ModelSource | null) => void;
  // Called whenever the model is replaced: whatever was generated from the
  // previous one no longer describes this one.
  onReplace: () => void;
}

/**
 * The model file the screen works with: where the person says it is (a typed
 * or browsed workspace path, or an upload), and what the backend makes of it.
 * Loading anything replaces everything that followed from the previous file,
 * and an answer that arrives after the person has moved on is dropped.
 */
export function useModelSource({ guard, setSource, onReplace }: Options) {
  const [pathText, setPathText] = useState('');
  const [inspection, setInspection] = useState<Inspection | null>(null);
  const [inspecting, setInspecting] = useState(false);
  const [loadError, setLoadError] = useState<StepError | null>(null);
  // The last text a load was started for, so leaving the field again without
  // changing it does not load the same file twice.
  const committed = useRef('');

  // Takes over from whatever came before: invalidates in-flight answers and
  // clears what they would have produced, busy flags included.
  const begin = useCallback((): number => {
    const current = guard.next();
    onReplace();
    setInspection(null);
    setInspecting(false);
    setLoadError(null);
    return current;
  }, [guard, onReplace]);

  const inspect = useCallback(
    async (model: ModelSource, current: number) => {
      setSource(model);
      const shown = model.path ?? model.filename;
      setPathText(shown);
      committed.current = shown;
      setInspecting(true);
      try {
        const result = await inspectModel(model.filename, model.content);
        if (guard.isCurrent(current)) setInspection(result);
      } catch (err) {
        if (guard.isCurrent(current)) {
          setLoadError(toStepError(err, 'Could not check this file.'));
          // A path can be tried again with the same text once the checker is
          // back; an upload is retried by choosing the file again.
          if (model.path !== null) committed.current = '';
        }
      } finally {
        if (guard.isCurrent(current)) setInspecting(false);
      }
    },
    [setSource, guard]
  );

  const reject = useCallback(
    (message: string) => {
      setSource(null);
      setLoadError(stepError(message));
      // Nothing is loaded now, so the path still in the field is not "done".
      committed.current = '';
    },
    [setSource]
  );

  const loadFromPath = useCallback(
    async (path: string) => {
      const current = begin();
      setSource(null);
      try {
        const file = await readWorkspaceFile(path);
        if (!guard.isCurrent(current)) return;
        await inspect({ filename: file.filename, content: file.content, path: file.path }, current);
      } catch (err) {
        if (guard.isCurrent(current)) {
          setSource(null);
          setLoadError(toStepError(err, 'Could not open this file.'));
          // Whatever was wrong may have been fixed: the same text can be tried again.
          committed.current = '';
        }
      }
    },
    [begin, inspect, setSource, guard]
  );

  const loadFromUpload = useCallback(
    async (file: File) => {
      const current = begin();
      let content: string;
      try {
        content = await file.text();
      } catch {
        if (guard.isCurrent(current)) reject('Could not read this file.');
        return;
      }
      if (!guard.isCurrent(current)) return;
      if (content.trim() === '') {
        reject('This file is empty.');
        return;
      }
      await inspect({ filename: file.name, content, path: null }, current);
    },
    [begin, inspect, reject, guard]
  );

  // Called when the person leaves the path field or presses Enter in it.
  const commitPath = useCallback(() => {
    const text = pathText.trim();
    if (text === '' || text === committed.current) return;
    committed.current = text;
    void loadFromPath(text);
  }, [pathText, loadFromPath]);

  // Brings back a model that is already in hand, with no read to make.
  const adopt = useCallback(
    (model: ModelSource) => inspect(model, begin()),
    [inspect, begin]
  );

  return { pathText, setPathText, inspection, inspecting, loadError, loadFromPath, loadFromUpload, commitPath, adopt };
}
