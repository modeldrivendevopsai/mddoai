import pytest

from clients.journal_testing import journal_in_process


def pytest_configure(config):
    # Tests must not depend on a developer's local .env, notably
    # ORCHESTRATOR_STUB_DOCS (see stages/docs/agent.py), meant only for
    # interactive UI testing against a running Docker container, never for
    # the automated suite. Force it off for the whole test session;
    # test_docs_stage_returns_stub_output_without_calling_retrieval_when_flag_set
    # still exercises the stub path itself by patching docs_stage._STUB_DOCS
    # directly.
    from integration_runner.stages.docs import agent as docs_stage
    docs_stage._STUB_DOCS = False


@pytest.fixture(autouse=True)
def journal(tmp_path):
    """The real journal, in this process, over folders of this test alone. Any
    test that runs a run or a stage keeps its runs and attempts there, so none
    of them reads or writes another's. It is autouse, so no test file has to
    remember to isolate this itself."""
    with journal_in_process(tmp_path / "runs", tmp_path / "chats", tmp_path / "platforms"):
        yield tmp_path / "runs"
