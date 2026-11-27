import type { IconName } from './Icon';

/**
 * A rule a stage was given, as the screens that list rules draw it. An icon says where a
 * rule came from: a person typed it, a failed check taught it, or a person made it permanent.
 */
export interface CorrectionItem {
  text: string;
  // True for a rule a failed check taught, false or absent for one a person typed.
  learned?: boolean;
  // A person made it permanent.
  permanent?: boolean;
}

export type CorrectionKind = 'typed' | 'learned' | 'permanent';

export const CORRECTION_KINDS = {
  typed: { icon: 'User', words: 'Typed by a person' },
  learned: { icon: 'Wrench', words: 'Learned from a failed check' },
  permanent: { icon: 'Infinity', words: 'Permanent: used in every draft' },
} as const satisfies Record<CorrectionKind, { icon: IconName; words: string }>;

/** Where a rule came from, leaving out that it was made permanent afterwards. */
export function originOf(item: CorrectionItem): 'typed' | 'learned' {
  return item.learned ? 'learned' : 'typed';
}
