# Round 1 — Notes

## Run date
2026-04-11

## Context given
- `context/pimMM.ecore`
- `context/azuredevopsMM.ecore` (Step 1 Round 2 output)
- `context/cicd2gha.atl`
- Constraints pre-loaded: all 5 ATL constraints from GitLab and Bamboo

---

## Errors found
1 compile error:
- `mismatched input '''' expecting SEMI` — line 27, column 17

---

## Root cause
Line 27: `'variables[''' + self.reference.name + ''']';`

The LLM attempted to build the string `variables['name']` by embedding single
quotes using ATL's quote-doubling escape: `''''` means a string containing one
single quote `'`. However ATL's parser cannot resolve the token boundaries in
`'variables['''` — it reads the string as `variables[` followed by a stray `''`
token, then fails expecting a SEMI.

ATL does not support embedding single quotes inside string literals using the
`''''` pattern in expression context. The only safe approach is to avoid literal
single-quote characters in generated strings entirely, or restructure the
expression so no embedded quote is needed.

---

## Compilable / runs without error?
No — compile error at line 27.

---

## Key findings
- All 5 pre-loaded constraints held — no reserved keyword, no and, no oclAsType()
- New issue: ATL cannot embed single quotes in string literals using `''''` in
  expression context — parser fails to resolve token boundaries
- Fix: add constraint — never embed single quotes inside ATL string literals;
  restructure any expression that would require a literal `'` character in output
