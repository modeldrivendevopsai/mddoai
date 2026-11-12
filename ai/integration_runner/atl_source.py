"""Reading facts out of a generated ATL transformation's source text, shared by
the generation stage (which runs the transformation) and publishing (which
records it for the pipeline generator)."""
import re

# The generation prompt forces the output model name into this shape:
# "create OUT : <ModelName> from IN : PIM;". It is read from the generated
# source, the one place that name is declared.
_OUTPUT_MODEL_NAME_PATTERN = re.compile(r"create\s+OUT\s*:\s*(\w+)\s+from")

# ATL line comments are stripped first, so a comment that mentions the
# convention is never mistaken for the declaration.
_ATL_LINE_COMMENT_PATTERN = re.compile(r"--.*$", re.MULTILINE)


def output_model_name(atl_source: str) -> str:
    code_only = _ATL_LINE_COMMENT_PATTERN.sub("", atl_source)
    match = _OUTPUT_MODEL_NAME_PATTERN.search(code_only)
    if not match:
        raise ValueError(
            "Could not find a real output model name in the generated ATL source "
            '(expected "create OUT : <Name> from IN : PIM;")'
        )
    return match.group(1)
