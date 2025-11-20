package main.java.mddoai.generators;

import java.io.IOException;
import java.io.File;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class GeneratorExecutor {
	public static void execute(EObject model, String generatorType, String outputFolder) {
		if (model == null) {
			System.err.println("Code generation failed: Input model cannot be null");
			return;
		}
		
		if (generatorType == null || generatorType.trim().isEmpty()) {
			System.err.println("Code generation failed: Generator type cannot be null or empty");
			return;
		}
		
		if (outputFolder == null || outputFolder.trim().isEmpty()) {
			System.err.println("Code generation failed: Output folder cannot be null or empty");
			return;
		}
		
		// Validate output folder exists, create if it doesn't
		File folder = new File(outputFolder);
		if (!folder.exists()) {
			boolean created = folder.mkdirs();
			if (!created) {
				System.err.println("Code generation failed: Could not create output folder " + outputFolder);
				return;
			}
		} else if (!folder.isDirectory()) {
			System.err.println("Code generation failed: Output path exists but is not a directory " + outputFolder);
			return;
		}
		
		try {
			ResourceSet resourceSet = new ResourceSetImpl();

			Generator<EObject> generator;
			try {
				generator = GeneratorFactory.create(generatorType, resourceSet);
				if (generator == null) {
					System.err.println("Code generation failed: Could not create generator for type: " + generatorType);
					return;
				}
			} catch (IllegalArgumentException e) {
				System.err.println("Code generation failed: " + e.getMessage());
				return;
			}

			generator.generate(model, outputFolder);

		} catch (IOException e) {
			System.err.println("Code generation failed: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Code generation failed: " + e.getMessage());
			e.printStackTrace();
			System.exit(2);
		}
	}
}
