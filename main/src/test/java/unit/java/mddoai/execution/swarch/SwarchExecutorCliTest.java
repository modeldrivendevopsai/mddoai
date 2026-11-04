package test.java.unit.java.mddoai.execution.swarch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import main.java.mddoai.execution.swarch.SwarchExecutorCli;

public class SwarchExecutorCliTest {

    private static final String SWARCH2PIM_ATL_PATH = "./src/main/resources/transformations/swarch2pim/swarch2pim.atl";
    private static final String SWARCH_MODEL_PATH = "./src/test/resources/testCases/e2e/input1.swarch";
    private static final String PIM_INSTANCE_PATH = "./src/test/resources/testCases/execution/gitlab/input.pimmm";

    @Test
    public void wrongArgCountReturnsUsageError() {
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        int exitCode = SwarchExecutorCli.run(new String[]{SWARCH2PIM_ATL_PATH}, nullOut(), new PrintStream(err));

        assertEquals(2, exitCode);
        assertTrue(err.toString(StandardCharsets.UTF_8).contains("usage:"));
    }

    @Test
    public void turnsARealSwarchModelIntoARealPimFile(@TempDir Path tmp) throws Exception {
        Path outputFile = tmp.resolve("output.pimmm");
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int exitCode = SwarchExecutorCli.run(
                new String[]{SWARCH2PIM_ATL_PATH, SWARCH_MODEL_PATH, outputFile.toString()},
                new PrintStream(out), nullOut());

        assertEquals(0, exitCode);
        String json = out.toString(StandardCharsets.UTF_8).trim();
        assertTrue(json.contains("\"success\":true"), "expected success:true, got: " + json);
        assertTrue(Files.readString(outputFile, StandardCharsets.UTF_8).contains("Pipeline"),
                "expected a real PIM Pipeline in the output file");
    }

    @Test
    public void aModelThatIsNotSwarchReturnsZeroWithFailureJson(@TempDir Path tmp) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int exitCode = SwarchExecutorCli.run(
                new String[]{SWARCH2PIM_ATL_PATH, PIM_INSTANCE_PATH, tmp.resolve("o.pimmm").toString()},
                new PrintStream(out), nullOut());

        assertEquals(0, exitCode);
        String json = out.toString(StandardCharsets.UTF_8).trim();
        assertTrue(json.contains("\"success\":false"), "expected success:false, got: " + json);
    }

    @Test
    public void aMissingInputFileIsAnInfrastructureFailureNotAReportedOne(@TempDir Path tmp) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int exitCode = SwarchExecutorCli.run(
                new String[]{SWARCH2PIM_ATL_PATH, tmp.resolve("missing.swarch").toString(), tmp.resolve("o.pimmm").toString()},
                new PrintStream(out), nullOut());

        assertEquals(1, exitCode);
        assertEquals("", out.toString(StandardCharsets.UTF_8).trim());
    }

    private static PrintStream nullOut() {
        return new PrintStream(java.io.OutputStream.nullOutputStream());
    }
}
