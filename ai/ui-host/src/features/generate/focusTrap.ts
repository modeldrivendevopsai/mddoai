const FOCUSABLE = 'button:not([disabled]), [href], input:not([disabled]), [tabindex]:not([tabindex="-1"])';

/**
 * Keeps Tab inside a modal: from its last control it goes to its first, and
 * back, and from anywhere outside it (focus can fall to the page when the
 * control that had it is removed) it comes back in.
 */
export function keepFocusInside(event: KeyboardEvent, container: HTMLElement | null) {
  if (!container) return;
  const items = [...container.querySelectorAll<HTMLElement>(FOCUSABLE)];
  if (items.length === 0) {
    event.preventDefault();
    container.focus();
    return;
  }
  const first = items[0];
  const last = items[items.length - 1];
  const active = document.activeElement;
  const outside = !container.contains(active) || active === container;
  if (event.shiftKey && (outside || active === first)) {
    event.preventDefault();
    last.focus();
  } else if (!event.shiftKey && (outside || active === last)) {
    event.preventDefault();
    first.focus();
  }
}
