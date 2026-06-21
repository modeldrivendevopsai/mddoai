/* @ds-bundle: {"format":3,"namespace":"MDDOAIDesignSystem_0a843c","components":[{"name":"Button","sourcePath":"components/actions/Button.jsx"},{"name":"Icon","sourcePath":"components/actions/Icon.jsx"},{"name":"Badge","sourcePath":"components/feedback/Badge.jsx"},{"name":"Callout","sourcePath":"components/feedback/Callout.jsx"},{"name":"StatusPill","sourcePath":"components/feedback/StatusPill.jsx"},{"name":"Input","sourcePath":"components/forms/Input.jsx"},{"name":"Card","sourcePath":"components/surfaces/Card.jsx"},{"name":"CodeBlock","sourcePath":"components/surfaces/CodeBlock.jsx"},{"name":"Tabs","sourcePath":"components/surfaces/Tabs.jsx"}],"sourceHashes":{"components/actions/Button.jsx":"e51f7a6d3caa","components/actions/Icon.jsx":"30dc9139a326","components/feedback/Badge.jsx":"8a249119411f","components/feedback/Callout.jsx":"20ded2895c11","components/feedback/StatusPill.jsx":"4e944e08e24e","components/forms/Input.jsx":"0eb78fa510f1","components/surfaces/Card.jsx":"8e6048f1fd02","components/surfaces/CodeBlock.jsx":"64e77d340238","components/surfaces/Tabs.jsx":"83783fe20a7d","ui_kits/docs/DocsBody.jsx":"02fca01e17a9","ui_kits/docs/DocsOnThisPage.jsx":"dae2b052a4ff","ui_kits/docs/DocsSidebar.jsx":"eae03734f25d","ui_kits/docs/DocsTopbar.jsx":"121d17845fd5","ui_kits/marketing/Footer.jsx":"a11e21494ca1","ui_kits/marketing/Header.jsx":"437699ee4c17","ui_kits/marketing/Hero.jsx":"1b43eba77059","ui_kits/marketing/Quickstart.jsx":"4edcdf4c4e66","ui_kits/marketing/Sections.jsx":"9dc18b04c770","video/animations.jsx":"ebe6809a6cbe","video/scenes.jsx":"1d06a608c4c7","video/tweaks-panel.jsx":"6591467622ed"},"inlinedExternals":[],"unexposedExports":[]} */

(() => {

const __ds_ns = (window.MDDOAIDesignSystem_0a843c = window.MDDOAIDesignSystem_0a843c || {});

const __ds_scope = {};

(__ds_ns.__errors = __ds_ns.__errors || []);

// components/actions/Button.jsx
try { (() => {
function _extends() { return _extends = Object.assign ? Object.assign.bind() : function (n) { for (var e = 1; e < arguments.length; e++) { var t = arguments[e]; for (var r in t) ({}).hasOwnProperty.call(t, r) && (n[r] = t[r]); } return n; }, _extends.apply(null, arguments); }
/**
 * MDDOAI Button — the primary action primitive.
 * Variants map to brand intent; sizes follow the 4px grid.
 */
function Button({
  children,
  variant = 'primary',
  size = 'md',
  disabled = false,
  iconLeft = null,
  iconRight = null,
  full = false,
  type = 'button',
  onClick,
  ...rest
}) {
  const [hover, setHover] = React.useState(false);
  const [active, setActive] = React.useState(false);
  const sizes = {
    sm: {
      padding: '0 12px',
      height: 32,
      fontSize: 13,
      gap: 6,
      radius: 'var(--radius-sm)'
    },
    md: {
      padding: '0 16px',
      height: 40,
      fontSize: 14,
      gap: 8,
      radius: 'var(--radius-md)'
    },
    lg: {
      padding: '0 22px',
      height: 48,
      fontSize: 16,
      gap: 8,
      radius: 'var(--radius-md)'
    }
  };
  const s = sizes[size] || sizes.md;
  const base = {
    display: 'inline-flex',
    alignItems: 'center',
    justifyContent: 'center',
    gap: s.gap,
    height: s.height,
    padding: s.padding,
    width: full ? '100%' : 'auto',
    fontFamily: 'var(--font-sans)',
    fontSize: s.fontSize,
    fontWeight: 600,
    lineHeight: 1,
    letterSpacing: '-0.005em',
    borderRadius: s.radius,
    border: '1px solid transparent',
    cursor: disabled ? 'not-allowed' : 'pointer',
    opacity: disabled ? 0.5 : 1,
    transition: 'background var(--duration-fast) var(--ease-out), box-shadow var(--duration-fast) var(--ease-out), transform var(--duration-fast) var(--ease-out), color var(--duration-fast) var(--ease-out)',
    transform: active && !disabled ? 'translateY(1px)' : 'translateY(0)',
    whiteSpace: 'nowrap',
    userSelect: 'none'
  };
  const variants = {
    primary: {
      background: hover ? 'var(--brand-strong)' : 'var(--brand)',
      color: 'var(--on-brand)',
      boxShadow: active ? 'none' : hover ? 'var(--glow-brand)' : 'var(--shadow-xs)'
    },
    secondary: {
      background: hover ? 'var(--surface-sunken)' : 'var(--surface-card)',
      color: 'var(--text-strong)',
      borderColor: 'var(--border-default)',
      boxShadow: active ? 'none' : 'var(--shadow-xs)'
    },
    ghost: {
      background: hover ? 'var(--surface-sunken)' : 'transparent',
      color: 'var(--text-body)'
    },
    gradient: {
      background: 'var(--gradient-brand)',
      color: 'var(--on-brand)',
      boxShadow: active ? 'none' : hover ? 'var(--glow-brand)' : 'var(--shadow-sm)'
    },
    danger: {
      background: hover ? '#c93b34' : 'var(--danger-500)',
      color: '#fff',
      boxShadow: active ? 'none' : 'var(--shadow-xs)'
    }
  };
  return /*#__PURE__*/React.createElement("button", _extends({
    type: type,
    disabled: disabled,
    onClick: onClick,
    onMouseEnter: () => setHover(true),
    onMouseLeave: () => {
      setHover(false);
      setActive(false);
    },
    onMouseDown: () => setActive(true),
    onMouseUp: () => setActive(false),
    style: {
      ...base,
      ...(variants[variant] || variants.primary)
    }
  }, rest), iconLeft, children, iconRight);
}
Object.assign(__ds_scope, { Button });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/actions/Button.jsx", error: String((e && e.message) || e) }); }

// components/actions/Icon.jsx
try { (() => {
function _extends() { return _extends = Object.assign ? Object.assign.bind() : function (n) { for (var e = 1; e < arguments.length; e++) { var t = arguments[e]; for (var r in t) ({}).hasOwnProperty.call(t, r) && (n[r] = t[r]); } return n; }, _extends.apply(null, arguments); }
/**
 * MDDOAI Icon — thin wrapper over Lucide (https://lucide.dev), the brand icon set.
 * Renders a Lucide line icon by name. Requires the Lucide UMD script to be present
 * on the page (cards & UI kits load it from CDN); falls back to an empty box if not.
 *
 * Stroke inherits `currentColor`; size is a single px value (square).
 */
function Icon({
  name,
  size = 20,
  strokeWidth = 1.9,
  color = 'currentColor',
  style = {},
  ...rest
}) {
  const ref = React.useRef(null);
  React.useEffect(() => {
    const el = ref.current;
    if (!el) return;
    // Reset to the data-lucide marker so createIcons re-renders cleanly on name change.
    el.innerHTML = '';
    const i = document.createElement('i');
    i.setAttribute('data-lucide', name);
    el.appendChild(i);
    const lucide = typeof window !== 'undefined' ? window.lucide : null;
    if (lucide && typeof lucide.createIcons === 'function') {
      lucide.createIcons({
        attrs: {
          width: size,
          height: size,
          'stroke-width': strokeWidth
        },
        nameAttr: 'data-lucide'
      });
    }
  }, [name, size, strokeWidth]);
  return /*#__PURE__*/React.createElement("span", _extends({
    ref: ref,
    "aria-hidden": "true",
    style: {
      display: 'inline-flex',
      width: size,
      height: size,
      color,
      flex: 'none',
      ...style
    }
  }, rest));
}
Object.assign(__ds_scope, { Icon });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/actions/Icon.jsx", error: String((e && e.message) || e) }); }

// components/feedback/Badge.jsx
try { (() => {
/**
 * Badge — a small label for counts, versions, and categories.
 * Quieter than StatusPill; not tied to pipeline state.
 */
function Badge({
  children,
  variant = 'neutral',
  size = 'md'
}) {
  const map = {
    neutral: {
      bg: 'var(--neutral-100)',
      fg: 'var(--neutral-600)',
      bd: 'var(--border-subtle)'
    },
    brand: {
      bg: 'var(--brand-faint)',
      fg: 'var(--text-brand)',
      bd: 'var(--border-brand)'
    },
    solid: {
      bg: 'var(--brand)',
      fg: 'var(--on-brand)',
      bd: 'transparent'
    },
    outline: {
      bg: 'transparent',
      fg: 'var(--text-body)',
      bd: 'var(--border-default)'
    },
    success: {
      bg: 'var(--success-100)',
      fg: 'var(--success-500)',
      bd: 'transparent'
    }
  };
  const s = map[variant] || map.neutral;
  const dims = size === 'sm' ? {
    fontSize: 10,
    padding: '1px 7px'
  } : {
    fontSize: 11,
    padding: '2px 9px'
  };
  return /*#__PURE__*/React.createElement("span", {
    style: {
      display: 'inline-flex',
      alignItems: 'center',
      fontFamily: 'var(--font-mono)',
      fontSize: dims.fontSize,
      fontWeight: 600,
      letterSpacing: '0.02em',
      padding: dims.padding,
      borderRadius: 'var(--radius-pill)',
      background: s.bg,
      color: s.fg,
      border: `1px solid ${s.bd}`,
      lineHeight: 1.4,
      whiteSpace: 'nowrap'
    }
  }, children);
}
Object.assign(__ds_scope, { Badge });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/feedback/Badge.jsx", error: String((e && e.message) || e) }); }

// components/feedback/Callout.jsx
try { (() => {
/**
 * Callout — an inline note box for docs & product copy.
 * Tone sets the accent colour; keep the left rule thin (no heavy colour-block cards).
 */
function Callout({
  tone = 'info',
  title,
  children,
  icon = null
}) {
  const map = {
    info: {
      fg: 'var(--info-500)',
      bg: '#f3f7fd',
      bd: '#cfe0f6'
    },
    tip: {
      fg: 'var(--brand)',
      bg: 'var(--brand-faint)',
      bd: 'var(--border-brand)'
    },
    warning: {
      fg: '#9a6800',
      bg: 'var(--warning-100)',
      bd: '#ecd9a3'
    },
    danger: {
      fg: 'var(--danger-500)',
      bg: 'var(--danger-100)',
      bd: '#f3c4c0'
    }
  };
  const s = map[tone] || map.info;
  return /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      gap: 12,
      padding: '14px 16px',
      background: s.bg,
      border: `1px solid ${s.bd}`,
      borderRadius: 'var(--radius-md)',
      fontFamily: 'var(--font-sans)'
    }
  }, icon && /*#__PURE__*/React.createElement("div", {
    style: {
      color: s.fg,
      flex: 'none',
      marginTop: 1
    }
  }, icon), /*#__PURE__*/React.createElement("div", {
    style: {
      minWidth: 0
    }
  }, title && /*#__PURE__*/React.createElement("div", {
    style: {
      fontWeight: 600,
      fontSize: 14,
      color: 'var(--text-strong)',
      marginBottom: children ? 4 : 0
    }
  }, title), children && /*#__PURE__*/React.createElement("div", {
    style: {
      fontSize: 13.5,
      lineHeight: 1.6,
      color: 'var(--text-body)'
    }
  }, children)));
}
Object.assign(__ds_scope, { Callout });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/feedback/Callout.jsx", error: String((e && e.message) || e) }); }

// components/feedback/StatusPill.jsx
try { (() => {
/**
 * StatusPill — CI/CD pipeline state chip. The brand maps semantic colours to
 * pipeline states: passed / running / failed / pending / info / skipped.
 */
function StatusPill({
  status = 'passed',
  children,
  size = 'md'
}) {
  const map = {
    passed: {
      bg: 'var(--success-100)',
      fg: 'var(--success-500)',
      dot: 'var(--success-500)',
      label: 'passed'
    },
    running: {
      bg: 'var(--warning-100)',
      fg: '#9a6800',
      dot: 'var(--warning-500)',
      label: 'running',
      pulse: true
    },
    pending: {
      bg: 'var(--neutral-100)',
      fg: 'var(--neutral-600)',
      dot: 'var(--neutral-400)',
      label: 'pending'
    },
    failed: {
      bg: 'var(--danger-100)',
      fg: 'var(--danger-500)',
      dot: 'var(--danger-500)',
      label: 'failed'
    },
    info: {
      bg: 'var(--info-100)',
      fg: 'var(--info-500)',
      dot: 'var(--info-500)',
      label: 'info'
    },
    skipped: {
      bg: 'var(--neutral-100)',
      fg: 'var(--neutral-500)',
      dot: 'var(--neutral-300)',
      label: 'skipped'
    }
  };
  const s = map[status] || map.passed;
  const dims = size === 'sm' ? {
    fontSize: 11,
    padding: '2px 8px',
    dot: 6
  } : {
    fontSize: 12,
    padding: '3px 11px',
    dot: 7
  };
  return /*#__PURE__*/React.createElement("span", {
    style: {
      display: 'inline-flex',
      alignItems: 'center',
      gap: 6,
      fontFamily: 'var(--font-mono)',
      fontSize: dims.fontSize,
      fontWeight: 600,
      letterSpacing: '0.01em',
      padding: dims.padding,
      borderRadius: 'var(--radius-pill)',
      background: s.bg,
      color: s.fg,
      lineHeight: 1.3
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      width: dims.dot,
      height: dims.dot,
      borderRadius: '50%',
      background: s.dot,
      flex: 'none',
      boxShadow: s.pulse ? `0 0 0 0 ${s.dot}` : 'none',
      animation: s.pulse ? 'mddoai-pulse 1.6s var(--ease-out) infinite' : 'none'
    }
  }), children || s.label, /*#__PURE__*/React.createElement("style", null, `@keyframes mddoai-pulse {
        0% { box-shadow: 0 0 0 0 rgba(207,138,0,0.45); }
        70% { box-shadow: 0 0 0 5px rgba(207,138,0,0); }
        100% { box-shadow: 0 0 0 0 rgba(207,138,0,0); }
      }`));
}
Object.assign(__ds_scope, { StatusPill });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/feedback/StatusPill.jsx", error: String((e && e.message) || e) }); }

// components/forms/Input.jsx
try { (() => {
function _extends() { return _extends = Object.assign ? Object.assign.bind() : function (n) { for (var e = 1; e < arguments.length; e++) { var t = arguments[e]; for (var r in t) ({}).hasOwnProperty.call(t, r) && (n[r] = t[r]); } return n; }, _extends.apply(null, arguments); }
/**
 * Input — single-line text field. Mono variant for command/path entry.
 */
function Input({
  value,
  onChange,
  placeholder = '',
  label = null,
  hint = null,
  error = null,
  disabled = false,
  mono = false,
  iconLeft = null,
  type = 'text',
  full = true,
  ...rest
}) {
  const [focus, setFocus] = React.useState(false);
  const borderColor = error ? 'var(--danger-500)' : focus ? 'var(--brand)' : 'var(--border-default)';
  return /*#__PURE__*/React.createElement("label", {
    style: {
      display: full ? 'block' : 'inline-block',
      width: full ? '100%' : 'auto',
      fontFamily: 'var(--font-sans)'
    }
  }, label && /*#__PURE__*/React.createElement("span", {
    style: {
      display: 'block',
      fontSize: 13,
      fontWeight: 600,
      color: 'var(--text-strong)',
      marginBottom: 6
    }
  }, label), /*#__PURE__*/React.createElement("span", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 8,
      height: 40,
      padding: '0 12px',
      background: disabled ? 'var(--surface-sunken)' : 'var(--surface-card)',
      border: `1px solid ${borderColor}`,
      borderRadius: 'var(--radius-md)',
      boxShadow: focus ? 'var(--ring-brand)' : 'none',
      transition: 'border-color var(--duration-fast) var(--ease-out), box-shadow var(--duration-fast) var(--ease-out)'
    }
  }, iconLeft && /*#__PURE__*/React.createElement("span", {
    style: {
      color: 'var(--text-faint)',
      display: 'inline-flex',
      flex: 'none'
    }
  }, iconLeft), /*#__PURE__*/React.createElement("input", _extends({
    type: type,
    value: value,
    onChange: onChange,
    placeholder: placeholder,
    disabled: disabled,
    onFocus: () => setFocus(true),
    onBlur: () => setFocus(false),
    style: {
      flex: 1,
      minWidth: 0,
      border: 'none',
      outline: 'none',
      background: 'transparent',
      fontFamily: mono ? 'var(--font-mono)' : 'var(--font-sans)',
      fontSize: mono ? 13 : 14,
      color: 'var(--text-strong)'
    }
  }, rest))), (hint || error) && /*#__PURE__*/React.createElement("span", {
    style: {
      display: 'block',
      fontSize: 12,
      marginTop: 6,
      color: error ? 'var(--danger-500)' : 'var(--text-muted)'
    }
  }, error || hint));
}
Object.assign(__ds_scope, { Input });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/forms/Input.jsx", error: String((e && e.message) || e) }); }

// components/surfaces/Card.jsx
try { (() => {
function _extends() { return _extends = Object.assign ? Object.assign.bind() : function (n) { for (var e = 1; e < arguments.length; e++) { var t = arguments[e]; for (var r in t) ({}).hasOwnProperty.call(t, r) && (n[r] = t[r]); } return n; }, _extends.apply(null, arguments); }
/**
 * Card — the standard surface container. White on a faint slate page,
 * 1px hairline border, soft cool shadow. No coloured left-border accents.
 */
function Card({
  children,
  padding = 20,
  hover = false,
  interactive = false,
  onClick,
  style = {},
  ...rest
}) {
  const [h, setH] = React.useState(false);
  const lift = (hover || interactive) && h;
  return /*#__PURE__*/React.createElement("div", _extends({
    onClick: onClick,
    onMouseEnter: () => setH(true),
    onMouseLeave: () => setH(false),
    style: {
      background: 'var(--surface-card)',
      border: '1px solid var(--border-subtle)',
      borderRadius: 'var(--radius-lg)',
      boxShadow: lift ? 'var(--shadow-md)' : 'var(--shadow-sm)',
      padding,
      cursor: interactive ? 'pointer' : 'default',
      transform: lift ? 'translateY(-2px)' : 'translateY(0)',
      transition: 'box-shadow var(--duration-base) var(--ease-out), transform var(--duration-base) var(--ease-out), border-color var(--duration-base) var(--ease-out)',
      borderColor: lift ? 'var(--border-default)' : 'var(--border-subtle)',
      ...style
    }
  }, rest), children);
}
Object.assign(__ds_scope, { Card });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/surfaces/Card.jsx", error: String((e && e.message) || e) }); }

// components/surfaces/CodeBlock.jsx
try { (() => {
/**
 * CodeBlock — the dark CLI/terminal surface. Commands are first-class content in
 * the MDDOAI brand, so code always reads as a terminal even on light pages.
 *
 * `lines` may be strings or { text, kind } where kind ∈ prompt|comment|output|flag.
 * Or pass a plain `code` string. Optional title bar with a copy affordance.
 */
function CodeBlock({
  code,
  lines,
  title = null,
  lang = 'bash',
  copyable = true
}) {
  const [copied, setCopied] = React.useState(false);
  const rows = lines || String(code || '').split('\n').map(text => ({
    text
  }));
  const plain = rows.map(r => typeof r === 'string' ? r : r.text).join('\n');
  const copy = () => {
    try {
      navigator.clipboard.writeText(plain);
    } catch (e) {}
    setCopied(true);
    setTimeout(() => setCopied(false), 1400);
  };
  const colorFor = kind => ({
    prompt: 'var(--violet-400)',
    comment: '#6f6c86',
    output: '#8f8caa',
    flag: '#7fd6a8',
    string: '#7fd6a8'
  })[kind] || '#d6d3e8';
  return /*#__PURE__*/React.createElement("div", {
    style: {
      background: 'var(--surface-code)',
      border: '1px solid rgba(255,255,255,0.07)',
      borderRadius: 'var(--radius-md)',
      overflow: 'hidden',
      fontFamily: 'var(--font-mono)'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'space-between',
      padding: '8px 12px 8px 14px',
      borderBottom: '1px solid rgba(255,255,255,0.07)'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 8
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      display: 'flex',
      gap: 6
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      width: 10,
      height: 10,
      borderRadius: '50%',
      background: '#46435a'
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      width: 10,
      height: 10,
      borderRadius: '50%',
      background: '#46435a'
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      width: 10,
      height: 10,
      borderRadius: '50%',
      background: '#46435a'
    }
  })), title && /*#__PURE__*/React.createElement("span", {
    style: {
      fontSize: 12,
      color: '#9b98b5',
      marginLeft: 4
    }
  }, title), !title && /*#__PURE__*/React.createElement("span", {
    style: {
      fontSize: 11,
      color: '#6f6c86',
      marginLeft: 4
    }
  }, lang)), copyable && /*#__PURE__*/React.createElement("button", {
    onClick: copy,
    style: {
      appearance: 'none',
      background: copied ? 'rgba(127,214,168,0.14)' : 'rgba(255,255,255,0.06)',
      border: '1px solid rgba(255,255,255,0.09)',
      color: copied ? '#7fd6a8' : '#b9b6d0',
      fontFamily: 'var(--font-mono)',
      fontSize: 11,
      padding: '3px 9px',
      borderRadius: 'var(--radius-sm)',
      cursor: 'pointer',
      transition: 'all var(--duration-fast) var(--ease-out)'
    }
  }, copied ? 'copied' : 'copy')), /*#__PURE__*/React.createElement("pre", {
    style: {
      margin: 0,
      padding: '14px 16px',
      overflowX: 'auto',
      fontSize: 13,
      lineHeight: 1.75
    }
  }, rows.map((r, i) => {
    const row = typeof r === 'string' ? {
      text: r
    } : r;
    return /*#__PURE__*/React.createElement("div", {
      key: i,
      style: {
        color: colorFor(row.kind),
        whiteSpace: 'pre'
      }
    }, row.kind === 'prompt' || row.prompt ? /*#__PURE__*/React.createElement("span", {
      style: {
        color: 'var(--violet-400)',
        userSelect: 'none'
      }
    }, "$ ") : null, row.text);
  })));
}
Object.assign(__ds_scope, { CodeBlock });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/surfaces/CodeBlock.jsx", error: String((e && e.message) || e) }); }

// components/surfaces/Tabs.jsx
try { (() => {
/**
 * Tabs — horizontal segmented navigation with an animated underline.
 */
function Tabs({
  tabs = [],
  value,
  defaultValue,
  onChange
}) {
  const [internal, setInternal] = React.useState(defaultValue ?? (tabs[0] && tabs[0].id));
  const active = value !== undefined ? value : internal;
  const select = id => {
    if (value === undefined) setInternal(id);
    onChange && onChange(id);
  };
  return /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      gap: 4,
      borderBottom: '1px solid var(--border-subtle)',
      fontFamily: 'var(--font-sans)'
    }
  }, tabs.map(t => {
    const on = t.id === active;
    return /*#__PURE__*/React.createElement("button", {
      key: t.id,
      onClick: () => select(t.id),
      style: {
        position: 'relative',
        appearance: 'none',
        background: 'transparent',
        border: 'none',
        cursor: 'pointer',
        padding: '10px 14px',
        marginBottom: -1,
        fontFamily: 'inherit',
        fontSize: 14,
        fontWeight: on ? 600 : 500,
        color: on ? 'var(--text-strong)' : 'var(--text-muted)',
        borderBottom: `2px solid ${on ? 'var(--brand)' : 'transparent'}`,
        transition: 'color var(--duration-fast) var(--ease-out), border-color var(--duration-fast) var(--ease-out)',
        display: 'inline-flex',
        alignItems: 'center',
        gap: 7
      }
    }, t.icon, t.label, t.count !== undefined && /*#__PURE__*/React.createElement("span", {
      style: {
        fontFamily: 'var(--font-mono)',
        fontSize: 11,
        color: 'var(--text-faint)'
      }
    }, t.count));
  }));
}
Object.assign(__ds_scope, { Tabs });
})(); } catch (e) { __ds_ns.__errors.push({ path: "components/surfaces/Tabs.jsx", error: String((e && e.message) || e) }); }

// ui_kits/docs/DocsBody.jsx
try { (() => {
// MDDOAI docs — article body. Renders the active page's content.
const {
  Badge,
  Icon,
  Callout,
  CodeBlock,
  StatusPill
} = window.MDDOAIDesignSystem_0a843c;

// shared prose atoms ---------------------------------------------------------
const P = props => /*#__PURE__*/React.createElement("p", {
  style: {
    fontFamily: 'var(--font-sans)',
    fontSize: 16,
    lineHeight: 1.7,
    color: 'var(--text-body)',
    margin: '0 0 18px',
    maxWidth: 720
  }
}, props.children);
const H2 = ({
  id,
  children
}) => /*#__PURE__*/React.createElement("h2", {
  id: id,
  style: {
    scrollMarginTop: 80,
    fontFamily: 'var(--font-display)',
    fontWeight: 700,
    fontSize: 26,
    letterSpacing: '-0.02em',
    color: 'var(--text-strong)',
    margin: '40px 0 14px'
  }
}, children);
const C = ({
  children
}) => /*#__PURE__*/React.createElement("code", {
  style: {
    fontFamily: 'var(--font-mono)',
    fontSize: '0.88em',
    background: 'var(--surface-sunken)',
    border: '1px solid var(--border-subtle)',
    borderRadius: 5,
    padding: '1px 5px',
    color: 'var(--text-strong)'
  }
}, children);
function DocsTable() {
  const rows = [['swarch2gitlab', 'Architecture model', 'Direct model → pipeline. The common path.'], ['pim2gitlab', 'Platform-independent model', 'For abstract, platform-agnostic inputs.'], ['psm2gitlab', 'Platform-specific model', 'When the model already targets a platform.']];
  return /*#__PURE__*/React.createElement("div", {
    style: {
      border: '1px solid var(--border-subtle)',
      borderRadius: 'var(--radius-md)',
      overflow: 'hidden',
      margin: '8px 0 24px',
      maxWidth: 720
    }
  }, /*#__PURE__*/React.createElement("table", {
    style: {
      borderCollapse: 'collapse',
      width: '100%',
      fontFamily: 'var(--font-sans)',
      fontSize: 14.5
    }
  }, /*#__PURE__*/React.createElement("thead", null, /*#__PURE__*/React.createElement("tr", {
    style: {
      background: 'var(--surface-sunken)'
    }
  }, ['Command', 'Input', 'When to use'].map(h => /*#__PURE__*/React.createElement("th", {
    key: h,
    style: {
      textAlign: 'left',
      padding: '10px 14px',
      fontWeight: 600,
      fontSize: 12.5,
      textTransform: 'uppercase',
      letterSpacing: '0.05em',
      color: 'var(--text-faint)',
      borderBottom: '1px solid var(--border-subtle)'
    }
  }, h)))), /*#__PURE__*/React.createElement("tbody", null, rows.map((r, i) => /*#__PURE__*/React.createElement("tr", {
    key: r[0],
    style: {
      borderBottom: i < rows.length - 1 ? '1px solid var(--border-subtle)' : 'none'
    }
  }, /*#__PURE__*/React.createElement("td", {
    style: {
      padding: '11px 14px',
      verticalAlign: 'top'
    }
  }, /*#__PURE__*/React.createElement(C, null, r[0])), /*#__PURE__*/React.createElement("td", {
    style: {
      padding: '11px 14px',
      verticalAlign: 'top',
      color: 'var(--text-body)'
    }
  }, r[1]), /*#__PURE__*/React.createElement("td", {
    style: {
      padding: '11px 14px',
      verticalAlign: 'top',
      color: 'var(--text-muted)'
    }
  }, r[2]))))));
}
function DocsBody({
  active
}) {
  const titleMap = {
    quickstart: 'Quickstart',
    introduction: 'Introduction',
    docker: 'Using Docker',
    types: 'Transformation types'
  };
  const title = titleMap[active] || 'Quickstart';
  return /*#__PURE__*/React.createElement("article", {
    style: {
      flex: 1,
      minWidth: 0,
      maxWidth: 820,
      margin: '0 auto',
      padding: '40px 48px 96px'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 8,
      fontFamily: 'var(--font-sans)',
      fontSize: 13.5,
      color: 'var(--text-faint)',
      marginBottom: 18
    }
  }, /*#__PURE__*/React.createElement("span", null, "Guide"), /*#__PURE__*/React.createElement(Icon, {
    name: "chevron-right",
    size: 14
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      color: 'var(--text-muted)'
    }
  }, "Get started"), /*#__PURE__*/React.createElement(Icon, {
    name: "chevron-right",
    size: 14
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      color: 'var(--text-body)'
    }
  }, title)), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 12,
      marginBottom: 10
    }
  }, /*#__PURE__*/React.createElement("h1", {
    style: {
      fontFamily: 'var(--font-display)',
      fontWeight: 700,
      fontSize: 40,
      letterSpacing: '-0.03em',
      color: 'var(--text-strong)',
      margin: 0
    }
  }, title), /*#__PURE__*/React.createElement(Badge, {
    variant: "neutral"
  }, "5 min read")), /*#__PURE__*/React.createElement(P, null, "MDDOAI turns a software architecture model into a ready-to-run GitLab CI/CD pipeline. Place a ", /*#__PURE__*/React.createElement(C, null, ".swarch"), " file in ", /*#__PURE__*/React.createElement(C, null, "input/"), ", run one transformation, and collect the generated ", /*#__PURE__*/React.createElement(C, null, ".gitlab-ci.yml"), " from ", /*#__PURE__*/React.createElement(C, null, "output/"), ". No build required to try it."), /*#__PURE__*/React.createElement(Callout, {
    tone: "tip",
    title: "Fastest way to get started",
    icon: /*#__PURE__*/React.createElement(Icon, {
      name: "zap",
      size: 18
    })
  }, "Pull the image and run the transformation \u2014 Docker needs zero local setup."), /*#__PURE__*/React.createElement(H2, {
    id: "pull"
  }, "1 \u2014 Pull the image"), /*#__PURE__*/React.createElement(P, null, "The published container lives in the GitHub Container Registry. Pull the pinned release tag rather than ", /*#__PURE__*/React.createElement(C, null, "latest"), " so builds stay reproducible."), /*#__PURE__*/React.createElement(CodeBlock, {
    title: "bash",
    lines: [{
      text: '# pull the pinned release',
      kind: 'comment'
    }, {
      text: 'docker pull ghcr.io/modeldrivendevopsai/mddoai/mddoai:1.0.1',
      prompt: true
    }, {
      text: 'Status: Downloaded newer image for mddoai:1.0.1',
      kind: 'output'
    }]
  }), /*#__PURE__*/React.createElement(H2, {
    id: "prepare"
  }, "2 \u2014 Prepare input & output"), /*#__PURE__*/React.createElement(P, null, "Create the two working directories MDDOAI reads from and writes to, then drop your architecture model into ", /*#__PURE__*/React.createElement(C, null, "input/"), "."), /*#__PURE__*/React.createElement(CodeBlock, {
    title: "bash",
    lines: [{
      text: 'mkdir -p input output',
      prompt: true
    }, {
      text: 'cp ./my-app.swarch input/',
      prompt: true
    }]
  }), /*#__PURE__*/React.createElement(H2, {
    id: "run"
  }, "3 \u2014 Run the transformation"), /*#__PURE__*/React.createElement(P, null, "Mount the two directories and call a transformation command. ", /*#__PURE__*/React.createElement(C, null, "swarch2gitlab"), " is the common path \u2014 it maps an architecture model straight to a pipeline."), /*#__PURE__*/React.createElement(CodeBlock, {
    title: "bash",
    lines: [{
      text: 'docker run --rm \\\\',
      prompt: true
    }, {
      text: '  -v "$(pwd)/input:/app/input" \\\\'
    }, {
      text: '  -v "$(pwd)/output:/app/output" \\\\'
    }, {
      text: '  mddoai:1.0.1 swarch2gitlab \\\\',
      kind: 'string'
    }, {
      text: '  /app/input/my-app.swarch /app/output'
    }, {
      text: 'BUILD SUCCESSFUL',
      kind: 'flag'
    }]
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 10,
      margin: '0 0 24px'
    }
  }, /*#__PURE__*/React.createElement(StatusPill, {
    state: "passed"
  }, "transformation passed"), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 14,
      color: 'var(--text-muted)'
    }
  }, "Output written to ", /*#__PURE__*/React.createElement(C, null, "output/.gitlab-ci.yml"))), /*#__PURE__*/React.createElement(H2, {
    id: "types"
  }, "Transformation types"), /*#__PURE__*/React.createElement(P, null, "Three commands cover the model spectrum. Pick the one that matches your input model."), /*#__PURE__*/React.createElement(DocsTable, null), /*#__PURE__*/React.createElement(Callout, {
    tone: "warning",
    title: "Windows users",
    icon: /*#__PURE__*/React.createElement(Icon, {
      name: "terminal",
      size: 18
    })
  }, "In CMD use ", /*#__PURE__*/React.createElement(C, null, ".\\cli.bat"), " instead of ", /*#__PURE__*/React.createElement(C, null, "./cli.bat"), " when running from source."), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      gap: 14,
      marginTop: 44
    }
  }, /*#__PURE__*/React.createElement("a", {
    href: "#",
    onClick: e => e.preventDefault(),
    style: pagerStyle('left')
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      color: 'var(--text-faint)',
      fontSize: 12.5
    }
  }, "Previous"), /*#__PURE__*/React.createElement("span", {
    style: {
      color: 'var(--text-strong)',
      fontWeight: 600,
      fontFamily: 'var(--font-sans)'
    }
  }, "Introduction")), /*#__PURE__*/React.createElement("a", {
    href: "#",
    onClick: e => e.preventDefault(),
    style: pagerStyle('right')
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      color: 'var(--text-faint)',
      fontSize: 12.5
    }
  }, "Next"), /*#__PURE__*/React.createElement("span", {
    style: {
      color: 'var(--text-strong)',
      fontWeight: 600,
      fontFamily: 'var(--font-sans)'
    }
  }, "Using Docker"))));
}
function pagerStyle(align) {
  return {
    flex: 1,
    display: 'flex',
    flexDirection: 'column',
    gap: 4,
    padding: '14px 18px',
    border: '1px solid var(--border-subtle)',
    borderRadius: 'var(--radius-md)',
    textDecoration: 'none',
    background: 'var(--surface-card)',
    textAlign: align === 'right' ? 'right' : 'left'
  };
}
window.DocsBody = DocsBody;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/docs/DocsBody.jsx", error: String((e && e.message) || e) }); }

// ui_kits/docs/DocsOnThisPage.jsx
try { (() => {
// MDDOAI docs — right-hand "on this page" rail
function DocsOnThisPage() {
  const items = [{
    id: 'pull',
    label: '1 — Pull the image'
  }, {
    id: 'prepare',
    label: '2 — Prepare input & output'
  }, {
    id: 'run',
    label: '3 — Run the transformation'
  }, {
    id: 'types',
    label: 'Transformation types'
  }];
  const [active, setActive] = React.useState('pull');
  return /*#__PURE__*/React.createElement("aside", {
    style: {
      width: 232,
      flex: 'none',
      padding: '40px 24px',
      position: 'sticky',
      top: 60,
      alignSelf: 'flex-start'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 11.5,
      fontWeight: 600,
      textTransform: 'uppercase',
      letterSpacing: '0.07em',
      color: 'var(--text-faint)',
      marginBottom: 12
    }
  }, "On this page"), /*#__PURE__*/React.createElement("ul", {
    style: {
      listStyle: 'none',
      margin: 0,
      padding: 0,
      display: 'flex',
      flexDirection: 'column',
      gap: 2,
      borderLeft: '1px solid var(--border-subtle)'
    }
  }, items.map(it => {
    const on = it.id === active;
    return /*#__PURE__*/React.createElement("li", {
      key: it.id
    }, /*#__PURE__*/React.createElement("a", {
      href: '#' + it.id,
      onClick: () => setActive(it.id),
      style: {
        display: 'block',
        padding: '5px 0 5px 14px',
        marginLeft: -1,
        borderLeft: on ? '2px solid var(--brand)' : '2px solid transparent',
        fontFamily: 'var(--font-sans)',
        fontSize: 13.5,
        color: on ? 'var(--text-brand)' : 'var(--text-muted)',
        fontWeight: on ? 600 : 400,
        textDecoration: 'none'
      }
    }, it.label));
  })), /*#__PURE__*/React.createElement("div", {
    style: {
      marginTop: 26,
      paddingTop: 20,
      borderTop: '1px solid var(--border-subtle)',
      display: 'flex',
      flexDirection: 'column',
      gap: 10
    }
  }, /*#__PURE__*/React.createElement("a", {
    href: "#",
    onClick: e => e.preventDefault(),
    style: railLink
  }, "Edit this page"), /*#__PURE__*/React.createElement("a", {
    href: "#",
    onClick: e => e.preventDefault(),
    style: railLink
  }, "Open an issue")));
}
const railLink = {
  fontFamily: 'var(--font-sans)',
  fontSize: 13.5,
  color: 'var(--text-muted)',
  textDecoration: 'none'
};
window.DocsOnThisPage = DocsOnThisPage;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/docs/DocsOnThisPage.jsx", error: String((e && e.message) || e) }); }

// ui_kits/docs/DocsSidebar.jsx
try { (() => {
// MDDOAI docs — left sidebar navigation
const {
  Badge,
  Icon
} = window.MDDOAIDesignSystem_0a843c;
const DOCS_NAV = [{
  group: 'Get started',
  items: [{
    id: 'introduction',
    label: 'Introduction'
  }, {
    id: 'quickstart',
    label: 'Quickstart'
  }, {
    id: 'docker',
    label: 'Using Docker'
  }, {
    id: 'source',
    label: 'Build from source'
  }]
}, {
  group: 'Transformations',
  items: [{
    id: 'types',
    label: 'Transformation types'
  }, {
    id: 'swarch',
    label: 'swarch2gitlab'
  }, {
    id: 'pim',
    label: 'pim2gitlab'
  }, {
    id: 'psm',
    label: 'psm2gitlab'
  }]
}, {
  group: 'Operate',
  items: [{
    id: 'versioning',
    label: 'Image versioning'
  }, {
    id: 'ci',
    label: 'GitLab integration'
  }, {
    id: 'agents',
    label: 'AI agents'
  }]
}];
function DocsSidebar({
  active,
  onSelect
}) {
  return /*#__PURE__*/React.createElement("aside", {
    style: {
      width: 256,
      flex: 'none',
      borderRight: '1px solid var(--border-subtle)',
      background: 'var(--surface-card)',
      height: '100%',
      overflowY: 'auto'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      padding: '20px 16px'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 8,
      padding: '8px 10px',
      borderRadius: 'var(--radius-md)',
      border: '1px solid var(--border-subtle)',
      color: 'var(--text-muted)',
      marginBottom: 20,
      cursor: 'text'
    }
  }, /*#__PURE__*/React.createElement(Icon, {
    name: "search",
    size: 16
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 13.5
    }
  }, "Search docs"), /*#__PURE__*/React.createElement("span", {
    style: {
      marginLeft: 'auto',
      fontFamily: 'var(--font-mono)',
      fontSize: 11,
      color: 'var(--text-faint)',
      border: '1px solid var(--border-subtle)',
      borderRadius: 4,
      padding: '1px 5px'
    }
  }, "\u2318K")), DOCS_NAV.map(g => /*#__PURE__*/React.createElement("div", {
    key: g.group,
    style: {
      marginBottom: 22
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 11.5,
      fontWeight: 600,
      textTransform: 'uppercase',
      letterSpacing: '0.07em',
      color: 'var(--text-faint)',
      padding: '0 10px',
      marginBottom: 8
    }
  }, g.group), /*#__PURE__*/React.createElement("ul", {
    style: {
      listStyle: 'none',
      margin: 0,
      padding: 0,
      display: 'flex',
      flexDirection: 'column',
      gap: 1
    }
  }, g.items.map(it => {
    const on = it.id === active;
    return /*#__PURE__*/React.createElement("li", {
      key: it.id
    }, /*#__PURE__*/React.createElement("a", {
      href: "#",
      onClick: e => {
        e.preventDefault();
        onSelect(it.id);
      },
      style: {
        display: 'block',
        padding: '7px 10px',
        borderRadius: 'var(--radius-sm)',
        fontFamily: 'var(--font-sans)',
        fontSize: 14,
        fontWeight: on ? 600 : 400,
        color: on ? 'var(--text-brand)' : 'var(--text-body)',
        background: on ? 'var(--brand-faint)' : 'transparent',
        textDecoration: 'none',
        borderLeft: on ? '2px solid var(--brand)' : '2px solid transparent'
      },
      onMouseEnter: e => {
        if (!on) e.currentTarget.style.background = 'var(--surface-sunken)';
      },
      onMouseLeave: e => {
        if (!on) e.currentTarget.style.background = 'transparent';
      }
    }, it.label));
  }))))));
}
window.DocsSidebar = DocsSidebar;
window.DOCS_NAV = DOCS_NAV;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/docs/DocsSidebar.jsx", error: String((e && e.message) || e) }); }

// ui_kits/docs/DocsTopbar.jsx
try { (() => {
// MDDOAI docs — top bar
const {
  Badge,
  Icon,
  Button
} = window.MDDOAIDesignSystem_0a843c;
function DocsTopbar() {
  return /*#__PURE__*/React.createElement("header", {
    style: {
      height: 60,
      flex: 'none',
      display: 'flex',
      alignItems: 'center',
      gap: 16,
      padding: '0 22px',
      borderBottom: '1px solid var(--border-subtle)',
      background: 'rgba(255,255,255,0.85)',
      backdropFilter: 'blur(10px)',
      WebkitBackdropFilter: 'blur(10px)',
      position: 'relative',
      zIndex: 10
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 9
    }
  }, /*#__PURE__*/React.createElement("img", {
    src: "../../assets/logo/MDDOAI-transparent-2048.png",
    alt: "",
    style: {
      height: 26
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: 'var(--font-display)',
      fontWeight: 700,
      fontSize: 17,
      letterSpacing: '-0.02em',
      color: 'var(--text-strong)'
    }
  }, "MDDOAI"), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 15,
      color: 'var(--text-faint)',
      fontWeight: 500
    }
  }, "docs")), /*#__PURE__*/React.createElement(Badge, {
    variant: "brand"
  }, "v1.0.1"), /*#__PURE__*/React.createElement("nav", {
    style: {
      display: 'flex',
      gap: 2,
      marginLeft: 12
    }
  }, ['Guide', 'Reference', 'Changelog'].map((l, i) => /*#__PURE__*/React.createElement("a", {
    key: l,
    href: "#",
    onClick: e => e.preventDefault(),
    style: {
      padding: '7px 11px',
      borderRadius: 'var(--radius-sm)',
      fontFamily: 'var(--font-sans)',
      fontSize: 14,
      fontWeight: 500,
      color: i === 0 ? 'var(--text-strong)' : 'var(--text-muted)',
      textDecoration: 'none'
    }
  }, l))), /*#__PURE__*/React.createElement("div", {
    style: {
      marginLeft: 'auto',
      display: 'flex',
      alignItems: 'center',
      gap: 14
    }
  }, /*#__PURE__*/React.createElement("a", {
    href: "#",
    onClick: e => e.preventDefault(),
    style: {
      color: 'var(--text-muted)',
      display: 'inline-flex'
    }
  }, /*#__PURE__*/React.createElement(Icon, {
    name: "git-branch",
    size: 18
  })), /*#__PURE__*/React.createElement("a", {
    href: "#",
    onClick: e => e.preventDefault(),
    style: {
      color: 'var(--text-muted)',
      display: 'inline-flex'
    }
  }, /*#__PURE__*/React.createElement(Icon, {
    name: "container",
    size: 18
  })), /*#__PURE__*/React.createElement(Button, {
    variant: "primary",
    size: "sm"
  }, "Get started")));
}
window.DocsTopbar = DocsTopbar;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/docs/DocsTopbar.jsx", error: String((e && e.message) || e) }); }

// ui_kits/marketing/Footer.jsx
try { (() => {
// MDDOAI marketing — footer
const {
  Icon
} = window.MDDOAIDesignSystem_0a843c;
function Footer() {
  const cols = [{
    h: 'Product',
    links: ['Transformations', 'Docker images', 'Releases', 'Coverage reports']
  }, {
    h: 'Docs',
    links: ['Getting started', 'Build from source', 'GitLab integration', 'AI agents']
  }, {
    h: 'Project',
    links: ['GitHub', 'License (EPL-2.0)', 'Container registry', 'Draw.io diagrams']
  }];
  return /*#__PURE__*/React.createElement("footer", {
    style: {
      background: 'var(--neutral-900)',
      borderTop: '1px solid rgba(255,255,255,0.08)'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      maxWidth: 1180,
      margin: '0 auto',
      padding: '56px 32px 36px',
      display: 'grid',
      gridTemplateColumns: '1.4fr 1fr 1fr 1fr',
      gap: 40
    }
  }, /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 10,
      marginBottom: 14
    }
  }, /*#__PURE__*/React.createElement("img", {
    src: "../../assets/logo/MDDOAI-white-2048.png",
    alt: "",
    style: {
      height: 28
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: 'var(--font-display)',
      fontWeight: 700,
      fontSize: 18,
      color: '#fff',
      letterSpacing: '-0.02em'
    }
  }, "MDDOAI")), /*#__PURE__*/React.createElement("p", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 13.5,
      lineHeight: 1.6,
      color: 'var(--text-on-inverse-muted)',
      margin: '0 0 18px',
      maxWidth: 280
    }
  }, "Model-driven DevOps AI \u2014 transform software architecture models into CI/CD pipelines."), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      gap: 10
    }
  }, ['github', 'book-open', 'container'].map(n => /*#__PURE__*/React.createElement("a", {
    key: n,
    href: "#",
    onClick: e => e.preventDefault(),
    style: {
      width: 34,
      height: 34,
      borderRadius: 'var(--radius-sm)',
      border: '1px solid rgba(255,255,255,0.12)',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      color: 'var(--text-on-inverse-muted)'
    }
  }, /*#__PURE__*/React.createElement(Icon, {
    name: n,
    size: 16
  }))))), cols.map(c => /*#__PURE__*/React.createElement("div", {
    key: c.h
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 12,
      fontWeight: 600,
      textTransform: 'uppercase',
      letterSpacing: '0.08em',
      color: 'var(--text-faint)',
      marginBottom: 14
    }
  }, c.h), /*#__PURE__*/React.createElement("ul", {
    style: {
      listStyle: 'none',
      margin: 0,
      padding: 0,
      display: 'flex',
      flexDirection: 'column',
      gap: 10
    }
  }, c.links.map(l => /*#__PURE__*/React.createElement("li", {
    key: l
  }, /*#__PURE__*/React.createElement("a", {
    href: "#",
    onClick: e => e.preventDefault(),
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 14,
      color: 'var(--text-on-inverse-muted)',
      textDecoration: 'none'
    },
    onMouseEnter: e => e.currentTarget.style.color = '#fff',
    onMouseLeave: e => e.currentTarget.style.color = 'var(--text-on-inverse-muted)'
  }, l))))))), /*#__PURE__*/React.createElement("div", {
    style: {
      borderTop: '1px solid rgba(255,255,255,0.08)'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      maxWidth: 1180,
      margin: '0 auto',
      padding: '20px 32px',
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'center'
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: 'var(--font-mono)',
      fontSize: 12,
      color: 'var(--text-faint)'
    }
  }, "\xA9 2026 MDDOAI \xB7 Eclipse Public License 2.0"), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: 'var(--font-mono)',
      fontSize: 12,
      color: 'var(--text-faint)'
    }
  }, "ghcr.io/modeldrivendevopsai/mddoai"))));
}
window.Footer = Footer;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/marketing/Footer.jsx", error: String((e && e.message) || e) }); }

// ui_kits/marketing/Header.jsx
try { (() => {
// MDDOAI marketing — sticky site header
const {
  Button,
  Icon
} = window.MDDOAIDesignSystem_0a843c;
function Header({
  onNav
}) {
  const [scrolled, setScrolled] = React.useState(false);
  React.useEffect(() => {
    const onScroll = () => setScrolled(window.scrollY > 8);
    window.addEventListener('scroll', onScroll);
    return () => window.removeEventListener('scroll', onScroll);
  }, []);
  const links = ['Product', 'Transformations', 'Docs', 'Pricing'];
  return /*#__PURE__*/React.createElement("header", {
    style: {
      position: 'sticky',
      top: 0,
      zIndex: 50,
      background: scrolled ? 'rgba(255,255,255,0.82)' : 'transparent',
      backdropFilter: scrolled ? 'saturate(140%) blur(12px)' : 'none',
      WebkitBackdropFilter: scrolled ? 'saturate(140%) blur(12px)' : 'none',
      borderBottom: scrolled ? '1px solid var(--border-subtle)' : '1px solid transparent',
      transition: 'background .25s var(--ease-out), border-color .25s var(--ease-out)'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      maxWidth: 1180,
      margin: '0 auto',
      padding: '0 32px',
      height: 68,
      display: 'flex',
      alignItems: 'center',
      gap: 32
    }
  }, /*#__PURE__*/React.createElement("a", {
    href: "#top",
    onClick: e => {
      e.preventDefault();
      onNav && onNav('top');
    },
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 10,
      textDecoration: 'none'
    }
  }, /*#__PURE__*/React.createElement("img", {
    src: "../../assets/logo/MDDOAI-transparent-2048.png",
    alt: "",
    style: {
      height: 30
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: 'var(--font-display)',
      fontWeight: 700,
      fontSize: 19,
      letterSpacing: '-0.02em',
      color: 'var(--text-strong)'
    }
  }, "MDDOAI")), /*#__PURE__*/React.createElement("nav", {
    style: {
      display: 'flex',
      gap: 4,
      marginLeft: 8
    }
  }, links.map(l => /*#__PURE__*/React.createElement("a", {
    key: l,
    href: "#",
    onClick: e => e.preventDefault(),
    style: {
      padding: '8px 12px',
      fontFamily: 'var(--font-sans)',
      fontSize: 14,
      fontWeight: 500,
      color: 'var(--text-body)',
      textDecoration: 'none',
      borderRadius: 'var(--radius-sm)'
    },
    onMouseEnter: e => e.currentTarget.style.color = 'var(--text-brand)',
    onMouseLeave: e => e.currentTarget.style.color = 'var(--text-body)'
  }, l))), /*#__PURE__*/React.createElement("div", {
    style: {
      marginLeft: 'auto',
      display: 'flex',
      alignItems: 'center',
      gap: 10
    }
  }, /*#__PURE__*/React.createElement("a", {
    href: "#",
    onClick: e => e.preventDefault(),
    style: {
      display: 'inline-flex',
      alignItems: 'center',
      gap: 7,
      fontFamily: 'var(--font-sans)',
      fontSize: 14,
      fontWeight: 500,
      color: 'var(--text-body)',
      textDecoration: 'none'
    }
  }, /*#__PURE__*/React.createElement(Icon, {
    name: "github",
    size: 17
  }), " GitHub"), /*#__PURE__*/React.createElement(Button, {
    variant: "primary",
    size: "sm",
    iconRight: /*#__PURE__*/React.createElement(Icon, {
      name: "arrow-right",
      size: 15
    }),
    onClick: () => onNav && onNav('quickstart')
  }, "Get started"))));
}
window.Header = Header;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/marketing/Header.jsx", error: String((e && e.message) || e) }); }

// ui_kits/marketing/Hero.jsx
try { (() => {
// MDDOAI marketing — hero
const {
  Button,
  Icon,
  Badge,
  CodeBlock,
  StatusPill
} = window.MDDOAIDesignSystem_0a843c;
function Hero({
  onNav
}) {
  return /*#__PURE__*/React.createElement("section", {
    id: "top",
    style: {
      position: 'relative',
      overflow: 'hidden'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      inset: 0,
      pointerEvents: 'none',
      background: 'radial-gradient(1100px 460px at 78% -10%, rgba(104,74,235,0.12), transparent 60%), radial-gradient(700px 380px at 8% 8%, rgba(164,94,237,0.08), transparent 60%)'
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'relative',
      maxWidth: 1180,
      margin: '0 auto',
      padding: '64px 32px 72px',
      display: 'grid',
      gridTemplateColumns: '1.05fr 0.95fr',
      gap: 56,
      alignItems: 'center'
    }
  }, /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'inline-flex',
      alignItems: 'center',
      gap: 8,
      padding: '5px 12px 5px 8px',
      background: 'var(--brand-faint)',
      border: '1px solid var(--border-brand)',
      borderRadius: 'var(--radius-pill)',
      marginBottom: 22
    }
  }, /*#__PURE__*/React.createElement(Badge, {
    variant: "solid",
    size: "sm"
  }, "v1.0.1"), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 13,
      color: 'var(--text-brand)',
      fontWeight: 500
    }
  }, "Model-driven pipelines, now AI-assisted")), /*#__PURE__*/React.createElement("h1", {
    style: {
      fontFamily: 'var(--font-display)',
      fontWeight: 700,
      fontSize: 56,
      lineHeight: 1.04,
      letterSpacing: '-0.03em',
      color: 'var(--text-strong)',
      margin: '0 0 20px'
    }
  }, "Turn architecture", /*#__PURE__*/React.createElement("br", null), "models into", ' ', /*#__PURE__*/React.createElement("span", {
    style: {
      background: 'var(--gradient-brand)',
      WebkitBackgroundClip: 'text',
      backgroundClip: 'text',
      color: 'transparent'
    }
  }, "CI/CD pipelines"), "."), /*#__PURE__*/React.createElement("p", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 19,
      lineHeight: 1.55,
      color: 'var(--text-body)',
      margin: '0 0 30px',
      maxWidth: 520
    }
  }, "Feed MDDOAI a software architecture model and it generates a ready-to-run GitLab ", /*#__PURE__*/React.createElement("code", {
    style: {
      fontFamily: 'var(--font-mono)',
      fontSize: 16,
      color: 'var(--text-brand)',
      background: 'var(--brand-faint)',
      padding: '1px 6px',
      borderRadius: 6
    }
  }, ".gitlab-ci.yml"), " \u2014 no boilerplate, no copy-paste."), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      gap: 12,
      marginBottom: 26
    }
  }, /*#__PURE__*/React.createElement(Button, {
    variant: "gradient",
    size: "lg",
    iconRight: /*#__PURE__*/React.createElement(Icon, {
      name: "arrow-right",
      size: 18
    }),
    onClick: () => onNav && onNav('quickstart')
  }, "Start in 2 minutes"), /*#__PURE__*/React.createElement(Button, {
    variant: "secondary",
    size: "lg",
    iconLeft: /*#__PURE__*/React.createElement(Icon, {
      name: "github",
      size: 18
    })
  }, "View source")), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 18,
      fontFamily: 'var(--font-mono)',
      fontSize: 12.5,
      color: 'var(--text-muted)'
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      display: 'inline-flex',
      alignItems: 'center',
      gap: 6
    }
  }, /*#__PURE__*/React.createElement(Icon, {
    name: "check",
    size: 15,
    color: "var(--success-500)"
  }), " Open source \xB7 EPL-2.0"), /*#__PURE__*/React.createElement("span", {
    style: {
      display: 'inline-flex',
      alignItems: 'center',
      gap: 6
    }
  }, /*#__PURE__*/React.createElement(Icon, {
    name: "container",
    size: 15,
    color: "var(--text-faint)"
  }), " Docker & CLI"))), /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'relative'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      inset: '-14px -14px auto auto',
      width: 130,
      height: 130,
      background: 'var(--gradient-brand)',
      filter: 'blur(48px)',
      opacity: 0.35,
      borderRadius: '50%'
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'relative'
    }
  }, /*#__PURE__*/React.createElement(CodeBlock, {
    title: "swarch2gitlab",
    lines: [{
      text: 'docker run --rm \\\\',
      prompt: true
    }, {
      text: '  -v "$(pwd)/input:/app/input" \\\\'
    }, {
      text: '  -v "$(pwd)/output:/app/output" \\\\'
    }, {
      text: '  mddoai:1.0.1 \\\\'
    }, {
      text: '  swarch2gitlab input.swarch /app/output',
      kind: 'string'
    }, {
      text: ''
    }, {
      text: '→ parsing architecture model…',
      kind: 'comment'
    }, {
      text: '→ PIM → PSM → GitLab YAML',
      kind: 'comment'
    }, {
      text: '✓ wrote output/.gitlab-ci.yml',
      kind: 'flag'
    }, {
      text: 'BUILD SUCCESSFUL',
      kind: 'flag'
    }]
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'space-between',
      marginTop: 14,
      padding: '12px 16px',
      background: 'var(--surface-card)',
      border: '1px solid var(--border-subtle)',
      borderRadius: 'var(--radius-md)',
      boxShadow: 'var(--shadow-sm)'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 10
    }
  }, /*#__PURE__*/React.createElement(Icon, {
    name: "file-code",
    size: 18,
    color: "var(--brand)"
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: 'var(--font-mono)',
      fontSize: 13,
      color: 'var(--text-strong)'
    }
  }, ".gitlab-ci.yml")), /*#__PURE__*/React.createElement(StatusPill, {
    status: "passed"
  }, "generated"))))));
}
window.Hero = Hero;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/marketing/Hero.jsx", error: String((e && e.message) || e) }); }

// ui_kits/marketing/Quickstart.jsx
try { (() => {
// MDDOAI marketing — interactive quickstart with method tabs
const {
  Tabs,
  CodeBlock,
  Icon,
  Callout,
  Button
} = window.MDDOAIDesignSystem_0a843c;
function Quickstart() {
  const [method, setMethod] = React.useState('docker');
  const docker = [{
    text: '# 1 — pull the image',
    kind: 'comment'
  }, {
    text: 'docker pull ghcr.io/modeldrivendevopsai/mddoai/mddoai:1.0.1',
    prompt: true
  }, {
    text: ''
  }, {
    text: '# 2 — prepare input/output',
    kind: 'comment'
  }, {
    text: 'mkdir -p input output',
    prompt: true
  }, {
    text: ''
  }, {
    text: '# 3 — run the transformation',
    kind: 'comment'
  }, {
    text: 'docker run --rm \\\\',
    prompt: true
  }, {
    text: '  -v "$(pwd)/input:/app/input" \\\\'
  }, {
    text: '  -v "$(pwd)/output:/app/output" \\\\'
  }, {
    text: '  mddoai:1.0.1 swarch2gitlab \\\\',
    kind: 'string'
  }, {
    text: '  /app/input/my-app.swarch /app/output'
  }];
  const source = [{
    text: '# clone & build with Gradle',
    kind: 'comment'
  }, {
    text: 'git clone https://github.com/modeldrivendevopsai/mddoai.git',
    prompt: true
  }, {
    text: 'cd mddoai/main',
    prompt: true
  }, {
    text: './gradlew clean installDist',
    prompt: true
  }, {
    text: ''
  }, {
    text: '# run the CLI',
    kind: 'comment'
  }, {
    text: './cli.bat swarch2gitlab \\\\',
    prompt: true
  }, {
    text: '  ./input/my-app.swarch ./output'
  }, {
    text: 'BUILD SUCCESSFUL',
    kind: 'flag'
  }];
  const ci = [{
    text: '# .gitlab-ci.yml — regenerate on every push',
    kind: 'comment'
  }, {
    text: 'generate-pipeline:',
    kind: 'string'
  }, {
    text: '  image: ghcr.io/modeldrivendevopsai/mddoai/mddoai:1.0.1'
  }, {
    text: '  script:'
  }, {
    text: '    - swarch2gitlab architecture.swarch ./generated',
    kind: 'string'
  }, {
    text: '  artifacts:'
  }, {
    text: '    paths: [ generated/.gitlab-ci.yml ]'
  }];
  const map = {
    docker,
    source,
    ci
  };
  return /*#__PURE__*/React.createElement("section", {
    id: "quickstart",
    style: {
      maxWidth: 1180,
      margin: '0 auto',
      padding: '78px 32px'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'grid',
      gridTemplateColumns: '0.85fr 1.15fr',
      gap: 48,
      alignItems: 'start'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'sticky',
      top: 96
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 13,
      fontWeight: 600,
      textTransform: 'uppercase',
      letterSpacing: '0.08em',
      color: 'var(--text-brand)',
      marginBottom: 12
    }
  }, "Quickstart"), /*#__PURE__*/React.createElement("h2", {
    style: {
      fontFamily: 'var(--font-display)',
      fontWeight: 700,
      fontSize: 38,
      lineHeight: 1.1,
      letterSpacing: '-0.025em',
      color: 'var(--text-strong)',
      margin: '0 0 16px'
    }
  }, "Two ways to run it"), /*#__PURE__*/React.createElement("p", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 16,
      lineHeight: 1.6,
      color: 'var(--text-body)',
      margin: '0 0 24px'
    }
  }, "Use Docker for a 2-minute start with zero setup, or build from source for development and customization."), /*#__PURE__*/React.createElement(Callout, {
    tone: "warning",
    title: "Windows users",
    icon: /*#__PURE__*/React.createElement(Icon, {
      name: "terminal",
      size: 17
    })
  }, "In CMD use ", /*#__PURE__*/React.createElement("code", {
    style: {
      fontFamily: 'var(--font-mono)'
    }
  }, ".\\cli.bat"), " instead of ", /*#__PURE__*/React.createElement("code", {
    style: {
      fontFamily: 'var(--font-mono)'
    }
  }, "./cli.bat"), "."), /*#__PURE__*/React.createElement("div", {
    style: {
      marginTop: 22,
      display: 'flex',
      gap: 10
    }
  }, /*#__PURE__*/React.createElement(Button, {
    variant: "primary",
    iconRight: /*#__PURE__*/React.createElement(Icon, {
      name: "arrow-right",
      size: 15
    })
  }, "Read the full guide"))), /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement("div", {
    style: {
      marginBottom: 16
    }
  }, /*#__PURE__*/React.createElement(Tabs, {
    value: method,
    onChange: setMethod,
    tabs: [{
      id: 'docker',
      label: 'Docker',
      icon: /*#__PURE__*/React.createElement(Icon, {
        name: "container",
        size: 15
      })
    }, {
      id: 'source',
      label: 'From source',
      icon: /*#__PURE__*/React.createElement(Icon, {
        name: "git-branch",
        size: 15
      })
    }, {
      id: 'ci',
      label: 'In CI/CD',
      icon: /*#__PURE__*/React.createElement(Icon, {
        name: "workflow",
        size: 15
      })
    }]
  })), /*#__PURE__*/React.createElement(CodeBlock, {
    key: method,
    title: method === 'ci' ? '.gitlab-ci.yml' : 'bash',
    lang: method === 'ci' ? 'yaml' : 'bash',
    lines: map[method]
  }))));
}
function CTA({
  onNav
}) {
  return /*#__PURE__*/React.createElement("section", {
    style: {
      position: 'relative',
      overflow: 'hidden',
      background: 'var(--surface-inverse)'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      inset: 0,
      pointerEvents: 'none',
      background: 'radial-gradient(680px 320px at 50% 120%, rgba(104,74,235,0.45), transparent 65%), radial-gradient(420px 240px at 85% -20%, rgba(164,94,237,0.3), transparent 60%)'
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'relative',
      maxWidth: 880,
      margin: '0 auto',
      padding: '84px 32px',
      textAlign: 'center'
    }
  }, /*#__PURE__*/React.createElement("img", {
    src: "../../assets/logo/MDDOAI-white-2048.png",
    alt: "",
    style: {
      height: 52,
      marginBottom: 26
    }
  }), /*#__PURE__*/React.createElement("h2", {
    style: {
      fontFamily: 'var(--font-display)',
      fontWeight: 700,
      fontSize: 44,
      lineHeight: 1.08,
      letterSpacing: '-0.03em',
      color: '#fff',
      margin: '0 0 16px'
    }
  }, "Stop hand-writing pipelines."), /*#__PURE__*/React.createElement("p", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 18,
      lineHeight: 1.55,
      color: 'var(--text-on-inverse-muted)',
      margin: '0 auto 30px',
      maxWidth: 540
    }
  }, "Generate your first GitLab CI/CD pipeline from an architecture model in under two minutes."), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      gap: 12,
      justifyContent: 'center'
    }
  }, /*#__PURE__*/React.createElement(Button, {
    variant: "gradient",
    size: "lg",
    iconRight: /*#__PURE__*/React.createElement(Icon, {
      name: "arrow-right",
      size: 18
    }),
    onClick: () => onNav && onNav('quickstart')
  }, "Get started free"), /*#__PURE__*/React.createElement(Button, {
    variant: "secondary",
    size: "lg",
    iconLeft: /*#__PURE__*/React.createElement(Icon, {
      name: "github",
      size: 18
    })
  }, "Star on GitHub"))));
}
window.Quickstart = Quickstart;
window.CTA = CTA;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/marketing/Quickstart.jsx", error: String((e && e.message) || e) }); }

// ui_kits/marketing/Sections.jsx
try { (() => {
// MDDOAI marketing — how it works + transformation types + features
const {
  Card,
  Icon,
  Badge
} = window.MDDOAIDesignSystem_0a843c;
function Eyebrow({
  children
}) {
  return /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 13,
      fontWeight: 600,
      textTransform: 'uppercase',
      letterSpacing: '0.08em',
      color: 'var(--text-brand)',
      marginBottom: 12
    }
  }, children);
}
function SectionTitle({
  children,
  sub
}) {
  return /*#__PURE__*/React.createElement("div", {
    style: {
      maxWidth: 640
    }
  }, /*#__PURE__*/React.createElement("h2", {
    style: {
      fontFamily: 'var(--font-display)',
      fontWeight: 700,
      fontSize: 38,
      lineHeight: 1.1,
      letterSpacing: '-0.025em',
      color: 'var(--text-strong)',
      margin: '0 0 14px'
    }
  }, children), sub && /*#__PURE__*/React.createElement("p", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 17,
      lineHeight: 1.55,
      color: 'var(--text-body)',
      margin: 0
    }
  }, sub));
}
function Pipeline() {
  const steps = [{
    icon: 'box',
    tag: '.swarch',
    title: 'Model your architecture',
    body: 'Describe services, dependencies, and stages in a software architecture model.'
  }, {
    icon: 'wand-sparkles',
    tag: 'PIM → PSM',
    title: 'MDDOAI transforms',
    body: 'Model-driven transformations map your architecture to a platform-specific pipeline model.'
  }, {
    icon: 'file-code',
    tag: '.gitlab-ci.yml',
    title: 'Run the pipeline',
    body: 'Get a ready-to-commit GitLab CI/CD configuration, generated and validated.'
  }];
  return /*#__PURE__*/React.createElement("section", {
    style: {
      background: 'var(--surface-card)',
      borderTop: '1px solid var(--border-subtle)',
      borderBottom: '1px solid var(--border-subtle)'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      maxWidth: 1180,
      margin: '0 auto',
      padding: '72px 32px'
    }
  }, /*#__PURE__*/React.createElement(Eyebrow, null, "How it works"), /*#__PURE__*/React.createElement(SectionTitle, {
    sub: "One transformation, three steps. The model is the single source of truth \u2014 the pipeline is generated, never hand-maintained."
  }, "From model to pipeline"), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'grid',
      gridTemplateColumns: 'repeat(3, 1fr)',
      gap: 20,
      marginTop: 40,
      position: 'relative'
    }
  }, steps.map((s, i) => /*#__PURE__*/React.createElement("div", {
    key: i,
    style: {
      position: 'relative'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 12,
      marginBottom: 16
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      width: 44,
      height: 44,
      borderRadius: 'var(--radius-md)',
      background: 'var(--brand-faint)',
      border: '1px solid var(--border-brand)',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center'
    }
  }, /*#__PURE__*/React.createElement(Icon, {
    name: s.icon,
    size: 21,
    color: "var(--brand)"
  })), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: 'var(--font-mono)',
      fontSize: 11,
      color: 'var(--text-faint)'
    }
  }, "0", i + 1), i < steps.length - 1 && /*#__PURE__*/React.createElement(Icon, {
    name: "arrow-right",
    size: 18,
    color: "var(--border-strong)",
    style: {
      marginLeft: 'auto'
    }
  })), /*#__PURE__*/React.createElement(Badge, {
    variant: "brand"
  }, s.tag), /*#__PURE__*/React.createElement("h3", {
    style: {
      fontFamily: 'var(--font-display)',
      fontWeight: 600,
      fontSize: 19,
      color: 'var(--text-strong)',
      margin: '12px 0 8px',
      letterSpacing: '-0.01em'
    }
  }, s.title), /*#__PURE__*/React.createElement("p", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 14.5,
      lineHeight: 1.55,
      color: 'var(--text-muted)',
      margin: 0
    }
  }, s.body))))));
}
function Transformations() {
  const types = [{
    cmd: 'swarch2gitlab',
    input: '.swarch',
    use: 'End-to-end transformation',
    desc: 'Architecture model straight to a GitLab pipeline. The default path.',
    primary: true
  }, {
    cmd: 'pim2gitlab',
    input: '.pim',
    use: 'Custom PIM workflows',
    desc: 'Start from a platform-independent model for advanced control.'
  }, {
    cmd: 'psm2gitlab',
    input: '.gitlabpsm',
    use: 'Direct PSM to YAML',
    desc: 'Already have a platform-specific model? Emit YAML directly.'
  }];
  return /*#__PURE__*/React.createElement("section", {
    style: {
      maxWidth: 1180,
      margin: '0 auto',
      padding: '78px 32px'
    }
  }, /*#__PURE__*/React.createElement(Eyebrow, null, "Transformations"), /*#__PURE__*/React.createElement(SectionTitle, {
    sub: "Three transformation types cover the full model-driven spectrum, from a single architecture file to fine-grained platform control."
  }, "Pick your entry point"), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'grid',
      gridTemplateColumns: 'repeat(3, 1fr)',
      gap: 18,
      marginTop: 38
    }
  }, types.map(t => /*#__PURE__*/React.createElement(Card, {
    key: t.cmd,
    hover: true,
    padding: 22,
    style: t.primary ? {
      borderColor: 'var(--border-brand)',
      boxShadow: 'var(--shadow-md)'
    } : {}
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'space-between',
      marginBottom: 16
    }
  }, /*#__PURE__*/React.createElement(Icon, {
    name: "git-compare-arrows",
    size: 22,
    color: "var(--brand)"
  }), t.primary && /*#__PURE__*/React.createElement(Badge, {
    variant: "solid",
    size: "sm"
  }, "recommended")), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: 'var(--font-mono)',
      fontSize: 16,
      fontWeight: 600,
      color: 'var(--text-strong)'
    }
  }, t.cmd), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      gap: 8,
      alignItems: 'center',
      margin: '10px 0 14px',
      fontFamily: 'var(--font-mono)',
      fontSize: 12
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      color: 'var(--text-muted)'
    }
  }, t.input), /*#__PURE__*/React.createElement(Icon, {
    name: "arrow-right",
    size: 13,
    color: "var(--text-faint)"
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      color: 'var(--text-brand)'
    }
  }, ".gitlab-ci.yml")), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 14,
      color: 'var(--text-muted)',
      lineHeight: 1.55,
      marginBottom: 8
    }
  }, t.desc), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 13,
      color: 'var(--text-faint)'
    }
  }, t.use)))));
}
function Features() {
  const feats = [{
    icon: 'boxes',
    title: 'Model-driven core',
    body: 'Built on EMF, Acceleo, and ATL. Metamodels and templates do the work — deterministic, inspectable, versionable.'
  }, {
    icon: 'container',
    title: 'Docker or source',
    body: 'Pull a versioned image and run in two minutes, or build from source with Gradle for development.'
  }, {
    icon: 'bot',
    title: 'AI-assisted',
    body: 'Copilot agents handle PR review, test generation, and transformation debugging across the project.'
  }, {
    icon: 'git-branch',
    title: 'Pipeline-native',
    body: 'Drop MDDOAI into your own CI/CD to regenerate pipelines as architecture evolves.'
  }, {
    icon: 'tags',
    title: 'Semantic releases',
    body: 'Three-tier image tags — snapshot, main, and permanent semver releases for production.'
  }, {
    icon: 'shield-check',
    title: 'Tested & covered',
    body: 'Unit, integration, and end-to-end suites with published coverage reports.'
  }];
  return /*#__PURE__*/React.createElement("section", {
    style: {
      background: 'var(--surface-card)',
      borderTop: '1px solid var(--border-subtle)'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      maxWidth: 1180,
      margin: '0 auto',
      padding: '78px 32px'
    }
  }, /*#__PURE__*/React.createElement(Eyebrow, null, "Why MDDOAI"), /*#__PURE__*/React.createElement(SectionTitle, {
    sub: "An engineering tool, not a wizard. Everything is a model, a template, or a command."
  }, "Built for platform engineers"), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'grid',
      gridTemplateColumns: 'repeat(3, 1fr)',
      gap: 18,
      marginTop: 38
    }
  }, feats.map(f => /*#__PURE__*/React.createElement("div", {
    key: f.title,
    style: {
      padding: 4
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      width: 40,
      height: 40,
      borderRadius: 'var(--radius-md)',
      background: 'var(--surface-sunken)',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      marginBottom: 14
    }
  }, /*#__PURE__*/React.createElement(Icon, {
    name: f.icon,
    size: 20,
    color: "var(--brand)"
  })), /*#__PURE__*/React.createElement("h3", {
    style: {
      fontFamily: 'var(--font-display)',
      fontWeight: 600,
      fontSize: 17,
      color: 'var(--text-strong)',
      margin: '0 0 7px',
      letterSpacing: '-0.01em'
    }
  }, f.title), /*#__PURE__*/React.createElement("p", {
    style: {
      fontFamily: 'var(--font-sans)',
      fontSize: 14,
      lineHeight: 1.55,
      color: 'var(--text-muted)',
      margin: 0
    }
  }, f.body))))));
}
window.Pipeline = Pipeline;
window.Transformations = Transformations;
window.Features = Features;
})(); } catch (e) { __ds_ns.__errors.push({ path: "ui_kits/marketing/Sections.jsx", error: String((e && e.message) || e) }); }

// video/animations.jsx
try { (() => {
// @ds-adherence-ignore -- omelette starter scaffold (raw elements/hex/px by design)

/* BEGIN USAGE */
// animations.jsx
// Reusable animation starter: Stage, Timeline, Sprite, easing helpers.
// Exports (to window): Stage, Sprite, PlaybackBar, TextSprite, ImageSprite, RectSprite,
//   useTime, useTimeline, useSprite, Easing, interpolate, animate, clamp.
//
// Usage (in an HTML file that loads React + Babel):
//
//   <Stage width={1280} height={720} duration={10} background="#f6f4ef">
//     <MyScene />
//   </Stage>
//
// <Stage> auto-scales to the viewport and provides the scrubber, play/pause,
// ←/→ seek, space, and 0-to-reset controls, and persists the playhead.
// Inside <Stage>, any child can call useTime() to read the current
// playhead (seconds). Or wrap content in <Sprite start={1} end={4}>...</Sprite>
// to only render during that window -- children receive a `localTime` and
// `progress` via the useSprite() hook. Use Easing + interpolate()/animate()
// for tweens; TextSprite / ImageSprite / RectSprite have built-in entry/exit.
// Build YOUR scenes by composing Sprites inside a Stage.
/* END USAGE */
// ─────────────────────────────────────────────────────────────────────────────

// ── Easing functions (hand-rolled, Popmotion-style) ─────────────────────────
// All easings take t ∈ [0,1] and return eased t ∈ [0,1] (may overshoot for back/elastic).
const Easing = {
  linear: t => t,
  // Quad
  easeInQuad: t => t * t,
  easeOutQuad: t => t * (2 - t),
  easeInOutQuad: t => t < 0.5 ? 2 * t * t : -1 + (4 - 2 * t) * t,
  // Cubic
  easeInCubic: t => t * t * t,
  easeOutCubic: t => --t * t * t + 1,
  easeInOutCubic: t => t < 0.5 ? 4 * t * t * t : (t - 1) * (2 * t - 2) * (2 * t - 2) + 1,
  // Quart
  easeInQuart: t => t * t * t * t,
  easeOutQuart: t => 1 - --t * t * t * t,
  easeInOutQuart: t => t < 0.5 ? 8 * t * t * t * t : 1 - 8 * --t * t * t * t,
  // Expo
  easeInExpo: t => t === 0 ? 0 : Math.pow(2, 10 * (t - 1)),
  easeOutExpo: t => t === 1 ? 1 : 1 - Math.pow(2, -10 * t),
  easeInOutExpo: t => {
    if (t === 0) return 0;
    if (t === 1) return 1;
    if (t < 0.5) return 0.5 * Math.pow(2, 20 * t - 10);
    return 1 - 0.5 * Math.pow(2, -20 * t + 10);
  },
  // Sine
  easeInSine: t => 1 - Math.cos(t * Math.PI / 2),
  easeOutSine: t => Math.sin(t * Math.PI / 2),
  easeInOutSine: t => -(Math.cos(Math.PI * t) - 1) / 2,
  // Back (overshoot)
  easeOutBack: t => {
    const c1 = 1.70158,
      c3 = c1 + 1;
    return 1 + c3 * Math.pow(t - 1, 3) + c1 * Math.pow(t - 1, 2);
  },
  easeInBack: t => {
    const c1 = 1.70158,
      c3 = c1 + 1;
    return c3 * t * t * t - c1 * t * t;
  },
  easeInOutBack: t => {
    const c1 = 1.70158,
      c2 = c1 * 1.525;
    return t < 0.5 ? Math.pow(2 * t, 2) * ((c2 + 1) * 2 * t - c2) / 2 : (Math.pow(2 * t - 2, 2) * ((c2 + 1) * (t * 2 - 2) + c2) + 2) / 2;
  },
  // Elastic
  easeOutElastic: t => {
    const c4 = 2 * Math.PI / 3;
    if (t === 0) return 0;
    if (t === 1) return 1;
    return Math.pow(2, -10 * t) * Math.sin((t * 10 - 0.75) * c4) + 1;
  }
};

// ── Core interpolation helpers ──────────────────────────────────────────────

// Clamp a value to [min, max]
const clamp = (v, min, max) => Math.max(min, Math.min(max, v));

// interpolate([0, 0.5, 1], [0, 100, 50], ease?) -> fn(t)
// Popmotion-style: linearly maps t across input keyframes to output values,
// with optional easing per segment (single fn or array of fns).
function interpolate(input, output, ease = Easing.linear) {
  return t => {
    if (t <= input[0]) return output[0];
    if (t >= input[input.length - 1]) return output[output.length - 1];
    for (let i = 0; i < input.length - 1; i++) {
      if (t >= input[i] && t <= input[i + 1]) {
        const span = input[i + 1] - input[i];
        const local = span === 0 ? 0 : (t - input[i]) / span;
        const easeFn = Array.isArray(ease) ? ease[i] || Easing.linear : ease;
        const eased = easeFn(local);
        return output[i] + (output[i + 1] - output[i]) * eased;
      }
    }
    return output[output.length - 1];
  };
}

// animate({from, to, start, end, ease})(t) — simpler single-segment tween.
// Returns `from` before `start`, `to` after `end`.
function animate({
  from = 0,
  to = 1,
  start = 0,
  end = 1,
  ease = Easing.easeInOutCubic
}) {
  return t => {
    if (t <= start) return from;
    if (t >= end) return to;
    const local = (t - start) / (end - start);
    return from + (to - from) * ease(local);
  };
}

// ── Timeline context ────────────────────────────────────────────────────────

const TimelineContext = React.createContext({
  time: 0,
  duration: 10,
  playing: false
});
const useTime = () => React.useContext(TimelineContext).time;
const useTimeline = () => React.useContext(TimelineContext);

// ── Sprite ──────────────────────────────────────────────────────────────────
// Renders children only when the playhead is inside [start, end]. Provides
// a sub-context with `localTime` (seconds since start) and `progress` (0..1).
//
//   <Sprite start={2} end={5}>
//     {({ localTime, progress }) => <Thing x={progress * 100} />}
//   </Sprite>
//
// Or as a plain wrapper — children can call useSprite() themselves.

const SpriteContext = React.createContext({
  localTime: 0,
  progress: 0,
  duration: 0
});
const useSprite = () => React.useContext(SpriteContext);
function Sprite({
  start = 0,
  end = Infinity,
  children,
  keepMounted = false
}) {
  const {
    time
  } = useTimeline();
  const visible = time >= start && time <= end;
  if (!visible && !keepMounted) return null;
  const duration = end - start;
  const localTime = Math.max(0, time - start);
  const progress = duration > 0 && isFinite(duration) ? clamp(localTime / duration, 0, 1) : 0;
  const value = {
    localTime,
    progress,
    duration,
    visible
  };
  return /*#__PURE__*/React.createElement(SpriteContext.Provider, {
    value: value
  }, typeof children === 'function' ? children(value) : children);
}

// ── Sample sprite components ────────────────────────────────────────────────

// TextSprite: fades/slides text in on entry, holds, then fades out on exit.
// Props: text, x, y, size, color, font, entryDur, exitDur, align
function TextSprite({
  text,
  x = 0,
  y = 0,
  size = 48,
  color = '#111',
  font = 'Inter, system-ui, sans-serif',
  weight = 600,
  entryDur = 0.45,
  exitDur = 0.35,
  entryEase = Easing.easeOutBack,
  exitEase = Easing.easeInCubic,
  align = 'left',
  letterSpacing = '-0.01em'
}) {
  const {
    localTime,
    duration
  } = useSprite();
  const exitStart = Math.max(0, duration - exitDur);
  let opacity = 1;
  let ty = 0;
  if (localTime < entryDur) {
    const t = entryEase(clamp(localTime / entryDur, 0, 1));
    opacity = t;
    ty = (1 - t) * 16;
  } else if (localTime > exitStart) {
    const t = exitEase(clamp((localTime - exitStart) / exitDur, 0, 1));
    opacity = 1 - t;
    ty = -t * 8;
  }
  const translateX = align === 'center' ? '-50%' : align === 'right' ? '-100%' : '0';
  return /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      left: x,
      top: y,
      transform: `translate(${translateX}, ${ty}px)`,
      opacity,
      fontFamily: font,
      fontSize: size,
      fontWeight: weight,
      color,
      letterSpacing,
      whiteSpace: 'pre',
      lineHeight: 1.1,
      willChange: 'transform, opacity'
    }
  }, text);
}

// ImageSprite: scales + fades in; optional Ken Burns drift during hold.
function ImageSprite({
  src,
  x = 0,
  y = 0,
  width = 400,
  height = 300,
  entryDur = 0.6,
  exitDur = 0.4,
  kenBurns = false,
  kenBurnsScale = 1.08,
  radius = 12,
  fit = 'cover',
  placeholder = null // {label: string} for striped placeholder
}) {
  const {
    localTime,
    duration
  } = useSprite();
  const exitStart = Math.max(0, duration - exitDur);
  let opacity = 1;
  let scale = 1;
  if (localTime < entryDur) {
    const t = Easing.easeOutCubic(clamp(localTime / entryDur, 0, 1));
    opacity = t;
    scale = 0.96 + 0.04 * t;
  } else if (localTime > exitStart) {
    const t = Easing.easeInCubic(clamp((localTime - exitStart) / exitDur, 0, 1));
    opacity = 1 - t;
    scale = (kenBurns ? kenBurnsScale : 1) + 0.02 * t;
  } else if (kenBurns) {
    const holdSpan = exitStart - entryDur;
    const holdT = holdSpan > 0 ? (localTime - entryDur) / holdSpan : 0;
    scale = 1 + (kenBurnsScale - 1) * holdT;
  }
  const content = placeholder ? /*#__PURE__*/React.createElement("div", {
    style: {
      width: '100%',
      height: '100%',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      background: 'repeating-linear-gradient(135deg, #e9e6df 0 10px, #dcd8cf 10px 20px)',
      color: '#6b6458',
      fontFamily: 'JetBrains Mono, ui-monospace, monospace',
      fontSize: 13,
      letterSpacing: '0.04em',
      textTransform: 'uppercase'
    }
  }, placeholder.label || 'image') : /*#__PURE__*/React.createElement("img", {
    src: src,
    alt: "",
    style: {
      width: '100%',
      height: '100%',
      objectFit: fit,
      display: 'block'
    }
  });
  return /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      left: x,
      top: y,
      width,
      height,
      opacity,
      transform: `scale(${scale})`,
      transformOrigin: 'center',
      borderRadius: radius,
      overflow: 'hidden',
      willChange: 'transform, opacity'
    }
  }, content);
}

// RectSprite: simple rectangle that animates position/size/color via props.
// Useful demo primitive — takes a `render` fn for per-frame customization.
function RectSprite({
  x = 0,
  y = 0,
  width = 100,
  height = 100,
  color = '#111',
  radius = 8,
  entryDur = 0.4,
  exitDur = 0.3,
  render // optional: (ctx) => style overrides
}) {
  const spriteCtx = useSprite();
  const {
    localTime,
    duration
  } = spriteCtx;
  const exitStart = Math.max(0, duration - exitDur);
  let opacity = 1;
  let scale = 1;
  if (localTime < entryDur) {
    const t = Easing.easeOutBack(clamp(localTime / entryDur, 0, 1));
    opacity = clamp(localTime / entryDur, 0, 1);
    scale = 0.4 + 0.6 * t;
  } else if (localTime > exitStart) {
    const t = Easing.easeInQuad(clamp((localTime - exitStart) / exitDur, 0, 1));
    opacity = 1 - t;
    scale = 1 - 0.15 * t;
  }
  const overrides = render ? render(spriteCtx) : {};
  return /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      left: x,
      top: y,
      width,
      height,
      background: color,
      borderRadius: radius,
      opacity,
      transform: `scale(${scale})`,
      transformOrigin: 'center',
      willChange: 'transform, opacity',
      ...overrides
    }
  });
}
function Stage({
  width = 1280,
  height = 720,
  duration = 10,
  background = '#f6f4ef',
  fps = 60,
  loop = true,
  autoplay = true,
  persistKey = 'animstage',
  children
}) {
  const [time, setTime] = React.useState(() => {
    try {
      const v = parseFloat(localStorage.getItem(persistKey + ':t') || '0');
      return isFinite(v) ? clamp(v, 0, duration) : 0;
    } catch {
      return 0;
    }
  });
  const [playing, setPlaying] = React.useState(autoplay);
  const [hoverTime, setHoverTime] = React.useState(null);
  const [scale, setScale] = React.useState(1);
  const stageRef = React.useRef(null);
  const canvasRef = React.useRef(null);
  const rafRef = React.useRef(null);
  const lastTsRef = React.useRef(null);

  // Persist playhead
  React.useEffect(() => {
    try {
      localStorage.setItem(persistKey + ':t', String(time));
    } catch {}
  }, [time, persistKey]);

  // Auto-scale to fit viewport
  React.useEffect(() => {
    if (!stageRef.current) return;
    const el = stageRef.current;
    const measure = () => {
      const barH = 44; // playback bar height
      const s = Math.min(el.clientWidth / width, (el.clientHeight - barH) / height);
      setScale(Math.max(0.05, s));
    };
    measure();
    const ro = new ResizeObserver(measure);
    ro.observe(el);
    window.addEventListener('resize', measure);
    return () => {
      ro.disconnect();
      window.removeEventListener('resize', measure);
    };
  }, [width, height]);

  // Animation loop
  React.useEffect(() => {
    if (!playing) {
      lastTsRef.current = null;
      return;
    }
    const step = ts => {
      if (lastTsRef.current == null) lastTsRef.current = ts;
      const dt = (ts - lastTsRef.current) / 1000;
      lastTsRef.current = ts;
      setTime(t => {
        let next = t + dt;
        if (next >= duration) {
          if (loop) next = next % duration;else {
            next = duration;
            setPlaying(false);
          }
        }
        return next;
      });
      rafRef.current = requestAnimationFrame(step);
    };
    rafRef.current = requestAnimationFrame(step);
    return () => {
      if (rafRef.current) cancelAnimationFrame(rafRef.current);
      lastTsRef.current = null;
    };
  }, [playing, duration, loop]);

  // Keyboard: space = play/pause, ← → = seek
  React.useEffect(() => {
    const onKey = e => {
      if (e.target && (e.target.tagName === 'INPUT' || e.target.tagName === 'TEXTAREA')) return;
      if (e.code === 'Space') {
        e.preventDefault();
        setPlaying(p => !p);
      } else if (e.code === 'ArrowLeft') {
        setTime(t => clamp(t - (e.shiftKey ? 1 : 0.1), 0, duration));
      } else if (e.code === 'ArrowRight') {
        setTime(t => clamp(t + (e.shiftKey ? 1 : 0.1), 0, duration));
      } else if (e.key === '0' || e.code === 'Home') {
        setTime(0);
      }
    };
    window.addEventListener('keydown', onKey);
    return () => window.removeEventListener('keydown', onKey);
  }, [duration]);
  const displayTime = hoverTime != null ? hoverTime : time;
  const ctxValue = React.useMemo(() => ({
    time: displayTime,
    duration,
    playing,
    setTime,
    setPlaying
  }), [displayTime, duration, playing]);
  return /*#__PURE__*/React.createElement("div", {
    ref: stageRef,
    style: {
      position: 'absolute',
      inset: 0,
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
      background: '#0a0a0a',
      fontFamily: 'Inter, system-ui, sans-serif'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      flex: 1,
      width: '100%',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      overflow: 'hidden',
      minHeight: 0
    }
  }, /*#__PURE__*/React.createElement("div", {
    ref: canvasRef,
    style: {
      width,
      height,
      background,
      position: 'relative',
      transform: `scale(${scale})`,
      transformOrigin: 'center',
      flexShrink: 0,
      boxShadow: '0 20px 60px rgba(0,0,0,0.4)',
      overflow: 'hidden'
    }
  }, /*#__PURE__*/React.createElement(TimelineContext.Provider, {
    value: ctxValue
  }, children))), /*#__PURE__*/React.createElement(PlaybackBar, {
    time: displayTime,
    actualTime: time,
    duration: duration,
    playing: playing,
    onPlayPause: () => setPlaying(p => !p),
    onReset: () => {
      setTime(0);
    },
    onSeek: t => setTime(t),
    onHover: t => setHoverTime(t)
  }));
}

// ── Playback bar ────────────────────────────────────────────────────────────
// Play/pause, return-to-begin, scrub track, time display.
// Uses fixed-width time fields so layout doesn't thrash.

function PlaybackBar({
  time,
  duration,
  playing,
  onPlayPause,
  onReset,
  onSeek,
  onHover
}) {
  const trackRef = React.useRef(null);
  const [dragging, setDragging] = React.useState(false);
  const timeFromEvent = React.useCallback(e => {
    const rect = trackRef.current.getBoundingClientRect();
    const x = clamp((e.clientX - rect.left) / rect.width, 0, 1);
    return x * duration;
  }, [duration]);
  const onTrackMove = e => {
    if (!trackRef.current) return;
    const t = timeFromEvent(e);
    if (dragging) {
      onSeek(t);
    } else {
      onHover(t);
    }
  };
  const onTrackLeave = () => {
    if (!dragging) onHover(null);
  };
  const onTrackDown = e => {
    setDragging(true);
    const t = timeFromEvent(e);
    onSeek(t);
    onHover(null);
  };
  React.useEffect(() => {
    if (!dragging) return;
    const onUp = () => setDragging(false);
    const onMove = e => {
      if (!trackRef.current) return;
      const t = timeFromEvent(e);
      onSeek(t);
    };
    window.addEventListener('mouseup', onUp);
    window.addEventListener('mousemove', onMove);
    return () => {
      window.removeEventListener('mouseup', onUp);
      window.removeEventListener('mousemove', onMove);
    };
  }, [dragging, timeFromEvent, onSeek]);
  const pct = duration > 0 ? time / duration * 100 : 0;
  const fmt = t => {
    const total = Math.max(0, t);
    const m = Math.floor(total / 60);
    const s = Math.floor(total % 60);
    const cs = Math.floor(total * 100 % 100);
    return `${String(m).padStart(1, '0')}:${String(s).padStart(2, '0')}.${String(cs).padStart(2, '0')}`;
  };
  const mono = 'JetBrains Mono, ui-monospace, SFMono-Regular, monospace';
  return /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 12,
      padding: '8px 16px',
      background: 'rgba(20,20,20,0.92)',
      borderTop: '1px solid rgba(255,255,255,0.08)',
      width: '100%',
      maxWidth: 680,
      alignSelf: 'center',
      borderRadius: 8,
      color: '#f6f4ef',
      fontFamily: 'Inter, system-ui, sans-serif',
      userSelect: 'none',
      flexShrink: 0
    }
  }, /*#__PURE__*/React.createElement(IconButton, {
    onClick: onReset,
    title: "Return to start (0)"
  }, /*#__PURE__*/React.createElement("svg", {
    width: "14",
    height: "14",
    viewBox: "0 0 14 14",
    fill: "none"
  }, /*#__PURE__*/React.createElement("path", {
    d: "M3 2v10M12 2L5 7l7 5V2z",
    stroke: "currentColor",
    strokeWidth: "1.5",
    strokeLinejoin: "round",
    strokeLinecap: "round"
  }))), /*#__PURE__*/React.createElement(IconButton, {
    onClick: onPlayPause,
    title: "Play/pause (space)"
  }, playing ? /*#__PURE__*/React.createElement("svg", {
    width: "14",
    height: "14",
    viewBox: "0 0 14 14",
    fill: "none"
  }, /*#__PURE__*/React.createElement("rect", {
    x: "3",
    y: "2",
    width: "3",
    height: "10",
    fill: "currentColor"
  }), /*#__PURE__*/React.createElement("rect", {
    x: "8",
    y: "2",
    width: "3",
    height: "10",
    fill: "currentColor"
  })) : /*#__PURE__*/React.createElement("svg", {
    width: "14",
    height: "14",
    viewBox: "0 0 14 14",
    fill: "none"
  }, /*#__PURE__*/React.createElement("path", {
    d: "M3 2l9 5-9 5V2z",
    fill: "currentColor"
  }))), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: mono,
      fontSize: 12,
      fontVariantNumeric: 'tabular-nums',
      width: 64,
      textAlign: 'right',
      color: '#f6f4ef'
    }
  }, fmt(time)), /*#__PURE__*/React.createElement("div", {
    ref: trackRef,
    onMouseMove: onTrackMove,
    onMouseLeave: onTrackLeave,
    onMouseDown: onTrackDown,
    style: {
      flex: 1,
      height: 22,
      position: 'relative',
      cursor: 'pointer',
      display: 'flex',
      alignItems: 'center'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      left: 0,
      right: 0,
      height: 4,
      background: 'rgba(255,255,255,0.12)',
      borderRadius: 2
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      left: 0,
      width: `${pct}%`,
      height: 4,
      background: 'oklch(72% 0.12 250)',
      borderRadius: 2
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      left: `${pct}%`,
      top: '50%',
      width: 12,
      height: 12,
      marginLeft: -6,
      marginTop: -6,
      background: '#fff',
      borderRadius: 6,
      boxShadow: '0 2px 4px rgba(0,0,0,0.4)'
    }
  })), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: mono,
      fontSize: 12,
      fontVariantNumeric: 'tabular-nums',
      width: 64,
      textAlign: 'left',
      color: 'rgba(246,244,239,0.55)'
    }
  }, fmt(duration)));
}
function IconButton({
  children,
  onClick,
  title
}) {
  const [hover, setHover] = React.useState(false);
  return /*#__PURE__*/React.createElement("button", {
    onClick: onClick,
    title: title,
    onMouseEnter: () => setHover(true),
    onMouseLeave: () => setHover(false),
    style: {
      width: 28,
      height: 28,
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      background: hover ? 'rgba(255,255,255,0.12)' : 'rgba(255,255,255,0.04)',
      border: '1px solid rgba(255,255,255,0.1)',
      borderRadius: 6,
      color: '#f6f4ef',
      cursor: 'pointer',
      padding: 0,
      transition: 'background 120ms'
    }
  }, children);
}
Object.assign(window, {
  Easing,
  interpolate,
  animate,
  clamp,
  TimelineContext,
  useTime,
  useTimeline,
  Sprite,
  SpriteContext,
  useSprite,
  TextSprite,
  ImageSprite,
  RectSprite,
  Stage,
  PlaybackBar
});
})(); } catch (e) { __ds_ns.__errors.push({ path: "video/animations.jsx", error: String((e && e.message) || e) }); }

// video/scenes.jsx
try { (() => {
function _extends() { return _extends = Object.assign ? Object.assign.bind() : function (n) { for (var e = 1; e < arguments.length; e++) { var t = arguments[e]; for (var r in t) ({}).hasOwnProperty.call(t, r) && (n[r] = t[r]); } return n; }, _extends.apply(null, arguments); }
// MDDOAI product demo — scene components.
// Composes the animation starter (Stage/Sprite/Easing) into a 6-scene story:
// Title → Input (model) → Command → Output (yaml) → Result (pipeline) → Outro.
// All visuals use the MDDOAI brand foundations (violet, slate, Space Grotesk / Plex).

const C = {
  ink: '#0e0d15',
  inkPanel: '#16141f',
  inkBorder: 'rgba(255,255,255,0.10)',
  page: '#f8f8fb',
  card: '#ffffff',
  border: '#e4e4ee',
  borderSoft: '#f0f0f5',
  brand: '#684aeb',
  accent: '#a45eed',
  grad: 'linear-gradient(120deg,#684aeb 0%,#a45eed 100%)',
  success: '#15a36a',
  successBg: '#e3f6ec',
  warning: '#cf8a00',
  warningBg: '#fbf0d6',
  info: '#3a7bd5',
  tStrong: '#181820',
  tBody: '#3d3d4c',
  tMuted: '#74748d',
  tFaint: '#a1a1b8',
  onDark: '#f0f0f5',
  onDarkMut: '#9b9bb5'
};
const F = {
  display: "'Space Grotesk','IBM Plex Sans',sans-serif",
  sans: "'IBM Plex Sans',system-ui,sans-serif",
  mono: "'IBM Plex Mono',ui-monospace,monospace"
};
const LOGO = '../assets/logo/MDDOAI-transparent-2048.png';

// ── helpers ──────────────────────────────────────────────────────────────────

// Scene shell: full-frame background + crossfade + slow camera push.
function SceneFrame({
  bg = C.ink,
  zoomFrom = 1,
  zoomTo = 1,
  fade = 0.55,
  children
}) {
  const {
    localTime,
    duration
  } = useSprite();
  const fadeIn = clamp(localTime / fade, 0, 1);
  const fadeOut = clamp((duration - localTime) / fade, 0, 1);
  const opacity = Math.min(fadeIn, fadeOut);
  const p = duration > 0 ? clamp(localTime / duration, 0, 1) : 0;
  const scale = zoomFrom + (zoomTo - zoomFrom) * Easing.easeInOutSine(p);
  return /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      inset: 0,
      background: bg,
      opacity
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      inset: 0,
      transform: `scale(${scale})`,
      transformOrigin: 'center',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center'
    }
  }, children));
}
function Eyebrow({
  children,
  dark = false
}) {
  return /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 12,
      marginBottom: 22
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      width: 30,
      height: 4,
      borderRadius: 2,
      background: C.grad,
      display: 'inline-block'
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: F.sans,
      fontWeight: 600,
      fontSize: 19,
      letterSpacing: '0.14em',
      textTransform: 'uppercase',
      color: dark ? C.accent : C.brand
    }
  }, children));
}

// Typewriter span driven by absolute stage time.
function Typed({
  text,
  start,
  cps = 34,
  color = C.onDark,
  size = 30,
  weight = 500,
  caret = true
}) {
  const t = useTime();
  const elapsed = Math.max(0, t - start);
  const n = Math.min(text.length, Math.floor(elapsed * cps));
  const done = n >= text.length;
  const blink = Math.floor(t * 2) % 2 === 0;
  const showCaret = caret && (!done || blink);
  return /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: F.mono,
      fontSize: size,
      fontWeight: weight,
      color,
      whiteSpace: 'pre-wrap',
      lineHeight: 1.55
    }
  }, text.slice(0, n), /*#__PURE__*/React.createElement("span", {
    style: {
      display: 'inline-block',
      width: 11,
      height: size * 0.95,
      transform: 'translateY(3px)',
      marginLeft: 2,
      background: showCaret ? C.accent : 'transparent',
      borderRadius: 1
    }
  }));
}

// Lines that fade/slide in one after another, gated on absolute stage time.
function RevealLines({
  lines,
  start,
  interval = 0.5,
  riseFrom = 12,
  lineGap = 0
}) {
  const t = useTime();
  return lines.map((ln, i) => {
    const appear = start + i * interval;
    const o = clamp((t - appear) / 0.4, 0, 1);
    const ty = (1 - Easing.easeOutCubic(o)) * riseFrom;
    return /*#__PURE__*/React.createElement("div", {
      key: i,
      style: {
        opacity: o,
        transform: `translateY(${ty}px)`,
        marginTop: i === 0 ? 0 : lineGap,
        ...(ln.style || {})
      }
    }, ln.node);
  });
}

// A macOS-ish window chrome wrapper (dark terminal / file panels).
function Window({
  title,
  accent = false,
  width,
  children,
  dark = true
}) {
  return /*#__PURE__*/React.createElement("div", {
    style: {
      width,
      borderRadius: 14,
      overflow: 'hidden',
      background: dark ? C.inkPanel : C.card,
      border: `1px solid ${dark ? C.inkBorder : C.border}`,
      boxShadow: dark ? '0 40px 90px -30px rgba(0,0,0,0.7), 0 0 0 1px rgba(104,74,235,0.18)' : '0 36px 80px -34px rgba(24,24,40,0.4)'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      height: 48,
      display: 'flex',
      alignItems: 'center',
      gap: 9,
      padding: '0 18px',
      background: dark ? 'rgba(255,255,255,0.03)' : C.page,
      borderBottom: `1px solid ${dark ? C.inkBorder : C.border}`
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      width: 13,
      height: 13,
      borderRadius: '50%',
      background: '#ff5f57'
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      width: 13,
      height: 13,
      borderRadius: '50%',
      background: '#febc2e'
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      width: 13,
      height: 13,
      borderRadius: '50%',
      background: '#28c840'
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      marginLeft: 14,
      fontFamily: F.mono,
      fontSize: 16,
      color: dark ? C.onDarkMut : C.tMuted,
      display: 'flex',
      alignItems: 'center',
      gap: 8
    }
  }, accent && /*#__PURE__*/React.createElement("span", {
    style: {
      width: 8,
      height: 8,
      borderRadius: '50%',
      background: C.accent
    }
  }), title)), children);
}

// In-flow fade/rise block (normal layout flow — unlike absolute TextSprite).
function FadeUp({
  start,
  dur = 0.5,
  rise = 16,
  ease = Easing.easeOutCubic,
  style,
  children
}) {
  const t = useTime();
  const o = clamp((t - start) / dur, 0, 1);
  const e = ease(o);
  return /*#__PURE__*/React.createElement("div", {
    style: {
      opacity: o,
      transform: `translateY(${(1 - e) * rise}px)`,
      ...style
    }
  }, children);
}

// ── Scene 1 — Title ──────────────────────────────────────────────────────────
function TitleScene() {
  return /*#__PURE__*/React.createElement(Sprite, {
    start: 0,
    end: 5
  }, /*#__PURE__*/React.createElement(SceneFrame, {
    bg: C.ink,
    zoomFrom: 1.0,
    zoomTo: 1.05
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      width: 900,
      height: 900,
      borderRadius: '50%',
      background: 'radial-gradient(circle, rgba(104,74,235,0.30) 0%, rgba(104,74,235,0) 62%)',
      filter: 'blur(10px)'
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'relative',
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
      textAlign: 'center'
    }
  }, /*#__PURE__*/React.createElement(Sprite, {
    start: 0.2,
    end: 5
  }, /*#__PURE__*/React.createElement(TitleLogo, null)), /*#__PURE__*/React.createElement(FadeUp, {
    start: 0.85,
    rise: 20,
    style: {
      fontFamily: F.display,
      fontWeight: 700,
      fontSize: 110,
      letterSpacing: '-0.03em',
      color: C.onDark,
      lineHeight: 1.0
    }
  }, "MDDOAI"), /*#__PURE__*/React.createElement(FadeUp, {
    start: 1.5,
    rise: 16,
    style: {
      marginTop: 22,
      fontFamily: F.sans,
      fontWeight: 400,
      fontSize: 36,
      color: C.onDarkMut,
      letterSpacing: '0.01em'
    }
  }, "Model-Driven DevOps with Artificial Intelligence"))));
}
function TitleLogo() {
  const {
    localTime
  } = useSprite();
  const t = Easing.easeOutBack(clamp(localTime / 0.7, 0, 1));
  const o = clamp(localTime / 0.5, 0, 1);
  return /*#__PURE__*/React.createElement("img", {
    src: LOGO,
    alt: "",
    style: {
      width: 168,
      height: 168,
      marginBottom: 30,
      opacity: o,
      transform: `scale(${0.6 + 0.4 * t})`,
      filter: 'drop-shadow(0 12px 40px rgba(104,74,235,0.5))'
    }
  });
}

// ── Scene 2 — Input (architecture model) ─────────────────────────────────────
const NODES = [{
  id: 'gw',
  label: 'api-gateway',
  x: 300,
  y: 24,
  color: C.brand
}, {
  id: 'auth',
  label: 'auth-svc',
  x: 70,
  y: 210,
  color: C.accent
}, {
  id: 'ord',
  label: 'orders-svc',
  x: 300,
  y: 210,
  color: C.info
}, {
  id: 'db',
  label: 'orders-db',
  x: 530,
  y: 210,
  color: C.success
}];
function ModelDiagram() {
  const t = useTime();
  const NW = 200,
    NH = 64;
  const cx = n => n.x + NW / 2,
    cy = n => n.y + NH / 2;
  const edges = [['gw', 'auth'], ['gw', 'ord'], ['ord', 'db']];
  const byId = Object.fromEntries(NODES.map(n => [n.id, n]));
  return /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'relative',
      width: 730,
      height: 300,
      margin: '0 auto'
    }
  }, /*#__PURE__*/React.createElement("svg", {
    width: "730",
    height: "300",
    style: {
      position: 'absolute',
      inset: 0
    }
  }, edges.map(([a, b], i) => {
    const o = clamp((t - (5.4 + i * 0.18)) / 0.4, 0, 1);
    return /*#__PURE__*/React.createElement("line", {
      key: i,
      x1: cx(byId[a]),
      y1: cy(byId[a]) + 18,
      x2: cx(byId[b]),
      y2: cy(byId[b]) - 18,
      stroke: C.border,
      strokeWidth: "2.5",
      strokeDasharray: "6 6",
      style: {
        opacity: o
      }
    });
  })), NODES.map((n, i) => {
    const o = clamp((t - (5.0 + i * 0.16)) / 0.45, 0, 1);
    const s = 0.85 + 0.15 * Easing.easeOutBack(o);
    return /*#__PURE__*/React.createElement("div", {
      key: n.id,
      style: {
        position: 'absolute',
        left: n.x,
        top: n.y,
        width: NW,
        height: NH,
        opacity: o,
        transform: `scale(${s})`,
        transformOrigin: 'center',
        display: 'flex',
        alignItems: 'center',
        gap: 12,
        padding: '0 18px',
        background: C.card,
        border: `1px solid ${C.border}`,
        borderRadius: 10,
        boxShadow: '0 8px 20px -10px rgba(24,24,40,0.25)'
      }
    }, /*#__PURE__*/React.createElement("span", {
      style: {
        width: 12,
        height: 12,
        borderRadius: 3,
        background: n.color,
        flex: 'none'
      }
    }), /*#__PURE__*/React.createElement("span", {
      style: {
        fontFamily: F.mono,
        fontSize: 21,
        color: C.tStrong,
        fontWeight: 500
      }
    }, n.label));
  }));
}
function InputScene() {
  return /*#__PURE__*/React.createElement(Sprite, {
    start: 5,
    end: 10
  }, /*#__PURE__*/React.createElement(SceneFrame, {
    bg: C.page,
    zoomFrom: 1.0,
    zoomTo: 1.05
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      width: 1300,
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      alignSelf: 'flex-start',
      marginLeft: 60
    }
  }, /*#__PURE__*/React.createElement(Eyebrow, null, "Step 01 \u2014 Input"), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: F.display,
      fontWeight: 700,
      fontSize: 58,
      letterSpacing: '-0.03em',
      color: C.tStrong,
      marginBottom: 34,
      lineHeight: 1.05
    }
  }, "Start with your architecture model.")), /*#__PURE__*/React.createElement(Window, {
    title: "my-app.swarch",
    accent: true,
    dark: false,
    width: 900
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      padding: '40px 40px 46px'
    }
  }, /*#__PURE__*/React.createElement(ModelDiagram, null))))));
}

// ── Scene 3 — Command ────────────────────────────────────────────────────────
function CommandScene() {
  const check = txt => /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 14,
      fontFamily: F.mono,
      fontSize: 26,
      color: C.onDark
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      color: C.success,
      fontWeight: 700
    }
  }, "\u2713"), txt);
  return /*#__PURE__*/React.createElement(Sprite, {
    start: 10,
    end: 16.5
  }, /*#__PURE__*/React.createElement(SceneFrame, {
    bg: C.ink,
    zoomFrom: 1.0,
    zoomTo: 1.04
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      width: 1100,
      height: 700,
      borderRadius: '50%',
      background: 'radial-gradient(circle, rgba(104,74,235,0.16) 0%, rgba(104,74,235,0) 65%)'
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'relative',
      width: 1240
    }
  }, /*#__PURE__*/React.createElement(Eyebrow, {
    dark: true
  }, "Step 02 \u2014 One command"), /*#__PURE__*/React.createElement(Window, {
    title: "bash",
    width: 1240
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      padding: '34px 40px 40px',
      minHeight: 320
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      marginBottom: 22
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: F.mono,
      fontSize: 30,
      color: C.accent,
      marginRight: 14
    }
  }, "$"), /*#__PURE__*/React.createElement(Typed, {
    start: 10.5,
    cps: 26,
    size: 30,
    color: C.onDark,
    text: 'mddoai input/my-app.swarch gitlab output/'
  })), /*#__PURE__*/React.createElement(Sprite, {
    start: 13.2,
    end: 16.5
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      flexDirection: 'column',
      gap: 16,
      marginTop: 14
    }
  }, /*#__PURE__*/React.createElement(RevealLines, {
    start: 13.3,
    interval: 0.42,
    lineGap: 16,
    lines: [{
      node: check('Parsing architecture model')
    }, {
      node: check('Generating MD artefacts')
    }, {
      node: check('Applying Acceleo templates')
    }, {
      node: /*#__PURE__*/React.createElement("div", {
        style: {
          display: 'flex',
          alignItems: 'center',
          gap: 14,
          fontFamily: F.mono,
          fontSize: 26,
          color: C.onDark
        }
      }, /*#__PURE__*/React.createElement("span", {
        style: {
          color: C.success,
          fontWeight: 700
        }
      }, "\u2713"), /*#__PURE__*/React.createElement("span", null, "Generating "), /*#__PURE__*/React.createElement("span", {
        style: {
          color: '#fc6d26',
          fontWeight: 700
        }
      }, "GitLab"), /*#__PURE__*/React.createElement("span", null, " CI pipeline"))
    }, {
      node: /*#__PURE__*/React.createElement("div", {
        style: {
          display: 'flex',
          alignItems: 'center',
          gap: 14,
          fontFamily: F.mono,
          fontSize: 26,
          color: C.onDarkMut,
          marginTop: 4
        }
      }, /*#__PURE__*/React.createElement("span", {
        style: {
          color: C.accent
        }
      }, "\u2192"), " output/.gitlab-ci.yml written")
    }]
  }))))))));
}

// ── Scene 4 — Output (generated yaml) ────────────────────────────────────────
const YAML = [{
  t: 'stages:',
  c: C.info
}, {
  t: '  - build',
  c: C.onDark
}, {
  t: '  - test',
  c: C.onDark
}, {
  t: '  - deploy',
  c: C.onDark
}, {
  t: '',
  c: C.onDark
}, {
  t: 'build:',
  c: C.accent
}, {
  t: '  stage: build',
  c: C.onDarkMut
}, {
  t: '  script: mvn package',
  c: C.onDark
}, {
  t: '',
  c: C.onDark
}, {
  t: 'test:',
  c: C.accent
}, {
  t: '  stage: test',
  c: C.onDarkMut
}, {
  t: '  script: mvn verify',
  c: C.onDark
}, {
  t: '',
  c: C.onDark
}, {
  t: 'deploy:',
  c: C.accent
}, {
  t: '  stage: deploy',
  c: C.onDarkMut
}, {
  t: '  script: ./deploy.sh',
  c: C.onDark
}];
function OutputScene() {
  return /*#__PURE__*/React.createElement(Sprite, {
    start: 16,
    end: 22
  }, /*#__PURE__*/React.createElement(SceneFrame, {
    bg: C.page,
    zoomFrom: 1.0,
    zoomTo: 1.045
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      width: 1320,
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      alignSelf: 'flex-start',
      marginLeft: 40,
      display: 'flex',
      alignItems: 'flex-end',
      justifyContent: 'space-between',
      width: '100%'
    }
  }, /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement(Eyebrow, null, "Step 03 \u2014 Output"), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: F.display,
      fontWeight: 700,
      fontSize: 56,
      letterSpacing: '-0.03em',
      color: C.tStrong,
      marginBottom: 30,
      lineHeight: 1.05
    }
  }, "A ready-to-run pipeline, generated.")), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 16,
      marginBottom: 36,
      fontFamily: F.mono,
      fontSize: 22,
      color: C.tMuted
    }
  }, /*#__PURE__*/React.createElement("span", null, "my-app.swarch"), /*#__PURE__*/React.createElement("span", {
    style: {
      width: 54,
      height: 4,
      borderRadius: 2,
      background: C.grad
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      color: C.tStrong,
      fontWeight: 600
    }
  }, ".gitlab-ci.yml"))), /*#__PURE__*/React.createElement(Window, {
    title: ".gitlab-ci.yml",
    accent: true,
    width: 900
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      padding: '30px 44px 36px',
      display: 'flex',
      flexDirection: 'column',
      columnGap: 0
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'grid',
      gridTemplateColumns: '1fr 1fr',
      columnGap: 60,
      rowGap: 0
    }
  }, /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement(RevealLines, {
    start: 16.6,
    interval: 0.16,
    riseFrom: 6,
    lines: YAML.slice(0, 8).map(l => ({
      node: /*#__PURE__*/React.createElement("span", {
        style: {
          fontFamily: F.mono,
          fontSize: 23,
          color: l.c,
          lineHeight: 1.7,
          whiteSpace: 'pre',
          display: 'block',
          minHeight: 39
        }
      }, l.t || ' ')
    }))
  })), /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement(RevealLines, {
    start: 16.6 + 8 * 0.16,
    interval: 0.16,
    riseFrom: 6,
    lines: YAML.slice(8).map(l => ({
      node: /*#__PURE__*/React.createElement("span", {
        style: {
          fontFamily: F.mono,
          fontSize: 23,
          color: l.c,
          lineHeight: 1.7,
          whiteSpace: 'pre',
          display: 'block',
          minHeight: 39
        }
      }, l.t || ' ')
    }))
  }))))))));
}

// ── Scene 5 — Parallel pipelines + merge ────────────────────────────────────
const PIPELINES = [{
  label: 'api-gateway',
  color: '#684aeb',
  stageStarts: [22.7, 23.35, 23.95],
  rowAppear: 22.35
}, {
  label: 'auth-svc',
  color: '#a45eed',
  stageStarts: [23.0, 23.65, 24.25],
  rowAppear: 22.6
}, {
  label: 'orders-svc',
  color: '#3a7bd5',
  stageStarts: [23.3, 23.95, 24.55],
  rowAppear: 22.85
}, {
  label: 'orders-db',
  color: '#15a36a',
  stageStarts: [23.6, 24.25, 24.85],
  rowAppear: 23.1
}];
// Scene 5 timing
const MERGE_SCENE_START = 27;
const MERGE_ANIM_START = 27.7;
const MERGED_STAGE_S = [28.4, 29.1, 29.8];
const BANNER_TIME = 30.6;

// ── shared chip (animated stage state) ───────────────────────────────────────
function StageChip({
  name,
  starts,
  baseTime,
  minW = 100
}) {
  const t = useTime();
  const passed = t >= starts + 0.6;
  const running = t >= starts && !passed;
  const bg = passed ? C.successBg : running ? C.warningBg : 'rgba(255,255,255,0.06)';
  const bdr = passed ? C.success : running ? C.warning : 'rgba(255,255,255,0.11)';
  const txtC = passed ? C.success : running ? C.warning : 'rgba(200,200,220,0.38)';
  const appear = clamp((t - baseTime) / 0.3, 0, 1);
  const pop = passed ? 1 + 0.07 * Math.max(0, 1 - (t - (starts + 0.6)) / 0.3) : 1;
  return /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 8,
      padding: '8px 14px',
      borderRadius: 8,
      background: bg,
      border: `1.5px solid ${bdr}`,
      opacity: appear,
      transform: `scale(${pop})`,
      transition: 'none',
      minWidth: minW
    }
  }, passed ? /*#__PURE__*/React.createElement("span", {
    style: {
      fontSize: 13,
      color: C.success,
      fontWeight: 800
    }
  }, "\u2713") : running ? /*#__PURE__*/React.createElement("span", {
    style: {
      width: 9,
      height: 9,
      borderRadius: '50%',
      background: C.warning
    }
  }) : /*#__PURE__*/React.createElement("span", {
    style: {
      width: 9,
      height: 9,
      borderRadius: '50%',
      border: '2px solid rgba(200,200,220,0.25)'
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: F.mono,
      fontSize: 17,
      fontWeight: 600,
      color: txtC
    }
  }, name));
}

// animated pipeline row (Scene 5a)
function PipelineRow({
  label,
  color,
  stageStarts,
  rowAppear
}) {
  const t = useTime();
  const o = clamp((t - rowAppear) / 0.4, 0, 1);
  const ty = (1 - Easing.easeOutCubic(o)) * 16;
  return /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      opacity: o,
      transform: `translateY(${ty}px)`
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 10,
      width: 236,
      flex: 'none'
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      width: 10,
      height: 10,
      borderRadius: 3,
      background: color,
      flex: 'none'
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: F.mono,
      fontSize: 20,
      color: C.onDark,
      fontWeight: 500
    }
  }, label)), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center'
    }
  }, ['build', 'test', 'deploy'].map((name, i) => /*#__PURE__*/React.createElement(React.Fragment, {
    key: name
  }, i > 0 && /*#__PURE__*/React.createElement("div", {
    style: {
      width: 32,
      height: 3,
      background: 'rgba(255,255,255,0.09)',
      position: 'relative',
      borderRadius: 2
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      inset: 0,
      width: `${clamp((t - stageStarts[i - 1] - 0.6) / 0.45, 0, 1) * 100}%`,
      background: C.grad,
      borderRadius: 2
    }
  })), /*#__PURE__*/React.createElement(StageChip, {
    name: name,
    starts: stageStarts[i],
    baseTime: rowAppear + i * 0.1
  })))));
}

// ── Scene 5a — Parallel pipelines ────────────────────────────────────────────
function PipelinesScene() {
  return /*#__PURE__*/React.createElement(Sprite, {
    start: 22,
    end: 27
  }, /*#__PURE__*/React.createElement(SceneFrame, {
    bg: C.ink,
    zoomFrom: 1.0,
    zoomTo: 1.06
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      width: 1100,
      height: 800,
      borderRadius: '50%',
      background: 'radial-gradient(circle,rgba(104,74,235,0.12) 0%,rgba(104,74,235,0) 58%)'
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'relative',
      width: 1240,
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      alignSelf: 'flex-start',
      marginBottom: 44
    }
  }, /*#__PURE__*/React.createElement(Eyebrow, {
    dark: true
  }, "Step 04 \u2014 Pipelines"), /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: F.display,
      fontWeight: 700,
      fontSize: 54,
      letterSpacing: '-0.03em',
      color: C.onDark,
      lineHeight: 1.05
    }
  }, "Each component. Its own pipeline.")), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      flexDirection: 'column',
      gap: 28,
      alignSelf: 'flex-start'
    }
  }, PIPELINES.map(p => /*#__PURE__*/React.createElement(PipelineRow, _extends({
    key: p.label
  }, p)))))));
}

// ── Scene 5b — Zoom out, merge, result ───────────────────────────────────────
// static row (all-passed) for left panel
function StaticRow({
  label,
  color,
  appear
}) {
  const t = useTime();
  const o = clamp((t - appear) / 0.4, 0, 1);
  const ty = (1 - Easing.easeOutCubic(o)) * 12;
  return /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      opacity: o,
      transform: `translateY(${ty}px)`
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 8,
      width: 162,
      flex: 'none'
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      width: 9,
      height: 9,
      borderRadius: 3,
      background: color,
      flex: 'none'
    }
  }), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: F.mono,
      fontSize: 16,
      color: C.onDark,
      fontWeight: 500
    }
  }, label)), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center'
    }
  }, ['build', 'test', 'deploy'].map((name, i) => /*#__PURE__*/React.createElement(React.Fragment, {
    key: name
  }, i > 0 && /*#__PURE__*/React.createElement("div", {
    style: {
      width: 18,
      height: 3,
      background: C.grad,
      borderRadius: 2
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 5,
      padding: '5px 10px',
      borderRadius: 6,
      background: C.successBg,
      border: `1.5px solid ${C.success}`,
      minWidth: 76
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      fontSize: 11,
      color: C.success,
      fontWeight: 800
    }
  }, "\u2713"), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: F.mono,
      fontSize: 14,
      fontWeight: 600,
      color: C.success
    }
  }, name))))));
}
function MergeResultScene() {
  const t = useTime();
  const linesP = Easing.easeOutCubic(clamp((t - MERGE_ANIM_START) / 0.7, 0, 1));
  const rightP = Easing.easeOutCubic(clamp((t - MERGE_ANIM_START - 0.15) / 0.5, 0, 1));
  const bannerO = clamp((t - BANNER_TIME) / 0.5, 0, 1);
  const bannerS = 0.92 + 0.08 * Easing.easeOutBack(bannerO);
  return /*#__PURE__*/React.createElement(Sprite, {
    start: MERGE_SCENE_START,
    end: 33
  }, /*#__PURE__*/React.createElement(SceneFrame, {
    bg: C.ink,
    zoomFrom: 1.08,
    zoomTo: 1.0
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      width: 1200,
      height: 900,
      borderRadius: '50%',
      background: 'radial-gradient(circle,rgba(104,74,235,0.09) 0%,rgba(104,74,235,0) 56%)'
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'relative',
      width: 1560,
      display: 'flex',
      alignItems: 'center'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      flex: '0 0 560px',
      display: 'flex',
      flexDirection: 'column',
      gap: 26
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: F.sans,
      fontWeight: 600,
      fontSize: 12,
      textTransform: 'uppercase',
      letterSpacing: '0.12em',
      color: C.onDarkMut,
      marginBottom: 4
    }
  }, "Component pipelines"), PIPELINES.map((p, i) => /*#__PURE__*/React.createElement(StaticRow, {
    key: p.label,
    label: p.label,
    color: p.color,
    appear: MERGE_SCENE_START + 0.1 + i * 0.13
  }))), /*#__PURE__*/React.createElement("div", {
    style: {
      flex: '0 0 210px',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center'
    }
  }, /*#__PURE__*/React.createElement("svg", {
    width: 210,
    height: 260,
    style: {
      overflow: 'visible'
    }
  }, /*#__PURE__*/React.createElement("defs", null, /*#__PURE__*/React.createElement("linearGradient", {
    id: "mg2",
    x1: "0",
    y1: "0",
    x2: "1",
    y2: "0"
  }, /*#__PURE__*/React.createElement("stop", {
    offset: "0%",
    stopColor: "#684aeb"
  }), /*#__PURE__*/React.createElement("stop", {
    offset: "100%",
    stopColor: "#a45eed"
  }))), [55, 88, 172, 205].map((y, i) => /*#__PURE__*/React.createElement("line", {
    key: i,
    x1: 0,
    y1: y,
    x2: 86 * linesP,
    y2: 130,
    stroke: PIPELINES[i].color,
    strokeWidth: 2.5,
    strokeLinecap: "round",
    opacity: 0.82
  })), /*#__PURE__*/React.createElement("circle", {
    cx: 86,
    cy: 130,
    r: 9 * Easing.easeOutBack(clamp((t - MERGE_ANIM_START - 0.05) / 0.3, 0, 1)),
    fill: "#684aeb"
  }), /*#__PURE__*/React.createElement("line", {
    x1: 96,
    y1: 130,
    x2: 96 + 90 * Easing.easeOutCubic(clamp((t - MERGE_ANIM_START - 0.22) / 0.45, 0, 1)),
    y2: 130,
    stroke: "url(#mg2)",
    strokeWidth: 3.5,
    strokeLinecap: "round"
  }), linesP > 0.8 && /*#__PURE__*/React.createElement(React.Fragment, null, /*#__PURE__*/React.createElement("line", {
    x1: 180,
    y1: 123,
    x2: 192,
    y2: 130,
    stroke: "#a45eed",
    strokeWidth: 3,
    strokeLinecap: "round"
  }), /*#__PURE__*/React.createElement("line", {
    x1: 180,
    y1: 137,
    x2: 192,
    y2: 130,
    stroke: "#a45eed",
    strokeWidth: 3,
    strokeLinecap: "round"
  })))), /*#__PURE__*/React.createElement("div", {
    style: {
      flex: '0 0 580px',
      display: 'flex',
      flexDirection: 'column',
      gap: 28,
      opacity: rightP,
      transform: `translateX(${(1 - rightP) * 20}px)`
    }
  }, /*#__PURE__*/React.createElement("div", null, /*#__PURE__*/React.createElement("div", {
    style: {
      fontFamily: F.sans,
      fontWeight: 600,
      fontSize: 12,
      textTransform: 'uppercase',
      letterSpacing: '0.12em',
      color: C.onDarkMut,
      marginBottom: 14
    }
  }, "Merged result"), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 10,
      marginBottom: 18
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      display: 'inline-flex',
      alignItems: 'center',
      gap: 6,
      padding: '5px 12px',
      borderRadius: 6,
      background: 'rgba(104,74,235,0.22)',
      border: '1px solid rgba(164,94,237,0.45)',
      fontFamily: F.sans,
      fontSize: 13,
      color: '#a45eed',
      fontWeight: 700
    }
  }, "merged"), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: F.mono,
      fontSize: 13,
      color: C.onDarkMut
    }
  }, "my-app.gitlab-ci.yml")), /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center'
    }
  }, ['provision cluster', 'e2e tests', 'production'].map((name, i) => /*#__PURE__*/React.createElement(React.Fragment, {
    key: name
  }, i > 0 && /*#__PURE__*/React.createElement("div", {
    style: {
      width: 28,
      height: 3,
      background: 'rgba(255,255,255,0.09)',
      position: 'relative',
      borderRadius: 2
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      inset: 0,
      width: `${clamp((t - MERGED_STAGE_S[i - 1] - 0.6) / 0.4, 0, 1) * 100}%`,
      background: C.grad,
      borderRadius: 2
    }
  })), /*#__PURE__*/React.createElement(StageChip, {
    name: name,
    starts: MERGED_STAGE_S[i],
    baseTime: MERGE_ANIM_START + 0.65 + i * 0.1,
    minW: 162
  }))))), /*#__PURE__*/React.createElement("div", {
    style: {
      opacity: bannerO,
      transform: `scale(${bannerS})`,
      display: 'flex',
      flexDirection: 'column',
      gap: 10,
      padding: '20px 24px',
      background: C.inkPanel,
      border: '1px solid rgba(21,163,106,0.35)',
      borderRadius: 14,
      boxShadow: '0 20px 50px -20px rgba(21,163,106,0.55)'
    }
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      display: 'flex',
      alignItems: 'center',
      gap: 12
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      display: 'inline-flex',
      alignItems: 'center',
      gap: 8,
      padding: '6px 14px',
      borderRadius: 999,
      background: C.successBg,
      color: C.success,
      fontFamily: F.sans,
      fontWeight: 700,
      fontSize: 18
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      width: 8,
      height: 8,
      borderRadius: '50%',
      background: C.success
    }
  }), " passed"), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: F.display,
      fontWeight: 700,
      fontSize: 27,
      color: C.onDark,
      letterSpacing: '-0.02em'
    }
  }, "DevOps Successful")), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: F.mono,
      fontSize: 16,
      color: C.onDarkMut
    }
  }, "4 pipelines \xB7 merged \xB7 52s"))))));
}

// ── Scene 6 — Outro ──────────────────────────────────────────────────────────
function OutroScene() {
  return /*#__PURE__*/React.createElement(Sprite, {
    start: 33,
    end: 37
  }, /*#__PURE__*/React.createElement(SceneFrame, {
    bg: C.ink,
    zoomFrom: 1.04,
    zoomTo: 1.0
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'absolute',
      width: 1000,
      height: 1000,
      borderRadius: '50%',
      background: 'radial-gradient(circle, rgba(104,74,235,0.26) 0%, rgba(104,74,235,0) 60%)'
    }
  }), /*#__PURE__*/React.createElement("div", {
    style: {
      position: 'relative',
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
      textAlign: 'center'
    }
  }, /*#__PURE__*/React.createElement(Sprite, {
    start: 33.2,
    end: 37
  }, /*#__PURE__*/React.createElement(OutroLogo, null)), /*#__PURE__*/React.createElement(FadeUp, {
    start: 33.5,
    rise: 18,
    style: {
      fontFamily: F.display,
      fontWeight: 700,
      fontSize: 64,
      letterSpacing: '-0.03em',
      color: C.onDark,
      lineHeight: 1.05
    }
  }, "One Architecture model. Any pipeline."), /*#__PURE__*/React.createElement(Sprite, {
    start: 34.0,
    end: 37
  }, /*#__PURE__*/React.createElement("div", {
    style: {
      marginTop: 30,
      display: 'inline-flex',
      alignItems: 'center',
      gap: 14,
      padding: '16px 26px',
      borderRadius: 12,
      background: C.inkPanel,
      border: `1px solid ${C.inkBorder}`
    }
  }, /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: F.mono,
      fontSize: 26,
      color: C.accent
    }
  }, "$"), /*#__PURE__*/React.createElement("span", {
    style: {
      fontFamily: F.mono,
      fontSize: 20,
      color: C.onDark
    }
  }, 'mddoai input/<any>.swarch <any platform> output/*pipeline*')), /*#__PURE__*/React.createElement("div", {
    style: {
      marginTop: 26,
      fontFamily: F.sans,
      fontSize: 22,
      color: C.onDarkMut,
      letterSpacing: '0.02em'
    }
  }, "github.com/modeldrivendevopsai")))));
}
function OutroLogo() {
  const {
    localTime
  } = useSprite();
  const o = clamp(localTime / 0.5, 0, 1);
  return /*#__PURE__*/React.createElement("img", {
    src: LOGO,
    alt: "",
    style: {
      width: 120,
      height: 120,
      marginBottom: 30,
      opacity: o,
      filter: 'drop-shadow(0 10px 34px rgba(104,74,235,0.5))'
    }
  });
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
  TitleScene,
  InputScene,
  CommandScene,
  OutputScene,
  PipelinesScene,
  MergeResultScene,
  OutroScene,
  TimestampLabeler
});
})(); } catch (e) { __ds_ns.__errors.push({ path: "video/scenes.jsx", error: String((e && e.message) || e) }); }

// video/tweaks-panel.jsx
try { (() => {
// @ds-adherence-ignore -- omelette starter scaffold (raw elements/hex/px by design)

/* BEGIN USAGE */
// tweaks-panel.jsx
// Reusable Tweaks shell + form-control helpers.
// Exports (to window): useTweaks, TweaksPanel, TweakSection, TweakRow, TweakSlider,
//   TweakToggle, TweakRadio, TweakSelect, TweakText, TweakNumber, TweakColor, TweakButton.
//
// Owns the host protocol (listens for __activate_edit_mode / __deactivate_edit_mode,
// posts __edit_mode_available / __edit_mode_set_keys / __edit_mode_dismissed) so
// individual prototypes don't re-roll it. Ships a consistent set of controls so you
// don't hand-draw <input type="range">, segmented radios, steppers, etc.
//
// Usage (in an HTML file that loads React + Babel):
//
//   const TWEAK_DEFAULTS = /*EDITMODE-BEGIN*/{
//     "primaryColor": "#D97757",
//     "palette": ["#D97757", "#29261b", "#f6f4ef"],
//     "fontSize": 16,
//     "density": "regular",
//     "dark": false
//   }/*EDITMODE-END*/;
//
//   function App() {
//     const [t, setTweak] = useTweaks(TWEAK_DEFAULTS);
//     return (
//       <div style={{ fontSize: t.fontSize, color: t.primaryColor }}>
//         Hello
//         <TweaksPanel>
//           <TweakSection label="Typography" />
//           <TweakSlider label="Font size" value={t.fontSize} min={10} max={32} unit="px"
//                        onChange={(v) => setTweak('fontSize', v)} />
//           <TweakRadio  label="Density" value={t.density}
//                        options={['compact', 'regular', 'comfy']}
//                        onChange={(v) => setTweak('density', v)} />
//           <TweakSection label="Theme" />
//           <TweakColor  label="Primary" value={t.primaryColor}
//                        options={['#D97757', '#2A6FDB', '#1F8A5B', '#7A5AE0']}
//                        onChange={(v) => setTweak('primaryColor', v)} />
//           <TweakColor  label="Palette" value={t.palette}
//                        options={[['#D97757', '#29261b', '#f6f4ef'],
//                                  ['#475569', '#0f172a', '#f1f5f9']]}
//                        onChange={(v) => setTweak('palette', v)} />
//           <TweakToggle label="Dark mode" value={t.dark}
//                        onChange={(v) => setTweak('dark', v)} />
//         </TweaksPanel>
//       </div>
//     );
//   }
//
// TweakRadio is the segmented control for 2–3 short options (auto-falls-back to
// TweakSelect past ~16/~10 chars per label); reach for TweakSelect directly when
// options are many or long. For color tweaks always curate 3-4 options rather than
// a free picker; an option can also be a whole 2–5 color palette (the stored value
// is the array). The Tweak* controls are a floor, not a ceiling — build custom
// controls inside the panel if a tweak calls for UI they don't cover.
/* END USAGE */
// ─────────────────────────────────────────────────────────────────────────────

const __TWEAKS_STYLE = `
  .twk-panel{position:fixed;right:16px;bottom:16px;z-index:2147483646;width:280px;
    max-height:calc(100vh - 32px);display:flex;flex-direction:column;
    transform:scale(var(--dc-inv-zoom,1));transform-origin:bottom right;
    background:rgba(250,249,247,.78);color:#29261b;
    -webkit-backdrop-filter:blur(24px) saturate(160%);backdrop-filter:blur(24px) saturate(160%);
    border:.5px solid rgba(255,255,255,.6);border-radius:14px;
    box-shadow:0 1px 0 rgba(255,255,255,.5) inset,0 12px 40px rgba(0,0,0,.18);
    font:11.5px/1.4 ui-sans-serif,system-ui,-apple-system,sans-serif;overflow:hidden}
  .twk-hd{display:flex;align-items:center;justify-content:space-between;
    padding:10px 8px 10px 14px;cursor:move;user-select:none}
  .twk-hd b{font-size:12px;font-weight:600;letter-spacing:.01em}
  .twk-x{appearance:none;border:0;background:transparent;color:rgba(41,38,27,.55);
    width:22px;height:22px;border-radius:6px;cursor:default;font-size:13px;line-height:1}
  .twk-x:hover{background:rgba(0,0,0,.06);color:#29261b}
  .twk-body{padding:2px 14px 14px;display:flex;flex-direction:column;gap:10px;
    overflow-y:auto;overflow-x:hidden;min-height:0;
    scrollbar-width:thin;scrollbar-color:rgba(0,0,0,.15) transparent}
  .twk-body::-webkit-scrollbar{width:8px}
  .twk-body::-webkit-scrollbar-track{background:transparent;margin:2px}
  .twk-body::-webkit-scrollbar-thumb{background:rgba(0,0,0,.15);border-radius:4px;
    border:2px solid transparent;background-clip:content-box}
  .twk-body::-webkit-scrollbar-thumb:hover{background:rgba(0,0,0,.25);
    border:2px solid transparent;background-clip:content-box}
  .twk-row{display:flex;flex-direction:column;gap:5px}
  .twk-row-h{flex-direction:row;align-items:center;justify-content:space-between;gap:10px}
  .twk-lbl{display:flex;justify-content:space-between;align-items:baseline;
    color:rgba(41,38,27,.72)}
  .twk-lbl>span:first-child{font-weight:500}
  .twk-val{color:rgba(41,38,27,.5);font-variant-numeric:tabular-nums}

  .twk-sect{font-size:10px;font-weight:600;letter-spacing:.06em;text-transform:uppercase;
    color:rgba(41,38,27,.45);padding:10px 0 0}
  .twk-sect:first-child{padding-top:0}

  .twk-field{appearance:none;box-sizing:border-box;width:100%;min-width:0;height:26px;padding:0 8px;
    border:.5px solid rgba(0,0,0,.1);border-radius:7px;
    background:rgba(255,255,255,.6);color:inherit;font:inherit;outline:none}
  .twk-field:focus{border-color:rgba(0,0,0,.25);background:rgba(255,255,255,.85)}
  select.twk-field{padding-right:22px;
    background-image:url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='10' height='6' viewBox='0 0 10 6'><path fill='rgba(0,0,0,.5)' d='M0 0h10L5 6z'/></svg>");
    background-repeat:no-repeat;background-position:right 8px center}

  .twk-slider{appearance:none;-webkit-appearance:none;width:100%;height:4px;margin:6px 0;
    border-radius:999px;background:rgba(0,0,0,.12);outline:none}
  .twk-slider::-webkit-slider-thumb{-webkit-appearance:none;appearance:none;
    width:14px;height:14px;border-radius:50%;background:#fff;
    border:.5px solid rgba(0,0,0,.12);box-shadow:0 1px 3px rgba(0,0,0,.2);cursor:default}
  .twk-slider::-moz-range-thumb{width:14px;height:14px;border-radius:50%;
    background:#fff;border:.5px solid rgba(0,0,0,.12);box-shadow:0 1px 3px rgba(0,0,0,.2);cursor:default}

  .twk-seg{position:relative;display:flex;padding:2px;border-radius:8px;
    background:rgba(0,0,0,.06);user-select:none}
  .twk-seg-thumb{position:absolute;top:2px;bottom:2px;border-radius:6px;
    background:rgba(255,255,255,.9);box-shadow:0 1px 2px rgba(0,0,0,.12);
    transition:left .15s cubic-bezier(.3,.7,.4,1),width .15s}
  .twk-seg.dragging .twk-seg-thumb{transition:none}
  .twk-seg button{appearance:none;position:relative;z-index:1;flex:1;border:0;
    background:transparent;color:inherit;font:inherit;font-weight:500;min-height:22px;
    border-radius:6px;cursor:default;padding:4px 6px;line-height:1.2;
    overflow-wrap:anywhere}

  .twk-toggle{position:relative;width:32px;height:18px;border:0;border-radius:999px;
    background:rgba(0,0,0,.15);transition:background .15s;cursor:default;padding:0}
  .twk-toggle[data-on="1"]{background:#34c759}
  .twk-toggle i{position:absolute;top:2px;left:2px;width:14px;height:14px;border-radius:50%;
    background:#fff;box-shadow:0 1px 2px rgba(0,0,0,.25);transition:transform .15s}
  .twk-toggle[data-on="1"] i{transform:translateX(14px)}

  .twk-num{display:flex;align-items:center;box-sizing:border-box;min-width:0;height:26px;padding:0 0 0 8px;
    border:.5px solid rgba(0,0,0,.1);border-radius:7px;background:rgba(255,255,255,.6)}
  .twk-num-lbl{font-weight:500;color:rgba(41,38,27,.6);cursor:ew-resize;
    user-select:none;padding-right:8px}
  .twk-num input{flex:1;min-width:0;height:100%;border:0;background:transparent;
    font:inherit;font-variant-numeric:tabular-nums;text-align:right;padding:0 8px 0 0;
    outline:none;color:inherit;-moz-appearance:textfield}
  .twk-num input::-webkit-inner-spin-button,.twk-num input::-webkit-outer-spin-button{
    -webkit-appearance:none;margin:0}
  .twk-num-unit{padding-right:8px;color:rgba(41,38,27,.45)}

  .twk-btn{appearance:none;height:26px;padding:0 12px;border:0;border-radius:7px;
    background:rgba(0,0,0,.78);color:#fff;font:inherit;font-weight:500;cursor:default}
  .twk-btn:hover{background:rgba(0,0,0,.88)}
  .twk-btn.secondary{background:rgba(0,0,0,.06);color:inherit}
  .twk-btn.secondary:hover{background:rgba(0,0,0,.1)}

  .twk-swatch{appearance:none;-webkit-appearance:none;width:56px;height:22px;
    border:.5px solid rgba(0,0,0,.1);border-radius:6px;padding:0;cursor:default;
    background:transparent;flex-shrink:0}
  .twk-swatch::-webkit-color-swatch-wrapper{padding:0}
  .twk-swatch::-webkit-color-swatch{border:0;border-radius:5.5px}
  .twk-swatch::-moz-color-swatch{border:0;border-radius:5.5px}

  .twk-chips{display:flex;gap:6px}
  .twk-chip{position:relative;appearance:none;flex:1;min-width:0;height:46px;
    padding:0;border:0;border-radius:6px;overflow:hidden;cursor:default;
    box-shadow:0 0 0 .5px rgba(0,0,0,.12),0 1px 2px rgba(0,0,0,.06);
    transition:transform .12s cubic-bezier(.3,.7,.4,1),box-shadow .12s}
  .twk-chip:hover{transform:translateY(-1px);
    box-shadow:0 0 0 .5px rgba(0,0,0,.18),0 4px 10px rgba(0,0,0,.12)}
  .twk-chip[data-on="1"]{box-shadow:0 0 0 1.5px rgba(0,0,0,.85),
    0 2px 6px rgba(0,0,0,.15)}
  .twk-chip>span{position:absolute;top:0;bottom:0;right:0;width:34%;
    display:flex;flex-direction:column;box-shadow:-1px 0 0 rgba(0,0,0,.1)}
  .twk-chip>span>i{flex:1;box-shadow:0 -1px 0 rgba(0,0,0,.1)}
  .twk-chip>span>i:first-child{box-shadow:none}
  .twk-chip svg{position:absolute;top:6px;left:6px;width:13px;height:13px;
    filter:drop-shadow(0 1px 1px rgba(0,0,0,.3))}
`;

// ── useTweaks ───────────────────────────────────────────────────────────────
// Single source of truth for tweak values. setTweak persists via the host
// (__edit_mode_set_keys → host rewrites the EDITMODE block on disk).
function useTweaks(defaults) {
  const [values, setValues] = React.useState(defaults);
  // Accepts either setTweak('key', value) or setTweak({ key: value, ... }) so a
  // useState-style call doesn't write a "[object Object]" key into the persisted
  // JSON block.
  const setTweak = React.useCallback((keyOrEdits, val) => {
    const edits = typeof keyOrEdits === 'object' && keyOrEdits !== null ? keyOrEdits : {
      [keyOrEdits]: val
    };
    setValues(prev => ({
      ...prev,
      ...edits
    }));
    window.parent.postMessage({
      type: '__edit_mode_set_keys',
      edits
    }, '*');
    // Same-window signal so in-page listeners (deck-stage rail thumbnails)
    // can react — the parent message only reaches the host, not peers.
    window.dispatchEvent(new CustomEvent('tweakchange', {
      detail: edits
    }));
  }, []);
  return [values, setTweak];
}

// ── TweaksPanel ─────────────────────────────────────────────────────────────
// Floating shell. Registers the protocol listener BEFORE announcing
// availability — if the announce ran first, the host's activate could land
// before our handler exists and the toolbar toggle would silently no-op.
// The close button posts __edit_mode_dismissed so the host's toolbar toggle
// flips off in lockstep; the host echoes __deactivate_edit_mode back which
// is what actually hides the panel.
function TweaksPanel({
  title = 'Tweaks',
  children
}) {
  const [open, setOpen] = React.useState(false);
  const dragRef = React.useRef(null);
  const offsetRef = React.useRef({
    x: 16,
    y: 16
  });
  const PAD = 16;
  const clampToViewport = React.useCallback(() => {
    const panel = dragRef.current;
    if (!panel) return;
    const w = panel.offsetWidth,
      h = panel.offsetHeight;
    const maxRight = Math.max(PAD, window.innerWidth - w - PAD);
    const maxBottom = Math.max(PAD, window.innerHeight - h - PAD);
    offsetRef.current = {
      x: Math.min(maxRight, Math.max(PAD, offsetRef.current.x)),
      y: Math.min(maxBottom, Math.max(PAD, offsetRef.current.y))
    };
    panel.style.right = offsetRef.current.x + 'px';
    panel.style.bottom = offsetRef.current.y + 'px';
  }, []);
  React.useEffect(() => {
    if (!open) return;
    clampToViewport();
    if (typeof ResizeObserver === 'undefined') {
      window.addEventListener('resize', clampToViewport);
      return () => window.removeEventListener('resize', clampToViewport);
    }
    const ro = new ResizeObserver(clampToViewport);
    ro.observe(document.documentElement);
    return () => ro.disconnect();
  }, [open, clampToViewport]);
  React.useEffect(() => {
    const onMsg = e => {
      const t = e?.data?.type;
      if (t === '__activate_edit_mode') setOpen(true);else if (t === '__deactivate_edit_mode') setOpen(false);
    };
    window.addEventListener('message', onMsg);
    window.parent.postMessage({
      type: '__edit_mode_available'
    }, '*');
    return () => window.removeEventListener('message', onMsg);
  }, []);
  const dismiss = () => {
    setOpen(false);
    window.parent.postMessage({
      type: '__edit_mode_dismissed'
    }, '*');
  };
  const onDragStart = e => {
    const panel = dragRef.current;
    if (!panel) return;
    const r = panel.getBoundingClientRect();
    const sx = e.clientX,
      sy = e.clientY;
    const startRight = window.innerWidth - r.right;
    const startBottom = window.innerHeight - r.bottom;
    const move = ev => {
      offsetRef.current = {
        x: startRight - (ev.clientX - sx),
        y: startBottom - (ev.clientY - sy)
      };
      clampToViewport();
    };
    const up = () => {
      window.removeEventListener('mousemove', move);
      window.removeEventListener('mouseup', up);
    };
    window.addEventListener('mousemove', move);
    window.addEventListener('mouseup', up);
  };
  if (!open) return null;
  return /*#__PURE__*/React.createElement(React.Fragment, null, /*#__PURE__*/React.createElement("style", null, __TWEAKS_STYLE), /*#__PURE__*/React.createElement("div", {
    ref: dragRef,
    className: "twk-panel",
    "data-omelette-chrome": "",
    style: {
      right: offsetRef.current.x,
      bottom: offsetRef.current.y
    }
  }, /*#__PURE__*/React.createElement("div", {
    className: "twk-hd",
    onMouseDown: onDragStart
  }, /*#__PURE__*/React.createElement("b", null, title), /*#__PURE__*/React.createElement("button", {
    className: "twk-x",
    "aria-label": "Close tweaks",
    onMouseDown: e => e.stopPropagation(),
    onClick: dismiss
  }, "\u2715")), /*#__PURE__*/React.createElement("div", {
    className: "twk-body"
  }, children)));
}

// ── Layout helpers ──────────────────────────────────────────────────────────

function TweakSection({
  label,
  children
}) {
  return /*#__PURE__*/React.createElement(React.Fragment, null, /*#__PURE__*/React.createElement("div", {
    className: "twk-sect"
  }, label), children);
}
function TweakRow({
  label,
  value,
  children,
  inline = false
}) {
  return /*#__PURE__*/React.createElement("div", {
    className: inline ? 'twk-row twk-row-h' : 'twk-row'
  }, /*#__PURE__*/React.createElement("div", {
    className: "twk-lbl"
  }, /*#__PURE__*/React.createElement("span", null, label), value != null && /*#__PURE__*/React.createElement("span", {
    className: "twk-val"
  }, value)), children);
}

// ── Controls ────────────────────────────────────────────────────────────────

function TweakSlider({
  label,
  value,
  min = 0,
  max = 100,
  step = 1,
  unit = '',
  onChange
}) {
  return /*#__PURE__*/React.createElement(TweakRow, {
    label: label,
    value: `${value}${unit}`
  }, /*#__PURE__*/React.createElement("input", {
    type: "range",
    className: "twk-slider",
    min: min,
    max: max,
    step: step,
    value: value,
    onChange: e => onChange(Number(e.target.value))
  }));
}
function TweakToggle({
  label,
  value,
  onChange
}) {
  return /*#__PURE__*/React.createElement("div", {
    className: "twk-row twk-row-h"
  }, /*#__PURE__*/React.createElement("div", {
    className: "twk-lbl"
  }, /*#__PURE__*/React.createElement("span", null, label)), /*#__PURE__*/React.createElement("button", {
    type: "button",
    className: "twk-toggle",
    "data-on": value ? '1' : '0',
    role: "switch",
    "aria-checked": !!value,
    onClick: () => onChange(!value)
  }, /*#__PURE__*/React.createElement("i", null)));
}
function TweakRadio({
  label,
  value,
  options,
  onChange
}) {
  const trackRef = React.useRef(null);
  const [dragging, setDragging] = React.useState(false);
  // The active value is read by pointer-move handlers attached for the lifetime
  // of a drag — ref it so a stale closure doesn't fire onChange for every move.
  const valueRef = React.useRef(value);
  valueRef.current = value;

  // Segments wrap mid-word once per-segment width runs out. The track is
  // ~248px (280 panel − 28 body pad − 4 seg pad), each button loses 12px
  // to its own padding, and 11.5px system-ui averages ~6.3px/char — so 2
  // options fit ~16 chars each, 3 fit ~10. Past that (or >3 options), fall
  // back to a dropdown rather than wrap.
  const labelLen = o => String(typeof o === 'object' ? o.label : o).length;
  const maxLen = options.reduce((m, o) => Math.max(m, labelLen(o)), 0);
  const fitsAsSegments = maxLen <= ({
    2: 16,
    3: 10
  }[options.length] ?? 0);
  if (!fitsAsSegments) {
    // <select> emits strings — map back to the original option value so the
    // fallback stays type-preserving (numbers, booleans) like the segment path.
    const resolve = s => {
      const m = options.find(o => String(typeof o === 'object' ? o.value : o) === s);
      return m === undefined ? s : typeof m === 'object' ? m.value : m;
    };
    return /*#__PURE__*/React.createElement(TweakSelect, {
      label: label,
      value: value,
      options: options,
      onChange: s => onChange(resolve(s))
    });
  }
  const opts = options.map(o => typeof o === 'object' ? o : {
    value: o,
    label: o
  });
  const idx = Math.max(0, opts.findIndex(o => o.value === value));
  const n = opts.length;
  const segAt = clientX => {
    const r = trackRef.current.getBoundingClientRect();
    const inner = r.width - 4;
    const i = Math.floor((clientX - r.left - 2) / inner * n);
    return opts[Math.max(0, Math.min(n - 1, i))].value;
  };
  const onPointerDown = e => {
    setDragging(true);
    const v0 = segAt(e.clientX);
    if (v0 !== valueRef.current) onChange(v0);
    const move = ev => {
      if (!trackRef.current) return;
      const v = segAt(ev.clientX);
      if (v !== valueRef.current) onChange(v);
    };
    const up = () => {
      setDragging(false);
      window.removeEventListener('pointermove', move);
      window.removeEventListener('pointerup', up);
    };
    window.addEventListener('pointermove', move);
    window.addEventListener('pointerup', up);
  };
  return /*#__PURE__*/React.createElement(TweakRow, {
    label: label
  }, /*#__PURE__*/React.createElement("div", {
    ref: trackRef,
    role: "radiogroup",
    onPointerDown: onPointerDown,
    className: dragging ? 'twk-seg dragging' : 'twk-seg'
  }, /*#__PURE__*/React.createElement("div", {
    className: "twk-seg-thumb",
    style: {
      left: `calc(2px + ${idx} * (100% - 4px) / ${n})`,
      width: `calc((100% - 4px) / ${n})`
    }
  }), opts.map(o => /*#__PURE__*/React.createElement("button", {
    key: o.value,
    type: "button",
    role: "radio",
    "aria-checked": o.value === value
  }, o.label))));
}
function TweakSelect({
  label,
  value,
  options,
  onChange
}) {
  return /*#__PURE__*/React.createElement(TweakRow, {
    label: label
  }, /*#__PURE__*/React.createElement("select", {
    className: "twk-field",
    value: value,
    onChange: e => onChange(e.target.value)
  }, options.map(o => {
    const v = typeof o === 'object' ? o.value : o;
    const l = typeof o === 'object' ? o.label : o;
    return /*#__PURE__*/React.createElement("option", {
      key: v,
      value: v
    }, l);
  })));
}
function TweakText({
  label,
  value,
  placeholder,
  onChange
}) {
  return /*#__PURE__*/React.createElement(TweakRow, {
    label: label
  }, /*#__PURE__*/React.createElement("input", {
    className: "twk-field",
    type: "text",
    value: value,
    placeholder: placeholder,
    onChange: e => onChange(e.target.value)
  }));
}
function TweakNumber({
  label,
  value,
  min,
  max,
  step = 1,
  unit = '',
  onChange
}) {
  const clamp = n => {
    if (min != null && n < min) return min;
    if (max != null && n > max) return max;
    return n;
  };
  const startRef = React.useRef({
    x: 0,
    val: 0
  });
  const onScrubStart = e => {
    e.preventDefault();
    startRef.current = {
      x: e.clientX,
      val: value
    };
    const decimals = (String(step).split('.')[1] || '').length;
    const move = ev => {
      const dx = ev.clientX - startRef.current.x;
      const raw = startRef.current.val + dx * step;
      const snapped = Math.round(raw / step) * step;
      onChange(clamp(Number(snapped.toFixed(decimals))));
    };
    const up = () => {
      window.removeEventListener('pointermove', move);
      window.removeEventListener('pointerup', up);
    };
    window.addEventListener('pointermove', move);
    window.addEventListener('pointerup', up);
  };
  return /*#__PURE__*/React.createElement("div", {
    className: "twk-num"
  }, /*#__PURE__*/React.createElement("span", {
    className: "twk-num-lbl",
    onPointerDown: onScrubStart
  }, label), /*#__PURE__*/React.createElement("input", {
    type: "number",
    value: value,
    min: min,
    max: max,
    step: step,
    onChange: e => onChange(clamp(Number(e.target.value)))
  }), unit && /*#__PURE__*/React.createElement("span", {
    className: "twk-num-unit"
  }, unit));
}

// Relative-luminance contrast pick — checkmarks drawn over a swatch need to
// read on both #111 and #fafafa without per-option configuration. Hex input
// only (#rgb / #rrggbb); named or rgb()/hsl() colors fall through to "light".
function __twkIsLight(hex) {
  const h = String(hex).replace('#', '');
  const x = h.length === 3 ? h.replace(/./g, c => c + c) : h.padEnd(6, '0');
  const n = parseInt(x.slice(0, 6), 16);
  if (Number.isNaN(n)) return true;
  const r = n >> 16 & 255,
    g = n >> 8 & 255,
    b = n & 255;
  return r * 299 + g * 587 + b * 114 > 148000;
}
const __TwkCheck = ({
  light
}) => /*#__PURE__*/React.createElement("svg", {
  viewBox: "0 0 14 14",
  "aria-hidden": "true"
}, /*#__PURE__*/React.createElement("path", {
  d: "M3 7.2 5.8 10 11 4.2",
  fill: "none",
  strokeWidth: "2.2",
  strokeLinecap: "round",
  strokeLinejoin: "round",
  stroke: light ? 'rgba(0,0,0,.78)' : '#fff'
}));

// TweakColor — curated color/palette picker. Each option is either a single
// hex string or an array of 1-5 hex strings; the card adapts — a lone color
// renders solid, a palette renders colors[0] as the hero (left ~2/3) with the
// rest stacked in a sharp column on the right. onChange emits the
// option in the shape it was passed (string stays string, array stays array).
// Without options it falls back to the native color input for back-compat.
function TweakColor({
  label,
  value,
  options,
  onChange
}) {
  if (!options || !options.length) {
    return /*#__PURE__*/React.createElement("div", {
      className: "twk-row twk-row-h"
    }, /*#__PURE__*/React.createElement("div", {
      className: "twk-lbl"
    }, /*#__PURE__*/React.createElement("span", null, label)), /*#__PURE__*/React.createElement("input", {
      type: "color",
      className: "twk-swatch",
      value: value,
      onChange: e => onChange(e.target.value)
    }));
  }
  // Native <input type=color> emits lowercase hex per the HTML spec, so
  // compare case-insensitively. String() guards JSON.stringify(undefined),
  // which returns the primitive undefined (no .toLowerCase).
  const key = o => String(JSON.stringify(o)).toLowerCase();
  const cur = key(value);
  return /*#__PURE__*/React.createElement(TweakRow, {
    label: label
  }, /*#__PURE__*/React.createElement("div", {
    className: "twk-chips",
    role: "radiogroup"
  }, options.map((o, i) => {
    const colors = Array.isArray(o) ? o : [o];
    const [hero, ...rest] = colors;
    const sup = rest.slice(0, 4);
    const on = key(o) === cur;
    return /*#__PURE__*/React.createElement("button", {
      key: i,
      type: "button",
      className: "twk-chip",
      role: "radio",
      "aria-checked": on,
      "data-on": on ? '1' : '0',
      "aria-label": colors.join(', '),
      title: colors.join(' · '),
      style: {
        background: hero
      },
      onClick: () => onChange(o)
    }, sup.length > 0 && /*#__PURE__*/React.createElement("span", null, sup.map((c, j) => /*#__PURE__*/React.createElement("i", {
      key: j,
      style: {
        background: c
      }
    }))), on && /*#__PURE__*/React.createElement(__TwkCheck, {
      light: __twkIsLight(hero)
    }));
  })));
}
function TweakButton({
  label,
  onClick,
  secondary = false
}) {
  return /*#__PURE__*/React.createElement("button", {
    type: "button",
    className: secondary ? 'twk-btn secondary' : 'twk-btn',
    onClick: onClick
  }, label);
}
Object.assign(window, {
  useTweaks,
  TweaksPanel,
  TweakSection,
  TweakRow,
  TweakSlider,
  TweakToggle,
  TweakRadio,
  TweakSelect,
  TweakText,
  TweakNumber,
  TweakColor,
  TweakButton
});
})(); } catch (e) { __ds_ns.__errors.push({ path: "video/tweaks-panel.jsx", error: String((e && e.message) || e) }); }

__ds_ns.Button = __ds_scope.Button;

__ds_ns.Icon = __ds_scope.Icon;

__ds_ns.Badge = __ds_scope.Badge;

__ds_ns.Callout = __ds_scope.Callout;

__ds_ns.StatusPill = __ds_scope.StatusPill;

__ds_ns.Input = __ds_scope.Input;

__ds_ns.Card = __ds_scope.Card;

__ds_ns.CodeBlock = __ds_scope.CodeBlock;

__ds_ns.Tabs = __ds_scope.Tabs;

})();
