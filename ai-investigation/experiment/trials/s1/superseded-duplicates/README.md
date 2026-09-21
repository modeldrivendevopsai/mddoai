# Superseded duplicate runs, not corrupted data

These two folders are real, valid uc3-new-rule trial runs (`model_used:
claude-sonnet-5`, correct `task` field, real success/timing data), but they
are not cited by any row in `trials.csv`. Each duplicates the trial number
of an already-counted, later run at `../uc3-new-rule/trial<N>/`, made when
an earlier version of `run_direct_trial.py` still wrote to
`trials/<approach>/trial<N>/` without the task name in the path. Once that
path convention was fixed, the same nominal trial number was run again into
the new, task-scoped location, which is the run `trials.csv` actually
counts. These are kept for transparency rather than deleted: nothing here
is mislabeled, they are simply not the counted copy.
