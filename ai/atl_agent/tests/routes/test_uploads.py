"""routes/uploads.py unit tests: a real TestClient with a real multipart
file upload, not the plain-function-call convention this service's other
route tests use - upload_attachment_endpoint is async and needs FastAPI's
own real multipart parsing (UploadFile), which a bare function call can't
exercise.
"""
from fastapi.testclient import TestClient

import main


def test_uploads_a_real_file_and_returns_its_stored_path(isolated_attachment_uploads_dir):
    client = TestClient(main.app)

    response = client.post("/attachment-uploads", files={"file": ("example.atl", b"module m;", "text/plain")})

    assert response.status_code == 200
    stored_path = response.json()["path"]
    assert (isolated_attachment_uploads_dir / stored_path).read_bytes() == b"module m;"


def test_uploaded_file_becomes_available_to_the_picker(isolated_attachment_uploads_dir):
    client = TestClient(main.app)

    upload = client.post("/attachment-uploads", files={"file": ("example.atl", b"module m;", "text/plain")})
    stored_path = upload.json()["path"]

    listed = client.get("/available-files")

    assert stored_path in listed.json()["files"]


def test_rejects_a_file_over_the_size_limit(isolated_attachment_uploads_dir, monkeypatch):
    import prompt_paths

    monkeypatch.setattr(prompt_paths, "MAX_UPLOAD_BYTES", 4)
    client = TestClient(main.app)

    response = client.post("/attachment-uploads", files={"file": ("example.atl", b"way too much", "text/plain")})

    assert response.status_code == 400


def test_sanitizes_a_path_traversal_filename(isolated_attachment_uploads_dir):
    client = TestClient(main.app)

    response = client.post("/attachment-uploads", files={"file": ("../../etc/passwd", b"x", "text/plain")})

    assert response.status_code == 200
    stored_path = response.json()["path"]
    assert "/" not in stored_path
    assert ".." not in stored_path
