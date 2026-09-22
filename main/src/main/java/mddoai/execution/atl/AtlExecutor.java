package main.java.mddoai.execution.atl;

import com.mddoai.metamodel.pim.pimMM.Pipeline;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import main.java.mddoai.loaders.ModelLoader;
import main.java.mddoai.transformers.FromPIMAbstractTransformer;
import main.java.mddoai.utils.EMFUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.m2m.atl.engine.compiler.AtlCompiler;
import org.eclipse.m2m.atl.engine.compiler.AtlStandaloneCompiler;
import org.eclipse.m2m.atl.engine.compiler.CompileTimeError;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Actually runs an ATL transformation from a real PIM model instance to a
 * target platform's PSM, instead of only compiling it to check for errors
 * (that's {@link main.java.mddoai.validation.atl.AtlValidator}'s own,
 * separate concern - never throws for bad input, this class does).
 *
 * <p>The PIM side is always MDDOAI's own real, fixed, compiled
 * {@link PimMMPackage} - the same one every real PIM instance and every
 * generated ATL transformation already targets. The PSM side is whatever
 * target platform metamodel this call is given, loaded dynamically (see
 * {@link EMFUtils#loadEPackage(String)}) since a freshly LLM-generated
 * platform has no compiled Java package or genmodel of its own. Once
 * loaded, both sides run through {@link FromPIMAbstractTransformer}
 * unchanged - that class is already generic over its own output package,
 * so a dynamically loaded one works exactly like the compiled ones every
 * existing hand-authored transformation (e.g. pim2gitlabmodel.atl) already
 * uses it with.
 */
public final class AtlExecutor {

    // atl_agent's own generation prompt forces every generated ATL's output
    // model name into this exact shape: "create OUT : <ModelName> from IN :
    // PIM;", with a matching "-- @nsURI <ModelName>=<platform>MM=..." header
    // line just above it (mirrored independently in ai/integration_runner's
    // own Python stage/generation/agent.py, which needs the same name for
    // its own, separate call into execution_agent - that duplication is
    // across a language boundary, not something either side can import from
    // the other). Comments are stripped first so a header line merely
    // mentioning this convention is never mistaken for the real declaration.
    private static final Pattern OUTPUT_MODEL_NAME_PATTERN =
            Pattern.compile("create\\s+OUT\\s*:\\s*(\\w+)\\s+from");
    private static final Pattern ATL_LINE_COMMENT_PATTERN = Pattern.compile("--.*$", Pattern.MULTILINE);

    private AtlExecutor() {
    }

    /**
     * Parses the real output model name out of {@code atlSource} itself
     * (see {@link #OUTPUT_MODEL_NAME_PATTERN}'s own comment for the exact
     * convention), so a caller that only has the ATL source, not a
     * separately-computed name, can still call {@link #execute} - used by
     * {@link main.java.mddoai.validation.atl.AtlValidator}'s own real
     * execution smoke test. Throws IllegalArgumentException, matching this
     * class's own "bad input" contract, when the source doesn't declare one.
     */
    public static String parseOutputModelName(String atlSource) {
        String codeOnly = ATL_LINE_COMMENT_PATTERN.matcher(atlSource).replaceAll("");
        Matcher matcher = OUTPUT_MODEL_NAME_PATTERN.matcher(codeOnly);
        if (!matcher.find()) {
            throw new IllegalArgumentException(
                    "Could not find a real output model name in the generated ATL source "
                            + "(expected \"create OUT : <Name> from IN : PIM;\")");
        }
        return matcher.group(1);
    }

    /**
     * Compiles {@code atlSource} (real .atl text, not a file path - this is
     * always freshly generated content from an HTTP request body, never
     * something already sitting on disk under a name worth keeping) and
     * runs it against {@code pimModelXmi} (a real PIM model instance, XMI
     * text) to produce a real instance of {@code targetEcore} (the target
     * platform's own PSM metamodel, Ecore text). Returns the output model,
     * serialized as XMI text. Throws IllegalArgumentException for a bad
     * input (not a real Pipeline, not a loadable metamodel) and IOException
     * for a real compile/transform failure - a caller distinguishes "your
     * input was wrong" from "the transformation itself failed" the same way
     * every other real validator/executor in this project already does.
     */
    public static String execute(String atlSource, String pimModelXmi, String targetEcore, String outputModelName)
            throws IOException {
        EMFUtils.init();

        File workDir = Files.createTempDirectory("atl-execute-").toFile();
        try {
            File asmFile = compileToAsm(atlSource, workDir);
            EPackage targetPackage = loadTargetPackage(targetEcore, workDir);
            Pipeline pimModel = loadPimModel(pimModelXmi, workDir);

            ResourceSet resourceSet = new ResourceSetImpl();
            FromPIMAbstractTransformer<EObject, EPackage> transformer = new FromPIMAbstractTransformer<>(
                    resourceSet, targetPackage, asmFile.getAbsolutePath(), outputModelName);
            EObject outputModel;
            try {
                outputModel = transformer.transform(pimModel);
            } catch (org.eclipse.m2m.atl.common.ATLExecutionException e) {
                // A real ATL runtime failure (e.g. "The class 'X' is not a
                // valid classifier") - genuinely different from a compile
                // error above, but just as real and describable, so it gets
                // the same IOException treatment this method already
                // documents. getMessage() is safe here even though
                // VMException's own printStackTrace() isn't (confirmed via
                // javap against the real vendored jar: it overrides only
                // the two printStackTrace overloads, whose own pretty-print
                // path can NPE on an invalid classifier - see
                // AtlExecutorCli's own catch-all for why that matters too).
                throw new IOException("ATL runtime failure: " + e.getMessage(), e);
            }
            if (outputModel == null) {
                throw new IOException("ATL transformation produced no output model");
            }

            File outputFile = new File(workDir, "output.xmi");
            EMFUtils.serializeModel(outputModel, outputFile.getAbsolutePath(), resourceSet);
            return Files.readString(outputFile.toPath(), StandardCharsets.UTF_8);
        } finally {
            deleteRecursively(workDir);
        }
    }

    // ATL's own EMFVMLauncher (AbstractTransformer.runATLTransformation)
    // runs compiled bytecode (.asm), never raw .atl source directly -
    // confirmed against this project's own real, working
    // TransformerFactory, whose every real transformer (e.g.
    // FromPIMAbstractTransformer for pim2gitlabmodel) is already pointed at
    // a pre-compiled .asm file, never a .atl one. Compiling here first is
    // what makes a freshly LLM-generated .atl runnable the same way.
    private static File compileToAsm(String atlSource, File workDir) throws IOException {
        File sourceFile = new File(workDir, "transformation.atl");
        Files.writeString(sourceFile.toPath(), atlSource, StandardCharsets.UTF_8);
        File asmFile = new File(workDir, "transformation.asm");

        AtlStandaloneCompiler compiler = AtlCompiler.getCompiler(AtlCompiler.DEFAULT_COMPILER_NAME);
        try (Reader reader = new InputStreamReader(new FileInputStream(sourceFile), StandardCharsets.UTF_8)) {
            CompileTimeError[] errors = compiler.compile(reader, asmFile.getAbsolutePath());
            if (!asmFile.isFile()) {
                throw new IOException("ATL source failed to compile: " + describe(errors));
            }
            return asmFile;
        }
    }

    private static String describe(CompileTimeError[] errors) {
        if (errors == null || errors.length == 0) {
            return "no diagnostic detail was reported";
        }
        return Arrays.stream(errors)
                .map(e -> e.getLocation() + ": " + e.getDescription())
                .collect(Collectors.joining("; "));
    }

    private static EPackage loadTargetPackage(String targetEcore, File workDir) throws IOException {
        File ecoreFile = new File(workDir, "target.ecore");
        Files.writeString(ecoreFile.toPath(), targetEcore, StandardCharsets.UTF_8);
        EPackage targetPackage = EMFUtils.loadEPackage(ecoreFile.getAbsolutePath());
        if (targetPackage == null) {
            throw new IllegalArgumentException("Target metamodel could not be loaded as a single EPackage");
        }
        return targetPackage;
    }

    private static Pipeline loadPimModel(String pimModelXmi, File workDir) throws IOException {
        File modelFile = new File(workDir, "input.xmi");
        Files.writeString(modelFile.toPath(), pimModelXmi, StandardCharsets.UTF_8);

        ResourceSet resourceSet = new ResourceSetImpl();
        EObject model;
        try {
            model = ModelLoader.loadModel(modelFile.getAbsolutePath(), resourceSet, EObject.class);
        } catch (Exception e) {
            throw new IOException("Failed to load PIM model instance: " + e.getMessage(), e);
        }
        if (model.eClass().getEPackage() != PimMMPackage.eINSTANCE || !(model instanceof Pipeline)) {
            throw new IllegalArgumentException("Input model is not a real PIM Pipeline instance");
        }
        return (Pipeline) model;
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
