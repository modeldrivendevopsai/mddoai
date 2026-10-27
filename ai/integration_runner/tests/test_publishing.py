"""publishing.py: what a completed run publishes, and when it refuses. Only the
pipeline-generator client is mocked."""
from unittest.mock import patch

import pytest

from integration_runner import pipeline, publishing

ATL = "-- create OUT : NotThis from IN : PIM;\ncreate OUT : WoodMM from IN : PIM;"
CONTEXT = {"psm_output": "<ecore/>", "atl_output": ATL, "acceleo_output": "[module m]"}
PUBLISHED = {"id": "woodpecker-ci", "name": "Woodpecker CI", "version": 1, "output_filename": ".woodpecker/pipeline.yaml"}


def _completed_run(generated_files=None, platform="Woodpecker CI", context=None):
    context = dict(CONTEXT if context is None else context)
    run = pipeline.IntegrationRun()
    run.record_event("call_started", "docs", {"platform_description": platform})
    run.record_event("call_started", "generation", context)
    run.record_event("call_completed", "generation", {
        "output": "steps: []\n",
        "generated_files": {".woodpecker/pipeline.yaml": "steps: []\n"} if generated_files is None else generated_files,
    })
    run.current_stage_index = len(pipeline.STAGES)
    run.last_context = dict(context)
    return run


def test_a_completed_run_publishes_its_approved_outputs_and_records_it():
    run = _completed_run()

    with patch("integration_runner.publishing.pipeline_generator_client.publish_platform", return_value=PUBLISHED) as publish:
        result = publishing.publish(run)

    assert result == PUBLISHED
    publish.assert_called_once_with(
        name="Woodpecker CI",
        output_filename=".woodpecker/pipeline.yaml",
        output_model_name="WoodMM",
        source_run_id=run.run_id,
        metamodel="<ecore/>",
        transformation=ATL,
        template="[module m]",
    )
    event = run.events[-1]
    assert (event["type"], event["data"]) == ("platform_published", {"platform_id": "woodpecker-ci", "name": "Woodpecker CI", "version": 1})


def test_what_is_published_is_what_the_successful_generation_attempt_ran_with():
    run = _completed_run()
    # A later attempt that failed, with other inputs, leaves the run's latest
    # context changed; the approved pipeline still came from the earlier one.
    other = {**CONTEXT, "atl_output": "create OUT : Other from IN : PIM;"}
    run.record_event("call_started", "generation", other)
    run.record_event("call_failed", "generation", {"error": "boom"})
    run.last_context = other

    with patch("integration_runner.publishing.pipeline_generator_client.publish_platform", return_value=PUBLISHED) as publish:
        publishing.publish(run)

    assert publish.call_args.kwargs["transformation"] == ATL
    assert publish.call_args.kwargs["output_model_name"] == "WoodMM"


def test_a_given_name_and_output_file_win_over_the_defaults():
    with patch("integration_runner.publishing.pipeline_generator_client.publish_platform", return_value=PUBLISHED) as publish:
        publishing.publish(_completed_run(), name="  Woodpecker  ", output_filename="ci.yml")

    assert publish.call_args.kwargs["name"] == "Woodpecker"
    assert publish.call_args.kwargs["output_filename"] == "ci.yml"


def test_a_run_that_is_not_complete_is_refused_and_nothing_is_published():
    run = _completed_run()
    run.current_stage_index = pipeline.STAGES.index("acceleo")

    with patch("integration_runner.publishing.pipeline_generator_client.publish_platform") as publish:
        with pytest.raises(publishing.NotPublishableError, match="acceleo"):
            publishing.publish(run)
    publish.assert_not_called()


def test_a_run_missing_an_approved_output_is_refused():
    run = _completed_run(context={**CONTEXT, "atl_output": ""})

    with pytest.raises(publishing.NotPublishableError, match="atl_output"):
        publishing.publish(run)


def test_a_run_whose_generation_never_completed_is_refused():
    run = pipeline.IntegrationRun()
    run.current_stage_index = len(pipeline.STAGES)
    run.last_context = dict(CONTEXT)

    with pytest.raises(publishing.NotPublishableError, match="psm_output"):
        publishing.publish(run)


def test_several_generated_files_need_the_caller_to_say_which_one():
    run = _completed_run(generated_files={"a.yml": "x", "b.yml": "y"})

    assert publishing.defaults(run)["output_filename"] is None
    with pytest.raises(publishing.NotPublishableError, match="which generated file"):
        publishing.publish(run)


def test_defaults_are_the_platform_description_and_the_one_generated_file():
    assert publishing.defaults(_completed_run()) == {"name": "Woodpecker CI", "output_filename": ".woodpecker/pipeline.yaml"}


def test_a_transformation_without_an_output_declaration_is_not_publishable():
    run = _completed_run(context={**CONTEXT, "atl_output": "module x;"})

    with patch("integration_runner.publishing.pipeline_generator_client.publish_platform") as publish:
        with pytest.raises(publishing.NotPublishableError, match="output model name"):
            publishing.publish(run)
    publish.assert_not_called()
