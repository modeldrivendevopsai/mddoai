package main.java.mddoai.transformers;

import com.mddoai.metamodel.pim.pimMM.Pipeline;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.IOException;

public class FromPIMAbstractTransformer<OutputModel extends EObject, OutputPackage extends EPackage> extends ExogenousAbstractTransformer<Pipeline, PimMMPackage, OutputModel, OutputPackage> implements Transformer<OutputModel, Pipeline>{
    public FromPIMAbstractTransformer(ResourceSet resourceSet, OutputPackage outputPackage, String atlFilePath, String outputModelName) throws IOException {
        super(resourceSet, PimMMPackage.eINSTANCE, outputPackage, atlFilePath, "PIM", outputModelName);
    }

    @Override
    public OutputModel transform(Pipeline pipeline) throws IOException {
        return super.transform(pipeline);
    }
}