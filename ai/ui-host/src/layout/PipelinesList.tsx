import { useEffect, useState } from 'react';
import { listPipelines } from '../services/generator.service';
import type { PipelineSummary } from '../services/generator.service';
import { SIDEBAR_PIPELINES_COPY, SIDEBAR_POLL_INTERVAL_MS } from '../config/sidebar.config';
import './sessions-list.css';

interface PipelinesListProps {
  // The pipeline open on screen, so its row stands out.
  openId: string | undefined;
  onOpen: (pipelineId: string) => void;
}

/** What a pipeline was made from, for which platforms, into which files: the row's second line. */
function describe(pipeline: PipelineSummary): string {
  return [pipeline.platforms.map((platform) => platform.name).join(', '), pipeline.outputs.join(', ')]
    .filter(Boolean)
    .join(' · ');
}

/**
 * The "Pipelines" tab's list: every pipeline generated so far, newest first,
 * kept by the pipeline generator. Navigation is the caller's, so this knows
 * nothing about routes.
 */
export function PipelinesList({ openId, onOpen }: PipelinesListProps) {
  const copy = SIDEBAR_PIPELINES_COPY;
  // null until the first answer, so "nothing yet" and "not loaded yet" differ.
  const [pipelines, setPipelines] = useState<PipelineSummary[] | null>(null);
  const [failed, setFailed] = useState(false);

  useEffect(() => {
    let cancelled = false;
    const load = () =>
      listPipelines()
        .then((list) => {
          if (cancelled) return;
          setPipelines(list);
          setFailed(false);
        })
        .catch(() => {
          if (!cancelled) setFailed(true);
        });
    load();
    const timer = setInterval(load, SIDEBAR_POLL_INTERVAL_MS);
    return () => {
      cancelled = true;
      clearInterval(timer);
    };
  }, []);

  return (
    <div className="mdd-sessions">
      <div className="mdd-sessions__label">{copy.label}</div>
      {pipelines === null && failed && <div className="mdd-sessions__empty">{copy.loadError}</div>}
      {pipelines?.length === 0 && <div className="mdd-sessions__empty">{copy.empty}</div>}
      {pipelines?.map((pipeline) => (
        <button
          key={pipeline.id}
          type="button"
          className={`mdd-sessions__item mdd-sessions__item--pipeline mdd-sessions__item--row${pipeline.id === openId ? ' is-selected' : ''}`}
          aria-current={pipeline.id === openId ? 'true' : undefined}
          title={`${pipeline.swarch_name}\n${describe(pipeline)}\n${new Date(pipeline.generated_at).toLocaleString()}`}
          onClick={() => onOpen(pipeline.id)}
        >
          <span className="mdd-sessions__name">{pipeline.swarch_name}</span>
          <span className="mdd-sessions__meta">{describe(pipeline)}</span>
        </button>
      ))}
    </div>
  );
}
