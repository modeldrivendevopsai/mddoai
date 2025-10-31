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

class TransformerExecutorPIM2GitLabTest {
	@Test
	public void executesM2MTransformationAndAssertsIfTheOutputIsAsExpected() {
		EMFUtils.init();

		String transformationType = "pim2gitlab";
		String inputModelPath = "./src/test/resources/testCases/pim2psm/input1.pimmm";
		String outputModelFilePath = "./test/generatedModels/output1.gitlabmm";
		String expectedModelPath = "./src/test/resources/testCases/pim2psm/expected1.gitlabmm";
		try {
			ResourceSet resourceSet = new ResourceSetImpl();

			Transformer<EObject, EObject> transformer = TransformerFactory.create(transformationType, resourceSet);

			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			EObject outputPipeline = transformer.transform(inputModel);

			EMFUtils.serializeModel(outputPipeline, outputModelFilePath, resourceSet);

			EObject exptectedOutput = ModelLoader.loadModel(expectedModelPath, resourceSet, EObject.class);

			assertTrue(EcoreUtil.equals(outputPipeline, exptectedOutput));

			new File("./test/generatedModels/output1.gitlabmm").delete();
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

		String transformationType = "pim2gitlab";
		String inputModelPath = "./src/test/resources/testCases/pim2psm/input2.pimmm";
		String outputModelFilePath = "./test/generatedModels/output2.gitlabmm";
		String expectedModelPath = "./src/test/resources/testCases/pim2psm/expected2.gitlabmm";
		try {
			ResourceSet resourceSet = new ResourceSetImpl();

			Transformer<EObject, EObject> transformer = TransformerFactory.create(transformationType, resourceSet);

			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			EObject outputPipeline = transformer.transform(inputModel);

			EMFUtils.serializeModel(outputPipeline, outputModelFilePath, resourceSet);

			EObject exptectedOutput = ModelLoader.loadModel(expectedModelPath, resourceSet, EObject.class);

			assertFalse(EcoreUtil.equals(outputPipeline, exptectedOutput));

			new File("./test/generatedModels/output2.gitlabmm").delete();
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

		String transformationType = "pim2gitlab";
		String inputModelPath = "./src/test/resources/testCases/pim2psm/nonexistent-input-pim.pimmm";
		String outputModelFilePath = "./test/generatedModels/invalid-output-git.gitlabmm";

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

		String transformationType = "pim2gitlab";
		String inputModelPath = "./src/test/resources/testCases/pim2psm/input3.pimmm";
		String outputModelFilePath = "./test/generatedModels/output3.gitlabmm";

		ResourceSet resourceSet = new ResourceSetImpl();

		assertDoesNotThrow(() -> {
			Transformer<EObject, EObject> transformer = TransformerFactory.create(transformationType, resourceSet);

			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			EObject outputPipeline = transformer.transform(inputModel);

			EMFUtils.serializeModel(outputPipeline, outputModelFilePath, resourceSet);
		});

		new File("./test/generatedModels/output3.gitlabmm").delete();
	}

}
