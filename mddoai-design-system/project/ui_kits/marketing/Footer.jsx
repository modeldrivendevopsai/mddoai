// MDDOAI marketing — footer
const { Icon } = window.MDDOAIDesignSystem_0a843c;

function Footer() {
  const cols = [
    { h: 'Product', links: ['Transformations', 'Docker images', 'Releases', 'Coverage reports'] },
    { h: 'Docs', links: ['Getting started', 'Build from source', 'GitLab integration', 'AI agents'] },
    { h: 'Project', links: ['GitHub', 'License (EPL-2.0)', 'Container registry', 'Draw.io diagrams'] },
  ];
  return (
    <footer style={{ background: 'var(--neutral-900)', borderTop: '1px solid rgba(255,255,255,0.08)' }}>
      <div style={{ maxWidth: 1180, margin: '0 auto', padding: '56px 32px 36px',
        display: 'grid', gridTemplateColumns: '1.4fr 1fr 1fr 1fr', gap: 40 }}>
        <div>
          <div style={{ display: 'flex', alignItems: 'center', gap: 10, marginBottom: 14 }}>
            <img src="../../assets/logo/MDDOAI-white-2048.png" alt="" style={{ height: 28 }} />
            <span style={{ fontFamily: 'var(--font-display)', fontWeight: 700, fontSize: 18, color: '#fff', letterSpacing: '-0.02em' }}>MDDOAI</span>
          </div>
          <p style={{ fontFamily: 'var(--font-sans)', fontSize: 13.5, lineHeight: 1.6, color: 'var(--text-on-inverse-muted)', margin: '0 0 18px', maxWidth: 280 }}>
            Model-driven DevOps AI — transform software architecture models into CI/CD pipelines.
          </p>
          <div style={{ display: 'flex', gap: 10 }}>
            {['github', 'book-open', 'container'].map((n) => (
              <a key={n} href="#" onClick={(e) => e.preventDefault()}
                 style={{ width: 34, height: 34, borderRadius: 'var(--radius-sm)', border: '1px solid rgba(255,255,255,0.12)', display: 'flex', alignItems: 'center', justifyContent: 'center', color: 'var(--text-on-inverse-muted)' }}>
                <Icon name={n} size={16} />
              </a>
            ))}
          </div>
        </div>
        {cols.map((c) => (
          <div key={c.h}>
            <div style={{ fontFamily: 'var(--font-sans)', fontSize: 12, fontWeight: 600, textTransform: 'uppercase', letterSpacing: '0.08em', color: 'var(--text-faint)', marginBottom: 14 }}>{c.h}</div>
            <ul style={{ listStyle: 'none', margin: 0, padding: 0, display: 'flex', flexDirection: 'column', gap: 10 }}>
              {c.links.map((l) => (
                <li key={l}><a href="#" onClick={(e) => e.preventDefault()}
                  style={{ fontFamily: 'var(--font-sans)', fontSize: 14, color: 'var(--text-on-inverse-muted)', textDecoration: 'none' }}
                  onMouseEnter={(e) => (e.currentTarget.style.color = '#fff')}
                  onMouseLeave={(e) => (e.currentTarget.style.color = 'var(--text-on-inverse-muted)')}>{l}</a></li>
              ))}
            </ul>
          </div>
        ))}
      </div>
      <div style={{ borderTop: '1px solid rgba(255,255,255,0.08)' }}>
        <div style={{ maxWidth: 1180, margin: '0 auto', padding: '20px 32px', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
          <span style={{ fontFamily: 'var(--font-mono)', fontSize: 12, color: 'var(--text-faint)' }}>© 2026 MDDOAI · Eclipse Public License 2.0</span>
          <span style={{ fontFamily: 'var(--font-mono)', fontSize: 12, color: 'var(--text-faint)' }}>ghcr.io/modeldrivendevopsai/mddoai</span>
        </div>
      </div>
    </footer>
  );
}
window.Footer = Footer;
