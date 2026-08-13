"""Reference Knowledge Agent.

A static knowledge base derived from a reference CI/CD-migration project's
meta-model, transformation process, and reusability assessment for MDDOAI.
This is NOT a RAG/vector-DB agent: the knowledge is a fixed, hand-curated list
of real facts, and matching is plain keyword scoring against that list.

Intended use: a future PIM comparison agent calls ground(query) to pull relevant
context (concept definitions, meta-model structure, known reusable/
non-reusable components) as grounding when comparing platform docs against our
PIM. This module only produces that grounding context; it does not itself
compare or extend anything.
"""

from dataclasses import dataclass

_REFERENCE_SOURCE = "the reference CI/CD migration project"


@dataclass
class GroundingExample:
    """One piece of grounding context from the reference CI/CD migration project.
    This shape is the stable public contract: implementation of ground() can
    change without breaking callers."""

    category: str  # "metamodel", "process", "reusability", or "limitation"
    title: str
    content: str


@dataclass(frozen=True)
class _KnowledgeEntry:
    category: str
    title: str
    description: str
    keywords: tuple[str, ...]
    source_note: str

    def to_grounding_example(self) -> GroundingExample:
        return GroundingExample(
            category=self.category,
            title=self.title,
            content=f"{self.description}\n\nSource: {self.source_note}",
        )


_KNOWLEDGE: list[_KnowledgeEntry] = [
    # --- metamodel ------------------------------------------------------------
    _KnowledgeEntry(
        category="metamodel",
        title="Pipeline and PipelineBlock",
        description=(
            "Pipeline extends PipelineBlock, the base concept carrying an agent, "
            "inputs, outputs, environment variables, a timeout, a working directory, "
            "and a shell. PipelineCallJob also extends PipelineBlock, so pipelines "
            "and pipeline-calling jobs share these same base attributes."
        ),
        keywords=("pipeline", "pipelineblock", "agent", "inputs", "outputs", "environment", "timeout", "shell"),
        source_note=f"{_REFERENCE_SOURCE} meta-model; structural reference for MDDOAI's pimMM.ecore, not directly reusable.",
    ),
    _KnowledgeEntry(
        category="metamodel",
        title="Job types: ScriptJob and PipelineCallJob",
        description=(
            "A Job is either a ScriptJob, holding an ordered list of Steps, or a "
            "PipelineCallJob, which calls another pipeline. Jobs are ordered via "
            "explicit previous/next references rather than an implicit list position."
        ),
        keywords=("job", "scriptjob", "pipelinecalljob", "step", "steps", "previous", "next", "ordering"),
        source_note=f"{_REFERENCE_SOURCE} meta-model; structural reference for MDDOAI's pimMM.ecore, not directly reusable.",
    ),
    _KnowledgeEntry(
        category="metamodel",
        title="Agent types",
        description=(
            "Agents are either a CustomAgent, identified by a set of labels, or a "
            "PresetAgent, which is specialized further into LinuxAgent, WindowsAgent, "
            "or MacOSAgent for a specific OS."
        ),
        keywords=("agent", "customagent", "presetagent", "linuxagent", "windowsagent", "macosagent", "labels", "runner"),
        source_note=f"{_REFERENCE_SOURCE} meta-model; structural reference for MDDOAI's pimMM.ecore, not directly reusable.",
    ),
    _KnowledgeEntry(
        category="metamodel",
        title="DockerContainer",
        description=(
            "DockerContainer captures an image, environment variables, volumes, "
            "ports, registry credentials, and a network, letting a job run inside a "
            "specific containerized environment."
        ),
        keywords=("docker", "dockercontainer", "image", "volumes", "ports", "registry", "credentials", "network", "container"),
        source_note=f"{_REFERENCE_SOURCE} meta-model; structural reference for MDDOAI's pimMM.ecore, not directly reusable.",
    ),
    _KnowledgeEntry(
        category="metamodel",
        title="Job services",
        description=(
            "A Job declares a `services` property (DockerContainer[*|1]): one or "
            "more DockerContainer instances that run alongside the job during its "
            "execution, e.g. spinning up a database or cache container for a test "
            "job. This is the Job-to-DockerContainer dependency relationship, "
            "distinct from the DockerContainer class's own fields (image, env "
            "vars, volumes, ports, credentials)."
        ),
        keywords=("service", "services", "job", "dockercontainer", "docker", "sidecar", "database", "cache"),
        source_note=f"{_REFERENCE_SOURCE} meta-model; structural reference for MDDOAI's pimMM.ecore, not directly reusable.",
    ),
    _KnowledgeEntry(
        category="metamodel",
        title="Trigger types",
        description=(
            "Triggers cover PushTrigger, PullRequestTrigger, ManualTrigger, and "
            "ScheduledTrigger, the last of which carries a cron expression for "
            "time-based runs."
        ),
        keywords=("trigger", "pushtrigger", "pullrequesttrigger", "manualtrigger", "scheduledtrigger", "cron", "schedule"),
        source_note=f"{_REFERENCE_SOURCE} meta-model; structural reference for MDDOAI's pimMM.ecore, not directly reusable.",
    ),
    _KnowledgeEntry(
        category="metamodel",
        title="Matrix",
        description=(
            "Matrix defines build/test axes plus explicit include and exclude "
            "combinations, with a fail-fast flag controlling whether one failing "
            "combination cancels the rest of the matrix."
        ),
        keywords=("matrix", "axes", "axis", "include", "exclude", "combinations", "fail-fast", "failfast"),
        source_note=f"{_REFERENCE_SOURCE} meta-model; structural reference for MDDOAI's pimMM.ecore, not directly reusable.",
    ),
    _KnowledgeEntry(
        category="metamodel",
        title="Input and output parameters",
        description=(
            "Input and Output parameters model the values a pipeline or pipeline "
            "block consumes or produces, used e.g. when one pipeline calls another "
            "via a PipelineCallJob."
        ),
        keywords=("input", "output", "parameter", "parameters"),
        source_note=f"{_REFERENCE_SOURCE} meta-model; structural reference for MDDOAI's pimMM.ecore, not directly reusable.",
    ),
    _KnowledgeEntry(
        category="metamodel",
        title="Step types",
        description=(
            "A ScriptJob's Steps can be a Command, a Plugin invocation, a Cache "
            "step, an Artifact step, a Checkout step, or a ConditionalStep that "
            "wraps another step in a condition."
        ),
        keywords=("step", "command", "plugin", "cache", "artifact", "checkout", "conditionalstep", "condition"),
        source_note=f"{_REFERENCE_SOURCE} meta-model; structural reference for MDDOAI's pimMM.ecore, not directly reusable.",
    ),
    _KnowledgeEntry(
        category="metamodel",
        title="Expression tree",
        description=(
            "Expressions form a tree of Concat, Literals, and VariableReference "
            "nodes, plus operator nodes: BinaryOp (with EqualityOp, ComparisonOp, "
            "LogicalOp specializations) and UnaryOp (with Negation)."
        ),
        keywords=(
            "expression", "concat", "literal", "literals", "variablereference", "binaryop",
            "equalityop", "comparisonop", "logicalop", "unaryop", "negation",
        ),
        source_note=f"{_REFERENCE_SOURCE} meta-model; structural reference for MDDOAI's pimMM.ecore, not directly reusable.",
    ),
    _KnowledgeEntry(
        category="metamodel",
        title="VariableDeclaration",
        description=(
            "VariableDeclaration introduces a named variable (a single `name` "
            "attribute). It's referenced by VariableReference and Assignment, and "
            "is used across environment variables, plugin keyword arguments, and "
            "matrix combinations; MatrixAxis also relates to VariableDeclaration."
        ),
        keywords=(
            "variabledeclaration", "variable", "declaration", "variablereference",
            "assignment", "environment variable", "plugin", "kwargs", "matrixaxis", "matrix",
        ),
        source_note=f"{_REFERENCE_SOURCE} meta-model; structural reference for MDDOAI's pimMM.ecore, not directly reusable.",
    ),
    # --- process ---------------------------------------------------------------
    _KnowledgeEntry(
        category="process",
        title="Step 0: CLI parsing and environment init",
        description="The transformation starts with CLI argument parsing and environment initialization.",
        keywords=("cli", "parsing", "environment", "init", "step 0"),
        source_note=f"{_REFERENCE_SOURCE} transformation process.",
    ),
    _KnowledgeEntry(
        category="process",
        title="Step 1: input script to platform-specific input model",
        description=(
            "The input CI/CD script is parsed into a platform-specific input model, "
            "using the source platform's meta-model plus YAML parsing."
        ),
        keywords=("input script", "platform-specific", "input model", "yaml", "parsing", "step 1"),
        source_note=f"{_REFERENCE_SOURCE} transformation process.",
    ),
    _KnowledgeEntry(
        category="process",
        title="Step 2: input model to platform-independent model",
        description=(
            "The platform-specific input model is transformed into a "
            "platform-independent model via ATL, run through EMFVMLauncher, using "
            "the scripts in tsm2tim."
        ),
        keywords=("platform-independent", "atl", "emfvmlauncher", "tsm2tim", "step 2"),
        source_note=f"{_REFERENCE_SOURCE} transformation process.",
    ),
    _KnowledgeEntry(
        category="process",
        title="Step 3: platform-independent model to output platform-specific model",
        description=(
            "The platform-independent model is refined into an output "
            "platform-specific model via the tim2tsm ATL scripts."
        ),
        keywords=("refinement", "tim2tsm", "atl", "output platform-specific", "step 3"),
        source_note=f"{_REFERENCE_SOURCE} transformation process.",
    ),
    _KnowledgeEntry(
        category="process",
        title="Step 4: output model to output script",
        description="The output platform-specific model is turned into the final output script via Acceleo code generation.",
        keywords=("acceleo", "code generation", "output script", "step 4"),
        source_note=f"{_REFERENCE_SOURCE} transformation process.",
    ),
    # --- reusability -------------------------------------------------------------
    _KnowledgeEntry(
        category="reusability",
        title="Meta-models: structural reference only",
        description=(
            "The reference CI/CD migration project's meta-models are a structural "
            "reference only, not directly reusable in MDDOAI: it targets multiple "
            "platforms, while MDDOAI targets GitLab specifically."
        ),
        keywords=("reusable", "reusability", "meta-model", "metamodel", "multi-platform", "gitlab"),
        source_note="MDDOAI team's own prior reusability analysis of the reference CI/CD migration project's components.",
    ),
    _KnowledgeEntry(
        category="reusability",
        title="Code generation approach: adaptable",
        description="The reference CI/CD migration project's Acceleo-based code generation approach is adaptable to MDDOAI with only minor modifications.",
        keywords=("acceleo", "code generation", "adaptable", "reusable"),
        source_note="MDDOAI team's own prior reusability analysis of the reference CI/CD migration project's components.",
    ),
    _KnowledgeEntry(
        category="reusability",
        title="Transformation rules: reference for approach, not directly reusable",
        description=(
            "The reference CI/CD migration project's ATL transformation rules are a "
            "reference for approach, not directly reusable, since MDDOAI has "
            "different model types. Specific patterns, such as lazy rules and "
            "helper rules, may still be useful."
        ),
        keywords=("atl", "transformation rules", "lazy rule", "helper rule", "reusable", "reusability"),
        source_note="MDDOAI team's own prior reusability analysis of the reference CI/CD migration project's components.",
    ),
    # --- limitation --------------------------------------------------------------
    _KnowledgeEntry(
        category="limitation",
        title="Generated output can be incomplete",
        description=(
            "The reference CI/CD migration project's generated output can be "
            "incomplete: one documented case didn't generate build jobs or specify "
            "a version, requiring manual fixes. Treat it as a useful but not fully "
            "authoritative reference when grounding a comparison."
        ),
        keywords=("limitation", "incomplete", "build job", "version", "manual fix", "caveat"),
        source_note="Observed in practice during the MDDOAI team's evaluation of the reference CI/CD migration project's generated output.",
    ),
]


# The nine DevOps PIM concepts (issue #221's AC; the 9-concept set itself is
# independently corroborated by test_coverage.py's own checklist, sourced
# from Uldis's paper). Each concept maps to the metamodel-category
# _KnowledgeEntry title(s) that define it — this only groups existing
# entries, it adds no new knowledge. DockerContainer folds into "Services",
# not "Agent": the "Job services" entry explicitly types a Job's `services`
# property as DockerContainer[*|1] — a direct documented relationship: this
# metamodel's own text ties DockerContainer to Services, not to Agent (the
# "Agent types" entry never mentions DockerContainer at all). Expression
# tree + VariableDeclaration merge into one concept for the same
# entries-that-clearly-belong-together reasoning.
PIM_CONCEPTS: dict[str, tuple[str, ...]] = {
    "Pipeline": ("Pipeline and PipelineBlock",),
    "Job": ("Job types: ScriptJob and PipelineCallJob",),
    "Agent": ("Agent types",),
    "Services": ("Job services", "DockerContainer"),
    "Trigger": ("Trigger types",),
    "Matrix": ("Matrix",),
    "Parameters": ("Input and output parameters",),
    "Steps": ("Step types",),
    "Expressions/VariableDeclaration": ("Expression tree", "VariableDeclaration"),
}


def concept_for_entry_title(title: str) -> str | None:
    """Reverse lookup: the PIM_CONCEPTS key whose grouped titles include the
    given metamodel-category _KnowledgeEntry title, or None if it's not a
    concept-labeling target (e.g. a process/reusability/limitation entry)."""
    for concept, titles in PIM_CONCEPTS.items():
        if title in titles:
            return concept
    return None


_STOPWORDS = {
    "a", "an", "the", "in", "on", "at", "is", "are", "was", "were", "of", "to", "for",
    "and", "or", "what", "which", "how", "does", "do", "this", "that", "it", "its",
    "with", "by", "as", "be", "can", "like",
}


def _tokenize(text: str) -> list[str]:
    # Strip apostrophes rather than replacing with a space, so contractions like
    # "project's" don't split into a spurious lone "s" token that would otherwise
    # false-positive match against any other possessive/contraction in the haystack.
    cleaned = text.lower().replace("'", "")
    tokens = "".join(c if c.isalnum() else " " for c in cleaned).split()
    return [token for token in tokens if token and token not in _STOPWORDS]


# Minimum length a stem must retain after suffix stripping. Guards against
# collapsing short, unrelated words (e.g. "gas", "is") into near-empty or
# colliding stems, which would reintroduce the earlier substring-matching
# false-positive bug (see _tokenize's apostrophe handling above) in a new form.
_MIN_STEM_LEN = 3


def _stem(token: str) -> str:
    """Lightweight, dependency-free suffix stripping (not real stemming): only
    strips trailing "ing", "es", or "s" so simple word-form mismatches (e.g.
    "limitations" vs "limitation", "triggers" vs "trigger") still match under
    the plain keyword-overlap scorer. Applied identically to query and haystack
    tokens, so the exact stem produced doesn't need to be linguistically correct,
    only consistent between the two sides."""
    if token.endswith("ing") and len(token) - 3 >= _MIN_STEM_LEN:
        return token[:-3]
    if token.endswith("es") and len(token) - 2 >= _MIN_STEM_LEN:
        return token[:-2]
    if token.endswith("s") and not token.endswith("ss") and len(token) - 1 >= _MIN_STEM_LEN:
        return token[:-1]
    return token


def _score(entry: _KnowledgeEntry, query_stems: list[str]) -> int:
    haystack_stems = {_stem(t) for t in _tokenize(f"{entry.title} {entry.description} {' '.join(entry.keywords)}")}
    return sum(1 for stem in query_stems if stem in haystack_stems)


def ground(query: str, top_k: int = 5) -> list[GroundingExample]:
    """Return the most relevant reference-project grounding examples for a query.

    Matching is plain keyword scoring against each entry's title, description,
    and keywords, ordered highest-scoring first, matching this scope's Phase 0
    "not RAG" constraint. Entries with no matching tokens are excluded.
    """
    query_stems = [_stem(t) for t in _tokenize(query)]
    if not query_stems:
        return []

    scored = [(entry, _score(entry, query_stems)) for entry in _KNOWLEDGE]
    matches = [(entry, score) for entry, score in scored if score > 0]
    matches.sort(key=lambda pair: pair[1], reverse=True)

    return [entry.to_grounding_example() for entry, _ in matches[:top_k]]
