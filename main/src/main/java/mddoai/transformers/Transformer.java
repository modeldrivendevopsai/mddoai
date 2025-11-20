package main.java.mddoai.transformers;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;

public interface Transformer<OutputModel extends EObject, InputModel> {
	OutputModel transform(InputModel inputModel) throws IOException;
}
