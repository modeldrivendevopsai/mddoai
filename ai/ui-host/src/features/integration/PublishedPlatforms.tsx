import { useEffect, useState } from "react"
import { Button } from "design-system"
import type { PlatformSummary } from "orchestrator-types"

interface PublishedPlatformsProps {
  loadPlatforms: () => Promise<PlatformSummary[]>
  onPurge: (platformId: string, version?: string) => Promise<string[]>
  // Changes when a platform was just published, so the list loads again.
  refreshKey: number
}

const textStyle: React.CSSProperties = { margin: 0, color: "var(--text-muted)", fontSize: 14 }

const rowStyle: React.CSSProperties = {
  display: "flex",
  flexWrap: "wrap",
  alignItems: "center",
  gap: "var(--space-2)",
}

// What a click on a Remove button is about: a whole platform, or one version.
function targetKey(platformId: string, version?: string): string {
  return version ? `${platformId}@${version}` : platformId
}

/**
 * The platforms published from a run, each with its published versions and a
 * button to remove one version, or all of them when every version was published.
 * A version that came with the stack is not listed, because it cannot be removed.
 * Removing asks once more first, since it cannot be undone.
 */
export function PublishedPlatforms({ loadPlatforms, onPurge, refreshKey }: PublishedPlatformsProps) {
  const [platforms, setPlatforms] = useState<PlatformSummary[] | null>(null)
  const [confirming, setConfirming] = useState<string | null>(null)
  const [removing, setRemoving] = useState(false)
  const [error, setError] = useState<string | null>(null)
  // Counts the removals, so the list loads again after each one.
  const [removed, setRemoved] = useState(0)

  useEffect(() => {
    let current = true
    loadPlatforms()
      .then((all) => {
        if (current) setPlatforms(all.filter((platform) => platform.published_versions.length > 0))
      })
      .catch((err) => {
        if (current) setError(err instanceof Error && err.message ? err.message : "Could not load the published platforms.")
      })
    return () => {
      current = false
    }
  }, [loadPlatforms, refreshKey, removed])

  async function remove(platformId: string, version?: string) {
    setRemoving(true)
    setError(null)
    try {
      await onPurge(platformId, version)
      setConfirming(null)
      setRemoved((count) => count + 1)
    } catch (err) {
      setError(err instanceof Error && err.message ? err.message : "Could not remove it.")
    } finally {
      setRemoving(false)
    }
  }

  function removeControl(platform: PlatformSummary, version?: string) {
    const key = targetKey(platform.id, version)
    const what = version ? `${platform.name} ${version}` : `all of ${platform.name}`
    if (confirming !== key) {
      return (
        <Button variant="ghost" size="sm" disabled={removing} onClick={() => setConfirming(key)}>
          {version ? `Remove ${version}` : "Remove all"}
        </Button>
      )
    }
    return (
      <span style={rowStyle}>
        <span style={textStyle}>Remove {what}? This cannot be undone.</span>
        <Button variant="danger" size="sm" disabled={removing} onClick={() => void remove(platform.id, version)}>
          {removing ? "Removing…" : "Remove"}
        </Button>
        <Button variant="ghost" size="sm" disabled={removing} onClick={() => setConfirming(null)}>
          Cancel
        </Button>
      </span>
    )
  }

  return (
    <section aria-label="Published platforms" style={{ display: "flex", flexDirection: "column", gap: "var(--space-3)" }}>
      <h2 style={{ margin: 0, fontFamily: "var(--font-display)", fontSize: "var(--text-md)", color: "var(--text-strong)" }}>
        Published platforms
      </h2>
      {platforms !== null && platforms.length === 0 && (
        <p style={textStyle}>No platform has been published from a run yet.</p>
      )}
      {platforms?.map((platform) => (
        <div key={platform.id} style={{ display: "flex", flexDirection: "column", gap: "var(--space-2)" }}>
          <div style={rowStyle}>
            <strong style={{ color: "var(--text-strong)", fontSize: 14 }}>{platform.name}</strong>
            {platform.published_versions.length === platform.versions.length && removeControl(platform)}
          </div>
          {platform.published_versions.map((version) => (
            <div key={version} style={{ ...rowStyle, paddingLeft: "var(--space-3)" }}>
              <span style={textStyle}>{version}</span>
              {removeControl(platform, version)}
            </div>
          ))}
        </div>
      ))}
      {error && (
        <p role="alert" style={{ ...textStyle, color: "var(--danger-strong)" }}>
          {error}
        </p>
      )}
    </section>
  )
}
