/**
 */
package com.mddoai.metamodel.pim.pimMM.provider;

import com.mddoai.metamodel.pim.pimMM.PimMMFactory;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import com.mddoai.metamodel.pim.pimMM.PipelineCallJob;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.mddoai.metamodel.pim.pimMM.PipelineCallJob} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PipelineCallJobItemProvider extends JobItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PipelineCallJobItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH);
			childrenFeatures.add(PimMMPackage.Literals.PIPELINE_CALL_JOB__ARGS);
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
	 * This returns PipelineCallJob.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/PipelineCallJob"));
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
		String label = ((PipelineCallJob) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_PipelineCallJob_type")
				: getString("_UI_PipelineCallJob_type") + " " + label;
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

		switch (notification.getFeatureID(PipelineCallJob.class)) {
		case PimMMPackage.PIPELINE_CALL_JOB__PIPELINE_PATH:
		case PimMMPackage.PIPELINE_CALL_JOB__ARGS:
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

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH,
				PimMMFactory.eINSTANCE.create(PimMMPackage.Literals.ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH,
				PimMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH,
				PimMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH,
				PimMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH,
				PimMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH,
				PimMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH,
				PimMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH,
				PimMMFactory.eINSTANCE.createEqualityOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH,
				PimMMFactory.eINSTANCE.createComparisonOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH,
				PimMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH,
				PimMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH,
				PimMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH,
				PimMMFactory.eINSTANCE.createUnaryOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH,
				PimMMFactory.eINSTANCE.createNegation()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_CALL_JOB__ARGS,
				PimMMFactory.eINSTANCE.create(PimMMPackage.Literals.ASSIGNMENT)));
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

		boolean qualify = childFeature == PimMMPackage.Literals.PIPELINE_BLOCK__ENVIRONMENT_VARIABLES
				|| childFeature == PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY
				|| childFeature == PimMMPackage.Literals.PIPELINE_BLOCK__SHELL
				|| childFeature == PimMMPackage.Literals.JOB__IF_CONDITION
				|| childFeature == PimMMPackage.Literals.PIPELINE_CALL_JOB__PIPELINE_PATH
				|| childFeature == PimMMPackage.Literals.PIPELINE_CALL_JOB__ARGS;

		if (qualify) {
			return getString("_UI_CreateChild_text2",
					new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
