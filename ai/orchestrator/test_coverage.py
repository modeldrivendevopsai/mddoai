"""Coverage checker for the ACICDTrip Knowledge Agent.

Unlike test_manual.py (which spot-checks a handful of queries), this script
builds a checklist of EVERY concept/subclass that should exist in the
knowledge base -- sourced from Uldis's paper (9 PIM concepts) and the
ACICDTrip meta-model itself (Flores et al.) -- then checks ground() actually
returns something for each one. Anything with zero results is a confirmed gap.

HOW TO RUN:
1. Place this file inside ai/orchestrator/ (same folder as the pim_agent package)
2. From that folder, run: python3 test_coverage.py
"""

from pim_agent import ground

# The full checklist. Each entry: (search term, what it represents, where it's from)
CHECKLIST = [
    # --- The 9 canonical PIM concepts (top level) ---
    ("Pipeline", "PIM concept #1", "9-concept set"),
    ("Job", "PIM concept #2", "9-concept set"),
    ("Matrix", "PIM concept #3", "9-concept set"),
    ("Agent", "PIM concept #4", "9-concept set"),
    ("Services", "PIM concept #5", "9-concept set"),
    ("Trigger", "PIM concept #6", "9-concept set"),
    ("Parameters", "PIM concept #7", "9-concept set"),
    ("Steps", "PIM concept #8", "9-concept set"),
    ("Expressions", "PIM concept #9a", "9-concept set"),
    ("VariableDeclaration", "PIM concept #9b", "9-concept set"),

    # --- Subclasses/attributes mentioned in the ACICDTrip meta-model paper ---
    ("PipelineBlock", "base class for Pipeline/PipelineCallJob", "ACICDTrip meta-model"),
    ("ScriptJob", "Job subclass", "ACICDTrip meta-model"),
    ("PipelineCallJob", "Job subclass", "ACICDTrip meta-model"),
    ("CustomAgent", "Agent subclass", "ACICDTrip meta-model"),
    ("PresetAgent", "Agent subclass", "ACICDTrip meta-model"),
    ("LinuxAgent", "Agent subclass", "ACICDTrip meta-model"),
    ("WindowsAgent", "Agent subclass", "ACICDTrip meta-model"),
    ("MacOSAgent", "Agent subclass", "ACICDTrip meta-model"),
    ("DockerContainer", "execution environment concept", "ACICDTrip meta-model"),
    ("PushTrigger", "Trigger subclass", "ACICDTrip meta-model"),
    ("PullRequestTrigger", "Trigger subclass", "ACICDTrip meta-model"),
    ("ManualTrigger", "Trigger subclass", "ACICDTrip meta-model"),
    ("ScheduledTrigger", "Trigger subclass", "ACICDTrip meta-model"),
    ("Command step", "Step subclass", "ACICDTrip meta-model"),
    ("Plugin step", "Step subclass", "ACICDTrip meta-model"),
    ("Cache step", "Step subclass", "ACICDTrip meta-model"),
    ("Artifact step", "Step subclass", "ACICDTrip meta-model"),
    ("Checkout step", "Step subclass", "ACICDTrip meta-model"),
    ("ConditionalStep", "Step subclass", "ACICDTrip meta-model"),
    ("Concat expression", "Expression subclass", "ACICDTrip meta-model"),
    ("VariableReference", "Expression subclass", "ACICDTrip meta-model"),
    ("BinaryOp", "Expression operator", "ACICDTrip meta-model"),
    ("UnaryOp", "Expression operator", "ACICDTrip meta-model"),

    # --- Known platform gaps (from Uldis's paper Table III) ---
    ("Bamboo Matrix gap", "known platform limitation", "coverage evaluation"),
    ("Bamboo Services gap", "known platform limitation", "coverage evaluation"),
    ("GitLab Parameters gap", "known generation gap", "coverage evaluation"),
    ("Trigger partial support", "known cross-platform limitation", "coverage evaluation"),
    ("Steps partial support", "known cross-platform limitation", "coverage evaluation"),

    # --- Process/transformation chain ---
    ("ATL transformation", "process step", "ACICDTrip process"),
    ("Acceleo code generation", "process step", "ACICDTrip process"),
    ("reusability of meta-model", "MDDOAI's own analysis", "reusability category"),
]

print(f"Checking {len(CHECKLIST)} known concepts against the knowledge base...\n")

missing = []
weak = []
ok = []

for term, description, source in CHECKLIST:
    results = ground(term)
    if not results:
        missing.append((term, description, source))
    elif len(results) == 1:
        # Only found via one weak/generic match - worth a second look
        weak.append((term, description, source, results[0].title))
    else:
        ok.append(term)

print(f"OK ({len(ok)}/{len(CHECKLIST)}): concepts with solid grounding")
print(f"WEAK ({len(weak)}/{len(CHECKLIST)}): only one thin match, worth checking")
print(f"MISSING ({len(missing)}/{len(CHECKLIST)}): zero results, confirmed gaps\n")

if missing:
    print("=" * 70)
    print("MISSING - these need new knowledge entries:")
    print("=" * 70)
    for term, desc, source in missing:
        print(f"  '{term}' -- {desc} (source: {source})")
    print()

if weak:
    print("=" * 70)
    print("WEAK - only one match found, review if it's a good match:")
    print("=" * 70)
    for term, desc, source, matched_title in weak:
        print(f"  '{term}' -- {desc} -> matched only: [{matched_title}]")
    print()

print("=" * 70)
print(f"SUMMARY: {len(missing)} missing, {len(weak)} weak, {len(ok)} solid out of {len(CHECKLIST)} total")
print("=" * 70)