/**
 */
package com.mddoai.metamodel.bambooMM.provider;

import com.mddoai.metamodel.bambooMM.BambooFactory;
import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.Environment;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.bambooMM.Environment} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EnvironmentItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentItemProvider(AdapterFactory adapterFactory) {
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
			addReleaseApprovalPrerequisitePropertyDescriptor(object);
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
						getResourceLocator(), getString("_UI_Environment_name_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Environment_name_feature",
								"_UI_Environment_type"),
						BambooPackage.Literals.ENVIRONMENT__NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Release Approval Prerequisite feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addReleaseApprovalPrerequisitePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_Environment_releaseApprovalPrerequisite_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_Environment_releaseApprovalPrerequisite_feature",
						"_UI_Environment_type"),
				BambooPackage.Literals.ENVIRONMENT__RELEASE_APPROVAL_PREREQUISITE, true, false, false,
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
			childrenFeatures.add(BambooPackage.Literals.ENVIRONMENT__TASKS);
			childrenFeatures.add(BambooPackage.Literals.ENVIRONMENT__FINAL_TASKS);
			childrenFeatures.add(BambooPackage.Literals.ENVIRONMENT__VARIABLES);
			childrenFeatures.add(BambooPackage.Literals.ENVIRONMENT__TRIGGERS);
			childrenFeatures.add(BambooPackage.Literals.ENVIRONMENT__NOTIFICATIONS);
			childrenFeatures.add(BambooPackage.Literals.ENVIRONMENT__DOCKER);
			childrenFeatures.add(BambooPackage.Literals.ENVIRONMENT__REQUIREMENTS);
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
	 * This returns Environment.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Environment"));
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
		String label = ((Environment) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_Environment_type")
				: getString("_UI_Environment_type") + " " + label;
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

		switch (notification.getFeatureID(Environment.class)) {
		case BambooPackage.ENVIRONMENT__NAME:
		case BambooPackage.ENVIRONMENT__RELEASE_APPROVAL_PREREQUISITE:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case BambooPackage.ENVIRONMENT__TASKS:
		case BambooPackage.ENVIRONMENT__FINAL_TASKS:
		case BambooPackage.ENVIRONMENT__VARIABLES:
		case BambooPackage.ENVIRONMENT__TRIGGERS:
		case BambooPackage.ENVIRONMENT__NOTIFICATIONS:
		case BambooPackage.ENVIRONMENT__DOCKER:
		case BambooPackage.ENVIRONMENT__REQUIREMENTS:
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

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TASKS,
				BambooFactory.eINSTANCE.createScriptTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TASKS,
				BambooFactory.eINSTANCE.createCleanTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TASKS,
				BambooFactory.eINSTANCE.createCheckoutTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TASKS,
				BambooFactory.eINSTANCE.createMavenTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TASKS,
				BambooFactory.eINSTANCE.createInjectVariablesTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TASKS,
				BambooFactory.eINSTANCE.createTestParserTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TASKS,
				BambooFactory.eINSTANCE.createArtifactDownloadTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TASKS,
				BambooFactory.eINSTANCE.createAnyTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__FINAL_TASKS,
				BambooFactory.eINSTANCE.createScriptTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__FINAL_TASKS,
				BambooFactory.eINSTANCE.createCleanTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__FINAL_TASKS,
				BambooFactory.eINSTANCE.createCheckoutTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__FINAL_TASKS,
				BambooFactory.eINSTANCE.createMavenTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__FINAL_TASKS,
				BambooFactory.eINSTANCE.createInjectVariablesTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__FINAL_TASKS,
				BambooFactory.eINSTANCE.createTestParserTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__FINAL_TASKS,
				BambooFactory.eINSTANCE.createArtifactDownloadTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__FINAL_TASKS,
				BambooFactory.eINSTANCE.createAnyTask()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__VARIABLES,
				BambooFactory.eINSTANCE.create(BambooPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TRIGGERS,
				BambooFactory.eINSTANCE.createPollingTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TRIGGERS,
				BambooFactory.eINSTANCE.createCronTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TRIGGERS,
				BambooFactory.eINSTANCE.createRemoteTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TRIGGERS,
				BambooFactory.eINSTANCE.createAfterDeploymentTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TRIGGERS,
				BambooFactory.eINSTANCE.createBuildSuccessTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TRIGGERS,
				BambooFactory.eINSTANCE.createStageSuccessTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TRIGGERS,
				BambooFactory.eINSTANCE.createEnvironmentSuccessTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__TRIGGERS,
				BambooFactory.eINSTANCE.createScheduledDeploymentTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__NOTIFICATIONS,
				BambooFactory.eINSTANCE.createNotification()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__DOCKER,
				BambooFactory.eINSTANCE.createDockerConfig()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.ENVIRONMENT__REQUIREMENTS,
				BambooFactory.eINSTANCE.createRequirement()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify = childFeature == BambooPackage.Literals.ENVIRONMENT__TASKS
				|| childFeature == BambooPackage.Literals.ENVIRONMENT__FINAL_TASKS;

		if (qualify) {
			return getString("_UI_CreateChild_text2",
					new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return BambooEditPlugin.INSTANCE;
	}

}
