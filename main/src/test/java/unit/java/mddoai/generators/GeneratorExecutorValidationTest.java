package test.java.unit.java.mddoai.generators;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.mddoai.metamodel.pim.pimMM.PimMMFactory;

import main.java.ExitException;
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
    void testExecute_throwsExitException_whenModelIsNull() {
        assertThrows(ExitException.class, () -> GeneratorExecutor.execute(null, "gitlab", tempDir.toString()));
    }

    @Test
    void testExecute_throwsExitException_whenGeneratorTypeIsNull() {
        assertThrows(ExitException.class, () -> GeneratorExecutor.execute(validModel, null, tempDir.toString()));
    }

    @Test
    void testExecute_throwsExitException_whenGeneratorTypeIsEmpty() {
        assertThrows(ExitException.class, () -> GeneratorExecutor.execute(validModel, "", tempDir.toString()));
    }

    @Test
    void testExecute_throwsExitException_whenOutputFolderIsNull() {
        assertThrows(ExitException.class, () -> GeneratorExecutor.execute(validModel, "gitlab", null));
    }

    @Test
    void testExecute_throwsExitException_whenOutputFolderIsEmpty() {
        assertThrows(ExitException.class, () -> GeneratorExecutor.execute(validModel, "gitlab", ""));
    }

    @Test
    void testExecute_throwsExitException_whenOutputPathIsExistingFile() throws IOException {
        // Output path exists but is a file, not a directory — exercises !folder.isDirectory() branch
        File existingFile = tempDir.resolve("notadir.txt").toFile();
        existingFile.createNewFile();

        assertThrows(ExitException.class, () ->
            GeneratorExecutor.execute(validModel, "gitlab", existingFile.getAbsolutePath()));
    }

    @Test
    void testExecute_throwsExitException_whenGeneratorTypeIsUnknown() {
        // Exercises the IllegalArgumentException catch branch from GeneratorFactory.create()
        assertThrows(ExitException.class, () ->
            GeneratorExecutor.execute(validModel, "unknown_type", tempDir.toString()));
    }
}
