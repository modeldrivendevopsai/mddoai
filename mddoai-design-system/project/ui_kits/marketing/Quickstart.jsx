// MDDOAI marketing — interactive quickstart with method tabs
const { Tabs, CodeBlock, Icon, Callout, Button } = window.MDDOAIDesignSystem_0a843c;

function Quickstart() {
  const [method, setMethod] = React.useState('docker');

  const docker = [
    { text: '# 1 — pull the image', kind: 'comment' },
    { text: 'docker pull ghcr.io/modeldrivendevopsai/mddoai/mddoai:1.0.1', prompt: true },
    { text: '' },
    { text: '# 2 — prepare input/output', kind: 'comment' },
    { text: 'mkdir -p input output', prompt: true },
    { text: '' },
    { text: '# 3 — run the transformation', kind: 'comment' },
    { text: 'docker run --rm \\\\', prompt: true },
    { text: '  -v "$(pwd)/input:/app/input" \\\\' },
    { text: '  -v "$(pwd)/output:/app/output" \\\\' },
    { text: '  mddoai:1.0.1 swarch2gitlab \\\\', kind: 'string' },
    { text: '  /app/input/my-app.swarch /app/output' },
  ];
  const source = [
    { text: '# clone & build with Gradle', kind: 'comment' },
    { text: 'git clone https://github.com/modeldrivendevopsai/mddoai.git', prompt: true },
    { text: 'cd mddoai/main', prompt: true },
    { text: './gradlew clean installDist', prompt: true },
    { text: '' },
    { text: '# run the CLI', kind: 'comment' },
    { text: './cli.bat swarch2gitlab \\\\', prompt: true },
    { text: '  ./input/my-app.swarch ./output' },
    { text: 'BUILD SUCCESSFUL', kind: 'flag' },
  ];
  const ci = [
    { text: '# .gitlab-ci.yml — regenerate on every push', kind: 'comment' },
    { text: 'generate-pipeline:', kind: 'string' },
    { text: '  image: ghcr.io/modeldrivendevopsai/mddoai/mddoai:1.0.1' },
    { text: '  script:' },
    { text: '    - swarch2gitlab architecture.swarch ./generated', kind: 'string' },
    { text: '  artifacts:' },
    { text: '    paths: [ generated/.gitlab-ci.yml ]' },
  ];
  const map = { docker, source, ci };

  return (
    <section id="quickstart" style={{ maxWidth: 1180, margin: '0 auto', padding: '78px 32px' }}>
      <div style={{ display: 'grid', gridTemplateColumns: '0.85fr 1.15fr', gap: 48, alignItems: 'start' }}>
        <div style={{ position: 'sticky', top: 96 }}>
          <div style={{ fontFamily: 'var(--font-sans)', fontSize: 13, fontWeight: 600, textTransform: 'uppercase', letterSpacing: '0.08em', color: 'var(--text-brand)', marginBottom: 12 }}>Quickstart</div>
          <h2 style={{ fontFamily: 'var(--font-display)', fontWeight: 700, fontSize: 38, lineHeight: 1.1, letterSpacing: '-0.025em', color: 'var(--text-strong)', margin: '0 0 16px' }}>Two ways to run it</h2>
          <p style={{ fontFamily: 'var(--font-sans)', fontSize: 16, lineHeight: 1.6, color: 'var(--text-body)', margin: '0 0 24px' }}>
            Use Docker for a 2-minute start with zero setup, or build from source for development and customization.
          </p>
          <Callout tone="warning" title="Windows users" icon={<Icon name="terminal" size={17} />}>
            In CMD use <code style={{ fontFamily: 'var(--font-mono)' }}>.\cli.bat</code> instead of <code style={{ fontFamily: 'var(--font-mono)' }}>./cli.bat</code>.
          </Callout>
          <div style={{ marginTop: 22, display: 'flex', gap: 10 }}>
            <Button variant="primary" iconRight={<Icon name="arrow-right" size={15} />}>Read the full guide</Button>
          </div>
        </div>
        <div>
          <div style={{ marginBottom: 16 }}>
            <Tabs value={method} onChange={setMethod} tabs={[
              { id: 'docker', label: 'Docker', icon: <Icon name="container" size={15} /> },
              { id: 'source', label: 'From source', icon: <Icon name="git-branch" size={15} /> },
              { id: 'ci', label: 'In CI/CD', icon: <Icon name="workflow" size={15} /> },
            ]} />
          </div>
          <CodeBlock key={method} title={method === 'ci' ? '.gitlab-ci.yml' : 'bash'} lang={method === 'ci' ? 'yaml' : 'bash'} lines={map[method]} />
        </div>
      </div>
    </section>
  );
}

function CTA({ onNav }) {
  return (
    <section style={{ position: 'relative', overflow: 'hidden', background: 'var(--surface-inverse)' }}>
      <div style={{ position: 'absolute', inset: 0, pointerEvents: 'none',
        background: 'radial-gradient(680px 320px at 50% 120%, rgba(104,74,235,0.45), transparent 65%), radial-gradient(420px 240px at 85% -20%, rgba(164,94,237,0.3), transparent 60%)' }} />
      <div style={{ position: 'relative', maxWidth: 880, margin: '0 auto', padding: '84px 32px', textAlign: 'center' }}>
        <img src="../../assets/logo/MDDOAI-white-2048.png" alt="" style={{ height: 52, marginBottom: 26 }} />
        <h2 style={{ fontFamily: 'var(--font-display)', fontWeight: 700, fontSize: 44, lineHeight: 1.08, letterSpacing: '-0.03em', color: '#fff', margin: '0 0 16px' }}>
          Stop hand-writing pipelines.
        </h2>
        <p style={{ fontFamily: 'var(--font-sans)', fontSize: 18, lineHeight: 1.55, color: 'var(--text-on-inverse-muted)', margin: '0 auto 30px', maxWidth: 540 }}>
          Generate your first GitLab CI/CD pipeline from an architecture model in under two minutes.
        </p>
        <div style={{ display: 'flex', gap: 12, justifyContent: 'center' }}>
          <Button variant="gradient" size="lg" iconRight={<Icon name="arrow-right" size={18} />} onClick={() => onNav && onNav('quickstart')}>Get started free</Button>
          <Button variant="secondary" size="lg" iconLeft={<Icon name="github" size={18} />}>Star on GitHub</Button>
        </div>
      </div>
    </section>
  );
}
window.Quickstart = Quickstart;
window.CTA = CTA;
