// MDDOAI docs — right-hand "on this page" rail
function DocsOnThisPage() {
  const items = [
    { id: 'pull', label: '1 — Pull the image' },
    { id: 'prepare', label: '2 — Prepare input & output' },
    { id: 'run', label: '3 — Run the transformation' },
    { id: 'types', label: 'Transformation types' },
  ];
  const [active, setActive] = React.useState('pull');
  return (
    <aside style={{ width: 232, flex: 'none', padding: '40px 24px', position: 'sticky', top: 60, alignSelf: 'flex-start' }}>
      <div style={{ fontFamily: 'var(--font-sans)', fontSize: 11.5, fontWeight: 600, textTransform: 'uppercase', letterSpacing: '0.07em', color: 'var(--text-faint)', marginBottom: 12 }}>On this page</div>
      <ul style={{ listStyle: 'none', margin: 0, padding: 0, display: 'flex', flexDirection: 'column', gap: 2, borderLeft: '1px solid var(--border-subtle)' }}>
        {items.map((it) => {
          const on = it.id === active;
          return (
            <li key={it.id}>
              <a href={'#' + it.id} onClick={() => setActive(it.id)}
                style={{ display: 'block', padding: '5px 0 5px 14px', marginLeft: -1,
                  borderLeft: on ? '2px solid var(--brand)' : '2px solid transparent',
                  fontFamily: 'var(--font-sans)', fontSize: 13.5,
                  color: on ? 'var(--text-brand)' : 'var(--text-muted)', fontWeight: on ? 600 : 400,
                  textDecoration: 'none' }}>{it.label}</a>
            </li>
          );
        })}
      </ul>
      <div style={{ marginTop: 26, paddingTop: 20, borderTop: '1px solid var(--border-subtle)', display: 'flex', flexDirection: 'column', gap: 10 }}>
        <a href="#" onClick={(e) => e.preventDefault()} style={railLink}>Edit this page</a>
        <a href="#" onClick={(e) => e.preventDefault()} style={railLink}>Open an issue</a>
      </div>
    </aside>
  );
}
const railLink = { fontFamily: 'var(--font-sans)', fontSize: 13.5, color: 'var(--text-muted)', textDecoration: 'none' };
window.DocsOnThisPage = DocsOnThisPage;
