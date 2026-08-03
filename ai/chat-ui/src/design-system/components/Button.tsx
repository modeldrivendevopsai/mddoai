import type { ButtonHTMLAttributes } from 'react';
import './button.css';
import { Icon } from './Icon';
import type { IconName } from './Icon';

/**
 * Button — primitive shared across the app.
 */
export type ButtonVariant = 'primary' | 'ghost' | 'icon';

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  variant?: ButtonVariant;
  icon?: IconName;
}

export function Button({ variant = 'primary', icon, children, className = '', ...rest }: ButtonProps) {
  return (
    <button className={`mdd-btn mdd-btn--${variant} ${className}`} {...rest}>
      {icon && <Icon name={icon} size={variant === 'icon' ? 12 : 13} />}
      {children}
    </button>
  );
}
