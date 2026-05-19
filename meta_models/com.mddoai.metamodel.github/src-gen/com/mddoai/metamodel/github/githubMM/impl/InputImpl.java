/**
 */
package com.mddoai.metamodel.github.githubMM.impl;

import com.mddoai.metamodel.github.githubMM.Expression;
import com.mddoai.metamodel.github.githubMM.GithubMMPackage;
import com.mddoai.metamodel.github.githubMM.INPUT_TYPES;
import com.mddoai.metamodel.github.githubMM.Input;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.InputImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.InputImpl#getIsRequired <em>Is Required</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.InputImpl#getDefault <em>Default</em>}</li>
 *   <li>{@link com.mddoai.metamodel.github.githubMM.impl.InputImpl#getOptions <em>Options</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InputImpl extends ParameterImpl implements Input {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final INPUT_TYPES TYPE_EDEFAULT = INPUT_TYPES.STRING;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected INPUT_TYPES type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsRequired() <em>Is Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsRequired()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_REQUIRED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsRequired() <em>Is Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsRequired()
	 * @generated
	 * @ordered
	 */
	protected Boolean isRequired = IS_REQUIRED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDefault() <em>Default</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefault()
	 * @generated
	 * @ordered
	 */
	protected Expression default_;

	/**
	 * The cached value of the '{@link #getOptions() <em>Options</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected EList<String> options;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InputImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GithubMMPackage.Literals.INPUT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public INPUT_TYPES getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(INPUT_TYPES newType) {
		INPUT_TYPES oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.INPUT__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIsRequired() {
		return isRequired;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsRequired(Boolean newIsRequired) {
		Boolean oldIsRequired = isRequired;
		isRequired = newIsRequired;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.INPUT__IS_REQUIRED, oldIsRequired,
					isRequired));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getDefault() {
		return default_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefault(Expression newDefault, NotificationChain msgs) {
		Expression oldDefault = default_;
		default_ = newDefault;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GithubMMPackage.INPUT__DEFAULT, oldDefault, newDefault);
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
	public void setDefault(Expression newDefault) {
		if (newDefault != default_) {
			NotificationChain msgs = null;
			if (default_ != null)
				msgs = ((InternalEObject) default_).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.INPUT__DEFAULT, null, msgs);
			if (newDefault != null)
				msgs = ((InternalEObject) newDefault).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GithubMMPackage.INPUT__DEFAULT, null, msgs);
			msgs = basicSetDefault(newDefault, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GithubMMPackage.INPUT__DEFAULT, newDefault,
					newDefault));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getOptions() {
		if (options == null) {
			options = new EDataTypeUniqueEList<String>(String.class, this, GithubMMPackage.INPUT__OPTIONS);
		}
		return options;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GithubMMPackage.INPUT__DEFAULT:
			return basicSetDefault(null, msgs);
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
		case GithubMMPackage.INPUT__TYPE:
			return getType();
		case GithubMMPackage.INPUT__IS_REQUIRED:
			return getIsRequired();
		case GithubMMPackage.INPUT__DEFAULT:
			return getDefault();
		case GithubMMPackage.INPUT__OPTIONS:
			return getOptions();
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
		case GithubMMPackage.INPUT__TYPE:
			setType((INPUT_TYPES) newValue);
			return;
		case GithubMMPackage.INPUT__IS_REQUIRED:
			setIsRequired((Boolean) newValue);
			return;
		case GithubMMPackage.INPUT__DEFAULT:
			setDefault((Expression) newValue);
			return;
		case GithubMMPackage.INPUT__OPTIONS:
			getOptions().clear();
			getOptions().addAll((Collection<? extends String>) newValue);
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
		case GithubMMPackage.INPUT__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case GithubMMPackage.INPUT__IS_REQUIRED:
			setIsRequired(IS_REQUIRED_EDEFAULT);
			return;
		case GithubMMPackage.INPUT__DEFAULT:
			setDefault((Expression) null);
			return;
		case GithubMMPackage.INPUT__OPTIONS:
			getOptions().clear();
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
		case GithubMMPackage.INPUT__TYPE:
			return type != TYPE_EDEFAULT;
		case GithubMMPackage.INPUT__IS_REQUIRED:
			return IS_REQUIRED_EDEFAULT == null ? isRequired != null : !IS_REQUIRED_EDEFAULT.equals(isRequired);
		case GithubMMPackage.INPUT__DEFAULT:
			return default_ != null;
		case GithubMMPackage.INPUT__OPTIONS:
			return options != null && !options.isEmpty();
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
		result.append(" (type: ");
		result.append(type);
		result.append(", isRequired: ");
		result.append(isRequired);
		result.append(", options: ");
		result.append(options);
		result.append(')');
		return result.toString();
	}

} //InputImpl
