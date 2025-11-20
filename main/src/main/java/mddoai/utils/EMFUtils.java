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
