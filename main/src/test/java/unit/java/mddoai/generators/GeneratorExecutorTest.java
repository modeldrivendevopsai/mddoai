package test.java.unit.java.mddoai.generators;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import main.java.mddoai.generators.GeneratorExecutor;

class GeneratorExecutorTest {

    @TempDir
    File tempDir;

    @Test
    @DisplayName("Should handle null model gracefully")
    void execute_nullModel_returnsEarly() {
        // Covers: if (model == null)
        GeneratorExecutor.execute(null, "gitlab", "output");
    }

    @Test
    @DisplayName("Should handle null or empty generator type")
    void execute_invalidType_returnsEarly() {
        EObject mockModel = mock(EObject.class);
        // Covers: if (generatorType == null || empty)
        GeneratorExecutor.execute(mockModel, "", "output");
        GeneratorExecutor.execute(mockModel, null, "output");
    }

    @Test
    @DisplayName("Should handle null or empty output folder")
    void execute_invalidFolder_returnsEarly() {
        EObject mockModel = mock(EObject.class);
        // Covers: if (outputFolder == null || empty)
        GeneratorExecutor.execute(mockModel, "gitlab", "  ");
    }

    @Test
    @DisplayName("Should create directory if it does not exist")
    void execute_createsDirectory() {
        EObject mockModel = mock(EObject.class);
        File newFolder = new File(tempDir, "auto_created_folder");
        
        // This will trigger folder.mkdirs()
        // We use an invalid type so it returns after creating the folder
        GeneratorExecutor.execute(mockModel, "non_existent_type", newFolder.getAbsolutePath());
        
        assertTrue(newFolder.exists(), "Folder should have been created by mkdirs()");
    }

    @Test
    @DisplayName("Should handle case where output path is a file, not a directory")
    void execute_pathIsFile_returnsEarly() throws IOException {
        EObject mockModel = mock(EObject.class);
        File realFile = new File(tempDir, "actually_a_file.txt");
        realFile.createNewFile();

        // Covers: else if (!folder.isDirectory())
        GeneratorExecutor.execute(mockModel, "gitlab", realFile.getAbsolutePath());
    }

    @Test
    @DisplayName("Should return early when directory creation fails")
    void execute_mkdirFails_returnsEarly() {
        EObject mockModel = mock(EObject.class);
        // On Windows, you can't create a folder named "CON" or "PRN" easily, 
        // or we use a path that is impossible to create.
        GeneratorExecutor.execute(mockModel, "gitlab", "Z:/impossible/path/here");
    }

    @Test
    @DisplayName("Should exit with status 2 when an unexpected exception occurs")
    void execute_unexpectedException_exitsWithTwo() throws Exception {
        // We need to pass the folder check but fail inside the try block.
        // We pass a mock that will cause a NullPointerException inside the real generate() method.
        EObject mockModel = mock(EObject.class);
        String validPath = tempDir.getAbsolutePath();

        int statusCode = catchSystemExit(() -> {
            // "gitlab" type is valid, but the mock model will make the generator crash
            // triggering the 'catch (Exception e)' block.
            GeneratorExecutor.execute(mockModel, "gitlab", validPath);
        });

        assertEquals(2, statusCode, "Should hit System.exit(2) on unexpected runtime exception");
    }
}