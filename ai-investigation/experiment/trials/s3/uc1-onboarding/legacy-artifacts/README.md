# S3 UC1 legacy artifacts

S3 has no automated harness (see the experiment root README, "Alignment with
the paper"), so these files predate the structured `trials/<approach>/<uc>/
trial<N>/` layout that `run_direct_trial.py` gives S1/S2. They were saved
ad hoc while diagnosing real bugs during S3's first real chain synthesis.

The two files `trials.csv` actually cites for S3 UC1:
- `trial 1` (failed): `trial1_attempt0.yaml`
- `trial 2` (succeeded): initial output same as trial 1's, final output
  `trial2_repaired.yaml`

(Both real, both `claude-sonnet-5`, no other model was ever used for any
counted trial in this dataset — the original filenames' "sonnet5" suffix
predates that being settled and is dropped here since there's nothing left
to distinguish it from.)

The rest (`s3_trial1_atl_final_failed.atl`, `s3_trial1_repair1.yaml`,
`s3_trial2_uc1_output.yaml`, `s3_trial3_atl_compiler_crash.atl`) are
intermediate/debug artifacts from that same real diagnostic session,
referenced narratively in `../../FINDINGS.md`'s bug write-ups, not each
their own separate counted trial.

