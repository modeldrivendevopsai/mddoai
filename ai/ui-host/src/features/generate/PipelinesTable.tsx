import type { PipelineSummary } from '@/services/generator.service';
import { GENERATE_COPY } from '../../config/generate.config';
import { timeAgo } from './format';

interface PipelinesTableProps {
  pipelines: PipelineSummary[];
  onOpen: (pipelineId: string) => void;
}

/** What has been generated before: the model, the platforms, the files, and when. */
export function PipelinesTable({ pipelines, onOpen }: PipelinesTableProps) {
  const copy = GENERATE_COPY.pipelines;
  return (
    <div className="mdd-generate__table-wrap">
      <table className="mdd-generate__table">
        <thead>
          <tr>
            <th scope="col">{copy.columns.input}</th>
            <th scope="col">{copy.columns.platforms}</th>
            <th scope="col">{copy.columns.outputs}</th>
            <th scope="col">{copy.columns.lastGenerated}</th>
            <th scope="col">
              <span className="mdd-generate__sr-only">Actions</span>
            </th>
          </tr>
        </thead>
        <tbody>
          {pipelines.map((pipeline) => (
            <tr key={pipeline.id}>
              <th scope="row" title={pipeline.source_path ?? undefined}>
                {pipeline.swarch_name}
              </th>
              <td>{pipeline.platforms.map((platform) => platform.name).join(', ')}</td>
              <td className="mdd-generate__mono">{pipeline.outputs.join(', ')}</td>
              <td title={new Date(pipeline.generated_at).toLocaleString()}>{timeAgo(pipeline.generated_at)}</td>
              <td>
                <button
                  type="button"
                  className="mdd-generate__link"
                  aria-label={`${copy.open} ${pipeline.swarch_name}`}
                  onClick={() => onOpen(pipeline.id)}
                >
                  {copy.open}
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
