/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
	 * The feature id for the '<em><b>Jobs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__JOBS = 1;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__VARIABLES = 2;

	/**
	 * The feature id for the '<em><b>Workflow</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__WORKFLOW = 3;

	/**
	 * The feature id for the '<em><b>Default</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE__DEFAULT = 4;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl <em>Job</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.JobImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getJob()
	 * @generated
	 */
	int JOB = 1;

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
	 * The feature id for the '<em><b>Artifacts</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ARTIFACTS = 2;

	/**
	 * The feature id for the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__SCRIPT = 3;

	/**
	 * The feature id for the '<em><b>Before Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__BEFORE_SCRIPT = 4;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__TAGS = 5;

	/**
	 * The feature id for the '<em><b>Only</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ONLY = 6;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__DEPENDENCIES = 7;

	/**
	 * The feature id for the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__WHEN = 8;

	/**
	 * The feature id for the '<em><b>Image</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__IMAGE = 9;

	/**
	 * The feature id for the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ALLOW_FAILURE = 10;

	/**
	 * The feature id for the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__TIMEOUT = 11;

	/**
	 * The feature id for the '<em><b>Interruptible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__INTERRUPTIBLE = 12;

	/**
	 * The feature id for the '<em><b>Resource Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__RESOURCE_GROUP = 13;

	/**
	 * The feature id for the '<em><b>After Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__AFTER_SCRIPT = 14;

	/**
	 * The feature id for the '<em><b>Cache</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__CACHE = 15;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__RULES = 16;

	/**
	 * The feature id for the '<em><b>Needs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__NEEDS = 17;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__SERVICES = 18;

	/**
	 * The feature id for the '<em><b>Environment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ENVIRONMENT = 19;

	/**
	 * The feature id for the '<em><b>Retry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__RETRY = 20;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__VARIABLES = 21;

	/**
	 * The feature id for the '<em><b>Parallel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__PARALLEL = 22;

	/**
	 * The number of structural features of the '<em>Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_FEATURE_COUNT = 23;

	/**
	 * The number of operations of the '<em>Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactImpl <em>Artifact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getArtifact()
	 * @generated
	 */
	int ARTIFACT = 2;

	/**
	 * The feature id for the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__WHEN = 0;

	/**
	 * The feature id for the '<em><b>Reports</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__REPORTS = 1;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PATHS = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__NAME = 3;

	/**
	 * The feature id for the '<em><b>Expire In</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__EXPIRE_IN = 4;

	/**
	 * The feature id for the '<em><b>Expose As</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__EXPOSE_AS = 5;

	/**
	 * The feature id for the '<em><b>Untracked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__UNTRACKED = 6;

	/**
	 * The feature id for the '<em><b>Exclude</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__EXCLUDE = 7;

	/**
	 * The number of structural features of the '<em>Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ReportImpl <em>Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ReportImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getReport()
	 * @generated
	 */
	int REPORT = 3;

	/**
	 * The feature id for the '<em><b>Junit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__JUNIT = 0;

	/**
	 * The feature id for the '<em><b>Coverage Report</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__COVERAGE_REPORT = 1;

	/**
	 * The feature id for the '<em><b>Dotenv</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__DOTENV = 2;

	/**
	 * The feature id for the '<em><b>Cobertura</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT__COBERTURA = 3;

	/**
	 * The number of structural features of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CommandImpl <em>Command</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.CommandImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getCommand()
	 * @generated
	 */
	int COMMAND = 4;

	/**
	 * The feature id for the '<em><b>Command</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND__COMMAND = 0;

	/**
	 * The number of structural features of the '<em>Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Command</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMAND_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.BeforeScriptImpl <em>Before Script</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.BeforeScriptImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getBeforeScript()
	 * @generated
	 */
	int BEFORE_SCRIPT = 5;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEFORE_SCRIPT__COMMANDS = 0;

	/**
	 * The number of structural features of the '<em>Before Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEFORE_SCRIPT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Before Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEFORE_SCRIPT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ScriptImpl <em>Script</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ScriptImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getScript()
	 * @generated
	 */
	int SCRIPT = 6;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT__COMMANDS = 0;

	/**
	 * The number of structural features of the '<em>Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.TagsImpl <em>Tags</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.TagsImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getTags()
	 * @generated
	 */
	int TAGS = 7;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGS__TAGS = 0;

	/**
	 * The number of structural features of the '<em>Tags</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGS_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Tags</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.TagImpl <em>Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.TagImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getTag()
	 * @generated
	 */
	int TAG = 8;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__TAG = 0;

	/**
	 * The number of structural features of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.OnlyImpl <em>Only</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.OnlyImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getOnly()
	 * @generated
	 */
	int ONLY = 9;

	/**
	 * The feature id for the '<em><b>Branches</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONLY__BRANCHES = 0;

	/**
	 * The number of structural features of the '<em>Only</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONLY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Only</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONLY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.OnlyBranchesImpl <em>Only Branches</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.OnlyBranchesImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getOnlyBranches()
	 * @generated
	 */
	int ONLY_BRANCHES = 10;

	/**
	 * The feature id for the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONLY_BRANCHES__BRANCH = 0;

	/**
	 * The number of structural features of the '<em>Only Branches</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONLY_BRANCHES_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Only Branches</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONLY_BRANCHES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DependenciesImpl <em>Dependencies</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.DependenciesImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getDependencies()
	 * @generated
	 */
	int DEPENDENCIES = 11;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCIES__DEPENDENCIES = 0;

	/**
	 * The number of structural features of the '<em>Dependencies</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCIES_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Dependencies</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCIES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DependencyImpl <em>Dependency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.DependencyImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getDependency()
	 * @generated
	 */
	int DEPENDENCY = 12;

	/**
	 * The feature id for the '<em><b>Dependency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__DEPENDENCY = 0;

	/**
	 * The number of structural features of the '<em>Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.VariablesImpl <em>Variables</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.VariablesImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getVariables()
	 * @generated
	 */
	int VARIABLES = 13;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLES__VARIABLES = 0;

	/**
	 * The number of structural features of the '<em>Variables</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLES_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Variables</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.VariableImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getVariable()
	 * @generated
	 */
	int VARIABLE = 14;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.PathImpl <em>Path</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.PathImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getPath()
	 * @generated
	 */
	int PATH = 15;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__PATH = 0;

	/**
	 * The number of structural features of the '<em>Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabRuleImpl <em>Gitlab Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabRuleImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getGitlabRule()
	 * @generated
	 */
	int GITLAB_RULE = 16;

	/**
	 * The feature id for the '<em><b>Gitlab If</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITLAB_RULE__GITLAB_IF = 0;

	/**
	 * The feature id for the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITLAB_RULE__WHEN = 1;

	/**
	 * The feature id for the '<em><b>Changes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITLAB_RULE__CHANGES = 2;

	/**
	 * The feature id for the '<em><b>Exists</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITLAB_RULE__EXISTS = 3;

	/**
	 * The feature id for the '<em><b>Allow Failure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITLAB_RULE__ALLOW_FAILURE = 4;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITLAB_RULE__VARIABLES = 5;

	/**
	 * The number of structural features of the '<em>Gitlab Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITLAB_RULE_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Gitlab Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GITLAB_RULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.WorkflowImpl <em>Workflow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.WorkflowImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getWorkflow()
	 * @generated
	 */
	int WORKFLOW = 17;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ImageImpl <em>Image</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ImageImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getImage()
	 * @generated
	 */
	int IMAGE = 18;

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
	 * The feature id for the '<em><b>Docker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__DOCKER = 2;

	/**
	 * The feature id for the '<em><b>Kubernetes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__KUBERNETES = 3;

	/**
	 * The feature id for the '<em><b>Pull Policy</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__PULL_POLICY = 4;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DockerImpl <em>Docker</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.DockerImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getDocker()
	 * @generated
	 */
	int DOCKER = 19;

	/**
	 * The feature id for the '<em><b>Platform</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER__PLATFORM = 0;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER__USER = 1;

	/**
	 * The number of structural features of the '<em>Docker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Docker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCKER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.KubernetesImpl <em>Kubernetes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.KubernetesImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getKubernetes()
	 * @generated
	 */
	int KUBERNETES = 20;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KUBERNETES__USER = 0;

	/**
	 * The number of structural features of the '<em>Kubernetes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KUBERNETES_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Kubernetes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KUBERNETES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ServiceImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getService()
	 * @generated
	 */
	int SERVICE = 21;

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
	 * The feature id for the '<em><b>Commands</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__COMMANDS = 3;

	/**
	 * The feature id for the '<em><b>Docker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__DOCKER = 4;

	/**
	 * The feature id for the '<em><b>Kubernetes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__KUBERNETES = 5;

	/**
	 * The feature id for the '<em><b>Pull Policy</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__PULL_POLICY = 6;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheImpl <em>Cache</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getCache()
	 * @generated
	 */
	int CACHE = 22;

	/**
	 * The feature id for the '<em><b>Key</b></em>' reference.
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
	 * The feature id for the '<em><b>Unprotect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__UNPROTECT = 3;

	/**
	 * The feature id for the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__WHEN = 4;

	/**
	 * The number of structural features of the '<em>Cache</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Cache</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.KeyImpl <em>Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.KeyImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getKey()
	 * @generated
	 */
	int KEY = 23;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Files</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY__FILES = 1;

	/**
	 * The feature id for the '<em><b>Files Commits</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY__FILES_COMMITS = 2;

	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY__PREFIX = 3;

	/**
	 * The number of structural features of the '<em>Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.NeedImpl <em>Need</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.NeedImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getNeed()
	 * @generated
	 */
	int NEED = 24;

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
	 * The feature id for the '<em><b>Project</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED__PROJECT = 2;

	/**
	 * The feature id for the '<em><b>Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED__REF = 3;

	/**
	 * The feature id for the '<em><b>Pipeline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED__PIPELINE = 4;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEED__OPTIONAL = 5;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.EnvironmentImpl <em>Environment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.EnvironmentImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getEnvironment()
	 * @generated
	 */
	int ENVIRONMENT = 25;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.AfterScriptImpl <em>After Script</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.AfterScriptImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getAfterScript()
	 * @generated
	 */
	int AFTER_SCRIPT = 26;

	/**
	 * The feature id for the '<em><b>Commands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFTER_SCRIPT__COMMANDS = 0;

	/**
	 * The number of structural features of the '<em>After Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFTER_SCRIPT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>After Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFTER_SCRIPT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl <em>Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.DefaultImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getDefault()
	 * @generated
	 */
	int DEFAULT = 27;

	/**
	 * The feature id for the '<em><b>Image</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__IMAGE = 0;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__SERVICES = 1;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__TAGS = 2;

	/**
	 * The feature id for the '<em><b>Cache</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__CACHE = 3;

	/**
	 * The feature id for the '<em><b>Before Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__BEFORE_SCRIPT = 4;

	/**
	 * The feature id for the '<em><b>After Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT__AFTER_SCRIPT = 5;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.RetryImpl <em>Retry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.RetryImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getRetry()
	 * @generated
	 */
	int RETRY = 28;

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
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.AxisImpl <em>Axis</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.AxisImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getAxis()
	 * @generated
	 */
	int AXIS = 29;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXIS__NAME = 0;

	/**
	 * The feature id for the '<em><b>Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXIS__VALUES = 1;

	/**
	 * The number of structural features of the '<em>Axis</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXIS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Axis</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXIS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.MatrixImpl <em>Matrix</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.MatrixImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getMatrix()
	 * @generated
	 */
	int MATRIX = 30;

	/**
	 * The feature id for the '<em><b>Axes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX__AXES = 0;

	/**
	 * The number of structural features of the '<em>Matrix</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Matrix</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MATRIX_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ParallelImpl <em>Parallel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ParallelImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getParallel()
	 * @generated
	 */
	int PARALLEL = 31;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL__COUNT = 0;

	/**
	 * The feature id for the '<em><b>Matrix</b></em>' containment reference.
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
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Pipeline#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Pipeline#getVariables()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Variables();

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
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Pipeline#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Pipeline#getDefault()
	 * @see #getPipeline()
	 * @generated
	 */
	EReference getPipeline_Default();

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
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Script</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getScript()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Script();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getBeforeScript <em>Before Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Before Script</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getBeforeScript()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_BeforeScript();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tags</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getTags()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Tags();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getOnly <em>Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Only</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getOnly()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Only();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getDependencies <em>Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Dependencies</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getDependencies()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Dependencies();

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
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#isAllowFailure <em>Allow Failure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allow Failure</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#isAllowFailure()
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
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#isInterruptible <em>Interruptible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interruptible</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#isInterruptible()
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
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getAfterScript <em>After Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>After Script</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getAfterScript()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_AfterScript();

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
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getVariables()
	 * @see #getJob()
	 * @generated
	 */
	EReference getJob_Variables();

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
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifact
	 * @generated
	 */
	EClass getArtifact();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getWhen <em>When</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>When</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getWhen()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_When();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getReports <em>Reports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Reports</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getReports()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_Reports();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getPaths <em>Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Paths</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getPaths()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_Paths();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getName()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Name();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getExpireIn <em>Expire In</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expire In</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getExpireIn()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_ExpireIn();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getExposeAs <em>Expose As</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expose As</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getExposeAs()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_ExposeAs();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#isUntracked <em>Untracked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Untracked</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifact#isUntracked()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Untracked();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getExclude <em>Exclude</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Exclude</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Artifact#getExclude()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Exclude();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Report <em>Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Report</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Report
	 * @generated
	 */
	EClass getReport();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Report#getJunit <em>Junit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Junit</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Report#getJunit()
	 * @see #getReport()
	 * @generated
	 */
	EAttribute getReport_Junit();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Report#getCoverageReport <em>Coverage Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Coverage Report</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Report#getCoverageReport()
	 * @see #getReport()
	 * @generated
	 */
	EAttribute getReport_CoverageReport();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Report#getDotenv <em>Dotenv</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dotenv</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Report#getDotenv()
	 * @see #getReport()
	 * @generated
	 */
	EAttribute getReport_Dotenv();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Report#getCobertura <em>Cobertura</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cobertura</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Report#getCobertura()
	 * @see #getReport()
	 * @generated
	 */
	EAttribute getReport_Cobertura();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Command <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Command</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Command
	 * @generated
	 */
	EClass getCommand();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Command#getCommand <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Command</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Command#getCommand()
	 * @see #getCommand()
	 * @generated
	 */
	EAttribute getCommand_Command();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.BeforeScript <em>Before Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Before Script</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.BeforeScript
	 * @generated
	 */
	EClass getBeforeScript();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.BeforeScript#getCommands <em>Commands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Commands</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.BeforeScript#getCommands()
	 * @see #getBeforeScript()
	 * @generated
	 */
	EReference getBeforeScript_Commands();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Script <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Script
	 * @generated
	 */
	EClass getScript();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Script#getCommands <em>Commands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Commands</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Script#getCommands()
	 * @see #getScript()
	 * @generated
	 */
	EReference getScript_Commands();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Tags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tags</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Tags
	 * @generated
	 */
	EClass getTags();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Tags#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tags</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Tags#getTags()
	 * @see #getTags()
	 * @generated
	 */
	EReference getTags_Tags();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Tag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Tag
	 * @generated
	 */
	EClass getTag();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Tag#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tag</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Tag#getTag()
	 * @see #getTag()
	 * @generated
	 */
	EAttribute getTag_Tag();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Only <em>Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Only</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Only
	 * @generated
	 */
	EClass getOnly();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Only#getBranches <em>Branches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Branches</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Only#getBranches()
	 * @see #getOnly()
	 * @generated
	 */
	EReference getOnly_Branches();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.OnlyBranches <em>Only Branches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Only Branches</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.OnlyBranches
	 * @generated
	 */
	EClass getOnlyBranches();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.OnlyBranches#getBranch <em>Branch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Branch</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.OnlyBranches#getBranch()
	 * @see #getOnlyBranches()
	 * @generated
	 */
	EAttribute getOnlyBranches_Branch();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Dependencies <em>Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dependencies</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Dependencies
	 * @generated
	 */
	EClass getDependencies();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Dependencies#getDependencies <em>Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dependencies</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Dependencies#getDependencies()
	 * @see #getDependencies()
	 * @generated
	 */
	EReference getDependencies_Dependencies();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Dependency <em>Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dependency</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Dependency
	 * @generated
	 */
	EClass getDependency();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Dependency#getDependency <em>Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dependency</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Dependency#getDependency()
	 * @see #getDependency()
	 * @generated
	 */
	EAttribute getDependency_Dependency();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Variables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Variables
	 * @generated
	 */
	EClass getVariables();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Variables#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Variables#getVariables()
	 * @see #getVariables()
	 * @generated
	 */
	EReference getVariables_Variables();

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
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Variable#isExpand <em>Expand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expand</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Variable#isExpand()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Expand();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Path <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Path</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Path
	 * @generated
	 */
	EClass getPath();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Path#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Path#getPath()
	 * @see #getPath()
	 * @generated
	 */
	EAttribute getPath_Path();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule <em>Gitlab Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gitlab Rule</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule
	 * @generated
	 */
	EClass getGitlabRule();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getGitlabIf <em>Gitlab If</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Gitlab If</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getGitlabIf()
	 * @see #getGitlabRule()
	 * @generated
	 */
	EAttribute getGitlabRule_GitlabIf();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getWhen <em>When</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>When</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getWhen()
	 * @see #getGitlabRule()
	 * @generated
	 */
	EAttribute getGitlabRule_When();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getChanges <em>Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Changes</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getChanges()
	 * @see #getGitlabRule()
	 * @generated
	 */
	EAttribute getGitlabRule_Changes();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getExists <em>Exists</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Exists</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getExists()
	 * @see #getGitlabRule()
	 * @generated
	 */
	EAttribute getGitlabRule_Exists();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#isAllowFailure <em>Allow Failure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allow Failure</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#isAllowFailure()
	 * @see #getGitlabRule()
	 * @generated
	 */
	EAttribute getGitlabRule_AllowFailure();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Variables</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule#getVariables()
	 * @see #getGitlabRule()
	 * @generated
	 */
	EReference getGitlabRule_Variables();

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
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Image#getPullPolicy <em>Pull Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Pull Policy</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Image#getPullPolicy()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_PullPolicy();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Docker <em>Docker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Docker</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Docker
	 * @generated
	 */
	EClass getDocker();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Docker#getPlatform <em>Platform</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Platform</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Docker#getPlatform()
	 * @see #getDocker()
	 * @generated
	 */
	EAttribute getDocker_Platform();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Docker#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Docker#getUser()
	 * @see #getDocker()
	 * @generated
	 */
	EAttribute getDocker_User();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Kubernetes <em>Kubernetes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Kubernetes</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Kubernetes
	 * @generated
	 */
	EClass getKubernetes();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Kubernetes#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Kubernetes#getUser()
	 * @see #getKubernetes()
	 * @generated
	 */
	EAttribute getKubernetes_User();

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
	 * Returns the meta object for the reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Service#getCommands <em>Commands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Commands</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Service#getCommands()
	 * @see #getService()
	 * @generated
	 */
	EReference getService_Commands();

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
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Service#getKubernetes <em>Kubernetes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Kubernetes</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Service#getKubernetes()
	 * @see #getService()
	 * @generated
	 */
	EReference getService_Kubernetes();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Service#getPullPolicy <em>Pull Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Pull Policy</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Service#getPullPolicy()
	 * @see #getService()
	 * @generated
	 */
	EAttribute getService_PullPolicy();

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
	 * Returns the meta object for the reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key</em>'.
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
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#isUntracked <em>Untracked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Untracked</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Cache#isUntracked()
	 * @see #getCache()
	 * @generated
	 */
	EAttribute getCache_Untracked();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Cache#isUnprotect <em>Unprotect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unprotect</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Cache#isUnprotect()
	 * @see #getCache()
	 * @generated
	 */
	EAttribute getCache_Unprotect();

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
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Key <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Key</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Key
	 * @generated
	 */
	EClass getKey();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Key#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Key#getName()
	 * @see #getKey()
	 * @generated
	 */
	EAttribute getKey_Name();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Key#getFiles <em>Files</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Files</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Key#getFiles()
	 * @see #getKey()
	 * @generated
	 */
	EAttribute getKey_Files();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Key#getFilesCommits <em>Files Commits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Files Commits</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Key#getFilesCommits()
	 * @see #getKey()
	 * @generated
	 */
	EAttribute getKey_FilesCommits();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Key#getPrefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefix</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Key#getPrefix()
	 * @see #getKey()
	 * @generated
	 */
	EAttribute getKey_Prefix();

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
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#isArtifacts <em>Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifacts</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Need#isArtifacts()
	 * @see #getNeed()
	 * @generated
	 */
	EAttribute getNeed_Artifacts();

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
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Need#isOptional <em>Optional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Need#isOptional()
	 * @see #getNeed()
	 * @generated
	 */
	EAttribute getNeed_Optional();

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
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.AfterScript <em>After Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>After Script</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.AfterScript
	 * @generated
	 */
	EClass getAfterScript();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.AfterScript#getCommands <em>Commands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Commands</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.AfterScript#getCommands()
	 * @see #getAfterScript()
	 * @generated
	 */
	EReference getAfterScript_Commands();

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
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tags</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Default#getTags()
	 * @see #getDefault()
	 * @generated
	 */
	EReference getDefault_Tags();

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
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getBeforeScript <em>Before Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Before Script</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Default#getBeforeScript()
	 * @see #getDefault()
	 * @generated
	 */
	EReference getDefault_BeforeScript();

	/**
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#getAfterScript <em>After Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>After Script</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Default#getAfterScript()
	 * @see #getDefault()
	 * @generated
	 */
	EReference getDefault_AfterScript();

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
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Default#isInterruptible <em>Interruptible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interruptible</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Default#isInterruptible()
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
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Axis <em>Axis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Axis</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Axis
	 * @generated
	 */
	EClass getAxis();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Axis#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Axis#getName()
	 * @see #getAxis()
	 * @generated
	 */
	EAttribute getAxis_Name();

	/**
	 * Returns the meta object for the attribute list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Axis#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Values</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Axis#getValues()
	 * @see #getAxis()
	 * @generated
	 */
	EAttribute getAxis_Values();

	/**
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Matrix <em>Matrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Matrix</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Matrix
	 * @generated
	 */
	EClass getMatrix();

	/**
	 * Returns the meta object for the containment reference list '{@link com.mddoai.metamodel.gitlab.gitlabMM.Matrix#getAxes <em>Axes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Axes</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Matrix#getAxes()
	 * @see #getMatrix()
	 * @generated
	 */
	EReference getMatrix_Axes();

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
	 * Returns the meta object for the containment reference '{@link com.mddoai.metamodel.gitlab.gitlabMM.Parallel#getMatrix <em>Matrix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Matrix</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Parallel#getMatrix()
	 * @see #getParallel()
	 * @generated
	 */
	EReference getParallel_Matrix();

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
		 * The meta object literal for the '<em><b>Jobs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__JOBS = eINSTANCE.getPipeline_Jobs();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__VARIABLES = eINSTANCE.getPipeline_Variables();

		/**
		 * The meta object literal for the '<em><b>Workflow</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__WORKFLOW = eINSTANCE.getPipeline_Workflow();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PIPELINE__DEFAULT = eINSTANCE.getPipeline_Default();

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
		 * The meta object literal for the '<em><b>Artifacts</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__ARTIFACTS = eINSTANCE.getJob_Artifacts();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__SCRIPT = eINSTANCE.getJob_Script();

		/**
		 * The meta object literal for the '<em><b>Before Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__BEFORE_SCRIPT = eINSTANCE.getJob_BeforeScript();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__TAGS = eINSTANCE.getJob_Tags();

		/**
		 * The meta object literal for the '<em><b>Only</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__ONLY = eINSTANCE.getJob_Only();

		/**
		 * The meta object literal for the '<em><b>Dependencies</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__DEPENDENCIES = eINSTANCE.getJob_Dependencies();

		/**
		 * The meta object literal for the '<em><b>When</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__WHEN = eINSTANCE.getJob_When();

		/**
		 * The meta object literal for the '<em><b>Image</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__IMAGE = eINSTANCE.getJob_Image();

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
		 * The meta object literal for the '<em><b>After Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__AFTER_SCRIPT = eINSTANCE.getJob_AfterScript();

		/**
		 * The meta object literal for the '<em><b>Cache</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__CACHE = eINSTANCE.getJob_Cache();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__RULES = eINSTANCE.getJob_Rules();

		/**
		 * The meta object literal for the '<em><b>Needs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__NEEDS = eINSTANCE.getJob_Needs();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__SERVICES = eINSTANCE.getJob_Services();

		/**
		 * The meta object literal for the '<em><b>Environment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__ENVIRONMENT = eINSTANCE.getJob_Environment();

		/**
		 * The meta object literal for the '<em><b>Retry</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__RETRY = eINSTANCE.getJob_Retry();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__VARIABLES = eINSTANCE.getJob_Variables();

		/**
		 * The meta object literal for the '<em><b>Parallel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOB__PARALLEL = eINSTANCE.getJob_Parallel();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactImpl <em>Artifact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getArtifact()
		 * @generated
		 */
		EClass ARTIFACT = eINSTANCE.getArtifact();

		/**
		 * The meta object literal for the '<em><b>When</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__WHEN = eINSTANCE.getArtifact_When();

		/**
		 * The meta object literal for the '<em><b>Reports</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__REPORTS = eINSTANCE.getArtifact_Reports();

		/**
		 * The meta object literal for the '<em><b>Paths</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__PATHS = eINSTANCE.getArtifact_Paths();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__NAME = eINSTANCE.getArtifact_Name();

		/**
		 * The meta object literal for the '<em><b>Expire In</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__EXPIRE_IN = eINSTANCE.getArtifact_ExpireIn();

		/**
		 * The meta object literal for the '<em><b>Expose As</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__EXPOSE_AS = eINSTANCE.getArtifact_ExposeAs();

		/**
		 * The meta object literal for the '<em><b>Untracked</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__UNTRACKED = eINSTANCE.getArtifact_Untracked();

		/**
		 * The meta object literal for the '<em><b>Exclude</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__EXCLUDE = eINSTANCE.getArtifact_Exclude();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ReportImpl <em>Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ReportImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getReport()
		 * @generated
		 */
		EClass REPORT = eINSTANCE.getReport();

		/**
		 * The meta object literal for the '<em><b>Junit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPORT__JUNIT = eINSTANCE.getReport_Junit();

		/**
		 * The meta object literal for the '<em><b>Coverage Report</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPORT__COVERAGE_REPORT = eINSTANCE.getReport_CoverageReport();

		/**
		 * The meta object literal for the '<em><b>Dotenv</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPORT__DOTENV = eINSTANCE.getReport_Dotenv();

		/**
		 * The meta object literal for the '<em><b>Cobertura</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPORT__COBERTURA = eINSTANCE.getReport_Cobertura();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CommandImpl <em>Command</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.CommandImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getCommand()
		 * @generated
		 */
		EClass COMMAND = eINSTANCE.getCommand();

		/**
		 * The meta object literal for the '<em><b>Command</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMAND__COMMAND = eINSTANCE.getCommand_Command();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.BeforeScriptImpl <em>Before Script</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.BeforeScriptImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getBeforeScript()
		 * @generated
		 */
		EClass BEFORE_SCRIPT = eINSTANCE.getBeforeScript();

		/**
		 * The meta object literal for the '<em><b>Commands</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEFORE_SCRIPT__COMMANDS = eINSTANCE.getBeforeScript_Commands();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ScriptImpl <em>Script</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ScriptImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getScript()
		 * @generated
		 */
		EClass SCRIPT = eINSTANCE.getScript();

		/**
		 * The meta object literal for the '<em><b>Commands</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCRIPT__COMMANDS = eINSTANCE.getScript_Commands();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.TagsImpl <em>Tags</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.TagsImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getTags()
		 * @generated
		 */
		EClass TAGS = eINSTANCE.getTags();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAGS__TAGS = eINSTANCE.getTags_Tags();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.TagImpl <em>Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.TagImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getTag()
		 * @generated
		 */
		EClass TAG = eINSTANCE.getTag();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG__TAG = eINSTANCE.getTag_Tag();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.OnlyImpl <em>Only</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.OnlyImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getOnly()
		 * @generated
		 */
		EClass ONLY = eINSTANCE.getOnly();

		/**
		 * The meta object literal for the '<em><b>Branches</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ONLY__BRANCHES = eINSTANCE.getOnly_Branches();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.OnlyBranchesImpl <em>Only Branches</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.OnlyBranchesImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getOnlyBranches()
		 * @generated
		 */
		EClass ONLY_BRANCHES = eINSTANCE.getOnlyBranches();

		/**
		 * The meta object literal for the '<em><b>Branch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONLY_BRANCHES__BRANCH = eINSTANCE.getOnlyBranches_Branch();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DependenciesImpl <em>Dependencies</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.DependenciesImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getDependencies()
		 * @generated
		 */
		EClass DEPENDENCIES = eINSTANCE.getDependencies();

		/**
		 * The meta object literal for the '<em><b>Dependencies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPENDENCIES__DEPENDENCIES = eINSTANCE.getDependencies_Dependencies();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DependencyImpl <em>Dependency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.DependencyImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getDependency()
		 * @generated
		 */
		EClass DEPENDENCY = eINSTANCE.getDependency();

		/**
		 * The meta object literal for the '<em><b>Dependency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPENDENCY__DEPENDENCY = eINSTANCE.getDependency_Dependency();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.VariablesImpl <em>Variables</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.VariablesImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getVariables()
		 * @generated
		 */
		EClass VARIABLES = eINSTANCE.getVariables();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLES__VARIABLES = eINSTANCE.getVariables_Variables();

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
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.PathImpl <em>Path</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.PathImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getPath()
		 * @generated
		 */
		EClass PATH = eINSTANCE.getPath();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH__PATH = eINSTANCE.getPath_Path();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabRuleImpl <em>Gitlab Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabRuleImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getGitlabRule()
		 * @generated
		 */
		EClass GITLAB_RULE = eINSTANCE.getGitlabRule();

		/**
		 * The meta object literal for the '<em><b>Gitlab If</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GITLAB_RULE__GITLAB_IF = eINSTANCE.getGitlabRule_GitlabIf();

		/**
		 * The meta object literal for the '<em><b>When</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GITLAB_RULE__WHEN = eINSTANCE.getGitlabRule_When();

		/**
		 * The meta object literal for the '<em><b>Changes</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GITLAB_RULE__CHANGES = eINSTANCE.getGitlabRule_Changes();

		/**
		 * The meta object literal for the '<em><b>Exists</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GITLAB_RULE__EXISTS = eINSTANCE.getGitlabRule_Exists();

		/**
		 * The meta object literal for the '<em><b>Allow Failure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GITLAB_RULE__ALLOW_FAILURE = eINSTANCE.getGitlabRule_AllowFailure();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GITLAB_RULE__VARIABLES = eINSTANCE.getGitlabRule_Variables();

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
		 * The meta object literal for the '<em><b>Pull Policy</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__PULL_POLICY = eINSTANCE.getImage_PullPolicy();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.DockerImpl <em>Docker</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.DockerImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getDocker()
		 * @generated
		 */
		EClass DOCKER = eINSTANCE.getDocker();

		/**
		 * The meta object literal for the '<em><b>Platform</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCKER__PLATFORM = eINSTANCE.getDocker_Platform();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCKER__USER = eINSTANCE.getDocker_User();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.KubernetesImpl <em>Kubernetes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.KubernetesImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getKubernetes()
		 * @generated
		 */
		EClass KUBERNETES = eINSTANCE.getKubernetes();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KUBERNETES__USER = eINSTANCE.getKubernetes_User();

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
		 * The meta object literal for the '<em><b>Commands</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE__COMMANDS = eINSTANCE.getService_Commands();

		/**
		 * The meta object literal for the '<em><b>Docker</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE__DOCKER = eINSTANCE.getService_Docker();

		/**
		 * The meta object literal for the '<em><b>Kubernetes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE__KUBERNETES = eINSTANCE.getService_Kubernetes();

		/**
		 * The meta object literal for the '<em><b>Pull Policy</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE__PULL_POLICY = eINSTANCE.getService_PullPolicy();

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
		 * The meta object literal for the '<em><b>Key</b></em>' reference feature.
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
		 * The meta object literal for the '<em><b>Unprotect</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CACHE__UNPROTECT = eINSTANCE.getCache_Unprotect();

		/**
		 * The meta object literal for the '<em><b>When</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CACHE__WHEN = eINSTANCE.getCache_When();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.KeyImpl <em>Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.KeyImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getKey()
		 * @generated
		 */
		EClass KEY = eINSTANCE.getKey();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KEY__NAME = eINSTANCE.getKey_Name();

		/**
		 * The meta object literal for the '<em><b>Files</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KEY__FILES = eINSTANCE.getKey_Files();

		/**
		 * The meta object literal for the '<em><b>Files Commits</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KEY__FILES_COMMITS = eINSTANCE.getKey_FilesCommits();

		/**
		 * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KEY__PREFIX = eINSTANCE.getKey_Prefix();

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
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NEED__OPTIONAL = eINSTANCE.getNeed_Optional();

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
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.AfterScriptImpl <em>After Script</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.AfterScriptImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getAfterScript()
		 * @generated
		 */
		EClass AFTER_SCRIPT = eINSTANCE.getAfterScript();

		/**
		 * The meta object literal for the '<em><b>Commands</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AFTER_SCRIPT__COMMANDS = eINSTANCE.getAfterScript_Commands();

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
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFAULT__SERVICES = eINSTANCE.getDefault_Services();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFAULT__TAGS = eINSTANCE.getDefault_Tags();

		/**
		 * The meta object literal for the '<em><b>Cache</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFAULT__CACHE = eINSTANCE.getDefault_Cache();

		/**
		 * The meta object literal for the '<em><b>Before Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFAULT__BEFORE_SCRIPT = eINSTANCE.getDefault_BeforeScript();

		/**
		 * The meta object literal for the '<em><b>After Script</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFAULT__AFTER_SCRIPT = eINSTANCE.getDefault_AfterScript();

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
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.AxisImpl <em>Axis</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.AxisImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getAxis()
		 * @generated
		 */
		EClass AXIS = eINSTANCE.getAxis();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AXIS__NAME = eINSTANCE.getAxis_Name();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AXIS__VALUES = eINSTANCE.getAxis_Values();

		/**
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.MatrixImpl <em>Matrix</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.MatrixImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getMatrix()
		 * @generated
		 */
		EClass MATRIX = eINSTANCE.getMatrix();

		/**
		 * The meta object literal for the '<em><b>Axes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MATRIX__AXES = eINSTANCE.getMatrix_Axes();

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
		 * The meta object literal for the '<em><b>Matrix</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARALLEL__MATRIX = eINSTANCE.getParallel_Matrix();

	}

} //GitlabMMPackage
