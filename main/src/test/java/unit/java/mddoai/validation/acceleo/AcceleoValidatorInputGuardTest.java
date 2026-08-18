package test.java.unit.java.mddoai.validation.acceleo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.ValidationResult;
import main.java.mddoai.validation.acceleo.AcceleoValidator;

public class AcceleoValidatorInputGuardTest {

    @Test
    public void validateRejectsNullPath() {
        assertThrows(IllegalArgumentException.class, () -> AcceleoValidator.validate(null));
    }

    @Test
    public void validateRejectsEmptyPath() {
        assertThrows(IllegalArgumentException.class, () -> AcceleoValidator.validate("  "));
    }

    @Test
    public void validateReportsNonexistentPathAsIssueNotThrow() {
        ValidationResult result = AcceleoValidator.validate(
                "./src/test/resources/testCases/validation/acceleo/nonexistent.mtl");

        assertFalse(result.valid());
        assertEquals(1, result.issues().size());
    }
}
