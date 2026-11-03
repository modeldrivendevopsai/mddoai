package test.java.unit.java.mddoai.execution.atl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import main.java.mddoai.execution.atl.AtlExecutor;

/**
 * Pure-logic unit tests for {@link AtlExecutor#parseOutputModelName}, no EMF
 * or real execution involved (see the integration-tier AtlExecutorTest for
 * that). Real execution against real fixtures is proven separately.
 */
public class AtlExecutorTest {

    @Test
    public void parsesTheRealOutputModelNameFromRealAtlSource() {
        String atlSource = "-- @nsURI PIM=pimMM=http://x\n"
                + "-- @nsURI GitLabMM=gitlabMM=http://y\n"
                + "\n"
                + "module pim2gitlab;\n"
                + "create OUT : GitLabMM from IN : PIM;\n";

        assertEquals("GitLabMM", AtlExecutor.parseOutputModelName(atlSource));
    }

    @Test
    public void ignoresAMatchingConventionMentionedOnlyInsideAComment() {
        // A comment merely describing the convention ("create OUT : Foo
        // from IN : PIM;") must never be mistaken for the real declaration
        // - only the real one below, after the comment is stripped, counts.
        String atlSource = "-- e.g. create OUT : Foo from IN : PIM;\n"
                + "create OUT : RealTarget from IN : PIM;\n";

        assertEquals("RealTarget", AtlExecutor.parseOutputModelName(atlSource));
    }

    @Test
    public void missingDeclarationThrowsIllegalArgumentException() {
        String atlSource = "module noOutputDeclared; rule R { from s : PIM!X to t : PIM!Y () }";

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> AtlExecutor.parseOutputModelName(atlSource));
        assertEquals(true, e.getMessage().contains("output model name"));
    }
}
