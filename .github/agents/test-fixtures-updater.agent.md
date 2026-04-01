---
name: Test Fixtures Updater
description: "After a transformation change, re-runs all transformations against the input1 test cases and overwrites the expected1 output files. Run after changes to ATL rules, Acceleo templates, or metamodels."
tools: [execute, read]
argument-hint: "stage=<all|swarch2pim|pim2psm|psm2gitlab|e2e>"
user-invocable: true
---

## Critical Rules

- ONLY overwrite `expected1.*` files — NEVER touch `expected2.*` or any other expected files
  - `expected1.*` files are used with `assertTrue` (must match real output)
  - `expected2.*` files are used with `assertFalse` (intentionally wrong — do not regenerate)
- NEVER touch input files
- If any transformation step fails, stop immediately and report the error — do not overwrite anything from that stage
- If the build is not installed, run `cd main && ./gradlew installDist` first

## Goal

After a change to an ATL rule, Acceleo template, or metamodel, the `expected1.*` test
fixture files become stale. Manually re-running each transformation and copying output is
tedious. This agent automates the full refresh in one command.

## Test Fixture Map

Only these files are regenerated:

| Stage | Input | Runs this CLI command | Overwrites |
|-------|-------|-----------------------|------------|
| `swarch2pim` | `testCases/swarch2pim/input1.swarch` | `swarch2pim <input> <outdir>` | `testCases/swarch2pim/expected1.pimmm` |
| `pim2psm` | `testCases/pim2psm/input1.pimmm` | `pim2gitlab <input> <outdir>` | `testCases/pim2psm/expected1.gitlabmm` |
| `psm2gitlab` | `testCases/psm2gitlab/input1.gitlabmm` | `psm2gitlab <input> <outdir>` | `testCases/psm2gitlab/expected1.yml` |
| `e2e` | `testCases/e2e/input1.swarch` | `swarch2gitlab <input> <outdir>` | `testCases/e2e/expected1.yml` |

## Procedure

### Step 0 — Ensure the Build is Up To Date

Check whether `main/build/install/com.mddoai/bin/cli.bat` exists.
If not, build first:
```
cd main && ./gradlew installDist
```

### Step 1 — Determine Which Stages to Run

Parse the `stage=` argument:
- `all` or no argument → run all four stages in the order listed in the table above
- Any specific stage name → run only that stage

### Step 2 — Run Each Stage

For each stage to run, execute the CLI and write output to a temporary directory.
Run commands from inside `main/`:

**swarch2pim:**
```
cd main
mkdir -p test/tmpFixtures
./build/install/com.mddoai/bin/cli.bat swarch2pim \
  ./src/test/resources/testCases/swarch2pim/input1.swarch \
  ./test/tmpFixtures
```
Generated file: `test/tmpFixtures/PipelinePIM.pimmm` (or similar — check what the CLI actually outputs)

**pim2psm:**
```
cd main
./build/install/com.mddoai/bin/cli.bat pim2gitlab \
  ./src/test/resources/testCases/pim2psm/input1.pimmm \
  ./test/tmpFixtures
```
Generated file: `test/tmpFixtures/*.gitlabmm`

**psm2gitlab:**
```
cd main
./build/install/com.mddoai/bin/cli.bat psm2gitlab \
  ./src/test/resources/testCases/psm2gitlab/input1.gitlabmm \
  ./test/tmpFixtures
```
Generated file: `test/tmpFixtures/.gitlab-ci.yml`

**e2e:**
```
cd main
./build/install/com.mddoai/bin/cli.bat swarch2gitlab \
  ./src/test/resources/testCases/e2e/input1.swarch \
  ./test/tmpFixtures
```
Generated file: `test/tmpFixtures/.gitlab-ci.yml`

> After each command, verify the output file exists before proceeding. If the command
> failed or produced no output file, stop and report the error.

### Step 3 — Check What Actually Changed

Before overwriting, read the current `expected1.*` file and the newly generated file.
Show a brief diff summary: what lines were added, removed, or changed.
If the files are identical, note that — no overwrite needed for that stage.

### Step 4 — Overwrite the Expected Files

For each stage where the output differs from the current expected file, copy the
generated output to the correct location:

- `swarch2pim` → copy to `main/src/test/resources/testCases/swarch2pim/expected1.pimmm`
- `pim2psm` → copy to `main/src/test/resources/testCases/pim2psm/expected1.gitlabmm`
- `psm2gitlab` → copy to `main/src/test/resources/testCases/psm2gitlab/expected1.yml`
- `e2e` → copy to `main/src/test/resources/testCases/e2e/expected1.yml`

### Step 5 — Verify

Run the integration and e2e tests to confirm the updated expected files now pass:
```
cd main && ./gradlew integrationTest e2eTest
```

### Step 6 — Clean Up

Remove the temporary directory:
```
rm -rf main/test/tmpFixtures
```

## Output Format

**Test Fixtures Updater**
Stages run: `<all | specific stages>`

| Stage | Status | Changes |
|-------|--------|---------|
| swarch2pim | Updated / No change / FAILED | Brief description of what changed |
| pim2psm    | Updated / No change / FAILED | Brief description of what changed |
| psm2gitlab | Updated / No change / FAILED | Brief description of what changed |
| e2e        | Updated / No change / FAILED | Brief description of what changed |

**Test run:** PASSED / FAILED
If tests failed after the update, paste the failing assertion so the user knows which
transformation still has an issue.

**Files updated:**
- List each overwritten expected file with its path
