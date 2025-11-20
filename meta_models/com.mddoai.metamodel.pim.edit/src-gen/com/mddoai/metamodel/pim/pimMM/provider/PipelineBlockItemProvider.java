/**
 */
package com.mddoai.metamodel.pim.pimMM.provider;

import com.mddoai.metamodel.pim.pimMM.PimMMFactory;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;
import com.mddoai.metamodel.pim.pimMM.PipelineBlock;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.pim.pimMM.PipelineBlock} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PipelineBlockItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PipelineBlockItemProvider(AdapterFactory adapterFactory) {
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
			addTimeoutMinutesPropertyDescriptor(object);
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
						getResourceLocator(), getString("_UI_PipelineBlock_name_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_PipelineBlock_name_feature",
								"_UI_PipelineBlock_type"),
						PimMMPackage.Literals.PIPELINE_BLOCK__NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Timeout Minutes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTimeoutMinutesPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_PipelineBlock_timeoutMinutes_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_PipelineBlock_timeoutMinutes_feature",
								"_UI_PipelineBlock_type"),
						PimMMPackage.Literals.PIPELINE_BLOCK__TIMEOUT_MINUTES, true, false, false,
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
			childrenFeatures.add(PimMMPackage.Literals.PIPELINE_BLOCK__AGENT);
			childrenFeatures.add(PimMMPackage.Literals.PIPELINE_BLOCK__INPUTS);
			childrenFeatures.add(PimMMPackage.Literals.PIPELINE_BLOCK__OUTPUTS);
			childrenFeatures.add(PimMMPackage.Literals.PIPELINE_BLOCK__ENVIRONMENT_VARIABLES);
			childrenFeatures.add(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY);
			childrenFeatures.add(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL);
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
		String label = ((PipelineBlock) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_PipelineBlock_type")
				: getString("_UI_PipelineBlock_type") + " " + label;
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

		switch (notification.getFeatureID(PipelineBlock.class)) {
		case PimMMPackage.PIPELINE_BLOCK__NAME:
		case PimMMPackage.PIPELINE_BLOCK__TIMEOUT_MINUTES:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case PimMMPackage.PIPELINE_BLOCK__AGENT:
		case PimMMPackage.PIPELINE_BLOCK__INPUTS:
		case PimMMPackage.PIPELINE_BLOCK__OUTPUTS:
		case PimMMPackage.PIPELINE_BLOCK__ENVIRONMENT_VARIABLES:
		case PimMMPackage.PIPELINE_BLOCK__WORKING_DIRECTORY:
		case PimMMPackage.PIPELINE_BLOCK__SHELL:
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

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__AGENT,
				PimMMFactory.eINSTANCE.createCustomAgent()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__AGENT,
				PimMMFactory.eINSTANCE.createLinuxAgent()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__AGENT,
				PimMMFactory.eINSTANCE.createWindowsAgent()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__AGENT,
				PimMMFactory.eINSTANCE.createMacOSAgent()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__INPUTS,
				PimMMFactory.eINSTANCE.createInput()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__OUTPUTS,
				PimMMFactory.eINSTANCE.createOutput()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__ENVIRONMENT_VARIABLES,
				PimMMFactory.eINSTANCE.create(PimMMPackage.Literals.ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY,
				PimMMFactory.eINSTANCE.create(PimMMPackage.Literals.ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY,
				PimMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY,
				PimMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY,
				PimMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY,
				PimMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY,
				PimMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY,
				PimMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY,
				PimMMFactory.eINSTANCE.createEqualityOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY,
				PimMMFactory.eINSTANCE.createComparisonOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY,
				PimMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY,
				PimMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY,
				PimMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY,
				PimMMFactory.eINSTANCE.createUnaryOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY,
				PimMMFactory.eINSTANCE.createNegation()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL,
				PimMMFactory.eINSTANCE.create(PimMMPackage.Literals.ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL,
				PimMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL,
				PimMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL,
				PimMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL,
				PimMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL,
				PimMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL,
				PimMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL,
				PimMMFactory.eINSTANCE.createEqualityOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL,
				PimMMFactory.eINSTANCE.createComparisonOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL,
				PimMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL, PimMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(
				createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL, PimMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL,
				PimMMFactory.eINSTANCE.createUnaryOp()));

		newChildDescriptors.add(createChildParameter(PimMMPackage.Literals.PIPELINE_BLOCK__SHELL,
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

		boolean qualify = childFeature == PimMMPackage.Literals.PIPELINE_BLOCK__ENVIRONMENT_VARIABLES
				|| childFeature == PimMMPackage.Literals.PIPELINE_BLOCK__WORKING_DIRECTORY
				|| childFeature == PimMMPackage.Literals.PIPELINE_BLOCK__SHELL;

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
