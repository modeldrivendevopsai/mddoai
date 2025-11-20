package main.java.mddoai.transformers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import main.java.mddoai.utils.EMFUtils;

public class TransformerExecutor {
	public static EObject execute(String transformerType, EObject inputModel, String outputPath) {
		if (transformerType == null || transformerType.trim().isEmpty()) {
			System.err.println("Transformation failed: Transformer type cannot be null or empty");
			return null;
		}

		if (inputModel == null) {
			System.err.println("Transformation failed: Input model cannot be null");
			return null;
		}

		if (outputPath == null || outputPath.trim().isEmpty()) {
			System.err.println("Transformation failed: Output path cannot be null or empty");
			return null;
		}

		Path outputDir = Paths.get(outputPath).getParent();
		if (outputDir != null && !outputDir.toFile().exists()) {
			try {
				outputDir.toFile().mkdirs();
			} catch (Exception e) {
				System.err.println("Transformation failed: Could not create output directory: " + outputDir);
				return null;
			}
		}

		try {
			ResourceSet resourceSet = new ResourceSetImpl();

			Transformer<EObject, EObject> transformer;
			try {
				transformer = TransformerFactory.create(transformerType, resourceSet);
				if (transformer == null) {
					System.err.println(
							"Transformation failed: Could not create transformer for type: " + transformerType);
					return null;
				}
			} catch (IllegalArgumentException e) {
				System.err.println("Transformation failed: " + e.getMessage());
				return null;
			}

			EObject outputPipeline = transformer.transform(inputModel);

			if (outputPipeline == null) {
				System.err.println("Transformation failed: Transformation result is null");
				return null;
			}

			EMFUtils.serializeModel(outputPipeline, outputPath, resourceSet);

			return outputPipeline;
		} catch (IOException e) {
			System.err.println("Transformation failed: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Transformation failed: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
