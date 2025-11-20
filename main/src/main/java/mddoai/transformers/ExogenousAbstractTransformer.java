package main.java.mddoai.transformers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.IOException;

public abstract class ExogenousAbstractTransformer<InputModel extends EObject, InputPackage extends EPackage, OutputModel extends EObject, OutputPackage extends EPackage> extends AbstractTransformer<InputModel, InputPackage, OutputModel, OutputPackage> {

    protected ExogenousAbstractTransformer(ResourceSet resourceSet, InputPackage inputPackage, OutputPackage outputPackage, String atlFilePath, String inputModelName, String outputModelName) throws IOException {
        super(resourceSet, inputPackage, outputPackage, atlFilePath, inputModelName, outputModelName);
    }

}