"""The workspace is the one place a caller-supplied path touches the disk, so
these tests are about what it refuses as much as what it does."""
import os
import stat

import pytest

import workspace as workspace_module
from workspace import Workspace, WorkspaceError


@pytest.fixture
def ws(workspace_dir):
    return Workspace(workspace_dir)


@pytest.fixture
def outside(tmp_path):
    """A folder next to the workspace, holding an architecture file nothing
    may reach. It is named like a model so that only confinement, not the
    extension rule, can be what keeps it unread."""
    folder = tmp_path / "outside"
    folder.mkdir()
    (folder / "secret.swarch").write_text("do not read", encoding="utf-8")
    return folder


def _link(target, link):
    try:
        os.symlink(target, link, target_is_directory=target.is_dir())
    except (OSError, NotImplementedError):
        pytest.skip("this machine does not allow creating symbolic links")


def _refused(call):
    with pytest.raises(WorkspaceError) as caught:
        call()
    return caught.value


# --- reading a file ---------------------------------------------------------


def test_reads_a_file_by_a_path_from_the_workspace_folder(ws, workspace_dir):
    (workspace_dir / "repos").mkdir()
    (workspace_dir / "repos" / "app.swarch").write_text("<a/>", encoding="utf-8")

    for spelling in ("/repos/app.swarch", "./repos/app.swarch", "repos/app.swarch", "\\repos\\app.swarch"):
        assert ws.read_text(spelling, 1000) == {"filename": "app.swarch", "path": "/repos/app.swarch", "content": "<a/>"}


@pytest.mark.parametrize("path", ["../outside/secret.swarch", "/../outside/secret.swarch", "a/../../outside/secret.swarch"])
def test_a_path_that_climbs_out_of_the_workspace_is_refused(ws, outside, path):
    error = _refused(lambda: ws.read_text(path, 1000))

    assert error.status == 422
    assert "outside the workspace" in error.message


def test_an_absolute_machine_path_never_reaches_the_file(ws, outside):
    # A leading slash means "from the workspace folder", so a real machine
    # path names something that does not exist there (or, with a Windows
    # drive letter, is refused as outside): either way the file stays unread.
    error = _refused(lambda: ws.read_text(str(outside / "secret.swarch"), 1000))

    assert error.status in (404, 422)
    assert "do not read" not in error.message


def test_a_link_leading_out_of_the_workspace_is_refused(ws, workspace_dir, outside):
    _link(outside / "secret.swarch", workspace_dir / "shortcut.swarch")

    error = _refused(lambda: ws.read_text("/shortcut.swarch", 1000))

    assert "outside the workspace" in error.message


def test_a_folder_link_leading_out_of_the_workspace_is_refused(ws, workspace_dir, outside):
    _link(outside, workspace_dir / "elsewhere")

    error = _refused(lambda: ws.read_text("/elsewhere/secret.swarch", 1000))

    assert "outside the workspace" in error.message


def test_a_null_byte_in_a_path_is_refused(ws):
    assert _refused(lambda: ws.read_text("a\x00b", 1000)).status == 422


def test_a_missing_file_and_a_folder_are_both_reported_as_not_a_file(ws, workspace_dir):
    (workspace_dir / "folder.swarch").mkdir()

    assert _refused(lambda: ws.read_text("/nothing.swarch", 1000)).status == 404
    assert _refused(lambda: ws.read_text("/folder.swarch", 1000)).status == 404


def test_a_file_over_the_limit_is_refused_without_being_returned(ws, workspace_dir):
    (workspace_dir / "big.swarch").write_text("x" * 50, encoding="utf-8")

    error = _refused(lambda: ws.read_text("/big.swarch", 49))

    assert error.status == 413


def test_a_file_exactly_at_the_limit_is_read(ws, workspace_dir):
    (workspace_dir / "edge.swarch").write_text("x" * 49, encoding="utf-8")

    assert ws.read_text("/edge.swarch", 49)["content"] == "x" * 49


def test_a_model_file_that_is_not_text_is_refused(ws, workspace_dir):
    (workspace_dir / "broken.swarch").write_bytes(b"\x89PNG\xff\xfe\x00")

    assert "not text" in _refused(lambda: ws.read_text("/broken.swarch", 1000)).message


@pytest.mark.parametrize("name", ["notes.txt", ".env", "id_rsa", "model.swarch.bak", "swarch"])
def test_only_architecture_files_can_be_read_whatever_else_is_in_the_folder(ws, workspace_dir, name):
    (workspace_dir / name).write_text("SECRET=1", encoding="utf-8")

    error = _refused(lambda: ws.read_text("/" + name, 1000))

    assert error.status == 422
    assert ".swarch" in error.message
    assert "SECRET" not in error.message


def test_the_extension_is_matched_whatever_its_case(ws, workspace_dir):
    (workspace_dir / "MODEL.SWARCH").write_text("<a/>", encoding="utf-8")

    assert ws.read_text("/MODEL.SWARCH", 1000)["content"] == "<a/>"


def test_a_link_named_like_a_model_cannot_expose_another_kind_of_file(ws, workspace_dir):
    # A repository can carry links, and this one stays inside the workspace, so
    # confinement alone lets it through: what it points at must be a model too.
    (workspace_dir / ".env").write_text("SECRET=1", encoding="utf-8")
    _link(workspace_dir / ".env", workspace_dir / "arch.swarch")

    error = _refused(lambda: ws.read_text("/arch.swarch", 1000))

    assert error.status == 422
    assert "SECRET" not in error.message


def test_a_link_to_a_model_inside_the_workspace_is_followed(ws, workspace_dir):
    (workspace_dir / "real.swarch").write_text("<a/>", encoding="utf-8")
    _link(workspace_dir / "real.swarch", workspace_dir / "alias.swarch")

    assert ws.read_text("/alias.swarch", 1000)["content"] == "<a/>"


def test_whether_a_non_model_name_leads_outside_is_not_revealed(ws, workspace_dir, outside):
    # The same answer for a link that leaves the workspace and for a plain
    # missing file: the extension is checked before the disk is consulted.
    _link(outside / "secret.swarch", workspace_dir / "leak.txt")

    leaking = _refused(lambda: ws.read_text("/leak.txt", 1000))
    plain = _refused(lambda: ws.read_text("/plain.txt", 1000))

    assert leaking.message == plain.message
    assert leaking.status == plain.status


def test_a_link_loop_is_reported_not_crashed_on(ws, workspace_dir):
    _link(workspace_dir / "b.swarch", workspace_dir / "a.swarch")
    _link(workspace_dir / "a.swarch", workspace_dir / "b.swarch")

    error = _refused(lambda: ws.read_text("/a.swarch", 1000))

    assert error.status in (404, 422)
    assert ws.browse("/", False)["entries"] == []


def test_errors_never_name_a_location_outside_the_workspace(ws, workspace_dir, outside):
    messages = [
        _refused(lambda: ws.read_text("../outside/secret.swarch", 10)).message,
        _refused(lambda: ws.read_text("/missing.swarch", 10)).message,
        _refused(lambda: ws.browse("../outside", False)).message,
    ]

    for message in messages:
        assert str(outside) not in message
        assert str(workspace_dir) not in message


# --- listing a folder -------------------------------------------------------


def test_browse_lists_folders_first_then_files_each_alphabetically(ws, workspace_dir):
    (workspace_dir / "zeta").mkdir()
    (workspace_dir / "Alpha").mkdir()
    (workspace_dir / "b.swarch").write_text("", encoding="utf-8")
    (workspace_dir / "a.swarch").write_text("", encoding="utf-8")

    listing = ws.browse("/", False)

    assert [(e["name"], e["kind"]) for e in listing["entries"]] == [
        ("Alpha", "dir"), ("zeta", "dir"), ("a.swarch", "file"), ("b.swarch", "file"),
    ]
    assert listing["path"] == "/"
    assert listing["parent"] is None


def test_browse_reports_the_parent_of_a_subfolder(ws, workspace_dir):
    (workspace_dir / "repos" / "app").mkdir(parents=True)

    listing = ws.browse("/repos/app", False)

    assert listing["path"] == "/repos/app"
    assert listing["parent"] == "/repos"


def test_browse_lists_only_folders_and_architecture_files(ws, workspace_dir):
    (workspace_dir / "sub").mkdir()
    (workspace_dir / "a.swarch").write_text("", encoding="utf-8")
    (workspace_dir / "notes.txt").write_text("", encoding="utf-8")
    (workspace_dir / ".env").write_text("", encoding="utf-8")

    assert [e["name"] for e in ws.browse("/", False)["entries"]] == ["sub", "a.swarch"]


def test_browse_does_not_offer_a_link_named_like_a_model_that_points_at_something_else(ws, workspace_dir):
    (workspace_dir / ".env").write_text("SECRET=1", encoding="utf-8")
    _link(workspace_dir / ".env", workspace_dir / "arch.swarch")
    (workspace_dir / "real.swarch").write_text("", encoding="utf-8")

    assert [e["name"] for e in ws.browse("/", False)["entries"]] == ["real.swarch"]


def test_browse_can_list_folders_only(ws, workspace_dir):
    (workspace_dir / "sub").mkdir()
    (workspace_dir / "a.swarch").write_text("", encoding="utf-8")

    assert [e["name"] for e in ws.browse("/", True)["entries"]] == ["sub"]


def test_browse_does_not_offer_a_link_that_leaves_the_workspace(ws, workspace_dir, outside):
    _link(outside, workspace_dir / "elsewhere")
    _link(outside / "secret.swarch", workspace_dir / "shortcut.swarch")
    (workspace_dir / "real.swarch").write_text("", encoding="utf-8")

    assert [e["name"] for e in ws.browse("/", False)["entries"]] == ["real.swarch"]


def test_browse_refuses_to_leave_the_workspace_or_list_a_file(ws, workspace_dir, outside):
    (workspace_dir / "a.swarch").write_text("", encoding="utf-8")

    assert _refused(lambda: ws.browse("../outside", False)).status == 422
    assert _refused(lambda: ws.browse("/a.swarch", False)).status == 404


def test_browse_stops_at_the_listing_limit_and_says_so(ws, workspace_dir, monkeypatch):
    monkeypatch.setattr(workspace_module, "MAX_LISTED_ENTRIES", 3)
    for number in range(5):
        (workspace_dir / f"f{number}.swarch").write_text("", encoding="utf-8")

    listing = ws.browse("/", False)

    assert len(listing["entries"]) == 3
    assert listing["truncated"] is True


# --- saving a file ----------------------------------------------------------


def test_write_creates_the_folder_and_returns_where_it_went(ws, workspace_dir):
    saved = ws.write_text("./ci-output/", ".gitlab-ci.yml", "stages: [build]\n")

    assert saved.path == "/ci-output/.gitlab-ci.yml"
    assert (workspace_dir / "ci-output" / ".gitlab-ci.yml").read_text(encoding="utf-8") == "stages: [build]\n"


def test_write_says_whether_it_replaced_an_existing_file(ws):
    first = ws.write_text("/out", "p.yml", "old")
    second = ws.write_text("/out", "p.yml", "new")

    assert first.replaced is False
    assert second.replaced is True


def test_write_replaces_an_existing_file(ws, workspace_dir):
    ws.write_text("/out", "p.yml", "old")
    ws.write_text("/out", "p.yml", "new")

    assert (workspace_dir / "out" / "p.yml").read_text(encoding="utf-8") == "new"


def test_write_preserves_line_endings_exactly(ws, workspace_dir):
    ws.write_text("/out", "p.yml", "a\r\nb\n")

    assert (workspace_dir / "out" / "p.yml").read_bytes() == b"a\r\nb\n"


def test_write_leaves_no_temporary_file_behind(ws, workspace_dir):
    ws.write_text("/out", "p.yml", "x")

    assert [p.name for p in (workspace_dir / "out").iterdir()] == ["p.yml"]


def test_a_failed_write_leaves_no_temporary_file_and_reports_it(ws, workspace_dir, monkeypatch):
    def failing_replace(source, destination):
        raise OSError("disk full")

    monkeypatch.setattr(workspace_module.os, "replace", failing_replace)

    error = _refused(lambda: ws.write_text("/out", "p.yml", "x"))

    assert error.message == "Could not save to that folder."
    assert list((workspace_dir / "out").iterdir()) == []


@pytest.mark.skipif(os.name == "nt", reason="file modes are not meaningful on Windows")
def test_a_saved_file_gets_the_ordinary_mode_from_the_umask_not_a_private_one(ws, workspace_dir):
    previous = os.umask(0o022)
    try:
        ws.write_text("/out", "p.yml", "x")
    finally:
        os.umask(previous)

    assert stat.S_IMODE((workspace_dir / "out" / "p.yml").stat().st_mode) == 0o644


@pytest.mark.parametrize("folder", ["../outside", "/../outside", "a/../../outside"])
def test_write_refuses_a_folder_outside_the_workspace(ws, outside, folder):
    error = _refused(lambda: ws.write_text(folder, "p.yml", "x"))

    assert "outside the workspace" in error.message
    assert not (outside / "p.yml").exists()


@pytest.mark.parametrize("name", ["../p.yml", "sub/p.yml", "", ".", ".."])
def test_write_refuses_a_file_name_that_is_not_a_plain_name(ws, name):
    assert "valid file name" in _refused(lambda: ws.write_text("/out", name, "x")).message


def test_a_link_in_the_way_is_replaced_never_written_through(ws, workspace_dir, outside):
    (workspace_dir / "out").mkdir()
    _link(outside / "secret.swarch", workspace_dir / "out" / "p.yml")

    ws.write_text("/out", "p.yml", "generated")

    assert (outside / "secret.swarch").read_text(encoding="utf-8") == "do not read"
    placed = workspace_dir / "out" / "p.yml"
    assert not placed.is_symlink()
    assert placed.read_text(encoding="utf-8") == "generated"


def test_a_link_to_another_file_inside_the_workspace_is_not_written_through_either(ws, workspace_dir):
    (workspace_dir / "script.sh").write_text("echo keep me", encoding="utf-8")
    (workspace_dir / "out").mkdir()
    _link(workspace_dir / "script.sh", workspace_dir / "out" / "p.yml")

    ws.write_text("/out", "p.yml", "generated")

    assert (workspace_dir / "script.sh").read_text(encoding="utf-8") == "echo keep me"


def test_write_refuses_a_folder_link_leading_out(ws, workspace_dir, outside):
    _link(outside, workspace_dir / "elsewhere")

    _refused(lambda: ws.write_text("/elsewhere", "p.yml", "x"))

    assert not (outside / "p.yml").exists()


def test_write_reports_a_folder_that_is_really_a_file(ws, workspace_dir):
    (workspace_dir / "out").write_text("i am a file", encoding="utf-8")

    error = _refused(lambda: ws.write_text("/out", "p.yml", "x"))

    assert error.message == "Could not save to that folder."


# --- the folder itself ------------------------------------------------------


def test_a_missing_workspace_folder_is_reported_as_unavailable(tmp_path):
    ws = Workspace(tmp_path / "not-mounted")

    assert ws.available is False
    assert _refused(lambda: ws.browse("/", False)).status == 503
    assert _refused(lambda: ws.write_text("/", "p.yml", "x")).status == 503


def test_normalize_gives_the_display_form_or_none(ws):
    assert ws.normalize("./a/b.swarch") == "/a/b.swarch"
    assert ws.normalize("../x") is None
    assert ws.normalize("a\x00") is None


# --- odd entries and failures --------------------------------------------------


class _Scan:
    """Stands in for os.scandir's result, to hand the listing entries it cannot look at."""

    def __init__(self, entries):
        self._entries = entries

    def __enter__(self):
        return iter(self._entries)

    def __exit__(self, *exc):
        return False


class _Unreadable:
    name = "locked.swarch"
    path = "locked.swarch"

    def is_dir(self):
        raise PermissionError("denied")

    is_file = is_symlink = is_dir


@pytest.mark.skipif(os.name == "nt", reason="these names cannot be created on Windows")
def test_browse_skips_names_that_cannot_be_shown_or_typed_back(ws, workspace_dir):
    (workspace_dir / "ok.swarch").write_text("", encoding="utf-8")
    (workspace_dir / "back\\slash.swarch").write_text("", encoding="utf-8")
    with open(os.fsencode(workspace_dir) + b"/bad-\xff.swarch", "w", encoding="utf-8") as handle:
        handle.write("")

    assert [e["name"] for e in ws.browse("/", False)["entries"]] == ["ok.swarch"]


def test_one_entry_that_cannot_be_looked_at_does_not_fail_the_whole_listing(ws, workspace_dir, monkeypatch):
    (workspace_dir / "good.swarch").write_text("", encoding="utf-8")
    with os.scandir(workspace_dir) as scan:
        real_entries = list(scan)
    monkeypatch.setattr(workspace_module.os, "scandir", lambda path: _Scan([_Unreadable(), *real_entries]))

    assert [e["name"] for e in ws.browse("/", False)["entries"]] == ["good.swarch"]


def test_a_folder_that_cannot_be_read_is_reported_not_crashed_on(ws, monkeypatch):
    def refusing(path):
        raise PermissionError("denied")

    monkeypatch.setattr(workspace_module.os, "scandir", refusing)

    error = _refused(lambda: ws.browse("/", False))

    assert error.status == 500
    assert error.message == "Could not list this folder."


def test_a_file_that_cannot_be_looked_at_reads_as_not_there(ws, workspace_dir, monkeypatch):
    (workspace_dir / "a.swarch").write_text("<a/>", encoding="utf-8")

    def refusing(self):
        raise PermissionError("denied")

    monkeypatch.setattr(workspace_module.Path, "is_file", refusing)

    assert _refused(lambda: ws.read_text("/a.swarch", 100)).status == 404


def test_saving_onto_a_folder_of_that_name_is_reported_and_leaves_nothing_behind(ws, workspace_dir):
    (workspace_dir / "out" / "p.yml").mkdir(parents=True)

    error = _refused(lambda: ws.write_text("/out", "p.yml", "x"))

    assert error.message == "Could not save to that folder."
    assert [p.name for p in (workspace_dir / "out").iterdir()] == ["p.yml"]


def test_a_dangling_link_in_the_way_counts_as_a_replaced_file(ws, workspace_dir):
    (workspace_dir / "out").mkdir()
    _link(workspace_dir / "nowhere.txt", workspace_dir / "out" / "p.yml")

    saved = ws.write_text("/out", "p.yml", "x")

    assert saved.replaced is True
    assert not (workspace_dir / "out" / "p.yml").is_symlink()


def test_a_save_folder_reached_through_a_link_inside_the_workspace_lands_on_the_real_folder(ws, workspace_dir):
    (workspace_dir / "real").mkdir()
    _link(workspace_dir / "real", workspace_dir / "alias")

    saved = ws.write_text("/alias", "p.yml", "x")

    assert saved.path == "/real/p.yml"
    assert (workspace_dir / "real" / "p.yml").read_text(encoding="utf-8") == "x"


# --- git's own folder -----------------------------------------------------------


def test_the_git_folder_is_never_listed(ws, workspace_dir):
    (workspace_dir / ".git").mkdir()
    (workspace_dir / ".github").mkdir()

    names = [entry["name"] for entry in ws.browse("/", dirs_only=False)["entries"]]

    assert ".git" not in names
    assert ".github" in names


@pytest.mark.parametrize("folder", ["/.git", "/.git/hooks", "/repos/app/.git/hooks"])
def test_nothing_is_ever_written_into_a_git_folder(ws, workspace_dir, folder):
    with pytest.raises(WorkspaceError):
        ws.write_text(folder, ".gitlab-ci.yml", "stages: [build]\n")

    assert not list(workspace_dir.rglob(".gitlab-ci.yml"))


def test_a_git_folder_cannot_be_browsed_or_read_from(ws, workspace_dir):
    hooks = workspace_dir / ".git" / "hooks"
    hooks.mkdir(parents=True)
    (hooks / "a.swarch").write_text("<a/>", encoding="utf-8")

    with pytest.raises(WorkspaceError):
        ws.browse("/.git/hooks", dirs_only=False)
    with pytest.raises(WorkspaceError):
        ws.read_text("/.git/hooks/a.swarch", 1024)


def test_a_link_into_a_git_folder_is_refused_like_the_folder_itself(ws, workspace_dir):
    (workspace_dir / ".git" / "hooks").mkdir(parents=True)
    _link(workspace_dir / ".git" / "hooks", workspace_dir / "innocent")

    with pytest.raises(WorkspaceError):
        ws.write_text("/innocent", ".gitlab-ci.yml", "stages: [build]\n")
