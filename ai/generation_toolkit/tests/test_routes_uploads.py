from fastapi import FastAPI
from fastapi.testclient import TestClient

from generation_toolkit.attachments.uploads import UploadedFiles
from generation_toolkit.routes.uploads import build_uploads_router
from helpers import AGENT


def _client(max_bytes=1_000_000):
    router = build_uploads_router(AGENT, lambda: max_bytes)
    app = FastAPI()
    app.include_router(router)
    return TestClient(app)


def test_uploads_a_real_file_and_returns_its_stored_path():
    client = _client()

    response = client.post("/attachment-uploads", files={"file": ("example.mtl", b"[module m('x')]", "text/plain")})

    assert response.status_code == 200
    assert UploadedFiles(AGENT).read(response.json()["path"]) == "[module m('x')]"


def test_rejects_a_file_over_the_size_limit():
    client = _client(max_bytes=4)

    response = client.post("/attachment-uploads", files={"file": ("example.mtl", b"way too much", "text/plain")})

    assert response.status_code == 400


def test_rejects_a_file_that_is_not_text():
    response = _client().post("/attachment-uploads", files={"file": ("a.bin", b"\xff\xfe\x00", "application/octet-stream")})

    assert response.status_code == 400


def test_reads_max_bytes_fresh_on_every_request():
    # A zero-arg getter, not a plain value, so a caller that changes it (a test's own
    # monkeypatch, or a config reload) is respected on the very next request - proven
    # here by changing what it returns between two calls to the same router.
    state = {"max_bytes": 1_000_000}
    app = FastAPI()
    app.include_router(build_uploads_router(AGENT, lambda: state["max_bytes"]))
    client = TestClient(app)

    assert client.post("/attachment-uploads", files={"file": ("a.mtl", b"first", "text/plain")}).status_code == 200

    state["max_bytes"] = 2
    assert client.post("/attachment-uploads", files={"file": ("b.mtl", b"second", "text/plain")}).status_code == 400
