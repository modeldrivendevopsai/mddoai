/**
 */
package com.mddoai.metamodel.pim.pimMM.util;

import com.mddoai.metamodel.pim.pimMM.*;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.pim.pimMM.PimMMPackage
 * @generated
 */
public class PimMMAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PimMMPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PimMMAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PimMMPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PimMMSwitch<Adapter> modelSwitch = new PimMMSwitch<Adapter>() {
		@Override
		public Adapter casePipelineBlock(PipelineBlock object) {
			return createPipelineBlockAdapter();
		}

		@Override
		public Adapter casePipeline(Pipeline object) {
			return createPipelineAdapter();
		}

		@Override
		public Adapter caseJob(Job object) {
			return createJobAdapter();
		}

		@Override
		public Adapter caseScriptJob(ScriptJob object) {
			return createScriptJobAdapter();
		}

		@Override
		public Adapter casePipelineCallJob(PipelineCallJob object) {
			return createPipelineCallJobAdapter();
		}

		@Override
		public Adapter caseAgent(Agent object) {
			return createAgentAdapter();
		}

		@Override
		public Adapter caseCustomAgent(CustomAgent object) {
			return createCustomAgentAdapter();
		}

		@Override
		public Adapter casePresetAgent(PresetAgent object) {
			return createPresetAgentAdapter();
		}

		@Override
		public Adapter caseLinuxAgent(LinuxAgent object) {
			return createLinuxAgentAdapter();
		}

		@Override
		public Adapter caseWindowsAgent(WindowsAgent object) {
			return createWindowsAgentAdapter();
		}

		@Override
		public Adapter caseMacOSAgent(MacOSAgent object) {
			return createMacOSAgentAdapter();
		}

		@Override
		public Adapter caseDockerContainer(DockerContainer object) {
			return createDockerContainerAdapter();
		}

		@Override
		public Adapter caseTrigger(Trigger object) {
			return createTriggerAdapter();
		}

		@Override
		public Adapter casePushTrigger(PushTrigger object) {
			return createPushTriggerAdapter();
		}

		@Override
		public Adapter casePullRequestTrigger(PullRequestTrigger object) {
			return createPullRequestTriggerAdapter();
		}

		@Override
		public Adapter caseManualTrigger(ManualTrigger object) {
			return createManualTriggerAdapter();
		}

		@Override
		public Adapter caseScheduledTrigger(ScheduledTrigger object) {
			return createScheduledTriggerAdapter();
		}

		@Override
		public Adapter caseMatrix(Matrix object) {
			return createMatrixAdapter();
		}

		@Override
		public Adapter caseMatrixAxis(MatrixAxis object) {
			return createMatrixAxisAdapter();
		}

		@Override
		public Adapter caseMatrixCombination(MatrixCombination object) {
			return createMatrixCombinationAdapter();
		}

		@Override
		public Adapter caseParameter(Parameter object) {
			return createParameterAdapter();
		}

		@Override
		public Adapter caseInput(Input object) {
			return createInputAdapter();
		}

		@Override
		public Adapter caseOutput(Output object) {
			return createOutputAdapter();
		}

		@Override
		public Adapter caseStep(Step object) {
			return createStepAdapter();
		}

		@Override
		public Adapter caseConditionalStep(ConditionalStep object) {
			return createConditionalStepAdapter();
		}

		@Override
		public Adapter caseNonConditionalStep(NonConditionalStep object) {
			return createNonConditionalStepAdapter();
		}

		@Override
		public Adapter caseCommand(Command object) {
			return createCommandAdapter();
		}

		@Override
		public Adapter casePlugin(Plugin object) {
			return createPluginAdapter();
		}

		@Override
		public Adapter caseCache(Cache object) {
			return createCacheAdapter();
		}

		@Override
		public Adapter caseArtifact(Artifact object) {
			return createArtifactAdapter();
		}

		@Override
		public Adapter caseCheckout(Checkout object) {
			return createCheckoutAdapter();
		}

		@Override
		public Adapter caseVariableDeclaration(VariableDeclaration object) {
			return createVariableDeclarationAdapter();
		}

		@Override
		public Adapter caseAssignment(Map.Entry<VariableDeclaration, Expression> object) {
			return createAssignmentAdapter();
		}

		@Override
		public Adapter caseExpression(Expression object) {
			return createExpressionAdapter();
		}

		@Override
		public Adapter caseConcat(Concat object) {
			return createConcatAdapter();
		}

		@Override
		public Adapter caseValue(Value object) {
			return createValueAdapter();
		}

		@Override
		public Adapter caseLiteral(Literal object) {
			return createLiteralAdapter();
		}

		@Override
		public Adapter caseStringLiteral(StringLiteral object) {
			return createStringLiteralAdapter();
		}

		@Override
		public Adapter caseIntegerLiteral(IntegerLiteral object) {
			return createIntegerLiteralAdapter();
		}

		@Override
		public Adapter caseDoubleLiteral(DoubleLiteral object) {
			return createDoubleLiteralAdapter();
		}

		@Override
		public Adapter caseBooleanLiteral(BooleanLiteral object) {
			return createBooleanLiteralAdapter();
		}

		@Override
		public Adapter caseVariableReference(VariableReference object) {
			return createVariableReferenceAdapter();
		}

		@Override
		public Adapter caseBinaryOp(BinaryOp object) {
			return createBinaryOpAdapter();
		}

		@Override
		public Adapter caseEqualityOp(EqualityOp object) {
			return createEqualityOpAdapter();
		}

		@Override
		public Adapter caseComparisonOp(ComparisonOp object) {
			return createComparisonOpAdapter();
		}

		@Override
		public Adapter caseDotOp(DotOp object) {
			return createDotOpAdapter();
		}

		@Override
		public Adapter caseLogicalOp(LogicalOp object) {
			return createLogicalOpAdapter();
		}

		@Override
		public Adapter caseAnd(And object) {
			return createAndAdapter();
		}

		@Override
		public Adapter caseOr(Or object) {
			return createOrAdapter();
		}

		@Override
		public Adapter caseUnaryOp(UnaryOp object) {
			return createUnaryOpAdapter();
		}

		@Override
		public Adapter caseNegation(Negation object) {
			return createNegationAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.PipelineBlock <em>Pipeline Block</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.PipelineBlock
	 * @generated
	 */
	public Adapter createPipelineBlockAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Pipeline <em>Pipeline</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Pipeline
	 * @generated
	 */
	public Adapter createPipelineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Job <em>Job</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Job
	 * @generated
	 */
	public Adapter createJobAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.ScriptJob <em>Script Job</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.ScriptJob
	 * @generated
	 */
	public Adapter createScriptJobAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.PipelineCallJob <em>Pipeline Call Job</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.PipelineCallJob
	 * @generated
	 */
	public Adapter createPipelineCallJobAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Agent <em>Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Agent
	 * @generated
	 */
	public Adapter createAgentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.CustomAgent <em>Custom Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.CustomAgent
	 * @generated
	 */
	public Adapter createCustomAgentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.PresetAgent <em>Preset Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.PresetAgent
	 * @generated
	 */
	public Adapter createPresetAgentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.LinuxAgent <em>Linux Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.LinuxAgent
	 * @generated
	 */
	public Adapter createLinuxAgentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.WindowsAgent <em>Windows Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.WindowsAgent
	 * @generated
	 */
	public Adapter createWindowsAgentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.MacOSAgent <em>Mac OS Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.MacOSAgent
	 * @generated
	 */
	public Adapter createMacOSAgentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.DockerContainer <em>Docker Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.DockerContainer
	 * @generated
	 */
	public Adapter createDockerContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Trigger
	 * @generated
	 */
	public Adapter createTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.PushTrigger <em>Push Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.PushTrigger
	 * @generated
	 */
	public Adapter createPushTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.PullRequestTrigger <em>Pull Request Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.PullRequestTrigger
	 * @generated
	 */
	public Adapter createPullRequestTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.ManualTrigger <em>Manual Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.ManualTrigger
	 * @generated
	 */
	public Adapter createManualTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.ScheduledTrigger <em>Scheduled Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.ScheduledTrigger
	 * @generated
	 */
	public Adapter createScheduledTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Matrix <em>Matrix</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Matrix
	 * @generated
	 */
	public Adapter createMatrixAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.MatrixAxis <em>Matrix Axis</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.MatrixAxis
	 * @generated
	 */
	public Adapter createMatrixAxisAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.MatrixCombination <em>Matrix Combination</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.MatrixCombination
	 * @generated
	 */
	public Adapter createMatrixCombinationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Parameter
	 * @generated
	 */
	public Adapter createParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Input <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Input
	 * @generated
	 */
	public Adapter createInputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Output <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Output
	 * @generated
	 */
	public Adapter createOutputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Step <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Step
	 * @generated
	 */
	public Adapter createStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.ConditionalStep <em>Conditional Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.ConditionalStep
	 * @generated
	 */
	public Adapter createConditionalStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.NonConditionalStep <em>Non Conditional Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.NonConditionalStep
	 * @generated
	 */
	public Adapter createNonConditionalStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Command <em>Command</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Command
	 * @generated
	 */
	public Adapter createCommandAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Plugin <em>Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Plugin
	 * @generated
	 */
	public Adapter createPluginAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Cache <em>Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Cache
	 * @generated
	 */
	public Adapter createCacheAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Artifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Artifact
	 * @generated
	 */
	public Adapter createArtifactAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Checkout <em>Checkout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Checkout
	 * @generated
	 */
	public Adapter createCheckoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.VariableDeclaration <em>Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.VariableDeclaration
	 * @generated
	 */
	public Adapter createVariableDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createAssignmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Concat <em>Concat</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Concat
	 * @generated
	 */
	public Adapter createConcatAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Value
	 * @generated
	 */
	public Adapter createValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Literal <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Literal
	 * @generated
	 */
	public Adapter createLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.StringLiteral <em>String Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.StringLiteral
	 * @generated
	 */
	public Adapter createStringLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.IntegerLiteral <em>Integer Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.IntegerLiteral
	 * @generated
	 */
	public Adapter createIntegerLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.DoubleLiteral <em>Double Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.DoubleLiteral
	 * @generated
	 */
	public Adapter createDoubleLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.BooleanLiteral <em>Boolean Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.BooleanLiteral
	 * @generated
	 */
	public Adapter createBooleanLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.VariableReference <em>Variable Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.VariableReference
	 * @generated
	 */
	public Adapter createVariableReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.BinaryOp <em>Binary Op</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.BinaryOp
	 * @generated
	 */
	public Adapter createBinaryOpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.EqualityOp <em>Equality Op</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.EqualityOp
	 * @generated
	 */
	public Adapter createEqualityOpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.ComparisonOp <em>Comparison Op</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.ComparisonOp
	 * @generated
	 */
	public Adapter createComparisonOpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.DotOp <em>Dot Op</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.DotOp
	 * @generated
	 */
	public Adapter createDotOpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.LogicalOp <em>Logical Op</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.LogicalOp
	 * @generated
	 */
	public Adapter createLogicalOpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.And <em>And</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.And
	 * @generated
	 */
	public Adapter createAndAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Or <em>Or</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Or
	 * @generated
	 */
	public Adapter createOrAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.UnaryOp <em>Unary Op</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.UnaryOp
	 * @generated
	 */
	public Adapter createUnaryOpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.pim.pimMM.Negation <em>Negation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.pim.pimMM.Negation
	 * @generated
	 */
	public Adapter createNegationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //PimMMAdapterFactory
