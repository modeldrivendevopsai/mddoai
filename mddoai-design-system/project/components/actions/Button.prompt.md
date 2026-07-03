**Button** — the primary action element; use for any clickable command. Primary (solid violet) for the main action per view, secondary/ghost for the rest.

```jsx
<Button variant="primary" size="md" onClick={run}>Run the transformation</Button>
<Button variant="secondary" iconLeft={<Icon name="download" />}>Pull the image</Button>
<Button variant="ghost" size="sm">Read the docs</Button>
```

Variants: `primary` (solid `--brand`, violet glow on hover), `secondary` (bordered white), `ghost` (transparent), `gradient` (brand gradient — reserve for one hero CTA), `danger`. Sizes `sm | md | lg`. Hover darkens one step; press settles 1px; focus shows the violet ring. Pass `iconLeft` / `iconRight` Lucide nodes; `full` stretches to container width.
