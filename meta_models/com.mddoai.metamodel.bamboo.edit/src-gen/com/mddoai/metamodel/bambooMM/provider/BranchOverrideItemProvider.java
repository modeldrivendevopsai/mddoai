/**
 */
package com.mddoai.metamodel.bambooMM.provider;

import com.mddoai.metamodel.bambooMM.BambooFactory;
import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.BranchOverride;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.bambooMM.BranchOverride} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class BranchOverrideItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BranchOverrideItemProvider(AdapterFactory adapterFactory) {
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

			addBranchPatternPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Branch Pattern feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBranchPatternPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_BranchOverride_branchPattern_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_BranchOverride_branchPattern_feature",
								"_UI_BranchOverride_type"),
						BambooPackage.Literals.BRANCH_OVERRIDE__BRANCH_PATTERN, true, false, false,
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
			childrenFeatures.add(BambooPackage.Literals.BRANCH_OVERRIDE__STAGES);
			childrenFeatures.add(BambooPackage.Literals.BRANCH_OVERRIDE__JOBS);
			childrenFeatures.add(BambooPackage.Literals.BRANCH_OVERRIDE__TRIGGERS);
			childrenFeatures.add(BambooPackage.Literals.BRANCH_OVERRIDE__NOTIFICATIONS);
			childrenFeatures.add(BambooPackage.Literals.BRANCH_OVERRIDE__VARIABLES);
			childrenFeatures.add(BambooPackage.Literals.BRANCH_OVERRIDE__LABELS);
			childrenFeatures.add(BambooPackage.Literals.BRANCH_OVERRIDE__DOCKER);
			childrenFeatures.add(BambooPackage.Literals.BRANCH_OVERRIDE__BRANCH_CONFIG);
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
	 * This returns BranchOverride.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/BranchOverride"));
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
		String label = ((BranchOverride) object).getBranchPattern();
		return label == null || label.length() == 0 ? getString("_UI_BranchOverride_type")
				: getString("_UI_BranchOverride_type") + " " + label;
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

		switch (notification.getFeatureID(BranchOverride.class)) {
		case BambooPackage.BRANCH_OVERRIDE__BRANCH_PATTERN:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case BambooPackage.BRANCH_OVERRIDE__STAGES:
		case BambooPackage.BRANCH_OVERRIDE__JOBS:
		case BambooPackage.BRANCH_OVERRIDE__TRIGGERS:
		case BambooPackage.BRANCH_OVERRIDE__NOTIFICATIONS:
		case BambooPackage.BRANCH_OVERRIDE__VARIABLES:
		case BambooPackage.BRANCH_OVERRIDE__LABELS:
		case BambooPackage.BRANCH_OVERRIDE__DOCKER:
		case BambooPackage.BRANCH_OVERRIDE__BRANCH_CONFIG:
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

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__STAGES,
				BambooFactory.eINSTANCE.createStage()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__JOBS,
				BambooFactory.eINSTANCE.createJob()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__TRIGGERS,
				BambooFactory.eINSTANCE.createPollingTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__TRIGGERS,
				BambooFactory.eINSTANCE.createCronTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__TRIGGERS,
				BambooFactory.eINSTANCE.createRemoteTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__TRIGGERS,
				BambooFactory.eINSTANCE.createAfterDeploymentTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__TRIGGERS,
				BambooFactory.eINSTANCE.createBuildSuccessTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__TRIGGERS,
				BambooFactory.eINSTANCE.createStageSuccessTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__TRIGGERS,
				BambooFactory.eINSTANCE.createEnvironmentSuccessTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__TRIGGERS,
				BambooFactory.eINSTANCE.createScheduledDeploymentTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__NOTIFICATIONS,
				BambooFactory.eINSTANCE.createNotification()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__VARIABLES,
				BambooFactory.eINSTANCE.create(BambooPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__LABELS,
				BambooFactory.eINSTANCE.createLabel()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__DOCKER,
				BambooFactory.eINSTANCE.createDockerConfig()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.BRANCH_OVERRIDE__BRANCH_CONFIG,
				BambooFactory.eINSTANCE.createBranchSpecificConfig()));
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
