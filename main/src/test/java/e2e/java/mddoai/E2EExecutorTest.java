package test.java.e2e.java.mddoai;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Test;

import com.mddoai.metamodel.swarch.SwarchPackage;

import main.java.mddoai.generators.GeneratorExecutor;
import main.java.mddoai.loaders.ModelLoader;
import main.java.mddoai.transformers.TransformerExecutor;
import main.java.mddoai.utils.EMFUtils;

public class E2EExecutorTest {
	@Test
	public void executesE2ETransformationAndAssertsIfTheOutputIsAsExpected() {
		EMFUtils.init();

		String inputModelPath = "./src/test/resources/testCases/e2e/input1.swarch";
		String outputFolderPath = "./test/generatedCode";
		String expectedCodePath = "./src/test/resources/testCases/e2e/expected1.yml";
		ResourceSet resourceSet = new ResourceSetImpl();
		try {
			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			if (inputModel.eClass().getEPackage() != SwarchPackage.eINSTANCE) {
				System.err.println("Input model should be an SW Arch metamodel instance.");
				System.exit(1);
			}

			String outputModelFilePath = "./test/generatedModels/PipelinePIM.pimmm";
			EObject pimModel = TransformerExecutor.execute("swarch2pim", inputModel, outputModelFilePath);

			outputModelFilePath = "./test/generatedModels/PipelineGit.gitlabmm";
			EObject gitlabModel = TransformerExecutor.execute("pim2gitlab", pimModel, outputModelFilePath);

			GeneratorExecutor.execute(gitlabModel, "gitlab", outputFolderPath);

			Path generatedPath = Paths.get("test/generatedCode/.gitlab-ci.yml");
			String actual = Files.readString(generatedPath);

			Path expectedPath = Paths.get(expectedCodePath);
			String expected = Files.readString(expectedPath);

			actual = actual.replace("\r\n", "\n");
			expected = expected.replace("\r\n", "\n");

			assertEquals(expected, actual);

			new File("./test/generatedModels/PipelinePIM.pimmm").delete();
			new File("./test/generatedModels/PipelineGit.gitlabmm").delete();
			new File("./test/generatedCode/.gitlab-ci.yml").delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void executesE2ETransformationAndAssertsIfTheOutputDoesntMatchTheExpected() {
		EMFUtils.init();

		String inputModelPath = "./src/test/resources/testCases/e2e/input2.swarch";
		String outputFolderPath = "./test/generatedCode";
		String expectedCodePath = "./src/test/resources/testCases/e2e/expected2.yml";
		ResourceSet resourceSet = new ResourceSetImpl();
		try {
			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			if (inputModel.eClass().getEPackage() != SwarchPackage.eINSTANCE) {
				System.err.println("Input model should be an SW Arch metamodel instance.");
				System.exit(1);
			}

			String outputModelFilePath = "./test/generatedModels/PipelinePIM.pimmm";
			EObject pimModel = TransformerExecutor.execute("swarch2pim", inputModel, outputModelFilePath);

			outputModelFilePath = "./test/generatedModels/PipelineGit.gitlabmm";
			EObject gitlabModel = TransformerExecutor.execute("pim2gitlab", pimModel, outputModelFilePath);

			GeneratorExecutor.execute(gitlabModel, "gitlab", outputFolderPath);

			Path generatedPath = Paths.get("test/generatedCode/.gitlab-ci.yml");
			String actual = Files.readString(generatedPath);

			Path expectedPath = Paths.get(expectedCodePath);
			String expected = Files.readString(expectedPath);

			actual = actual.replace("\r\n", "\n");
			expected = expected.replace("\r\n", "\n");

			assertNotEquals(expected, actual);

			new File("./test/generatedModels/PipelinePIM.pimmm").delete();
			new File("./test/generatedModels/PipelineGit.gitlabmm").delete();
			new File("./test/generatedCode/.gitlab-ci.yml").delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void executesE2ETransformationWithInvalidFilePathItShouldThrowException() {
		EMFUtils.init();

		PrintStream originalErr = System.err;
		ByteArrayOutputStream dummyErr = new ByteArrayOutputStream();
		System.setErr(new PrintStream(dummyErr));

		String inputModelPath = "./src/test/resources/testCases/e2e/nonexistent-input-swarch.swarch";
		String outputFolderPath = "./test/generatedCode";

		String generatedCIFile = outputFolderPath + "/.gitlab-ci.yml";

		ResourceSet resourceSet = new ResourceSetImpl();
		try {
			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			if (inputModel.eClass().getEPackage() != SwarchPackage.eINSTANCE) {
				System.err.println("Input model should be an SW Arch metamodel instance.");
				System.exit(1);
			}

			String outputModelFilePath = "./test/generatedModels/PipelinePIM.pimmm";
			EObject pimModel = TransformerExecutor.execute("swarch2pim", inputModel, outputModelFilePath);

			assertNull(pimModel);

			outputModelFilePath = "./test/generatedModels/PipelineGit.gitlabmm";
			EObject gitlabModel = TransformerExecutor.execute("pim2gitlab", pimModel, outputModelFilePath);

			assertNull(gitlabModel);

			File ciFile = new File(generatedCIFile);
			assertFalse(ciFile.exists());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.setErr(originalErr);

			new File("./test/generatedModels/PipelinePIM.pimmm").delete();
			new File("./test/generatedModels/PipelineGit.gitlabmm").delete();
			new File("./test/generatedCode/.gitlab-ci.yml").delete();
		}
	}

	@Test
	public void executesE2ETransformationWithValidFilePathItShouldNotThrowException() {
		EMFUtils.init();

		String inputModelPath = "./src/test/resources/testCases/e2e/input3.swarch";
		String outputFolderPath = "./test/generatedCode";
		ResourceSet resourceSet = new ResourceSetImpl();

		assertDoesNotThrow(() -> {
			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			if (inputModel.eClass().getEPackage() != SwarchPackage.eINSTANCE) {
				System.err.println("Input model should be an SW Arch metamodel instance.");
				System.exit(1);
			}

			String outputModelFilePath = "./test/generatedModels/PipelinePIM.pimmm";
			EObject pimModel = TransformerExecutor.execute("swarch2pim", inputModel, outputModelFilePath);

			outputModelFilePath = "./test/generatedModels/PipelineGit.gitlabmm";
			EObject gitlabModel = TransformerExecutor.execute("pim2gitlab", pimModel, outputModelFilePath);

			GeneratorExecutor.execute(gitlabModel, "gitlab", outputFolderPath);

			new File("./test/generatedModels/PipelinePIM.pimmm").delete();
			new File("./test/generatedModels/PipelineGit.gitlabmm").delete();
			new File("./test/generatedCode/.gitlab-ci.yml").delete();
		});
	}

}
