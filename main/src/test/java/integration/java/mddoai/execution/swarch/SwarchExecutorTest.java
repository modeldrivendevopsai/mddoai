package test.java.integration.java.mddoai.execution.swarch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.java.mddoai.execution.swarch.SwarchExecutor;
import main.java.mddoai.loaders.ModelLoader;
import main.java.mddoai.transformers.TransformerExecutor;
import main.java.mddoai.utils.EMFUtils;

/**
 * Real execution of the project's own SWArch-to-PIM transformation from its
 * source text, against the real SWArch example models. Nothing is mocked: this
 * runs the real ATL compiler and engine.
 */
public class SwarchExecutorTest {

    private static final String SWARCH2PIM_ATL_PATH = "./src/main/resources/transformations/swarch2pim/swarch2pim.atl";
    private static final String PIM_SAMPLE_INSTANCE_PATH = "./src/test/resources/testCases/execution/gitlab/input.pimmm";

    @ParameterizedTest
    @ValueSource(strings = {"input1.swarch", "input2.swarch", "input3.swarch"})
    public void fromSourceProducesExactlyWhatTheEnginesCompiledStepProduces(String model, @TempDir Path tmp)
            throws Exception {
        Path swarchPath = Path.of("./src/test/resources/testCases/e2e", model);
        String atlSource = Files.readString(Path.of(SWARCH2PIM_ATL_PATH), StandardCharsets.UTF_8);
        String swarchXmi = Files.readString(swarchPath, StandardCharsets.UTF_8);

        String fromSource = SwarchExecutor.execute(atlSource, swarchXmi);

        // The engine's own swarch2pim step (the one swarch2gitlab runs), from
        // its precompiled .asm, on the same model.
        EMFUtils.init();
        EObject swarchModel = ModelLoader.loadModel(swarchPath.toAbsolutePath().toString(), new ResourceSetImpl(), EObject.class);
        Path enginePim = tmp.resolve("engine.pimmm");
        assertTrue(TransformerExecutor.execute("swarch2pim", swarchModel, enginePim.toString()) != null,
                "the engine's own swarch2pim step failed on " + model);
        assertEquals(Files.readString(enginePim, StandardCharsets.UTF_8), fromSource);
    }

    @Test
    public void rejectsAModelThatIsNotSwarch() throws IOException {
        String atlSource = Files.readString(Path.of(SWARCH2PIM_ATL_PATH), StandardCharsets.UTF_8);
        String pimInstance = Files.readString(Path.of(PIM_SAMPLE_INSTANCE_PATH), StandardCharsets.UTF_8);

        assertThrows(IllegalArgumentException.class, () -> SwarchExecutor.execute(atlSource, pimInstance));
    }

    @Test
    public void reportsBrokenAtlAsACompileFailure() throws IOException {
        String swarchXmi = Files.readString(Path.of("./src/test/resources/testCases/e2e/input1.swarch"), StandardCharsets.UTF_8);

        IOException e = assertThrows(IOException.class,
                () -> SwarchExecutor.execute("create OUT : PIMMM from IN : SWArchMM; this is not ATL", swarchXmi));
        assertTrue(e.getMessage().contains("failed to compile"), "expected a real compile failure message, got: " + e.getMessage());
    }
}
