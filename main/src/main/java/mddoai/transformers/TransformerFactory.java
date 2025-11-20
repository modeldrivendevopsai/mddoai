package main.java.mddoai.transformers;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;

public class TransformerFactory {
	@SuppressWarnings("unchecked")
	public static <OutputModel extends EObject, InputModel extends EObject> Transformer<OutputModel, InputModel> create(String type, ResourceSet rs) throws IOException {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Transformer type cannot be null or empty");
        }
        
        if (rs == null) {
            throw new IllegalArgumentException("ResourceSet cannot be null");
        }
        
        try {
	    	return switch (type.toLowerCase()) {
	            case "pim2gitlab" -> (Transformer<OutputModel, InputModel>) new FromPIMAbstractTransformer<>(
            		rs, 
            		GitlabMMPackage.eINSTANCE,
            		"./src/main/resources/transformations/pim2psm/pim2gitlabmodel.asm", 
            		"GitLabMM"
	            );
	            case "swarch2pim" -> (Transformer<OutputModel, InputModel>) new FromSWArchAbstractTransformer<>(
	            		rs, 
	            		PimMMPackage.eINSTANCE,
	            		"./src/main/resources/transformations/swarch2pim/swarch2pim.asm", 
	            		"PIMMM"
		            );
	            default -> throw new IllegalArgumentException("Unknown transformer type: " + type);
	        };
        } catch (IOException e) {
        	throw new IOException("Failed to create transformer of type '" + type + "': " + e.getMessage(), e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to create transformer of type '" + type + "': " + e.getMessage(), e);
        }
    }
}
