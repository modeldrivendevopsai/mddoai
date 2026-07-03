import React from 'react';

/**
 * MDDOAI Icon — thin wrapper over Lucide (https://lucide.dev), the brand icon set.
 * Renders a Lucide line icon by name. Requires the Lucide UMD script to be present
 * on the page (cards & UI kits load it from CDN); falls back to an empty box if not.
 *
 * Stroke inherits `currentColor`; size is a single px value (square).
 */
export function Icon({ name, size = 20, strokeWidth = 1.9, color = 'currentColor', style = {}, ...rest }) {
  const ref = React.useRef(null);

  React.useEffect(() => {
    const el = ref.current;
    if (!el) return;
    // Reset to the data-lucide marker so createIcons re-renders cleanly on name change.
    el.innerHTML = '';
    const i = document.createElement('i');
    i.setAttribute('data-lucide', name);
    el.appendChild(i);
    const lucide = typeof window !== 'undefined' ? window.lucide : null;
    if (lucide && typeof lucide.createIcons === 'function') {
      lucide.createIcons({
        attrs: { width: size, height: size, 'stroke-width': strokeWidth },
        nameAttr: 'data-lucide',
      });
    }
  }, [name, size, strokeWidth]);

  return (
    <span
      ref={ref}
      aria-hidden="true"
      style={{
        display: 'inline-flex',
        width: size,
        height: size,
        color,
        flex: 'none',
        ...style,
      }}
      {...rest}
    />
  );
}
