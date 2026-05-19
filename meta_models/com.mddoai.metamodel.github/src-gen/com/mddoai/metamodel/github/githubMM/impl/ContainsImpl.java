/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.Contains;
import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contains</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.ContainsImpl#getSearch <em>Search</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.ContainsImpl#getItem <em>Item</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContainsImpl extends BuiltInFunctionCallImpl implements Contains {
	/**
	 * The cached value of the '{@link #getSearch() <em>Search</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSearch()
	 * @generated
	 * @ordered
	 */
	protected Expression search;

	/**
	 * The cached value of the '{@link #getItem() <em>Item</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getItem()
	 * @generated
	 * @ordered
	 */
	protected Expression item;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.CONTAINS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getSearch() {
		return search;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSearch(Expression newSearch, NotificationChain msgs) {
		Expression oldSearch = search;
		search = newSearch;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.CONTAINS__SEARCH, oldSearch, newSearch);
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
	public void setSearch(Expression newSearch) {
		if (newSearch != search) {
			NotificationChain msgs = null;
			if (search != null)
				msgs = ((InternalEObject) search).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONTAINS__SEARCH, null, msgs);
			if (newSearch != null)
				msgs = ((InternalEObject) newSearch).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONTAINS__SEARCH, null, msgs);
			msgs = basicSetSearch(newSearch, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.CONTAINS__SEARCH, newSearch,
					newSearch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getItem() {
		return item;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetItem(Expression newItem, NotificationChain msgs) {
		Expression oldItem = item;
		item = newItem;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.CONTAINS__ITEM, oldItem, newItem);
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
	public void setItem(Expression newItem) {
		if (newItem != item) {
			NotificationChain msgs = null;
			if (item != null)
				msgs = ((InternalEObject) item).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONTAINS__ITEM, null, msgs);
			if (newItem != null)
				msgs = ((InternalEObject) newItem).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.CONTAINS__ITEM, null, msgs);
			msgs = basicSetItem(newItem, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.CONTAINS__ITEM, newItem, newItem));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.CONTAINS__SEARCH:
			return basicSetSearch(null, msgs);
		case GithubMMPackage.CONTAINS__ITEM:
			return basicSetItem(null, msgs);
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
		case GithubMMPackage.CONTAINS__SEARCH:
			return getSearch();
		case GithubMMPackage.CONTAINS__ITEM:
			return getItem();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GithubMMPackage.CONTAINS__SEARCH:
			setSearch((Expression) newValue);
			return;
		case GithubMMPackage.CONTAINS__ITEM:
			setItem((Expression) newValue);
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
		case GithubMMPackage.CONTAINS__SEARCH:
			setSearch((Expression) null);
			return;
		case GithubMMPackage.CONTAINS__ITEM:
			setItem((Expression) null);
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
		case GithubMMPackage.CONTAINS__SEARCH:
			return search != null;
		case GithubMMPackage.CONTAINS__ITEM:
			return item != null;
		}
		return super.eIsSet(featureID);
	}

} //ContainsImpl
