// @vitest-environment jsdom
//
// A caller can keep the same PromptBuilder mounted while switching it to an
// entirely different named config as circumstances change (a stage panel
// that only learns which of two prompts a given attempt actually used once
// that attempt's own real result comes back, with no reason to remount the
// whole panel just for that) - see index.tsx's own load effect for why it
// keys on manifest.name rather than mounting once and never reloading.
// This is a real regression test for that: switching `manifest` while
// mounted must load and display the NEW config, not keep showing the old
// one under the new label.
import { cleanup, render, screen } from "@testing-library/react"
import { afterEach, describe, expect, it, vi } from "vitest"
import { PromptBuilder } from "./index"
import type { PromptBuilderCallbacks, PromptBuilderManifest, PromptConfig } from "./types"

afterEach(cleanup)

const GENERATION_MANIFEST: PromptBuilderManifest = {
  name: "generation",
  label: "Generation prompt",
  attachmentTypes: ["text"],
  contextKeyOptions: [],
}

const COMPARISON_MANIFEST: PromptBuilderManifest = {
  name: "comparison",
  label: "Comparison prompt",
  attachmentTypes: ["text"],
  contextKeyOptions: [],
}

// Every field but onLoad, which each test supplies itself (the one real
// caller decides how "which config" actually gets resolved - see
// PsmStagePanel's own `onLoad: () => onLoadPromptConfig(activeManifest.name)`
// for the real pattern this mirrors).
function baseCallbacks(): Omit<PromptBuilderCallbacks, "onLoad"> {
  return {
    onSave: vi.fn((config: PromptConfig) => Promise.resolve(config)),
    onPreview: vi.fn(() => Promise.resolve({ system_prompt: "", user_content: "", attachments: {} })),
    onLoadHistory: vi.fn(() => Promise.resolve([])),
    onDiffVersions: vi.fn(),
    onRestoreVersion: vi.fn(),
    onCheckReferences: vi.fn(() => Promise.resolve([])),
    onAddLearnedConstraints: vi.fn(),
    onRemoveLearnedConstraint: vi.fn(),
  }
}

describe("PromptBuilder", () => {
  it("reloads the real config when switched to a different manifest while still mounted", async () => {
    const configs: Record<string, PromptConfig> = {
      generation: { attachments: [{ id: "g", name: "Generation system prompt", type: "text", content: "generation content" }] },
      comparison: { attachments: [{ id: "c", name: "Comparison system prompt", type: "text", content: "comparison content" }] },
    }
    // Both manifests share the same underlying load, called with whichever
    // manifest.name each render's own callbacks close over - matching how
    // every real caller actually wires this.
    const rawOnLoad = vi.fn((name: string) => Promise.resolve(configs[name]))
    const propsFor = (manifest: PromptBuilderManifest) => ({
      manifest,
      callbacks: { ...baseCallbacks(), onLoad: () => rawOnLoad(manifest.name) },
    })

    const { rerender } = render(<PromptBuilder {...propsFor(GENERATION_MANIFEST)} />)

    await screen.findByText("Generation prompt")
    // The attachment's own name renders as a real editable input's value
    // (see DocumentBlock.tsx) - a single exact string, unlike JsonView's
    // syntax-highlighted, token-split output, so this is what actually
    // proves which config is on screen right now.
    expect(await screen.findByDisplayValue("Generation system prompt")).toBeTruthy()
    expect(rawOnLoad).toHaveBeenCalledTimes(1)

    rerender(<PromptBuilder {...propsFor(COMPARISON_MANIFEST)} />)

    await screen.findByText("Comparison prompt")
    // The real regression this guards: without reloading, this would still
    // show the generation attachment under the new "Comparison prompt" label.
    expect(await screen.findByDisplayValue("Comparison system prompt")).toBeTruthy()
    expect(screen.queryByDisplayValue("Generation system prompt")).toBeNull()
    expect(rawOnLoad).toHaveBeenCalledTimes(2)
  })

  it("does not reload on an ordinary re-render where the manifest itself hasn't changed", async () => {
    const config: PromptConfig = { attachments: [{ id: "g", name: "Generation system prompt", type: "text", content: "generation content" }] }
    const onLoad = vi.fn(() => Promise.resolve(config))

    const { rerender } = render(<PromptBuilder manifest={GENERATION_MANIFEST} callbacks={{ ...baseCallbacks(), onLoad }} />)
    await screen.findByText("Generation prompt")
    expect(onLoad).toHaveBeenCalledTimes(1)

    // A fresh manifest object and a fresh callbacks object (as every real
    // caller creates on every render) with the SAME manifest.name must not
    // re-trigger a load - only an actual manifest.name change should.
    rerender(<PromptBuilder manifest={{ ...GENERATION_MANIFEST }} callbacks={{ ...baseCallbacks(), onLoad }} />)

    expect(onLoad).toHaveBeenCalledTimes(1)
  })
})
