/**
 */
package com.mddoai.metamodel.bambooMM.provider;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.BitbucketServerRepository;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link com.mddoai.metamodel.bambooMM.BitbucketServerRepository} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class BitbucketServerRepositoryItemProvider extends RepositoryItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BitbucketServerRepositoryItemProvider(AdapterFactory adapterFactory) {
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

			addServerPropertyDescriptor(object);
			addProjectPropertyDescriptor(object);
			addSlugPropertyDescriptor(object);
			addCloneUrlPropertyDescriptor(object);
			addPublicKeyPropertyDescriptor(object);
			addPrivateKeyPropertyDescriptor(object);
			addBranchPropertyDescriptor(object);
			addLfsPropertyDescriptor(object);
			addUseShallowClonesPropertyDescriptor(object);
			addSubmodulesPropertyDescriptor(object);
			addCommandTimeoutMinutesPropertyDescriptor(object);
			addFetchAllPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Server feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addServerPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_BitbucketServerRepository_server_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_BitbucketServerRepository_server_feature",
								"_UI_BitbucketServerRepository_type"),
						BambooPackage.Literals.BITBUCKET_SERVER_REPOSITORY__SERVER, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Project feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addProjectPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_BitbucketServerRepository_project_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_BitbucketServerRepository_project_feature",
								"_UI_BitbucketServerRepository_type"),
						BambooPackage.Literals.BITBUCKET_SERVER_REPOSITORY__PROJECT, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Slug feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSlugPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_BitbucketServerRepository_slug_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_BitbucketServerRepository_slug_feature",
						"_UI_BitbucketServerRepository_type"),
				BambooPackage.Literals.BITBUCKET_SERVER_REPOSITORY__SLUG, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Clone Url feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCloneUrlPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_BitbucketServerRepository_cloneUrl_feature"),
						getString("_UI_PropertyDescriptor_description",
								"_UI_BitbucketServerRepository_cloneUrl_feature", "_UI_BitbucketServerRepository_type"),
						BambooPackage.Literals.BITBUCKET_SERVER_REPOSITORY__CLONE_URL, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Public Key feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPublicKeyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_BitbucketServerRepository_publicKey_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_BitbucketServerRepository_publicKey_feature",
						"_UI_BitbucketServerRepository_type"),
				BambooPackage.Literals.BITBUCKET_SERVER_REPOSITORY__PUBLIC_KEY, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Private Key feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPrivateKeyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_BitbucketServerRepository_privateKey_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_BitbucketServerRepository_privateKey_feature",
						"_UI_BitbucketServerRepository_type"),
				BambooPackage.Literals.BITBUCKET_SERVER_REPOSITORY__PRIVATE_KEY, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Branch feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addBranchPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_BitbucketServerRepository_branch_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_BitbucketServerRepository_branch_feature",
								"_UI_BitbucketServerRepository_type"),
						BambooPackage.Literals.BITBUCKET_SERVER_REPOSITORY__BRANCH, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Lfs feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLfsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_BitbucketServerRepository_lfs_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_BitbucketServerRepository_lfs_feature",
						"_UI_BitbucketServerRepository_type"),
				BambooPackage.Literals.BITBUCKET_SERVER_REPOSITORY__LFS, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Use Shallow Clones feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUseShallowClonesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_BitbucketServerRepository_useShallowClones_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_BitbucketServerRepository_useShallowClones_feature", "_UI_BitbucketServerRepository_type"),
				BambooPackage.Literals.BITBUCKET_SERVER_REPOSITORY__USE_SHALLOW_CLONES, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Submodules feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSubmodulesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_BitbucketServerRepository_submodules_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_BitbucketServerRepository_submodules_feature",
						"_UI_BitbucketServerRepository_type"),
				BambooPackage.Literals.BITBUCKET_SERVER_REPOSITORY__SUBMODULES, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Command Timeout Minutes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCommandTimeoutMinutesPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_BitbucketServerRepository_commandTimeoutMinutes_feature"),
						getString("_UI_PropertyDescriptor_description",
								"_UI_BitbucketServerRepository_commandTimeoutMinutes_feature",
								"_UI_BitbucketServerRepository_type"),
						BambooPackage.Literals.BITBUCKET_SERVER_REPOSITORY__COMMAND_TIMEOUT_MINUTES, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Fetch All feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFetchAllPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_BitbucketServerRepository_fetchAll_feature"),
						getString("_UI_PropertyDescriptor_description",
								"_UI_BitbucketServerRepository_fetchAll_feature", "_UI_BitbucketServerRepository_type"),
						BambooPackage.Literals.BITBUCKET_SERVER_REPOSITORY__FETCH_ALL, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This returns BitbucketServerRepository.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/BitbucketServerRepository"));
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
		String label = ((BitbucketServerRepository) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_BitbucketServerRepository_type")
				: getString("_UI_BitbucketServerRepository_type") + " " + label;
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

		switch (notification.getFeatureID(BitbucketServerRepository.class)) {
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SERVER:
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PROJECT:
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SLUG:
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__CLONE_URL:
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PUBLIC_KEY:
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__PRIVATE_KEY:
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__BRANCH:
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__LFS:
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__USE_SHALLOW_CLONES:
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__SUBMODULES:
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__COMMAND_TIMEOUT_MINUTES:
		case BambooPackage.BITBUCKET_SERVER_REPOSITORY__FETCH_ALL:
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
