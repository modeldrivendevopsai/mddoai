from fastapi import FastAPI
from fastapi.testclient import TestClient

from generation_toolkit.routes.uploads import build_uploads_router


def _client(tmp_path, max_bytes=1_000_000):
    router = build_uploads_router(lambda: tmp_path, lambda: max_bytes)
    app = FastAPI()
    app.include_router(router)
    return TestClient(app)


def test_uploads_a_real_file_and_returns_its_stored_path(tmp_path):
    client = _client(tmp_path)

    response = client.post("/attachment-uploads", files={"file": ("example.mtl", b"[module m('x')]", "text/plain")})

    assert response.status_code == 200
    stored_path = response.json()["path"]
    assert (tmp_path / stored_path).read_bytes() == b"[module m('x')]"


def test_rejects_a_file_over_the_size_limit(tmp_path):
    client = _client(tmp_path, max_bytes=4)

    response = client.post("/attachment-uploads", files={"file": ("example.mtl", b"way too much", "text/plain")})

    assert response.status_code == 400


def test_reads_uploads_dir_and_max_bytes_fresh_on_every_request(tmp_path):
    # Both are zero-arg getters, not plain values, specifically so a caller
    # that changes what they point to (e.g. a test's own monkeypatch, or a
    # config reload) is respected on the very next request - proven here by
    # actually changing the getters' return values between two calls to the
    # same router.
    state = {"dir": tmp_path / "first", "max_bytes": 1_000_000}
    state["dir"].mkdir()
    router = build_uploads_router(lambda: state["dir"], lambda: state["max_bytes"])
    app = FastAPI()
    app.include_router(router)
    client = TestClient(app)

    first = client.post("/attachment-uploads", files={"file": ("a.mtl", b"first", "text/plain")})
    assert first.status_code == 200
    assert (state["dir"] / first.json()["path"]).read_bytes() == b"first"

    second_dir = tmp_path / "second"
    second_dir.mkdir()
    state["dir"] = second_dir
    second = client.post("/attachment-uploads", files={"file": ("b.mtl", b"second", "text/plain")})
    assert second.status_code == 200
    assert (second_dir / second.json()["path"]).read_bytes() == b"second"
