package main.java.mddoai.execution.acceleo;

import main.java.mddoai.utils.EMFUtils;
import org.eclipse.acceleo.engine.service.AcceleoService;
import org.eclipse.acceleo.model.mtl.Module;
import org.eclipse.acceleo.model.mtl.resource.AcceleoResourceSetImpl;
import org.eclipse.acceleo.parser.compiler.AcceleoCompilerHelper;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

/**
 * Actually runs Acceleo code generation from a real PSM model instance,
 * instead of only compiling the .mtl template to check for errors (that's
 * {@link main.java.mddoai.validation.acceleo.AcceleoValidator}'s own,
 * separate concern - never throws for bad input, this class does).
 *
 * <p>Every module this runs is one acceleo_agent's own generation prompt
 * already forces into a fixed, known shape: named after the target
 * platform, its one real template named {@link #TEMPLATE_NAME}, taking the
 * platform's own root PSM element as its single parameter. That fixed
 * contract is what lets this class call the compiled module directly via
 * {@link AcceleoService#doGenerate}, without the Eclipse-tooling-generated
 * {@code Generate.java} wrapper class every other real generator in this
 * project needs (see {@code main.java.mddoai.generators.wrappers}) - a
 * freshly LLM-generated module, compiled here at runtime, never has one of
 * those, since no Eclipse project or genmodel was ever created for it.
 *
 * <p>Precondition, not currently enforced by this class itself: only one
 * {@link #execute} call per target metamodel nsURI may run per JVM process
 * lifetime. {@link #loadTargetPackage} registers the loaded metamodel into
 * the static, process-wide {@link EPackage.Registry#INSTANCE}, and
 * {@link #loadModule} reparents that same metamodel's own EMF
 * {@link Resource} into a fresh resource set to keep EClass identity
 * consistent between compile time and generation time (see that method's
 * own comment) - reparenting is a real EMF mutation that detaches a
 * Resource from whatever resource set currently holds it, so a second,
 * concurrent or overlapping {@link #execute} call for the same nsURI in the
 * same process would silently corrupt the first call's own in-flight state.
 * Every real caller today invokes this from a fresh, single-purpose OS
 * process per call, so the static registry always starts empty and this
 * never happens in practice - re-verify this holds before ever moving to a
 * pooled or long-lived JVM for performance.
 */
public final class AcceleoExecutor {

    private static final String TEMPLATE_NAME = "generateElement";

    private AcceleoExecutor() {
    }

    /**
     * Compiles {@code mtlSource} (real .mtl text, freshly generated, not a
     * file already on disk) against {@code targetEcore} (the target
     * platform's own PSM metamodel, Ecore text, loaded dynamically the same
     * way {@link main.java.mddoai.execution.atl.AtlExecutor} does), then
     * runs the compiled module's fixed {@link #TEMPLATE_NAME} template
     * against {@code psmModelXmi} (a real PSM model instance conforming to
     * that same metamodel, XMI text). Returns the real generated file(s),
     * keyed by path relative to the internal, throwaway target folder they
     * were written under (that folder is deleted before this method
     * returns, so only the returned content survives) - read directly off
     * disk after generation, not from doGenerate's own return value: that
     * map isn't reliably populated under the default generation strategy
     * (confirmed empirically - a real .gitlab-ci.yml lands on disk while
     * doGenerate's own returned map is still empty), only the real files it
     * actually wrote are trustworthy. Throws IllegalArgumentException for a
     * bad input (unloadable metamodel, a model that isn't an instance of
     * it) and IOException for a real compile/generation failure.
     */
    public static Map<String, String> execute(String mtlSource, String psmModelXmi, String targetEcore)
            throws IOException {
        EMFUtils.init();

        File workDir = Files.createTempDirectory("acceleo-execute-").toFile();
        try {
            // Real bug this once was: compiling before the target metamodel
            // was registered meant AcceleoCompilerHelper could never resolve
            // any type the template actually declares (every real class the
            // target platform's own metamodel defines - "The metamodel
            // couldn't be resolved", cascading into "Unrecognized variable"/
            // "Invalid Type" for every single reference to it) - confirmed
            // for real: a genuinely valid, already-validator-approved
            // template failed here even though AcceleoValidator's own
            // compile check on the exact same content passed clean, because
            // that check happens to register the target metamodel as a side
            // effect of its own two-arg overload before this method ever
            // runs. loadTargetPackage() (which also calls EMFUtils.init())
            // must run before compileToEmtl(), not after, so a real,
            // standalone call to this method (the real generation stage's
            // own real call path, with no prior validate() call to
            // accidentally register it first) resolves the target
            // metamodel too.
            EPackage psmPackage = loadTargetPackage(targetEcore, workDir);
            File emtlDir = compileToEmtl(mtlSource, workDir);
            EObject psmModel = loadPsmModel(psmModelXmi, psmPackage, workDir);
            Module module = loadModule(emtlDir, psmPackage);

            File targetFolder = new File(workDir, "generated");
            if (!targetFolder.mkdirs()) {
                throw new IOException("Failed to create generation output directory: " + targetFolder);
            }

            AcceleoService service = new AcceleoService();
            Monitor monitor = BasicMonitor.toMonitor(new NullProgressMonitor());
            service.doGenerate(module, TEMPLATE_NAME, psmModel, targetFolder, monitor);

            Map<String, String> generated = readGeneratedFiles(targetFolder);
            if (generated.isEmpty()) {
                throw new IOException("Acceleo generation produced no real output files under " + targetFolder);
            }
            return generated;
        } finally {
            deleteRecursively(workDir);
        }
    }

    private static Map<String, String> readGeneratedFiles(File targetFolder) throws IOException {
        Map<String, String> files = new java.util.LinkedHashMap<>();
        collectGeneratedFiles(targetFolder, targetFolder, files);
        return files;
    }

    private static void collectGeneratedFiles(File root, File dir, Map<String, String> files) throws IOException {
        File[] children = dir.listFiles();
        if (children == null) {
            return;
        }
        for (File child : children) {
            if (child.isDirectory()) {
                collectGeneratedFiles(root, child, files);
            } else {
                String relativePath = root.toPath().relativize(child.toPath()).toString();
                files.put(relativePath, Files.readString(child.toPath(), StandardCharsets.UTF_8));
            }
        }
    }

    // AcceleoCompilerHelper scans a whole source folder, not a single file
    // (see AcceleoValidator's own comment on the same quirk) - isolating
    // the one real source file into its own folder first is duplicated
    // here rather than reused from AcceleoValidator's own private helper,
    // since that one is tightly coupled to reporting a ValidationResult
    // (turning a bare RuntimeException into per-line ValidationIssues) that
    // this execution-focused class has no use for; both call the same
    // small, stable third-party API (AcceleoCompilerHelper) the same way.
    private static File compileToEmtl(String mtlSource, File workDir) throws IOException {
        File sourceDir = new File(workDir, "src");
        File outputDir = new File(workDir, "out");
        if (!sourceDir.mkdirs() || !outputDir.mkdirs()) {
            throw new IOException("Failed to create Acceleo compile work directories under " + workDir);
        }
        File sourceFile = new File(sourceDir, "generate.mtl");
        Files.writeString(sourceFile.toPath(), mtlSource, StandardCharsets.UTF_8);

        AcceleoCompilerHelper compiler = new AcceleoCompilerHelper();
        compiler.setSourceFolder(sourceDir.getAbsolutePath());
        compiler.setOutputFolder(outputDir.getAbsolutePath());
        try {
            compiler.execute();
        } catch (RuntimeException e) {
            throw new IOException("Acceleo source failed to compile: " + e.getMessage(), e);
        }
        if (!hasAnyFile(outputDir)) {
            throw new IOException("Acceleo compiler produced no .emtl output");
        }
        return outputDir;
    }

    // Real bug this once was, confirmed by direct reproduction: a compiled
    // .emtl's own cross-reference to its target metamodel resolves through
    // the FILE it was compiled against (workDir/target.ecore), not through
    // EPackage.Registry.INSTANCE - so loading the .emtl into a bare, empty
    // AcceleoResourceSetImpl silently reloads that same .ecore file a SECOND
    // time from disk, producing a structurally-identical but
    // object-different EClass for the template's own parameter type.
    // AcceleoService.doGenerate's own template dispatch (confirmed by
    // decompiling the real vendored engine jar) matches the model argument
    // against that parameter type via EClassifier.isInstance(), which walks
    // precomputed supertype identity, not structural shape - so a
    // real, valid model whose EClass comes from psmPackage (the ONE
    // dynamic EPackage this whole execute() call already loaded and
    // registered) silently fails that check against the .emtl's own
    // independently-reloaded EClass, and doGenerate finishes normally with
    // zero real output and no exception. Pre-registering psmPackage's own
    // Resource (the exact file it was loaded from) into this resource set
    // before loading the .emtl makes the .emtl's cross-reference resolve to
    // that SAME already-loaded Resource's contents instead of triggering a
    // second load, which keeps the EClass identity consistent end to end -
    // confirmed fixed by a direct before/after reproduction against a real,
    // non-genmodel target metamodel. A genmodel-based platform (e.g. this
    // project's own hand-authored GitLab reference) never hit this: its
    // EPackage is a static Java singleton with no eResource() to begin with
    // (eResource() is null below, so nothing changes for it).
    private static Module loadModule(File emtlDir, EPackage psmPackage) throws IOException {
        File emtlFile = findEmtlFile(emtlDir);
        AcceleoResourceSetImpl resourceSet = new AcceleoResourceSetImpl();
        Resource metamodelResource = psmPackage.eResource();
        if (metamodelResource != null) {
            resourceSet.getResources().add(metamodelResource);
        }
        Resource resource = resourceSet.getResource(URI.createFileURI(emtlFile.getAbsolutePath()), true);
        if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof Module)) {
            throw new IOException("Compiled Acceleo output is not a real Module: " + emtlFile);
        }
        return (Module) resource.getContents().get(0);
    }

    private static File findEmtlFile(File dir) throws IOException {
        File[] children = dir.listFiles();
        if (children != null) {
            for (File child : children) {
                if (child.isDirectory()) {
                    try {
                        return findEmtlFile(child);
                    } catch (IOException ignored) {
                        // keep looking in sibling directories
                    }
                } else if (child.getName().toLowerCase(java.util.Locale.ROOT).endsWith(".emtl")) {
                    return child;
                }
            }
        }
        throw new IOException("No compiled .emtl file found under " + dir);
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

    private static EPackage loadTargetPackage(String targetEcore, File workDir) throws IOException {
        File ecoreFile = new File(workDir, "target.ecore");
        Files.writeString(ecoreFile.toPath(), targetEcore, StandardCharsets.UTF_8);
        EPackage dynamicPackage = EMFUtils.loadEPackage(ecoreFile.getAbsolutePath());
        if (dynamicPackage == null) {
            throw new IllegalArgumentException("Target metamodel could not be loaded as a single EPackage");
        }

        // A compiled package already resolving for this nsURI (a hand-authored
        // reference platform like GitLab, which - unlike a freshly
        // LLM-generated one - already has genmodel-generated Java classes)
        // must be used instead of the freshly dynamic one just loaded: the
        // compiled module (compiled while only the compiled package was ever
        // registered, via this class's own EMFUtils.init() call above) already
        // resolved its own template parameter type (e.g. Pipeline) against
        // it, and AcceleoService.doGenerate's own template lookup matches by
        // EClass identity, not by structural shape - a same-shaped but
        // differently-instanced dynamic EClass silently matches no template
        // at all, producing no output with no real error. Same reasoning
        // AcceleoValidator.validate(String,String) already documents and
        // applies to module resolution; applied here to model loading instead.
        //
        // Real bug this once was: when nothing was already registered for
        // this nsURI, this method returned dynamicPackage for its own
        // caller's local use without ever registering it globally too - so
        // AcceleoCompilerHelper (called next, in execute() above) could
        // never resolve a single type the template actually declares
        // ("The metamodel couldn't be resolved", cascading into
        // "Unrecognized variable"/"Invalid Type" for every real reference
        // to it), confirmed for real against an already-validator-approved
        // template that only happened to compile earlier because a prior,
        // separate validate() call had registered it first as a side
        // effect. Must register here too, matching
        // AcceleoValidator.validate(String, String)'s own identical
        // registration exactly, not just look for one already done.
        String nsURI = dynamicPackage.getNsURI();
        EPackage alreadyRegistered = nsURI != null ? EPackage.Registry.INSTANCE.getEPackage(nsURI) : null;
        if (alreadyRegistered != null) {
            return alreadyRegistered;
        }
        if (nsURI != null) {
            EPackage.Registry.INSTANCE.put(nsURI, dynamicPackage);
        }
        return dynamicPackage;
    }

    private static EObject loadPsmModel(String psmModelXmi, EPackage psmPackage, File workDir) throws IOException {
        File modelFile = new File(workDir, "input.xmi");
        Files.writeString(modelFile.toPath(), psmModelXmi, StandardCharsets.UTF_8);

        ResourceSet resourceSet = new ResourceSetImpl();
        EMFUtils.registerPackages(resourceSet, psmPackage);
        Object model;
        try {
            model = EMFUtils.deserializeModel(modelFile.getAbsolutePath(), resourceSet);
        } catch (Exception e) {
            throw new IOException("Failed to load PSM model instance: " + e.getMessage(), e);
        }
        if (!(model instanceof EObject) || ((EObject) model).eClass().getEPackage() != psmPackage) {
            throw new IllegalArgumentException("Input model is not a real instance of the given target metamodel");
        }
        return (EObject) model;
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
