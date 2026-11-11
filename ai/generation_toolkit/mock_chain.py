"""What a stage returns when it runs mocked (the "Mock" checkbox): no model is called, and
no correction changes the output.

The three artifacts belong together. The metamodel is a small mock platform with its own
namespace. The transformation reads the real PIM (its namespace and its classes) and writes
that platform. The template reads that platform and writes one file. So a mocked run compiles
at every stage, goes through generation on the real PIM, and can be published and used to
generate pipelines like any other platform.

A mocked stage takes part only in a run whose stages are all mocked. Another platform's real
metamodel does not fit a mock transformation, and the other way round.
"""

# The real PIM's namespace, which the transformation declares. A test checks it against the
# real PIM metamodel.
PIM_NS_URI = "http://www.mddoai.com/mddoai/metamodel/pim"
# The mock platform's own namespace, in the same form as every real platform's.
MOCK_NS_URI = "http://www.mddoai.com/mddoai/metamodel/mock"

# The file the mock template writes: the one file a published platform generates.
MOCK_OUTPUT_FILENAME = "mock-pipeline.yaml"
# The file name each validator and the executor give the template. Its module is named after it.
MOCK_TEMPLATE_FILENAME = "generate.mtl"

def mock_generation_result(artifact: str, prompt: dict, validation: dict, prompt_version: str | None) -> dict:
    """What every agent's generate() returns for a mocked call. In one place, so the three agents
    cannot drift apart. A mocked call learns no fix, so `round_constraints` is empty."""
    return {
        "artifact": artifact,
        "prompt": prompt,
        "validation": validation,
        "rounds": 1,
        "prompt_version": prompt_version,
        "round_constraints": [],
    }


MOCK_PSM_ECORE = f"""<?xml version="1.0" encoding="UTF-8"?>
<ecore:EPackage xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" name="mockMM" nsURI="{MOCK_NS_URI}" nsPrefix="mockMM">
  <eClassifiers xsi:type="ecore:EClass" name="Pipeline">
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name" eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"/>
    <eStructuralFeatures xsi:type="ecore:EReference" name="jobs" upperBound="-1" eType="#//Job" containment="true"/>
  </eClassifiers>
  <eClassifiers xsi:type="ecore:EClass" name="Job">
    <eStructuralFeatures xsi:type="ecore:EAttribute" name="name" eType="ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString"/>
  </eClassifiers>
</ecore:EPackage>
"""

MOCK_ATL = f"""-- @nsURI PIM=pimMM={PIM_NS_URI}
-- @nsURI MockMM=mockMM={MOCK_NS_URI}

module mock2psm;
create OUT : MockMM from IN : PIM;

rule Pipeline2Pipeline {{
	from
		pimPipeline : PIM!Pipeline
	to
		mockPipeline : MockMM!Pipeline (
			name <- pimPipeline.name,
			jobs <- pimPipeline.jobStreams
		)
}}

rule Job2Job {{
	from
		pimJob : PIM!Job
	to
		mockJob : MockMM!Job (
			name <- pimJob.name
		)
}}
"""

MOCK_MTL = f"""[comment encoding = UTF-8 /]
[module generate('{MOCK_NS_URI}')]

[template public generateElement(pipeline : Pipeline)]
[comment @main/]
[file ('{MOCK_OUTPUT_FILENAME}', false, 'UTF-8')]
name: [pipeline.name/]
jobs:
[for (job : Job | pipeline.jobs)]
  - [job.name/]
[/for]
[/file]
[/template]
"""
