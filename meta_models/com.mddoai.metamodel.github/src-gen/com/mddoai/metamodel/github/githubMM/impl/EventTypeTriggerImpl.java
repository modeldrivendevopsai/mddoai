/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.EventTypeTrigger;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.WEBHOOK_ACTIVITY_TYPES;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event Type Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.EventTypeTriggerImpl#getEventTypes <em>Event Types</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class EventTypeTriggerImpl extends TriggerImpl implements EventTypeTrigger {
	/**
	 * The cached value of the '{@link #getEventTypes() <em>Event Types</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<WEBHOOK_ACTIVITY_TYPES> eventTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventTypeTriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.EVENT_TYPE_TRIGGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<WEBHOOK_ACTIVITY_TYPES> getEventTypes() {
		if (eventTypes == null) {
			eventTypes = new EDataTypeUniqueEList<WEBHOOK_ACTIVITY_TYPES>(WEBHOOK_ACTIVITY_TYPES.class, this,
					GithubMMPackage.EVENT_TYPE_TRIGGER__EVENT_TYPES);
		}
		return eventTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GithubMMPackage.EVENT_TYPE_TRIGGER__EVENT_TYPES:
			return getEventTypes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GithubMMPackage.EVENT_TYPE_TRIGGER__EVENT_TYPES:
			getEventTypes().clear();
			getEventTypes().addAll((Collection<? extends WEBHOOK_ACTIVITY_TYPES>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case GithubMMPackage.EVENT_TYPE_TRIGGER__EVENT_TYPES:
			getEventTypes().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case GithubMMPackage.EVENT_TYPE_TRIGGER__EVENT_TYPES:
			return eventTypes != null && !eventTypes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (eventTypes: ");
		result.append(eventTypes);
		result.append(')');
		return result.toString();
	}

} //EventTypeTriggerImpl
