import type { CSSProperties } from 'react';
import { Button, Panel } from 'design-system';
import { CYCLE_COPY, START_OPTIONS } from '../../config/startOptions.config';
import './start-cycle.css';

/**
 * The picture the start screen opens with: the two options as the two sides
 * of one cycle. A gap found while generating a pipeline leads to platform
 * work, and a fixed platform leads back to generating. Each option is a card
 * on the ring with its own "Open" button; the ring behind it is the picture.
 *
 * The ring is drawn in a fixed box that scales as a whole, so the cards (two
 * columns of a grid laid over it, in percentages) stay on it at any width.
 * Each way round is a top or bottom arc with an arrowhead at its highest or
 * lowest point: each path has that point as its middle vertex, which is where
 * `markerMid` draws the head. The arcs run behind the cards, which are
 * opaque.
 */
interface StartCycleProps {
  onSelectOption?: (optionId: string) => void;
}

// The ring's drawing box. The picture is given the same width to height ratio
// (see CYCLE_STYLE), so the drawing and the cards laid over it scale together.
// The numbers are a design choice, found by eye: they make the arcs rise clear
// of the cards.
const BOX_WIDTH = 860;
const BOX_HEIGHT = 390;
// Hands the box's ratio to the stylesheet, which sizes the picture with it.
const CYCLE_STYLE = { '--cycle-ratio': `${BOX_WIDTH} / ${BOX_HEIGHT}` } as CSSProperties;
// How far the ring stays in from the box's sides, and from its top and bottom
// (so the stroke and the arrowheads are not clipped). Inset from the sides is
// where the ring runs behind the cards.
const INSET_X = 60;
const INSET_Y = 20;

const CENTER_X = BOX_WIDTH / 2;
const CENTER_Y = BOX_HEIGHT / 2;
const RADIUS_X = CENTER_X - INSET_X;
const RADIUS_Y = CENTER_Y - INSET_Y;
const LEFT = CENTER_X - RADIUS_X;
const RIGHT = CENTER_X + RADIUS_X;
const TOP = CENTER_Y - RADIUS_Y;
const BOTTOM = CENTER_Y + RADIUS_Y;

// A quarter of the ring's ellipse, clockwise, ending at (x, y).
const arcTo = (x: number, y: number) => `A ${RADIUS_X} ${RADIUS_Y} 0 0 1 ${x} ${y}`;
// Left to right over the top, then right to left underneath. The highest and
// lowest points are the middle vertex of each, where the arrowhead goes.
const OVER_THE_TOP = `M ${LEFT} ${CENTER_Y} ${arcTo(CENTER_X, TOP)} ${arcTo(RIGHT, CENTER_Y)}`;
const UNDERNEATH = `M ${RIGHT} ${CENTER_Y} ${arcTo(CENTER_X, BOTTOM)} ${arcTo(LEFT, CENTER_Y)}`;

export function StartCycle({ onSelectOption }: StartCycleProps) {
  return (
    <div className="mdd-cycle" style={CYCLE_STYLE}>
      <svg
        className="mdd-cycle__ring"
        viewBox={`0 0 ${BOX_WIDTH} ${BOX_HEIGHT}`}
        aria-hidden="true"
        focusable="false"
      >
        <defs>
          <marker
            id="mdd-cycle-head-pipeline"
            className="mdd-cycle__head mdd-cycle__head--pipeline"
            viewBox="0 0 10 10"
            refX="5"
            refY="5"
            markerWidth="7"
            markerHeight="7"
            orient="auto"
          >
            <path d="M 1 1 L 9 5 L 1 9 z" />
          </marker>
          <marker
            id="mdd-cycle-head-platform"
            className="mdd-cycle__head mdd-cycle__head--platform"
            viewBox="0 0 10 10"
            refX="5"
            refY="5"
            markerWidth="7"
            markerHeight="7"
            orient="auto"
          >
            <path d="M 1 1 L 9 5 L 1 9 z" />
          </marker>
        </defs>
        {/* Left to right over the top: from the pipeline to the platform. */}
        <path
          className="mdd-cycle__arc mdd-cycle__arc--pipeline"
          d={OVER_THE_TOP}
          markerMid="url(#mdd-cycle-head-pipeline)"
        />
        {/* Right to left underneath: from the platform back to the pipeline. */}
        <path
          className="mdd-cycle__arc mdd-cycle__arc--platform"
          d={UNDERNEATH}
          markerMid="url(#mdd-cycle-head-platform)"
        />
      </svg>

      <div className="mdd-cycle__nodes">
        {START_OPTIONS.map((option) => (
          <Panel key={option.id} tone={option.tone} className={`mdd-cycle__node mdd-cycle__node--${option.tone}`}>
            <h2>{option.title}</h2>
            <p>{option.description}</p>
            <Button variant="primary" onClick={() => onSelectOption?.(option.id)}>
              {option.cta}
            </Button>
          </Panel>
        ))}
      </div>

      <p className="mdd-cycle__caption mdd-cycle__caption--pipeline">
        <strong>{CYCLE_COPY.gapFound.lead}</strong>
        <span>{CYCLE_COPY.gapFound.then}</span>
      </p>
      <p className="mdd-cycle__caption mdd-cycle__caption--platform">
        <strong>{CYCLE_COPY.platformFixed.lead}</strong>
        <span>{CYCLE_COPY.platformFixed.then}</span>
      </p>
    </div>
  );
}
