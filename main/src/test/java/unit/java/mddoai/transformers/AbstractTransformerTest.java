package test.java.unit.java.mddoai.transformers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import main.java.mddoai.transformers.AbstractTransformer;
import main.java.mddoai.utils.EMFUtils;


class AbstractTransformerTest {
    
    private ResourceSet resourceSet;
    
    @BeforeEach
    void setUp() {
        EMFUtils.init();
        resourceSet = new ResourceSetImpl();
    }
    
    @Test
    void testConstructorWithValidParameters() {
        assertDoesNotThrow(() -> {
            new TestAbstractTransformer(
                resourceSet,
                SwarchPackage.eINSTANCE,
                PimMMPackage.eINSTANCE,
                "./src/main/resources/transformations/swarch2pim/swarch2pim.asm",
                "SWArchMM",
                "PIMMM"
            );
        });
    }
    
    @Test
    void testConstructorWithNullResourceSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestAbstractTransformer(
                null,
                SwarchPackage.eINSTANCE,
                PimMMPackage.eINSTANCE,
                "./src/main/resources/transformations/swarch2pim/swarch2pim.asm",
                "SWArchMM",
                "PIMMM"
            );
        });
    }
    
    @Test
    void testConstructorWithNullInputPackage() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestAbstractTransformer(
                resourceSet,
                null,
                PimMMPackage.eINSTANCE,
                "./src/main/resources/transformations/swarch2pim/swarch2pim.asm",
                "SWArchMM",
                "PIMMM"
            );
        });
    }
    
    @Test
    void testConstructorWithNullOutputPackage() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestAbstractTransformer(
                resourceSet,
                SwarchPackage.eINSTANCE,
                null,
                "./src/main/resources/transformations/swarch2pim/swarch2pim.asm",
                "SWArchMM",
                "PIMMM"
            );
        });
    }
    
    @Test
    void testConstructorWithNullAtlFilePath() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestAbstractTransformer(
                resourceSet,
                SwarchPackage.eINSTANCE,
                PimMMPackage.eINSTANCE,
                null,
                "SWArchMM",
                "PIMMM"
            );
        });
    }
    
    @Test
    void testConstructorWithEmptyAtlFilePath() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestAbstractTransformer(
                resourceSet,
                SwarchPackage.eINSTANCE,
                PimMMPackage.eINSTANCE,
                "",
                "SWArchMM",
                "PIMMM"
            );
        });
    }
    
    @Test
    void testConstructorWithNullInputModelName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestAbstractTransformer(
                resourceSet,
                SwarchPackage.eINSTANCE,
                PimMMPackage.eINSTANCE,
                "./src/main/resources/transformations/swarch2pim/swarch2pim.asm",
                null,
                "PIMMM"
            );
        });
    }
    
    @Test
    void testConstructorWithEmptyInputModelName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestAbstractTransformer(
                resourceSet,
                SwarchPackage.eINSTANCE,
                PimMMPackage.eINSTANCE,
                "./src/main/resources/transformations/swarch2pim/swarch2pim.asm",
                "",
                "PIMMM"
            );
        });
    }
    
    @Test
    void testConstructorWithNullOutputModelName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestAbstractTransformer(
                resourceSet,
                SwarchPackage.eINSTANCE,
                PimMMPackage.eINSTANCE,
                "./src/main/resources/transformations/swarch2pim/swarch2pim.asm",
                "SWArchMM",
                null
            );
        });
    }
    
    @Test
    void testConstructorWithEmptyOutputModelName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestAbstractTransformer(
                resourceSet,
                SwarchPackage.eINSTANCE,
                PimMMPackage.eINSTANCE,
                "./src/main/resources/transformations/swarch2pim/swarch2pim.asm",
                "SWArchMM",
                ""
            );
        });
    }
    
    @Test
    void testTransformWithNullInputModel() {      
        assertThrows(IllegalArgumentException.class, () -> {
            TestAbstractTransformer transformer = new TestAbstractTransformer(
                    resourceSet,
                    SwarchPackage.eINSTANCE,
                    PimMMPackage.eINSTANCE,
                    "./src/main/resources/transformations/swarch2pim/swarch2pim.asm",
                    "SWArchMM",
                    "PIMMM"
                );
            transformer.transform(null);
        });
    }
    
    @Test
    void testGetters() throws IOException {
        TestAbstractTransformer transformer = new TestAbstractTransformer(
            resourceSet,
            SwarchPackage.eINSTANCE,
            PimMMPackage.eINSTANCE,
            "./src/main/resources/transformations/swarch2pim/swarch2pim.asm",
            "SWArchMM",
            "PIMMM"
        );
        
        assertEquals(resourceSet, transformer.getResourceSet());
        assertEquals(SwarchPackage.eINSTANCE, transformer.getInputPackage());
        assertEquals(PimMMPackage.eINSTANCE, transformer.getOutputPackage());
    }
// =========================================================================
    // NEW TESTS: Targeting the missing Coverage (transform and deserialize)
    // =========================================================================

    @Test
    void testTransformIOExceptionTriggersCleanup() {
        TestAbstractTransformer transformer = assertDoesNotThrow(() -> new TestAbstractTransformer(
            resourceSet,
            SwarchPackage.eINSTANCE,
            PimMMPackage.eINSTANCE,
            "./src/main/resources/transformations/swarch2pim/swarch2pim.asm",
            "SWArchMM",
            "PIMMM"
        ));
        
        // 1. Tell our custom transformer to simulate a hard crash during ATL transformation
        transformer.simulateIoException = true;

        // 2. Pass a REAL EObject so EMFUtils doesn't throw a NullPointerException
        EObject validDummyInput = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEObject();
        
        // 3. This will now throw the IOException safely, triggering the 11 missed cleanup lines!
        assertThrows(IOException.class, () -> {
            transformer.transform(validDummyInput);
        });
    }

    @Test
    void testDeserializeEmptyFileThrowsException() {
        TestAbstractTransformer transformer = assertDoesNotThrow(() -> new TestAbstractTransformer(
            resourceSet,
            SwarchPackage.eINSTANCE,
            PimMMPackage.eINSTANCE,
            "./src/main/resources/transformations/swarch2pim/swarch2pim.asm",
            "SWArchMM",
            "PIMMM"
        ));
        
        // 1. Flag to make our overridden method create an empty file instead of a real model
        transformer.simulateEmptyOutput = true;

        // 2. Create a basic valid EObject so the initial serialization passes
        EObject validDummyInput = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEObject();

        // 3. When it tries to deserialize the empty output file, it hits the 5 missed lines
        assertThrows(IOException.class, () -> {
            transformer.transform(validDummyInput);
        });
    }

    // =========================================================================
    // UPDATED INNER CLASS: Exposes protected methods for our new tests
    // =========================================================================

    private class TestAbstractTransformer extends AbstractTransformer<EObject, EPackage, EObject, EPackage> {
        
        public boolean simulateEmptyOutput = false;
        public boolean simulateIoException = false;

        public TestAbstractTransformer(ResourceSet resourceSet, EPackage inputPackage, EPackage outputPackage,
                String atlFilePath, String inputModelName, String outputModelName) throws IOException {
            super(resourceSet, inputPackage, outputPackage, atlFilePath, inputModelName, outputModelName);
        }

        // Override the actual transformation to safely simulate errors
        @Override
        protected void runATLTransformation(String inputModelFilePath, String outputModelFilePath) throws IOException {
            if (simulateIoException) {
                // Simulate a crash to trigger the cleanup catch block in transform()
                throw new IOException("Simulated ATL failure for cleanup testing");
            } else if (simulateEmptyOutput) {
                // Create an empty file to trigger the deserialization error
                new java.io.File(outputModelFilePath).createNewFile(); 
            } else {
                super.runATLTransformation(inputModelFilePath, outputModelFilePath);
            }
        }
    }
}