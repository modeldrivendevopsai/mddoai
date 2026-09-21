# S3 UC2/Bamboo chain (run `7477a88eecab4ef59bae4555c54f4c7a`)

- `atl_source.atl` — the real, synthesized ATL transformation (PIM → Bamboo PSM).
- `acceleo_source.mtl` — the real, synthesized Acceleo template (Bamboo PSM → YAML).
- `psm.ecore` — the real, synthesized target metamodel this chain conforms to.
- `psm_instance.xmi` — the real PSM instance this run produced.
- `output.yaml` — the real final generated pipeline (`fullstack-webapp.yaml`).
- `run_manifest.json` — the run's own manifest from `integration_runner`.

`atl_source.atl`, `acceleo_source.mtl`, and `psm.ecore` are also what the
Bamboo-targeting UC3 test in `../../uc3-new-rule/trial1/` reuses unmodified.
