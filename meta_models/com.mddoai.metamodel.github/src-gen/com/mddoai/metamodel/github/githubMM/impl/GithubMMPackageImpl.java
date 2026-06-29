/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.AbstractStep;
import com.mddoai.metamodel.github.githubMM.Agent;
import com.mddoai.metamodel.github.githubMM.Always;
import com.mddoai.metamodel.github.githubMM.And;
import com.mddoai.metamodel.github.githubMM.BinaryOp;
import com.mddoai.metamodel.github.githubMM.BooleanLiteral;
import com.mddoai.metamodel.github.githubMM.BuiltInFunctionCall;
import com.mddoai.metamodel.github.githubMM.Cancelled;
import com.mddoai.metamodel.github.githubMM.Command;
import com.mddoai.metamodel.github.githubMM.Comparison;
import com.mddoai.metamodel.github.githubMM.Concat;
import com.mddoai.metamodel.github.githubMM.ConcurrencyGroup;
import com.mddoai.metamodel.github.githubMM.Contains;
import com.mddoai.metamodel.github.githubMM.Defaults;
import com.mddoai.metamodel.github.githubMM.DotOp;
import com.mddoai.metamodel.github.githubMM.DoubleLiteral;
import com.mddoai.metamodel.github.githubMM.EndsWith;
import com.mddoai.metamodel.github.githubMM.Equality;
import com.mddoai.metamodel.github.githubMM.EventTypeTrigger;
import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.Failure;
import com.mddoai.metamodel.github.githubMM.Format;
import com.mddoai.metamodel.github.githubMM.FromJSON;
import com.mddoai.metamodel.github.githubMM.GitHubContext;
import com.mddoai.metamodel.github.githubMM.GithubMMFactory;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.HashFiles;
import com.mddoai.metamodel.github.githubMM.IfStep;
import com.mddoai.metamodel.github.githubMM.Input;
import com.mddoai.metamodel.github.githubMM.InputTrigger;
import com.mddoai.metamodel.github.githubMM.IntegerLiteral;
import com.mddoai.metamodel.github.githubMM.Job;
import com.mddoai.metamodel.github.githubMM.Join;
import com.mddoai.metamodel.github.githubMM.Literal;
import com.mddoai.metamodel.github.githubMM.LogicalOp;
import com.mddoai.metamodel.github.githubMM.Matrix;
import com.mddoai.metamodel.github.githubMM.MatrixAxis;
import com.mddoai.metamodel.github.githubMM.MatrixCombination;
import com.mddoai.metamodel.github.githubMM.Not;
import com.mddoai.metamodel.github.githubMM.Or;
import com.mddoai.metamodel.github.githubMM.Output;
import com.mddoai.metamodel.github.githubMM.Parameter;
import com.mddoai.metamodel.github.githubMM.PullRequestTargetTrigger;
import com.mddoai.metamodel.github.githubMM.PullRequestTrigger;
import com.mddoai.metamodel.github.githubMM.PushTrigger;
import com.mddoai.metamodel.github.githubMM.ScheduleTrigger;
import com.mddoai.metamodel.github.githubMM.ScriptJob;
import com.mddoai.metamodel.github.githubMM.Secret;
import com.mddoai.metamodel.github.githubMM.SpecifiedBranchesTrigger;
import com.mddoai.metamodel.github.githubMM.SpecifiedPathsTrigger;
import com.mddoai.metamodel.github.githubMM.StagingEnvironment;
import com.mddoai.metamodel.github.githubMM.StandardEventTrigger;
import com.mddoai.metamodel.github.githubMM.StartsWith;
import com.mddoai.metamodel.github.githubMM.StatusCheck;
import com.mddoai.metamodel.github.githubMM.Step;
import com.mddoai.metamodel.github.githubMM.StringLiteral;
import com.mddoai.metamodel.github.githubMM.Success;
import com.mddoai.metamodel.github.githubMM.ToJSON;
import com.mddoai.metamodel.github.githubMM.Trigger;
import com.mddoai.metamodel.github.githubMM.UnaryOp;
import com.mddoai.metamodel.github.githubMM.Value;
import com.mddoai.metamodel.github.githubMM.VariableDeclaration;
import com.mddoai.metamodel.github.githubMM.VariableReference;
import com.mddoai.metamodel.github.githubMM.Workflow;
import com.mddoai.metamodel.github.githubMM.WorkflowCallJob;
import com.mddoai.metamodel.github.githubMM.WorkflowCallTrigger;
import com.mddoai.metamodel.github.githubMM.WorkflowDispatchTrigger;
import com.mddoai.metamodel.github.githubMM.WorkflowRunTrigger;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GithubMMPackageImpl extends EPackageImpl implements GithubMMPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workflowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jobEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scriptJobEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workflowCallJobEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass agentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stagingEnvironmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass triggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventTypeTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specifiedBranchesTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specifiedPathsTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass standardEventTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inputTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workflowRunTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pullRequestTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pullRequestTargetTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pushTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scheduleTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workflowCallTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workflowDispatchTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass permissionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass concurrencyGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass defaultsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableAssignmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass binaryOpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass concatEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dotOpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass equalityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass comparisonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logicalOpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass andEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unaryOpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass notEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass builtInFunctionCallEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass startsWithEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass endsWithEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formatEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass joinEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass toJSONEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fromJSONEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hashFilesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass statusCheckEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass alwaysEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass successEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cancelledEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass failureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doubleLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gitHubContextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass matrixEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass matrixAxisEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass matrixCombinationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ifStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commandEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass secretEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eventsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum webhooK_ACTIVITY_TYPESEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum permissioN_SCOPESEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum permissionsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum equalitY_OPSEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum comparisoN_OPSEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum contextsEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum inpuT_TYPESEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see com.mddoai.metamodel.github.githubMM.GithubMMPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private GithubMMPackageImpl() {
		super(eNS_URI, GithubMMFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link GithubMMPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static GithubMMPackage init() {
		if (isInited)
			return (GithubMMPackage) EPackage.Registry.INSTANCE.getEPackage(GithubMMPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredGithubMMPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		GithubMMPackageImpl theGithubMMPackage = registeredGithubMMPackage instanceof GithubMMPackageImpl
				? (GithubMMPackageImpl) registeredGithubMMPackage
				: new GithubMMPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theGithubMMPackage.createPackageContents();

		// Initialize created meta-data
		theGithubMMPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theGithubMMPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(GithubMMPackage.eNS_URI, theGithubMMPackage);
		return theGithubMMPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWorkflow() {
		return workflowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkflow_Name() {
		return (EReference) workflowEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkflow_RunName() {
		return (EReference) workflowEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkflow_Triggers() {
		return (EReference) workflowEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkflow_Permissions() {
		return (EReference) workflowEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkflow_Defaults() {
		return (EReference) workflowEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkflow_EnvironmentVariables() {
		return (EReference) workflowEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkflow_ConcurrencyGroup() {
		return (EReference) workflowEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkflow_Jobs() {
		return (EReference) workflowEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getJob() {
		return jobEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getJob_Name() {
		return (EAttribute) jobEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_JobName() {
		return (EReference) jobEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Permissions() {
		return (EReference) jobEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_DependsOn() {
		return (EReference) jobEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_NecessaryFor() {
		return (EReference) jobEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_IfCondition() {
		return (EReference) jobEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Agent() {
		return (EReference) jobEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Container() {
		return (EReference) jobEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_StagingEnvironment() {
		return (EReference) jobEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Defaults() {
		return (EReference) jobEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_EnvironmentVariables() {
		return (EReference) jobEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Services() {
		return (EReference) jobEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_ConcurrencyGroup() {
		return (EReference) jobEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_TimeoutMinutes() {
		return (EReference) jobEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_ContinueOnError() {
		return (EReference) jobEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Strategy() {
		return (EReference) jobEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Outputs() {
		return (EReference) jobEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScriptJob() {
		return scriptJobEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScriptJob_Steps() {
		return (EReference) scriptJobEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWorkflowCallJob() {
		return workflowCallJobEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkflowCallJob_Uses() {
		return (EReference) workflowCallJobEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkflowCallJob_Args() {
		return (EReference) workflowCallJobEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkflowCallJob_Secrets() {
		return (EReference) workflowCallJobEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWorkflowCallJob_InheritSecrets() {
		return (EAttribute) workflowCallJobEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAgent() {
		return agentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAgent_Group() {
		return (EReference) agentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAgent_Labels() {
		return (EReference) agentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContainer() {
		return containerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainer_Image() {
		return (EReference) containerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainer_Username() {
		return (EReference) containerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainer_Password() {
		return (EReference) containerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainer_EnvironmentVariables() {
		return (EReference) containerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainer_Ports() {
		return (EReference) containerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainer_Volumes() {
		return (EReference) containerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainer_Options() {
		return (EReference) containerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getService() {
		return serviceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getService_Key() {
		return (EAttribute) serviceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getService_Value() {
		return (EReference) serviceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStagingEnvironment() {
		return stagingEnvironmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStagingEnvironment_Name() {
		return (EReference) stagingEnvironmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStagingEnvironment_Url() {
		return (EReference) stagingEnvironmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTrigger() {
		return triggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEventTypeTrigger() {
		return eventTypeTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEventTypeTrigger_EventTypes() {
		return (EAttribute) eventTypeTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSpecifiedBranchesTrigger() {
		return specifiedBranchesTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpecifiedBranchesTrigger_Branches() {
		return (EReference) specifiedBranchesTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpecifiedBranchesTrigger_IgnoreSpecifiedBranches() {
		return (EAttribute) specifiedBranchesTriggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSpecifiedPathsTrigger() {
		return specifiedPathsTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSpecifiedPathsTrigger_Paths() {
		return (EReference) specifiedPathsTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpecifiedPathsTrigger_IgnoreSpecifiedPaths() {
		return (EAttribute) specifiedPathsTriggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStandardEventTrigger() {
		return standardEventTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStandardEventTrigger_Event() {
		return (EAttribute) standardEventTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInputTrigger() {
		return inputTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInputTrigger_Inputs() {
		return (EReference) inputTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWorkflowRunTrigger() {
		return workflowRunTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPullRequestTrigger() {
		return pullRequestTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPullRequestTargetTrigger() {
		return pullRequestTargetTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPushTrigger() {
		return pushTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPushTrigger_Tags() {
		return (EReference) pushTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPushTrigger_IgnoreSpecifiedTags() {
		return (EAttribute) pushTriggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScheduleTrigger() {
		return scheduleTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScheduleTrigger_Crons() {
		return (EReference) scheduleTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWorkflowCallTrigger() {
		return workflowCallTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkflowCallTrigger_Outputs() {
		return (EReference) workflowCallTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkflowCallTrigger_Secrets() {
		return (EReference) workflowCallTriggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWorkflowDispatchTrigger() {
		return workflowDispatchTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPermission() {
		return permissionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPermission_Key() {
		return (EAttribute) permissionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPermission_Value() {
		return (EAttribute) permissionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConcurrencyGroup() {
		return concurrencyGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConcurrencyGroup_Name() {
		return (EReference) concurrencyGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConcurrencyGroup_CancelInProgress() {
		return (EReference) concurrencyGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDefaults() {
		return defaultsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDefaults_Shell() {
		return (EReference) defaultsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDefaults_WorkingDirectory() {
		return (EReference) defaultsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExpression() {
		return expressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVariableDeclaration() {
		return variableDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVariableDeclaration_Name() {
		return (EAttribute) variableDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVariableAssignment() {
		return variableAssignmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getVariableAssignment_Key() {
		return (EReference) variableAssignmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getVariableAssignment_Value() {
		return (EReference) variableAssignmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBinaryOp() {
		return binaryOpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBinaryOp_Lhs() {
		return (EReference) binaryOpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBinaryOp_Rhs() {
		return (EReference) binaryOpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConcat() {
		return concatEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConcat_Expressions() {
		return (EReference) concatEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDotOp() {
		return dotOpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEquality() {
		return equalityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEquality_Op() {
		return (EAttribute) equalityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getComparison() {
		return comparisonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComparison_Op() {
		return (EAttribute) comparisonEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLogicalOp() {
		return logicalOpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOr() {
		return orEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAnd() {
		return andEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUnaryOp() {
		return unaryOpEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getUnaryOp_ChildExpr() {
		return (EReference) unaryOpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNot() {
		return notEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBuiltInFunctionCall() {
		return builtInFunctionCallEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContains() {
		return containsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContains_Search() {
		return (EReference) containsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContains_Item() {
		return (EReference) containsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStartsWith() {
		return startsWithEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStartsWith_SearchString() {
		return (EReference) startsWithEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStartsWith_SearchValue() {
		return (EReference) startsWithEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEndsWith() {
		return endsWithEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEndsWith_SearchString() {
		return (EReference) endsWithEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEndsWith_SearchValue() {
		return (EReference) endsWithEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFormat() {
		return formatEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFormat_String() {
		return (EReference) formatEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFormat_ReplaceValues() {
		return (EReference) formatEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getJoin() {
		return joinEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJoin_Array() {
		return (EReference) joinEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJoin_Sep() {
		return (EReference) joinEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getToJSON() {
		return toJSONEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getToJSON_Value() {
		return (EReference) toJSONEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFromJSON() {
		return fromJSONEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFromJSON_Value() {
		return (EReference) fromJSONEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHashFiles() {
		return hashFilesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getHashFiles_Path() {
		return (EReference) hashFilesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStatusCheck() {
		return statusCheckEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAlways() {
		return alwaysEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSuccess() {
		return successEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCancelled() {
		return cancelledEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFailure() {
		return failureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValue() {
		return valueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLiteral() {
		return literalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStringLiteral() {
		return stringLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStringLiteral_Value() {
		return (EAttribute) stringLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIntegerLiteral() {
		return integerLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIntegerLiteral_Value() {
		return (EAttribute) integerLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDoubleLiteral() {
		return doubleLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDoubleLiteral_Value() {
		return (EAttribute) doubleLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBooleanLiteral() {
		return booleanLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBooleanLiteral_Value() {
		return (EAttribute) booleanLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVariableReference() {
		return variableReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getVariableReference_Reference() {
		return (EReference) variableReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGitHubContext() {
		return gitHubContextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitHubContext_Context() {
		return (EAttribute) gitHubContextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMatrix() {
		return matrixEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMatrix_Axes() {
		return (EReference) matrixEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMatrix_Includes() {
		return (EReference) matrixEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMatrix_Excludes() {
		return (EReference) matrixEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMatrix_FailFast() {
		return (EReference) matrixEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMatrix_MaxParallel() {
		return (EReference) matrixEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMatrixAxis() {
		return matrixAxisEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMatrixAxis_Name() {
		return (EReference) matrixAxisEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMatrixAxis_Cells() {
		return (EReference) matrixAxisEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMatrixCombination() {
		return matrixCombinationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMatrixCombination_Entries() {
		return (EReference) matrixCombinationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAbstractStep() {
		return abstractStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIfStep() {
		return ifStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIfStep_IfCondition() {
		return (EReference) ifStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIfStep_ThenRun() {
		return (EReference) ifStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStep() {
		return stepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStep_Id() {
		return (EAttribute) stepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStep_Name() {
		return (EReference) stepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStep_TimeoutMinutes() {
		return (EReference) stepEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStep_ContinueOnError() {
		return (EReference) stepEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStep_Shell() {
		return (EReference) stepEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStep_WorkingDirectory() {
		return (EReference) stepEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStep_EnvironmentVariables() {
		return (EReference) stepEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCommand() {
		return commandEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCommand_Command() {
		return (EReference) commandEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPackage() {
		return packageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_Uses() {
		return (EReference) packageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_Args() {
		return (EReference) packageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_Entrypoint() {
		return (EReference) packageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackage_ContainerArgs() {
		return (EReference) packageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getParameter() {
		return parameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParameter_Id() {
		return (EReference) parameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParameter_Description() {
		return (EReference) parameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInput() {
		return inputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInput_Type() {
		return (EAttribute) inputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInput_IsRequired() {
		return (EAttribute) inputEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInput_Default() {
		return (EReference) inputEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInput_Options() {
		return (EAttribute) inputEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSecret() {
		return secretEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSecret_IsRequired() {
		return (EAttribute) secretEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOutput() {
		return outputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOutput_Value() {
		return (EReference) outputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getEVENTS() {
		return eventsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getWEBHOOK_ACTIVITY_TYPES() {
		return webhooK_ACTIVITY_TYPESEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getPERMISSION_SCOPES() {
		return permissioN_SCOPESEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getPERMISSIONS() {
		return permissionsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getEQUALITY_OPS() {
		return equalitY_OPSEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getCOMPARISON_OPS() {
		return comparisoN_OPSEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getCONTEXTS() {
		return contextsEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getINPUT_TYPES() {
		return inpuT_TYPESEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GithubMMFactory getGithubMMFactory() {
		return (GithubMMFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		workflowEClass = createEClass(WORKFLOW);
		createEReference(workflowEClass, WORKFLOW__NAME);
		createEReference(workflowEClass, WORKFLOW__RUN_NAME);
		createEReference(workflowEClass, WORKFLOW__TRIGGERS);
		createEReference(workflowEClass, WORKFLOW__PERMISSIONS);
		createEReference(workflowEClass, WORKFLOW__DEFAULTS);
		createEReference(workflowEClass, WORKFLOW__ENVIRONMENT_VARIABLES);
		createEReference(workflowEClass, WORKFLOW__CONCURRENCY_GROUP);
		createEReference(workflowEClass, WORKFLOW__JOBS);

		jobEClass = createEClass(JOB);
		createEAttribute(jobEClass, JOB__NAME);
		createEReference(jobEClass, JOB__JOB_NAME);
		createEReference(jobEClass, JOB__PERMISSIONS);
		createEReference(jobEClass, JOB__DEPENDS_ON);
		createEReference(jobEClass, JOB__NECESSARY_FOR);
		createEReference(jobEClass, JOB__IF_CONDITION);
		createEReference(jobEClass, JOB__AGENT);
		createEReference(jobEClass, JOB__CONTAINER);
		createEReference(jobEClass, JOB__STAGING_ENVIRONMENT);
		createEReference(jobEClass, JOB__DEFAULTS);
		createEReference(jobEClass, JOB__ENVIRONMENT_VARIABLES);
		createEReference(jobEClass, JOB__SERVICES);
		createEReference(jobEClass, JOB__CONCURRENCY_GROUP);
		createEReference(jobEClass, JOB__TIMEOUT_MINUTES);
		createEReference(jobEClass, JOB__CONTINUE_ON_ERROR);
		createEReference(jobEClass, JOB__STRATEGY);
		createEReference(jobEClass, JOB__OUTPUTS);

		scriptJobEClass = createEClass(SCRIPT_JOB);
		createEReference(scriptJobEClass, SCRIPT_JOB__STEPS);

		workflowCallJobEClass = createEClass(WORKFLOW_CALL_JOB);
		createEReference(workflowCallJobEClass, WORKFLOW_CALL_JOB__USES);
		createEReference(workflowCallJobEClass, WORKFLOW_CALL_JOB__ARGS);
		createEReference(workflowCallJobEClass, WORKFLOW_CALL_JOB__SECRETS);
		createEAttribute(workflowCallJobEClass, WORKFLOW_CALL_JOB__INHERIT_SECRETS);

		agentEClass = createEClass(AGENT);
		createEReference(agentEClass, AGENT__GROUP);
		createEReference(agentEClass, AGENT__LABELS);

		containerEClass = createEClass(CONTAINER);
		createEReference(containerEClass, CONTAINER__IMAGE);
		createEReference(containerEClass, CONTAINER__USERNAME);
		createEReference(containerEClass, CONTAINER__PASSWORD);
		createEReference(containerEClass, CONTAINER__ENVIRONMENT_VARIABLES);
		createEReference(containerEClass, CONTAINER__PORTS);
		createEReference(containerEClass, CONTAINER__VOLUMES);
		createEReference(containerEClass, CONTAINER__OPTIONS);

		serviceEClass = createEClass(SERVICE);
		createEAttribute(serviceEClass, SERVICE__KEY);
		createEReference(serviceEClass, SERVICE__VALUE);

		stagingEnvironmentEClass = createEClass(STAGING_ENVIRONMENT);
		createEReference(stagingEnvironmentEClass, STAGING_ENVIRONMENT__NAME);
		createEReference(stagingEnvironmentEClass, STAGING_ENVIRONMENT__URL);

		triggerEClass = createEClass(TRIGGER);

		eventTypeTriggerEClass = createEClass(EVENT_TYPE_TRIGGER);
		createEAttribute(eventTypeTriggerEClass, EVENT_TYPE_TRIGGER__EVENT_TYPES);

		specifiedBranchesTriggerEClass = createEClass(SPECIFIED_BRANCHES_TRIGGER);
		createEReference(specifiedBranchesTriggerEClass, SPECIFIED_BRANCHES_TRIGGER__BRANCHES);
		createEAttribute(specifiedBranchesTriggerEClass, SPECIFIED_BRANCHES_TRIGGER__IGNORE_SPECIFIED_BRANCHES);

		specifiedPathsTriggerEClass = createEClass(SPECIFIED_PATHS_TRIGGER);
		createEReference(specifiedPathsTriggerEClass, SPECIFIED_PATHS_TRIGGER__PATHS);
		createEAttribute(specifiedPathsTriggerEClass, SPECIFIED_PATHS_TRIGGER__IGNORE_SPECIFIED_PATHS);

		standardEventTriggerEClass = createEClass(STANDARD_EVENT_TRIGGER);
		createEAttribute(standardEventTriggerEClass, STANDARD_EVENT_TRIGGER__EVENT);

		inputTriggerEClass = createEClass(INPUT_TRIGGER);
		createEReference(inputTriggerEClass, INPUT_TRIGGER__INPUTS);

		workflowRunTriggerEClass = createEClass(WORKFLOW_RUN_TRIGGER);

		pullRequestTriggerEClass = createEClass(PULL_REQUEST_TRIGGER);

		pullRequestTargetTriggerEClass = createEClass(PULL_REQUEST_TARGET_TRIGGER);

		pushTriggerEClass = createEClass(PUSH_TRIGGER);
		createEReference(pushTriggerEClass, PUSH_TRIGGER__TAGS);
		createEAttribute(pushTriggerEClass, PUSH_TRIGGER__IGNORE_SPECIFIED_TAGS);

		scheduleTriggerEClass = createEClass(SCHEDULE_TRIGGER);
		createEReference(scheduleTriggerEClass, SCHEDULE_TRIGGER__CRONS);

		workflowCallTriggerEClass = createEClass(WORKFLOW_CALL_TRIGGER);
		createEReference(workflowCallTriggerEClass, WORKFLOW_CALL_TRIGGER__OUTPUTS);
		createEReference(workflowCallTriggerEClass, WORKFLOW_CALL_TRIGGER__SECRETS);

		workflowDispatchTriggerEClass = createEClass(WORKFLOW_DISPATCH_TRIGGER);

		permissionEClass = createEClass(PERMISSION);
		createEAttribute(permissionEClass, PERMISSION__KEY);
		createEAttribute(permissionEClass, PERMISSION__VALUE);

		concurrencyGroupEClass = createEClass(CONCURRENCY_GROUP);
		createEReference(concurrencyGroupEClass, CONCURRENCY_GROUP__NAME);
		createEReference(concurrencyGroupEClass, CONCURRENCY_GROUP__CANCEL_IN_PROGRESS);

		defaultsEClass = createEClass(DEFAULTS);
		createEReference(defaultsEClass, DEFAULTS__SHELL);
		createEReference(defaultsEClass, DEFAULTS__WORKING_DIRECTORY);

		expressionEClass = createEClass(EXPRESSION);

		variableDeclarationEClass = createEClass(VARIABLE_DECLARATION);
		createEAttribute(variableDeclarationEClass, VARIABLE_DECLARATION__NAME);

		variableAssignmentEClass = createEClass(VARIABLE_ASSIGNMENT);
		createEReference(variableAssignmentEClass, VARIABLE_ASSIGNMENT__KEY);
		createEReference(variableAssignmentEClass, VARIABLE_ASSIGNMENT__VALUE);

		binaryOpEClass = createEClass(BINARY_OP);
		createEReference(binaryOpEClass, BINARY_OP__LHS);
		createEReference(binaryOpEClass, BINARY_OP__RHS);

		concatEClass = createEClass(CONCAT);
		createEReference(concatEClass, CONCAT__EXPRESSIONS);

		dotOpEClass = createEClass(DOT_OP);

		equalityEClass = createEClass(EQUALITY);
		createEAttribute(equalityEClass, EQUALITY__OP);

		comparisonEClass = createEClass(COMPARISON);
		createEAttribute(comparisonEClass, COMPARISON__OP);

		logicalOpEClass = createEClass(LOGICAL_OP);

		orEClass = createEClass(OR);

		andEClass = createEClass(AND);

		unaryOpEClass = createEClass(UNARY_OP);
		createEReference(unaryOpEClass, UNARY_OP__CHILD_EXPR);

		notEClass = createEClass(NOT);

		builtInFunctionCallEClass = createEClass(BUILT_IN_FUNCTION_CALL);

		containsEClass = createEClass(CONTAINS);
		createEReference(containsEClass, CONTAINS__SEARCH);
		createEReference(containsEClass, CONTAINS__ITEM);

		startsWithEClass = createEClass(STARTS_WITH);
		createEReference(startsWithEClass, STARTS_WITH__SEARCH_STRING);
		createEReference(startsWithEClass, STARTS_WITH__SEARCH_VALUE);

		endsWithEClass = createEClass(ENDS_WITH);
		createEReference(endsWithEClass, ENDS_WITH__SEARCH_STRING);
		createEReference(endsWithEClass, ENDS_WITH__SEARCH_VALUE);

		formatEClass = createEClass(FORMAT);
		createEReference(formatEClass, FORMAT__STRING);
		createEReference(formatEClass, FORMAT__REPLACE_VALUES);

		joinEClass = createEClass(JOIN);
		createEReference(joinEClass, JOIN__ARRAY);
		createEReference(joinEClass, JOIN__SEP);

		toJSONEClass = createEClass(TO_JSON);
		createEReference(toJSONEClass, TO_JSON__VALUE);

		fromJSONEClass = createEClass(FROM_JSON);
		createEReference(fromJSONEClass, FROM_JSON__VALUE);

		hashFilesEClass = createEClass(HASH_FILES);
		createEReference(hashFilesEClass, HASH_FILES__PATH);

		statusCheckEClass = createEClass(STATUS_CHECK);

		alwaysEClass = createEClass(ALWAYS);

		successEClass = createEClass(SUCCESS);

		cancelledEClass = createEClass(CANCELLED);

		failureEClass = createEClass(FAILURE);

		valueEClass = createEClass(VALUE);

		literalEClass = createEClass(LITERAL);

		stringLiteralEClass = createEClass(STRING_LITERAL);
		createEAttribute(stringLiteralEClass, STRING_LITERAL__VALUE);

		integerLiteralEClass = createEClass(INTEGER_LITERAL);
		createEAttribute(integerLiteralEClass, INTEGER_LITERAL__VALUE);

		doubleLiteralEClass = createEClass(DOUBLE_LITERAL);
		createEAttribute(doubleLiteralEClass, DOUBLE_LITERAL__VALUE);

		booleanLiteralEClass = createEClass(BOOLEAN_LITERAL);
		createEAttribute(booleanLiteralEClass, BOOLEAN_LITERAL__VALUE);

		variableReferenceEClass = createEClass(VARIABLE_REFERENCE);
		createEReference(variableReferenceEClass, VARIABLE_REFERENCE__REFERENCE);

		gitHubContextEClass = createEClass(GIT_HUB_CONTEXT);
		createEAttribute(gitHubContextEClass, GIT_HUB_CONTEXT__CONTEXT);

		matrixEClass = createEClass(MATRIX);
		createEReference(matrixEClass, MATRIX__AXES);
		createEReference(matrixEClass, MATRIX__INCLUDES);
		createEReference(matrixEClass, MATRIX__EXCLUDES);
		createEReference(matrixEClass, MATRIX__FAIL_FAST);
		createEReference(matrixEClass, MATRIX__MAX_PARALLEL);

		matrixAxisEClass = createEClass(MATRIX_AXIS);
		createEReference(matrixAxisEClass, MATRIX_AXIS__NAME);
		createEReference(matrixAxisEClass, MATRIX_AXIS__CELLS);

		matrixCombinationEClass = createEClass(MATRIX_COMBINATION);
		createEReference(matrixCombinationEClass, MATRIX_COMBINATION__ENTRIES);

		abstractStepEClass = createEClass(ABSTRACT_STEP);

		ifStepEClass = createEClass(IF_STEP);
		createEReference(ifStepEClass, IF_STEP__IF_CONDITION);
		createEReference(ifStepEClass, IF_STEP__THEN_RUN);

		stepEClass = createEClass(STEP);
		createEAttribute(stepEClass, STEP__ID);
		createEReference(stepEClass, STEP__NAME);
		createEReference(stepEClass, STEP__TIMEOUT_MINUTES);
		createEReference(stepEClass, STEP__CONTINUE_ON_ERROR);
		createEReference(stepEClass, STEP__SHELL);
		createEReference(stepEClass, STEP__WORKING_DIRECTORY);
		createEReference(stepEClass, STEP__ENVIRONMENT_VARIABLES);

		commandEClass = createEClass(COMMAND);
		createEReference(commandEClass, COMMAND__COMMAND);

		packageEClass = createEClass(PACKAGE);
		createEReference(packageEClass, PACKAGE__USES);
		createEReference(packageEClass, PACKAGE__ARGS);
		createEReference(packageEClass, PACKAGE__ENTRYPOINT);
		createEReference(packageEClass, PACKAGE__CONTAINER_ARGS);

		parameterEClass = createEClass(PARAMETER);
		createEReference(parameterEClass, PARAMETER__ID);
		createEReference(parameterEClass, PARAMETER__DESCRIPTION);

		inputEClass = createEClass(INPUT);
		createEAttribute(inputEClass, INPUT__TYPE);
		createEAttribute(inputEClass, INPUT__IS_REQUIRED);
		createEReference(inputEClass, INPUT__DEFAULT);
		createEAttribute(inputEClass, INPUT__OPTIONS);

		secretEClass = createEClass(SECRET);
		createEAttribute(secretEClass, SECRET__IS_REQUIRED);

		outputEClass = createEClass(OUTPUT);
		createEReference(outputEClass, OUTPUT__VALUE);

		// Create enums
		eventsEEnum = createEEnum(EVENTS);
		webhooK_ACTIVITY_TYPESEEnum = createEEnum(WEBHOOK_ACTIVITY_TYPES);
		permissioN_SCOPESEEnum = createEEnum(PERMISSION_SCOPES);
		permissionsEEnum = createEEnum(PERMISSIONS);
		equalitY_OPSEEnum = createEEnum(EQUALITY_OPS);
		comparisoN_OPSEEnum = createEEnum(COMPARISON_OPS);
		contextsEEnum = createEEnum(CONTEXTS);
		inpuT_TYPESEEnum = createEEnum(INPUT_TYPES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		scriptJobEClass.getESuperTypes().add(this.getJob());
		workflowCallJobEClass.getESuperTypes().add(this.getJob());
		eventTypeTriggerEClass.getESuperTypes().add(this.getTrigger());
		specifiedBranchesTriggerEClass.getESuperTypes().add(this.getTrigger());
		specifiedPathsTriggerEClass.getESuperTypes().add(this.getTrigger());
		standardEventTriggerEClass.getESuperTypes().add(this.getEventTypeTrigger());
		inputTriggerEClass.getESuperTypes().add(this.getTrigger());
		workflowRunTriggerEClass.getESuperTypes().add(this.getSpecifiedBranchesTrigger());
		pullRequestTriggerEClass.getESuperTypes().add(this.getEventTypeTrigger());
		pullRequestTriggerEClass.getESuperTypes().add(this.getSpecifiedBranchesTrigger());
		pullRequestTriggerEClass.getESuperTypes().add(this.getSpecifiedPathsTrigger());
		pullRequestTargetTriggerEClass.getESuperTypes().add(this.getPullRequestTrigger());
		pushTriggerEClass.getESuperTypes().add(this.getSpecifiedBranchesTrigger());
		pushTriggerEClass.getESuperTypes().add(this.getSpecifiedPathsTrigger());
		scheduleTriggerEClass.getESuperTypes().add(this.getTrigger());
		workflowCallTriggerEClass.getESuperTypes().add(this.getInputTrigger());
		workflowDispatchTriggerEClass.getESuperTypes().add(this.getInputTrigger());
		variableAssignmentEClass.getESuperTypes().add(this.getExpression());
		binaryOpEClass.getESuperTypes().add(this.getExpression());
		concatEClass.getESuperTypes().add(this.getExpression());
		dotOpEClass.getESuperTypes().add(this.getBinaryOp());
		equalityEClass.getESuperTypes().add(this.getBinaryOp());
		comparisonEClass.getESuperTypes().add(this.getBinaryOp());
		logicalOpEClass.getESuperTypes().add(this.getBinaryOp());
		orEClass.getESuperTypes().add(this.getLogicalOp());
		andEClass.getESuperTypes().add(this.getLogicalOp());
		unaryOpEClass.getESuperTypes().add(this.getExpression());
		notEClass.getESuperTypes().add(this.getUnaryOp());
		builtInFunctionCallEClass.getESuperTypes().add(this.getExpression());
		containsEClass.getESuperTypes().add(this.getBuiltInFunctionCall());
		startsWithEClass.getESuperTypes().add(this.getBuiltInFunctionCall());
		endsWithEClass.getESuperTypes().add(this.getBuiltInFunctionCall());
		formatEClass.getESuperTypes().add(this.getBuiltInFunctionCall());
		joinEClass.getESuperTypes().add(this.getBuiltInFunctionCall());
		toJSONEClass.getESuperTypes().add(this.getBuiltInFunctionCall());
		fromJSONEClass.getESuperTypes().add(this.getBuiltInFunctionCall());
		hashFilesEClass.getESuperTypes().add(this.getBuiltInFunctionCall());
		statusCheckEClass.getESuperTypes().add(this.getBuiltInFunctionCall());
		alwaysEClass.getESuperTypes().add(this.getStatusCheck());
		successEClass.getESuperTypes().add(this.getStatusCheck());
		cancelledEClass.getESuperTypes().add(this.getStatusCheck());
		failureEClass.getESuperTypes().add(this.getStatusCheck());
		valueEClass.getESuperTypes().add(this.getExpression());
		literalEClass.getESuperTypes().add(this.getValue());
		stringLiteralEClass.getESuperTypes().add(this.getLiteral());
		integerLiteralEClass.getESuperTypes().add(this.getLiteral());
		doubleLiteralEClass.getESuperTypes().add(this.getLiteral());
		booleanLiteralEClass.getESuperTypes().add(this.getLiteral());
		variableReferenceEClass.getESuperTypes().add(this.getValue());
		gitHubContextEClass.getESuperTypes().add(this.getValue());
		ifStepEClass.getESuperTypes().add(this.getAbstractStep());
		stepEClass.getESuperTypes().add(this.getAbstractStep());
		commandEClass.getESuperTypes().add(this.getStep());
		packageEClass.getESuperTypes().add(this.getStep());
		inputEClass.getESuperTypes().add(this.getParameter());
		secretEClass.getESuperTypes().add(this.getParameter());
		outputEClass.getESuperTypes().add(this.getParameter());

		// Initialize classes, features, and operations; add parameters
		initEClass(workflowEClass, Workflow.class, "Workflow", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWorkflow_Name(), this.getExpression(), null, "name", null, 0, 1, Workflow.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflow_RunName(), this.getExpression(), null, "runName", null, 0, 1, Workflow.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflow_Triggers(), this.getTrigger(), null, "triggers", null, 1, -1, Workflow.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflow_Permissions(), this.getPermission(), null, "permissions", null, 0, -1,
				Workflow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflow_Defaults(), this.getDefaults(), null, "defaults", null, 0, 1, Workflow.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflow_EnvironmentVariables(), this.getVariableAssignment(), null, "environmentVariables",
				null, 0, -1, Workflow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflow_ConcurrencyGroup(), this.getConcurrencyGroup(), null, "concurrencyGroup", null, 0, 1,
				Workflow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflow_Jobs(), this.getJob(), null, "jobs", null, 1, -1, Workflow.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(jobEClass, Job.class, "Job", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJob_Name(), ecorePackage.getEString(), "name", null, 1, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_JobName(), this.getExpression(), null, "jobName", null, 0, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJob_Permissions(), this.getPermission(), null, "permissions", null, 0, -1, Job.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_DependsOn(), this.getJob(), this.getJob_NecessaryFor(), "dependsOn", null, 0, -1,
				Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_NecessaryFor(), this.getJob(), this.getJob_DependsOn(), "necessaryFor", null, 0, -1,
				Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_IfCondition(), this.getExpression(), null, "ifCondition", null, 0, 1, Job.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Agent(), this.getAgent(), null, "agent", null, 1, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJob_Container(), this.getContainer(), null, "container", null, 0, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJob_StagingEnvironment(), this.getStagingEnvironment(), null, "stagingEnvironment", null, 0,
				1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Defaults(), this.getDefaults(), null, "defaults", null, 0, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJob_EnvironmentVariables(), this.getVariableAssignment(), null, "environmentVariables", null,
				0, -1, Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Services(), this.getService(), null, "services", null, 0, -1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJob_ConcurrencyGroup(), this.getConcurrencyGroup(), null, "concurrencyGroup", null, 0, 1,
				Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_TimeoutMinutes(), this.getExpression(), null, "timeoutMinutes", null, 0, 1, Job.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_ContinueOnError(), this.getExpression(), null, "continueOnError", null, 0, 1, Job.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Strategy(), this.getMatrix(), null, "strategy", null, 0, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJob_Outputs(), this.getOutput(), null, "outputs", null, 0, -1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(scriptJobEClass, ScriptJob.class, "ScriptJob", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScriptJob_Steps(), this.getAbstractStep(), null, "steps", null, 1, -1, ScriptJob.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workflowCallJobEClass, WorkflowCallJob.class, "WorkflowCallJob", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWorkflowCallJob_Uses(), this.getExpression(), null, "uses", null, 1, 1, WorkflowCallJob.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflowCallJob_Args(), this.getVariableAssignment(), null, "args", null, 0, -1,
				WorkflowCallJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflowCallJob_Secrets(), this.getVariableAssignment(), null, "secrets", null, 0, -1,
				WorkflowCallJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkflowCallJob_InheritSecrets(), ecorePackage.getEBooleanObject(), "inheritSecrets", "false",
				1, 1, WorkflowCallJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(agentEClass, Agent.class, "Agent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAgent_Group(), this.getExpression(), null, "group", null, 0, 1, Agent.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getAgent_Labels(), this.getExpression(), null, "labels", null, 0, -1, Agent.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(containerEClass, com.mddoai.metamodel.github.githubMM.Container.class, "Container", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainer_Image(), this.getExpression(), null, "image", null, 1, 1,
				com.mddoai.metamodel.github.githubMM.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_Username(), this.getExpression(), null, "username", null, 0, 1,
				com.mddoai.metamodel.github.githubMM.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_Password(), this.getExpression(), null, "password", null, 0, 1,
				com.mddoai.metamodel.github.githubMM.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_EnvironmentVariables(), this.getVariableAssignment(), null, "environmentVariables",
				null, 0, -1, com.mddoai.metamodel.github.githubMM.Container.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_Ports(), this.getExpression(), null, "ports", null, 0, -1,
				com.mddoai.metamodel.github.githubMM.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_Volumes(), this.getExpression(), null, "volumes", null, 0, -1,
				com.mddoai.metamodel.github.githubMM.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_Options(), this.getExpression(), null, "options", null, 0, 1,
				com.mddoai.metamodel.github.githubMM.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceEClass, Map.Entry.class, "Service", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getService_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getService_Value(), this.getContainer(), null, "value", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stagingEnvironmentEClass, StagingEnvironment.class, "StagingEnvironment", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStagingEnvironment_Name(), this.getExpression(), null, "name", null, 1, 1,
				StagingEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStagingEnvironment_Url(), this.getExpression(), null, "url", null, 0, 1,
				StagingEnvironment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(triggerEClass, Trigger.class, "Trigger", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(eventTypeTriggerEClass, EventTypeTrigger.class, "EventTypeTrigger", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEventTypeTrigger_EventTypes(), this.getWEBHOOK_ACTIVITY_TYPES(), "eventTypes", null, 0, -1,
				EventTypeTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(specifiedBranchesTriggerEClass, SpecifiedBranchesTrigger.class, "SpecifiedBranchesTrigger",
				IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpecifiedBranchesTrigger_Branches(), this.getExpression(), null, "branches", null, 0, -1,
				SpecifiedBranchesTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpecifiedBranchesTrigger_IgnoreSpecifiedBranches(), ecorePackage.getEBoolean(),
				"ignoreSpecifiedBranches", "false", 1, 1, SpecifiedBranchesTrigger.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(specifiedPathsTriggerEClass, SpecifiedPathsTrigger.class, "SpecifiedPathsTrigger", IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpecifiedPathsTrigger_Paths(), this.getExpression(), null, "paths", null, 0, -1,
				SpecifiedPathsTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpecifiedPathsTrigger_IgnoreSpecifiedPaths(), ecorePackage.getEBoolean(),
				"ignoreSpecifiedPaths", "false", 1, 1, SpecifiedPathsTrigger.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(standardEventTriggerEClass, StandardEventTrigger.class, "StandardEventTrigger", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStandardEventTrigger_Event(), this.getEVENTS(), "event", null, 1, 1,
				StandardEventTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inputTriggerEClass, InputTrigger.class, "InputTrigger", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInputTrigger_Inputs(), this.getInput(), null, "inputs", null, 0, -1, InputTrigger.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workflowRunTriggerEClass, WorkflowRunTrigger.class, "WorkflowRunTrigger", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(pullRequestTriggerEClass, PullRequestTrigger.class, "PullRequestTrigger", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(pullRequestTargetTriggerEClass, PullRequestTargetTrigger.class, "PullRequestTargetTrigger",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(pushTriggerEClass, PushTrigger.class, "PushTrigger", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPushTrigger_Tags(), this.getExpression(), null, "tags", null, 0, -1, PushTrigger.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPushTrigger_IgnoreSpecifiedTags(), ecorePackage.getEBoolean(), "ignoreSpecifiedTags", "false",
				1, 1, PushTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(scheduleTriggerEClass, ScheduleTrigger.class, "ScheduleTrigger", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScheduleTrigger_Crons(), this.getExpression(), null, "crons", null, 1, -1,
				ScheduleTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workflowCallTriggerEClass, WorkflowCallTrigger.class, "WorkflowCallTrigger", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWorkflowCallTrigger_Outputs(), this.getOutput(), null, "outputs", null, 0, -1,
				WorkflowCallTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkflowCallTrigger_Secrets(), this.getSecret(), null, "secrets", null, 0, -1,
				WorkflowCallTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workflowDispatchTriggerEClass, WorkflowDispatchTrigger.class, "WorkflowDispatchTrigger",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(permissionEClass, Map.Entry.class, "Permission", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPermission_Key(), this.getPERMISSION_SCOPES(), "key", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPermission_Value(), this.getPERMISSIONS(), "value", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(concurrencyGroupEClass, ConcurrencyGroup.class, "ConcurrencyGroup", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConcurrencyGroup_Name(), this.getExpression(), null, "name", null, 1, 1,
				ConcurrencyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcurrencyGroup_CancelInProgress(), this.getExpression(), null, "cancelInProgress", null, 0,
				1, ConcurrencyGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(defaultsEClass, Defaults.class, "Defaults", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDefaults_Shell(), this.getExpression(), null, "shell", null, 0, 1, Defaults.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDefaults_WorkingDirectory(), this.getExpression(), null, "workingDirectory", null, 0, 1,
				Defaults.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(expressionEClass, Expression.class, "Expression", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(variableDeclarationEClass, VariableDeclaration.class, "VariableDeclaration", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariableDeclaration_Name(), ecorePackage.getEString(), "name", null, 1, 1,
				VariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(variableAssignmentEClass, Map.Entry.class, "VariableAssignment", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVariableAssignment_Key(), this.getVariableDeclaration(), null, "key", null, 1, 1,
				Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVariableAssignment_Value(), this.getExpression(), null, "value", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(binaryOpEClass, BinaryOp.class, "BinaryOp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBinaryOp_Lhs(), this.getExpression(), null, "lhs", null, 1, 1, BinaryOp.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getBinaryOp_Rhs(), this.getExpression(), null, "rhs", null, 1, 1, BinaryOp.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(concatEClass, Concat.class, "Concat", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConcat_Expressions(), this.getExpression(), null, "expressions", null, 1, -1, Concat.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dotOpEClass, DotOp.class, "DotOp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(equalityEClass, Equality.class, "Equality", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEquality_Op(), this.getEQUALITY_OPS(), "op", null, 1, 1, Equality.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(comparisonEClass, Comparison.class, "Comparison", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComparison_Op(), this.getCOMPARISON_OPS(), "op", null, 1, 1, Comparison.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(logicalOpEClass, LogicalOp.class, "LogicalOp", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(orEClass, Or.class, "Or", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(andEClass, And.class, "And", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unaryOpEClass, UnaryOp.class, "UnaryOp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnaryOp_ChildExpr(), this.getExpression(), null, "childExpr", null, 1, 1, UnaryOp.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(notEClass, Not.class, "Not", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(builtInFunctionCallEClass, BuiltInFunctionCall.class, "BuiltInFunctionCall", IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containsEClass, Contains.class, "Contains", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContains_Search(), this.getExpression(), null, "search", null, 1, 1, Contains.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContains_Item(), this.getExpression(), null, "item", null, 1, 1, Contains.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(startsWithEClass, StartsWith.class, "StartsWith", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStartsWith_SearchString(), this.getExpression(), null, "searchString", null, 1, 1,
				StartsWith.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStartsWith_SearchValue(), this.getExpression(), null, "searchValue", null, 1, 1,
				StartsWith.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(endsWithEClass, EndsWith.class, "EndsWith", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEndsWith_SearchString(), this.getExpression(), null, "searchString", null, 1, 1,
				EndsWith.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEndsWith_SearchValue(), this.getExpression(), null, "searchValue", null, 1, 1, EndsWith.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formatEClass, Format.class, "Format", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFormat_String(), this.getExpression(), null, "string", null, 1, 1, Format.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFormat_ReplaceValues(), this.getExpression(), null, "replaceValues", null, 0, -1,
				Format.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(joinEClass, Join.class, "Join", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getJoin_Array(), this.getExpression(), null, "array", null, 1, 1, Join.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJoin_Sep(), this.getExpression(), null, "sep", null, 0, 1, Join.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(toJSONEClass, ToJSON.class, "ToJSON", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getToJSON_Value(), this.getExpression(), null, "value", null, 1, 1, ToJSON.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(fromJSONEClass, FromJSON.class, "FromJSON", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFromJSON_Value(), this.getExpression(), null, "value", null, 1, 1, FromJSON.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hashFilesEClass, HashFiles.class, "HashFiles", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHashFiles_Path(), this.getExpression(), null, "path", null, 1, 1, HashFiles.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(statusCheckEClass, StatusCheck.class, "StatusCheck", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(alwaysEClass, Always.class, "Always", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(successEClass, Success.class, "Success", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cancelledEClass, Cancelled.class, "Cancelled", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(failureEClass, Failure.class, "Failure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(valueEClass, Value.class, "Value", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(literalEClass, Literal.class, "Literal", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringLiteralEClass, StringLiteral.class, "StringLiteral", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringLiteral_Value(), ecorePackage.getEString(), "value", null, 1, 1, StringLiteral.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerLiteralEClass, IntegerLiteral.class, "IntegerLiteral", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerLiteral_Value(), ecorePackage.getEIntegerObject(), "value", null, 1, 1,
				IntegerLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(doubleLiteralEClass, DoubleLiteral.class, "DoubleLiteral", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoubleLiteral_Value(), ecorePackage.getEDoubleObject(), "value", null, 1, 1,
				DoubleLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(booleanLiteralEClass, BooleanLiteral.class, "BooleanLiteral", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanLiteral_Value(), ecorePackage.getEBoolean(), "value", null, 1, 1, BooleanLiteral.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(variableReferenceEClass, VariableReference.class, "VariableReference", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVariableReference_Reference(), this.getVariableDeclaration(), null, "reference", null, 1, 1,
				VariableReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gitHubContextEClass, GitHubContext.class, "GitHubContext", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGitHubContext_Context(), this.getCONTEXTS(), "context", null, 1, 1, GitHubContext.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(matrixEClass, Matrix.class, "Matrix", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMatrix_Axes(), this.getMatrixAxis(), null, "axes", null, 0, -1, Matrix.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getMatrix_Includes(), this.getMatrixCombination(), null, "includes", null, 0, -1, Matrix.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMatrix_Excludes(), this.getMatrixCombination(), null, "excludes", null, 0, -1, Matrix.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMatrix_FailFast(), this.getExpression(), null, "failFast", null, 0, 1, Matrix.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMatrix_MaxParallel(), this.getExpression(), null, "maxParallel", null, 0, 1, Matrix.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(matrixAxisEClass, MatrixAxis.class, "MatrixAxis", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMatrixAxis_Name(), this.getVariableDeclaration(), null, "name", null, 1, 1, MatrixAxis.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMatrixAxis_Cells(), this.getExpression(), null, "cells", null, 1, -1, MatrixAxis.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(matrixCombinationEClass, MatrixCombination.class, "MatrixCombination", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMatrixCombination_Entries(), this.getVariableAssignment(), null, "entries", null, 0, -1,
				MatrixCombination.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractStepEClass, AbstractStep.class, "AbstractStep", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(ifStepEClass, IfStep.class, "IfStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIfStep_IfCondition(), this.getExpression(), null, "ifCondition", null, 0, 1, IfStep.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIfStep_ThenRun(), this.getStep(), null, "thenRun", null, 1, 1, IfStep.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(stepEClass, Step.class, "Step", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStep_Id(), ecorePackage.getEString(), "id", null, 0, 1, Step.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStep_Name(), this.getExpression(), null, "name", null, 0, 1, Step.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getStep_TimeoutMinutes(), this.getExpression(), null, "timeoutMinutes", null, 0, 1, Step.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStep_ContinueOnError(), this.getExpression(), null, "continueOnError", null, 0, 1, Step.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStep_Shell(), this.getExpression(), null, "shell", null, 0, 1, Step.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getStep_WorkingDirectory(), this.getExpression(), null, "workingDirectory", null, 0, 1,
				Step.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStep_EnvironmentVariables(), this.getVariableAssignment(), null, "environmentVariables", null,
				0, -1, Step.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(commandEClass, Command.class, "Command", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCommand_Command(), this.getExpression(), null, "command", null, 1, 1, Command.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageEClass, com.mddoai.metamodel.github.githubMM.Package.class, "Package", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPackage_Uses(), this.getExpression(), null, "uses", null, 1, 1,
				com.mddoai.metamodel.github.githubMM.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackage_Args(), this.getVariableAssignment(), null, "args", null, 0, -1,
				com.mddoai.metamodel.github.githubMM.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackage_Entrypoint(), this.getExpression(), null, "entrypoint", null, 0, 1,
				com.mddoai.metamodel.github.githubMM.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackage_ContainerArgs(), this.getExpression(), null, "containerArgs", null, 0, 1,
				com.mddoai.metamodel.github.githubMM.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterEClass, Parameter.class, "Parameter", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameter_Id(), this.getVariableDeclaration(), null, "id", null, 1, 1, Parameter.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParameter_Description(), this.getExpression(), null, "description", null, 0, 1,
				Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inputEClass, Input.class, "Input", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInput_Type(), this.getINPUT_TYPES(), "type", null, 1, 1, Input.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInput_IsRequired(), ecorePackage.getEBooleanObject(), "isRequired", null, 0, 1, Input.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInput_Default(), this.getExpression(), null, "default", null, 0, 1, Input.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInput_Options(), ecorePackage.getEString(), "options", null, 0, -1, Input.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(secretEClass, Secret.class, "Secret", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSecret_IsRequired(), ecorePackage.getEBooleanObject(), "isRequired", null, 0, 1, Secret.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(outputEClass, Output.class, "Output", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOutput_Value(), this.getExpression(), null, "value", null, 1, 1, Output.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.class, "EVENTS");
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.BRANCH_PROTECTION_RULE);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.CHECK_RUN);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.CHECK_SUITE);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.CREATE);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.DELETE);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.DEPLOYMENT);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.DEPLOYMENT_STATUS);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.DISCUSSION);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.DISCUSSION_COMMENT);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.FORK);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.GOLLUM);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.ISSUE_COMMENT);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.ISSUES);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.LABEL);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.MERGE_GROUP);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.MILESTONE);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.PAGE_BUILD);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.PROJECT);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.PROJECT_CARD);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.PROJECT_COLUMN);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.PUBLIC);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.PULL_REQUEST_REVIEW);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.PULL_REQUEST_REVIEW_COMMENT);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.REGISTRY_PACKAGE);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.RELEASE);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.REPOSITORY_DISPATCH);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.STATUS);
		addEEnumLiteral(eventsEEnum, com.mddoai.metamodel.github.githubMM.EVENTS.WATCH);

		initEEnum(webhooK_ACTIVITY_TYPESEEnum, com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.class,
				"WEBHOOK_ACTIVITY_TYPES");
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.ASSIGNED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.AUTO_MERGE_DISABLED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.AUTO_MERGE_ENABLED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.CLOSED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.CONVERTED_TO_DRAFT);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.EDITED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.LABELED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.LOCKED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.OPENED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.READY_FOR_REVIEW);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.REOPENED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.REVIEW_REQUEST_REMOVED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.REVIEW_REQUESTED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.SYNCHRONIZE);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.UNASSIGNED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.UNLABELED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.UNLOCKED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.CREATED);
		addEEnumLiteral(webhooK_ACTIVITY_TYPESEEnum,
				com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES.DELETED);

		initEEnum(permissioN_SCOPESEEnum, com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES.class,
				"PERMISSION_SCOPES");
		addEEnumLiteral(permissioN_SCOPESEEnum, com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES.ACTIONS);
		addEEnumLiteral(permissioN_SCOPESEEnum, com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES.CHECKS);
		addEEnumLiteral(permissioN_SCOPESEEnum, com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES.CONTENTS);
		addEEnumLiteral(permissioN_SCOPESEEnum, com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES.DEPLOYMENTS);
		addEEnumLiteral(permissioN_SCOPESEEnum, com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES.DISCUSSIONS);
		addEEnumLiteral(permissioN_SCOPESEEnum, com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES.ID_TOKEN);
		addEEnumLiteral(permissioN_SCOPESEEnum, com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES.ISSUES);
		addEEnumLiteral(permissioN_SCOPESEEnum, com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES.PACKAGES);
		addEEnumLiteral(permissioN_SCOPESEEnum, com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES.PAGES);
		addEEnumLiteral(permissioN_SCOPESEEnum, com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES.PULL_REQUESTS);
		addEEnumLiteral(permissioN_SCOPESEEnum,
				com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES.REPOSITORY_PROJECTS);
		addEEnumLiteral(permissioN_SCOPESEEnum, com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES.SECURITY_EVENTS);
		addEEnumLiteral(permissioN_SCOPESEEnum, com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES.STATUSES);

		initEEnum(permissionsEEnum, com.mddoai.metamodel.github.githubMM.PERMISSIONS.class, "PERMISSIONS");
		addEEnumLiteral(permissionsEEnum, com.mddoai.metamodel.github.githubMM.PERMISSIONS.READ);
		addEEnumLiteral(permissionsEEnum, com.mddoai.metamodel.github.githubMM.PERMISSIONS.WRITE);
		addEEnumLiteral(permissionsEEnum, com.mddoai.metamodel.github.githubMM.PERMISSIONS.NONE);

		initEEnum(equalitY_OPSEEnum, com.mddoai.metamodel.github.githubMM.EQUALITY_OPS.class, "EQUALITY_OPS");
		addEEnumLiteral(equalitY_OPSEEnum, com.mddoai.metamodel.github.githubMM.EQUALITY_OPS.EQUALS);
		addEEnumLiteral(equalitY_OPSEEnum, com.mddoai.metamodel.github.githubMM.EQUALITY_OPS.NOT_EQUALS);

		initEEnum(comparisoN_OPSEEnum, com.mddoai.metamodel.github.githubMM.COMPARISON_OPS.class, "COMPARISON_OPS");
		addEEnumLiteral(comparisoN_OPSEEnum, com.mddoai.metamodel.github.githubMM.COMPARISON_OPS.GT);
		addEEnumLiteral(comparisoN_OPSEEnum, com.mddoai.metamodel.github.githubMM.COMPARISON_OPS.GTE);
		addEEnumLiteral(comparisoN_OPSEEnum, com.mddoai.metamodel.github.githubMM.COMPARISON_OPS.LT);
		addEEnumLiteral(comparisoN_OPSEEnum, com.mddoai.metamodel.github.githubMM.COMPARISON_OPS.LTE);

		initEEnum(contextsEEnum, com.mddoai.metamodel.github.githubMM.CONTEXTS.class, "CONTEXTS");
		addEEnumLiteral(contextsEEnum, com.mddoai.metamodel.github.githubMM.CONTEXTS.GITHUB);
		addEEnumLiteral(contextsEEnum, com.mddoai.metamodel.github.githubMM.CONTEXTS.ENV);
		addEEnumLiteral(contextsEEnum, com.mddoai.metamodel.github.githubMM.CONTEXTS.VARS);
		addEEnumLiteral(contextsEEnum, com.mddoai.metamodel.github.githubMM.CONTEXTS.JOB);
		addEEnumLiteral(contextsEEnum, com.mddoai.metamodel.github.githubMM.CONTEXTS.JOBS);
		addEEnumLiteral(contextsEEnum, com.mddoai.metamodel.github.githubMM.CONTEXTS.STEPS);
		addEEnumLiteral(contextsEEnum, com.mddoai.metamodel.github.githubMM.CONTEXTS.RUNNER);
		addEEnumLiteral(contextsEEnum, com.mddoai.metamodel.github.githubMM.CONTEXTS.SECRETS);
		addEEnumLiteral(contextsEEnum, com.mddoai.metamodel.github.githubMM.CONTEXTS.STRATEGY);
		addEEnumLiteral(contextsEEnum, com.mddoai.metamodel.github.githubMM.CONTEXTS.MATRIX);
		addEEnumLiteral(contextsEEnum, com.mddoai.metamodel.github.githubMM.CONTEXTS.NEEDS);
		addEEnumLiteral(contextsEEnum, com.mddoai.metamodel.github.githubMM.CONTEXTS.INPUTS);

		initEEnum(inpuT_TYPESEEnum, com.mddoai.metamodel.github.githubMM.INPUT_TYPES.class, "INPUT_TYPES");
		addEEnumLiteral(inpuT_TYPESEEnum, com.mddoai.metamodel.github.githubMM.INPUT_TYPES.STRING);
		addEEnumLiteral(inpuT_TYPESEEnum, com.mddoai.metamodel.github.githubMM.INPUT_TYPES.NUMBER);
		addEEnumLiteral(inpuT_TYPESEEnum, com.mddoai.metamodel.github.githubMM.INPUT_TYPES.BOOLEAN);
		addEEnumLiteral(inpuT_TYPESEEnum, com.mddoai.metamodel.github.githubMM.INPUT_TYPES.ENVIRONMENT);
		addEEnumLiteral(inpuT_TYPESEEnum, com.mddoai.metamodel.github.githubMM.INPUT_TYPES.CHOICE);

		// Create resource
		createResource(eNS_URI);
	}

} //GithubMMPackageImpl
