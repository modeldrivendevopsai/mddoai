package test.java.integration.java.mddoai.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.pim.pimMM.PimMMFactory;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import com.mddoai.metamodel.pim.pimMM.Pipeline;
import com.mddoai.metamodel.pim.pimMM.Stage;
import com.mddoai.metamodel.swarch.SwarchPackage;

import main.java.mddoai.utils.EMFUtils;

public class EMFUtilsTest {
	private ResourceSet resourceSet;
    private static final String TEST_PIM_MODEL_PATH = "./src/test/resources/testCases/utils/input1.pimmm";
    private static final String TEST_PIM_OUTPUT_PATH = "./src/test/resources/testCases/utils/output1.pimmm";

    @BeforeEach
    void setup() {
        resourceSet = new ResourceSetImpl();
        EPackage.Registry.INSTANCE.remove(PimMMPackage.eNS_URI);
        EPackage.Registry.INSTANCE.remove(SwarchPackage.eNS_URI);
        EPackage.Registry.INSTANCE.remove(GitlabMMPackage.eNS_URI);
    }
    
    @AfterEach
    void cleanup() {
        deleteTestFile(TEST_PIM_MODEL_PATH);
        deleteTestFile(TEST_PIM_OUTPUT_PATH);
    }
    
    private void deleteTestFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }
	
	@Test
    void testSerializeModel_createsFileAndURI() throws IOException {
        EMFUtils.registerPackages(resourceSet, PimMMPackage.eINSTANCE);
        EObject model = PimMMFactory.eINSTANCE.createPipeline();

        EMFUtils.init();
        
        URI uri = EMFUtils.serializeModel(model, TEST_PIM_MODEL_PATH, resourceSet);

        assertNotNull(uri);
        File file = new File(TEST_PIM_MODEL_PATH);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
    
    @Test
    void testCreateResource() {
        EMFUtils.registerPackages(resourceSet, PimMMPackage.eINSTANCE);
        EObject model = PimMMFactory.eINSTANCE.createPipeline();
        
        EMFUtils.init();
        
        Resource resource = EMFUtils.createResource(model, TEST_PIM_MODEL_PATH, resourceSet);
        
        assertNotNull(resource);
        assertEquals(URI.createURI(TEST_PIM_MODEL_PATH), resource.getURI());
        assertFalse(resource.getContents().isEmpty());
        assertTrue(resource.getContents().get(0) instanceof Pipeline);
    }
    
    @Test
    void testDeserializeModel() throws IOException {
        EMFUtils.registerPackages(resourceSet, PimMMPackage.eINSTANCE);
        Pipeline pipeline = PimMMFactory.eINSTANCE.createPipeline();
        
        EMFUtils.init();
        
        EMFUtils.serializeModel(pipeline, TEST_PIM_MODEL_PATH, resourceSet);
        
        ResourceSet newResourceSet = new ResourceSetImpl();
        EMFUtils.registerPackages(newResourceSet, PimMMPackage.eINSTANCE);
        
        Object result = EMFUtils.deserializeModel(TEST_PIM_MODEL_PATH, newResourceSet);
        
        assertNotNull(result);
        assertTrue(result instanceof Pipeline);
    }
    
    @Test
    void testSerializeAndDeserializeWithReferences() throws IOException {
        EMFUtils.registerPackages(resourceSet, PimMMPackage.eINSTANCE);
        
        Pipeline pipeline = PimMMFactory.eINSTANCE.createPipeline();
        
        Stage stage1 = PimMMFactory.eINSTANCE.createStage();
        stage1.setName("Stage1");
        Stage stage2 = PimMMFactory.eINSTANCE.createStage();
        stage2.setName("Stage2");
        pipeline.getStages().add(stage1);
        pipeline.getStages().add(stage2);
        
        EMFUtils.init();
        
        EMFUtils.serializeModel(pipeline, TEST_PIM_MODEL_PATH, resourceSet);
        
        ResourceSet newResourceSet = new ResourceSetImpl();
        EMFUtils.registerPackages(newResourceSet, PimMMPackage.eINSTANCE);
        
        Pipeline deserializedPipeline = (Pipeline) EMFUtils.deserializeModel(TEST_PIM_MODEL_PATH, newResourceSet);
        
        assertNotNull(deserializedPipeline);
        assertEquals(2, deserializedPipeline.getStages().size());
        assertEquals("Stage1", deserializedPipeline.getStages().get(0).getName());
        assertEquals("Stage2", deserializedPipeline.getStages().get(1).getName());
    }
    
    @Test
    void testDeserializeModel_throwsIOException_whenFileNotExists() {
        EMFUtils.registerPackages(resourceSet, PimMMPackage.eINSTANCE);
        
        assertThrows(IOException.class, () -> {
            EMFUtils.deserializeModel("nonexistent.pimmm", resourceSet);
        });
    }
}
