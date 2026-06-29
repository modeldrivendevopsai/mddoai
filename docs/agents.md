# GitHub Copilot Agents

This project uses [GitHub Copilot](https://github.com/features/copilot) agents to assist with development workflows.
Agents are defined as markdown files in [`.github/agents/`](../.github/agents/).

**How to invoke:** Open Copilot Chat in VSCode (`Ctrl+Alt+I`), click the **agent picker** at the bottom of the chat input, select the agent, then type any arguments directly in the chat and send.

---

## Available Agents

### PR Logic Reviewer

**File:** [`.github/agents/pr-logic-reviewer.agent.md`](../.github/agents/pr-logic-reviewer.agent.md)

Reviews the logic of a pull request — fetching the diff directly from GitHub and checking for logic errors, edge cases, security concerns, and whether the implementation matches the PR description.

**Prerequisites:**

- GitHub CLI (`gh`) installed — [https://cli.github.com](https://cli.github.com)
- Authenticated: run `gh auth login` if not already done
- Verify with: `gh auth status`

**Invoke:** Select **PR Logic Reviewer** from the agent picker, then type:

```
pr=<number>
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

**Invoke:** Select **Lint Reviewer** from the agent picker and send with no arguments — it defaults to `main/src/main/java/`. Optionally narrow the scope:

```
path=main/src/main/java/mddoai/transformers
```

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

> **Note:** This agent executes the test suite, which may take several minutes.

**Invoke:** Select **Coverage Reviewer** from the agent picker and send with no arguments — it analyses all classes. Optionally narrow the gap report:

```
path=main/src/main/java/mddoai/transformers
```

The test suite always runs in full regardless of scope.

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

**Invoke:** Select **OOP Reviewer** from the agent picker and send with no arguments — it defaults to `main/src/main/java/`. Optionally narrow the scope:

```
path=main/src/main/java/mddoai/transformers
```

**What it checks:**

| Principle / Smell   | What is flagged                                                 |
| ------------------- | --------------------------------------------------------------- |
| SRP                 | Classes handling multiple concerns; >10 public methods          |
| OCP                 | `if/else` or `switch` chains that branch on type strings        |
| LSP                 | Overrides that throw undeclared exceptions or ignore parameters |
| ISP                 | Interfaces with >7 methods; methods left as empty stubs         |
| DIP                 | `new ConcreteClass()` inside methods where injection would help |
| God class           | Classes >300 lines handling diverse concerns                    |
| Feature envy        | Methods that operate mostly on another object's data            |
| Primitive obsession | Raw strings/ints where a value object would clarify intent      |
| Public fields       | Non-final instance fields that are `public`                     |

**Output:** a findings table with principle/smell, file, line, description, and suggestion. Verdict: CLEAN, NEEDS ATTENTION, or REQUIRES REFACTORING.

---

### Test Fixtures Updater

**File:** [`.github/agents/test-fixtures-updater.agent.md`](../.github/agents/test-fixtures-updater.agent.md)

After changing an ATL rule, Acceleo template, or metamodel, the `expected1.*` test fixture files become stale. Instead of manually re-running each transformation and copying outputs, this agent does the full refresh in one command.

**Invoke:** Select **Test Fixtures Updater** from the agent picker and send with no arguments.

**What it does:**

1. Runs `swarch2gitlab` on `swarch2pim/input1.swarch` — one CLI call produces all intermediates
2. Copies each output to both its expected file and the downstream stage's input file
3. Runs `integrationTest` and `e2eTest` to confirm everything passes

**What it never touches:**

- `expected2.*` files — intentionally wrong (used with `assertFalse`), must stay manually crafted

| Generated file         | Copied to                                                   |
| ---------------------- | ----------------------------------------------------------- |
| `PipelinePIM.pimmm`    | `swarch2pim/expected1.pimmm` + `pim2psm/input1.pimmm`       |
| `PipelineGit.gitlabmm` | `pim2psm/expected1.gitlabmm` + `psm2gitlab/input1.gitlabmm` |
| `.gitlab-ci.yml`       | `psm2gitlab/expected1.yml` + `e2e/expected1.yml`            |

---

### PR Description Generator

**File:** [`.github/agents/pr-description-generator.agent.md`](../.github/agents/pr-description-generator.agent.md)

Reads the current branch diff against `main` and produces a ready-to-paste PR description. Run this **before** opening a PR on GitHub — no PR number needed.

**Invoke:** Select **PR Description Generator** from the agent picker and send with no arguments.

**What it does:**

- Runs `git diff main...HEAD` to get all changes on the current branch
- Maps changed files to transformation stages using project domain language (ATL, PIM, PSM, swarch, gitlabmm)
- Generates a `## Summary` block with typed bullets: `Bug fix`, `New feature`, `Improvement`, `Refactor`, `Tests`, `Docs`, `CI`, `Agents`
- Appends the branch name and commit count for reference

**Output:** A paste-ready description block — no preamble, just the formatted text to copy into the GitHub PR form.

---
