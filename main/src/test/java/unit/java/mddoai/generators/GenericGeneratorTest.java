package test.java.unit.java.mddoai.generators;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.pim.pimMM.PimMMFactory;
import com.mddoai.metamodel.pim.pimMM.Pipeline;

import main.java.mddoai.generators.GenericGenerator;
import main.java.mddoai.generators.wrappers.ICodeGeneratorWrapper;
import main.java.mddoai.utils.EMFUtils;

import java.nio.file.Path;

public class GenericGeneratorTest {

	private ResourceSet resourceSet;
	private TestGeneratorWrapper generatorWrapper;

	@TempDir
	Path tempDir;

	@BeforeEach
	void setUp() {
		EMFUtils.init();
		resourceSet = new ResourceSetImpl();
		generatorWrapper = new TestGeneratorWrapper();
	}

	@Test
	void testConstructorWithValidParameters() {
		assertDoesNotThrow(() -> {
			new GenericGenerator<>(resourceSet, GitlabMMPackage.eINSTANCE, generatorWrapper);
		});
	}

	@Test
	void testConstructorWithNullResourceSet() {
		assertThrows(IllegalArgumentException.class, () -> {
			new GenericGenerator<>(null, GitlabMMPackage.eINSTANCE, generatorWrapper);
		});
	}

	@Test
	void testConstructorWithNullPackage() {
		assertThrows(IllegalArgumentException.class, () -> {
			new GenericGenerator<>(resourceSet, null, generatorWrapper);
		});
	}

	@Test
	void testConstructorWithNullGeneratorWrapper() {
		assertThrows(NullPointerException.class, () -> {
			new GenericGenerator<>(resourceSet, GitlabMMPackage.eINSTANCE, null);
		});
	}

	@Test
	void testGenerateWithValidParameters() throws IOException {
		GenericGenerator<EObject, GitlabMMPackage, TestGeneratorWrapper> generator = new GenericGenerator<>(resourceSet,
				GitlabMMPackage.eINSTANCE, generatorWrapper);

		Pipeline pipeline = PimMMFactory.eINSTANCE.createPipeline();

		assertDoesNotThrow(() -> {
			generator.generate(pipeline, tempDir.toString());
		});

		assert (generatorWrapper.wasExecuted);
	}

	@Test
	void testGenerateWithNullModel() throws IOException {
		GenericGenerator<EObject, GitlabMMPackage, TestGeneratorWrapper> generator = new GenericGenerator<>(resourceSet,
				GitlabMMPackage.eINSTANCE, generatorWrapper);

		assertThrows(IllegalArgumentException.class, () -> {
			generator.generate(null, tempDir.toString());
		});
	}

	@Test
	void testGenerateWithNullOutputDir() throws IOException {
		GenericGenerator<EObject, GitlabMMPackage, TestGeneratorWrapper> generator = new GenericGenerator<>(resourceSet,
				GitlabMMPackage.eINSTANCE, generatorWrapper);

		Pipeline pipeline = PimMMFactory.eINSTANCE.createPipeline();

		assertThrows(IllegalArgumentException.class, () -> {
			generator.generate(pipeline, null);
		});
	}

	@Test
	void testGenerateWithEmptyOutputDir() throws IOException {
		GenericGenerator<EObject, GitlabMMPackage, TestGeneratorWrapper> generator = new GenericGenerator<>(resourceSet,
				GitlabMMPackage.eINSTANCE, generatorWrapper);

		Pipeline pipeline = PimMMFactory.eINSTANCE.createPipeline();

		assertThrows(IllegalArgumentException.class, () -> {
			generator.generate(pipeline, "");
		});
	}

	private static class TestGeneratorWrapper implements ICodeGeneratorWrapper {
		boolean wasExecuted = false;

		@Override
		public void execute(String[] args) throws Exception {
			wasExecuted = true;
		}
	}
}