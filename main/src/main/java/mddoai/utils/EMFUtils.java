package main.java.mddoai.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import com.mddoai.metamodel.swarch.SwarchPackage;

public class EMFUtils {
	public static void init() {
	    Map<String, Object> factoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
	    factoryMap.put("xmi", new XMIResourceFactoryImpl());
	    factoryMap.put("pimmm", new XMIResourceFactoryImpl());
	    factoryMap.put("swarch", new XMIResourceFactoryImpl());
	    factoryMap.put("gitlabmm", new XMIResourceFactoryImpl());

	    EPackage.Registry.INSTANCE.put(PimMMPackage.eNS_URI, PimMMPackage.eINSTANCE);
	    EPackage.Registry.INSTANCE.put(SwarchPackage.eNS_URI, SwarchPackage.eINSTANCE);
	    EPackage.Registry.INSTANCE.put(GitlabMMPackage.eNS_URI, GitlabMMPackage.eINSTANCE);
    }

    public static void registerPackages(ResourceSet resourceSet, EPackage ...ePackages) {
        for (EPackage ePackage : ePackages) {
            resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
        }
    }

    // Loads a single EPackage straight from a .ecore file via ordinary EMF
    // resource loading - no genmodel, no generated Java code, no compile
    // step ("dynamic EMF"). The returned EPackage exposes the same
    // EClass/EStructuralFeature metadata a generated Java package's own
    // eINSTANCE would, which is all a reflective consumer (an OCL-based
    // tool like Acceleo's compiler) ever resolves types against - confirmed
    // empirically against Acceleo's real classic compiler. Returns null,
    // never throws, if the file doesn't parse to a single EPackage, so a
    // caller can report that as an ordinary validation issue rather than an
    // exception.
    //
    // The content this loads is not necessarily trusted: callers include a
    // platform's own PSM ecore forwarded here from an HTTP request body
    // (real caller: AcceleoValidator.validate(String, String), fed by
    // ai/validator_agent's own /validate/acceleo metamodel_ecore field,
    // itself fed unmodified from ai/acceleo_agent's own POST /generate
    // request body). Two independent EMF behaviors are hardened against
    // that, not just one: (1) plain XML parsing of a DOCTYPE with an
    // external entity can make this JVM fetch an arbitrary local file or
    // remote URL (classic XXE - EMF's own resource loading applies no
    // parser hardening by default, a real, disclosed gap, see
    // eclipse-emf/org.eclipse.emf#10), disabled outright below; (2)
    // entirely separately, EMF's own ordinary cross-document proxy
    // resolution (resolveAll() below) will happily dereference an http(s)
    // href in a well-formed cross-reference to wherever it points -
    // NetworkBlockingURIHandler below closes that for remote (network)
    // schemes specifically. It does not attempt to also confine local
    // filesystem cross-references (a `file:` href, or a relative one that
    // resolves against this same container's own disk) - this deployment
    // is local-only, not a multi-tenant service handling content from
    // untrusted third parties, so that narrower guarantee hasn't been
    // needed; see NetworkBlockingURIHandler's own comment for the exact
    // scheme this covers.
    public static EPackage loadEPackage(String ecoreFilePath) {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put("ecore", new org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl());
        hardenAgainstUntrustedContent(resourceSet);

        Resource resource = resourceSet.createResource(URI.createFileURI(new File(ecoreFilePath).getAbsolutePath()));
        try {
            resource.load(hardenedEcoreLoadOptions());
        } catch (Exception e) {
            return null;
        }
        if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof EPackage)) {
            return null;
        }
        EPackage ePackage = (EPackage) resource.getContents().get(0);
        EcoreUtil.resolveAll(ePackage);
        return ePackage;
    }

    // Shared by every caller in main/ that parses a .ecore file whose
    // content isn't necessarily trusted (this method's own callers, plus
    // EcoreValidator.validateReflectively() - both real, HTTP-reachable
    // entry points into validator-agent, one per real submitted file type).
    // Disables DOCTYPE declarations outright (XXE): EMF's own resource
    // loading applies no parser hardening by default, a real, disclosed gap
    // (see eclipse-emf/org.eclipse.emf#10). Pass the returned map to
    // resource.load(...) instead of load(null).
    public static Map<String, Object> hardenedEcoreLoadOptions() {
        Map<String, Object> loadOptions = new java.util.HashMap<>();
        Map<String, Boolean> parserFeatures = new java.util.HashMap<>();
        parserFeatures.put("http://apache.org/xml/features/disallow-doctype-decl", Boolean.TRUE);
        loadOptions.put(org.eclipse.emf.ecore.xmi.XMLResource.OPTION_PARSER_FEATURES, parserFeatures);
        return loadOptions;
    }

    // The other half of the same hardening: entirely independent of XML
    // entities, EMF's own ordinary cross-document proxy resolution
    // (EcoreUtil.resolveAll()) will happily dereference an http(s) href in
    // a well-formed cross-reference to wherever it points (SSRF). Call
    // before resolving any untrusted content's references; only same-file
    // references still resolve, which is all self-contained content ever
    // legitimately needs.
    public static void hardenAgainstUntrustedContent(ResourceSet resourceSet) {
        resourceSet.getURIConverter().getURIHandlers().add(0, new NetworkBlockingURIHandler());
    }

    // Intercepts every non-file, non-platform URI scheme (http, https, ftp,
    // ...) ahead of EMF's own default handler, turning what would otherwise
    // be a real outbound connection (java.net.URL.openConnection(), inside
    // EMF's own default URIHandlerImpl) into an ordinary IOException - the
    // same failure shape an unresolvable proxy already produces, handled
    // the same way EMF always has (an unresolved reference, not a crash).
    public static final class NetworkBlockingURIHandler extends org.eclipse.emf.ecore.resource.impl.URIHandlerImpl {
        @Override
        public boolean canHandle(URI uri) {
            String scheme = uri.scheme();
            return scheme != null && !"file".equalsIgnoreCase(scheme) && !"platform".equalsIgnoreCase(scheme);
        }

        @Override
        public java.io.InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
            throw new IOException("Network access disabled while resolving untrusted metamodel content: " + uri);
        }

        @Override
        public java.io.OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
            throw new IOException("Network access disabled while resolving untrusted metamodel content: " + uri);
        }
    }

    public static void registerExtensionToFactoryMap(ResourceSet resourceSet, String extension, Object factory) {
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(extension, factory);
    }

    public static URI serializeModel(EObject model, String filePath, ResourceSet resourceSet) throws IOException {
        

        Resource completeResource = createResource(model, filePath, resourceSet);
        completeResource.save(null);
        return completeResource.getURI();
    }


    public static Resource createResource(EObject model, String filePath, ResourceSet resourceSet) {
        URI uri = URI.createURI(filePath);
        Resource completeResource = resourceSet.createResource(uri);

        List<EObject> collection = new ArrayList<>();
        readReferences(model, new HashSet<>(), collection);

        completeResource.getContents().addAll(EcoreUtil.copyAll(collection));

        return completeResource;
    }

    private static void readReferences(EObject eobject, HashSet<EObject> preventCycles, List<EObject> rootList) {
        if (eobject == null) {
            return;
        }
        if(preventCycles.contains(eobject)){
            return;
        }
        preventCycles.add(eobject);
        if(eobject.eContainer() != null){
            readReferences(eobject.eContainer(), preventCycles, rootList);
        }else{
            rootList.add(eobject);
        }
        for(EReference eRefObj : eobject.eClass().getEAllReferences()){
            final Object value = eobject.eGet(eRefObj);
            if (value == null) {
                continue;
            }
            if(value instanceof List){
                for(Object obj : (List<?>)value){
                    readReferences((EObject)obj, preventCycles, rootList);
                }
            }else{
                readReferences((EObject)value, preventCycles, rootList);
            }
        }
    }

    public static Object deserializeModel(String filePath, ResourceSet resourceSet) throws IOException {
    	File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (parentDir == null || !parentDir.exists() || !parentDir.isDirectory() || !parentDir.canWrite()) {
            throw new IOException("Cannot write to directory: " + (parentDir != null ? parentDir.getAbsolutePath() : "null"));
        }
    	
        Resource resource = resourceSet.createResource(org.eclipse.emf.common.util.URI.createURI(filePath));

        resource.load(null);

        for (EObject obj : resource.getContents()) {
            EcoreUtil.resolveAll(obj);
        }

        return resource.getContents().get(0);
    }
}
