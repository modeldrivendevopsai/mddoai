**Input** — single-line text field. Use `mono` for command, path, or tag entry.

```jsx
<Input label="Output folder" value={out} onChange={e => setOut(e.target.value)} mono placeholder="./output" />
<Input label="Email" hint="We'll send release notes." />
<Input error="Required" value="" />
```

Focus shows the violet ring + brand border. `error` turns the border red and replaces the hint. Optional `iconLeft` (Lucide node).
