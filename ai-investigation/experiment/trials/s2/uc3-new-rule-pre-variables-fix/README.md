# Archived: UC3 trials run before a task-spec gap was fixed

These 10 trials (all real, all `claude-sonnet-5`) were run while
`tasks/uc3-new-rule/requirements.md` was missing an item that both
`tasks/uc1-onboarding/requirements.md` and `tasks/uc2-migration/requirements.md`
already had: "uses pipeline-level variables for the image name/tag rather
than repeating a literal image string." The shared grader (`grade()` in
`run_direct_trial.py`) checks for this on every use case regardless, so
UC3 was being graded against a requirement its own task text never stated.

**This is kept, not deleted, because it's a real, interesting finding, not
just a bug to erase.** S2's real result here was 4/10 first-attempt success
(6 of the 10 failed on exactly this one check, nothing else — every failing
trial's `attempt0.yaml` and its own repaired `attempt1.yaml` are otherwise
identical, the secret-scan job, its ordering, and the blocking `needs`
chain were correct from the first attempt every single time). The
mechanism: S2's input is the bare `architecture.swarch` file, which has no
concept of pipeline variables at all, so whether to use one or hardcode the
image string is a genuine, ungrounded coin flip the model makes fresh on
every run, unlike S1 (see `trials/s1/uc3-new-rule-pre-variables-fix/README.md`),
whose PSM input already contains a real, structured `<variables>` element
it can just transcribe.

This is a real, legitimate illustration of how much input richness affects
reliability, even for a "simple" formatting-level requirement, not only for
complex design decisions — worth keeping as evidence even though the
specific 40% figure isn't the fair, final number for the paper (that number
conflated a real capability signal with an unstated requirement).

The corrected, fair version of this task (with the missing item added) is
run fresh into `../uc3-new-rule/` (not this folder). Those are the trials
counted for the paper; these are kept as the real evidence behind the
finding above.
