package test.java.unit.java.mddoai.execution.atl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import main.java.mddoai.execution.atl.AtlExecutorCli;

public class AtlExecutorCliTest {

    private static final String ATL_SOURCE_PATH = "./src/main/resources/transformations/pim2psm/pim2gitlabmodel.atl";
    private static final String GITLAB_ECORE_PATH = "../meta_models/com.mddoai.metamodel.gitlab/model/gitlabMM.ecore";
    private static final String PIM_INSTANCE_PATH = "./src/test/resources/testCases/execution/gitlab/input.pimmm";

    @Test
    public void wrongArgCountReturnsUsageError() {
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        int exitCode = AtlExecutorCli.run(new String[]{}, nullOut(), new PrintStream(err));

        assertEquals(2, exitCode);
        assertTrue(err.toString(StandardCharsets.UTF_8).contains("usage:"));
    }

    @Test
    public void realAtlAndPimInstanceProduceARealOutputFile(@TempDir Path tmp) throws Exception {
        Path outputFile = tmp.resolve("output.xmi");
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int exitCode = AtlExecutorCli.run(
                new String[]{ATL_SOURCE_PATH, PIM_INSTANCE_PATH, GITLAB_ECORE_PATH, "GitLabMM", outputFile.toString()},
                new PrintStream(out), nullOut());

        assertEquals(0, exitCode);
        String json = out.toString(StandardCharsets.UTF_8).trim();
        assertTrue(json.contains("\"success\":true"), "expected success:true, got: " + json);
        String outputXmi = Files.readString(outputFile, StandardCharsets.UTF_8);
        assertTrue(outputXmi.contains("gitlabMM:Pipeline"), "expected a real gitlabMM:Pipeline root, got:\n" + outputXmi);
        assertTrue(outputXmi.contains("install-deps"), "expected a real job to survive, got:\n" + outputXmi);
    }

    @Test
    public void aModelThatIsNotARealPimPipelineReturnsZeroWithFailureJson(@TempDir Path tmp) throws Exception {
        Path notAPimModel = tmp.resolve("not-pim.xmi");
        Files.writeString(notAPimModel, "<?xml version=\"1.0\"?><root/>", StandardCharsets.UTF_8);
        Path outputFile = tmp.resolve("output.xmi");
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int exitCode = AtlExecutorCli.run(
                new String[]{ATL_SOURCE_PATH, notAPimModel.toString(), GITLAB_ECORE_PATH, "GitLabMM", outputFile.toString()},
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
