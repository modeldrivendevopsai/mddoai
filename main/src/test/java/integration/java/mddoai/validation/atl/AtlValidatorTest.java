package test.java.integration.java.mddoai.validation.atl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.ValidationIssue;
import main.java.mddoai.validation.ValidationResult;
import main.java.mddoai.validation.atl.AtlValidator;

/**
 * Real ATL standalone compilation (AtlCompiler.getCompiler("atl2006")) against
 * the real shipped .atl transformations and a deliberately broken fixture. No
 * mocking — matches EcoreValidatorReflectiveTest's real-EMF pattern.
 */
public class AtlValidatorTest {

    private static final String FIXTURES = "./src/test/resources/testCases/validation/atl/";

    @Test
    public void realShippedSwarch2PimAtlCompilesClean() {
        ValidationResult result = AtlValidator.validate(
                "./src/main/resources/transformations/swarch2pim/swarch2pim.atl");

        assertTrue(result.valid(), "expected clean compile, got: " + result.issues());
    }

    @Test
    public void realShippedPim2GitlabModelAtlCompilesClean() {
        ValidationResult result = AtlValidator.validate(
                "./src/main/resources/transformations/pim2psm/pim2gitlabmodel.atl");

        assertTrue(result.valid(), "expected clean compile, got: " + result.issues());
    }

    @Test
    public void emptyFileIsReportedAsError() {
        ValidationResult result = AtlValidator.validate(FIXTURES + "empty.atl");

        assertFalse(result.valid());
    }

    @Test
    public void brokenSyntaxAtlSurfacesRealParserError() {
        ValidationResult result = AtlValidator.validate(FIXTURES + "broken-atl-syntax.atl");

        assertFalse(result.valid());
        assertTrue(result.issues().stream().anyMatch(i -> i.severity() == ValidationIssue.Severity.ERROR),
                "expected an ERROR-severity issue, got: " + result.issues());
        assertTrue(result.issues().stream().anyMatch(i -> !i.message().isBlank()),
                "expected a real, non-blank compiler message, got: " + result.issues());
    }

    @Test
    public void reservedWordUsedAsIdentifierSurfacesRealParserError() {
        // "rule" is an ATL keyword; using it as a variable name is syntactically
        // valid-looking but not valid ATL grammar. Distinct from an unmatched-paren
        // style error — proves the parser rejects reserved-word misuse specifically,
        // not just gross structural breakage.
        ValidationResult result = AtlValidator.validate(FIXTURES + "broken-atl-reserved-word.atl");

        assertFalse(result.valid());
        assertTrue(result.issues().stream()
                        .anyMatch(i -> i.severity() == ValidationIssue.Severity.ERROR
                                && i.message().toLowerCase().contains("rule")),
                "expected an ERROR-severity issue naming 'rule', got: " + result.issues());
    }
}
