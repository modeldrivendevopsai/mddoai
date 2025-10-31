/**
 */
package com.mddoai.metamodel.swarch.provider;

import com.mddoai.metamodel.swarch.SoftwareArchitecture;
import com.mddoai.metamodel.swarch.SwarchPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.swarch.SoftwareArchitecture} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SoftwareArchitectureItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareArchitectureItemProvider(AdapterFactory adapterFactory) {
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
			addTypePropertyDescriptor(object);
			addBuild_systemPropertyDescriptor(object);
			addLanguagePropertyDescriptor(object);
			addUnit_testPropertyDescriptor(object);
			addHealth_checkPropertyDescriptor(object);
			addLint_checkPropertyDescriptor(object);
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
						getResourceLocator(), getString("_UI_SoftwareArchitecture_name_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_SoftwareArchitecture_name_feature",
								"_UI_SoftwareArchitecture_type"),
						SwarchPackage.Literals.SOFTWARE_ARCHITECTURE__NAME, true, false, false,
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
						getResourceLocator(), getString("_UI_SoftwareArchitecture_type_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_SoftwareArchitecture_type_feature",
								"_UI_SoftwareArchitecture_type"),
						SwarchPackage.Literals.SOFTWARE_ARCHITECTURE__TYPE, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Build system feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBuild_systemPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_SoftwareArchitecture_build_system_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_SoftwareArchitecture_build_system_feature",
						"_UI_SoftwareArchitecture_type"),
				SwarchPackage.Literals.SOFTWARE_ARCHITECTURE__BUILD_SYSTEM, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Language feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLanguagePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_SoftwareArchitecture_language_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_SoftwareArchitecture_language_feature",
						"_UI_SoftwareArchitecture_type"),
				SwarchPackage.Literals.SOFTWARE_ARCHITECTURE__LANGUAGE, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Unit test feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUnit_testPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_SoftwareArchitecture_unit_test_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_SoftwareArchitecture_unit_test_feature",
						"_UI_SoftwareArchitecture_type"),
				SwarchPackage.Literals.SOFTWARE_ARCHITECTURE__UNIT_TEST, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Health check feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addHealth_checkPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_SoftwareArchitecture_health_check_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_SoftwareArchitecture_health_check_feature",
						"_UI_SoftwareArchitecture_type"),
				SwarchPackage.Literals.SOFTWARE_ARCHITECTURE__HEALTH_CHECK, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Lint check feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLint_checkPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_SoftwareArchitecture_lint_check_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_SoftwareArchitecture_lint_check_feature",
						"_UI_SoftwareArchitecture_type"),
				SwarchPackage.Literals.SOFTWARE_ARCHITECTURE__LINT_CHECK, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This returns SoftwareArchitecture.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/SoftwareArchitecture"));
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
		String label = ((SoftwareArchitecture) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_SoftwareArchitecture_type")
				: getString("_UI_SoftwareArchitecture_type") + " " + label;
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

		switch (notification.getFeatureID(SoftwareArchitecture.class)) {
		case SwarchPackage.SOFTWARE_ARCHITECTURE__NAME:
		case SwarchPackage.SOFTWARE_ARCHITECTURE__TYPE:
		case SwarchPackage.SOFTWARE_ARCHITECTURE__BUILD_SYSTEM:
		case SwarchPackage.SOFTWARE_ARCHITECTURE__LANGUAGE:
		case SwarchPackage.SOFTWARE_ARCHITECTURE__UNIT_TEST:
		case SwarchPackage.SOFTWARE_ARCHITECTURE__HEALTH_CHECK:
		case SwarchPackage.SOFTWARE_ARCHITECTURE__LINT_CHECK:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return SwarchEditPlugin.INSTANCE;
	}

}
