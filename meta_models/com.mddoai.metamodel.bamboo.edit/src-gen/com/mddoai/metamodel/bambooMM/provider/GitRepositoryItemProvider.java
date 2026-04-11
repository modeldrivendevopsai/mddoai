/**
 */
package com.mddoai.metamodel.bambooMM.provider;

import com.mddoai.metamodel.bambooMM.BambooFactory;
import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.GitRepository;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.bambooMM.GitRepository} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GitRepositoryItemProvider extends RepositoryItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GitRepositoryItemProvider(AdapterFactory adapterFactory) {
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

			addUrlPropertyDescriptor(object);
			addBranchPropertyDescriptor(object);
			addUsernamePropertyDescriptor(object);
			addPasswordPropertyDescriptor(object);
			addSshKeyPropertyDescriptor(object);
			addSshKeyPassphrasePropertyDescriptor(object);
			addSharedCredentialsPropertyDescriptor(object);
			addSharedCredentialsScopePropertyDescriptor(object);
			addLfsPropertyDescriptor(object);
			addUseShallowClonesPropertyDescriptor(object);
			addSubmodulesPropertyDescriptor(object);
			addFetchAllPropertyDescriptor(object);
			addCacheOnAgentsPropertyDescriptor(object);
			addVerboseLogsPropertyDescriptor(object);
			addCommandTimeoutMinutesPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Url feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUrlPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_GitRepository_url_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_url_feature",
								"_UI_GitRepository_type"),
						BambooPackage.Literals.GIT_REPOSITORY__URL, true, false, false,
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
						getResourceLocator(), getString("_UI_GitRepository_branch_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_branch_feature",
								"_UI_GitRepository_type"),
						BambooPackage.Literals.GIT_REPOSITORY__BRANCH, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Username feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUsernamePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_GitRepository_username_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_username_feature",
								"_UI_GitRepository_type"),
						BambooPackage.Literals.GIT_REPOSITORY__USERNAME, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Password feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPasswordPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_GitRepository_password_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_password_feature",
								"_UI_GitRepository_type"),
						BambooPackage.Literals.GIT_REPOSITORY__PASSWORD, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Ssh Key feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSshKeyPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_GitRepository_sshKey_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_sshKey_feature",
								"_UI_GitRepository_type"),
						BambooPackage.Literals.GIT_REPOSITORY__SSH_KEY, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Ssh Key Passphrase feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSshKeyPassphrasePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_GitRepository_sshKeyPassphrase_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_sshKeyPassphrase_feature",
								"_UI_GitRepository_type"),
						BambooPackage.Literals.GIT_REPOSITORY__SSH_KEY_PASSPHRASE, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Shared Credentials feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSharedCredentialsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_GitRepository_sharedCredentials_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_sharedCredentials_feature",
								"_UI_GitRepository_type"),
						BambooPackage.Literals.GIT_REPOSITORY__SHARED_CREDENTIALS, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Shared Credentials Scope feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSharedCredentialsScopePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_GitRepository_sharedCredentialsScope_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_sharedCredentialsScope_feature",
						"_UI_GitRepository_type"),
				BambooPackage.Literals.GIT_REPOSITORY__SHARED_CREDENTIALS_SCOPE, true, false, false,
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
						getResourceLocator(), getString("_UI_GitRepository_lfs_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_lfs_feature",
								"_UI_GitRepository_type"),
						BambooPackage.Literals.GIT_REPOSITORY__LFS, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Use Shallow Clones feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUseShallowClonesPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_GitRepository_useShallowClones_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_useShallowClones_feature",
								"_UI_GitRepository_type"),
						BambooPackage.Literals.GIT_REPOSITORY__USE_SHALLOW_CLONES, true, false, false,
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
						getResourceLocator(), getString("_UI_GitRepository_submodules_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_submodules_feature",
								"_UI_GitRepository_type"),
						BambooPackage.Literals.GIT_REPOSITORY__SUBMODULES, true, false, false,
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
						getResourceLocator(), getString("_UI_GitRepository_fetchAll_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_fetchAll_feature",
								"_UI_GitRepository_type"),
						BambooPackage.Literals.GIT_REPOSITORY__FETCH_ALL, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Cache On Agents feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCacheOnAgentsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_GitRepository_cacheOnAgents_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_cacheOnAgents_feature",
								"_UI_GitRepository_type"),
						BambooPackage.Literals.GIT_REPOSITORY__CACHE_ON_AGENTS, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Verbose Logs feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addVerboseLogsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_GitRepository_verboseLogs_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_verboseLogs_feature",
								"_UI_GitRepository_type"),
						BambooPackage.Literals.GIT_REPOSITORY__VERBOSE_LOGS, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Command Timeout Minutes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCommandTimeoutMinutesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_GitRepository_commandTimeoutMinutes_feature"),
				getString("_UI_PropertyDescriptor_description", "_UI_GitRepository_commandTimeoutMinutes_feature",
						"_UI_GitRepository_type"),
				BambooPackage.Literals.GIT_REPOSITORY__COMMAND_TIMEOUT_MINUTES, true, false, false,
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
			childrenFeatures.add(BambooPackage.Literals.GIT_REPOSITORY__CHANGE_DETECTION);
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
	 * This returns GitRepository.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/GitRepository"));
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
		String label = ((GitRepository) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_GitRepository_type")
				: getString("_UI_GitRepository_type") + " " + label;
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

		switch (notification.getFeatureID(GitRepository.class)) {
		case BambooPackage.GIT_REPOSITORY__URL:
		case BambooPackage.GIT_REPOSITORY__BRANCH:
		case BambooPackage.GIT_REPOSITORY__USERNAME:
		case BambooPackage.GIT_REPOSITORY__PASSWORD:
		case BambooPackage.GIT_REPOSITORY__SSH_KEY:
		case BambooPackage.GIT_REPOSITORY__SSH_KEY_PASSPHRASE:
		case BambooPackage.GIT_REPOSITORY__SHARED_CREDENTIALS:
		case BambooPackage.GIT_REPOSITORY__SHARED_CREDENTIALS_SCOPE:
		case BambooPackage.GIT_REPOSITORY__LFS:
		case BambooPackage.GIT_REPOSITORY__USE_SHALLOW_CLONES:
		case BambooPackage.GIT_REPOSITORY__SUBMODULES:
		case BambooPackage.GIT_REPOSITORY__FETCH_ALL:
		case BambooPackage.GIT_REPOSITORY__CACHE_ON_AGENTS:
		case BambooPackage.GIT_REPOSITORY__VERBOSE_LOGS:
		case BambooPackage.GIT_REPOSITORY__COMMAND_TIMEOUT_MINUTES:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case BambooPackage.GIT_REPOSITORY__CHANGE_DETECTION:
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

		newChildDescriptors.add(createChildParameter(BambooPackage.Literals.GIT_REPOSITORY__CHANGE_DETECTION,
				BambooFactory.eINSTANCE.createChangeDetection()));
	}

}
