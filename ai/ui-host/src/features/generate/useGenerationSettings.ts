import { useCallback, useMemo, useState } from 'react';
import type { GeneratorPlatform } from '@/services/generator.service';

/**
 * The platforms the person has ticked. Loading a different model does not
 * change them.
 */
export function useGenerationSettings(platforms: GeneratorPlatform[]) {
  const [selected, setSelected] = useState<string[]>([]);

  const toggle = useCallback((platformId: string) => {
    setSelected((current) =>
      current.includes(platformId) ? current.filter((id) => id !== platformId) : [...current, platformId]
    );
  }, []);

  // The chosen platforms the backend actually offers: a saved pipeline can
  // name one that has since gone, and that one cannot be generated for.
  const chosen = useMemo(() => platforms.filter((platform) => selected.includes(platform.id)), [platforms, selected]);

  // Puts back the platforms a saved pipeline was made for.
  const apply = useCallback((platformIds: string[]) => setSelected(platformIds), []);

  return { selected, chosen, toggle, apply };
}
