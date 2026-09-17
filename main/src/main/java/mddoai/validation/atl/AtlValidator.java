package main.java.mddoai.validation.atl;

import main.java.mddoai.execution.atl.AtlExecutor;
import main.java.mddoai.validation.ValidationIssue;
import main.java.mddoai.validation.ValidationResult;
import org.eclipse.m2m.atl.engine.compiler.AtlCompiler;
import org.eclipse.m2m.atl.engine.compiler.AtlStandaloneCompiler;
import org.eclipse.m2m.atl.engine.compiler.CompileTimeError;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Headless validation of {@code .atl} transformation source: whether it
 * actually compiles to real ATL bytecode (.asm) via ATL's own standalone
 * compiler. Never throws for a bad input file — a bad file is reported as a
 * failing {@link ValidationResult}, not an exception. Only programmer error
 * (null/empty path) throws.
 */
public final class AtlValidator {

    // Where the compiled .asm is persisted after a run actually produces one,
    // instead of being wiped before any caller can see it — the real,
    // runnable transformation bytecode, not just a validation side effect
    // (this repo's own stated direction is to eventually assemble a real
    // conversion pipeline from these compiled artifacts, alongside Ecore's
    // own compiled model classes and Acceleo's compiled .emtl templates, not
    // just use them for debugging one failed check). Same env var
    // EcoreValidator's own codegen path reads (see its OUTPUT_ROOT) — one
    // shared, writable area every validator that produces a real compiled
    // artifact writes into, scoped per run_id by the Python caller
    // (validator_runner.py) before this process even starts; this class
    // never needs to know about run_id itself.
    private static final String OUTPUT_ROOT =
            System.getenv().getOrDefault("VALIDATOR_OUTPUT_DIR", System.getProperty("java.io.tmpdir"));

    // The env var real Docker deployments set (see ai/docker-compose.yml's
    // validator-agent service) to point at a real, fixed PIM model instance
    // - main/'s own real Java test fixture (see ai/CLAUDE.md's "third
    // exception" for why a real model instance belongs under main/'s test
    // resources, read-only, even for an ai/ service, and why validator_agent
    // now bind-mounts this exact file the same narrow way integration_runner
    // already does for the generation stage). ATL's source metamodel never
    // varies by target platform - it's always this project's own fixed PIM
    // - so one shared real instance is enough to smoke-test ANY future
    // platform's generated ATL, not just the one it happened to be authored
    // against.
    private static final String PIM_SAMPLE_INSTANCE_PATH_ENV = "ATL_SMOKE_TEST_PIM_INSTANCE_PATH";

    private AtlValidator() {
    }

    // A system property, checked before the env var above, so a real Gradle
    // unit test (no Docker, no real env var to set for one JVM process) can
    // still exercise real execution-based validation end to end with
    // System.setProperty(...), while a real Docker deployment keeps using
    // the env var unchanged (see EcoreValidator's/AtlValidator's own
    // OUTPUT_ROOT for the same env-var-first convention this mirrors).
    // Neither set (e.g. a plain unit test calling validate(String) alone):
    // execution-based validation is simply skipped, falling back to
    // compile-only, exactly like AcceleoValidator's own optional
    // targetEcoreFilePath already does when omitted.
    private static String pimSampleInstancePath() {
        String property = System.getProperty(PIM_SAMPLE_INSTANCE_PATH_ENV);
        return property != null ? property : System.getenv(PIM_SAMPLE_INSTANCE_PATH_ENV);
    }

    // Additive overload: on top of everything validate(String) already does
    // (compiling to real ATL bytecode), also actually RUNS the compiled
    // transformation against a real PIM model instance, once a real
    // targetEcoreFilePath is given - this is what catches a real runtime-only
    // failure (e.g. "The class 'X' is not a valid classifier", an ATL lazy
    // rule instantiating an abstract target) that compiles perfectly well
    // (legal syntax, a real referenced type) but crashes the moment it
    // actually runs, confirmed for real against a genuine LLM-generated ATL
    // that passed this class's own compile-only check, got approved, and
    // only then failed - with nothing left to retry against - at the
    // pipeline's own final execution stage. Compiling has never implied a
    // transformation actually produces correct real output; this closes
    // that gap during atl_stage's own retry loop instead, where a real
    // failure still has real regenerate rounds left to fix it in. A
    // null/blank targetEcoreFilePath, or an environment with no
    // PIM_SAMPLE_INSTANCE_PATH mounted, behaves exactly like validate(String)
    // alone - execution-based checking is additive, never a precondition for
    // the existing compile-only pass/fail.
    public static AtlCompileResult validate(String atlFilePath, String targetEcoreFilePath) {
        AtlCompileResult compileResult = validate(atlFilePath);
        if (!compileResult.result().valid()) {
            return compileResult;
        }
        if (targetEcoreFilePath == null || targetEcoreFilePath.isBlank()) {
            return compileResult;
        }
        String pimSamplePath = pimSampleInstancePath();
        if (pimSamplePath == null || pimSamplePath.isBlank()) {
            return compileResult;
        }
        try {
            String atlSource = Files.readString(Path.of(atlFilePath), StandardCharsets.UTF_8);
            String targetEcore = Files.readString(Path.of(targetEcoreFilePath), StandardCharsets.UTF_8);
            String pimInstance = Files.readString(Path.of(pimSamplePath), StandardCharsets.UTF_8);
            String outputModelName = AtlExecutor.parseOutputModelName(atlSource);
            AtlExecutor.execute(atlSource, pimInstance, targetEcore, outputModelName);
            return compileResult;
        } catch (Exception e) {
            return new AtlCompileResult(ValidationResult.of(List.of(new ValidationIssue(
                    ValidationIssue.Severity.ERROR,
                    "ATL compiled but failed to actually run against a real PIM model instance: " + e.getMessage(),
                    atlFilePath))), compileResult.generatedOutputPath());
        }
    }

    public static AtlCompileResult validate(String atlFilePath) {
        requireNonBlank(atlFilePath);

        File file = new File(atlFilePath);
        if (!file.exists()) {
            return AtlCompileResult.of(ValidationResult.of(List.of(new ValidationIssue(
                    ValidationIssue.Severity.ERROR, "File does not exist: " + atlFilePath, atlFilePath))));
        }

        AtlStandaloneCompiler compiler = AtlCompiler.getCompiler(AtlCompiler.DEFAULT_COMPILER_NAME);

        File workDir = new File(OUTPUT_ROOT, "atl-validate-" + java.util.UUID.randomUUID());
        // Cleared in the finally on every exit that isn't keeping the .asm,
        // including a Throwable that isn't an Exception (a StackOverflowError
        // from a pathological source) - matches EcoreValidator's own
        // try/finally rather than deleting only on the happy path and the
        // catch.
        boolean keepOutput = false;
        try {
            if (!workDir.mkdirs()) {
                throw new java.io.IOException("Could not create validator output directory: " + workDir);
            }
            File target = new File(workDir, baseName(file) + ".asm");

            try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
                CompileTimeError[] errors = compiler.compile(reader, target.getAbsolutePath());
                // Keep only what's actually real: compile() can report errors
                // without ever writing target (a broken-enough source produces
                // no .asm at all) - checking the file itself, not the error
                // count, is what tells the two cases apart.
                keepOutput = target.exists();
                List<ValidationIssue> issues = toIssues(errors, atlFilePath);
                if (!keepOutput && issues.isEmpty()) {
                    // ATLCompiler.atl (ATL's own standalone compiler, itself
                    // written in ATL) can crash internally - a real
                    // VMException from its own codegen, printed to stderr by
                    // the EMFVM launcher rather than raised as a catchable
                    // Java exception - and still return normally with an
                    // empty errors array, confirmed for real against a
                    // genuine LLM-generated .atl source whose real execution
                    // later failed despite this method reporting it valid.
                    // No .asm produced plus no reported errors is never a
                    // real pass, it's this exact silent-crash case.
                    issues = List.of(new ValidationIssue(ValidationIssue.Severity.ERROR,
                            "ATL compilation produced no output and reported no errors "
                                    + "(the ATL compiler's own internal execution likely crashed - check stderr)",
                            atlFilePath));
                }
                ValidationResult result = ValidationResult.of(issues);
                return new AtlCompileResult(result, keepOutput ? target.getAbsolutePath() : null);
            }
        } catch (Exception e) {
            return AtlCompileResult.of(ValidationResult.of(List.of(new ValidationIssue(
                    ValidationIssue.Severity.ERROR, "Failed to compile .atl file: " + e, atlFilePath))));
        } finally {
            if (!keepOutput) {
                deleteRecursively(workDir);
            }
        }
    }

    private static List<ValidationIssue> toIssues(CompileTimeError[] errors, String sourceFile) {
        List<ValidationIssue> issues = new ArrayList<>();
        for (CompileTimeError error : errors) {
            ValidationIssue.Severity severity = "error".equalsIgnoreCase(error.getSeverity())
                    ? ValidationIssue.Severity.ERROR
                    : ValidationIssue.Severity.WARNING;
            issues.add(new ValidationIssue(severity, error.getDescription(),
                    locate(sourceFile, error.getLocation())));
        }
        return issues;
    }

    private static String locate(String sourceFile, String location) {
        return location != null && !location.isBlank() ? sourceFile + "#" + location : sourceFile;
    }

    private static String baseName(File file) {
        String name = file.getName();
        int dot = name.lastIndexOf('.');
        return dot > 0 ? name.substring(0, dot) : name;
    }

    private static void requireNonBlank(String path) {
        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException("Atl file path cannot be null or empty");
        }
    }

    private static void deleteRecursively(File file) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                deleteRecursively(child);
            }
        }
        file.delete();
    }
}
