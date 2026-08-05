import * as LucideIcons from 'lucide-react';
import type { LucideProps } from 'lucide-react';

/**
 * Icon — thin wrapper around Lucide (the brand's icon library).
 * Usage: <Icon name="Plus" size={14} />
 * Keeps icon usage declarative and swappable in one place if the icon
 * library is ever replaced.
 */
export type IconName = keyof typeof LucideIcons;

interface IconProps extends LucideProps {
  name: IconName;
}

// size/strokeWidth defaults match the real source verbatim
// (mddoai-design-system/project/components/actions/Icon.jsx).
export function Icon({ name, size = 20, strokeWidth = 1.9, ...rest }: IconProps) {
  const LucideIcon = LucideIcons[name] as React.ComponentType<LucideProps> | undefined;
  if (!LucideIcon) {
    if (import.meta.env.DEV) {
      console.warn(`Icon: "${name}" is not a valid Lucide icon name.`);
    }
    return null;
  }
  return <LucideIcon size={size} strokeWidth={strokeWidth} {...rest} />;
}
