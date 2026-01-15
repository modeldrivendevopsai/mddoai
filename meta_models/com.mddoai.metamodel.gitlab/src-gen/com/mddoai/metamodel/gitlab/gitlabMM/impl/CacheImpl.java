/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.Cache;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.Key;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cache</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheImpl#getKey <em>Key</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheImpl#getPaths <em>Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheImpl#isUntracked <em>Untracked</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheImpl#isUnprotect <em>Unprotect</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CacheImpl#getWhen <em>When</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CacheImpl extends MinimalEObjectImpl.Container implements Cache {
	/**
	 * The cached value of the '{@link #getKey() <em>Key</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected Key key;

	/**
	 * The cached value of the '{@link #getPaths() <em>Paths</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaths()
	 * @generated
	 * @ordered
	 */
	protected EList<String> paths;

	/**
	 * The default value of the '{@link #isUntracked() <em>Untracked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUntracked()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNTRACKED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUntracked() <em>Untracked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUntracked()
	 * @generated
	 * @ordered
	 */
	protected boolean untracked = UNTRACKED_EDEFAULT;

	/**
	 * The default value of the '{@link #isUnprotect() <em>Unprotect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnprotect()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNPROTECT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUnprotect() <em>Unprotect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnprotect()
	 * @generated
	 * @ordered
	 */
	protected boolean unprotect = UNPROTECT_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CacheImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GitlabMMPackage.Literals.CACHE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Key getKey() {
		if (key != null && key.eIsProxy()) {
			InternalEObject oldKey = (InternalEObject) key;
			key = (Key) eResolveProxy(oldKey);
			if (key != oldKey) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GitlabMMPackage.CACHE__KEY, oldKey, key));
			}
		}
		return key;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Key basicGetKey() {
		return key;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setKey(Key newKey) {
		Key oldKey = key;
		key = newKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.CACHE__KEY, oldKey, key));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getPaths() {
		if (paths == null) {
			paths = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.CACHE__PATHS);
		}
		return paths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isUntracked() {
		return untracked;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUntracked(boolean newUntracked) {
		boolean oldUntracked = untracked;
		untracked = newUntracked;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.CACHE__UNTRACKED, oldUntracked,
					untracked));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isUnprotect() {
		return unprotect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUnprotect(boolean newUnprotect) {
		boolean oldUnprotect = unprotect;
		unprotect = newUnprotect;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.CACHE__UNPROTECT, oldUnprotect,
					unprotect));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.CACHE__WHEN, oldWhen, when));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GitlabMMPackage.CACHE__KEY:
			if (resolve)
				return getKey();
			return basicGetKey();
		case GitlabMMPackage.CACHE__PATHS:
			return getPaths();
		case GitlabMMPackage.CACHE__UNTRACKED:
			return isUntracked();
		case GitlabMMPackage.CACHE__UNPROTECT:
			return isUnprotect();
		case GitlabMMPackage.CACHE__WHEN:
			return getWhen();
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
		case GitlabMMPackage.CACHE__KEY:
			setKey((Key) newValue);
			return;
		case GitlabMMPackage.CACHE__PATHS:
			getPaths().clear();
			getPaths().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.CACHE__UNTRACKED:
			setUntracked((Boolean) newValue);
			return;
		case GitlabMMPackage.CACHE__UNPROTECT:
			setUnprotect((Boolean) newValue);
			return;
		case GitlabMMPackage.CACHE__WHEN:
			setWhen((String) newValue);
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
		case GitlabMMPackage.CACHE__KEY:
			setKey((Key) null);
			return;
		case GitlabMMPackage.CACHE__PATHS:
			getPaths().clear();
			return;
		case GitlabMMPackage.CACHE__UNTRACKED:
			setUntracked(UNTRACKED_EDEFAULT);
			return;
		case GitlabMMPackage.CACHE__UNPROTECT:
			setUnprotect(UNPROTECT_EDEFAULT);
			return;
		case GitlabMMPackage.CACHE__WHEN:
			setWhen(WHEN_EDEFAULT);
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
		case GitlabMMPackage.CACHE__KEY:
			return key != null;
		case GitlabMMPackage.CACHE__PATHS:
			return paths != null && !paths.isEmpty();
		case GitlabMMPackage.CACHE__UNTRACKED:
			return untracked != UNTRACKED_EDEFAULT;
		case GitlabMMPackage.CACHE__UNPROTECT:
			return unprotect != UNPROTECT_EDEFAULT;
		case GitlabMMPackage.CACHE__WHEN:
			return WHEN_EDEFAULT == null ? when != null : !WHEN_EDEFAULT.equals(when);
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
		result.append(" (paths: ");
		result.append(paths);
		result.append(", untracked: ");
		result.append(untracked);
		result.append(", unprotect: ");
		result.append(unprotect);
		result.append(", when: ");
		result.append(when);
		result.append(')');
		return result.toString();
	}

} //CacheImpl
