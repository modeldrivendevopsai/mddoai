# CLAUDE.md — repo root

## What This Project Is

MDDOAI (Model-Driven DevOps AI) generates CI/CD pipeline configs from software architecture models, without requiring MDE expertise, via two tracks:

- **The MDE engine** (repo root): a Java/Eclipse EMF/ATL/Acceleo transformation chain, `SWArch → PIM → PSM → YAML`. See [README.md](README.md) for build/usage.
- **The AI product** (`ai/`): a chat-based agent system built on top of the same chain. See [ai/README.md](ai/README.md) and [ai/CLAUDE.md](ai/CLAUDE.md).

## Repo Structure

- `main/`, `meta_models/`, `code_generation/`, `designs/`, `feature/`, `update_site/` — the Java/Eclipse MDE engine and its transformation artifacts.
- `ai/` — the chat-ui + ai-layer product, isolated from the Java engine. See `ai/CLAUDE.md` for folder boundaries.
- `mddoai-design-system/` — the on-brand component library and Claude Design skill (`/mddoai-design`). Read its `SKILL.md` before doing UI work.
- `docs/` — misc project docs.

## Agents in `.github/agents/`

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

## Engineering Standards

### Before you build

- Before writing new code for a capability, check whether a well maintained library, an established design, or an existing pattern already in this repo solves it. Write custom code only when nothing suitable exists, or there's a specific, stated reason existing options don't fit.
- For a nontrivial technical decision, do a short, time-boxed research pass comparing the realistic options before committing to an implementation. This is sometimes called a spike: a small, bounded investigation whose only output is a decision, not production code. Note briefly why the chosen approach won.

### Design

- **Keep things loosely coupled.** When one part of the system needs something from another part, prefer a well defined interface, such as an HTTP API or a function with a clear contract, over reaching into another module's internals or shared global state.
- **Give each function, class, or service one clear job.** A change in one place should have a small, predictable effect, not a ripple through unrelated code.
- **Do not hardcode values that can change.** A URL, a port, a timeout, a feature flag, a threshold, a secret: all belong in an environment variable or a config file, never a literal buried in source. Never commit a real secret or credential.
- **Name and explain non-obvious constants.** If a number isn't self-explanatory, give it a name and a short comment on where it came from: measured, a library default, or an engineering guess.
- **Build only what the current task needs (YAGNI, "you aren't gonna need it").** Don't add options, abstractions, or generalized code paths for a need you're only guessing at — a config system for one deployment target, a plugin architecture for one plugin, generalized dispatch built around a single real case. **This does not cover basic structure for concerns that already concretely exist.** If two or more distinct, real things already sit flattened into one file or folder today, giving them their own files or modules is normal engineering hygiene, not speculative generalization, even if a third might join later. Don't invoke YAGNI to justify skipping real loose coupling or real separation of concerns that are already real, only to defend against ones that are still hypothetical.
- **Depend on abstractions, not specific implementations**, so an implementation can change without every caller changing with it.

### Testing

- **Test every feature end to end before it's considered done.** A passing mocked/unit test suite isn't sufficient. Run the feature against its real dependencies (real service, real DB, real external API where feasible), and confirm the actual input and output, not just that an assertion passed.
- **If a feature runs in Docker in production, test it in Docker**, not only on the host. A container can behave differently: different base image, missing dev tools, different network resolution between services.
- **Don't break what already works.** Run the full existing test suite for the area you touched, not just tests for the new change. If you touched something shared (a shared config, a compose file wiring multiple services together), check what else depends on it.

### Review

- **After a non-trivial implementation or feature, and before committing it, get an independent review — don't self-certify.** Use the `/code-review` skill for general correctness/reuse/simplification, or spawn an independent, foreground agent (subagent type `Plan`, read-only) as a reviewer with a self-contained prompt that quotes the relevant rules from this file and points it at the real changed files. Either way it should have no memory of the conversation that produced the change, so it forms its own judgment instead of rubber-stamping the reasoning that led there — a reviewer that only sees the diff, not the justification, catches more.
- **Tell the reviewer to be strict.** Cite an exact file and line for every finding. Verify claims by reading the real files and running real commands, not by trusting a description of what changed. State plainly when a category has no findings instead of praising it or staying silent. A review that finds nothing wrong should be the rare outcome, not the default one.
- **Re-verify every finding yourself before acting on it or dismissing it.** A subagent's report describes what it believes it found, not necessarily ground truth. Confirm against the real file before changing anything, and before telling the user something is fine.

### Scope

- **Keep changes scoped to the task at hand.** Touch only the files a task actually needs. If you notice an unrelated problem while working, note it separately instead of folding a fix into the current change.
- **Keep a commit small: one logical change.** Not several unrelated things bundled together. A commit you can describe in one sentence is usually the right size.

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
- No em dashes anywhere. Use a comma, a period, or restructure the sentence.
