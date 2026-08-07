package test.java.integration.java.mddoai.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.EcoreValidator;
import main.java.mddoai.validation.ValidationIssue;
import main.java.mddoai.validation.ValidationResult;

/**
 * Real EMF load against the fixtures in src/test/resources/testCases/validation,
 * matching EMFUtilsTest's headless-EMF pattern (plain ResourceSetImpl, no OSGi).
 */
public class EcoreValidatorReflectiveTest {

    private static final String FIXTURES = "./src/test/resources/testCases/validation/";

    @Test
    public void validEcoreHasNoIssues() {
        ValidationResult result = EcoreValidator.validateReflectively(FIXTURES + "valid.ecore");

        assertTrue(result.valid(), "expected no issues, got: " + result.issues());
        assertTrue(result.issues().isEmpty());
    }

    @Test
    public void danglingReferenceIsReportedByName() {
        ValidationResult result = EcoreValidator.validateReflectively(FIXTURES + "broken-dangling-reference.ecore");

        assertFalse(result.valid());
        assertTrue(containsMessageMentioning(result, "NoSuchAgent"),
                "expected an issue naming NoSuchAgent, got: " + result.issues());
    }

    @Test
    public void danglingEOppositeIsReportedByName() {
        ValidationResult result = EcoreValidator.validateReflectively(FIXTURES + "broken-dangling-eopposite.ecore");

        assertFalse(result.valid());
        assertTrue(containsMessageMentioning(result, "noSuchPrevious"),
                "expected an issue naming noSuchPrevious, got: " + result.issues());
    }

    @Test
    public void malformedXmlIsReportedAsParseError() {
        ValidationResult result = EcoreValidator.validateReflectively(FIXTURES + "broken-malformed-xml.ecore");

        assertFalse(result.valid());
        assertTrue(result.issues().stream().anyMatch(i -> i.severity() == ValidationIssue.Severity.ERROR));
    }

    @Test
    public void emptyFileIsReportedAsError() {
        ValidationResult result = EcoreValidator.validateReflectively(FIXTURES + "empty.ecore");

        assertFalse(result.valid());
    }

    @Test
    public void reflectiveCheckAcceptsUncompilableFixtureItDoesNotCheckCompilation() {
        // broken-uncompilable.ecore is reflectively VALID (proxies resolve fine, structure is
        // sound) — its problem only surfaces at the codegen+javac stage. Confirms the
        // reflective check doesn't false-positive on something it isn't meant to catch.
        ValidationResult result = EcoreValidator.validateReflectively(FIXTURES + "broken-uncompilable.ecore");

        assertTrue(result.valid(), "expected reflective check to pass, got: " + result.issues());
    }

    @Test
    public void realShippedGitlabMetamodelIsReflectivelyValid() {
        ValidationResult result = EcoreValidator.validateReflectively(
                "../meta_models/com.mddoai.metamodel.gitlab/model/gitlabMM.ecore");

        assertTrue(result.valid(), "expected no issues, got: " + result.issues());
    }

    @Test
    public void realShippedGithubMetamodelIsReflectivelyValid() {
        ValidationResult result = EcoreValidator.validateReflectively(
                "../meta_models/com.mddoai.metamodel.github/model/githubMM.ecore");

        assertTrue(result.valid(), "expected no issues, got: " + result.issues());
    }

    private static boolean containsMessageMentioning(ValidationResult result, String needle) {
        return result.issues().stream().anyMatch(i -> i.message().contains(needle));
    }
}
