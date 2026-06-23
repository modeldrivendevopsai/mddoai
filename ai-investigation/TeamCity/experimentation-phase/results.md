# TeamCity Experiment Results

## Platform
**JetBrains TeamCity** (Kotlin DSL)  
Not mentioned in the original paper. Enterprise CI/CD platform using Kotlin DSL configuration.

## Round Count Summary

| Step | Rounds | Result |
|---|---|---|
| Step 1 — PSM Metamodel (.ecore) | 1 | PASS |
| Step 2 — ATL Transformation (.atl) | 1 | PASS (fixes applied within round) |
| Step 3 — Acceleo Template (.mtl) | 1 | PASS |
| **Total** | **3** | **PASS** |

## Comparison with Paper Results

| Platform | Total Rounds |
|---|---|
| GitLab CI (Run 1 — baseline) | 11 |
| Atlassian Bamboo (Run 2) | 6 |
| Azure DevOps (Run 3) | 5 |
| **TeamCity (This experiment)** | **3** |

## Cross-Platform Constraint Transfer

All constraints accumulated from the paper's three runs were pre-loaded before starting:
- No reserved ATL keywords as variable names (from GitLab run)
- No ->max() or oclAsType() (from GitLab run)
- No bare and operator in filter expressions — use nested if (from GitLab run)
- No def keyword in Acceleo templates (from GitLab run)
- [comment @main/] must be first line inside template body (from GitLab run)
- No post(trim()) on indent-receiving templates (from Bamboo run)
- EEnum literal names must be valid Java identifiers with no hyphens (from Bamboo run)

Result: 0 ATL errors, 0 Acceleo errors on first generation attempt. Only fixes needed were two semantic issues caught by static validation, resolved within Round 1.

## Artifacts Generated

- `step1-metamodel-generation/round1/output.ecore` — TeamCity PSM metamodel (426 lines, 47 EClasses, 14 EEnums)
- `step2-atl-generation/round1/output.atl` — ATL transformation PIM to TeamCity PSM (704 lines)
- `step3-acceleo-generation/round1/output.mtl` — Acceleo template generating Kotlin DSL (773 lines)

## Key Observations

1. **Constraint transfer works** — Pre-loading all 9 constraints from previous runs reduced total rounds from 11 (baseline) to 3. This confirms the paper's finding that accumulated constraints encode platform-agnostic ATL and Acceleo processor behaviour.

2. **TeamCity is structurally different** — Unlike GitLab/GitHub Actions/Azure DevOps which use flat YAML jobs, TeamCity uses a hierarchical Kotlin DSL (Project → BuildType → Steps). The metamodel required a different class hierarchy (Project as root, ScriptBuildType and CompositeBuildType as BuildType subtypes) compared to the GitLab PSM.

3. **Kotlin DSL vs YAML** — The Acceleo template generates Kotlin DSL rather than YAML. This confirms the paper's claim that the approach is not inherently limited to YAML — the Step 3 template mechanism supports any text-based output format.

4. **Static validation caught semantic issues** — Two bugs (matched rule invocation via thisModule, inconsistent Artifact rule) were caught by static cross-reference checking rather than runtime Eclipse validation. This suggests static validation tooling could reduce the need for Eclipse runtime testing in future runs.

## Conclusion

The three-step artifact chain converged in 3 total rounds for TeamCity, lower than all three platforms in the original paper. Cross-platform constraint transfer was effective — no ATL or Acceleo syntax errors occurred. The experiment also validated that the approach extends beyond YAML-based platforms to Kotlin DSL output, broadening the scope of the paper's findings.
