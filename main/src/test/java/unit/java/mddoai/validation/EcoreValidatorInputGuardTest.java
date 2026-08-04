package test.java.unit.java.mddoai.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.EcoreValidator;
import main.java.mddoai.validation.ValidationResult;

public class EcoreValidatorInputGuardTest {

    @Test
    public void validateReflectivelyRejectsNullPath() {
        assertThrows(IllegalArgumentException.class, () -> EcoreValidator.validateReflectively(null));
    }

    @Test
    public void validateReflectivelyRejectsEmptyPath() {
        assertThrows(IllegalArgumentException.class, () -> EcoreValidator.validateReflectively("  "));
    }

    @Test
    public void validateReflectivelyReportsNonexistentPathAsIssueNotThrow() {
        ValidationResult result = EcoreValidator.validateReflectively(
                "./src/test/resources/testCases/validation/nonexistent.ecore");

        assertFalse(result.valid());
        assertEquals(1, result.issues().size());
    }

    @Test
    public void validateViaCodegenRejectsNullPath() {
        assertThrows(IllegalArgumentException.class, () -> EcoreValidator.validateViaCodegen(null));
    }

    @Test
    public void validateViaCodegenRejectsEmptyPath() {
        assertThrows(IllegalArgumentException.class, () -> EcoreValidator.validateViaCodegen(""));
    }
}
