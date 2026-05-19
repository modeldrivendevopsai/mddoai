/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.EndsWith;
import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ends With</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.EndsWithImpl#getSearchString <em>Search String</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.EndsWithImpl#getSearchValue <em>Search Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EndsWithImpl extends BuiltInFunctionCallImpl implements EndsWith {
	/**
	 * The cached value of the '{@link #getSearchString() <em>Search String</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSearchString()
	 * @generated
	 * @ordered
	 */
	protected Expression searchString;

	/**
	 * The cached value of the '{@link #getSearchValue() <em>Search Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSearchValue()
	 * @generated
	 * @ordered
	 */
	protected Expression searchValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EndsWithImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.ENDS_WITH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getSearchString() {
		return searchString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSearchString(Expression newSearchString, NotificationChain msgs) {
		Expression oldSearchString = searchString;
		searchString = newSearchString;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.ENDS_WITH__SEARCH_STRING, oldSearchString, newSearchString);
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
	public void setSearchString(Expression newSearchString) {
		if (newSearchString != searchString) {
			NotificationChain msgs = null;
			if (searchString != null)
				msgs = ((InternalEObject) searchString).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.ENDS_WITH__SEARCH_STRING, null, msgs);
			if (newSearchString != null)
				msgs = ((InternalEObject) newSearchString).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.ENDS_WITH__SEARCH_STRING, null, msgs);
			msgs = basicSetSearchString(newSearchString, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.ENDS_WITH__SEARCH_STRING,
					newSearchString, newSearchString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getSearchValue() {
		return searchValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSearchValue(Expression newSearchValue, NotificationChain msgs) {
		Expression oldSearchValue = searchValue;
		searchValue = newSearchValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.ENDS_WITH__SEARCH_VALUE, oldSearchValue, newSearchValue);
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
	public void setSearchValue(Expression newSearchValue) {
		if (newSearchValue != searchValue) {
			NotificationChain msgs = null;
			if (searchValue != null)
				msgs = ((InternalEObject) searchValue).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.ENDS_WITH__SEARCH_VALUE, null, msgs);
			if (newSearchValue != null)
				msgs = ((InternalEObject) newSearchValue).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.ENDS_WITH__SEARCH_VALUE, null, msgs);
			msgs = basicSetSearchValue(newSearchValue, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.ENDS_WITH__SEARCH_VALUE,
					newSearchValue, newSearchValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.ENDS_WITH__SEARCH_STRING:
			return basicSetSearchString(null, msgs);
		case GithubMMPackage.ENDS_WITH__SEARCH_VALUE:
			return basicSetSearchValue(null, msgs);
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
		case GithubMMPackage.ENDS_WITH__SEARCH_STRING:
			return getSearchString();
		case GithubMMPackage.ENDS_WITH__SEARCH_VALUE:
			return getSearchValue();
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
		case GithubMMPackage.ENDS_WITH__SEARCH_STRING:
			setSearchString((Expression) newValue);
			return;
		case GithubMMPackage.ENDS_WITH__SEARCH_VALUE:
			setSearchValue((Expression) newValue);
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
		case GithubMMPackage.ENDS_WITH__SEARCH_STRING:
			setSearchString((Expression) null);
			return;
		case GithubMMPackage.ENDS_WITH__SEARCH_VALUE:
			setSearchValue((Expression) null);
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
		case GithubMMPackage.ENDS_WITH__SEARCH_STRING:
			return searchString != null;
		case GithubMMPackage.ENDS_WITH__SEARCH_VALUE:
			return searchValue != null;
		}
		return super.eIsSet(featureID);
	}

} //EndsWithImpl
