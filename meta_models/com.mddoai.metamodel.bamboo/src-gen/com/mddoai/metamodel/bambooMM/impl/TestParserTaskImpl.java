/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.TEST_PARSER_TYPE;
import com.mddoai.metamodel.bambooMM.TestParserTask;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Parser Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.TestParserTaskImpl#getType <em>Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.TestParserTaskImpl#getTestResults <em>Test Results</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.TestParserTaskImpl#getIgnoreTime <em>Ignore Time</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestParserTaskImpl extends TaskImpl implements TestParserTask {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final TEST_PARSER_TYPE TYPE_EDEFAULT = TEST_PARSER_TYPE.JUNIT;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected TEST_PARSER_TYPE type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTestResults() <em>Test Results</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestResults()
	 * @generated
	 * @ordered
	 */
	protected EList<String> testResults;

	/**
	 * The default value of the '{@link #getIgnoreTime() <em>Ignore Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIgnoreTime()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IGNORE_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIgnoreTime() <em>Ignore Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIgnoreTime()
	 * @generated
	 * @ordered
	 */
	protected Boolean ignoreTime = IGNORE_TIME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestParserTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.TEST_PARSER_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TEST_PARSER_TYPE getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(TEST_PARSER_TYPE newType) {
		TEST_PARSER_TYPE oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.TEST_PARSER_TASK__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getTestResults() {
		if (testResults == null) {
			testResults = new EDataTypeUniqueEList<String>(String.class, this,
					BambooPackage.TEST_PARSER_TASK__TEST_RESULTS);
		}
		return testResults;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Boolean getIgnoreTime() {
		return ignoreTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIgnoreTime(Boolean newIgnoreTime) {
		Boolean oldIgnoreTime = ignoreTime;
		ignoreTime = newIgnoreTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.TEST_PARSER_TASK__IGNORE_TIME,
					oldIgnoreTime, ignoreTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case BambooPackage.TEST_PARSER_TASK__TYPE:
			return getType();
		case BambooPackage.TEST_PARSER_TASK__TEST_RESULTS:
			return getTestResults();
		case BambooPackage.TEST_PARSER_TASK__IGNORE_TIME:
			return getIgnoreTime();
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
		case BambooPackage.TEST_PARSER_TASK__TYPE:
			setType((TEST_PARSER_TYPE) newValue);
			return;
		case BambooPackage.TEST_PARSER_TASK__TEST_RESULTS:
			getTestResults().clear();
			getTestResults().addAll((Collection<? extends String>) newValue);
			return;
		case BambooPackage.TEST_PARSER_TASK__IGNORE_TIME:
			setIgnoreTime((Boolean) newValue);
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
		case BambooPackage.TEST_PARSER_TASK__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case BambooPackage.TEST_PARSER_TASK__TEST_RESULTS:
			getTestResults().clear();
			return;
		case BambooPackage.TEST_PARSER_TASK__IGNORE_TIME:
			setIgnoreTime(IGNORE_TIME_EDEFAULT);
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
		case BambooPackage.TEST_PARSER_TASK__TYPE:
			return type != TYPE_EDEFAULT;
		case BambooPackage.TEST_PARSER_TASK__TEST_RESULTS:
			return testResults != null && !testResults.isEmpty();
		case BambooPackage.TEST_PARSER_TASK__IGNORE_TIME:
			return IGNORE_TIME_EDEFAULT == null ? ignoreTime != null : !IGNORE_TIME_EDEFAULT.equals(ignoreTime);
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
		result.append(", testResults: ");
		result.append(testResults);
		result.append(", ignoreTime: ");
		result.append(ignoreTime);
		result.append(')');
		return result.toString();
	}

} //TestParserTaskImpl
