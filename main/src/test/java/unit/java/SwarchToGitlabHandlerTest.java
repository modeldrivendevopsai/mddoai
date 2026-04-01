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

import com.mddoai.metamodel.swarch.SwarchPackage;

import main.java.SwarchToGitlabHandler;
import main.java.mddoai.generators.GeneratorExecutor;
import main.java.mddoai.loaders.ModelLoader;
import main.java.mddoai.transformers.TransformerExecutor;

/**
 * Unit tests for SwarchToGitlabHandler.
 *
 * SwarchToGitlabHandler is the most complex handler — it has TWO
 * transformation steps:
 *   1. swarch2pim  (SW Architecture → PIM)
 *   2. pim2gitlab  (PIM → GitLab model)
 * Then generates GitLab YAML files from the GitLab model.
 *
 * Steps: load → check package → transform(swarch2pim) → transform(pim2gitlab)
 *        → generate → check files
 */
public class SwarchToGitlabHandlerTest {

    @TempDir
    Path tempDir;

    private String outputFolder;

    @BeforeEach
    void setUp() {
        outputFolder = tempDir.resolve("output").toString();
        new File(outputFolder).mkdirs();
    }

    // -------------------------------------------------------------------------
    // Branch 1: ModelLoader returns null
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when ModelLoader returns null for SW Architecture model")
    void handle_nullInputModel_exitsWithOne() throws Exception {
        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(null);

            int code = catchSystemExit(() ->
                SwarchToGitlabHandler.handle("fake/path/model.swarchmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }

    // -------------------------------------------------------------------------
    // Branch 2: Input model is wrong metamodel type (not SwarchPackage)
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when input model is not a SW Architecture metamodel instance")
    void handle_wrongMetamodelPackage_exitsWithOne() throws Exception {
        EObject wrongModel = mock(EObject.class);
        EClass wrongEClass = mock(EClass.class);
        EPackage wrongPackage = mock(EPackage.class);

        when(wrongModel.eClass()).thenReturn(wrongEClass);
        when(wrongEClass.getEPackage()).thenReturn(wrongPackage);

        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(wrongModel);

            int code = catchSystemExit(() ->
                SwarchToGitlabHandler.handle("fake/path/model.swarchmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }

    // -------------------------------------------------------------------------
    // Branch 3: First transformation (swarch2pim) returns null
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when swarch2pim transformation returns null")
    void handle_swarch2pimTransformationFails_exitsWithOne() throws Exception {
        EObject validSwarchModel = mock(EObject.class);
        EClass eClass = mock(EClass.class);
        when(validSwarchModel.eClass()).thenReturn(eClass);
        when(eClass.getEPackage()).thenReturn(SwarchPackage.eINSTANCE);

        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class);
             MockedStatic<TransformerExecutor> mockTransformer = mockStatic(TransformerExecutor.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(validSwarchModel);

            // First transformation fails
            mockTransformer.when(() -> TransformerExecutor.execute(eq("swarch2pim"), any(EObject.class), anyString()))
                           .thenReturn(null);

            int code = catchSystemExit(() ->
                SwarchToGitlabHandler.handle("fake/path/model.swarchmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }

    // -------------------------------------------------------------------------
    // Branch 4: Second transformation (pim2gitlab) returns null
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when pim2gitlab transformation returns null")
    void handle_pim2gitlabTransformationFails_exitsWithOne() throws Exception {
        EObject validSwarchModel = mock(EObject.class);
        EClass eClass = mock(EClass.class);
        when(validSwarchModel.eClass()).thenReturn(eClass);
        when(eClass.getEPackage()).thenReturn(SwarchPackage.eINSTANCE);

        EObject pimModel = mock(EObject.class);

        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class);
             MockedStatic<TransformerExecutor> mockTransformer = mockStatic(TransformerExecutor.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(validSwarchModel);

            // First transformation succeeds
            mockTransformer.when(() -> TransformerExecutor.execute(eq("swarch2pim"), any(EObject.class), anyString()))
                           .thenReturn(pimModel);

            // Second transformation fails
            mockTransformer.when(() -> TransformerExecutor.execute(eq("pim2gitlab"), any(EObject.class), anyString()))
                           .thenReturn(null);

            int code = catchSystemExit(() ->
                SwarchToGitlabHandler.handle("fake/path/model.swarchmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }

    // -------------------------------------------------------------------------
    // Branch 5: Generator runs but produces no output files
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when no files are generated in output folder")
    void handle_noFilesGenerated_exitsWithOne() throws Exception {
        EObject validSwarchModel = mock(EObject.class);
        EClass eClass = mock(EClass.class);
        when(validSwarchModel.eClass()).thenReturn(eClass);
        when(eClass.getEPackage()).thenReturn(SwarchPackage.eINSTANCE);

        EObject pimModel = mock(EObject.class);
        EObject gitlabModel = mock(EObject.class);

        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class);
             MockedStatic<TransformerExecutor> mockTransformer = mockStatic(TransformerExecutor.class);
             MockedStatic<GeneratorExecutor> mockGenerator = mockStatic(GeneratorExecutor.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(validSwarchModel);

            mockTransformer.when(() -> TransformerExecutor.execute(eq("swarch2pim"), any(EObject.class), anyString()))
                           .thenReturn(pimModel);

            mockTransformer.when(() -> TransformerExecutor.execute(eq("pim2gitlab"), any(EObject.class), anyString()))
                           .thenReturn(gitlabModel);

            // Generator does nothing → output folder stays empty
            mockGenerator.when(() -> GeneratorExecutor.execute(any(), anyString(), anyString()))
                         .thenAnswer(invocation -> null);

            int code = catchSystemExit(() ->
                SwarchToGitlabHandler.handle("fake/path/model.swarchmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }

    // -------------------------------------------------------------------------
    // Branch 6: Happy path — all two transformations and generation succeed
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should complete successfully when all steps succeed and files are generated")
    void handle_allStepsSucceed_noExit() throws Exception {
        EObject validSwarchModel = mock(EObject.class);
        EClass eClass = mock(EClass.class);
        when(validSwarchModel.eClass()).thenReturn(eClass);
        when(eClass.getEPackage()).thenReturn(SwarchPackage.eINSTANCE);

        EObject pimModel = mock(EObject.class);
        EObject gitlabModel = mock(EObject.class);

        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class);
             MockedStatic<TransformerExecutor> mockTransformer = mockStatic(TransformerExecutor.class);
             MockedStatic<GeneratorExecutor> mockGenerator = mockStatic(GeneratorExecutor.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(validSwarchModel);

            mockTransformer.when(() -> TransformerExecutor.execute(eq("swarch2pim"), any(EObject.class), anyString()))
                           .thenReturn(pimModel);

            mockTransformer.when(() -> TransformerExecutor.execute(eq("pim2gitlab"), any(EObject.class), anyString()))
                           .thenReturn(gitlabModel);

            // Simulate file being generated
            mockGenerator.when(() -> GeneratorExecutor.execute(any(), anyString(), anyString()))
                         .thenAnswer(invocation -> {
                             new File(outputFolder, "pipeline.gitlab-ci.yml").createNewFile();
                             return null;
                         });

            assertDoesNotThrow(() ->
                SwarchToGitlabHandler.handle("fake/path/model.swarchmm", outputFolder)
            );
        }
    }

    // -------------------------------------------------------------------------
    // Branch 7: ModelLoader throws an exception
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when ModelLoader throws an unexpected exception")
    void handle_modelLoaderThrowsException_exitsWithOne() throws Exception {
        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenThrow(new Exception("Simulated loading error"));

            int code = catchSystemExit(() ->
                SwarchToGitlabHandler.handle("fake/path/model.swarchmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }
}
