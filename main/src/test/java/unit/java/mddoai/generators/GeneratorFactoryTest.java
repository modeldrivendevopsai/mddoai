package test.java.unit.java.mddoai.generators;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.mddoai.generators.Generator;
import main.java.mddoai.generators.GeneratorFactory;
import main.java.mddoai.generators.GenericGenerator;
import main.java.mddoai.utils.EMFUtils;

public class GeneratorFactoryTest {
private ResourceSet resourceSet;
    
    @BeforeEach
    void setUp() {
        EMFUtils.init();
        resourceSet = new ResourceSetImpl();
    }
    
    @Test
    void testCreateGitLab() throws IOException {
        Generator<EObject> transformer = GeneratorFactory.create("gitlab", resourceSet);
        assertNotNull(transformer);
        assertTrue(transformer instanceof GenericGenerator);
    }
    
    
    @Test
    void testCreateWithNullType() {
        assertThrows(IllegalArgumentException.class, () -> {
        	GeneratorFactory.create(null, resourceSet);
        });
    }
    
    @Test
    void testCreateWithEmptyType() {
        assertThrows(IllegalArgumentException.class, () -> {
        	GeneratorFactory.create("", resourceSet);
        });
    }
    
    @Test
    void testCreateWithNullResourceSet() {
        assertThrows(IllegalArgumentException.class, () -> {
        	GeneratorFactory.create("pim2gitlab", null);
        });
    }
    
    @Test
    void testCreateWithInvalidType() {
        assertThrows(IllegalArgumentException.class, () -> {
        	GeneratorFactory.create("invalid_type", resourceSet);
        });
    }
}
