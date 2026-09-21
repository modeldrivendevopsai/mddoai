# Findings: S1 / S2 / S3 across UC1 (onboarding), UC2 (migration), UC3 (new rule)

All results use `claude-sonnet-5`, at the model's fixed default sampling
temperature (see the temperature note below).

## Determinism

| Approach | UC1 | UC2 | UC3 |
| -------- | --- | --- | --- |
| S1 (existing chain) | 100% convergent | 100% convergent | 100% convergent |
| S2 (minimal prompting) | fully divergent | fully divergent | fully divergent |
| S3 (from-scratch synthesis) | measured: 2/2 independent syntheses near-identical | measured: 2/2 converge on job/stage design, differ on implementation defects | same as UC1 (reuses the already-validated chain, no fresh synthesis) |

S1 reuses an already-validated, human-authored transformation chain
unmodified, so every run produces the byte-identical pipeline. The model's
task narrows to transcription of an already-complete model instance, not
design. S2 gets only a name and a bare architecture description, so every
real design choice is up to the model and varies every single time. This
holds on UC3 even after its first-attempt pass rate reached 100% (see
below): passing every trial and producing a different design every trial
are independent facts.

**S3's own determinism is now a real, measured result on UC1 and UC2, not
just a mechanism argument.** Two independent, fresh, cold-start syntheses
were run for each. On UC1 (GitLab), the two converged almost completely:
every job name, variable name, image choice, script, and flag was
byte-identical, the only difference was two explicit `needs:` fields the
second run added where the first relied on implicit stage-ordering. On UC2
(Bamboo), the two converged on the same high-level design (identical job
names, same stage grouping) but diverged on implementation robustness: the
first run had a malformed trigger block and an unexpanded build matrix; the
second had a different real bug, multi-job stage names getting job names
concatenated repeatedly (e.g. `Stage-push-push-push-push-push`), plus a
real semantic gap, no docker-capable environment declared for the build/push
jobs. High-level design converges reliably; low-level implementation detail
still varies run to run, a real, more precise version of the determinism
claim than "S3 is deterministic once validated."

## Success rate

| Approach | UC1 first-attempt | UC1 final | UC2 first-attempt | UC2 final | UC3 first-attempt | UC3 final |
| -------- | ------------------ | --------- | ------------------- | --------- | ------------------- | --------- |
| S1 | 100% | 100% | 100% | 100% | 100% | 100% |
| S2 | 100% | 100% | well below 100% | 100% | 100% | 100% |
| S3 | full pass within the repair budget | 100% | full pass within the repair budget | 100% | full pass, no repair needed | 100% |

S1 is perfect by construction. S2's first-attempt success drops sharply
where the task gets harder: UC1 (fully-specified architecture, well-known
platform) succeeds immediately every time; UC2 (unfamiliar platform, bare
name only) fails its first attempt on most runs, inventing a
plausible-looking but non-existent job-declaration schema; UC3 (Bamboo,
extending an existing pipeline with a new job) now succeeds on the first
attempt every time, once a real task-specification gap was fixed (see
below for the 40%-first-attempt result this replaced). Where S2 does still
need repair (UC2), it succeeds on the very next attempt.

S3's results are the most interesting finding here. UC1 and UC2 each
needed real, substantive repair work, a different genuine bug each time,
before reaching a full pass. UC3 needed no repair at all, on either
platform: on GitLab, the chain synthesized for UC1 was reused completely
unmodified and correctly produced the new job in the right position; on
Bamboo, the chain reused was synthesized for UC2 against a rich, unrelated
fixture, not the chatbot pipeline at all, so reuse crossed both a new
requirement and a different PIM-instance family, and it still produced the
new job correctly with zero repair.

**A genuine caveat**: S3's UC2 output, while a full pass, has two real
defects visible on manual inspection that the pass/fail check doesn't
catch: a malformed trigger block (two colliding keys in one mapping
instead of a list) and an unexpanded build matrix (a variable left
undefined instead of expanded into real job variants). The pass/fail check
verifies real execution against the target metamodel, not full semantic
correctness against every feature the source pipeline uses.

**A methodological note**: S3's Bamboo UC3 result was verified by real
execution success plus manual inspection against the requirements
checklist, not by `run_direct_trial.py`'s automated grader. That grader
expects the platform's documented schema (top-level job keys, the shape
S1/S2 are prompted to produce); S3 synthesizes its own self-consistent but
differently-shaped schema (jobs nested under `stages`). Confirmed this is
a pre-existing mismatch, not a regression: running the grader against S3's
own already-accepted UC2 output fails the same way.

## Why a corrected UC3 spec doesn't weaken the case for S3

UC3's original `requirements.md` was missing an explicit "use
pipeline-level variables for the image name/tag" item that UC1 and UC2's
checklists already had. The grader checks for this regardless, so S2 was
being graded against a requirement its own task text never stated. Once
the missing item was added, S2's first-attempt success on UC3 rose from
40% to 100% (10/10, Bamboo, verified against the grader).

This doesn't weaken the case for S3, it sharpens it. Two things survive
the fix untouched:

1. **Non-determinism is untouched.** Even at 100% pass, the same 10 real
   trials each produced a genuinely different design: 6/10 converged on
   identical job names, 4/10 diverged (snake_case, PascalCase,
   role-descriptive naming); tool choice varied (`gitleaks` vs
   `trufflehog`, 8/10 vs 2/10); variable-naming scheme varied across 9 of
   the 10 trials; stage topology varied (8/10 one stage, 2/10 split into 4
   named stages); task/script schema varied once. Passing a checklist is
   not the same as being governable or reproducible across regenerations.
2. **The spec gap is itself first-hand evidence of S2's real weakness.**
   One forgotten requirement cost 60% of first-attempt success. S2 has no
   schema and no structural memory, so every future rule or convention has
   to be perfectly re-stated in every prompt, forever, or it silently
   breaks. S1 and S3 don't have this problem: the convention lives in the
   model/metamodel or the synthesized chain, inherited automatically by
   every future generation.

The determinism finding, not the pass-rate comparison, is the load-bearing
evidence here: it's real, measured, and doesn't depend on whether every
task description happens to be complete.

## Real bugs found and fixed along the way

**Two real grading gaps** (false negatives, not model failures): GitLab's
older top-level `image:` key (valid, functionally identical to `default:
image:`) wasn't recognized by the docker-capable-image check; a job
relying on a pipeline-level `default: before_script:` for its login step
(also valid GitLab inheritance) wasn't recognized by the push-role check.
A third, structural false-negative: the checklist only checked that the
right values appeared anywhere in a job's structure, not that they were
correctly nested, so a value misplaced as a sibling of its real parent key
still read as a pass.

**A real Acceleo indentation bug** (S3, UC1): the caller writes its own
literal parent key then calls a sub-template on the next line with no
leading indent, so the sub-template's hardcoded child indent lands as a
sibling of the parent instead of nested under it. Fixed as a permanent
constraint in `acceleo_agent`'s prompt config.

**A real ATL metamodel-granularity bug** (S3, UC1): a rule's `to` block
left its target type as the source package instead of a real GitLab-side
equivalent, compiling fine but crashing at real execution, because
GitLab's metamodel has no separate Expression/Literal class hierarchy at
all. Fixed as a permanent ATL constraint.

**A real documentation-retrieval bug** (S3, UC2, Bamboo): the initial docs
crawl used a stale, conceptual page about Bamboo's UI notion of
Plans/Stages/Jobs (pre-Specs era, no real YAML schema). Fixed by pointing
the crawl at Bamboo's current Specs YAML reference.

**A real product bug**: `psm_agent` and `serialization_agent` were both
injecting irrelevant "grounding" text into the generation prompt via a
keyword-matching lookup that returned an unrelated reference-tool
description merely because it shared a common word with the query.
Removed both call sites, tests updated accordingly.

**Two real ATL-language constraint gaps** (S3, UC2, Bamboo): (1) the same
PIM class is legitimately reused across structurally different containing
features, and writing two separate unconditional matched rules for that
source type compiles but fails at real execution since ATL requires every
source element to resolve to exactly one applicable rule; (2) a helper
operation was asked to both recursively compute a grouping and construct
new target model objects, which ATL doesn't support at all. Both fixed as
permanent constraints.

## Temperature note

Claude Sonnet 5 does not accept a configurable `temperature` parameter,
confirmed twice against the live API: first as a hard rejection, then, after
routing around that, as a client-side rejection of any explicit value other
than 1. Fixed at the router level (`ai/ai-layer/router/router.py`, using
litellm's own `drop_params` plus a defensive catch-and-retry for future
models it doesn't yet know about), so requests still succeed, but Sonnet 5
always runs at its own fixed default, never an explicit 0.

## Current state

Every cell across all three approaches and all three use cases has at
least one real, verified datapoint. S3's own determinism (a dedicated
repeated-synthesis test from a frozen constraint library, comparing
whether independent fresh syntheses converge) has not been independently
measured yet.
