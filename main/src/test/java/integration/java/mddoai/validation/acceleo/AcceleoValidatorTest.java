package test.java.integration.java.mddoai.validation.acceleo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.ValidationIssue;
import main.java.mddoai.validation.ValidationResult;
import main.java.mddoai.validation.acceleo.AcceleoCompileResult;
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
    private static final String REAL_MTL = "../code_generation/com.mddoai.codegeneration.gitlab.acceleo/src/"
            + "com/mddoai/codegeneration/gitlab/acceleo/main/generate.mtl";

    @Test
    public void realShippedGenerateMtlCompilesClean() {
        AcceleoCompileResult compileResult = AcceleoValidator.validate(REAL_MTL);
        ValidationResult result = compileResult.result();

        assertTrue(result.valid(), "expected clean compile, got: " + result.issues());
    }

    @Test
    public void realCleanCompileKeepsTheRealEmtlOutputOnDisk() {
        // The whole point of keeping compiled output: it must actually be a
        // real, readable file on disk, not just a non-null path string.
        AcceleoCompileResult compileResult = AcceleoValidator.validate(REAL_MTL);

        assertTrue(compileResult.result().valid());
        String path = compileResult.generatedOutputPath();
        assertTrue(path != null && new File(path).isDirectory(),
                "expected a real output directory on disk, got: " + path);
        assertTrue(containsFileNamed(new File(path), ".emtl"),
                "expected a compiled .emtl file under: " + path);
    }

    @Test
    public void nonexistentFileHasNoGeneratedOutput() {
        AcceleoCompileResult compileResult = AcceleoValidator.validate(FIXTURES + "nonexistent.mtl");

        assertFalse(compileResult.result().valid());
        assertNull(compileResult.generatedOutputPath());
    }

    @Test
    public void unclosedForBlockSurfacesRealCompilerError() {
        // Distinct failure class from ATL's fixtures: an Acceleo template block
        // ([for]...[/for]) left unterminated, not a generic unmatched-paren parse
        // error or a reserved-word identifier misuse.
        AcceleoCompileResult compileResult = AcceleoValidator.validate(FIXTURES + "brokenUnclosedFor.mtl");
        ValidationResult result = compileResult.result();

        assertFalse(result.valid());
        assertTrue(result.issues().stream().anyMatch(i -> i.severity() == ValidationIssue.Severity.ERROR
                        && i.message().toLowerCase().contains("terminated")),
                "expected an ERROR-severity issue about an unterminated block, got: " + result.issues());
    }

    private static boolean containsFileNamed(File dir, String suffix) {
        File[] children = dir.listFiles();
        if (children == null) {
            return false;
        }
        for (File child : children) {
            if (child.isFile() && child.getName().endsWith(suffix)) {
                return true;
            }
            if (child.isDirectory() && containsFileNamed(child, suffix)) {
                return true;
            }
        }
        return false;
    }
}
