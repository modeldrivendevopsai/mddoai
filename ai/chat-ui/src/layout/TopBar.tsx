import { Link } from 'react-router-dom';
import './top-bar.css';

/**
 * TopBar — persistent header, identical on every page. No page-specific
 * content lives here; if a page needs breadcrumbs/actions in the header,
 * pass them in as children/props rather than special-casing routes here.
 */
export function TopBar() {
  return (
    <header className="mdd-topbar">
      <Link to="/" className="mdd-topbar__brand" aria-label="MDDOAI home">
        {/* The real mark (mddoai-design-system/project/assets/logo/MDDOAI-mark.svg:
            "DevOps loop + transformation arrow + AI sparkle"), ported verbatim
            (path geometry only) — fills swapped for --brand/--brand-accent,
            the exact tokens those two hex values already alias. */}
        <svg
          className="mdd-topbar__mark"
          viewBox="0 0 8.4666586 8.4666672"
          xmlns="http://www.w3.org/2000/svg"
          aria-hidden="true"
        >
          <path
            fill="var(--brand)"
            d="M 6.4706035,2.7607196 A 1.9628057,2.0434876 0 0 0 5.0827804,3.3591359 L 4.2705889,4.204622 h 0.7929705 l 0.415687,-0.4327795 a 1.4020681,1.4597008 0 1 1 1.9827016,2.0644523 1.4020041,1.459634 0 0 1 -1.9827016,0 L 5.0883032,5.4292496 H 4.2952302 L 5.0826651,6.2489988 A 1.9628057,2.0434876 0 1 0 7.8585293,3.3591359 1.9628057,2.0434876 0 0 0 6.4706035,2.7607196 Z m -4.5688669,0.00206 A 1.9628057,2.0434876 0 1 0 3.3803344,6.2489928 L 4.1676666,5.4292406 H 3.3748244 L 2.9837531,5.8362848 a 1.4020041,1.459634 0 1 1 0,-2.0643475 L 3.3993378,4.2046118 H 4.1925132 L 3.3803344,3.3590231 A 1.9628057,2.0434876 0 0 0 1.9017366,2.7627698 Z"
          />
          <rect fill="var(--brand-accent)" width="3.8085151" height="0.57656187" x="2.1273446" y="4.5290303" />
          <path
            fill="var(--brand-accent)"
            transform="matrix(1.2814028,0,0,1.2814028,3.8897851,1.5750766)"
            d="m 0.2465622,1.833806 c -0.007079,0.00437 -0.0751403,-0.8222896 -0.0810243,-0.8281736 -0.008081,-0.008081 -1.08413913,-0.3332742 -1.09014597,-0.34299657 -0.004374,-0.007079 0.82228963,-0.0751403 0.82817367,-0.0810243 0.0080811,-0.008081 0.33327417,-1.08413912 0.34299654,-1.09014596 0.007079,-0.004374 0.0751403,0.82228962 0.0810243,0.82817366 0.008081,0.008081 1.08413916,0.33327418 1.09014596,0.34299654 0.00437,0.007079 -0.82228962,0.0751403 -0.82817366,0.0810243 C 0.58147768,0.7517411 0.25628456,1.8277992 0.2465622,1.833806 Z"
          />
          <path
            fill="var(--brand-accent)"
            transform="matrix(0.64922585,-1.1047617,1.1047617,0.64922585,4.8562796,5.7036852)"
            d="m 1.3402833,0.91531538 c -0.00868,0.005096 -0.87533073,-0.48700754 -0.87540295,-0.49707412 -7.223e-5,-0.0100666 0.85942625,-0.51455485 0.86818025,-0.50958411 0.00875,0.0049707 0.015904,1.00156239 0.00722,1.00665823 z"
          />
        </svg>
        <span className="mdd-topbar__wordmark">MDDOAI</span>
      </Link>
    </header>
  );
}
