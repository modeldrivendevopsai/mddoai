"""integration_runner/stages/__init__.py unit tests: the STAGE_DESCRIPTIONS
and stage_agents dicts that assemble the seven per-file stage agents into
the two lookups pipeline.py/orchestrator actually read from.

Tests verify:
  1. stage_agents maps every stage name to its real agent function.
  2. STAGE_DESCRIPTIONS has an entry for every stage in stage_agents (the
     system prompt's stage list can't silently omit one).
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
