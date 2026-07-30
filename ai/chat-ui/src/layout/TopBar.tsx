import './top-bar.css';

/**
 * TopBar — persistent header, identical on every page. No page-specific
 * content lives here; if a page needs breadcrumbs/actions in the header,
 * pass them in as children/props rather than special-casing routes here.
 */
export function TopBar() {
  return (
    <header className="mdd-topbar">
      <div className="mdd-topbar__mark" aria-hidden="true">
        <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.2" strokeLinecap="round" strokeLinejoin="round">
          <path d="M17.8 12A5.8 5.8 0 1 1 12 6.2M12 6.2V3m0 3.2L9.5 4.5" />
        </svg>
      </div>
      <span className="mdd-topbar__wordmark">MDDOAI</span>
    </header>
  );
}
