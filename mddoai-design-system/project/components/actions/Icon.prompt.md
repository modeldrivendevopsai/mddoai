**Icon** — renders a [Lucide](https://lucide.dev) line icon by name; the brand's icon system (2px stroke, rounded joins, monochrome `currentColor`). The page must load the Lucide UMD script (`https://unpkg.com/lucide@latest`).

```jsx
<Icon name="arrow-right" size={18} />
<Icon name="terminal" />
<Button iconLeft={<Icon name="download" size={16} />}>Pull the image</Button>
```

Use kebab-case Lucide names. Keep icons monochrome and inheriting text colour; don't use filled/duotone styles or emoji.
