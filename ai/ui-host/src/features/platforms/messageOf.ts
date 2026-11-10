/** The reason a failed load gives, or `fallback` when it gives none. */
export function messageOf(err: unknown, fallback: string): string {
  return err instanceof Error && err.message ? err.message : fallback;
}
