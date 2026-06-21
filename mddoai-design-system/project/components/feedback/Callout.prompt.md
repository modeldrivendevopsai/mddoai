**Callout** — an inline note box for docs and product copy (notes, tips, warnings).

```jsx
<Callout tone="tip" title="Fastest way to get started" icon={<Icon name="zap" size={18} />}>
  No build required — pull the image and run the transformation.
</Callout>
<Callout tone="warning" title="Windows users">Use <code>.\cli.bat</code> in CMD.</Callout>
```

Tones: `info`, `tip` (violet), `warning` (amber), `danger` (red). Thin 1px border + tinted background — never a heavy colour block. Optional `icon` and `title`.
