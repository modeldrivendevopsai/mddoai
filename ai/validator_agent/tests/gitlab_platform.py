"""The committed GitLab CI platform as these tests need it: its schema, for
checking real GitLab pipelines. Test-only: the service itself reads
no platform data, it is given a schema with each request.
Modules that import this skip themselves when the platform is not there (the
service's own container holds only the service)."""
import json
from pathlib import Path

import pytest

from pipeline_checks import check_pipeline

_FOLDER = Path(__file__).resolve().parents[2] / "data" / "platforms" / "gitlab" / "1"

if not (_FOLDER / "schema.json").is_file():
    pytest.skip("the committed GitLab platform is not available", allow_module_level=True)

_META = json.loads((_FOLDER / "platform.json").read_text(encoding="utf-8"))
SCHEMA = json.loads((_FOLDER / "schema.json").read_text(encoding="utf-8"))
NAME = _META["name"]
FILENAME = _META["output_filename"]


def check_gitlab_pipeline(content: str):
    return check_pipeline(content, FILENAME, NAME, SCHEMA)
