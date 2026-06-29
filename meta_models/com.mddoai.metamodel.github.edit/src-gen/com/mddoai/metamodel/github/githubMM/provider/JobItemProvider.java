/**
 */
package com.mddoai.metamodel.github.githubMM.provider;

import com.mddoai.metamodel.github.githubMM.GithubMMFactory;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.Job;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.github.githubMM.Job} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class JobItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JobItemProvider(AdapterFactory adapterFactory) {
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
			addDependsOnPropertyDescriptor(object);
			addNecessaryForPropertyDescriptor(object);
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
						getResourceLocator(), getString("_UI_Job_name_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_name_feature", "_UI_Job_type"),
						GithubMMPackage.Literals.JOB__NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Depends On feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDependsOnPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_dependsOn_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_dependsOn_feature", "_UI_Job_type"),
						GithubMMPackage.Literals.JOB__DEPENDS_ON, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Necessary For feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNecessaryForPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_necessaryFor_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_necessaryFor_feature", "_UI_Job_type"),
						GithubMMPackage.Literals.JOB__NECESSARY_FOR, true, false, true, null, null, null));
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
			childrenFeatures.add(GithubMMPackage.Literals.JOB__JOB_NAME);
			childrenFeatures.add(GithubMMPackage.Literals.JOB__PERMISSIONS);
			childrenFeatures.add(GithubMMPackage.Literals.JOB__IF_CONDITION);
			childrenFeatures.add(GithubMMPackage.Literals.JOB__AGENT);
			childrenFeatures.add(GithubMMPackage.Literals.JOB__CONTAINER);
			childrenFeatures.add(GithubMMPackage.Literals.JOB__STAGING_ENVIRONMENT);
			childrenFeatures.add(GithubMMPackage.Literals.JOB__DEFAULTS);
			childrenFeatures.add(GithubMMPackage.Literals.JOB__ENVIRONMENT_VARIABLES);
			childrenFeatures.add(GithubMMPackage.Literals.JOB__SERVICES);
			childrenFeatures.add(GithubMMPackage.Literals.JOB__CONCURRENCY_GROUP);
			childrenFeatures.add(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES);
			childrenFeatures.add(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR);
			childrenFeatures.add(GithubMMPackage.Literals.JOB__STRATEGY);
			childrenFeatures.add(GithubMMPackage.Literals.JOB__OUTPUTS);
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
		String label = ((Job) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_Job_type")
				: getString("_UI_Job_type") + " " + label;
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

		switch (notification.getFeatureID(Job.class)) {
		case GithubMMPackage.JOB__NAME:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case GithubMMPackage.JOB__JOB_NAME:
		case GithubMMPackage.JOB__PERMISSIONS:
		case GithubMMPackage.JOB__IF_CONDITION:
		case GithubMMPackage.JOB__AGENT:
		case GithubMMPackage.JOB__CONTAINER:
		case GithubMMPackage.JOB__STAGING_ENVIRONMENT:
		case GithubMMPackage.JOB__DEFAULTS:
		case GithubMMPackage.JOB__ENVIRONMENT_VARIABLES:
		case GithubMMPackage.JOB__SERVICES:
		case GithubMMPackage.JOB__CONCURRENCY_GROUP:
		case GithubMMPackage.JOB__TIMEOUT_MINUTES:
		case GithubMMPackage.JOB__CONTINUE_ON_ERROR:
		case GithubMMPackage.JOB__STRATEGY:
		case GithubMMPackage.JOB__OUTPUTS:
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

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME, GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME, GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME, GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME, GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME, GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME, GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME, GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME, GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME, GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__JOB_NAME,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__PERMISSIONS,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.PERMISSION)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION, GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__IF_CONDITION,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOB__AGENT, GithubMMFactory.eINSTANCE.createAgent()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTAINER,
				GithubMMFactory.eINSTANCE.createContainer()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__STAGING_ENVIRONMENT,
				GithubMMFactory.eINSTANCE.createStagingEnvironment()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__DEFAULTS,
				GithubMMFactory.eINSTANCE.createDefaults()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__ENVIRONMENT_VARIABLES,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__SERVICES,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.SERVICE)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONCURRENCY_GROUP,
				GithubMMFactory.eINSTANCE.createConcurrencyGroup()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOB__STRATEGY, GithubMMFactory.eINSTANCE.createMatrix()));

		newChildDescriptors.add(
				createChildParameter(GithubMMPackage.Literals.JOB__OUTPUTS, GithubMMFactory.eINSTANCE.createOutput()));
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

		boolean qualify = childFeature == GithubMMPackage.Literals.JOB__JOB_NAME
				|| childFeature == GithubMMPackage.Literals.JOB__IF_CONDITION
				|| childFeature == GithubMMPackage.Literals.JOB__ENVIRONMENT_VARIABLES
				|| childFeature == GithubMMPackage.Literals.JOB__TIMEOUT_MINUTES
				|| childFeature == GithubMMPackage.Literals.JOB__CONTINUE_ON_ERROR;

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
