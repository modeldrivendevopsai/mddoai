# Step 3 — Acceleo Template Generation: Experiment Results

## What this step does

Takes the GitLab PSM model (output of Step 2 ATL transformation) and generates a
valid `.gitlab-ci.yml` file using an Acceleo model-to-text template. The LLM was
asked to write this template from scratch, with only context provided via the prompt.
Each round added one new constraint to fix a bug found in the previous round.

---

## Context provided (fixed across all rounds)

| Artifact | Purpose |
|---|---|
| `gitlabMM.ecore` | GitLab PSM metamodel — class/attribute names |
| `gha_generate.mtl` | ACICDTrip GHA Acceleo template — syntax reference |
| `gitlab-ci-docs.md` | GitLab CI/CD keyword reference — YAML output format |

---

## Round-by-round progression

```mermaid
flowchart TD
    CTX("🗂️ Base context\n gitlabMM.ecore\n gha_generate.mtl\n gitlab-ci-docs.md")

    CTX --> R1

    R1["🔴 Round 1\nNo constraints"]
    R1 -- "def used as param name\n→ 18 compile errors\n@main outside template body" --> R2

    R2["🟡 Round 2\n+ no reserved keywords\n+ skip enum defaults\n+ correct indentation\n+ name template generateElement"]
    R2 -- "def still broken (too vague)\n@main regressed (new bug)\npull_policy ✓  when ✓" --> R3

    R3["🟡 Round 3\n+ def named explicitly\n+ @main must be inside body"]
    R3 -- "def ✓  @main ✓\npull_policy regressed\nwhen regressed" --> R4

    R4["🟢 Round 4\n+ enum toString() returns literal\nnot enum name"]
    R4 -- "all bugs resolved\noutput accepted" --> DONE

    DONE("✅ Clean gitlab-ci.yml")

    style R1 fill:#ff6b6b,color:#fff
    style R2 fill:#ffd93d,color:#333
    style R3 fill:#ffd93d,color:#333
    style R4 fill:#6bcb77,color:#fff
    style CTX fill:#4d96ff,color:#fff
    style DONE fill:#6bcb77,color:#fff
```

---

## Bug tracker

| Bug | R1 | R2 | R3 | R4 |
|---|:---:|:---:|:---:|:---:|
| `def` as template parameter (compile error) | ❌ | ❌ | ✅ | ✅ |
| `[comment @main/]` outside template body | ⚠️ | ❌ | ✅ | ✅ |
| `pull_policy: always` on every image | ❌ | ✅ | ❌ | ✅ |
| `when: on_success` on every job | ❌ | ✅ | ❌ | ✅ |
| `default` block over-indented | ❌ | ❌ | ✅ | ✅ |

> ⚠️ = bug present but not yet observed &nbsp; ❌ = broken &nbsp; ✅ = fixed

---

## Key findings

**Vague constraints don't work.** "Avoid reserved keywords" did not prevent `def`.
Naming the exact forbidden token in Round 3 fixed it permanently.

**Enum handling requires two separate constraints.** First: don't use `oclIsUndefined()`
on enum attributes (Round 2). Second: `toString()` returns the ecore `literal=` value
(lowercase), not the Java enum `name` (uppercase) — so comparisons must use the
literal string (Round 4). Getting the first without the second caused a regression.

**Recency bias.** Constraints placed after long reference files stick better.
All constraints in this step were placed at the end of the prompt (after the GHA
template reference), consistent with the ATL Round 6 finding.

**Compiles first try from Round 3 onward.** Once `def` was named explicitly,
no further manual fixes were needed.
