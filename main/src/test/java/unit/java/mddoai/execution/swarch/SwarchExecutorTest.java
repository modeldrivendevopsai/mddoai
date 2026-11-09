package test.java.unit.java.mddoai.execution.swarch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import main.java.mddoai.execution.swarch.SwarchExecutor;

/**
 * Pure-logic unit test for the check SwarchExecutor makes before anything is
 * compiled or loaded; no EMF or real execution involved (see the
 * integration-tier SwarchExecutorTest for that).
 */
public class SwarchExecutorTest {

    @Test
    public void aTransformationThatNamesItsInputOtherwiseIsRefusedWithAClearMessage() {
        // Refused before anything is compiled or loaded: the transformer binds
        // the input model to SWArchMM, so any other name could never run.
        String atlSource = "-- create OUT : PIMMM from IN : SWArchMM;\n"
                + "create OUT : PIMMM from IN : SWARCH;\n";

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> SwarchExecutor.execute(atlSource, "<not read/>"));
        assertEquals(true, e.getMessage().contains("SWArchMM"));
        assertEquals(true, e.getMessage().contains("found: SWARCH"));
    }
}
