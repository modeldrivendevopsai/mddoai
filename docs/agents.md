# GitHub Copilot Agents

This project uses [GitHub Copilot](https://github.com/features/copilot) agents to assist with development workflows.
Agents are defined as markdown files in [`.github/agents/`](../.github/agents/) and are invoked from the GitHub Copilot Chat panel in VSCode using the `@agent` or `/agent-name` syntax.

---

## Available Agents

### PR Logic Reviewer

**File:** [`.github/agents/pr-logic-reviewer.agent.md`](../.github/agents/pr-logic-reviewer.agent.md)

Reviews the logic of a pull request — fetching the diff directly from GitHub and checking for logic errors, edge cases, security concerns, and whether the implementation matches the PR description.

**Prerequisites:**
- GitHub CLI (`gh`) installed — [https://cli.github.com](https://cli.github.com)
- Authenticated: run `gh auth login` if not already done
- Verify with: `gh auth status`

**Invoke:**
```
/pr-logic-reviewer pr=<number>
```

**What it checks:**
- Logic errors and incorrect assumptions in the diff
- Unhandled edge cases
- Security concerns (injection, auth bypass, data leaks)
- Whether the implementation matches the PR description

**What it does NOT do:** run builds, tests, or Docker commands — those are handled by CI.

**Output:** findings with severity levels (High / Medium / Low) and a final verdict of PASS, PASS WITH RISKS, or FAIL.

---

### Lint Reviewer

**File:** [`.github/agents/lint-reviewer.agent.md`](../.github/agents/lint-reviewer.agent.md)

Reviews Java source files for formatting and style violations. Uses Checkstyle or PMD via Gradle if configured; otherwise performs manual pattern analysis.

**Invoke:**
```
/lint-reviewer path=main/src/main/java/mddoai/transformers
```
Omit `path` to review the entire `main/src/main/java/` directory.

**What it checks:**
- Naming conventions (PascalCase classes, camelCase methods, UPPER_SNAKE_CASE constants)
- Method length (flags methods over ~30 lines)
- Magic numbers (unlabelled numeric literals)
- Deep nesting (more than 3 levels)
- Unused imports
- Missing blank lines between methods

**Output:** a table of violations with file, line, rule, and severity. Verdict: CLEAN or NEEDS ATTENTION.

---

### Coverage Reviewer

**File:** [`.github/agents/coverage-reviewer.agent.md`](../.github/agents/coverage-reviewer.agent.md)

Runs the full Gradle test suite, generates a JaCoCo coverage report, and identifies classes with insufficient coverage.

> **Note:** This agent executes the test suite, which may take several minutes. It is best run in the background.

**Invoke:**
```
/coverage-reviewer path=main/src/main/java/mddoai/transformers
```
Omit `path` to analyse all classes. The test suite always runs in full regardless of scope.

**What it checks:**
- Runs `test`, `integrationTest`, and `e2eTest` Gradle tasks
- Parses JaCoCo XML output for per-class line and branch coverage
- Flags classes as Critical (0%), High (<40%), Medium (40–70%), or Low (70–80%) priority
- For Critical/High classes, identifies likely untested methods

**Output:** coverage summary, gap table, and a verdict of ADEQUATE, GAPS FOUND, or CRITICALLY UNDER-TESTED.

---

### OOP Reviewer

**File:** [`.github/agents/oop-reviewer.agent.md`](../.github/agents/oop-reviewer.agent.md)

Reviews Java source files for object-oriented design quality — SOLID principle violations and common code smells. This agent reads files only; it does not run any commands.

**Invoke:**
```
/oop-reviewer path=main/src/main/java/mddoai/transformers
```
Omit `path` to review the entire `main/src/main/java/` directory.

**What it checks:**

| Principle / Smell | What is flagged |
|-------------------|----------------|
| SRP | Classes handling multiple concerns; >10 public methods |
| OCP | `if/else` or `switch` chains that branch on type strings |
| LSP | Overrides that throw undeclared exceptions or ignore parameters |
| ISP | Interfaces with >7 methods; methods left as empty stubs |
| DIP | `new ConcreteClass()` inside methods where injection would help |
| God class | Classes >300 lines handling diverse concerns |
| Feature envy | Methods that operate mostly on another object's data |
| Primitive obsession | Raw strings/ints where a value object would clarify intent |
| Public fields | Non-final instance fields that are `public` |

**Output:** a findings table with principle/smell, file, line, description, and suggestion. Verdict: CLEAN, NEEDS ATTENTION, or REQUIRES REFACTORING.

---

### Test Fixtures Updater

**File:** [`.github/agents/test-fixtures-updater.agent.md`](../.github/agents/test-fixtures-updater.agent.md)

After changing an ATL rule, Acceleo template, or metamodel, the `expected1.*` test fixture files become stale. Instead of manually re-running each transformation and copying outputs, this agent does the full refresh in one command.

**Invoke:**
```
/test-fixtures-updater
/test-fixtures-updater stage=pim2psm
```
Omit `stage` (or use `stage=all`) to refresh all four stages at once.

**What it does:**
1. Ensures the build is installed (`gradlew installDist` if needed)
2. Runs the real CLI transformation for each stage against its `input1` fixture
3. Shows what changed before overwriting anything
4. Overwrites only the `expected1.*` files that differ from the new output
5. Runs `integrationTest` and `e2eTest` to confirm the updates pass

**What it never touches:**
- `expected2.*` files — these are intentionally wrong (used with `assertFalse`) and must stay manually crafted
- Any `input*` file
- `input3` files have no expected output at all — nothing to update there

| Stage | Input used | File overwritten |
|-------|-----------|-----------------|
| `swarch2pim` | `testCases/swarch2pim/input1.swarch` | `testCases/swarch2pim/expected1.pimmm` |
| `pim2psm` | `testCases/pim2psm/input1.pimmm` | `testCases/pim2psm/expected1.gitlabmm` |
| `psm2gitlab` | `testCases/psm2gitlab/input1.gitlabmm` | `testCases/psm2gitlab/expected1.yml` |
| `e2e` | `testCases/e2e/input1.swarch` | `testCases/e2e/expected1.yml` |

---

## Running Agents in the Background (VSCode)

Coverage Reviewer runs the full Gradle test suite and takes several minutes — run it in the background.
All other agents are fast enough to run inline.

To run an agent in the background in VSCode Copilot Chat:
1. Open GitHub Copilot Chat in VSCode
2. Type the invocation command (e.g., `@workspace /coverage-reviewer`)
3. Copilot will run the agent as a background task

---

## 1 Agent vs. Many — Design Rationale

The three code quality agents (lint, coverage, OOP) are **separate** rather than combined. Here is why:

| Concern | Separate | Combined |
|---------|----------|----------|
| Focus | Each agent has one job and one output format | A combined agent must juggle three different procedures |
| Independent invocation | Run only what you need (e.g., just linting before a quick fix) | Always runs everything even if only one check is needed |
| Parallel execution | All three can run as background jobs simultaneously | One job, sequential phases |
| Failure isolation | A test suite failure does not block the lint review | One phase failing can muddle the entire report |
| Maintenance | Update one agent without touching the others | Any change risks affecting unrelated checks |

The tradeoff against a single agent: three invocations instead of one, and no cross-referencing between findings. For this project — where checks are meaningfully independent and are often needed individually — three focused agents produce better results than one broad one.

If the project later adds more checks (e.g., dependency auditing, API contract validation), each new concern should get its own agent rather than being folded into an existing one.
