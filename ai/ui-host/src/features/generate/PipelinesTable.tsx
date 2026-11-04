import type { PipelineSummary } from '@/services/generator.service';
import { GENERATE_COPY } from '../../config/generate.config';
import { timeAgo } from './format';

// A press that ends a text selection inside the row (copying a file name) is
// not a request to open it.
function isSelectingTextIn(row: HTMLElement): boolean {
  const selection = window.getSelection();
  return selection !== null && !selection.isCollapsed && row.contains(selection.anchorNode);
}

interface PipelinesTableProps {
  pipelines: PipelineSummary[];
  onOpen: (pipelineId: string) => void;
}

/**
 * What has been generated before: the model, the platforms, the files, and
 * when. A row opens its pipeline wherever it is pressed; the model's name is a
 * button, which is how the keyboard gets in.
 */
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
          </tr>
        </thead>
        <tbody>
          {pipelines.map((pipeline) => (
            <tr
              key={pipeline.id}
              onClick={(event) => {
                if (!isSelectingTextIn(event.currentTarget)) onOpen(pipeline.id);
              }}
            >
              <th scope="row" title={pipeline.source_path ?? undefined}>
                <button type="button" className="mdd-generate__link" aria-label={copy.openPipeline(pipeline.swarch_name)}>
                  {pipeline.swarch_name}
                </button>
              </th>
              <td>{pipeline.platforms.map((platform) => platform.name).join(', ')}</td>
              <td className="mdd-generate__mono">{pipeline.outputs.join(', ')}</td>
              <td title={new Date(pipeline.generated_at).toLocaleString()}>{timeAgo(pipeline.generated_at)}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
