import logging
import os

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field, field_validator

from validator_runner import (
    AcceleoValidationResult,
    AtlValidationResult,
    EcoreValidationResult,
    ValidatorInfraError,
    run_acceleo_validator,
    run_atl_validator,
    run_ecore_validator,
)

logging.basicConfig(
    level=os.environ.get("LOG_LEVEL", "INFO").upper(),
    format="%(asctime)s %(levelname)s %(name)s: %(message)s",
)
logger = logging.getLogger(__name__)

app = FastAPI(title="MDDOAI Validator Agent")

MAX_CONTENT_BYTES = int(os.environ.get("MAX_CONTENT_BYTES", str(5 * 1024 * 1024)))

# Every request model below that takes a run_id (it scopes where a real
# compiled artifact lands - EcoreValidator's codegen output, AtlValidator's
# .asm, AcceleoValidator's .emtl, all persisted under a shared, writable area
# per ai/CLAUDE.md's folder-boundaries section) shares this same pattern and
# validator, not three independent copies that could quietly drift out of
# sync on a security-relevant check. stage and attempt (below) reuse the
# exact same pattern and validator for the same reason: both are joined onto
# the same output path, right under run_id (see validator_runner.py's own
# _scoped_output_env()), so each needs the exact same path-traversal defense.
# Pydantic v2 enforces `pattern=` via pydantic_core's own Rust regex engine,
# not Python's re module - confirmed directly that this engine's $ requires
# true end-of-string (rejects "..\n"/"abc\n"), unlike Python's re.match,
# where $ also matches immediately before a single trailing "\n" even
# without re.MULTILINE. A \A/\z-anchored variant is Python-only syntax
# pydantic_core's engine doesn't accept at all (confirmed: it raises a
# regex parse error at import time), so this plain ^...$ form is both
# correct and the only one this engine supports.
_RUN_ID_PATTERN = r"^[A-Za-z0-9._-]+$"

# A real run_id/stage/attempt is short (a uuid4().hex, a one-word stage name,
# "attempt_N") - nowhere near this. Without any cap, these three values get
# joined into one path and passed as an env var to a subprocess.run() call
# (validator_runner.py's own _scoped_output_env()); confirmed directly, in a
# real Linux container matching this project's own Docker deployment, that a
# combined size in the low hundreds of KB makes that subprocess.run() raise
# an uncaught OSError ("Argument list too long") instead of a clean 422 -
# the OS's own execve() argv+envp size limit, not something Python or
# Pydantic enforces on its own. 128 is a generous ceiling for any real
# identifier this field is ever actually used for, comfortably below where
# that OS limit could ever be reached even with all three fields combined.
# Env-configurable, matching this file's own MAX_CONTENT_BYTES/
# VALIDATOR_TIMEOUT_SECONDS convention rather than a bare literal.
_ID_MAX_LENGTH = int(os.environ.get("VALIDATOR_ID_MAX_LENGTH", "128"))


def _reject_dot_segments(value: str | None) -> str | None:
    """The character-class pattern alone still lets "." or ".." through
    (both are made only of allowed characters), and either would resolve to
    the parent directory once joined onto a base output path - see
    validator_runner.py's own _scoped_output_env(). No character-class regex
    can distinguish "a run_id that happens to be only dots" from "the
    literal path-traversal segment" without this same explicit check. Also
    guards stage and attempt, each joined onto that same path in turn.

    Rejects every all-dot value ("...", "....", not just "."/".."), not
    because three-or-more dots is a real traversal segment on any OS
    (confirmed it isn't, on either Linux or Windows) - it's that on native
    Windows (this project's supported local-dev-without-Docker mode, see
    validator_runner.py's own LIB_DIR comment), the Win32 filesystem layer
    silently strips trailing dots from a path component, so "..." would
    silently collapse the scoping this whole mechanism exists to provide
    (the caller's output lands in the parent scope instead of its own
    isolated subfolder) rather than actually escape further up. Confirmed
    this collapsing is Windows-only; an all-dot value is an entirely
    ordinary, non-special directory name on Linux, the real deployment OS
    per ai/docker-compose.yml."""
    if value is not None and set(value) == {"."}:
        raise ValueError('value must not consist only of "." characters')
    return value


class EcoreValidateRequest(BaseModel):
    filename: str = Field(..., description="Original filename, used only for the temp file suffix/logging.")
    content: str = Field(..., min_length=1, description="Raw .ecore XML content.")
    mode: str = Field(default="reflective", pattern="^(reflective|codegen)$")
    run_id: str | None = Field(default=None, pattern=_RUN_ID_PATTERN, max_length=_ID_MAX_LENGTH)
    # Names the calling stage (e.g. "atl"), joined onto run_id before
    # attempt below, matching integration_runner's own real
    # runs/<run_id>/<stage>/attempt_N/ layout exactly (see
    # _validation.py's reserve_attempt_dir()) - not used by pim_stage today
    # (see clients/validator_agent_client.py's own validate_ecore
    # docstring), accepted here for parity with AtlValidateRequest/
    # AcceleoValidateRequest below.
    stage: str | None = Field(default=None, pattern=_RUN_ID_PATTERN, max_length=_ID_MAX_LENGTH)
    # Names the calling stage's own reserved attempt directory (e.g.
    # "attempt_2"), joined after stage above, so a codegen-mode call's real
    # compiled output nests inside that specific attempt rather than only
    # scoped by run_id.
    attempt: str | None = Field(default=None, pattern=_RUN_ID_PATTERN, max_length=_ID_MAX_LENGTH)

    _validate_run_id = field_validator("run_id")(classmethod(lambda cls, v: _reject_dot_segments(v)))
    _validate_stage = field_validator("stage")(classmethod(lambda cls, v: _reject_dot_segments(v)))
    _validate_attempt = field_validator("attempt")(classmethod(lambda cls, v: _reject_dot_segments(v)))


class AtlValidateRequest(BaseModel):
    filename: str = Field(..., description="Original filename, used only for the temp file suffix/logging.")
    content: str = Field(..., min_length=1, description="Raw .atl source content.")
    run_id: str | None = Field(default=None, pattern=_RUN_ID_PATTERN, max_length=_ID_MAX_LENGTH)
    # See EcoreValidateRequest's own stage/attempt fields for what these
    # scope and why.
    stage: str | None = Field(default=None, pattern=_RUN_ID_PATTERN, max_length=_ID_MAX_LENGTH)
    attempt: str | None = Field(default=None, pattern=_RUN_ID_PATTERN, max_length=_ID_MAX_LENGTH)

    _validate_run_id = field_validator("run_id")(classmethod(lambda cls, v: _reject_dot_segments(v)))
    _validate_stage = field_validator("stage")(classmethod(lambda cls, v: _reject_dot_segments(v)))
    _validate_attempt = field_validator("attempt")(classmethod(lambda cls, v: _reject_dot_segments(v)))


class AcceleoValidateRequest(BaseModel):
    filename: str = Field(..., description="Original filename, used only for the temp file suffix/logging.")
    content: str = Field(..., min_length=1, description="Raw .mtl source content.")
    run_id: str | None = Field(default=None, pattern=_RUN_ID_PATTERN, max_length=_ID_MAX_LENGTH)
    # See EcoreValidateRequest's own stage/attempt fields for what these
    # scope and why.
    stage: str | None = Field(default=None, pattern=_RUN_ID_PATTERN, max_length=_ID_MAX_LENGTH)
    attempt: str | None = Field(default=None, pattern=_RUN_ID_PATTERN, max_length=_ID_MAX_LENGTH)

    _validate_run_id = field_validator("run_id")(classmethod(lambda cls, v: _reject_dot_segments(v)))
    _validate_stage = field_validator("stage")(classmethod(lambda cls, v: _reject_dot_segments(v)))
    _validate_attempt = field_validator("attempt")(classmethod(lambda cls, v: _reject_dot_segments(v)))


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/validate/ecore", response_model=EcoreValidationResult)
def validate_ecore_endpoint(request: EcoreValidateRequest) -> EcoreValidationResult:
    content_bytes = len(request.content.encode("utf-8"))
    if content_bytes > MAX_CONTENT_BYTES:
        raise HTTPException(status_code=413, detail=f"content exceeds {MAX_CONTENT_BYTES} bytes")

    logger.info("POST /validate/ecore filename=%s mode=%s bytes=%d", request.filename, request.mode, content_bytes)
    try:
        result = run_ecore_validator(
            request.content, request.filename, request.mode, request.run_id, request.stage, request.attempt
        )
    except ValidatorInfraError as e:
        logger.error("POST /validate/ecore infra failure: %s", e)
        raise HTTPException(status_code=500, detail=str(e))
    logger.info("POST /validate/ecore done filename=%s valid=%s duration_ms=%d",
                request.filename, result["valid"], result["duration_ms"])
    return result


@app.post("/validate/atl", response_model=AtlValidationResult)
def validate_atl_endpoint(request: AtlValidateRequest) -> AtlValidationResult:
    content_bytes = len(request.content.encode("utf-8"))
    if content_bytes > MAX_CONTENT_BYTES:
        raise HTTPException(status_code=413, detail=f"content exceeds {MAX_CONTENT_BYTES} bytes")

    logger.info("POST /validate/atl filename=%s bytes=%d", request.filename, content_bytes)
    try:
        result = run_atl_validator(request.content, request.filename, request.run_id, request.stage, request.attempt)
    except ValidatorInfraError as e:
        logger.error("POST /validate/atl infra failure: %s", e)
        raise HTTPException(status_code=500, detail=str(e))
    logger.info("POST /validate/atl done filename=%s valid=%s duration_ms=%d",
                request.filename, result["valid"], result["duration_ms"])
    return result


@app.post("/validate/acceleo", response_model=AcceleoValidationResult)
def validate_acceleo_endpoint(request: AcceleoValidateRequest) -> AcceleoValidationResult:
    content_bytes = len(request.content.encode("utf-8"))
    if content_bytes > MAX_CONTENT_BYTES:
        raise HTTPException(status_code=413, detail=f"content exceeds {MAX_CONTENT_BYTES} bytes")

    logger.info("POST /validate/acceleo filename=%s bytes=%d", request.filename, content_bytes)
    try:
        result = run_acceleo_validator(
            request.content, request.filename, request.run_id, request.stage, request.attempt
        )
    except ValidatorInfraError as e:
        logger.error("POST /validate/acceleo infra failure: %s", e)
        raise HTTPException(status_code=500, detail=str(e))
    logger.info("POST /validate/acceleo done filename=%s valid=%s duration_ms=%d",
                request.filename, result["valid"], result["duration_ms"])
    return result
