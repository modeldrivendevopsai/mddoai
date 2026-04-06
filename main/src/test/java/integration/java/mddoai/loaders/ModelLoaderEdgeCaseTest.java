package test.java.integration.java.mddoai.loaders;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.nio.file.Path;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import main.java.mddoai.loaders.ModelLoader;
import main.java.mddoai.utils.EMFUtils;

public class ModelLoaderEdgeCaseTest {

    private static final String EXISTING_MODEL_PATH = "./src/test/resources/testCases/loaders/input1.pimmm";

    @TempDir
    Path tempDir;

    private ResourceSet resourceSet;

    @BeforeEach
    void setUp() {
        EMFUtils.init();
        resourceSet = new ResourceSetImpl();
    }

    @Test
    void testLoadModel_throwsIllegalArgumentException_whenModelPathIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
            ModelLoader.loadModel(null, resourceSet, EObject.class));
    }

    @Test
    void testLoadModel_throwsIllegalArgumentException_whenModelPathIsEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
            ModelLoader.loadModel("", resourceSet, EObject.class));
    }

    @Test
    void testLoadModel_throwsIllegalArgumentException_whenModelPathIsBlank() {
        assertThrows(IllegalArgumentException.class, () ->
            ModelLoader.loadModel("   ", resourceSet, EObject.class));
    }

    @Test
    void testLoadModel_throwsIllegalArgumentException_whenExpectedTypeIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
            ModelLoader.loadModel(EXISTING_MODEL_PATH, resourceSet, null));
    }

    @Test
    void testLoadModel_throwsIllegalArgumentException_whenFileIsEmpty() throws Exception {
        File emptyFile = tempDir.resolve("empty.pimmm").toFile();
        emptyFile.createNewFile();

        assertThrows(IllegalArgumentException.class, () ->
            ModelLoader.loadModel(emptyFile.getAbsolutePath(), resourceSet, EObject.class));
    }

    @Test
    void testLoadModel_throwsIllegalArgumentException_whenPathIsDirectory() {
        String dirPath = tempDir.toAbsolutePath().toString();

        assertThrows(IllegalArgumentException.class, () ->
            ModelLoader.loadModel(dirPath, resourceSet, EObject.class));
    }
}
