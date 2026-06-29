/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GitlabMMFactoryImpl extends EFactoryImpl implements GitlabMMFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GitlabMMFactory init() {
		try {
			GitlabMMFactory theGitlabMMFactory = (GitlabMMFactory) EPackage.Registry.INSTANCE
					.getEFactory(GitlabMMPackage.eNS_URI);
			if (theGitlabMMFactory != null) {
				return theGitlabMMFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new GitlabMMFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GitlabMMFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case GitlabMMPackage.PIPELINE:
			return createPipeline();
		case GitlabMMPackage.JOB:
			return createJob();
		case GitlabMMPackage.ARTIFACT:
			return createArtifact();
		case GitlabMMPackage.REPORT:
			return createReport();
		case GitlabMMPackage.COMMAND:
			return createCommand();
		case GitlabMMPackage.BEFORE_SCRIPT:
			return createBeforeScript();
		case GitlabMMPackage.SCRIPT:
			return createScript();
		case GitlabMMPackage.TAGS:
			return createTags();
		case GitlabMMPackage.TAG:
			return createTag();
		case GitlabMMPackage.ONLY:
			return createOnly();
		case GitlabMMPackage.ONLY_BRANCHES:
			return createOnlyBranches();
		case GitlabMMPackage.DEPENDENCIES:
			return createDependencies();
		case GitlabMMPackage.DEPENDENCY:
			return createDependency();
		case GitlabMMPackage.VARIABLES:
			return createVariables();
		case GitlabMMPackage.VARIABLE:
			return createVariable();
		case GitlabMMPackage.PATH:
			return createPath();
		case GitlabMMPackage.GITLAB_RULE:
			return createGitlabRule();
		case GitlabMMPackage.WORKFLOW:
			return createWorkflow();
		case GitlabMMPackage.IMAGE:
			return createImage();
		case GitlabMMPackage.DOCKER:
			return createDocker();
		case GitlabMMPackage.KUBERNETES:
			return createKubernetes();
		case GitlabMMPackage.SERVICE:
			return createService();
		case GitlabMMPackage.CACHE:
			return createCache();
		case GitlabMMPackage.KEY:
			return createKey();
		case GitlabMMPackage.NEED:
			return createNeed();
		case GitlabMMPackage.ENVIRONMENT:
			return createEnvironment();
		case GitlabMMPackage.AFTER_SCRIPT:
			return createAfterScript();
		case GitlabMMPackage.DEFAULT:
			return createDefault();
		case GitlabMMPackage.RETRY:
			return createRetry();
		case GitlabMMPackage.AXIS:
			return createAxis();
		case GitlabMMPackage.MATRIX:
			return createMatrix();
		case GitlabMMPackage.PARALLEL:
			return createParallel();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Pipeline createPipeline() {
		PipelineImpl pipeline = new PipelineImpl();
		return pipeline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Job createJob() {
		JobImpl job = new JobImpl();
		return job;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Artifact createArtifact() {
		ArtifactImpl artifact = new ArtifactImpl();
		return artifact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Report createReport() {
		ReportImpl report = new ReportImpl();
		return report;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Command createCommand() {
		CommandImpl command = new CommandImpl();
		return command;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BeforeScript createBeforeScript() {
		BeforeScriptImpl beforeScript = new BeforeScriptImpl();
		return beforeScript;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Script createScript() {
		ScriptImpl script = new ScriptImpl();
		return script;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Tags createTags() {
		TagsImpl tags = new TagsImpl();
		return tags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Tag createTag() {
		TagImpl tag = new TagImpl();
		return tag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Only createOnly() {
		OnlyImpl only = new OnlyImpl();
		return only;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OnlyBranches createOnlyBranches() {
		OnlyBranchesImpl onlyBranches = new OnlyBranchesImpl();
		return onlyBranches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Dependencies createDependencies() {
		DependenciesImpl dependencies = new DependenciesImpl();
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Dependency createDependency() {
		DependencyImpl dependency = new DependencyImpl();
		return dependency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Variables createVariables() {
		VariablesImpl variables = new VariablesImpl();
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Variable createVariable() {
		VariableImpl variable = new VariableImpl();
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Path createPath() {
		PathImpl path = new PathImpl();
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GitlabRule createGitlabRule() {
		GitlabRuleImpl gitlabRule = new GitlabRuleImpl();
		return gitlabRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Workflow createWorkflow() {
		WorkflowImpl workflow = new WorkflowImpl();
		return workflow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Image createImage() {
		ImageImpl image = new ImageImpl();
		return image;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Docker createDocker() {
		DockerImpl docker = new DockerImpl();
		return docker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Kubernetes createKubernetes() {
		KubernetesImpl kubernetes = new KubernetesImpl();
		return kubernetes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Service createService() {
		ServiceImpl service = new ServiceImpl();
		return service;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Cache createCache() {
		CacheImpl cache = new CacheImpl();
		return cache;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Key createKey() {
		KeyImpl key = new KeyImpl();
		return key;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Need createNeed() {
		NeedImpl need = new NeedImpl();
		return need;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Environment createEnvironment() {
		EnvironmentImpl environment = new EnvironmentImpl();
		return environment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AfterScript createAfterScript() {
		AfterScriptImpl afterScript = new AfterScriptImpl();
		return afterScript;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Default createDefault() {
		DefaultImpl default_ = new DefaultImpl();
		return default_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Retry createRetry() {
		RetryImpl retry = new RetryImpl();
		return retry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Axis createAxis() {
		AxisImpl axis = new AxisImpl();
		return axis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Matrix createMatrix() {
		MatrixImpl matrix = new MatrixImpl();
		return matrix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Parallel createParallel() {
		ParallelImpl parallel = new ParallelImpl();
		return parallel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GitlabMMPackage getGitlabMMPackage() {
		return (GitlabMMPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static GitlabMMPackage getPackage() {
		return GitlabMMPackage.eINSTANCE;
	}

} //GitlabMMFactoryImpl
