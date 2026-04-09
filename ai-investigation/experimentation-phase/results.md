# Experiment Results — LLM-Assisted MDE Artifact Generation for GitLab CI/CD

## What we did

We used an LLM (Claude) to generate all three MDE artifacts needed to transform a
platform-independent CI/CD model into a `.gitlab-ci.yml` file. Each artifact was
generated from scratch using only context provided in the prompt. Bugs were fixed
by adding one constraint per round — not by editing the output manually.

---

## The MDE pipeline

```mermaid
flowchart LR
    SW["SWArch model"]
    PIM["PIM<br/>platform-independent<br/>CI/CD model"]
    PSM["GitLab PSM<br/>platform-specific<br/>model"]
    YAML[".gitlab-ci.yml"]

    MM1["Step 1<br/>gitlabMM.ecore"]
    MM2["Step 2<br/>pim2gitlab.atl"]
    MM3["Step 3<br/>generate.mtl"]

    SW -->|existing GHA chain| PIM
    MM1 -.->|defines schema| PSM
    PIM -->|ATL| PSM
    PSM -->|Acceleo| YAML
    MM2 -.->|transforms| PIM
    MM3 -.->|generates| YAML

    style MM1 fill:#4d96ff,color:#fff
    style MM2 fill:#4d96ff,color:#fff
    style MM3 fill:#4d96ff,color:#fff
    style YAML fill:#6bcb77,color:#333
```

---

## How context engineering worked

Each step followed the same loop. Context given every round: the ACICDTrip GHA artifact
of the same type (syntax reference) + the GitLab PSM metamodel + GitLab CI/CD keyword docs.

```mermaid
flowchart LR
    C["Context<br/>(metamodels +<br/>reference artifact +<br/>docs)"]
    G["LLM generates<br/>artifact"]
    T["Compile and run<br/>in Eclipse"]
    B{"Bug?"}
    A["Add one constraint<br/>to prompt"]
    D["Done"]

    C --> G --> T --> B
    B -->|yes| A --> G
    B -->|no| D

    style D fill:#6bcb77,color:#333
    style C fill:#4d96ff,color:#fff
```

---

## Step 1 — Metamodel (`gitlabMM.ecore`)

**1 round. Clean first try.**

| | |
|---|---|
| Context given | GHA metamodel + GitLab CI/CD docs |
| Output | 20 classes, 6 enums |
| Class name accuracy | 20/20 |
| Manual fixes | None |

The LLM correctly filtered GitHub-specific constructs without being told to.
It chose a flatter design than the GHA reference (e.g. `script : EString[*]`
instead of `Script → Command[*]`) — a valid simplification that downstream steps built on.

---

## Step 2 — ATL Transformation (`pim2gitlab.atl`)

**6 rounds.**

```mermaid
flowchart TD
    CTX["Base context — every round<br/>─────────────────────<br/>pimMM.ecore<br/>gitlabMM.ecore (Step 1 output)<br/>cicd2gha.atl (GHA ATL reference)"]

    R1["Round 1<br/>No constraints"]
    R2["Round 2<br/>+ treat cicd2gha.atl as the only<br/>valid source of ATL/OCL syntax"]
    R3["Round 3<br/>+ only use OCL collection ops<br/>that appear in the reference"]
    R4["Round 4<br/>+ never use 'and' as boolean op<br/>+ no reserved keywords as<br/>target variable names"]
    R5["Round 5<br/>+ never use oclAsType();<br/>use context helpers instead"]
    R6["Round 6<br/>same constraints as Round 5<br/>moved to AFTER the reference file"]
    DONE["Clean run — correct XMI output"]

    CTX --> R1
    R1 -- "def keyword — compile error<br/>and in select — runtime crash" --> R2
    R2 -- "->max() unsupported — runtime crash<br/>and in if — latent bug" --> R3
    R3 -- "rule keyword — compile error<br/>and regressed — runtime crash<br/>->max() fixed" --> R4
    R4 -- "oclAsType() unsupported — runtime crash<br/>keyword fixed   and fixed" --> R5
    R5 -- "rule keyword again — compile error<br/>oclAsType() fixed" --> R6
    R6 --> DONE

    style CTX fill:#4d96ff,color:#fff
    style R1 fill:#ff6b6b,color:#fff
    style R2 fill:#ff6b6b,color:#fff
    style R3 fill:#ff6b6b,color:#fff
    style R4 fill:#ff6b6b,color:#fff
    style R5 fill:#ffd93d,color:#333
    style R6 fill:#6bcb77,color:#333
    style DONE fill:#6bcb77,color:#333
```

**Recency bias (Round 6):** The reserved keyword bug (`def` → `rule` → `rule`) persisted
through Rounds 1, 3, and 5 despite an explicit constraint. In Round 6 the only change was
moving all constraints to after the 754-line ATL reference, immediately before the task.
The bug disappeared. Constraint placement matters as much as constraint content.

---

## Step 3 — Acceleo Template (`generate.mtl`)

**4 rounds.**

```mermaid
flowchart TD
    CTX["Base context — every round<br/>─────────────────────<br/>gitlabMM.ecore (Step 1 output)<br/>gha_generate.mtl (GHA Acceleo reference)<br/>gitlab-ci-docs.md (keyword reference)"]

    R1["Round 1<br/>No constraints"]
    R2["Round 2<br/>+ do not use oclIsUndefined() on enums<br/>+ skip enum attribute when value is default<br/>+ main template named generateElement<br/>+ no reserved keywords as param names"]
    R3["Round 3<br/>+ def is explicitly forbidden<br/>+ @main must be first line inside<br/>the template body, not before it"]
    R4["Round 4<br/>+ enum toString() returns the ecore<br/>literal value, not the enum name<br/>— compare against lowercase literal"]
    DONE["Clean run — valid gitlab-ci.yml"]

    CTX --> R1
    R1 -- "def keyword — 18 compile errors<br/>@main placed outside template" --> R2
    R2 -- "def still broken (too vague)<br/>@main regressed as new bug<br/>pull_policy fixed   when fixed" --> R3
    R3 -- "def fixed   @main fixed<br/>pull_policy regressed<br/>when regressed" --> R4
    R4 --> DONE

    style CTX fill:#4d96ff,color:#fff
    style R1 fill:#ff6b6b,color:#fff
    style R2 fill:#ffd93d,color:#333
    style R3 fill:#ffd93d,color:#333
    style R4 fill:#6bcb77,color:#333
    style DONE fill:#6bcb77,color:#333
```

**Enum regression (Round 3→4):** The LLM compared `toString()` against the enum name
(`'ALWAYS'`) but Acceleo returns the ecore literal (`'always'`). Two constraints were
needed — one for the semantics (skip defaults) and one for the API detail (use the literal).

---

## Cross-step summary

| | Step 1 | Step 2 | Step 3 |
|---|:---:|:---:|:---:|
| Artifact | Ecore metamodel | ATL transformation | Acceleo template |
| Rounds needed | **1** | **6** | **4** |
| Compile errors round 1 | 0 | 1 | 18 |
| Runtime errors round 1 | 0 | 1 | 1 |
| Manual fixes | 0 | 1 | 0 |
| Class name accuracy | 20/20 | 15/15 | 19/19 |

---

## Key findings

1. **Domain knowledge transfers cleanly.** All class names, YAML keys, and structural
   mappings were correct from Round 1 in every step. Errors were never about understanding
   GitLab CI/CD — always about tool-specific quirks.

2. **The bugs are tool quirks, not domain gaps.** Every error came from ATL/Acceleo
   behaviour that differs from mainstream OCL/Java: `and` not short-circuit,
   `->max()` unsupported, `oclAsType()` unsupported, enum `toString()` returning
   the literal not the name, `def`/`rule` as reserved keywords.

3. **Vague constraints don't stick.** "Avoid reserved keywords" did not stop `def` or
   `rule`. "Use only OCL operations from the reference" did not stop `and`. Every
   constraint that worked named the exact forbidden construct explicitly.

4. **Recency bias is measurable.** Moving identical constraints to after a long reference
   file eliminated a 3-round recurring bug immediately. Instruction placement in
   long-context prompts is a controllable variable with a measurable effect.

5. **Three context artifacts were sufficient for all steps.** Metamodel + syntax reference
   + keyword docs. No step required anything beyond this base set plus targeted constraints.
