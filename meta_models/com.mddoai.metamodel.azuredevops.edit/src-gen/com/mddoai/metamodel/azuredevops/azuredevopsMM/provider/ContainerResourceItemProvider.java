/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.provider;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMFactory;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.ContainerResource} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ContainerResourceItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContainerResourceItemProvider(AdapterFactory adapterFactory) {
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

			addContainerPropertyDescriptor(object);
			addImagePropertyDescriptor(object);
			addTypePropertyDescriptor(object);
			addAzureSubscriptionPropertyDescriptor(object);
			addResourceGroupPropertyDescriptor(object);
			addRegistryPropertyDescriptor(object);
			addRepositoryPropertyDescriptor(object);
			addEndpointPropertyDescriptor(object);
			addLocalImagePropertyDescriptor(object);
			addOptionsPropertyDescriptor(object);
			addPortsPropertyDescriptor(object);
			addVolumesPropertyDescriptor(object);
			addMapDockerSocketPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Container feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addContainerPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ContainerResource_container_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_ContainerResource_container_feature",
						"_UI_ContainerResource_type"),
				AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__CONTAINER, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
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
						getResourceLocator(), getString("_UI_ContainerResource_image_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_ContainerResource_image_feature",
								"_UI_ContainerResource_type"),
						AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__IMAGE, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_ContainerResource_type_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_ContainerResource_type_feature",
								"_UI_ContainerResource_type"),
						AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__TYPE, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Azure Subscription feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAzureSubscriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_ContainerResource_azureSubscription_feature"),
						getString("_UI_PropertyDescriptor_description",
								"_UI_ContainerResource_azureSubscription_feature", "_UI_ContainerResource_type"),
						AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__AZURE_SUBSCRIPTION, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Resource Group feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addResourceGroupPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ContainerResource_resourceGroup_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_ContainerResource_resourceGroup_feature",
						"_UI_ContainerResource_type"),
				AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__RESOURCE_GROUP, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Registry feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRegistryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ContainerResource_registry_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_ContainerResource_registry_feature",
						"_UI_ContainerResource_type"),
				AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__REGISTRY, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Repository feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRepositoryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ContainerResource_repository_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_ContainerResource_repository_feature",
						"_UI_ContainerResource_type"),
				AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__REPOSITORY, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Endpoint feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEndpointPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ContainerResource_endpoint_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_ContainerResource_endpoint_feature",
						"_UI_ContainerResource_type"),
				AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__ENDPOINT, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Local Image feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLocalImagePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ContainerResource_localImage_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_ContainerResource_localImage_feature",
						"_UI_ContainerResource_type"),
				AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__LOCAL_IMAGE, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Options feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOptionsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ContainerResource_options_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_ContainerResource_options_feature",
						"_UI_ContainerResource_type"),
				AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__OPTIONS, true, false, false,
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
						getResourceLocator(), getString("_UI_ContainerResource_ports_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_ContainerResource_ports_feature",
								"_UI_ContainerResource_type"),
						AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__PORTS, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Volumes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVolumesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_ContainerResource_volumes_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_ContainerResource_volumes_feature",
						"_UI_ContainerResource_type"),
				AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__VOLUMES, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Map Docker Socket feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMapDockerSocketPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_ContainerResource_mapDockerSocket_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_ContainerResource_mapDockerSocket_feature",
								"_UI_ContainerResource_type"),
						AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__MAP_DOCKER_SOCKET, true, false, false,
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
			childrenFeatures.add(AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__ENV);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__MOUNT_READ_ONLY);
			childrenFeatures.add(AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__TRIGGER);
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
	 * This returns ContainerResource.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ContainerResource"));
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
		String label = ((ContainerResource) object).getContainer();
		return label == null || label.length() == 0 ? getString("_UI_ContainerResource_type")
				: getString("_UI_ContainerResource_type") + " " + label;
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

		switch (notification.getFeatureID(ContainerResource.class)) {
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__CONTAINER:
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__IMAGE:
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__TYPE:
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__AZURE_SUBSCRIPTION:
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__RESOURCE_GROUP:
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__REGISTRY:
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__REPOSITORY:
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__ENDPOINT:
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__LOCAL_IMAGE:
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__OPTIONS:
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__PORTS:
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__VOLUMES:
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__MAP_DOCKER_SOCKET:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__ENV:
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__MOUNT_READ_ONLY:
		case AzuredevopsMMPackage.CONTAINER_RESOURCE__TRIGGER:
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

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__ENV,
				AzuredevopsMMFactory.eINSTANCE.create(AzuredevopsMMPackage.Literals.ENV_ENTRY)));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__MOUNT_READ_ONLY,
				AzuredevopsMMFactory.eINSTANCE.createMountReadOnly()));

		newChildDescriptors.add(createChildParameter(AzuredevopsMMPackage.Literals.CONTAINER_RESOURCE__TRIGGER,
				AzuredevopsMMFactory.eINSTANCE.createContainerResourceTrigger()));
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
