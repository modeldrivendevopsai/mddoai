"""ACICDTrip Knowledge Agent tests. No LLM calls, no mocking: ground() is a plain
keyword lookup against a fixed, static knowledge list.

Tests verify:
  1. ground() returns a relevant metamodel-category result for a metamodel query.
  2. ground() returns a relevant process-category result for a transformation-process query.
  3. ground() returns a relevant reusability-category result for a reusability query.
  4. ground() returns a relevant limitation-category result for a limitation/caveat query.
  5. ground() returns an empty list for a query unrelated to ACICDTrip.
  6. ground() respects top_k, capping the number of results returned.
  7. Every returned GroundingExample has non-empty category/title/content fields.
  8. ground() matches a plural query term ("limitations") against a singular
     keyword ("limitation") via the scorer's lightweight suffix stripping,
     without reintroducing the earlier substring-matching false-positive bug.
  9. ground() returns the Job services entry for a "services" query (PR
     feedback: this previously returned zero results).
  10. ground() returns the VariableDeclaration entry for a "VariableDeclaration"
      query (PR feedback: this previously returned zero results).
"""

from pim_agent import GroundingExample, ground


def test_ground_matches_metamodel_category():
    results = ground("what does the DockerContainer concept look like")
    assert results
    assert any(r.category == "metamodel" for r in results)


def test_ground_matches_process_category():
    results = ground("how does ATL transform the input model into a platform-independent model")
    assert results
    assert any(r.category == "process" for r in results)


def test_ground_matches_reusability_category():
    results = ground("is the ACICDTrip meta-model reusable for MDDOAI")
    assert results
    assert any(r.category == "reusability" for r in results)


def test_ground_matches_limitation_category():
    results = ground("what caveats or limitations exist with ACICDTrip's generated output")
    assert results
    assert any(r.category == "limitation" for r in results)


def test_ground_returns_empty_for_unrelated_query():
    assert ground("what's the weather like in Paris tomorrow") == []


def test_ground_respects_top_k():
    results = ground("pipeline job step trigger agent docker matrix expression", top_k=2)
    assert len(results) <= 2


def test_ground_matches_plural_query_against_singular_keyword():
    # Regression test for a found gap: "limitations" (query, plural) must match
    # the limitation entry's "limitation" (singular) keyword via suffix stripping.
    results = ground("known limitations")
    assert results
    assert any(r.category == "limitation" for r in results)


def test_ground_matches_job_services_entry():
    # PR feedback (@Dhingraakshat): "services" previously returned 0 results.
    results = ground("services")
    assert results
    assert any(r.title == "Job services" for r in results)


def test_ground_matches_variable_declaration_entry():
    # PR feedback (@Dhingraakshat): "VariableDeclaration" previously returned 0 results.
    results = ground("VariableDeclaration")
    assert results
    assert any(r.title == "VariableDeclaration" for r in results)


def test_ground_results_are_well_formed():
    results = ground("Matrix axes include exclude fail-fast")
    assert results
    for result in results:
        assert isinstance(result, GroundingExample)
        assert result.category
        assert result.title
        assert result.content
