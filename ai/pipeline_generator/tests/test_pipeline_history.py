"""What the generator decides about the pipelines it records: what identifies an input, and
that a failed record never loses the pipelines that were generated. The journal keeps them,
in this process over folders of the test alone (see conftest.py)."""
from unittest.mock import patch

import httpx

from clients import journal_client
from clients.agent_service_errors import AgentServiceError

GITLAB = {"id": "gitlab", "name": "GitLab CI"}


def _result(platform=GITLAB, filename=".gitlab-ci.yml", pipeline="stages: [build]\n"):
    return {
        "platform": platform,
        "output_filename": filename,
        "pipeline": pipeline,
        "intermediates": [],
        "duration_ms": 10,
        "error": None,
    }


def test_record_generation_names_the_input_by_path_else_by_file_name(history):
    by_path = history.record_generation(
        filename="a.swarch", content="<a/>", source_path="/repos/a.swarch", generated=[_result()]
    )
    by_upload = history.record_generation(
        filename="/repos/a.swarch", content="<a/>", source_path=None, generated=[_result()]
    )

    assert by_path != by_upload
    assert len(history.summaries()) == 2


def test_record_generation_has_nothing_to_record_when_nothing_was_generated(history):
    assert history.record_generation(filename="a.swarch", content="<a/>", source_path=None, generated=[]) is None
    assert history.summaries() == []


def test_record_generation_reports_a_journal_that_cannot_be_reached_as_no_id_instead_of_raising(history):
    with patch.object(journal_client, "record_pipeline", side_effect=httpx.ConnectError("the journal is down")):
        assert history.record_generation(filename="a.swarch", content="<a/>", source_path=None, generated=[_result()]) is None


def test_record_generation_reports_a_refusal_of_the_journal_as_no_id_instead_of_raising(history):
    refused = AgentServiceError(422, "the pipeline has no text")
    with patch.object(journal_client, "record_pipeline", side_effect=refused):
        assert history.record_generation(filename="a.swarch", content="<a/>", source_path=None, generated=[_result()]) is None


def test_a_value_that_is_not_an_id_is_not_asked_for(history):
    with patch.object(journal_client, "get_pipeline") as asked:
        assert history.get("../etc") is None

    asked.assert_not_called()
