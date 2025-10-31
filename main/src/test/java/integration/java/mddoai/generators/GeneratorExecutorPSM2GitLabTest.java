package test.java.integration.java.mddoai.generators;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Test;

import main.java.mddoai.generators.Generator;
import main.java.mddoai.generators.GeneratorFactory;
import main.java.mddoai.loaders.ModelLoader;
import main.java.mddoai.utils.EMFUtils;

public class GeneratorExecutorPSM2GitLabTest {
	@Test
	public void executesM2CTransformationAndAssertsIfTheOutputIsAsExpected() {
		EMFUtils.init();

		String generationType = "gitlab";
		String inputModelPath = "./src/test/resources/testCases/psm2gitlab/input1.gitlabmm";
		String outputFolderPath = "./test/generatedCode";
		try {
			ResourceSet resourceSet = new ResourceSetImpl();

			Generator<EObject> generator = GeneratorFactory.create(generationType, resourceSet);

			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			generator.generate(inputModel, outputFolderPath);

			Path generatedPath = Paths.get("test/generatedCode/.gitlab-ci.yml");
			String actual = Files.readString(generatedPath);

			Path expectedPath = Paths.get("src/test/resources/testCases/psm2gitlab/expected1.yml");
			String expected = Files.readString(expectedPath);

			actual = actual.replace("\r\n", "\n");
			expected = expected.replace("\r\n", "\n");

			assertEquals(expected, actual);

			new File("./test/generatedCode/.gitlab-ci.yml").delete();

		} catch (Exception e) {
			System.err.println("Code generation failed:");
			e.printStackTrace();
			System.exit(2);
		}
	}

	@Test
	public void executesM2CTransformationAndAssertsIfTheOutputDoesntMatchTheExpected() {
		EMFUtils.init();

		String generationType = "gitlab";
		String inputModelPath = "./src/test/resources/testCases/psm2gitlab/input2.gitlabmm";
		String outputFolderPath = "./test/generatedCode";
		try {
			ResourceSet resourceSet = new ResourceSetImpl();

			Generator<EObject> generator = GeneratorFactory.create(generationType, resourceSet);

			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			generator.generate(inputModel, outputFolderPath);

			Path generatedPath = Paths.get("test/generatedCode/.gitlab-ci.yml");
			String actual = Files.readString(generatedPath);

			Path expectedPath = Paths.get("src/test/resources/testCases/psm2gitlab/expected2.yml");
			String expected = Files.readString(expectedPath);

			actual = actual.replace("\r\n", "\n");
			expected = expected.replace("\r\n", "\n");

			assertNotEquals(expected, actual);

			new File("./test/generatedCode/.gitlab-ci.yml").delete();
		} catch (Exception e) {
			System.err.println("Code generation failed:");
			e.printStackTrace();
			System.exit(2);
		}
	}

	@Test
	public void executesM2CTransformationWithInvalidFilePathItShouldThrowException() {
		EMFUtils.init();

		String generationType = "gitlab";
		String inputModelPath = "./src/test/resources/testCases/psm2gitlab/nonexistent-input-psm.gitlabmm";
		String outputFolderPath = "./test/generatedCode";

		ResourceSet resourceSet = new ResourceSetImpl();

		assertThrows(IllegalArgumentException.class, () -> {
			Generator<EObject> generator = GeneratorFactory.create(generationType, resourceSet);

			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			generator.generate(inputModel, outputFolderPath);
		});

	}

	@Test
	public void executesM2CTransformationWithValidFilePathItShouldNotThrowException() {
		EMFUtils.init();

		String generationType = "gitlab";
		String inputModelPath = "./src/test/resources/testCases/psm2gitlab/input3.gitlabmm";
		String outputFolderPath = "./test/generatedCode";

		ResourceSet resourceSet = new ResourceSetImpl();

		assertDoesNotThrow(() -> {
			Generator<EObject> generator = GeneratorFactory.create(generationType, resourceSet);

			EObject inputModel = ModelLoader.loadModel(inputModelPath, resourceSet, EObject.class);

			generator.generate(inputModel, outputFolderPath);
		});

		new File("./test/generatedCode/.gitlab-ci.yml").delete();
	}
}
