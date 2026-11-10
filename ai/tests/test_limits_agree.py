"""Settings that must agree across services are separate constants, one in each service. This
pins them: a limit one service holds must not be above what the service behind it accepts, and a
caller's timeout must outlast the work it waits for."""
import ast
import operator
from pathlib import Path

AI_DIR = Path(__file__).resolve().parents[1]

_OPERATORS = {ast.Add: operator.add, ast.Sub: operator.sub, ast.Mult: operator.mul}


def _fold(node: ast.AST):
    """A number written as a literal or as arithmetic on literals, such as 512 * 1024."""
    if isinstance(node, ast.Constant) and isinstance(node.value, (int, float)):
        return node.value
    if isinstance(node, ast.BinOp) and type(node.op) in _OPERATORS:
        return _OPERATORS[type(node.op)](_fold(node.left), _fold(node.right))
    raise AssertionError(f"not a plain number: {ast.unparse(node)}")


def _constant(path: Path, name: str):
    for node in ast.parse(path.read_text(encoding="utf-8")).body:
        if isinstance(node, ast.Assign) and any(isinstance(target, ast.Name) and target.id == name for target in node.targets):
            return _fold(node.value)
        if isinstance(node, ast.AnnAssign) and isinstance(node.target, ast.Name) and node.target.id == name and node.value:
            return _fold(node.value)
    raise AssertionError(f"{name} is not set in {path}")


def test_the_validator_accepts_every_pipeline_the_generator_may_produce():
    generator = _constant(AI_DIR / "pipeline_generator" / "settings.py", "MAX_PIPELINE_BYTES")
    validator = _constant(AI_DIR / "validator_agent" / "main.py", "MAX_PIPELINE_BYTES")

    assert validator >= generator


def test_a_caller_waits_longer_for_the_validator_than_the_validator_waits_for_its_own_work():
    caller = _constant(AI_DIR / "clients" / "validator_agent_client.py", "VALIDATE_TIMEOUT")
    service = _constant(AI_DIR / "validator_agent" / "validator_runner.py", "TIMEOUT_SECONDS")

    assert caller > service


def test_a_caller_waits_longer_for_the_execution_agent_than_it_waits_for_its_own_work():
    caller = _constant(AI_DIR / "clients" / "execution_agent_client.py", "EXECUTE_TIMEOUT")
    service = _constant(AI_DIR / "execution_agent" / "execution_runner.py", "TIMEOUT_SECONDS")

    assert caller > service
