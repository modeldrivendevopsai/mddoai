package test.java.unit.java.mddoai.validation.atl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.ValidationResult;
import main.java.mddoai.validation.atl.AtlValidator;

public class AtlValidatorInputGuardTest {

    @Test
    public void validateRejectsNullPath() {
        assertThrows(IllegalArgumentException.class, () -> AtlValidator.validate(null));
    }

    @Test
    public void validateRejectsEmptyPath() {
        assertThrows(IllegalArgumentException.class, () -> AtlValidator.validate("  "));
    }

    @Test
    public void validateReportsNonexistentPathAsIssueNotThrow() {
        ValidationResult result = AtlValidator.validate(
                "./src/test/resources/testCases/validation/atl/nonexistent.atl");

        assertFalse(result.valid());
        assertEquals(1, result.issues().size());
    }
}
