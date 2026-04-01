package test.java.unit.java.mddoai.generators;

import static org.junit.jupiter.api.Assertions.*;
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
        // Using an invalid type ensures it exits after creation but before generating
        GeneratorExecutor.execute(mockModel, "invalid_type_to_stop_early", newFolder.getAbsolutePath());
        
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
    @DisplayName("Should handle unknown generator type safely")
    void execute_unknownGeneratorType_returnsEarly() {
        EObject mockModel = mock(EObject.class);
        String validPath = tempDir.getAbsolutePath();

        // This triggers the 'catch (IllegalArgumentException e)' block inside the try
        // when GeneratorFactory.create fails to find the type.
        // This is safe coverage that won't call System.exit(2).
        GeneratorExecutor.execute(mockModel, "unknown_type_xyz", validPath);
    }
}