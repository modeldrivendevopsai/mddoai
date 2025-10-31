/**
 */
package com.mddoai.metamodel.swarch.impl;

import com.mddoai.metamodel.swarch.SoftwareArchitecture;
import com.mddoai.metamodel.swarch.SwarchPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Software Architecture</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.swarch.impl.SoftwareArchitectureImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.mddoai.metamodel.swarch.impl.SoftwareArchitectureImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.swarch.impl.SoftwareArchitectureImpl#getBuild_system <em>Build system</em>}</li>
 *   <li>{@link com.mddoai.metamodel.swarch.impl.SoftwareArchitectureImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link com.mddoai.metamodel.swarch.impl.SoftwareArchitectureImpl#getUnit_test <em>Unit test</em>}</li>
 *   <li>{@link com.mddoai.metamodel.swarch.impl.SoftwareArchitectureImpl#getHealth_check <em>Health check</em>}</li>
 *   <li>{@link com.mddoai.metamodel.swarch.impl.SoftwareArchitectureImpl#getLint_check <em>Lint check</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SoftwareArchitectureImpl extends MinimalEObjectImpl.Container implements SoftwareArchitecture {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getBuild_system() <em>Build system</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuild_system()
	 * @generated
	 * @ordered
	 */
	protected static final String BUILD_SYSTEM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBuild_system() <em>Build system</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuild_system()
	 * @generated
	 * @ordered
	 */
	protected String build_system = BUILD_SYSTEM_EDEFAULT;

	/**
	 * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected String language = LANGUAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUnit_test() <em>Unit test</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit_test()
	 * @generated
	 * @ordered
	 */
	protected static final String UNIT_TEST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUnit_test() <em>Unit test</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnit_test()
	 * @generated
	 * @ordered
	 */
	protected String unit_test = UNIT_TEST_EDEFAULT;

	/**
	 * The default value of the '{@link #getHealth_check() <em>Health check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHealth_check()
	 * @generated
	 * @ordered
	 */
	protected static final String HEALTH_CHECK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHealth_check() <em>Health check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHealth_check()
	 * @generated
	 * @ordered
	 */
	protected String health_check = HEALTH_CHECK_EDEFAULT;

	/**
	 * The default value of the '{@link #getLint_check() <em>Lint check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLint_check()
	 * @generated
	 * @ordered
	 */
	protected static final String LINT_CHECK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLint_check() <em>Lint check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLint_check()
	 * @generated
	 * @ordered
	 */
	protected String lint_check = LINT_CHECK_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SoftwareArchitectureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SwarchPackage.Literals.SOFTWARE_ARCHITECTURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SwarchPackage.SOFTWARE_ARCHITECTURE__NAME, oldName,
					name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SwarchPackage.SOFTWARE_ARCHITECTURE__TYPE, oldType,
					type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBuild_system() {
		return build_system;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBuild_system(String newBuild_system) {
		String oldBuild_system = build_system;
		build_system = newBuild_system;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SwarchPackage.SOFTWARE_ARCHITECTURE__BUILD_SYSTEM,
					oldBuild_system, build_system));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLanguage() {
		return language;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLanguage(String newLanguage) {
		String oldLanguage = language;
		language = newLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SwarchPackage.SOFTWARE_ARCHITECTURE__LANGUAGE,
					oldLanguage, language));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUnit_test() {
		return unit_test;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUnit_test(String newUnit_test) {
		String oldUnit_test = unit_test;
		unit_test = newUnit_test;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SwarchPackage.SOFTWARE_ARCHITECTURE__UNIT_TEST,
					oldUnit_test, unit_test));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getHealth_check() {
		return health_check;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHealth_check(String newHealth_check) {
		String oldHealth_check = health_check;
		health_check = newHealth_check;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SwarchPackage.SOFTWARE_ARCHITECTURE__HEALTH_CHECK,
					oldHealth_check, health_check));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLint_check() {
		return lint_check;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLint_check(String newLint_check) {
		String oldLint_check = lint_check;
		lint_check = newLint_check;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SwarchPackage.SOFTWARE_ARCHITECTURE__LINT_CHECK,
					oldLint_check, lint_check));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case SwarchPackage.SOFTWARE_ARCHITECTURE__NAME:
			return getName();
		case SwarchPackage.SOFTWARE_ARCHITECTURE__TYPE:
			return getType();
		case SwarchPackage.SOFTWARE_ARCHITECTURE__BUILD_SYSTEM:
			return getBuild_system();
		case SwarchPackage.SOFTWARE_ARCHITECTURE__LANGUAGE:
			return getLanguage();
		case SwarchPackage.SOFTWARE_ARCHITECTURE__UNIT_TEST:
			return getUnit_test();
		case SwarchPackage.SOFTWARE_ARCHITECTURE__HEALTH_CHECK:
			return getHealth_check();
		case SwarchPackage.SOFTWARE_ARCHITECTURE__LINT_CHECK:
			return getLint_check();
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
		case SwarchPackage.SOFTWARE_ARCHITECTURE__NAME:
			setName((String) newValue);
			return;
		case SwarchPackage.SOFTWARE_ARCHITECTURE__TYPE:
			setType((String) newValue);
			return;
		case SwarchPackage.SOFTWARE_ARCHITECTURE__BUILD_SYSTEM:
			setBuild_system((String) newValue);
			return;
		case SwarchPackage.SOFTWARE_ARCHITECTURE__LANGUAGE:
			setLanguage((String) newValue);
			return;
		case SwarchPackage.SOFTWARE_ARCHITECTURE__UNIT_TEST:
			setUnit_test((String) newValue);
			return;
		case SwarchPackage.SOFTWARE_ARCHITECTURE__HEALTH_CHECK:
			setHealth_check((String) newValue);
			return;
		case SwarchPackage.SOFTWARE_ARCHITECTURE__LINT_CHECK:
			setLint_check((String) newValue);
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
		case SwarchPackage.SOFTWARE_ARCHITECTURE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case SwarchPackage.SOFTWARE_ARCHITECTURE__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case SwarchPackage.SOFTWARE_ARCHITECTURE__BUILD_SYSTEM:
			setBuild_system(BUILD_SYSTEM_EDEFAULT);
			return;
		case SwarchPackage.SOFTWARE_ARCHITECTURE__LANGUAGE:
			setLanguage(LANGUAGE_EDEFAULT);
			return;
		case SwarchPackage.SOFTWARE_ARCHITECTURE__UNIT_TEST:
			setUnit_test(UNIT_TEST_EDEFAULT);
			return;
		case SwarchPackage.SOFTWARE_ARCHITECTURE__HEALTH_CHECK:
			setHealth_check(HEALTH_CHECK_EDEFAULT);
			return;
		case SwarchPackage.SOFTWARE_ARCHITECTURE__LINT_CHECK:
			setLint_check(LINT_CHECK_EDEFAULT);
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
		case SwarchPackage.SOFTWARE_ARCHITECTURE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case SwarchPackage.SOFTWARE_ARCHITECTURE__TYPE:
			return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		case SwarchPackage.SOFTWARE_ARCHITECTURE__BUILD_SYSTEM:
			return BUILD_SYSTEM_EDEFAULT == null ? build_system != null : !BUILD_SYSTEM_EDEFAULT.equals(build_system);
		case SwarchPackage.SOFTWARE_ARCHITECTURE__LANGUAGE:
			return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
		case SwarchPackage.SOFTWARE_ARCHITECTURE__UNIT_TEST:
			return UNIT_TEST_EDEFAULT == null ? unit_test != null : !UNIT_TEST_EDEFAULT.equals(unit_test);
		case SwarchPackage.SOFTWARE_ARCHITECTURE__HEALTH_CHECK:
			return HEALTH_CHECK_EDEFAULT == null ? health_check != null : !HEALTH_CHECK_EDEFAULT.equals(health_check);
		case SwarchPackage.SOFTWARE_ARCHITECTURE__LINT_CHECK:
			return LINT_CHECK_EDEFAULT == null ? lint_check != null : !LINT_CHECK_EDEFAULT.equals(lint_check);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", type: ");
		result.append(type);
		result.append(", build_system: ");
		result.append(build_system);
		result.append(", language: ");
		result.append(language);
		result.append(", unit_test: ");
		result.append(unit_test);
		result.append(", health_check: ");
		result.append(health_check);
		result.append(", lint_check: ");
		result.append(lint_check);
		result.append(')');
		return result.toString();
	}

} //SoftwareArchitectureImpl
