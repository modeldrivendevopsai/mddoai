import { PLATFORMS_COPY } from '../../config/platforms.config';
import { useRunEvolution } from './useRunEvolution';

/**
 * The model rounds the chat that made a platform spent, in all, for a row of
 * the platforms list. A platform no chat made, or whose rounds cannot be read,
 * shows a dash: the row is still worth showing without the number.
 */
export function RoundsCell({ runId }: { runId: string | null }) {
  const { evolution } = useRunEvolution(runId);
  return <td className="mdd-platforms-list__mono">{evolution ? evolution.total_rounds : PLATFORMS_COPY.noRounds}</td>;
}
