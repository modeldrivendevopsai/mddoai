/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.util;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.*;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage
 * @generated
 */
public class AzuredevopsMMAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AzuredevopsMMPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AzuredevopsMMAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AzuredevopsMMPackage.eINSTANCE;
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
	protected AzuredevopsMMSwitch<Adapter> modelSwitch = new AzuredevopsMMSwitch<Adapter>() {
		@Override
		public Adapter casePipeline(Pipeline object) {
			return createPipelineAdapter();
		}

		@Override
		public Adapter caseExtends(Extends object) {
			return createExtendsAdapter();
		}

		@Override
		public Adapter caseStageElement(StageElement object) {
			return createStageElementAdapter();
		}

		@Override
		public Adapter caseStage(Stage object) {
			return createStageAdapter();
		}

		@Override
		public Adapter caseStageTemplate(StageTemplate object) {
			return createStageTemplateAdapter();
		}

		@Override
		public Adapter caseJobElement(JobElement object) {
			return createJobElementAdapter();
		}

		@Override
		public Adapter caseJob(Job object) {
			return createJobAdapter();
		}

		@Override
		public Adapter caseDeploymentJob(DeploymentJob object) {
			return createDeploymentJobAdapter();
		}

		@Override
		public Adapter caseJobTemplate(JobTemplate object) {
			return createJobTemplateAdapter();
		}

		@Override
		public Adapter caseEnvironment(Environment object) {
			return createEnvironmentAdapter();
		}

		@Override
		public Adapter caseDeploymentStrategy(DeploymentStrategy object) {
			return createDeploymentStrategyAdapter();
		}

		@Override
		public Adapter caseRunOnceStrategy(RunOnceStrategy object) {
			return createRunOnceStrategyAdapter();
		}

		@Override
		public Adapter caseRollingStrategy(RollingStrategy object) {
			return createRollingStrategyAdapter();
		}

		@Override
		public Adapter caseCanaryStrategy(CanaryStrategy object) {
			return createCanaryStrategyAdapter();
		}

		@Override
		public Adapter caseDeployHook(DeployHook object) {
			return createDeployHookAdapter();
		}

		@Override
		public Adapter caseOnSuccessOrFailureHook(OnSuccessOrFailureHook object) {
			return createOnSuccessOrFailureHookAdapter();
		}

		@Override
		public Adapter caseJobStrategy(JobStrategy object) {
			return createJobStrategyAdapter();
		}

		@Override
		public Adapter caseMatrixEntry(Map.Entry<String, EMap<String, String>> object) {
			return createMatrixEntryAdapter();
		}

		@Override
		public Adapter caseMatrixVariable(Map.Entry<String, String> object) {
			return createMatrixVariableAdapter();
		}

		@Override
		public Adapter caseJobUses(JobUses object) {
			return createJobUsesAdapter();
		}

		@Override
		public Adapter casePool(Pool object) {
			return createPoolAdapter();
		}

		@Override
		public Adapter caseWorkspace(Workspace object) {
			return createWorkspaceAdapter();
		}

		@Override
		public Adapter caseContainerReference(ContainerReference object) {
			return createContainerReferenceAdapter();
		}

		@Override
		public Adapter caseContainerAlias(ContainerAlias object) {
			return createContainerAliasAdapter();
		}

		@Override
		public Adapter caseContainerImage(ContainerImage object) {
			return createContainerImageAdapter();
		}

		@Override
		public Adapter caseMountReadOnly(MountReadOnly object) {
			return createMountReadOnlyAdapter();
		}

		@Override
		public Adapter caseServiceEntry(Map.Entry<String, String> object) {
			return createServiceEntryAdapter();
		}

		@Override
		public Adapter caseStep(Step object) {
			return createStepAdapter();
		}

		@Override
		public Adapter caseTaskStep(TaskStep object) {
			return createTaskStepAdapter();
		}

		@Override
		public Adapter caseScriptStep(ScriptStep object) {
			return createScriptStepAdapter();
		}

		@Override
		public Adapter caseBashStep(BashStep object) {
			return createBashStepAdapter();
		}

		@Override
		public Adapter casePowershellStep(PowershellStep object) {
			return createPowershellStepAdapter();
		}

		@Override
		public Adapter casePwshStep(PwshStep object) {
			return createPwshStepAdapter();
		}

		@Override
		public Adapter caseCheckoutStep(CheckoutStep object) {
			return createCheckoutStepAdapter();
		}

		@Override
		public Adapter caseDownloadStep(DownloadStep object) {
			return createDownloadStepAdapter();
		}

		@Override
		public Adapter caseDownloadBuildStep(DownloadBuildStep object) {
			return createDownloadBuildStepAdapter();
		}

		@Override
		public Adapter caseGetPackageStep(GetPackageStep object) {
			return createGetPackageStepAdapter();
		}

		@Override
		public Adapter casePublishStep(PublishStep object) {
			return createPublishStepAdapter();
		}

		@Override
		public Adapter caseReviewAppStep(ReviewAppStep object) {
			return createReviewAppStepAdapter();
		}

		@Override
		public Adapter caseStepTemplate(StepTemplate object) {
			return createStepTemplateAdapter();
		}

		@Override
		public Adapter caseStepTarget(StepTarget object) {
			return createStepTargetAdapter();
		}

		@Override
		public Adapter caseSettableVariables(SettableVariables object) {
			return createSettableVariablesAdapter();
		}

		@Override
		public Adapter caseTrigger(Trigger object) {
			return createTriggerAdapter();
		}

		@Override
		public Adapter casePrTrigger(PrTrigger object) {
			return createPrTriggerAdapter();
		}

		@Override
		public Adapter caseCronSchedule(CronSchedule object) {
			return createCronScheduleAdapter();
		}

		@Override
		public Adapter caseIncludeExcludeFilters(IncludeExcludeFilters object) {
			return createIncludeExcludeFiltersAdapter();
		}

		@Override
		public Adapter caseParameter(Parameter object) {
			return createParameterAdapter();
		}

		@Override
		public Adapter caseTemplateParameterAssignment(Map.Entry<String, String> object) {
			return createTemplateParameterAssignmentAdapter();
		}

		@Override
		public Adapter caseVariableDefinition(VariableDefinition object) {
			return createVariableDefinitionAdapter();
		}

		@Override
		public Adapter caseVariableName(VariableName object) {
			return createVariableNameAdapter();
		}

		@Override
		public Adapter caseVariableGroup(VariableGroup object) {
			return createVariableGroupAdapter();
		}

		@Override
		public Adapter caseVariableTemplate(VariableTemplate object) {
			return createVariableTemplateAdapter();
		}

		@Override
		public Adapter caseResources(Resources object) {
			return createResourcesAdapter();
		}

		@Override
		public Adapter caseBuildResource(BuildResource object) {
			return createBuildResourceAdapter();
		}

		@Override
		public Adapter caseContainerResource(ContainerResource object) {
			return createContainerResourceAdapter();
		}

		@Override
		public Adapter caseContainerResourceTrigger(ContainerResourceTrigger object) {
			return createContainerResourceTriggerAdapter();
		}

		@Override
		public Adapter casePipelineResource(PipelineResource object) {
			return createPipelineResourceAdapter();
		}

		@Override
		public Adapter casePipelineResourceTrigger(PipelineResourceTrigger object) {
			return createPipelineResourceTriggerAdapter();
		}

		@Override
		public Adapter caseRepositoryResource(RepositoryResource object) {
			return createRepositoryResourceAdapter();
		}

		@Override
		public Adapter caseWebhookResource(WebhookResource object) {
			return createWebhookResourceAdapter();
		}

		@Override
		public Adapter caseWebhookFilter(WebhookFilter object) {
			return createWebhookFilterAdapter();
		}

		@Override
		public Adapter casePackageResource(PackageResource object) {
			return createPackageResourceAdapter();
		}

		@Override
		public Adapter caseEnvEntry(Map.Entry<String, String> object) {
			return createEnvEntryAdapter();
		}

		@Override
		public Adapter caseInputEntry(Map.Entry<String, String> object) {
			return createInputEntryAdapter();
		}

		@Override
		public Adapter caseTemplateContext(TemplateContext object) {
			return createTemplateContextAdapter();
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
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline <em>Pipeline</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline
	 * @generated
	 */
	public Adapter createPipelineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Extends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Extends
	 * @generated
	 */
	public Adapter createExtendsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StageElement <em>Stage Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StageElement
	 * @generated
	 */
	public Adapter createStageElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage <em>Stage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage
	 * @generated
	 */
	public Adapter createStageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StageTemplate <em>Stage Template</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StageTemplate
	 * @generated
	 */
	public Adapter createStageTemplateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobElement <em>Job Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobElement
	 * @generated
	 */
	public Adapter createJobElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job <em>Job</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Job
	 * @generated
	 */
	public Adapter createJobAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob <em>Deployment Job</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob
	 * @generated
	 */
	public Adapter createDeploymentJobAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobTemplate <em>Job Template</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobTemplate
	 * @generated
	 */
	public Adapter createJobTemplateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment
	 * @generated
	 */
	public Adapter createEnvironmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy <em>Deployment Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy
	 * @generated
	 */
	public Adapter createDeploymentStrategyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy <em>Run Once Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy
	 * @generated
	 */
	public Adapter createRunOnceStrategyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy <em>Rolling Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy
	 * @generated
	 */
	public Adapter createRollingStrategyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy <em>Canary Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy
	 * @generated
	 */
	public Adapter createCanaryStrategyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook <em>Deploy Hook</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook
	 * @generated
	 */
	public Adapter createDeployHookAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook <em>On Success Or Failure Hook</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook
	 * @generated
	 */
	public Adapter createOnSuccessOrFailureHookAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy <em>Job Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy
	 * @generated
	 */
	public Adapter createJobStrategyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Matrix Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createMatrixEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Matrix Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createMatrixVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.JobUses <em>Job Uses</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.JobUses
	 * @generated
	 */
	public Adapter createJobUsesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool
	 * @generated
	 */
	public Adapter createPoolAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Workspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Workspace
	 * @generated
	 */
	public Adapter createWorkspaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerReference <em>Container Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerReference
	 * @generated
	 */
	public Adapter createContainerReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerAlias <em>Container Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerAlias
	 * @generated
	 */
	public Adapter createContainerAliasAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage <em>Container Image</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage
	 * @generated
	 */
	public Adapter createContainerImageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly <em>Mount Read Only</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly
	 * @generated
	 */
	public Adapter createMountReadOnlyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Service Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createServiceEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Step <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Step
	 * @generated
	 */
	public Adapter createStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.TaskStep <em>Task Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.TaskStep
	 * @generated
	 */
	public Adapter createTaskStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep <em>Script Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep
	 * @generated
	 */
	public Adapter createScriptStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep <em>Bash Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep
	 * @generated
	 */
	public Adapter createBashStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep <em>Powershell Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep
	 * @generated
	 */
	public Adapter createPowershellStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep <em>Pwsh Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep
	 * @generated
	 */
	public Adapter createPwshStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep <em>Checkout Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep
	 * @generated
	 */
	public Adapter createCheckoutStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep <em>Download Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep
	 * @generated
	 */
	public Adapter createDownloadStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep <em>Download Build Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep
	 * @generated
	 */
	public Adapter createDownloadBuildStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.GetPackageStep <em>Get Package Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.GetPackageStep
	 * @generated
	 */
	public Adapter createGetPackageStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PublishStep <em>Publish Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PublishStep
	 * @generated
	 */
	public Adapter createPublishStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ReviewAppStep <em>Review App Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ReviewAppStep
	 * @generated
	 */
	public Adapter createReviewAppStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTemplate <em>Step Template</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTemplate
	 * @generated
	 */
	public Adapter createStepTemplateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget <em>Step Target</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget
	 * @generated
	 */
	public Adapter createStepTargetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.SettableVariables <em>Settable Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.SettableVariables
	 * @generated
	 */
	public Adapter createSettableVariablesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger
	 * @generated
	 */
	public Adapter createTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger <em>Pr Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger
	 * @generated
	 */
	public Adapter createPrTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule <em>Cron Schedule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule
	 * @generated
	 */
	public Adapter createCronScheduleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters <em>Include Exclude Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters
	 * @generated
	 */
	public Adapter createIncludeExcludeFiltersAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter
	 * @generated
	 */
	public Adapter createParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Template Parameter Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createTemplateParameterAssignmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableDefinition <em>Variable Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableDefinition
	 * @generated
	 */
	public Adapter createVariableDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableName <em>Variable Name</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableName
	 * @generated
	 */
	public Adapter createVariableNameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableGroup <em>Variable Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableGroup
	 * @generated
	 */
	public Adapter createVariableGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableTemplate <em>Variable Template</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableTemplate
	 * @generated
	 */
	public Adapter createVariableTemplateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources
	 * @generated
	 */
	public Adapter createResourcesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource <em>Build Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource
	 * @generated
	 */
	public Adapter createBuildResourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource <em>Container Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource
	 * @generated
	 */
	public Adapter createContainerResourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger <em>Container Resource Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger
	 * @generated
	 */
	public Adapter createContainerResourceTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource <em>Pipeline Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource
	 * @generated
	 */
	public Adapter createPipelineResourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger <em>Pipeline Resource Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger
	 * @generated
	 */
	public Adapter createPipelineResourceTriggerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource <em>Repository Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource
	 * @generated
	 */
	public Adapter createRepositoryResourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource <em>Webhook Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource
	 * @generated
	 */
	public Adapter createWebhookResourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookFilter <em>Webhook Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookFilter
	 * @generated
	 */
	public Adapter createWebhookFilterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource <em>Package Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource
	 * @generated
	 */
	public Adapter createPackageResourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Env Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createEnvEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Input Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createInputEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.TemplateContext <em>Template Context</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.TemplateContext
	 * @generated
	 */
	public Adapter createTemplateContextAdapter() {
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

} //AzuredevopsMMAdapterFactory
