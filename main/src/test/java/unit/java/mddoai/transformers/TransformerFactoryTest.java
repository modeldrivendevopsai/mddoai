package test.java.unit.java.mddoai.transformers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.mddoai.transformers.FromPIMAbstractTransformer;
import main.java.mddoai.transformers.FromSWArchAbstractTransformer;
import main.java.mddoai.transformers.Transformer;
import main.java.mddoai.transformers.TransformerFactory;
import main.java.mddoai.utils.EMFUtils;

class TransformerFactoryTest {
    
    private ResourceSet resourceSet;
    
    @BeforeEach
    void setUp() {
        EMFUtils.init();
        resourceSet = new ResourceSetImpl();
    }
    
    @Test
    void testCreatePIM2GitLab() throws IOException {
        Transformer<EObject, EObject> transformer = TransformerFactory.create("pim2gitlab", resourceSet);
        assertNotNull(transformer);
        assertTrue(transformer instanceof FromPIMAbstractTransformer);
    }
    
    @Test
    void testCreateSWArch2PIM() throws IOException {
        Transformer<EObject, EObject> transformer = TransformerFactory.create("swarch2pim", resourceSet);
        assertNotNull(transformer);
        assertTrue(transformer instanceof FromSWArchAbstractTransformer);
    }
    
    @Test
    void testCreateWithNullType() {
        assertThrows(IllegalArgumentException.class, () -> {
            TransformerFactory.create(null, resourceSet);
        });
    }
    
    @Test
    void testCreateWithEmptyType() {
        assertThrows(IllegalArgumentException.class, () -> {
            TransformerFactory.create("", resourceSet);
        });
    }
    
    @Test
    void testCreateWithNullResourceSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            TransformerFactory.create("pim2gitlab", null);
        });
    }
    
    @Test
    void testCreateWithInvalidType() {
        assertThrows(IllegalArgumentException.class, () -> {
            TransformerFactory.create("invalid_type", resourceSet);
        });
    }
} 