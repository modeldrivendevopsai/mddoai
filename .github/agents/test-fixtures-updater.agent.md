---
name: Test Fixtures Updater
description: "After a transformation change, re-runs all transformations against the input1 test cases and overwrites the expected1 output files. Run after changes to ATL rules, Acceleo templates, or metamodels."
tools: [execute, read]
argument-hint: "stage=<all|swarch2pim|pim2psm|psm2gitlab|e2e>"
user-invocable: true
---

## Shell Note

All commands below use Unix syntax. In VSCode on Windows, ensure the integrated terminal
is set to **Git Bash** before running this agent — `mkdir -p` and `cp` will not work in CMD.

## Critical Rules

- ONLY overwrite `expected1.*` files — NEVER touch `expected2.*` or any other expected files
  - `expected1.*` files are used with `assertTrue` (must match real output)
  - `expected2.*` files are used with `assertFalse` (intentionally wrong — do not regenerate)
- NEVER touch input files
- If any transformation fails, stop immediately — do not overwrite anything from that stage
- The CLI is `./cli.bat` in the `main/` directory — do not use the long build path

## Goal

After a change to an ATL rule, Acceleo template, or metamodel, the `expected1.*` test
fixture files become stale. This agent re-runs the real transformations and refreshes
all affected expected files in one command.

## How the CLI Works

The CLI (`./cli.bat`) supports three commands:

| CLI command | What it does | Intermediate files written |
|-------------|-------------|---------------------------|
| `swarch2gitlab <input.swarch> <outdir>` | Full chain: swarch → PIM → gitlabmm → yml | `./test/generatedModels/PipelinePIM.pimmm` and `PipelineGit.gitlabmm` |
| `pim2gitlab <input.pimmm> <outdir>` | PIM → gitlabmm → yml | `./test/generatedModels/PipelineGit.gitlabmm` |
| `psm2gitlab <input.gitlabmm> <outdir>` | gitlabmm → yml | output dir only |

Intermediate `.pimmm` and `.gitlabmm` models are always written to `./test/generatedModels/`
regardless of `<outdir>`. The final `.gitlab-ci.yml` goes to `<outdir>`.

## Test Fixture Map

| Stage | CLI command to run | Source of generated file | Overwrites |
|-------|--------------------|--------------------------|-----------|
| `swarch2pim` | `swarch2gitlab` on `swarch2pim/input1.swarch` | `./test/generatedModels/PipelinePIM.pimmm` | `testCases/swarch2pim/expected1.pimmm` |
| `pim2psm` | `pim2gitlab` on `pim2psm/input1.pimmm` | `./test/generatedModels/PipelineGit.gitlabmm` | `testCases/pim2psm/expected1.gitlabmm` |
| `psm2gitlab` | `psm2gitlab` on `psm2gitlab/input1.gitlabmm` | `./test/tmpFixtures/.gitlab-ci.yml` | `testCases/psm2gitlab/expected1.yml` |
| `e2e` | `swarch2gitlab` on `e2e/input1.swarch` | `./test/tmpFixtures/.gitlab-ci.yml` | `testCases/e2e/expected1.yml` |

## Procedure

### Step 0 — Prepare

```bash
cd main
mkdir -p test/tmpFixtures
```

### Step 1 — Determine Which Stages to Run

Parse the `stage=` argument:
- `all` or no argument → run all four stages
- Any specific stage name → run only that stage

### Step 2 — Run Each Stage and Copy Output

Run each stage and **immediately copy the output** before the next stage overwrites intermediates.

**swarch2pim:**
```bash
./cli.bat swarch2gitlab ./src/test/resources/testCases/swarch2pim/input1.swarch ./test/tmpFixtures
cp ./test/generatedModels/PipelinePIM.pimmm ./src/test/resources/testCases/swarch2pim/expected1.pimmm
```

**pim2psm:**
```bash
./cli.bat pim2gitlab ./src/test/resources/testCases/pim2psm/input1.pimmm ./test/tmpFixtures
cp ./test/generatedModels/PipelineGit.gitlabmm ./src/test/resources/testCases/pim2psm/expected1.gitlabmm
```

**psm2gitlab:**
```bash
./cli.bat psm2gitlab ./src/test/resources/testCases/psm2gitlab/input1.gitlabmm ./test/tmpFixtures
cp ./test/tmpFixtures/.gitlab-ci.yml ./src/test/resources/testCases/psm2gitlab/expected1.yml
```

**e2e:**
```bash
./cli.bat swarch2gitlab ./src/test/resources/testCases/e2e/input1.swarch ./test/tmpFixtures
cp ./test/tmpFixtures/.gitlab-ci.yml ./src/test/resources/testCases/e2e/expected1.yml
```

> After each `./cli.bat` call, verify the expected source file exists before copying.
> If the CLI exits with an error, stop and report — do not copy.

### Step 3 — Show What Changed

Read the old and new versions of each updated file and summarise the diff:
- For `.pimmm` / `.gitlabmm` (XMI): note added, removed, or changed XML elements
- For `.yml`: note added, removed, or changed YAML keys or values
- If a file is identical to what was there before, note "No change" for that stage

### Step 4 — Verify

Run the integration and e2e tests to confirm everything passes:
```bash
./gradlew integrationTest e2eTest
```

### Step 5 — Clean Up

```bash
rm -rf ./test/tmpFixtures
```

## Output Format

**Test Fixtures Updater**
Stages run: `<all | specific stages>`

| Stage | Status | What changed |
|-------|--------|--------------|
| swarch2pim | Updated / No change / FAILED | e.g. "added `lint_check` stage to pipeline" |
| pim2psm    | Updated / No change / FAILED | ... |
| psm2gitlab | Updated / No change / FAILED | ... |
| e2e        | Updated / No change / FAILED | ... |

**Test run:** PASSED / FAILED
If failed, paste the failing assertion.

**Files updated:**
- List each overwritten path
