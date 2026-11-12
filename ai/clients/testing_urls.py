"""For tests only: gives every client the link it needs, so importing one in a test
does not stop on a missing setting. The services' own code never does this: a
service takes its links from its environment or it does not start.

A test imports this module before anything that imports a client, usually in its
conftest. A link a test already set is left alone."""
import os

for _name, _address in (
    ("ACCELEO_AGENT_URL", "http://acceleo-agent.test:8080"),
    ("AI_LAYER_URL", "http://ai-layer.test:8000"),
    ("ATL_AGENT_URL", "http://atl-agent.test:8070"),
    ("EXECUTION_AGENT_URL", "http://execution-agent.test:8090"),
    ("INTEGRATION_RUNNER_URL", "http://integration-runner.test:8050"),
    ("JOURNAL_URL", "http://journal.test:8120"),
    ("PIM_AGENT_URL", "http://pim-agent.test:8030"),
    ("PSM_AGENT_URL", "http://psm-agent.test:8040"),
    ("RETRIEVAL_URL", "http://retrieval.test:8010"),
    ("SERIALIZATION_AGENT_URL", "http://serialization-agent.test:8060"),
    ("VALIDATOR_AGENT_URL", "http://validator-agent.test:8020"),
):
    os.environ.setdefault(_name, _address)
