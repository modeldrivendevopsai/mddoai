/**
 */
package com.mddoai.metamodel.gitlab.gitlabMM.impl;

import com.mddoai.metamodel.gitlab.gitlabMM.ArtifactReports;
import com.mddoai.metamodel.gitlab.gitlabMM.CoverageReport;
import com.mddoai.metamodel.gitlab.gitlabMM.GitlabMMPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Artifact Reports</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactReportsImpl#getJunit <em>Junit</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactReportsImpl#getCoverageReport <em>Coverage Report</em>}</li>
 *   <li>{@link com.mddoai.metamodel.gitlab.gitlabMM.impl.ArtifactReportsImpl#getDotenv <em>Dotenv</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArtifactReportsImpl extends MinimalEObjectImpl.Container implements ArtifactReports {
	/**
	 * The cached value of the '{@link #getJunit() <em>Junit</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJunit()
	 * @generated
	 * @ordered
	 */
	protected EList<String> junit;

	/**
	 * The cached value of the '{@link #getCoverageReport() <em>Coverage Report</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoverageReport()
	 * @generated
	 * @ordered
	 */
	protected CoverageReport coverageReport;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArtifactReportsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GitlabMMPackage.Literals.ARTIFACT_REPORTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<String> getJunit() {
		if (junit == null) {
			junit = new EDataTypeUniqueEList<String>(String.class, this, GitlabMMPackage.ARTIFACT_REPORTS__JUNIT);
		}
		return junit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CoverageReport getCoverageReport() {
		return coverageReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCoverageReport(CoverageReport newCoverageReport, NotificationChain msgs) {
		CoverageReport oldCoverageReport = coverageReport;
		coverageReport = newCoverageReport;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GitlabMMPackage.ARTIFACT_REPORTS__COVERAGE_REPORT, oldCoverageReport, newCoverageReport);
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
	public void setCoverageReport(CoverageReport newCoverageReport) {
		if (newCoverageReport != coverageReport) {
			NotificationChain msgs = null;
			if (coverageReport != null)
				msgs = ((InternalEObject) coverageReport).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.ARTIFACT_REPORTS__COVERAGE_REPORT, null, msgs);
			if (newCoverageReport != null)
				msgs = ((InternalEObject) newCoverageReport).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - GitlabMMPackage.ARTIFACT_REPORTS__COVERAGE_REPORT, null, msgs);
			msgs = basicSetCoverageReport(newCoverageReport, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ARTIFACT_REPORTS__COVERAGE_REPORT,
					newCoverageReport, newCoverageReport));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GitlabMMPackage.ARTIFACT_REPORTS__DOTENV, oldDotenv,
					dotenv));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GitlabMMPackage.ARTIFACT_REPORTS__COVERAGE_REPORT:
			return basicSetCoverageReport(null, msgs);
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
		case GitlabMMPackage.ARTIFACT_REPORTS__JUNIT:
			return getJunit();
		case GitlabMMPackage.ARTIFACT_REPORTS__COVERAGE_REPORT:
			return getCoverageReport();
		case GitlabMMPackage.ARTIFACT_REPORTS__DOTENV:
			return getDotenv();
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
		case GitlabMMPackage.ARTIFACT_REPORTS__JUNIT:
			getJunit().clear();
			getJunit().addAll((Collection<? extends String>) newValue);
			return;
		case GitlabMMPackage.ARTIFACT_REPORTS__COVERAGE_REPORT:
			setCoverageReport((CoverageReport) newValue);
			return;
		case GitlabMMPackage.ARTIFACT_REPORTS__DOTENV:
			setDotenv((String) newValue);
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
		case GitlabMMPackage.ARTIFACT_REPORTS__JUNIT:
			getJunit().clear();
			return;
		case GitlabMMPackage.ARTIFACT_REPORTS__COVERAGE_REPORT:
			setCoverageReport((CoverageReport) null);
			return;
		case GitlabMMPackage.ARTIFACT_REPORTS__DOTENV:
			setDotenv(DOTENV_EDEFAULT);
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
		case GitlabMMPackage.ARTIFACT_REPORTS__JUNIT:
			return junit != null && !junit.isEmpty();
		case GitlabMMPackage.ARTIFACT_REPORTS__COVERAGE_REPORT:
			return coverageReport != null;
		case GitlabMMPackage.ARTIFACT_REPORTS__DOTENV:
			return DOTENV_EDEFAULT == null ? dotenv != null : !DOTENV_EDEFAULT.equals(dotenv);
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
		result.append(", dotenv: ");
		result.append(dotenv);
		result.append(')');
		return result.toString();
	}

} //ArtifactReportsImpl
