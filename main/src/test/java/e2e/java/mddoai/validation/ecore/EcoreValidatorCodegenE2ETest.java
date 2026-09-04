package test.java.e2e.java.mddoai.validation.ecore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.ValidationIssue;
import main.java.mddoai.validation.ValidationResult;
import main.java.mddoai.validation.ecore.EcoreCodegenResult;
import main.java.mddoai.validation.ecore.EcoreValidator;
import main.java.mddoai.validation.ecore.EcoreValidatorCli;

/**
 * Full genmodel+javac pipeline: real temp dirs, real EMF codegen, real javac.
 * Matches E2EExecutorTest's real-pipeline pattern (no mocking).
 */
public class EcoreValidatorCodegenE2ETest {

    private static final String FIXTURES = "./src/test/resources/testCases/validation/ecore/";

    @Test
    public void validEcoreCompilesClean() {
        EcoreCodegenResult codegenResult = EcoreValidator.validateViaCodegen(FIXTURES + "valid.ecore");
        ValidationResult result = codegenResult.result();

        assertTrue(result.valid(), "expected clean compile, got: " + result.issues());
    }

    @Test
    public void validEcoreCodegenOutputIsPersistedNotDeleted() {
        EcoreCodegenResult codegenResult = EcoreValidator.validateViaCodegen(FIXTURES + "valid.ecore");

        assertTrue(codegenResult.result().valid(), "expected clean compile, got: " + codegenResult.result().issues());
        String outputPath = codegenResult.generatedOutputPath();
        assertTrue(outputPath != null && !outputPath.isBlank(), "expected a non-null generated output path");
        java.io.File outputDir = new java.io.File(outputPath);
        assertTrue(outputDir.isDirectory(), "expected " + outputPath + " to exist as a real directory after the call returns");
        assertTrue(new java.io.File(outputDir, "model.genmodel").isFile(), "expected model.genmodel to survive on disk");
        assertTrue(new java.io.File(outputDir, "src-gen").isDirectory(), "expected src-gen/ to survive on disk");
    }

    @Test
    public void brokenReflectiveFixtureShortCircuitsBeforeCodegen() {
        ValidationResult reflective = EcoreValidator.validateReflectively(FIXTURES + "broken-dangling-reference.ecore");
        EcoreCodegenResult viaCodegen = EcoreValidator.validateViaCodegen(FIXTURES + "broken-dangling-reference.ecore");

        assertFalse(viaCodegen.result().valid());
        // Fail-fast: validateViaCodegen returns the reflective result verbatim, proving
        // codegen/javac never ran (no codegen-stage issues were appended).
        assertEquals(reflective.issues(), viaCodegen.result().issues());
        assertEquals(null, viaCodegen.generatedOutputPath(), "nothing was generated, so no output path");
    }

    @Test
    public void uncompilableFixtureSurfacesRealJavacDiagnostic() {
        EcoreCodegenResult codegenResult = EcoreValidator.validateViaCodegen(FIXTURES + "broken-uncompilable.ecore");
        ValidationResult result = codegenResult.result();

        assertFalse(result.valid());
        boolean hasRealCompilerError = result.issues().stream()
                .filter(i -> i.severity() == ValidationIssue.Severity.ERROR)
                .anyMatch(i -> i.message().contains("com.nonexistent.pkg.NoSuchAgentType")
                        || i.message().toLowerCase().contains("cannot find symbol")
                        || i.message().toLowerCase().contains("cannot be resolved"));
        assertTrue(hasRealCompilerError,
                "expected a real javac compiler error naming the bogus type, got: " + result.issues());
        // src-gen genuinely exists even though compiling it failed - that's exactly the
        // output a human debugging the failure needs, so it must still be kept.
        String outputPath = codegenResult.generatedOutputPath();
        assertTrue(outputPath != null && !outputPath.isBlank(),
                "expected generated src-gen to be kept even though compilation failed");
    }

    @Test
    public void realShippedGitlabMetamodelCompilesCleanEndToEnd() {
        EcoreCodegenResult codegenResult = EcoreValidator.validateViaCodegen(
                "../meta_models/com.mddoai.metamodel.gitlab/model/gitlabMM.ecore");

        assertTrue(codegenResult.result().valid(), "expected clean compile, got: " + codegenResult.result().issues());
    }

    // The tests above call EcoreValidator.validateViaCodegen() directly, an in-memory Java
    // call that never touches EcoreValidatorCli's toJson()/escape(). That serialization is
    // exactly what ai/validator_agent's real subprocess call depends on, and it's the one
    // path a real javac error (which routinely spans multiple lines) has to survive intact
    // through, or the Python side misreads a real result as an unparseable-stdout infra
    // failure. Nothing else in this suite exercises codegen mode through the real CLI/JSON
    // boundary, so this is the one test standing between a regression there and it going
    // unnoticed.
    @Test
    public void codegenModeSurfacesRealJavacErrorThroughCliJson() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int exitCode = EcoreValidatorCli.run(
                new String[]{"codegen", FIXTURES + "broken-uncompilable.ecore"},
                new PrintStream(out), nullOut());

        assertEquals(0, exitCode);
        String json = out.toString(StandardCharsets.UTF_8).trim();
        assertTrue(json.contains("\"valid\":false"), "expected valid:false, got: " + json);
        boolean hasRealCompilerError = json.contains("com.nonexistent.pkg.NoSuchAgentType")
                || json.toLowerCase().contains("cannot find symbol")
                || json.toLowerCase().contains("cannot be resolved");
        assertTrue(hasRealCompilerError, "expected a real javac compiler error in the JSON, got: " + json);
    }

    private static PrintStream nullOut() {
        return new PrintStream(java.io.OutputStream.nullOutputStream());
    }
}
