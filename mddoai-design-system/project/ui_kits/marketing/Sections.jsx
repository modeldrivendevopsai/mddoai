// MDDOAI marketing — how it works + transformation types + features
const { Card, Icon, Badge } = window.MDDOAIDesignSystem_0a843c;

function Eyebrow({ children }) {
  return <div style={{ fontFamily: 'var(--font-sans)', fontSize: 13, fontWeight: 600, textTransform: 'uppercase', letterSpacing: '0.08em', color: 'var(--text-brand)', marginBottom: 12 }}>{children}</div>;
}
function SectionTitle({ children, sub }) {
  return (
    <div style={{ maxWidth: 640 }}>
      <h2 style={{ fontFamily: 'var(--font-display)', fontWeight: 700, fontSize: 38, lineHeight: 1.1, letterSpacing: '-0.025em', color: 'var(--text-strong)', margin: '0 0 14px' }}>{children}</h2>
      {sub && <p style={{ fontFamily: 'var(--font-sans)', fontSize: 17, lineHeight: 1.55, color: 'var(--text-body)', margin: 0 }}>{sub}</p>}
    </div>
  );
}

function Pipeline() {
  const steps = [
    { icon: 'box', tag: '.swarch', title: 'Model your architecture', body: 'Describe services, dependencies, and stages in a software architecture model.' },
    { icon: 'wand-sparkles', tag: 'PIM → PSM', title: 'MDDOAI transforms', body: 'Model-driven transformations map your architecture to a platform-specific pipeline model.' },
    { icon: 'file-code', tag: '.gitlab-ci.yml', title: 'Run the pipeline', body: 'Get a ready-to-commit GitLab CI/CD configuration, generated and validated.' },
  ];
  return (
    <section style={{ background: 'var(--surface-card)', borderTop: '1px solid var(--border-subtle)', borderBottom: '1px solid var(--border-subtle)' }}>
      <div style={{ maxWidth: 1180, margin: '0 auto', padding: '72px 32px' }}>
        <Eyebrow>How it works</Eyebrow>
        <SectionTitle sub="One transformation, three steps. The model is the single source of truth — the pipeline is generated, never hand-maintained.">From model to pipeline</SectionTitle>
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: 20, marginTop: 40, position: 'relative' }}>
          {steps.map((s, i) => (
            <div key={i} style={{ position: 'relative' }}>
              <div style={{ display: 'flex', alignItems: 'center', gap: 12, marginBottom: 16 }}>
                <div style={{ width: 44, height: 44, borderRadius: 'var(--radius-md)', background: 'var(--brand-faint)', border: '1px solid var(--border-brand)', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                  <Icon name={s.icon} size={21} color="var(--brand)" />
                </div>
                <span style={{ fontFamily: 'var(--font-mono)', fontSize: 11, color: 'var(--text-faint)' }}>0{i + 1}</span>
                {i < steps.length - 1 && <Icon name="arrow-right" size={18} color="var(--border-strong)" style={{ marginLeft: 'auto' }} />}
              </div>
              <Badge variant="brand">{s.tag}</Badge>
              <h3 style={{ fontFamily: 'var(--font-display)', fontWeight: 600, fontSize: 19, color: 'var(--text-strong)', margin: '12px 0 8px', letterSpacing: '-0.01em' }}>{s.title}</h3>
              <p style={{ fontFamily: 'var(--font-sans)', fontSize: 14.5, lineHeight: 1.55, color: 'var(--text-muted)', margin: 0 }}>{s.body}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}

function Transformations() {
  const types = [
    { cmd: 'swarch2gitlab', input: '.swarch', use: 'End-to-end transformation', desc: 'Architecture model straight to a GitLab pipeline. The default path.', primary: true },
    { cmd: 'pim2gitlab', input: '.pim', use: 'Custom PIM workflows', desc: 'Start from a platform-independent model for advanced control.' },
    { cmd: 'psm2gitlab', input: '.gitlabpsm', use: 'Direct PSM to YAML', desc: 'Already have a platform-specific model? Emit YAML directly.' },
  ];
  return (
    <section style={{ maxWidth: 1180, margin: '0 auto', padding: '78px 32px' }}>
      <Eyebrow>Transformations</Eyebrow>
      <SectionTitle sub="Three transformation types cover the full model-driven spectrum, from a single architecture file to fine-grained platform control.">Pick your entry point</SectionTitle>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: 18, marginTop: 38 }}>
        {types.map((t) => (
          <Card key={t.cmd} hover padding={22} style={t.primary ? { borderColor: 'var(--border-brand)', boxShadow: 'var(--shadow-md)' } : {}}>
            <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: 16 }}>
              <Icon name="git-compare-arrows" size={22} color="var(--brand)" />
              {t.primary && <Badge variant="solid" size="sm">recommended</Badge>}
            </div>
            <div style={{ fontFamily: 'var(--font-mono)', fontSize: 16, fontWeight: 600, color: 'var(--text-strong)' }}>{t.cmd}</div>
            <div style={{ display: 'flex', gap: 8, alignItems: 'center', margin: '10px 0 14px', fontFamily: 'var(--font-mono)', fontSize: 12 }}>
              <span style={{ color: 'var(--text-muted)' }}>{t.input}</span>
              <Icon name="arrow-right" size={13} color="var(--text-faint)" />
              <span style={{ color: 'var(--text-brand)' }}>.gitlab-ci.yml</span>
            </div>
            <div style={{ fontFamily: 'var(--font-sans)', fontSize: 14, color: 'var(--text-muted)', lineHeight: 1.55, marginBottom: 8 }}>{t.desc}</div>
            <div style={{ fontFamily: 'var(--font-sans)', fontSize: 13, color: 'var(--text-faint)' }}>{t.use}</div>
          </Card>
        ))}
      </div>
    </section>
  );
}

function Features() {
  const feats = [
    { icon: 'boxes', title: 'Model-driven core', body: 'Built on EMF, Acceleo, and ATL. Metamodels and templates do the work — deterministic, inspectable, versionable.' },
    { icon: 'container', title: 'Docker or source', body: 'Pull a versioned image and run in two minutes, or build from source with Gradle for development.' },
    { icon: 'bot', title: 'AI-assisted', body: 'Copilot agents handle PR review, test generation, and transformation debugging across the project.' },
    { icon: 'git-branch', title: 'Pipeline-native', body: 'Drop MDDOAI into your own CI/CD to regenerate pipelines as architecture evolves.' },
    { icon: 'tags', title: 'Semantic releases', body: 'Three-tier image tags — snapshot, main, and permanent semver releases for production.' },
    { icon: 'shield-check', title: 'Tested & covered', body: 'Unit, integration, and end-to-end suites with published coverage reports.' },
  ];
  return (
    <section style={{ background: 'var(--surface-card)', borderTop: '1px solid var(--border-subtle)' }}>
      <div style={{ maxWidth: 1180, margin: '0 auto', padding: '78px 32px' }}>
        <Eyebrow>Why MDDOAI</Eyebrow>
        <SectionTitle sub="An engineering tool, not a wizard. Everything is a model, a template, or a command.">Built for platform engineers</SectionTitle>
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: 18, marginTop: 38 }}>
          {feats.map((f) => (
            <div key={f.title} style={{ padding: 4 }}>
              <div style={{ width: 40, height: 40, borderRadius: 'var(--radius-md)', background: 'var(--surface-sunken)', display: 'flex', alignItems: 'center', justifyContent: 'center', marginBottom: 14 }}>
                <Icon name={f.icon} size={20} color="var(--brand)" />
              </div>
              <h3 style={{ fontFamily: 'var(--font-display)', fontWeight: 600, fontSize: 17, color: 'var(--text-strong)', margin: '0 0 7px', letterSpacing: '-0.01em' }}>{f.title}</h3>
              <p style={{ fontFamily: 'var(--font-sans)', fontSize: 14, lineHeight: 1.55, color: 'var(--text-muted)', margin: 0 }}>{f.body}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}
window.Pipeline = Pipeline;
window.Transformations = Transformations;
window.Features = Features;
