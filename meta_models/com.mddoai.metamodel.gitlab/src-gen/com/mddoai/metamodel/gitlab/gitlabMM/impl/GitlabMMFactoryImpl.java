/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
		case GitlabMMPackage.WORKFLOW:
			return createWorkflow();
		case GitlabMMPackage.DEFAULT:
			return createDefault();
		case GitlabMMPackage.VARIABLE:
			return createVariable();
		case GitlabMMPackage.JOB:
			return createJob();
		case GitlabMMPackage.IMAGE:
			return createImage();
		case GitlabMMPackage.DOCKER_OPTIONS:
			return createDockerOptions();
		case GitlabMMPackage.KUBERNETES_OPTIONS:
			return createKubernetesOptions();
		case GitlabMMPackage.SERVICE:
			return createService();
		case GitlabMMPackage.ARTIFACTS:
			return createArtifacts();
		case GitlabMMPackage.ARTIFACT_REPORTS:
			return createArtifactReports();
		case GitlabMMPackage.COVERAGE_REPORT:
			return createCoverageReport();
		case GitlabMMPackage.CACHE:
			return createCache();
		case GitlabMMPackage.CACHE_KEY:
			return createCacheKey();
		case GitlabMMPackage.NEED:
			return createNeed();
		case GitlabMMPackage.RULE:
			return createRule();
		case GitlabMMPackage.RETRY:
			return createRetry();
		case GitlabMMPackage.PARALLEL:
			return createParallel();
		case GitlabMMPackage.MATRIX_ENTRY:
			return createMatrixEntry();
		case GitlabMMPackage.ENVIRONMENT:
			return createEnvironment();
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
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case GitlabMMPackage.WHEN_TYPE:
			return createWhenTypeFromString(eDataType, initialValue);
		case GitlabMMPackage.ARTIFACTS_WHEN_TYPE:
			return createArtifactsWhenTypeFromString(eDataType, initialValue);
		case GitlabMMPackage.CACHE_WHEN_TYPE:
			return createCacheWhenTypeFromString(eDataType, initialValue);
		case GitlabMMPackage.PULL_POLICY:
			return createPullPolicyFromString(eDataType, initialValue);
		case GitlabMMPackage.ENVIRONMENT_ACTION:
			return createEnvironmentActionFromString(eDataType, initialValue);
		case GitlabMMPackage.RETRY_WHEN_TYPE:
			return createRetryWhenTypeFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case GitlabMMPackage.WHEN_TYPE:
			return convertWhenTypeToString(eDataType, instanceValue);
		case GitlabMMPackage.ARTIFACTS_WHEN_TYPE:
			return convertArtifactsWhenTypeToString(eDataType, instanceValue);
		case GitlabMMPackage.CACHE_WHEN_TYPE:
			return convertCacheWhenTypeToString(eDataType, instanceValue);
		case GitlabMMPackage.PULL_POLICY:
			return convertPullPolicyToString(eDataType, instanceValue);
		case GitlabMMPackage.ENVIRONMENT_ACTION:
			return convertEnvironmentActionToString(eDataType, instanceValue);
		case GitlabMMPackage.RETRY_WHEN_TYPE:
			return convertRetryWhenTypeToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
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
	public DockerOptions createDockerOptions() {
		DockerOptionsImpl dockerOptions = new DockerOptionsImpl();
		return dockerOptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public KubernetesOptions createKubernetesOptions() {
		KubernetesOptionsImpl kubernetesOptions = new KubernetesOptionsImpl();
		return kubernetesOptions;
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
	public Artifacts createArtifacts() {
		ArtifactsImpl artifacts = new ArtifactsImpl();
		return artifacts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ArtifactReports createArtifactReports() {
		ArtifactReportsImpl artifactReports = new ArtifactReportsImpl();
		return artifactReports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CoverageReport createCoverageReport() {
		CoverageReportImpl coverageReport = new CoverageReportImpl();
		return coverageReport;
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
	public CacheKey createCacheKey() {
		CacheKeyImpl cacheKey = new CacheKeyImpl();
		return cacheKey;
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
	public Rule createRule() {
		RuleImpl rule = new RuleImpl();
		return rule;
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
	public MatrixEntry createMatrixEntry() {
		MatrixEntryImpl matrixEntry = new MatrixEntryImpl();
		return matrixEntry;
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
	public WhenType createWhenTypeFromString(EDataType eDataType, String initialValue) {
		WhenType result = WhenType.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWhenTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArtifactsWhenType createArtifactsWhenTypeFromString(EDataType eDataType, String initialValue) {
		ArtifactsWhenType result = ArtifactsWhenType.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertArtifactsWhenTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CacheWhenType createCacheWhenTypeFromString(EDataType eDataType, String initialValue) {
		CacheWhenType result = CacheWhenType.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCacheWhenTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PullPolicy createPullPolicyFromString(EDataType eDataType, String initialValue) {
		PullPolicy result = PullPolicy.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPullPolicyToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentAction createEnvironmentActionFromString(EDataType eDataType, String initialValue) {
		EnvironmentAction result = EnvironmentAction.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEnvironmentActionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RetryWhenType createRetryWhenTypeFromString(EDataType eDataType, String initialValue) {
		RetryWhenType result = RetryWhenType.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRetryWhenTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
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
