/**
 */
package com.mddoai.metamodel.pim.pimMM.provider;

import com.mddoai.metamodel.pim.pimMM.MatrixAxis;
import com.mddoai.metamodel.pim.pimMM.PimMMFactory;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.mddoai.metamodel.pim.pimMM.MatrixAxis} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MatrixAxisItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MatrixAxisItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(PimMMPackage.Literals.MATRIX_AXIS__NAME);
			childrenFeatures.add(PimMMPackage.Literals.MATRIX_AXIS__CELLS);
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
	 * This returns MatrixAxis.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/MatrixAxis"));
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
		return getString("_UI_MatrixAxis_type");
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

		switch (notification.getFeatureID(MatrixAxis.class)) {
		case PimMMPackage.MATRIX_AXIS__NAME:
		case PimMMPackage.MATRIX_AXIS__CELLS:
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

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__NAME,
				PimMMFactory.eINSTANCE.createVariableDeclaration()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__CELLS,
				PimMMFactory.eINSTANCE.create(PimMMPackage.Literals.ASSIGNMENT)));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__CELLS, PimMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__CELLS,
				PimMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__CELLS,
				PimMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__CELLS,
				PimMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__CELLS,
				PimMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__CELLS,
				PimMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__CELLS,
				PimMMFactory.eINSTANCE.createEqualityOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__CELLS,
				PimMMFactory.eINSTANCE.createComparisonOp()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__CELLS, PimMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__CELLS, PimMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors
				.add(createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__CELLS, PimMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__CELLS, PimMMFactory.eINSTANCE.createUnaryOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.MATRIX_AXIS__CELLS,
				PimMMFactory.eINSTANCE.createNegation()));
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
