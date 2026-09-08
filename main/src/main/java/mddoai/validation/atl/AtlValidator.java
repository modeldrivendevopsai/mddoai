package main.java.mddoai.validation.atl;

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

    private AtlValidator() {
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
        try {
            if (!workDir.mkdirs()) {
                throw new java.io.IOException("Could not create validator output directory: " + workDir);
            }
            File target = new File(workDir, baseName(file) + ".asm");

            try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
                CompileTimeError[] errors = compiler.compile(reader, target.getAbsolutePath());
                ValidationResult result = ValidationResult.of(toIssues(errors, atlFilePath));
                // Keep only what's actually real: compile() can report errors
                // without ever writing target (a broken-enough source produces
                // no .asm at all) - checking the file itself, not the error
                // count, is what tells the two cases apart.
                boolean keepOutput = target.exists();
                if (!keepOutput) {
                    deleteRecursively(workDir);
                }
                return new AtlCompileResult(result, keepOutput ? target.getAbsolutePath() : null);
            }
        } catch (Exception e) {
            deleteRecursively(workDir);
            return AtlCompileResult.of(ValidationResult.of(List.of(new ValidationIssue(
                    ValidationIssue.Severity.ERROR, "Failed to compile .atl file: " + e, atlFilePath))));
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
