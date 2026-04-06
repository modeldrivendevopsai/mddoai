package test.java.integration.java.mddoai;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import main.java.PimToGitlabHandler;
import main.java.PsmToGitlabHandler;
import main.java.SwarchToGitlabHandler;
import main.java.mddoai.utils.EMFUtils;

public class HandlerIntegrationTest {

    private static final String OUTPUT_FOLDER = "./test/handlerIntegrationOutput";
    private static final String INTERMEDIATE_DIR = "./test/generatedModels";

    @AfterEach
    void cleanUp() {
        new File(INTERMEDIATE_DIR + "/PipelinePIM.pimmm").delete();
        new File(INTERMEDIATE_DIR + "/PipelineGit.gitlabmm").delete();
        new File(OUTPUT_FOLDER + "/.gitlab-ci.yml").delete();
        new File(OUTPUT_FOLDER).delete();
    }

    // -------------------------------------------------------------------------
    // PsmToGitlabHandler — input is a .gitlabmm model, generates YAML directly
    // -------------------------------------------------------------------------

    @Test
    void testPsmToGitlabHandler_happyPath_doesNotThrow() {
        EMFUtils.init();
        String inputModel = "./src/test/resources/testCases/psm2gitlab/input1.gitlabmm";

        assertDoesNotThrow(() -> PsmToGitlabHandler.handle(inputModel, OUTPUT_FOLDER));
    }

    @Test
    void testPsmToGitlabHandler_happyPath_generatesOutputFile() {
        EMFUtils.init();
        String inputModel = "./src/test/resources/testCases/psm2gitlab/input1.gitlabmm";

        assertDoesNotThrow(() -> PsmToGitlabHandler.handle(inputModel, OUTPUT_FOLDER));

        File[] files = new File(OUTPUT_FOLDER).listFiles();
        assertTrue(files != null && files.length > 0, "Output folder should contain generated files");
    }

    // -------------------------------------------------------------------------
    // PimToGitlabHandler — input is a .pimmm model, transforms to gitlab then generates
    // -------------------------------------------------------------------------

    @Test
    void testPimToGitlabHandler_happyPath_doesNotThrow() {
        EMFUtils.init();
        String inputModel = "./src/test/resources/testCases/pim2psm/input1.pimmm";

        assertDoesNotThrow(() -> PimToGitlabHandler.handle(inputModel, OUTPUT_FOLDER));
    }

    @Test
    void testPimToGitlabHandler_happyPath_generatesOutputFile() {
        EMFUtils.init();
        String inputModel = "./src/test/resources/testCases/pim2psm/input1.pimmm";

        assertDoesNotThrow(() -> PimToGitlabHandler.handle(inputModel, OUTPUT_FOLDER));

        File[] files = new File(OUTPUT_FOLDER).listFiles();
        assertTrue(files != null && files.length > 0, "Output folder should contain generated files");
    }

    // -------------------------------------------------------------------------
    // SwarchToGitlabHandler — input is a .swarch model, two-step transform then generates
    // -------------------------------------------------------------------------

    @Test
    void testSwarchToGitlabHandler_happyPath_doesNotThrow() {
        EMFUtils.init();
        String inputModel = "./src/test/resources/testCases/swarch2pim/input1.swarch";

        assertDoesNotThrow(() -> SwarchToGitlabHandler.handle(inputModel, OUTPUT_FOLDER));
    }

    @Test
    void testSwarchToGitlabHandler_happyPath_generatesOutputFile() {
        EMFUtils.init();
        String inputModel = "./src/test/resources/testCases/swarch2pim/input1.swarch";

        assertDoesNotThrow(() -> SwarchToGitlabHandler.handle(inputModel, OUTPUT_FOLDER));

        File[] files = new File(OUTPUT_FOLDER).listFiles();
        assertTrue(files != null && files.length > 0, "Output folder should contain generated files");
    }
}
