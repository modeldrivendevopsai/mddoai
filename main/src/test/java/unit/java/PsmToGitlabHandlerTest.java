package test.java.unit.java;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.nio.file.Path;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;

import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;

import main.java.PsmToGitlabHandler;
import main.java.mddoai.generators.GeneratorExecutor;
import main.java.mddoai.loaders.ModelLoader;

/**
 * Unit tests for PsmToGitlabHandler.
 *
 * PsmToGitlabHandler takes a GitLab PSM model file and generates
 * GitLab YAML output files. It is the simplest handler — no
 * transformation step, just load → generate.
 *
 * We use Mockito's mockStatic to "fake" ModelLoader and GeneratorExecutor
 * so that we can test each branch without needing real EMF model files.
 */
public class PsmToGitlabHandlerTest {

    @TempDir
    Path tempDir;

    private String outputFolder;

    @BeforeEach
    void setUp() {
        outputFolder = tempDir.resolve("output").toString();
        new File(outputFolder).mkdirs();
    }

    // -------------------------------------------------------------------------
    // Branch 1: ModelLoader returns null (model failed to load)
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when ModelLoader returns null for input model")
    void handle_nullInputModel_exitsWithOne() throws Exception {
        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(null);

            int code = catchSystemExit(() ->
                PsmToGitlabHandler.handle("fake/path/model.gitlabmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }

    // -------------------------------------------------------------------------
    // Branch 2: Input model is wrong metamodel type (not GitLab package)
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when input model is not a GitLab metamodel instance")
    void handle_wrongMetamodelPackage_exitsWithOne() throws Exception {
        // Create a mock EObject whose package is NOT GitlabMMPackage
        EObject wrongModel = mock(EObject.class);
        EClass wrongEClass = mock(EClass.class);
        EPackage wrongPackage = mock(EPackage.class); // some other package

        when(wrongModel.eClass()).thenReturn(wrongEClass);
        when(wrongEClass.getEPackage()).thenReturn(wrongPackage);
        // wrongPackage != GitlabMMPackage.eINSTANCE → handler exits

        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(wrongModel);

            int code = catchSystemExit(() ->
                PsmToGitlabHandler.handle("fake/path/model.gitlabmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }

    // -------------------------------------------------------------------------
    // Branch 3: GeneratorExecutor runs but produces no output files
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when no files are generated in output folder")
    void handle_noFilesGenerated_exitsWithOne() throws Exception {
        // Mock a correctly-typed model (package matches GitlabMMPackage)
        EObject validModel = mock(EObject.class);
        EClass eClass = mock(EClass.class);
        when(validModel.eClass()).thenReturn(eClass);
        when(eClass.getEPackage()).thenReturn(GitlabMMPackage.eINSTANCE);

        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class);
             MockedStatic<GeneratorExecutor> mockGenerator = mockStatic(GeneratorExecutor.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(validModel);

            // GeneratorExecutor does nothing — output folder stays empty
            mockGenerator.when(() -> GeneratorExecutor.execute(any(), anyString(), anyString()))
                         .thenAnswer(invocation -> null);

            int code = catchSystemExit(() ->
                PsmToGitlabHandler.handle("fake/path/model.gitlabmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }

    // -------------------------------------------------------------------------
    // Branch 4: Happy path — model loads, correct package, files are generated
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should complete successfully when model is valid and files are generated")
    void handle_validModelAndFilesGenerated_noExit() throws Exception {
        EObject validModel = mock(EObject.class);
        EClass eClass = mock(EClass.class);
        when(validModel.eClass()).thenReturn(eClass);
        when(eClass.getEPackage()).thenReturn(GitlabMMPackage.eINSTANCE);

        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class);
             MockedStatic<GeneratorExecutor> mockGenerator = mockStatic(GeneratorExecutor.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(validModel);

            // Simulate generator creating a file in the output folder
            mockGenerator.when(() -> GeneratorExecutor.execute(any(), anyString(), anyString()))
                         .thenAnswer(invocation -> {
                             new File(outputFolder, "pipeline.gitlab-ci.yml").createNewFile();
                             return null;
                         });

            // Should complete without calling System.exit
            assertDoesNotThrow(() ->
                PsmToGitlabHandler.handle("fake/path/model.gitlabmm", outputFolder)
            );
        }
    }

    // -------------------------------------------------------------------------
    // Branch 5: ModelLoader throws an exception
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when ModelLoader throws an exception")
    void handle_modelLoaderThrowsException_exitsWithOne() throws Exception {
        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenThrow(new Exception("Simulated loading error"));

            int code = catchSystemExit(() ->
                PsmToGitlabHandler.handle("fake/path/model.gitlabmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }
}
