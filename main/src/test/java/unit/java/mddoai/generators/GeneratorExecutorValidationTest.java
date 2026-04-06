package test.java.unit.java.mddoai.generators;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.mddoai.metamodel.pim.pimMM.PimMMFactory;

import main.java.mddoai.generators.GeneratorExecutor;
import main.java.mddoai.utils.EMFUtils;

public class GeneratorExecutorValidationTest {

    @TempDir
    Path tempDir;

    private EObject validModel;

    @BeforeEach
    void setUp() {
        EMFUtils.init();
        validModel = PimMMFactory.eINSTANCE.createPipeline();
    }

    @Test
    void testExecute_doesNotThrow_whenModelIsNull() {
        assertDoesNotThrow(() -> GeneratorExecutor.execute(null, "gitlab", tempDir.toString()));
    }

    @Test
    void testExecute_doesNotThrow_whenGeneratorTypeIsNull() {
        assertDoesNotThrow(() -> GeneratorExecutor.execute(validModel, null, tempDir.toString()));
    }

    @Test
    void testExecute_doesNotThrow_whenGeneratorTypeIsEmpty() {
        assertDoesNotThrow(() -> GeneratorExecutor.execute(validModel, "", tempDir.toString()));
    }

    @Test
    void testExecute_doesNotThrow_whenOutputFolderIsNull() {
        assertDoesNotThrow(() -> GeneratorExecutor.execute(validModel, "gitlab", null));
    }

    @Test
    void testExecute_doesNotThrow_whenOutputFolderIsEmpty() {
        assertDoesNotThrow(() -> GeneratorExecutor.execute(validModel, "gitlab", ""));
    }

    @Test
    void testExecute_doesNotThrow_whenOutputPathIsExistingFile() throws IOException {
        // Pass a path that exists but is a FILE (not a directory) — exercises !folder.isDirectory() branch
        File existingFile = tempDir.resolve("notadir.txt").toFile();
        existingFile.createNewFile();

        assertDoesNotThrow(() -> GeneratorExecutor.execute(validModel, "gitlab", existingFile.getAbsolutePath()));
    }

    @Test
    void testExecute_doesNotThrow_whenGeneratorTypeIsUnknown() {
        // Exercises the IllegalArgumentException catch branch from GeneratorFactory.create()
        assertDoesNotThrow(() -> GeneratorExecutor.execute(validModel, "unknown_type", tempDir.toString()));
    }
}
