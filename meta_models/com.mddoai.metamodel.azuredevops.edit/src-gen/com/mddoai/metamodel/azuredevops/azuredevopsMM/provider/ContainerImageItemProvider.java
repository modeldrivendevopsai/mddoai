/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.provider;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMFactory;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerImage} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ContainerImageItemProvider extends ContainerReferenceItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContainerImageItemProvider(AdapterFactory adapterFactory) {
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

			addImagePropertyDescriptor(object);
			addEndpointPropertyDescriptor(object);
			addOptionsPropertyDescriptor(object);
			addPortsPropertyDescriptor(object);
			addVolumesPropertyDescriptor(object);
			addMapDockerSocketPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Image feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addImagePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_ContainerImage_image_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_ContainerImage_image_feature",
								"_UI_ContainerImage_type"),
						AzuredevopsMMPackage.Literals.CONTAINER_IMAGE__IMAGE, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Endpoint feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEndpointPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_ContainerImage_endpoint_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_ContainerImage_endpoint_feature",
								"_UI_ContainerImage_type"),
						AzuredevopsMMPackage.Literals.CONTAINER_IMAGE__ENDPOINT, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Options feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOptionsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_ContainerImage_options_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_ContainerImage_options_feature",
								"_UI_ContainerImage_type"),
						AzuredevopsMMPackage.Literals.CONTAINER_IMAGE__OPTIONS, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Ports feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPortsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_ContainerImage_ports_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_ContainerImage_ports_feature",
								"_UI_ContainerImage_type"),
						AzuredevopsMMPackage.Literals.CONTAINER_IMAGE__PORTS, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Volumes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVolumesPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_ContainerImage_volumes_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_ContainerImage_volumes_feature",
								"_UI_ContainerImage_type"),
						AzuredevopsMMPackage.Literals.CONTAINER_IMAGE__VOLUMES, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Map Docker Socket feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMapDockerSocketPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ContainerImage_mapDockerSocket_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_ContainerImage_mapDockerSocket_feature",
						"_UI_ContainerImage_type"),
				AzuredevopsMMPackage.Literals.CONTAINER_IMAGE__MAP_DOCKER_SOCKET, true, false, false,
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
			childrenFeatures.add(AzuredevopsMMPackage.Literals.CONTAINER_IMAGE__ENV);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.CONTAINER_IMAGE__MOUNT_READ_ONLY);
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
	 * This returns ContainerImage.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ContainerImage"));
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
		String label = ((ContainerImage) object).getImage();
		return label == null || label.length() == 0 ? getString("_UI_ContainerImage_type")
				: getString("_UI_ContainerImage_type") + " " + label;
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

		switch (notification.getFeatureID(ContainerImage.class)) {
		case AzuredevopsMMPackage.CONTAINER_IMAGE__IMAGE:
		case AzuredevopsMMPackage.CONTAINER_IMAGE__ENDPOINT:
		case AzuredevopsMMPackage.CONTAINER_IMAGE__OPTIONS:
		case AzuredevopsMMPackage.CONTAINER_IMAGE__PORTS:
		case AzuredevopsMMPackage.CONTAINER_IMAGE__VOLUMES:
		case AzuredevopsMMPackage.CONTAINER_IMAGE__MAP_DOCKER_SOCKET:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case AzuredevopsMMPackage.CONTAINER_IMAGE__ENV:
		case AzuredevopsMMPackage.CONTAINER_IMAGE__MOUNT_READ_ONLY:
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

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.CONTAINER_IMAGE__ENV,
				AzuredevopsMMFactory.eINSTANCE.create(AzuredevopsMMPackage.Literals.ENV_ENTRY)));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.CONTAINER_IMAGE__MOUNT_READ_ONLY,
				AzuredevopsMMFactory.eINSTANCE.createMountReadOnly()));
	}

}
