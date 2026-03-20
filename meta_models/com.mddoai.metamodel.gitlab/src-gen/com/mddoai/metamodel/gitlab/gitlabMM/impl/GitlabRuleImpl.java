/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabRule;
import com.mddoai.metamodel.gitlab.gitlabMM.Variables;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gitlab Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabRuleImpl#getGitlabIf <em>Gitlab If</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabRuleImpl#getWhen <em>When</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabRuleImpl#getChanges <em>Changes</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabRuleImpl#getExists <em>Exists</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabRuleImpl#isAllowFailure <em>Allow Failure</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.GitlabRuleImpl#getVariables <em>Variables</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GitlabRuleImpl extends MinimalEObjectImpl.Container implements GitlabRule {
	/**
	 * The default value of the '{@link #getGitlabIf() <em>Gitlab If</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGitlabIf()
	 * @generated
	 * @ordered
	 */
	protected static final String GITLAB_IF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGitlabIf() <em>Gitlab If</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGitlabIf()
	 * @generated
	 * @ordered
	 */
	protected String gitlabIf = GITLAB_IF_EDEFAULT;

	/**
	 * The default value of the '{@link #getWhen() <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhen()
	 * @generated
	 * @ordered
	 */
	protected static final String WHEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWhen() <em>When</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhen()
	 * @generated
	 * @ordered
	 */
	protected String when = WHEN_EDEFAULT;

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
	 * The default value of the '{@link #isAllowFailure() <em>Allow Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowFailure()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALLOW_FAILURE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAllowFailure() <em>Allow Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllowFailure()
	 * @generated
	 * @ordered
	 */
	protected boolean allowFailure = ALLOW_FAILURE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected Variables variables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GitlabRuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GitlabMMPackage.Literals.GITLAB_RULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getGitlabIf() {
		return gitlabIf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGitlabIf(String newGitlabIf) {
		String oldGitlabIf = gitlabIf;
		gitlabIf = newGitlabIf;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.GITLAB_RULE__GITLAB_IF, oldGitlabIf,
					gitlabIf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getWhen() {
		return when;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWhen(String newWhen) {
		String oldWhen = when;
		when = newWhen;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.GITLAB_RULE__WHEN, oldWhen, when));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getChanges() {
		if (changes == null) {
			changes = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.GITLAB_RULE__CHANGES);
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
			exists = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.GITLAB_RULE__EXISTS);
		}
		return exists;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isAllowFailure() {
		return allowFailure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAllowFailure(boolean newAllowFailure) {
		boolean oldAllowFailure = allowFailure;
		allowFailure = newAllowFailure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.GITLAB_RULE__ALLOW_FAILURE,
					oldAllowFailure, allowFailure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Variables getVariables() {
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariables(Variables newVariables, NotificationChain msgs) {
		Variables oldVariables = variables;
		variables = newVariables;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.GITLAB_RULE__VARIABLES, oldVariables, newVariables);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVariables(Variables newVariables) {
		if (newVariables != variables) {
			NotificationChain msgs = null;
			if (variables != null)
				msgs = ((InternalEObject) variables).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.GITLAB_RULE__VARIABLES, null, msgs);
			if (newVariables != null)
				msgs = ((InternalEObject) newVariables).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.GITLAB_RULE__VARIABLES, null, msgs);
			msgs = basicSetVariables(newVariables, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.GITLAB_RULE__VARIABLES, newVariables,
					newVariables));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GitlabMMPackage.GITLAB_RULE__VARIABLES:
			return basicSetVariables(null, msgs);
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
		case GitlabMMPackage.GITLAB_RULE__GITLAB_IF:
			return getGitlabIf();
		case GitlabMMPackage.GITLAB_RULE__WHEN:
			return getWhen();
		case GitlabMMPackage.GITLAB_RULE__CHANGES:
			return getChanges();
		case GitlabMMPackage.GITLAB_RULE__EXISTS:
			return getExists();
		case GitlabMMPackage.GITLAB_RULE__ALLOW_FAILURE:
			return isAllowFailure();
		case GitlabMMPackage.GITLAB_RULE__VARIABLES:
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
		case GitlabMMPackage.GITLAB_RULE__GITLAB_IF:
			setGitlabIf((String) newValue);
			return;
		case GitlabMMPackage.GITLAB_RULE__WHEN:
			setWhen((String) newValue);
			return;
		case GitlabMMPackage.GITLAB_RULE__CHANGES:
			getChanges().clear();
			getChanges().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.GITLAB_RULE__EXISTS:
			getExists().clear();
			getExists().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.GITLAB_RULE__ALLOW_FAILURE:
			setAllowFailure((Boolean) newValue);
			return;
		case GitlabMMPackage.GITLAB_RULE__VARIABLES:
			setVariables((Variables) newValue);
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
		case GitlabMMPackage.GITLAB_RULE__GITLAB_IF:
			setGitlabIf(GITLAB_IF_EDEFAULT);
			return;
		case GitlabMMPackage.GITLAB_RULE__WHEN:
			setWhen(WHEN_EDEFAULT);
			return;
		case GitlabMMPackage.GITLAB_RULE__CHANGES:
			getChanges().clear();
			return;
		case GitlabMMPackage.GITLAB_RULE__EXISTS:
			getExists().clear();
			return;
		case GitlabMMPackage.GITLAB_RULE__ALLOW_FAILURE:
			setAllowFailure(ALLOW_FAILURE_EDEFAULT);
			return;
		case GitlabMMPackage.GITLAB_RULE__VARIABLES:
			setVariables((Variables) null);
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
		case GitlabMMPackage.GITLAB_RULE__GITLAB_IF:
			return GITLAB_IF_EDEFAULT == null ? gitlabIf != null : !GITLAB_IF_EDEFAULT.equals(gitlabIf);
		case GitlabMMPackage.GITLAB_RULE__WHEN:
			return WHEN_EDEFAULT == null ? when != null : !WHEN_EDEFAULT.equals(when);
		case GitlabMMPackage.GITLAB_RULE__CHANGES:
			return changes != null && !changes.isEmpty();
		case GitlabMMPackage.GITLAB_RULE__EXISTS:
			return exists != null && !exists.isEmpty();
		case GitlabMMPackage.GITLAB_RULE__ALLOW_FAILURE:
			return allowFailure != ALLOW_FAILURE_EDEFAULT;
		case GitlabMMPackage.GITLAB_RULE__VARIABLES:
			return variables != null;
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
		result.append(" (gitlabIf: ");
		result.append(gitlabIf);
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

} //GitlabRuleImpl
