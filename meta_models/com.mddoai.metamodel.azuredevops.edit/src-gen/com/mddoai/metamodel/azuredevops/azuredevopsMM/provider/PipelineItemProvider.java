/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.provider;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMFactory;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Pipeline} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PipelineItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PipelineItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNamePropertyDescriptor(object);
			addAppendCommitMessageToRunNamePropertyDescriptor(object);
			addLockBehaviorPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Pipeline_name_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Pipeline_name_feature",
								"_UI_Pipeline_type"),
						AzuredevopsMMPackage.Literals.PIPELINE__NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Append Commit Message To Run Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAppendCommitMessageToRunNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Pipeline_appendCommitMessageToRunName_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_Pipeline_appendCommitMessageToRunName_feature",
						"_UI_Pipeline_type"),
				AzuredevopsMMPackage.Literals.PIPELINE__APPEND_COMMIT_MESSAGE_TO_RUN_NAME, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Lock Behavior feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLockBehaviorPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Pipeline_lockBehavior_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Pipeline_lockBehavior_feature",
								"_UI_Pipeline_type"),
						AzuredevopsMMPackage.Literals.PIPELINE__LOCK_BEHAVIOR, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.PIPELINE__TRIGGER);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.PIPELINE__PR);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.PIPELINE__SCHEDULES);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.PIPELINE__PARAMETERS);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.PIPELINE__VARIABLES);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.PIPELINE__POOL);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.PIPELINE__RESOURCES);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.PIPELINE__STAGES);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.PIPELINE__JOBS);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.PIPELINE__STEPS);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.PIPELINE__EXTENDS);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.PIPELINE__WORKSPACE);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Pipeline.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Pipeline"));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Pipeline) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_Pipeline_type")
				: getString("_UI_Pipeline_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Pipeline.class)) {
		case AzuredevopsMMPackage.PIPELINE__NAME:
		case AzuredevopsMMPackage.PIPELINE__APPEND_COMMIT_MESSAGE_TO_RUN_NAME:
		case AzuredevopsMMPackage.PIPELINE__LOCK_BEHAVIOR:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case AzuredevopsMMPackage.PIPELINE__TRIGGER:
		case AzuredevopsMMPackage.PIPELINE__PR:
		case AzuredevopsMMPackage.PIPELINE__SCHEDULES:
		case AzuredevopsMMPackage.PIPELINE__PARAMETERS:
		case AzuredevopsMMPackage.PIPELINE__VARIABLES:
		case AzuredevopsMMPackage.PIPELINE__POOL:
		case AzuredevopsMMPackage.PIPELINE__RESOURCES:
		case AzuredevopsMMPackage.PIPELINE__STAGES:
		case AzuredevopsMMPackage.PIPELINE__JOBS:
		case AzuredevopsMMPackage.PIPELINE__STEPS:
		case AzuredevopsMMPackage.PIPELINE__EXTENDS:
		case AzuredevopsMMPackage.PIPELINE__WORKSPACE:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__TRIGGER,
				AzuredevopsMMFactory.eINSTANCE.createTrigger()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__PR,
				AzuredevopsMMFactory.eINSTANCE.createPrTrigger()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__SCHEDULES,
				AzuredevopsMMFactory.eINSTANCE.createCronSchedule()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__PARAMETERS,
				AzuredevopsMMFactory.eINSTANCE.createParameter()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__VARIABLES,
				AzuredevopsMMFactory.eINSTANCE.createVariableName()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__VARIABLES,
				AzuredevopsMMFactory.eINSTANCE.createVariableGroup()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__VARIABLES,
				AzuredevopsMMFactory.eINSTANCE.createVariableTemplate()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__POOL,
				AzuredevopsMMFactory.eINSTANCE.createPool()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__RESOURCES,
				AzuredevopsMMFactory.eINSTANCE.createResources()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__STAGES,
				AzuredevopsMMFactory.eINSTANCE.createStage()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__STAGES,
				AzuredevopsMMFactory.eINSTANCE.createStageTemplate()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__JOBS,
				AzuredevopsMMFactory.eINSTANCE.createJob()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__JOBS,
				AzuredevopsMMFactory.eINSTANCE.createDeploymentJob()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__JOBS,
				AzuredevopsMMFactory.eINSTANCE.createJobTemplate()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createTaskStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createScriptStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createBashStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createPowershellStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createPwshStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createCheckoutStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createDownloadStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createDownloadBuildStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createGetPackageStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createPublishStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createReviewAppStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createStepTemplate()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__EXTENDS,
				AzuredevopsMMFactory.eINSTANCE.createExtends()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.PIPELINE__WORKSPACE,
				AzuredevopsMMFactory.eINSTANCE.createWorkspace()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return AzuredevopsMMEditPlugin.INSTANCE;
	}

}
