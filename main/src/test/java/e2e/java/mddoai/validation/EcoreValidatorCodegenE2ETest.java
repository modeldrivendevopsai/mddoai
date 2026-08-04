package test.java.e2e.java.mddoai.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.EcoreValidator;
import main.java.mddoai.validation.ValidationIssue;
import main.java.mddoai.validation.ValidationResult;

/**
 * Full genmodel+javac pipeline: real temp dirs, real EMF codegen, real javac.
 * Matches E2EExecutorTest's real-pipeline pattern (no mocking).
 */
public class EcoreValidatorCodegenE2ETest {

    private static final String FIXTURES = "./src/test/resources/testCases/validation/";

    @Test
    public void validEcoreCompilesClean() {
        ValidationResult result = EcoreValidator.validateViaCodegen(FIXTURES + "valid.ecore");

        assertTrue(result.valid(), "expected clean compile, got: " + result.issues());
    }

    @Test
    public void brokenReflectiveFixtureShortCircuitsBeforeCodegen() {
        ValidationResult reflective = EcoreValidator.validateReflectively(FIXTURES + "broken-dangling-reference.ecore");
        ValidationResult viaCodegen = EcoreValidator.validateViaCodegen(FIXTURES + "broken-dangling-reference.ecore");

        assertFalse(viaCodegen.valid());
        // Fail-fast: validateViaCodegen returns the reflective result verbatim, proving
        // codegen/javac never ran (no codegen-stage issues were appended).
        assertEquals(reflective.issues(), viaCodegen.issues());
    }

    @Test
    public void uncompilableFixtureSurfacesRealJavacDiagnostic() {
        ValidationResult result = EcoreValidator.validateViaCodegen(FIXTURES + "broken-uncompilable.ecore");

        assertFalse(result.valid());
        boolean hasRealCompilerError = result.issues().stream()
                .filter(i -> i.severity() == ValidationIssue.Severity.ERROR)
                .anyMatch(i -> i.message().contains("com.nonexistent.pkg.NoSuchAgentType")
                        || i.message().toLowerCase().contains("cannot find symbol")
                        || i.message().toLowerCase().contains("cannot be resolved"));
        assertTrue(hasRealCompilerError,
                "expected a real javac compiler error naming the bogus type, got: " + result.issues());
    }

    @Test
    public void realShippedGitlabMetamodelCompilesCleanEndToEnd() {
        ValidationResult result = EcoreValidator.validateViaCodegen(
                "../meta_models/com.mddoai.metamodel.gitlab/model/gitlabMM.ecore");

        assertTrue(result.valid(), "expected clean compile, got: " + result.issues());
    }
}
