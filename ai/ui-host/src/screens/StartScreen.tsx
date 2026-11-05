import { CYCLE_COPY, START_LISTS } from '../config/startOptions.config';
import { PlatformsList } from '../features/platforms/PlatformsList';
import { RecentPipelines } from '../features/start/RecentPipelines';
import { StartCycle } from '../features/start/StartCycle';
import { useSidebarTab } from '../layout/sidebarTab';
import './start-screen.css';

/**
 * StartScreen: the "Choose how you want to start" landing page
 * (wireframe scenario 0a): a picture of how the two options connect, each
 * with its "Open" button, and under it a short list that follows the
 * sidebar's tab: the supported platforms on Platforms, the latest pipelines
 * on Pipelines. onSelectOption and onOpenPipeline let the parent route decide
 * what happens next. This page has no routing knowledge itself.
 */
interface StartScreenProps {
  onSelectOption?: (optionId: string) => void;
  onOpenPipeline: (pipelineId: string) => void;
}

export function StartScreen({ onSelectOption, onOpenPipeline }: StartScreenProps) {
  const { activeTab } = useSidebarTab();

  return (
    <div className="mdd-start-screen">
      <p className="mdd-start-screen__subtitle">Choose how you want to start</p>
      <p className="mdd-start-screen__intro">
        <strong>{CYCLE_COPY.introLead}</strong> {CYCLE_COPY.intro}
      </p>

      <StartCycle onSelectOption={onSelectOption} />

      {activeTab === 'platform' ? (
        <>
          <h3 className="mdd-start-screen__heading">{START_LISTS.platforms.title}</h3>
          <PlatformsList />
        </>
      ) : (
        <>
          <h3 className="mdd-start-screen__heading">{START_LISTS.pipelines.title}</h3>
          <RecentPipelines onOpen={onOpenPipeline} />
        </>
      )}
    </div>
  );
}
