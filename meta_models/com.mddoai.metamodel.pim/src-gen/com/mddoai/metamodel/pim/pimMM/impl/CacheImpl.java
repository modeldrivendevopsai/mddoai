/**
 */
package com.mddoai.metamodel.pim.pimMM.impl;

import com.mddoai.metamodel.pim.pimMM.CACHE_MODE;
import com.mddoai.metamodel.pim.pimMM.Cache;
import com.mddoai.metamodel.pim.pimMM.Expression;
import com.mddoai.metamodel.pim.pimMM.PimMMPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cache</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.CacheImpl#getCacheName <em>Cache Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.CacheImpl#getKeys <em>Keys</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.CacheImpl#getPaths <em>Paths</em>}</li>
 *   <li>{@link com.mddoai.metamodel.pim.pimMM.impl.CacheImpl#getStore <em>Store</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CacheImpl extends NonConditionalStepImpl implements Cache {
	/**
	 * The cached value of the '{@link #getCacheName() <em>Cache Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCacheName()
	 * @generated
	 * @ordered
	 */
	protected Expression cacheName;

	/**
	 * The cached value of the '{@link #getKeys() <em>Keys</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeys()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> keys;

	/**
	 * The cached value of the '{@link #getPaths() <em>Paths</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaths()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> paths;

	/**
	 * The default value of the '{@link #getStore() <em>Store</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStore()
	 * @generated
	 * @ordered
	 */
	protected static final CACHE_MODE STORE_EDEFAULT = CACHE_MODE.STORE;

	/**
	 * The cached value of the '{@link #getStore() <em>Store</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStore()
	 * @generated
	 * @ordered
	 */
	protected CACHE_MODE store = STORE_EDEFAULT;

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
		return PimMMPackage.Literals.CACHE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getCacheName() {
		return cacheName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCacheName(Expression newCacheName, NotificationChain msgs) {
		Expression oldCacheName = cacheName;
		cacheName = newCacheName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PimMMPackage.CACHE__CACHE_NAME, oldCacheName, newCacheName);
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
	public void setCacheName(Expression newCacheName) {
		if (newCacheName != cacheName) {
			NotificationChain msgs = null;
			if (cacheName != null)
				msgs = ((InternalEObject) cacheName).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.CACHE__CACHE_NAME, null, msgs);
			if (newCacheName != null)
				msgs = ((InternalEObject) newCacheName).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - PimMMPackage.CACHE__CACHE_NAME, null, msgs);
			msgs = basicSetCacheName(newCacheName, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.CACHE__CACHE_NAME, newCacheName,
					newCacheName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expression> getKeys() {
		if (keys == null) {
			keys = new EObjectContainmentEList<Expression>(Expression.class, this, PimMMPackage.CACHE__KEYS);
		}
		return keys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expression> getPaths() {
		if (paths == null) {
			paths = new EObjectContainmentEList<Expression>(Expression.class, this, PimMMPackage.CACHE__PATHS);
		}
		return paths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CACHE_MODE getStore() {
		return store;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStore(CACHE_MODE newStore) {
		CACHE_MODE oldStore = store;
		store = newStore == null ? STORE_EDEFAULT : newStore;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PimMMPackage.CACHE__STORE, oldStore, store));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PimMMPackage.CACHE__CACHE_NAME:
			return basicSetCacheName(null, msgs);
		case PimMMPackage.CACHE__KEYS:
			return ((InternalEList<?>) getKeys()).basicRemove(otherEnd, msgs);
		case PimMMPackage.CACHE__PATHS:
			return ((InternalEList<?>) getPaths()).basicRemove(otherEnd, msgs);
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
		case PimMMPackage.CACHE__CACHE_NAME:
			return getCacheName();
		case PimMMPackage.CACHE__KEYS:
			return getKeys();
		case PimMMPackage.CACHE__PATHS:
			return getPaths();
		case PimMMPackage.CACHE__STORE:
			return getStore();
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
		case PimMMPackage.CACHE__CACHE_NAME:
			setCacheName((Expression) newValue);
			return;
		case PimMMPackage.CACHE__KEYS:
			getKeys().clear();
			getKeys().addAll((Collection<? extends Expression>) newValue);
			return;
		case PimMMPackage.CACHE__PATHS:
			getPaths().clear();
			getPaths().addAll((Collection<? extends Expression>) newValue);
			return;
		case PimMMPackage.CACHE__STORE:
			setStore((CACHE_MODE) newValue);
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
		case PimMMPackage.CACHE__CACHE_NAME:
			setCacheName((Expression) null);
			return;
		case PimMMPackage.CACHE__KEYS:
			getKeys().clear();
			return;
		case PimMMPackage.CACHE__PATHS:
			getPaths().clear();
			return;
		case PimMMPackage.CACHE__STORE:
			setStore(STORE_EDEFAULT);
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
		case PimMMPackage.CACHE__CACHE_NAME:
			return cacheName != null;
		case PimMMPackage.CACHE__KEYS:
			return keys != null && !keys.isEmpty();
		case PimMMPackage.CACHE__PATHS:
			return paths != null && !paths.isEmpty();
		case PimMMPackage.CACHE__STORE:
			return store != STORE_EDEFAULT;
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
		result.append(" (store: ");
		result.append(store);
		result.append(')');
		return result.toString();
	}

} //CacheImpl
