package test.java.unit.java.mddoai.execution.acceleo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import main.java.mddoai.execution.acceleo.AcceleoExecutorCli;
import main.java.mddoai.execution.atl.AtlExecutor;

public class AcceleoExecutorCliTest {

    private static final String ATL_SOURCE_PATH = "./src/main/resources/transformations/pim2psm/pim2gitlabmodel.atl";
    private static final String MTL_SOURCE_PATH =
            "../code_generation/com.mddoai.codegeneration.gitlab.acceleo/src/com/mddoai/codegeneration/gitlab/acceleo/main/generate.mtl";
    private static final String GITLAB_ECORE_PATH = "../meta_models/com.mddoai.metamodel.gitlab/model/gitlabMM.ecore";
    private static final String PIM_INSTANCE_PATH = "./src/test/resources/testCases/execution/gitlab/input.pimmm";

    @Test
    public void wrongArgCountReturnsUsageError() {
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        int exitCode = AcceleoExecutorCli.run(new String[]{}, nullOut(), new PrintStream(err));

        assertEquals(2, exitCode);
        assertTrue(err.toString(StandardCharsets.UTF_8).contains("usage:"));
    }

    @Test
    public void realMtlAndPsmInstanceProduceARealGeneratedFile(@TempDir Path tmp) throws Exception {
        // A real GitLab PSM instance, produced by AtlExecutor itself (see
        // AtlExecutorTest/AcceleoExecutorTest for why this is preferred over
        // the ported ai-research fixture's own stale output.gitlabmm).
        String atlSource = Files.readString(Path.of(ATL_SOURCE_PATH), StandardCharsets.UTF_8);
        String pimInstance = Files.readString(Path.of(PIM_INSTANCE_PATH), StandardCharsets.UTF_8);
        String gitlabEcore = Files.readString(Path.of(GITLAB_ECORE_PATH), StandardCharsets.UTF_8);
        String psmInstance = AtlExecutor.execute(atlSource, pimInstance, gitlabEcore, "GitLabMM");
        Path psmModelFile = tmp.resolve("psm.xmi");
        Files.writeString(psmModelFile, psmInstance, StandardCharsets.UTF_8);

        Path outputDir = tmp.resolve("generated");
        Files.createDirectories(outputDir);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int exitCode = AcceleoExecutorCli.run(
                new String[]{MTL_SOURCE_PATH, psmModelFile.toString(), GITLAB_ECORE_PATH, outputDir.toString()},
                new PrintStream(out), nullOut());

        assertEquals(0, exitCode);
        String json = out.toString(StandardCharsets.UTF_8).trim();
        assertTrue(json.contains("\"success\":true"), "expected success:true, got: " + json);
        assertTrue(json.contains("\"files\":["), "expected a real files list, got: " + json);

        Path generatedFile = outputDir.resolve(".gitlab-ci.yml");
        assertTrue(Files.isRegularFile(generatedFile), "expected a real generated file on disk at " + generatedFile);
        String generatedYaml = Files.readString(generatedFile, StandardCharsets.UTF_8);
        assertTrue(generatedYaml.contains("install-deps"), "expected a real job to survive, got:\n" + generatedYaml);
    }

    @Test
    public void aModelThatIsNotARealInstanceOfTheTargetMetamodelReturnsZeroWithFailureJson(@TempDir Path tmp) throws Exception {
        Path notAGitlabModel = tmp.resolve("not-gitlab.xmi");
        Files.writeString(notAGitlabModel, "<?xml version=\"1.0\"?><root/>", StandardCharsets.UTF_8);
        Path outputDir = tmp.resolve("generated");
        Files.createDirectories(outputDir);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int exitCode = AcceleoExecutorCli.run(
                new String[]{MTL_SOURCE_PATH, notAGitlabModel.toString(), GITLAB_ECORE_PATH, outputDir.toString()},
                new PrintStream(out), nullOut());

        assertEquals(0, exitCode);
        String json = out.toString(StandardCharsets.UTF_8).trim();
        assertTrue(json.contains("\"success\":false"), "expected success:false, got: " + json);
        assertTrue(json.contains("\"error\":"), "expected a real error message, got: " + json);
    }

    private static PrintStream nullOut() {
        return new PrintStream(java.io.OutputStream.nullOutputStream());
    }
}
