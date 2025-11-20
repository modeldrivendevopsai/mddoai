/**
 */
package com.mddoai.metamodel.pim.pimMM.provider;

import com.mddoai.metamodel.pim.pimMM.Cache;
import com.mddoai.metamodel.pim.pimMM.PimMMFactory;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.pim.pimMM.Cache} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CacheItemProvider extends NonConditionalStepItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CacheItemProvider(AdapterFactory adapterFactory) {
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

			addStorePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Store feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStorePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Cache_store_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Cache_store_feature", "_UI_Cache_type"),
						PimMMPackage.Literals.CACHE__STORE, true, false, false,
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
			childrenFeatures.add(PimMMPackage.Literals.CACHE__CACHE_NAME);
			childrenFeatures.add(PimMMPackage.Literals.CACHE__KEYS);
			childrenFeatures.add(PimMMPackage.Literals.CACHE__PATHS);
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
	 * This returns Cache.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Cache"));
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
		String label = ((Cache) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_Cache_type")
				: getString("_UI_Cache_type") + " " + label;
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

		switch (notification.getFeatureID(Cache.class)) {
		case PimMMPackage.CACHE__STORE:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case PimMMPackage.CACHE__CACHE_NAME:
		case PimMMPackage.CACHE__KEYS:
		case PimMMPackage.CACHE__PATHS:
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

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.CACHE__CACHE_NAME,
				PimMMFactory.eINSTANCE.create(PimMMPackage.Literals.ASSIGNMENT)));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.CACHE__CACHE_NAME, PimMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.CACHE__CACHE_NAME,
				PimMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.CACHE__CACHE_NAME,
				PimMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.CACHE__CACHE_NAME,
				PimMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.CACHE__CACHE_NAME,
				PimMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.CACHE__CACHE_NAME,
				PimMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.CACHE__CACHE_NAME,
				PimMMFactory.eINSTANCE.createEqualityOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.CACHE__CACHE_NAME,
				PimMMFactory.eINSTANCE.createComparisonOp()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.CACHE__CACHE_NAME, PimMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.CACHE__CACHE_NAME, PimMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.CACHE__CACHE_NAME, PimMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.CACHE__CACHE_NAME, PimMMFactory.eINSTANCE.createUnaryOp()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.CACHE__CACHE_NAME, PimMMFactory.eINSTANCE.createNegation()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.CACHE__KEYS,
				PimMMFactory.eINSTANCE.create(PimMMPackage.Literals.ASSIGNMENT)));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.CACHE__KEYS, PimMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.CACHE__KEYS, PimMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.CACHE__KEYS, PimMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.CACHE__KEYS, PimMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.CACHE__KEYS, PimMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.CACHE__KEYS,
				PimMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.CACHE__KEYS, PimMMFactory.eINSTANCE.createEqualityOp()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.CACHE__KEYS, PimMMFactory.eINSTANCE.createComparisonOp()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.CACHE__KEYS, PimMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.CACHE__KEYS, PimMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.CACHE__KEYS, PimMMFactory.eINSTANCE.createOr()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.CACHE__KEYS, PimMMFactory.eINSTANCE.createUnaryOp()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.CACHE__KEYS, PimMMFactory.eINSTANCE.createNegation()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.CACHE__PATHS,
				PimMMFactory.eINSTANCE.create(PimMMPackage.Literals.ASSIGNMENT)));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.CACHE__PATHS, PimMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.CACHE__PATHS, PimMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.CACHE__PATHS,
				PimMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.CACHE__PATHS, PimMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.CACHE__PATHS,
				PimMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.CACHE__PATHS,
				PimMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.CACHE__PATHS, PimMMFactory.eINSTANCE.createEqualityOp()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.CACHE__PATHS, PimMMFactory.eINSTANCE.createComparisonOp()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.CACHE__PATHS, PimMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.CACHE__PATHS, PimMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.CACHE__PATHS, PimMMFactory.eINSTANCE.createOr()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.CACHE__PATHS, PimMMFactory.eINSTANCE.createUnaryOp()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.CACHE__PATHS, PimMMFactory.eINSTANCE.createNegation()));
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

		boolean qualify = childFeature == PimMMPackage.Literals.NON_CONDITIONAL_STEP__ENVIRONMENT_VARIABLES
				|| childFeature == PimMMPackage.Literals.NON_CONDITIONAL_STEP__WORKING_DIRECTORY
				|| childFeature == PimMMPackage.Literals.NON_CONDITIONAL_STEP__TIMEOUT_MINUTES
				|| childFeature == PimMMPackage.Literals.CACHE__CACHE_NAME
				|| childFeature == PimMMPackage.Literals.CACHE__KEYS
				|| childFeature == PimMMPackage.Literals.CACHE__PATHS;

		if (qualify) {
			return getString("_UI_CreateChild_text2",
					new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
