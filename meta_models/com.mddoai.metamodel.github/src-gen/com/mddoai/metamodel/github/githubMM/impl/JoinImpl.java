/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.Join;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Join</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JoinImpl#getArray <em>Array</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.JoinImpl#getSep <em>Sep</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JoinImpl extends BuiltInFunctionCallImpl implements Join {
	/**
	 * The cached value of the '{@link #getArray() <em>Array</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArray()
	 * @generated
	 * @ordered
	 */
	protected Expression array;

	/**
	 * The cached value of the '{@link #getSep() <em>Sep</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSep()
	 * @generated
	 * @ordered
	 */
	protected Expression sep;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JoinImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.JOIN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getArray() {
		return array;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArray(Expression newArray, NotificationChain msgs) {
		Expression oldArray = array;
		array = newArray;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOIN__ARRAY,
					oldArray, newArray);
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
	public void setArray(Expression newArray) {
		if (newArray != array) {
			NotificationChain msgs = null;
			if (array != null)
				msgs = ((InternalEObject) array).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOIN__ARRAY, null, msgs);
			if (newArray != null)
				msgs = ((InternalEObject) newArray).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOIN__ARRAY, null, msgs);
			msgs = basicSetArray(newArray, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOIN__ARRAY, newArray, newArray));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getSep() {
		return sep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSep(Expression newSep, NotificationChain msgs) {
		Expression oldSep = sep;
		sep = newSep;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOIN__SEP,
					oldSep, newSep);
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
	public void setSep(Expression newSep) {
		if (newSep != sep) {
			NotificationChain msgs = null;
			if (sep != null)
				msgs = ((InternalEObject) sep).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOIN__SEP,
						null, msgs);
			if (newSep != null)
				msgs = ((InternalEObject) newSep).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GithubMMPackage.JOIN__SEP,
						null, msgs);
			msgs = basicSetSep(newSep, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.JOIN__SEP, newSep, newSep));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.JOIN__ARRAY:
			return basicSetArray(null, msgs);
		case GithubMMPackage.JOIN__SEP:
			return basicSetSep(null, msgs);
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
		case GithubMMPackage.JOIN__ARRAY:
			return getArray();
		case GithubMMPackage.JOIN__SEP:
			return getSep();
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
		case GithubMMPackage.JOIN__ARRAY:
			setArray((Expression) newValue);
			return;
		case GithubMMPackage.JOIN__SEP:
			setSep((Expression) newValue);
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
		case GithubMMPackage.JOIN__ARRAY:
			setArray((Expression) null);
			return;
		case GithubMMPackage.JOIN__SEP:
			setSep((Expression) null);
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
		case GithubMMPackage.JOIN__ARRAY:
			return array != null;
		case GithubMMPackage.JOIN__SEP:
			return sep != null;
		}
		return super.eIsSet(featureID);
	}

} //JoinImpl
