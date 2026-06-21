// MDDOAI product demo — scene components.
// Composes the animation starter (Stage/Sprite/Easing) into a 6-scene story:
// Title → Input (model) → Command → Output (yaml) → Result (pipeline) → Outro.
// All visuals use the MDDOAI brand foundations (violet, slate, Space Grotesk / Plex).

const C = {
  ink:        '#0e0d15',
  inkPanel:   '#16141f',
  inkBorder:  'rgba(255,255,255,0.10)',
  page:       '#f8f8fb',
  card:       '#ffffff',
  border:     '#e4e4ee',
  borderSoft: '#f0f0f5',
  brand:      '#684aeb',
  accent:     '#a45eed',
  grad:       'linear-gradient(120deg,#684aeb 0%,#a45eed 100%)',
  success:    '#15a36a',
  successBg:  '#e3f6ec',
  warning:    '#cf8a00',
  warningBg:  '#fbf0d6',
  info:       '#3a7bd5',
  tStrong:    '#181820',
  tBody:      '#3d3d4c',
  tMuted:     '#74748d',
  tFaint:     '#a1a1b8',
  onDark:     '#f0f0f5',
  onDarkMut:  '#9b9bb5',
};
const F = {
  display: "'Space Grotesk','IBM Plex Sans',sans-serif",
  sans:    "'IBM Plex Sans',system-ui,sans-serif",
  mono:    "'IBM Plex Mono',ui-monospace,monospace",
};
const LOGO = '../assets/logo/MDDOAI-transparent-2048.png';

// ── helpers ──────────────────────────────────────────────────────────────────

// Scene shell: full-frame background + crossfade + slow camera push.
function SceneFrame({ bg = C.ink, zoomFrom = 1, zoomTo = 1, fade = 0.55, children }) {
  const { localTime, duration } = useSprite();
  const fadeIn = clamp(localTime / fade, 0, 1);
  const fadeOut = clamp((duration - localTime) / fade, 0, 1);
  const opacity = Math.min(fadeIn, fadeOut);
  const p = duration > 0 ? clamp(localTime / duration, 0, 1) : 0;
  const scale = zoomFrom + (zoomTo - zoomFrom) * Easing.easeInOutSine(p);
  return (
    <div style={{ position: 'absolute', inset: 0, background: bg, opacity }}>
      <div style={{ position: 'absolute', inset: 0, transform: `scale(${scale})`, transformOrigin: 'center', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
        {children}
      </div>
    </div>
  );
}

function Eyebrow({ children, dark = false }) {
  return (
    <div style={{ display: 'flex', alignItems: 'center', gap: 12, marginBottom: 22 }}>
      <span style={{ width: 30, height: 4, borderRadius: 2, background: C.grad, display: 'inline-block' }} />
      <span style={{ fontFamily: F.sans, fontWeight: 600, fontSize: 19, letterSpacing: '0.14em', textTransform: 'uppercase', color: dark ? C.accent : C.brand }}>{children}</span>
    </div>
  );
}

// Typewriter span driven by absolute stage time.
function Typed({ text, start, cps = 34, color = C.onDark, size = 30, weight = 500, caret = true }) {
  const t = useTime();
  const elapsed = Math.max(0, t - start);
  const n = Math.min(text.length, Math.floor(elapsed * cps));
  const done = n >= text.length;
  const blink = Math.floor(t * 2) % 2 === 0;
  const showCaret = caret && (!done || blink);
  return (
    <span style={{ fontFamily: F.mono, fontSize: size, fontWeight: weight, color, whiteSpace: 'pre-wrap', lineHeight: 1.55 }}>
      {text.slice(0, n)}
      <span style={{ display: 'inline-block', width: 11, height: size * 0.95, transform: 'translateY(3px)', marginLeft: 2, background: showCaret ? C.accent : 'transparent', borderRadius: 1 }} />
    </span>
  );
}

// Lines that fade/slide in one after another, gated on absolute stage time.
function RevealLines({ lines, start, interval = 0.5, riseFrom = 12, lineGap = 0 }) {
  const t = useTime();
  return lines.map((ln, i) => {
    const appear = start + i * interval;
    const o = clamp((t - appear) / 0.4, 0, 1);
    const ty = (1 - Easing.easeOutCubic(o)) * riseFrom;
    return (
      <div key={i} style={{ opacity: o, transform: `translateY(${ty}px)`, marginTop: i === 0 ? 0 : lineGap, ...(ln.style || {}) }}>
        {ln.node}
      </div>
    );
  });
}

// A macOS-ish window chrome wrapper (dark terminal / file panels).
function Window({ title, accent = false, width, children, dark = true }) {
  return (
    <div style={{
      width, borderRadius: 14, overflow: 'hidden',
      background: dark ? C.inkPanel : C.card,
      border: `1px solid ${dark ? C.inkBorder : C.border}`,
      boxShadow: dark ? '0 40px 90px -30px rgba(0,0,0,0.7), 0 0 0 1px rgba(104,74,235,0.18)' : '0 36px 80px -34px rgba(24,24,40,0.4)',
    }}>
      <div style={{ height: 48, display: 'flex', alignItems: 'center', gap: 9, padding: '0 18px', background: dark ? 'rgba(255,255,255,0.03)' : C.page, borderBottom: `1px solid ${dark ? C.inkBorder : C.border}` }}>
        <span style={{ width: 13, height: 13, borderRadius: '50%', background: '#ff5f57' }} />
        <span style={{ width: 13, height: 13, borderRadius: '50%', background: '#febc2e' }} />
        <span style={{ width: 13, height: 13, borderRadius: '50%', background: '#28c840' }} />
        <span style={{ marginLeft: 14, fontFamily: F.mono, fontSize: 16, color: dark ? C.onDarkMut : C.tMuted, display: 'flex', alignItems: 'center', gap: 8 }}>
          {accent && <span style={{ width: 8, height: 8, borderRadius: '50%', background: C.accent }} />}
          {title}
        </span>
      </div>
      {children}
    </div>
  );
}

// In-flow fade/rise block (normal layout flow — unlike absolute TextSprite).
function FadeUp({ start, dur = 0.5, rise = 16, ease = Easing.easeOutCubic, style, children }) {
  const t = useTime();
  const o = clamp((t - start) / dur, 0, 1);
  const e = ease(o);
  return <div style={{ opacity: o, transform: `translateY(${(1 - e) * rise}px)`, ...style }}>{children}</div>;
}

// ── Scene 1 — Title ──────────────────────────────────────────────────────────
function TitleScene() {
  return (
    <Sprite start={0} end={5}>
      <SceneFrame bg={C.ink} zoomFrom={1.0} zoomTo={1.05}>
        {/* violet glow */}
        <div style={{ position: 'absolute', width: 900, height: 900, borderRadius: '50%', background: 'radial-gradient(circle, rgba(104,74,235,0.30) 0%, rgba(104,74,235,0) 62%)', filter: 'blur(10px)' }} />
        <div style={{ position: 'relative', display: 'flex', flexDirection: 'column', alignItems: 'center', textAlign: 'center' }}>
          <Sprite start={0.2} end={5}>
            <TitleLogo />
          </Sprite>
          <FadeUp start={0.85} rise={20} style={{ fontFamily: F.display, fontWeight: 700, fontSize: 110, letterSpacing: '-0.03em', color: C.onDark, lineHeight: 1.0 }}>MDDOAI</FadeUp>
          <FadeUp start={1.5} rise={16} style={{ marginTop: 22, fontFamily: F.sans, fontWeight: 400, fontSize: 36, color: C.onDarkMut, letterSpacing: '0.01em' }}>Model-Driven DevOps with Artificial Intelligence</FadeUp>
        </div>
      </SceneFrame>
    </Sprite>
  );
}
function TitleLogo() {
  const { localTime } = useSprite();
  const t = Easing.easeOutBack(clamp(localTime / 0.7, 0, 1));
  const o = clamp(localTime / 0.5, 0, 1);
  return (
    <img src={LOGO} alt="" style={{ width: 168, height: 168, marginBottom: 30, opacity: o, transform: `scale(${0.6 + 0.4 * t})`, filter: 'drop-shadow(0 12px 40px rgba(104,74,235,0.5))' }} />
  );
}

// ── Scene 2 — Input (architecture model) ─────────────────────────────────────
const NODES = [
  { id: 'gw',  label: 'api-gateway', x: 300, y: 24,  color: C.brand },
  { id: 'auth',label: 'auth-svc',    x: 70,  y: 210, color: C.accent },
  { id: 'ord', label: 'orders-svc',  x: 300, y: 210, color: C.info },
  { id: 'db',  label: 'orders-db',   x: 530, y: 210, color: C.success },
];
function ModelDiagram() {
  const t = useTime();
  const NW = 200, NH = 64;
  const cx = (n) => n.x + NW / 2, cy = (n) => n.y + NH / 2;
  const edges = [['gw', 'auth'], ['gw', 'ord'], ['ord', 'db']];
  const byId = Object.fromEntries(NODES.map((n) => [n.id, n]));
  return (
    <div style={{ position: 'relative', width: 730, height: 300, margin: '0 auto' }}>
      <svg width="730" height="300" style={{ position: 'absolute', inset: 0 }}>
        {edges.map(([a, b], i) => {
          const o = clamp((t - (5.4 + i * 0.18)) / 0.4, 0, 1);
          return <line key={i} x1={cx(byId[a])} y1={cy(byId[a]) + 18} x2={cx(byId[b])} y2={cy(byId[b]) - 18} stroke={C.border} strokeWidth="2.5" strokeDasharray="6 6" style={{ opacity: o }} />;
        })}
      </svg>
      {NODES.map((n, i) => {
        const o = clamp((t - (5.0 + i * 0.16)) / 0.45, 0, 1);
        const s = 0.85 + 0.15 * Easing.easeOutBack(o);
        return (
          <div key={n.id} style={{ position: 'absolute', left: n.x, top: n.y, width: NW, height: NH, opacity: o, transform: `scale(${s})`, transformOrigin: 'center', display: 'flex', alignItems: 'center', gap: 12, padding: '0 18px', background: C.card, border: `1px solid ${C.border}`, borderRadius: 10, boxShadow: '0 8px 20px -10px rgba(24,24,40,0.25)' }}>
            <span style={{ width: 12, height: 12, borderRadius: 3, background: n.color, flex: 'none' }} />
            <span style={{ fontFamily: F.mono, fontSize: 21, color: C.tStrong, fontWeight: 500 }}>{n.label}</span>
          </div>
        );
      })}
    </div>
  );
}
function InputScene() {
  return (
    <Sprite start={5} end={10}>
      <SceneFrame bg={C.page} zoomFrom={1.0} zoomTo={1.05}>
        <div style={{ width: 1300, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
          <div style={{ alignSelf: 'flex-start', marginLeft: 60 }}>
            <Eyebrow>Step 01 — Input</Eyebrow>
            <div style={{ fontFamily: F.display, fontWeight: 700, fontSize: 58, letterSpacing: '-0.03em', color: C.tStrong, marginBottom: 34, lineHeight: 1.05 }}>Start with your architecture model.</div>
          </div>
          <Window title="my-app.swarch" accent dark={false} width={900}>
            <div style={{ padding: '40px 40px 46px' }}>
              <ModelDiagram />
            </div>
          </Window>
        </div>
      </SceneFrame>
    </Sprite>
  );
}

// ── Scene 3 — Command ────────────────────────────────────────────────────────
function CommandScene() {
  const check = (txt) => (
    <div style={{ display: 'flex', alignItems: 'center', gap: 14, fontFamily: F.mono, fontSize: 26, color: C.onDark }}>
      <span style={{ color: C.success, fontWeight: 700 }}>✓</span>{txt}
    </div>
  );
  return (
    <Sprite start={10} end={16.5}>
      <SceneFrame bg={C.ink} zoomFrom={1.0} zoomTo={1.04}>
        {/* glow */}
        <div style={{ position: 'absolute', width: 1100, height: 700, borderRadius: '50%', background: 'radial-gradient(circle, rgba(104,74,235,0.16) 0%, rgba(104,74,235,0) 65%)' }} />
        <div style={{ position: 'relative', width: 1240 }}>
          <Eyebrow dark>Step 02 — One command</Eyebrow>
          <Window title="bash" width={1240}>
            <div style={{ padding: '34px 40px 40px', minHeight: 320 }}>
              <div style={{ marginBottom: 22 }}>
                <span style={{ fontFamily: F.mono, fontSize: 30, color: C.accent, marginRight: 14 }}>$</span>
                <Typed start={10.5} cps={26} size={30} color={C.onDark} text={'mddoai input/my-app.swarch gitlab output/'} />
              </div>
              <Sprite start={13.2} end={16.5}>
                <div style={{ display: 'flex', flexDirection: 'column', gap: 16, marginTop: 14 }}>
                  <RevealLines start={13.3} interval={0.42} lineGap={16} lines={[
                    { node: check('Parsing architecture model') },
                    { node: check('Generating MD artefacts') },
                    { node: check('Applying Acceleo templates') },
                    { node: (
                      <div style={{ display: 'flex', alignItems: 'center', gap: 14, fontFamily: F.mono, fontSize: 26, color: C.onDark }}>
                        <span style={{ color: C.success, fontWeight: 700 }}>✓</span>
                        <span>Generating </span>
                        <span style={{ color: '#fc6d26', fontWeight: 700 }}>GitLab</span>
                        <span> CI pipeline</span>
                      </div>
                    ) },
                    { node: (
                      <div style={{ display: 'flex', alignItems: 'center', gap: 14, fontFamily: F.mono, fontSize: 26, color: C.onDarkMut, marginTop: 4 }}>
                        <span style={{ color: C.accent }}>→</span> output/.gitlab-ci.yml written
                      </div>
                    ) },
                  ]} />
                </div>
              </Sprite>
            </div>
          </Window>
        </div>
      </SceneFrame>
    </Sprite>
  );
}

// ── Scene 4 — Output (generated yaml) ────────────────────────────────────────
const YAML = [
  { t: 'stages:', c: C.info },
  { t: '  - build', c: C.onDark },
  { t: '  - test', c: C.onDark },
  { t: '  - deploy', c: C.onDark },
  { t: '', c: C.onDark },
  { t: 'build:', c: C.accent },
  { t: '  stage: build', c: C.onDarkMut },
  { t: '  script: mvn package', c: C.onDark },
  { t: '', c: C.onDark },
  { t: 'test:', c: C.accent },
  { t: '  stage: test', c: C.onDarkMut },
  { t: '  script: mvn verify', c: C.onDark },
  { t: '', c: C.onDark },
  { t: 'deploy:', c: C.accent },
  { t: '  stage: deploy', c: C.onDarkMut },
  { t: '  script: ./deploy.sh', c: C.onDark },
];
function OutputScene() {
  return (
    <Sprite start={16} end={22}>
      <SceneFrame bg={C.page} zoomFrom={1.0} zoomTo={1.045}>
        <div style={{ width: 1320, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
          <div style={{ alignSelf: 'flex-start', marginLeft: 40, display: 'flex', alignItems: 'flex-end', justifyContent: 'space-between', width: '100%' }}>
            <div>
              <Eyebrow>Step 03 — Output</Eyebrow>
              <div style={{ fontFamily: F.display, fontWeight: 700, fontSize: 56, letterSpacing: '-0.03em', color: C.tStrong, marginBottom: 30, lineHeight: 1.05 }}>A ready-to-run pipeline, generated.</div>
            </div>
            <div style={{ display: 'flex', alignItems: 'center', gap: 16, marginBottom: 36, fontFamily: F.mono, fontSize: 22, color: C.tMuted }}>
              <span>my-app.swarch</span>
              <span style={{ width: 54, height: 4, borderRadius: 2, background: C.grad }} />
              <span style={{ color: C.tStrong, fontWeight: 600 }}>.gitlab-ci.yml</span>
            </div>
          </div>
          <Window title=".gitlab-ci.yml" accent width={900}>
            <div style={{ padding: '30px 44px 36px', display: 'flex', flexDirection: 'column', columnGap: 0 }}>
              <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', columnGap: 60, rowGap: 0 }}>
                <div>
                  <RevealLines start={16.6} interval={0.16} riseFrom={6} lines={YAML.slice(0, 8).map((l) => ({
                    node: <span style={{ fontFamily: F.mono, fontSize: 23, color: l.c, lineHeight: 1.7, whiteSpace: 'pre', display: 'block', minHeight: 39 }}>{l.t || ' '}</span>,
                  }))} />
                </div>
                <div>
                  <RevealLines start={16.6 + 8 * 0.16} interval={0.16} riseFrom={6} lines={YAML.slice(8).map((l) => ({
                    node: <span style={{ fontFamily: F.mono, fontSize: 23, color: l.c, lineHeight: 1.7, whiteSpace: 'pre', display: 'block', minHeight: 39 }}>{l.t || ' '}</span>,
                  }))} />
                </div>
              </div>
            </div>
          </Window>
        </div>
      </SceneFrame>
    </Sprite>
  );
}

// ── Scene 5 — Parallel pipelines + merge ────────────────────────────────────
const PIPELINES = [
  { label: 'api-gateway',  color: '#684aeb', stageStarts: [22.7, 23.35, 23.95], rowAppear: 22.35 },
  { label: 'auth-svc',     color: '#a45eed', stageStarts: [23.0,  23.65, 24.25], rowAppear: 22.6  },
  { label: 'orders-svc',   color: '#3a7bd5', stageStarts: [23.3,  23.95, 24.55], rowAppear: 22.85 },
  { label: 'orders-db',    color: '#15a36a', stageStarts: [23.6,  24.25, 24.85], rowAppear: 23.1  },
];
// Scene 5 timing
const MERGE_SCENE_START = 27;
const MERGE_ANIM_START  = 27.7;
const MERGED_STAGE_S    = [28.4, 29.1, 29.8];
const BANNER_TIME       = 30.6;

// ── shared chip (animated stage state) ───────────────────────────────────────
function StageChip({ name, starts, baseTime, minW = 100 }) {
  const t = useTime();
  const passed  = t >= starts + 0.6;
  const running = t >= starts && !passed;
  const bg   = passed ? C.successBg : running ? C.warningBg : 'rgba(255,255,255,0.06)';
  const bdr  = passed ? C.success   : running ? C.warning   : 'rgba(255,255,255,0.11)';
  const txtC = passed ? C.success   : running ? C.warning   : 'rgba(200,200,220,0.38)';
  const appear = clamp((t - baseTime) / 0.3, 0, 1);
  const pop    = passed ? 1 + 0.07 * Math.max(0, 1 - (t - (starts + 0.6)) / 0.3) : 1;
  return (
    <div style={{ display: 'flex', alignItems: 'center', gap: 8, padding: '8px 14px', borderRadius: 8, background: bg, border: `1.5px solid ${bdr}`, opacity: appear, transform: `scale(${pop})`, transition: 'none', minWidth: minW }}>
      {passed  ? <span style={{ fontSize: 13, color: C.success, fontWeight: 800 }}>✓</span>
       : running ? <span style={{ width: 9, height: 9, borderRadius: '50%', background: C.warning }} />
       :           <span style={{ width: 9, height: 9, borderRadius: '50%', border: '2px solid rgba(200,200,220,0.25)' }} />}
      <span style={{ fontFamily: F.mono, fontSize: 17, fontWeight: 600, color: txtC }}>{name}</span>
    </div>
  );
}

// animated pipeline row (Scene 5a)
function PipelineRow({ label, color, stageStarts, rowAppear }) {
  const t = useTime();
  const o  = clamp((t - rowAppear) / 0.4, 0, 1);
  const ty = (1 - Easing.easeOutCubic(o)) * 16;
  return (
    <div style={{ display: 'flex', alignItems: 'center', opacity: o, transform: `translateY(${ty}px)` }}>
      <div style={{ display: 'flex', alignItems: 'center', gap: 10, width: 236, flex: 'none' }}>
        <span style={{ width: 10, height: 10, borderRadius: 3, background: color, flex: 'none' }} />
        <span style={{ fontFamily: F.mono, fontSize: 20, color: C.onDark, fontWeight: 500 }}>{label}</span>
      </div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        {['build','test','deploy'].map((name, i) => (
          <React.Fragment key={name}>
            {i > 0 && (
              <div style={{ width: 32, height: 3, background: 'rgba(255,255,255,0.09)', position: 'relative', borderRadius: 2 }}>
                <div style={{ position: 'absolute', inset: 0, width: `${clamp((t - stageStarts[i-1] - 0.6) / 0.45, 0, 1) * 100}%`, background: C.grad, borderRadius: 2 }} />
              </div>
            )}
            <StageChip name={name} starts={stageStarts[i]} baseTime={rowAppear + i * 0.1} />
          </React.Fragment>
        ))}
      </div>
    </div>
  );
}

// ── Scene 5a — Parallel pipelines ────────────────────────────────────────────
function PipelinesScene() {
  return (
    <Sprite start={22} end={27}>
      <SceneFrame bg={C.ink} zoomFrom={1.0} zoomTo={1.06}>
        <div style={{ position: 'absolute', width: 1100, height: 800, borderRadius: '50%', background: 'radial-gradient(circle,rgba(104,74,235,0.12) 0%,rgba(104,74,235,0) 58%)' }} />
        <div style={{ position: 'relative', width: 1240, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
          <div style={{ alignSelf: 'flex-start', marginBottom: 44 }}>
            <Eyebrow dark>Step 04 — Pipelines</Eyebrow>
            <div style={{ fontFamily: F.display, fontWeight: 700, fontSize: 54, letterSpacing: '-0.03em', color: C.onDark, lineHeight: 1.05 }}>Each component. Its own pipeline.</div>
          </div>
          <div style={{ display: 'flex', flexDirection: 'column', gap: 28, alignSelf: 'flex-start' }}>
            {PIPELINES.map((p) => <PipelineRow key={p.label} {...p} />)}
          </div>
        </div>
      </SceneFrame>
    </Sprite>
  );
}

// ── Scene 5b — Zoom out, merge, result ───────────────────────────────────────
// static row (all-passed) for left panel
function StaticRow({ label, color, appear }) {
  const t = useTime();
  const o  = clamp((t - appear) / 0.4, 0, 1);
  const ty = (1 - Easing.easeOutCubic(o)) * 12;
  return (
    <div style={{ display: 'flex', alignItems: 'center', opacity: o, transform: `translateY(${ty}px)` }}>
      <div style={{ display: 'flex', alignItems: 'center', gap: 8, width: 162, flex: 'none' }}>
        <span style={{ width: 9, height: 9, borderRadius: 3, background: color, flex: 'none' }} />
        <span style={{ fontFamily: F.mono, fontSize: 16, color: C.onDark, fontWeight: 500 }}>{label}</span>
      </div>
      <div style={{ display: 'flex', alignItems: 'center' }}>
        {['build','test','deploy'].map((name, i) => (
          <React.Fragment key={name}>
            {i > 0 && <div style={{ width: 18, height: 3, background: C.grad, borderRadius: 2 }} />}
            <div style={{ display: 'flex', alignItems: 'center', gap: 5, padding: '5px 10px', borderRadius: 6, background: C.successBg, border: `1.5px solid ${C.success}`, minWidth: 76 }}>
              <span style={{ fontSize: 11, color: C.success, fontWeight: 800 }}>✓</span>
              <span style={{ fontFamily: F.mono, fontSize: 14, fontWeight: 600, color: C.success }}>{name}</span>
            </div>
          </React.Fragment>
        ))}
      </div>
    </div>
  );
}

function MergeResultScene() {
  const t = useTime();
  const linesP  = Easing.easeOutCubic(clamp((t - MERGE_ANIM_START) / 0.7, 0, 1));
  const rightP  = Easing.easeOutCubic(clamp((t - MERGE_ANIM_START - 0.15) / 0.5, 0, 1));
  const bannerO = clamp((t - BANNER_TIME) / 0.5, 0, 1);
  const bannerS = 0.92 + 0.08 * Easing.easeOutBack(bannerO);
  return (
    <Sprite start={MERGE_SCENE_START} end={33}>
      <SceneFrame bg={C.ink} zoomFrom={1.08} zoomTo={1.0}>
        <div style={{ position: 'absolute', width: 1200, height: 900, borderRadius: '50%', background: 'radial-gradient(circle,rgba(104,74,235,0.09) 0%,rgba(104,74,235,0) 56%)' }} />
        <div style={{ position: 'relative', width: 1560, display: 'flex', alignItems: 'center' }}>

          {/* LEFT — 4 completed pipelines */}
          <div style={{ flex: '0 0 560px', display: 'flex', flexDirection: 'column', gap: 26 }}>
            <div style={{ fontFamily: F.sans, fontWeight: 600, fontSize: 12, textTransform: 'uppercase', letterSpacing: '0.12em', color: C.onDarkMut, marginBottom: 4 }}>Component pipelines</div>
            {PIPELINES.map((p, i) => (
              <StaticRow key={p.label} label={p.label} color={p.color} appear={MERGE_SCENE_START + 0.1 + i * 0.13} />
            ))}
          </div>

          {/* CENTER — convergence SVG */}
          <div style={{ flex: '0 0 210px', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
            <svg width={210} height={260} style={{ overflow: 'visible' }}>
              <defs>
                <linearGradient id="mg2" x1="0" y1="0" x2="1" y2="0">
                  <stop offset="0%" stopColor="#684aeb" />
                  <stop offset="100%" stopColor="#a45eed" />
                </linearGradient>
              </defs>
              {[55, 88, 172, 205].map((y, i) => (
                <line key={i} x1={0} y1={y} x2={86 * linesP} y2={130}
                  stroke={PIPELINES[i].color} strokeWidth={2.5} strokeLinecap="round" opacity={0.82} />
              ))}
              <circle cx={86} cy={130}
                r={9 * Easing.easeOutBack(clamp((t - MERGE_ANIM_START - 0.05) / 0.3, 0, 1))}
                fill="#684aeb" />
              <line x1={96} y1={130}
                x2={96 + 90 * Easing.easeOutCubic(clamp((t - MERGE_ANIM_START - 0.22) / 0.45, 0, 1))} y2={130}
                stroke="url(#mg2)" strokeWidth={3.5} strokeLinecap="round" />
              {linesP > 0.8 && (
                <>
                  <line x1={180} y1={123} x2={192} y2={130} stroke="#a45eed" strokeWidth={3} strokeLinecap="round" />
                  <line x1={180} y1={137} x2={192} y2={130} stroke="#a45eed" strokeWidth={3} strokeLinecap="round" />
                </>
              )}
            </svg>
          </div>

          {/* RIGHT — merged pipeline + success banner */}
          <div style={{ flex: '0 0 580px', display: 'flex', flexDirection: 'column', gap: 28, opacity: rightP, transform: `translateX(${(1 - rightP) * 20}px)` }}>
            <div>
              <div style={{ fontFamily: F.sans, fontWeight: 600, fontSize: 12, textTransform: 'uppercase', letterSpacing: '0.12em', color: C.onDarkMut, marginBottom: 14 }}>Merged result</div>
              <div style={{ display: 'flex', alignItems: 'center', gap: 10, marginBottom: 18 }}>
                <span style={{ display: 'inline-flex', alignItems: 'center', gap: 6, padding: '5px 12px', borderRadius: 6, background: 'rgba(104,74,235,0.22)', border: '1px solid rgba(164,94,237,0.45)', fontFamily: F.sans, fontSize: 13, color: '#a45eed', fontWeight: 700 }}>merged</span>
                <span style={{ fontFamily: F.mono, fontSize: 13, color: C.onDarkMut }}>my-app.gitlab-ci.yml</span>
              </div>
              <div style={{ display: 'flex', alignItems: 'center' }}>
                {['provision cluster','e2e tests','production'].map((name, i) => (
                  <React.Fragment key={name}>
                    {i > 0 && (
                      <div style={{ width: 28, height: 3, background: 'rgba(255,255,255,0.09)', position: 'relative', borderRadius: 2 }}>
                        <div style={{ position: 'absolute', inset: 0, width: `${clamp((t - MERGED_STAGE_S[i-1] - 0.6) / 0.4, 0, 1) * 100}%`, background: C.grad, borderRadius: 2 }} />
                      </div>
                    )}
                    <StageChip name={name} starts={MERGED_STAGE_S[i]} baseTime={MERGE_ANIM_START + 0.65 + i * 0.1} minW={162} />
                  </React.Fragment>
                ))}
              </div>
            </div>
            <div style={{ opacity: bannerO, transform: `scale(${bannerS})`, display: 'flex', flexDirection: 'column', gap: 10, padding: '20px 24px', background: C.inkPanel, border: '1px solid rgba(21,163,106,0.35)', borderRadius: 14, boxShadow: '0 20px 50px -20px rgba(21,163,106,0.55)' }}>
              <div style={{ display: 'flex', alignItems: 'center', gap: 12 }}>
                <span style={{ display: 'inline-flex', alignItems: 'center', gap: 8, padding: '6px 14px', borderRadius: 999, background: C.successBg, color: C.success, fontFamily: F.sans, fontWeight: 700, fontSize: 18 }}>
                  <span style={{ width: 8, height: 8, borderRadius: '50%', background: C.success }} /> passed
                </span>
                <span style={{ fontFamily: F.display, fontWeight: 700, fontSize: 27, color: C.onDark, letterSpacing: '-0.02em' }}>DevOps Successful</span>
              </div>
              <span style={{ fontFamily: F.mono, fontSize: 16, color: C.onDarkMut }}>4 pipelines · merged · 52s</span>
            </div>
          </div>

        </div>
      </SceneFrame>
    </Sprite>
  );
}

// ── Scene 6 — Outro ──────────────────────────────────────────────────────────
function OutroScene() {
  return (
    <Sprite start={33} end={37}>
      <SceneFrame bg={C.ink} zoomFrom={1.04} zoomTo={1.0}>
        <div style={{ position: 'absolute', width: 1000, height: 1000, borderRadius: '50%', background: 'radial-gradient(circle, rgba(104,74,235,0.26) 0%, rgba(104,74,235,0) 60%)' }} />
        <div style={{ position: 'relative', display: 'flex', flexDirection: 'column', alignItems: 'center', textAlign: 'center' }}>
          <Sprite start={33.2} end={37}><OutroLogo /></Sprite>
          <FadeUp start={33.5} rise={18} style={{ fontFamily: F.display, fontWeight: 700, fontSize: 64, letterSpacing: '-0.03em', color: C.onDark, lineHeight: 1.05 }}>One Architecture model. Any pipeline.</FadeUp>
          <Sprite start={34.0} end={37}>
            <div style={{ marginTop: 30, display: 'inline-flex', alignItems: 'center', gap: 14, padding: '16px 26px', borderRadius: 12, background: C.inkPanel, border: `1px solid ${C.inkBorder}` }}>
              <span style={{ fontFamily: F.mono, fontSize: 26, color: C.accent }}>$</span>
              <span style={{ fontFamily: F.mono, fontSize: 20, color: C.onDark }}>{'mddoai input/<any>.swarch <any platform> output/*pipeline*'}</span>
            </div>
            <div style={{ marginTop: 26, fontFamily: F.sans, fontSize: 22, color: C.onDarkMut, letterSpacing: '0.02em' }}>github.com/modeldrivendevopsai</div>
          </Sprite>
        </div>
      </SceneFrame>
    </Sprite>
  );
}
function OutroLogo() {
  const { localTime } = useSprite();
  const o = clamp(localTime / 0.5, 0, 1);
  return <img src={LOGO} alt="" style={{ width: 120, height: 120, marginBottom: 30, opacity: o, filter: 'drop-shadow(0 10px 34px rgba(104,74,235,0.5))' }} />;
}

// ── timestamp labeler (for review comments) ──────────────────────────────────
function TimestampLabeler() {
  const t = useTime();
  React.useEffect(() => {
    const root = document.getElementById('video-root');
    if (root) root.setAttribute('data-screen-label', `t=${t.toFixed(1)}s`);
  }, [Math.floor(t)]);
  return null;
}

Object.assign(window, {
  TitleScene, InputScene, CommandScene, OutputScene, PipelinesScene, MergeResultScene, OutroScene, TimestampLabeler,
});
