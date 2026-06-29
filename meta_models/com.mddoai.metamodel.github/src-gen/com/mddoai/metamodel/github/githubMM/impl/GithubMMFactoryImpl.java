/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.Agent;
import com.mddoai.metamodel.github.githubMM.Always;
import com.mddoai.metamodel.github.githubMM.And;
import com.mddoai.metamodel.github.githubMM.BooleanLiteral;
import com.mddoai.metamodel.github.githubMM.COMPARISON_OPS;
import com.mddoai.metamodel.github.githubMM.CONTEXTS;
import com.mddoai.metamodel.github.githubMM.Cancelled;
import com.mddoai.metamodel.github.githubMM.Command;
import com.mddoai.metamodel.github.githubMM.Comparison;
import com.mddoai.metamodel.github.githubMM.Concat;
import com.mddoai.metamodel.github.githubMM.ConcurrencyGroup;
import com.mddoai.metamodel.github.githubMM.Contains;
import com.mddoai.metamodel.github.githubMM.Defaults;
import com.mddoai.metamodel.github.githubMM.DotOp;
import com.mddoai.metamodel.github.githubMM.DoubleLiteral;
import com.mddoai.metamodel.github.githubMM.EQUALITY_OPS;
import com.mddoai.metamodel.github.githubMM.EVENTS;
import com.mddoai.metamodel.github.githubMM.EndsWith;
import com.mddoai.metamodel.github.githubMM.Equality;
import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.Failure;
import com.mddoai.metamodel.github.githubMM.Format;
import com.mddoai.metamodel.github.githubMM.FromJSON;
import com.mddoai.metamodel.github.githubMM.GitHubContext;
import com.mddoai.metamodel.github.githubMM.GithubMMFactory;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.HashFiles;
import com.mddoai.metamodel.github.githubMM.INPUT_TYPES;
import com.mddoai.metamodel.github.githubMM.IfStep;
import com.mddoai.metamodel.github.githubMM.Input;
import com.mddoai.metamodel.github.githubMM.IntegerLiteral;
import com.mddoai.metamodel.github.githubMM.Join;
import com.mddoai.metamodel.github.githubMM.Matrix;
import com.mddoai.metamodel.github.githubMM.MatrixAxis;
import com.mddoai.metamodel.github.githubMM.MatrixCombination;
import com.mddoai.metamodel.github.githubMM.Not;
import com.mddoai.metamodel.github.githubMM.Or;
import com.mddoai.metamodel.github.githubMM.Output;
import com.mddoai.metamodel.github.githubMM.PERMISSIONS;
import com.mddoai.metamodel.github.githubMM.PERMISSION_SCOPES;
import com.mddoai.metamodel.github.githubMM.PullRequestTargetTrigger;
import com.mddoai.metamodel.github.githubMM.PullRequestTrigger;
import com.mddoai.metamodel.github.githubMM.PushTrigger;
import com.mddoai.metamodel.github.githubMM.ScheduleTrigger;
import com.mddoai.metamodel.github.githubMM.ScriptJob;
import com.mddoai.metamodel.github.githubMM.Secret;
import com.mddoai.metamodel.github.githubMM.StagingEnvironment;
import com.mddoai.metamodel.github.githubMM.StandardEventTrigger;
import com.mddoai.metamodel.github.githubMM.StartsWith;
import com.mddoai.metamodel.github.githubMM.StringLiteral;
import com.mddoai.metamodel.github.githubMM.Success;
import com.mddoai.metamodel.github.githubMM.ToJSON;
import com.mddoai.metamodel.github.githubMM.VariableDeclaration;
import com.mddoai.metamodel.github.githubMM.VariableReference;
import com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES;
import com.mddoai.metamodel.github.githubMM.Workflow;
import com.mddoai.metamodel.github.githubMM.WorkflowCallJob;
import com.mddoai.metamodel.github.githubMM.WorkflowCallTrigger;
import com.mddoai.metamodel.github.githubMM.WorkflowDispatchTrigger;
import com.mddoai.metamodel.github.githubMM.WorkflowRunTrigger;

import java.util.Map;

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
public class GithubMMFactoryImpl extends EFactoryImpl implements GithubMMFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GithubMMFactory init() {
		try {
			GithubMMFactory theGithubMMFactory = (GithubMMFactory) EPackage.Registry.INSTANCE
					.getEFactory(GithubMMPackage.eNS_URI);
			if (theGithubMMFactory != null) {
				return theGithubMMFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new GithubMMFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GithubMMFactoryImpl() {
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
		case GithubMMPackage.WORKFLOW:
			return createWorkflow();
		case GithubMMPackage.SCRIPT_JOB:
			return createScriptJob();
		case GithubMMPackage.WORKFLOW_CALL_JOB:
			return createWorkflowCallJob();
		case GithubMMPackage.AGENT:
			return createAgent();
		case GithubMMPackage.CONTAINER:
			return createContainer();
		case GithubMMPackage.SERVICE:
			return (EObject) createService();
		case GithubMMPackage.STAGING_ENVIRONMENT:
			return createStagingEnvironment();
		case GithubMMPackage.STANDARD_EVENT_TRIGGER:
			return createStandardEventTrigger();
		case GithubMMPackage.WORKFLOW_RUN_TRIGGER:
			return createWorkflowRunTrigger();
		case GithubMMPackage.PULL_REQUEST_TRIGGER:
			return createPullRequestTrigger();
		case GithubMMPackage.PULL_REQUEST_TARGET_TRIGGER:
			return createPullRequestTargetTrigger();
		case GithubMMPackage.PUSH_TRIGGER:
			return createPushTrigger();
		case GithubMMPackage.SCHEDULE_TRIGGER:
			return createScheduleTrigger();
		case GithubMMPackage.WORKFLOW_CALL_TRIGGER:
			return createWorkflowCallTrigger();
		case GithubMMPackage.WORKFLOW_DISPATCH_TRIGGER:
			return createWorkflowDispatchTrigger();
		case GithubMMPackage.PERMISSION:
			return (EObject) createPermission();
		case GithubMMPackage.CONCURRENCY_GROUP:
			return createConcurrencyGroup();
		case GithubMMPackage.DEFAULTS:
			return createDefaults();
		case GithubMMPackage.VARIABLE_DECLARATION:
			return createVariableDeclaration();
		case GithubMMPackage.VARIABLE_ASSIGNMENT:
			return (EObject) createVariableAssignment();
		case GithubMMPackage.CONCAT:
			return createConcat();
		case GithubMMPackage.DOT_OP:
			return createDotOp();
		case GithubMMPackage.EQUALITY:
			return createEquality();
		case GithubMMPackage.COMPARISON:
			return createComparison();
		case GithubMMPackage.OR:
			return createOr();
		case GithubMMPackage.AND:
			return createAnd();
		case GithubMMPackage.NOT:
			return createNot();
		case GithubMMPackage.CONTAINS:
			return createContains();
		case GithubMMPackage.STARTS_WITH:
			return createStartsWith();
		case GithubMMPackage.ENDS_WITH:
			return createEndsWith();
		case GithubMMPackage.FORMAT:
			return createFormat();
		case GithubMMPackage.JOIN:
			return createJoin();
		case GithubMMPackage.TO_JSON:
			return createToJSON();
		case GithubMMPackage.FROM_JSON:
			return createFromJSON();
		case GithubMMPackage.HASH_FILES:
			return createHashFiles();
		case GithubMMPackage.ALWAYS:
			return createAlways();
		case GithubMMPackage.SUCCESS:
			return createSuccess();
		case GithubMMPackage.CANCELLED:
			return createCancelled();
		case GithubMMPackage.FAILURE:
			return createFailure();
		case GithubMMPackage.STRING_LITERAL:
			return createStringLiteral();
		case GithubMMPackage.INTEGER_LITERAL:
			return createIntegerLiteral();
		case GithubMMPackage.DOUBLE_LITERAL:
			return createDoubleLiteral();
		case GithubMMPackage.BOOLEAN_LITERAL:
			return createBooleanLiteral();
		case GithubMMPackage.VARIABLE_REFERENCE:
			return createVariableReference();
		case GithubMMPackage.GIT_HUB_CONTEXT:
			return createGitHubContext();
		case GithubMMPackage.MATRIX:
			return createMatrix();
		case GithubMMPackage.MATRIX_AXIS:
			return createMatrixAxis();
		case GithubMMPackage.MATRIX_COMBINATION:
			return createMatrixCombination();
		case GithubMMPackage.IF_STEP:
			return createIfStep();
		case GithubMMPackage.COMMAND:
			return createCommand();
		case GithubMMPackage.PACKAGE:
			return createPackage();
		case GithubMMPackage.INPUT:
			return createInput();
		case GithubMMPackage.SECRET:
			return createSecret();
		case GithubMMPackage.OUTPUT:
			return createOutput();
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
		case GithubMMPackage.EVENTS:
			return createEVENTSFromString(eDataType, initialValue);
		case GithubMMPackage.WEBHOOK_ACTIVITY_TYPES:
			return createWEBHOOK_ACTIVITY_TYPESFromString(eDataType, initialValue);
		case GithubMMPackage.PERMISSION_SCOPES:
			return createPERMISSION_SCOPESFromString(eDataType, initialValue);
		case GithubMMPackage.PERMISSIONS:
			return createPERMISSIONSFromString(eDataType, initialValue);
		case GithubMMPackage.EQUALITY_OPS:
			return createEQUALITY_OPSFromString(eDataType, initialValue);
		case GithubMMPackage.COMPARISON_OPS:
			return createCOMPARISON_OPSFromString(eDataType, initialValue);
		case GithubMMPackage.CONTEXTS:
			return createCONTEXTSFromString(eDataType, initialValue);
		case GithubMMPackage.INPUT_TYPES:
			return createINPUT_TYPESFromString(eDataType, initialValue);
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
		case GithubMMPackage.EVENTS:
			return convertEVENTSToString(eDataType, instanceValue);
		case GithubMMPackage.WEBHOOK_ACTIVITY_TYPES:
			return convertWEBHOOK_ACTIVITY_TYPESToString(eDataType, instanceValue);
		case GithubMMPackage.PERMISSION_SCOPES:
			return convertPERMISSION_SCOPESToString(eDataType, instanceValue);
		case GithubMMPackage.PERMISSIONS:
			return convertPERMISSIONSToString(eDataType, instanceValue);
		case GithubMMPackage.EQUALITY_OPS:
			return convertEQUALITY_OPSToString(eDataType, instanceValue);
		case GithubMMPackage.COMPARISON_OPS:
			return convertCOMPARISON_OPSToString(eDataType, instanceValue);
		case GithubMMPackage.CONTEXTS:
			return convertCONTEXTSToString(eDataType, instanceValue);
		case GithubMMPackage.INPUT_TYPES:
			return convertINPUT_TYPESToString(eDataType, instanceValue);
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
	public ScriptJob createScriptJob() {
		ScriptJobImpl scriptJob = new ScriptJobImpl();
		return scriptJob;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WorkflowCallJob createWorkflowCallJob() {
		WorkflowCallJobImpl workflowCallJob = new WorkflowCallJobImpl();
		return workflowCallJob;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Agent createAgent() {
		AgentImpl agent = new AgentImpl();
		return agent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public com.mddoai.metamodel.github.githubMM.Container createContainer() {
		ContainerImpl container = new ContainerImpl();
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, com.mddoai.metamodel.github.githubMM.Container> createService() {
		ServiceImpl service = new ServiceImpl();
		return service;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StagingEnvironment createStagingEnvironment() {
		StagingEnvironmentImpl stagingEnvironment = new StagingEnvironmentImpl();
		return stagingEnvironment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StandardEventTrigger createStandardEventTrigger() {
		StandardEventTriggerImpl standardEventTrigger = new StandardEventTriggerImpl();
		return standardEventTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WorkflowRunTrigger createWorkflowRunTrigger() {
		WorkflowRunTriggerImpl workflowRunTrigger = new WorkflowRunTriggerImpl();
		return workflowRunTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PullRequestTrigger createPullRequestTrigger() {
		PullRequestTriggerImpl pullRequestTrigger = new PullRequestTriggerImpl();
		return pullRequestTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PullRequestTargetTrigger createPullRequestTargetTrigger() {
		PullRequestTargetTriggerImpl pullRequestTargetTrigger = new PullRequestTargetTriggerImpl();
		return pullRequestTargetTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PushTrigger createPushTrigger() {
		PushTriggerImpl pushTrigger = new PushTriggerImpl();
		return pushTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScheduleTrigger createScheduleTrigger() {
		ScheduleTriggerImpl scheduleTrigger = new ScheduleTriggerImpl();
		return scheduleTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WorkflowCallTrigger createWorkflowCallTrigger() {
		WorkflowCallTriggerImpl workflowCallTrigger = new WorkflowCallTriggerImpl();
		return workflowCallTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WorkflowDispatchTrigger createWorkflowDispatchTrigger() {
		WorkflowDispatchTriggerImpl workflowDispatchTrigger = new WorkflowDispatchTriggerImpl();
		return workflowDispatchTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<PERMISSION_SCOPES, PERMISSIONS> createPermission() {
		PermissionImpl permission = new PermissionImpl();
		return permission;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConcurrencyGroup createConcurrencyGroup() {
		ConcurrencyGroupImpl concurrencyGroup = new ConcurrencyGroupImpl();
		return concurrencyGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Defaults createDefaults() {
		DefaultsImpl defaults = new DefaultsImpl();
		return defaults;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VariableDeclaration createVariableDeclaration() {
		VariableDeclarationImpl variableDeclaration = new VariableDeclarationImpl();
		return variableDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<VariableDeclaration, Expression> createVariableAssignment() {
		VariableAssignmentImpl variableAssignment = new VariableAssignmentImpl();
		return variableAssignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Concat createConcat() {
		ConcatImpl concat = new ConcatImpl();
		return concat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DotOp createDotOp() {
		DotOpImpl dotOp = new DotOpImpl();
		return dotOp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Equality createEquality() {
		EqualityImpl equality = new EqualityImpl();
		return equality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Comparison createComparison() {
		ComparisonImpl comparison = new ComparisonImpl();
		return comparison;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Or createOr() {
		OrImpl or = new OrImpl();
		return or;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public And createAnd() {
		AndImpl and = new AndImpl();
		return and;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Not createNot() {
		NotImpl not = new NotImpl();
		return not;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Contains createContains() {
		ContainsImpl contains = new ContainsImpl();
		return contains;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StartsWith createStartsWith() {
		StartsWithImpl startsWith = new StartsWithImpl();
		return startsWith;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EndsWith createEndsWith() {
		EndsWithImpl endsWith = new EndsWithImpl();
		return endsWith;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Format createFormat() {
		FormatImpl format = new FormatImpl();
		return format;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Join createJoin() {
		JoinImpl join = new JoinImpl();
		return join;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ToJSON createToJSON() {
		ToJSONImpl toJSON = new ToJSONImpl();
		return toJSON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FromJSON createFromJSON() {
		FromJSONImpl fromJSON = new FromJSONImpl();
		return fromJSON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HashFiles createHashFiles() {
		HashFilesImpl hashFiles = new HashFilesImpl();
		return hashFiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Always createAlways() {
		AlwaysImpl always = new AlwaysImpl();
		return always;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Success createSuccess() {
		SuccessImpl success = new SuccessImpl();
		return success;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Cancelled createCancelled() {
		CancelledImpl cancelled = new CancelledImpl();
		return cancelled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Failure createFailure() {
		FailureImpl failure = new FailureImpl();
		return failure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StringLiteral createStringLiteral() {
		StringLiteralImpl stringLiteral = new StringLiteralImpl();
		return stringLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntegerLiteral createIntegerLiteral() {
		IntegerLiteralImpl integerLiteral = new IntegerLiteralImpl();
		return integerLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoubleLiteral createDoubleLiteral() {
		DoubleLiteralImpl doubleLiteral = new DoubleLiteralImpl();
		return doubleLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BooleanLiteral createBooleanLiteral() {
		BooleanLiteralImpl booleanLiteral = new BooleanLiteralImpl();
		return booleanLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VariableReference createVariableReference() {
		VariableReferenceImpl variableReference = new VariableReferenceImpl();
		return variableReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GitHubContext createGitHubContext() {
		GitHubContextImpl gitHubContext = new GitHubContextImpl();
		return gitHubContext;
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
	public MatrixAxis createMatrixAxis() {
		MatrixAxisImpl matrixAxis = new MatrixAxisImpl();
		return matrixAxis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MatrixCombination createMatrixCombination() {
		MatrixCombinationImpl matrixCombination = new MatrixCombinationImpl();
		return matrixCombination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IfStep createIfStep() {
		IfStepImpl ifStep = new IfStepImpl();
		return ifStep;
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
	public com.mddoai.metamodel.github.githubMM.Package createPackage() {
		PackageImpl package_ = new PackageImpl();
		return package_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Input createInput() {
		InputImpl input = new InputImpl();
		return input;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Secret createSecret() {
		SecretImpl secret = new SecretImpl();
		return secret;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Output createOutput() {
		OutputImpl output = new OutputImpl();
		return output;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EVENTS createEVENTSFromString(EDataType eDataType, String initialValue) {
		EVENTS result = EVENTS.get(initialValue);
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
	public String convertEVENTSToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WEBHOOK_ACTIVITY_TYPES createWEBHOOK_ACTIVITY_TYPESFromString(EDataType eDataType, String initialValue) {
		WEBHOOK_ACTIVITY_TYPES result = WEBHOOK_ACTIVITY_TYPES.get(initialValue);
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
	public String convertWEBHOOK_ACTIVITY_TYPESToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PERMISSION_SCOPES createPERMISSION_SCOPESFromString(EDataType eDataType, String initialValue) {
		PERMISSION_SCOPES result = PERMISSION_SCOPES.get(initialValue);
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
	public String convertPERMISSION_SCOPESToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PERMISSIONS createPERMISSIONSFromString(EDataType eDataType, String initialValue) {
		PERMISSIONS result = PERMISSIONS.get(initialValue);
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
	public String convertPERMISSIONSToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EQUALITY_OPS createEQUALITY_OPSFromString(EDataType eDataType, String initialValue) {
		EQUALITY_OPS result = EQUALITY_OPS.get(initialValue);
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
	public String convertEQUALITY_OPSToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COMPARISON_OPS createCOMPARISON_OPSFromString(EDataType eDataType, String initialValue) {
		COMPARISON_OPS result = COMPARISON_OPS.get(initialValue);
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
	public String convertCOMPARISON_OPSToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CONTEXTS createCONTEXTSFromString(EDataType eDataType, String initialValue) {
		CONTEXTS result = CONTEXTS.get(initialValue);
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
	public String convertCONTEXTSToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public INPUT_TYPES createINPUT_TYPESFromString(EDataType eDataType, String initialValue) {
		INPUT_TYPES result = INPUT_TYPES.get(initialValue);
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
	public String convertINPUT_TYPESToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GithubMMPackage getGithubMMPackage() {
		return (GithubMMPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static GithubMMPackage getPackage() {
		return GithubMMPackage.eINSTANCE;
	}

} //GithubMMFactoryImpl
