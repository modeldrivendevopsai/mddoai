// MDDOAI marketing — sticky site header
const { Button, Icon } = window.MDDOAIDesignSystem_0a843c;

function Header({ onNav }) {
  const [scrolled, setScrolled] = React.useState(false);
  React.useEffect(() => {
    const onScroll = () => setScrolled(window.scrollY > 8);
    window.addEventListener('scroll', onScroll);
    return () => window.removeEventListener('scroll', onScroll);
  }, []);

  const links = ['Product', 'Transformations', 'Docs', 'Pricing'];
  return (
    <header
      style={{
        position: 'sticky', top: 0, zIndex: 50,
        background: scrolled ? 'rgba(255,255,255,0.82)' : 'transparent',
        backdropFilter: scrolled ? 'saturate(140%) blur(12px)' : 'none',
        WebkitBackdropFilter: scrolled ? 'saturate(140%) blur(12px)' : 'none',
        borderBottom: scrolled ? '1px solid var(--border-subtle)' : '1px solid transparent',
        transition: 'background .25s var(--ease-out), border-color .25s var(--ease-out)',
      }}
    >
      <div style={{ maxWidth: 1180, margin: '0 auto', padding: '0 32px', height: 68, display: 'flex', alignItems: 'center', gap: 32 }}>
        <a href="#top" onClick={(e) => { e.preventDefault(); onNav && onNav('top'); }}
           style={{ display: 'flex', alignItems: 'center', gap: 10, textDecoration: 'none' }}>
          <img src="../../assets/logo/MDDOAI-transparent-2048.png" alt="" style={{ height: 30 }} />
          <span style={{ fontFamily: 'var(--font-display)', fontWeight: 700, fontSize: 19, letterSpacing: '-0.02em', color: 'var(--text-strong)' }}>MDDOAI</span>
        </a>
        <nav style={{ display: 'flex', gap: 4, marginLeft: 8 }}>
          {links.map((l) => (
            <a key={l} href="#" onClick={(e) => e.preventDefault()}
               style={{ padding: '8px 12px', fontFamily: 'var(--font-sans)', fontSize: 14, fontWeight: 500, color: 'var(--text-body)', textDecoration: 'none', borderRadius: 'var(--radius-sm)' }}
               onMouseEnter={(e) => (e.currentTarget.style.color = 'var(--text-brand)')}
               onMouseLeave={(e) => (e.currentTarget.style.color = 'var(--text-body)')}>
              {l}
            </a>
          ))}
        </nav>
        <div style={{ marginLeft: 'auto', display: 'flex', alignItems: 'center', gap: 10 }}>
          <a href="#" onClick={(e) => e.preventDefault()}
             style={{ display: 'inline-flex', alignItems: 'center', gap: 7, fontFamily: 'var(--font-sans)', fontSize: 14, fontWeight: 500, color: 'var(--text-body)', textDecoration: 'none' }}>
            <Icon name="github" size={17} /> GitHub
          </a>
          <Button variant="primary" size="sm" iconRight={<Icon name="arrow-right" size={15} />} onClick={() => onNav && onNav('quickstart')}>Get started</Button>
        </div>
      </div>
    </header>
  );
}
window.Header = Header;
