package main.java.mddoai.transformers;

import com.mddoai.metamodel.swarch.SoftwareArchitecture;
import com.mddoai.metamodel.swarch.SwarchPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.IOException;

public class FromSWArchAbstractTransformer<OutputModel extends EObject, OutputPackage extends EPackage> extends ExogenousAbstractTransformer<SoftwareArchitecture, SwarchPackage, OutputModel, OutputPackage> implements Transformer<OutputModel, SoftwareArchitecture>{
    public FromSWArchAbstractTransformer(ResourceSet resourceSet, OutputPackage outputPackage, String atlFilePath, String outputModelName) throws IOException {
        super(resourceSet, SwarchPackage.eINSTANCE, outputPackage, atlFilePath, "SWArchMM", outputModelName);
    }

    @Override
    public OutputModel transform(SoftwareArchitecture pipeline) throws IOException {
        return super.transform(pipeline);
    }
}