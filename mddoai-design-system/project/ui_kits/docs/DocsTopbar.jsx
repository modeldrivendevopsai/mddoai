// MDDOAI docs — top bar
const { Badge, Icon, Button } = window.MDDOAIDesignSystem_0a843c;

function DocsTopbar() {
  return (
    <header style={{ height: 60, flex: 'none', display: 'flex', alignItems: 'center', gap: 16, padding: '0 22px',
      borderBottom: '1px solid var(--border-subtle)', background: 'rgba(255,255,255,0.85)', backdropFilter: 'blur(10px)', WebkitBackdropFilter: 'blur(10px)', position: 'relative', zIndex: 10 }}>
      <div style={{ display: 'flex', alignItems: 'center', gap: 9 }}>
        <img src="../../assets/logo/MDDOAI-transparent-2048.png" alt="" style={{ height: 26 }} />
        <span style={{ fontFamily: 'var(--font-display)', fontWeight: 700, fontSize: 17, letterSpacing: '-0.02em', color: 'var(--text-strong)' }}>MDDOAI</span>
        <span style={{ fontFamily: 'var(--font-sans)', fontSize: 15, color: 'var(--text-faint)', fontWeight: 500 }}>docs</span>
      </div>
      <Badge variant="brand">v1.0.1</Badge>
      <nav style={{ display: 'flex', gap: 2, marginLeft: 12 }}>
        {['Guide', 'Reference', 'Changelog'].map((l, i) => (
          <a key={l} href="#" onClick={(e) => e.preventDefault()}
            style={{ padding: '7px 11px', borderRadius: 'var(--radius-sm)', fontFamily: 'var(--font-sans)', fontSize: 14, fontWeight: 500,
              color: i === 0 ? 'var(--text-strong)' : 'var(--text-muted)', textDecoration: 'none' }}>{l}</a>
        ))}
      </nav>
      <div style={{ marginLeft: 'auto', display: 'flex', alignItems: 'center', gap: 14 }}>
        <a href="#" onClick={(e) => e.preventDefault()} style={{ color: 'var(--text-muted)', display: 'inline-flex' }}><Icon name="git-branch" size={18} /></a>
        <a href="#" onClick={(e) => e.preventDefault()} style={{ color: 'var(--text-muted)', display: 'inline-flex' }}><Icon name="container" size={18} /></a>
        <Button variant="primary" size="sm">Get started</Button>
      </div>
    </header>
  );
}
window.DocsTopbar = DocsTopbar;
