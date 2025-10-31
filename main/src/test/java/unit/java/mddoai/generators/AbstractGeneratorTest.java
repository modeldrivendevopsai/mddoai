package test.java.unit.java.mddoai.generators;

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

import com.mddoai.codegeneration.gitlab.acceleo.main.Generate;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;

import main.java.mddoai.generators.AbstractGenerator;
import main.java.mddoai.utils.EMFUtils;

public class AbstractGeneratorTest {

	private ResourceSet resourceSet;
    
    @BeforeEach
    void setUp() {
        EMFUtils.init();
        resourceSet = new ResourceSetImpl();
    }
    
    @Test
    void testConstructorWithValidParameters() {
        assertDoesNotThrow(() -> {
            new TestAbstractGenerator(resourceSet, GitlabMMPackage.eINSTANCE);
        });
    }
    
    @Test
    void testConstructorWithNullResourceSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestAbstractGenerator(null, GitlabMMPackage.eINSTANCE);
        });
    }
    
    @Test
    void testConstructorWithNullPackage() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestAbstractGenerator(resourceSet, null);
        });
    }
    
    @Test
    void TestGenerateWithNullValues() {
    	assertThrows(IllegalArgumentException.class, () -> {
    		TestAbstractGenerator generator = new TestAbstractGenerator(resourceSet, GitlabMMPackage.eINSTANCE);
    		
    		generator.generate(null, null);
        });	
    }
    
    @Test
    void testGetters() throws IOException {
    	TestAbstractGenerator generator = new TestAbstractGenerator(resourceSet, GitlabMMPackage.eINSTANCE);
        
        assertEquals(resourceSet, generator.getResourceSet());
        assertEquals(GitlabMMPackage.eINSTANCE, generator.getInputPackage());
    }
    
    private class TestAbstractGenerator extends AbstractGenerator<EObject, EPackage> {
        public TestAbstractGenerator(ResourceSet resourceSet, EPackage inputPackage) throws IOException {
            super(resourceSet, inputPackage);
        }

		@Override
		protected void runGeneratorMain(String[] args) {
			Generate.main(args);
		}
    }
}
