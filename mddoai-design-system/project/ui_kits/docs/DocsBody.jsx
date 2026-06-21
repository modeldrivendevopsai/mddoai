// MDDOAI docs — article body. Renders the active page's content.
const { Badge, Icon, Callout, CodeBlock, StatusPill } = window.MDDOAIDesignSystem_0a843c;

// shared prose atoms ---------------------------------------------------------
const P = (props) => (
  <p style={{ fontFamily: 'var(--font-sans)', fontSize: 16, lineHeight: 1.7, color: 'var(--text-body)', margin: '0 0 18px', maxWidth: 720 }}>{props.children}</p>
);
const H2 = ({ id, children }) => (
  <h2 id={id} style={{ scrollMarginTop: 80, fontFamily: 'var(--font-display)', fontWeight: 700, fontSize: 26, letterSpacing: '-0.02em', color: 'var(--text-strong)', margin: '40px 0 14px' }}>{children}</h2>
);
const C = ({ children }) => (
  <code style={{ fontFamily: 'var(--font-mono)', fontSize: '0.88em', background: 'var(--surface-sunken)', border: '1px solid var(--border-subtle)', borderRadius: 5, padding: '1px 5px', color: 'var(--text-strong)' }}>{children}</code>
);

function DocsTable() {
  const rows = [
    ['swarch2gitlab', 'Architecture model', 'Direct model → pipeline. The common path.'],
    ['pim2gitlab', 'Platform-independent model', 'For abstract, platform-agnostic inputs.'],
    ['psm2gitlab', 'Platform-specific model', 'When the model already targets a platform.'],
  ];
  return (
    <div style={{ border: '1px solid var(--border-subtle)', borderRadius: 'var(--radius-md)', overflow: 'hidden', margin: '8px 0 24px', maxWidth: 720 }}>
      <table style={{ borderCollapse: 'collapse', width: '100%', fontFamily: 'var(--font-sans)', fontSize: 14.5 }}>
        <thead>
          <tr style={{ background: 'var(--surface-sunken)' }}>
            {['Command', 'Input', 'When to use'].map((h) => (
              <th key={h} style={{ textAlign: 'left', padding: '10px 14px', fontWeight: 600, fontSize: 12.5, textTransform: 'uppercase', letterSpacing: '0.05em', color: 'var(--text-faint)', borderBottom: '1px solid var(--border-subtle)' }}>{h}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {rows.map((r, i) => (
            <tr key={r[0]} style={{ borderBottom: i < rows.length - 1 ? '1px solid var(--border-subtle)' : 'none' }}>
              <td style={{ padding: '11px 14px', verticalAlign: 'top' }}><C>{r[0]}</C></td>
              <td style={{ padding: '11px 14px', verticalAlign: 'top', color: 'var(--text-body)' }}>{r[1]}</td>
              <td style={{ padding: '11px 14px', verticalAlign: 'top', color: 'var(--text-muted)' }}>{r[2]}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

function DocsBody({ active }) {
  const titleMap = {
    quickstart: 'Quickstart',
    introduction: 'Introduction',
    docker: 'Using Docker',
    types: 'Transformation types',
  };
  const title = titleMap[active] || 'Quickstart';

  return (
    <article style={{ flex: 1, minWidth: 0, maxWidth: 820, margin: '0 auto', padding: '40px 48px 96px' }}>
      {/* breadcrumb */}
      <div style={{ display: 'flex', alignItems: 'center', gap: 8, fontFamily: 'var(--font-sans)', fontSize: 13.5, color: 'var(--text-faint)', marginBottom: 18 }}>
        <span>Guide</span>
        <Icon name="chevron-right" size={14} />
        <span style={{ color: 'var(--text-muted)' }}>Get started</span>
        <Icon name="chevron-right" size={14} />
        <span style={{ color: 'var(--text-body)' }}>{title}</span>
      </div>

      <div style={{ display: 'flex', alignItems: 'center', gap: 12, marginBottom: 10 }}>
        <h1 style={{ fontFamily: 'var(--font-display)', fontWeight: 700, fontSize: 40, letterSpacing: '-0.03em', color: 'var(--text-strong)', margin: 0 }}>{title}</h1>
        <Badge variant="neutral">5 min read</Badge>
      </div>
      <P>
        MDDOAI turns a software architecture model into a ready-to-run GitLab CI/CD pipeline.
        Place a <C>.swarch</C> file in <C>input/</C>, run one transformation, and collect the
        generated <C>.gitlab-ci.yml</C> from <C>output/</C>. No build required to try it.
      </P>

      <Callout tone="tip" title="Fastest way to get started" icon={<Icon name="zap" size={18} />}>
        Pull the image and run the transformation — Docker needs zero local setup.
      </Callout>

      <H2 id="pull">1 — Pull the image</H2>
      <P>The published container lives in the GitHub Container Registry. Pull the pinned release tag rather than <C>latest</C> so builds stay reproducible.</P>
      <CodeBlock title="bash" lines={[
        { text: '# pull the pinned release', kind: 'comment' },
        { text: 'docker pull ghcr.io/modeldrivendevopsai/mddoai/mddoai:1.0.1', prompt: true },
        { text: 'Status: Downloaded newer image for mddoai:1.0.1', kind: 'output' },
      ]} />

      <H2 id="prepare">2 — Prepare input & output</H2>
      <P>Create the two working directories MDDOAI reads from and writes to, then drop your architecture model into <C>input/</C>.</P>
      <CodeBlock title="bash" lines={[
        { text: 'mkdir -p input output', prompt: true },
        { text: 'cp ./my-app.swarch input/', prompt: true },
      ]} />

      <H2 id="run">3 — Run the transformation</H2>
      <P>Mount the two directories and call a transformation command. <C>swarch2gitlab</C> is the common path — it maps an architecture model straight to a pipeline.</P>
      <CodeBlock title="bash" lines={[
        { text: 'docker run --rm \\\\', prompt: true },
        { text: '  -v "$(pwd)/input:/app/input" \\\\' },
        { text: '  -v "$(pwd)/output:/app/output" \\\\' },
        { text: '  mddoai:1.0.1 swarch2gitlab \\\\', kind: 'string' },
        { text: '  /app/input/my-app.swarch /app/output' },
        { text: 'BUILD SUCCESSFUL', kind: 'flag' },
      ]} />
      <div style={{ display: 'flex', alignItems: 'center', gap: 10, margin: '0 0 24px' }}>
        <StatusPill state="passed">transformation passed</StatusPill>
        <span style={{ fontFamily: 'var(--font-sans)', fontSize: 14, color: 'var(--text-muted)' }}>Output written to <C>output/.gitlab-ci.yml</C></span>
      </div>

      <H2 id="types">Transformation types</H2>
      <P>Three commands cover the model spectrum. Pick the one that matches your input model.</P>
      <DocsTable />

      <Callout tone="warning" title="Windows users" icon={<Icon name="terminal" size={18} />}>
        In CMD use <C>.\cli.bat</C> instead of <C>./cli.bat</C> when running from source.
      </Callout>

      {/* pager */}
      <div style={{ display: 'flex', gap: 14, marginTop: 44 }}>
        <a href="#" onClick={(e) => e.preventDefault()} style={pagerStyle('left')}>
          <span style={{ color: 'var(--text-faint)', fontSize: 12.5 }}>Previous</span>
          <span style={{ color: 'var(--text-strong)', fontWeight: 600, fontFamily: 'var(--font-sans)' }}>Introduction</span>
        </a>
        <a href="#" onClick={(e) => e.preventDefault()} style={pagerStyle('right')}>
          <span style={{ color: 'var(--text-faint)', fontSize: 12.5 }}>Next</span>
          <span style={{ color: 'var(--text-strong)', fontWeight: 600, fontFamily: 'var(--font-sans)' }}>Using Docker</span>
        </a>
      </div>
    </article>
  );
}

function pagerStyle(align) {
  return {
    flex: 1, display: 'flex', flexDirection: 'column', gap: 4,
    padding: '14px 18px', border: '1px solid var(--border-subtle)', borderRadius: 'var(--radius-md)',
    textDecoration: 'none', background: 'var(--surface-card)', textAlign: align === 'right' ? 'right' : 'left',
  };
}

window.DocsBody = DocsBody;
