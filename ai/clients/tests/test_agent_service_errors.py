"""agent_service_errors.py unit tests: raise_for_business_error is the one
real thing this module does, shared by psm_agent_client.py/atl_agent_client.py/
acceleo_agent_client.py's own _config_request. Exercised directly here
rather than only indirectly through one of those three, since it's genuinely
shared, sibling-independent logic (see integration_runner_client.py's own
_request, the same pattern this mirrors).

Imported qualified (clients.agent_service_errors), not bare, unlike this
package's other tests' own module-under-test imports: every real caller
already imports it qualified this same way, and a bare import here would
load a second, distinct module object with its own separate AgentServiceError
class - an isinstance()/pytest.raises() check against one import path would
then silently never match an instance raised through the other.
"""
from unittest.mock import MagicMock

import pytest

from clients.agent_service_errors import AgentServiceError, raise_for_business_error


def _fake_response(status_code, json_body=None, text=""):
    resp = MagicMock()
    resp.status_code = status_code
    if json_body is not None:
        resp.json.return_value = json_body
    else:
        resp.json.side_effect = ValueError("not JSON")
    resp.text = text
    return resp


@pytest.mark.parametrize("status_code", [400, 404, 409])
def test_raises_agent_service_error_with_the_real_status_and_detail(status_code):
    response = _fake_response(status_code, json_body={"detail": "a real, specific validation error"})

    with pytest.raises(AgentServiceError) as exc_info:
        raise_for_business_error(response)

    assert exc_info.value.status_code == status_code
    assert exc_info.value.detail == "a real, specific validation error"


def test_falls_back_to_raw_text_when_the_body_has_no_detail_key():
    response = _fake_response(400, json_body={"message": "no detail field here"}, text="raw body text")

    with pytest.raises(AgentServiceError) as exc_info:
        raise_for_business_error(response)

    assert exc_info.value.detail == "raw body text"


def test_falls_back_to_raw_text_when_the_body_is_not_json_at_all():
    response = _fake_response(404, json_body=None, text="Not Found")

    with pytest.raises(AgentServiceError) as exc_info:
        raise_for_business_error(response)

    assert exc_info.value.detail == "Not Found"


def test_a_genuine_infrastructure_failure_still_raises_for_status_not_agent_service_error():
    response = _fake_response(500, text="Internal Server Error")

    raise_for_business_error(response)

    response.raise_for_status.assert_called_once()


def test_a_successful_response_is_a_no_op():
    response = _fake_response(200, json_body={"ok": True})

    raise_for_business_error(response)

    response.raise_for_status.assert_called_once()
