package main.java.mddoai.validation.acceleo;

import main.java.mddoai.utils.EMFUtils;
import main.java.mddoai.validation.ValidationIssue;
import main.java.mddoai.validation.ValidationResult;
import org.eclipse.acceleo.parser.compiler.AcceleoCompilerHelper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Headless validation of {@code .mtl} transformation source: whether it
 * actually compiles to a real Acceleo module (.emtl) via Acceleo's own
 * classic standalone compiler ({@code AcceleoCompilerHelper}, from the
 * vendored {@code org.eclipse.acceleo.parser} jar - see build.gradle for its
 * provenance). Never throws for a bad input file - a bad file is reported as
 * a failing {@link ValidationResult}, not an exception. Only programmer error
 * (null/empty path) throws.
 */
public final class AcceleoValidator {

    // AcceleoCompilerHelper.execute() has no structured error API (unlike ATL's
    // CompileTimeError[]) and prints nothing to System.out/err: on failure it
    // throws a bare RuntimeException whose getMessage() is the failing file's
    // name followed by one "<1-based source line>:<message>" line per real
    // diagnostic, e.g. "generate.mtl\n13:'for' block body isn't terminated\n\n".
    // Confirmed empirically (an unresolved-metamodel failure and an unclosed
    // [for] block) since the classic compiler's own docs don't state this.
    private static final Pattern DIAGNOSTIC_LINE = Pattern.compile("^(\\d+):(.*)$");

    private AcceleoValidator() {
    }

    public static ValidationResult validate(String mtlFilePath) {
        requireNonBlank(mtlFilePath);

        File file = new File(mtlFilePath);
        if (!file.exists()) {
            return ValidationResult.of(List.of(new ValidationIssue(
                    ValidationIssue.Severity.ERROR, "File does not exist: " + mtlFilePath, mtlFilePath)));
        }

        // AcceleoCompilerHelper compiles a whole source folder, not a single file
        // (unlike AtlStandaloneCompiler.compile(Reader, target)) - so the target
        // file is isolated into its own temp source folder first.
        File workDir = null;
        try {
            workDir = Files.createTempDirectory("acceleo-validate").toFile();
            File sourceDir = new File(workDir, "src");
            File outputDir = new File(workDir, "out");
            sourceDir.mkdirs();
            outputDir.mkdirs();
            Files.copy(file.toPath(), new File(sourceDir, file.getName()).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            // Registers this repo's known metamodels (SWArch/PIM/GitLab) by nsURI,
            // the same registration the real Generate launcher's own
            // registerPackages() performs. Without it AcceleoCompilerHelper can't
            // resolve a module's declared metamodel at all ("The metamodel
            // couldn't be resolved"), confirmed empirically.
            EMFUtils.init();

            return compile(sourceDir, outputDir, mtlFilePath);
        } catch (Exception e) {
            return ValidationResult.of(List.of(new ValidationIssue(
                    ValidationIssue.Severity.ERROR, "Failed to compile .mtl file: " + e, mtlFilePath)));
        } finally {
            if (workDir != null) {
                deleteRecursively(workDir);
            }
        }
    }

    private static ValidationResult compile(File sourceDir, File outputDir, String sourceFile) {
        AcceleoCompilerHelper compiler = new AcceleoCompilerHelper();
        compiler.setSourceFolder(sourceDir.getAbsolutePath());
        compiler.setOutputFolder(outputDir.getAbsolutePath());

        try {
            compiler.execute();
        } catch (RuntimeException e) {
            return ValidationResult.of(toIssues(e.getMessage(), sourceFile));
        }
        return ValidationResult.ok();
    }

    private static List<ValidationIssue> toIssues(String failureMessage, String sourceFile) {
        List<ValidationIssue> issues = new ArrayList<>();
        if (failureMessage != null) {
            for (String line : failureMessage.split("\\R")) {
                Matcher matcher = DIAGNOSTIC_LINE.matcher(line);
                if (matcher.matches()) {
                    issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                            matcher.group(2).trim(), sourceFile + "#" + matcher.group(1)));
                }
            }
        }
        if (issues.isEmpty()) {
            // Compilation failed but the message didn't contain a single
            // parseable "<line>:<message>" diagnostic - surface the raw failure
            // rather than silently reporting success.
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    "Failed to compile .mtl file: " + failureMessage, sourceFile));
        }
        return issues;
    }

    private static void requireNonBlank(String path) {
        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException("Mtl file path cannot be null or empty");
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
