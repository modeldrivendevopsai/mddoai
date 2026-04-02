package test.java.unit.java;

import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InputValidator.
 *
 * Dependency needed in build.gradle:
 *   testImplementation 'com.github.stefanbirkner:system-lambda:1.2.1'
 */
class InputValidatorTest {

    @TempDir
    Path tempDir;

    private File validInputFile;
    private String validOutputFolder;

    @BeforeEach
    void setUp() throws IOException {
        // Create a real temp file to act as a valid input model
        validInputFile = tempDir.resolve("model.xmi").toFile();
        validInputFile.createNewFile();

        // A subfolder that doesn't exist yet (InputValidator will create it)
        validOutputFolder = tempDir.resolve("output").toString();
    }

    // -------------------------------------------------------------------------
    // Argument count
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when fewer than 3 arguments are provided")
    void validate_tooFewArgs_exitsWithOne() throws Exception {
        int code = catchSystemExit(() ->
            InputValidator.validate(new String[]{"swarch2gitlab", "model.xmi"})
        );
        assertEquals(1, code);
    }

    @Test
    @DisplayName("Should exit(1) when no arguments are provided")
    void validate_noArgs_exitsWithOne() throws Exception {
        int code = catchSystemExit(() ->
            InputValidator.validate(new String[]{})
        );
        assertEquals(1, code);
    }

    // -------------------------------------------------------------------------
    // Transformation type
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when transformation type is empty string")
    void validate_emptyTransformationType_exitsWithOne() throws Exception {
        int code = catchSystemExit(() ->
            InputValidator.validate(new String[]{"", validInputFile.getAbsolutePath(), validOutputFolder})
        );
        assertEquals(1, code);
    }

    @Test
    @DisplayName("Should exit(1) when transformation type is blank (only whitespace)")
    void validate_blankTransformationType_exitsWithOne() throws Exception {
        int code = catchSystemExit(() ->
            InputValidator.validate(new String[]{"   ", validInputFile.getAbsolutePath(), validOutputFolder})
        );
        assertEquals(1, code);
    }

    // -------------------------------------------------------------------------
    // Input model path
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when input model path is empty string")
    void validate_emptyInputModelPath_exitsWithOne() throws Exception {
        int code = catchSystemExit(() ->
            InputValidator.validate(new String[]{"swarch2gitlab", "", validOutputFolder})
        );
        assertEquals(1, code);
    }

    @Test
    @DisplayName("Should exit(1) when input model path is blank (only whitespace)")
    void validate_blankInputModelPath_exitsWithOne() throws Exception {
        int code = catchSystemExit(() ->
            InputValidator.validate(new String[]{"swarch2gitlab", "   ", validOutputFolder})
        );
        assertEquals(1, code);
    }

    @Test
    @DisplayName("Should exit(1) when input model file does not exist on disk")
    void validate_inputFileDoesNotExist_exitsWithOne() throws Exception {
        String nonExistentPath = tempDir.resolve("doesNotExist.xmi").toString();
        int code = catchSystemExit(() ->
            InputValidator.validate(new String[]{"swarch2gitlab", nonExistentPath, validOutputFolder})
        );
        assertEquals(1, code);
    }

    @Test
    @DisplayName("Should exit(1) when input model path points to a directory, not a file")
    void validate_inputPathIsDirectory_exitsWithOne() throws Exception {
        // tempDir itself is a directory
        int code = catchSystemExit(() ->
            InputValidator.validate(new String[]{"swarch2gitlab", tempDir.toString(), validOutputFolder})
        );
        assertEquals(1, code);
    }

    // -------------------------------------------------------------------------
    // Output folder
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when output folder is empty string")
    void validate_emptyOutputFolder_exitsWithOne() throws Exception {
        int code = catchSystemExit(() ->
            InputValidator.validate(new String[]{"swarch2gitlab", validInputFile.getAbsolutePath(), ""})
        );
        assertEquals(1, code);
    }

    @Test
    @DisplayName("Should exit(1) when output folder is blank (only whitespace)")
    void validate_blankOutputFolder_exitsWithOne() throws Exception {
        int code = catchSystemExit(() ->
            InputValidator.validate(new String[]{"swarch2gitlab", validInputFile.getAbsolutePath(), "   "})
        );
        assertEquals(1, code);
    }

    @Test
    @DisplayName("Should exit(1) when output path exists but is a file, not a directory")
    void validate_outputPathIsFile_exitsWithOne() throws Exception {
        // Create a file where a directory is expected
        File fileNotDir = tempDir.resolve("notADir.txt").toFile();
        fileNotDir.createNewFile();

        int code = catchSystemExit(() ->
            InputValidator.validate(new String[]{"swarch2gitlab", validInputFile.getAbsolutePath(), fileNotDir.getAbsolutePath()})
        );
        assertEquals(1, code);
    }

    // -------------------------------------------------------------------------
    // Happy path
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should pass validation when all arguments are valid and output dir already exists")
    void validate_allValidArgsOutputDirExists_noExit() {
        File existingOutputDir = tempDir.resolve("existingOutput").toFile();
        existingOutputDir.mkdirs();

        // Should not throw or call System.exit
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

        // Verify the directory was actually created
        assertTrue(new File(newOutputDir).isDirectory(),
            "Output directory should have been created by the validator");
    }
}
