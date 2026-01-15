/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.provider;

import com.mddoai.metamodel.gitlab.gitlabMM.Default;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMFactory;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.gitlab.gitlabMM.Default} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DefaultItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DefaultItemProvider(AdapterFactory adapterFactory) {
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

			addTimeoutPropertyDescriptor(object);
			addInterruptiblePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
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
						getResourceLocator(), getString("_UI_Default_timeout_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Default_timeout_feature",
								"_UI_Default_type"),
						GitlabMMPackage.Literals.DEFAULT__TIMEOUT, true, false, false,
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
						getResourceLocator(), getString("_UI_Default_interruptible_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Default_interruptible_feature",
								"_UI_Default_type"),
						GitlabMMPackage.Literals.DEFAULT__INTERRUPTIBLE, true, false, false,
						ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
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
			childrenFeatures.add(GitlabMMPackage.Literals.DEFAULT__IMAGE);
			childrenFeatures.add(GitlabMMPackage.Literals.DEFAULT__SERVICES);
			childrenFeatures.add(GitlabMMPackage.Literals.DEFAULT__TAGS);
			childrenFeatures.add(GitlabMMPackage.Literals.DEFAULT__CACHE);
			childrenFeatures.add(GitlabMMPackage.Literals.DEFAULT__BEFORE_SCRIPT);
			childrenFeatures.add(GitlabMMPackage.Literals.DEFAULT__AFTER_SCRIPT);
			childrenFeatures.add(GitlabMMPackage.Literals.DEFAULT__RETRY);
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
	 * This returns Default.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Default"));
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
		String label = ((Default) object).getTimeout();
		return label == null || label.length() == 0 ? getString("_UI_Default_type")
				: getString("_UI_Default_type") + " " + label;
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

		switch (notification.getFeatureID(Default.class)) {
		case GitlabMMPackage.DEFAULT__TIMEOUT:
		case GitlabMMPackage.DEFAULT__INTERRUPTIBLE:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case GitlabMMPackage.DEFAULT__IMAGE:
		case GitlabMMPackage.DEFAULT__SERVICES:
		case GitlabMMPackage.DEFAULT__TAGS:
		case GitlabMMPackage.DEFAULT__CACHE:
		case GitlabMMPackage.DEFAULT__BEFORE_SCRIPT:
		case GitlabMMPackage.DEFAULT__AFTER_SCRIPT:
		case GitlabMMPackage.DEFAULT__RETRY:
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

		newChildDescriptors.add(
				createChildParameter(GitlabMMPackage.Literals.DEFAULT__IMAGE, GitlabMMFactory.eINSTANCE.createImage()));

		newChildDescriptors.add(createChildParameter(GitlabMMPackage.Literals.DEFAULT__SERVICES,
				GitlabMMFactory.eINSTANCE.createService()));

		newChildDescriptors.add(
				createChildParameter(GitlabMMPackage.Literals.DEFAULT__TAGS, GitlabMMFactory.eINSTANCE.createTags()));

		newChildDescriptors.add(
				createChildParameter(GitlabMMPackage.Literals.DEFAULT__CACHE, GitlabMMFactory.eINSTANCE.createCache()));

		newChildDescriptors.add(createChildParameter(GitlabMMPackage.Literals.DEFAULT__BEFORE_SCRIPT,
				GitlabMMFactory.eINSTANCE.createBeforeScript()));

		newChildDescriptors.add(createChildParameter(GitlabMMPackage.Literals.DEFAULT__AFTER_SCRIPT,
				GitlabMMFactory.eINSTANCE.createAfterScript()));

		newChildDescriptors.add(
				createChildParameter(GitlabMMPackage.Literals.DEFAULT__RETRY, GitlabMMFactory.eINSTANCE.createRetry()));
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
