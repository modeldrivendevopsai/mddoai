package test.java.integration.java.mddoai.validation.acceleo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.ValidationIssue;
import main.java.mddoai.validation.ValidationResult;
import main.java.mddoai.validation.acceleo.AcceleoValidator;

/**
 * Real Acceleo classic compilation (AcceleoCompilerHelper) against the real
 * shipped .mtl module and a deliberately broken fixture. No mocking - matches
 * AtlValidatorTest's real-shipped-transformation pattern (no separate
 * "valid.mtl" fixture, same as ATL's own tests: the real production module is
 * itself the valid case).
 */
public class AcceleoValidatorTest {

    private static final String FIXTURES = "./src/test/resources/testCases/validation/acceleo/";

    @Test
    public void realShippedGenerateMtlCompilesClean() {
        ValidationResult result = AcceleoValidator.validate(
                "../code_generation/com.mddoai.codegeneration.gitlab.acceleo/src/"
                        + "com/mddoai/codegeneration/gitlab/acceleo/main/generate.mtl");

        assertTrue(result.valid(), "expected clean compile, got: " + result.issues());
    }

    @Test
    public void unclosedForBlockSurfacesRealCompilerError() {
        // Distinct failure class from ATL's fixtures: an Acceleo template block
        // ([for]...[/for]) left unterminated, not a generic unmatched-paren parse
        // error or a reserved-word identifier misuse.
        ValidationResult result = AcceleoValidator.validate(FIXTURES + "brokenUnclosedFor.mtl");

        assertFalse(result.valid());
        assertTrue(result.issues().stream().anyMatch(i -> i.severity() == ValidationIssue.Severity.ERROR
                        && i.message().toLowerCase().contains("terminated")),
                "expected an ERROR-severity issue about an unterminated block, got: " + result.issues());
    }
}
