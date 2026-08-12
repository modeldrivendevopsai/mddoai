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
import java.nio.file.Files;
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

    private AtlValidator() {
    }

    public static ValidationResult validate(String atlFilePath) {
        requireNonBlank(atlFilePath);

        File file = new File(atlFilePath);
        if (!file.exists()) {
            return ValidationResult.of(List.of(new ValidationIssue(
                    ValidationIssue.Severity.ERROR, "File does not exist: " + atlFilePath, atlFilePath)));
        }

        AtlStandaloneCompiler compiler = AtlCompiler.getCompiler(AtlCompiler.DEFAULT_COMPILER_NAME);

        File workDir = null;
        try {
            workDir = Files.createTempDirectory("atl-validate").toFile();
            File target = new File(workDir, baseName(file) + ".asm");

            try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
                CompileTimeError[] errors = compiler.compile(reader, target.getAbsolutePath());
                return ValidationResult.of(toIssues(errors, atlFilePath));
            }
        } catch (Exception e) {
            return ValidationResult.of(List.of(new ValidationIssue(
                    ValidationIssue.Severity.ERROR, "Failed to compile .atl file: " + e, atlFilePath)));
        } finally {
            if (workDir != null) {
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
