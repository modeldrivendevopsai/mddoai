# Archived: UC3 trials run before a task-spec gap was fixed

These 10 trials (all real, all `claude-sonnet-5`) were run while
`tasks/uc3-new-rule/requirements.md` was missing an item that both
`tasks/uc1-onboarding/requirements.md` and `tasks/uc2-migration/requirements.md`
already had: "uses pipeline-level variables for the image name/tag rather
than repeating a literal image string." The shared grader (`grade()` in
`run_direct_trial.py`) checks for this on every use case regardless, so
UC3 was being graded against a requirement its own task text never stated.

**This is kept, not deleted, because it's a real, interesting finding, not
just a bug to erase.** S1's real result here was 10/10 first-attempt
success on this exact check, because S1's PSM input
(`reference/psm_instance.gitlabmm`) already contains a structured
`<variables>` element with the real `IMAGE_NAME`/`IMAGE_TAG` values — S1
transcribes an answer that's already explicitly present in its input, it
never has to invent one. See `trials/s2/uc3-new-rule-pre-variables-fix/README.md`
for the contrasting S2 result, where the same missing task-spec item
produced real, substantive failures, because S2's input has no equivalent
structural signal at all.

The corrected, fair version of this task (with the missing item added) is
run fresh into `../uc3-new-rule/` (not this folder). Those are the trials
counted for the paper; these are kept as the real evidence behind the
input-richness finding above.
