package test.java.integration.java.mddoai.loaders;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mddoai.metamodel.pim.pimMM.Pipeline;

import main.java.mddoai.loaders.ModelLoader;
import main.java.mddoai.utils.EMFUtils;

public class ModleLoaderTest {
	private static final String TEST_PIM_MODEL_PATH = "./src/test/resources/testCases/loaders/input1.pimmm";
	@BeforeEach
	void setup() {
		EMFUtils.init();
	}
	@Test
	void testLoadModel_whereModelIsAbstract() {
		ResourceSet resourceSet = new ResourceSetImpl();
		String modelPath = TEST_PIM_MODEL_PATH;
		
		try {
			EObject model = ModelLoader.loadModel(modelPath, resourceSet, EObject.class);
			
			assertNotNull(model);
			assertTrue(model instanceof EObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testLoadModel_whereModelIsKnown() {
		ResourceSet resourceSet = new ResourceSetImpl();
		String modelPath = TEST_PIM_MODEL_PATH;
		
		try {
			Pipeline model = ModelLoader.loadModel(modelPath, resourceSet, Pipeline.class);
			
			assertNotNull(model);
			assertEquals(4, model.getJobStreams().size());
			assertEquals("build", model.getJobStreams().get(0).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testLoadModel_throwsException_wherePathIsInvalid() {
		ResourceSet resourceSet = new ResourceSetImpl();
		String modelPath = "./invalid/path/model.pimmm";

		assertThrows(IllegalArgumentException.class, () -> {
			ModelLoader.loadModel(modelPath, resourceSet, EObject.class);
        });
	}
	
	@Test
	void testLoadModel_throwsException_whereResourceSetIsNull() {
		ResourceSet resourceSet = null;
		String modelPath = TEST_PIM_MODEL_PATH;

		assertThrows(IllegalArgumentException.class, () -> {
			ModelLoader.loadModel(modelPath, resourceSet, EObject.class);
        });
	}
	
	@Test
	void testLoadModel_throwsException_whereInvalidClass() {
		ResourceSet resourceSet = new ResourceSetImpl();
		String modelPath = TEST_PIM_MODEL_PATH;

		assertThrows(Exception.class, () -> {
			ModelLoader.loadModel(modelPath, resourceSet, String.class);
        });
	}
}
