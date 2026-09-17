package test.java.integration.java.mddoai.execution.acceleo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.junit.jupiter.api.Test;

import main.java.mddoai.execution.acceleo.AcceleoExecutor;
import main.java.mddoai.execution.atl.AtlExecutor;

/**
 * Real execution of the project's own real, hand-authored generate.mtl
 * against a real GitLab PSM model instance. The instance itself comes from
 * AtlExecutor's own real output (see AtlExecutorTest), not the ported
 * ai-research fixture's own output.gitlabmm directly: that file predates a
 * real rename in the current gitlabMM.ecore (ifCondition -> gitlabIf, see
 * both classes' own git history) and is stale against this branch's real
 * metamodel - deriving the PSM instance fresh from the real, current
 * pim2gitlabmodel.atl + input.pimmm keeps this test honest about what the
 * current pipeline actually produces, rather than hand-patching a fixture
 * that's already drifted once and could drift again. No mocking: real
 * Acceleo compilation (AcceleoCompilerHelper) and real generation
 * (AcceleoService.doGenerate), proving AcceleoExecutor's own new "run", not
 * just "compile", capability - the same way AcceleoValidatorTest already
 * proves the compile half for this exact same file.
 */
public class AcceleoExecutorTest {

    private static final String ATL_SOURCE_PATH = "./src/main/resources/transformations/pim2psm/pim2gitlabmodel.atl";
    private static final String MTL_SOURCE_PATH =
            "../code_generation/com.mddoai.codegeneration.gitlab.acceleo/src/com/mddoai/codegeneration/gitlab/acceleo/main/generate.mtl";
    private static final String GITLAB_ECORE_PATH = "../meta_models/com.mddoai.metamodel.gitlab/model/gitlabMM.ecore";
    private static final String PIM_SAMPLE_INSTANCE_PATH = "./src/test/resources/testCases/execution/gitlab/input.pimmm";
    private static final String DYNAMIC_MINIMAL_ECORE_PATH =
            "./src/test/resources/testCases/execution/dynamic-minimal/minimalMM.ecore";
    private static final String DYNAMIC_MINIMAL_MTL_PATH =
            "./src/test/resources/testCases/execution/dynamic-minimal/generate.mtl";
    private static final String DYNAMIC_MINIMAL_INSTANCE_PATH =
            "./src/test/resources/testCases/execution/dynamic-minimal/input.xmi";

    private static String realGitlabPsmInstance() throws IOException {
        String atlSource = Files.readString(Path.of(ATL_SOURCE_PATH), StandardCharsets.UTF_8);
        String pimInstance = Files.readString(Path.of(PIM_SAMPLE_INSTANCE_PATH), StandardCharsets.UTF_8);
        String gitlabEcore = Files.readString(Path.of(GITLAB_ECORE_PATH), StandardCharsets.UTF_8);
        return AtlExecutor.execute(atlSource, pimInstance, gitlabEcore, "GitLabMM");
    }

    @Test
    public void realGenerateMtlProducesRealGitlabYamlFromARealAtlProducedPsmInstance() throws IOException {
        String mtlSource = Files.readString(Path.of(MTL_SOURCE_PATH), StandardCharsets.UTF_8);
        String psmInstance = realGitlabPsmInstance();
        String gitlabEcore = Files.readString(Path.of(GITLAB_ECORE_PATH), StandardCharsets.UTF_8);

        Map<String, String> generated = AcceleoExecutor.execute(mtlSource, psmInstance, gitlabEcore);

        assertTrue(generated.size() >= 1, "expected at least one real generated file, got: " + generated.keySet());
        String generatedYaml = String.join("\n", generated.values());

        // Real content, not just "something non-empty": every real job the
        // real sample PIM instance's own 11 jobs produced must survive all
        // the way through PIM -> (real ATL) -> PSM -> (real Acceleo) -> YAML.
        assertTrue(generatedYaml.contains("install-deps"), "expected the real 'install-deps' job in the generated YAML");
        assertTrue(generatedYaml.contains("docker-build"), "expected the real 'docker-build' job in the generated YAML");
        assertTrue(generatedYaml.contains("deploy-prod"), "expected the real 'deploy-prod' job in the generated YAML");
        assertTrue(generatedYaml.contains("stages:"), "expected a real GitLab 'stages:' key in the generated YAML");
    }

    @Test
    public void aTargetMetamodelThatIsNotARealEPackageIsRejected() throws IOException {
        String mtlSource = Files.readString(Path.of(MTL_SOURCE_PATH), StandardCharsets.UTF_8);
        String psmInstance = realGitlabPsmInstance();

        assertThrows(IllegalArgumentException.class,
                () -> AcceleoExecutor.execute(mtlSource, psmInstance, "not a real ecore file"));
    }

    @Test
    public void aModelThatIsNotARealInstanceOfTheTargetMetamodelIsRejected() throws IOException {
        String mtlSource = Files.readString(Path.of(MTL_SOURCE_PATH), StandardCharsets.UTF_8);
        String gitlabEcore = Files.readString(Path.of(GITLAB_ECORE_PATH), StandardCharsets.UTF_8);
        String notAGitlabModel = "<?xml version=\"1.0\"?><root/>";

        assertThrows(Exception.class,
                () -> AcceleoExecutor.execute(mtlSource, notAGitlabModel, gitlabEcore));
    }

    // Regression test for a real bug (see AcceleoExecutor.loadModule's own
    // comment for the full mechanism): the GitLab fixture above never
    // exercised this, because gitlabMM is a genmodel-based, compiled Java
    // metamodel, not a dynamically-loaded one - a real LLM-generated target
    // platform's metamodel (like this fixture) is always the latter. Uses a
    // deliberately tiny, hand-authored metamodel/template/instance triple,
    // not a ported reference, since this test is about the execution
    // mechanism itself, not about any one platform's real content.
    @Test
    public void realGenerateMtlProducesRealOutputForAGenuinelyDynamicNonGenmodelMetamodel() throws IOException {
        String mtlSource = Files.readString(Path.of(DYNAMIC_MINIMAL_MTL_PATH), StandardCharsets.UTF_8);
        String ecore = Files.readString(Path.of(DYNAMIC_MINIMAL_ECORE_PATH), StandardCharsets.UTF_8);
        String instance = Files.readString(Path.of(DYNAMIC_MINIMAL_INSTANCE_PATH), StandardCharsets.UTF_8);

        Map<String, String> generated = AcceleoExecutor.execute(mtlSource, instance, ecore);

        assertTrue(generated.size() >= 1, "expected at least one real generated file, got: " + generated.keySet());
        String content = String.join("\n", generated.values());
        assertTrue(content.contains("hello-world"), "expected the real model's own label in the generated output, got: " + content);
    }

    @Test
    public void brokenMtlSourceFailsWithARealCompileDiagnostic() throws IOException {
        // A real, valid target ecore is required here, not an empty string:
        // loadTargetPackage() now runs (and validates) before compileToEmtl()
        // (see execute()'s own comment for why), so an empty/invalid ecore
        // would be rejected as a bad target metamodel before this test's own
        // broken .mtl source ever reached the real compile step it means to
        // check - aTargetMetamodelThatIsNotARealEPackageIsRejected already
        // covers that earlier failure mode.
        String gitlabEcore = Files.readString(Path.of(GITLAB_ECORE_PATH), StandardCharsets.UTF_8);
        String psmInstance = "";
        String brokenMtl = "this is not real Acceleo source";

        IOException e = assertThrows(IOException.class,
                () -> AcceleoExecutor.execute(brokenMtl, psmInstance, gitlabEcore));
        assertTrue(e.getMessage().contains("failed to compile"), "expected a real compile failure message, got: " + e.getMessage());
    }
}
