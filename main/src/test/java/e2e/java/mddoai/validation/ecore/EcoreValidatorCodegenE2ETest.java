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
        ValidationResult result = EcoreValidator.validateViaCodegen(FIXTURES + "valid.ecore");

        assertTrue(result.valid(), "expected clean compile, got: " + result.issues());
    }

    @Test
    public void brokenReflectiveFixtureShortCircuitsBeforeCodegen() {
        ValidationResult reflective = EcoreValidator.validateReflectively(FIXTURES + "broken-dangling-reference.ecore");
        ValidationResult viaCodegen = EcoreValidator.validateViaCodegen(FIXTURES + "broken-dangling-reference.ecore");

        assertFalse(viaCodegen.valid());
        // Fail-fast: validateViaCodegen returns the reflective result verbatim, proving
        // codegen/javac never ran (no codegen-stage issues were appended).
        assertEquals(reflective.issues(), viaCodegen.issues());
    }

    @Test
    public void uncompilableFixtureSurfacesRealJavacDiagnostic() {
        ValidationResult result = EcoreValidator.validateViaCodegen(FIXTURES + "broken-uncompilable.ecore");

        assertFalse(result.valid());
        boolean hasRealCompilerError = result.issues().stream()
                .filter(i -> i.severity() == ValidationIssue.Severity.ERROR)
                .anyMatch(i -> i.message().contains("com.nonexistent.pkg.NoSuchAgentType")
                        || i.message().toLowerCase().contains("cannot find symbol")
                        || i.message().toLowerCase().contains("cannot be resolved"));
        assertTrue(hasRealCompilerError,
                "expected a real javac compiler error naming the bogus type, got: " + result.issues());
    }

    @Test
    public void realShippedGitlabMetamodelCompilesCleanEndToEnd() {
        ValidationResult result = EcoreValidator.validateViaCodegen(
                "../meta_models/com.mddoai.metamodel.gitlab/model/gitlabMM.ecore");

        assertTrue(result.valid(), "expected clean compile, got: " + result.issues());
    }

    // The tests above call EcoreValidator.validateViaCodegen() directly, an in-memory Java
    // call that never touches EcoreValidatorCli's toJson()/escape(). That serialization is
    // exactly what ai/integration-agent's real subprocess call depends on, and it's the one
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
