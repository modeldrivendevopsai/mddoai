import orchestrator


def pytest_configure(config):
    # Tests must not depend on a developer's local .env, notably
    # ORCHESTRATOR_STUB_DOCS (see orchestrator.py), meant only for
    # interactive UI testing against a running Docker container, never for
    # the automated suite. Force it off for the whole test session;
    # test_docs_agent_returns_stub_output_without_calling_retrieval_when_flag_set
    # still exercises the stub path itself by patching orchestrator._STUB_DOCS
    # directly.
    orchestrator._STUB_DOCS = False
