# Round 3 — Notes

## Run date
2026-04-08

## Context given
- `context/gitlabMM.ecore`
- `context/gha_generate.mtl`
- `context/gitlab-ci-docs.md`
- All Round 2 constraints retained
- Added: `def` named explicitly as forbidden parameter name
- Added: `[comment @main/]` must be first line inside template body

---

## Round 2 bugs — status in Round 3

### `def` reserved keyword — FIXED ✓
Parameter renamed to `glDefault`. No compile errors.

### `[comment @main/]` outside template body — FIXED ✓
Now correctly placed as first line inside `generateElement` template body.

### `pull_policy: always` on every image — REGRESSED ✗
Was fixed in Round 2 (manually). Round 3 LLM regenerated it broken.
Root cause identified: template compares `img.pullPolicy.toString() <> 'ALWAYS'` but
in Acceleo, `toString()` on an EMF enum returns the **literal** value (from `literal=`
in the ecore), not the enum **name**. The ecore defines `name="ALWAYS" literal="always"`,
so `toString()` returns `'always'` — the comparison against `'ALWAYS'` is always true,
causing `pull_policy` to be emitted on every image.

### `when: on_success` on every job — REGRESSED ✗
Same root cause as above. `j.when.toString()` returns `'on_success'` not `'ON_SUCCESS'`.
The check `<> 'ON_SUCCESS'` is always true, so `when` is emitted on every job.

### Variables indentation — FIXED ✓
Variables now serialized inline: `IMAGE_NAME: ${CI_REGISTRY_IMAGE}/...`

### `default` image indentation — FIXED ✓
Now correctly indented with 2 spaces under `default:`.

---

## New bugs found

### None — the two regressions above are the only remaining issues.

---

## Compiles in Eclipse Acceleo?
Yes — no compile errors.

## Runs successfully?
Yes. Output structurally valid but `pull_policy` and `when` emitted for every element
due to enum literal vs name mismatch in comparisons.
