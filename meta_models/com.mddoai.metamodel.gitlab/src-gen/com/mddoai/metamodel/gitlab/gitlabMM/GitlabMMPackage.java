/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMFactory
 * @model kind="package"
 * @generated
 */
public interface GitlabMMPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "gitlabMM";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.mddoai.com/mddoai/metamodel/gitlab";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "gitlabMM";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GitlabMMPackage eINSTANCE = com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl.init();

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.PipelineImpl <em>Pipeline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.PipelineImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getPipeline()
	 * @generated
	 */
	int PIPELINE = 0;

	/**
	 * The feature id for the '<em><b>Stages</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__STAGES = 0;

	/**
	 * The feature id for the '<em><b>Workflow</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__WORKFLOW = 1;

	/**
	 * The feature id for the '<em><b>Defaults</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__DEFAULTS = 2;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__VARIABLES = 3;

	/**
	 * The feature id for the '<em><b>Jobs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__JOBS = 4;

	/**
	 * The number of structural features of the '<em>Pipeline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Pipeline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.WorkflowImpl <em>Workflow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.WorkflowImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getWorkflow()
	 * @generated
	 */
	int WORKFLOW = 1;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW__RULES = 0;

	/**
	 * The number of structural features of the '<em>Workflow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Workflow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl <em>Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getDefault()
	 * @generated
	 */
	int DEFAULT = 2;

	/**
	 * The feature id for the '<em><b>Image</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__IMAGE = 0;

	/**
	 * The feature id for the '<em><b>Before Script</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__BEFORE_SCRIPT = 1;

	/**
	 * The feature id for the '<em><b>After Script</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__AFTER_SCRIPT = 2;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__SERVICES = 3;

	/**
	 * The feature id for the '<em><b>Cache</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__CACHE = 4;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__TAGS = 5;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__TIMEOUT = 6;

	/**
	 * The feature id for the '<em><b>Interruptible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__INTERRUPTIBLE = 7;

	/**
	 * The feature id for the '<em><b>Retry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__RETRY = 8;

	/**
	 * The number of structural features of the '<em>Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.VariableImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getVariable()
	 * @generated
	 */
	int VARIABLE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Expand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__EXPAND = 3;

	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl <em>Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getJob()
	 * @generated
	 */
	int JOB = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__NAME = 0;

	/**
	 * The feature id for the '<em><b>Stage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__STAGE = 1;

	/**
	 * The feature id for the '<em><b>Image</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__IMAGE = 2;

	/**
	 * The feature id for the '<em><b>Script</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__SCRIPT = 3;

	/**
	 * The feature id for the '<em><b>Before Script</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__BEFORE_SCRIPT = 4;

	/**
	 * The feature id for the '<em><b>After Script</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__AFTER_SCRIPT = 5;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__SERVICES = 6;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__VARIABLES = 7;

	/**
	 * The feature id for the '<em><b>Artifacts</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ARTIFACTS = 8;

	/**
	 * The feature id for the '<em><b>Cache</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__CACHE = 9;

	/**
	 * The feature id for the '<em><b>Needs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__NEEDS = 10;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__RULES = 11;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__TAGS = 12;

	/**
	 * The feature id for the '<em><b>Only</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ONLY = 13;

	/**
	 * The feature id for the '<em><b>Except</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__EXCEPT = 14;

	/**
	 * The feature id for the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__WHEN = 15;

	/**
	 * The feature id for the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ALLOW_FAILURE = 16;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__TIMEOUT = 17;

	/**
	 * The feature id for the '<em><b>Interruptible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__INTERRUPTIBLE = 18;

	/**
	 * The feature id for the '<em><b>Resource Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__RESOURCE_GROUP = 19;

	/**
	 * The feature id for the '<em><b>Retry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__RETRY = 20;

	/**
	 * The feature id for the '<em><b>Parallel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__PARALLEL = 21;

	/**
	 * The feature id for the '<em><b>Environment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ENVIRONMENT = 22;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__DEPENDENCIES = 23;

	/**
	 * The number of structural features of the '<em>Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_FEATURE_COUNT = 24;

	/**
	 * The number of operations of the '<em>Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ImageImpl <em>Image</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ImageImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getImage()
	 * @generated
	 */
	int IMAGE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Entrypoint</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__ENTRYPOINT = 1;

	/**
	 * The feature id for the '<em><b>Pull Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__PULL_POLICY = 2;

	/**
	 * The feature id for the '<em><b>Docker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__DOCKER = 3;

	/**
	 * The feature id for the '<em><b>Kubernetes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__KUBERNETES = 4;

	/**
	 * The number of structural features of the '<em>Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DockerOptionsImpl <em>Docker Options</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.DockerOptionsImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getDockerOptions()
	 * @generated
	 */
	int DOCKER_OPTIONS = 6;

	/**
	 * The feature id for the '<em><b>Platform</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_OPTIONS__PLATFORM = 0;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_OPTIONS__USER = 1;

	/**
	 * The number of structural features of the '<em>Docker Options</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_OPTIONS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Docker Options</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_OPTIONS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.KubernetesOptionsImpl <em>Kubernetes Options</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.KubernetesOptionsImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getKubernetesOptions()
	 * @generated
	 */
	int KUBERNETES_OPTIONS = 7;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KUBERNETES_OPTIONS__USER = 0;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KUBERNETES_OPTIONS__NAMESPACE = 1;

	/**
	 * The number of structural features of the '<em>Kubernetes Options</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KUBERNETES_OPTIONS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Kubernetes Options</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KUBERNETES_OPTIONS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ServiceImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getService()
	 * @generated
	 */
	int SERVICE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__ALIAS = 1;

	/**
	 * The feature id for the '<em><b>Entrypoint</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__ENTRYPOINT = 2;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__VARIABLES = 3;

	/**
	 * The feature id for the '<em><b>Pull Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__PULL_POLICY = 4;

	/**
	 * The feature id for the '<em><b>Docker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__DOCKER = 5;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactsImpl <em>Artifacts</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactsImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getArtifacts()
	 * @generated
	 */
	int ARTIFACTS = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACTS__NAME = 0;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACTS__PATHS = 1;

	/**
	 * The feature id for the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACTS__WHEN = 2;

	/**
	 * The feature id for the '<em><b>Expire In</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACTS__EXPIRE_IN = 3;

	/**
	 * The feature id for the '<em><b>Expose As</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACTS__EXPOSE_AS = 4;

	/**
	 * The feature id for the '<em><b>Untracked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACTS__UNTRACKED = 5;

	/**
	 * The feature id for the '<em><b>Exclude</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACTS__EXCLUDE = 6;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACTS__REPORTS = 7;

	/**
	 * The number of structural features of the '<em>Artifacts</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACTS_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>Artifacts</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACTS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactReportsImpl <em>Artifact Reports</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactReportsImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getArtifactReports()
	 * @generated
	 */
	int ARTIFACT_REPORTS = 10;

	/**
	 * The feature id for the '<em><b>Junit</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_REPORTS__JUNIT = 0;

	/**
	 * The feature id for the '<em><b>Coverage Report</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_REPORTS__COVERAGE_REPORT = 1;

	/**
	 * The feature id for the '<em><b>Dotenv</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_REPORTS__DOTENV = 2;

	/**
	 * The number of structural features of the '<em>Artifact Reports</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_REPORTS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Artifact Reports</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_REPORTS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CoverageReportImpl <em>Coverage Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.CoverageReportImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getCoverageReport()
	 * @generated
	 */
	int COVERAGE_REPORT = 11;

	/**
	 * The feature id for the '<em><b>Coverage Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COVERAGE_REPORT__COVERAGE_FORMAT = 0;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COVERAGE_REPORT__PATH = 1;

	/**
	 * The number of structural features of the '<em>Coverage Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COVERAGE_REPORT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Coverage Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COVERAGE_REPORT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheImpl <em>Cache</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getCache()
	 * @generated
	 */
	int CACHE = 12;

	/**
	 * The feature id for the '<em><b>Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__KEY = 0;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__PATHS = 1;

	/**
	 * The feature id for the '<em><b>Untracked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__UNTRACKED = 2;

	/**
	 * The feature id for the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__WHEN = 3;

	/**
	 * The number of structural features of the '<em>Cache</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Cache</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheKeyImpl <em>Cache Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheKeyImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getCacheKey()
	 * @generated
	 */
	int CACHE_KEY = 13;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_KEY__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Files</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_KEY__FILES = 1;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_KEY__PREFIX = 2;

	/**
	 * The number of structural features of the '<em>Cache Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_KEY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Cache Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_KEY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.NeedImpl <em>Need</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.NeedImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getNeed()
	 * @generated
	 */
	int NEED = 14;

	/**
	 * The feature id for the '<em><b>Job</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED__JOB = 0;

	/**
	 * The feature id for the '<em><b>Artifacts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED__ARTIFACTS = 1;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED__OPTIONAL = 2;

	/**
	 * The feature id for the '<em><b>Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED__PROJECT = 3;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED__REF = 4;

	/**
	 * The feature id for the '<em><b>Pipeline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED__PIPELINE = 5;

	/**
	 * The number of structural features of the '<em>Need</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Need</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.RuleImpl <em>Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.RuleImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getRule()
	 * @generated
	 */
	int RULE = 15;

	/**
	 * The feature id for the '<em><b>If Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__IF_CONDITION = 0;

	/**
	 * The feature id for the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__WHEN = 1;

	/**
	 * The feature id for the '<em><b>Changes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__CHANGES = 2;

	/**
	 * The feature id for the '<em><b>Exists</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__EXISTS = 3;

	/**
	 * The feature id for the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__ALLOW_FAILURE = 4;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__VARIABLES = 5;

	/**
	 * The number of structural features of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.RetryImpl <em>Retry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.RetryImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getRetry()
	 * @generated
	 */
	int RETRY = 16;

	/**
	 * The feature id for the '<em><b>Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY__MAX = 0;

	/**
	 * The feature id for the '<em><b>When</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY__WHEN = 1;

	/**
	 * The number of structural features of the '<em>Retry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Retry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ParallelImpl <em>Parallel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ParallelImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getParallel()
	 * @generated
	 */
	int PARALLEL = 17;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL__COUNT = 0;

	/**
	 * The feature id for the '<em><b>Matrix</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL__MATRIX = 1;

	/**
	 * The number of structural features of the '<em>Parallel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Parallel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.MatrixEntryImpl <em>Matrix Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.MatrixEntryImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getMatrixEntry()
	 * @generated
	 */
	int MATRIX_ENTRY = 18;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_ENTRY__VALUES = 1;

	/**
	 * The number of structural features of the '<em>Matrix Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Matrix Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.EnvironmentImpl <em>Environment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.EnvironmentImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getEnvironment()
	 * @generated
	 */
	int ENVIRONMENT = 19;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__URL = 1;

	/**
	 * The feature id for the '<em><b>On Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__ON_STOP = 2;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__ACTION = 3;

	/**
	 * The feature id for the '<em><b>Auto Stop In</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__AUTO_STOP_IN = 4;

	/**
	 * The feature id for the '<em><b>Kubernetes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT__KUBERNETES = 5;

	/**
	 * The number of structural features of the '<em>Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.WhenType <em>When Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.WhenType
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getWhenType()
	 * @generated
	 */
	int WHEN_TYPE = 20;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.ArtifactsWhenType <em>Artifacts When Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.ArtifactsWhenType
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getArtifactsWhenType()
	 * @generated
	 */
	int ARTIFACTS_WHEN_TYPE = 21;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.CacheWhenType <em>Cache When Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.CacheWhenType
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getCacheWhenType()
	 * @generated
	 */
	int CACHE_WHEN_TYPE = 22;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.PullPolicy <em>Pull Policy</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.PullPolicy
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getPullPolicy()
	 * @generated
	 */
	int PULL_POLICY = 23;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.EnvironmentAction <em>Environment Action</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.EnvironmentAction
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getEnvironmentAction()
	 * @generated
	 */
	int ENVIRONMENT_ACTION = 24;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.RetryWhenType <em>Retry When Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.RetryWhenType
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getRetryWhenType()
	 * @generated
	 */
	int RETRY_WHEN_TYPE = 25;

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Pipeline <em>Pipeline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pipeline</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Pipeline
	 * @generated
	 */
	EClass getPipeline();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Pipeline#getStages <em>Stages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Stages</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Pipeline#getStages()
	 * @see #getPipeline()
	 * @generated
	 */
	EAttribute getPipeline_Stages();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Pipeline#getWorkflow <em>Workflow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Workflow</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Pipeline#getWorkflow()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Workflow();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Pipeline#getDefaults <em>Defaults</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Defaults</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Pipeline#getDefaults()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Defaults();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Pipeline#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Pipeline#getVariables()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Variables();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Pipeline#getJobs <em>Jobs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Jobs</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Pipeline#getJobs()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Jobs();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Workflow <em>Workflow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workflow</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Workflow
	 * @generated
	 */
	EClass getWorkflow();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Workflow#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rules</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Workflow#getRules()
	 * @see #getWorkflow()
	 * @generated
	 */
	EReference getWorkflow_Rules();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Default</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Default
	 * @generated
	 */
	EClass getDefault();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getImage <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Image</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Default#getImage()
	 * @see #getDefault()
	 * @generated
	 */
	EReference getDefault_Image();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getBeforeScript <em>Before Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Before Script</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Default#getBeforeScript()
	 * @see #getDefault()
	 * @generated
	 */
	EAttribute getDefault_BeforeScript();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getAfterScript <em>After Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>After Script</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Default#getAfterScript()
	 * @see #getDefault()
	 * @generated
	 */
	EAttribute getDefault_AfterScript();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Services</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Default#getServices()
	 * @see #getDefault()
	 * @generated
	 */
	EReference getDefault_Services();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getCache <em>Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cache</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Default#getCache()
	 * @see #getDefault()
	 * @generated
	 */
	EReference getDefault_Cache();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Tags</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Default#getTags()
	 * @see #getDefault()
	 * @generated
	 */
	EAttribute getDefault_Tags();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Default#getTimeout()
	 * @see #getDefault()
	 * @generated
	 */
	EAttribute getDefault_Timeout();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getInterruptible <em>Interruptible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interruptible</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Default#getInterruptible()
	 * @see #getDefault()
	 * @generated
	 */
	EAttribute getDefault_Interruptible();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getRetry <em>Retry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Retry</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Default#getRetry()
	 * @see #getDefault()
	 * @generated
	 */
	EReference getDefault_Retry();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Variable
	 * @generated
	 */
	EClass getVariable();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Variable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Variable#getName()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Variable#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Variable#getValue()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Value();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Variable#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Variable#getDescription()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Description();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Variable#getExpand <em>Expand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expand</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Variable#getExpand()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Expand();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job <em>Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Job</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job
	 * @generated
	 */
	EClass getJob();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getName()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getStage <em>Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stage</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getStage()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Stage();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getImage <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Image</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getImage()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Image();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Script</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getScript()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Script();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getBeforeScript <em>Before Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Before Script</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getBeforeScript()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_BeforeScript();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getAfterScript <em>After Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>After Script</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getAfterScript()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_AfterScript();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Services</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getServices()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Services();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getVariables()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Variables();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getArtifacts <em>Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Artifacts</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getArtifacts()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Artifacts();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getCache <em>Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cache</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getCache()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Cache();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getNeeds <em>Needs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Needs</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getNeeds()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Needs();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rules</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getRules()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Rules();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Tags</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getTags()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Tags();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getOnly <em>Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Only</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getOnly()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Only();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getExcept <em>Except</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Except</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getExcept()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Except();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getWhen <em>When</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>When</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getWhen()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_When();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getAllowFailure <em>Allow Failure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allow Failure</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getAllowFailure()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_AllowFailure();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getTimeout <em>Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timeout</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getTimeout()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Timeout();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getInterruptible <em>Interruptible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interruptible</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getInterruptible()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Interruptible();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getResourceGroup <em>Resource Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Group</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getResourceGroup()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_ResourceGroup();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getRetry <em>Retry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Retry</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getRetry()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Retry();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getParallel <em>Parallel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Parallel</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getParallel()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Parallel();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getEnvironment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Environment</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getEnvironment()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Environment();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getDependencies <em>Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Dependencies</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getDependencies()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Dependencies();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Image <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Image
	 * @generated
	 */
	EClass getImage();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Image#getName()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Name();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getEntrypoint <em>Entrypoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Entrypoint</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Image#getEntrypoint()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Entrypoint();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getPullPolicy <em>Pull Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pull Policy</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Image#getPullPolicy()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_PullPolicy();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getDocker <em>Docker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Docker</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Image#getDocker()
	 * @see #getImage()
	 * @generated
	 */
	EReference getImage_Docker();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getKubernetes <em>Kubernetes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Kubernetes</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Image#getKubernetes()
	 * @see #getImage()
	 * @generated
	 */
	EReference getImage_Kubernetes();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.DockerOptions <em>Docker Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Docker Options</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.DockerOptions
	 * @generated
	 */
	EClass getDockerOptions();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.DockerOptions#getPlatform <em>Platform</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Platform</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.DockerOptions#getPlatform()
	 * @see #getDockerOptions()
	 * @generated
	 */
	EAttribute getDockerOptions_Platform();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.DockerOptions#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.DockerOptions#getUser()
	 * @see #getDockerOptions()
	 * @generated
	 */
	EAttribute getDockerOptions_User();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.KubernetesOptions <em>Kubernetes Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Kubernetes Options</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.KubernetesOptions
	 * @generated
	 */
	EClass getKubernetesOptions();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.KubernetesOptions#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.KubernetesOptions#getUser()
	 * @see #getKubernetesOptions()
	 * @generated
	 */
	EAttribute getKubernetesOptions_User();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.KubernetesOptions#getNamespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Namespace</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.KubernetesOptions#getNamespace()
	 * @see #getKubernetesOptions()
	 * @generated
	 */
	EAttribute getKubernetesOptions_Namespace();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Service <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Service
	 * @generated
	 */
	EClass getService();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Service#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Service#getName()
	 * @see #getService()
	 * @generated
	 */
	EAttribute getService_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Service#getAlias <em>Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alias</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Service#getAlias()
	 * @see #getService()
	 * @generated
	 */
	EAttribute getService_Alias();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Service#getEntrypoint <em>Entrypoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Entrypoint</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Service#getEntrypoint()
	 * @see #getService()
	 * @generated
	 */
	EAttribute getService_Entrypoint();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Service#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Service#getVariables()
	 * @see #getService()
	 * @generated
	 */
	EReference getService_Variables();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Service#getPullPolicy <em>Pull Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pull Policy</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Service#getPullPolicy()
	 * @see #getService()
	 * @generated
	 */
	EAttribute getService_PullPolicy();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Service#getDocker <em>Docker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Docker</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Service#getDocker()
	 * @see #getService()
	 * @generated
	 */
	EReference getService_Docker();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts <em>Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifacts</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifacts
	 * @generated
	 */
	EClass getArtifacts();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getName()
	 * @see #getArtifacts()
	 * @generated
	 */
	EAttribute getArtifacts_Name();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getPaths <em>Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Paths</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getPaths()
	 * @see #getArtifacts()
	 * @generated
	 */
	EAttribute getArtifacts_Paths();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getWhen <em>When</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>When</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getWhen()
	 * @see #getArtifacts()
	 * @generated
	 */
	EAttribute getArtifacts_When();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getExpireIn <em>Expire In</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expire In</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getExpireIn()
	 * @see #getArtifacts()
	 * @generated
	 */
	EAttribute getArtifacts_ExpireIn();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getExposeAs <em>Expose As</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expose As</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getExposeAs()
	 * @see #getArtifacts()
	 * @generated
	 */
	EAttribute getArtifacts_ExposeAs();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getUntracked <em>Untracked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Untracked</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getUntracked()
	 * @see #getArtifacts()
	 * @generated
	 */
	EAttribute getArtifacts_Untracked();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getExclude <em>Exclude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Exclude</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getExclude()
	 * @see #getArtifacts()
	 * @generated
	 */
	EAttribute getArtifacts_Exclude();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getReports <em>Reports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Reports</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifacts#getReports()
	 * @see #getArtifacts()
	 * @generated
	 */
	EReference getArtifacts_Reports();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports <em>Artifact Reports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact Reports</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports
	 * @generated
	 */
	EClass getArtifactReports();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports#getJunit <em>Junit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Junit</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports#getJunit()
	 * @see #getArtifactReports()
	 * @generated
	 */
	EAttribute getArtifactReports_Junit();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports#getCoverageReport <em>Coverage Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Coverage Report</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports#getCoverageReport()
	 * @see #getArtifactReports()
	 * @generated
	 */
	EReference getArtifactReports_CoverageReport();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports#getDotenv <em>Dotenv</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dotenv</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports#getDotenv()
	 * @see #getArtifactReports()
	 * @generated
	 */
	EAttribute getArtifactReports_Dotenv();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.CoverageReport <em>Coverage Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Coverage Report</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.CoverageReport
	 * @generated
	 */
	EClass getCoverageReport();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.CoverageReport#getCoverageFormat <em>Coverage Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Coverage Format</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.CoverageReport#getCoverageFormat()
	 * @see #getCoverageReport()
	 * @generated
	 */
	EAttribute getCoverageReport_CoverageFormat();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.CoverageReport#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.CoverageReport#getPath()
	 * @see #getCoverageReport()
	 * @generated
	 */
	EAttribute getCoverageReport_Path();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache <em>Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cache</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Cache
	 * @generated
	 */
	EClass getCache();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Key</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Cache#getKey()
	 * @see #getCache()
	 * @generated
	 */
	EReference getCache_Key();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#getPaths <em>Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Paths</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Cache#getPaths()
	 * @see #getCache()
	 * @generated
	 */
	EAttribute getCache_Paths();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#getUntracked <em>Untracked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Untracked</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Cache#getUntracked()
	 * @see #getCache()
	 * @generated
	 */
	EAttribute getCache_Untracked();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#getWhen <em>When</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>When</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Cache#getWhen()
	 * @see #getCache()
	 * @generated
	 */
	EAttribute getCache_When();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.CacheKey <em>Cache Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cache Key</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.CacheKey
	 * @generated
	 */
	EClass getCacheKey();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.CacheKey#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.CacheKey#getValue()
	 * @see #getCacheKey()
	 * @generated
	 */
	EAttribute getCacheKey_Value();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.CacheKey#getFiles <em>Files</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Files</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.CacheKey#getFiles()
	 * @see #getCacheKey()
	 * @generated
	 */
	EAttribute getCacheKey_Files();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.CacheKey#getPrefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefix</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.CacheKey#getPrefix()
	 * @see #getCacheKey()
	 * @generated
	 */
	EAttribute getCacheKey_Prefix();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need <em>Need</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Need</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Need
	 * @generated
	 */
	EClass getNeed();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#getJob <em>Job</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Job</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Need#getJob()
	 * @see #getNeed()
	 * @generated
	 */
	EAttribute getNeed_Job();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#getArtifacts <em>Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifacts</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Need#getArtifacts()
	 * @see #getNeed()
	 * @generated
	 */
	EAttribute getNeed_Artifacts();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#getOptional <em>Optional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Need#getOptional()
	 * @see #getNeed()
	 * @generated
	 */
	EAttribute getNeed_Optional();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#getProject <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Need#getProject()
	 * @see #getNeed()
	 * @generated
	 */
	EAttribute getNeed_Project();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#getRef <em>Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ref</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Need#getRef()
	 * @see #getNeed()
	 * @generated
	 */
	EAttribute getNeed_Ref();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#getPipeline <em>Pipeline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pipeline</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Need#getPipeline()
	 * @see #getNeed()
	 * @generated
	 */
	EAttribute getNeed_Pipeline();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Rule
	 * @generated
	 */
	EClass getRule();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule#getIfCondition <em>If Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>If Condition</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Rule#getIfCondition()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_IfCondition();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule#getWhen <em>When</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>When</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Rule#getWhen()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_When();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule#getChanges <em>Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Changes</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Rule#getChanges()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_Changes();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule#getExists <em>Exists</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Exists</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Rule#getExists()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_Exists();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule#getAllowFailure <em>Allow Failure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allow Failure</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Rule#getAllowFailure()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_AllowFailure();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Rule#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Rule#getVariables()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_Variables();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Retry <em>Retry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Retry</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Retry
	 * @generated
	 */
	EClass getRetry();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Retry#getMax <em>Max</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Retry#getMax()
	 * @see #getRetry()
	 * @generated
	 */
	EAttribute getRetry_Max();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Retry#getWhen <em>When</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>When</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Retry#getWhen()
	 * @see #getRetry()
	 * @generated
	 */
	EAttribute getRetry_When();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Parallel <em>Parallel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parallel</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Parallel
	 * @generated
	 */
	EClass getParallel();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Parallel#getCount <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Parallel#getCount()
	 * @see #getParallel()
	 * @generated
	 */
	EAttribute getParallel_Count();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Parallel#getMatrix <em>Matrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Matrix</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Parallel#getMatrix()
	 * @see #getParallel()
	 * @generated
	 */
	EReference getParallel_Matrix();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.MatrixEntry <em>Matrix Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Matrix Entry</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.MatrixEntry
	 * @generated
	 */
	EClass getMatrixEntry();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.MatrixEntry#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.MatrixEntry#getKey()
	 * @see #getMatrixEntry()
	 * @generated
	 */
	EAttribute getMatrixEntry_Key();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.MatrixEntry#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Values</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.MatrixEntry#getValues()
	 * @see #getMatrixEntry()
	 * @generated
	 */
	EAttribute getMatrixEntry_Values();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Environment
	 * @generated
	 */
	EClass getEnvironment();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Environment#getName()
	 * @see #getEnvironment()
	 * @generated
	 */
	EAttribute getEnvironment_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Environment#getUrl()
	 * @see #getEnvironment()
	 * @generated
	 */
	EAttribute getEnvironment_Url();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getOnStop <em>On Stop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Stop</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Environment#getOnStop()
	 * @see #getEnvironment()
	 * @generated
	 */
	EAttribute getEnvironment_OnStop();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Environment#getAction()
	 * @see #getEnvironment()
	 * @generated
	 */
	EAttribute getEnvironment_Action();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getAutoStopIn <em>Auto Stop In</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Auto Stop In</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Environment#getAutoStopIn()
	 * @see #getEnvironment()
	 * @generated
	 */
	EAttribute getEnvironment_AutoStopIn();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Environment#getKubernetes <em>Kubernetes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Kubernetes</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Environment#getKubernetes()
	 * @see #getEnvironment()
	 * @generated
	 */
	EReference getEnvironment_Kubernetes();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.gitlab.gitlabMM.WhenType <em>When Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>When Type</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.WhenType
	 * @generated
	 */
	EEnum getWhenType();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.gitlab.gitlabMM.ArtifactsWhenType <em>Artifacts When Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Artifacts When Type</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.ArtifactsWhenType
	 * @generated
	 */
	EEnum getArtifactsWhenType();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.gitlab.gitlabMM.CacheWhenType <em>Cache When Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Cache When Type</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.CacheWhenType
	 * @generated
	 */
	EEnum getCacheWhenType();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.gitlab.gitlabMM.PullPolicy <em>Pull Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Pull Policy</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.PullPolicy
	 * @generated
	 */
	EEnum getPullPolicy();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.gitlab.gitlabMM.EnvironmentAction <em>Environment Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Environment Action</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.EnvironmentAction
	 * @generated
	 */
	EEnum getEnvironmentAction();

	/**
	 * Returns the meta object for enum '{@link com.mddoai.metamodel.gitlab.gitlabMM.RetryWhenType <em>Retry When Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Retry When Type</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.RetryWhenType
	 * @generated
	 */
	EEnum getRetryWhenType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GitlabMMFactory getGitlabMMFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.PipelineImpl <em>Pipeline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.PipelineImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getPipeline()
		 * @generated
		 */
		EClass PIPELINE = eINSTANCE.getPipeline();

		/**
		 * The meta object literal for the '<em><b>Stages</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PIPELINE__STAGES = eINSTANCE.getPipeline_Stages();

		/**
		 * The meta object literal for the '<em><b>Workflow</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__WORKFLOW = eINSTANCE.getPipeline_Workflow();

		/**
		 * The meta object literal for the '<em><b>Defaults</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__DEFAULTS = eINSTANCE.getPipeline_Defaults();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__VARIABLES = eINSTANCE.getPipeline_Variables();

		/**
		 * The meta object literal for the '<em><b>Jobs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__JOBS = eINSTANCE.getPipeline_Jobs();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.WorkflowImpl <em>Workflow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.WorkflowImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getWorkflow()
		 * @generated
		 */
		EClass WORKFLOW = eINSTANCE.getWorkflow();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW__RULES = eINSTANCE.getWorkflow_Rules();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl <em>Default</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getDefault()
		 * @generated
		 */
		EClass DEFAULT = eINSTANCE.getDefault();

		/**
		 * The meta object literal for the '<em><b>Image</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFAULT__IMAGE = eINSTANCE.getDefault_Image();

		/**
		 * The meta object literal for the '<em><b>Before Script</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEFAULT__BEFORE_SCRIPT = eINSTANCE.getDefault_BeforeScript();

		/**
		 * The meta object literal for the '<em><b>After Script</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEFAULT__AFTER_SCRIPT = eINSTANCE.getDefault_AfterScript();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFAULT__SERVICES = eINSTANCE.getDefault_Services();

		/**
		 * The meta object literal for the '<em><b>Cache</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFAULT__CACHE = eINSTANCE.getDefault_Cache();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEFAULT__TAGS = eINSTANCE.getDefault_Tags();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEFAULT__TIMEOUT = eINSTANCE.getDefault_Timeout();

		/**
		 * The meta object literal for the '<em><b>Interruptible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEFAULT__INTERRUPTIBLE = eINSTANCE.getDefault_Interruptible();

		/**
		 * The meta object literal for the '<em><b>Retry</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFAULT__RETRY = eINSTANCE.getDefault_Retry();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.VariableImpl <em>Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.VariableImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getVariable()
		 * @generated
		 */
		EClass VARIABLE = eINSTANCE.getVariable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__NAME = eINSTANCE.getVariable_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__VALUE = eINSTANCE.getVariable_Value();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__DESCRIPTION = eINSTANCE.getVariable_Description();

		/**
		 * The meta object literal for the '<em><b>Expand</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__EXPAND = eINSTANCE.getVariable_Expand();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl <em>Job</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getJob()
		 * @generated
		 */
		EClass JOB = eINSTANCE.getJob();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__NAME = eINSTANCE.getJob_Name();

		/**
		 * The meta object literal for the '<em><b>Stage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__STAGE = eINSTANCE.getJob_Stage();

		/**
		 * The meta object literal for the '<em><b>Image</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__IMAGE = eINSTANCE.getJob_Image();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__SCRIPT = eINSTANCE.getJob_Script();

		/**
		 * The meta object literal for the '<em><b>Before Script</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__BEFORE_SCRIPT = eINSTANCE.getJob_BeforeScript();

		/**
		 * The meta object literal for the '<em><b>After Script</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__AFTER_SCRIPT = eINSTANCE.getJob_AfterScript();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__SERVICES = eINSTANCE.getJob_Services();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__VARIABLES = eINSTANCE.getJob_Variables();

		/**
		 * The meta object literal for the '<em><b>Artifacts</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__ARTIFACTS = eINSTANCE.getJob_Artifacts();

		/**
		 * The meta object literal for the '<em><b>Cache</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__CACHE = eINSTANCE.getJob_Cache();

		/**
		 * The meta object literal for the '<em><b>Needs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__NEEDS = eINSTANCE.getJob_Needs();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__RULES = eINSTANCE.getJob_Rules();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__TAGS = eINSTANCE.getJob_Tags();

		/**
		 * The meta object literal for the '<em><b>Only</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__ONLY = eINSTANCE.getJob_Only();

		/**
		 * The meta object literal for the '<em><b>Except</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__EXCEPT = eINSTANCE.getJob_Except();

		/**
		 * The meta object literal for the '<em><b>When</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__WHEN = eINSTANCE.getJob_When();

		/**
		 * The meta object literal for the '<em><b>Allow Failure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__ALLOW_FAILURE = eINSTANCE.getJob_AllowFailure();

		/**
		 * The meta object literal for the '<em><b>Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__TIMEOUT = eINSTANCE.getJob_Timeout();

		/**
		 * The meta object literal for the '<em><b>Interruptible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__INTERRUPTIBLE = eINSTANCE.getJob_Interruptible();

		/**
		 * The meta object literal for the '<em><b>Resource Group</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__RESOURCE_GROUP = eINSTANCE.getJob_ResourceGroup();

		/**
		 * The meta object literal for the '<em><b>Retry</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__RETRY = eINSTANCE.getJob_Retry();

		/**
		 * The meta object literal for the '<em><b>Parallel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__PARALLEL = eINSTANCE.getJob_Parallel();

		/**
		 * The meta object literal for the '<em><b>Environment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__ENVIRONMENT = eINSTANCE.getJob_Environment();

		/**
		 * The meta object literal for the '<em><b>Dependencies</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__DEPENDENCIES = eINSTANCE.getJob_Dependencies();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ImageImpl <em>Image</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ImageImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getImage()
		 * @generated
		 */
		EClass IMAGE = eINSTANCE.getImage();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__NAME = eINSTANCE.getImage_Name();

		/**
		 * The meta object literal for the '<em><b>Entrypoint</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__ENTRYPOINT = eINSTANCE.getImage_Entrypoint();

		/**
		 * The meta object literal for the '<em><b>Pull Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__PULL_POLICY = eINSTANCE.getImage_PullPolicy();

		/**
		 * The meta object literal for the '<em><b>Docker</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMAGE__DOCKER = eINSTANCE.getImage_Docker();

		/**
		 * The meta object literal for the '<em><b>Kubernetes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMAGE__KUBERNETES = eINSTANCE.getImage_Kubernetes();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DockerOptionsImpl <em>Docker Options</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.DockerOptionsImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getDockerOptions()
		 * @generated
		 */
		EClass DOCKER_OPTIONS = eINSTANCE.getDockerOptions();

		/**
		 * The meta object literal for the '<em><b>Platform</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCKER_OPTIONS__PLATFORM = eINSTANCE.getDockerOptions_Platform();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCKER_OPTIONS__USER = eINSTANCE.getDockerOptions_User();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.KubernetesOptionsImpl <em>Kubernetes Options</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.KubernetesOptionsImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getKubernetesOptions()
		 * @generated
		 */
		EClass KUBERNETES_OPTIONS = eINSTANCE.getKubernetesOptions();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KUBERNETES_OPTIONS__USER = eINSTANCE.getKubernetesOptions_User();

		/**
		 * The meta object literal for the '<em><b>Namespace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KUBERNETES_OPTIONS__NAMESPACE = eINSTANCE.getKubernetesOptions_Namespace();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ServiceImpl <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ServiceImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getService()
		 * @generated
		 */
		EClass SERVICE = eINSTANCE.getService();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE__NAME = eINSTANCE.getService_Name();

		/**
		 * The meta object literal for the '<em><b>Alias</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE__ALIAS = eINSTANCE.getService_Alias();

		/**
		 * The meta object literal for the '<em><b>Entrypoint</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE__ENTRYPOINT = eINSTANCE.getService_Entrypoint();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE__VARIABLES = eINSTANCE.getService_Variables();

		/**
		 * The meta object literal for the '<em><b>Pull Policy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE__PULL_POLICY = eINSTANCE.getService_PullPolicy();

		/**
		 * The meta object literal for the '<em><b>Docker</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE__DOCKER = eINSTANCE.getService_Docker();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactsImpl <em>Artifacts</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactsImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getArtifacts()
		 * @generated
		 */
		EClass ARTIFACTS = eINSTANCE.getArtifacts();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACTS__NAME = eINSTANCE.getArtifacts_Name();

		/**
		 * The meta object literal for the '<em><b>Paths</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACTS__PATHS = eINSTANCE.getArtifacts_Paths();

		/**
		 * The meta object literal for the '<em><b>When</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACTS__WHEN = eINSTANCE.getArtifacts_When();

		/**
		 * The meta object literal for the '<em><b>Expire In</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACTS__EXPIRE_IN = eINSTANCE.getArtifacts_ExpireIn();

		/**
		 * The meta object literal for the '<em><b>Expose As</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACTS__EXPOSE_AS = eINSTANCE.getArtifacts_ExposeAs();

		/**
		 * The meta object literal for the '<em><b>Untracked</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACTS__UNTRACKED = eINSTANCE.getArtifacts_Untracked();

		/**
		 * The meta object literal for the '<em><b>Exclude</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACTS__EXCLUDE = eINSTANCE.getArtifacts_Exclude();

		/**
		 * The meta object literal for the '<em><b>Reports</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACTS__REPORTS = eINSTANCE.getArtifacts_Reports();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactReportsImpl <em>Artifact Reports</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactReportsImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getArtifactReports()
		 * @generated
		 */
		EClass ARTIFACT_REPORTS = eINSTANCE.getArtifactReports();

		/**
		 * The meta object literal for the '<em><b>Junit</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_REPORTS__JUNIT = eINSTANCE.getArtifactReports_Junit();

		/**
		 * The meta object literal for the '<em><b>Coverage Report</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT_REPORTS__COVERAGE_REPORT = eINSTANCE.getArtifactReports_CoverageReport();

		/**
		 * The meta object literal for the '<em><b>Dotenv</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_REPORTS__DOTENV = eINSTANCE.getArtifactReports_Dotenv();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CoverageReportImpl <em>Coverage Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.CoverageReportImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getCoverageReport()
		 * @generated
		 */
		EClass COVERAGE_REPORT = eINSTANCE.getCoverageReport();

		/**
		 * The meta object literal for the '<em><b>Coverage Format</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COVERAGE_REPORT__COVERAGE_FORMAT = eINSTANCE.getCoverageReport_CoverageFormat();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COVERAGE_REPORT__PATH = eINSTANCE.getCoverageReport_Path();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheImpl <em>Cache</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getCache()
		 * @generated
		 */
		EClass CACHE = eINSTANCE.getCache();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CACHE__KEY = eINSTANCE.getCache_Key();

		/**
		 * The meta object literal for the '<em><b>Paths</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CACHE__PATHS = eINSTANCE.getCache_Paths();

		/**
		 * The meta object literal for the '<em><b>Untracked</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CACHE__UNTRACKED = eINSTANCE.getCache_Untracked();

		/**
		 * The meta object literal for the '<em><b>When</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CACHE__WHEN = eINSTANCE.getCache_When();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheKeyImpl <em>Cache Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheKeyImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getCacheKey()
		 * @generated
		 */
		EClass CACHE_KEY = eINSTANCE.getCacheKey();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CACHE_KEY__VALUE = eINSTANCE.getCacheKey_Value();

		/**
		 * The meta object literal for the '<em><b>Files</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CACHE_KEY__FILES = eINSTANCE.getCacheKey_Files();

		/**
		 * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CACHE_KEY__PREFIX = eINSTANCE.getCacheKey_Prefix();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.NeedImpl <em>Need</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.NeedImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getNeed()
		 * @generated
		 */
		EClass NEED = eINSTANCE.getNeed();

		/**
		 * The meta object literal for the '<em><b>Job</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NEED__JOB = eINSTANCE.getNeed_Job();

		/**
		 * The meta object literal for the '<em><b>Artifacts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NEED__ARTIFACTS = eINSTANCE.getNeed_Artifacts();

		/**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NEED__OPTIONAL = eINSTANCE.getNeed_Optional();

		/**
		 * The meta object literal for the '<em><b>Project</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NEED__PROJECT = eINSTANCE.getNeed_Project();

		/**
		 * The meta object literal for the '<em><b>Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NEED__REF = eINSTANCE.getNeed_Ref();

		/**
		 * The meta object literal for the '<em><b>Pipeline</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NEED__PIPELINE = eINSTANCE.getNeed_Pipeline();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.RuleImpl <em>Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.RuleImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getRule()
		 * @generated
		 */
		EClass RULE = eINSTANCE.getRule();

		/**
		 * The meta object literal for the '<em><b>If Condition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__IF_CONDITION = eINSTANCE.getRule_IfCondition();

		/**
		 * The meta object literal for the '<em><b>When</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__WHEN = eINSTANCE.getRule_When();

		/**
		 * The meta object literal for the '<em><b>Changes</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__CHANGES = eINSTANCE.getRule_Changes();

		/**
		 * The meta object literal for the '<em><b>Exists</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__EXISTS = eINSTANCE.getRule_Exists();

		/**
		 * The meta object literal for the '<em><b>Allow Failure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__ALLOW_FAILURE = eINSTANCE.getRule_AllowFailure();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__VARIABLES = eINSTANCE.getRule_Variables();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.RetryImpl <em>Retry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.RetryImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getRetry()
		 * @generated
		 */
		EClass RETRY = eINSTANCE.getRetry();

		/**
		 * The meta object literal for the '<em><b>Max</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RETRY__MAX = eINSTANCE.getRetry_Max();

		/**
		 * The meta object literal for the '<em><b>When</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RETRY__WHEN = eINSTANCE.getRetry_When();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ParallelImpl <em>Parallel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ParallelImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getParallel()
		 * @generated
		 */
		EClass PARALLEL = eINSTANCE.getParallel();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARALLEL__COUNT = eINSTANCE.getParallel_Count();

		/**
		 * The meta object literal for the '<em><b>Matrix</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARALLEL__MATRIX = eINSTANCE.getParallel_Matrix();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.MatrixEntryImpl <em>Matrix Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.MatrixEntryImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getMatrixEntry()
		 * @generated
		 */
		EClass MATRIX_ENTRY = eINSTANCE.getMatrixEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MATRIX_ENTRY__KEY = eINSTANCE.getMatrixEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MATRIX_ENTRY__VALUES = eINSTANCE.getMatrixEntry_Values();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.EnvironmentImpl <em>Environment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.EnvironmentImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getEnvironment()
		 * @generated
		 */
		EClass ENVIRONMENT = eINSTANCE.getEnvironment();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT__NAME = eINSTANCE.getEnvironment_Name();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT__URL = eINSTANCE.getEnvironment_Url();

		/**
		 * The meta object literal for the '<em><b>On Stop</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT__ON_STOP = eINSTANCE.getEnvironment_OnStop();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT__ACTION = eINSTANCE.getEnvironment_Action();

		/**
		 * The meta object literal for the '<em><b>Auto Stop In</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT__AUTO_STOP_IN = eINSTANCE.getEnvironment_AutoStopIn();

		/**
		 * The meta object literal for the '<em><b>Kubernetes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENVIRONMENT__KUBERNETES = eINSTANCE.getEnvironment_Kubernetes();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.WhenType <em>When Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.WhenType
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getWhenType()
		 * @generated
		 */
		EEnum WHEN_TYPE = eINSTANCE.getWhenType();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.ArtifactsWhenType <em>Artifacts When Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.ArtifactsWhenType
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getArtifactsWhenType()
		 * @generated
		 */
		EEnum ARTIFACTS_WHEN_TYPE = eINSTANCE.getArtifactsWhenType();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.CacheWhenType <em>Cache When Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.CacheWhenType
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getCacheWhenType()
		 * @generated
		 */
		EEnum CACHE_WHEN_TYPE = eINSTANCE.getCacheWhenType();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.PullPolicy <em>Pull Policy</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.PullPolicy
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getPullPolicy()
		 * @generated
		 */
		EEnum PULL_POLICY = eINSTANCE.getPullPolicy();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.EnvironmentAction <em>Environment Action</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.EnvironmentAction
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getEnvironmentAction()
		 * @generated
		 */
		EEnum ENVIRONMENT_ACTION = eINSTANCE.getEnvironmentAction();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.RetryWhenType <em>Retry When Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.RetryWhenType
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getRetryWhenType()
		 * @generated
		 */
		EEnum RETRY_WHEN_TYPE = eINSTANCE.getRetryWhenType();

	}

} //GitlabMMPackage
