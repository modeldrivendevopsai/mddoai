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

    // Where the compiled .emtl module is persisted after a run actually
    // produces one, instead of being wiped before any caller can see it -
    // same shared, writable area AtlValidator's/EcoreValidator's own
    // OUTPUT_ROOT reads (see AtlValidator's own comment for why this repo
    // wants the real compiled artifact kept, not just a validation side
    // effect, and why this class never needs to know about run_id itself).
    private static final String OUTPUT_ROOT =
            System.getenv().getOrDefault("VALIDATOR_OUTPUT_DIR", System.getProperty("java.io.tmpdir"));

    private AcceleoValidator() {
    }

    public static AcceleoCompileResult validate(String mtlFilePath) {
        requireNonBlank(mtlFilePath);

        File file = new File(mtlFilePath);
        if (!file.exists()) {
            return AcceleoCompileResult.of(ValidationResult.of(List.of(new ValidationIssue(
                    ValidationIssue.Severity.ERROR, "File does not exist: " + mtlFilePath, mtlFilePath))));
        }

        // AcceleoCompilerHelper scans its source folder for files ending in
        // ".mtl" rather than parsing the single file it's given directly (unlike
        // EcoreValidator's resource.load() or AtlValidator's compiler.compile(
        // Reader, ...), which both parse content regardless of filename). A file
        // copied into that folder under any other extension is invisible to the
        // scan: execute() finds nothing to compile, doesn't throw, and this
        // method would otherwise report a trivial, wrong "valid" result -
        // confirmed empirically (a non-.mtl file with garbage or even another
        // file type's real content silently reports valid:true).
        if (!file.getName().toLowerCase(java.util.Locale.ROOT).endsWith(".mtl")) {
            return AcceleoCompileResult.of(ValidationResult.of(List.of(new ValidationIssue(
                    ValidationIssue.Severity.ERROR,
                    "Not a .mtl file: " + mtlFilePath, mtlFilePath))));
        }

        return compileInIsolatedWorkDir(file, mtlFilePath);
    }

    // AcceleoCompilerHelper compiles a whole source folder, not a single file
    // (unlike AtlStandaloneCompiler.compile(Reader, target)) - so the target
    // file is isolated into its own source folder first, under the same
    // shared OUTPUT_ROOT AtlValidator's/EcoreValidator's own compiled output
    // lives in, rather than a plain system temp dir. Split out of validate()
    // itself so that method stays the guard-clauses-then-delegate shape.
    private static AcceleoCompileResult compileInIsolatedWorkDir(File file, String mtlFilePath) {
        File workDir = new File(OUTPUT_ROOT, "acceleo-validate-" + java.util.UUID.randomUUID());
        // Cleared in the finally on every exit that isn't keeping the .emtl,
        // including a Throwable that isn't an Exception (a StackOverflowError
        // from a pathological module) - matches EcoreValidator's own
        // try/finally rather than deleting only on the happy path and the
        // catch.
        boolean keepOutput = false;
        try {
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

            ValidationResult result = compile(sourceDir, outputDir, mtlFilePath);
            // Keep only what's actually real: execute() can throw without ever
            // writing a .emtl (a broken-enough module produces nothing in
            // outputDir at all) - checking outputDir's real contents, not the
            // pass/fail outcome, is what tells the two cases apart.
            keepOutput = hasAnyFile(outputDir);
            return new AcceleoCompileResult(result, keepOutput ? outputDir.getAbsolutePath() : null);
        } catch (Exception e) {
            return AcceleoCompileResult.of(ValidationResult.of(List.of(new ValidationIssue(
                    ValidationIssue.Severity.ERROR, "Failed to compile .mtl file: " + e, mtlFilePath))));
        } finally {
            if (!keepOutput) {
                deleteRecursively(workDir);
            }
        }
    }

    private static boolean hasAnyFile(File dir) {
        File[] children = dir.listFiles();
        if (children == null) {
            return false;
        }
        for (File child : children) {
            if (child.isFile() || hasAnyFile(child)) {
                return true;
            }
        }
        return false;
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
