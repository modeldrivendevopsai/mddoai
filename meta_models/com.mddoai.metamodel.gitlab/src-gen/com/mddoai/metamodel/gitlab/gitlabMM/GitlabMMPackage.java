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
	 * The number of structural features of the '<em>Pipeline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PIPELINE_FEATURE_COUNT = 3;

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
	 * The feature id for the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__IMAGE = 5;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__TAGS = 6;

	/**
	 * The feature id for the '<em><b>Only</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__ONLY = 7;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__DEPENDENCIES = 8;

	/**
	 * The feature id for the '<em><b>When</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB__WHEN = 9;

	/**
	 * The number of structural features of the '<em>Job</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOB_FEATURE_COUNT = 10;

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
	 * The number of structural features of the '<em>Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_FEATURE_COUNT = 3;

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
	 * The number of structural features of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPORT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ComandImpl <em>Comand</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ComandImpl
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getComand()
	 * @generated
	 */
	int COMAND = 4;

	/**
	 * The feature id for the '<em><b>Comand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMAND__COMAND = 0;

	/**
	 * The number of structural features of the '<em>Comand</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMAND_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Comand</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMAND_OPERATION_COUNT = 0;

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
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_FEATURE_COUNT = 2;

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
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Job#getImage <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Image</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Job#getImage()
	 * @see #getJob()
	 * @generated
	 */
	EAttribute getJob_Image();

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
	 * Returns the meta object for class '{@link com.mddoai.metamodel.gitlab.gitlabMM.Comand <em>Comand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comand</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Comand
	 * @generated
	 */
	EClass getComand();

	/**
	 * Returns the meta object for the attribute '{@link com.mddoai.metamodel.gitlab.gitlabMM.Comand#getComand <em>Comand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comand</em>'.
	 * @see com.mddoai.metamodel.gitlab.gitlabMM.Comand#getComand()
	 * @see #getComand()
	 * @generated
	 */
	EAttribute getComand_Comand();

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
		 * The meta object literal for the '<em><b>Image</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOB__IMAGE = eINSTANCE.getJob_Image();

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
		 * The meta object literal for the '{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ComandImpl <em>Comand</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.ComandImpl
		 * @see com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabMMPackageImpl#getComand()
		 * @generated
		 */
		EClass COMAND = eINSTANCE.getComand();

		/**
		 * The meta object literal for the '<em><b>Comand</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMAND__COMAND = eINSTANCE.getComand_Comand();

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

	}

} //GitlabMMPackage
