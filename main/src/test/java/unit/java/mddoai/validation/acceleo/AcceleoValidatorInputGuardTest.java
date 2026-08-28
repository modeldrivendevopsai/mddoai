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

    @Test
    public void validateRejectsFileWithoutMtlExtensionInsteadOfFalsePositive() {
        // AcceleoCompilerHelper compiles a whole source folder and finds files by
        // their ".mtl" extension, not by parsing whatever single file it's handed
        // (unlike EcoreValidator/AtlValidator, which parse content directly). A
        // non-.mtl file dropped into that folder is invisible to the compiler's
        // scan, so execute() would otherwise finish having "compiled" nothing and
        // report a false valid:true - regardless of how malformed its content is.
        ValidationResult result = AcceleoValidator.validate(
                "./src/test/resources/testCases/validation/acceleo/notActuallyMtl.txt");

        assertFalse(result.valid(), "a non-.mtl file must never be reported as a valid Acceleo module");
        assertEquals(1, result.issues().size());
    }
}
