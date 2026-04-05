package test.java.unit.java;

import main.java.ExitException;
import main.java.Main;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Main.run()
 */
class MainTest {

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
    @DisplayName("Should exit(1) when no arguments are provided")
    void run_noArgs_exitsWithOne() {
        ExitException e = assertThrows(ExitException.class, () ->
            Main.run(new String[]{})
        );
        assertEquals(1, e.getCode());
    }

    @Test
    @DisplayName("Should exit(1) when only two arguments are provided")
    void run_twoArgs_exitsWithOne() {
        ExitException e = assertThrows(ExitException.class, () ->
            Main.run(new String[]{"swarch2gitlab", "model.xmi"})
        );
        assertEquals(1, e.getCode());
    }

    @Test
    @DisplayName("Should exit(1) when transformation type is unknown")
    void run_unknownTransformationType_exitsWithOne() {
        ExitException e = assertThrows(ExitException.class, () ->
            Main.run(new String[]{"unknown_type", validInputFile.getAbsolutePath(), validOutputFolder})
        );
        assertEquals(1, e.getCode());
    }
}
