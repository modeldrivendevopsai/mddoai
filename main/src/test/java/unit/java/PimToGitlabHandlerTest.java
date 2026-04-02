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

import com.mddoai.metamodel.pim.pimMM.PimMMPackage;

import main.java.PimToGitlabHandler;
import main.java.mddoai.generators.GeneratorExecutor;
import main.java.mddoai.loaders.ModelLoader;
import main.java.mddoai.transformers.TransformerExecutor;

/**
 * Unit tests for PimToGitlabHandler.
 *
 * PimToGitlabHandler takes a PIM model, transforms it to a GitLab model
 * (pim2gitlab), then generates GitLab YAML files.
 * Steps: load → check package → transform (pim2gitlab) → generate → check files
 */
public class PimToGitlabHandlerTest {

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
    @DisplayName("Should exit(1) when ModelLoader returns null for PIM model")
    void handle_nullInputModel_exitsWithOne() throws Exception {
        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(null);

            int code = catchSystemExit(() ->
                PimToGitlabHandler.handle("fake/path/model.pimmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }

    // -------------------------------------------------------------------------
    // Branch 2: Input model is wrong metamodel type (not PIM package)
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when input model is not a PIM metamodel instance")
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
                PimToGitlabHandler.handle("fake/path/model.pimmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }

    // -------------------------------------------------------------------------
    // Branch 3: TransformerExecutor returns null (transformation failed)
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when pim2gitlab transformation returns null")
    void handle_transformationReturnsNull_exitsWithOne() throws Exception {
        EObject validPimModel = mock(EObject.class);
        EClass eClass = mock(EClass.class);
        when(validPimModel.eClass()).thenReturn(eClass);
        when(eClass.getEPackage()).thenReturn(PimMMPackage.eINSTANCE);

        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class);
             MockedStatic<TransformerExecutor> mockTransformer = mockStatic(TransformerExecutor.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(validPimModel);

            // Transformation fails → returns null
            mockTransformer.when(() -> TransformerExecutor.execute(eq("pim2gitlab"), any(EObject.class), anyString()))
                           .thenReturn(null);

            int code = catchSystemExit(() ->
                PimToGitlabHandler.handle("fake/path/model.pimmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }

    // -------------------------------------------------------------------------
    // Branch 4: Generator runs but produces no output files
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when no files are generated in output folder")
    void handle_noFilesGenerated_exitsWithOne() throws Exception {
        EObject validPimModel = mock(EObject.class);
        EClass eClass = mock(EClass.class);
        when(validPimModel.eClass()).thenReturn(eClass);
        when(eClass.getEPackage()).thenReturn(PimMMPackage.eINSTANCE);

        EObject gitlabModel = mock(EObject.class);

        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class);
             MockedStatic<TransformerExecutor> mockTransformer = mockStatic(TransformerExecutor.class);
             MockedStatic<GeneratorExecutor> mockGenerator = mockStatic(GeneratorExecutor.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(validPimModel);

            mockTransformer.when(() -> TransformerExecutor.execute(eq("pim2gitlab"), any(EObject.class), anyString()))
                           .thenReturn(gitlabModel);

            // Generator does nothing → output folder stays empty
            mockGenerator.when(() -> GeneratorExecutor.execute(any(), anyString(), anyString()))
                         .thenAnswer(invocation -> null);

            int code = catchSystemExit(() ->
                PimToGitlabHandler.handle("fake/path/model.pimmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }

    // -------------------------------------------------------------------------
    // Branch 5: Happy path — everything works
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should complete successfully when all steps succeed and files are generated")
    void handle_allStepsSucceed_noExit() throws Exception {
        EObject validPimModel = mock(EObject.class);
        EClass eClass = mock(EClass.class);
        when(validPimModel.eClass()).thenReturn(eClass);
        when(eClass.getEPackage()).thenReturn(PimMMPackage.eINSTANCE);

        EObject gitlabModel = mock(EObject.class);

        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class);
             MockedStatic<TransformerExecutor> mockTransformer = mockStatic(TransformerExecutor.class);
             MockedStatic<GeneratorExecutor> mockGenerator = mockStatic(GeneratorExecutor.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(validPimModel);

            mockTransformer.when(() -> TransformerExecutor.execute(eq("pim2gitlab"), any(EObject.class), anyString()))
                           .thenReturn(gitlabModel);

            // Simulate file being generated
            mockGenerator.when(() -> GeneratorExecutor.execute(any(), anyString(), anyString()))
                         .thenAnswer(invocation -> {
                             new File(outputFolder, "pipeline.gitlab-ci.yml").createNewFile();
                             return null;
                         });

            assertDoesNotThrow(() ->
                PimToGitlabHandler.handle("fake/path/model.pimmm", outputFolder)
            );
        }
    }

    // -------------------------------------------------------------------------
    // Branch 6: ModelLoader throws an exception
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Should exit(1) when ModelLoader throws an unexpected exception")
    void handle_modelLoaderThrowsException_exitsWithOne() throws Exception {
        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class)) {

            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenThrow(new Exception("Simulated loading error"));

            int code = catchSystemExit(() ->
                PimToGitlabHandler.handle("fake/path/model.pimmm", outputFolder)
            );

            assertEquals(1, code);
        }
    }
}
