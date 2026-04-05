package test.java.unit.java;

import main.java.ExitException;
import main.java.InputValidator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InputValidator.
 */
class InputValidatorTest {

    @TempDir
    Path tempDir;

    private File validInputFile;
    private String validOutputFolder;

    @BeforeEach
    void setUp() throws IOException {
        validInputFile = tempDir.resolve("model.xmi").toFile();
        validInputFile.createNewFile();
        validOutputFolder = tempDir.resolve("output").toString();
    }

    @Test
    @DisplayName("Should exit(1) when fewer than 3 arguments are provided")
    void validate_tooFewArgs_exitsWithOne() {
        ExitException e = assertThrows(ExitException.class, () ->
            InputValidator.validate(new String[]{"swarch2gitlab", "model.xmi"})
        );
        assertEquals(1, e.getCode());
    }

    @Test
    @DisplayName("Should exit(1) when no arguments are provided")
    void validate_noArgs_exitsWithOne() {
        ExitException e = assertThrows(ExitException.class, () ->
            InputValidator.validate(new String[]{})
        );
        assertEquals(1, e.getCode());
    }

    @Test
    @DisplayName("Should exit(1) when transformation type is empty string")
    void validate_emptyTransformationType_exitsWithOne() {
        ExitException e = assertThrows(ExitException.class, () ->
            InputValidator.validate(new String[]{"", validInputFile.getAbsolutePath(), validOutputFolder})
        );
        assertEquals(1, e.getCode());
    }

    @Test
    @DisplayName("Should exit(1) when transformation type is blank (only whitespace)")
    void validate_blankTransformationType_exitsWithOne() {
        ExitException e = assertThrows(ExitException.class, () ->
            InputValidator.validate(new String[]{"   ", validInputFile.getAbsolutePath(), validOutputFolder})
        );
        assertEquals(1, e.getCode());
    }

    @Test
    @DisplayName("Should exit(1) when input model path is empty string")
    void validate_emptyInputModelPath_exitsWithOne() {
        ExitException e = assertThrows(ExitException.class, () ->
            InputValidator.validate(new String[]{"swarch2gitlab", "", validOutputFolder})
        );
        assertEquals(1, e.getCode());
    }

    @Test
    @DisplayName("Should exit(1) when input model path is blank (only whitespace)")
    void validate_blankInputModelPath_exitsWithOne() {
        ExitException e = assertThrows(ExitException.class, () ->
            InputValidator.validate(new String[]{"swarch2gitlab", "   ", validOutputFolder})
        );
        assertEquals(1, e.getCode());
    }

    @Test
    @DisplayName("Should exit(1) when input model file does not exist on disk")
    void validate_inputFileDoesNotExist_exitsWithOne() {
        String nonExistentPath = tempDir.resolve("doesNotExist.xmi").toString();
        ExitException e = assertThrows(ExitException.class, () ->
            InputValidator.validate(new String[]{"swarch2gitlab", nonExistentPath, validOutputFolder})
        );
        assertEquals(1, e.getCode());
    }

    @Test
    @DisplayName("Should exit(1) when input model path points to a directory, not a file")
    void validate_inputPathIsDirectory_exitsWithOne() {
        ExitException e = assertThrows(ExitException.class, () ->
            InputValidator.validate(new String[]{"swarch2gitlab", tempDir.toString(), validOutputFolder})
        );
        assertEquals(1, e.getCode());
    }

    @Test
    @DisplayName("Should exit(1) when output folder is empty string")
    void validate_emptyOutputFolder_exitsWithOne() {
        ExitException e = assertThrows(ExitException.class, () ->
            InputValidator.validate(new String[]{"swarch2gitlab", validInputFile.getAbsolutePath(), ""})
        );
        assertEquals(1, e.getCode());
    }

    @Test
    @DisplayName("Should exit(1) when output folder is blank (only whitespace)")
    void validate_blankOutputFolder_exitsWithOne() {
        ExitException e = assertThrows(ExitException.class, () ->
            InputValidator.validate(new String[]{"swarch2gitlab", validInputFile.getAbsolutePath(), "   "})
        );
        assertEquals(1, e.getCode());
    }

    @Test
    @DisplayName("Should exit(1) when output path exists but is a file, not a directory")
    void validate_outputPathIsFile_exitsWithOne() throws IOException {
        File fileNotDir = tempDir.resolve("notADir.txt").toFile();
        fileNotDir.createNewFile();
        ExitException e = assertThrows(ExitException.class, () ->
            InputValidator.validate(new String[]{"swarch2gitlab", validInputFile.getAbsolutePath(), fileNotDir.getAbsolutePath()})
        );
        assertEquals(1, e.getCode());
    }

    @Test
    @DisplayName("Should pass validation when all arguments are valid and output dir already exists")
    void validate_allValidArgsOutputDirExists_noExit() {
        File existingOutputDir = tempDir.resolve("existingOutput").toFile();
        existingOutputDir.mkdirs();
        assertDoesNotThrow(() ->
            InputValidator.validate(new String[]{
                "swarch2gitlab",
                validInputFile.getAbsolutePath(),
                existingOutputDir.getAbsolutePath()
            })
        );
    }

    @Test
    @DisplayName("Should pass validation and create output directory when it does not exist yet")
    void validate_allValidArgsOutputDirCreated_noExit() {
        String newOutputDir = tempDir.resolve("brandNewOutputDir").toString();
        assertDoesNotThrow(() ->
            InputValidator.validate(new String[]{
                "pim2gitlab",
                validInputFile.getAbsolutePath(),
                newOutputDir
            })
        );
        assertTrue(new File(newOutputDir).isDirectory(),
            "Output directory should have been created by the validator");
    }
}
