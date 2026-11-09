import { Button, Panel } from 'design-system';
import { RECENT_PIPELINES_LIMIT, START_LISTS } from '../../config/startOptions.config';
import { timeAgo } from '../generate/format';
import { usePipelines } from '../generate/usePipelines';
import './recent-pipelines.css';

interface RecentPipelinesProps {
  onOpen: (pipelineId: string) => void;
}

/**
 * The latest pipelines, newest first, for the start screen's Pipelines tab:
 * the model, the platforms it was made for, and when. Each row opens its
 * pipeline; where that goes is the caller's to decide. Only a reminder of
 * recent work, so it stops at RECENT_PIPELINES_LIMIT.
 */
export function RecentPipelines({ onOpen }: RecentPipelinesProps) {
  const copy = START_LISTS.pipelines;
  const { pipelines, error, retry } = usePipelines();

  if (error) {
    return (
      <div className="mdd-recent__message" role="alert">
        <span>{error.message}</span>
        <Button variant="secondary" size="sm" onClick={retry}>
          {copy.retry}
        </Button>
      </div>
    );
  }
  if (pipelines === null) {
    return (
      <p className="mdd-recent__message" role="status">
        {copy.loading}
      </p>
    );
  }
  if (pipelines.length === 0) return <p className="mdd-recent__message">{copy.empty}</p>;

  return (
    <Panel tone="neutral" className="mdd-recent">
      {pipelines.slice(0, RECENT_PIPELINES_LIMIT).map((pipeline) => (
        <button key={pipeline.id} type="button" className="mdd-recent__row" onClick={() => onOpen(pipeline.id)}>
          <span className="mdd-recent__name">{pipeline.swarch_name}</span>
          <span className="mdd-recent__platforms">{pipeline.platforms.map((platform) => platform.name).join(', ')}</span>
          <span className="mdd-recent__when" title={new Date(pipeline.generated_at).toLocaleString()}>
            {timeAgo(pipeline.generated_at)}
          </span>
        </button>
      ))}
    </Panel>
  );
}
