package test.java.unit.java.mddoai.transformers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import com.mddoai.metamodel.swarch.SwarchPackage;

import main.java.mddoai.transformers.ExogenousAbstractTransformer;
import main.java.mddoai.utils.EMFUtils;

public class ExogenousAbstractTransformerTest {

	private ResourceSet resourceSet;

	@BeforeEach
	void setUp() {
		EMFUtils.init();
		resourceSet = new ResourceSetImpl();
	}

	@Test
	void testConstructorWithValidParameters() {
		assertDoesNotThrow(() -> {
			new TestExogenousTransformer(resourceSet, SwarchPackage.eINSTANCE, PimMMPackage.eINSTANCE,
					"./src/main/resources/transformations/swarch2pim/swarch2pim.asm", "SWArchMM", "PIMMM");
		});
	}

	@Test
	void testConstructorWithNullResourceSet() {
		assertThrows(IllegalArgumentException.class, () -> {
			new TestExogenousTransformer(null, SwarchPackage.eINSTANCE, PimMMPackage.eINSTANCE,
					"./src/main/resources/transformations/swarch2pim/swarch2pim.asm", "SWArchMM", "PIMMM");
		});
	}

	@Test
	void testConstructorWithNullInputPackage() {
		assertThrows(IllegalArgumentException.class, () -> {
			new TestExogenousTransformer(resourceSet, null, PimMMPackage.eINSTANCE,
					"./src/main/resources/transformations/swarch2pim/swarch2pim.asm", "SWArchMM", "PIMMM");
		});
	}

	@Test
	void testConstructorWithNullOutputPackage() {
		assertThrows(IllegalArgumentException.class, () -> {
			new TestExogenousTransformer(resourceSet, SwarchPackage.eINSTANCE, null,
					"./src/main/resources/transformations/swarch2pim/swarch2pim.asm", "SWArchMM", "PIMMM");
		});
	}

	@Test
	void testConstructorWithNullAtlFilePath() {
		assertThrows(IllegalArgumentException.class, () -> {
			new TestExogenousTransformer(resourceSet, SwarchPackage.eINSTANCE, PimMMPackage.eINSTANCE, null, "SWArchMM",
					"PIMMM");
		});
	}

	@Test
	void testConstructorWithEmptyAtlFilePath() {
		assertThrows(IllegalArgumentException.class, () -> {
			new TestExogenousTransformer(resourceSet, SwarchPackage.eINSTANCE, PimMMPackage.eINSTANCE, "", "SWArchMM",
					"PIMMM");
		});
	}

	@Test
	void testConstructorWithNullInputModelName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new TestExogenousTransformer(resourceSet, SwarchPackage.eINSTANCE, PimMMPackage.eINSTANCE,
					"./src/main/resources/transformations/swarch2pim/swarch2pim.asm", null, "PIMMM");
		});
	}

	@Test
	void testConstructorWithEmptyInputModelName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new TestExogenousTransformer(resourceSet, SwarchPackage.eINSTANCE, PimMMPackage.eINSTANCE,
					"./src/main/resources/transformations/swarch2pim/swarch2pim.asm", "", "PIMMM");
		});
	}

	@Test
	void testConstructorWithNullOutputModelName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new TestExogenousTransformer(resourceSet, SwarchPackage.eINSTANCE, PimMMPackage.eINSTANCE,
					"./src/main/resources/transformations/swarch2pim/swarch2pim.asm", "SWArchMM", null);
		});
	}

	@Test
	void testConstructorWithEmptyOutputModelName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new TestExogenousTransformer(resourceSet, SwarchPackage.eINSTANCE, PimMMPackage.eINSTANCE,
					"./src/main/resources/transformations/swarch2pim/swarch2pim.asm", "SWArchMM", "");
		});
	}

	private class TestExogenousTransformer extends ExogenousAbstractTransformer<EObject, EPackage, EObject, EPackage> {
		public TestExogenousTransformer(ResourceSet resourceSet, EPackage inputPackage, EPackage outputPackage,
				String atlFilePath, String inputModelName, String outputModelName) throws IOException {
			super(resourceSet, inputPackage, outputPackage, atlFilePath, inputModelName, outputModelName);
		}
	}
}