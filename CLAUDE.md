# CLAUDE.md — repo root

## What This Project Is

MDDOAI (Model-Driven DevOps AI) generates CI/CD pipeline configs from software architecture models, without requiring MDE expertise, via two tracks:

- **The MDE engine** (repo root): a Java/Eclipse EMF/ATL/Acceleo transformation chain, `SWArch → PIM → PSM → YAML`. See [README.md](README.md) for build/usage.
- **The AI product** (`ai/`): a chat-based agent system built on top of the same chain. See [ai/README.md](ai/README.md) and [ai/CLAUDE.md](ai/CLAUDE.md).

## Repo Structure

- `main/`, `meta_models/`, `code_generation/`, `designs/`, `feature/`, `update_site/` — the Java/Eclipse MDE engine and its transformation artifacts.
- `ai/` — the AI product: multiple services (chat UI, backend API, and supporting agents) built on top of the transformation chain. Mostly separate from the Java/Eclipse engine, with one narrow, explicitly documented exception. See `ai/CLAUDE.md` for the documented folder boundaries and the exception's exact scope.
- `mddoai-design-system/` — the on-brand component library and Claude Design skill (`/mddoai-design`). Read `mddoai-design-system/project/SKILL.md` before doing UI work.
- `docs/` — misc project docs.
- `pipeline_tests/`, `install_necessary_packages/`, `viewpointrepresentations/` — supporting material for the MDE engine; match a new file's placement to the existing sibling closest to its purpose.
- `logo/` and top-level project docs — general project branding and documentation, shared across the MDE engine and the AI product.
- If a directory doesn't appear anywhere in this list, that's a gap in this section to flag and fix, not a signal that a file placed there is automatically wrong.

## Agents in `.github/agents/`

These are GitHub Copilot agent definitions, run from VSCode's Copilot Chat agent picker, not tools Claude Code can invoke directly. When a task matches one of their purposes, open the `.agent.md` file and carry out its steps directly instead of trying to call it as a tool. `lint-reviewer`, `oop-reviewer`, and `coverage-reviewer` overlap with the independent-review need described under [Review](#review) below for Java changes specifically; the Review section's `/code-review`/subagent mechanism is what Claude Code itself can actually run.

- `pr-logic-reviewer` — review a PR's actual logic/diff (`pr=<number>`)
- `pr-description-generator` — write a PR description from the current branch's diff against main
- `coverage-reviewer` — run the Gradle suite, report JaCoCo coverage gaps by class
- `lint-reviewer` — Java formatting/lint issues (naming, method length, magic numbers, nesting)
- `oop-reviewer` — Java OOP design quality (SOLID, code smells, encapsulation)
- `test-fixtures-updater` — after a transformation change, re-run swarch2pim/input1.swarch and update expected + downstream fixtures

## Git Workflow — read before pushing or merging

- **Never force-push `main`, full stop.**
- **Never rebase or force-push any other branch that's already been pushed to origin.** Check first with `git ls-remote --heads origin <branch>`, if that returns nothing, the branch is local-only and rebasing is safe. If it returns a ref, merge instead. This project has already had one real incident where a force-push wiped pushed commits before a stripped-down version got merged into `main`, recovering required a separate revert PR. Don't repeat it.
- **Merge `main` into your feature branch, not the other way around.** Never merge a feature branch directly into `main` outside of a reviewed PR.
- **Do not commit unless explicitly asked.** Wait for a direct instruction to commit.
- **If you do commit, keep the message to 5-6 words, one line.** No large multi-paragraph bodies.
- **Do not add a co-author line to any commit.**
- **Run `git status` before any destructive command** (`checkout --`, `restore`, `reset --hard`, `clean`) on a path that might have uncommitted work.
- **Confirm before merging PRs**, even on your own branches, unless explicitly told to proceed autonomously.
- **Name new branches `<type>/<short-description>`** (`feature/`, `fix/`, `docs/`, `refactor/`). Some existing branches (the `feature/*` and `docs/*` ones) already use this pattern; use it going forward.

## Engineering Standards

A change is **non-trivial** if it touches more than one small file, changes a public interface or contract, changes behavior a test could observe, or touches shared/production config. A one-line fix or a pure rename is trivial. A decision meets the same bar if the change it leads to would meet it. This definition is what "nontrivial"/"non-trivial" means everywhere it's used below.

### Before you build

- Before writing new code for a capability, check whether a well maintained library, an established design, or an existing pattern already in this repo solves it (grep this repo for similar functionality; check `build.gradle` or the relevant `requirements.txt`/`package.json` for an already-available library before adding a new dependency). Write custom code only when nothing suitable exists, or there's a specific, stated reason existing options don't fit.
- For a nontrivial technical decision, do a short, time-boxed research pass comparing the realistic options before committing to an implementation. This is sometimes called a spike: a small, bounded investigation whose only output is a decision, not production code. Note briefly why the chosen approach won.
- When a nontrivial change restructures more than one file (splitting, merging, or relocating modules), decide the complete target layout before making the first move, not incrementally per file. Re-deciding a boundary one file at a time wastes real work, leaves the codebase in an incoherent intermediate state between each step, and usually means the earlier moves get redone once the fuller shape becomes clear anyway.

### Design

- **Keep things loosely coupled.** When one part of the system needs something from another part, prefer a well defined interface, such as an HTTP API or a function with a clear contract, over reaching into another module's internals or shared global state.
- **Give each function, class, or service one clear job.** A change in one place should have a small, predictable effect, not a ripple through unrelated code. A function or method that's grown past roughly 40-50 lines, or is nested more than 3 levels deep, is a signal to split it.
- **Give each file or folder one coherent purpose too, not just each function.** A module that accumulates several genuinely distinct concerns, multiple independent pipeline stages, a mix of generic and one-specific-case's logic, several unrelated route groups, should be split so each concern gets its own file or folder. Prefer more, clearly-named, single-purpose files over one larger file doing several unrelated things: it's easier to find the piece you need, easier to test in isolation, and a change to one concern can't accidentally ripple into another living in the same file. This is the same one-clear-job principle as the bullet above, just applied to physical organization, not just behavior.
- **Do not hardcode values that can change.** A URL, a port, a timeout, a feature flag, a threshold, a secret: all belong in an environment variable or a config file, never a literal buried in source. Never commit a real secret or credential.
- **Don't name a specific internal file path in a code comment purely to point the reader elsewhere.** A comment like "see X.py for how this works" goes stale the moment that file moves, renames, or splits, and scattered comments like this are easy to miss when it does. Describe the concept or relationship instead of the path, what the other code does, not where it lives. Exception: references within one coherent, actively-maintained package pointing at its own sibling files (e.g. a stage folder's own `__init__.py` naming its submodules), where the path itself is genuinely the point.
- **Name and explain non-obvious constants.** If a number isn't self-explanatory, give it a name and a short comment on where it came from: measured, a library default, or an engineering guess.
- **Build only what the current task needs (YAGNI, "you aren't gonna need it").** Don't add options, abstractions, or generalized code paths for a need you're only guessing at: a config system for one deployment target, a plugin architecture for one plugin, generalized dispatch built around a single real case. **This does not cover basic structure for concerns that already concretely exist.** If two or more distinct, real things already sit flattened into one file or folder today, giving them their own files or modules is normal engineering hygiene, not speculative generalization, even if a third might join later. Don't invoke YAGNI to justify skipping real loose coupling or real separation of concerns that are already real, only to defend against ones that are still hypothetical.
- **Depend on abstractions, not specific implementations**, so an implementation can change without every caller changing with it (e.g. a service depends on an interface like `PaymentGateway`, not directly on a `StripeClient`).
- **A convenience interface must invoke the same real action the thing it fronts would use, not a separate, weaker echo of it.** A chat tool, a wizard step, or a shortcut endpoint that wraps an existing capability (running a job, changing state, calling an external API) must trigger that capability's actual state-changing path, not a parallel implementation that only logs, previews, or summarizes while the real underlying action goes untouched. If a capability is genuinely meant to be preview-only (no real effect), say so explicitly in its own name/description, don't let that scope narrow silently as a side effect of how it happened to get built. When it's unclear whether a new capability should have full real effect or stay preview-only, ask, don't default to preview as the "safe" choice, since that default can silently contradict a stated product direction (e.g. an interface meant to eventually replace manual action entirely).
- **When choosing a structural boundary (a new file, folder, package, or service), weigh any concrete, already-stated future direction for that piece, not just today's literal need.** This is not license to build speculative capability, that's still YAGNI's territory. It applies only when a future need has actually been decided or stated somewhere, not merely imagined ("someone might want this someday" is still speculation; "this will need a different runtime soon, per an actual stated plan" is not):
  - A piece with a stated, concrete plan to need a different runtime, an independent deploy, or independent scaling soon can get its own boundary now, so it doesn't need a disruptive redesign when that future arrives.
  - A piece with a stated, concrete plan to repeat along some axis (e.g. "every item in this category will eventually grow its own extra module") can get a boundary shaped for that repetition now (a subfolder per item, not one shared file), so the next real instance is a new sibling file, not a restructuring of an existing one.
  - The stated direction must come from the user or existing project docs, never your own inference about what might be needed later.
  - Record it in writing as part of the same change (a short code comment at the boundary, a note in the relevant README, or a linked issue), so an independent reviewer with no memory of this conversation can verify the justification from the diff alone.

### Testing

- **Test every feature end to end before it's considered done.** A passing mocked/unit test suite isn't sufficient. Run the feature against its real dependencies (real service, real DB, real external API where feasible), and confirm the actual input and output, not just that an assertion passed.
- **If a feature runs in Docker in production, test it in Docker**, not only on the host. A container can behave differently: different base image, missing dev tools, different network resolution between services.
- **Don't break what already works.** Run the full existing test suite for the area you touched, not just tests for the new change. If you touched something shared (a shared config, a compose file wiring multiple services together), check what else depends on it.

### Review

- **After a non-trivial implementation or feature, and before committing it, get an independent review, don't self-certify.** Use the `/code-review` skill for general correctness/reuse/simplification, or spawn an independent, foreground agent (subagent type `Plan`, read-only) as a reviewer with a self-contained prompt that quotes the relevant rules from this file and points it at the real changed files. Either way it should have no memory of the conversation that produced the change, so it forms its own judgment instead of rubber-stamping the reasoning that led there. A reviewer that only sees the diff, not the justification, catches more. If asked to commit non-trivial work that hasn't been reviewed yet, say so and run the review first, then commit: an explicit request to commit doesn't waive this.
- **For a change touching auth, secrets, or handling of user-supplied input, also run `/security-review` before merging.**
- **Tell the reviewer to be strict.** Cite an exact file and line for every finding. Verify claims by reading the real files and running real commands, not by trusting a description of what changed. State plainly when a category has no findings instead of praising it or staying silent. A review that finds nothing wrong should be the rare outcome, not the default one.
- **The reviewer must check file placement, not just file presence.** For every new file in the diff, it must confirm the file sits in the repo-structure section that actually owns that kind of content, not merely that files exist somewhere reasonable-looking. A new file in the wrong section is a finding on its own, even if the file's contents are otherwise correct, unless the directory it's in has no entry anywhere in [Repo Structure](#repo-structure) yet, in which case that's a gap in this file to flag and fix, not automatically a misplaced file.
- **This placement check also covers files a structural change stranded, not only files it created.** Splitting or moving a module can leave an existing file, most often a test file, sitting in a location that matched the old structure but not the new one, e.g. a test still living next to code that moved out from under it. When reviewing a change that splits, moves, or renames a module, the reviewer must check every file that tests or references the moved code for this, not only files the diff adds.
- **Re-verify every finding yourself before acting on it or dismissing it.** A subagent's report describes what it believes it found, not necessarily ground truth. Confirm against the real file before changing anything, and before telling the user something is fine.

### Scope

- **Keep changes scoped to the task at hand.** Touch only the files a task actually needs. If you notice an unrelated problem while working, note it separately instead of folding a fix into the current change.
- **Keep a commit small: one logical change.** Not several unrelated things bundled together. A commit you can describe in one sentence is usually the right size.
- **Do not put files in random places.** Every new file belongs in the section of the repo structure that already owns that kind of content (test fixtures under the matching test-resources tree, docs under `docs/`, AI-layer code under `ai/`, and so on, per [Repo Structure](#repo-structure)). If no existing location fits, decide where the file's proper home is before creating it, don't default to the repo root or the nearest convenient folder. If [Repo Structure](#repo-structure) itself has no entry for the directory you land on, add one as part of the same change, rather than leaving the map incomplete.

### Documentation

- Create or update documentation for any feature you add or change, as part of that same change, not as a followup.
- State the current design only. Never reference a document's own edit history: no "this used to say X," no "previously Y, now Z," no meta-commentary about a rewrite. Write only what's true now, as if it was always written this way.
- Avoid hardcoding anything likely to go stale: no exact prices, exact counts, narrow enumerated lists, or absolute claims like "the only file that..." or "the whole app is..." about a part of a system that changes. Describe a file or service's role, not that it's uniquely or exclusively the one with that role, so the sentence stays true after the system grows.
- Don't name specific files, classes, or examples in prose purely as illustration unless the rule genuinely depends on that exact name. A phrase like "e.g. `FooBar`, `BazQux`" goes stale the moment one of those is renamed, moved, or removed, even though the rule itself never changed, and it invites an edit here on every commit that happens to touch one of the named things. State the rule by its real, durable shape instead: a naming pattern (`*Cli.java`), a glob, a path, or a structural description, so a future rename or addition never requires editing this file.
- Write for a colleague who has never done this task before, not someone who already knows the process.
- Be clear: short sentences, one idea per sentence, active voice, plain words over jargon. Define jargon on first use.
- Break up any sentence doing more than one job.
- Prefer a numbered step or a short bullet list over a dense paragraph wherever the content is actually a sequence.
- Don't sacrifice accuracy for simplicity. Simplify the wording, never the substance; keep every gotcha and warning.
- No em dashes inside explanatory sentences or paragraphs. Use a comma, a period, or restructure the sentence. The `` `item` — description `` label-list style used elsewhere in this file (Repo Structure, Agents, section subtitles) is a structural convention, not prose, and stays exempt.
