/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.provider;

import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMFactory;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.Job;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.gitlab.gitlabMM.Job} object.
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
			addStagePropertyDescriptor(object);
			addWhenPropertyDescriptor(object);
			addImagePropertyDescriptor(object);
			addAllowFailurePropertyDescriptor(object);
			addTimeoutPropertyDescriptor(object);
			addInterruptiblePropertyDescriptor(object);
			addResourceGroupPropertyDescriptor(object);
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
						GitlabMMPackage.Literals.JOB__NAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Stage feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStagePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_stage_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_stage_feature", "_UI_Job_type"),
						GitlabMMPackage.Literals.JOB__STAGE, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Image feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addImagePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_image_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_image_feature", "_UI_Job_type"),
						GitlabMMPackage.Literals.JOB__IMAGE, true, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Allow Failure feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAllowFailurePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_allowFailure_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_allowFailure_feature", "_UI_Job_type"),
						GitlabMMPackage.Literals.JOB__ALLOW_FAILURE, true, false, false,
						ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Timeout feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTimeoutPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_timeout_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_timeout_feature", "_UI_Job_type"),
						GitlabMMPackage.Literals.JOB__TIMEOUT, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Interruptible feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInterruptiblePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_interruptible_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_interruptible_feature",
								"_UI_Job_type"),
						GitlabMMPackage.Literals.JOB__INTERRUPTIBLE, true, false, false,
						ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Resource Group feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addResourceGroupPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_resourceGroup_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_resourceGroup_feature",
								"_UI_Job_type"),
						GitlabMMPackage.Literals.JOB__RESOURCE_GROUP, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the When feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addWhenPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Job_when_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Job_when_feature", "_UI_Job_type"),
						GitlabMMPackage.Literals.JOB__WHEN, true, false, false,
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
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__ARTIFACTS);
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__SCRIPT);
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__BEFORE_SCRIPT);
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__TAGS);
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__ONLY);
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__DEPENDENCIES);
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__AFTER_SCRIPT);
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__CACHE);
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__RULES);
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__NEEDS);
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__SERVICES);
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__ENVIRONMENT);
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__RETRY);
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__VARIABLES);
			childrenFeatures.add(GitlabMMPackage.Literals.JOB__PARALLEL);
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
	 * This returns Job.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Job"));
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
		case GitlabMMPackage.JOB__NAME:
		case GitlabMMPackage.JOB__STAGE:
		case GitlabMMPackage.JOB__WHEN:
		case GitlabMMPackage.JOB__IMAGE:
		case GitlabMMPackage.JOB__ALLOW_FAILURE:
		case GitlabMMPackage.JOB__TIMEOUT:
		case GitlabMMPackage.JOB__INTERRUPTIBLE:
		case GitlabMMPackage.JOB__RESOURCE_GROUP:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case GitlabMMPackage.JOB__ARTIFACTS:
		case GitlabMMPackage.JOB__SCRIPT:
		case GitlabMMPackage.JOB__BEFORE_SCRIPT:
		case GitlabMMPackage.JOB__TAGS:
		case GitlabMMPackage.JOB__ONLY:
		case GitlabMMPackage.JOB__DEPENDENCIES:
		case GitlabMMPackage.JOB__AFTER_SCRIPT:
		case GitlabMMPackage.JOB__CACHE:
		case GitlabMMPackage.JOB__RULES:
		case GitlabMMPackage.JOB__NEEDS:
		case GitlabMMPackage.JOB__SERVICES:
		case GitlabMMPackage.JOB__ENVIRONMENT:
		case GitlabMMPackage.JOB__RETRY:
		case GitlabMMPackage.JOB__VARIABLES:
		case GitlabMMPackage.JOB__PARALLEL:
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

		newChildDescriptors.add(createChildParameter(GitlabMMPackage.Literals.JOB__ARTIFACTS,
				GitlabMMFactory.eINSTANCE.createArtifact()));

		newChildDescriptors.add(
				createChildParameter(GitlabMMPackage.Literals.JOB__SCRIPT, GitlabMMFactory.eINSTANCE.createScript()));

		newChildDescriptors.add(createChildParameter(GitlabMMPackage.Literals.JOB__BEFORE_SCRIPT,
				GitlabMMFactory.eINSTANCE.createBeforeScript()));

		newChildDescriptors
				.add(createChildParameter(GitlabMMPackage.Literals.JOB__TAGS, GitlabMMFactory.eINSTANCE.createTags()));

		newChildDescriptors
				.add(createChildParameter(GitlabMMPackage.Literals.JOB__ONLY, GitlabMMFactory.eINSTANCE.createOnly()));

		newChildDescriptors.add(createChildParameter(GitlabMMPackage.Literals.JOB__DEPENDENCIES,
				GitlabMMFactory.eINSTANCE.createDependencies()));

		newChildDescriptors.add(createChildParameter(GitlabMMPackage.Literals.JOB__AFTER_SCRIPT,
				GitlabMMFactory.eINSTANCE.createAfterScript()));

		newChildDescriptors.add(
				createChildParameter(GitlabMMPackage.Literals.JOB__CACHE, GitlabMMFactory.eINSTANCE.createCache()));

		newChildDescriptors.add(
				createChildParameter(GitlabMMPackage.Literals.JOB__RULES, GitlabMMFactory.eINSTANCE.createRule_()));

		newChildDescriptors
				.add(createChildParameter(GitlabMMPackage.Literals.JOB__NEEDS, GitlabMMFactory.eINSTANCE.createNeed()));

		newChildDescriptors.add(createChildParameter(GitlabMMPackage.Literals.JOB__SERVICES,
				GitlabMMFactory.eINSTANCE.createService()));

		newChildDescriptors.add(createChildParameter(GitlabMMPackage.Literals.JOB__ENVIRONMENT,
				GitlabMMFactory.eINSTANCE.createEnvironment()));

		newChildDescriptors.add(
				createChildParameter(GitlabMMPackage.Literals.JOB__RETRY, GitlabMMFactory.eINSTANCE.createRetry()));

		newChildDescriptors.add(createChildParameter(GitlabMMPackage.Literals.JOB__VARIABLES,
				GitlabMMFactory.eINSTANCE.createVariables()));

		newChildDescriptors.add(createChildParameter(GitlabMMPackage.Literals.JOB__PARALLEL,
				GitlabMMFactory.eINSTANCE.createParallel()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return GitlabMMEditPlugin.INSTANCE;
	}

}
