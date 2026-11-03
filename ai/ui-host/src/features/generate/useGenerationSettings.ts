import { useCallback, useMemo, useState } from 'react';
import type { GeneratorPlatform } from '@/services/generator.service';

/**
 * What the person chooses before generating: which platforms, and which
 * workspace folder to save into. These are their own settings, so loading a
 * different model does not change them.
 */
export function useGenerationSettings(platforms: GeneratorPlatform[]) {
  const [selected, setSelected] = useState<string[]>([]);
  // Empty until the person chooses a folder: nothing is saved without one.
  const [saveTo, setSaveTo] = useState('');

  const toggle = useCallback((platformId: string) => {
    setSelected((current) =>
      current.includes(platformId) ? current.filter((id) => id !== platformId) : [...current, platformId]
    );
  }, []);

  // The chosen platforms the backend actually offers: a saved pipeline can
  // name one that has since gone, and that one cannot be generated for.
  const chosen = useMemo(() => platforms.filter((platform) => selected.includes(platform.id)), [platforms, selected]);

  // Puts back the settings a saved pipeline was made with.
  const apply = useCallback((platformIds: string[], folder: string | null) => {
    setSelected(platformIds);
    setSaveTo(folder ?? '');
  }, []);

  return { selected, chosen, saveTo, setSaveTo, toggle, apply };
}
