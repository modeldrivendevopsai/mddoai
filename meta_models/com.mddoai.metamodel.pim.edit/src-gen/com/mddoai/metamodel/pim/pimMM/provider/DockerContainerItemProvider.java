/**
 */
package com.mddoai.metamodel.pim.pimMM.provider;

import com.mddoai.metamodel.pim.pimMM.DockerContainer;
import com.mddoai.metamodel.pim.pimMM.PimMMFactory;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.pim.pimMM.DockerContainer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DockerContainerItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DockerContainerItemProvider(AdapterFactory adapterFactory) {
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

			addLabelPropertyDescriptor(object);
			addOptionsPropertyDescriptor(object);
			addRegistryUsernamePropertyDescriptor(object);
			addRegistryPasswordPropertyDescriptor(object);
			addNetworkPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Label feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLabelPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_DockerContainer_label_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_DockerContainer_label_feature",
								"_UI_DockerContainer_type"),
						PimMMPackage.Literals.DOCKER_CONTAINER__LABEL, true, false, false,
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
						getResourceLocator(), getString("_UI_DockerContainer_options_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_DockerContainer_options_feature",
								"_UI_DockerContainer_type"),
						PimMMPackage.Literals.DOCKER_CONTAINER__OPTIONS, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Registry Username feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRegistryUsernamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_DockerContainer_registryUsername_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_DockerContainer_registryUsername_feature",
								"_UI_DockerContainer_type"),
						PimMMPackage.Literals.DOCKER_CONTAINER__REGISTRY_USERNAME, true, false, true, null, null,
						null));
	}

	/**
	 * This adds a property descriptor for the Registry Password feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRegistryPasswordPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_DockerContainer_registryPassword_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_DockerContainer_registryPassword_feature",
								"_UI_DockerContainer_type"),
						PimMMPackage.Literals.DOCKER_CONTAINER__REGISTRY_PASSWORD, true, false, true, null, null,
						null));
	}

	/**
	 * This adds a property descriptor for the Network feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNetworkPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_DockerContainer_network_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_DockerContainer_network_feature",
								"_UI_DockerContainer_type"),
						PimMMPackage.Literals.DOCKER_CONTAINER__NETWORK, true, false, true, null, null, null));
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
			childrenFeatures.add(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE);
			childrenFeatures.add(PimMMPackage.Literals.DOCKER_CONTAINER__ENVIRONMENT_VARIABLES);
			childrenFeatures.add(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES);
			childrenFeatures.add(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS);
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
	 * This returns DockerContainer.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/DockerContainer"));
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
		String label = ((DockerContainer) object).getLabel();
		return label == null || label.length() == 0 ? getString("_UI_DockerContainer_type")
				: getString("_UI_DockerContainer_type") + " " + label;
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

		switch (notification.getFeatureID(DockerContainer.class)) {
		case PimMMPackage.DOCKER_CONTAINER__LABEL:
		case PimMMPackage.DOCKER_CONTAINER__OPTIONS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case PimMMPackage.DOCKER_CONTAINER__IMAGE:
		case PimMMPackage.DOCKER_CONTAINER__ENVIRONMENT_VARIABLES:
		case PimMMPackage.DOCKER_CONTAINER__VOLUMES:
		case PimMMPackage.DOCKER_CONTAINER__PORTS:
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

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE,
				PimMMFactory.eINSTANCE.create(PimMMPackage.Literals.ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE,
				PimMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE,
				PimMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE,
				PimMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE,
				PimMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE,
				PimMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE,
				PimMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE,
				PimMMFactory.eINSTANCE.createEqualityOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE,
				PimMMFactory.eINSTANCE.createComparisonOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE,
				PimMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE,
				PimMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE, PimMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE,
				PimMMFactory.eINSTANCE.createUnaryOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE,
				PimMMFactory.eINSTANCE.createNegation()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__ENVIRONMENT_VARIABLES,
				PimMMFactory.eINSTANCE.create(PimMMPackage.Literals.ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES,
				PimMMFactory.eINSTANCE.create(PimMMPackage.Literals.ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES,
				PimMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES,
				PimMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES,
				PimMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES,
				PimMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES,
				PimMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES,
				PimMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES,
				PimMMFactory.eINSTANCE.createEqualityOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES,
				PimMMFactory.eINSTANCE.createComparisonOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES,
				PimMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES,
				PimMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES,
				PimMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES,
				PimMMFactory.eINSTANCE.createUnaryOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES,
				PimMMFactory.eINSTANCE.createNegation()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS,
				PimMMFactory.eINSTANCE.create(PimMMPackage.Literals.ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS,
				PimMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS,
				PimMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS,
				PimMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS,
				PimMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS,
				PimMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS,
				PimMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS,
				PimMMFactory.eINSTANCE.createEqualityOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS,
				PimMMFactory.eINSTANCE.createComparisonOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS,
				PimMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS,
				PimMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS, PimMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS,
				PimMMFactory.eINSTANCE.createUnaryOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.DOCKER_CONTAINER__PORTS,
				PimMMFactory.eINSTANCE.createNegation()));
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

		boolean qualify = childFeature == PimMMPackage.Literals.DOCKER_CONTAINER__IMAGE
				|| childFeature == PimMMPackage.Literals.DOCKER_CONTAINER__ENVIRONMENT_VARIABLES
				|| childFeature == PimMMPackage.Literals.DOCKER_CONTAINER__VOLUMES
				|| childFeature == PimMMPackage.Literals.DOCKER_CONTAINER__PORTS;

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
		return PimMMEditPlugin.INSTANCE;
	}

}
