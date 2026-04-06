/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.Rule;
import com.mddoai.metamodel.gitlab.gitlabMM.Variable;
import com.mddoai.metamodel.gitlab.gitlabMM.WhenType;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.RuleImpl#getIfCondition <em>If Condition</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.RuleImpl#getWhen <em>When</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.RuleImpl#getChanges <em>Changes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.RuleImpl#getExists <em>Exists</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.RuleImpl#getAllowFailure <em>Allow Failure</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.RuleImpl#getVariables <em>Variables</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RuleImpl extends MinimalEObjectImpl.Container implements Rule {
	/**
	 * The default value of the '{@link #getIfCondition() <em>If Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIfCondition()
	 * @generated
	 * @ordered
	 */
	protected static final String IF_CONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIfCondition() <em>If Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIfCondition()
	 * @generated
	 * @ordered
	 */
	protected String ifCondition = IF_CONDITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getWhen() <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhen()
	 * @generated
	 * @ordered
	 */
	protected static final WhenType WHEN_EDEFAULT = WhenType.ON_SUCCESS;

	/**
	 * The cached value of the '{@link #getWhen() <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhen()
	 * @generated
	 * @ordered
	 */
	protected WhenType when = WHEN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getChanges() <em>Changes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChanges()
	 * @generated
	 * @ordered
	 */
	protected EList<String> changes;

	/**
	 * The cached value of the '{@link #getExists() <em>Exists</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExists()
	 * @generated
	 * @ordered
	 */
	protected EList<String> exists;

	/**
	 * The default value of the '{@link #getAllowFailure() <em>Allow Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllowFailure()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ALLOW_FAILURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAllowFailure() <em>Allow Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllowFailure()
	 * @generated
	 * @ordered
	 */
	protected Boolean allowFailure = ALLOW_FAILURE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> variables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GitlabMMPackage.Literals.RULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIfCondition() {
		return ifCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIfCondition(String newIfCondition) {
		String oldIfCondition = ifCondition;
		ifCondition = newIfCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.RULE__IF_CONDITION, oldIfCondition,
					ifCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WhenType getWhen() {
		return when;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWhen(WhenType newWhen) {
		WhenType oldWhen = when;
		when = newWhen == null ? WHEN_EDEFAULT : newWhen;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.RULE__WHEN, oldWhen, when));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getChanges() {
		if (changes == null) {
			changes = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.RULE__CHANGES);
		}
		return changes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getExists() {
		if (exists == null) {
			exists = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.RULE__EXISTS);
		}
		return exists;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getAllowFailure() {
		return allowFailure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAllowFailure(Boolean newAllowFailure) {
		Boolean oldAllowFailure = allowFailure;
		allowFailure = newAllowFailure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.RULE__ALLOW_FAILURE, oldAllowFailure,
					allowFailure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Variable> getVariables() {
		if (variables == null) {
			variables = new EObjectContainmentEList<Variable>(Variable.class, this, GitlabMMPackage.RULE__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GitlabMMPackage.RULE__VARIABLES:
			return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GitlabMMPackage.RULE__IF_CONDITION:
			return getIfCondition();
		case GitlabMMPackage.RULE__WHEN:
			return getWhen();
		case GitlabMMPackage.RULE__CHANGES:
			return getChanges();
		case GitlabMMPackage.RULE__EXISTS:
			return getExists();
		case GitlabMMPackage.RULE__ALLOW_FAILURE:
			return getAllowFailure();
		case GitlabMMPackage.RULE__VARIABLES:
			return getVariables();
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
		case GitlabMMPackage.RULE__IF_CONDITION:
			setIfCondition((String) newValue);
			return;
		case GitlabMMPackage.RULE__WHEN:
			setWhen((WhenType) newValue);
			return;
		case GitlabMMPackage.RULE__CHANGES:
			getChanges().clear();
			getChanges().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.RULE__EXISTS:
			getExists().clear();
			getExists().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.RULE__ALLOW_FAILURE:
			setAllowFailure((Boolean) newValue);
			return;
		case GitlabMMPackage.RULE__VARIABLES:
			getVariables().clear();
			getVariables().addAll((Collection<? extends Variable>) newValue);
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
		case GitlabMMPackage.RULE__IF_CONDITION:
			setIfCondition(IF_CONDITION_EDEFAULT);
			return;
		case GitlabMMPackage.RULE__WHEN:
			setWhen(WHEN_EDEFAULT);
			return;
		case GitlabMMPackage.RULE__CHANGES:
			getChanges().clear();
			return;
		case GitlabMMPackage.RULE__EXISTS:
			getExists().clear();
			return;
		case GitlabMMPackage.RULE__ALLOW_FAILURE:
			setAllowFailure(ALLOW_FAILURE_EDEFAULT);
			return;
		case GitlabMMPackage.RULE__VARIABLES:
			getVariables().clear();
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
		case GitlabMMPackage.RULE__IF_CONDITION:
			return IF_CONDITION_EDEFAULT == null ? ifCondition != null : !IF_CONDITION_EDEFAULT.equals(ifCondition);
		case GitlabMMPackage.RULE__WHEN:
			return when != WHEN_EDEFAULT;
		case GitlabMMPackage.RULE__CHANGES:
			return changes != null && !changes.isEmpty();
		case GitlabMMPackage.RULE__EXISTS:
			return exists != null && !exists.isEmpty();
		case GitlabMMPackage.RULE__ALLOW_FAILURE:
			return ALLOW_FAILURE_EDEFAULT == null ? allowFailure != null : !ALLOW_FAILURE_EDEFAULT.equals(allowFailure);
		case GitlabMMPackage.RULE__VARIABLES:
			return variables != null && !variables.isEmpty();
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
		result.append(" (ifCondition: ");
		result.append(ifCondition);
		result.append(", when: ");
		result.append(when);
		result.append(", changes: ");
		result.append(changes);
		result.append(", exists: ");
		result.append(exists);
		result.append(", allowFailure: ");
		result.append(allowFailure);
		result.append(')');
		return result.toString();
	}

} //RuleImpl
