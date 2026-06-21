**StatusPill** — a pill showing a CI/CD pipeline state, using the brand's semantic-colour mapping. Use anywhere you show job/stage/pipeline status.

```jsx
<StatusPill status="passed" />
<StatusPill status="running" />
<StatusPill status="failed">build failed</StatusPill>
```

States: `passed` (green), `running` (amber, animated pulse), `pending`, `failed` (red), `info` (blue), `skipped`. Monospace label; defaults to the state name. `size="sm"` for dense tables.
