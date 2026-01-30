/**
 */
package com.mddoai.metamodel.pim.pimMM.impl;

import com.mddoai.metamodel.pim.pimMM.*;

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
public class PimMMFactoryImpl extends EFactoryImpl implements PimMMFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PimMMFactory init() {
		try {
			PimMMFactory thePimMMFactory = (PimMMFactory) EPackage.Registry.INSTANCE.getEFactory(PimMMPackage.eNS_URI);
			if (thePimMMFactory != null) {
				return thePimMMFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PimMMFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PimMMFactoryImpl() {
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
		case PimMMPackage.PIPELINE:
			return createPipeline();
		case PimMMPackage.SCRIPT_JOB:
			return createScriptJob();
		case PimMMPackage.PIPELINE_CALL_JOB:
			return createPipelineCallJob();
		case PimMMPackage.CUSTOM_AGENT:
			return createCustomAgent();
		case PimMMPackage.LINUX_AGENT:
			return createLinuxAgent();
		case PimMMPackage.WINDOWS_AGENT:
			return createWindowsAgent();
		case PimMMPackage.MAC_OS_AGENT:
			return createMacOSAgent();
		case PimMMPackage.DOCKER_CONTAINER:
			return createDockerContainer();
		case PimMMPackage.PUSH_TRIGGER:
			return createPushTrigger();
		case PimMMPackage.PULL_REQUEST_TRIGGER:
			return createPullRequestTrigger();
		case PimMMPackage.MANUAL_TRIGGER:
			return createManualTrigger();
		case PimMMPackage.SCHEDULED_TRIGGER:
			return createScheduledTrigger();
		case PimMMPackage.MATRIX:
			return createMatrix();
		case PimMMPackage.MATRIX_AXIS:
			return createMatrixAxis();
		case PimMMPackage.MATRIX_COMBINATION:
			return createMatrixCombination();
		case PimMMPackage.INPUT:
			return createInput();
		case PimMMPackage.OUTPUT:
			return createOutput();
		case PimMMPackage.CONDITIONAL_STEP:
			return createConditionalStep();
		case PimMMPackage.COMMAND:
			return createCommand();
		case PimMMPackage.PLUGIN:
			return createPlugin();
		case PimMMPackage.CACHE:
			return createCache();
		case PimMMPackage.ARTIFACT:
			return createArtifact();
		case PimMMPackage.CHECKOUT:
			return createCheckout();
		case PimMMPackage.VARIABLE_DECLARATION:
			return createVariableDeclaration();
		case PimMMPackage.ASSIGNMENT:
			return (EObject) createAssignment();
		case PimMMPackage.CONCAT:
			return createConcat();
		case PimMMPackage.STRING_LITERAL:
			return createStringLiteral();
		case PimMMPackage.INTEGER_LITERAL:
			return createIntegerLiteral();
		case PimMMPackage.DOUBLE_LITERAL:
			return createDoubleLiteral();
		case PimMMPackage.BOOLEAN_LITERAL:
			return createBooleanLiteral();
		case PimMMPackage.VARIABLE_REFERENCE:
			return createVariableReference();
		case PimMMPackage.EQUALITY_OP:
			return createEqualityOp();
		case PimMMPackage.COMPARISON_OP:
			return createComparisonOp();
		case PimMMPackage.DOT_OP:
			return createDotOp();
		case PimMMPackage.AND:
			return createAnd();
		case PimMMPackage.OR:
			return createOr();
		case PimMMPackage.UNARY_OP:
			return createUnaryOp();
		case PimMMPackage.NEGATION:
			return createNegation();
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
		case PimMMPackage.INPUT_TYPE:
			return createINPUT_TYPEFromString(eDataType, initialValue);
		case PimMMPackage.EQUALITY_OPS:
			return createEQUALITY_OPSFromString(eDataType, initialValue);
		case PimMMPackage.COMPARISON_OPS:
			return createCOMPARISON_OPSFromString(eDataType, initialValue);
		case PimMMPackage.CACHE_MODE:
			return createCACHE_MODEFromString(eDataType, initialValue);
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
		case PimMMPackage.INPUT_TYPE:
			return convertINPUT_TYPEToString(eDataType, instanceValue);
		case PimMMPackage.EQUALITY_OPS:
			return convertEQUALITY_OPSToString(eDataType, instanceValue);
		case PimMMPackage.COMPARISON_OPS:
			return convertCOMPARISON_OPSToString(eDataType, instanceValue);
		case PimMMPackage.CACHE_MODE:
			return convertCACHE_MODEToString(eDataType, instanceValue);
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
	public PipelineCallJob createPipelineCallJob() {
		PipelineCallJobImpl pipelineCallJob = new PipelineCallJobImpl();
		return pipelineCallJob;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CustomAgent createCustomAgent() {
		CustomAgentImpl customAgent = new CustomAgentImpl();
		return customAgent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LinuxAgent createLinuxAgent() {
		LinuxAgentImpl linuxAgent = new LinuxAgentImpl();
		return linuxAgent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WindowsAgent createWindowsAgent() {
		WindowsAgentImpl windowsAgent = new WindowsAgentImpl();
		return windowsAgent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MacOSAgent createMacOSAgent() {
		MacOSAgentImpl macOSAgent = new MacOSAgentImpl();
		return macOSAgent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DockerContainer createDockerContainer() {
		DockerContainerImpl dockerContainer = new DockerContainerImpl();
		return dockerContainer;
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
	public ManualTrigger createManualTrigger() {
		ManualTriggerImpl manualTrigger = new ManualTriggerImpl();
		return manualTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScheduledTrigger createScheduledTrigger() {
		ScheduledTriggerImpl scheduledTrigger = new ScheduledTriggerImpl();
		return scheduledTrigger;
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
	public Output createOutput() {
		OutputImpl output = new OutputImpl();
		return output;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConditionalStep createConditionalStep() {
		ConditionalStepImpl conditionalStep = new ConditionalStepImpl();
		return conditionalStep;
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
	public Plugin createPlugin() {
		PluginImpl plugin = new PluginImpl();
		return plugin;
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
	public Checkout createCheckout() {
		CheckoutImpl checkout = new CheckoutImpl();
		return checkout;
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
	public Map.Entry<VariableDeclaration, Expression> createAssignment() {
		AssignmentImpl assignment = new AssignmentImpl();
		return assignment;
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
	public EqualityOp createEqualityOp() {
		EqualityOpImpl equalityOp = new EqualityOpImpl();
		return equalityOp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComparisonOp createComparisonOp() {
		ComparisonOpImpl comparisonOp = new ComparisonOpImpl();
		return comparisonOp;
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
	public UnaryOp createUnaryOp() {
		UnaryOpImpl unaryOp = new UnaryOpImpl();
		return unaryOp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Negation createNegation() {
		NegationImpl negation = new NegationImpl();
		return negation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public INPUT_TYPE createINPUT_TYPEFromString(EDataType eDataType, String initialValue) {
		INPUT_TYPE result = INPUT_TYPE.get(initialValue);
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
	public String convertINPUT_TYPEToString(EDataType eDataType, Object instanceValue) {
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
	public CACHE_MODE createCACHE_MODEFromString(EDataType eDataType, String initialValue) {
		CACHE_MODE result = CACHE_MODE.get(initialValue);
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
	public String convertCACHE_MODEToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PimMMPackage getPimMMPackage() {
		return (PimMMPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PimMMPackage getPackage() {
		return PimMMPackage.eINSTANCE;
	}

} //PimMMFactoryImpl
