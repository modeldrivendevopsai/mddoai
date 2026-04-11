/**
 */
package com.mddoai.metamodel.bambooMM.provider;

import com.mddoai.metamodel.bambooMM.BambooFactory;
import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.DeploymentProject;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.bambooMM.DeploymentProject} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DeploymentProjectItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentProjectItemProvider(AdapterFactory adapterFactory) {
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
			addSourcePlanPropertyDescriptor(object);
			addServerNamePropertyDescriptor(object);
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
						getResourceLocator(), getString("_UI_DeploymentProject_name_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_DeploymentProject_name_feature",
								"_UI_DeploymentProject_type"),
						BambooPackage.Literals.DEPLOYMENT_PROJECT__NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Source Plan feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourcePlanPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_DeploymentProject_sourcePlan_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_DeploymentProject_sourcePlan_feature",
								"_UI_DeploymentProject_type"),
						BambooPackage.Literals.DEPLOYMENT_PROJECT__SOURCE_PLAN, true, false, false,
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
						getResourceLocator(), getString("_UI_DeploymentProject_serverName_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_DeploymentProject_serverName_feature",
								"_UI_DeploymentProject_type"),
						BambooPackage.Literals.DEPLOYMENT_PROJECT__SERVER_NAME, true, false, false,
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
			childrenFeatures.add(BambooPackage.Literals.DEPLOYMENT_PROJECT__RELEASE_NAMING);
			childrenFeatures.add(BambooPackage.Literals.DEPLOYMENT_PROJECT__ENVIRONMENTS);
			childrenFeatures.add(BambooPackage.Literals.DEPLOYMENT_PROJECT__PERMISSIONS);
			childrenFeatures.add(BambooPackage.Literals.DEPLOYMENT_PROJECT__DEFAULT_ENVIRONMENT_PERMISSIONS);
			childrenFeatures.add(BambooPackage.Literals.DEPLOYMENT_PROJECT__ENVIRONMENT_PERMISSIONS);
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
	 * This returns DeploymentProject.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/DeploymentProject"));
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
		String label = ((DeploymentProject) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_DeploymentProject_type")
				: getString("_UI_DeploymentProject_type") + " " + label;
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

		switch (notification.getFeatureID(DeploymentProject.class)) {
		case BambooPackage.DEPLOYMENT_PROJECT__NAME:
		case BambooPackage.DEPLOYMENT_PROJECT__SOURCE_PLAN:
		case BambooPackage.DEPLOYMENT_PROJECT__SERVER_NAME:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case BambooPackage.DEPLOYMENT_PROJECT__RELEASE_NAMING:
		case BambooPackage.DEPLOYMENT_PROJECT__ENVIRONMENTS:
		case BambooPackage.DEPLOYMENT_PROJECT__PERMISSIONS:
		case BambooPackage.DEPLOYMENT_PROJECT__DEFAULT_ENVIRONMENT_PERMISSIONS:
		case BambooPackage.DEPLOYMENT_PROJECT__ENVIRONMENT_PERMISSIONS:
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

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.DEPLOYMENT_PROJECT__RELEASE_NAMING,
				BambooFactory.eINSTANCE.createReleaseNaming()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.DEPLOYMENT_PROJECT__ENVIRONMENTS,
				BambooFactory.eINSTANCE.createEnvironment()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.DEPLOYMENT_PROJECT__PERMISSIONS,
				BambooFactory.eINSTANCE.createDeploymentPermissionEntry()));

		newChildDescriptors
				.add(createChildParameter(BambooPackage.Literals.DEPLOYMENT_PROJECT__DEFAULT_ENVIRONMENT_PERMISSIONS,
						BambooFactory.eINSTANCE.createEnvironmentPermissionEntry()));

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.DEPLOYMENT_PROJECT__ENVIRONMENT_PERMISSIONS,
				BambooFactory.eINSTANCE.createNamedEnvironmentPermission()));
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
