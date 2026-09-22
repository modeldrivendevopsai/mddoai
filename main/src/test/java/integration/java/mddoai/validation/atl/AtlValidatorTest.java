package test.java.integration.java.mddoai.validation.atl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.ValidationIssue;
import main.java.mddoai.validation.ValidationResult;
import main.java.mddoai.validation.atl.AtlCompileResult;
import main.java.mddoai.validation.atl.AtlValidator;

/**
 * Real ATL standalone compilation (AtlCompiler.getCompiler("atl2006")) against
 * the real shipped .atl transformations and a deliberately broken fixture. No
 * mocking — matches EcoreValidatorReflectiveTest's real-EMF pattern.
 */
public class AtlValidatorTest {

    private static final String FIXTURES = "./src/test/resources/testCases/validation/atl/";
    private static final String EXECUTION_FIXTURES = "./src/test/resources/testCases/execution/";
    private static final String PIM_SAMPLE_INSTANCE_PATH_PROPERTY = "ATL_SMOKE_TEST_PIM_INSTANCE_PATH";
    private static final String REAL_GITLAB_ATL_PATH = "./src/main/resources/transformations/pim2psm/pim2gitlabmodel.atl";
    private static final String REAL_GITLAB_ECORE_PATH = "../meta_models/com.mddoai.metamodel.gitlab/model/gitlabMM.ecore";

    @AfterEach
    public void clearPimSampleInstanceProperty() {
        // AtlValidator.pimSampleInstancePath() checks this system property
        // before ATL_SMOKE_TEST_PIM_INSTANCE_PATH's own env var - the only
        // way a plain Gradle unit test (one JVM, no Docker, no real env var
        // to set) can exercise real execution-based validation end to end.
        // Cleared after every test so a test that sets it never leaks into
        // an unrelated one run later in the same JVM.
        System.clearProperty(PIM_SAMPLE_INSTANCE_PATH_PROPERTY);
    }

    @Test
    public void realShippedSwarch2PimAtlCompilesClean() {
        AtlCompileResult compileResult = AtlValidator.validate(
                "./src/main/resources/transformations/swarch2pim/swarch2pim.atl");
        ValidationResult result = compileResult.result();

        assertTrue(result.valid(), "expected clean compile, got: " + result.issues());
    }

    @Test
    public void realShippedPim2GitlabModelAtlCompilesClean() {
        AtlCompileResult compileResult = AtlValidator.validate(
                "./src/main/resources/transformations/pim2psm/pim2gitlabmodel.atl");
        ValidationResult result = compileResult.result();

        assertTrue(result.valid(), "expected clean compile, got: " + result.issues());
    }

    @Test
    public void realCleanCompileKeepsTheRealAsmOutputOnDisk() {
        // The whole point of keeping compiled output: it must actually be a
        // real, readable file on disk, not just a non-null path string.
        AtlCompileResult compileResult = AtlValidator.validate(
                "./src/main/resources/transformations/swarch2pim/swarch2pim.atl");

        assertTrue(compileResult.result().valid());
        String path = compileResult.generatedOutputPath();
        assertTrue(path != null && new File(path).isFile(),
                "expected a real .asm file on disk, got: " + path);
        assertTrue(path.endsWith(".asm"), "expected a .asm file, got: " + path);
    }

    @Test
    public void emptyFileIsReportedAsError() {
        AtlCompileResult compileResult = AtlValidator.validate(FIXTURES + "empty.atl");
        ValidationResult result = compileResult.result();

        assertFalse(result.valid());
    }

    @Test
    public void nonexistentFileHasNoGeneratedOutput() {
        AtlCompileResult compileResult = AtlValidator.validate(FIXTURES + "nonexistent.atl");

        assertFalse(compileResult.result().valid());
        assertNull(compileResult.generatedOutputPath());
    }

    @Test
    public void brokenSyntaxAtlSurfacesRealParserError() {
        AtlCompileResult compileResult = AtlValidator.validate(FIXTURES + "broken-atl-syntax.atl");
        ValidationResult result = compileResult.result();

        assertFalse(result.valid());
        assertTrue(result.issues().stream().anyMatch(i -> i.severity() == ValidationIssue.Severity.ERROR),
                "expected an ERROR-severity issue, got: " + result.issues());
        assertTrue(result.issues().stream().anyMatch(i -> !i.message().isBlank()),
                "expected a real, non-blank compiler message, got: " + result.issues());
    }

    @Test
    public void lazyRuleTargetingARawCollectionIsReportedInvalidNotSilentlyValid() {
        // ATLCompiler.atl (ATL's own standalone compiler, itself written in
        // ATL) can crash internally with a real VMException from its own
        // codegen - printed to stderr by the EMFVM launcher, not raised as
        // a catchable Java exception - while leaving both the errors array
        // and the .asm output empty. Confirmed for real: a genuine
        // LLM-generated .atl this exact fixture reduces to compiled "clean"
        // here before this test existed, then failed for real once
        // AtlExecutor actually tried to run it (see AtlExecutorTest and
        // this fixture's own file header for the real construct that
        // triggers it). No output plus no errors must never read as valid.
        AtlCompileResult compileResult = AtlValidator.validate(FIXTURES + "lazy-rule-targets-raw-collection.atl");
        ValidationResult result = compileResult.result();

        assertFalse(result.valid(), "expected this to be reported invalid, got: " + result.issues());
        assertTrue(result.issues().stream().anyMatch(i -> i.severity() == ValidationIssue.Severity.ERROR),
                "expected a real ERROR-severity issue, got: " + result.issues());
        assertNull(compileResult.generatedOutputPath());
    }

    @Test
    public void reservedWordUsedAsIdentifierSurfacesRealParserError() {
        // "rule" is an ATL keyword; using it as a variable name is syntactically
        // valid-looking but not valid ATL grammar. Distinct from an unmatched-paren
        // style error — proves the parser rejects reserved-word misuse specifically,
        // not just gross structural breakage.
        AtlCompileResult compileResult = AtlValidator.validate(FIXTURES + "broken-atl-reserved-word.atl");
        ValidationResult result = compileResult.result();

        assertFalse(result.valid());
        assertTrue(result.issues().stream()
                        .anyMatch(i -> i.severity() == ValidationIssue.Severity.ERROR
                                && i.message().toLowerCase().contains("rule")),
                "expected an ERROR-severity issue naming 'rule', got: " + result.issues());
    }

    @Test
    public void realAtlThatActuallyRunsPassesTheExecutionSmokeTestToo() throws Exception {
        // The real, hand-authored pim2gitlabmodel.atl, given a real target
        // metamodel and a real PIM sample instance to actually run against
        // (not just compile) - proves the happy path of the new overload:
        // a genuinely runnable transformation stays valid end to end.
        System.setProperty(PIM_SAMPLE_INSTANCE_PATH_PROPERTY, EXECUTION_FIXTURES + "gitlab/input.pimmm");

        AtlCompileResult compileResult = AtlValidator.validate(REAL_GITLAB_ATL_PATH, REAL_GITLAB_ECORE_PATH);
        ValidationResult result = compileResult.result();

        assertTrue(result.valid(), "expected the real transformation to actually run clean, got: " + result.issues());
    }

    @Test
    public void atlThatCompilesButInstantiatesAnAbstractClassifierIsReportedInvalid() throws Exception {
        // This is the real bug that motivated the execution smoke test:
        // this exact ATL/ecore pair compiles perfectly clean (legal syntax,
        // a real referenced type) - AtlValidator.validate(String) alone
        // reports it valid - but crashes for real the moment it actually
        // runs, since PIM!Trigger/GenericCICDMM!Trigger are abstract and
        // this rule set instantiates the base type directly instead of
        // dispatching to a concrete subtype rule (confirmed for real
        // against a genuine LLM-generated transformation - see
        // AtlExecutorTest's own aRealAtlRuntimeFailureIsReportedCleanlyNotThrownRaw
        // for the same fixture's own execution-level proof). Compiling has
        // never implied a transformation actually produces correct real
        // output; this is what catches that gap during atl_stage's own
        // retry loop instead of only later, with no retries left.
        System.setProperty(PIM_SAMPLE_INSTANCE_PATH_PROPERTY, EXECUTION_FIXTURES + "gitlab/input.pimmm");
        String atlPath = EXECUTION_FIXTURES + "generic-cicd/pim2genericcicd-abstract-trigger.atl";
        String targetEcorePath = EXECUTION_FIXTURES + "generic-cicd/genericCICDMM.ecore";

        AtlCompileResult compileOnly = AtlValidator.validate(atlPath);
        assertTrue(compileOnly.result().valid(),
                "expected this fixture to still compile clean on its own, got: " + compileOnly.result().issues());

        AtlCompileResult compileResult = AtlValidator.validate(atlPath, targetEcorePath);
        ValidationResult result = compileResult.result();

        assertFalse(result.valid(), "expected the real runtime failure to be caught, got a clean pass");
        assertTrue(result.issues().stream().anyMatch(i -> i.severity() == ValidationIssue.Severity.ERROR
                        && i.message().contains("Trigger")),
                "expected a real ERROR naming the abstract classifier, got: " + result.issues());
    }

    @Test
    public void executionSmokeTestIsSkippedWithoutAConfiguredPimSampleInstance() throws Exception {
        // No PIM_SAMPLE_INSTANCE_PATH property (nor, in this test JVM, the
        // real env var) configured: even a target ecore given, this must
        // fall back to compile-only behavior rather than fail trying to
        // read a sample instance that was never configured - the same
        // fixture as the previous test would otherwise be caught as
        // invalid here too.
        String atlPath = EXECUTION_FIXTURES + "generic-cicd/pim2genericcicd-abstract-trigger.atl";
        String targetEcorePath = EXECUTION_FIXTURES + "generic-cicd/genericCICDMM.ecore";

        AtlCompileResult compileResult = AtlValidator.validate(atlPath, targetEcorePath);

        assertTrue(compileResult.result().valid(),
                "expected compile-only fallback when no PIM sample instance is configured, got: "
                        + compileResult.result().issues());
    }
}
