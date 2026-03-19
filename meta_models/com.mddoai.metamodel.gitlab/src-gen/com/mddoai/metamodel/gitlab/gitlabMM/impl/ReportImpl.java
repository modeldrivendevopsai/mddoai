/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;
import com.mddoai.metamodel.gitlab.gitlabMM.Report;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Report</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ReportImpl#getJunit <em>Junit</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ReportImpl#getCoverageReport <em>Coverage Report</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ReportImpl#getDotenv <em>Dotenv</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ReportImpl#getCobertura <em>Cobertura</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReportImpl extends MinimalEObjectImpl.Container implements Report {
	/**
	 * The default value of the '{@link #getJunit() <em>Junit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJunit()
	 * @generated
	 * @ordered
	 */
	protected static final String JUNIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJunit() <em>Junit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJunit()
	 * @generated
	 * @ordered
	 */
	protected String junit = JUNIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getCoverageReport() <em>Coverage Report</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoverageReport()
	 * @generated
	 * @ordered
	 */
	protected static final String COVERAGE_REPORT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCoverageReport() <em>Coverage Report</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoverageReport()
	 * @generated
	 * @ordered
	 */
	protected String coverageReport = COVERAGE_REPORT_EDEFAULT;

	/**
	 * The default value of the '{@link #getDotenv() <em>Dotenv</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDotenv()
	 * @generated
	 * @ordered
	 */
	protected static final String DOTENV_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDotenv() <em>Dotenv</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDotenv()
	 * @generated
	 * @ordered
	 */
	protected String dotenv = DOTENV_EDEFAULT;

	/**
	 * The default value of the '{@link #getCobertura() <em>Cobertura</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCobertura()
	 * @generated
	 * @ordered
	 */
	protected static final String COBERTURA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCobertura() <em>Cobertura</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCobertura()
	 * @generated
	 * @ordered
	 */
	protected String cobertura = COBERTURA_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GitlabMMPackage.Literals.REPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getJunit() {
		return junit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setJunit(String newJunit) {
		String oldJunit = junit;
		junit = newJunit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.REPORT__JUNIT, oldJunit, junit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCoverageReport() {
		return coverageReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCoverageReport(String newCoverageReport) {
		String oldCoverageReport = coverageReport;
		coverageReport = newCoverageReport;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.REPORT__COVERAGE_REPORT,
					oldCoverageReport, coverageReport));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDotenv() {
		return dotenv;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDotenv(String newDotenv) {
		String oldDotenv = dotenv;
		dotenv = newDotenv;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.REPORT__DOTENV, oldDotenv, dotenv));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCobertura() {
		return cobertura;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCobertura(String newCobertura) {
		String oldCobertura = cobertura;
		cobertura = newCobertura;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.REPORT__COBERTURA, oldCobertura,
					cobertura));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GitlabMMPackage.REPORT__JUNIT:
			return getJunit();
		case GitlabMMPackage.REPORT__COVERAGE_REPORT:
			return getCoverageReport();
		case GitlabMMPackage.REPORT__DOTENV:
			return getDotenv();
		case GitlabMMPackage.REPORT__COBERTURA:
			return getCobertura();
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
		case GitlabMMPackage.REPORT__JUNIT:
			setJunit((String) newValue);
			return;
		case GitlabMMPackage.REPORT__COVERAGE_REPORT:
			setCoverageReport((String) newValue);
			return;
		case GitlabMMPackage.REPORT__DOTENV:
			setDotenv((String) newValue);
			return;
		case GitlabMMPackage.REPORT__COBERTURA:
			setCobertura((String) newValue);
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
		case GitlabMMPackage.REPORT__JUNIT:
			setJunit(JUNIT_EDEFAULT);
			return;
		case GitlabMMPackage.REPORT__COVERAGE_REPORT:
			setCoverageReport(COVERAGE_REPORT_EDEFAULT);
			return;
		case GitlabMMPackage.REPORT__DOTENV:
			setDotenv(DOTENV_EDEFAULT);
			return;
		case GitlabMMPackage.REPORT__COBERTURA:
			setCobertura(COBERTURA_EDEFAULT);
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
		case GitlabMMPackage.REPORT__JUNIT:
			return JUNIT_EDEFAULT == null ? junit != null : !JUNIT_EDEFAULT.equals(junit);
		case GitlabMMPackage.REPORT__COVERAGE_REPORT:
			return COVERAGE_REPORT_EDEFAULT == null ? coverageReport != null
					: !COVERAGE_REPORT_EDEFAULT.equals(coverageReport);
		case GitlabMMPackage.REPORT__DOTENV:
			return DOTENV_EDEFAULT == null ? dotenv != null : !DOTENV_EDEFAULT.equals(dotenv);
		case GitlabMMPackage.REPORT__COBERTURA:
			return COBERTURA_EDEFAULT == null ? cobertura != null : !COBERTURA_EDEFAULT.equals(cobertura);
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
		result.append(" (junit: ");
		result.append(junit);
		result.append(", coverageReport: ");
		result.append(coverageReport);
		result.append(", dotenv: ");
		result.append(dotenv);
		result.append(", cobertura: ");
		result.append(cobertura);
		result.append(')');
		return result.toString();
	}

} //ReportImpl
