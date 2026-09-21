# S3 UC3/Bamboo

Reuses `../../uc2-migration/trial1/`'s chain (`atl_source.atl`,
`acceleo_source.mtl`, `psm.ecore`) unmodified against
`tasks/uc3-new-rule/reference/input.pimmm`.

- `psm_instance.xmi` — real ATL output.
- `output.yaml` — real Acceleo output.

Result: zero repair needed, all 7 requirements checklist items pass on
manual inspection (`run_direct_trial.py`'s automated grader doesn't parse
S3's own schema, same as its UC2 output).
