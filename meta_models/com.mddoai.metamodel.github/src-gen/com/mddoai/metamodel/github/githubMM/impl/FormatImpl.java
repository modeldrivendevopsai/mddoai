/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.Format;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;

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
 * An implementation of the model object '<em><b>Format</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.FormatImpl#getString <em>String</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.FormatImpl#getReplaceValues <em>Replace Values</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FormatImpl extends BuiltInFunctionCallImpl implements Format {
	/**
	 * The cached value of the '{@link #getString() <em>String</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getString()
	 * @generated
	 * @ordered
	 */
	protected Expression string;

	/**
	 * The cached value of the '{@link #getReplaceValues() <em>Replace Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReplaceValues()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> replaceValues;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormatImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.FORMAT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getString() {
		return string;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetString(Expression newString, NotificationChain msgs) {
		Expression oldString = string;
		string = newString;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.FORMAT__STRING, oldString, newString);
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
	public void setString(Expression newString) {
		if (newString != string) {
			NotificationChain msgs = null;
			if (string != null)
				msgs = ((InternalEObject) string).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.FORMAT__STRING, null, msgs);
			if (newString != null)
				msgs = ((InternalEObject) newString).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.FORMAT__STRING, null, msgs);
			msgs = basicSetString(newString, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.FORMAT__STRING, newString,
					newString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Expression> getReplaceValues() {
		if (replaceValues == null) {
			replaceValues = new EObjectContainmentEList<Expression>(Expression.class, this,
					GithubMMPackage.FORMAT__REPLACE_VALUES);
		}
		return replaceValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.FORMAT__STRING:
			return basicSetString(null, msgs);
		case GithubMMPackage.FORMAT__REPLACE_VALUES:
			return ((InternalEList<?>) getReplaceValues()).basicRemove(otherEnd, msgs);
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
		case GithubMMPackage.FORMAT__STRING:
			return getString();
		case GithubMMPackage.FORMAT__REPLACE_VALUES:
			return getReplaceValues();
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
		case GithubMMPackage.FORMAT__STRING:
			setString((Expression) newValue);
			return;
		case GithubMMPackage.FORMAT__REPLACE_VALUES:
			getReplaceValues().clear();
			getReplaceValues().addAll((Collection<? extends Expression>) newValue);
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
		case GithubMMPackage.FORMAT__STRING:
			setString((Expression) null);
			return;
		case GithubMMPackage.FORMAT__REPLACE_VALUES:
			getReplaceValues().clear();
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
		case GithubMMPackage.FORMAT__STRING:
			return string != null;
		case GithubMMPackage.FORMAT__REPLACE_VALUES:
			return replaceValues != null && !replaceValues.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //FormatImpl
