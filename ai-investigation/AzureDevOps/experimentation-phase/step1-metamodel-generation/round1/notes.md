# Round 1 — Notes

## Run date
2026-04-11

## Context given
- `context/GHA.ecore` (ACICDTrip GitHub Actions metamodel)
- `context/azure-devops-docs.md` (Azure Pipelines YAML reference)
- Constraints pre-loaded: EEnum name= camelCase, literal= holds YAML string, defaultValueLiteral matches literal=

---

## Classes generated
<!-- fill in — class count, enum count -->

---

## Validation result in Eclipse EMF
Failed — 1 error:
- `A map entry class must have a feature called value`

Root cause: LLM generated a class intended to model key-value variable entries
using EMF's map entry convention (`EStringToStringMapEntry` pattern) but did not
include the required `value` EAttribute. EMF requires any class that acts as a
map entry to have an EAttribute named `value`.

---

## Compilable in Eclipse EMF?
No — fails validation with the map entry error above.

---

## Key findings
- All three pre-loaded constraints held — no defaultValueLiteral or hyphenated enum name violations
- New issue: map entry class modelling pattern not known to the LLM
- Fix: add explicit constraint — if a class is used as a map entry it must have a feature named `value`
- Round 2 adds this constraint
