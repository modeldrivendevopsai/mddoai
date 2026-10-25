package main.java.mddoai.execution.atl;

import com.mddoai.metamodel.pim.pimMM.Pipeline;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import com.mddoai.metamodel.swarch.SoftwareArchitecture;
import com.mddoai.metamodel.swarch.SwarchPackage;
import main.java.mddoai.loaders.ModelLoader;
import main.java.mddoai.transformers.FromPIMAbstractTransformer;
import main.java.mddoai.transformers.FromSWArchAbstractTransformer;
import main.java.mddoai.transformers.Transformer;
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
 *
 * <p>It also runs the step before that, a Software Architecture model to a
 * PIM ({@link #executeFromSwarch}), where both metamodels are MDDOAI's own
 * compiled ones and only the transformation itself is given as source.
 */
public final class AtlExecutor {

    // Every transformation this class runs declares its models in one line,
    // "create OUT : <OutputModel> from IN : <InputModel>;". atl_agent's own
    // generation prompt forces that shape on every generated PIM-to-PSM
    // transformation, with a matching "-- @nsURI <ModelName>=<platform>MM=..."
    // header line just above it (mirrored independently in
    // ai/integration_runner's own Python stage/generation/agent.py, which
    // needs the same name for its own, separate call into execution_agent -
    // that duplication is across a language boundary, not something either
    // side can import from the other), and the engine's own hand-written
    // transformations use it too. Comments are stripped first so a header
    // line merely mentioning this convention is never mistaken for the real
    // declaration.
    private static final Pattern OUTPUT_MODEL_NAME_PATTERN =
            Pattern.compile("create\\s+OUT\\s*:\\s*(\\w+)\\s+from");
    // The same declaration's input side, checked only where the input name
    // is fixed by the transformer (see SWARCH_INPUT_MODEL_NAME).
    private static final Pattern INPUT_MODEL_NAME_PATTERN =
            Pattern.compile("create\\s+OUT\\s*:\\s*\\w+\\s+from\\s+IN\\s*:\\s*(\\w+)");
    private static final Pattern ATL_LINE_COMMENT_PATTERN = Pattern.compile("--.*$", Pattern.MULTILINE);

    // The name FromSWArchAbstractTransformer binds the input model to, so the
    // name a SWArch-to-PIM transformation must read its input as.
    static final String SWARCH_INPUT_MODEL_NAME = "SWArchMM";

    private AtlExecutor() {
    }

    /**
     * Parses the real output model name out of {@code atlSource} itself
     * (see {@link #OUTPUT_MODEL_NAME_PATTERN}'s own comment for the exact
     * convention), so a caller that only has the ATL source, not a
     * separately-computed name, can still run it - used by
     * {@link main.java.mddoai.validation.atl.AtlValidator}'s own real
     * execution smoke test and by {@link #executeFromSwarch}. Throws
     * IllegalArgumentException, matching this class's own "bad input"
     * contract, when the source doesn't declare one.
     */
    public static String parseOutputModelName(String atlSource) {
        Matcher matcher = OUTPUT_MODEL_NAME_PATTERN.matcher(withoutComments(atlSource));
        if (!matcher.find()) {
            throw new IllegalArgumentException(
                    "Could not find a real output model name in the ATL source "
                            + "(expected \"create OUT : <Name> from IN : <InputModel>;\")");
        }
        return matcher.group(1);
    }

    // A SWArch-to-PIM transformation must read its input under the name the
    // transformer binds it to; any other name would fail deep inside ATL with
    // a message that does not say why.
    private static void requireSwarchInputName(String atlSource) {
        Matcher matcher = INPUT_MODEL_NAME_PATTERN.matcher(withoutComments(atlSource));
        String found = matcher.find() ? matcher.group(1) : null;
        if (!SWARCH_INPUT_MODEL_NAME.equals(found)) {
            throw new IllegalArgumentException("A SWArch-to-PIM transformation must read its input as "
                    + SWARCH_INPUT_MODEL_NAME + " (\"create OUT : <Name> from IN : " + SWARCH_INPUT_MODEL_NAME
                    + ";\"), found: " + found);
        }
    }

    private static String withoutComments(String atlSource) {
        return ATL_LINE_COMMENT_PATTERN.matcher(atlSource).replaceAll("");
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
            return transformToXmi(transformer, pimModel, resourceSet, workDir);
        } finally {
            deleteRecursively(workDir);
        }
    }

    /**
     * Compiles {@code atlSource} and runs it against {@code swarchModelXmi}
     * (a real Software Architecture model, a .swarch file's XMI text) to
     * produce a real PIM model instance, returned as XMI text. The other
     * direction from {@link #execute}: here both sides are MDDOAI's own fixed,
     * compiled metamodels, {@link SwarchPackage} in and {@link PimMMPackage}
     * out, the same pair the engine's own swarch2pim step uses. Taking the
     * transformation as source text, not a precompiled .asm, lets a caller run
     * the current SWArch-to-PIM transformation or an improved one without a
     * Java change. The output model name is read from the source itself (see
     * {@link #parseOutputModelName}); the input must be named
     * {@value #SWARCH_INPUT_MODEL_NAME}, or IllegalArgumentException says so.
     * Same error contract as {@link #execute}.
     */
    public static String executeFromSwarch(String atlSource, String swarchModelXmi) throws IOException {
        EMFUtils.init();
        String outputModelName = parseOutputModelName(atlSource);
        requireSwarchInputName(atlSource);

        File workDir = Files.createTempDirectory("atl-execute-swarch-").toFile();
        try {
            File asmFile = compileToAsm(atlSource, workDir);
            SoftwareArchitecture swarchModel = loadSwarchModel(swarchModelXmi, workDir);

            ResourceSet resourceSet = new ResourceSetImpl();
            FromSWArchAbstractTransformer<EObject, PimMMPackage> transformer = new FromSWArchAbstractTransformer<>(
                    resourceSet, PimMMPackage.eINSTANCE, asmFile.getAbsolutePath(), outputModelName);
            return transformToXmi(transformer, swarchModel, resourceSet, workDir);
        } finally {
            deleteRecursively(workDir);
        }
    }

    // Runs an already-built transformer and returns its output model as XMI
    // text, with every real ATL failure turned into an IOException.
    private static <I> String transformToXmi(Transformer<EObject, I> transformer, I input,
            ResourceSet resourceSet, File workDir) throws IOException {
        EObject outputModel;
        try {
            outputModel = transformer.transform(input);
        } catch (org.eclipse.m2m.atl.common.ATLExecutionException e) {
            // A real ATL runtime failure (e.g. "The class 'X' is not a
            // valid classifier") - genuinely different from a compile
            // error, but just as real and describable, so it gets
            // the same IOException treatment both public methods
            // document. getMessage() is safe here even though
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

    private static SoftwareArchitecture loadSwarchModel(String swarchModelXmi, File workDir) throws IOException {
        File modelFile = new File(workDir, "input.swarch");
        Files.writeString(modelFile.toPath(), swarchModelXmi, StandardCharsets.UTF_8);

        ResourceSet resourceSet = new ResourceSetImpl();
        EObject model;
        try {
            model = ModelLoader.loadModel(modelFile.getAbsolutePath(), resourceSet, EObject.class);
        } catch (Exception e) {
            throw new IOException("Failed to load Software Architecture model: " + e.getMessage(), e);
        }
        if (model == null || model.eClass().getEPackage() != SwarchPackage.eINSTANCE
                || !(model instanceof SoftwareArchitecture)) {
            throw new IllegalArgumentException("Input model is not a real Software Architecture model");
        }
        return (SoftwareArchitecture) model;
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
