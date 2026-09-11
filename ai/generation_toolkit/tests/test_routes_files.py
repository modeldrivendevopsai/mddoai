from fastapi import FastAPI
from fastapi.testclient import TestClient

from generation_toolkit.routes.files import build_files_router


def test_available_files_endpoint_returns_whatever_the_lister_returns():
    router, available_files_endpoint = build_files_router(lambda: ["a.mtl", "b.mtl"])

    assert available_files_endpoint() == {"files": ["a.mtl", "b.mtl"]}

    app = FastAPI()
    app.include_router(router)
    client = TestClient(app)
    response = client.get("/available-files")

    assert response.status_code == 200
    assert response.json() == {"files": ["a.mtl", "b.mtl"]}


def test_available_files_endpoint_calls_the_lister_fresh_each_time():
    calls = []

    def list_available_files():
        calls.append(1)
        return [f"call-{len(calls)}.mtl"]

    router, available_files_endpoint = build_files_router(list_available_files)

    assert available_files_endpoint() == {"files": ["call-1.mtl"]}
    assert available_files_endpoint() == {"files": ["call-2.mtl"]}
