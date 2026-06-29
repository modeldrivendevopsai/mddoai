/**
 */
package com.mddoai.metamodel.github.githubMM.provider;

import com.mddoai.metamodel.github.githubMM.GithubMMFactory;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.Join;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.mddoai.metamodel.github.githubMM.Join} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class JoinItemProvider extends BuiltInFunctionCallItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JoinItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(GithubMMPackage.Literals.JOIN__ARRAY);
			childrenFeatures.add(GithubMMPackage.Literals.JOIN__SEP);
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
	 * This returns Join.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Join"));
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
		return getString("_UI_Join_type");
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

		switch (notification.getFeatureID(Join.class)) {
		case GithubMMPackage.JOIN__ARRAY:
		case GithubMMPackage.JOIN__SEP:
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

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY, GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__ARRAY,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__SEP,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOIN__SEP, GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__SEP,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__SEP,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__SEP,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__SEP,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__SEP,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOIN__SEP,
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

		boolean qualify = childFeature == GithubMMPackage.Literals.JOIN__ARRAY
				|| childFeature == GithubMMPackage.Literals.JOIN__SEP;

		if (qualify) {
			return getString("_UI_CreateChild_text2",
					new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
