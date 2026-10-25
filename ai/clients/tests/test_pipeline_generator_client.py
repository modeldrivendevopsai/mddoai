"""pipeline_generator_client.publish_platform: POSTs a platform to
pipeline-generator's /platforms. Mocks httpx.post directly, the real network
boundary."""
from unittest.mock import MagicMock, patch

import pytest

import pipeline_generator_client
from clients.agent_service_errors import AgentServiceError
from helpers import _fake_httpx_response_raw

PLATFORM = {
    "name": "Woodpecker CI", "output_filename": "ci.yml", "output_model_name": "WoodMM",
    "metamodel": "<ecore/>", "transformation": "<atl/>", "template": "<mtl/>", "source_run_id": "run1",
}


def test_publish_platform_posts_every_field_and_returns_the_published_version():
    published = {"id": "woodpecker-ci", "name": "Woodpecker CI", "version": 1, "output_filename": "ci.yml"}
    with patch("pipeline_generator_client.httpx.post", return_value=_fake_httpx_response_raw(published)) as mock_post:
        assert pipeline_generator_client.publish_platform(**PLATFORM) == published

    mock_post.assert_called_once_with(
        f"{pipeline_generator_client.PIPELINE_GENERATOR_URL}/platforms",
        json=PLATFORM,
        timeout=pipeline_generator_client.PUBLISH_TIMEOUT,
    )


def test_a_refused_publish_raises_agent_service_error_with_the_reason():
    response = MagicMock()
    response.status_code = 422
    response.json.return_value = {"detail": "'gitlab' is a built-in platform; publish under another name."}
    with patch("pipeline_generator_client.httpx.post", return_value=response):
        with pytest.raises(AgentServiceError) as caught:
            pipeline_generator_client.publish_platform(**PLATFORM)

    assert caught.value.status_code == 422
    assert "built-in" in caught.value.detail


def test_artifacts_over_the_size_limit_are_a_refusal_not_an_outage():
    response = MagicMock()
    response.status_code = 413
    with patch("pipeline_generator_client.httpx.post", return_value=response):
        with pytest.raises(AgentServiceError) as caught:
            pipeline_generator_client.publish_platform(**PLATFORM)

    assert caught.value.status_code == 413
    assert "larger" in caught.value.detail
