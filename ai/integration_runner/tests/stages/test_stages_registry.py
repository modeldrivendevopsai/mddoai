"""integration_runner/stages/__init__.py unit tests: the STAGE_DESCRIPTIONS,
STAGE_DETAILS, and stage_agents dicts that assemble the seven per-file
stage agents into the lookups pipeline.py/orchestrator/the UI actually read
from.

Tests verify:
  1. stage_agents maps every stage name to its real agent function.
  2. STAGE_DESCRIPTIONS has an entry for every stage in stage_agents (the
     system prompt's stage list can't silently omit one).
  3. STAGE_DETAILS covers every stage too, and STAGE_DESCRIPTIONS is
     genuinely derived from it (not a separately hand-maintained copy that
     could drift).
"""
from integration_runner import stages
from integration_runner.stages.acceleo.agent import acceleo_stage
from integration_runner.stages.atl.agent import atl_stage
from integration_runner.stages.docs.agent import docs_stage
from integration_runner.stages.generation.agent import gen_stage
from integration_runner.stages.pim.agent import pim_stage
from integration_runner.stages.psm.agent import psm_stage
from integration_runner.stages.serialization.agent import serialization_stage


def test_stage_agents_maps_stage_names_to_agent_functions():
    assert stages.stage_agents == {
        "docs": docs_stage,
        "serialization": serialization_stage,
        "pim": pim_stage,
        "psm": psm_stage,
        "atl": atl_stage,
        "acceleo": acceleo_stage,
        "generation": gen_stage,
    }


def test_stage_descriptions_covers_every_stage_in_stage_agents():
    assert set(stages.STAGE_DESCRIPTIONS) == set(stages.stage_agents)


def test_stage_details_covers_every_stage_in_stage_agents():
    assert set(stages.STAGE_DETAILS) == set(stages.stage_agents)


def test_stage_descriptions_is_derived_from_stage_details():
    assert stages.STAGE_DESCRIPTIONS == {stage: info.description for stage, info in stages.STAGE_DETAILS.items()}


def test_stage_details_marks_stages_with_no_real_implementation_yet():
    # pim still ignores its real input and always returns the same fixed
    # placeholder content (see its own agent.py) - this is the one flag a
    # UI reads to show that honestly. docs/serialization/psm/atl/acceleo
    # are each real.
    assert stages.STAGE_DETAILS["pim"].real is False
    assert stages.STAGE_DETAILS["docs"].real is True
    assert stages.STAGE_DETAILS["serialization"].real is True
    assert stages.STAGE_DETAILS["psm"].real is True
    assert stages.STAGE_DETAILS["atl"].real is True
    assert stages.STAGE_DETAILS["acceleo"].real is True
