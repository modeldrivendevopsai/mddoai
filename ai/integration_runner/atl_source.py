"""Reading facts out of a generated ATL transformation's source text, shared by
the generation stage (which runs the transformation) and publishing (which
records it for the pipeline generator)."""
import re

# atl_agent's own generation prompt forces every generated ATL's output
# model name into this exact shape: "create OUT : <ModelName> from IN :
# PIM;", with a matching "-- @nsURI <ModelName>=<platform>MM=..." header
# line just above it. Parsed directly out of the real generated source
# rather than reconstructed from a platform name string the caller doesn't
# otherwise have: the ATL source itself is the one place that name is
# declared, and it must match exactly for AtlExecutor's own output model
# to resolve.
_OUTPUT_MODEL_NAME_PATTERN = re.compile(r"create\s+OUT\s*:\s*(\w+)\s+from")

# ATL's own line-comment marker (matching this project's real, existing
# .atl files' own "-- @nsURI ..."/"-- Main pipeline transformation"-style
# lines). Stripped before searching for the real output model name so a
# comment merely mentioning the "create OUT : ... from" convention (in an
# LLM-generated header, say) can never be mistaken for the actual
# declaration.
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
