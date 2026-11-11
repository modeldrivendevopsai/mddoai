"""The files uploaded to an agent: kept under their own name, never over another file."""
import pytest

from journal import dependencies, settings


def test_an_upload_is_kept_and_read_back(client):
    assert client.put("/uploads/psm/abc-model.ecore", json={"content": "<ecore/>"}).status_code == 201

    assert client.get("/uploads/psm/abc-model.ecore").json() == {"content": "<ecore/>"}


def test_the_files_of_an_agent_are_listed_by_name_and_not_those_of_another(client):
    client.put("/uploads/psm/b.txt", json={"content": "b"})
    client.put("/uploads/psm/a.txt", json={"content": "a"})
    client.put("/uploads/atl/c.txt", json={"content": "c"})

    assert client.get("/uploads/psm").json() == {"files": ["a.txt", "b.txt"]}
    assert client.get("/uploads/acceleo").json() == {"files": []}


def test_a_second_upload_under_a_taken_name_is_refused_and_the_first_stays(client):
    client.put("/uploads/psm/a.txt", json={"content": "first"})

    response = client.put("/uploads/psm/a.txt", json={"content": "second"})

    assert response.status_code == 409
    assert client.get("/uploads/psm/a.txt").json() == {"content": "first"}


def test_a_file_over_the_size_limit_is_a_413_and_is_not_kept(client, folders, monkeypatch):
    monkeypatch.setattr(settings, "MAX_UPLOAD_BYTES", 10)
    dependencies.use_folders(*folders)

    response = client.put("/uploads/psm/big.txt", json={"content": "x" * 11})

    assert response.status_code == 413
    assert client.get("/uploads/psm/big.txt").status_code == 404
    assert client.put("/uploads/psm/ok.txt", json={"content": "x" * 10}).status_code == 201


@pytest.mark.parametrize("name", ["..", ".incoming", "a%2Fb", "a b", "x" * 256])
def test_a_name_that_could_not_be_a_file_is_refused(client, name):
    assert client.put(f"/uploads/psm/{name}", json={"content": "x"}).status_code in (404, 422)


def test_an_unknown_file_is_a_404(client):
    assert client.get("/uploads/psm/none.txt").status_code == 404


def test_a_file_that_is_not_text_is_a_422(client, folders):
    (folders[4] / "psm").mkdir(parents=True)
    (folders[4] / "psm" / "bin.dat").write_bytes(b"\xff\xfe\x00")

    assert client.get("/uploads/psm/bin.dat").status_code == 422


def test_a_file_is_written_whole_and_nothing_is_left_half_done_or_listed(client, folders):
    client.put("/uploads/psm/a.txt", json={"content": "x" * 1000})
    client.put("/uploads/psm/a.txt", json={"content": "again"})

    assert client.get("/uploads/psm").json() == {"files": ["a.txt"]}
    assert list((folders[4] / "psm" / ".incoming").iterdir()) == []
    assert client.get("/uploads/psm/a.txt").json() == {"content": "x" * 1000}
