import { defineConfig } from "vitest/config"

// design-system ships no build of its own (consumers import straight from
// src, see package.json's "main") - this config exists only to run its own
// test suite, not to build or serve anything.
export default defineConfig({
  test: {
    environment: "jsdom",
  },
})
