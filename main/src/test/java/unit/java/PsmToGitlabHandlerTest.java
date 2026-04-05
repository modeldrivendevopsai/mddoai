package test.java.unit.java;

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

import main.java.ExitException;
import main.java.PsmToGitlabHandler;
import main.java.mddoai.generators.GeneratorExecutor;
import main.java.mddoai.loaders.ModelLoader;

public class PsmToGitlabHandlerTest {

    @TempDir
    Path tempDir;

    private String outputFolder;

    @BeforeEach
    void setUp() {
        outputFolder = tempDir.resolve("output").toString();
        new File(outputFolder).mkdirs();
    }

    @Test
    @DisplayName("Should exit(1) when ModelLoader returns null for input model")
    void handle_nullInputModel_exitsWithOne() {
        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class)) {
            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(null);

            ExitException e = assertThrows(ExitException.class, () ->
                PsmToGitlabHandler.handle("fake/path/model.gitlabmm", outputFolder)
            );
            assertEquals(1, e.getCode());
        }
    }

    @Test
    @DisplayName("Should exit(1) when input model is not a GitLab metamodel instance")
    void handle_wrongMetamodelPackage_exitsWithOne() {
        EObject wrongModel = mock(EObject.class);
        EClass wrongEClass = mock(EClass.class);
        EPackage wrongPackage = mock(EPackage.class);
        when(wrongModel.eClass()).thenReturn(wrongEClass);
        when(wrongEClass.getEPackage()).thenReturn(wrongPackage);

        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class)) {
            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(wrongModel);

            ExitException e = assertThrows(ExitException.class, () ->
                PsmToGitlabHandler.handle("fake/path/model.gitlabmm", outputFolder)
            );
            assertEquals(1, e.getCode());
        }
    }

    @Test
    @DisplayName("Should exit(1) when no files are generated in output folder")
    void handle_noFilesGenerated_exitsWithOne() {
        EObject validModel = mock(EObject.class);
        EClass eClass = mock(EClass.class);
        when(validModel.eClass()).thenReturn(eClass);
        when(eClass.getEPackage()).thenReturn(GitlabMMPackage.eINSTANCE);

        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class);
             MockedStatic<GeneratorExecutor> mockGenerator = mockStatic(GeneratorExecutor.class)) {
            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenReturn(validModel);
            mockGenerator.when(() -> GeneratorExecutor.execute(any(), anyString(), anyString()))
                         .thenAnswer(invocation -> null);

            ExitException e = assertThrows(ExitException.class, () ->
                PsmToGitlabHandler.handle("fake/path/model.gitlabmm", outputFolder)
            );
            assertEquals(1, e.getCode());
        }
    }

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
            mockGenerator.when(() -> GeneratorExecutor.execute(any(), anyString(), anyString()))
                         .thenAnswer(invocation -> {
                             new File(outputFolder, "pipeline.gitlab-ci.yml").createNewFile();
                             return null;
                         });

            assertDoesNotThrow(() ->
                PsmToGitlabHandler.handle("fake/path/model.gitlabmm", outputFolder)
            );
        }
    }

    @Test
    @DisplayName("Should exit(1) when ModelLoader throws an exception")
    void handle_modelLoaderThrowsException_exitsWithOne() {
        try (MockedStatic<ModelLoader> mockLoader = mockStatic(ModelLoader.class)) {
            mockLoader.when(() -> ModelLoader.loadModel(anyString(), any(ResourceSet.class), eq(EObject.class)))
                      .thenThrow(new RuntimeException("Simulated loading error"));

            ExitException e = assertThrows(ExitException.class, () ->
                PsmToGitlabHandler.handle("fake/path/model.gitlabmm", outputFolder)
            );
            assertEquals(1, e.getCode());
        }
    }
}
