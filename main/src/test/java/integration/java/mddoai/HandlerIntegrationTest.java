package test.java.integration.java.mddoai;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import main.java.ExitException;
import main.java.PimToGitlabHandler;
import main.java.PsmToGitlabHandler;
import main.java.SwarchToGitlabHandler;
import main.java.mddoai.utils.EMFUtils;

public class HandlerIntegrationTest {

    private static final String OUTPUT_FOLDER = "./test/handlerIntegrationOutput";
    private static final String INTERMEDIATE_DIR = "./test/generatedModels";
    private static final String GENERATED_YAML = OUTPUT_FOLDER + "/.gitlab-ci.yml";

    @AfterEach
    void cleanUp() {
        new File(INTERMEDIATE_DIR + "/PipelinePIM.pimmm").delete();
        new File(INTERMEDIATE_DIR + "/PipelineGit.gitlabmm").delete();
        new File(GENERATED_YAML).delete();
        new File(OUTPUT_FOLDER).delete();
    }

    // -------------------------------------------------------------------------
    // PsmToGitlabHandler
    // -------------------------------------------------------------------------

    @Test
    void testPsmToGitlabHandler_generatesGitlabCiYaml() throws Exception {
        EMFUtils.init();
        PsmToGitlabHandler.handle("./src/test/resources/testCases/psm2gitlab/input1.gitlabmm", OUTPUT_FOLDER);

        File yaml = new File(GENERATED_YAML);
        assertTrue(yaml.exists(), ".gitlab-ci.yml should be generated");
        assertTrue(yaml.length() > 0, ".gitlab-ci.yml should not be empty");

        String content = Files.readString(Paths.get(GENERATED_YAML));
        assertTrue(content.contains("workflow") || content.contains("stages") || content.contains("job"),
                ".gitlab-ci.yml should contain valid GitLab CI YAML markers");
    }

    @Test
    void testPsmToGitlabHandler_throwsExitException_whenInputModelPathIsInvalid() {
        EMFUtils.init();
        assertThrows(IllegalArgumentException.class, () ->
            PsmToGitlabHandler.handle("./nonexistent/path/model.gitlabmm", OUTPUT_FOLDER));
    }

    // -------------------------------------------------------------------------
    // PimToGitlabHandler
    // -------------------------------------------------------------------------

    @Test
    void testPimToGitlabHandler_generatesGitlabCiYaml() throws Exception {
        EMFUtils.init();
        PimToGitlabHandler.handle("./src/test/resources/testCases/pim2psm/input1.pimmm", OUTPUT_FOLDER);

        File yaml = new File(GENERATED_YAML);
        assertTrue(yaml.exists(), ".gitlab-ci.yml should be generated");
        assertTrue(yaml.length() > 0, ".gitlab-ci.yml should not be empty");

        String content = Files.readString(Paths.get(GENERATED_YAML));
        assertTrue(content.contains("workflow") || content.contains("stages") || content.contains("job"),
                ".gitlab-ci.yml should contain valid GitLab CI YAML markers");
    }

    @Test
    void testPimToGitlabHandler_throwsExitException_whenInputModelPathIsInvalid() {
        EMFUtils.init();
        assertThrows(IllegalArgumentException.class, () ->
            PimToGitlabHandler.handle("./nonexistent/path/model.pimmm", OUTPUT_FOLDER));
    }

    // -------------------------------------------------------------------------
    // SwarchToGitlabHandler
    // -------------------------------------------------------------------------

    @Test
    void testSwarchToGitlabHandler_generatesGitlabCiYaml() throws Exception {
        EMFUtils.init();
        SwarchToGitlabHandler.handle("./src/test/resources/testCases/swarch2pim/input1.swarch", OUTPUT_FOLDER);

        File yaml = new File(GENERATED_YAML);
        assertTrue(yaml.exists(), ".gitlab-ci.yml should be generated");
        assertTrue(yaml.length() > 0, ".gitlab-ci.yml should not be empty");

        String content = Files.readString(Paths.get(GENERATED_YAML));
        assertTrue(content.contains("workflow") || content.contains("stages") || content.contains("job"),
                ".gitlab-ci.yml should contain valid GitLab CI YAML markers");
    }

    @Test
    void testSwarchToGitlabHandler_throwsExitException_whenInputModelPathIsInvalid() {
        EMFUtils.init();
        assertThrows(IllegalArgumentException.class, () ->
            SwarchToGitlabHandler.handle("./nonexistent/path/model.swarch", OUTPUT_FOLDER));
    }
}
