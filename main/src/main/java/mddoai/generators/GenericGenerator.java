package main.java.mddoai.generators;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;

import main.java.mddoai.generators.wrappers.ICodeGeneratorWrapper;

public class GenericGenerator<T extends EObject, P extends EPackage, G extends ICodeGeneratorWrapper>
		extends AbstractGenerator<T, P> implements Generator<T> {

	private G generatorWrapper;

	public GenericGenerator(ResourceSet resourceSet, P modelPackage, G generatorWrapper) {
		super(resourceSet, modelPackage);
		if(generatorWrapper == null) {
			throw new NullPointerException();
		}
		this.generatorWrapper = generatorWrapper;
	}

	@Override
	protected void runGeneratorMain(String[] args) {
		try {
			generatorWrapper.execute(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void generate(T model, String outputDir) throws IOException {
		super.generate(model, outputDir);
	}
}
