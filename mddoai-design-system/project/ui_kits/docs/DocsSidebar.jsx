// MDDOAI docs — left sidebar navigation
const { Badge, Icon } = window.MDDOAIDesignSystem_0a843c;

const DOCS_NAV = [
  { group: 'Get started', items: [
    { id: 'introduction', label: 'Introduction' },
    { id: 'quickstart', label: 'Quickstart' },
    { id: 'docker', label: 'Using Docker' },
    { id: 'source', label: 'Build from source' },
  ]},
  { group: 'Transformations', items: [
    { id: 'types', label: 'Transformation types' },
    { id: 'swarch', label: 'swarch2gitlab' },
    { id: 'pim', label: 'pim2gitlab' },
    { id: 'psm', label: 'psm2gitlab' },
  ]},
  { group: 'Operate', items: [
    { id: 'versioning', label: 'Image versioning' },
    { id: 'ci', label: 'GitLab integration' },
    { id: 'agents', label: 'AI agents' },
  ]},
];

function DocsSidebar({ active, onSelect }) {
  return (
    <aside style={{ width: 256, flex: 'none', borderRight: '1px solid var(--border-subtle)', background: 'var(--surface-card)', height: '100%', overflowY: 'auto' }}>
      <div style={{ padding: '20px 16px' }}>
        <div style={{ display: 'flex', alignItems: 'center', gap: 8, padding: '8px 10px', borderRadius: 'var(--radius-md)', border: '1px solid var(--border-subtle)', color: 'var(--text-muted)', marginBottom: 20, cursor: 'text' }}>
          <Icon name="search" size={16} />
          <span style={{ fontFamily: 'var(--font-sans)', fontSize: 13.5 }}>Search docs</span>
          <span style={{ marginLeft: 'auto', fontFamily: 'var(--font-mono)', fontSize: 11, color: 'var(--text-faint)', border: '1px solid var(--border-subtle)', borderRadius: 4, padding: '1px 5px' }}>⌘K</span>
        </div>
        {DOCS_NAV.map((g) => (
          <div key={g.group} style={{ marginBottom: 22 }}>
            <div style={{ fontFamily: 'var(--font-sans)', fontSize: 11.5, fontWeight: 600, textTransform: 'uppercase', letterSpacing: '0.07em', color: 'var(--text-faint)', padding: '0 10px', marginBottom: 8 }}>{g.group}</div>
            <ul style={{ listStyle: 'none', margin: 0, padding: 0, display: 'flex', flexDirection: 'column', gap: 1 }}>
              {g.items.map((it) => {
                const on = it.id === active;
                return (
                  <li key={it.id}>
                    <a href="#" onClick={(e) => { e.preventDefault(); onSelect(it.id); }}
                      style={{ display: 'block', padding: '7px 10px', borderRadius: 'var(--radius-sm)', fontFamily: 'var(--font-sans)', fontSize: 14,
                        fontWeight: on ? 600 : 400, color: on ? 'var(--text-brand)' : 'var(--text-body)',
                        background: on ? 'var(--brand-faint)' : 'transparent', textDecoration: 'none',
                        borderLeft: on ? '2px solid var(--brand)' : '2px solid transparent' }}
                      onMouseEnter={(e) => { if (!on) e.currentTarget.style.background = 'var(--surface-sunken)'; }}
                      onMouseLeave={(e) => { if (!on) e.currentTarget.style.background = 'transparent'; }}>
                      {it.label}
                    </a>
                  </li>
                );
              })}
            </ul>
          </div>
        ))}
      </div>
    </aside>
  );
}
window.DocsSidebar = DocsSidebar;
window.DOCS_NAV = DOCS_NAV;
