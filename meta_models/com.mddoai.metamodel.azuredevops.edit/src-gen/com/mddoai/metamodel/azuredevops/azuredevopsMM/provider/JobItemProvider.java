/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.provider;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMFactory;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.Job;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.Job} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class JobItemProvider extends JobElementItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JobItemProvider(AdapterFactory adapterFactory) {
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

			addJobPropertyDescriptor(object);
			addDisplayNamePropertyDescriptor(object);
			addConditionPropertyDescriptor(object);
			addContinueOnErrorPropertyDescriptor(object);
			addTimeoutInMinutesPropertyDescriptor(object);
			addCancelTimeoutInMinutesPropertyDescriptor(object);
			addDependsOnPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Job feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addJobPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_job_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_job_feature", "_UI_Job_type"),
						AzuredevopsMMPackage.Literals.JOB__JOB, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Display Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDisplayNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_displayName_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_displayName_feature", "_UI_Job_type"),
						AzuredevopsMMPackage.Literals.JOB__DISPLAY_NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Condition feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addConditionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_condition_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_condition_feature", "_UI_Job_type"),
						AzuredevopsMMPackage.Literals.JOB__CONDITION, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Continue On Error feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addContinueOnErrorPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_continueOnError_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_continueOnError_feature",
								"_UI_Job_type"),
						AzuredevopsMMPackage.Literals.JOB__CONTINUE_ON_ERROR, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Timeout In Minutes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTimeoutInMinutesPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_timeoutInMinutes_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_timeoutInMinutes_feature",
								"_UI_Job_type"),
						AzuredevopsMMPackage.Literals.JOB__TIMEOUT_IN_MINUTES, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Cancel Timeout In Minutes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCancelTimeoutInMinutesPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_cancelTimeoutInMinutes_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_cancelTimeoutInMinutes_feature",
								"_UI_Job_type"),
						AzuredevopsMMPackage.Literals.JOB__CANCEL_TIMEOUT_IN_MINUTES, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Depends On feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDependsOnPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_dependsOn_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_dependsOn_feature", "_UI_Job_type"),
						AzuredevopsMMPackage.Literals.JOB__DEPENDS_ON, true, false, false,
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
			childrenFeatures.add(AzuredevopsMMPackage.Literals.JOB__POOL);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.JOB__VARIABLES);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.JOB__STRATEGY);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.JOB__CONTAINER);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.JOB__SERVICES);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.JOB__WORKSPACE);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.JOB__USES);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.JOB__STEPS);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.JOB__TEMPLATE_CONTEXT);
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
	 * This returns Job.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Job"));
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
		String label = ((Job) object).getDisplayName();
		return label == null || label.length() == 0 ? getString("_UI_Job_type")
				: getString("_UI_Job_type") + " " + label;
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

		switch (notification.getFeatureID(Job.class)) {
		case AzuredevopsMMPackage.JOB__JOB:
		case AzuredevopsMMPackage.JOB__DISPLAY_NAME:
		case AzuredevopsMMPackage.JOB__CONDITION:
		case AzuredevopsMMPackage.JOB__CONTINUE_ON_ERROR:
		case AzuredevopsMMPackage.JOB__TIMEOUT_IN_MINUTES:
		case AzuredevopsMMPackage.JOB__CANCEL_TIMEOUT_IN_MINUTES:
		case AzuredevopsMMPackage.JOB__DEPENDS_ON:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case AzuredevopsMMPackage.JOB__POOL:
		case AzuredevopsMMPackage.JOB__VARIABLES:
		case AzuredevopsMMPackage.JOB__STRATEGY:
		case AzuredevopsMMPackage.JOB__CONTAINER:
		case AzuredevopsMMPackage.JOB__SERVICES:
		case AzuredevopsMMPackage.JOB__WORKSPACE:
		case AzuredevopsMMPackage.JOB__USES:
		case AzuredevopsMMPackage.JOB__STEPS:
		case AzuredevopsMMPackage.JOB__TEMPLATE_CONTEXT:
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

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__POOL,
				AzuredevopsMMFactory.eINSTANCE.createPool()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__VARIABLES,
				AzuredevopsMMFactory.eINSTANCE.createVariableName()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__VARIABLES,
				AzuredevopsMMFactory.eINSTANCE.createVariableGroup()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__VARIABLES,
				AzuredevopsMMFactory.eINSTANCE.createVariableTemplate()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__STRATEGY,
				AzuredevopsMMFactory.eINSTANCE.createJobStrategy()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__CONTAINER,
				AzuredevopsMMFactory.eINSTANCE.createContainerAlias()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__CONTAINER,
				AzuredevopsMMFactory.eINSTANCE.createContainerImage()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__SERVICES,
				AzuredevopsMMFactory.eINSTANCE.create(AzuredevopsMMPackage.Literals.SERVICE_ENTRY)));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__WORKSPACE,
				AzuredevopsMMFactory.eINSTANCE.createWorkspace()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__USES,
				AzuredevopsMMFactory.eINSTANCE.createJobUses()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createTaskStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createScriptStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createBashStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createPowershellStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createPwshStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createCheckoutStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createDownloadStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createDownloadBuildStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createGetPackageStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createPublishStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createReviewAppStep()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__STEPS,
				AzuredevopsMMFactory.eINSTANCE.createStepTemplate()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.JOB__TEMPLATE_CONTEXT,
				AzuredevopsMMFactory.eINSTANCE.createTemplateContext()));
	}

}
