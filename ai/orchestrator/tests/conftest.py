import assistant
import orchestrator
import stage_agents

# Several test files (test_orchestrator.py's record_event tests,
# test_assistant.py's nudge()/react_to_event() tests) exercise real
# narration, which only works once a reactor is wired in (see
# orchestrator.set_reactor()). main.py does this at its own import time for
# the app itself; test files that never import main.py need it done
# somewhere too, once, for the whole session, rather than every file
# repeating the same module-level line.
orchestrator.set_reactor(assistant.react_to_event)


def pytest_configure(config):
    # Tests must not depend on a developer's local .env, notably
    # ORCHESTRATOR_STUB_DOCS (see stage_agents.py), meant only for
    # interactive UI testing against a running Docker container, never for
    # the automated suite. Force it off for the whole test session;
    # test_docs_agent_returns_stub_output_without_calling_retrieval_when_flag_set
    # still exercises the stub path itself by patching stage_agents._STUB_DOCS
    # directly.
    stage_agents._STUB_DOCS = False
