package main.java.mddoai.loaders;

import java.io.File;

import org.eclipse.emf.ecore.resource.ResourceSet;

import main.java.mddoai.utils.EMFUtils;

public class ModelLoader {
	public static <T> T loadModel(String modelPath, ResourceSet resourceSet, Class<T> expectedType) throws Exception {
		if (modelPath == null || modelPath.trim().isEmpty()) {
			throw new IllegalArgumentException("Model path cannot be null or empty");
		}
		
		if (resourceSet == null) {
			throw new IllegalArgumentException("ResourceSet cannot be null");
		}
		
		if (expectedType == null) {
			throw new IllegalArgumentException("Expected type cannot be null");
		}
		
		File inputFile = new File(modelPath);
		if (!inputFile.exists() || !inputFile.isFile()) {
			throw new IllegalArgumentException("Input model file does not exist: " + modelPath);
		}
		
		if (inputFile.length() == 0) {
			throw new IllegalArgumentException("Input model file is empty: " + modelPath);
		}

		try {
			EMFUtils.init();
			
			Object raw = EMFUtils.deserializeModel(modelPath, resourceSet);
			
			if (raw == null) {
				throw new IllegalArgumentException("Failed to deserialize model from " + modelPath);
			}
			
			if (!expectedType.isInstance(raw)) {
				throw new IllegalArgumentException("Model is not instance of " + expectedType.getSimpleName() + 
						". Found: " + (raw.getClass().getSimpleName()));
			}
			
			return expectedType.cast(raw);
		} catch (Exception e) {
			throw new Exception("Error loading model " + modelPath + ": " + e.getMessage(), e);
		}
	}
}
