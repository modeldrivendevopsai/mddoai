/**
 */
package com.mddoai.metamodel.azuredevops.azuredevopsMM.impl;

import com.mddoai.metamodel.azuredevops.azuredevopsMM.AzuredevopsMMPackage;
import com.mddoai.metamodel.azuredevops.azuredevopsMM.DownloadStep;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Download Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadStepImpl#getDownload <em>Download</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadStepImpl#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link com.mddoai.metamodel.azuredevops.azuredevopsMM.impl.DownloadStepImpl#getPatterns <em>Patterns</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DownloadStepImpl extends StepImpl implements DownloadStep {
	/**
	 * The default value of the '{@link #getDownload() <em>Download</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDownload()
	 * @generated
	 * @ordered
	 */
	protected static final String DOWNLOAD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDownload() <em>Download</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDownload()
	 * @generated
	 * @ordered
	 */
	protected String download = DOWNLOAD_EDEFAULT;

	/**
	 * The default value of the '{@link #getArtifact() <em>Artifact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifact()
	 * @generated
	 * @ordered
	 */
	protected static final String ARTIFACT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArtifact() <em>Artifact</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifact()
	 * @generated
	 * @ordered
	 */
	protected String artifact = ARTIFACT_EDEFAULT;

	/**
	 * The default value of the '{@link #getPatterns() <em>Patterns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPatterns()
	 * @generated
	 * @ordered
	 */
	protected static final String PATTERNS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPatterns() <em>Patterns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPatterns()
	 * @generated
	 * @ordered
	 */
	protected String patterns = PATTERNS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DownloadStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AzuredevopsMMPackage.Literals.DOWNLOAD_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDownload() {
		return download;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDownload(String newDownload) {
		String oldDownload = download;
		download = newDownload;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DOWNLOAD_STEP__DOWNLOAD,
					oldDownload, download));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getArtifact() {
		return artifact;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setArtifact(String newArtifact) {
		String oldArtifact = artifact;
		artifact = newArtifact;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DOWNLOAD_STEP__ARTIFACT,
					oldArtifact, artifact));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPatterns() {
		return patterns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPatterns(String newPatterns) {
		String oldPatterns = patterns;
		patterns = newPatterns;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AzuredevopsMMPackage.DOWNLOAD_STEP__PATTERNS,
					oldPatterns, patterns));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AzuredevopsMMPackage.DOWNLOAD_STEP__DOWNLOAD:
			return getDownload();
		case AzuredevopsMMPackage.DOWNLOAD_STEP__ARTIFACT:
			return getArtifact();
		case AzuredevopsMMPackage.DOWNLOAD_STEP__PATTERNS:
			return getPatterns();
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
		case AzuredevopsMMPackage.DOWNLOAD_STEP__DOWNLOAD:
			setDownload((String) newValue);
			return;
		case AzuredevopsMMPackage.DOWNLOAD_STEP__ARTIFACT:
			setArtifact((String) newValue);
			return;
		case AzuredevopsMMPackage.DOWNLOAD_STEP__PATTERNS:
			setPatterns((String) newValue);
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
		case AzuredevopsMMPackage.DOWNLOAD_STEP__DOWNLOAD:
			setDownload(DOWNLOAD_EDEFAULT);
			return;
		case AzuredevopsMMPackage.DOWNLOAD_STEP__ARTIFACT:
			setArtifact(ARTIFACT_EDEFAULT);
			return;
		case AzuredevopsMMPackage.DOWNLOAD_STEP__PATTERNS:
			setPatterns(PATTERNS_EDEFAULT);
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
		case AzuredevopsMMPackage.DOWNLOAD_STEP__DOWNLOAD:
			return DOWNLOAD_EDEFAULT == null ? download != null : !DOWNLOAD_EDEFAULT.equals(download);
		case AzuredevopsMMPackage.DOWNLOAD_STEP__ARTIFACT:
			return ARTIFACT_EDEFAULT == null ? artifact != null : !ARTIFACT_EDEFAULT.equals(artifact);
		case AzuredevopsMMPackage.DOWNLOAD_STEP__PATTERNS:
			return PATTERNS_EDEFAULT == null ? patterns != null : !PATTERNS_EDEFAULT.equals(patterns);
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
		result.append(" (download: ");
		result.append(download);
		result.append(", artifact: ");
		result.append(artifact);
		result.append(", patterns: ");
		result.append(patterns);
		result.append(')');
		return result.toString();
	}

} //DownloadStepImpl
