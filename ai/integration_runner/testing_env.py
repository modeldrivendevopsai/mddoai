"""For tests only: the settings integration_runner takes from its environment, which Docker
Compose sets in a container. Both its own tests and orchestrator's, which import it,
import this before anything of integration_runner. A setting a test already set is left alone."""
import os
from pathlib import Path

_REPO = Path(__file__).resolve().parents[2]

# The PIM metamodel and the sample PIM instance, which Docker Compose mounts read-only.
os.environ.setdefault("PIM_METAMODEL_PATH", str(_REPO / "meta_models" / "com.mddoai.metamodel.pim" / "model" / "pimMM.ecore"))
os.environ.setdefault(
    "PIM_SAMPLE_INSTANCE_PATH",
    str(_REPO / "main" / "src" / "test" / "resources" / "testCases" / "execution" / "gitlab" / "input.pimmm"),
)
