/**
 */
package com.mddoai.metamodel.github.githubMM.provider;

import com.mddoai.metamodel.github.githubMM.Container;
import com.mddoai.metamodel.github.githubMM.GithubMMFactory;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.github.githubMM.Container} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ContainerItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContainerItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(GithubMMPackage.Literals.CONTAINER__IMAGE);
			childrenFeatures.add(GithubMMPackage.Literals.CONTAINER__USERNAME);
			childrenFeatures.add(GithubMMPackage.Literals.CONTAINER__PASSWORD);
			childrenFeatures.add(GithubMMPackage.Literals.CONTAINER__ENVIRONMENT_VARIABLES);
			childrenFeatures.add(GithubMMPackage.Literals.CONTAINER__PORTS);
			childrenFeatures.add(GithubMMPackage.Literals.CONTAINER__VOLUMES);
			childrenFeatures.add(GithubMMPackage.Literals.CONTAINER__OPTIONS);
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
	 * This returns Container.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Container"));
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
		return getString("_UI_Container_type");
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

		switch (notification.getFeatureID(Container.class)) {
		case GithubMMPackage.CONTAINER__IMAGE:
		case GithubMMPackage.CONTAINER__USERNAME:
		case GithubMMPackage.CONTAINER__PASSWORD:
		case GithubMMPackage.CONTAINER__ENVIRONMENT_VARIABLES:
		case GithubMMPackage.CONTAINER__PORTS:
		case GithubMMPackage.CONTAINER__VOLUMES:
		case GithubMMPackage.CONTAINER__OPTIONS:
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

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE, GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE, GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE, GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__IMAGE,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__USERNAME,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PASSWORD,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__ENVIRONMENT_VARIABLES,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS, GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS, GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS, GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__PORTS,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__VOLUMES,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.CONTAINER__OPTIONS,
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

		boolean qualify = childFeature == GithubMMPackage.Literals.CONTAINER__IMAGE
				|| childFeature == GithubMMPackage.Literals.CONTAINER__USERNAME
				|| childFeature == GithubMMPackage.Literals.CONTAINER__PASSWORD
				|| childFeature == GithubMMPackage.Literals.CONTAINER__ENVIRONMENT_VARIABLES
				|| childFeature == GithubMMPackage.Literals.CONTAINER__PORTS
				|| childFeature == GithubMMPackage.Literals.CONTAINER__VOLUMES
				|| childFeature == GithubMMPackage.Literals.CONTAINER__OPTIONS;

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
		return GithubMMEditPlugin.INSTANCE;
	}

}
