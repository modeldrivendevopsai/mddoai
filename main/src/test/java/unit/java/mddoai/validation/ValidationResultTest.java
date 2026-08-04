package test.java.unit.java.mddoai.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.ValidationIssue;
import main.java.mddoai.validation.ValidationResult;

public class ValidationResultTest {

    @Test
    public void okHasNoIssuesAndIsValid() {
        ValidationResult result = ValidationResult.ok();

        assertTrue(result.valid());
        assertTrue(result.issues().isEmpty());
    }

    @Test
    public void ofIsValidWhenOnlyWarningsPresent() {
        ValidationResult result = ValidationResult.of(List.of(
                new ValidationIssue(ValidationIssue.Severity.WARNING, "a warning", "src")));

        assertTrue(result.valid());
        assertEquals(1, result.issues().size());
    }

    @Test
    public void ofIsInvalidWhenAnyErrorPresent() {
        ValidationResult result = ValidationResult.of(List.of(
                new ValidationIssue(ValidationIssue.Severity.WARNING, "a warning", "src"),
                new ValidationIssue(ValidationIssue.Severity.ERROR, "an error", "src")));

        assertFalse(result.valid());
        assertEquals(2, result.issues().size());
    }

    @Test
    public void constructorRejectsNullIssues() {
        assertThrows(IllegalArgumentException.class, () -> new ValidationResult(true, null));
    }

    @Test
    public void issueConstructorRejectsNullSeverity() {
        assertThrows(IllegalArgumentException.class,
                () -> new ValidationIssue(null, "message", "src"));
    }

    @Test
    public void issueConstructorRejectsBlankMessage() {
        assertThrows(IllegalArgumentException.class,
                () -> new ValidationIssue(ValidationIssue.Severity.ERROR, "  ", "src"));
    }
}
