package test.java.unit.java.mddoai;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import main.java.InputValidator;

public class InputValidatorHappyPathTest {

    @TempDir
    Path tempDir;

    @Test
    void testValidate_whenOutputFolderExists_doesNotThrow() throws IOException {
        File inputFile = tempDir.resolve("model.xmi").toFile();
        inputFile.createNewFile();

        File outputDir = tempDir.resolve("output").toFile();
        outputDir.mkdir();

        String[] args = { "swarch2gitlab", inputFile.getAbsolutePath(), outputDir.getAbsolutePath() };

        assertDoesNotThrow(() -> InputValidator.validate(args));
    }

    @Test
    void testValidate_whenOutputFolderDoesNotExist_createsItAndDoesNotThrow() throws IOException {
        File inputFile = tempDir.resolve("model.xmi").toFile();
        inputFile.createNewFile();

        String outputPath = tempDir.resolve("newOutputDir").toAbsolutePath().toString();

        String[] args = { "pim2gitlab", inputFile.getAbsolutePath(), outputPath };

        assertDoesNotThrow(() -> InputValidator.validate(args));
        assertTrue(new File(outputPath).isDirectory(), "Output directory should have been created");
    }
}
