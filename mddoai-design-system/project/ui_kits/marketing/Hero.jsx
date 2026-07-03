// MDDOAI marketing — hero
const { Button, Icon, Badge, CodeBlock, StatusPill } = window.MDDOAIDesignSystem_0a843c;

function Hero({ onNav }) {
  return (
    <section id="top" style={{ position: 'relative', overflow: 'hidden' }}>
      {/* soft violet glow backdrop */}
      <div style={{ position: 'absolute', inset: 0, pointerEvents: 'none',
        background: 'radial-gradient(1100px 460px at 78% -10%, rgba(104,74,235,0.12), transparent 60%), radial-gradient(700px 380px at 8% 8%, rgba(164,94,237,0.08), transparent 60%)' }} />
      <div style={{ position: 'relative', maxWidth: 1180, margin: '0 auto', padding: '64px 32px 72px',
        display: 'grid', gridTemplateColumns: '1.05fr 0.95fr', gap: 56, alignItems: 'center' }}>
        <div>
          <div style={{ display: 'inline-flex', alignItems: 'center', gap: 8, padding: '5px 12px 5px 8px',
            background: 'var(--brand-faint)', border: '1px solid var(--border-brand)', borderRadius: 'var(--radius-pill)', marginBottom: 22 }}>
            <Badge variant="solid" size="sm">v1.0.1</Badge>
            <span style={{ fontFamily: 'var(--font-sans)', fontSize: 13, color: 'var(--text-brand)', fontWeight: 500 }}>Model-driven pipelines, now AI-assisted</span>
          </div>
          <h1 style={{ fontFamily: 'var(--font-display)', fontWeight: 700, fontSize: 56, lineHeight: 1.04,
            letterSpacing: '-0.03em', color: 'var(--text-strong)', margin: '0 0 20px' }}>
            Turn architecture<br/>models into{' '}
            <span style={{ background: 'var(--gradient-brand)', WebkitBackgroundClip: 'text', backgroundClip: 'text', color: 'transparent' }}>CI/CD pipelines</span>.
          </h1>
          <p style={{ fontFamily: 'var(--font-sans)', fontSize: 19, lineHeight: 1.55, color: 'var(--text-body)', margin: '0 0 30px', maxWidth: 520 }}>
            Feed MDDOAI a software architecture model and it generates a ready-to-run
            GitLab <code style={{ fontFamily: 'var(--font-mono)', fontSize: 16, color: 'var(--text-brand)', background: 'var(--brand-faint)', padding: '1px 6px', borderRadius: 6 }}>.gitlab-ci.yml</code> — no boilerplate, no copy-paste.
          </p>
          <div style={{ display: 'flex', gap: 12, marginBottom: 26 }}>
            <Button variant="gradient" size="lg" iconRight={<Icon name="arrow-right" size={18} />} onClick={() => onNav && onNav('quickstart')}>Start in 2 minutes</Button>
            <Button variant="secondary" size="lg" iconLeft={<Icon name="github" size={18} />}>View source</Button>
          </div>
          <div style={{ display: 'flex', alignItems: 'center', gap: 18, fontFamily: 'var(--font-mono)', fontSize: 12.5, color: 'var(--text-muted)' }}>
            <span style={{ display: 'inline-flex', alignItems: 'center', gap: 6 }}><Icon name="check" size={15} color="var(--success-500)" /> Open source · EPL-2.0</span>
            <span style={{ display: 'inline-flex', alignItems: 'center', gap: 6 }}><Icon name="container" size={15} color="var(--text-faint)" /> Docker &amp; CLI</span>
          </div>
        </div>

        {/* terminal demo */}
        <div style={{ position: 'relative' }}>
          <div style={{ position: 'absolute', inset: '-14px -14px auto auto', width: 130, height: 130,
            background: 'var(--gradient-brand)', filter: 'blur(48px)', opacity: 0.35, borderRadius: '50%' }} />
          <div style={{ position: 'relative' }}>
            <CodeBlock title="swarch2gitlab" lines={[
              { text: 'docker run --rm \\\\', prompt: true },
              { text: '  -v "$(pwd)/input:/app/input" \\\\' },
              { text: '  -v "$(pwd)/output:/app/output" \\\\' },
              { text: '  mddoai:1.0.1 \\\\' },
              { text: '  swarch2gitlab input.swarch /app/output', kind: 'string' },
              { text: '' },
              { text: '→ parsing architecture model…', kind: 'comment' },
              { text: '→ PIM → PSM → GitLab YAML', kind: 'comment' },
              { text: '✓ wrote output/.gitlab-ci.yml', kind: 'flag' },
              { text: 'BUILD SUCCESSFUL', kind: 'flag' },
            ]} />
            <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between',
              marginTop: 14, padding: '12px 16px', background: 'var(--surface-card)',
              border: '1px solid var(--border-subtle)', borderRadius: 'var(--radius-md)', boxShadow: 'var(--shadow-sm)' }}>
              <div style={{ display: 'flex', alignItems: 'center', gap: 10 }}>
                <Icon name="file-code" size={18} color="var(--brand)" />
                <span style={{ fontFamily: 'var(--font-mono)', fontSize: 13, color: 'var(--text-strong)' }}>.gitlab-ci.yml</span>
              </div>
              <StatusPill status="passed">generated</StatusPill>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
window.Hero = Hero;
