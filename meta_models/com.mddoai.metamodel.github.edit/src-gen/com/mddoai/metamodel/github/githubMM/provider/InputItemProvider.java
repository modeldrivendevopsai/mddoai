/**
 */
package com.mddoai.metamodel.github.githubMM.provider;

import com.mddoai.metamodel.github.githubMM.GithubMMFactory;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.INPUT_TYPES;
import com.mddoai.metamodel.github.githubMM.Input;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.github.githubMM.Input} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class InputItemProvider extends ParameterItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputItemProvider(AdapterFactory adapterFactory) {
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

			addTypePropertyDescriptor(object);
			addIsRequiredPropertyDescriptor(object);
			addOptionsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
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
						getResourceLocator(), getString("_UI_Input_type_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Input_type_feature", "_UI_Input_type"),
						GithubMMPackage.Literals.INPUT__TYPE, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Is Required feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsRequiredPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Input_isRequired_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Input_isRequired_feature",
								"_UI_Input_type"),
						GithubMMPackage.Literals.INPUT__IS_REQUIRED, true, false, false,
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
						getResourceLocator(), getString("_UI_Input_options_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Input_options_feature", "_UI_Input_type"),
						GithubMMPackage.Literals.INPUT__OPTIONS, true, false, false,
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
			childrenFeatures.add(GithubMMPackage.Literals.INPUT__DEFAULT);
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
	 * This returns Input.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Input"));
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
		INPUT_TYPES labelValue = ((Input) object).getType();
		String label = labelValue == null ? null : labelValue.toString();
		return label == null || label.length() == 0 ? getString("_UI_Input_type")
				: getString("_UI_Input_type") + " " + label;
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

		switch (notification.getFeatureID(Input.class)) {
		case GithubMMPackage.INPUT__TYPE:
		case GithubMMPackage.INPUT__IS_REQUIRED:
		case GithubMMPackage.INPUT__OPTIONS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case GithubMMPackage.INPUT__DEFAULT:
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

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT, GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT, GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT, GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT, GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT, GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.INPUT__DEFAULT,
				GithubMMFactory.eINSTANCE.createGitHubContext()));
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

		boolean qualify = childFeature == GithubMMPackage.Literals.PARAMETER__DESCRIPTION
				|| childFeature == GithubMMPackage.Literals.INPUT__DEFAULT;

		if (qualify) {
			return getString("_UI_CreateChild_text2",
					new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
