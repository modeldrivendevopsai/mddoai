"""main.py's own real app-assembly concerns: the registered PathSegmentError
exception handler, which routes/prompt_config.py's own tests (calling each
endpoint as a plain function, see tests/routes/test_prompt_config.py's own
docstring) can never exercise, since that convention bypasses FastAPI's
real exception-handling middleware entirely. A real TestClient is the only
way to verify this actually reaches an HTTP client as a 400, not a
generic, unhandled 500, so this is the one file in this service's own
test suite that uses one.
"""
from fastapi.testclient import TestClient

import main


def test_a_path_traversal_version_in_the_url_returns_400_not_500():
    client = TestClient(main.app)

    # A literal, un-encoded ".." segment never reaches this route at all -
    # httpx (like a real browser) normalizes it client-side before sending,
    # so the request never leaves as anything but a 404. Percent-encoding is
    # the real bypass technique for that normalization (the dots only
    # become literal after the server decodes the path parameter, which
    # happens after any client- or proxy-level normalization already ran) -
    # %2e is ".". Targets `version` specifically, not `name`: `name` is
    # checked against a fixed set of known names before it ever reaches
    # path construction (a 404, not a 500 or 400, for anything else), so a
    # traversal payload there never reaches validate_path_segment at all -
    # `version` is the real remaining path where a user-suppliable segment
    # flows straight into a filesystem path.
    response = client.post("/prompt-config/generation/restore/%2e%2e")

    assert response.status_code == 400
