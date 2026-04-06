package main.java.mddoai.generators;

import java.io.IOException;
import java.io.File;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import main.java.ExitException;

public class GeneratorExecutor {
	public static void execute(EObject model, String generatorType, String outputFolder) {
		if (model == null) {
			throw new ExitException(1, "Code generation failed: Input model cannot be null");
		}

		if (generatorType == null || generatorType.trim().isEmpty()) {
			throw new ExitException(1, "Code generation failed: Generator type cannot be null or empty");
		}

		if (outputFolder == null || outputFolder.trim().isEmpty()) {
			throw new ExitException(1, "Code generation failed: Output folder cannot be null or empty");
		}

		File folder = new File(outputFolder);
		if (!folder.exists()) {
			boolean created = folder.mkdirs();
			if (!created) {
				throw new ExitException(1, "Code generation failed: Could not create output folder " + outputFolder);
			}
		} else if (!folder.isDirectory()) {
			throw new ExitException(1, "Code generation failed: Output path exists but is not a directory " + outputFolder);
		}

		try {
			ResourceSet resourceSet = new ResourceSetImpl();

			Generator<EObject> generator;
			try {
				generator = GeneratorFactory.create(generatorType, resourceSet);
				if (generator == null) {
					throw new ExitException(1, "Code generation failed: Could not create generator for type: " + generatorType);
				}
			} catch (IllegalArgumentException e) {
				throw new ExitException(1, "Code generation failed: " + e.getMessage());
			}

			generator.generate(model, outputFolder);

		} catch (ExitException e) {
			throw e;
		} catch (IOException e) {
			throw new ExitException(1, "Code generation failed: " + e.getMessage());
		} catch (Exception e) {
			throw new ExitException(2, "Code generation failed: " + e.getMessage());
		}
	}
}
