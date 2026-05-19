/**
 */
package com.mddoai.metamodel.github.githubMM.provider;

import com.mddoai.metamodel.github.githubMM.GithubMMFactory;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.PushTrigger;

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
 * This is the item provider adapter for a {@link com.mddoai.metamodel.github.githubMM.PushTrigger} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PushTriggerItemProvider extends SpecifiedBranchesTriggerItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PushTriggerItemProvider(AdapterFactory adapterFactory) {
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

			addIgnoreSpecifiedPathsPropertyDescriptor(object);
			addIgnoreSpecifiedTagsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Ignore Specified Paths feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIgnoreSpecifiedPathsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_SpecifiedPathsTrigger_ignoreSpecifiedPaths_feature"),
				getString("_UI_PropertyDescriptor_description",
						"_UI_SpecifiedPathsTrigger_ignoreSpecifiedPaths_feature", "_UI_SpecifiedPathsTrigger_type"),
				GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__IGNORE_SPECIFIED_PATHS, true, false, false,
				ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Ignore Specified Tags feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIgnoreSpecifiedTagsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_PushTrigger_ignoreSpecifiedTags_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_PushTrigger_ignoreSpecifiedTags_feature",
								"_UI_PushTrigger_type"),
						GithubMMPackage.Literals.PUSH_TRIGGER__IGNORE_SPECIFIED_TAGS, true, false, false,
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
			childrenFeatures.add(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS);
			childrenFeatures.add(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS);
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
	 * This returns PushTrigger.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/PushTrigger"));
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
		PushTrigger pushTrigger = (PushTrigger) object;
		return getString("_UI_PushTrigger_type") + " " + pushTrigger.isIgnoreSpecifiedBranches();
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

		switch (notification.getFeatureID(PushTrigger.class)) {
		case GithubMMPackage.PUSH_TRIGGER__IGNORE_SPECIFIED_PATHS:
		case GithubMMPackage.PUSH_TRIGGER__IGNORE_SPECIFIED_TAGS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case GithubMMPackage.PUSH_TRIGGER__PATHS:
		case GithubMMPackage.PUSH_TRIGGER__TAGS:
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

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS,
				GithubMMFactory.eINSTANCE.createGitHubContext()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.create(GithubMMPackage.Literals.VARIABLE_ASSIGNMENT)));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createConcat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createDotOp()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createEquality()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createComparison()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createOr()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createAnd()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createNot()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createContains()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createStartsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createEndsWith()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createFormat()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createJoin()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createToJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createFromJSON()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createHashFiles()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createAlways()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createSuccess()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createCancelled()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createFailure()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createStringLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createIntegerLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createDoubleLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createBooleanLiteral()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
				GithubMMFactory.eINSTANCE.createVariableReference()));

		newChildDescriptors.add(createChildParameter(GithubMMPackage.Literals.PUSH_TRIGGER__TAGS,
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

		boolean qualify = childFeature == GithubMMPackage.Literals.SPECIFIED_BRANCHES_TRIGGER__BRANCHES
				|| childFeature == GithubMMPackage.Literals.SPECIFIED_PATHS_TRIGGER__PATHS
				|| childFeature == GithubMMPackage.Literals.PUSH_TRIGGER__TAGS;

		if (qualify) {
			return getString("_UI_CreateChild_text2",
					new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
