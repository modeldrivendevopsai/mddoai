package test.java.unit.java.mddoai.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import main.java.mddoai.validation.EcoreValidatorCli;

public class EcoreValidatorCliTest {

    @Test
    public void wrongArgCountReturnsUsageError() {
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        int exitCode = EcoreValidatorCli.run(new String[]{"reflective"}, nullOut(), new PrintStream(err));

        assertEquals(2, exitCode);
        assertTrue(err.toString(StandardCharsets.UTF_8).contains("usage:"));
    }

    @Test
    public void unknownModeReturnsUsageError() {
        ByteArrayOutputStream err = new ByteArrayOutputStream();
        int exitCode = EcoreValidatorCli.run(
                new String[]{"bogus-mode", "some/path.ecore"}, nullOut(), new PrintStream(err));

        assertEquals(2, exitCode);
    }

    @Test
    public void reflectiveModeOnNonexistentFileReturnsZeroWithInvalidJson() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int exitCode = EcoreValidatorCli.run(
                new String[]{"reflective", "./src/test/resources/testCases/validation/nonexistent.ecore"},
                new PrintStream(out), nullOut());

        assertEquals(0, exitCode);
        String json = out.toString(StandardCharsets.UTF_8).trim();
        assertTrue(json.contains("\"valid\":false"), "expected valid:false, got: " + json);
        assertTrue(json.contains("\"mode\":\"reflective\""), "expected mode:reflective, got: " + json);
    }

    @Test
    public void reflectiveModeOnValidFileReturnsZeroWithValidJson() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int exitCode = EcoreValidatorCli.run(
                new String[]{"reflective", "./src/test/resources/testCases/validation/valid.ecore"},
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
