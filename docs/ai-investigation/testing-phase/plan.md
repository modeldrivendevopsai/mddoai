# Experiment Plan

## Goal

Test whether context engineering alone is enough for an LLM to generate MDE artifacts
(ATL rules, metamodels, Acceleo templates) without fine-tuning.

---

## Phase 1 - ATL Extension (same CI/CD target, new inputs)

The metamodels, Acceleo template, and GitLab as target all stay fixed.
The LLM must extend the existing ATL rules to handle new SWArch inputs.

| # | Input | What it tests |
|---|---|---|
| 1 | Node.js / docker | New language, partial ATL support already |
| 2 | Go / docker | New language, zero ATL representation |
| 3 | Java / Maven | New build system, affects every ATL branch |
| 4 | Python + new SWArch attributes | Requires metamodel extension before ATL |

Status: 1-3 done, 4 pending.

---

## Phase 2 - Full New CI/CD Target (GitHub Actions)

Everything on the right side of the PIM must be generated from scratch.
The three steps are sequential and can only be validated together at the end.

**Step 2a** Generate GitHub Actions PSM metamodel (`githubMM.ecore`)
Context: `gitlabMM.ecore` + `pimMM.ecore` + GitHub Actions documentation

**Step 2b** Generate ATL transformation (`pim2githubmodel.atl`)
Context: `pim2gitlabmodel.atl` + `pimMM.ecore` + `githubMM.ecore` from 2a

**Step 2c** Generate Acceleo template (`generate_github.mtl`)
Context: `generate.mtl` + `githubMM.ecore` from 2a + example GitHub Actions YAML

Validation: Run the full chain on a SWArch input and inspect the final YAML.

---

## Key Observation

MDE artifacts cannot be tested in isolation. A wrong class name in the metamodel
silently breaks the ATL and Acceleo downstream. This dependency chain is itself a finding.
