/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.provider;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.mddoai.metamodel.azuredevops.azuredevopsMM.CheckoutStep} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CheckoutStepItemProvider extends StepItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckoutStepItemProvider(AdapterFactory adapterFactory) {
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

			addCheckoutPropertyDescriptor(object);
			addCleanPropertyDescriptor(object);
			addFetchDepthPropertyDescriptor(object);
			addFetchFilterPropertyDescriptor(object);
			addFetchTagsPropertyDescriptor(object);
			addLfsPropertyDescriptor(object);
			addPersistCredentialsPropertyDescriptor(object);
			addSubmodulesPropertyDescriptor(object);
			addPathPropertyDescriptor(object);
			addSparseCheckoutDirectoriesPropertyDescriptor(object);
			addSparseCheckoutPatternsPropertyDescriptor(object);
			addWorkspaceRepoPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Checkout feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCheckoutPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CheckoutStep_checkout_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CheckoutStep_checkout_feature",
								"_UI_CheckoutStep_type"),
						AzuredevopsMMPackage.Literals.CHECKOUT_STEP__CHECKOUT, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Clean feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCleanPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CheckoutStep_clean_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CheckoutStep_clean_feature",
								"_UI_CheckoutStep_type"),
						AzuredevopsMMPackage.Literals.CHECKOUT_STEP__CLEAN, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Fetch Depth feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFetchDepthPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CheckoutStep_fetchDepth_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CheckoutStep_fetchDepth_feature",
								"_UI_CheckoutStep_type"),
						AzuredevopsMMPackage.Literals.CHECKOUT_STEP__FETCH_DEPTH, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Fetch Filter feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFetchFilterPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CheckoutStep_fetchFilter_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CheckoutStep_fetchFilter_feature",
								"_UI_CheckoutStep_type"),
						AzuredevopsMMPackage.Literals.CHECKOUT_STEP__FETCH_FILTER, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Fetch Tags feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFetchTagsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CheckoutStep_fetchTags_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CheckoutStep_fetchTags_feature",
								"_UI_CheckoutStep_type"),
						AzuredevopsMMPackage.Literals.CHECKOUT_STEP__FETCH_TAGS, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Lfs feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLfsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CheckoutStep_lfs_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CheckoutStep_lfs_feature",
								"_UI_CheckoutStep_type"),
						AzuredevopsMMPackage.Literals.CHECKOUT_STEP__LFS, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Persist Credentials feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPersistCredentialsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_CheckoutStep_persistCredentials_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_CheckoutStep_persistCredentials_feature",
						"_UI_CheckoutStep_type"),
				AzuredevopsMMPackage.Literals.CHECKOUT_STEP__PERSIST_CREDENTIALS, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Submodules feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSubmodulesPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CheckoutStep_submodules_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CheckoutStep_submodules_feature",
								"_UI_CheckoutStep_type"),
						AzuredevopsMMPackage.Literals.CHECKOUT_STEP__SUBMODULES, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Path feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPathPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CheckoutStep_path_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CheckoutStep_path_feature",
								"_UI_CheckoutStep_type"),
						AzuredevopsMMPackage.Literals.CHECKOUT_STEP__PATH, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Sparse Checkout Directories feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSparseCheckoutDirectoriesPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CheckoutStep_sparseCheckoutDirectories_feature"),
						getString("_UI_PropertyDescriptor_description",
								"_UI_CheckoutStep_sparseCheckoutDirectories_feature", "_UI_CheckoutStep_type"),
						AzuredevopsMMPackage.Literals.CHECKOUT_STEP__SPARSE_CHECKOUT_DIRECTORIES, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Sparse Checkout Patterns feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSparseCheckoutPatternsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_CheckoutStep_sparseCheckoutPatterns_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_CheckoutStep_sparseCheckoutPatterns_feature",
						"_UI_CheckoutStep_type"),
				AzuredevopsMMPackage.Literals.CHECKOUT_STEP__SPARSE_CHECKOUT_PATTERNS, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Workspace Repo feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addWorkspaceRepoPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_CheckoutStep_workspaceRepo_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_CheckoutStep_workspaceRepo_feature",
								"_UI_CheckoutStep_type"),
						AzuredevopsMMPackage.Literals.CHECKOUT_STEP__WORKSPACE_REPO, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This returns CheckoutStep.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CheckoutStep"));
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
		String label = ((CheckoutStep) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_CheckoutStep_type")
				: getString("_UI_CheckoutStep_type") + " " + label;
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

		switch (notification.getFeatureID(CheckoutStep.class)) {
		case AzuredevopsMMPackage.CHECKOUT_STEP__CHECKOUT:
		case AzuredevopsMMPackage.CHECKOUT_STEP__CLEAN:
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_DEPTH:
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_FILTER:
		case AzuredevopsMMPackage.CHECKOUT_STEP__FETCH_TAGS:
		case AzuredevopsMMPackage.CHECKOUT_STEP__LFS:
		case AzuredevopsMMPackage.CHECKOUT_STEP__PERSIST_CREDENTIALS:
		case AzuredevopsMMPackage.CHECKOUT_STEP__SUBMODULES:
		case AzuredevopsMMPackage.CHECKOUT_STEP__PATH:
		case AzuredevopsMMPackage.CHECKOUT_STEP__SPARSE_CHECKOUT_DIRECTORIES:
		case AzuredevopsMMPackage.CHECKOUT_STEP__SPARSE_CHECKOUT_PATTERNS:
		case AzuredevopsMMPackage.CHECKOUT_STEP__WORKSPACE_REPO:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
	}

}
