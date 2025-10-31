package test.java.integration.java.mddoai.transformers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.Test;

import main.java.mddoai.loaders.ModelLoader;
import main.java.mddoai.transformers.Transformer;
import main.java.mddoai.transformers.TransformerFactory;
import main.java.mddoai.utils.EMFUtils;

class TransformerExecutorSwarch2PIMTest {
	@Test
	public void executesM2MTransformationAndAssertsIfTheOutputIsAsExpected() {
		EMFUtils.init();

		String transformationType = "swarch2pim";
		String inputModelPath = "./src/test/resources/testCases/swarch2pim/input1.swarch";
		String outputModelFilePath = "./test/generatedModels/output1.pimmm";
		String expectedModelPath = "./src/test/resources/testCases/swarch2pim/expected1.pimmm";
		try {
			ResourceSet resourceSet = new ResourceSetImpl();

			Transformer<EObject, EObject> transformer = TransformerFactory.create(transformationType, resourceSet);

			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			EObject outputPipeline = transformer.transform(inputModel);

			EMFUtils.serializeModel(outputPipeline, outputModelFilePath, resourceSet);

			EObject exptectedOutput = ModelLoader.loadModel(expectedModelPath, resourceSet, EObject.class);

			assertTrue(EcoreUtil.equals(outputPipeline, exptectedOutput));

			new File("./test/generatedModels/output1.pimmm").delete();
		} catch (IOException e) {
			System.err.println("Transformation failed:");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void executesM2MTransformationAndAssertsIfTheOutputDoesntMatchTheExpected() {
		EMFUtils.init();

		String transformationType = "swarch2pim";
		String inputModelPath = "./src/test/resources/testCases/swarch2pim/input2.swarch";
		String outputModelFilePath = "./test/generatedModels/output2.pimmm";
		String expectedModelPath = "./src/test/resources/testCases/swarch2pim/expected2.pimmm";
		try {
			ResourceSet resourceSet = new ResourceSetImpl();

			Transformer<EObject, EObject> transformer = TransformerFactory.create(transformationType, resourceSet);

			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			EObject outputPipeline = transformer.transform(inputModel);

			EMFUtils.serializeModel(outputPipeline, outputModelFilePath, resourceSet);

			EObject exptectedOutput = ModelLoader.loadModel(expectedModelPath, resourceSet, EObject.class);

			assertFalse(EcoreUtil.equals(outputPipeline, exptectedOutput));

			new File("./test/generatedModels/output2.pimmm").delete();
		} catch (IOException e) {
			System.err.println("Transformation failed:");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void executesM2MTransformationWithInvalidFilePathItShouldThrowException() {
		EMFUtils.init();

		String transformationType = "swarch2pim";
		String inputModelPath = "./src/test/resources/testCases/swarch2pim/nonexistent-input-swarch.swarch";
		String outputModelFilePath = "./test/generatedModels/invalid-output-pim.pimmm";

		ResourceSet resourceSet = new ResourceSetImpl();

		assertThrows(IllegalArgumentException.class, () -> {
			Transformer<EObject, EObject> transformer = TransformerFactory.create(transformationType, resourceSet);

			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			EObject outputPipeline = transformer.transform(inputModel);

			EMFUtils.serializeModel(outputPipeline, outputModelFilePath, resourceSet);
		});
	}

	@Test
	public void executesM2MTransformationWithValidFilePathItShouldNotThrowException() {
		EMFUtils.init();

		String transformationType = "swarch2pim";
		String inputModelPath = "./src/test/resources/testCases/swarch2pim/input3.swarch";
		String outputModelFilePath = "./test/generatedModels/output3.pimmm";

		ResourceSet resourceSet = new ResourceSetImpl();

		assertDoesNotThrow(() -> {
			Transformer<EObject, EObject> transformer = TransformerFactory.create(transformationType, resourceSet);

			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			EObject outputPipeline = transformer.transform(inputModel);

			EMFUtils.serializeModel(outputPipeline, outputModelFilePath, resourceSet);
		});

		new File("./test/generatedModels/output3.pimmm").delete();
	}

}
