"""Configuration read from the environment, in one place."""
import os
from pathlib import Path

# The SWArch-to-PIM transformation this service runs, as ATL source text.
# Docker mounts the folder holding the engine's own transformation, read-only,
# and this names the file in it; pointing it at another file is how an
# improved transformation is put to use, with no change to any other service.
# The default resolves to that same engine file when the service is run from
# its own folder in a checkout.
SWARCH2PIM_ATL_PATH = Path(
    os.environ.get(
        "SWARCH2PIM_ATL_PATH",
        str(Path(__file__).resolve().parents[2] / "main" / "src" / "main" / "resources" / "transformations" / "swarch2pim" / "swarch2pim.atl"),
    )
)

# A real .swarch model is a handful of attributes. The ceiling is far above
# any real file and only stops an absurdly large body being passed on to a
# JVM. Engineering guess, the same one pipeline_generator uses for models.
MAX_MODEL_BYTES = int(os.environ.get("SWARCH_MAX_MODEL_BYTES", str(256 * 1024)))
