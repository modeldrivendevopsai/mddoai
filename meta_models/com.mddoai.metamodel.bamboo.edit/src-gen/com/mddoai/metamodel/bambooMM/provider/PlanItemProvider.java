/**
 */
package com.mddoai.metamodel.bambooMM.provider;

import com.mddoai.metamodel.bambooMM.BambooFactory;
import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.Plan;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.bambooMM.Plan} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PlanItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlanItemProvider(AdapterFactory adapterFactory) {
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

			addProjectKeyPropertyDescriptor(object);
			addKeyPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addServerNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Project Key feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addProjectKeyPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Plan_projectKey_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Plan_projectKey_feature", "_UI_Plan_type"),
						BambooPackage.Literals.PLAN__PROJECT_KEY, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Key feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addKeyPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Plan_key_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Plan_key_feature", "_UI_Plan_type"),
						BambooPackage.Literals.PLAN__KEY, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
						getResourceLocator(), getString("_UI_Plan_name_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Plan_name_feature", "_UI_Plan_type"),
						BambooPackage.Literals.PLAN__NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Server Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addServerNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Plan_serverName_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Plan_serverName_feature", "_UI_Plan_type"),
						BambooPackage.Literals.PLAN__SERVER_NAME, true, false, false,
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
			childrenFeatures.add(BambooPackage.Literals.PLAN__STAGES);
			childrenFeatures.add(BambooPackage.Literals.PLAN__JOBS);
			childrenFeatures.add(BambooPackage.Literals.PLAN__TRIGGERS);
			childrenFeatures.add(BambooPackage.Literals.PLAN__REPOSITORIES);
			childrenFeatures.add(BambooPackage.Literals.PLAN__VARIABLES);
			childrenFeatures.add(BambooPackage.Literals.PLAN__NOTIFICATIONS);
			childrenFeatures.add(BambooPackage.Literals.PLAN__LABELS);
			childrenFeatures.add(BambooPackage.Literals.PLAN__BRANCHES);
			childrenFeatures.add(BambooPackage.Literals.PLAN__DEPENDENCIES);
			childrenFeatures.add(BambooPackage.Literals.PLAN__DOCKER);
			childrenFeatures.add(BambooPackage.Literals.PLAN__BRANCH_OVERRIDES);
			childrenFeatures.add(BambooPackage.Literals.PLAN__OTHER);
			childrenFeatures.add(BambooPackage.Literals.PLAN__PERMISSIONS);
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
	 * This returns Plan.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Plan"));
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
		String label = ((Plan) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_Plan_type")
				: getString("_UI_Plan_type") + " " + label;
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

		switch (notification.getFeatureID(Plan.class)) {
		case BambooPackage.PLAN__PROJECT_KEY:
		case BambooPackage.PLAN__KEY:
		case BambooPackage.PLAN__NAME:
		case BambooPackage.PLAN__SERVER_NAME:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case BambooPackage.PLAN__STAGES:
		case BambooPackage.PLAN__JOBS:
		case BambooPackage.PLAN__TRIGGERS:
		case BambooPackage.PLAN__REPOSITORIES:
		case BambooPackage.PLAN__VARIABLES:
		case BambooPackage.PLAN__NOTIFICATIONS:
		case BambooPackage.PLAN__LABELS:
		case BambooPackage.PLAN__BRANCHES:
		case BambooPackage.PLAN__DEPENDENCIES:
		case BambooPackage.PLAN__DOCKER:
		case BambooPackage.PLAN__BRANCH_OVERRIDES:
		case BambooPackage.PLAN__OTHER:
		case BambooPackage.PLAN__PERMISSIONS:
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

		newChildDescriptors
				.add(createChildParameter(BambooPackage.Literals.PLAN__STAGES, BambooFactory.eINSTANCE.createStage()));

		newChildDescriptors
				.add(createChildParameter(BambooPackage.Literals.PLAN__JOBS, BambooFactory.eINSTANCE.createJob()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__TRIGGERS,
				BambooFactory.eINSTANCE.createPollingTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__TRIGGERS,
				BambooFactory.eINSTANCE.createCronTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__TRIGGERS,
				BambooFactory.eINSTANCE.createRemoteTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__TRIGGERS,
				BambooFactory.eINSTANCE.createAfterDeploymentTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__TRIGGERS,
				BambooFactory.eINSTANCE.createBuildSuccessTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__TRIGGERS,
				BambooFactory.eINSTANCE.createStageSuccessTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__TRIGGERS,
				BambooFactory.eINSTANCE.createEnvironmentSuccessTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__TRIGGERS,
				BambooFactory.eINSTANCE.createScheduledDeploymentTrigger()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__REPOSITORIES,
				BambooFactory.eINSTANCE.createLinkedRepository()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__REPOSITORIES,
				BambooFactory.eINSTANCE.createGitRepository()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__REPOSITORIES,
				BambooFactory.eINSTANCE.createBitbucketCloudRepository()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__REPOSITORIES,
				BambooFactory.eINSTANCE.createBitbucketServerRepository()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__REPOSITORIES,
				BambooFactory.eINSTANCE.createGithubRepository()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__REPOSITORIES,
				BambooFactory.eINSTANCE.createAnyVcsRepository()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__VARIABLES,
				BambooFactory.eINSTANCE.create(BambooPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__NOTIFICATIONS,
				BambooFactory.eINSTANCE.createNotification()));

		newChildDescriptors
				.add(createChildParameter(BambooPackage.Literals.PLAN__LABELS, BambooFactory.eINSTANCE.createLabel()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__BRANCHES,
				BambooFactory.eINSTANCE.createBranchManagement()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__DEPENDENCIES,
				BambooFactory.eINSTANCE.createDependencies()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__DOCKER,
				BambooFactory.eINSTANCE.createDockerConfig()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__BRANCH_OVERRIDES,
				BambooFactory.eINSTANCE.createBranchOverride()));

		newChildDescriptors.add(
				createChildParameter(BambooPackage.Literals.PLAN__OTHER, BambooFactory.eINSTANCE.createOtherConfig()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.PLAN__PERMISSIONS,
				BambooFactory.eINSTANCE.createPlanPermissionEntry()));
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
