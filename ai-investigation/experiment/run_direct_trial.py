"""Runs one S1 or S2 trial for real: a single LLM call (via the real,
already-running ai-layer service) given a task's frozen input, checked
against that task's requirements.md, with up to 3 repair cycles that feed
the real failures back to the model. S1's own model-to-model stages still
run for real (a real PSM instance produced by B2's own real ATL chain), only
the final code-generation step is replaced by the LLM call this script
makes; S2 skips the transformation chain entirely and hands the LLM the raw
architecture model instance instead (see this folder's README, "Alignment
with the paper", for why each gets exactly the input it does).

Usage: python run_direct_trial.py <approach: s1|s2> <task_dir> <trial_number>
Requires ai-layer reachable at AI_LAYER_URL (defaults to the host-published
http://localhost:8000, since this script runs from the host, not a
container).
"""
import json
import os
import re
import subprocess
import sys
import time
import urllib.request
from pathlib import Path

import yaml

AI_LAYER_URL = os.environ.get("AI_LAYER_URL", "http://localhost:8000")
MAX_REPAIRS = 3


def get_git_commit_hash():
    """The ai/ tree's current commit, recorded per trial so a later code
    change (e.g. the router/grounding fixes, or an S3 constraint update
    between two otherwise-identical syntheses) is traceable to which trials
    ran before vs. after it, instead of silently assumed constant."""
    repo_root = Path(__file__).resolve().parents[2]
    try:
        return subprocess.run(
            ["git", "rev-parse", "HEAD"], cwd=repo_root, capture_output=True, text=True, check=True
        ).stdout.strip()
    except Exception:
        return None


def chat(messages, model=None, temperature=0):
    payload = {"messages": messages, "temperature": temperature}
    if model is not None:
        payload["model"] = model
    body = json.dumps(payload).encode()
    req = urllib.request.Request(
        AI_LAYER_URL + "/chat", data=body, method="POST", headers={"Content-Type": "application/json"}
    )
    with urllib.request.urlopen(req, timeout=180) as resp:
        return json.loads(resp.read().decode())


def extract_yaml(text):
    match = re.search(r"```(?:ya?ml)?\s*\n(.*?)```", text, re.DOTALL)
    return match.group(1) if match else text


def grade(output_text):
    """Structural checks against requirements.md's 8 items, by each job's
    actual PURPOSE rather than its exact name. An earlier, exact-name-
    matching version of this function ("must have a job literally named
    'unitTest'") was confirmed for real to unfairly fail two genuinely
    correct, more sophisticated GitLab pipelines (real Sonnet 5 trials) that
    used different, equally valid names for the same roles
    ('test'/'healthcheck' and 'unit_tests'/'health_check'/'verify'/'publish')
    - the checklist must reflect what requirements.md's own real prose
    actually requires (a job that does X, running after a job that does Y),
    not the reference output's own incidental naming choices. Returns
    (passed, failures)."""
    failures = []
    try:
        doc = yaml.safe_load(output_text)
    except yaml.YAMLError as e:
        return False, [f"not valid YAML: {e}"]
    if not isinstance(doc, dict):
        return False, ["parsed YAML is not a mapping"]

    stages = doc.get("stages")
    if not isinstance(stages, list) or not stages:
        failures.append(f"no real 'stages' list declared, got {stages!r}")
        stages = []

    # Real, confirmed structural bug: a generator can render a nested block's
    # OWN child key as a SIBLING of the parent key instead of nested under
    # it (e.g. `image: null` immediately followed by a sibling `name: ...`,
    # instead of `image:` with `name:` nested under it) - this still parses
    # as valid YAML and a naive substring check still finds "docker:latest"
    # or "python:3.12-slim" somewhere in the same dict, so it must be
    # checked structurally, not by presence alone. `NESTED_FIELDS` lists the
    # real GitLab keys confirmed to have this shape; a `None` parent value
    # sitting next to one of its own real child keys is never valid GitLab
    # CI syntax (a stray top-level `name`/`max` key means nothing to GitLab
    # on its own, both are only ever nested under `image`/`retry`).
    NESTED_FIELDS = {"image": {"name", "entrypoint"}, "retry": {"max", "when"}}

    def structural_failures(container, label):
        found = []
        if not isinstance(container, dict):
            return found
        for parent_key, child_keys in NESTED_FIELDS.items():
            if parent_key in container and container[parent_key] is None:
                stray = child_keys & container.keys()
                if stray:
                    found.append(
                        f"{label}: '{parent_key}' is null but {sorted(stray)} appear as sibling keys instead of "
                        f"nested under '{parent_key}' - not valid GitLab CI syntax"
                    )
        return found

    for issue in structural_failures(doc.get("default"), "default"):
        failures.append(issue)

    # A "job" is any top-level mapping with its own real script - the one
    # feature every job has regardless of naming convention, distinguishing
    # it from pipeline-level keys like stages/variables/default/workflow.
    # "tasks" covers Bamboo's own real convention (a job's script lives at
    # job.tasks[i].script.scripts, not job.script directly) - confirmed for
    # real: without it, every Bamboo job was invisible to this grader and
    # every check failed, not because the output was wrong, but because the
    # grader only knew GitLab's own shape. job_text()'s json.dumps already
    # recurses into however deeply a platform nests its own real commands,
    # once a job is actually found at all.
    jobs = {k: v for k, v in doc.items() if isinstance(v, dict) and ("script" in v or "commands" in v or "tasks" in v)}
    for job_name, job in jobs.items():
        for issue in structural_failures(job, f"job '{job_name}'"):
            failures.append(issue)

    def job_text(job):
        return json.dumps(job).lower()

    def find_role(predicate):
        for name, job in jobs.items():
            if predicate(name, job):
                return name, job
        return None

    build = find_role(lambda name, job: "docker build" in job_text(job))
    if not build:
        failures.append("no job runs a docker build command")

    test = find_role(
        lambda name, job: "python" in json.dumps(job.get("image", "")).lower() or "test" in job_text(job)
    )
    if not test:
        failures.append("no job runs against a Python image or a recognizable test command")

    health = find_role(
        lambda name, job: "curl" in json.dumps(job.get("image", "")).lower()
        or "health" in job_text(job)
        or "wget" in job_text(job)
    )
    if not health:
        failures.append("no job performs a recognizable health check (curl-capable image, or a health-check script/command)")

    # Excludes the build job itself: a build job commonly pushes its own
    # freshly-built CANDIDATE image to a registry too (confirmed for real in
    # two genuine Sonnet 5 trials, e.g. "docker build -t ...:candidate &&
    # docker push ...:candidate"), which is a legitimate staging push, not
    # the FINAL push requirements.md item 6 describes (the one that must run
    # only after the health check) - without this exclusion, find_role
    # picked the build job as "push" and produced a nonsensical ordering
    # failure ("build has no dependency on healthcheck").
    # GitLab merges a pipeline-level `default: before_script:` into every
    # job that doesn't declare its own, so a real, correct job can rely on
    # a login done once at that level rather than repeating it - confirmed
    # for real: a genuinely correct push job's own script only pulled,
    # tagged, and pushed, with `docker login` living once in `default:
    # before_script:` instead. Check the job's own text OR the pipeline
    # default's own before_script for "login".
    default_before_script = json.dumps(doc.get("default", {}).get("beforeScript", doc.get("default", {}).get("before_script", ""))).lower()
    build_name = build[0] if build else None
    push = find_role(
        lambda name, job: name != build_name
        and "docker push" in job_text(job)
        and ("login" in job_text(job) or "login" in default_before_script)
    )
    if not push:
        failures.append("no job both logs in (directly or via a pipeline-level default before_script) and pushes an image to a registry")

    def stage_index(job):
        stage = job.get("stage")
        return stages.index(stage) if stage in stages else None

    # Some platforms (GitLab) give each job its own `stage:` field, so a
    # later stage is a real, checkable ordering signal. Others (Bamboo) name
    # a stage's member jobs from the stage's own side instead, with no
    # per-job `stage:` field at all - confirmed for real that even the
    # already-validated reference chain for this platform puts every job in
    # one stage with no explicit inter-job ordering at all. Enforcing an
    # ordering check the reference itself doesn't demonstrate would fail a
    # genuinely correct output for a limitation of the platform's own real
    # structure, not the model's - only check ordering when at least one
    # real job in this document actually declares its own `stage` field.
    any_job_has_own_stage = any(job.get("stage") is not None for job in jobs.values())

    def runs_after(later, earlier, earlier_label):
        if not later or not earlier or not any_job_has_own_stage:
            return  # already reported as missing above, or platform has no per-job ordering signal to check
        later_name, later_job = later
        earlier_name, earlier_job = earlier
        needs = later_job.get("needs") or later_job.get("dependencies") or []
        needs_names = {n.get("job") if isinstance(n, dict) else n for n in needs} if isinstance(needs, list) else set()
        if earlier_name in needs_names:
            return
        later_idx, earlier_idx = stage_index(later_job), stage_index(earlier_job)
        if later_idx is not None and earlier_idx is not None and later_idx > earlier_idx:
            return
        failures.append(
            f"'{later_name}' (health/push role) has no explicit dependency on '{earlier_name}' "
            f"({earlier_label}) and does not run in a later stage"
        )

    runs_after(health, build, "build role")
    runs_after(push, health, "health-check role")

    # GitLab's own top-level `image:` key is real, valid, deprecated-but-
    # functional syntax for a pipeline-wide default image, equivalent to
    # `default: image:` (confirmed via GitLab's own forum/docs: "top level
    # image: still works... default: image: is the modern, recommended
    # approach" - both set the same thing). Missing this form was confirmed
    # for real to mark a genuinely valid S2 pipeline as failing.
    top_level_image = json.dumps(doc.get("image", "")).lower()
    default_image = json.dumps(doc.get("default", {})).lower()
    build_image = json.dumps((build[1] if build else {}).get("image", "")).lower()
    services_text = json.dumps([j.get("services", []) for j in jobs.values()] + [doc.get("services", [])]).lower()
    # Bamboo has no per-job container-image concept at all: a job instead
    # declares a `requirements` capability (e.g. `os.linux`) naming what its
    # own build agent must provide, and runs `docker build`/`docker push` as
    # plain shell commands on that agent - confirmed for real against
    # Bamboo's own actual documented convention and a real, validated
    # reference chain (`tasks/uc2-migration/reference/`). A job that both
    # names a real Linux/docker-capable requirement AND runs a real docker
    # command satisfies the same real intent GitLab's `image:` expresses.
    requirements_text = json.dumps([j.get("requirements", []) for j in jobs.values()]).lower()
    any_job_runs_docker = any("docker" in job_text(job) for job in jobs.values())
    bamboo_style_capability = "linux" in requirements_text and any_job_runs_docker
    if not any("docker" in t for t in (top_level_image, default_image, build_image, services_text)) and not bamboo_style_capability:
        failures.append(
            "no docker-capable environment declared (top-level/default/build job's own image, a dind "
            "service, or a real Linux/docker-capable job requirement alongside a real docker command)"
        )

    if not doc.get("variables"):
        failures.append("no pipeline-level variables block for image name/tag")

    return (len(failures) == 0), failures


def check_secret_scan_gate(output_text):
    """UC3-specific: on top of grade()'s own build/test/health/push checks
    (still run separately), verify the new secret-scanning job is real, and
    is a real BLOCKING gate - it runs after health-check and before push,
    not just alongside them. Kept as its own function, not folded into
    grade(), so UC1/UC2's own already-verified checks and regression tests
    are never at risk of this task-specific addition changing their
    behavior. Returns (passed, failures)."""
    failures = []
    try:
        doc = yaml.safe_load(output_text)
    except yaml.YAMLError as e:
        return False, [f"not valid YAML: {e}"]
    if not isinstance(doc, dict):
        return False, ["parsed YAML is not a mapping"]

    stages = doc.get("stages") if isinstance(doc.get("stages"), list) else []
    jobs = {k: v for k, v in doc.items() if isinstance(v, dict) and ("script" in v or "commands" in v or "tasks" in v)}

    def job_text(job):
        return json.dumps(job).lower()

    def find_role(predicate):
        for name, job in jobs.items():
            if predicate(name, job):
                return name, job
        return None

    SECRET_SCAN_MARKERS = ("gitleaks", "trufflehog", "secret", "detect-secrets")
    secret_scan = find_role(lambda name, job: any(m in job_text(job) for m in SECRET_SCAN_MARKERS))
    if not secret_scan:
        failures.append("no job runs a real, named secret-scanning command")
        return False, failures

    health = find_role(lambda name, job: "curl" in json.dumps(job.get("image", "")).lower() or "health" in job_text(job))
    push = find_role(
        lambda name, job: name != secret_scan[0] and "docker push" in job_text(job) and "login" in job_text(job)
    )

    def stage_index(job):
        stage = job.get("stage")
        return stages.index(stage) if stage in stages else None

    any_job_has_own_stage = any(job.get("stage") is not None for job in jobs.values())

    # Bamboo has no per-job `stage:`/`needs` field at all: ordering is
    # expressed from the STAGE's own side, as the position of a job's NAME
    # within that stage's `jobs:` list (confirmed for real against
    # tasks/uc2-migration/reference/output.bamboo.yaml: `stages: [{'Default
    # Stage': {'jobs': ['build', 'unitTest', 'healthCheck', 'push']}}]`).
    # grade()'s own ordering check silently skips Bamboo entirely (no signal
    # to check), which is correct there since UC1/UC2 never move a job's
    # position. UC3 is specifically ABOUT verifying a new job's position, so
    # silently skipping here would let any placement of secretScan pass -
    # this flattens every stage's own job-name list, in stage order, into
    # one sequence and checks the new job's real position in it instead.
    bamboo_job_order = None
    if stages and all(isinstance(s, dict) for s in stages):
        order = []
        for stage in stages:
            for stage_body in stage.values():
                if isinstance(stage_body, dict) and isinstance(stage_body.get("jobs"), list):
                    order.extend(stage_body["jobs"])
        if order:
            bamboo_job_order = order

    def runs_after(later, earlier, later_label, earlier_label):
        if not later or not earlier:
            return
        later_name, later_job = later
        earlier_name, earlier_job = earlier
        if bamboo_job_order is not None:
            if later_name in bamboo_job_order and earlier_name in bamboo_job_order:
                if bamboo_job_order.index(later_name) > bamboo_job_order.index(earlier_name):
                    return
            failures.append(
                f"'{later_name}' ({later_label}) does not come after '{earlier_name}' ({earlier_label}) "
                "in its stage's own job order"
            )
            return
        needs = later_job.get("needs") or later_job.get("dependencies") or []
        needs_names = {n.get("job") if isinstance(n, dict) else n for n in needs} if isinstance(needs, list) else set()
        if earlier_name in needs_names:
            return
        if any_job_has_own_stage:
            later_idx, earlier_idx = stage_index(later_job), stage_index(earlier_job)
            if later_idx is not None and earlier_idx is not None and later_idx > earlier_idx:
                return
        failures.append(f"'{later_name}' ({later_label}) has no explicit dependency on '{earlier_name}' ({earlier_label})")

    runs_after(secret_scan, health, "secret-scan role", "health-check role")
    runs_after(push, secret_scan, "push role", "secret-scan role")

    return (len(failures) == 0), failures


def _task_config(task_dir: Path) -> dict:
    """Per-task platform identity, read from task_config.json so this script
    works across tasks/platforms (UC1/GitLab, UC2/Bamboo, ...) instead of
    hardcoding GitLab's own filenames and platform name. Defaults match
    UC1's own original, real files, so an older task folder without this
    file keeps working unchanged."""
    config_path = task_dir / "task_config.json"
    defaults = {"platform_name": "GitLab", "psm_instance_filename": "psm_instance.gitlabmm"}
    if config_path.is_file():
        defaults.update(json.loads(config_path.read_text(encoding="utf-8")))
    return defaults


def build_initial_messages(approach, task_dir: Path):
    """S1 gets a real PSM MODEL INSTANCE (produced by actually running B2's
    real ATL chain against a real .swarch file), not a bare metamodel/schema.
    S2 gets the real .swarch ARCHITECTURE MODEL INSTANCE itself and nothing
    else beyond the shared task text - no hand-written description, no
    platform documentation - matching the paper's own definition of S2 as
    the naive "ask for a name and let the LLM figure out the rest" baseline
    (see this folder's README, "Alignment with the paper"). Both receive the
    identical task/quality-gate text, so the only real difference between
    the two prompts is which real model instance is attached.

    Returns (messages, inputs_manifest): inputs_manifest lists the exact
    source file paths (relative to this experiment folder) whose content was
    embedded into the prompt, so a reader can see exactly what was fed in
    without parsing the raw prompt text."""
    exp_root = task_dir.parent.parent
    requirements_path = task_dir / "requirements.md"
    requirements = requirements_path.read_text(encoding="utf-8")
    manifest = [requirements_path.relative_to(exp_root).as_posix()]
    config = _task_config(task_dir)
    platform = config["platform_name"]
    if approach == "s1":
        psm_path = task_dir / "reference" / config["psm_instance_filename"]
        psm_instance = psm_path.read_text(encoding="utf-8")
        manifest.append(psm_path.relative_to(exp_root).as_posix())
        system = (
            f"You are given a platform-specific model (PSM) instance for a {platform} CI/CD "
            "pipeline (a real, populated model, not a schema) and a task. Write the "
            "actual pipeline configuration file directly, in this platform's own real "
            "format. Output only the configuration, in a single code block, no explanation."
        )
        user = f"Target platform: {platform}.\n\nPSM model instance:\n```xml\n{psm_instance}\n```\n\nTask:\n{requirements}"
    elif approach == "s2":
        arch_path = task_dir / "reference" / "architecture.swarch"
        arch_instance = arch_path.read_text(encoding="utf-8")
        manifest.append(arch_path.relative_to(exp_root).as_posix())
        system = (
            "You are given a software architecture model instance and a task. Write the "
            "actual pipeline configuration file directly, in the target platform's own "
            "real format. Output only the configuration, in a single code block, no "
            "explanation."
        )
        user = f"Target platform: {platform}.\n\nArchitecture model instance:\n```xml\n{arch_instance}\n```\n\nTask:\n{requirements}"
    else:
        raise ValueError(approach)
    messages = [{"role": "system", "content": system}, {"role": "user", "content": user}]
    return messages, manifest


def main():
    approach, task_name, trial_number = sys.argv[1], sys.argv[2], sys.argv[3]
    # Pinned so every approach/trial uses the same underlying model - left to
    # ai-layer's own AUTO routing, two calls with no other difference can
    # land on different models (confirmed for real: an unpinned S1 and S2
    # call in this same task landed on openai/gpt-oss-120b and
    # claude-haiku-4-5-20251001 respectively), which would confound approach
    # with model choice. The paper's own protocol requires S1, S2, and S3 to
    # all use the same single model - "claude" is ai-layer's registered name
    # for the real, paid commercial tier (CLAUDE_MODEL in ai-layer's .env,
    # resolves to claude-sonnet-5), not the underlying model string itself
    # (that comes back as "model_used" per trial). temperature=0 is passed
    # as before; ai-layer's router silently drops it for models (Sonnet 5,
    # Opus 4.8 onward) that no longer support an explicit temperature at all.
    model = os.environ.get("TRIAL_MODEL", "claude")
    task_dir = Path(__file__).resolve().parent / "tasks" / task_name
    # Real bug hit this session: without task_name in the path, two
    # different tasks reusing the same trial number (e.g. uc2-migration
    # trial 1 and uc3-new-rule trial 1) silently overwrote each other's
    # real output, corrupting already-recorded results without any error.
    # task_name is now part of the path specifically to make that
    # structurally impossible, not just less likely.
    out_dir = Path(__file__).resolve().parent / "trials" / approach / task_name / f"trial{trial_number}"
    out_dir.mkdir(parents=True, exist_ok=True)

    messages, inputs_manifest = build_initial_messages(approach, task_dir)
    (out_dir / "inputs_manifest.json").write_text(json.dumps(inputs_manifest, indent=2), encoding="utf-8")
    commit_hash = get_git_commit_hash()
    start = time.time()

    attempt = 0
    passed = False
    failures = []
    first_attempt_success = None
    model_used = None
    while attempt <= MAX_REPAIRS:
        (out_dir / f"attempt{attempt}_input.json").write_text(json.dumps(messages, indent=2), encoding="utf-8")
        response = chat(messages, model=model)
        model_used = response.get("model")
        content = response.get("content") or ""
        output = extract_yaml(content)
        (out_dir / f"attempt{attempt}.yaml").write_text(output, encoding="utf-8")
        if attempt == 0:
            (out_dir / "attempt0_raw.txt").write_text(content, encoding="utf-8")

        passed, failures = grade(output)
        if task_name == "uc3-new-rule":
            secret_passed, secret_failures = check_secret_scan_gate(output)
            passed = passed and secret_passed
            failures = failures + secret_failures
        if attempt == 0:
            first_attempt_success = passed
        print(f"[{approach} trial {trial_number}] attempt {attempt}: passed={passed} failures={failures}")
        if passed:
            break
        attempt += 1
        if attempt > MAX_REPAIRS:
            break
        messages.append({"role": "assistant", "content": content})
        messages.append(
            {
                "role": "user",
                "content": "That output failed these checks:\n- " + "\n- ".join(failures)
                + "\n\nFix the YAML and output the corrected version the same way (a single ```yaml block, no explanation).",
            }
        )

    elapsed = time.time() - start
    result = {
        "approach": approach,
        "task": task_name,
        "trial_number": trial_number,
        "model_used": model_used,
        "ai_tree_commit": commit_hash,
        "first_attempt_success": first_attempt_success,
        "final_success": passed,
        "final_failures": failures,
        "repair_cycles": attempt if not passed else attempt,
        "total_elapsed_seconds": elapsed,
    }
    (out_dir / "result.json").write_text(json.dumps(result, indent=2), encoding="utf-8")
    print(json.dumps(result, indent=2))


if __name__ == "__main__":
    main()
