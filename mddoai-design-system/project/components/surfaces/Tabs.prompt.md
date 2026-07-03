**Tabs** — horizontal segmented navigation with a violet active underline. Controlled or uncontrolled.

```jsx
<Tabs
  tabs={[{ id: 'docker', label: 'Docker' }, { id: 'source', label: 'From source' }]}
  defaultValue="docker"
  onChange={setTab}
/>
```

Each tab takes `{ id, label, icon?, count? }`. Pass `value` + `onChange` for controlled use, or `defaultValue` for uncontrolled.
