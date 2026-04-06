/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.CoverageReport;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Coverage Report</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CoverageReportImpl#getCoverageFormat <em>Coverage Format</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.CoverageReportImpl#getPath <em>Path</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CoverageReportImpl extends MinimalEObjectImpl.Container implements CoverageReport {
	/**
	 * The default value of the '{@link #getCoverageFormat() <em>Coverage Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoverageFormat()
	 * @generated
	 * @ordered
	 */
	protected static final String COVERAGE_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCoverageFormat() <em>Coverage Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoverageFormat()
	 * @generated
	 * @ordered
	 */
	protected String coverageFormat = COVERAGE_FORMAT_EDEFAULT;

	/**
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CoverageReportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GitlabMMPackage.Literals.COVERAGE_REPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCoverageFormat() {
		return coverageFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCoverageFormat(String newCoverageFormat) {
		String oldCoverageFormat = coverageFormat;
		coverageFormat = newCoverageFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.COVERAGE_REPORT__COVERAGE_FORMAT,
					oldCoverageFormat, coverageFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.COVERAGE_REPORT__PATH, oldPath,
					path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GitlabMMPackage.COVERAGE_REPORT__COVERAGE_FORMAT:
			return getCoverageFormat();
		case GitlabMMPackage.COVERAGE_REPORT__PATH:
			return getPath();
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
		case GitlabMMPackage.COVERAGE_REPORT__COVERAGE_FORMAT:
			setCoverageFormat((String) newValue);
			return;
		case GitlabMMPackage.COVERAGE_REPORT__PATH:
			setPath((String) newValue);
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
		case GitlabMMPackage.COVERAGE_REPORT__COVERAGE_FORMAT:
			setCoverageFormat(COVERAGE_FORMAT_EDEFAULT);
			return;
		case GitlabMMPackage.COVERAGE_REPORT__PATH:
			setPath(PATH_EDEFAULT);
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
		case GitlabMMPackage.COVERAGE_REPORT__COVERAGE_FORMAT:
			return COVERAGE_FORMAT_EDEFAULT == null ? coverageFormat != null
					: !COVERAGE_FORMAT_EDEFAULT.equals(coverageFormat);
		case GitlabMMPackage.COVERAGE_REPORT__PATH:
			return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
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
		result.append(" (coverageFormat: ");
		result.append(coverageFormat);
		result.append(", path: ");
		result.append(path);
		result.append(')');
		return result.toString();
	}

} //CoverageReportImpl
