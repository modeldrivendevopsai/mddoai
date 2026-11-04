package test.java.integration.java.mddoai.validation.swarch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import main.java.mddoai.validation.ValidationIssue;
import main.java.mddoai.validation.ValidationResult;
import main.java.mddoai.validation.swarch.SwarchValidator;

/**
 * Real EMF load against the fixtures in src/test/resources/testCases/validation/swarch,
 * matching EcoreValidatorReflectiveTest's headless-EMF pattern (plain ResourceSetImpl, no OSGi).
 */
public class SwarchValidatorTest {

    private static final String FIXTURES = "./src/test/resources/testCases/validation/swarch/";

    @Test
    public void validSwarchHasNoIssues() {
        ValidationResult result = SwarchValidator.validate(FIXTURES + "valid.swarch");

        assertTrue(result.valid(), "expected no issues, got: " + result.issues());
        assertTrue(result.issues().isEmpty());
    }

    @Test
    public void missingRequiredFeatureIsReportedByName() {
        ValidationResult result = SwarchValidator.validate(FIXTURES + "broken-missing-required.swarch");

        assertFalse(result.valid());
        assertTrue(containsMessageMentioning(result, "language"),
                "expected an issue naming 'language', got: " + result.issues());
    }

    // A message is shown to a person who uploaded a file, not a developer:
    // it must name the metamodel class, not EMF's internal implementation
    // class/hash, carry no aggregate "Diagnosis of" line, and not leak the
    // server-side path the file happened to be validated from.
    @Test
    public void messagesCarryNoEmfInternalsOrServerPaths() {
        for (String fixture : new String[]{"broken-missing-required.swarch", "broken-unknown-feature.swarch"}) {
            ValidationResult result = SwarchValidator.validate(FIXTURES + fixture);

            for (ValidationIssue issue : result.issues()) {
                String message = issue.message();
                assertFalse(message.contains("Diagnosis of"), fixture + ": " + message);
                assertFalse(message.contains("Impl@"), fixture + ": " + message);
                assertFalse(message.contains("file:/"), fixture + ": " + message);
                assertFalse(message.contains("src/test/resources"), fixture + ": " + message);
            }
        }
    }

    @Test
    public void missingRequiredFeatureIsNamedAgainstTheMetamodelClass() {
        ValidationResult result = SwarchValidator.validate(FIXTURES + "broken-missing-required.swarch");

        assertTrue(containsMessageMentioning(result, "'language' of 'SoftwareArchitecture'"),
                "expected the metamodel class name, got: " + result.issues());
    }

    @Test
    public void blankRequiredFeatureIsReportedByName() {
        ValidationResult result = SwarchValidator.validate(FIXTURES + "broken-blank-required.swarch");

        assertFalse(result.valid());
        assertTrue(containsMessageMentioning(result, "'name'"),
                "expected an issue naming 'name', got: " + result.issues());
    }

    @Test
    public void unknownFeatureIsReported() {
        ValidationResult result = SwarchValidator.validate(FIXTURES + "broken-unknown-feature.swarch");

        assertFalse(result.valid());
        assertTrue(containsMessageMentioning(result, "noSuchFeature"),
                "expected an issue naming noSuchFeature, got: " + result.issues());
    }

    @Test
    public void malformedXmlIsReportedNotThrown() {
        ValidationResult result = SwarchValidator.validate(FIXTURES + "broken-malformed-xml.swarch");

        assertFalse(result.valid());
    }

    @Test
    public void emptyFileIsReportedNotThrown() {
        ValidationResult result = SwarchValidator.validate(FIXTURES + "empty.swarch");

        assertFalse(result.valid());
    }

    // Same real vulnerability class as EcoreValidatorReflectiveTest's own
    // doctype test: this is the entry point for real caller-submitted content.
    @Test
    public void doctypeDeclarationIsRejectedNotParsed() {
        ValidationResult result = SwarchValidator.validate(FIXTURES + "malicious-doctype.swarch");

        assertFalse(result.valid(), "a .swarch file with a DOCTYPE declaration must be rejected, not parsed");
        // Rejected for the DOCTYPE itself, not for some unrelated reason such
        // as the entity target not existing on this machine.
        assertTrue(containsMessageMentioning(result, "DOCTYPE"),
                "expected the DOCTYPE to be named as the reason, got: " + result.issues());
    }

    // A caller's copy of the model may be saved under any name; the content is
    // XMI regardless. Used to crash on a name whose extension EMF has no
    // factory for.
    @Test
    public void aValidModelIsRecognisedWhateverTheFileIsCalled(@TempDir Path directory) throws Exception {
        String xml = Files.readString(Path.of(FIXTURES + "valid.swarch"));

        for (String name : new String[]{"model.txt", "MODEL.SWARCH", "model"}) {
            Path copy = directory.resolve(name);
            Files.writeString(copy, xml);

            ValidationResult result = SwarchValidator.validate(copy.toString());

            assertTrue(result.valid(), name + ": expected a valid model, got: " + result.issues());
        }
    }

    // The generation engine loads and resolves every top-level element of a
    // file, while this validator only ever examines the first. A file holding
    // more than one is therefore not something "valid" can vouch for, so it
    // is rejected outright rather than passed with a warning.
    @Test
    public void aFileHoldingMoreThanOneModelIsRejected() {
        ValidationResult result = SwarchValidator.validate(FIXTURES + "broken-multiple-roots.swarch");

        assertFalse(result.valid(), "a file with several top-level elements must not pass, got: " + result.issues());
        assertTrue(containsMessageMentioning(result, "2 top-level elements"),
                "expected the element count to be named, got: " + result.issues());
    }

    @Test
    public void modelFromAnotherMetamodelIsRejected() {
        ValidationResult result = SwarchValidator.validate(FIXTURES + "wrong-metamodel.swarch");

        assertFalse(result.valid());
        assertTrue(containsMessageMentioning(result, "not a Software Architecture model"),
                "expected a wrong-metamodel issue, got: " + result.issues());
    }

    private static boolean containsMessageMentioning(ValidationResult result, String text) {
        return result.issues().stream()
                .map(ValidationIssue::message)
                .anyMatch(message -> message.contains(text));
    }
}
