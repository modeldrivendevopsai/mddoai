package main.java.mddoai.validation.acceleo;

import main.java.mddoai.execution.acceleo.AcceleoExecutor;
import main.java.mddoai.execution.atl.AtlExecutor;
import main.java.mddoai.utils.EMFUtils;
import main.java.mddoai.validation.ValidationIssue;
import main.java.mddoai.validation.ValidationResult;
import org.eclipse.acceleo.parser.compiler.AcceleoCompilerHelper;
import org.eclipse.emf.ecore.EPackage;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    // Same real, fixed PIM model instance AtlValidator's own execution smoke
    // test reads (see its own comment for why one shared instance covers
    // any future platform, and its own pimSampleInstancePath() for why this
    // checks a system property before the env var) - needed here too since
    // a real PSM model instance to actually run the .mtl template against
    // doesn't exist on its own; it only comes from actually running this
    // same run's already-generated ATL against this same fixed PIM instance
    // first.
    private static final String PIM_SAMPLE_INSTANCE_PATH_ENV = "ATL_SMOKE_TEST_PIM_INSTANCE_PATH";

    private AcceleoValidator() {
    }

    private static String pimSampleInstancePath() {
        String property = System.getProperty(PIM_SAMPLE_INSTANCE_PATH_ENV);
        return property != null ? property : System.getenv(PIM_SAMPLE_INSTANCE_PATH_ENV);
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

    // Additive overload: on top of everything validate(String) already does
    // (including EMFUtils.init()'s own fixed PIM/SWArch/GitLab registration,
    // still needed by the mock-mode path below, which always targets the
    // registered GitLab metamodel regardless of which real platform is
    // under test - see acceleo_agent/generation.py's own _MOCK_ARTIFACT),
    // also dynamically registers the given platform's own real target
    // metamodel, so a freshly-generated platform with no genmodel or
    // compiled Java package of its own can still be resolved - unless a
    // compiled package already owns that nsURI, in which case the compiled
    // one is kept (see the inline comments below). See
    // EMFUtils.loadEPackage()'s own comment for why this needs no code
    // generation or compile step at all. A null/blank targetEcoreFilePath
    // behaves exactly like validate(String) alone.
    public static AcceleoCompileResult validate(String mtlFilePath, String targetEcoreFilePath) {
        if (targetEcoreFilePath != null && !targetEcoreFilePath.isBlank()) {
            // Register this build's own compiled metamodels first. Each
            // compiled *Factory's static init casts
            // EPackage.Registry.INSTANCE.getEFactory(nsURI) to its own
            // concrete *Factory type, so that init must run while the
            // registry still holds the compiled factory for that nsURI,
            // never a dynamic EPackage put under the same nsURI below.
            EMFUtils.init();

            EPackage ePackage = EMFUtils.loadEPackage(targetEcoreFilePath);
            if (ePackage == null) {
                return AcceleoCompileResult.of(ValidationResult.of(
                        describeUnloadableTargetMetamodel(targetEcoreFilePath)));
            }

            // Register the dynamically loaded metamodel only when no
            // package already resolves for its nsURI. A generated .ecore
            // that reuses a known nsURI - e.g. an LLM copying the reference
            // GitLab metamodel wholesale, nsURI and all - must not shadow
            // the compiled package: the compiled one resolves the module
            // just as well, and overwriting it makes EMFUtils.init()'s own
            // (compiled *Factory) getEFactory(nsURI) cast throw a
            // ClassCastException for the rest of this JVM. getEPackage(),
            // not containsKey(), so a lazy Descriptor already registered
            // for that nsURI counts as resolved too.
            String nsURI = ePackage.getNsURI();
            if (nsURI != null && EPackage.Registry.INSTANCE.getEPackage(nsURI) == null) {
                EPackage.Registry.INSTANCE.put(nsURI, ePackage);
            }
        }
        return validate(mtlFilePath);
    }

    // Additive overload: on top of everything validate(String, String)
    // already does (compiling, plus dynamically registering the target
    // metamodel), also actually RUNS the compiled module against a real PSM
    // model instance, once a real atlFilePath is given too - the same class
    // of gap AtlValidator's own execution smoke test closes on the ATL side
    // (see its own comment), applied here: a .mtl template can compile
    // cleanly and still fail the moment it actually generates real output
    // (e.g. a template feature that only resolves against a real model
    // instance's real structure). There's no real PSM model instance to run
    // the template against on its own - it only exists once this run's own
    // already-generated atlFilePath is actually executed against the same
    // fixed PIM sample AtlValidator uses, so this overload does exactly
    // that first. A null/blank atlFilePath, or an environment with no
    // PIM_SAMPLE_INSTANCE_PATH mounted, behaves exactly like
    // validate(String, String) alone.
    public static AcceleoCompileResult validate(String mtlFilePath, String targetEcoreFilePath, String atlFilePath) {
        AcceleoCompileResult compileResult = validate(mtlFilePath, targetEcoreFilePath);
        if (!compileResult.result().valid()) {
            return compileResult;
        }
        if (targetEcoreFilePath == null || targetEcoreFilePath.isBlank()) {
            return compileResult;
        }
        if (atlFilePath == null || atlFilePath.isBlank()) {
            return compileResult;
        }
        String pimSamplePath = pimSampleInstancePath();
        if (pimSamplePath == null || pimSamplePath.isBlank()) {
            return compileResult;
        }
        try {
            String mtlSource = Files.readString(Path.of(mtlFilePath), StandardCharsets.UTF_8);
            String atlSource = Files.readString(Path.of(atlFilePath), StandardCharsets.UTF_8);
            String targetEcore = Files.readString(Path.of(targetEcoreFilePath), StandardCharsets.UTF_8);
            String pimInstance = Files.readString(Path.of(pimSamplePath), StandardCharsets.UTF_8);
            String outputModelName = AtlExecutor.parseOutputModelName(atlSource);
            String psmInstance = AtlExecutor.execute(atlSource, pimInstance, targetEcore, outputModelName);
            Map<String, String> generatedFiles = AcceleoExecutor.execute(mtlSource, psmInstance, targetEcore);

            // Every real platform this project targets produces YAML CI/CD
            // config - AcceleoExecutor.execute() already guarantees real,
            // non-empty file content (see its own comment), but never checks
            // that content is actually well-formed for the format it claims
            // to be. Confirmed for real: a genuinely "valid" template (real
            // compile, real non-empty execution) produced content that
            // failed to parse as YAML at all (a template's own [for] loop
            // emitting list items at a shallower indent than their parent
            // key) - this closes exactly that gap, feeding a real parser
            // diagnostic back into the same regenerate loop every other
            // issue here already flows through.
            List<ValidationIssue> yamlIssues = validateGeneratedYaml(generatedFiles, mtlFilePath);
            if (!yamlIssues.isEmpty()) {
                return new AcceleoCompileResult(ValidationResult.of(yamlIssues), compileResult.generatedOutputPath());
            }

            persistGeneratedFiles(generatedFiles, compileResult.generatedOutputPath());
            return compileResult;
        } catch (Exception e) {
            return new AcceleoCompileResult(ValidationResult.of(List.of(new ValidationIssue(
                    ValidationIssue.Severity.ERROR,
                    "Acceleo compiled but failed to actually generate output from a real PSM model instance: "
                            + e.getMessage(),
                    mtlFilePath))), compileResult.generatedOutputPath());
        }
    }

    private static List<ValidationIssue> validateGeneratedYaml(Map<String, String> generatedFiles, String sourceFile) {
        List<ValidationIssue> issues = new ArrayList<>();
        Yaml yaml = new Yaml();
        for (Map.Entry<String, String> entry : generatedFiles.entrySet()) {
            try {
                yaml.load(entry.getValue());
            } catch (YAMLException e) {
                issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                        "Generated file '" + entry.getKey() + "' is not valid YAML: " + e.getMessage(),
                        sourceFile));
            }
        }
        return issues;
    }

    // Kept alongside the compiled .emtl module a real, kept-output smoke
    // test already writes to disk (see compileInIsolatedWorkDir()), under
    // its own "generated" subfolder - a human debugging why an attempt
    // passed or failed needs the real file(s) the smoke test actually
    // produced, not just whether it passed. No-op when generatedOutputPath
    // is null (nothing was kept - see compileInIsolatedWorkDir()'s own
    // hasAnyFile() check), matching every other real-output path here that
    // tolerates a smoke test never having run.
    private static void persistGeneratedFiles(Map<String, String> generatedFiles, String generatedOutputPath) {
        if (generatedOutputPath == null) {
            return;
        }
        File generatedDir = new File(generatedOutputPath, "generated");
        for (Map.Entry<String, String> entry : generatedFiles.entrySet()) {
            try {
                File target = new File(generatedDir, entry.getKey());
                Files.createDirectories(target.getParentFile().toPath());
                Files.writeString(target.toPath(), entry.getValue(), StandardCharsets.UTF_8);
            } catch (java.io.IOException ignored) {
                // Best-effort: a failure to persist a debug copy must never
                // fail a smoke test that otherwise passed.
            }
        }
    }

    // A null from loadEPackage() only says the file didn't parse to a
    // single EPackage, not why. Run the reflective ecore check to turn
    // that into a real diagnostic (parse error, DOCTYPE rejected, dangling
    // reference, ...) the regenerate loop can actually act on, falling
    // back to a plain message if even that finds nothing wrong.
    private static List<ValidationIssue> describeUnloadableTargetMetamodel(String targetEcoreFilePath) {
        List<ValidationIssue> issues = new ArrayList<>();
        for (ValidationIssue issue :
                main.java.mddoai.validation.ecore.EcoreValidator.validateReflectively(targetEcoreFilePath).issues()) {
            issues.add(new ValidationIssue(issue.severity(),
                    "Target metamodel " + targetEcoreFilePath + ": " + issue.message(), targetEcoreFilePath));
        }
        if (issues.isEmpty()) {
            issues.add(new ValidationIssue(ValidationIssue.Severity.ERROR,
                    "Could not load target metamodel: " + targetEcoreFilePath, targetEcoreFilePath));
        }
        return issues;
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
