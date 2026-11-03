package main.java.mddoai.execution.swarch;

import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import com.mddoai.metamodel.swarch.SoftwareArchitecture;
import com.mddoai.metamodel.swarch.SwarchPackage;
import main.java.mddoai.execution.atl.AtlExecutor;
import main.java.mddoai.loaders.ModelLoader;
import main.java.mddoai.transformers.FromSWArchAbstractTransformer;
import main.java.mddoai.transformers.Transformer;
import main.java.mddoai.utils.EMFUtils;
import org.eclipse.emf.ecore.EObject;
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
 * Runs a SWArch-to-PIM ATL transformation from its source text: a Software
 * Architecture model (a .swarch file's XMI text) in, a PIM model instance out,
 * returned as XMI text. The step every platform's pipeline starts with.
 *
 * <p>Both metamodels are MDDOAI's own fixed, compiled ones
 * ({@link SwarchPackage} in, {@link PimMMPackage} out), the same pair the
 * engine's own swarch2pim step uses, and the work is done by the engine's own
 * {@link FromSWArchAbstractTransformer}. Taking the transformation as source
 * text, not a precompiled .asm, lets a caller run the current transformation
 * or an improved one without a Java change.
 *
 * <p>The other half of the chain, a PIM in and a platform's own model out, is
 * {@link AtlExecutor}. This class is separate because its input is a
 * different kind of model, and it shares nothing with that one beyond reading
 * the output model's name from the ATL source.
 */
public final class SwarchExecutor {

    // The name FromSWArchAbstractTransformer binds the input model to, so the
    // name a SWArch-to-PIM transformation must read its input as.
    static final String SWARCH_INPUT_MODEL_NAME = "SWArchMM";

    // A transformation declares its models in one line, "create OUT :
    // <OutputModel> from IN : <InputModel>;". The input side is checked here
    // because the transformer fixes it; the output side is read by
    // AtlExecutor.parseOutputModelName. Comments are stripped first so a line
    // that merely mentions the convention is never mistaken for the real one.
    private static final Pattern INPUT_MODEL_NAME_PATTERN =
            Pattern.compile("create\\s+OUT\\s*:\\s*\\w+\\s+from\\s+IN\\s*:\\s*(\\w+)");
    private static final Pattern ATL_LINE_COMMENT_PATTERN = Pattern.compile("--.*$", Pattern.MULTILINE);

    private SwarchExecutor() {
    }

    /**
     * Compiles {@code atlSource} and runs it against {@code swarchModelXmi}.
     * Returns the PIM it produced, as XMI text. Throws
     * IllegalArgumentException for a bad input (not a Software Architecture
     * model, an ATL source that does not declare its models as required) and
     * IOException for a real compile or transform failure, so a caller can
     * tell "your input was wrong" from "the transformation itself failed".
     */
    public static String execute(String atlSource, String swarchModelXmi) throws IOException {
        EMFUtils.init();
        String outputModelName = AtlExecutor.parseOutputModelName(atlSource);
        requireSwarchInputName(atlSource);

        File workDir = Files.createTempDirectory("swarch-execute-").toFile();
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

    // A transformation must read its input under the name the transformer
    // binds it to; any other name would fail deep inside ATL with a message
    // that does not say why.
    private static void requireSwarchInputName(String atlSource) {
        String code = ATL_LINE_COMMENT_PATTERN.matcher(atlSource).replaceAll("");
        Matcher matcher = INPUT_MODEL_NAME_PATTERN.matcher(code);
        String found = matcher.find() ? matcher.group(1) : null;
        if (!SWARCH_INPUT_MODEL_NAME.equals(found)) {
            throw new IllegalArgumentException("A SWArch-to-PIM transformation must read its input as "
                    + SWARCH_INPUT_MODEL_NAME + " (\"create OUT : <Name> from IN : " + SWARCH_INPUT_MODEL_NAME
                    + ";\"), found: " + found);
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
            // A real ATL runtime failure: genuinely different from a compile
            // error, but just as real and describable. getMessage() is safe
            // here even though VMException's own printStackTrace() is not (its
            // pretty-print path can NPE on an invalid classifier).
            throw new IOException("ATL runtime failure: " + e.getMessage(), e);
        }
        if (outputModel == null) {
            throw new IOException("ATL transformation produced no output model");
        }

        File outputFile = new File(workDir, "output.xmi");
        EMFUtils.serializeModel(outputModel, outputFile.getAbsolutePath(), resourceSet);
        return Files.readString(outputFile.toPath(), StandardCharsets.UTF_8);
    }

    // ATL's own launcher runs compiled bytecode (.asm), never raw .atl source,
    // so the source is compiled first.
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
