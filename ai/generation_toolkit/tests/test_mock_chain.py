"""The mock chain's three artifacts: that each follows the real PIM and that they fit each other.
Whether they compile and run is checked against the real engines, in integration_runner's tests."""
import re
import xml.etree.ElementTree as ET
from pathlib import Path

from generation_toolkit.mock_chain import (
    MOCK_ATL,
    MOCK_MTL,
    MOCK_NS_URI,
    MOCK_OUTPUT_FILENAME,
    MOCK_PSM_ECORE,
    MOCK_TEMPLATE_FILENAME,
    PIM_NS_URI,
)

# The real PIM metamodel, which Docker Compose mounts into the services that read it.
_PIM_METAMODEL = (
    Path(__file__).resolve().parents[3] / "meta_models" / "com.mddoai.metamodel.pim" / "model" / "pimMM.ecore"
)


def _classes(ecore_text: str) -> dict[str, ET.Element]:
    return {c.get("name"): c for c in ET.fromstring(ecore_text).findall("eClassifiers")}


def _own_features(cls: ET.Element) -> set[str]:
    return {f.get("name") for f in cls.findall("eStructuralFeatures")}


def _features_with_inherited(classes: dict[str, ET.Element], name: str) -> set[str]:
    found = _own_features(classes[name])
    for supertype in (classes[name].get("eSuperTypes") or "").split():
        found |= _features_with_inherited(classes, supertype.removeprefix("#//"))
    return found


def test_the_transformation_declares_the_real_pim_namespace():
    real = ET.fromstring(_PIM_METAMODEL.read_text(encoding="utf-8")).get("nsURI")

    assert PIM_NS_URI == real
    assert f"-- @nsURI PIM=pimMM={real}" in MOCK_ATL


def test_the_transformation_reads_only_classes_and_features_the_real_pim_has():
    pim = _classes(_PIM_METAMODEL.read_text(encoding="utf-8"))
    read_classes = set(re.findall(r"PIM!(\w+)", MOCK_ATL))
    read_features = set(re.findall(r"pim(?:Pipeline|Job)\.(\w+)", MOCK_ATL))

    assert read_classes <= set(pim)
    assert {"name", "jobStreams"} <= _features_with_inherited(pim, "Pipeline")
    # The binding `jobs <- pimPipeline.jobStreams` hands Jobs to a rule that matches Jobs.
    (job_streams,) = [f for f in pim["Pipeline"].findall("eStructuralFeatures") if f.get("name") == "jobStreams"]
    assert job_streams.get("eType") == "#//Job"
    assert "name" in _features_with_inherited(pim, "Job")
    assert read_features <= _features_with_inherited(pim, "Pipeline") | _features_with_inherited(pim, "Job")


def test_the_metamodel_has_its_own_namespace_which_is_not_the_pims():
    root = ET.fromstring(MOCK_PSM_ECORE)

    assert root.get("nsURI") == MOCK_NS_URI
    assert MOCK_NS_URI != PIM_NS_URI


def test_the_transformation_writes_the_mock_metamodel_by_the_name_the_executor_is_given():
    # The executor is told the output model by the name in "create OUT : <name> from IN : PIM;".
    assert f"-- @nsURI MockMM=mockMM={MOCK_NS_URI}" in MOCK_ATL
    assert "create OUT : MockMM from IN : PIM;" in MOCK_ATL


def test_the_transformation_writes_only_what_the_mock_metamodel_declares():
    mock = _classes(MOCK_PSM_ECORE)
    written_classes = set(re.findall(r"MockMM!(\w+)", MOCK_ATL))
    written_features = set(re.findall(r"^\s+(\w+) <-", MOCK_ATL, re.MULTILINE))

    assert written_classes <= set(mock)
    assert written_features <= _own_features(mock["Pipeline"]) | _own_features(mock["Job"])


def test_the_template_reads_the_mock_metamodel_and_only_what_it_declares():
    mock = _classes(MOCK_PSM_ECORE)

    assert f"[module generate('{MOCK_NS_URI}')]" in MOCK_MTL
    assert {"name", "jobs"} <= _own_features(mock["Pipeline"])
    assert "name" in _own_features(mock["Job"])
    assert "pipeline.name" in MOCK_MTL and "pipeline.jobs" in MOCK_MTL and "job.name" in MOCK_MTL


def test_the_template_is_a_module_named_after_the_file_it_is_given_and_writes_the_one_output_file():
    # Acceleo requires a module's file to be named after the module; the executor writes "generate.mtl".
    assert MOCK_TEMPLATE_FILENAME == "generate.mtl"
    assert "[module generate(" in MOCK_MTL
    assert re.findall(r"\[file \('([^']+)'", MOCK_MTL) == [MOCK_OUTPUT_FILENAME]
    assert "[comment @main/]" in MOCK_MTL
