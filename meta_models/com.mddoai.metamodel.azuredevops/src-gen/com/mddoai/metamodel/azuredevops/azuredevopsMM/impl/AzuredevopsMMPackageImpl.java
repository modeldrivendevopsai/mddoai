/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMFactory;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.BashStep;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.BuildResource;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.CanaryStrategy;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerAlias;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerReference;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResourceTrigger;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.CronSchedule;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.DeployHook;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentJob;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.DeploymentStrategy;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadBuildStep;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Environment;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Extends;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.GetPackageStep;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.IncludeExcludeFilters;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Job;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.JobElement;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.JobStrategy;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.JobTemplate;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.JobUses;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.MountReadOnly;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.OnSuccessOrFailureHook;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PackageResource;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Parameter;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResource;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PipelineResourceTrigger;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Pool;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PowershellStep;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PrTrigger;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PublishStep;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.PwshStep;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.RepositoryResource;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Resources;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ReviewAppStep;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.RollingStrategy;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.RunOnceStrategy;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ScriptStep;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.SettableVariables;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Stage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.StageElement;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.StageTemplate;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Step;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTarget;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.StepTemplate;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.TaskStep;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.TemplateContext;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Trigger;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableDefinition;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableGroup;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableName;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.VariableTemplate;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookFilter;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.WebhookResource;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Workspace;

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
public class AzuredevopsMMPackageImpl extends EPackageImpl implements AzuredevopsMMPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pipelineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stageElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stageTemplateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jobElementEClass = null;

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
	private EClass deploymentJobEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jobTemplateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deploymentStrategyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass runOnceStrategyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rollingStrategyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass canaryStrategyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deployHookEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass onSuccessOrFailureHookEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jobStrategyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass matrixEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass matrixVariableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass jobUsesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass poolEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workspaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerAliasEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerImageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mountReadOnlyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceEntryEClass = null;

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
	private EClass taskStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scriptStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bashStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass powershellStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pwshStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass checkoutStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass downloadStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass downloadBuildStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass getPackageStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass publishStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reviewAppStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stepTemplateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stepTargetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass settableVariablesEClass = null;

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
	private EClass prTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cronScheduleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass includeExcludeFiltersEClass = null;

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
	private EClass templateParameterAssignmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableNameEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableTemplateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourcesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass buildResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerResourceTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pipelineResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pipelineResourceTriggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass repositoryResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass webhookResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass webhookFilterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass envEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inputEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateContextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum locK_BEHAVIOREEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum stagE_TRIGGEREEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum workspacE_CLEANEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum parameteR_TYPEEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum repositorY_TYPEEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum targeT_COMMANDSEEnum = null;

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
	 * @see com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AzuredevopsMMPackageImpl() {
		super(eNS_URI, AzuredevopsMMFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link AzuredevopsMMPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AzuredevopsMMPackage init() {
		if (isInited)
			return (AzuredevopsMMPackage) EPackage.Registry.INSTANCE.getEPackage(AzuredevopsMMPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredAzuredevopsMMPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		AzuredevopsMMPackageImpl theAzuredevopsMMPackage = registeredAzuredevopsMMPackage instanceof AzuredevopsMMPackageImpl
				? (AzuredevopsMMPackageImpl) registeredAzuredevopsMMPackage
				: new AzuredevopsMMPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theAzuredevopsMMPackage.createPackageContents();

		// Initialize created meta-data
		theAzuredevopsMMPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAzuredevopsMMPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AzuredevopsMMPackage.eNS_URI, theAzuredevopsMMPackage);
		return theAzuredevopsMMPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPipeline() {
		return pipelineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPipeline_Name() {
		return (EAttribute) pipelineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPipeline_AppendCommitMessageToRunName() {
		return (EAttribute) pipelineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPipeline_LockBehavior() {
		return (EAttribute) pipelineEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPipeline_Trigger() {
		return (EReference) pipelineEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPipeline_Pr() {
		return (EReference) pipelineEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPipeline_Schedules() {
		return (EReference) pipelineEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPipeline_Parameters() {
		return (EReference) pipelineEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPipeline_Variables() {
		return (EReference) pipelineEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPipeline_Pool() {
		return (EReference) pipelineEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPipeline_Resources() {
		return (EReference) pipelineEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPipeline_Stages() {
		return (EReference) pipelineEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPipeline_Jobs() {
		return (EReference) pipelineEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPipeline_Steps() {
		return (EReference) pipelineEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPipeline_Extends() {
		return (EReference) pipelineEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPipeline_Workspace() {
		return (EReference) pipelineEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExtends() {
		return extendsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getExtends_Template() {
		return (EAttribute) extendsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getExtends_Parameters() {
		return (EReference) extendsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStageElement() {
		return stageElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStage() {
		return stageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStage_Stage() {
		return (EAttribute) stageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStage_DisplayName() {
		return (EAttribute) stageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStage_Condition() {
		return (EAttribute) stageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStage_LockBehavior() {
		return (EAttribute) stageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStage_Trigger() {
		return (EAttribute) stageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStage_IsSkippable() {
		return (EAttribute) stageEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStage_DependsOn() {
		return (EAttribute) stageEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStage_Pool() {
		return (EReference) stageEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStage_Variables() {
		return (EReference) stageEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStage_Jobs() {
		return (EReference) stageEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStage_TemplateContext() {
		return (EReference) stageEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStageTemplate() {
		return stageTemplateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStageTemplate_Template() {
		return (EAttribute) stageTemplateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStageTemplate_Parameters() {
		return (EReference) stageTemplateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getJobElement() {
		return jobElementEClass;
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
	public EAttribute getJob_Job() {
		return (EAttribute) jobEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getJob_DisplayName() {
		return (EAttribute) jobEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getJob_Condition() {
		return (EAttribute) jobEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getJob_ContinueOnError() {
		return (EAttribute) jobEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getJob_TimeoutInMinutes() {
		return (EAttribute) jobEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getJob_CancelTimeoutInMinutes() {
		return (EAttribute) jobEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getJob_DependsOn() {
		return (EAttribute) jobEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Pool() {
		return (EReference) jobEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Variables() {
		return (EReference) jobEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Strategy() {
		return (EReference) jobEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Container() {
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
	public EReference getJob_Workspace() {
		return (EReference) jobEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Uses() {
		return (EReference) jobEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_Steps() {
		return (EReference) jobEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJob_TemplateContext() {
		return (EReference) jobEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDeploymentJob() {
		return deploymentJobEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeploymentJob_Deployment() {
		return (EAttribute) deploymentJobEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeploymentJob_DisplayName() {
		return (EAttribute) deploymentJobEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeploymentJob_Condition() {
		return (EAttribute) deploymentJobEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeploymentJob_ContinueOnError() {
		return (EAttribute) deploymentJobEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeploymentJob_TimeoutInMinutes() {
		return (EAttribute) deploymentJobEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeploymentJob_CancelTimeoutInMinutes() {
		return (EAttribute) deploymentJobEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeploymentJob_DependsOn() {
		return (EAttribute) deploymentJobEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentJob_Pool() {
		return (EReference) deploymentJobEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentJob_Variables() {
		return (EReference) deploymentJobEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentJob_Environment() {
		return (EReference) deploymentJobEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentJob_Strategy() {
		return (EReference) deploymentJobEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentJob_Container() {
		return (EReference) deploymentJobEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentJob_Services() {
		return (EReference) deploymentJobEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentJob_Workspace() {
		return (EReference) deploymentJobEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentJob_Uses() {
		return (EReference) deploymentJobEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentJob_TemplateContext() {
		return (EReference) deploymentJobEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getJobTemplate() {
		return jobTemplateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getJobTemplate_Template() {
		return (EAttribute) jobTemplateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJobTemplate_Parameters() {
		return (EReference) jobTemplateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEnvironment() {
		return environmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEnvironment_Name() {
		return (EAttribute) environmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEnvironment_ResourceName() {
		return (EAttribute) environmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEnvironment_ResourceId() {
		return (EAttribute) environmentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEnvironment_ResourceType() {
		return (EAttribute) environmentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEnvironment_Tags() {
		return (EAttribute) environmentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDeploymentStrategy() {
		return deploymentStrategyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentStrategy_RunOnce() {
		return (EReference) deploymentStrategyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentStrategy_Rolling() {
		return (EReference) deploymentStrategyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeploymentStrategy_Canary() {
		return (EReference) deploymentStrategyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRunOnceStrategy() {
		return runOnceStrategyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRunOnceStrategy_PreDeploy() {
		return (EReference) runOnceStrategyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRunOnceStrategy_Deploy() {
		return (EReference) runOnceStrategyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRunOnceStrategy_RouteTraffic() {
		return (EReference) runOnceStrategyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRunOnceStrategy_PostRouteTraffic() {
		return (EReference) runOnceStrategyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRunOnceStrategy_On() {
		return (EReference) runOnceStrategyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRollingStrategy() {
		return rollingStrategyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRollingStrategy_MaxParallel() {
		return (EAttribute) rollingStrategyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRollingStrategy_PreDeploy() {
		return (EReference) rollingStrategyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRollingStrategy_Deploy() {
		return (EReference) rollingStrategyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRollingStrategy_RouteTraffic() {
		return (EReference) rollingStrategyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRollingStrategy_PostRouteTraffic() {
		return (EReference) rollingStrategyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRollingStrategy_On() {
		return (EReference) rollingStrategyEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCanaryStrategy() {
		return canaryStrategyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCanaryStrategy_Increments() {
		return (EAttribute) canaryStrategyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCanaryStrategy_PreDeploy() {
		return (EReference) canaryStrategyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCanaryStrategy_Deploy() {
		return (EReference) canaryStrategyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCanaryStrategy_RouteTraffic() {
		return (EReference) canaryStrategyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCanaryStrategy_PostRouteTraffic() {
		return (EReference) canaryStrategyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCanaryStrategy_On() {
		return (EReference) canaryStrategyEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDeployHook() {
		return deployHookEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeployHook_Pool() {
		return (EReference) deployHookEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeployHook_Steps() {
		return (EReference) deployHookEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOnSuccessOrFailureHook() {
		return onSuccessOrFailureHookEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOnSuccessOrFailureHook_Failure() {
		return (EReference) onSuccessOrFailureHookEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOnSuccessOrFailureHook_Success() {
		return (EReference) onSuccessOrFailureHookEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getJobStrategy() {
		return jobStrategyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getJobStrategy_Parallel() {
		return (EAttribute) jobStrategyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getJobStrategy_MaxParallel() {
		return (EAttribute) jobStrategyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getJobStrategy_Matrix() {
		return (EReference) jobStrategyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMatrixEntry() {
		return matrixEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMatrixEntry_Key() {
		return (EAttribute) matrixEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMatrixEntry_Value() {
		return (EReference) matrixEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMatrixVariable() {
		return matrixVariableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMatrixVariable_Key() {
		return (EAttribute) matrixVariableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMatrixVariable_Value() {
		return (EAttribute) matrixVariableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getJobUses() {
		return jobUsesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getJobUses_Repositories() {
		return (EAttribute) jobUsesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getJobUses_Pools() {
		return (EAttribute) jobUsesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPool() {
		return poolEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPool_Name() {
		return (EAttribute) poolEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPool_VmImage() {
		return (EAttribute) poolEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPool_Demands() {
		return (EAttribute) poolEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWorkspace() {
		return workspaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWorkspace_Clean() {
		return (EAttribute) workspaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContainerReference() {
		return containerReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContainerAlias() {
		return containerAliasEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerAlias_Alias() {
		return (EAttribute) containerAliasEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContainerImage() {
		return containerImageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerImage_Image() {
		return (EAttribute) containerImageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerImage_Endpoint() {
		return (EAttribute) containerImageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerImage_Options() {
		return (EAttribute) containerImageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerImage_Ports() {
		return (EAttribute) containerImageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerImage_Volumes() {
		return (EAttribute) containerImageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerImage_MapDockerSocket() {
		return (EAttribute) containerImageEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainerImage_Env() {
		return (EReference) containerImageEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainerImage_MountReadOnly() {
		return (EReference) containerImageEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMountReadOnly() {
		return mountReadOnlyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMountReadOnly_Work() {
		return (EAttribute) mountReadOnlyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMountReadOnly_Externals() {
		return (EAttribute) mountReadOnlyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMountReadOnly_Tools() {
		return (EAttribute) mountReadOnlyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMountReadOnly_Tasks() {
		return (EAttribute) mountReadOnlyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getServiceEntry() {
		return serviceEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getServiceEntry_Key() {
		return (EAttribute) serviceEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getServiceEntry_Value() {
		return (EAttribute) serviceEntryEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getStep_Name() {
		return (EAttribute) stepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStep_DisplayName() {
		return (EAttribute) stepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStep_Condition() {
		return (EAttribute) stepEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStep_ContinueOnError() {
		return (EAttribute) stepEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStep_Enabled() {
		return (EAttribute) stepEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStep_TimeoutInMinutes() {
		return (EAttribute) stepEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStep_RetryCountOnTaskFailure() {
		return (EAttribute) stepEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStep_Env() {
		return (EReference) stepEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStep_Target() {
		return (EReference) stepEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTaskStep() {
		return taskStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTaskStep_Task() {
		return (EAttribute) taskStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTaskStep_Inputs() {
		return (EReference) taskStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScriptStep() {
		return scriptStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScriptStep_Script() {
		return (EAttribute) scriptStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScriptStep_FailOnStderr() {
		return (EAttribute) scriptStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScriptStep_WorkingDirectory() {
		return (EAttribute) scriptStepEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBashStep() {
		return bashStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBashStep_Bash() {
		return (EAttribute) bashStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBashStep_FailOnStderr() {
		return (EAttribute) bashStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBashStep_WorkingDirectory() {
		return (EAttribute) bashStepEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPowershellStep() {
		return powershellStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPowershellStep_Powershell() {
		return (EAttribute) powershellStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPowershellStep_ErrorActionPreference() {
		return (EAttribute) powershellStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPowershellStep_FailOnStderr() {
		return (EAttribute) powershellStepEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPowershellStep_IgnoreLASTEXITCODE() {
		return (EAttribute) powershellStepEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPowershellStep_WorkingDirectory() {
		return (EAttribute) powershellStepEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPwshStep() {
		return pwshStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPwshStep_Pwsh() {
		return (EAttribute) pwshStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPwshStep_ErrorActionPreference() {
		return (EAttribute) pwshStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPwshStep_FailOnStderr() {
		return (EAttribute) pwshStepEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPwshStep_IgnoreLASTEXITCODE() {
		return (EAttribute) pwshStepEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPwshStep_WorkingDirectory() {
		return (EAttribute) pwshStepEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCheckoutStep() {
		return checkoutStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutStep_Checkout() {
		return (EAttribute) checkoutStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutStep_Clean() {
		return (EAttribute) checkoutStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutStep_FetchDepth() {
		return (EAttribute) checkoutStepEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutStep_FetchFilter() {
		return (EAttribute) checkoutStepEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutStep_FetchTags() {
		return (EAttribute) checkoutStepEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutStep_Lfs() {
		return (EAttribute) checkoutStepEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutStep_PersistCredentials() {
		return (EAttribute) checkoutStepEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutStep_Submodules() {
		return (EAttribute) checkoutStepEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutStep_Path() {
		return (EAttribute) checkoutStepEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutStep_SparseCheckoutDirectories() {
		return (EAttribute) checkoutStepEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutStep_SparseCheckoutPatterns() {
		return (EAttribute) checkoutStepEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCheckoutStep_WorkspaceRepo() {
		return (EAttribute) checkoutStepEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDownloadStep() {
		return downloadStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDownloadStep_Download() {
		return (EAttribute) downloadStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDownloadStep_Artifact() {
		return (EAttribute) downloadStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDownloadStep_Patterns() {
		return (EAttribute) downloadStepEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDownloadBuildStep() {
		return downloadBuildStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDownloadBuildStep_DownloadBuild() {
		return (EAttribute) downloadBuildStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDownloadBuildStep_Artifact() {
		return (EAttribute) downloadBuildStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDownloadBuildStep_Path() {
		return (EAttribute) downloadBuildStepEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDownloadBuildStep_Patterns() {
		return (EAttribute) downloadBuildStepEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGetPackageStep() {
		return getPackageStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGetPackageStep_GetPackage() {
		return (EAttribute) getPackageStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGetPackageStep_Path() {
		return (EAttribute) getPackageStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPublishStep() {
		return publishStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPublishStep_Publish() {
		return (EAttribute) publishStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPublishStep_Artifact() {
		return (EAttribute) publishStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReviewAppStep() {
		return reviewAppStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReviewAppStep_ReviewApp() {
		return (EAttribute) reviewAppStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStepTemplate() {
		return stepTemplateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStepTemplate_Template() {
		return (EAttribute) stepTemplateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStepTemplate_Parameters() {
		return (EReference) stepTemplateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStepTarget() {
		return stepTargetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStepTarget_Container() {
		return (EAttribute) stepTargetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStepTarget_Commands() {
		return (EAttribute) stepTargetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStepTarget_SettableVariables() {
		return (EReference) stepTargetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSettableVariables() {
		return settableVariablesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSettableVariables_None() {
		return (EAttribute) settableVariablesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSettableVariables_Variables() {
		return (EAttribute) settableVariablesEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getTrigger_Disabled() {
		return (EAttribute) triggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTrigger_Batch() {
		return (EAttribute) triggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTrigger_BranchList() {
		return (EAttribute) triggerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTrigger_Branches() {
		return (EReference) triggerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTrigger_Paths() {
		return (EReference) triggerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTrigger_Tags() {
		return (EReference) triggerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPrTrigger() {
		return prTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPrTrigger_Disabled() {
		return (EAttribute) prTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPrTrigger_AutoCancel() {
		return (EAttribute) prTriggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPrTrigger_Drafts() {
		return (EAttribute) prTriggerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPrTrigger_BranchList() {
		return (EAttribute) prTriggerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPrTrigger_Branches() {
		return (EReference) prTriggerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPrTrigger_Paths() {
		return (EReference) prTriggerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCronSchedule() {
		return cronScheduleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCronSchedule_Cron() {
		return (EAttribute) cronScheduleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCronSchedule_DisplayName() {
		return (EAttribute) cronScheduleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCronSchedule_Batch() {
		return (EAttribute) cronScheduleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCronSchedule_Always() {
		return (EAttribute) cronScheduleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCronSchedule_Branches() {
		return (EReference) cronScheduleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIncludeExcludeFilters() {
		return includeExcludeFiltersEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIncludeExcludeFilters_Include() {
		return (EAttribute) includeExcludeFiltersEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIncludeExcludeFilters_Exclude() {
		return (EAttribute) includeExcludeFiltersEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getParameter_Name() {
		return (EAttribute) parameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameter_DisplayName() {
		return (EAttribute) parameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameter_Type() {
		return (EAttribute) parameterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameter_Default() {
		return (EAttribute) parameterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameter_Values() {
		return (EAttribute) parameterEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTemplateParameterAssignment() {
		return templateParameterAssignmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTemplateParameterAssignment_Key() {
		return (EAttribute) templateParameterAssignmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTemplateParameterAssignment_Value() {
		return (EAttribute) templateParameterAssignmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVariableDefinition() {
		return variableDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVariableName() {
		return variableNameEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVariableName_Name() {
		return (EAttribute) variableNameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVariableName_Value() {
		return (EAttribute) variableNameEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVariableName_Readonly() {
		return (EAttribute) variableNameEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVariableGroup() {
		return variableGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVariableGroup_Group() {
		return (EAttribute) variableGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVariableTemplate() {
		return variableTemplateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVariableTemplate_Template() {
		return (EAttribute) variableTemplateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getVariableTemplate_Parameters() {
		return (EReference) variableTemplateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResources() {
		return resourcesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResources_Builds() {
		return (EReference) resourcesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResources_Containers() {
		return (EReference) resourcesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResources_Pipelines() {
		return (EReference) resourcesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResources_Repositories() {
		return (EReference) resourcesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResources_Webhooks() {
		return (EReference) resourcesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResources_Packages() {
		return (EReference) resourcesEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBuildResource() {
		return buildResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBuildResource_Build() {
		return (EAttribute) buildResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBuildResource_Type() {
		return (EAttribute) buildResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBuildResource_Connection() {
		return (EAttribute) buildResourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBuildResource_Source() {
		return (EAttribute) buildResourceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBuildResource_Version() {
		return (EAttribute) buildResourceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBuildResource_Branch() {
		return (EAttribute) buildResourceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBuildResource_Trigger() {
		return (EAttribute) buildResourceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContainerResource() {
		return containerResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResource_Container() {
		return (EAttribute) containerResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResource_Image() {
		return (EAttribute) containerResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResource_Type() {
		return (EAttribute) containerResourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResource_AzureSubscription() {
		return (EAttribute) containerResourceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResource_ResourceGroup() {
		return (EAttribute) containerResourceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResource_Registry() {
		return (EAttribute) containerResourceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResource_Repository() {
		return (EAttribute) containerResourceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResource_Endpoint() {
		return (EAttribute) containerResourceEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResource_LocalImage() {
		return (EAttribute) containerResourceEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResource_Options() {
		return (EAttribute) containerResourceEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResource_Ports() {
		return (EAttribute) containerResourceEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResource_Volumes() {
		return (EAttribute) containerResourceEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResource_MapDockerSocket() {
		return (EAttribute) containerResourceEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainerResource_Env() {
		return (EReference) containerResourceEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainerResource_MountReadOnly() {
		return (EReference) containerResourceEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainerResource_Trigger() {
		return (EReference) containerResourceEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContainerResourceTrigger() {
		return containerResourceTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResourceTrigger_Enabled() {
		return (EAttribute) containerResourceTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainerResourceTrigger_Disabled() {
		return (EAttribute) containerResourceTriggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainerResourceTrigger_Tags() {
		return (EReference) containerResourceTriggerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPipelineResource() {
		return pipelineResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPipelineResource_Pipeline() {
		return (EAttribute) pipelineResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPipelineResource_Project() {
		return (EAttribute) pipelineResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPipelineResource_Source() {
		return (EAttribute) pipelineResourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPipelineResource_Version() {
		return (EAttribute) pipelineResourceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPipelineResource_Branch() {
		return (EAttribute) pipelineResourceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPipelineResource_Tags() {
		return (EAttribute) pipelineResourceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPipelineResource_Trigger() {
		return (EReference) pipelineResourceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPipelineResourceTrigger() {
		return pipelineResourceTriggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPipelineResourceTrigger_Enabled() {
		return (EAttribute) pipelineResourceTriggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPipelineResourceTrigger_Disabled() {
		return (EAttribute) pipelineResourceTriggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPipelineResourceTrigger_Stages() {
		return (EAttribute) pipelineResourceTriggerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPipelineResourceTrigger_Tags() {
		return (EAttribute) pipelineResourceTriggerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPipelineResourceTrigger_Branches() {
		return (EReference) pipelineResourceTriggerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRepositoryResource() {
		return repositoryResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRepositoryResource_Repository() {
		return (EAttribute) repositoryResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRepositoryResource_Name() {
		return (EAttribute) repositoryResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRepositoryResource_Type() {
		return (EAttribute) repositoryResourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRepositoryResource_Endpoint() {
		return (EAttribute) repositoryResourceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRepositoryResource_Ref() {
		return (EAttribute) repositoryResourceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRepositoryResource_Trigger() {
		return (EReference) repositoryResourceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWebhookResource() {
		return webhookResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWebhookResource_Webhook() {
		return (EAttribute) webhookResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWebhookResource_Connection() {
		return (EAttribute) webhookResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWebhookResource_Type() {
		return (EAttribute) webhookResourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWebhookResource_Filters() {
		return (EReference) webhookResourceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWebhookFilter() {
		return webhookFilterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWebhookFilter_Path() {
		return (EAttribute) webhookFilterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getWebhookFilter_Value() {
		return (EAttribute) webhookFilterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPackageResource() {
		return packageResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPackageResource_PackageName() {
		return (EAttribute) packageResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPackageResource_Type() {
		return (EAttribute) packageResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPackageResource_Connection() {
		return (EAttribute) packageResourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPackageResource_Name() {
		return (EAttribute) packageResourceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPackageResource_Version() {
		return (EAttribute) packageResourceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPackageResource_Tag() {
		return (EAttribute) packageResourceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPackageResource_Trigger() {
		return (EAttribute) packageResourceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEnvEntry() {
		return envEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEnvEntry_Key() {
		return (EAttribute) envEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEnvEntry_Value() {
		return (EAttribute) envEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInputEntry() {
		return inputEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInputEntry_Key() {
		return (EAttribute) inputEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInputEntry_Value() {
		return (EAttribute) inputEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTemplateContext() {
		return templateContextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateContext_Properties() {
		return (EReference) templateContextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getLOCK_BEHAVIOR() {
		return locK_BEHAVIOREEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getSTAGE_TRIGGER() {
		return stagE_TRIGGEREEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getWORKSPACE_CLEAN() {
		return workspacE_CLEANEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getPARAMETER_TYPE() {
		return parameteR_TYPEEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getREPOSITORY_TYPE() {
		return repositorY_TYPEEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getTARGET_COMMANDS() {
		return targeT_COMMANDSEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AzuredevopsMMFactory getAzuredevopsMMFactory() {
		return (AzuredevopsMMFactory) getEFactoryInstance();
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
		pipelineEClass = createEClass(PIPELINE);
		createEAttribute(pipelineEClass, PIPELINE__NAME);
		createEAttribute(pipelineEClass, PIPELINE__APPEND_COMMIT_MESSAGE_TO_RUN_NAME);
		createEAttribute(pipelineEClass, PIPELINE__LOCK_BEHAVIOR);
		createEReference(pipelineEClass, PIPELINE__TRIGGER);
		createEReference(pipelineEClass, PIPELINE__PR);
		createEReference(pipelineEClass, PIPELINE__SCHEDULES);
		createEReference(pipelineEClass, PIPELINE__PARAMETERS);
		createEReference(pipelineEClass, PIPELINE__VARIABLES);
		createEReference(pipelineEClass, PIPELINE__POOL);
		createEReference(pipelineEClass, PIPELINE__RESOURCES);
		createEReference(pipelineEClass, PIPELINE__STAGES);
		createEReference(pipelineEClass, PIPELINE__JOBS);
		createEReference(pipelineEClass, PIPELINE__STEPS);
		createEReference(pipelineEClass, PIPELINE__EXTENDS);
		createEReference(pipelineEClass, PIPELINE__WORKSPACE);

		extendsEClass = createEClass(EXTENDS);
		createEAttribute(extendsEClass, EXTENDS__TEMPLATE);
		createEReference(extendsEClass, EXTENDS__PARAMETERS);

		stageElementEClass = createEClass(STAGE_ELEMENT);

		stageEClass = createEClass(STAGE);
		createEAttribute(stageEClass, STAGE__STAGE);
		createEAttribute(stageEClass, STAGE__DISPLAY_NAME);
		createEAttribute(stageEClass, STAGE__CONDITION);
		createEAttribute(stageEClass, STAGE__LOCK_BEHAVIOR);
		createEAttribute(stageEClass, STAGE__TRIGGER);
		createEAttribute(stageEClass, STAGE__IS_SKIPPABLE);
		createEAttribute(stageEClass, STAGE__DEPENDS_ON);
		createEReference(stageEClass, STAGE__POOL);
		createEReference(stageEClass, STAGE__VARIABLES);
		createEReference(stageEClass, STAGE__JOBS);
		createEReference(stageEClass, STAGE__TEMPLATE_CONTEXT);

		stageTemplateEClass = createEClass(STAGE_TEMPLATE);
		createEAttribute(stageTemplateEClass, STAGE_TEMPLATE__TEMPLATE);
		createEReference(stageTemplateEClass, STAGE_TEMPLATE__PARAMETERS);

		jobElementEClass = createEClass(JOB_ELEMENT);

		jobEClass = createEClass(JOB);
		createEAttribute(jobEClass, JOB__JOB);
		createEAttribute(jobEClass, JOB__DISPLAY_NAME);
		createEAttribute(jobEClass, JOB__CONDITION);
		createEAttribute(jobEClass, JOB__CONTINUE_ON_ERROR);
		createEAttribute(jobEClass, JOB__TIMEOUT_IN_MINUTES);
		createEAttribute(jobEClass, JOB__CANCEL_TIMEOUT_IN_MINUTES);
		createEAttribute(jobEClass, JOB__DEPENDS_ON);
		createEReference(jobEClass, JOB__POOL);
		createEReference(jobEClass, JOB__VARIABLES);
		createEReference(jobEClass, JOB__STRATEGY);
		createEReference(jobEClass, JOB__CONTAINER);
		createEReference(jobEClass, JOB__SERVICES);
		createEReference(jobEClass, JOB__WORKSPACE);
		createEReference(jobEClass, JOB__USES);
		createEReference(jobEClass, JOB__STEPS);
		createEReference(jobEClass, JOB__TEMPLATE_CONTEXT);

		deploymentJobEClass = createEClass(DEPLOYMENT_JOB);
		createEAttribute(deploymentJobEClass, DEPLOYMENT_JOB__DEPLOYMENT);
		createEAttribute(deploymentJobEClass, DEPLOYMENT_JOB__DISPLAY_NAME);
		createEAttribute(deploymentJobEClass, DEPLOYMENT_JOB__CONDITION);
		createEAttribute(deploymentJobEClass, DEPLOYMENT_JOB__CONTINUE_ON_ERROR);
		createEAttribute(deploymentJobEClass, DEPLOYMENT_JOB__TIMEOUT_IN_MINUTES);
		createEAttribute(deploymentJobEClass, DEPLOYMENT_JOB__CANCEL_TIMEOUT_IN_MINUTES);
		createEAttribute(deploymentJobEClass, DEPLOYMENT_JOB__DEPENDS_ON);
		createEReference(deploymentJobEClass, DEPLOYMENT_JOB__POOL);
		createEReference(deploymentJobEClass, DEPLOYMENT_JOB__VARIABLES);
		createEReference(deploymentJobEClass, DEPLOYMENT_JOB__ENVIRONMENT);
		createEReference(deploymentJobEClass, DEPLOYMENT_JOB__STRATEGY);
		createEReference(deploymentJobEClass, DEPLOYMENT_JOB__CONTAINER);
		createEReference(deploymentJobEClass, DEPLOYMENT_JOB__SERVICES);
		createEReference(deploymentJobEClass, DEPLOYMENT_JOB__WORKSPACE);
		createEReference(deploymentJobEClass, DEPLOYMENT_JOB__USES);
		createEReference(deploymentJobEClass, DEPLOYMENT_JOB__TEMPLATE_CONTEXT);

		jobTemplateEClass = createEClass(JOB_TEMPLATE);
		createEAttribute(jobTemplateEClass, JOB_TEMPLATE__TEMPLATE);
		createEReference(jobTemplateEClass, JOB_TEMPLATE__PARAMETERS);

		environmentEClass = createEClass(ENVIRONMENT);
		createEAttribute(environmentEClass, ENVIRONMENT__NAME);
		createEAttribute(environmentEClass, ENVIRONMENT__RESOURCE_NAME);
		createEAttribute(environmentEClass, ENVIRONMENT__RESOURCE_ID);
		createEAttribute(environmentEClass, ENVIRONMENT__RESOURCE_TYPE);
		createEAttribute(environmentEClass, ENVIRONMENT__TAGS);

		deploymentStrategyEClass = createEClass(DEPLOYMENT_STRATEGY);
		createEReference(deploymentStrategyEClass, DEPLOYMENT_STRATEGY__RUN_ONCE);
		createEReference(deploymentStrategyEClass, DEPLOYMENT_STRATEGY__ROLLING);
		createEReference(deploymentStrategyEClass, DEPLOYMENT_STRATEGY__CANARY);

		runOnceStrategyEClass = createEClass(RUN_ONCE_STRATEGY);
		createEReference(runOnceStrategyEClass, RUN_ONCE_STRATEGY__PRE_DEPLOY);
		createEReference(runOnceStrategyEClass, RUN_ONCE_STRATEGY__DEPLOY);
		createEReference(runOnceStrategyEClass, RUN_ONCE_STRATEGY__ROUTE_TRAFFIC);
		createEReference(runOnceStrategyEClass, RUN_ONCE_STRATEGY__POST_ROUTE_TRAFFIC);
		createEReference(runOnceStrategyEClass, RUN_ONCE_STRATEGY__ON);

		rollingStrategyEClass = createEClass(ROLLING_STRATEGY);
		createEAttribute(rollingStrategyEClass, ROLLING_STRATEGY__MAX_PARALLEL);
		createEReference(rollingStrategyEClass, ROLLING_STRATEGY__PRE_DEPLOY);
		createEReference(rollingStrategyEClass, ROLLING_STRATEGY__DEPLOY);
		createEReference(rollingStrategyEClass, ROLLING_STRATEGY__ROUTE_TRAFFIC);
		createEReference(rollingStrategyEClass, ROLLING_STRATEGY__POST_ROUTE_TRAFFIC);
		createEReference(rollingStrategyEClass, ROLLING_STRATEGY__ON);

		canaryStrategyEClass = createEClass(CANARY_STRATEGY);
		createEAttribute(canaryStrategyEClass, CANARY_STRATEGY__INCREMENTS);
		createEReference(canaryStrategyEClass, CANARY_STRATEGY__PRE_DEPLOY);
		createEReference(canaryStrategyEClass, CANARY_STRATEGY__DEPLOY);
		createEReference(canaryStrategyEClass, CANARY_STRATEGY__ROUTE_TRAFFIC);
		createEReference(canaryStrategyEClass, CANARY_STRATEGY__POST_ROUTE_TRAFFIC);
		createEReference(canaryStrategyEClass, CANARY_STRATEGY__ON);

		deployHookEClass = createEClass(DEPLOY_HOOK);
		createEReference(deployHookEClass, DEPLOY_HOOK__POOL);
		createEReference(deployHookEClass, DEPLOY_HOOK__STEPS);

		onSuccessOrFailureHookEClass = createEClass(ON_SUCCESS_OR_FAILURE_HOOK);
		createEReference(onSuccessOrFailureHookEClass, ON_SUCCESS_OR_FAILURE_HOOK__FAILURE);
		createEReference(onSuccessOrFailureHookEClass, ON_SUCCESS_OR_FAILURE_HOOK__SUCCESS);

		jobStrategyEClass = createEClass(JOB_STRATEGY);
		createEAttribute(jobStrategyEClass, JOB_STRATEGY__PARALLEL);
		createEAttribute(jobStrategyEClass, JOB_STRATEGY__MAX_PARALLEL);
		createEReference(jobStrategyEClass, JOB_STRATEGY__MATRIX);

		matrixEntryEClass = createEClass(MATRIX_ENTRY);
		createEAttribute(matrixEntryEClass, MATRIX_ENTRY__KEY);
		createEReference(matrixEntryEClass, MATRIX_ENTRY__VALUE);

		matrixVariableEClass = createEClass(MATRIX_VARIABLE);
		createEAttribute(matrixVariableEClass, MATRIX_VARIABLE__KEY);
		createEAttribute(matrixVariableEClass, MATRIX_VARIABLE__VALUE);

		jobUsesEClass = createEClass(JOB_USES);
		createEAttribute(jobUsesEClass, JOB_USES__REPOSITORIES);
		createEAttribute(jobUsesEClass, JOB_USES__POOLS);

		poolEClass = createEClass(POOL);
		createEAttribute(poolEClass, POOL__NAME);
		createEAttribute(poolEClass, POOL__VM_IMAGE);
		createEAttribute(poolEClass, POOL__DEMANDS);

		workspaceEClass = createEClass(WORKSPACE);
		createEAttribute(workspaceEClass, WORKSPACE__CLEAN);

		containerReferenceEClass = createEClass(CONTAINER_REFERENCE);

		containerAliasEClass = createEClass(CONTAINER_ALIAS);
		createEAttribute(containerAliasEClass, CONTAINER_ALIAS__ALIAS);

		containerImageEClass = createEClass(CONTAINER_IMAGE);
		createEAttribute(containerImageEClass, CONTAINER_IMAGE__IMAGE);
		createEAttribute(containerImageEClass, CONTAINER_IMAGE__ENDPOINT);
		createEAttribute(containerImageEClass, CONTAINER_IMAGE__OPTIONS);
		createEAttribute(containerImageEClass, CONTAINER_IMAGE__PORTS);
		createEAttribute(containerImageEClass, CONTAINER_IMAGE__VOLUMES);
		createEAttribute(containerImageEClass, CONTAINER_IMAGE__MAP_DOCKER_SOCKET);
		createEReference(containerImageEClass, CONTAINER_IMAGE__ENV);
		createEReference(containerImageEClass, CONTAINER_IMAGE__MOUNT_READ_ONLY);

		mountReadOnlyEClass = createEClass(MOUNT_READ_ONLY);
		createEAttribute(mountReadOnlyEClass, MOUNT_READ_ONLY__WORK);
		createEAttribute(mountReadOnlyEClass, MOUNT_READ_ONLY__EXTERNALS);
		createEAttribute(mountReadOnlyEClass, MOUNT_READ_ONLY__TOOLS);
		createEAttribute(mountReadOnlyEClass, MOUNT_READ_ONLY__TASKS);

		serviceEntryEClass = createEClass(SERVICE_ENTRY);
		createEAttribute(serviceEntryEClass, SERVICE_ENTRY__KEY);
		createEAttribute(serviceEntryEClass, SERVICE_ENTRY__VALUE);

		stepEClass = createEClass(STEP);
		createEAttribute(stepEClass, STEP__NAME);
		createEAttribute(stepEClass, STEP__DISPLAY_NAME);
		createEAttribute(stepEClass, STEP__CONDITION);
		createEAttribute(stepEClass, STEP__CONTINUE_ON_ERROR);
		createEAttribute(stepEClass, STEP__ENABLED);
		createEAttribute(stepEClass, STEP__TIMEOUT_IN_MINUTES);
		createEAttribute(stepEClass, STEP__RETRY_COUNT_ON_TASK_FAILURE);
		createEReference(stepEClass, STEP__ENV);
		createEReference(stepEClass, STEP__TARGET);

		taskStepEClass = createEClass(TASK_STEP);
		createEAttribute(taskStepEClass, TASK_STEP__TASK);
		createEReference(taskStepEClass, TASK_STEP__INPUTS);

		scriptStepEClass = createEClass(SCRIPT_STEP);
		createEAttribute(scriptStepEClass, SCRIPT_STEP__SCRIPT);
		createEAttribute(scriptStepEClass, SCRIPT_STEP__FAIL_ON_STDERR);
		createEAttribute(scriptStepEClass, SCRIPT_STEP__WORKING_DIRECTORY);

		bashStepEClass = createEClass(BASH_STEP);
		createEAttribute(bashStepEClass, BASH_STEP__BASH);
		createEAttribute(bashStepEClass, BASH_STEP__FAIL_ON_STDERR);
		createEAttribute(bashStepEClass, BASH_STEP__WORKING_DIRECTORY);

		powershellStepEClass = createEClass(POWERSHELL_STEP);
		createEAttribute(powershellStepEClass, POWERSHELL_STEP__POWERSHELL);
		createEAttribute(powershellStepEClass, POWERSHELL_STEP__ERROR_ACTION_PREFERENCE);
		createEAttribute(powershellStepEClass, POWERSHELL_STEP__FAIL_ON_STDERR);
		createEAttribute(powershellStepEClass, POWERSHELL_STEP__IGNORE_LASTEXITCODE);
		createEAttribute(powershellStepEClass, POWERSHELL_STEP__WORKING_DIRECTORY);

		pwshStepEClass = createEClass(PWSH_STEP);
		createEAttribute(pwshStepEClass, PWSH_STEP__PWSH);
		createEAttribute(pwshStepEClass, PWSH_STEP__ERROR_ACTION_PREFERENCE);
		createEAttribute(pwshStepEClass, PWSH_STEP__FAIL_ON_STDERR);
		createEAttribute(pwshStepEClass, PWSH_STEP__IGNORE_LASTEXITCODE);
		createEAttribute(pwshStepEClass, PWSH_STEP__WORKING_DIRECTORY);

		checkoutStepEClass = createEClass(CHECKOUT_STEP);
		createEAttribute(checkoutStepEClass, CHECKOUT_STEP__CHECKOUT);
		createEAttribute(checkoutStepEClass, CHECKOUT_STEP__CLEAN);
		createEAttribute(checkoutStepEClass, CHECKOUT_STEP__FETCH_DEPTH);
		createEAttribute(checkoutStepEClass, CHECKOUT_STEP__FETCH_FILTER);
		createEAttribute(checkoutStepEClass, CHECKOUT_STEP__FETCH_TAGS);
		createEAttribute(checkoutStepEClass, CHECKOUT_STEP__LFS);
		createEAttribute(checkoutStepEClass, CHECKOUT_STEP__PERSIST_CREDENTIALS);
		createEAttribute(checkoutStepEClass, CHECKOUT_STEP__SUBMODULES);
		createEAttribute(checkoutStepEClass, CHECKOUT_STEP__PATH);
		createEAttribute(checkoutStepEClass, CHECKOUT_STEP__SPARSE_CHECKOUT_DIRECTORIES);
		createEAttribute(checkoutStepEClass, CHECKOUT_STEP__SPARSE_CHECKOUT_PATTERNS);
		createEAttribute(checkoutStepEClass, CHECKOUT_STEP__WORKSPACE_REPO);

		downloadStepEClass = createEClass(DOWNLOAD_STEP);
		createEAttribute(downloadStepEClass, DOWNLOAD_STEP__DOWNLOAD);
		createEAttribute(downloadStepEClass, DOWNLOAD_STEP__ARTIFACT);
		createEAttribute(downloadStepEClass, DOWNLOAD_STEP__PATTERNS);

		downloadBuildStepEClass = createEClass(DOWNLOAD_BUILD_STEP);
		createEAttribute(downloadBuildStepEClass, DOWNLOAD_BUILD_STEP__DOWNLOAD_BUILD);
		createEAttribute(downloadBuildStepEClass, DOWNLOAD_BUILD_STEP__ARTIFACT);
		createEAttribute(downloadBuildStepEClass, DOWNLOAD_BUILD_STEP__PATH);
		createEAttribute(downloadBuildStepEClass, DOWNLOAD_BUILD_STEP__PATTERNS);

		getPackageStepEClass = createEClass(GET_PACKAGE_STEP);
		createEAttribute(getPackageStepEClass, GET_PACKAGE_STEP__GET_PACKAGE);
		createEAttribute(getPackageStepEClass, GET_PACKAGE_STEP__PATH);

		publishStepEClass = createEClass(PUBLISH_STEP);
		createEAttribute(publishStepEClass, PUBLISH_STEP__PUBLISH);
		createEAttribute(publishStepEClass, PUBLISH_STEP__ARTIFACT);

		reviewAppStepEClass = createEClass(REVIEW_APP_STEP);
		createEAttribute(reviewAppStepEClass, REVIEW_APP_STEP__REVIEW_APP);

		stepTemplateEClass = createEClass(STEP_TEMPLATE);
		createEAttribute(stepTemplateEClass, STEP_TEMPLATE__TEMPLATE);
		createEReference(stepTemplateEClass, STEP_TEMPLATE__PARAMETERS);

		stepTargetEClass = createEClass(STEP_TARGET);
		createEAttribute(stepTargetEClass, STEP_TARGET__CONTAINER);
		createEAttribute(stepTargetEClass, STEP_TARGET__COMMANDS);
		createEReference(stepTargetEClass, STEP_TARGET__SETTABLE_VARIABLES);

		settableVariablesEClass = createEClass(SETTABLE_VARIABLES);
		createEAttribute(settableVariablesEClass, SETTABLE_VARIABLES__NONE);
		createEAttribute(settableVariablesEClass, SETTABLE_VARIABLES__VARIABLES);

		triggerEClass = createEClass(TRIGGER);
		createEAttribute(triggerEClass, TRIGGER__DISABLED);
		createEAttribute(triggerEClass, TRIGGER__BATCH);
		createEAttribute(triggerEClass, TRIGGER__BRANCH_LIST);
		createEReference(triggerEClass, TRIGGER__BRANCHES);
		createEReference(triggerEClass, TRIGGER__PATHS);
		createEReference(triggerEClass, TRIGGER__TAGS);

		prTriggerEClass = createEClass(PR_TRIGGER);
		createEAttribute(prTriggerEClass, PR_TRIGGER__DISABLED);
		createEAttribute(prTriggerEClass, PR_TRIGGER__AUTO_CANCEL);
		createEAttribute(prTriggerEClass, PR_TRIGGER__DRAFTS);
		createEAttribute(prTriggerEClass, PR_TRIGGER__BRANCH_LIST);
		createEReference(prTriggerEClass, PR_TRIGGER__BRANCHES);
		createEReference(prTriggerEClass, PR_TRIGGER__PATHS);

		cronScheduleEClass = createEClass(CRON_SCHEDULE);
		createEAttribute(cronScheduleEClass, CRON_SCHEDULE__CRON);
		createEAttribute(cronScheduleEClass, CRON_SCHEDULE__DISPLAY_NAME);
		createEAttribute(cronScheduleEClass, CRON_SCHEDULE__BATCH);
		createEAttribute(cronScheduleEClass, CRON_SCHEDULE__ALWAYS);
		createEReference(cronScheduleEClass, CRON_SCHEDULE__BRANCHES);

		includeExcludeFiltersEClass = createEClass(INCLUDE_EXCLUDE_FILTERS);
		createEAttribute(includeExcludeFiltersEClass, INCLUDE_EXCLUDE_FILTERS__INCLUDE);
		createEAttribute(includeExcludeFiltersEClass, INCLUDE_EXCLUDE_FILTERS__EXCLUDE);

		parameterEClass = createEClass(PARAMETER);
		createEAttribute(parameterEClass, PARAMETER__NAME);
		createEAttribute(parameterEClass, PARAMETER__DISPLAY_NAME);
		createEAttribute(parameterEClass, PARAMETER__TYPE);
		createEAttribute(parameterEClass, PARAMETER__DEFAULT);
		createEAttribute(parameterEClass, PARAMETER__VALUES);

		templateParameterAssignmentEClass = createEClass(TEMPLATE_PARAMETER_ASSIGNMENT);
		createEAttribute(templateParameterAssignmentEClass, TEMPLATE_PARAMETER_ASSIGNMENT__KEY);
		createEAttribute(templateParameterAssignmentEClass, TEMPLATE_PARAMETER_ASSIGNMENT__VALUE);

		variableDefinitionEClass = createEClass(VARIABLE_DEFINITION);

		variableNameEClass = createEClass(VARIABLE_NAME);
		createEAttribute(variableNameEClass, VARIABLE_NAME__NAME);
		createEAttribute(variableNameEClass, VARIABLE_NAME__VALUE);
		createEAttribute(variableNameEClass, VARIABLE_NAME__READONLY);

		variableGroupEClass = createEClass(VARIABLE_GROUP);
		createEAttribute(variableGroupEClass, VARIABLE_GROUP__GROUP);

		variableTemplateEClass = createEClass(VARIABLE_TEMPLATE);
		createEAttribute(variableTemplateEClass, VARIABLE_TEMPLATE__TEMPLATE);
		createEReference(variableTemplateEClass, VARIABLE_TEMPLATE__PARAMETERS);

		resourcesEClass = createEClass(RESOURCES);
		createEReference(resourcesEClass, RESOURCES__BUILDS);
		createEReference(resourcesEClass, RESOURCES__CONTAINERS);
		createEReference(resourcesEClass, RESOURCES__PIPELINES);
		createEReference(resourcesEClass, RESOURCES__REPOSITORIES);
		createEReference(resourcesEClass, RESOURCES__WEBHOOKS);
		createEReference(resourcesEClass, RESOURCES__PACKAGES);

		buildResourceEClass = createEClass(BUILD_RESOURCE);
		createEAttribute(buildResourceEClass, BUILD_RESOURCE__BUILD);
		createEAttribute(buildResourceEClass, BUILD_RESOURCE__TYPE);
		createEAttribute(buildResourceEClass, BUILD_RESOURCE__CONNECTION);
		createEAttribute(buildResourceEClass, BUILD_RESOURCE__SOURCE);
		createEAttribute(buildResourceEClass, BUILD_RESOURCE__VERSION);
		createEAttribute(buildResourceEClass, BUILD_RESOURCE__BRANCH);
		createEAttribute(buildResourceEClass, BUILD_RESOURCE__TRIGGER);

		containerResourceEClass = createEClass(CONTAINER_RESOURCE);
		createEAttribute(containerResourceEClass, CONTAINER_RESOURCE__CONTAINER);
		createEAttribute(containerResourceEClass, CONTAINER_RESOURCE__IMAGE);
		createEAttribute(containerResourceEClass, CONTAINER_RESOURCE__TYPE);
		createEAttribute(containerResourceEClass, CONTAINER_RESOURCE__AZURE_SUBSCRIPTION);
		createEAttribute(containerResourceEClass, CONTAINER_RESOURCE__RESOURCE_GROUP);
		createEAttribute(containerResourceEClass, CONTAINER_RESOURCE__REGISTRY);
		createEAttribute(containerResourceEClass, CONTAINER_RESOURCE__REPOSITORY);
		createEAttribute(containerResourceEClass, CONTAINER_RESOURCE__ENDPOINT);
		createEAttribute(containerResourceEClass, CONTAINER_RESOURCE__LOCAL_IMAGE);
		createEAttribute(containerResourceEClass, CONTAINER_RESOURCE__OPTIONS);
		createEAttribute(containerResourceEClass, CONTAINER_RESOURCE__PORTS);
		createEAttribute(containerResourceEClass, CONTAINER_RESOURCE__VOLUMES);
		createEAttribute(containerResourceEClass, CONTAINER_RESOURCE__MAP_DOCKER_SOCKET);
		createEReference(containerResourceEClass, CONTAINER_RESOURCE__ENV);
		createEReference(containerResourceEClass, CONTAINER_RESOURCE__MOUNT_READ_ONLY);
		createEReference(containerResourceEClass, CONTAINER_RESOURCE__TRIGGER);

		containerResourceTriggerEClass = createEClass(CONTAINER_RESOURCE_TRIGGER);
		createEAttribute(containerResourceTriggerEClass, CONTAINER_RESOURCE_TRIGGER__ENABLED);
		createEAttribute(containerResourceTriggerEClass, CONTAINER_RESOURCE_TRIGGER__DISABLED);
		createEReference(containerResourceTriggerEClass, CONTAINER_RESOURCE_TRIGGER__TAGS);

		pipelineResourceEClass = createEClass(PIPELINE_RESOURCE);
		createEAttribute(pipelineResourceEClass, PIPELINE_RESOURCE__PIPELINE);
		createEAttribute(pipelineResourceEClass, PIPELINE_RESOURCE__PROJECT);
		createEAttribute(pipelineResourceEClass, PIPELINE_RESOURCE__SOURCE);
		createEAttribute(pipelineResourceEClass, PIPELINE_RESOURCE__VERSION);
		createEAttribute(pipelineResourceEClass, PIPELINE_RESOURCE__BRANCH);
		createEAttribute(pipelineResourceEClass, PIPELINE_RESOURCE__TAGS);
		createEReference(pipelineResourceEClass, PIPELINE_RESOURCE__TRIGGER);

		pipelineResourceTriggerEClass = createEClass(PIPELINE_RESOURCE_TRIGGER);
		createEAttribute(pipelineResourceTriggerEClass, PIPELINE_RESOURCE_TRIGGER__ENABLED);
		createEAttribute(pipelineResourceTriggerEClass, PIPELINE_RESOURCE_TRIGGER__DISABLED);
		createEAttribute(pipelineResourceTriggerEClass, PIPELINE_RESOURCE_TRIGGER__STAGES);
		createEAttribute(pipelineResourceTriggerEClass, PIPELINE_RESOURCE_TRIGGER__TAGS);
		createEReference(pipelineResourceTriggerEClass, PIPELINE_RESOURCE_TRIGGER__BRANCHES);

		repositoryResourceEClass = createEClass(REPOSITORY_RESOURCE);
		createEAttribute(repositoryResourceEClass, REPOSITORY_RESOURCE__REPOSITORY);
		createEAttribute(repositoryResourceEClass, REPOSITORY_RESOURCE__NAME);
		createEAttribute(repositoryResourceEClass, REPOSITORY_RESOURCE__TYPE);
		createEAttribute(repositoryResourceEClass, REPOSITORY_RESOURCE__ENDPOINT);
		createEAttribute(repositoryResourceEClass, REPOSITORY_RESOURCE__REF);
		createEReference(repositoryResourceEClass, REPOSITORY_RESOURCE__TRIGGER);

		webhookResourceEClass = createEClass(WEBHOOK_RESOURCE);
		createEAttribute(webhookResourceEClass, WEBHOOK_RESOURCE__WEBHOOK);
		createEAttribute(webhookResourceEClass, WEBHOOK_RESOURCE__CONNECTION);
		createEAttribute(webhookResourceEClass, WEBHOOK_RESOURCE__TYPE);
		createEReference(webhookResourceEClass, WEBHOOK_RESOURCE__FILTERS);

		webhookFilterEClass = createEClass(WEBHOOK_FILTER);
		createEAttribute(webhookFilterEClass, WEBHOOK_FILTER__PATH);
		createEAttribute(webhookFilterEClass, WEBHOOK_FILTER__VALUE);

		packageResourceEClass = createEClass(PACKAGE_RESOURCE);
		createEAttribute(packageResourceEClass, PACKAGE_RESOURCE__PACKAGE_NAME);
		createEAttribute(packageResourceEClass, PACKAGE_RESOURCE__TYPE);
		createEAttribute(packageResourceEClass, PACKAGE_RESOURCE__CONNECTION);
		createEAttribute(packageResourceEClass, PACKAGE_RESOURCE__NAME);
		createEAttribute(packageResourceEClass, PACKAGE_RESOURCE__VERSION);
		createEAttribute(packageResourceEClass, PACKAGE_RESOURCE__TAG);
		createEAttribute(packageResourceEClass, PACKAGE_RESOURCE__TRIGGER);

		envEntryEClass = createEClass(ENV_ENTRY);
		createEAttribute(envEntryEClass, ENV_ENTRY__KEY);
		createEAttribute(envEntryEClass, ENV_ENTRY__VALUE);

		inputEntryEClass = createEClass(INPUT_ENTRY);
		createEAttribute(inputEntryEClass, INPUT_ENTRY__KEY);
		createEAttribute(inputEntryEClass, INPUT_ENTRY__VALUE);

		templateContextEClass = createEClass(TEMPLATE_CONTEXT);
		createEReference(templateContextEClass, TEMPLATE_CONTEXT__PROPERTIES);

		// Create enums
		locK_BEHAVIOREEnum = createEEnum(LOCK_BEHAVIOR);
		stagE_TRIGGEREEnum = createEEnum(STAGE_TRIGGER);
		workspacE_CLEANEEnum = createEEnum(WORKSPACE_CLEAN);
		parameteR_TYPEEEnum = createEEnum(PARAMETER_TYPE);
		repositorY_TYPEEEnum = createEEnum(REPOSITORY_TYPE);
		targeT_COMMANDSEEnum = createEEnum(TARGET_COMMANDS);
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
		stageEClass.getESuperTypes().add(this.getStageElement());
		stageTemplateEClass.getESuperTypes().add(this.getStageElement());
		jobEClass.getESuperTypes().add(this.getJobElement());
		deploymentJobEClass.getESuperTypes().add(this.getJobElement());
		jobTemplateEClass.getESuperTypes().add(this.getJobElement());
		containerAliasEClass.getESuperTypes().add(this.getContainerReference());
		containerImageEClass.getESuperTypes().add(this.getContainerReference());
		taskStepEClass.getESuperTypes().add(this.getStep());
		scriptStepEClass.getESuperTypes().add(this.getStep());
		bashStepEClass.getESuperTypes().add(this.getStep());
		powershellStepEClass.getESuperTypes().add(this.getStep());
		pwshStepEClass.getESuperTypes().add(this.getStep());
		checkoutStepEClass.getESuperTypes().add(this.getStep());
		downloadStepEClass.getESuperTypes().add(this.getStep());
		downloadBuildStepEClass.getESuperTypes().add(this.getStep());
		getPackageStepEClass.getESuperTypes().add(this.getStep());
		publishStepEClass.getESuperTypes().add(this.getStep());
		reviewAppStepEClass.getESuperTypes().add(this.getStep());
		stepTemplateEClass.getESuperTypes().add(this.getStep());
		variableNameEClass.getESuperTypes().add(this.getVariableDefinition());
		variableGroupEClass.getESuperTypes().add(this.getVariableDefinition());
		variableTemplateEClass.getESuperTypes().add(this.getVariableDefinition());

		// Initialize classes, features, and operations; add parameters
		initEClass(pipelineEClass, Pipeline.class, "Pipeline", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPipeline_Name(), ecorePackage.getEString(), "name", null, 0, 1, Pipeline.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPipeline_AppendCommitMessageToRunName(), ecorePackage.getEBooleanObject(),
				"appendCommitMessageToRunName", null, 0, 1, Pipeline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPipeline_LockBehavior(), this.getLOCK_BEHAVIOR(), "lockBehavior", null, 0, 1, Pipeline.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPipeline_Trigger(), this.getTrigger(), null, "trigger", null, 0, 1, Pipeline.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPipeline_Pr(), this.getPrTrigger(), null, "pr", null, 0, 1, Pipeline.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getPipeline_Schedules(), this.getCronSchedule(), null, "schedules", null, 0, -1, Pipeline.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPipeline_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, Pipeline.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPipeline_Variables(), this.getVariableDefinition(), null, "variables", null, 0, -1,
				Pipeline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPipeline_Pool(), this.getPool(), null, "pool", null, 0, 1, Pipeline.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getPipeline_Resources(), this.getResources(), null, "resources", null, 0, 1, Pipeline.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPipeline_Stages(), this.getStageElement(), null, "stages", null, 0, -1, Pipeline.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPipeline_Jobs(), this.getJobElement(), null, "jobs", null, 0, -1, Pipeline.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPipeline_Steps(), this.getStep(), null, "steps", null, 0, -1, Pipeline.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getPipeline_Extends(), this.getExtends(), null, "extends", null, 0, 1, Pipeline.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPipeline_Workspace(), this.getWorkspace(), null, "workspace", null, 0, 1, Pipeline.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(extendsEClass, Extends.class, "Extends", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExtends_Template(), ecorePackage.getEString(), "template", null, 1, 1, Extends.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExtends_Parameters(), this.getTemplateParameterAssignment(), null, "parameters", null, 0, -1,
				Extends.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stageElementEClass, StageElement.class, "StageElement", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(stageEClass, Stage.class, "Stage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStage_Stage(), ecorePackage.getEString(), "stage", null, 1, 1, Stage.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStage_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1, Stage.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStage_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, Stage.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStage_LockBehavior(), this.getLOCK_BEHAVIOR(), "lockBehavior", null, 0, 1, Stage.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStage_Trigger(), this.getSTAGE_TRIGGER(), "trigger", null, 0, 1, Stage.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStage_IsSkippable(), ecorePackage.getEBooleanObject(), "isSkippable", null, 0, 1, Stage.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStage_DependsOn(), ecorePackage.getEString(), "dependsOn", null, 0, -1, Stage.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStage_Pool(), this.getPool(), null, "pool", null, 0, 1, Stage.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getStage_Variables(), this.getVariableDefinition(), null, "variables", null, 0, -1, Stage.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStage_Jobs(), this.getJobElement(), null, "jobs", null, 0, -1, Stage.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getStage_TemplateContext(), this.getTemplateContext(), null, "templateContext", null, 0, 1,
				Stage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stageTemplateEClass, StageTemplate.class, "StageTemplate", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStageTemplate_Template(), ecorePackage.getEString(), "template", null, 1, 1,
				StageTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getStageTemplate_Parameters(), this.getTemplateParameterAssignment(), null, "parameters", null,
				0, -1, StageTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(jobElementEClass, JobElement.class, "JobElement", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(jobEClass, Job.class, "Job", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJob_Job(), ecorePackage.getEString(), "job", null, 1, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJob_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1, Job.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJob_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJob_ContinueOnError(), ecorePackage.getEString(), "continueOnError", null, 0, 1, Job.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJob_TimeoutInMinutes(), ecorePackage.getEString(), "timeoutInMinutes", null, 0, 1, Job.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJob_CancelTimeoutInMinutes(), ecorePackage.getEString(), "cancelTimeoutInMinutes", null, 0, 1,
				Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getJob_DependsOn(), ecorePackage.getEString(), "dependsOn", null, 0, -1, Job.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Pool(), this.getPool(), null, "pool", null, 0, 1, Job.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Variables(), this.getVariableDefinition(), null, "variables", null, 0, -1, Job.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Strategy(), this.getJobStrategy(), null, "strategy", null, 0, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJob_Container(), this.getContainerReference(), null, "container", null, 0, 1, Job.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Services(), this.getServiceEntry(), null, "services", null, 0, -1, Job.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJob_Workspace(), this.getWorkspace(), null, "workspace", null, 0, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJob_Uses(), this.getJobUses(), null, "uses", null, 0, 1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJob_Steps(), this.getStep(), null, "steps", null, 0, -1, Job.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getJob_TemplateContext(), this.getTemplateContext(), null, "templateContext", null, 0, 1,
				Job.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deploymentJobEClass, DeploymentJob.class, "DeploymentJob", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeploymentJob_Deployment(), ecorePackage.getEString(), "deployment", null, 1, 1,
				DeploymentJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeploymentJob_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1,
				DeploymentJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeploymentJob_Condition(), ecorePackage.getEString(), "condition", null, 0, 1,
				DeploymentJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeploymentJob_ContinueOnError(), ecorePackage.getEString(), "continueOnError", null, 0, 1,
				DeploymentJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeploymentJob_TimeoutInMinutes(), ecorePackage.getEString(), "timeoutInMinutes", null, 0, 1,
				DeploymentJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeploymentJob_CancelTimeoutInMinutes(), ecorePackage.getEString(), "cancelTimeoutInMinutes",
				null, 0, 1, DeploymentJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeploymentJob_DependsOn(), ecorePackage.getEString(), "dependsOn", null, 0, -1,
				DeploymentJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentJob_Pool(), this.getPool(), null, "pool", null, 0, 1, DeploymentJob.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentJob_Variables(), this.getVariableDefinition(), null, "variables", null, 0, -1,
				DeploymentJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentJob_Environment(), this.getEnvironment(), null, "environment", null, 0, 1,
				DeploymentJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentJob_Strategy(), this.getDeploymentStrategy(), null, "strategy", null, 0, 1,
				DeploymentJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentJob_Container(), this.getContainerReference(), null, "container", null, 0, 1,
				DeploymentJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentJob_Services(), this.getServiceEntry(), null, "services", null, 0, -1,
				DeploymentJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentJob_Workspace(), this.getWorkspace(), null, "workspace", null, 0, 1,
				DeploymentJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentJob_Uses(), this.getJobUses(), null, "uses", null, 0, 1, DeploymentJob.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentJob_TemplateContext(), this.getTemplateContext(), null, "templateContext", null, 0,
				1, DeploymentJob.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(jobTemplateEClass, JobTemplate.class, "JobTemplate", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJobTemplate_Template(), ecorePackage.getEString(), "template", null, 1, 1, JobTemplate.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getJobTemplate_Parameters(), this.getTemplateParameterAssignment(), null, "parameters", null, 0,
				-1, JobTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(environmentEClass, Environment.class, "Environment", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnvironment_Name(), ecorePackage.getEString(), "name", null, 0, 1, Environment.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnvironment_ResourceName(), ecorePackage.getEString(), "resourceName", null, 0, 1,
				Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnvironment_ResourceId(), ecorePackage.getEString(), "resourceId", null, 0, 1,
				Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnvironment_ResourceType(), ecorePackage.getEString(), "resourceType", null, 0, 1,
				Environment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnvironment_Tags(), ecorePackage.getEString(), "tags", null, 0, 1, Environment.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deploymentStrategyEClass, DeploymentStrategy.class, "DeploymentStrategy", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDeploymentStrategy_RunOnce(), this.getRunOnceStrategy(), null, "runOnce", null, 0, 1,
				DeploymentStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentStrategy_Rolling(), this.getRollingStrategy(), null, "rolling", null, 0, 1,
				DeploymentStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeploymentStrategy_Canary(), this.getCanaryStrategy(), null, "canary", null, 0, 1,
				DeploymentStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(runOnceStrategyEClass, RunOnceStrategy.class, "RunOnceStrategy", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRunOnceStrategy_PreDeploy(), this.getDeployHook(), null, "preDeploy", null, 0, 1,
				RunOnceStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRunOnceStrategy_Deploy(), this.getDeployHook(), null, "deploy", null, 0, 1,
				RunOnceStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRunOnceStrategy_RouteTraffic(), this.getDeployHook(), null, "routeTraffic", null, 0, 1,
				RunOnceStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRunOnceStrategy_PostRouteTraffic(), this.getDeployHook(), null, "postRouteTraffic", null, 0,
				1, RunOnceStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRunOnceStrategy_On(), this.getOnSuccessOrFailureHook(), null, "on", null, 0, 1,
				RunOnceStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rollingStrategyEClass, RollingStrategy.class, "RollingStrategy", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRollingStrategy_MaxParallel(), ecorePackage.getEString(), "maxParallel", null, 0, 1,
				RollingStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getRollingStrategy_PreDeploy(), this.getDeployHook(), null, "preDeploy", null, 0, 1,
				RollingStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRollingStrategy_Deploy(), this.getDeployHook(), null, "deploy", null, 0, 1,
				RollingStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRollingStrategy_RouteTraffic(), this.getDeployHook(), null, "routeTraffic", null, 0, 1,
				RollingStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRollingStrategy_PostRouteTraffic(), this.getDeployHook(), null, "postRouteTraffic", null, 0,
				1, RollingStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRollingStrategy_On(), this.getOnSuccessOrFailureHook(), null, "on", null, 0, 1,
				RollingStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(canaryStrategyEClass, CanaryStrategy.class, "CanaryStrategy", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCanaryStrategy_Increments(), ecorePackage.getEString(), "increments", null, 0, -1,
				CanaryStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getCanaryStrategy_PreDeploy(), this.getDeployHook(), null, "preDeploy", null, 0, 1,
				CanaryStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCanaryStrategy_Deploy(), this.getDeployHook(), null, "deploy", null, 0, 1,
				CanaryStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCanaryStrategy_RouteTraffic(), this.getDeployHook(), null, "routeTraffic", null, 0, 1,
				CanaryStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCanaryStrategy_PostRouteTraffic(), this.getDeployHook(), null, "postRouteTraffic", null, 0, 1,
				CanaryStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCanaryStrategy_On(), this.getOnSuccessOrFailureHook(), null, "on", null, 0, 1,
				CanaryStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deployHookEClass, DeployHook.class, "DeployHook", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDeployHook_Pool(), this.getPool(), null, "pool", null, 0, 1, DeployHook.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getDeployHook_Steps(), this.getStep(), null, "steps", null, 0, -1, DeployHook.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(onSuccessOrFailureHookEClass, OnSuccessOrFailureHook.class, "OnSuccessOrFailureHook", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOnSuccessOrFailureHook_Failure(), this.getDeployHook(), null, "failure", null, 0, 1,
				OnSuccessOrFailureHook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOnSuccessOrFailureHook_Success(), this.getDeployHook(), null, "success", null, 0, 1,
				OnSuccessOrFailureHook.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(jobStrategyEClass, JobStrategy.class, "JobStrategy", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJobStrategy_Parallel(), ecorePackage.getEString(), "parallel", null, 0, 1, JobStrategy.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJobStrategy_MaxParallel(), ecorePackage.getEString(), "maxParallel", null, 0, 1,
				JobStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getJobStrategy_Matrix(), this.getMatrixEntry(), null, "matrix", null, 0, -1, JobStrategy.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(matrixEntryEClass, Map.Entry.class, "MatrixEntry", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMatrixEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMatrixEntry_Value(), this.getMatrixVariable(), null, "value", null, 0, -1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(matrixVariableEClass, Map.Entry.class, "MatrixVariable", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMatrixVariable_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatrixVariable_Value(), ecorePackage.getEString(), "value", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(jobUsesEClass, JobUses.class, "JobUses", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJobUses_Repositories(), ecorePackage.getEString(), "repositories", null, 0, -1, JobUses.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getJobUses_Pools(), ecorePackage.getEString(), "pools", null, 0, -1, JobUses.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(poolEClass, Pool.class, "Pool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPool_Name(), ecorePackage.getEString(), "name", null, 0, 1, Pool.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPool_VmImage(), ecorePackage.getEString(), "vmImage", null, 0, 1, Pool.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPool_Demands(), ecorePackage.getEString(), "demands", null, 0, -1, Pool.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workspaceEClass, Workspace.class, "Workspace", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkspace_Clean(), this.getWORKSPACE_CLEAN(), "clean", null, 0, 1, Workspace.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(containerReferenceEClass, ContainerReference.class, "ContainerReference", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(containerAliasEClass, ContainerAlias.class, "ContainerAlias", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContainerAlias_Alias(), ecorePackage.getEString(), "alias", null, 1, 1, ContainerAlias.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(containerImageEClass, ContainerImage.class, "ContainerImage", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContainerImage_Image(), ecorePackage.getEString(), "image", null, 1, 1, ContainerImage.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerImage_Endpoint(), ecorePackage.getEString(), "endpoint", null, 0, 1,
				ContainerImage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerImage_Options(), ecorePackage.getEString(), "options", null, 0, 1,
				ContainerImage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerImage_Ports(), ecorePackage.getEString(), "ports", null, 0, -1, ContainerImage.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerImage_Volumes(), ecorePackage.getEString(), "volumes", null, 0, -1,
				ContainerImage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerImage_MapDockerSocket(), ecorePackage.getEBooleanObject(), "mapDockerSocket", null,
				0, 1, ContainerImage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerImage_Env(), this.getEnvEntry(), null, "env", null, 0, -1, ContainerImage.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerImage_MountReadOnly(), this.getMountReadOnly(), null, "mountReadOnly", null, 0, 1,
				ContainerImage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mountReadOnlyEClass, MountReadOnly.class, "MountReadOnly", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMountReadOnly_Work(), ecorePackage.getEBooleanObject(), "work", null, 0, 1,
				MountReadOnly.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMountReadOnly_Externals(), ecorePackage.getEBooleanObject(), "externals", null, 0, 1,
				MountReadOnly.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMountReadOnly_Tools(), ecorePackage.getEBooleanObject(), "tools", null, 0, 1,
				MountReadOnly.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMountReadOnly_Tasks(), ecorePackage.getEBooleanObject(), "tasks", null, 0, 1,
				MountReadOnly.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(serviceEntryEClass, Map.Entry.class, "ServiceEntry", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getServiceEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceEntry_Value(), ecorePackage.getEString(), "value", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stepEClass, Step.class, "Step", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStep_Name(), ecorePackage.getEString(), "name", null, 0, 1, Step.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStep_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1, Step.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStep_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, Step.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStep_ContinueOnError(), ecorePackage.getEBooleanObject(), "continueOnError", null, 0, 1,
				Step.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getStep_Enabled(), ecorePackage.getEBooleanObject(), "enabled", null, 0, 1, Step.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStep_TimeoutInMinutes(), ecorePackage.getEString(), "timeoutInMinutes", null, 0, 1,
				Step.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getStep_RetryCountOnTaskFailure(), ecorePackage.getEString(), "retryCountOnTaskFailure", null, 0,
				1, Step.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getStep_Env(), this.getEnvEntry(), null, "env", null, 0, -1, Step.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getStep_Target(), this.getStepTarget(), null, "target", null, 0, 1, Step.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(taskStepEClass, TaskStep.class, "TaskStep", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTaskStep_Task(), ecorePackage.getEString(), "task", null, 1, 1, TaskStep.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTaskStep_Inputs(), this.getInputEntry(), null, "inputs", null, 0, -1, TaskStep.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scriptStepEClass, ScriptStep.class, "ScriptStep", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScriptStep_Script(), ecorePackage.getEString(), "script", null, 1, 1, ScriptStep.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptStep_FailOnStderr(), ecorePackage.getEString(), "failOnStderr", null, 0, 1,
				ScriptStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getScriptStep_WorkingDirectory(), ecorePackage.getEString(), "workingDirectory", null, 0, 1,
				ScriptStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(bashStepEClass, BashStep.class, "BashStep", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBashStep_Bash(), ecorePackage.getEString(), "bash", null, 1, 1, BashStep.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBashStep_FailOnStderr(), ecorePackage.getEString(), "failOnStderr", null, 0, 1,
				BashStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBashStep_WorkingDirectory(), ecorePackage.getEString(), "workingDirectory", null, 0, 1,
				BashStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(powershellStepEClass, PowershellStep.class, "PowershellStep", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPowershellStep_Powershell(), ecorePackage.getEString(), "powershell", null, 1, 1,
				PowershellStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPowershellStep_ErrorActionPreference(), ecorePackage.getEString(), "errorActionPreference",
				null, 0, 1, PowershellStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPowershellStep_FailOnStderr(), ecorePackage.getEString(), "failOnStderr", null, 0, 1,
				PowershellStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPowershellStep_IgnoreLASTEXITCODE(), ecorePackage.getEString(), "ignoreLASTEXITCODE", null, 0,
				1, PowershellStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPowershellStep_WorkingDirectory(), ecorePackage.getEString(), "workingDirectory", null, 0, 1,
				PowershellStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(pwshStepEClass, PwshStep.class, "PwshStep", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPwshStep_Pwsh(), ecorePackage.getEString(), "pwsh", null, 1, 1, PwshStep.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPwshStep_ErrorActionPreference(), ecorePackage.getEString(), "errorActionPreference", null, 0,
				1, PwshStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPwshStep_FailOnStderr(), ecorePackage.getEString(), "failOnStderr", null, 0, 1,
				PwshStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPwshStep_IgnoreLASTEXITCODE(), ecorePackage.getEString(), "ignoreLASTEXITCODE", null, 0, 1,
				PwshStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPwshStep_WorkingDirectory(), ecorePackage.getEString(), "workingDirectory", null, 0, 1,
				PwshStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(checkoutStepEClass, CheckoutStep.class, "CheckoutStep", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCheckoutStep_Checkout(), ecorePackage.getEString(), "checkout", null, 1, 1,
				CheckoutStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCheckoutStep_Clean(), ecorePackage.getEString(), "clean", null, 0, 1, CheckoutStep.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCheckoutStep_FetchDepth(), ecorePackage.getEString(), "fetchDepth", null, 0, 1,
				CheckoutStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCheckoutStep_FetchFilter(), ecorePackage.getEString(), "fetchFilter", null, 0, 1,
				CheckoutStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCheckoutStep_FetchTags(), ecorePackage.getEString(), "fetchTags", null, 0, 1,
				CheckoutStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCheckoutStep_Lfs(), ecorePackage.getEString(), "lfs", null, 0, 1, CheckoutStep.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCheckoutStep_PersistCredentials(), ecorePackage.getEString(), "persistCredentials", null, 0,
				1, CheckoutStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCheckoutStep_Submodules(), ecorePackage.getEString(), "submodules", null, 0, 1,
				CheckoutStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCheckoutStep_Path(), ecorePackage.getEString(), "path", null, 0, 1, CheckoutStep.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCheckoutStep_SparseCheckoutDirectories(), ecorePackage.getEString(),
				"sparseCheckoutDirectories", null, 0, 1, CheckoutStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCheckoutStep_SparseCheckoutPatterns(), ecorePackage.getEString(), "sparseCheckoutPatterns",
				null, 0, 1, CheckoutStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCheckoutStep_WorkspaceRepo(), ecorePackage.getEBooleanObject(), "workspaceRepo", null, 0, 1,
				CheckoutStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(downloadStepEClass, DownloadStep.class, "DownloadStep", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDownloadStep_Download(), ecorePackage.getEString(), "download", null, 1, 1,
				DownloadStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDownloadStep_Artifact(), ecorePackage.getEString(), "artifact", null, 0, 1,
				DownloadStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDownloadStep_Patterns(), ecorePackage.getEString(), "patterns", null, 0, 1,
				DownloadStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(downloadBuildStepEClass, DownloadBuildStep.class, "DownloadBuildStep", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDownloadBuildStep_DownloadBuild(), ecorePackage.getEString(), "downloadBuild", null, 1, 1,
				DownloadBuildStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDownloadBuildStep_Artifact(), ecorePackage.getEString(), "artifact", null, 0, 1,
				DownloadBuildStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDownloadBuildStep_Path(), ecorePackage.getEString(), "path", null, 0, 1,
				DownloadBuildStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getDownloadBuildStep_Patterns(), ecorePackage.getEString(), "patterns", null, 0, 1,
				DownloadBuildStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(getPackageStepEClass, GetPackageStep.class, "GetPackageStep", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGetPackageStep_GetPackage(), ecorePackage.getEString(), "getPackage", null, 1, 1,
				GetPackageStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGetPackageStep_Path(), ecorePackage.getEString(), "path", null, 0, 1, GetPackageStep.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(publishStepEClass, PublishStep.class, "PublishStep", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPublishStep_Publish(), ecorePackage.getEString(), "publish", null, 1, 1, PublishStep.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPublishStep_Artifact(), ecorePackage.getEString(), "artifact", null, 0, 1, PublishStep.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reviewAppStepEClass, ReviewAppStep.class, "ReviewAppStep", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReviewAppStep_ReviewApp(), ecorePackage.getEString(), "reviewApp", null, 1, 1,
				ReviewAppStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(stepTemplateEClass, StepTemplate.class, "StepTemplate", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStepTemplate_Template(), ecorePackage.getEString(), "template", null, 1, 1,
				StepTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getStepTemplate_Parameters(), this.getTemplateParameterAssignment(), null, "parameters", null, 0,
				-1, StepTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stepTargetEClass, StepTarget.class, "StepTarget", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStepTarget_Container(), ecorePackage.getEString(), "container", null, 0, 1, StepTarget.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStepTarget_Commands(), this.getTARGET_COMMANDS(), "commands", null, 0, 1, StepTarget.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStepTarget_SettableVariables(), this.getSettableVariables(), null, "settableVariables", null,
				0, 1, StepTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(settableVariablesEClass, SettableVariables.class, "SettableVariables", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSettableVariables_None(), ecorePackage.getEBooleanObject(), "none", null, 0, 1,
				SettableVariables.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getSettableVariables_Variables(), ecorePackage.getEString(), "variables", null, 0, -1,
				SettableVariables.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(triggerEClass, Trigger.class, "Trigger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTrigger_Disabled(), ecorePackage.getEBooleanObject(), "disabled", null, 0, 1, Trigger.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrigger_Batch(), ecorePackage.getEBooleanObject(), "batch", null, 0, 1, Trigger.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrigger_BranchList(), ecorePackage.getEString(), "branchList", null, 0, -1, Trigger.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTrigger_Branches(), this.getIncludeExcludeFilters(), null, "branches", null, 0, 1,
				Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTrigger_Paths(), this.getIncludeExcludeFilters(), null, "paths", null, 0, 1, Trigger.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTrigger_Tags(), this.getIncludeExcludeFilters(), null, "tags", null, 0, 1, Trigger.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(prTriggerEClass, PrTrigger.class, "PrTrigger", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPrTrigger_Disabled(), ecorePackage.getEBooleanObject(), "disabled", null, 0, 1,
				PrTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrTrigger_AutoCancel(), ecorePackage.getEBooleanObject(), "autoCancel", null, 0, 1,
				PrTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrTrigger_Drafts(), ecorePackage.getEBooleanObject(), "drafts", null, 0, 1, PrTrigger.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrTrigger_BranchList(), ecorePackage.getEString(), "branchList", null, 0, -1, PrTrigger.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPrTrigger_Branches(), this.getIncludeExcludeFilters(), null, "branches", null, 0, 1,
				PrTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPrTrigger_Paths(), this.getIncludeExcludeFilters(), null, "paths", null, 0, 1,
				PrTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cronScheduleEClass, CronSchedule.class, "CronSchedule", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCronSchedule_Cron(), ecorePackage.getEString(), "cron", null, 1, 1, CronSchedule.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCronSchedule_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1,
				CronSchedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCronSchedule_Batch(), ecorePackage.getEBooleanObject(), "batch", null, 0, 1,
				CronSchedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getCronSchedule_Always(), ecorePackage.getEBooleanObject(), "always", null, 0, 1,
				CronSchedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getCronSchedule_Branches(), this.getIncludeExcludeFilters(), null, "branches", null, 0, 1,
				CronSchedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(includeExcludeFiltersEClass, IncludeExcludeFilters.class, "IncludeExcludeFilters", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIncludeExcludeFilters_Include(), ecorePackage.getEString(), "include", null, 0, -1,
				IncludeExcludeFilters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIncludeExcludeFilters_Exclude(), ecorePackage.getEString(), "exclude", null, 0, -1,
				IncludeExcludeFilters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameter_Name(), ecorePackage.getEString(), "name", null, 1, 1, Parameter.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameter_DisplayName(), ecorePackage.getEString(), "displayName", null, 0, 1,
				Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameter_Type(), this.getPARAMETER_TYPE(), "type", null, 0, 1, Parameter.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameter_Default(), ecorePackage.getEString(), "default", null, 0, 1, Parameter.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameter_Values(), ecorePackage.getEString(), "values", null, 0, -1, Parameter.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(templateParameterAssignmentEClass, Map.Entry.class, "TemplateParameterAssignment", !IS_ABSTRACT,
				!IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTemplateParameterAssignment_Key(), ecorePackage.getEString(), "key", null, 1, 1,
				Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getTemplateParameterAssignment_Value(), ecorePackage.getEString(), "value", null, 1, 1,
				Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(variableDefinitionEClass, VariableDefinition.class, "VariableDefinition", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(variableNameEClass, VariableName.class, "VariableName", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariableName_Name(), ecorePackage.getEString(), "name", null, 1, 1, VariableName.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariableName_Value(), ecorePackage.getEString(), "value", null, 0, 1, VariableName.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariableName_Readonly(), ecorePackage.getEBooleanObject(), "readonly", null, 0, 1,
				VariableName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(variableGroupEClass, VariableGroup.class, "VariableGroup", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariableGroup_Group(), ecorePackage.getEString(), "group", null, 1, 1, VariableGroup.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(variableTemplateEClass, VariableTemplate.class, "VariableTemplate", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariableTemplate_Template(), ecorePackage.getEString(), "template", null, 1, 1,
				VariableTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getVariableTemplate_Parameters(), this.getTemplateParameterAssignment(), null, "parameters",
				null, 0, -1, VariableTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourcesEClass, Resources.class, "Resources", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResources_Builds(), this.getBuildResource(), null, "builds", null, 0, -1, Resources.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResources_Containers(), this.getContainerResource(), null, "containers", null, 0, -1,
				Resources.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResources_Pipelines(), this.getPipelineResource(), null, "pipelines", null, 0, -1,
				Resources.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResources_Repositories(), this.getRepositoryResource(), null, "repositories", null, 0, -1,
				Resources.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResources_Webhooks(), this.getWebhookResource(), null, "webhooks", null, 0, -1,
				Resources.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResources_Packages(), this.getPackageResource(), null, "packages", null, 0, -1,
				Resources.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(buildResourceEClass, BuildResource.class, "BuildResource", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBuildResource_Build(), ecorePackage.getEString(), "build", null, 1, 1, BuildResource.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuildResource_Type(), ecorePackage.getEString(), "type", null, 1, 1, BuildResource.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuildResource_Connection(), ecorePackage.getEString(), "connection", null, 1, 1,
				BuildResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuildResource_Source(), ecorePackage.getEString(), "source", null, 1, 1, BuildResource.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuildResource_Version(), ecorePackage.getEString(), "version", null, 0, 1,
				BuildResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuildResource_Branch(), ecorePackage.getEString(), "branch", null, 0, 1, BuildResource.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuildResource_Trigger(), ecorePackage.getEString(), "trigger", null, 0, 1,
				BuildResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(containerResourceEClass, ContainerResource.class, "ContainerResource", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContainerResource_Container(), ecorePackage.getEString(), "container", null, 1, 1,
				ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerResource_Image(), ecorePackage.getEString(), "image", null, 1, 1,
				ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerResource_Type(), ecorePackage.getEString(), "type", null, 0, 1,
				ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerResource_AzureSubscription(), ecorePackage.getEString(), "azureSubscription", null,
				0, 1, ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerResource_ResourceGroup(), ecorePackage.getEString(), "resourceGroup", null, 0, 1,
				ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerResource_Registry(), ecorePackage.getEString(), "registry", null, 0, 1,
				ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerResource_Repository(), ecorePackage.getEString(), "repository", null, 0, 1,
				ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerResource_Endpoint(), ecorePackage.getEString(), "endpoint", null, 0, 1,
				ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerResource_LocalImage(), ecorePackage.getEBooleanObject(), "localImage", null, 0, 1,
				ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerResource_Options(), ecorePackage.getEString(), "options", null, 0, 1,
				ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerResource_Ports(), ecorePackage.getEString(), "ports", null, 0, -1,
				ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerResource_Volumes(), ecorePackage.getEString(), "volumes", null, 0, -1,
				ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerResource_MapDockerSocket(), ecorePackage.getEBooleanObject(), "mapDockerSocket",
				null, 0, 1, ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerResource_Env(), this.getEnvEntry(), null, "env", null, 0, -1,
				ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerResource_MountReadOnly(), this.getMountReadOnly(), null, "mountReadOnly", null, 0, 1,
				ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerResource_Trigger(), this.getContainerResourceTrigger(), null, "trigger", null, 0, 1,
				ContainerResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(containerResourceTriggerEClass, ContainerResourceTrigger.class, "ContainerResourceTrigger",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContainerResourceTrigger_Enabled(), ecorePackage.getEBooleanObject(), "enabled", null, 0, 1,
				ContainerResourceTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainerResourceTrigger_Disabled(), ecorePackage.getEBooleanObject(), "disabled", null, 0, 1,
				ContainerResourceTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerResourceTrigger_Tags(), this.getIncludeExcludeFilters(), null, "tags", null, 0, 1,
				ContainerResourceTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pipelineResourceEClass, PipelineResource.class, "PipelineResource", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPipelineResource_Pipeline(), ecorePackage.getEString(), "pipeline", null, 1, 1,
				PipelineResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPipelineResource_Project(), ecorePackage.getEString(), "project", null, 0, 1,
				PipelineResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPipelineResource_Source(), ecorePackage.getEString(), "source", null, 0, 1,
				PipelineResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPipelineResource_Version(), ecorePackage.getEString(), "version", null, 0, 1,
				PipelineResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPipelineResource_Branch(), ecorePackage.getEString(), "branch", null, 0, 1,
				PipelineResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPipelineResource_Tags(), ecorePackage.getEString(), "tags", null, 0, -1,
				PipelineResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getPipelineResource_Trigger(), this.getPipelineResourceTrigger(), null, "trigger", null, 0, 1,
				PipelineResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pipelineResourceTriggerEClass, PipelineResourceTrigger.class, "PipelineResourceTrigger",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPipelineResourceTrigger_Enabled(), ecorePackage.getEBooleanObject(), "enabled", null, 0, 1,
				PipelineResourceTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPipelineResourceTrigger_Disabled(), ecorePackage.getEBooleanObject(), "disabled", null, 0, 1,
				PipelineResourceTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPipelineResourceTrigger_Stages(), ecorePackage.getEString(), "stages", null, 0, -1,
				PipelineResourceTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPipelineResourceTrigger_Tags(), ecorePackage.getEString(), "tags", null, 0, -1,
				PipelineResourceTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPipelineResourceTrigger_Branches(), this.getIncludeExcludeFilters(), null, "branches", null,
				0, 1, PipelineResourceTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(repositoryResourceEClass, RepositoryResource.class, "RepositoryResource", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRepositoryResource_Repository(), ecorePackage.getEString(), "repository", null, 1, 1,
				RepositoryResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepositoryResource_Name(), ecorePackage.getEString(), "name", null, 0, 1,
				RepositoryResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepositoryResource_Type(), this.getREPOSITORY_TYPE(), "type", null, 0, 1,
				RepositoryResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepositoryResource_Endpoint(), ecorePackage.getEString(), "endpoint", null, 0, 1,
				RepositoryResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepositoryResource_Ref(), ecorePackage.getEString(), "ref", null, 0, 1,
				RepositoryResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getRepositoryResource_Trigger(), this.getTrigger(), null, "trigger", null, 0, 1,
				RepositoryResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(webhookResourceEClass, WebhookResource.class, "WebhookResource", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWebhookResource_Webhook(), ecorePackage.getEString(), "webhook", null, 1, 1,
				WebhookResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getWebhookResource_Connection(), ecorePackage.getEString(), "connection", null, 1, 1,
				WebhookResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getWebhookResource_Type(), ecorePackage.getEString(), "type", null, 0, 1, WebhookResource.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWebhookResource_Filters(), this.getWebhookFilter(), null, "filters", null, 0, -1,
				WebhookResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(webhookFilterEClass, WebhookFilter.class, "WebhookFilter", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWebhookFilter_Path(), ecorePackage.getEString(), "path", null, 1, 1, WebhookFilter.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWebhookFilter_Value(), ecorePackage.getEString(), "value", null, 1, 1, WebhookFilter.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageResourceEClass, PackageResource.class, "PackageResource", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPackageResource_PackageName(), ecorePackage.getEString(), "packageName", null, 1, 1,
				PackageResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageResource_Type(), ecorePackage.getEString(), "type", null, 1, 1, PackageResource.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageResource_Connection(), ecorePackage.getEString(), "connection", null, 1, 1,
				PackageResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageResource_Name(), ecorePackage.getEString(), "name", null, 1, 1, PackageResource.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageResource_Version(), ecorePackage.getEString(), "version", null, 0, 1,
				PackageResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageResource_Tag(), ecorePackage.getEString(), "tag", null, 0, 1, PackageResource.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPackageResource_Trigger(), ecorePackage.getEString(), "trigger", null, 0, 1,
				PackageResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(envEntryEClass, Map.Entry.class, "EnvEntry", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnvEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEnvEntry_Value(), ecorePackage.getEString(), "value", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inputEntryEClass, Map.Entry.class, "InputEntry", !IS_ABSTRACT, !IS_INTERFACE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInputEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInputEntry_Value(), ecorePackage.getEString(), "value", null, 1, 1, Map.Entry.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(templateContextEClass, TemplateContext.class, "TemplateContext", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTemplateContext_Properties(), this.getTemplateParameterAssignment(), null, "properties", null,
				0, -1, TemplateContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(locK_BEHAVIOREEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR.class,
				"LOCK_BEHAVIOR");
		addEEnumLiteral(locK_BEHAVIOREEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR.SEQUENTIAL);
		addEEnumLiteral(locK_BEHAVIOREEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.LOCK_BEHAVIOR.RUN_LATEST);

		initEEnum(stagE_TRIGGEREEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.STAGE_TRIGGER.class,
				"STAGE_TRIGGER");
		addEEnumLiteral(stagE_TRIGGEREEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.STAGE_TRIGGER.MANUAL);
		addEEnumLiteral(stagE_TRIGGEREEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.STAGE_TRIGGER.AUTOMATIC);

		initEEnum(workspacE_CLEANEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.WORKSPACE_CLEAN.class,
				"WORKSPACE_CLEAN");
		addEEnumLiteral(workspacE_CLEANEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.WORKSPACE_CLEAN.OUTPUTS);
		addEEnumLiteral(workspacE_CLEANEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.WORKSPACE_CLEAN.RESOURCES);
		addEEnumLiteral(workspacE_CLEANEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.WORKSPACE_CLEAN.ALL);

		initEEnum(parameteR_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE.class,
				"PARAMETER_TYPE");
		addEEnumLiteral(parameteR_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE.STRING);
		addEEnumLiteral(parameteR_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE.NUMBER);
		addEEnumLiteral(parameteR_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE.BOOLEAN);
		addEEnumLiteral(parameteR_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE.OBJECT);
		addEEnumLiteral(parameteR_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE.STEP);
		addEEnumLiteral(parameteR_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE.STEP_LIST);
		addEEnumLiteral(parameteR_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE.JOB);
		addEEnumLiteral(parameteR_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE.JOB_LIST);
		addEEnumLiteral(parameteR_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE.DEPLOYMENT);
		addEEnumLiteral(parameteR_TYPEEEnum,
				com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE.DEPLOYMENT_LIST);
		addEEnumLiteral(parameteR_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE.STAGE);
		addEEnumLiteral(parameteR_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.PARAMETER_TYPE.STAGE_LIST);

		initEEnum(repositorY_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.REPOSITORY_TYPE.class,
				"REPOSITORY_TYPE");
		addEEnumLiteral(repositorY_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.REPOSITORY_TYPE.GIT);
		addEEnumLiteral(repositorY_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.REPOSITORY_TYPE.GITHUB);
		addEEnumLiteral(repositorY_TYPEEEnum,
				com.mddoai.metamodel.azuredevops.azuredevopsMM.REPOSITORY_TYPE.GITHUBENTERPRISE);
		addEEnumLiteral(repositorY_TYPEEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.REPOSITORY_TYPE.BITBUCKET);

		initEEnum(targeT_COMMANDSEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.TARGET_COMMANDS.class,
				"TARGET_COMMANDS");
		addEEnumLiteral(targeT_COMMANDSEEnum, com.mddoai.metamodel.azuredevops.azuredevopsMM.TARGET_COMMANDS.ANY);
		addEEnumLiteral(targeT_COMMANDSEEnum,
				com.mddoai.metamodel.azuredevops.azuredevopsMM.TARGET_COMMANDS.RESTRICTED);

		// Create resource
		createResource(eNS_URI);
	}

} //AzuredevopsMMPackageImpl
