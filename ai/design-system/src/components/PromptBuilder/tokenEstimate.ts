// A rough, heuristic token-count estimate, not a real tokenizer call - the
// widely-cited ~4-characters-per-token approximation for English text (see
// OpenAI's own help-center guidance: roughly 4 chars, or 0.75 words, per
// token for GPT-family models). Good enough to flag a bloated attachment
// before it risks blowing a real context window, not meant to be exact -
// never shown as if it were the real number a provider would bill for.
const CHARS_PER_TOKEN_ESTIMATE = 4

export function estimateTokens(text: string): number {
  if (!text) return 0
  return Math.ceil(text.length / CHARS_PER_TOKEN_ESTIMATE)
}
