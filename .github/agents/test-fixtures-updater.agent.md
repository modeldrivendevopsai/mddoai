---
name: Test Fixtures Updater
description: "After a transformation change, re-runs all transformations from swarch2pim/input1.swarch and updates all expected files AND downstream input files to keep the chain consistent."
tools: [execute, read]
user-invocable: true
---

## Shell Note

All commands below use Unix syntax. In VSCode on Windows, ensure the integrated terminal
is set to **Git Bash** — `mkdir -p`, `cp`, and `rm -rf` will not work in CMD.

## Critical Rules

- ONLY overwrite `expected1.*` files — NEVER touch `expected2.*` or any other expected files
  - `expected1.*` files are used with `assertTrue` (must match real output)
  - `expected2.*` files are used with `assertFalse` (intentionally wrong — do not regenerate)
- The downstream INPUT files (`pim2psm/input1.pimmm` and `psm2gitlab/input1.gitlabmm`) MUST
  also be updated — they are derived from the chain and must stay consistent with upstream changes
- The CLI is `./cli.bat` in the `main/` directory
- NEVER manually write or guess file content — always copy directly from CLI output
- If the CLI fails, stop immediately and report the error

## Goal

All test fixture input1 files form a single consistent chain derived from `swarch2pim/input1.swarch`.
After changing any transformation (ATL rule or Acceleo template), run ONE command to
refresh all affected files and keep the chain consistent.

## How the Chain Works

```
swarch2pim/input1.swarch  (source of truth — do not modify)
        ↓  swarch2gitlab CLI
        ├─→  test/generatedModels/PipelinePIM.pimmm
        │         ↓ copy to
        │    swarch2pim/expected1.pimmm   (expected output of swarch2pim test)
        │    pim2psm/input1.pimmm         (input for pim2psm test — keep in sync)
        │
        ├─→  test/generatedModels/PipelineGit.gitlabmm
        │         ↓ copy to
        │    pim2psm/expected1.gitlabmm   (expected output of pim2psm test)
        │    psm2gitlab/input1.gitlabmm   (input for psm2gitlab test — keep in sync)
        │
        └─→  test/tmpFixtures/.gitlab-ci.yml
                  ↓ copy to
             psm2gitlab/expected1.yml     (expected output of psm2gitlab test)
             e2e/expected1.yml            (expected output of e2e test)
```

Running `swarch2gitlab` on `swarch2pim/input1.swarch` produces all three intermediates
in a single pass. Copy each one to BOTH its expected output location AND its downstream input location.

## Procedure

### Step 1 — Run the Full Chain Once

```bash
cd main
mkdir -p test/tmpFixtures
./cli.bat swarch2gitlab ./src/test/resources/testCases/swarch2pim/input1.swarch ./test/tmpFixtures
```

Verify these files now exist before proceeding:
- `test/generatedModels/PipelinePIM.pimmm`
- `test/generatedModels/PipelineGit.gitlabmm`
- `test/tmpFixtures/.gitlab-ci.yml`

If any are missing, stop and report the CLI error output.

### Step 2 — Copy to All Destinations

Run all six copies immediately after Step 1:

```bash
CASES=./src/test/resources/testCases

# PIM model → swarch2pim expected + pim2psm input
cp ./test/generatedModels/PipelinePIM.pimmm    $CASES/swarch2pim/expected1.pimmm
cp ./test/generatedModels/PipelinePIM.pimmm    $CASES/pim2psm/input1.pimmm

# GitLab model → pim2psm expected + psm2gitlab input
cp ./test/generatedModels/PipelineGit.gitlabmm $CASES/pim2psm/expected1.gitlabmm
cp ./test/generatedModels/PipelineGit.gitlabmm $CASES/psm2gitlab/input1.gitlabmm

# Final YAML → psm2gitlab expected + e2e expected
cp ./test/tmpFixtures/.gitlab-ci.yml           $CASES/psm2gitlab/expected1.yml
cp ./test/tmpFixtures/.gitlab-ci.yml           $CASES/e2e/expected1.yml
```

### Step 3 — Show What Changed

Read the old and new versions of each file and note what changed:
- Use `git diff` on `main/src/test/resources/testCases/` to see a full summary

### Step 4 — Verify

```bash
./gradlew integrationTest e2eTest
```

All tests must pass. If any fail, report the exact failing assertion — do not guess.

### Step 5 — Clean Up

```bash
rm -rf ./test/tmpFixtures
```

## Output Format

**Test Fixtures Updater**

**Files updated:**
- List all 6 destination paths with a one-line summary of what changed in each

**Test run:** PASSED / FAILED
If failed, paste the exact failing test name and assertion message.
