package test.java.integration.java.mddoai;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import main.java.Main;
import main.java.mddoai.utils.EMFUtils;

public class MainIntegrationTest {

    private static final String OUTPUT_FOLDER = "./test/mainIntegrationOutput";
    private static final String INTERMEDIATE_DIR = "./test/generatedModels";

    @AfterEach
    void cleanUp() {
        new File(INTERMEDIATE_DIR + "/PipelinePIM.pimmm").delete();
        new File(INTERMEDIATE_DIR + "/PipelineGit.gitlabmm").delete();
        new File(OUTPUT_FOLDER + "/.gitlab-ci.yml").delete();
        new File(OUTPUT_FOLDER).delete();
    }

    @Test
    void testMain_swarch2gitlab_happyPath() {
        EMFUtils.init();
        String[] args = {
            "swarch2gitlab",
            "./src/test/resources/testCases/swarch2pim/input1.swarch",
            OUTPUT_FOLDER
        };
        assertDoesNotThrow(() -> Main.main(args));
    }

    @Test
    void testMain_pim2gitlab_happyPath() {
        EMFUtils.init();
        String[] args = {
            "pim2gitlab",
            "./src/test/resources/testCases/pim2psm/input1.pimmm",
            OUTPUT_FOLDER
        };
        assertDoesNotThrow(() -> Main.main(args));
    }

    @Test
    void testMain_psm2gitlab_happyPath() {
        EMFUtils.init();
        String[] args = {
            "psm2gitlab",
            "./src/test/resources/testCases/psm2gitlab/input1.gitlabmm",
            OUTPUT_FOLDER
        };
        assertDoesNotThrow(() -> Main.main(args));
    }
}
