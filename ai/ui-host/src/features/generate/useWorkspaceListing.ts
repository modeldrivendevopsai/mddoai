import { useEffect, useState } from 'react';
import { browseWorkspace } from '@/services/generator.service';
import type { WorkspaceListing } from '@/services/generator.service';
import { toStepError } from './stepError';

interface Outcome {
  folder: string;
  listing: WorkspaceListing | null;
  error: string | null;
}

/**
 * What the workspace holds in one folder, as the backend lists it. An answer
 * for any other folder than the one asked about counts as not loaded yet, so
 * a slow answer for a folder the person has already left is never shown.
 */
export function useWorkspaceListing(folder: string, kind: 'file' | 'folder') {
  const [outcome, setOutcome] = useState<Outcome | null>(null);

  useEffect(() => {
    let current = true;
    browseWorkspace(folder, kind === 'folder')
      .then((listing) => current && setOutcome({ folder, listing, error: null }))
      .catch(
        (err) =>
          current && setOutcome({ folder, listing: null, error: toStepError(err, 'Could not list this folder.').message })
      );
    return () => {
      current = false;
    };
  }, [folder, kind]);

  const shown = outcome && outcome.folder === folder ? outcome : null;
  return { listing: shown?.listing ?? null, error: shown?.error ?? null };
}
