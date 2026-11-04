package test.java.unit.java.mddoai.validation.swarch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.ValidationResult;
import main.java.mddoai.validation.swarch.SwarchValidator;

public class SwarchValidatorInputGuardTest {

    @Test
    public void validateRejectsNullPath() {
        assertThrows(IllegalArgumentException.class, () -> SwarchValidator.validate(null));
    }

    @Test
    public void validateRejectsEmptyPath() {
        assertThrows(IllegalArgumentException.class, () -> SwarchValidator.validate("  "));
    }

    @Test
    public void validateReportsNonexistentPathAsIssueNotThrow() {
        ValidationResult result = SwarchValidator.validate(
                "./src/test/resources/testCases/validation/swarch/nonexistent.swarch");

        assertFalse(result.valid());
        assertEquals(1, result.issues().size());
    }
}
