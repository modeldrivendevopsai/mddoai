**CodeBlock** — the dark terminal surface for commands and snippets. Commands are first-class brand content, so this always reads as a terminal, even on light pages.

```jsx
<CodeBlock title="bash" lines={[
  { text: 'docker pull ghcr.io/modeldrivendevopsai/mddoai/mddoai:1.0.1', prompt: true },
  { text: 'docker run --rm mddoai swarch2gitlab input.swarch ./out', prompt: true },
  { text: 'BUILD SUCCESSFUL', kind: 'flag' },
]} />

<CodeBlock code={"./cli.bat swarch2gitlab ./input/my-app.swarch ./output"} />
```

`lines` items can set `prompt` (leading `$`) and `kind` (`comment | output | flag | string`) for colour. Includes a window bar and copy button.
