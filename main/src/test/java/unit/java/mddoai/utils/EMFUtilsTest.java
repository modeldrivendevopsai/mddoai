package test.java.unit.java.mddoai.utils;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.emf.common.util.URI;
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

    // loadEPackage() is the one place this codebase parses a .ecore file
    // that may not be trusted (a platform's own PSM ecore, forwarded here
    // from an HTTP request body all the way from ai/acceleo_agent's own
    // POST /generate) - these two cases are the real security regression
    // tests for that: a DOCTYPE must be rejected outright (XXE), and a
    // cross-document network reference must never actually connect (SSRF),
    // while a normal, self-contained .ecore still loads correctly.

    @Test
    void loadEPackage_rejectsADoctypeDeclaration(@org.junit.jupiter.api.io.TempDir Path tempDir) throws IOException {
        Path ecoreFile = tempDir.resolve("malicious.ecore");
        Files.writeString(ecoreFile, """
                <?xml version="1.0" encoding="UTF-8"?>
                <!DOCTYPE ecore:EPackage [
                    <!ENTITY xxe SYSTEM "file:///etc/passwd">
                ]>
                <ecore:EPackage xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI"
                    xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" name="evil"
                    nsURI="http://example.com/evil" nsPrefix="evil">
                  <eClassifiers xsi:type="ecore:EClass" name="&xxe;"
                      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
                </ecore:EPackage>
                """);

        EPackage result = EMFUtils.loadEPackage(ecoreFile.toString());

        assertNull(result, "a .ecore file with a DOCTYPE declaration must be rejected, not parsed");
    }

    @Test
    void loadEPackage_stillLoadsAWellFormedEcoreWithNoDoctype(@org.junit.jupiter.api.io.TempDir Path tempDir) throws IOException {
        Path ecoreFile = tempDir.resolve("clean.ecore");
        Files.writeString(ecoreFile, """
                <?xml version="1.0" encoding="UTF-8"?>
                <ecore:EPackage xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI"
                    xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" name="clean"
                    nsURI="http://example.com/clean" nsPrefix="clean">
                  <eClassifiers xsi:type="ecore:EClass" name="Widget"
                      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
                </ecore:EPackage>
                """);

        EPackage result = EMFUtils.loadEPackage(ecoreFile.toString());

        assertNotNull(result, "a normal, DOCTYPE-free .ecore file must still load");
        assertEquals("http://example.com/clean", result.getNsURI());
    }

    @Test
    void networkBlockingURIHandler_claimsHttpAndHttpsSchemes() {
        EMFUtils.NetworkBlockingURIHandler handler = new EMFUtils.NetworkBlockingURIHandler();

        assertTrue(handler.canHandle(URI.createURI("http://attacker.example/evil.ecore")));
        assertTrue(handler.canHandle(URI.createURI("https://attacker.example/evil.ecore")));
        assertTrue(handler.canHandle(URI.createURI("ftp://attacker.example/evil.ecore")));
    }

    @Test
    void networkBlockingURIHandler_leavesFileAndPlatformSchemesAlone() {
        EMFUtils.NetworkBlockingURIHandler handler = new EMFUtils.NetworkBlockingURIHandler();

        assertFalse(handler.canHandle(URI.createFileURI("/some/local/path.ecore")));
        assertFalse(handler.canHandle(URI.createURI("platform:/resource/some/path.ecore")));
    }

    @Test
    void networkBlockingURIHandler_refusesToOpenAConnectionForABlockedScheme() {
        EMFUtils.NetworkBlockingURIHandler handler = new EMFUtils.NetworkBlockingURIHandler();
        URI blocked = URI.createURI("http://attacker.example/evil.ecore#//Foo");

        // The real security property: this must throw before any real
        // java.net connection is attempted, not merely report the URI as
        // unreachable after trying it.
        assertThrows(IOException.class, () -> handler.createInputStream(blocked, null));
        assertThrows(IOException.class, () -> handler.createOutputStream(blocked, null));
    }
}

