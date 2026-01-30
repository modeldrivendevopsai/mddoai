/**
 */
package com.mddoai.metamodel.pim.pimMM.provider;

import com.mddoai.metamodel.pim.pimMM.PimMMFactory;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link java.util.Map.Entry} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AssignmentItemProvider extends ExpressionItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignmentItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenFeatures.add(PimMMPackage.Literals.ASSIGNMENT__KEY);
			childrenFeatures.add(PimMMPackage.Literals.ASSIGNMENT__VALUE);
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
	 * This returns Assignment.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Assignment"));
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
		Map.Entry<?, ?> assignment = (Map.Entry<?, ?>) object;
		return "" + assignment.getKey() + " -> " + assignment.getValue();
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

		switch (notification.getFeatureID(Map.Entry.class)) {
		case PimMMPackage.ASSIGNMENT__KEY:
		case PimMMPackage.ASSIGNMENT__VALUE:
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

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.ASSIGNMENT__KEY,
				PimMMFactory.eINSTANCE.createVariableDeclaration()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.ASSIGNMENT__VALUE,
				PimMMFactory.eINSTANCE.create(PimMMPackage.Literals.ASSIGNMENT)));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.ASSIGNMENT__VALUE, PimMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.ASSIGNMENT__VALUE,
				PimMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.ASSIGNMENT__VALUE,
				PimMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.ASSIGNMENT__VALUE,
				PimMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.ASSIGNMENT__VALUE,
				PimMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.ASSIGNMENT__VALUE,
				PimMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.ASSIGNMENT__VALUE,
				PimMMFactory.eINSTANCE.createEqualityOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.ASSIGNMENT__VALUE,
				PimMMFactory.eINSTANCE.createComparisonOp()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.ASSIGNMENT__VALUE, PimMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.ASSIGNMENT__VALUE, PimMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.ASSIGNMENT__VALUE, PimMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.ASSIGNMENT__VALUE, PimMMFactory.eINSTANCE.createUnaryOp()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.ASSIGNMENT__VALUE, PimMMFactory.eINSTANCE.createNegation()));
	}

}
