package test.java.integration.java.mddoai.execution.atl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import main.java.mddoai.execution.atl.AtlExecutor;

/**
 * Real execution of the project's own real, hand-authored
 * pim2gitlabmodel.atl against a real, rich PIM model instance (11 jobs,
 * matrix builds, triggers, caches, artifacts - exercising all of MDDOAI's
 * own PIM concepts, not a trivial one-class mock). No mocking: real ATL
 * compilation (AtlStandaloneCompiler) and real execution (EMFVMLauncher),
 * proving AtlExecutor's own new "run", not just "compile", capability -
 * the same way AtlValidatorTest already proves the compile half for this
 * exact same file.
 */
public class AtlExecutorTest {

    private static final String ATL_SOURCE_PATH = "./src/main/resources/transformations/pim2psm/pim2gitlabmodel.atl";
    private static final String GITLAB_ECORE_PATH = "../meta_models/com.mddoai.metamodel.gitlab/model/gitlabMM.ecore";
    // A real, rich PIM model instance, this test's own fixture, also read
    // directly (via a Docker bind mount to this same real file) by the AI
    // pipeline's own generation stage at runtime, a temporary stand-in
    // until a real SWArch-driven PIM extraction replaces it there.
    private static final String PIM_SAMPLE_INSTANCE_PATH =
            "./src/test/resources/testCases/execution/gitlab/input.pimmm";

    @Test
    public void realPim2GitlabAtlProducesARealGitlabPipelineFromTheRealSamplePim() throws IOException {
        String atlSource = Files.readString(Path.of(ATL_SOURCE_PATH), StandardCharsets.UTF_8);
        String pimInstance = Files.readString(Path.of(PIM_SAMPLE_INSTANCE_PATH), StandardCharsets.UTF_8);
        String gitlabEcore = Files.readString(Path.of(GITLAB_ECORE_PATH), StandardCharsets.UTF_8);

        String outputXmi = AtlExecutor.execute(atlSource, pimInstance, gitlabEcore, "GitLabMM");

        assertTrue(outputXmi.contains("gitlabMM:Pipeline"),
                "expected a real gitlabMM:Pipeline root element, got:\n" + outputXmi);
        // Real content actually carried over from the real sample PIM's own
        // 11 jobs (see input.pimmm's own job names), not just a
        // well-formed-but-empty output.
        assertTrue(outputXmi.contains("install-deps"), "expected the real 'install-deps' job to survive the transformation");
        assertTrue(outputXmi.contains("docker-build"), "expected the real 'docker-build' job to survive the transformation");
        assertTrue(outputXmi.contains("deploy-prod"), "expected the real 'deploy-prod' job to survive the transformation");
    }

    @Test
    public void aTargetMetamodelThatIsNotARealEPackageIsRejected() throws IOException {
        String atlSource = Files.readString(Path.of(ATL_SOURCE_PATH), StandardCharsets.UTF_8);
        String pimInstance = Files.readString(Path.of(PIM_SAMPLE_INSTANCE_PATH), StandardCharsets.UTF_8);

        assertThrows(IllegalArgumentException.class,
                () -> AtlExecutor.execute(atlSource, pimInstance, "not a real ecore file", "GitLabMM"));
    }

    @Test
    public void aModelThatIsNotARealPimPipelineIsRejected() throws IOException {
        String atlSource = Files.readString(Path.of(ATL_SOURCE_PATH), StandardCharsets.UTF_8);
        String gitlabEcore = Files.readString(Path.of(GITLAB_ECORE_PATH), StandardCharsets.UTF_8);
        String notAPimModel = "<?xml version=\"1.0\"?><root/>";

        assertThrows(Exception.class,
                () -> AtlExecutor.execute(atlSource, notAPimModel, gitlabEcore, "GitLabMM"));
    }

    @Test
    public void aRealAtlRuntimeFailureIsReportedCleanlyNotThrownRaw() throws IOException {
        // A real, genuine LLM-generated ATL that compiled clean (validator-
        // agent's own check passed it) but crashed EMFVM at real execution
        // time with org.eclipse.m2m.atl.engine.emfvm.VMException: "The
        // class 'Trigger' is not a valid classifier" - PIM!Trigger is
        // abstract (see pimMM.ecore), and this rule set tries to
        // instantiate it directly. Before AtlExecutor's own fix, this raw
        // ATLExecutionException propagated uncaught out of transform(),
        // and printing it via AtlExecutorCli's own catch-all crashed AGAIN
        // with a real NPE (VMException's own printStackTrace() override
        // chokes on the same invalid classifier) - confirmed for real
        // against a live Docker run, not a hypothetical.
        String atlSource = Files.readString(
                Path.of("./src/test/resources/testCases/execution/generic-cicd/pim2genericcicd-abstract-trigger.atl"),
                StandardCharsets.UTF_8);
        String pimInstance = Files.readString(Path.of(PIM_SAMPLE_INSTANCE_PATH), StandardCharsets.UTF_8);
        String targetEcore = Files.readString(
                Path.of("./src/test/resources/testCases/execution/generic-cicd/genericCICDMM.ecore"),
                StandardCharsets.UTF_8);

        IOException e = assertThrows(IOException.class,
                () -> AtlExecutor.execute(atlSource, pimInstance, targetEcore, "GenericCICDMM"));
        assertTrue(e.getMessage().contains("ATL runtime failure"),
                "expected a real, clean runtime-failure message, got: " + e.getMessage());
        assertTrue(e.getMessage().contains("Trigger"),
                "expected the real ATL engine's own diagnostic to survive, got: " + e.getMessage());
    }

    @Test
    public void brokenAtlSourceFailsWithARealCompileDiagnostic() {
        String pimInstance = "";
        String gitlabEcore = "";
        String brokenAtl = "this is not real ATL source";

        IOException e = assertThrows(IOException.class,
                () -> AtlExecutor.execute(brokenAtl, pimInstance, gitlabEcore, "GitLabMM"));
        assertTrue(e.getMessage().contains("failed to compile"), "expected a real compile failure message, got: " + e.getMessage());
    }
}
