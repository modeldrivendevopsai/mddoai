/**
 * A metamodel attribute name (`build_system`) as a human label ("Build
 * system"). Derived from the name alone, not looked up in a fixed list, so a
 * field the metamodel gains later is shown correctly without an edit here.
 */
export function labelFor(attributeName: string): string {
  const spaced = attributeName.replace(/_/g, ' ').trim();
  return spaced.charAt(0).toUpperCase() + spaced.slice(1);
}

/** A metamodel element name (`SoftwareArchitecture`) in plain lowercase words. */
export function elementLabel(elementName: string): string {
  return elementName.replace(/([a-z])([A-Z])/g, '$1 $2').toLowerCase();
}

/** `1.2 s` for a duration in milliseconds, one decimal. */
export function seconds(durationMs: number): string {
  return `${(durationMs / 1000).toFixed(1)} s`;
}

const RELATIVE = new Intl.RelativeTimeFormat('en', { numeric: 'always' });

// Calendar-free approximations (a month is 30 days, a year 365): "3 weeks
// ago" does not need to be exact. Largest first.
const AGO_UNITS: [Intl.RelativeTimeFormatUnit, number][] = [
  ['year', 365 * 86400],
  ['month', 30 * 86400],
  ['week', 7 * 86400],
  ['day', 86400],
  ['hour', 3600],
  ['minute', 60],
];

/**
 * How long ago an ISO 8601 timestamp was, in the largest whole unit ("2 days
 * ago", "1 week ago"). Anything under a minute old, or dated in the future
 * (a clock a little ahead of the server's), is "just now"; an unreadable
 * timestamp is shown as nothing rather than "NaN".
 */
export function timeAgo(isoTimestamp: string, now: Date = new Date()): string {
  const elapsed = Math.round((now.getTime() - new Date(isoTimestamp).getTime()) / 1000);
  if (Number.isNaN(elapsed)) return '';
  for (const [unit, length] of AGO_UNITS) {
    if (elapsed >= length) return RELATIVE.format(-Math.floor(elapsed / length), unit);
  }
  return 'just now';
}
