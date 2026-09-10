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
    private static final String ECORE_FIXTURES = "./src/test/resources/testCases/validation/ecore/";
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

    // Real bug this once was: a platform with no genmodel/compiled Java
    // package of its own (every platform except the ones EMFUtils.init()
    // hardcodes) always failed with "the metamodel couldn't be resolved",
    // regardless of how correct the .mtl itself was - confirmed via a real
    // end-to-end run generating a genuinely new platform's own metamodel
    // and Acceleo template. customPlatform.ecore/.mtl are a small,
    // purpose-built fixture (not one of the hardcoded PIM/SWArch/GitLab
    // metamodels), proving the fix is real dynamic registration, not an
    // accidental match against an already-registered nsURI.
    @Test
    public void unregisteredPlatformMetamodelResolvesWhenGivenTheTargetEcore() {
        AcceleoCompileResult compileResult = AcceleoValidator.validate(
                FIXTURES + "customPlatform.mtl", FIXTURES + "customPlatform.ecore");
        ValidationResult result = compileResult.result();

        assertTrue(result.valid(), "expected clean compile once the target metamodel is registered, got: " + result.issues());
    }

    // Real bug this once was: validate(mtl, targetEcore) put the loaded
    // target metamodel into EPackage.Registry.INSTANCE unconditionally.
    // When a generated PSM .ecore reused a compiled metamodel's own nsURI
    // (an LLM copying the reference GitLab metamodel wholesale, confirmed
    // in a real run), that generic dynamic package shadowed the compiled
    // one, and the next EMFUtils.init() threw ClassCastException casting
    // getEFactory(nsURI) to the compiled GitlabMMFactory. The compiled
    // package must win, and the real module must still compile against it.
    @Test
    public void generatedMetamodelReusingAKnownNsUriKeepsTheCompiledMetamodel() {
        AcceleoCompileResult compileResult = AcceleoValidator.validate(
                REAL_MTL, FIXTURES + "gitlabNsUriClash.ecore");
        ValidationResult result = compileResult.result();

        assertTrue(result.valid(),
                "expected the real module to still compile against the compiled GitLab metamodel, got: " + result.issues());
    }

    // A target ecore that won't load must fail with a real reason from the
    // reflective ecore check (here: the DOCTYPE rejection EMFUtils applies
    // to untrusted content), not a bare "could not load", so the
    // regenerate loop has something to act on.
    @Test
    public void unloadableTargetMetamodelReportsTheRealReasonNotJustCouldNotLoad() {
        AcceleoCompileResult compileResult = AcceleoValidator.validate(
                REAL_MTL, ECORE_FIXTURES + "malicious-doctype.ecore");
        ValidationResult result = compileResult.result();

        assertFalse(result.valid());
        assertTrue(result.issues().stream().anyMatch(i -> i.message().toLowerCase().contains("doctype")),
                "expected the DOCTYPE rejection to surface through the acceleo path, got: " + result.issues());
    }

    // Parses as a valid EObject but its root isn't an EPackage, so
    // loadEPackage() returns null while the reflective check finds nothing
    // wrong: the plain-message fallback path, still an ERROR, never a
    // silent pass or a crash.
    @Test
    public void targetEcoreWhoseRootIsNotAnEPackageFailsWithAPlainMessage() {
        AcceleoCompileResult compileResult = AcceleoValidator.validate(
                REAL_MTL, FIXTURES + "targetNotAnEPackage.ecore");
        ValidationResult result = compileResult.result();

        assertFalse(result.valid());
        assertTrue(result.issues().stream().anyMatch(i -> i.severity() == ValidationIssue.Severity.ERROR
                        && i.message().toLowerCase().contains("target metamodel")),
                "expected an ERROR naming the target metamodel, got: " + result.issues());
    }

    // No nsURI to register under at all: the guard skips the dynamic
    // registration, so an unregistered-platform module then simply fails
    // to resolve its declared metamodel. Must not throw.
    @Test
    public void targetEcoreWithNoNsUriDoesNotThrowAndFailsToResolve() {
        AcceleoCompileResult compileResult = AcceleoValidator.validate(
                FIXTURES + "customPlatform.mtl", FIXTURES + "targetNoNsUri.ecore");
        ValidationResult result = compileResult.result();

        assertFalse(result.valid());
    }

    @Test
    public void unregisteredPlatformMetamodelFailsToResolveWithoutTheTargetEcore() {
        // Same real .mtl, no ecore given - the single-arg overload's own
        // existing behavior, confirming the two-arg overload's own success
        // above is really the dynamic registration doing the work, not
        // something else about this fixture.
        AcceleoCompileResult compileResult = AcceleoValidator.validate(FIXTURES + "customPlatform.mtl");
        ValidationResult result = compileResult.result();

        assertFalse(result.valid());
        assertTrue(result.issues().stream().anyMatch(i -> i.message().toLowerCase().contains("resolved")),
                "expected an unresolved-metamodel error, got: " + result.issues());
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
