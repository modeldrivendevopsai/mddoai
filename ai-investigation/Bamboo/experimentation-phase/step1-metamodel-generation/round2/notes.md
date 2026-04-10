# Round 2 — Notes

## Run date
<!-- fill in -->

## Context given
- `context/GHA.ecore` (ACICDTrip GitHub Actions metamodel)
- `context/pimMM.ecore` (platform-independent CI/CD metamodel)
- `context/bamboo-docs.md` (Bamboo Specs v10.0.2)
- Added Round 2 constraint: enum-typed defaults must use exact EEnum literal values

---

## Round 1 issue targeted
Round 1 failed Eclipse EMF validation due to invalid enum default literals:
- `GLOBAL` used instead of `global` for `REPOSITORY_SCOPE`
- `NOT_BROKEN` used instead of `not-broken` for `RELEASE_APPROVAL`

---

## Validation checklist
- [ ] Every `defaultValueLiteral` on enum-typed attributes matches a declared EEnum `literal`
- [ ] No case mismatch in enum defaults
- [ ] No enum-name-as-default (for example `NOT_BROKEN`) when the literal differs
- [ ] Eclipse EMF validation has zero errors

---

## Classes generated
<!-- fill in after running -->

---

## Compilable in Eclipse EMF?
<!-- fill in -->

---

## Key findings
<!-- fill in -->
