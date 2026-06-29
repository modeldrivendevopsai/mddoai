/**
 */
package com.mddoai.metamodel.github.githubMM.provider;

import com.mddoai.metamodel.github.githubMM.GithubMMFactory;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.mddoai.metamodel.github.githubMM.Package} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PackageItemProvider extends StepItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(GithubMMPackage.Literals.PACKAGE__USES);
			childrenFeatures.add(GithubMMPackage.Literals.PACKAGE__ARGS);
			childrenFeatures.add(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT);
			childrenFeatures.add(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS);
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
	 * This returns Package.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Package"));
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
		String label = ((com.mddoai.metamodel.github.githubMM.Package) object).getId();
		return label == null || label.length() == 0 ? getString("_UI_Package_type")
				: getString("_UI_Package_type") + " " + label;
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

		switch (notification.getFeatureID(com.mddoai.metamodel.github.githubMM.Package.class)) {
		case GithubMMPackage.PACKAGE__USES:
		case GithubMMPackage.PACKAGE__ARGS:
		case GithubMMPackage.PACKAGE__ENTRYPOINT:
		case GithubMMPackage.PACKAGE__CONTAINER_ARGS:
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

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.PACKAGE__USES, GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.PACKAGE__USES, GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.PACKAGE__USES, GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.PACKAGE__USES, GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.PACKAGE__USES, GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.PACKAGE__USES, GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.PACKAGE__USES, GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.PACKAGE__USES, GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.PACKAGE__USES, GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__USES,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ARGS,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__ENTRYPOINT,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS,
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

		boolean qualify = childFeature == GithubMMPackage.Literals.STEP__NAME
				|| childFeature == GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES
				|| childFeature == GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR
				|| childFeature == GithubMMPackage.Literals.STEP__SHELL
				|| childFeature == GithubMMPackage.Literals.STEP__WORKING_DIRECTORY
				|| childFeature == GithubMMPackage.Literals.STEP__ENVIRONMENT_VARIABLES
				|| childFeature == GithubMMPackage.Literals.PACKAGE__USES
				|| childFeature == GithubMMPackage.Literals.PACKAGE__ARGS
				|| childFeature == GithubMMPackage.Literals.PACKAGE__ENTRYPOINT
				|| childFeature == GithubMMPackage.Literals.PACKAGE__CONTAINER_ARGS;

		if (qualify) {
			return getString("_UI_CreateChild_text2",
					new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
