package test.java.unit.java.mddoai.utils;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import com.mddoai.metamodel.swarch.SwarchPackage;

import main.java.mddoai.utils.EMFUtils;

public class EMFUtilsTest {
    private ResourceSet resourceSet;

    @BeforeEach
    void setup() {
        resourceSet = new ResourceSetImpl();
        EPackage.Registry.INSTANCE.remove(PimMMPackage.eNS_URI);
        EPackage.Registry.INSTANCE.remove(SwarchPackage.eNS_URI);
        EPackage.Registry.INSTANCE.remove(GitlabMMPackage.eNS_URI);
    }
    
    @Test
    void testInit() {
        assertNull(EPackage.Registry.INSTANCE.getEPackage(PimMMPackage.eNS_URI));
        assertNull(EPackage.Registry.INSTANCE.getEPackage(SwarchPackage.eNS_URI));
        assertNull(EPackage.Registry.INSTANCE.getEPackage(GitlabMMPackage.eNS_URI));
        
        EMFUtils.init();
        
        assertNotNull(EPackage.Registry.INSTANCE.getEPackage(PimMMPackage.eNS_URI));
        assertNotNull(EPackage.Registry.INSTANCE.getEPackage(SwarchPackage.eNS_URI));
        assertNotNull(EPackage.Registry.INSTANCE.getEPackage(GitlabMMPackage.eNS_URI));
        
        assertTrue(Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().containsKey("xmi"));
        assertTrue(Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().containsKey("pimmm"));
        assertTrue(Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().containsKey("swarch"));
        assertTrue(Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().containsKey("gitlabmm"));
    }

    @Test
    void testRegisterExtensionToFactoryMap() {
        assertFalse(resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().containsKey("test"));
        
        XMIResourceFactoryImpl factory = new XMIResourceFactoryImpl();
        EMFUtils.registerExtensionToFactoryMap(resourceSet, "test", factory);
        
        assertTrue(resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().containsKey("test"));
        assertEquals(factory, resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().get("test"));
    }
    
    @Test
    void testRegisterPackages_addsEPackageToRegistry() {
        ResourceSet resourceSet = new ResourceSetImpl();
        EPackage testPackage = PimMMPackage.eINSTANCE;

        EMFUtils.registerPackages(resourceSet, testPackage);

        EPackage.Registry registry = resourceSet.getPackageRegistry();
        String nsURI = testPackage.getNsURI();

        assertTrue(registry.containsKey(nsURI));
        assertSame(testPackage, registry.get(nsURI));
    }
    
    @Test
    void testRegisterPackages_registersMultiplePackages() {
        ResourceSet resourceSet = new ResourceSetImpl();
        EPackage pkg1 = PimMMPackage.eINSTANCE;
        EPackage pkg2 = GitlabMMPackage.eINSTANCE;

        EMFUtils.registerPackages(resourceSet, pkg1, pkg2);

        assertTrue(resourceSet.getPackageRegistry().containsKey(pkg1.getNsURI()));
        assertTrue(resourceSet.getPackageRegistry().containsKey(pkg2.getNsURI()));
    }
}

