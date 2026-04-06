package test.java.unit.java.mddoai.transformers;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mddoai.metamodel.pim.pimMM.PimMMFactory;

import main.java.mddoai.transformers.TransformerExecutor;
import main.java.mddoai.utils.EMFUtils;

public class TransformerExecutorValidationTest {

    private EObject validModel;

    @BeforeEach
    void setUp() {
        EMFUtils.init();
        new ResourceSetImpl();
        validModel = PimMMFactory.eINSTANCE.createPipeline();
    }

    @Test
    void testExecute_returnsNull_whenTransformerTypeIsNull() {
        assertNull(TransformerExecutor.execute(null, validModel, "./test/output/model.xmi"));
    }

    @Test
    void testExecute_returnsNull_whenTransformerTypeIsEmpty() {
        assertNull(TransformerExecutor.execute("", validModel, "./test/output/model.xmi"));
    }

    @Test
    void testExecute_returnsNull_whenTransformerTypeIsBlank() {
        assertNull(TransformerExecutor.execute("   ", validModel, "./test/output/model.xmi"));
    }

    @Test
    void testExecute_returnsNull_whenInputModelIsNull() {
        assertNull(TransformerExecutor.execute("pim2gitlab", null, "./test/output/model.xmi"));
    }

    @Test
    void testExecute_returnsNull_whenOutputPathIsNull() {
        assertNull(TransformerExecutor.execute("pim2gitlab", validModel, null));
    }

    @Test
    void testExecute_returnsNull_whenOutputPathIsEmpty() {
        assertNull(TransformerExecutor.execute("pim2gitlab", validModel, ""));
    }

    @Test
    void testExecute_returnsNull_whenTransformerTypeIsUnknown() {
        // Exercises the IllegalArgumentException catch branch inside TransformerExecutor
        assertNull(TransformerExecutor.execute("unknown_type", validModel, "./test/output/model.xmi"));
    }
}
