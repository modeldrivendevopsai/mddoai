/**
 */
package com.mddoai.metamodel.github.githubMM.provider;

import com.mddoai.metamodel.github.githubMM.GithubMMFactory;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.Step;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.github.githubMM.Step} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StepItemProvider extends AbstractStepItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StepItemProvider(AdapterFactory adapterFactory) {
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

			addIdPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Step_id_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Step_id_feature", "_UI_Step_type"),
						GithubMMPackage.Literals.STEP__ID, true, false, false,
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
			childrenFeatures.add(GithubMMPackage.Literals.STEP__NAME);
			childrenFeatures.add(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES);
			childrenFeatures.add(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR);
			childrenFeatures.add(GithubMMPackage.Literals.STEP__SHELL);
			childrenFeatures.add(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY);
			childrenFeatures.add(GithubMMPackage.Literals.STEP__ENVIRONMENT_VARIABLES);
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
		String label = ((Step) object).getId();
		return label == null || label.length() == 0 ? getString("_UI_Step_type")
				: getString("_UI_Step_type") + " " + label;
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

		switch (notification.getFeatureID(Step.class)) {
		case GithubMMPackage.STEP__ID:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case GithubMMPackage.STEP__NAME:
		case GithubMMPackage.STEP__TIMEOUT_MINUTES:
		case GithubMMPackage.STEP__CONTINUE_ON_ERROR:
		case GithubMMPackage.STEP__SHELL:
		case GithubMMPackage.STEP__WORKING_DIRECTORY:
		case GithubMMPackage.STEP__ENVIRONMENT_VARIABLES:
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

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__NAME,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__NAME,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__NAME,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__NAME, GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__NAME,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__NAME,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__NAME,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__NAME,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__NAME,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__NAME,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__SHELL,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__SHELL,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors
				.add(createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__SHELL,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__SHELL,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__SHELL,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.STEP__SHELL, GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__SHELL,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__SHELL,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__SHELL,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__SHELL,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__SHELL,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__SHELL,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__WORKING_DIRECTORY,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.STEP__ENVIRONMENT_VARIABLES,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));
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
				|| childFeature == GithubMMPackage.Literals.STEP__ENVIRONMENT_VARIABLES;

		if (qualify) {
			return getString("_UI_CreateChild_text2",
					new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
