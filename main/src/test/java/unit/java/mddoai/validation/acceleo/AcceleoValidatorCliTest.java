package test.java.unit.java.mddoai.validation.acceleo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.acceleo.AcceleoValidatorCli;

public class AcceleoValidatorCliTest {

    @Test
    public void wrongArgCountReturnsUsageError() {
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        int exitCode = AcceleoValidatorCli.run(new String[]{}, nullOut(), new PrintStream(err));

        assertEquals(2, exitCode);
        assertTrue(err.toString(StandardCharsets.UTF_8).contains("usage:"));
    }

    @Test
    public void nonexistentFileReturnsZeroWithInvalidJson() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int exitCode = AcceleoValidatorCli.run(
                new String[]{"./src/test/resources/testCases/validation/acceleo/nonexistent.mtl"},
                new PrintStream(out), nullOut());

        assertEquals(0, exitCode);
        String json = out.toString(StandardCharsets.UTF_8).trim();
        assertTrue(json.contains("\"valid\":false"), "expected valid:false, got: " + json);
    }

    @Test
    public void validFileReturnsZeroWithValidJson() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int exitCode = AcceleoValidatorCli.run(
                new String[]{"../code_generation/com.mddoai.codegeneration.gitlab.acceleo/src/"
                        + "com/mddoai/codegeneration/gitlab/acceleo/main/generate.mtl"},
                new PrintStream(out), nullOut());

        assertEquals(0, exitCode);
        String json = out.toString(StandardCharsets.UTF_8).trim();
        assertTrue(json.contains("\"valid\":true"), "expected valid:true, got: " + json);
        assertTrue(json.contains("\"issues\":[]"), "expected empty issues, got: " + json);
    }

    private static PrintStream nullOut() {
        return new PrintStream(java.io.OutputStream.nullOutputStream());
    }
}
