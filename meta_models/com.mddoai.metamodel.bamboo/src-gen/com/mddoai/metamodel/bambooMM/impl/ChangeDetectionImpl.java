/**
 */
package com.mddoai.metamodel.bambooMM.impl;

import com.mddoai.metamodel.bambooMM.BambooPackage;
import com.mddoai.metamodel.bambooMM.ChangeDetection;
import com.mddoai.metamodel.bambooMM.FILE_FILTER_TYPE;
import com.mddoai.metamodel.bambooMM.QuietPeriod;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Change Detection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ChangeDetectionImpl#getQuietPeriod <em>Quiet Period</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ChangeDetectionImpl#getExcludeChangesetPattern <em>Exclude Changeset Pattern</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ChangeDetectionImpl#getFileFilterType <em>File Filter Type</em>}</li>
 *   <li>{@link com.mddoai.metamodel.bambooMM.impl.ChangeDetectionImpl#getFileFilterPattern <em>File Filter Pattern</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ChangeDetectionImpl extends MinimalEObjectImpl.Container implements ChangeDetection {
	/**
	 * The cached value of the '{@link #getQuietPeriod() <em>Quiet Period</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuietPeriod()
	 * @generated
	 * @ordered
	 */
	protected QuietPeriod quietPeriod;

	/**
	 * The default value of the '{@link #getExcludeChangesetPattern() <em>Exclude Changeset Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExcludeChangesetPattern()
	 * @generated
	 * @ordered
	 */
	protected static final String EXCLUDE_CHANGESET_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExcludeChangesetPattern() <em>Exclude Changeset Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExcludeChangesetPattern()
	 * @generated
	 * @ordered
	 */
	protected String excludeChangesetPattern = EXCLUDE_CHANGESET_PATTERN_EDEFAULT;

	/**
	 * The default value of the '{@link #getFileFilterType() <em>File Filter Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileFilterType()
	 * @generated
	 * @ordered
	 */
	protected static final FILE_FILTER_TYPE FILE_FILTER_TYPE_EDEFAULT = FILE_FILTER_TYPE.INCLUDE_ONLY;

	/**
	 * The cached value of the '{@link #getFileFilterType() <em>File Filter Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileFilterType()
	 * @generated
	 * @ordered
	 */
	protected FILE_FILTER_TYPE fileFilterType = FILE_FILTER_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFileFilterPattern() <em>File Filter Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileFilterPattern()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_FILTER_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFileFilterPattern() <em>File Filter Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileFilterPattern()
	 * @generated
	 * @ordered
	 */
	protected String fileFilterPattern = FILE_FILTER_PATTERN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChangeDetectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BambooPackage.Literals.CHANGE_DETECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QuietPeriod getQuietPeriod() {
		return quietPeriod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQuietPeriod(QuietPeriod newQuietPeriod, NotificationChain msgs) {
		QuietPeriod oldQuietPeriod = quietPeriod;
		quietPeriod = newQuietPeriod;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					BambooPackage.CHANGE_DETECTION__QUIET_PERIOD, oldQuietPeriod, newQuietPeriod);
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
	public void setQuietPeriod(QuietPeriod newQuietPeriod) {
		if (newQuietPeriod != quietPeriod) {
			NotificationChain msgs = null;
			if (quietPeriod != null)
				msgs = ((InternalEObject) quietPeriod).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.CHANGE_DETECTION__QUIET_PERIOD, null, msgs);
			if (newQuietPeriod != null)
				msgs = ((InternalEObject) newQuietPeriod).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - BambooPackage.CHANGE_DETECTION__QUIET_PERIOD, null, msgs);
			msgs = basicSetQuietPeriod(newQuietPeriod, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.CHANGE_DETECTION__QUIET_PERIOD,
					newQuietPeriod, newQuietPeriod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getExcludeChangesetPattern() {
		return excludeChangesetPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExcludeChangesetPattern(String newExcludeChangesetPattern) {
		String oldExcludeChangesetPattern = excludeChangesetPattern;
		excludeChangesetPattern = newExcludeChangesetPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					BambooPackage.CHANGE_DETECTION__EXCLUDE_CHANGESET_PATTERN, oldExcludeChangesetPattern,
					excludeChangesetPattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FILE_FILTER_TYPE getFileFilterType() {
		return fileFilterType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFileFilterType(FILE_FILTER_TYPE newFileFilterType) {
		FILE_FILTER_TYPE oldFileFilterType = fileFilterType;
		fileFilterType = newFileFilterType == null ? FILE_FILTER_TYPE_EDEFAULT : newFileFilterType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.CHANGE_DETECTION__FILE_FILTER_TYPE,
					oldFileFilterType, fileFilterType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFileFilterPattern() {
		return fileFilterPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFileFilterPattern(String newFileFilterPattern) {
		String oldFileFilterPattern = fileFilterPattern;
		fileFilterPattern = newFileFilterPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BambooPackage.CHANGE_DETECTION__FILE_FILTER_PATTERN,
					oldFileFilterPattern, fileFilterPattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case BambooPackage.CHANGE_DETECTION__QUIET_PERIOD:
			return basicSetQuietPeriod(null, msgs);
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
		case BambooPackage.CHANGE_DETECTION__QUIET_PERIOD:
			return getQuietPeriod();
		case BambooPackage.CHANGE_DETECTION__EXCLUDE_CHANGESET_PATTERN:
			return getExcludeChangesetPattern();
		case BambooPackage.CHANGE_DETECTION__FILE_FILTER_TYPE:
			return getFileFilterType();
		case BambooPackage.CHANGE_DETECTION__FILE_FILTER_PATTERN:
			return getFileFilterPattern();
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
		case BambooPackage.CHANGE_DETECTION__QUIET_PERIOD:
			setQuietPeriod((QuietPeriod) newValue);
			return;
		case BambooPackage.CHANGE_DETECTION__EXCLUDE_CHANGESET_PATTERN:
			setExcludeChangesetPattern((String) newValue);
			return;
		case BambooPackage.CHANGE_DETECTION__FILE_FILTER_TYPE:
			setFileFilterType((FILE_FILTER_TYPE) newValue);
			return;
		case BambooPackage.CHANGE_DETECTION__FILE_FILTER_PATTERN:
			setFileFilterPattern((String) newValue);
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
		case BambooPackage.CHANGE_DETECTION__QUIET_PERIOD:
			setQuietPeriod((QuietPeriod) null);
			return;
		case BambooPackage.CHANGE_DETECTION__EXCLUDE_CHANGESET_PATTERN:
			setExcludeChangesetPattern(EXCLUDE_CHANGESET_PATTERN_EDEFAULT);
			return;
		case BambooPackage.CHANGE_DETECTION__FILE_FILTER_TYPE:
			setFileFilterType(FILE_FILTER_TYPE_EDEFAULT);
			return;
		case BambooPackage.CHANGE_DETECTION__FILE_FILTER_PATTERN:
			setFileFilterPattern(FILE_FILTER_PATTERN_EDEFAULT);
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
		case BambooPackage.CHANGE_DETECTION__QUIET_PERIOD:
			return quietPeriod != null;
		case BambooPackage.CHANGE_DETECTION__EXCLUDE_CHANGESET_PATTERN:
			return EXCLUDE_CHANGESET_PATTERN_EDEFAULT == null ? excludeChangesetPattern != null
					: !EXCLUDE_CHANGESET_PATTERN_EDEFAULT.equals(excludeChangesetPattern);
		case BambooPackage.CHANGE_DETECTION__FILE_FILTER_TYPE:
			return fileFilterType != FILE_FILTER_TYPE_EDEFAULT;
		case BambooPackage.CHANGE_DETECTION__FILE_FILTER_PATTERN:
			return FILE_FILTER_PATTERN_EDEFAULT == null ? fileFilterPattern != null
					: !FILE_FILTER_PATTERN_EDEFAULT.equals(fileFilterPattern);
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
		result.append(" (excludeChangesetPattern: ");
		result.append(excludeChangesetPattern);
		result.append(", fileFilterType: ");
		result.append(fileFilterType);
		result.append(", fileFilterPattern: ");
		result.append(fileFilterPattern);
		result.append(')');
		return result.toString();
	}

} //ChangeDetectionImpl
